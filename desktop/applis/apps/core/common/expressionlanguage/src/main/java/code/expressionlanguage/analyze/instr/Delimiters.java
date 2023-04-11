package code.expressionlanguage.analyze.instr;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.NumberInfos;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.NumberUtil;

public final class Delimiters {

    private int badOffset=-1;
    private int indexEnd;
    private boolean partOfString;
    private final CustList<OperatorOffsetLength> allowedOperatorsIndexes = new CustList<OperatorOffsetLength>();
    private final Ints namedArgs = new Ints();
    private final Ints delStringsChars = new Ints();
    private final Ints delTextBlocks = new Ints();
    private final Ints delNumbers = new Ints();
    private final Ints delKeyWordSuper = new Ints();
    private final Ints delKeyWordSuperAccess = new Ints();
    private final Ints delKeyWordClassChoice = new Ints();
    private final Ints delKeyWordStatic = new Ints();
    private final Ints delKeyWordStaticCall = new Ints();
    private final StringList delKeyWordStaticExtract = new StringList();
    private final CustList<AnaGeneType> staticAccessTypes = new CustList<AnaGeneType>();
    private final CustList<AnaResultPartTypeDtoInt> staticParts = new CustList<AnaResultPartTypeDtoInt>();
    private final Ints delExplicit = new Ints();
    private final Ints delCast = new Ints();
    private final StringList delCastExtract = new StringList();
    private final CustList<AnaResultPartTypeDtoInt> castParts = new CustList<AnaResultPartTypeDtoInt>();
    private final Ints delLambda = new Ints();
    private final Ints delIds = new Ints();
    private final Ints delLoopVars = new Ints();
    private final Ints delVararg = new Ints();
    private final Ints delDefaultValue = new Ints();
    private final Ints delClass = new Ints();
    private final Ints delSimpleAnnotations = new Ints();
    private final StackDelimiters stack = new StackDelimiters();
    private final CustList<TextBlockInfo> stringInfo = new CustList<TextBlockInfo>();
    private final CustList<NumberInfos> nbInfos = new CustList<NumberInfos>();
    private final CustList<VariableInfo> variables = new CustList<VariableInfo>();
    private final Ints dimsAddonIndexes = new Ints();
    private final Ints delAccessIndexers = new Ints();
    private final Ints delValues = new Ints();
    private final Ints delAnnotNew = new Ints();
    private boolean enabledOp = true;

    public int getBadOffset() {
        return badOffset;
    }

    public void setBadOffset(int _badOffset) {
        badOffset = NumberUtil.max(_badOffset,0);
    }

    public CustList<OperatorOffsetLength> getAllowedOperatorsIndexes() {
        return allowedOperatorsIndexes;
    }

    public Ints getNamedArgs() {
        return namedArgs;
    }

    public Ints getDelTextBlocks() {
        return delTextBlocks;
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

    public Ints getDelKeyWordStaticCall() {
        return delKeyWordStaticCall;
    }

    public StringList getDelKeyWordStaticExtract() {
        return delKeyWordStaticExtract;
    }

    public CustList<AnaGeneType> getStaticAccessTypes() {
        return staticAccessTypes;
    }

    public CustList<AnaResultPartTypeDtoInt> getStaticParts() {
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

    public CustList<AnaResultPartTypeDtoInt> getCastParts() {
        return castParts;
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

    public StackDelimiters getStack() {
        return stack;
    }

    public int getIndexEnd() {
        return indexEnd;
    }
    public void setIndexEnd(int _indexEnd) {
        indexEnd = _indexEnd;
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

    public CustList<TextBlockInfo> getStringInfo() {
        return stringInfo;
    }
    public CustList<VariableInfo> getVariables() {
        return variables;
    }

    public Ints getDimsAddonIndexes() {
        return dimsAddonIndexes;
    }

    public Ints getDelAccessIndexers() {
        return delAccessIndexers;
    }

    public Ints getDelValues() {
        return delValues;
    }

    public Ints getDelAnnotNew() {
        return delAnnotNew;
    }

    public boolean isEnabledOp() {
        return enabledOp;
    }

    public void setEnabledOp(boolean _enabledOp) {
        this.enabledOp = _enabledOp;
    }
}
