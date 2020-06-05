package code.expressionlanguage.methods.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.util.CustList;

public class ArgumentsPair {

    private Argument argument;
    private Argument previousArgument;
    private CustList<ClassMethodId> implicits = new CustList<ClassMethodId>();
    private int indexImplicit;
    private boolean calledToString;
    public Argument getArgument() {
        return argument;
    }
    public void setArgument(Argument _argument) {
        argument = _argument;
    }
    public Argument getPreviousArgument() {
        return previousArgument;
    }
    public void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    public boolean isCalledToString() {
        return calledToString;
    }

    public void setCalledToString(boolean _calledToString) {
        calledToString = _calledToString;
    }

    public CustList<ClassMethodId> getImplicits() {
        return implicits;
    }

    public void setImplicits(CustList<ClassMethodId> implicits) {
        this.implicits = implicits;
    }

    public int getIndexImplicit() {
        return indexImplicit;
    }

    public void setIndexImplicit(int indexImplicit) {
        this.indexImplicit = indexImplicit;
    }
}
