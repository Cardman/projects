package code.expressionlanguage.exec.variables;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.util.CustList;

public final class ArgumentsPair {

    private Argument argument;
    private Argument argumentParent;
    private CustList<ArgumentWrapper> argumentList = new CustList<ArgumentWrapper>();
    private AbstractWrapper wrapper;
    private Argument argumentBeforeTest;
    private Argument argumentBeforeImpl;
    private boolean argumentTest;
    private boolean calcArgumentTest;
    private Argument previousArgument;
    private int indexImplicit;
    private int indexImplicitTest;
    private int indexImplicitConv;
    private boolean endCalculate;
    private boolean calledIndexer;
    private boolean indexer;
    public Argument getArgument() {
        return argument;
    }
    public void setArgument(Argument _argument) {
        argument = _argument;
    }

    public Argument getArgumentParent() {
        return argumentParent;
    }

    public void setArgumentParent(Argument _arg) {
        this.argumentParent = _arg;
    }

    public CustList<ArgumentWrapper> getArgumentList() {
        return argumentList;
    }

    public void setArgumentList(CustList<ArgumentWrapper> _li) {
        this.argumentList = _li;
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

    public Argument getArgumentBeforeImpl() {
        return argumentBeforeImpl;
    }

    public void argumentImpl(Argument _arg) {
        if (argumentBeforeImpl != null) {
            return;
        }
        argumentBeforeImpl = _arg;
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

    public int getIndexImplicitConv() {
        return indexImplicitConv;
    }

    public void setIndexImplicitConv(int _indexImplicitConv) {
        this.indexImplicitConv = _indexImplicitConv;
    }

    public boolean isCalledIndexer() {
        return calledIndexer;
    }

    public void setCalledIndexer(boolean _calledIndexer) {
        calledIndexer = _calledIndexer;
    }

    public boolean isIndexer() {
        return indexer;
    }

    public void setIndexer(boolean _ind) {
        this.indexer = _ind;
    }
}
