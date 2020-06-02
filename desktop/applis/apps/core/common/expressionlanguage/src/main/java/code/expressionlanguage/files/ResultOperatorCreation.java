package code.expressionlanguage.files;

import code.expressionlanguage.methods.OperatorBlock;

public final class ResultOperatorCreation extends ResultCreation {

    private OperatorBlock type;

    public OperatorBlock getType() {
        return type;
    }

    public void setType(OperatorBlock _type) {
        type = _type;
    }
}
