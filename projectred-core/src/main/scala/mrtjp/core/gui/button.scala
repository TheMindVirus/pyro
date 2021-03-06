/*
 * Copyright (c) 2014.
 * Created by MrTJP.
 * All rights reserved.
 */
package mrtjp.core.gui

import codechicken.lib.texture.TextureUtils
import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.systems.RenderSystem
import mrtjp.core.vec.{Point, Rect, Size}
import net.minecraft.client.audio.SimpleSound
import net.minecraft.client.gui.AbstractGui
import net.minecraft.util.SoundEvents
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.fml.client.gui.GuiUtils

import scala.jdk.CollectionConverters._
import scala.collection.mutable.ListBuffer

/**
 * Base button class with position and width/height. Doesnt render anything, nor does it perform
 * action when clicked.
 */
class ButtonNode extends TNode
{
    var size = Size.zeroSize
    override def frame = Rect(position, size)

    var clickDelegate = {() => ()}
    var tooltipBuilder = {(_:ListBuffer[String]) => ()}
    var drawFunction = {() => ()}

    var mouseoverLock =  false

    override def mouseClicked_Impl(p:Point, button:Int, consumed:Boolean) =
    {
        if (!consumed && rayTest(p))
        {
            soundHandler.play(SimpleSound.forUI(SoundEvents.UI_BUTTON_CLICK, 1))
            onButtonClicked()
            true
        }
        else false
    }

    def onButtonClicked(): AnyVal = 
    {
        clickDelegate()
    }

    override def drawBack_Impl(stack:MatrixStack, mouse:Point, rframe:Float): AnyVal = 
    {
        RenderSystem.color4f(1, 1, 1, 1)
        val mouseover = mouseoverLock || (frame.contains(mouse) && rayTest(mouse))
        drawButtonBackground(stack, mouseover)
        drawButton(stack, mouseover)
    }

    override def drawFront_Impl(stack:MatrixStack, mouse:Point, rframe:Float): AnyVal = 
    {
        if (rayTest(mouse))
        {
            val list = new ListBuffer[String]
            tooltipBuilder(list)

            //draw tooltip with absolute coords to allow it to force-fit on screen
            translateToScreen()
            val Point(mx, my) = parent.convertPointToScreen(mouse)
            val root = getRoot
            GuiUtils.drawHoveringText(stack, list.map(new StringTextComponent(_)).asJava, mx+12, my-12, root.width, root.height, -1, getFontRenderer)
            translateFromScreen()
        }
    }

    def drawButtonBackground(stack:MatrixStack, mouseover:Boolean): AnyVal = {}
    def drawButton(stack:MatrixStack, mouseover:Boolean): AnyVal = {}
}

/**
 * Trait for buttons that renders their background as a default MC button.
 */
trait TButtonMC extends ButtonNode
{
    abstract override def drawButtonBackground(stack:MatrixStack, mouseover:Boolean): AnyVal = 
    {
        super.drawButtonBackground(stack, mouseover)

        TextureUtils.changeTexture(GuiLib.guiTex)

        RenderSystem.color4f(1, 1, 1, 1)
        val state = if (mouseover) 2 else 1

        blit(stack, position.x, position.y, 0, 46+state*20, size.width/2, size.height/2)
        blit(stack, position.x+size.width/2, position.y, 200-size.width/2, 46+state*20, size.width/2, size.height/2)
        blit(stack, position.x, position.y+size.height/2, 0, 46+state*20+20-size.height/2, size.width/2, size.height/2)
        blit(stack, position.x+size.width/2, position.y+size.height/2, 200-size.width/2, 46+state*20+20-size.height/2, size.width/2, size.height/2)
    }
}

/**
 * Trait for buttons that renders their foreground as text.
 */
trait TButtonText extends ButtonNode
{
    var text = ""
    def setText(t:String):this.type = {text = t; this}

    abstract override def drawButton(stack:MatrixStack, mouseover:Boolean): AnyVal = 
    {
        super.drawButton(stack, mouseover)
        AbstractGui.drawCenteredString(stack, getFontRenderer, text, position.x+size.width/2, position.y+(size.height-8)/2, if (mouseover) 0xFFFFFFA0 else 0xFFE0E0E0)
        RenderSystem.color4f(1, 1, 1, 1)
    }
}

/**
 * Button that is used for selection.
 */
class DotSelectNode extends ButtonNode
{
    size = Size(8, 8)

    override def drawButtonBackground(stack:MatrixStack, mouseover:Boolean): AnyVal = 
    {
        super.drawButtonBackground(stack, mouseover)
        TextureUtils.changeTexture(GuiLib.guiExtras)
        RenderSystem.color4f(1, 1, 1, 1)
        blit(stack, position.x, position.y, if (mouseover) 11 else 1, 1, 8, 8)
    }
}

object DotSelectNode
{
    def centered(x:Int, y:Int) =
    {
        val b = new DotSelectNode
        b.position = Point(x, y)-4
        b
    }
}

/**
 * Check box button that has either an on or off state.
 */
class CheckBoxNode extends ButtonNode with TButtonMC
{
    size = Size(14, 14)

    var state = false

    override def drawButton(stack:MatrixStack, mouseover:Boolean): AnyVal = 
    {
        super.drawButton(stack, mouseover)
        TextureUtils.changeTexture(GuiLib.guiExtras)
        val u = if (state) 17 else 1
        blit(stack, position.x, position.y, u, 134, 14, 14)
    }

    override def onButtonClicked(): AnyVal = 
    {
        state = !state
        super.onButtonClicked()
    }
}

object CheckBoxNode
{
    def centered(x:Int, y:Int) =
    {
        val b = new CheckBoxNode
        b.position = Point(x, y)-7
        b
    }
}

/**
  * Default implementation of a button in mc with normal render and text overlay
  */
class MCButtonNode extends ButtonNode with TButtonMC with TButtonText

/**
  * Implementation of a button with manual icon rendering via override.
  */
class IconButtonNode extends ButtonNode with TButtonMC
