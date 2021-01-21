package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.AnaAnonFctContent;
import code.util.BooleanList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class SwitchMethodBlock extends MemberCallingsBlock implements AnalyzedSwitch {
    private RootBlock parentType;
    private OperatorBlock operator;
    private int indexEnd;
    private final StringList allReservedInners = new StringList();

    private AnaClassArgumentMatching result = new AnaClassArgumentMatching("");

    private boolean enumTest;
    private String instanceTest = "";
    private String retType = "";

    private int conditionNb;

    private final MethodAccessKind kind;
    private boolean retRef;
    private String name;
    private final AnaAnonFctContent anaAnonFctContent = new AnaAnonFctContent();
    public SwitchMethodBlock(OffsetsBlock _offset, AnalyzedPageEl _page) {
        super(_offset);
        kind = _page.getStaticContext();
    }

    public AnaClassArgumentMatching getResult() {
        return result;
    }

    public void setResult(AnaClassArgumentMatching _result) {
        this.result = _result;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return kind;
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return getId().getSignature(_page);
    }
    public MethodId getId() {
        String name_ = getName();
        StringList pTypes_ = new StringList(result.getSingleNameOrEmpty());
        return new MethodId(retRef, kind, name_, pTypes_,new BooleanList(false), false);
    }

    public String getName() {
        return name;
    }
    public void setIntenName(String _name) {
        this.name = StringUtil.concat(".",_name);
    }

    public void setRetRef(boolean _retRef) {
        this.retRef = _retRef;
    }

    public String getInstanceTest() {
        return instanceTest;
    }

    public void setInstanceTest(String _instanceTest) {
        this.instanceTest = _instanceTest;
    }

    public AnaCache getCache() {
        return anaAnonFctContent.getCache();
    }
    public boolean isEnumTest() {
        return enumTest;
    }

    public void setEnumTest(boolean _enumTest) {
        this.enumTest = _enumTest;
    }

    public int getConditionNb() {
        return conditionNb;
    }

    public void setConditionNb(int _conditionNb) {
        conditionNb = _conditionNb;
    }

    public RootBlock getParentType() {
        return parentType;
    }

    public void setParentType(RootBlock _parentType) {
        this.parentType = _parentType;
    }

    public OperatorBlock getOperator() {
        return operator;
    }

    public void setOperator(OperatorBlock _operator) {
        this.operator = _operator;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int _indexEnd) {
        this.indexEnd = _indexEnd;
    }

    public StringList getAllReservedInners() {
        return allReservedInners;
    }

    public boolean isRetRef() {
        return retRef;
    }

    public String getRetType() {
        return retType;
    }

    public void setRetType(String _retType) {
        this.retType = _retType;
    }
}
