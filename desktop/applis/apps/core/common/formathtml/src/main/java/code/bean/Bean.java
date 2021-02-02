package code.bean;

public abstract class Bean {

    private String scope;

    private String className;

    private String language;

    public abstract void beforeDisplaying();

    public String getScope() {
        return scope;
    }

    public void setScope(String _scope) {
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
