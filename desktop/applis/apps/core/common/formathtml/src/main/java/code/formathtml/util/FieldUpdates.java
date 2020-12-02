package code.formathtml.util;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class FieldUpdates {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private String varName = "";
    private InputInfo varNames = new InputInfo();
    private String id = "";
    private String idClass = "";
    private String idName = "";
    private String className = "";
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private String varNameConverter = "";
    private boolean arrayConverter;

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

    public String getVarName() {
        return varName;
    }

    public void setVarName(String _varName) {
        varName = _varName;
    }

    public InputInfo getVarNames() {
        return varNames;
    }

    public void setVarNames(InputInfo _varNames) {
        varNames = _varNames;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        this.className = _className;
    }
}
