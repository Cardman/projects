package code.expressionlanguage.exec.variables;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.util.ImplicitMethods;

public class ArgumentsPair {

    private Argument argument;
    private AbstractWrapper wrapper;
    private Argument argumentBeforeTest;
    private boolean argumentTest;
    private boolean calcArgumentTest;
    private Argument previousArgument;
    private ImplicitMethods implicits = new ImplicitMethods();
    private ImplicitMethods implicitsTest = new ImplicitMethods();
    private ImplicitMethods implicitsCompound = new ImplicitMethods();
    private ImplicitMethods implicitsSemiFrom = new ImplicitMethods();
    private ImplicitMethods implicitsSemiTo = new ImplicitMethods();
    private int indexImplicit;
    private int indexImplicitTest;
    private int indexImplicitCompound;
    private int indexImplicitSemiFrom;
    private int indexImplicitSemiTo;
    private boolean endCalculate;
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

    public Argument getArgumentBeforeTest() {
        return argumentBeforeTest;
    }

    public void setArgumentBeforeTest(Argument _argumentBeforeTest) {
        argumentBeforeTest = _argumentBeforeTest;
    }

    public boolean isArgumentTest() {
        return argumentTest;
    }

    public void setArgumentTest(boolean _argumentTest) {
        this.argumentTest = _argumentTest;
    }

    public boolean isCalcArgumentTest() {
        return calcArgumentTest;
    }

    public void setCalcArgumentTest(boolean _calcArgumentTest) {
        this.calcArgumentTest = _calcArgumentTest;
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

    public ImplicitMethods getImplicits() {
        return implicits;
    }

    public void setImplicits(ImplicitMethods _implicits) {
        this.implicits = _implicits;
    }

    public ImplicitMethods getImplicitsTest() {
        return implicitsTest;
    }

    public void setImplicitsTest(ImplicitMethods _implicitsTest) {
        this.implicitsTest = _implicitsTest;
    }

    public ImplicitMethods getImplicitsCompound() {
        return implicitsCompound;
    }

    public void setImplicitsCompound(ImplicitMethods _implicitsCompound) {
        this.implicitsCompound = _implicitsCompound;
    }

    public ImplicitMethods getImplicitsSemiFrom() {
        return implicitsSemiFrom;
    }

    public void setImplicitsSemiFrom(ImplicitMethods _implicitsSemiFrom) {
        this.implicitsSemiFrom = _implicitsSemiFrom;
    }

    public ImplicitMethods getImplicitsSemiTo() {
        return implicitsSemiTo;
    }

    public void setImplicitsSemiTo(ImplicitMethods _implicitsSemiTo) {
        this.implicitsSemiTo = _implicitsSemiTo;
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
}
