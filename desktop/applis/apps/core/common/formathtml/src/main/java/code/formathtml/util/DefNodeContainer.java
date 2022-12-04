package code.formathtml.util;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.sml.NodeContainer;
import code.util.CustList;

public final class DefNodeContainer extends NodeContainer {
    private CustList<RendDynOperationNode> opsConvert;
    private String idFieldClass;
    private String idFieldName;
    private String idRadio = "";
    private Struct bean = NullStruct.NULL_VALUE;

    private Struct typedField = NullStruct.NULL_VALUE;
    private boolean arrayConverter;

    private AbstractWrapper input;

    private CustList<Struct> allObject = new CustList<Struct>();

    public CustList<Struct> getAllObject() {
        return allObject;
    }

    public void setAllObject(CustList<Struct> _allObject) {
        allObject = _allObject;
    }

    public Struct getTypedStruct() {
        return typedField;
    }
    public void setTypedStruct(Struct _typedField) {
        typedField = _typedField;
    }
    public CustList<Struct> getStructParam() {
        return getAllObject().mid(1);
    }

    public boolean isArrayConverter() {
        return arrayConverter;
    }

    public void setArrayConverter(boolean _arrayConverter) {
        arrayConverter = _arrayConverter;
    }

    public Struct getBean() {
        return bean;
    }

    public void setBean(Struct _bean) {
        bean = _bean;
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

    public String getIdRadio() {
        return idRadio;
    }

    public void setIdRadio(String _id) {
        this.idRadio = _id;
    }

}
