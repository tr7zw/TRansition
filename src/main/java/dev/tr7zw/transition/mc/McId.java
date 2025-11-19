package dev.tr7zw.transition.mc;

import net.minecraft.resources.*;

public record McId(
        //? if >= 1.21.11 {
        Identifier id

//? } else {
/*
        ResourceLocation id
*///? }
) {

    public static McId create(String namespace, String path) {
        //? if >= 1.21.11 {

        return new McId(Identifier.fromNamespaceAndPath(namespace, path));
        //? } else if >= 1.21.0 {
        /*
        return new McId(ResourceLocation.fromNamespaceAndPath(namespace, path));
        *///? } else {
        /*
         return new McId(new ResourceLocation(namespace, path));
        *///? }
    }

    public static McId create(String id) {
        //? if >= 1.21.11 {

        return new McId(Identifier.parse(id));
        //? } else if >= 1.21.0 {
        /*
        return new McId(ResourceLocation.parse(id));
        *///? } else {
        /*
         return new McId(new ResourceLocation(id));
        *///? }
    }
}
