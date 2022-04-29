package net.fabricmc.mod.mixin;

import net.fabricmc.mod.Mod;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ModMixin
{
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info)
    {
        Mod.DEBUG.info("[WARN]: Hello from Mixin.java!");
    }
}
