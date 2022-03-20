package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.common.ClassField;

public final class ExecSettableOperationContent {
    private final boolean variable;

    private final int anc;
    private final ClassField classField;
    private final String realType;

    public ExecSettableOperationContent(AnaSettableOperationContent _cont) {
        this.variable = _cont.isVariable();
        this.anc = _cont.getAnc();
        this.classField = _cont.getClassField();
        this.realType = _cont.getRealType();
    }

    public boolean isVariable() {
        return variable;
    }

    public ClassField getClassField() {
        return classField;
    }

    public int getAnc() {
        return anc;
    }

    public String getRealType() {
        return realType;
    }
}
