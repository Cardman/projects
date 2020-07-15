package code.expressionlanguage.exec.variables;
import code.expressionlanguage.Argument;
import code.expressionlanguage.functionid.ClassMethodId;
import code.util.CustList;

public class ArgumentsPair {

    private Argument argument;
    private Argument argumentBeforeTest;
    private boolean argumentTest;
    private boolean calcArgumentTest;
    private Argument previousArgument;
    private CustList<ClassMethodId> implicits = new CustList<ClassMethodId>();
    private CustList<ClassMethodId> implicitsTest = new CustList<ClassMethodId>();
    private CustList<ClassMethodId> implicitsCompound = new CustList<ClassMethodId>();
    private CustList<ClassMethodId> implicitsSemiFrom = new CustList<ClassMethodId>();
    private CustList<ClassMethodId> implicitsSemiTo = new CustList<ClassMethodId>();
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

    public Argument getArgumentBeforeTest() {
        return argumentBeforeTest;
    }

    public void setArgumentBeforeTest(Argument _argumentBeforeTest) {
        argumentBeforeTest = _argumentBeforeTest;
    }

    public boolean isArgumentTest() {
        return argumentTest;
    }

    public void setArgumentTest(boolean argumentTest) {
        this.argumentTest = argumentTest;
    }

    public boolean isCalcArgumentTest() {
        return calcArgumentTest;
    }

    public void setCalcArgumentTest(boolean calcArgumentTest) {
        this.calcArgumentTest = calcArgumentTest;
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

    public void setEndCalculate(boolean endCalculate) {
        this.endCalculate = endCalculate;
    }

    public CustList<ClassMethodId> getImplicits() {
        return implicits;
    }

    public void setImplicits(CustList<ClassMethodId> implicits) {
        this.implicits = implicits;
    }

    public CustList<ClassMethodId> getImplicitsTest() {
        return implicitsTest;
    }

    public void setImplicitsTest(CustList<ClassMethodId> implicitsTest) {
        this.implicitsTest = implicitsTest;
    }

    public CustList<ClassMethodId> getImplicitsCompound() {
        return implicitsCompound;
    }

    public void setImplicitsCompound(CustList<ClassMethodId> implicitsCompound) {
        this.implicitsCompound = implicitsCompound;
    }

    public CustList<ClassMethodId> getImplicitsSemiFrom() {
        return implicitsSemiFrom;
    }

    public void setImplicitsSemiFrom(CustList<ClassMethodId> implicitsSemiFrom) {
        this.implicitsSemiFrom = implicitsSemiFrom;
    }

    public CustList<ClassMethodId> getImplicitsSemiTo() {
        return implicitsSemiTo;
    }

    public void setImplicitsSemiTo(CustList<ClassMethodId> implicitsSemiTo) {
        this.implicitsSemiTo = implicitsSemiTo;
    }

    public int getIndexImplicit() {
        return indexImplicit;
    }

    public void setIndexImplicit(int indexImplicit) {
        this.indexImplicit = indexImplicit;
    }

    public int getIndexImplicitTest() {
        return indexImplicitTest;
    }

    public void setIndexImplicitTest(int indexImplicitTest) {
        this.indexImplicitTest = indexImplicitTest;
    }

    public int getIndexImplicitCompound() {
        return indexImplicitCompound;
    }

    public void setIndexImplicitCompound(int indexImplicitCompound) {
        this.indexImplicitCompound = indexImplicitCompound;
    }

    public int getIndexImplicitSemiFrom() {
        return indexImplicitSemiFrom;
    }

    public void setIndexImplicitSemiFrom(int indexImplicitSemiFrom) {
        this.indexImplicitSemiFrom = indexImplicitSemiFrom;
    }

    public int getIndexImplicitSemiTo() {
        return indexImplicitSemiTo;
    }

    public void setIndexImplicitSemiTo(int indexImplicitSemiTo) {
        this.indexImplicitSemiTo = indexImplicitSemiTo;
    }
}
