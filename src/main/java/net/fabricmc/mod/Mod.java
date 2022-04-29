package net.fabricmc.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer
{
    public static final Logger DEBUG = LoggerFactory.getLogger("projectred_rebuilt");

    public void newBlock(String name)
    {
        Identifier id = new Identifier("mod", name);
        Block block = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));
        FabricItemSettings settings = new FabricItemSettings().group(ItemGroup.MISC);
        Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(block, settings));
    }

    @Override
    public void onInitialize()
    {
        DEBUG.info("[INFO]: Hello World!");
        newBlock("example_block");
    }
}
