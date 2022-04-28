package mrtjp.projectred.core

import net.minecraftforge.fml.event.lifecycle.{FMLClientSetupEvent, FMLCommonSetupEvent, FMLDedicatedServerSetupEvent, FMLLoadCompleteEvent}

trait IProxy {

    def construct(): AnyVal = {}

    def commonSetup(event: FMLCommonSetupEvent): AnyVal = {}

    def clientSetup(event: FMLClientSetupEvent): AnyVal = {}

    def serverSetup(event: FMLDedicatedServerSetupEvent): AnyVal = {}

    def loadComplete(event: FMLLoadCompleteEvent): AnyVal = {}
}
