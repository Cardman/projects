package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.AnaAnonFctContent;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class SwitchMethodBlock extends MemberCallingsBlock implements AnalyzedSwitch,AnnotableParametersBlock {
    private ResultParsedAnnots annotations = new ResultParsedAnnots();

    private final CustList<ResultParsedAnnots> annotationsParams = new CustList<ResultParsedAnnots>();

    private AccessedBlock accessedBlock;
    private int indexEnd;
    private final StringList allReservedInners = new StringList();

    private AnaClassArgumentMatching result = new AnaClassArgumentMatching("");

    private boolean instance;
    private boolean forceInstance;
    private String instanceTest = "";
    private String retType = "";

    private int conditionNb;

    private final MethodAccessKind kind;
    private boolean retRef;
    private String name;
    private final AnaAnonFctContent anaAnonFctContent = new AnaAnonFctContent();
    private final StrTypes values = new StrTypes();
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
    public String getSignature(DisplayedStrings _page) {
        return getId().getSignature(_page);
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

    public boolean isForceInstance() {
        return forceInstance;
    }

    public void setForceInstance(boolean _f) {
        this.forceInstance = _f;
    }

    public StrTypes getValues() {
        return values;
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
        annotations.buildAnnotations(_page);
    }

    @Override
    public void buildAnnotationsParameters(AnalyzedPageEl _page) {
        for (ResultParsedAnnots l: annotationsParams) {
            l.buildAnnotations(_page);
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

}
