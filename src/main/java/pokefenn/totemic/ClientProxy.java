package pokefenn.totemic;

import net.minecraft.client.Minecraft;
import net.minecraft.world.ColorizerFoliage;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import pokefenn.totemic.client.rendering.entity.BaldEagleRendering;
import pokefenn.totemic.client.rendering.entity.BaykokRendering;
import pokefenn.totemic.client.rendering.entity.BuffaloRendering;
import pokefenn.totemic.client.rendering.entity.InvisArrowRendering;
import pokefenn.totemic.client.rendering.model.ModelTotemPole;
import pokefenn.totemic.client.rendering.tileentity.TileWindChimeRenderer;
import pokefenn.totemic.entity.animal.EntityBaldEagle;
import pokefenn.totemic.entity.animal.EntityBuffalo;
import pokefenn.totemic.entity.boss.EntityBaykok;
import pokefenn.totemic.entity.projectile.EntityInvisArrow;
import pokefenn.totemic.handler.GameOverlay;
import pokefenn.totemic.handler.PlayerRender;
import pokefenn.totemic.init.ModBlocks;
import pokefenn.totemic.tileentity.music.TileWindChime;
import pokefenn.totemic.totempedia.LexiconData;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        OBJLoader.INSTANCE.addDomain(Totemic.MOD_ID);
        ModelLoaderRegistry.registerLoader(ModelTotemPole.Loader.INSTANCE);
        RenderingRegistry.registerEntityRenderingHandler(EntityBuffalo.class, BuffaloRendering::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInvisArrow.class, InvisArrowRendering::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBaykok.class, BaykokRendering::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBaldEagle.class, BaldEagleRendering::new);
        initTESRs();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        registerBlockColors();
        LexiconData.init();
    }

    @Override
    protected void registerEventHandlers()
    {
        super.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(new GameOverlay());
        MinecraftForge.EVENT_BUS.register(new PlayerRender());
    }

    private static int getTotemPolePaintColor(int tintIndex)
    {
        switch(tintIndex)
        {
        case 1: return 0x555555; //Black
        case 2: return 0xAA5555; //Red
        case 3: return 0xAA55EE; //Purple
        case 4: return 0xBBBB66; //Yellow

        default: return -1;
        }
    }

    private void registerBlockColors()
    {
        Minecraft mc = Minecraft.getMinecraft();
        mc.getBlockColors().registerBlockColorHandler(
                (state, world, pos, tintIndex) -> ColorizerFoliage.getFoliageColorPine(), ModBlocks.cedar_leaves);
        mc.getBlockColors().registerBlockColorHandler(
                (state, world, pos, tintIndex) -> getTotemPolePaintColor(tintIndex), ModBlocks.totem_pole);
        mc.getItemColors().registerItemColorHandler(
                (stack, tintIndex) -> getTotemPolePaintColor(tintIndex), ModBlocks.totem_pole);
    }

    private void initTESRs()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileWindChime.class, new TileWindChimeRenderer());
    }
}
