package code.expressionlanguage.exec.variables;

public final class NamedWrapper extends NamedVariable{
    private final AbstractWrapper wrapper;
    private final String className;

    public NamedWrapper(String _name, AbstractWrapper _wrapper,String _className) {
        super(_name);
        this.wrapper = _wrapper;
        className = _className;
    }

    public AbstractWrapper getWrapper() {
        return wrapper;
    }

    public String getClassName() {
        return className;
    }
}