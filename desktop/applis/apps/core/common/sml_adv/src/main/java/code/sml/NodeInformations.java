package code.sml;
import code.util.StringList;


public class NodeInformations {

    private String id = "";
    private String inputClass = "";
    private String validator = "";
    private StringList value = new StringList();

    private boolean enabled;

    public String getId() {
        return id;
    }
    public void setId(String _id) {
        id = _id;
    }
    public String getInputClass() {
        return inputClass;
    }
    public void setInputClass(String _inputClass) {
        inputClass = _inputClass;
    }

    public String getValidator() {
        return validator;
    }
    public void setValidator(String _validator) {
        validator = _validator;
    }

    public StringList getValue() {
        return value;
    }
    public void setValue(StringList _value) {
        value = _value;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _enabled) {
        enabled = _enabled;
    }

}
