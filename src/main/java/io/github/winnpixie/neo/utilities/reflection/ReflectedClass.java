package io.github.winnpixie.neo.utilities.reflection;

public class ReflectedClass {
    private final Class<?> realClass;

    public ReflectedClass(Class<?> realClass) {
        this.realClass = realClass;
    }

    public Class<?> getRealClass() {
        return realClass;
    }

    public ReflectedField getField(String name) throws NoSuchFieldException {
        return new ReflectedField(realClass.getField(name), this);
    }

    public ReflectedField getDeclaredField(String name) throws NoSuchFieldException {
        return new ReflectedField(realClass.getDeclaredField(name), this);
    }

    public ReflectedMethod getMethod(String name, Class<?>... paramTypes) throws NoSuchMethodException {
        return new ReflectedMethod(realClass.getMethod(name, paramTypes), this);
    }

    public ReflectedMethod getDeclaredMethod(String name, Class<?>... paramTypes) throws NoSuchMethodException {
        return new ReflectedMethod(realClass.getDeclaredMethod(name, paramTypes), this);
    }

    public static ReflectedClass getClass(String clsName) throws ClassNotFoundException {
        return new ReflectedClass(Class.forName(clsName));
    }

    public static ReflectedClass getClass(Class<?> realClass) {
        return new ReflectedClass(realClass);
    }
}
