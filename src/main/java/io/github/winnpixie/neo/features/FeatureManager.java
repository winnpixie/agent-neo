package io.github.winnpixie.neo.features;

import java.util.ArrayList;
import java.util.List;

public class FeatureManager<T extends Feature> {
    private final List<T> features = new ArrayList<>();

    public List<T> getFeatures() {
        return features;
    }

    public void add(T feature) {
        features.add(feature);
    }

    public void remove(T feature) {
        features.remove(feature);
    }

    public void load() {
    }

    public void save() {
    }
}
