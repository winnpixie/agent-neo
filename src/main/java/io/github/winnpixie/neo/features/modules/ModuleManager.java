package io.github.winnpixie.neo.features.modules;

import io.github.winnpixie.neo.features.FeatureManager;
import io.github.winnpixie.neo.features.modules.impl.movement.AirJump;
import io.github.winnpixie.neo.features.modules.impl.movement.AutoSprint;
import io.github.winnpixie.neo.features.modules.impl.self.FastPlace;

public class ModuleManager extends FeatureManager<Module> {
    @Override
    public void load() {
        super.add(new AirJump());
        super.add(new AutoSprint());

        super.add(new FastPlace());

        getFeatures().forEach(Module::toggle);
    }
}
