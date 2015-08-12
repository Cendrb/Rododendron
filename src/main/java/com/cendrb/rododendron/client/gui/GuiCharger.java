package com.cendrb.rododendron.client.gui;

import com.cendrb.rododendron.container.ContainerCharger;
import com.cendrb.rododendron.reference.Reference;
import com.cendrb.rododendron.tile_entity.TileEntityCharger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */
public class GuiCharger extends GuiContainer {

    public static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID, "textures/gui/charger.png");

    private final TileEntityCharger chargerInventory;
    private final InventoryPlayer playerInventory;

    public GuiCharger(InventoryPlayer player, IInventory chargerInventory) {
        super(new ContainerCharger(player, chargerInventory));
        this.chargerInventory = (TileEntityCharger) chargerInventory;
        this.playerInventory = player;

        xSize = 175;
        ySize = 165;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    protected void actionPerformed(GuiButton p_146284_1_) {
        super.actionPerformed(p_146284_1_);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String inventoryName = I18n.format(chargerInventory.getInventoryName()) + " " + chargerInventory.getChargerTier();
        fontRendererObj.drawString(inventoryName, (xSize / 2) - (fontRendererObj.getStringWidth(inventoryName) / 2), 6, 4210752);
        //fontRendererObj.drawString(I18n.format("container.inventory"), xSize - (fontRendererObj.getStringWidth(I18n.format("container.inventory")) + 6), ySize - 94, 4210752);
        fontRendererObj.drawString(chargerInventory.getChargerEnergy(), 30, 67, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int y = chargerInventory.getChargerEnergyScaled(40);
        drawTexturedModalRect(guiLeft + 11, guiTop + 14 + (40 - y), 176, 0, 10, y);

        int x = chargerInventory.getChargeProgressScaled(24);
        drawTexturedModalRect(guiLeft + 79, guiTop + 35, 176, 40, x, 17);
    }
}
