package code.formathtml.util;

import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;

public final class FieldUpdates {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private String varName = "";
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

    public void setId(String id) {
        this.id = id;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
