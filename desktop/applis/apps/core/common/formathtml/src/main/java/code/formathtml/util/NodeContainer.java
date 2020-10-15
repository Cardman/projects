package code.formathtml.util;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public class NodeContainer {

    private CustList<Struct> object = new CustList<Struct>();
    private StringList objectClasses = new StringList();
    private Struct bean = NullStruct.NULL_VALUE;

    private Struct typedField = NullStruct.NULL_VALUE;

    private String idClass;
    private String idFieldClass;
    private String idFieldName;
    private String varPrevName;
    private CustList<String> varParamName;
    private String varName;
    private String varNameConvert;
    private CustList<RendDynOperationNode> opsWrite;
    private CustList<RendDynOperationNode> opsConvert;
    private boolean arrayConverter;

    private final NodeInformations nodeInformation = new NodeInformations();

    private boolean enabled;

    public boolean eqObj(CustList<Struct> _object) {
        int size_ = _object.size();
        if (object.size() != size_) {
            return false;
        }
        for (int i = 0; i < size_; i++) {
            if (!object.get(i).sameReference(_object.get(i))) {
                return false;
            }
        }
        return true;
    }

    public Struct getUpdated() {
        return object.first();
    }
    public String getUpdatedClass() {
        return objectClasses.first();
    }
    public CustList<Struct> getStructParam() {
        return object.mid(1);
    }
    public CustList<String> getStructParamClass() {
        return objectClasses.mid(1);
    }

    public void setStruct(CustList<Struct> _struct) {
        object = _struct;
    }

    public void setObjectClasses(StringList _objectClasses) {
        this.objectClasses = _objectClasses;
    }

    public Struct getTypedStruct() {
        return typedField;
    }
    public void setTypedStruct(Struct _typedField) {
        typedField = _typedField;
    }

    public StringList getValue() {
        return nodeInformation.getValue();
    }

    public ClassField getIdField() {
        return new ClassField(idFieldClass,idFieldName);
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String _idClass) {
        this.idClass = _idClass;
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

    public NodeInformations getNodeInformation() {
        return nodeInformation;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _enabled) {
        enabled = _enabled;
    }

    public String getVarPrevName() {
        return varPrevName;
    }

    public void setVarPrevName(String _varPrevName) {
        varPrevName = _varPrevName;
    }

    public  CustList<String> getVarParamName() {
        return varParamName;
    }

    public void setVarParamName(CustList<String> _varParamName) {
        this.varParamName = _varParamName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String _varName) {
        varName = _varName;
    }

    public CustList<RendDynOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public void setOpsWrite(CustList<RendDynOperationNode> _opsWrite) {
        opsWrite = _opsWrite;
    }

    public String getVarNameConvert() {
        return varNameConvert;
    }

    public void setVarNameConvert(String _varNameConvert) {
        varNameConvert = _varNameConvert;
    }

    public CustList<RendDynOperationNode> getOpsConvert() {
        return opsConvert;
    }

    public void setOpsConvert(CustList<RendDynOperationNode> _opsConvert) {
        opsConvert = _opsConvert;
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
}
