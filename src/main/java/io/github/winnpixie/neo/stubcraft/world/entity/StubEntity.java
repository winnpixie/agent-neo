package io.github.winnpixie.neo.stubcraft.world.entity;

import io.github.winnpixie.neo.stubcraft.StubClass;
import io.github.winnpixie.neo.stubcraft.mappings.ClassInfo;
import io.github.winnpixie.neo.stubcraft.mappings.MappingsLoader;
import io.github.winnpixie.neo.utilities.reflection.ReflectedClass;
import io.github.winnpixie.neo.utilities.reflection.ReflectedField;
import io.github.winnpixie.neo.utilities.reflection.ReflectedMethod;

import java.lang.reflect.InvocationTargetException;

public class StubEntity extends StubClass {
    private static final ClassInfo CLASS_INFO;
    private static final ReflectedClass ENTITY_CLASS;
    private static final ReflectedField FALLDISTANCE_FIELD;
    private static final ReflectedField ONGROUND_FIELD;
    private static final ReflectedMethod ISSPRINTING_METHOD;
    private static final ReflectedMethod SETSPRINTING_METHOD;

    static {
        CLASS_INFO = MappingsLoader.getClass("net.minecraft.world.entity.Entity");

        try {
            ENTITY_CLASS = ReflectedClass.getClass(CLASS_INFO.getObfuscatedName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            FALLDISTANCE_FIELD = ENTITY_CLASS.getDeclaredField(CLASS_INFO.getField("fallDistance"));
            ONGROUND_FIELD = ENTITY_CLASS.getDeclaredField(CLASS_INFO.getField("onGround"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        try {
            ISSPRINTING_METHOD = ENTITY_CLASS.getDeclaredMethod(CLASS_INFO.getMethod("isSprinting"));
            SETSPRINTING_METHOD = ENTITY_CLASS.getDeclaredMethod(CLASS_INFO.getMethod("setSprinting"), boolean.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public float getFallDistance() {
        try {
            return FALLDISTANCE_FIELD.getFloat(super.getRealInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFallDistance(float fallDistance) {
        try {
            FALLDISTANCE_FIELD.setFloat(super.getRealInstance(), fallDistance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isOnGround() {
        try {
            return ONGROUND_FIELD.getBoolean(super.getRealInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setOnGround(boolean onGround) {
        try {
            ONGROUND_FIELD.setBoolean(super.getRealInstance(), onGround);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSprinting() {
        try {
            return (boolean) ISSPRINTING_METHOD.invoke(super.getRealInstance());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSprinting(boolean sprinting) {
        try {
            SETSPRINTING_METHOD.invoke(super.getRealInstance(), sprinting);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
