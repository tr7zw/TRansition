package dev.tr7zw.transition.mc;

import com.mojang.blaze3d.vertex.VertexConsumer;

import lombok.experimental.UtilityClass;

//? if >= 1.19.3 {

import org.joml.Matrix4f;
//? } else {
/*
 import com.mojang.math.Matrix4f;
*///? }

@UtilityClass
public class VertexConsumerUtil {

    public static void addVertex(VertexConsumer cons, Matrix4f matrix4f, float x, float y, float z, float u, float v,
            int lightmapUV) {
        addVertex(cons, matrix4f, x, y, z, u, v, lightmapUV & 65535, lightmapUV >> 16 & 65535);
    }

    public static void addVertex(VertexConsumer cons, Matrix4f matrix4f, float x, float y, float z, float u, float v,
            int u2, int v2) {
        //$ add_vertex_basic
        cons.addVertex(matrix4f, x, y, z).setColor(255, 255, 255, 255).setUv(u, v).setUv2(u2, v2);
    }

    public static void addVertex(VertexConsumer cons, Matrix4f matrix4f, float x, float y, float z, float u, float v) {
        //$ add_vertex_uv
        cons.addVertex(matrix4f, x, y, z).setColor(255, 255, 255, 255).setUv(u, v);
    }

    public static void addVertex(VertexConsumer cons, Matrix4f matrix4f, float x, float y, float z, float u, float v,
            int overlay, int lightmapUV, float nx, float ny, float nz) {
        addVertex(cons, matrix4f, x, y, z, u, v, overlay, lightmapUV, nx, ny, nz, 1f);
    }

    public static void addVertex(VertexConsumer cons, Matrix4f matrix4f, float x, float y, float z, float u, float v,
            int overlay, int lightmapUV, float nx, float ny, float nz, float alpha) {
        addVertex(cons, matrix4f, x, y, z, u, v, overlay, lightmapUV & 65535, lightmapUV >> 16 & 65535, nx, ny, nz,
                (int) (alpha * 255));
    }

    public static void addVertex(VertexConsumer cons, Matrix4f matrix4f, float x, float y, float z, float u, float v,
            int overlay, int u2, int v2, float nx, float ny, float nz) {
        addVertex(cons, matrix4f, x, y, z, u, v, overlay, u2, v2, nx, ny, nz, 255);
    }

    public static void addVertex(VertexConsumer cons, Matrix4f matrix4f, float x, float y, float z, float u, float v,
            int overlay, int u2, int v2, float nx, float ny, float nz, int alpha) {
        // spotless:off
        //$ add_vertex_full
        cons.addVertex(matrix4f, x, y, z).setColor(255, 255, 255, alpha).setUv(u, v).setUv2(u2, v2).setOverlay(overlay).setNormal(nx, ny, nz);
        // spotless:on
    }

}
