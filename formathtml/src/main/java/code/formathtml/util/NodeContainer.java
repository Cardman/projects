package code.formathtml.util;
import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;

public class NodeContainer {

    private Struct object = NullStruct.NULL_VALUE;

    private Struct typedField = NullStruct.NULL_VALUE;

    private String access;

    private boolean key;
    private String lastToken;

    private long index = -1;

    private String beanName;

    private final NodeInformations nodeInformation;

    private boolean enabled = true;

    public NodeContainer(String _name) {
        nodeInformation = new NodeInformations(_name);
    }

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

    public Object getTypedField() {
        return typedField.getInstance();
    }

    public void setTypedField(Number _typedField) {
        typedField = NumberStruct.wrapNb(_typedField);
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

}
