package code.formathtml.util;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class DefNodeContainer extends NodeContainer {
    private CustList<RendDynOperationNode> opsConvert;
    private String idFieldClass;
    private String idFieldName;

    private AbstractWrapper input;

    public ClassField getIdField() {
        return new ClassField(idFieldClass,idFieldName);
    }

    public String getIdFieldClass() {
        return idFieldClass;
    }

    public void setIdFieldClass(String _idFieldClass) {
        this.idFieldClass = _idFieldClass;
    }

    public String getIdFieldName() {
        return idFieldName;
    }

    public void setIdFieldName(String _idFieldName) {
        this.idFieldName = _idFieldName;
    }

    public CustList<RendDynOperationNode> getOpsConvert() {
        return opsConvert;
    }

    public void setOpsConvert(CustList<RendDynOperationNode> _opsConvert) {
        opsConvert = _opsConvert;
    }

    public AbstractWrapper getInput() {
        return input;
    }

    public void setInput(AbstractWrapper _input) {
        input = _input;
    }
}
