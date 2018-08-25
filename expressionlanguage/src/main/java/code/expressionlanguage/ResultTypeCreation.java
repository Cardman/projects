package code.expressionlanguage;

import code.expressionlanguage.methods.RootBlock;

public final class ResultTypeCreation extends ResultCreation {

    private RootBlock type;

    @Override
    public RootBlock getType() {
        return type;
    }

    public void setType(RootBlock _type) {
        type = _type;
    }
}
