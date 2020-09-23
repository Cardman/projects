package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ExecDeclareVariable extends ExecLeaf implements StackableBlock {

    private StringList variableNames;

    private String importedClassName;

    public ExecDeclareVariable(OffsetsBlock _offset, String _importedClassName, StringList _variableNames) {
        super(_offset);
        importedClassName = _importedClassName;
        variableNames = _variableNames;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        String formatted_ = ip_.formatVarType(importedClassName, _cont);
        Struct struct_ = ExecClassArgumentMatching.defaultValue(formatted_, _cont);
        for (String v: variableNames) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,formatted_);
            ip_.putLocalVar(v, lv_);
            ip_.putValueVar(v, lv_);
        }
        processBlock(_cont);
    }

    public void setImportedClassName(String importedClassName) {
        this.importedClassName = importedClassName;
    }

    public StringList getVariableNames() {
        return variableNames;
    }
}
