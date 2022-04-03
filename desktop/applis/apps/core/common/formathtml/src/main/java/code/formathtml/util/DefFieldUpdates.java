package code.formathtml.util;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class DefFieldUpdates extends FieldUpdates {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private String varNameConverter = "";
    private boolean arrayConverter;
    private InputInfo varNames = new InputInfo();

    private String idClass = "";
    private String idName = "";

    private String id = "";

    public CustList<RendDynOperationNode> getOpsRead() {
        return opsRead;
    }

    public void setOpsRead(CustList<RendDynOperationNode> _opsRead) {
        opsRead = _opsRead;
    }

    public CustList<RendDynOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(CustList<RendDynOperationNode> _opsWrite) {
        opsWrite = _opsWrite;
    }

    public CustList<RendDynOperationNode> getOpsConverter() {
        return opsConverter;
    }

    public void setOpsConverter(CustList<RendDynOperationNode> _opsConverter) {
        opsConverter = _opsConverter;
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = _id;
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

    public InputInfo getVarNames() {
        return varNames;
    }

    public void setVarNames(InputInfo _varNames) {
        varNames = _varNames;
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
