package code.expressionlanguage;

public final class DefaultSetOffset implements AbstractSetOffset {
    private final ContextEl context;

    public DefaultSetOffset(ContextEl context) {
        this.context = context;
    }

    @Override
    public void setOffset(int _offset) {
        context.setOffset(_offset);
    }
}
