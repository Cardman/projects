package code.formathtml.util;

import code.expressionlanguage.opers.util.ClassField;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;

public final class FieldUpdates {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private String varName = "";
    private ClassField idField;
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private String varNameConverter = "";
    private boolean arrayConverter;

    public ClassField getIdField() {
        return idField;
    }

    public void setIdField(ClassField _idField) {
        idField = _idField;
    }

    public CustList<RendDynOperationNode> getOpsRead() {
        return opsRead;
    }

    public void setOpsRead(CustList<RendDynOperationNode> _opsRead) {
        opsRead = _opsRead;
    }

    public CustList<RendDynOperationNode> getOpsValue() {
        return opsValue;
    }

    public void setOpsValue(CustList<RendDynOperationNode> _opsValue) {
        opsValue = _opsValue;
    }

    public CustList<RendDynOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(CustList<RendDynOperationNode> _opsWrite) {
        opsWrite = _opsWrite;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String _varName) {
        varName = _varName;
    }

    public CustList<RendDynOperationNode> getOpsConverter() {
        return opsConverter;
    }

    public void setOpsConverter(CustList<RendDynOperationNode> _opsConverter) {
        opsConverter = _opsConverter;
    }

    public String getVarNameConverter() {
        return varNameConverter;
    }

    public void setVarNameConverter(String _varNameConverter) {
        varNameConverter = _varNameConverter;
    }

    public boolean isArrayConverter() {
        return arrayConverter;
    }

    public void setArrayConverter(boolean _arrayConverter) {
        arrayConverter = _arrayConverter;
    }
}
