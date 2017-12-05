package betterwithbooze;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import betterwithbooze.fluids.FluidBooze;
import betterwithbooze.item.ItemMug;
import betterwithbooze.potion.PotionAle;
import betterwithbooze.util.CaskManager;
import betterwithbooze.util.CaskManager.CaskLiquid;
import betterwithbooze.util.Nope;

import java.util.ArrayList;

public class Registry {
    private static ArrayList<Block> BLOCKS = new ArrayList<>();
    private static ArrayList<Item> ITEMS = new ArrayList<>();

    @GameRegistry.ObjectHolder("betterwithbooze:ale")
    public static Potion POTION_ALE;

    public static Fluid ALE;

    public static void preInit()
    {
        MinecraftForge.EVENT_BUS.register(Registry.class);
        registerBlocks();
        registerTileEntities();
        registerFluids();
        registerCapabilities();
    }

    public static void init()
    {
        registerCaskLiquids();
    }

    public static void registerCaskLiquids() 
    {
        CaskManager.register(new CaskLiquid(ALE,2,0xFFE1862C).addEffect(new PotionEffect(POTION_ALE,1200,0),4));
    }

    public static void registerBlocks()
    {
        Nope.shutupForge(Registry::registerOverrides);
    }

    public static void registerFluids()
    {
        //Alcohol itself. Cold.
        FluidRegistry.registerFluid(ALE = new Fluid("ale",new ResourceLocation(BetterWithBooze.MODID,"blocks/ale"),new ResourceLocation(BetterWithBooze.MODID,"blocks/ale_flowing")));

    public static void registerBlockModels()
    {
        for (Block block : MODELLED_BLOCKS) {
            BetterWithBooze.proxy.registerBlockModel(block);
        }
    }

    public static void registerItemModels()
    {
        for (Item item : MODELLED_ITEMS) {
            BetterWithBooze.proxy.registerItemModel(item);
        }
    }

    public static void registerBlock(String id,Block block, ItemBlock itemBlock)
    {
        block.setRegistryName(BetterWithBooze.MODID,id);
        block.setUnlocalizedName(id);
        registerBlock(block,true);
        registerItem(id,itemBlock);
    }

    public static void registerBlock(Block block, boolean hasmodel)
    {
        BLOCKS.add(block);
        if(hasmodel)
            MODELLED_BLOCKS.add(block);
    }

    public static void registerItem(String id,Item item)
    {
        item.setRegistryName(BetterWithBooze.MODID,id);
        item.setUnlocalizedName(id);
        registerItem(item,true);
    }

    public static void registerItem(Item item, boolean hasmodel)
    {
        ITEMS.add(item);
        if(hasmodel)
            MODELLED_ITEMS.add(item);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS) {
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(new PotionAle().setRegistryName(BetterWithBooze.MODID,"ale"));
    }

    private static void registerTileEntity(Class<? extends TileEntity> tile)
    {
        GameRegistry.registerTileEntity(tile,tile.getSimpleName().toLowerCase());
    }
}