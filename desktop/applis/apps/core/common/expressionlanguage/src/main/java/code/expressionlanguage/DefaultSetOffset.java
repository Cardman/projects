package code.expressionlanguage;

public final class DefaultSetOffset implements AbstractSetOffset {
    private final ContextEl context;

    public DefaultSetOffset(ContextEl _context) {
        this.context = _context;
    }

    @Override
    public void setOffset(int _offset) {
        context.setOffset(_offset);
    }
}
