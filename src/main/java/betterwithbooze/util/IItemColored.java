package betterwithbooze.util;

import net.minecraft.item.ItemStack;

public interface IItemColored {
    public int getColorFromItemstack(ItemStack stack, int tintIndex);
}