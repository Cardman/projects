package code.bean.validator;

public abstract class Validator {

    private String className;

    public abstract Message validate(Object _value);

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }
}
