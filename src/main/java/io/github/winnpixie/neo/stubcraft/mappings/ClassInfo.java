package io.github.winnpixie.neo.stubcraft.mappings;

import java.util.HashMap;
import java.util.Map;

public class ClassInfo {
    private final String realName;
    private final String obfuscatedName;
    private final Map<String, String> fieldMap = new HashMap<>();
    private final Map<String, String> methodMap = new HashMap<>();

    public ClassInfo(String realName, String obfuscatedName) {
        this.realName = realName;
        this.obfuscatedName = obfuscatedName;
    }

    public String getRealName() {
        return realName;
    }

    public String getObfuscatedName() {
        return obfuscatedName;
    }

    public String getField(String realName) {
        return fieldMap.get(realName);
    }

    public String getMethod(String realName) {
        return methodMap.get(realName);
    }

    public void mapField(String realName, String obfuscatedName) {
        fieldMap.put(realName, obfuscatedName);
    }

    public void mapMethod(String realName, String obfuscatedName) {
        methodMap.put(realName, obfuscatedName);
    }
}
