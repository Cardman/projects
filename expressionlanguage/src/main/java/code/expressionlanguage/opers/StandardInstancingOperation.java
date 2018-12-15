package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.calls.util.CustomFoundConstructor;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.errors.custom.BadAccessConstructor;
import code.expressionlanguage.errors.custom.IllegalCallCtorByType;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class StandardInstancingOperation extends
        AbstractInstancingOperation {

    private boolean possibleInitClass;

    private String methodName;

    private ConstructorId constId;

    private String className;

    private String fieldName = EMPTY_STRING;
    private int blockIndex = -1;

    private int naturalVararg = -1;

    private String lastType = EMPTY_STRING;
    public StandardInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    public void setFieldName(String _fieldName) {
        fieldName = _fieldName;
    }

    public boolean initStaticClass(Analyzable _an) {
        return isCallMethodCtor(_an);
    }

    @Override
    boolean isCallMethodCtor(Analyzable _an) {
        return true;
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        className = _conf.getStandards().getAliasObject();
        KeyWords keyWords_ = _conf.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String className_ = methodName.trim().substring(newKeyWord_.length());
        className_ = className_.trim();
        String realClassName_ = className_;
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(filter_, _conf);
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_conf.isStaticContext());
            realClassName_ = _conf.resolveCorrectType(realClassName_);
            analyzeCtor(_conf, realClassName_, firstArgs_);
            return;
        }
        if (realClassName_.startsWith("..")) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        ClassArgumentMatching arg_ = getPreviousResultClass();
        arg_.setCheckOnlyNullPe(true);
        if (arg_ == null || arg_.isUndefined() || arg_.isArray()) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        StringMap<String> ownersMap_ = new StringMap<String>();
        String idClass_ = Templates.getIdFromAllTypes(realClassName_);
        String glClass_ = _conf.getGlobalClass();
        for (String o: arg_.getNames()) {
            boolean ok_ = true;
            for (String p: Templates.getAllTypes(o).mid(1)) {
                if (p.startsWith(Templates.SUB_TYPE)) {
                    ok_ = false;
                }
                if (p.startsWith(Templates.SUP_TYPE)) {
                    ok_ = false;
                }
            }
            if (!ok_) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(static_);
                LgNames stds_ = _conf.getStandards();
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
        }
        for (String o: arg_.getNames()) {
            String idRoot_ = Templates.getIdFromAllTypes(o);
            StringList owners_ = TypeUtil.getOwners(false,true, glClass_, idRoot_, idClass_, false, _conf);
            owners_.removeDuplicates();
            if (owners_.size() == 1) {
                ownersMap_.put(o, owners_.first());
            }
        }
        if (ownersMap_.size() != 1) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String sub_ = ownersMap_.getKeys().first();
        String sup_ = ownersMap_.values().first();
        String new_ = Templates.getFullTypeByBases(sub_, sup_, _conf);
        if (new_ == null) {
            Block bl_ = _conf.getCurrentBlock();
            int rc_ = _conf.getCurrentLocationIndex();
            AccessingImportingBlock r_ = bl_.getImporting();
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(realClassName_);
            un_.setFileName(r_.getFile().getFileName());
            un_.setIndexFile(rc_);
            _conf.getClasses().addError(un_);
            realClassName_ = _conf.getStandards().getAliasObject();
        } else {
            StringList partsArgs_ = new StringList();
            for (String a: Templates.getAllTypes(realClassName_).mid(1)) {
                partsArgs_.add(_conf.resolveCorrectType(a));
            }
            if (partsArgs_.isEmpty()) {
                realClassName_ = StringList.concat(new_,"..",idClass_);
            } else {
                realClassName_ = StringList.concat(new_,"..",idClass_,"<",partsArgs_.join(","),">");
            }
            StringMap<StringList> vars_ = _conf.getCurrentConstraints();
            if (!Templates.isCorrectTemplateAll(realClassName_, vars_, _conf, true)) {
                Block bl_ = _conf.getCurrentBlock();
                int rc_ = _conf.getCurrentLocationIndex();
                AccessingImportingBlock r_ = bl_.getImporting();
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(realClassName_);
                un_.setFileName(r_.getFile().getFileName());
                un_.setIndexFile(rc_);
                _conf.getClasses().addError(un_);
                realClassName_ = _conf.getStandards().getAliasObject();
            }
        }
        analyzeCtor(_conf, realClassName_, firstArgs_);
    }
    void analyzeCtor(Analyzable _conf, String _realClassName, CustList<ClassArgumentMatching> _firstArgs) {
        String realClassName_ = _realClassName;
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        LgNames stds_ = _conf.getStandards();
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodId idMethod_ = lookOnlyForId();
        ConstrustorIdVarArg ctorRes_ = null;
        if (PrimitiveTypeUtil.isPrimitive(realClassName_, _conf)) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        String base_ = Templates.getIdFromAllTypes(realClassName_);
        GeneType g_ = _conf.getClassBody(base_);
        if (g_ == null) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        OperationNode possibleInit_ = getFirstChild();
        if (possibleInit_ instanceof StaticInitOperation) {
            StaticInitOperation st_ = (StaticInitOperation) possibleInit_;
            if (!isIntermediateDottedOperation()) {
                boolean staticType_ = g_.isStaticType();
                st_.setInit(_conf,base_,staticType_);
            }
        }
        for (String p:Templates.getAllTypes(realClassName_).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(realClassName_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(realClassName_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
            }
        }
        if (!g_.isStaticType() && !isIntermediateDottedOperation() && isStaticAccess()) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(static_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        if (g_.isAbstractType() && !(g_ instanceof EnumBlock)) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        if (g_ instanceof EnumBlock) {
            if (fieldName.isEmpty()) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(realClassName_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
                setResultClass(new ClassArgumentMatching(realClassName_));
                return;
            }
            blockIndex = _conf.getCurrentChildTypeIndex();
        }
        ConstructorId feed_ = null;
        if (idMethod_ != null) {
            String idClass_ = idMethod_.getClassName();
            boolean vararg_ = idMethod_.getConstraints().isVararg();
            StringList params_ = idMethod_.getConstraints().getParametersTypes();
            feed_ = new ConstructorId(idClass_, params_, vararg_);
        }
        ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(realClassName_), feed_, ClassArgumentMatching.toArgArray(_firstArgs));
        constId = ctorRes_.getRealId();
        className = ctorRes_.getConstId().getName();
        if (ctorRes_.isVarArgToCall()) {
            naturalVararg = constId.getParametersTypes().size() - 1;
            lastType = constId.getParametersTypes().last();
        }
        unwrapArgsFct(filter_, constId, naturalVararg, lastType, _firstArgs, _conf);
        String glClass_ = _conf.getGlobalClass();
        CustList<GeneConstructor> ctors_ = TypeUtil.getConstructorBodiesById(realClassName_, constId, _conf);
        String curClassBase_ = null;
        if (glClass_ != null) {
            curClassBase_ = Templates.getIdFromAllTypes(glClass_);
        }
        if (!ctors_.isEmpty() && !Classes.canAccess(curClassBase_, ctors_.first(), _conf)) {
            GeneConstructor ctr_ = ctors_.first();
            BadAccessConstructor access_ = new BadAccessConstructor();
            access_.setId(ctr_.getId());
            access_.setFileName(_conf.getCurrentFileName());
            access_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(access_);
        }
        possibleInitClass = !_conf.getOptions().isInitializeStaticClassFirst() && g_.isStaticType();
        setResultClass(new ClassArgumentMatching(realClassName_));
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (!_conf.isGearConst()) {
            return;
        }
        String cl_ = className;
        if (cl_ == null) {
            return;
        }
        if (_conf.getClasses().isCustomType(cl_)) {
            return;
        }
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            arguments_.add(o.getArgument());
            filter_.add(o);
        }
        CustList<Argument> firstArgs_ = quickListArguments(filter_, naturalVararg_, lastType_, arguments_, _conf);
        if (firstArgs_ == null) {
            return;
        }
        ResultErrorStd res_ = LgNames.newInstanceStd(_conf, constId, Argument.toArgArray(firstArgs_));
        if (res_.getResult() == null) {
            return;
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(res_.getResult());
        setSimpleArgumentAna(arg_, _conf);
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            arguments_.add(o.getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument argres_ = getArgument(previous_, arguments_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        Argument res_;
        CustomFoundConstructor ctor_ = _conf.getContextEl().getCallCtor();
        if (ctor_ != null) {
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(), ctor_.getCurrentObject(), ctor_.getId(), ctor_.getArguments(), _conf.getContextEl());
        } else {
            res_ = argres_;
        }
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }

    Argument getArgument(Argument _previous,CustList<Argument> _arguments,
            ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        PageEl page_ = _conf.getOperationPageEl();
        className_ = page_.formatVarType(className, _conf);
        if (possibleInitClass) {
            String base_ = Templates.getIdFromAllTypes(className_);
            if (InvokingOperation.hasToExit(_conf, base_)) {
                return Argument.createVoid();
            }
        }
        String lastType_ = Templates.quickFormat(className_, lastType, _conf);
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, _arguments, _conf);
        return instancePrepare(_conf, className_, constId, _previous, firstArgs_, fieldName, blockIndex, true);
    }

}
