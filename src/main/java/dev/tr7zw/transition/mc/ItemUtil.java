package dev.tr7zw.transition.mc;

import java.util.Set;
import java.util.Map.Entry;

import com.mojang.authlib.GameProfile;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

//? if >= 1.19.3 {

import net.minecraft.core.registries.BuiltInRegistries;
//? } else {
/*
 import net.minecraft.core.Registry;
*///? }
   //? if <= 1.20.4 {
   /*
    import net.minecraft.Util;
    import org.apache.commons.lang3.StringUtils;
    import net.minecraft.nbt.CompoundTag;
    import net.minecraft.nbt.NbtUtils;
   *///? } else {

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.ResolvableProfile;
//? }

@UtilityClass
public class ItemUtil {

    public static Item getItem(ResourceLocation key) {
        //? if >= 1.21.2 {

        return BuiltInRegistries.ITEM.get(key).map(net.minecraft.core.Holder.Reference::value).orElse(Items.AIR);
        //? } else if >= 1.19.3 {
        /*
         return BuiltInRegistries.ITEM.get(key);
        *///? } else {
        /*
         return Registry.ITEM.get(key);
        *///? }
    }

    public static Set<Entry<ResourceKey<Item>, Item>> getItems() {
        //? if >= 1.19.3 {

        return BuiltInRegistries.ITEM.entrySet();
        //? } else {
        /*
         return Registry.ITEM.entrySet();
        *///? }
    }

    public static GameProfile getGameProfile(ItemStack itemStack) {
        //? if >= 1.21.9 {

        if (itemStack.getComponents().has(DataComponents.CUSTOM_MODEL_DATA)) {
            return null;
        }
        if (itemStack.getComponents().has(DataComponents.PROFILE)) {
            ResolvableProfile resolvableProfile = (ResolvableProfile) itemStack.get(DataComponents.PROFILE);
            return Minecraft.getInstance().playerSkinRenderCache().getOrDefault(resolvableProfile).gameProfile();
        }
        return null;
        //? } else if >= 1.20.5 {
        /*
        if (itemStack.getComponents().has(DataComponents.CUSTOM_MODEL_DATA)) {
            return null;
        }
        if (itemStack.getComponents().has(DataComponents.PROFILE)) {
            ResolvableProfile resolvableProfile = (ResolvableProfile) itemStack.get(DataComponents.PROFILE);
            if (resolvableProfile != null && !resolvableProfile.isResolved()) {
                itemStack.remove(DataComponents.PROFILE);
                resolvableProfile.resolve().thenAcceptAsync(
                        resolvableProfile2 -> itemStack.set(DataComponents.PROFILE, resolvableProfile2),
                        Minecraft.getInstance());
                resolvableProfile = null;
            }
            if (resolvableProfile != null) {
                return resolvableProfile.gameProfile();
            }
        }
        return null;
        *///? } else {
        /*
         if (itemStack.hasTag()) {
             CompoundTag compoundTag = itemStack.getTag();
             if (compoundTag.contains("CustomModelData")) {
                 return null; // do not try to 3d-fy custom head models
             }
             if (compoundTag.contains("SkullOwner", 10)) {
                 return NbtUtils.readGameProfile(compoundTag.getCompound("SkullOwner"));
             } else if (compoundTag.contains("SkullOwner", 8)
                     && !StringUtils.isBlank(compoundTag.getString("SkullOwner"))) {
                 return new GameProfile(Util.NIL_UUID, compoundTag.getString("SkullOwner"));
             }
         }
         return null;
        *///? }
    }

    public static boolean isSame(ItemStack a, ItemStack b) {
        //? if < 1.17.0 {
        /*
         return ItemStack.isSame(a, b);
        *///? } else if <= 1.20.4 {
        /*
         return ItemStack.isSameItemSameTags(a, b);
        *///? } else {

        return ItemStack.isSameItemSameComponents(a, b);
        //? }
    }

    public static boolean hasCustomName(ItemStack stack) {
        //? if <= 1.20.4 {
        /*
         return stack.hasCustomHoverName();
        *///? } else {

        return stack.has(net.minecraft.core.component.DataComponents.CUSTOM_NAME);
        //? }
    }

}
