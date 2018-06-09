package code.expressionlanguage;


public abstract class AbstractCallingInstancingPageEl extends AbstractInstancingPageEl implements ForwardPageEl {

    private Argument returnedArgument;

    @Override
    public Argument getReturnedArgument() {
        return returnedArgument;
    }

    @Override
    public void setReturnedArgument(Argument _returnedArgument) {
        returnedArgument = _returnedArgument;
    }

    @Override
    public final void setArgumentForConstructor() {
        returnedArgument = getGlobalArgument();
    }

    @Override
    public final boolean forwardTo(AbstractPageEl _page, ContextEl _context) {
        Argument a_ = getReturnedArgument();
        _page.getLastEl().setArgument(a_, _context);
        return _context.processException();
    }
}
