package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.common.ClassField;

public final class ExecSettableOperationContent {
    private final boolean variable;

    private final boolean catString;

    private final int anc;
    private final ClassField classField;
    private final boolean staticField;
    private final String realType;

    public ExecSettableOperationContent(AnaSettableOperationContent _cont) {
        this.variable = _cont.isVariable();
        this.catString = _cont.isCatString();
        this.anc = _cont.getAnc();
        this.classField = _cont.getClassField();
        this.staticField = _cont.isStaticField();
        this.realType = _cont.getRealType();
    }

    public boolean isStaticField() {
        return staticField;
    }

    public boolean isCatString() {
        return catString;
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
