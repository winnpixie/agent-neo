package io.github.winnpixie.neo.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodListener {
    private final Method method;
    private final Object owner;
    private Class<?> eventClass;
    private final Handler handler;

    public MethodListener(Method method, Object owner) {
        this.method = method;
        this.owner = owner;
        this.eventClass = method.getParameterTypes()[0];
        this.handler = method.getAnnotation(Handler.class);
    }

    public Class<?> getEventClass() {
        return eventClass;
    }

    public void invoke(Object eventData) {
        try {
            method.invoke(owner, eventData);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
