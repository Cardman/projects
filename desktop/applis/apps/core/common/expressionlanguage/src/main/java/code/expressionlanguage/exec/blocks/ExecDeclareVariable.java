package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.StackableBlock;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ExecDeclareVariable extends ExecLeaf implements StackableBlock {

    private StringList variableNames;

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private CustList<PartOffset> partOffsets;

    public ExecDeclareVariable(OffsetsBlock _offset, String _className, int _classNameOffset,String _importedClassName,StringList _variableNames,CustList<PartOffset> _partOffsets) {
        super(_offset);
        className = _className;
        importedClassName = _importedClassName;
        classNameOffset = _classNameOffset;
        variableNames = _variableNames;
        partOffsets = _partOffsets;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        String formatted_ = ip_.formatVarType(importedClassName, _cont);
        Struct struct_ = PrimitiveTypeUtil.defaultValue(formatted_, _cont);
        for (String v: variableNames) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,formatted_);
            ip_.putLocalVar(v, lv_);
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
