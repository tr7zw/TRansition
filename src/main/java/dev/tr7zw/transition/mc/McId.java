package dev.tr7zw.transition.mc;

import net.minecraft.resources.*;

public record McId(Identifier id) {

    public static McId create(String namespace, String path) {
        //$ mc_id_create_namespace_path
        return new McId(Identifier.fromNamespaceAndPath(namespace, path));
    }

    public static McId create(String id) {
        //$ mc_id_create_string
        return new McId(Identifier.parse(id));
    }
}
