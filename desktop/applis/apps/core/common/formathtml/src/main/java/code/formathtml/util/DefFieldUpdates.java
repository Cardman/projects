package code.formathtml.util;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class DefFieldUpdates extends FieldUpdates {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private String varNameConverter = "";
    private boolean arrayConverter;

    private String idClass = "";
    private String idName = "";

    public CustList<RendDynOperationNode> getOpsRead() {
        return opsRead;
    }

    public void setOpsRead(CustList<RendDynOperationNode> _opsRead) {
        opsRead = _opsRead;
    }

    public CustList<RendDynOperationNode> getOpsConverter() {
        return opsConverter;
    }

    public void setOpsConverter(CustList<RendDynOperationNode> _opsConverter) {
        opsConverter = _opsConverter;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String _idClass) {
        this.idClass = _idClass;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String _idName) {
        this.idName = _idName;
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
