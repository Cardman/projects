package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.Line;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessConstructor;
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.UndefinedConstructorError;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
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
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    final boolean isCallMethodCtor() {
        return true;
    }

    @Override
    public void analyze(Analyzable _conf) {
        String clCurName_ = _conf.getGlobalClass();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        int varargOnly_ = lookOnlyForVarArg();
        LgNames stds_ = _conf.getStandards();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        ClassArgumentMatching clArg_ = getFrom(_conf);
        if (clArg_ == null) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
            checkPositionBasis(_conf);
            return;
        }
        ConstrustorIdVarArg ctorRes_;
        ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, clArg_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (ctorRes_ == null) {
            StringList cl_ = new StringList();
            for (ClassArgumentMatching c: firstArgs_) {
                cl_.add(c.getNames().join(""));
            }
            ConstructorId constId_ = new ConstructorId(clArg_.getNames().join(""), cl_, false);
            UndefinedConstructorError und_ = new UndefinedConstructorError();
            und_.setId(constId_);
            und_.setClassName(clArg_.getNames().join(""));
            und_.setRc(_conf.getCurrentLocation());
            und_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
            checkPositionBasis(_conf);
            return;
        }
        constId = ctorRes_.getRealId();
        GeneConstructor ctor_ = ctorRes_.getCtor();
        if (ctor_ != null && !Classes.canAccess(clCurName_, ctor_, _conf)) {
            BadAccessConstructor badAccess_ = new BadAccessConstructor();
            badAccess_.setId(ctor_.getId());
            badAccess_.setFileName(_conf.getCurrentFileName());
            badAccess_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badAccess_);
        }
        checkPositionBasis(_conf);
        postAnalysis(_conf, ctorRes_, chidren_, firstArgs_);
    }

    abstract ClassArgumentMatching getFrom(Analyzable _conf);
    final void postAnalysis(Analyzable _conf, ConstrustorIdVarArg _res, CustList<OperationNode> _children, CustList<ClassArgumentMatching> _args) {
        if (_res.isVarArgToCall()) {
            naturalVararg = constId.getParametersTypes().size() - 1;
            lastType = constId.getParametersTypes().last();
        }
        if (!_children.isEmpty() && _children.first() instanceof VarargOperation) {
            int i_ = CustList.FIRST_INDEX;
            for (OperationNode o: _children) {
                if (o instanceof VarargOperation) {
                    i_++;
                    continue;
                }
                if (o instanceof FirstOptOperation) {
                    break;
                }
                String param_ = constId.getParametersTypes().get(i_-1);
                if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                    o.getResultClass().setUnwrapObject(param_);
                }
                i_++;
            }
        } else if (naturalVararg > -1) {
            int lenCh_ = _args.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                ClassArgumentMatching a_ = _args.get(i);
                if (i >= naturalVararg) {
                    if (PrimitiveTypeUtil.isPrimitive(lastType, _conf)) {
                        a_.setUnwrapObject(lastType);
                    }
                } else {
                    String param_ = constId.getParametersTypes().get(i);
                    if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                        a_.setUnwrapObject(param_);
                    }
                }
            }
        } else {
            int lenCh_ = _args.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                ClassArgumentMatching a_ = _args.get(i);
                String param_ = constId.getParametersTypes().get(i);
                if (i + 1 == lenCh_ && constId.isVararg()) {
                    param_ = PrimitiveTypeUtil.getPrettyArrayType(param_);
                }
                if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                    a_.setUnwrapObject(param_);
                }
            }
        }
        LgNames stds_ = _conf.getStandards();
        setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        CustomFoundConstructor ctor_ = _conf.getContextEl().getCallCtor();
        Argument res_;
        if (ctor_ != null) {
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(), ctor_.getCurrentObject(), ctor_.getId(), ctor_.getArguments(), _conf.getContextEl());
        } else {
            res_ = argres_;
        }
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument res_ = getArgument(arguments_, _conf);
        if (_conf.callsOrException()) {
            return res_;
        }
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }

    final void checkPositionBasis(Analyzable _conf) {
        Block curBlock_ = _conf.getCurrentBlock();
        if (getParent() != null) {
            //error
            BadConstructorCall call_ = new BadConstructorCall();
            call_.setFileName(curBlock_.getFile().getFileName());
            call_.setRc(curBlock_.getRowCol(0, 0));
            call_.setLocalOffset(curBlock_.getRowCol(getFullIndexInEl(), 0));
            _conf.getClasses().addError(call_);
        } else {
            if (!(curBlock_.getParent() instanceof ConstructorBlock)) {
                //error
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(curBlock_.getFile().getFileName());
                call_.setRc(curBlock_.getRowCol(0, 0));
                call_.setLocalOffset(curBlock_.getRowCol(getFullIndexInEl(), 0));
                _conf.getClasses().addError(call_);
            } else if (!(curBlock_ instanceof Line)) {
                //error
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(curBlock_.getFile().getFileName());
                call_.setRc(curBlock_.getRowCol(0, 0));
                call_.setLocalOffset(curBlock_.getRowCol(getFullIndexInEl(), 0));
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
            call_.setRc(curBlock_.getRowCol(0, 0));
            call_.setLocalOffset(curBlock_.getRowCol(getFullIndexInEl(), 0));
            _conf.getClasses().addError(call_);
        }
    }
    protected final void processArgs(ExecutableCode _ex, CustList<Argument> _args, StringList _params) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int i_ = CustList.FIRST_INDEX;
        LgNames stds_ = _ex.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        for (Argument a: _args) {
            if (i_ < _params.size()) {
                Struct str_ = a.getStruct();
                if (!str_.isNull()) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(a.getObjectClassName(_ex.getContextEl()));
                    mapping_.setParam(_params.get(i_));
                    if (!Templates.isCorrect(mapping_, _ex)) {
                        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _ex);
                        _ex.setException(new StdStruct(new CustomError(_ex.joinPages()),cast_));
                        return;
                    }
                }
                if (str_ instanceof NumberStruct || str_ instanceof CharStruct) {
                    ClassArgumentMatching clArg_ = new ClassArgumentMatching(_params.get(i_));
                    a.setStruct(PrimitiveTypeUtil.convertObject(clArg_, str_, _ex));
                }
            }
            i_++;
        }
    }
    abstract Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf);

    @Override
    public ConstructorId getConstId() {
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
