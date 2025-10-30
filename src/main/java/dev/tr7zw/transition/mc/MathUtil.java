package dev.tr7zw.transition.mc;

import lombok.experimental.UtilityClass;

//? if >= 1.19.3 {

import org.joml.Quaternionf;
import com.mojang.math.Axis;
//? } else {
/*
 import com.mojang.math.Vector3f;
 import com.mojang.math.Quaternion;
*///? }

@UtilityClass
public class MathUtil {

    public static final float PI = (float) Math.PI;
    public static final float HALF_PI = (float) (Math.PI / 2);
    public static final float TWO_PI = (float) (Math.PI * 2);
    public static final float DEG_TO_RAD = (float) (Math.PI / 180.0);

    //? if >= 1.19.3 {

    public static Axis XN = f -> new Quaternionf().rotationX(-f);
    public static Axis XP = f -> new Quaternionf().rotationX(f);
    public static Axis YN = f -> new Quaternionf().rotationY(-f);
    public static Axis YP = f -> new Quaternionf().rotationY(f);
    public static Axis ZN = f -> new Quaternionf().rotationZ(-f);
    public static Axis ZP = f -> new Quaternionf().rotationZ(f);
    //? } else {
/*
     public static Vector3f XN = new Vector3f(-1.0F, 0.0F, 0.0F);
     public static Vector3f XP = new Vector3f(1.0F, 0.0F, 0.0F);
     public static Vector3f YN = new Vector3f(0.0F, -1.0F, 0.0F);
     public static Vector3f YP = new Vector3f(0.0F, 1.0F, 0.0F);
     public static Vector3f ZN = new Vector3f(0.0F, 0.0F, -1.0F);
     public static Vector3f ZP = new Vector3f(0.0F, 0.0F, 1.0F);
    *///? }

    //? if >= 1.19.3 {

    public static void conjugate(Quaternionf quaternion2) {
        quaternion2.conjugate();
        //? } else {
/*
         public static void conjugate(Quaternion quaternion2) {
         quaternion2.conj();
        *///? }
    }

}
