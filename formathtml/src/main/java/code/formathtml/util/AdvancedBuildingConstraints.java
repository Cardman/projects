package code.formathtml.util;

import code.expressionlanguage.types.AbstractBuildingConstraints;
import code.formathtml.Configuration;

public final class AdvancedBuildingConstraints implements AbstractBuildingConstraints {
    private final Configuration configuration;

    public AdvancedBuildingConstraints(Configuration _configuration) {
        configuration = _configuration;
    }

    @Override
    public void buildCurrentConstraintsFull() {
        configuration.buildCurrentConstraintsFull();
    }
}
