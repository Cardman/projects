package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.AnaAnonFctContent;
import code.util.BooleanList;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class SwitchMethodBlock extends MemberCallingsBlock implements AnalyzedSwitch,AnnotableParametersBlock {
    private final StringList annotations = new StringList();

    private final Ints annotationsIndexes = new Ints();
    private final CustList<StringList> annotationsParams = new CustList<StringList>();
    private final CustList<Ints> annotationsIndexesParams = new CustList<Ints>();

    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private final CustList<ResultExpression> resList = new CustList<ResultExpression>();
    private CustList<CustList<OperationNode>> rootsList = new CustList<CustList<OperationNode>>();
    private final CustList<CustList<ResultExpression>> resLists = new CustList<CustList<ResultExpression>>();

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
    public SwitchMethodBlock(int _offset, AnalyzedPageEl _page) {
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
        return new MethodId(retRef, kind, name_, pTypes_,new CustList<Boolean>(false), false);
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

    public AnaAnonFctContent getAnaAnonFctContent() {
        return anaAnonFctContent;
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

    @Override
    public void buildAnnotations(AnalyzedPageEl _page) {
        roots = new CustList<OperationNode>();
        int len_ = annotationsIndexes.size();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            _page.setGlobalOffset(begin_);
            _page.zeroOffset();
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(resList.get(i), annotations.get(i).trim(), c_, _page);
            ReachOperationUtil.tryCalculate(r_, _page);
            roots.add(r_);
        }
    }

    @Override
    public void buildAnnotationsParameters(AnalyzedPageEl _page) {
        int j_ = 0;
        rootsList = new CustList<CustList<OperationNode>>();
        for (Ints l: annotationsIndexesParams) {
            CustList<OperationNode> rootList_ = new CustList<OperationNode>();
            int len_ = l.size();
            StringList list_ = annotationsParams.get(j_);
            for (int i = 0; i < len_; i++) {
                int begin_ = l.get(i);
                _page.setGlobalOffset(begin_);
                _page.zeroOffset();
                Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
                OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(resLists.get(j_).get(i), list_.get(i).trim(), c_, _page);
                ReachOperationUtil.tryCalculate(r_, _page);
                rootList_.add(r_);
            }
            rootsList.add(rootList_);
            j_++;
        }
    }

    public StringList getAnnotations() {
        return annotations;
    }

    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public CustList<StringList> getAnnotationsParams() {
        return annotationsParams;
    }

    public CustList<Ints> getAnnotationsIndexesParams() {
        return annotationsIndexesParams;
    }

    public CustList<ResultExpression> getResList() {
        return resList;
    }

    public CustList<CustList<ResultExpression>> getResLists() {
        return resLists;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public CustList<CustList<OperationNode>> getRootsList() {
        return rootsList;
    }

}
