package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadAccessClass;
import code.expressionlanguage.errors.custom.IllegalCallCtorByType;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.exec.PossibleIntermediateDottedOperable;
import code.expressionlanguage.opers.exec.StaticInitOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
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

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        className = _conf.getStandards().getAliasObject();
        KeyWords keyWords_ = _conf.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String className_ = methodName.trim().substring(newKeyWord_.length());
        String realClassName_ = className_;
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(filter_, _conf);
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_conf.isStaticContext());
            realClassName_ = _conf.resolveCorrectType(realClassName_);
            analyzeCtor(_conf, realClassName_, firstArgs_);
            return;
        }
        realClassName_ = realClassName_.trim();
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
        if (arg_.isArray()) {
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
            AccessingImportingBlock r_ = _conf.getAnalyzing().getImporting();
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
                AccessingImportingBlock r_ = _conf.getAnalyzing().getImporting();
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
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        LgNames stds_ = _conf.getStandards();
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodId idMethod_ = lookOnlyForId();
        ConstrustorIdVarArg ctorRes_ = null;
        Classes classes_ = _conf.getClasses();
        if (PrimitiveTypeUtil.isPrimitive(_realClassName, _conf)) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(_realClassName);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            classes_.addError(call_);
            setResultClass(new ClassArgumentMatching(_realClassName));
            return;
        }
        String base_ = Templates.getIdFromAllTypes(_realClassName);
        GeneType g_ = _conf.getClassBody(base_);
        if (g_ == null) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(_realClassName);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            classes_.addError(call_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String glClass_ = _conf.getGlobalClass();
        if (classes_.isCustomType(base_)) {
            String curClassBase_ = Templates.getIdFromAllTypes(glClass_);
            if (!Classes.canAccessClass(curClassBase_, base_, _conf)) {
                BadAccessClass badAccess_ = new BadAccessClass();
                badAccess_.setId(base_);
                badAccess_.setIndexFile(_conf.getCurrentLocationIndex());
                badAccess_.setFileName(_conf.getCurrentFileName());
                classes_.addError(badAccess_);
            }
        }
        OperationNode possibleInit_ = getFirstChild();
        if (possibleInit_ instanceof StaticInitOperation) {
            StaticInitOperation st_ = (StaticInitOperation) possibleInit_;
            if (!isIntermediateDottedOperation()) {
                boolean staticType_ = g_.isStaticType();
                st_.setInit(_conf,base_,staticType_);
            }
        }
        for (String p:Templates.getAllTypes(_realClassName).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(_realClassName);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(_realClassName);
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
            setResultClass(new ClassArgumentMatching(_realClassName));
            return;
        }
        if (g_.isAbstractType() && !(g_ instanceof EnumBlock)) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(_realClassName);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(_realClassName));
            return;
        }
        if (g_ instanceof EnumBlock) {
            if (fieldName.isEmpty()) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(_realClassName);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
                setResultClass(new ClassArgumentMatching(_realClassName));
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
        ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(_realClassName), feed_, ClassArgumentMatching.toArgArray(_firstArgs));
        if (ctorRes_.getRealId() == null) {
            setResultClass(new ClassArgumentMatching(_realClassName));
            return;
        }
        constId = ctorRes_.getRealId();
        className = ctorRes_.getConstId().getName();
        if (ctorRes_.isVarArgToCall()) {
            naturalVararg = constId.getParametersTypes().size() - 1;
            lastType = constId.getParametersTypes().last();
        }
        unwrapArgsFct(filter_, constId, naturalVararg, lastType, _firstArgs, _conf);
        possibleInitClass = !_conf.getOptions().isInitializeStaticClassFirst() && g_.isStaticType();
        setResultClass(new ClassArgumentMatching(_realClassName));
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        tryGetArg(this,_conf,naturalVararg,className,constId,lastType);
    }

    public static void tryGetArg(PossibleIntermediateDottedOperable _current, Analyzable _conf,
                                 int _naturalVararg, String _className,ConstructorId _constId,String _lastType) {
        CustList<Operable> chidren_ = ((ParentOperable)_current).getChildrenOperable();
        CustList<Argument> arguments_ = new CustList<Argument>();
        if (!_conf.isGearConst()) {
            return;
        }
        if (_conf.getClasses().isCustomType(_className)) {
            return;
        }
        CustList<Operable> filter_ = new CustList<Operable>();
        for (Operable o: chidren_) {
            if (o instanceof StaticInitOperable) {
                continue;
            }
            arguments_.add(o.getArgument());
            filter_.add(o);
        }
        CustList<Argument> firstArgs_ = quickListArguments(filter_, _naturalVararg, _lastType, arguments_, _conf);
        if (firstArgs_ == null) {
            return;
        }
        ResultErrorStd res_ = LgNames.newInstanceStd(_conf, _constId, Argument.toArgArray(firstArgs_));
        Struct out_ = res_.getResult();
        if (out_ == null) {
            return;
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(out_);
        _current.setSimpleArgumentAna(arg_, _conf);
    }

    public boolean isPossibleInitClass() {
        return possibleInitClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public String getClassName() {
        return className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

}
