package code.expressionlanguage.exec.variables;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ArgumentsPair {

    private Struct argument;
    private Struct argumentParent;
    private CustList<ArgumentWrapper> argumentList = new CustList<ArgumentWrapper>();
    private AbstractWrapper wrapper;
    private Struct argumentBeforeTest;
    private Struct argumentBeforeImpl;
    private boolean argumentTest;
    private boolean calcArgumentTest;
    private Struct previousArgument;
    private int indexImplicit;
    private int indexImplicitTest;
    private int indexImplicitConv;
    private boolean endCalculate;
    private boolean calledIndexer;
    private boolean indexer;
    public Struct getArgument() {
        return argument;
    }
    public void setArgument(Struct _argument) {
        argument = _argument;
    }

    public Struct getArgumentParent() {
        return argumentParent;
    }

    public void setArgumentParent(Struct _arg) {
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

    public Struct argument(Struct _arg) {
        if (calcArgumentTest) {
            return ArgumentListCall.getNull(_arg);
        }
        return ArgumentListCall.getNull(argumentBeforeTest);
    }

    public void setArgumentBeforeTest(Struct _argumentBeforeTest) {
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

    public Struct getArgumentBeforeImpl() {
        return argumentBeforeImpl;
    }

    public void argumentImpl(Struct _arg) {
        if (argumentBeforeImpl != null) {
            return;
        }
        argumentBeforeImpl = _arg;
    }
    public Struct getPreviousArgument() {
        return previousArgument;
    }
    public void setPreviousArgument(Struct _previousArgument) {
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
