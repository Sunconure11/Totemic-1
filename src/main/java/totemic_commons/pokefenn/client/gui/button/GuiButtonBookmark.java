package totemic_commons.pokefenn.client.gui.button;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import totemic_commons.pokefenn.client.RenderHelper;
import totemic_commons.pokefenn.client.gui.GuiLexicon;

public class GuiButtonBookmark extends GuiButton
{
    private GuiLexicon gui = new GuiLexicon();

    public GuiButtonBookmark(int par1, int par2, int par3, GuiLexicon gui, String str)
    {
        super(par1, par2, par3, gui.bookmarkWidth(str) + 5, 11, str);
    }

    @Override
    public void drawButton(Minecraft mc, int par2, int par3)
    {
        gui.drawBookmark(xPosition, yPosition, displayString, false);
        hovered = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
        int k = getHoverState(hovered);

        List<String> tooltip = new ArrayList<>();
        if(displayString.equals("+"))
            tooltip.add(I18n.format("totemicmisc.clickToAdd"));
        else
        {
            tooltip.add(I18n.format("totemicmisc.bookmark", id - GuiLexicon.BOOKMARK_START + 1));
            tooltip.add(TextFormatting.GRAY + I18n.format("totemicmisc.clickToSee"));
            tooltip.add(TextFormatting.GRAY + I18n.format("totemicmisc.shiftToRemove"));
        }

        int tooltipY = (tooltip.size() + 1) * 5;
        if(k == 2)
            RenderHelper.renderTooltip(par2, par3 + tooltipY, tooltip);
    }

}
