package totemic_commons.pokefenn.misc;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import totemic_commons.pokefenn.ModItems;
import totemic_commons.pokefenn.item.ItemTotemicItems;

/**
 * Created with IntelliJ IDEA.
 * User: Pokefenn
 * Date: 23/12/13
 * Time: 13:54
 */
public class OreDictionaryTotemic
{

    public static void init()
    {
        OreDictionary.registerOre("cropVine", new ItemStack(Blocks.vine));
        OreDictionary.registerOre("infusedLeaf", new ItemStack(ModItems.subItems, 1, ItemTotemicItems.leaf));
        //OreDictionary.registerOre("crystalVerdant", new ItemStack(ModItems.verdantCrystal, 1, OreDictionary.WILDCARD_VALUE));
        //OreDictionary.registerOre("blazingCrystalVerdant", new ItemStack(ModItems.blazingChlorophyllCrystal, 1, OreDictionary.WILDCARD_VALUE));

    }
}