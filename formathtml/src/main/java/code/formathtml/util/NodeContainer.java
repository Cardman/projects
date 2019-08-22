package code.formathtml.util;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public class NodeContainer {

    private Struct object = NullStruct.NULL_VALUE;
    private Struct bean = NullStruct.NULL_VALUE;

    private Struct typedField = NullStruct.NULL_VALUE;

    private ClassField idField;
    private String varPrevName;
    private String varName;
    private String varNameConvert;
    private CustList<RendDynOperationNode> opsWrite;
    private CustList<RendDynOperationNode> opsConvert;
    private boolean arrayConverter;

    private final NodeInformations nodeInformation = new NodeInformations();

    private boolean enabled;

    public Struct getStruct() {
        return object;
    }
    public void setStruct(Struct _struct) {
        object = _struct;
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
        return idField;
    }

    public void setIdField(ClassField _idField) {
        idField = _idField;
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
