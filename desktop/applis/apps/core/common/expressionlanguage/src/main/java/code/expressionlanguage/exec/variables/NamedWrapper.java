package code.expressionlanguage.exec.variables;

public final class NamedWrapper extends NamedVariable{
    private final AbstractWrapper wrapper;

    public NamedWrapper(String _name, AbstractWrapper _wrapper) {
        super(_name);
        this.wrapper = _wrapper;
    }

    public AbstractWrapper getWrapper() {
        return wrapper;
    }

}