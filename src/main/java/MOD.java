package com.projectred_rebuild;

//https://mcforge.readthedocs.io/en/1.18.x/concepts/registries/

import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.TimeUnit;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.fml.common.crafting.RecipeType;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.Item;
import net.minecraft.world.inventory.MenuType;
//import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MOD.NAME)
public class MOD
{
    public static final String NAME = "projectred_rebuild";
    public static final Logger DEBUG = LoggerFactory.getLogger(MOD.NAME);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD.NAME);
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MOD.NAME);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD.NAME);
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD.NAME);
    //public static final DeferredRegister<RecipeType<?>> RECIPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, MOD.NAME);
    public static IEventBus EVENTS = null;

    //public static final RegistryObject<Block> SUCKS = MOD.BLOCKS.register("sucks", () -> new Block(AbstractBlock.Properties.create( Material.SUCKS )));

    public MOD()
    {
        MOD.DEBUG.info("[INFO]: Hello World!");
        MOD.EVENTS = FMLJavaModLoadingContext.get().getModEventBus();

        MOD.BLOCKS.register(MOD.EVENTS);
        MOD.TILES.register(MOD.EVENTS);
        MOD.ITEMS.register(MOD.EVENTS);
        MOD.MENUS.register(MOD.EVENTS);
        //MOD.RECIPES.register(MOD.EVENTS);
    }
}
