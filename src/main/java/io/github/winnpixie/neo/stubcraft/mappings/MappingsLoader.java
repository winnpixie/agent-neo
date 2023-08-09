package io.github.winnpixie.neo.stubcraft.mappings;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class MappingsLoader {
    private static final Map<String, ClassInfo> CLASS_MAP = new HashMap<>();

    public static void load(String mappingsUrl) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(HttpRequest.newBuilder()
                    .uri(new URL(mappingsUrl).toURI())
                    .build(), HttpResponse.BodyHandlers.ofString());

            ClassInfo currentClass = null;
            for (String line : response.body().split("\n")) {
                String[] pair = line.split(" -> ");
                if (pair.length < 2) continue; // Probably a comment line?

                if (!pair[0].startsWith(" ")) { // Classes
                    currentClass = CLASS_MAP.computeIfAbsent(pair[0], v -> new ClassInfo(v, pair[1].substring(0, pair[1].indexOf(':'))));
                } else if (currentClass != null) {
                    String memberName = pair[0].substring(4).split(" ")[1];

                    if (pair[0].contains("(")) { // Methods
                        currentClass.mapMethod(memberName.substring(0, memberName.indexOf('(')), pair[1]);
                    } else { // Fields
                        currentClass.mapField(memberName, pair[1]);
                    }
                }
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static ClassInfo getClass(String name) {
        return CLASS_MAP.get(name);
    }
}
