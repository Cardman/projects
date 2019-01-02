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

    public void setDelKeyWordSuper(Numbers<Integer> _delKeyWordSuper) {
        delKeyWordSuper = _delKeyWordSuper;
    }

    public Numbers<Integer> getDelKeyWordSuperAccess() {
        return delKeyWordSuperAccess;
    }

    public void setDelKeyWordSuperAccess(Numbers<Integer> _delKeyWordSuperAccess) {
        delKeyWordSuperAccess = _delKeyWordSuperAccess;
    }

    public Numbers<Integer> getDelKeyWordClassChoice() {
        return delKeyWordClassChoice;
    }

    public void setDelKeyWordClassChoice(Numbers<Integer> _delKeyWordClassChoice) {
        delKeyWordClassChoice = _delKeyWordClassChoice;
    }

    public Numbers<Integer> getDelKeyWordStatic() {
        return delKeyWordStatic;
    }

    public void setDelKeyWordStatic(Numbers<Integer> _delKeyWordStatic) {
        delKeyWordStatic = _delKeyWordStatic;
    }

    public StringList getDelKeyWordStaticExtract() {
        return delKeyWordStaticExtract;
    }

    public void setDelKeyWordStaticExtract(StringList _delKeyWordStaticExtract) {
        delKeyWordStaticExtract = _delKeyWordStaticExtract;
    }

    public Numbers<Integer> getDelCast() {
        return delCast;
    }

    public void setDelCast(Numbers<Integer> _delCast) {
        delCast = _delCast;
    }

    public StringList getDelCastExtract() {
        return delCastExtract;
    }

    public void setDelCastExtract(StringList _delCastExtract) {
        delCastExtract = _delCastExtract;
    }

    public Numbers<Integer> getDelInstanceof() {
        return delInstanceof;
    }

    public void setDelInstanceof(Numbers<Integer> _delInstanceof) {
        delInstanceof = _delInstanceof;
    }

    public Numbers<Integer> getDelLambda() {
        return delLambda;
    }

    public void setDelLambda(Numbers<Integer> _delLambda) {
        delLambda = _delLambda;
    }

    public Numbers<Integer> getDelIds() {
        return delIds;
    }

    public void setDelIds(Numbers<Integer> _delIds) {
        delIds = _delIds;
    }

    public Numbers<Integer> getDelLoopVars() {
        return delLoopVars;
    }

    public void setDelLoopVars(Numbers<Integer> _delLoopVars) {
        delLoopVars = _delLoopVars;
    }

    public Numbers<Integer> getDelVararg() {
        return delVararg;
    }

    public void setDelVararg(Numbers<Integer> _delVararg) {
        delVararg = _delVararg;
    }

    public Numbers<Integer> getDelClass() {
        return delClass;
    }

    public void setDelClass(Numbers<Integer> _delClass) {
        delClass = _delClass;
    }

    public Numbers<Integer> getDelSimpleAnnotations() {
        return delSimpleAnnotations;
    }

    public void setDelSimpleAnnotations(Numbers<Integer> _delSimpleAnnotations) {
        delSimpleAnnotations = _delSimpleAnnotations;
    }

    public Numbers<Integer> getCallings() {
        return callings;
    }

    public void setCallings(Numbers<Integer> _callings) {
        callings = _callings;
    }

    public int getFirstPrintableChar() {
        return firstPrintableChar;
    }
    public void setFirstPrintableChar(int _firstPrintableChar) {
        firstPrintableChar = _firstPrintableChar;
    }

    public int getAbsoluteOffset() {
        return absoluteOffset;
    }
    public void setAbsoluteOffset(int _absoluteOffset) {
        absoluteOffset = _absoluteOffset;
    }
    public int getChildOffest() {
        return childOffest;
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

    public Numbers<Integer> getDimsAddonIndexes() {
        return dimsAddonIndexes;
    }

    public void setDimsAddonIndexes(Numbers<Integer> _dimsAddonIndexes) {
        dimsAddonIndexes = _dimsAddonIndexes;
    }

    public Numbers<Integer> getEscapings() {
        return escapings;
    }

    public void setEscapings(Numbers<Integer> _escapings) {
        escapings = _escapings;
    }

}
