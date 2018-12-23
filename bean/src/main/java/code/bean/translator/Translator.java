package code.bean.translator;

import code.bean.Bean;

public abstract class Translator {

    private String className;

    public abstract String getString(String _pattern, Object _conf, Bean _object, String _value);

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

}
