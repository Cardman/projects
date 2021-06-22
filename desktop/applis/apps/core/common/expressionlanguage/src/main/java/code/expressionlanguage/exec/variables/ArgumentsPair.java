package code.expressionlanguage.exec.variables;
import code.expressionlanguage.Argument;

public final class ArgumentsPair {

    private Argument argument;
    private AbstractWrapper wrapper;
    private Argument argumentBeforeTest;
    private boolean argumentTest;
    private boolean calcArgumentTest;
    private Argument previousArgument;
    private int indexImplicit;
    private int indexImplicitTest;
    private int indexImplicitCompound;
    private int indexImplicitSemiFrom;
    private int indexImplicitSemiTo;
    private boolean endCalculate;
    private boolean calledIndexer;
    public Argument getArgument() {
        return argument;
    }
    public void setArgument(Argument _argument) {
        argument = _argument;
    }

    public AbstractWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(AbstractWrapper _wrapper) {
        this.wrapper = _wrapper;
    }

    public Argument argument(Argument _arg) {
        if (calcArgumentTest) {
            return Argument.getNullableValue(_arg);
        }
        return Argument.getNullableValue(argumentBeforeTest);
    }

    public void setArgumentBeforeTest(Argument _argumentBeforeTest) {
        argumentBeforeTest = _argumentBeforeTest;
    }

    public boolean isArgumentTest() {
        return argumentTest;
    }

    public void argumentTest(boolean _argumentTest) {
        if (calcArgumentTest) {
            return;
        }
        this.argumentTest = _argumentTest;
        calcArgumentTest = true;
    }

    public Argument getPreviousArgument() {
        return previousArgument;
    }
    public void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    public boolean isEndCalculate() {
        return endCalculate;
    }

    public void setEndCalculate(boolean _endCalculate) {
        this.endCalculate = _endCalculate;
    }

    public int getIndexImplicit() {
        return indexImplicit;
    }

    public void setIndexImplicit(int _indexImplicit) {
        this.indexImplicit = _indexImplicit;
    }

    public int getIndexImplicitTest() {
        return indexImplicitTest;
    }

    public void setIndexImplicitTest(int _indexImplicitTest) {
        this.indexImplicitTest = _indexImplicitTest;
    }

    public int getIndexImplicitCompound() {
        return indexImplicitCompound;
    }

    public void setIndexImplicitCompound(int _indexImplicitCompound) {
        this.indexImplicitCompound = _indexImplicitCompound;
    }

    public int getIndexImplicitSemiFrom() {
        return indexImplicitSemiFrom;
    }

    public void setIndexImplicitSemiFrom(int _indexImplicitSemiFrom) {
        this.indexImplicitSemiFrom = _indexImplicitSemiFrom;
    }

    public int getIndexImplicitSemiTo() {
        return indexImplicitSemiTo;
    }

    public void setIndexImplicitSemiTo(int _indexImplicitSemiTo) {
        this.indexImplicitSemiTo = _indexImplicitSemiTo;
    }

    public boolean isCalledIndexer() {
        return calledIndexer;
    }

    public void setCalledIndexer(boolean _calledIndexer) {
        calledIndexer = _calledIndexer;
    }

}
