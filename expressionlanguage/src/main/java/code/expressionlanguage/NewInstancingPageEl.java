package code.expressionlanguage;

public final class NewInstancingPageEl extends AbstractInstancingPageEl {

    @Override
    public void setArgumentForConstructor() {
        setReturnedArgument(getGlobalArgument());
    }
}
