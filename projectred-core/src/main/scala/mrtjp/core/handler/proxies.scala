/*
 * Copyright (c) 2014.
 * Created by MrTJP.
 * All rights reserved.
 */
package mrtjp.core.handler

import mrtjp.core.world.Messenger
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.event.lifecycle.{FMLClientSetupEvent, FMLCommonSetupEvent, FMLDedicatedServerSetupEvent, FMLLoadCompleteEvent}

class MrTJPCoreProxy_server {
    def commonSetup(event: FMLCommonSetupEvent): AnyVal =  {}

    def clientSetup(event: FMLClientSetupEvent): AnyVal =  {}

    def serverSetup(event: FMLDedicatedServerSetupEvent): AnyVal =  {}

    def loadComplete(event: FMLLoadCompleteEvent): AnyVal =  {}
}

class MrTJPCoreProxy_client extends MrTJPCoreProxy_server {


    override def clientSetup(event: FMLClientSetupEvent): Unit = {
        super.clientSetup(event)
        MinecraftForge.EVENT_BUS.register(Messenger)
    }
}

object MrTJPCoreProxy extends MrTJPCoreProxy_client
