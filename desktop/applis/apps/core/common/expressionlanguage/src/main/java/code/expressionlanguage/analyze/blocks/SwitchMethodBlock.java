package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
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
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class SwitchMethodBlock extends MemberCallingsBlock implements AnalyzedSwitch,AnnotableParametersBlock {
    private ResultParsedAnnots annotations = new ResultParsedAnnots();

    private final CustList<ResultParsedAnnots> annotationsParams = new CustList<ResultParsedAnnots>();

    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private final CustList<ResultExpression> resList = new CustList<ResultExpression>();
    private CustList<CustList<OperationNode>> rootsList = new CustList<CustList<OperationNode>>();
    private final CustList<CustList<ResultExpression>> resLists = new CustList<CustList<ResultExpression>>();

    private RootBlock parentType;
    private AccessedBlock accessedBlock;
    private int indexEnd;
    private final StringList allReservedInners = new StringList();

    private AnaClassArgumentMatching result = new AnaClassArgumentMatching("");

    private boolean instance;
    private String instanceTest = "";
    private String retType = "";

    private int conditionNb;

    private int caseCount;
    private final MethodAccessKind kind;
    private boolean retRef;
    private String name;
    private final AnaAnonFctContent anaAnonFctContent = new AnaAnonFctContent();
    public SwitchMethodBlock(int _offset, MethodAccessKind _statMeth) {
        super(_offset);
        kind = _statMeth;
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
        return getId().getSignature(_page.getDisplayedStrings());
    }
    public MethodId getId() {
        String name_ = getName();
        StringList pTypes_ = new StringList(result.getSingleNameOrEmpty());
        return new MethodId(retRef, kind, name_, pTypes_,new CustList<BoolVal>(BoolVal.FALSE), false);
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

    public boolean isInstance() {
        return instance;
    }

    @Override
    public void setInstanceTest(boolean _instance, String _instanceTest) {
        instance = _instance;
        this.instanceTest = _instanceTest;
    }

    public AnaAnonFctContent getAnaAnonFctContent() {
        return anaAnonFctContent;
    }

    public AnaCache getCache() {
        return anaAnonFctContent.getCache();
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

    public AccessedBlock getAccessedBlock() {
        return accessedBlock;
    }

    public void setAccessedBlock(AccessedBlock _accessedBlock) {
        this.accessedBlock = _accessedBlock;
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
        int len_ = annotations.getAnnotations().size();
        for (int i = 0; i < len_; i++) {
            _page.setSumOffset(resList.get(i).getSumOffset());
            _page.zeroOffset();
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(resList.get(i), c_, _page);
            ReachOperationUtil.tryCalculate(r_, _page);
            roots.add(r_);
        }
    }

    @Override
    public void buildAnnotationsParameters(AnalyzedPageEl _page) {
        int j_ = 0;
        rootsList = new CustList<CustList<OperationNode>>();
        for (ResultParsedAnnots l: annotationsParams) {
            CustList<OperationNode> rootList_ = new CustList<OperationNode>();
            int len_ = l.getAnnotations().size();
            for (int i = 0; i < len_; i++) {
                _page.setSumOffset(resLists.get(j_).get(i).getSumOffset());
                _page.zeroOffset();
                Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
                OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(resLists.get(j_).get(i), c_, _page);
                ReachOperationUtil.tryCalculate(r_, _page);
                rootList_.add(r_);
            }
            rootsList.add(rootList_);
            j_++;
        }
    }

    public ResultParsedAnnots getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ResultParsedAnnots _a) {
        this.annotations = _a;
    }

    public CustList<ResultParsedAnnots> getAnnotationsParams() {
        return annotationsParams;
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

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int _caseCount) {
        this.caseCount = _caseCount;
    }
}
