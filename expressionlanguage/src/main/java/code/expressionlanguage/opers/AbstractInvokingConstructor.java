package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadConstructorCall;
import code.expressionlanguage.errors.custom.UndefinedConstructorError;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.Line;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public abstract class AbstractInvokingConstructor extends InvokingOperation {

    private String methodName;
    private ConstructorId constId;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private int offsetOper;
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
    final void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public final void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodId idMethod_ = lookOnlyForId();
        LgNames stds_ = _conf.getStandards();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        ClassArgumentMatching clArg_ = getFrom(_conf);
        if (clArg_ == null) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
            checkPositionBasis(_conf);
            return;
        }
        ConstructorId feed_ = null;
        if (idMethod_ != null) {
            String idClass_ = idMethod_.getClassName();
            boolean vararg_ = idMethod_.getConstraints().isVararg();
            StringList params_ = idMethod_.getConstraints().getParametersTypes();
            feed_ = new ConstructorId(idClass_, params_, vararg_);
        }
        ConstrustorIdVarArg ctorRes_;
        ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, clArg_, feed_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (ctorRes_.getRealId() == null) {
            StringList cl_ = new StringList();
            for (ClassArgumentMatching c: firstArgs_) {
                cl_.add(StringList.join(c.getNames(), ""));
            }
            ConstructorId constId_ = new ConstructorId(StringList.join(clArg_.getNames(), ""), cl_, false);
            UndefinedConstructorError und_ = new UndefinedConstructorError();
            und_.setId(constId_.getSignature(_conf));
            und_.setClassName(StringList.join(clArg_.getNames(), ""));
            und_.setIndexFile(_conf.getCurrentLocationIndex());
            und_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
            checkPositionBasis(_conf);
            return;
        }
        constId = ctorRes_.getRealId();
        checkPositionBasis(_conf);
        postAnalysis(_conf, ctorRes_, chidren_, firstArgs_);
    }

    abstract ClassArgumentMatching getFrom(Analyzable _conf);
    final void postAnalysis(Analyzable _conf, ConstrustorIdVarArg _res, CustList<OperationNode> _children, CustList<ClassArgumentMatching> _args) {
        if (_res.isVarArgToCall()) {
            naturalVararg = constId.getParametersTypes().size() - 1;
            lastType = constId.getParametersTypes().last();
        }
        unwrapArgsFct(_children, constId, naturalVararg, lastType, _args, _conf);
        LgNames stds_ = _conf.getStandards();
        setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
    }

    final void checkPositionBasis(Analyzable _conf) {
        Block curBlock_ = _conf.getCurrentBlock();
        if (getParent() != null) {
            //error
            BadConstructorCall call_ = new BadConstructorCall();
            call_.setFileName(curBlock_.getFile().getFileName());
            call_.setIndexFile(0);
            call_.setLocalOffset(getFullIndexInEl());
            _conf.getClasses().addError(call_);
        } else {
            if (!(curBlock_.getParent() instanceof ConstructorBlock)) {
                //error
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(curBlock_.getFile().getFileName());
                call_.setIndexFile(0);
                call_.setLocalOffset(getFullIndexInEl());
                _conf.getClasses().addError(call_);
            } else if (!(curBlock_ instanceof Line)) {
                //error
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(curBlock_.getFile().getFileName());
                call_.setIndexFile(0);
                call_.setLocalOffset(getFullIndexInEl());
                _conf.getClasses().addError(call_);
            } else {
                checkPosition(_conf);
            }
        }
    }
    void checkPosition(Analyzable _conf) {
        Block curBlock_ = _conf.getCurrentBlock();
        if (curBlock_.getParent().getFirstChild() != curBlock_) {
            BadConstructorCall call_ = new BadConstructorCall();
            call_.setFileName(curBlock_.getFile().getFileName());
            call_.setIndexFile(0);
            call_.setLocalOffset(getFullIndexInEl());
            _conf.getClasses().addError(call_);
        }
    }

    public final ConstructorId getConstId() {
        return constId;
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
}
