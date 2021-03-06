package totemic_commons.pokefenn.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import totemic_commons.pokefenn.network.SynchronizedPacketBase;
import totemic_commons.pokefenn.tileentity.totem.TileTotemBase;

public class PacketTotemPoleChange extends SynchronizedPacketBase<PacketTotemPoleChange>
{
    private BlockPos pos;

    public PacketTotemPoleChange(BlockPos pos)
    {
        this.pos = pos;
    }

    public PacketTotemPoleChange() {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(pos.toLong());
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void handleClient(MessageContext ctx)
    {
        TileEntity tile = Minecraft.getMinecraft().theWorld.getTileEntity(pos);
        if(tile instanceof TileTotemBase)
        {
            ((TileTotemBase) tile).onPoleChange();
        }
    }
}
