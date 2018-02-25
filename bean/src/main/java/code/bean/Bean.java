package code.bean;
import code.util.StringMapObject;

public class Bean {

    private transient Object dataBase;

    private transient StringMapObject forms;

    private String scope;

    private String className;

    private transient String language;

    public void beforeDisplaying() {
    }

    public Object getDataBase() {
        return dataBase;
    }

    public void setDataBase(Object _dataBase) {
        dataBase = _dataBase;
    }

    public StringMapObject getForms() {
        return forms;
    }

    public void setForms(StringMapObject _forms) {
        if (_forms == null) {
            return;
        }
        forms = _forms;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String _scope) {
        if (_scope == null) {
            return;
        }
        scope = _scope;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String _language) {
        language = _language;
    }
}
