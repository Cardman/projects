package code.expressionlanguage;
import code.util.CustList;

public final class Arguments {

    private CustList<Argument> firstArguments = new CustList<Argument>();

    private CustList<Argument> optArguments = new CustList<Argument>();

    public CustList<Argument> getFirstArguments() {
        return firstArguments;
    }

    public void setFirstArguments(CustList<Argument> _firstArguments) {
        firstArguments = _firstArguments;
    }

    public CustList<Argument> getOptArguments() {
        return optArguments;
    }

    public void setOptArguments(CustList<Argument> _optArguments) {
        optArguments = _optArguments;
    }

}
