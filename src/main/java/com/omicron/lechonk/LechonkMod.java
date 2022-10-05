package com.omicron.lechonk;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LechonkMod.MODID)
public class LechonkMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "lechonk";
    // Directly reference a slf4j logger

    public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static RegistryObject<EntityType<Lechonk>> LECHONK_ENTITY = ENTITIES.register("lechonk", () -> EntityType.Builder.of(Lechonk::new, MobCategory.CREATURE)
            .sized(0.6f, 1.95f)
            .clientTrackingRange(8)
            .setShouldReceiveVelocityUpdates(false)
            .build("lechonk"));

    public LechonkMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITIES.register(modEventBus);
        modEventBus.addListener(LechonkMod::onRenderer);
        modEventBus.addListener(LechonkMod::onRegisterLayers);
        modEventBus.addListener(LechonkMod::onAttributeCreate);
    }

    public static void onRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(LECHONK_ENTITY.get(), LechonkRenderer::new);
    }

    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LechonkModel.LAYER_LOCATION, LechonkModel::createBodyLayer);
    }

    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(LECHONK_ENTITY.get(), Lechonk.prepareAttributes().build());
    }
}
