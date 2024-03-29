package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaInvokingConstructorContent;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractInvokingConstructor extends InvokingOperation implements PreAnalyzableOperation,RetrieveConstructor {
    private final CustList<AnaResultPartTypeDtoInt> partOffsets = new CustList<AnaResultPartTypeDtoInt>();

    private final String methodName;
    private ConstructorId constId;
    private final AnaInvokingConstructorContent invokingConstructorContent;

    private AnaTypeFct constructor;
    private AnaClassArgumentMatching from;
    private final CustList<ConstructorInfo> ctors = new CustList<ConstructorInfo>();
    private MemberId memberId = new MemberId();
    private AnaFormattedRootBlock type;

    protected AbstractInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = _op.getFctName();
        invokingConstructorContent = new AnaInvokingConstructorContent();
        invokingConstructorContent.setOffsetOper(getOperators().firstKey());
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
        tryGetCtors(clCurName_,ctors, _page, type.getRootBlock());
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
        invokingConstructorContent.setFormattedType(type);
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ConstrustorIdVarArg ctorRes_;
        ctorRes_ = getDeclaredCustConstructor(varargOnly_, from, type.getRootBlock(), feed_, varargParam_, name_, _page);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
        if (ctorRes_ == null) {
            buildCtorError(name_,_page,from.getName());
            checkPositionBasis(_page);
            return;
        }
        postAnalysis(ctorRes_);
        checkPositionBasis(_page);
    }

    abstract AnaClassArgumentMatching getFrom(AnalyzedPageEl _page);

    protected InterfaceBlock tryGetAsInterface(AnalyzedPageEl _page) {
        String cl_ = getMethodName();
        int leftPar_ = cl_.indexOf(PAR_LEFT) + 1;
        cl_ = cl_.substring(leftPar_, cl_.lastIndexOf(PAR_RIGHT));
        ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(leftPar_, cl_, _page);
        cl_ = resolvedIdType_.getFullName();
        partOffsets.add(resolvedIdType_.getDels());
        RootBlock candidate_ = _page.getAnaClassBody(cl_);
        if (!(candidate_ instanceof InterfaceBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //type len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return null;
        }
        return (InterfaceBlock) candidate_;
    }

    protected AnaClassArgumentMatching superType(AnalyzedPageEl _page, InterfaceBlock _candidate, AnaFormattedRootBlock _cur) {
        AnaFormattedRootBlock superClass_ = AnaInherits.getFormattedOverridingFullTypeByBases(_cur, _candidate);
        if (superClass_ == null) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //type len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return null;
        }
        setType(superClass_);
        return new AnaClassArgumentMatching(superClass_.getFormatted());
    }

    private void postAnalysis(ConstrustorIdVarArg _res) {
        constructor = _res.getParametrableContent().getPair();
        memberId = _res.getParametrableContent().getMemberId();
        constId = _res.getRealId();
        if (_res.getParametrableContent().isVarArgToCall()) {
            int nbParams_ = constId.getParametersTypesLength();
            invokingConstructorContent.setNaturalVararg(nbParams_ - 1);
            invokingConstructorContent.setLastType(constId.getParametersType(nbParams_ - 1));
        }
    }

    void checkPositionBasis(AnalyzedPageEl _page) {
        AbsBk curBlock_ = _page.getCurrentBlock();
        if (getParent() != null) {
            //error
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(curBlock_.getFile());
            call_.setIndexFile(_page);
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorEnd());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        } else {
            if (!(curBlock_.getParent() instanceof ConstructorBlock)) {
                //error
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFile(curBlock_.getFile());
                call_.setIndexFile(_page);
                //key word len
                call_.buildError(_page.getAnalysisMessages().getCallCtor());
                _page.getLocalizer().addError(call_);
                addErr(call_.getBuiltError());
            } else if (!(curBlock_ instanceof Line)) {
                //error
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFile(curBlock_.getFile());
                call_.setIndexFile(_page);
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
            call_.setFile(curBlock_.getFile());
            call_.setIndexFile(_page);
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorFirstLine());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
    }

    public CustList<AnaResultPartTypeDtoInt> getPartOffsets() {
        return partOffsets;
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

    public AnaFormattedRootBlock getType() {
        return type;
    }

    public void setType(AnaFormattedRootBlock _type) {
        type = _type;
    }

}
