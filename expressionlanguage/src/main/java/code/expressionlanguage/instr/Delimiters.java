package code.expressionlanguage.instr;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public final class Delimiters {

    private int badOffset=-1;
    private int firstPrintableChar = CustList.INDEX_NOT_FOUND_ELT;
    private int absoluteOffset;
    private int childOffest;
    private int indexBegin;
    private int indexEnd;
    private char begin;
    private char end;
    private boolean partOfString;
    private Numbers<Integer> allowedOperatorsIndexes = new Numbers<Integer>();
    private Numbers<Integer> delStringsChars = new Numbers<Integer>();
    private Numbers<Integer> delNumbers = new Numbers<Integer>();
    private Numbers<Integer> delKeyWordSuper = new Numbers<Integer>();
    private Numbers<Integer> delKeyWordSuperAccess = new Numbers<Integer>();
    private Numbers<Integer> delKeyWordClassChoice = new Numbers<Integer>();
    private Numbers<Integer> delKeyWordStatic = new Numbers<Integer>();
    private StringList delKeyWordStaticExtract = new StringList();
    private Numbers<Integer> delCast = new Numbers<Integer>();
    private StringList delCastExtract = new StringList();
    private Numbers<Integer> delInstanceof = new Numbers<Integer>();
    private Numbers<Integer> delLambda = new Numbers<Integer>();
    private Numbers<Integer> delIds = new Numbers<Integer>();
    private Numbers<Integer> delLoopVars = new Numbers<Integer>();
    private Numbers<Integer> delVararg = new Numbers<Integer>();
    private Numbers<Integer> delClass = new Numbers<Integer>();
    private Numbers<Integer> delSimpleAnnotations = new Numbers<Integer>();
    private Numbers<Integer> callings = new Numbers<Integer>();
    private CustList<StringInfo> stringInfo = new CustList<StringInfo>();
    private CustList<NumberInfos> nbInfos = new CustList<NumberInfos>();
    private CustList<VariableInfo> variables = new CustList<VariableInfo>();
    private Numbers<Integer> dimsAddonIndexes = new Numbers<Integer>();
    private Numbers<Integer> escapings = new Numbers<Integer>();
    private Numbers<Integer> delValue = new Numbers<Integer>();

    public int getBadOffset() {
        return badOffset;
    }

    public void setBadOffset(int _badOffset) {
        badOffset = Math.max(_badOffset,0);
    }

    public Numbers<Integer> getAllowedOperatorsIndexes() {
        return allowedOperatorsIndexes;
    }

    public Numbers<Integer> getDelStringsChars() {
        return delStringsChars;
    }

    public Numbers<Integer> getDelNumbers() {
        return delNumbers;
    }

    public Numbers<Integer> getDelKeyWordSuper() {
        return delKeyWordSuper;
    }

    public Numbers<Integer> getDelKeyWordSuperAccess() {
        return delKeyWordSuperAccess;
    }

    public Numbers<Integer> getDelKeyWordClassChoice() {
        return delKeyWordClassChoice;
    }

    public Numbers<Integer> getDelKeyWordStatic() {
        return delKeyWordStatic;
    }

    public StringList getDelKeyWordStaticExtract() {
        return delKeyWordStaticExtract;
    }

    public Numbers<Integer> getDelCast() {
        return delCast;
    }

    public Numbers<Integer> getDelValue() {
        return delValue;
    }

    public StringList getDelCastExtract() {
        return delCastExtract;
    }

    public Numbers<Integer> getDelInstanceof() {
        return delInstanceof;
    }

    public Numbers<Integer> getDelLambda() {
        return delLambda;
    }

    public Numbers<Integer> getDelIds() {
        return delIds;
    }

    public Numbers<Integer> getDelLoopVars() {
        return delLoopVars;
    }

    public Numbers<Integer> getDelVararg() {
        return delVararg;
    }

    public Numbers<Integer> getDelClass() {
        return delClass;
    }

    public Numbers<Integer> getDelSimpleAnnotations() {
        return delSimpleAnnotations;
    }

    public Numbers<Integer> getCallings() {
        return callings;
    }

    public void setChildOffest(int _childOffest) {
        childOffest = _childOffest;
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

    public Numbers<Integer> getDimsAddonIndexes() {
        return dimsAddonIndexes;
    }

    public Numbers<Integer> getEscapings() {
        return escapings;
    }

}
