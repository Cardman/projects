package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ClassMethodIdAncestor;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.ConstructorBlock;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public abstract class AbstractInvokingConstructor extends InvokingOperation implements PreAnalyzableOperation,RetrieveConstructor {

    private String methodName;
    private ConstructorId constId;
    private String classFromName = EMPTY_STRING;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private int offsetOper;
    private ClassArgumentMatching from;
    private CustList<ConstructorInfo> ctors = new CustList<ConstructorInfo>();
    private int rootNumber = -1;
    private int memberNumber = -1;
    public AbstractInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
        offsetOper = getOperations().getOperators().firstKey();
    }

    public int getOffsetOper() {
        return offsetOper;
    }

    @Override
    public void preAnalyze(ContextEl _an) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _an);
        from = getFrom(_an);
        if (from == null) {
            return;
        }
        String clCurName_ = from.getName();
        tryGetCtors(_an,clCurName_,ctors);
    }

    @Override
    public final void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        LgNames stds_ = _conf.getStandards();
        String varargParam_ = getVarargParam(chidren_);
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
        if (from == null) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            checkPositionBasis(_conf);
            return;
        }
        ConstructorId feed_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            boolean vararg_ = id_.getConstraints().isVararg();
            StringList params_ = id_.getConstraints().getParametersTypes();
            feed_ = new ConstructorId(idClass_, params_, vararg_);
        }
        String clCurName_ = from.getName();
        classFromName = clCurName_;
        RootBlock type_ = _conf.getAnalyzing().getAnaClassBody(StringExpUtil.getIdFromAllTypes(clCurName_));
        ConstrustorIdVarArg ctorRes_;
        ctorRes_ = getDeclaredCustConstructor(this,_conf, varargOnly_, from,type_, feed_, varargParam_, OperationNode.toArgArray(firstArgs_));
        if (ctorRes_.getRealId() == null) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            checkPositionBasis(_conf);
            return;
        }
        rootNumber = ctorRes_.getRootNumber();
        memberNumber = ctorRes_.getMemberNumber();
        constId = ctorRes_.getRealId();
        checkPositionBasis(_conf);
        postAnalysis(_conf, ctorRes_, chidren_, firstArgs_);
    }

    abstract ClassArgumentMatching getFrom(ContextEl _conf);
    final void postAnalysis(ContextEl _conf, ConstrustorIdVarArg _res, CustList<OperationNode> _children, CustList<ClassArgumentMatching> _args) {
        if (_res.isVarArgToCall()) {
            naturalVararg = constId.getParametersTypes().size() - 1;
            lastType = constId.getParametersTypes().last();
        }
        unwrapArgsFct(_children, constId, naturalVararg, lastType, _args, _conf);
        LgNames stds_ = _conf.getStandards();
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }

    void checkPositionBasis(ContextEl _conf) {
        Block curBlock_ = _conf.getAnalyzing().getCurrentBlock();
        if (getParent() != null) {
            //error
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(curBlock_.getFile().getFileName());
            call_.setIndexFile(getFullIndexInEl());
            //key word len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorEnd());
            _conf.addError(call_);
            getErrs().add(call_.getBuiltError());
        } else {
            if (!(curBlock_.getParent() instanceof ConstructorBlock)) {
                //error
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(curBlock_.getFile().getFileName());
                call_.setIndexFile(getFullIndexInEl());
                //key word len
                call_.buildError(_conf.getAnalysisMessages().getCallCtor());
                _conf.addError(call_);
                getErrs().add(call_.getBuiltError());
            } else if (!(curBlock_ instanceof Line)) {
                //error
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(curBlock_.getFile().getFileName());
                call_.setIndexFile(getFullIndexInEl());
                //key word len
                call_.buildError(_conf.getAnalysisMessages().getCallCtorBeforeBlock());
                _conf.addError(call_);
                getErrs().add(call_.getBuiltError());
            } else {
                checkPosition(_conf);
            }
        }
    }
    void checkPosition(ContextEl _conf) {
        Block curBlock_ = _conf.getAnalyzing().getCurrentBlock();
        if (curBlock_.getParent().getFirstChild() != curBlock_) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(curBlock_.getFile().getFileName());
            call_.setIndexFile(getFullIndexInEl());
            //key word len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorFirstLine());
            _conf.addError(call_);
            getErrs().add(call_.getBuiltError());
        }
    }

    public final ConstructorId getConstId() {
        return constId;
    }

    public String getClassFromName() {
        return classFromName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public CustList<ConstructorInfo> getCtors() {
        return ctors;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }
}
