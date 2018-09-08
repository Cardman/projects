package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.IllegalCallCtorByType;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UndefinedMethodError;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.methods.util.VarargError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.LambdaConstructorStruct;
import code.expressionlanguage.opers.util.LambdaMethodStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class LambdaOperation extends LeafOperation implements PossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private Argument previousArgument;
    private boolean staticAccess;

    private String className;
    private int offset;

    private ClassMethodId method;
    private String foundClass;
    private int ancestor;
    private boolean shiftArgument;
    private boolean polymorph;
    private boolean abstractMethod;
    private ConstructorId realId;

    public LambdaOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        offset = _op.getValues().firstKey();
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl() + offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        String extr_ = className.substring(className.indexOf("(")+1, className.lastIndexOf(")"));
        StringList args_ = Templates.getAllSepCommaTypes(extr_);
        int len_ = args_.size();
        String fromType_ = ContextEl.removeDottedSpaces(args_.first());
        StringList str_;
        CustList<ClassArgumentMatching> methodTypes_ = new CustList<ClassArgumentMatching>();
        String name_ = args_.get(1).trim();
        if (StringList.quickEq(name_, prefixFunction(INSTANCE))) {
            if (!isIntermediateDottedOperation()) {
                if (fromType_.trim().startsWith(ARR)) {
                    String cl_ = _conf.resolveCorrectType(fromType_);
                    int i_ = 2;
                    StringList parts_ = new StringList();
                    boolean err_ = false;
                    for (int i = i_; i < len_; i++) {
                        String arg_ = ContextEl.removeDottedSpaces(args_.get(i));
                        parts_.add(arg_);
                        ClassArgumentMatching clArg_ = new ClassArgumentMatching(arg_);
                        if (!clArg_.isNumericInt(_conf)) {
                            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                            un_.setRc(_conf.getCurrentLocation());
                            un_.setFileName(_conf.getCurrentFileName());
                            un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
                            un_.setOperands(clArg_);
                            _conf.getClasses().addError(un_);
                            err_ = true;
                        }
                        methodTypes_.add(clArg_);
                    }
                    if (methodTypes_.isEmpty()) {
                        BadOperandsNumber badCall_ = new BadOperandsNumber();
                        badCall_.setOperandsNumber(0);
                        badCall_.setFileName(_conf.getCurrentFileName());
                        badCall_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().addError(badCall_);
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                    if (err_) {
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                    String elt_ = cl_.substring(ARR.length());
                    String out_ = PrimitiveTypeUtil.getPrettyArrayType(elt_, methodTypes_.size());
                    foundClass = out_;
                    parts_.add(out_);
                    StringBuilder fct_ = new StringBuilder(stds_.getAliasFct());
                    fct_.append(Templates.TEMPLATE_BEGIN);
                    fct_.append(parts_.join(Templates.TEMPLATE_SEP));
                    fct_.append(Templates.TEMPLATE_END);
                    setResultClass(new ClassArgumentMatching(fct_.toString()));
                    return;
                }
            }
            int i_ = 2;
            int vararg_ = -1;
            for (int i = i_; i < len_; i++) {
                String arg_ = ContextEl.removeDottedSpaces(args_.get(i));
                String type_;
                if (arg_.endsWith(VARARG_SUFFIX)) {
                    if (i + 1 != len_) {
                        //last type => error
                        VarargError varg_ = new VarargError();
                        varg_.setFileName(_conf.getCurrentFileName());
                        varg_.setRc(_conf.getCurrentLocation());
                        varg_.setMethodName(VAR_ARG);
                        _conf.getClasses().addError(varg_);
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                    vararg_ = len_- i_;
                    type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
                    type_ = PrimitiveTypeUtil.getPrettyArrayType(type_);
                } else {
                    type_ = arg_;
                }
                arg_ = _conf.resolveCorrectType(type_);
                methodTypes_.add(new ClassArgumentMatching(arg_));
            }
            if (!isIntermediateDottedOperation()) {
                String cl_ = _conf.resolveCorrectType(fromType_);
                String id_ = Templates.getIdFromAllTypes(cl_);
                GeneType g_ = _conf.getClassBody(id_);
                if (g_ == null || PrimitiveTypeUtil.isPrimitive(id_, _conf) || g_.isAbstractType()) {
                    IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                    call_.setType(cl_);
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(call_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                ConstrustorIdVarArg ctorRes_;
                ctorRes_ = getDeclaredCustConstructor(_conf, vararg_, new ClassArgumentMatching(cl_), ClassArgumentMatching.toArgArray(methodTypes_));
                realId = ctorRes_.getRealId();
                ConstructorId fid_ = ctorRes_.getConstId();
                StringList parts_ = new StringList();
                if (!g_.isStaticType()) {
                    StringList innerParts_ = Templates.getAllInnerTypes(cl_);
                    parts_.add(innerParts_.mid(0, innerParts_.size() - 1).join(".."));
                }
                StringList params_ = fid_.getParametersTypes();
                if (fid_.isVararg()) {
                    for (String p: params_.mid(0, params_.size() - 1)) {
                        String p_ = p;
                        parts_.add(p_);
                    }
                    String p_ = params_.last();
                    parts_.add(PrimitiveTypeUtil.getPrettyArrayType(p_));
                } else {
                    for (String p: params_) {
                        String p_ = p;
                        parts_.add(p_);
                    }
                }
                foundClass = cl_;
                parts_.add(cl_);
                StringBuilder fct_ = new StringBuilder(stds_.getAliasFct());
                fct_.append(Templates.TEMPLATE_BEGIN);
                fct_.append(parts_.join(Templates.TEMPLATE_SEP));
                fct_.append(Templates.TEMPLATE_END);
                setResultClass(new ClassArgumentMatching(fct_.toString()));
                return;
            }
            String cl_ = fromType_.trim();
            if (cl_.startsWith("..")) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String idClass_ = Templates.getIdFromAllTypes(cl_);
            StringMap<String> ownersMap_ = new StringMap<String>();
            for (String o: previousResultClass.getNames()) {
                StringList ids_ = new StringList(Templates.getIdFromAllTypes(o));
                StringList owners_ = new StringList();
                while (true) {
                    StringList new_ = new StringList();
                    for (String s: ids_) {
                        GeneType g_ = _conf.getClassBody(s);
                        if (!(g_ instanceof RootBlock)) {
                            continue;
                        }
                        RootBlock sub_ = (RootBlock)g_;
                        boolean add_ = false;
                        for (Block b: Classes.getDirectChildren(sub_)) {
                            if (!(b instanceof RootBlock)) {
                                continue;
                            }
                            RootBlock inner_ = (RootBlock) b;
                            if (StringList.quickEq(inner_.getName(), idClass_)) {
                                owners_.add(s);
                                add_ = true;
                            }
                        }
                        if (add_) {
                            continue;
                        }
                        for (String t: sub_.getImportedDirectSuperTypes()) {
                            String id_ = Templates.getIdFromAllTypes(t);
                            new_.add(id_);
                        }
                    }
                    if (new_.isEmpty()) {
                        break;
                    }
                    ids_ = new_;
                }
                owners_.removeDuplicates();
                if (owners_.size() == 1) {
                    ownersMap_.put(o, owners_.first());
                }
            }
            if (ownersMap_.size() != 1) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String sub_ = ownersMap_.getKeys().first();
            String sup_ = ownersMap_.values().first();
            String new_ = Templates.getFullTypeByBases(sub_, sup_, _conf);
            cl_ = StringList.concat(new_,"..",cl_);
            foundClass = cl_;
            shiftArgument = true;
            String id_ = Templates.getIdFromAllTypes(cl_);
            GeneType g_ = _conf.getClassBody(id_);
            if (g_ == null || g_.isAbstractType()) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(cl_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(call_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            ConstrustorIdVarArg ctorRes_;
            ctorRes_ = getDeclaredCustConstructor(_conf, vararg_, new ClassArgumentMatching(cl_), ClassArgumentMatching.toArgArray(methodTypes_));
            realId = ctorRes_.getRealId();
            ConstructorId fid_ = ctorRes_.getConstId();
            StringList parts_ = new StringList();
            StringList params_ = fid_.getParametersTypes();
            if (fid_.isVararg()) {
                for (String p: params_.mid(0, params_.size() - 1)) {
                    String p_ = p;
                    parts_.add(p_);
                }
                String p_ = params_.last();
                parts_.add(PrimitiveTypeUtil.getPrettyArrayType(p_));
            } else {
                for (String p: params_) {
                    String p_ = p;
                    parts_.add(p_);
                }
            }
            parts_.add(cl_);
            StringBuilder fct_ = new StringBuilder(stds_.getAliasFct());
            fct_.append(Templates.TEMPLATE_BEGIN);
            fct_.append(parts_.join(Templates.TEMPLATE_SEP));
            fct_.append(Templates.TEMPLATE_END);
            setResultClass(new ClassArgumentMatching(fct_.toString()));
            return;
        }
        if (!isIntermediateDottedOperation()) {
            str_ = resolveCorrectTypes(_conf, true, fromType_);
            int i_ = 2;
            boolean staticChoiceMethod_ = false;
            boolean accessFromSuper_ = false;
            boolean accessSuper_ = true;
            if (StringList.quickEq(name_, prefixFunction(SUPER_ACCESS))) {
                name_ = args_.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                accessFromSuper_ = true;
            } else if (StringList.quickEq(name_, prefixFunction(THAT))) {
                name_ = args_.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
            } else if (StringList.quickEq(name_, prefixFunction(CLASS_CHOICE))) {
                name_ = args_.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                accessSuper_ = false;
            } else {
                polymorph = true;
            }
            int vararg_ = -1;
            for (int i = i_; i < len_; i++) {
                String arg_ = ContextEl.removeDottedSpaces(args_.get(i));
                String type_;
                if (arg_.endsWith(VARARG_SUFFIX)) {
                    if (i + 1 != len_) {
                        //last type => error
                        VarargError varg_ = new VarargError();
                        varg_.setFileName(_conf.getCurrentFileName());
                        varg_.setRc(_conf.getCurrentLocation());
                        varg_.setMethodName(VAR_ARG);
                        _conf.getClasses().addError(varg_);
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                    vararg_ = len_- i_;
                    type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
                    type_ = PrimitiveTypeUtil.getPrettyArrayType(type_);
                } else {
                    type_ = arg_;
                }
                arg_ = _conf.resolveCorrectType(type_);
                methodTypes_.add(new ClassArgumentMatching(arg_));
            }
            boolean cloneArray_ = false;
            for (String b: str_) {
                if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                    cloneArray_ = true;
                    break;
                }
            }
            if (cloneArray_) {
                if (!StringList.quickEq(name_, stds_.getAliasClone())) {
                    StringList classesNames_ = new StringList();
                    UndefinedMethodError undefined_ = new UndefinedMethodError();
                    MethodModifier mod_ = MethodModifier.FINAL;
                    undefined_.setClassName(str_);
                    undefined_.setId(new MethodId(mod_, name_, classesNames_));
                    undefined_.setFileName(_conf.getCurrentFileName());
                    undefined_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(undefined_);
                    return;
                }
                StringList a_ = new StringList();
                for (String b: str_) {
                    if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                        a_.add(b);
                        foundClass = b;
                    }
                }
                String foundClass_ = PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject());
                MethodId id_ = new MethodId(false, name_, new StringList());
                method = new ClassMethodId(foundClass_, id_);
                shiftArgument = true;
                StringBuilder fct_ = new StringBuilder(stds_.getAliasFct());
                fct_.append(Templates.TEMPLATE_BEGIN);
                fct_.append(foundClass);
                fct_.append(Templates.TEMPLATE_SEP);
                fct_.append(foundClass);
                fct_.append(Templates.TEMPLATE_END);
                setResultClass(new ClassArgumentMatching(fct_.toString()));
                if (isIntermediateDottedOperation()) {
                    Argument arg_ = getPreviousArgument();
                    if (Argument.isNullValue(arg_)) {
                        StaticAccessError static_ = new StaticAccessError();
                        static_.setFileName(_conf.getCurrentFileName());
                        static_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().addError(static_);
                    }
                }
                return;
            }
            ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, false, str_, name_, accessSuper_, accessFromSuper_, false, ClassArgumentMatching.toArgArray(methodTypes_));
            if (!id_.isFoundMethod()) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String foundClass_ = id_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            foundClass = id_.getId().getClassName();
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            ancestor = id_.getAncestor();
            abstractMethod = id_.isAbstractMethod();
            shiftArgument = !id_.isStaticMethod();
            String fct_ = formatReturn(_conf, id_, shiftArgument);
            setResultClass(new ClassArgumentMatching(fct_));
            if (staticChoiceMethod_) {
                if (id_.isAbstractMethod()) {
                    AbstractMethod abs_ = new AbstractMethod();
                    abs_.setClassName(id_.getRealClass());
                    abs_.setSgn(id_.getRealId().getSignature());
                    abs_.setRc(_conf.getCurrentLocation());
                    abs_.setFileName(_conf.getCurrentFileName());
                    _conf.getClasses().addError(abs_);
                    return;
                }
            }
            return;
        }
        OperationNode o_ = m_.getFirstChild();
        while (o_.getNextSibling() != this) {
            o_ = o_.getNextSibling();
        }
        if (o_ instanceof StaticAccessOperation) {
            str_ = resolveCorrectTypes(_conf, false, fromType_);
            int vararg_ = -1;
            for (int i = 2; i < len_; i++) {
                String arg_ = ContextEl.removeDottedSpaces(args_.get(i));
                String type_;
                if (arg_.endsWith(VARARG_SUFFIX)) {
                    if (i + 1 != len_) {
                        //last type => error
                        VarargError varg_ = new VarargError();
                        varg_.setFileName(_conf.getCurrentFileName());
                        varg_.setRc(_conf.getCurrentLocation());
                        varg_.setMethodName(VAR_ARG);
                        _conf.getClasses().addError(varg_);
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                    vararg_ = len_- 2;
                    type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
                    type_ = PrimitiveTypeUtil.getPrettyArrayType(type_);
                } else {
                    type_ = arg_;
                }
                arg_ = _conf.resolveCorrectType(type_);
                methodTypes_.add(new ClassArgumentMatching(arg_));
            }
            ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, true, str_, name_, true, false, false, ClassArgumentMatching.toArgArray(methodTypes_));
            if (!id_.isFoundMethod()) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String foundClass_ = id_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            foundClass = id_.getId().getClassName();
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            ancestor = id_.getAncestor();
            String fct_ = formatReturn(_conf, id_, false);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        boolean stCtx_ = isStaticAccess();
        str_ = resolveCorrectTypes(_conf, !stCtx_, fromType_);
        int vararg_ = -1;
        int i_ = 2;
        boolean staticChoiceMethod_ = false;
        boolean accessFromSuper_ = false;
        boolean accessSuper_ = true;
        if (StringList.quickEq(name_, prefixFunction(SUPER_ACCESS))) {
            name_ = args_.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            accessFromSuper_ = true;
        } else if (StringList.quickEq(name_, prefixFunction(THAT))) {
            name_ = args_.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
        } else if (StringList.quickEq(name_, prefixFunction(CLASS_CHOICE))) {
            name_ = args_.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            accessSuper_ = false;
        } else {
            polymorph = true;
        }
        for (int i = i_; i < len_; i++) {
            String arg_ = ContextEl.removeDottedSpaces(args_.get(i));
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    VarargError varg_ = new VarargError();
                    varg_.setFileName(_conf.getCurrentFileName());
                    varg_.setRc(_conf.getCurrentLocation());
                    varg_.setMethodName(VAR_ARG);
                    _conf.getClasses().addError(varg_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                vararg_ = len_- i_;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
                type_ = PrimitiveTypeUtil.getPrettyArrayType(type_);
            } else {
                type_ = arg_;
            }
            arg_ = _conf.resolveCorrectType(type_);
            methodTypes_.add(new ClassArgumentMatching(arg_));
        }
        StringList l_ = previousResultClass.getNames();
        StringList bounds_ = new StringList();
        for (String c: l_) {
            if (c.isEmpty()) {
                continue;
            }
            if (InvokingOperation.hasVoidPrevious(c, _conf)) {
                return;
            }
            bounds_.addAllElts(InvokingOperation.getBounds(c, _conf));
        }
        boolean cloneArray_ = false;
        for (String b: bounds_) {
            if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                cloneArray_ = true;
                break;
            }
        }
        if (cloneArray_) {
            if (!StringList.quickEq(name_, stds_.getAliasClone())) {
                StringList classesNames_ = new StringList();
                UndefinedMethodError undefined_ = new UndefinedMethodError();
                MethodModifier mod_ = MethodModifier.FINAL;
                undefined_.setClassName(bounds_);
                undefined_.setId(new MethodId(mod_, name_, classesNames_));
                undefined_.setFileName(_conf.getCurrentFileName());
                undefined_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(undefined_);
                return;
            }
            StringList a_ = new StringList();
            for (String b: bounds_) {
                if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                    a_.add(b);
                    foundClass = b;
                }
            }
            String foundClass_ = PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject());
            MethodId id_ = new MethodId(false, name_, new StringList());
            method = new ClassMethodId(foundClass_, id_);
            StringBuilder fct_ = new StringBuilder(stds_.getAliasFct());
            fct_.append(Templates.TEMPLATE_BEGIN);
            fct_.append(foundClass);
            fct_.append(Templates.TEMPLATE_END);
            setResultClass(new ClassArgumentMatching(fct_.toString()));
            if (isIntermediateDottedOperation()) {
                Argument arg_ = getPreviousArgument();
                if (Argument.isNullValue(arg_)) {
                    StaticAccessError static_ = new StaticAccessError();
                    static_.setFileName(_conf.getCurrentFileName());
                    static_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(static_);
                }
            }
            return;
        }
        Mapping map_ = new Mapping();
        map_.setArg(new ClassArgumentMatching(bounds_));
        map_.setParam(new ClassArgumentMatching(str_));
        StringMap<StringList> maps_ = new StringMap<StringList>();
        String glClass_ = _conf.getGlobalClass();
        if (glClass_ != null) {
            for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                maps_.put(t.getName(), t.getConstraints());
            }
        }
        map_.setMapping(maps_);
        if (!Templates.isCorrect(map_, _conf)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(map_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(cast_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, stCtx_, str_, name_, accessSuper_, accessFromSuper_, false, ClassArgumentMatching.toArgArray(methodTypes_));
        if (!id_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String foundClass_ = id_.getRealClass();
        foundClass_ = Templates.getIdFromAllTypes(foundClass_);
        foundClass = id_.getId().getClassName();
        abstractMethod = id_.isAbstractMethod();
        MethodId idCt_ = id_.getRealId();
        method = new ClassMethodId(foundClass_, idCt_);
        ancestor = id_.getAncestor();
        String fct_ = formatReturn(_conf, id_, false);
        setResultClass(new ClassArgumentMatching(fct_));
        if (staticChoiceMethod_) {
            if (id_.isAbstractMethod()) {
                AbstractMethod abs_ = new AbstractMethod();
                abs_.setClassName(id_.getRealClass());
                abs_.setSgn(id_.getRealId().getSignature());
                abs_.setRc(_conf.getCurrentLocation());
                abs_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(abs_);
                return;
            }
        }
    }

    private StringList resolveCorrectTypes(Analyzable _an, boolean _exact, String _type) {
        String type_ = _an.resolveCorrectType(_type, _exact);
        return InvokingOperation.getBounds(type_, _an);
    }
    private String formatReturn(Analyzable _an, ClassMethodIdReturn _id, boolean _demand) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getAliasFct();
        String returnType_ = _id.getReturnType();
        StringList paramsReturn_ = new StringList();
        MethodId id_ = _id.getId().getConstraints();
        StringList params_ = id_.getParametersTypes();
        if (!id_.isStaticMethod() && _demand) {
            paramsReturn_.add(foundClass);
        }
        if (id_.isVararg()) {
            for (String p: params_.mid(0, params_.size() - 1)) {
                String p_ = p;
                paramsReturn_.add(p_);
            }
            String p_ = params_.last();
            paramsReturn_.add(PrimitiveTypeUtil.getPrettyArrayType(p_));
        } else {
            for (String p: params_) {
                String p_ = p;
                paramsReturn_.add(p_);
            }
        }
        paramsReturn_.add(returnType_);
        return StringList.concat(fctBase_, Templates.TEMPLATE_BEGIN, paramsReturn_.join(Templates.TEMPLATE_SEP), Templates.TEMPLATE_END);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeNotBoolAssignmentAfter(_conf);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = _nodes.getVal(this).getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }

    Argument getCommonArgument(Argument _previous, ExecutableCode _conf) {
        Argument arg_ = new Argument();
        String clArg_ = getResultClass().getNames().first();
        String ownerType_ = foundClass;
        ownerType_ = _conf.getOperationPageEl().formatVarType(ownerType_, _conf);
        clArg_ = _conf.getOperationPageEl().formatVarType(clArg_, _conf);
        if (method == null) {
            LambdaConstructorStruct l_ = new LambdaConstructorStruct(clArg_, ownerType_, realId, shiftArgument);
            l_.setInstanceCall(_previous);
            arg_.setStruct(l_);
            return arg_;
        }
        MethodId id_ = method.getConstraints();
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, id_, polymorph, shiftArgument, ancestor,abstractMethod);
        l_.setInstanceCall(_previous);
        arg_.setStruct(l_);
        return arg_;
    }
    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public boolean isCalculated() {
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    public final void setStaticAccess(boolean _staticAccess) {
        staticAccess = _staticAccess;
    }

    public final boolean isStaticAccess() {
        return staticAccess;
    }
    @Override
    public final void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass) {
        setPreviousResultClass(_previousResultClass, false);
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        previousResultClass = _previousResultClass;
        setStaticAccess(_staticAccess);
    }
    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }
}
