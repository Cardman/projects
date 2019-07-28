package code.formathtml.util;
import code.util.StringList;


public class NodeInformations {

    private String id = "";
    private String inputClass = "";
    private String changing = "";
    private String validator = "";
    private String varMethod = "";
    private StringList value = new StringList();
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
    public String getChanging() {
        return changing;
    }
    public void setChanging(String _changing) {
        changing = _changing;
    }
    public String getValidator() {
        return validator;
    }
    public void setValidator(String _validator) {
        validator = _validator;
    }
    public String getVarMethod() {
        return varMethod;
    }
    public void setVarMethod(String _varMethod) {
        varMethod = _varMethod;
    }

    public StringList getValue() {
        return value;
    }
    public void setValue(StringList _value) {
        value = _value;
    }
}
