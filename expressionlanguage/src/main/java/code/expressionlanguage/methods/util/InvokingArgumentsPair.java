package code.expressionlanguage.methods.util;

import code.expressionlanguage.Argument;
import code.util.CustList;

public final class InvokingArgumentsPair extends ArgumentsPair {

    private CustList<Argument> finalArguments;

    public CustList<Argument> getFinalArguments() {
        return finalArguments;
    }
    public void setFinalArguments(CustList<Argument> _finalArguments) {
        finalArguments = _finalArguments;
    }
}
