package code.bean;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public class Bean {

    private transient Object dataBase;

    private transient StringMap<Object> forms;

    private String scope;

    private String className = getClass().getName();

    private transient String language;

    public void beforeDisplaying() {
    }

    public Object getDataBase() {
        return dataBase;
    }

    public void setDataBase(Object _dataBase) {
        dataBase = _dataBase;
    }

    public StringMap<Object> getForms() {
        return forms;
    }

    public void setForms(StringMap<Object> _forms) {
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
