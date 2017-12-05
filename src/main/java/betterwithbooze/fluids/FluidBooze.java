package betterwithbooze.fluids;


import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidBooze extends Fluid {
    public FluidBooze(String fluidName, ResourceLocation still, ResourceLocation flowing) {
        super(fluidName, still, flowing);
        this.setViscosity(1000);
        this.setDensity(1000);
        this.setLuminosity(0);
        this.setTemperature(300);
    }
}