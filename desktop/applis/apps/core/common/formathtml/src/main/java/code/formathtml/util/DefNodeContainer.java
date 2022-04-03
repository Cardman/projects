package code.formathtml.util;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class DefNodeContainer extends NodeContainer {
    private StringList objectClasses = new StringList();
    private CustList<AbstractWrapper> wrappers = new CustList<AbstractWrapper>();
    private CustList<RendDynOperationNode> opsWrite;
    private CustList<RendDynOperationNode> opsConvert;
    private String idFieldClass;
    private String idFieldName;

    private String idClass;

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String _id) {
        this.idClass = _id;
    }

    public String getUpdatedClass() {
        return objectClasses.first();
    }
    public CustList<String> getStructParamClass() {
        return objectClasses.mid(1);
    }

    public void setObjectClasses(StringList _objectClasses) {
        this.objectClasses = _objectClasses;
    }
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
    public CustList<RendDynOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(CustList<RendDynOperationNode> _opsWrite) {
        opsWrite = _opsWrite;
    }

    public CustList<AbstractWrapper> getWrappers() {
        return wrappers;
    }

    public void setWrappers(CustList<AbstractWrapper> _wrappers) {
        wrappers = _wrappers;
    }

    public CustList<RendDynOperationNode> getOpsConvert() {
        return opsConvert;
    }

    public void setOpsConvert(CustList<RendDynOperationNode> _opsConvert) {
        opsConvert = _opsConvert;
    }

}
