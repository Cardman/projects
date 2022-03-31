package code.bean;

import code.bean.nat.StringMapObjectBase;

public abstract class Bean {

    private String className = "";

    private String language = "";

    private StringMapObjectBase baseForms;

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

    public StringMapObjectBase getBaseForms() {
        return baseForms;
    }

    public void setBaseForms(StringMapObjectBase _base) {
        this.baseForms = _base;
    }
}
