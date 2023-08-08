package io.github.winnpixie.neo.stubcraft.entities;

import io.github.winnpixie.neo.stubcraft.Stub;
import io.github.winnpixie.neo.utilities.reflection.ReflectedClass;
import io.github.winnpixie.neo.utilities.reflection.ReflectedMethod;

import java.lang.reflect.InvocationTargetException;

public class StubEntity extends Stub {
    private static final ReflectedClass entityClass;
    private static final ReflectedMethod isSprintingMethod;
    private static final ReflectedMethod setSprintingMethod;

    static {
        try {
            entityClass = ReflectedClass.getClass("bfj");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            isSprintingMethod = entityClass.getDeclaredMethod("bV");
            setSprintingMethod = entityClass.getDeclaredMethod("g", boolean.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSprinting() {
        try {
            return (boolean) isSprintingMethod.invoke(super.getRealInstance());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSprinting(boolean sprinting) {
        try {
            setSprintingMethod.invoke(super.getRealInstance(), sprinting);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
