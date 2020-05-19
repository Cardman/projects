package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;

public final class DefaultBuildingConstraints implements AbstractBuildingConstraints {
    private final ContextEl context;

    public DefaultBuildingConstraints(ContextEl _context) {
        context = _context;
    }

    @Override
    public void buildCurrentConstraintsFull() {
        context.buildCurrentConstraintsFull();
    }
}
