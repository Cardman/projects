package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.common.ClassField;

public final class ExecSettableOperationContent {
    private final boolean variable;

    private final boolean catString;

    private final int anc;
    private final ClassField classField;
    private final boolean staticField;
    private final String realType;
    private final boolean finalField;

    public ExecSettableOperationContent(AnaSettableOperationContent _cont) {
        this.variable = _cont.isVariable();
        this.catString = _cont.isCatString();
        this.anc = _cont.getAnc();
        this.classField = _cont.getFieldMetaInfo().getClassField();
        this.staticField = _cont.getFieldMetaInfo().isStaticField();
        this.realType = _cont.getFieldMetaInfo().getRealType();
        this.finalField = _cont.getFieldMetaInfo().isFinalField();
    }

    public boolean isFinalField() {
        return finalField;
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
