package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaInvokingConstructorContent;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractInvokingConstructor extends InvokingOperation implements PreAnalyzableOperation,RetrieveConstructor {

    private final String methodName;
    private ConstructorId constId;
    private final AnaInvokingConstructorContent invokingConstructorContent;

    private AnaTypeFct constructor;
    private AnaClassArgumentMatching from;
    private final CustList<ConstructorInfo> ctors = new CustList<ConstructorInfo>();
    private MemberId memberId = new MemberId();
    private RootBlock type;
    public AbstractInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
        invokingConstructorContent = new AnaInvokingConstructorContent();
        invokingConstructorContent.setOffsetOper(getOperations().getOperators().firstKey());
    }

    public int getOffsetOper() {
        return invokingConstructorContent.getOffsetOper();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        from = getFrom(_page);
        if (from == null) {
            return;
        }
        if (type == null) {
            return;
        }
        String clCurName_ = from.getName();
        tryGetCtors(clCurName_,ctors, _page, type);
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringUtil.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        String varargParam_ = getVarargParam(chidren_);
        if (from == null) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            checkPositionBasis(_page);
            return;
        }
        if (type == null) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            checkPositionBasis(_page);
            return;
        }
        ConstructorId feed_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            feed_ = MethodId.to(idClass_, id_.getConstraints());
        }
        String clCurName_ = from.getName();
        invokingConstructorContent.setFormattedType(new AnaFormattedRootBlock(type,clCurName_));
        String id_ = StringExpUtil.getIdFromAllTypes(clCurName_);
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.isOk()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ConstrustorIdVarArg ctorRes_;
        ctorRes_ = getDeclaredCustConstructor(varargOnly_, from,id_,type, feed_, varargParam_, name_, _page);
        if (ctorRes_.getRealId() == null) {
            buildCtorError(name_,_page,from.getName());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            checkPositionBasis(_page);
            return;
        }
        constructor = ctorRes_.getPair();
        memberId = ctorRes_.getMemberId();
        constId = ctorRes_.getRealId();
        checkPositionBasis(_page);
        postAnalysis(ctorRes_, name_, _page);
    }

    abstract AnaClassArgumentMatching getFrom(AnalyzedPageEl _page);
    private void postAnalysis(ConstrustorIdVarArg _res, NameParametersFilter _args, AnalyzedPageEl _page) {
        if (_res.isVarArgToCall()) {
            invokingConstructorContent.setNaturalVararg(constId.getParametersTypes().size() - 1);
            invokingConstructorContent.setLastType(constId.getParametersTypes().last());
        }
        unwrapArgsFct(constId, invokingConstructorContent.getNaturalVararg(), invokingConstructorContent.getLastType(), _args.getAll(), _page);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    void checkPositionBasis(AnalyzedPageEl _page) {
        AbsBk curBlock_ = _page.getCurrentBlock();
        if (getParent() != null) {
            //error
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(curBlock_.getFile().getFileName());
            call_.setIndexFile(getFullIndexInEl());
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorEnd());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        } else {
            if (!(curBlock_.getParent() instanceof ConstructorBlock)) {
                //error
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(curBlock_.getFile().getFileName());
                call_.setIndexFile(getFullIndexInEl());
                //key word len
                call_.buildError(_page.getAnalysisMessages().getCallCtor());
                _page.getLocalizer().addError(call_);
                addErr(call_.getBuiltError());
            } else if (!(curBlock_ instanceof Line)) {
                //error
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(curBlock_.getFile().getFileName());
                call_.setIndexFile(getFullIndexInEl());
                //key word len
                call_.buildError(_page.getAnalysisMessages().getCallCtorBeforeBlock());
                _page.getLocalizer().addError(call_);
                addErr(call_.getBuiltError());
            } else {
                checkPosition(_page);
            }
        }
    }
    void checkPosition(AnalyzedPageEl _page) {
        AbsBk curBlock_ = _page.getCurrentBlock();
        if (curBlock_.getParent().getFirstChild() != curBlock_) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(curBlock_.getFile().getFileName());
            call_.setIndexFile(getFullIndexInEl());
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorFirstLine());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
    }

    public AnaTypeFct getConstructor() {
        return constructor;
    }

    public final ConstructorId getConstId() {
        return constId;
    }

    public String getMethodName() {
        return methodName;
    }

    public AnaInvokingConstructorContent getInvokingConstructorContent() {
        return invokingConstructorContent;
    }

    public CustList<ConstructorInfo> getCtors() {
        return ctors;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public RootBlock getType() {
        return type;
    }

    public void setType(RootBlock _type) {
        type = _type;
    }
}
