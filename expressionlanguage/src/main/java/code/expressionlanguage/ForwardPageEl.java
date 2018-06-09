package code.expressionlanguage;

public interface ForwardPageEl extends ReturnablePageEl {

    boolean forwardTo(AbstractPageEl _page, ContextEl _context);

    Argument getReturnedArgument();

    void setReturnedArgument(Argument _returnedArgument);
}
