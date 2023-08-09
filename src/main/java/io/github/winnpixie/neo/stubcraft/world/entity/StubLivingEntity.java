package io.github.winnpixie.neo.stubcraft.world.entity;

import io.github.winnpixie.neo.stubcraft.mappings.ClassInfo;
import io.github.winnpixie.neo.stubcraft.mappings.MappingsLoader;
import io.github.winnpixie.neo.utilities.reflection.ReflectedClass;
import io.github.winnpixie.neo.utilities.reflection.ReflectedMethod;

import java.lang.reflect.InvocationTargetException;

public class StubLivingEntity extends StubEntity {
    private static final ClassInfo CLASS_INFO;
    private static final ReflectedClass LIVINGENTITY_CLASS;
    private static final ReflectedMethod SETSPEED_METHOD;

    static {
        CLASS_INFO = MappingsLoader.getClass("net.minecraft.world.entity.LivingEntity");

        try {
            LIVINGENTITY_CLASS = ReflectedClass.getClass(CLASS_INFO.getObfuscatedName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            SETSPEED_METHOD = LIVINGENTITY_CLASS.getDeclaredMethod(CLASS_INFO.getMethod("setSpeed"), float.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSpeed(float speed) {
        try {
            SETSPEED_METHOD.invoke(super.getRealInstance(), speed);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
