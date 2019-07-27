package code.formathtml.util;
import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.structs.BeanStruct;
import code.formathtml.structs.StdStruct;
import code.util.CustList;

public class NodeContainer {

    private Struct object = NullStruct.NULL_VALUE;

    private Struct typedField = NullStruct.NULL_VALUE;

    private String access;

    private boolean key;
    private ClassField idField;
    private String varPrevName;
    private String varName;
    private String varNameConvert;
    private CustList<RendDynOperationNode> opsWrite;
    private CustList<RendDynOperationNode> opsConvert;
    private boolean arrayConverter;

    private String lastToken;

    private long index = -1;

    private String beanName;

    private final NodeInformations nodeInformation = new NodeInformations();

    private boolean enabled = true;

    public Struct getStruct() {
        return object;
    }
    public void setStruct(Struct _struct) {
        object = _struct;
        if (object == null) {
            object = NullStruct.NULL_VALUE;
        }
    }

    public void setObject(Object _object, ContextEl _context) {
        object = StdStruct.wrapStd(_object, _context);
    }

    public void setObject(Bean _object) {
        object = new BeanStruct(_object);
    }

    public Struct getTypedStruct() {
        return typedField;
    }
    public void setTypedStruct(Struct _typedField) {
        typedField = _typedField;
        if (typedField == null) {
            typedField = NullStruct.NULL_VALUE;
        }
    }

    public void setTypedField(String _typedField) {
        typedField = new StringStruct(_typedField);
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String _access) {
        access = _access;
    }

    public String getLastToken() {
        return lastToken;
    }

    public void setLastToken(String _lastToken) {
        lastToken = _lastToken;
    }

    public ClassField getIdField() {
        return idField;
    }

    public void setIdField(ClassField _idField) {
        idField = _idField;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long _index) {
        index = _index;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
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

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean _key) {
        key = _key;
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
}
