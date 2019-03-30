package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.IllegalCallCtorByType;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.*;
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
        AbstractInstancingOperation implements PreAnalyzableOperation {

    private boolean possibleInitClass;

    private String methodName;

    private ConstructorId constId;

    private String className;

    private String fieldName = EMPTY_STRING;
    private int blockIndex = -1;

    private int naturalVararg = -1;

    private String lastType = EMPTY_STRING;
    private String typeInfer = EMPTY_STRING;

    public StandardInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    public void setFieldName(String _fieldName) {
        fieldName = _fieldName;
    }

    @Override
    public void preAnalyze(Analyzable _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String className_ = methodName.trim().substring(newKeyWord_.length());
        String inferForm_ = Templates.getInferForm(className_);
        if (inferForm_ == null) {
            return;
        }
        if (!isIntermediateDottedOperation()) {
            String type_ = _an.resolveAccessibleIdTypeWithoutError(inferForm_);
            if (type_.isEmpty()) {
                return;
            }
            int nbParentsInfer_ = 0;
            OperationNode current_ = this;
            MethodOperation m_ = getParent();
            while (m_ != null) {
                if (!(m_ instanceof AbstractArrayElementOperation)) {
                    if (m_ instanceof IdOperation) {
                        current_ = current_.getParent();
                        m_ = m_.getParent();
                        continue;
                    }
                    if (m_ instanceof AbstractTernaryOperation) {
                        if (m_.getFirstChild() == current_) {
                            break;
                        }
                        current_ = current_.getParent();
                        m_ = m_.getParent();
                        continue;
                    }
                    break;
                }
                nbParentsInfer_++;
                current_ = current_.getParent();
                m_ = m_.getParent();
            }
            String typeAff_ = EMPTY_STRING;
            Block cur_ = _an.getCurrentBlock();
            if (m_ == null && cur_ instanceof ReturnMehod) {
                FunctionBlock f_ = _an.getAnalyzing().getCurrentFct();
                if (f_ instanceof NamedFunctionBlock) {
                    NamedFunctionBlock n_ = (NamedFunctionBlock) f_;
                    String ret_ = n_.getImportedReturnType();
                    String void_ = _an.getStandards().getAliasVoid();
                    if (!StringList.quickEq(ret_, void_)) {
                        typeAff_ = ret_;
                    }
                }
            } else if (m_ == null && cur_ instanceof AbstractForEachLoop) {
                AbstractForEachLoop i_ = (AbstractForEachLoop) _an.getCurrentBlock();
                typeAff_ = i_.getImportedClassName();
                if (!typeAff_.isEmpty()) {
                    String iter_ = _an.getStandards().getAliasIterable();
                    typeAff_ = StringList.concat(iter_,Templates.TEMPLATE_BEGIN,typeAff_,Templates.TEMPLATE_END);
                }
            } else if (m_ == null && cur_ instanceof ForEachTable) {
                ForEachTable i_ = (ForEachTable) _an.getCurrentBlock();
                String typeAffOne_ = i_.getImportedClassNameFirst();
                String typeAffTwo_ = i_.getImportedClassNameSecond();
                if (!typeAffOne_.isEmpty() && !typeAffTwo_.isEmpty()) {
                    String iter_ = _an.getStandards().getAliasIterableTable();
                    typeAff_ = StringList.concat(iter_,Templates.TEMPLATE_BEGIN,typeAffOne_,Templates.TEMPLATE_SEP,typeAffTwo_,Templates.TEMPLATE_END);
                }
            } else if (m_ instanceof AffectationOperation) {
                AffectationOperation a_ = (AffectationOperation) m_;
                SettableElResult s_ = AffectationOperation.tryGetSettable(a_);
                if (s_ != null) {
                    ClassArgumentMatching c_ = s_.getResultClass();
                    typeAff_ = c_.getSingleNameOrEmpty();
                }
            }
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (typeAff_.isEmpty() || StringList.quickEq(typeAff_, keyWordVar_)) {
                return;
            }
            String cp_ = PrimitiveTypeUtil.getQuickComponentType(typeAff_, nbParentsInfer_);
            if (cp_ == null) {
                return;
            }
            String infer_ = Templates.tryInfer(type_, cp_, _an);
            if (infer_ == null) {
                return;
            }
            typeInfer = infer_;
        }
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
            if (!typeInfer.isEmpty()) {
                realClassName_ = typeInfer;
            } else if (fieldName.isEmpty()) {
                realClassName_ = _conf.resolveCorrectType(realClassName_);
            } else {
                realClassName_ = realClassName_.trim();
            }
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
            StringList owners_ = TypeUtil.getGenericOwners(false,true, glClass_, o, idClass_, _conf);
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
        String sup_ = ownersMap_.values().first();
        StringList partsArgs_ = new StringList();
        for (String a: Templates.getAllTypes(realClassName_).mid(1)) {
            partsArgs_.add(_conf.resolveCorrectType(a));
        }
        if (partsArgs_.isEmpty()) {
            realClassName_ = StringList.concat(sup_,"..",idClass_);
        } else {
            realClassName_ = StringList.concat(sup_,"..",idClass_,"<",partsArgs_.join(","),">");
        }
        StringMap<StringList> vars_ = _conf.getCurrentConstraints();
        if (!Templates.isCorrectTemplateAll(realClassName_, vars_, _conf, true)) {
            int rc_ = _conf.getCurrentLocationIndex();
            AccessingImportingBlock r_ = _conf.getAnalyzing().getImporting();
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(realClassName_);
            un_.setFileName(r_.getFile().getFileName());
            un_.setIndexFile(rc_);
            _conf.getClasses().addError(un_);
            realClassName_ = _conf.getStandards().getAliasObject();
        }
        analyzeCtor(_conf, realClassName_, firstArgs_);
    }
    void analyzeCtor(Analyzable _conf, String _realClassName, CustList<ClassArgumentMatching> _firstArgs) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        LgNames stds_ = _conf.getStandards();
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodId idMethod_ = lookOnlyForId();
        Classes classes_ = _conf.getClasses();
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
        if (!g_.withoutInstance() && !isIntermediateDottedOperation() && isStaticAccess()) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(static_);
            setResultClass(new ClassArgumentMatching(_realClassName));
            return;
        }
        if (g_.isAbstractType() && !(g_ instanceof EnumBlock || g_ instanceof InnerElementBlock)) {
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
        ConstrustorIdVarArg ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(_realClassName), feed_, ClassArgumentMatching.toArgArray(_firstArgs));
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
        tryGetArg(this,_conf,naturalVararg, constId,lastType);
    }

    public static void tryGetArg(PossibleIntermediateDottedOperable _current, Analyzable _conf,
                                 int _naturalVararg, ConstructorId _constId, String _lastType) {
        CustList<Operable> chidren_ = ((ParentOperable)_current).getChildrenOperable();
        CustList<Argument> arguments_ = new CustList<Argument>();
        if (!_conf.isGearConst()) {
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
