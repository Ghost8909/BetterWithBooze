package betterwithbooze;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.RegistryManager;

@Mod(modid = BetterWithBooze.MODID, version = BetterWithBooze.VERSION, acceptedMinecraftVersions = "[1.12, 1.13)", dependencies = "required-after:betterwithbooze")
@Mod.EventBusSubscriber
public class BetterWithBooze
{
    public static final String MODID = "betterwithbooze";
    public static final String NAME = "BetterWithBooze";
    public static final String VERSION = "0.1";

    @SidedProxy(clientSide = "betterwithbooze.ClientProxy",serverSide = "betterwithbooze.ServerProxy")
    public static IProxy proxy;

    public static CreativeTabs creativeTab;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        creativeTab = new CreativeTabs("betterwithbooze") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(RegistryManager.mug_ale);
            }
        };
        CraftingRegistry.preInit();
        Registry.preInit();
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Registry.init();
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }
}