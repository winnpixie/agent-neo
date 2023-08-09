package io.github.winnpixie.neo.stubcraft.world.level.entity;

import io.github.winnpixie.neo.Neo;
import io.github.winnpixie.neo.stubcraft.StubClass;
import io.github.winnpixie.neo.stubcraft.mappings.ClassInfo;
import io.github.winnpixie.neo.stubcraft.mappings.MappingsLoader;
import io.github.winnpixie.neo.utilities.reflection.ReflectedClass;
import io.github.winnpixie.neo.utilities.reflection.ReflectedMethod;

import java.lang.reflect.InvocationTargetException;

public class StubLevelEntityGetter extends StubClass {
    private static final ClassInfo CLASS_INFO;
    private static final ReflectedClass LEVELENTITYGETTER_CLASS;
    private static final ReflectedMethod GETALL_METHOD;

    static {
        CLASS_INFO = MappingsLoader.getClass("net.minecraft.world.level.entity.LevelEntityGetter");

        try {
            LEVELENTITYGETTER_CLASS = ReflectedClass.getClass(CLASS_INFO.getObfuscatedName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            GETALL_METHOD = LEVELENTITYGETTER_CLASS.getDeclaredMethod(CLASS_INFO.getMethod("getAll"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<?> getAll() {
        try {
            return (Iterable<?>) GETALL_METHOD.invoke(super.getRealInstance());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
