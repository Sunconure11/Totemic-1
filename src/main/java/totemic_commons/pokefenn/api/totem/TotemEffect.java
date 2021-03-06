package totemic_commons.pokefenn.api.totem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Base class for all Totem Effects
 */
public abstract class TotemEffect
{
    protected final String name;
    protected final boolean portable;

    /**
     * @param name a unique name for the Totem Effect
     * @param portable whether this Totem Effect can be used with a Medicine Bag.
     * In this case, override {@link #medicineBagEffect}.
     */
    public TotemEffect(String name, boolean portable)
    {
        this.name = name;
        this.portable = portable;
    }

    /**
     * Performs the Totem effect at the given Totem base position.<p>
     * This gets called on the server and the client.
     * @param totem the Totem Base tile entity
     * @param repetition the number of Totem Pole blocks that are carved with this effect
     */
    public abstract void effect(World world, BlockPos pos, TotemBase totem, int repetition);

    /**
     * Performs the Totem effect to the given player, if applicable.
     * Override this method to make your effect work with Medicine Bags.<p>
     * This gets called on the server and the client.
     * @param charge time in ticks until the Medicine Bag is depleted
     */
    public void medicineBagEffect(World world, EntityPlayer player, int charge)
    { }

    /**
     * @return the Totem Effect's name
     */
    public final String getName()
    {
        return name;
    }

    /**
     * @return whether this Totem Effect can be used with a Medicine Bag
     */
    public final boolean isPortable()
    {
        return portable;
    }

    /**
     * @return the unlocalized name of the Effect, which by default
     * is given by "totemic.totem." followed by the name
     */
    public String getUnlocalizedName()
    {
        return "totemic.totem." + name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
