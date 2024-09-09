package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ExecFilterContent {
    private final int offset;
    private final String importedClassName;

    private final String variableName;
    private final ExecSwitchValuesList list;

    public ExecFilterContent(int _o, String _c, String _v, CustList<Struct> _stdValues, CustList<ClassField> _enumValues) {
        offset = _o;
        this.importedClassName = _c;
        this.variableName = _v;
        this.list = new ExecSwitchValuesList(_stdValues,_enumValues);
    }

    public int getOffset() {
        return offset;
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
