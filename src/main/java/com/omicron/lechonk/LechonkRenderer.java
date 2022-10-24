package com.omicron.lechonk;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public class LechonkRenderer extends MobRenderer<Lechonk, LechonkModel<Lechonk>> {

    private static final ResourceLocation LECHONK_LOCATION = new ResourceLocation(LechonkMod.MODID, "textures/entity/lechonk_iron.png");

    public LechonkRenderer(EntityRendererProvider.Context context) {
        super(context, new LechonkModel<>(context.bakeLayer(LechonkModel.LAYER_LOCATION)), 1f);
    }



    @Override
    public ResourceLocation getTextureLocation(Lechonk p_114482_) {
        //System.out.println(p_114482_.TYPE.toString().toLowerCase(Locale.ROOT));
        return new ResourceLocation(LechonkMod.MODID, "textures/entity/lechonk_" + p_114482_.TYPE.toString().toLowerCase(Locale.ROOT) + ".png");
    }
}
