package code.expressionlanguage;

public final class CurrentInstancingPageEl extends AbstractCallingInstancingPageEl {

    @Override
    public void setArgumentForConstructor() {
        setReturnedArgument(getGlobalArgument());
    }
}
