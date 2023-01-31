package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ClassField;
import code.util.CustList;

public final class ExecFilterContent {
    private final String importedClassName;

    private final String variableName;
    private final ExecSwitchValuesList list;

    public ExecFilterContent(String _c, String _v, CustList<Argument> _stdValues, CustList<ClassField> _enumValues) {
        this.importedClassName = _c;
        this.variableName = _v;
        this.list = new ExecSwitchValuesList(_stdValues,_enumValues);
    }

    public String getVariableName() {
        return variableName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public ExecSwitchValuesList getList() {
        return list;
    }
}
