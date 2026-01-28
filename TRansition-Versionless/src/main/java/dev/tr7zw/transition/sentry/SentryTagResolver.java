package dev.tr7zw.transition.sentry;

import lombok.experimental.*;

import java.util.*;

@UtilityClass
public class SentryTagResolver {

    private static final Map<String, String> MAPPINGS = new HashMap<>();
    private static final Set<String> FORBIDDEN_MODS = new HashSet<>();

    static {
        MAPPINGS.put("dev.tr7zw.transition", "TRansition");
        MAPPINGS.put("dev.tr7zw.trender", "TRender");
        MAPPINGS.put("dev.tr7zw.entityculling", "EntityCulling");
        MAPPINGS.put("com.logisticscraft.occlusionculling", "EntityCulling");
        MAPPINGS.put("dev.tr7zw.skinlayers", "3dSkinLayers");
        MAPPINGS.put("dev.tr7zw.armorlayers", "3dArmorLayers");
        MAPPINGS.put("dev.tr7zw.waveycapes", "WaveyCapes");
        MAPPINGS.put("dev.tr7zw.notenoughanimations", "NotEnoughAnimations");
        MAPPINGS.put("dev.tr7zw.firstperson", "FirstpersonMod");
        MAPPINGS.put("dev.tr7zw.exordium", "Exordium");
        MAPPINGS.put("dev.tr7zw.itemswapper", "ItemSwapper");
        MAPPINGS.put("dev.tr7zw.disguiseheads", "DisguiseHeads");
        MAPPINGS.put("dev.tr7zw.graphutil", "GraphUtil");
        MAPPINGS.put("dev.tr7zw.paperdoll", "PaperDoll");
        MAPPINGS.put("dev.tr7zw.modeldumper", "ModelDumper");
        FORBIDDEN_MODS.add("org.tlauncher");
    }

    public static String findSuspectMod(Throwable throwable) {
        Throwable t = throwable;

        while (t != null) {
            for (var e : t.getStackTrace()) {
                String name = e.getClassName();
                for (String forbidden : FORBIDDEN_MODS) {
                    if (name.startsWith(forbidden)) {
                        // Not reporting crashes caused by forbidden mods
                        return "ForbiddenMod";
                    }
                }
                for (var entry : MAPPINGS.entrySet()) {
                    if (name.startsWith(entry.getKey())) {
                        return entry.getValue();
                    }
                }
            }
            t = t.getCause();
        }

        return "UnknownMod";
    }

}
