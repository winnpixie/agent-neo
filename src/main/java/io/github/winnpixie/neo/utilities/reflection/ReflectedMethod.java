package io.github.winnpixie.neo.utilities.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectedMethod extends ReflectedMember<Method> {
    public ReflectedMethod(Method realMethod, ReflectedClass owner) {
        super(realMethod, owner);
    }
    
    public Object invoke(Object owner, Object... args) throws InvocationTargetException, IllegalAccessException {
        return getMember().invoke(owner, args);
    }
}
