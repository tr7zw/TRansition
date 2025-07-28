package dev.tr7zw.transition.mc;

import lombok.experimental.UtilityClass;
import net.minecraft.network.chat.Component;

//? if >= 1.19.0 {
import net.minecraft.network.chat.MutableComponent;
//?} else {
/*import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
*///?}

/**
 * Utility class to get Components, for better bridging between 1.19+ and pre
 * 1.19
 * 
 * @author tr7zw
 *
 */
@UtilityClass
public class ComponentProvider {

    public static final Component EMPTY = literal("");

    //? if >= 1.19.0 {
    public static MutableComponent literal(String string) {
        return Component.literal(string);
        //?} else {
        /*public static TextComponent literal(String string) {
        return new TextComponent(string);
        *///?}
    }

    //? if >= 1.19.0 {
    public static MutableComponent translatable(String string) {
        return Component.translatable(string);
        //?} else {
        /*public static TranslatableComponent translatable(String string) {
        return new TranslatableComponent(string);
        *///?}
    }

    //? if >= 1.19.0 {
    public static MutableComponent translatable(String string, Object... objects) {
        return Component.translatable(string, objects);
        //?} else {
        /*public static TranslatableComponent translatable(String string, Object... objects) {
        return new TranslatableComponent(string, objects);
        *///?}
    }

    //? if >= 1.19.0 {
    public static MutableComponent empty() {
        return Component.empty();
        //?} else {
        /*public static Component empty() {
        return TextComponent.EMPTY;
        *///?}
    }

}
