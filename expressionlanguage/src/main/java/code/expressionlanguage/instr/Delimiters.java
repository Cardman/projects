package code.expressionlanguage.instr;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class Delimiters {

    private int badOffset=-1;
    private int indexBegin;
    private int indexEnd;
    private char begin;
    private char end;
    private boolean partOfString;
    private Ints allowedOperatorsIndexes = new Ints();
    private Ints delStringsChars = new Ints();
    private Ints delNumbers = new Ints();
    private Ints delKeyWordSuper = new Ints();
    private Ints delKeyWordSuperAccess = new Ints();
    private Ints delKeyWordClassChoice = new Ints();
    private Ints delKeyWordStatic = new Ints();
    private StringList delKeyWordStaticExtract = new StringList();
    private CustList<CustList<PartOffset>> staticParts = new CustList<CustList<PartOffset>>();
    private Ints delExplicit = new Ints();
    private Ints delCast = new Ints();
    private StringList delCastExtract = new StringList();
    private CustList<CustList<PartOffset>> castParts = new CustList<CustList<PartOffset>>();
    private Ints delInstanceof = new Ints();
    private Ints delLambda = new Ints();
    private Ints delIds = new Ints();
    private Ints delLoopVars = new Ints();
    private Ints delVararg = new Ints();
    private Ints delDefaultValue = new Ints();
    private Ints delClass = new Ints();
    private Ints delSimpleAnnotations = new Ints();
    private Ints callings = new Ints();
    private CustList<StringInfo> stringInfo = new CustList<StringInfo>();
    private CustList<NumberInfos> nbInfos = new CustList<NumberInfos>();
    private CustList<VariableInfo> variables = new CustList<VariableInfo>();
    private Ints dimsAddonIndexes = new Ints();
    private Ints escapings = new Ints();
    private Ints delAccessIndexers = new Ints();

    public int getBadOffset() {
        return badOffset;
    }

    public void setBadOffset(int _badOffset) {
        badOffset = Math.max(_badOffset,0);
    }

    public Ints getAllowedOperatorsIndexes() {
        return allowedOperatorsIndexes;
    }

    public Ints getDelStringsChars() {
        return delStringsChars;
    }

    public Ints getDelNumbers() {
        return delNumbers;
    }

    public Ints getDelKeyWordSuper() {
        return delKeyWordSuper;
    }

    public Ints getDelKeyWordSuperAccess() {
        return delKeyWordSuperAccess;
    }

    public Ints getDelKeyWordClassChoice() {
        return delKeyWordClassChoice;
    }

    public Ints getDelKeyWordStatic() {
        return delKeyWordStatic;
    }

    public StringList getDelKeyWordStaticExtract() {
        return delKeyWordStaticExtract;
    }

    public CustList<CustList<PartOffset>> getStaticParts() {
        return staticParts;
    }

    public Ints getDelExplicit() {
        return delExplicit;
    }

    public Ints getDelCast() {
        return delCast;
    }

    public StringList getDelCastExtract() {
        return delCastExtract;
    }

    public CustList<CustList<PartOffset>> getCastParts() {
        return castParts;
    }

    public Ints getDelInstanceof() {
        return delInstanceof;
    }

    public Ints getDelLambda() {
        return delLambda;
    }

    public Ints getDelIds() {
        return delIds;
    }

    public Ints getDelLoopVars() {
        return delLoopVars;
    }

    public Ints getDelVararg() {
        return delVararg;
    }

    public Ints getDelDefaultValue() {
        return delDefaultValue;
    }

    public Ints getDelClass() {
        return delClass;
    }

    public Ints getDelSimpleAnnotations() {
        return delSimpleAnnotations;
    }

    public Ints getCallings() {
        return callings;
    }

    public int getIndexBegin() {
        return indexBegin;
    }
    public void setIndexBegin(int _indexBegin) {
        indexBegin = _indexBegin;
    }
    public int getIndexEnd() {
        return indexEnd;
    }
    public void setIndexEnd(int _indexEnd) {
        indexEnd = _indexEnd;
    }
    public char getBegin() {
        return begin;
    }
    public void setBegin(char _begin) {
        begin = _begin;
    }
    public char getEnd() {
        return end;
    }
    public void setEnd(char _end) {
        end = _end;
    }
    public boolean isPartOfString() {
        return partOfString;
    }
    public void setPartOfString(boolean _partOfString) {
        partOfString = _partOfString;
    }

    public CustList<NumberInfos> getNbInfos() {
        return nbInfos;
    }
    public CustList<StringInfo> getStringInfo() {
        return stringInfo;
    }
    public CustList<VariableInfo> getVariables() {
        return variables;
    }
    public boolean isWordLastVariable() {
        return variables.last().getKind() == ConstType.WORD;
    }

    public Ints getDimsAddonIndexes() {
        return dimsAddonIndexes;
    }

    public Ints getEscapings() {
        return escapings;
    }

    public Ints getDelAccessIndexers() {
        return delAccessIndexers;
    }
}
