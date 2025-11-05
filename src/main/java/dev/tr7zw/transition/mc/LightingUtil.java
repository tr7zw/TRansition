package dev.tr7zw.transition.mc;

//? if >= 1.21.6 {

import com.mojang.blaze3d.platform.Lighting.Entry;
//? } else {
/*
import com.mojang.blaze3d.platform.Lighting;
*///? }

import net.minecraft.client.Minecraft;

public class LightingUtil {

    public static void prepareLightingEntity() {
        //? if >= 1.21.6 {

        Minecraft.getInstance().gameRenderer.getLighting().setupFor(Entry.ENTITY_IN_UI);
        //? } else if >= 1.17.0 {
        /*
        Lighting.setupForEntityInInventory();
        *///? } else {
        /*
         Lighting.setupForFlatItems();
        *///? }
    }

}
