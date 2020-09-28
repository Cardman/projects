package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.AbstractBuildingConstraints;

public final class AdvancedBuildingConstraints implements AbstractBuildingConstraints {
    private final AnalyzedPageEl configuration;

    public AdvancedBuildingConstraints(AnalyzedPageEl _configuration) {
        configuration = _configuration;
    }

    @Override
    public void buildCurrentConstraintsFull() {
        configuration.getAvailableVariables().clear();
    }
}
