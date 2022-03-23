package code.bean;

public abstract class Bean {

    private String className = "";

    private String language = "";

    public abstract void beforeDisplaying();

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
