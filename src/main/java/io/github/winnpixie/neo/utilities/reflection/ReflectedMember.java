package io.github.winnpixie.neo.utilities.reflection;

import java.lang.reflect.AccessibleObject;

public class ReflectedMember<T extends AccessibleObject> {
    private final T member;
    private final ReflectedClass owner;

    public ReflectedMember(T member, ReflectedClass owner) {
        this.member = member;
        this.owner = owner;

        if (!isAccessible()) setAccessible(true);
    }

    public T getMember() {
        return member;
    }

    public ReflectedClass getOwner() {
        return owner;
    }

    public boolean isAccessible() {
        return member.isAccessible();
    }

    public void setAccessible(boolean accessible) {
        member.setAccessible(accessible);
    }
}
