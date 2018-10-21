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
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.IllegalCallCtorByType;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UndefinedFieldError;
import code.expressionlanguage.methods.util.UndefinedMethodError;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.methods.util.VarargError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.LambdaConstructorStruct;
import code.expressionlanguage.opers.util.LambdaFieldStruct;
import code.expressionlanguage.opers.util.LambdaMethodStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
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
    private ClassField fieldId;
    private boolean affField;
    private String returnFieldType;

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
        if (len_ < 2) {
            BadOperandsNumber badCall_ = new BadOperandsNumber();
            badCall_.setOperandsNumber(0);
            badCall_.setFileName(_conf.getCurrentFileName());
            badCall_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badCall_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String fromType_ = ContextEl.removeDottedSpaces(args_.first());
        CustList<ClassArgumentMatching> methodTypes_ = new CustList<ClassArgumentMatching>();
        if (StringList.quickEq(fromType_, prefixFunction("operator"))) {
            processOperator(_conf, stds_, args_, methodTypes_);
            return;
        }
        String name_ = args_.get(1).trim();
        if (name_.isEmpty()) {
            processField(_conf, stds_, m_, args_, len_, fromType_);
            return;
        }
        if (StringList.quickEq(name_, prefixFunction(INSTANCE))) {
            processInstance(_conf, stds_, args_, len_, fromType_, methodTypes_);
            return;
        }
        processMethod(_conf, stds_, m_, args_, len_, fromType_, methodTypes_,
                name_);
    }

    private void processMethod(Analyzable _conf, LgNames stds_,
            MethodOperation m_, StringList args_, int len_, String fromType_,
            CustList<ClassArgumentMatching> methodTypes_, String _name) {
        StringList str_;
        String name_ = _name;
        if (!isIntermediateDottedOperation()) {
            String type_ = _conf.resolveCorrectType(fromType_, true);
            str_ = resolveCorrectTypes(_conf, true, fromType_);
            int i_ = 2;
            boolean staticChoiceMethod_ = false;
            boolean accessFromSuper_ = false;
            boolean accessSuper_ = true;
            if (i_ < len_ && StringList.quickEq(name_, prefixFunction(SUPER_ACCESS))) {
                name_ = args_.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                accessFromSuper_ = true;
            } else if (i_ < len_ && StringList.quickEq(name_, prefixFunction(THAT))) {
                name_ = args_.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
            } else if (i_ < len_ && StringList.quickEq(name_, prefixFunction(CLASS_CHOICE))) {
                name_ = args_.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                accessSuper_ = false;
            } else if (i_ < len_ && StringList.quickEq(name_, prefixFunction(SUPER_ACCESS_FCT))) {
                name_ = args_.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
            } else {
                polymorph = true;
            }
            int vararg_ = -1;
            MethodId argsRes_;
            ClassMethodId feed_ = null;
            if (i_ < len_ && StringList.quickEq(args_.get(i_).trim(), prefixFunction("id"))) {
                String cl_ = Templates.getIdFromAllTypes(type_);
                argsRes_ = resolveArguments(i_+1, _conf, cl_, EMPTY_STRING, false, args_);
                if (argsRes_ == null) {
                    return;
                }
                boolean varargFct_ = argsRes_.isVararg();
                StringList params_ = argsRes_.getParametersTypes();
                feed_ = new ClassMethodId(cl_, new MethodId(false, name_, params_, varargFct_));
                for (String s: argsRes_.getParametersTypes()) {
                    String format_ = Templates.wildCardFormat(false, type_, s, _conf, false);
                    if (format_ == null) {
                        StaticAccessError static_ = new StaticAccessError();
                        static_.setFileName(_conf.getCurrentFileName());
                        static_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().addError(static_);
                        return;
                    }
                    methodTypes_.add(new ClassArgumentMatching(format_));
                }
            } else {
                argsRes_ = resolveArguments(i_, _conf, EMPTY_STRING, false, args_);
                if (argsRes_ == null) {
                    return;
                }
                if (argsRes_.isVararg()) {
                    vararg_ = len_- i_;
                }
                for (String s: argsRes_.getParametersTypes()) {
                    methodTypes_.add(new ClassArgumentMatching(s));
                }
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
            ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, false, str_, name_, accessSuper_, accessFromSuper_, false,feed_, ClassArgumentMatching.toArgArray(methodTypes_));
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
            String fct_ = formatReturn(_conf, false, id_, shiftArgument);
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
            MethodId argsRes_;
            ClassMethodId feed_ = null;
            if (2 < len_ && StringList.quickEq(args_.get(2).trim(), prefixFunction("id"))) {
                String nameId_ = args_.get(3).trim();
                String cl_ = _conf.resolveAccessibleIdType(nameId_);
                if (cl_.isEmpty()) {
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                argsRes_ = resolveArguments(4, _conf, cl_, EMPTY_STRING, false, args_);
                if (argsRes_ == null) {
                    return;
                }
                boolean varargFct_ = argsRes_.isVararg();
                StringList params_ = argsRes_.getParametersTypes();
                feed_ = new ClassMethodId(cl_, new MethodId(true, name_, params_, varargFct_));
            } else {
                argsRes_ = resolveArguments(2, _conf, EMPTY_STRING, false, args_);
                if (argsRes_ == null) {
                    return;
                }
                if (argsRes_.isVararg()) {
                    vararg_ = len_- 2;
                }
            }
            for (String s: argsRes_.getParametersTypes()) {
                methodTypes_.add(new ClassArgumentMatching(s));
            }
            ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, true, str_, name_, true, false, false, feed_, ClassArgumentMatching.toArgArray(methodTypes_));
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
            String fct_ = formatReturn(_conf, false, id_, false);
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
        if (i_ < len_ && StringList.quickEq(name_, prefixFunction(SUPER_ACCESS))) {
            name_ = args_.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            accessFromSuper_ = true;
        } else if (i_ < len_ && StringList.quickEq(name_, prefixFunction(THAT))) {
            name_ = args_.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
        } else if (i_ < len_ && StringList.quickEq(name_, prefixFunction(CLASS_CHOICE))) {
            name_ = args_.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            accessSuper_ = false;
        } else if (i_ < len_ && StringList.quickEq(name_, prefixFunction(SUPER_ACCESS_FCT))) {
            name_ = args_.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
        } else {
            polymorph = true;
        }
        MethodId argsRes_;
        ClassMethodId feed_ = null;
        if (i_ < len_ && StringList.quickEq(args_.get(i_).trim(), prefixFunction("id"))) {
            String type_ = _conf.resolveCorrectType(fromType_);
            String cl_ = Templates.getIdFromAllTypes(type_);
            if (cl_.isEmpty()) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            argsRes_ = resolveArguments(i_+1, _conf, cl_, EMPTY_STRING, false, args_);
            if (argsRes_ == null) {
                return;
            }
            boolean varargFct_ = argsRes_.isVararg();
            StringList params_ = argsRes_.getParametersTypes();
            feed_ = new ClassMethodId(cl_, new MethodId(stCtx_, name_, params_, varargFct_));
            for (String s: argsRes_.getParametersTypes()) {
                String format_ = Templates.wildCardFormat(stCtx_, type_, s, _conf, false);
                if (format_ == null) {
                    StaticAccessError static_ = new StaticAccessError();
                    static_.setFileName(_conf.getCurrentFileName());
                    static_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(static_);
                    return;
                }
                methodTypes_.add(new ClassArgumentMatching(format_));
            }
        } else {
            argsRes_ = resolveArguments(i_, _conf, EMPTY_STRING, false, args_);
            if (argsRes_ == null) {
                return;
            }
            if (argsRes_.isVararg()) {
                vararg_ = len_- i_;
            }
            for (String s: argsRes_.getParametersTypes()) {
                methodTypes_.add(new ClassArgumentMatching(s));
            }
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
        ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, stCtx_, str_, name_, accessSuper_, accessFromSuper_, false, feed_, ClassArgumentMatching.toArgArray(methodTypes_));
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
        String fct_ = formatReturn(_conf, false, id_, false);
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

    private void processInstance(Analyzable _conf, LgNames stds_,
            StringList args_, int len_, String fromType_,
            CustList<ClassArgumentMatching> methodTypes_) {
        if (!isIntermediateDottedOperation()) {
            String cl_ = _conf.resolveCorrectType(fromType_);
            if (cl_.startsWith(ARR)) {
                processArray(_conf, stds_, args_, len_, methodTypes_, cl_);
                return;
            }
        }
        int vararg_ = -1;
        MethodId argsRes_;
        ConstructorId feed_ = null;
        if (len_ > 2 &&StringList.quickEq(args_.get(2).trim(), prefixFunction("id"))) {
            String type_ = _conf.resolveCorrectType(fromType_, true);
            String cl_ = Templates.getIdFromAllTypes(type_);
            argsRes_ = resolveArguments(3, _conf, cl_, EMPTY_STRING, false, args_);
            if (argsRes_ == null) {
                return;
            }
            boolean varargFct_ = argsRes_.isVararg();
            StringList params_ = argsRes_.getParametersTypes();
            feed_ = new ConstructorId(cl_, params_, varargFct_);
            for (String s: argsRes_.getParametersTypes()) {
                String format_ = Templates.wildCardFormat(false, type_, s, _conf, false);
                if (format_ == null) {
                    StaticAccessError static_ = new StaticAccessError();
                    static_.setFileName(_conf.getCurrentFileName());
                    static_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(static_);
                    return;
                }
                methodTypes_.add(new ClassArgumentMatching(format_));
            }
        } else {
            int i_ = 2;
            argsRes_ = resolveArguments(i_, _conf, EMPTY_STRING, false, args_);
            if (argsRes_ == null) {
                return;
            }
            if (argsRes_.isVararg()) {
                vararg_ = len_- i_;
            }
            for (String s: argsRes_.getParametersTypes()) {
                methodTypes_.add(new ClassArgumentMatching(s));
            }
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
            for (String p:Templates.getAllTypes(cl_).mid(1)){
                if (p.startsWith(Templates.SUB_TYPE)) {
                    IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                    call_.setType(cl_);
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(call_);
                }
                if (p.startsWith(Templates.SUP_TYPE)) {
                    IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                    call_.setType(cl_);
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(call_);
                }
            }
            ConstrustorIdVarArg ctorRes_;
            ctorRes_ = getDeclaredCustConstructor(_conf, vararg_, new ClassArgumentMatching(cl_), feed_, ClassArgumentMatching.toArgArray(methodTypes_));
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
        String glClass_ = _conf.getGlobalClass();
        boolean ok_ = true;
        for (String o: previousResultClass.getNames()) {
            if (o.startsWith(ARR)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(o);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(call_);
                ok_ = false;
                continue;
            }
            for (String p:Templates.getAllTypes(o).mid(1)){
                if (p.startsWith(Templates.SUB_TYPE)) {
                    IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                    call_.setType(o);
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(call_);
                    ok_ = false;
                }
                if (p.startsWith(Templates.SUP_TYPE)) {
                    IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                    call_.setType(o);
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(call_);
                    ok_ = false;
                }
            }
        }
        if (!ok_) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        for (String o: previousResultClass.getNames()) {
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
        for (String p:Templates.getAllTypes(cl_).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(cl_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(cl_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(call_);
            }
        }
        ConstrustorIdVarArg ctorRes_;
        ctorRes_ = getDeclaredCustConstructor(_conf, vararg_, new ClassArgumentMatching(cl_), feed_, ClassArgumentMatching.toArgArray(methodTypes_));
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
    }

    private void processField(Analyzable _conf, LgNames stds_,
            MethodOperation m_, StringList args_, int len_, String fromType_) {
        StringList str_;
        if (len_ < 3) {
            BadOperandsNumber badCall_ = new BadOperandsNumber();
            badCall_.setOperandsNumber(0);
            badCall_.setFileName(_conf.getCurrentFileName());
            badCall_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badCall_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String fieldName_ = args_.get(2).trim();
        if (!isIntermediateDottedOperation()) {
            str_ = resolveCorrectTypes(_conf, true, fromType_);
            int i_ = 3;
            boolean accessFromSuper_ = false;
            boolean accessSuper_ = true;
            if (i_ < len_ && StringList.quickEq(fieldName_, prefixFunction(SUPER_ACCESS))) {
                fieldName_ = args_.get(i_).trim();
                i_++;
                accessFromSuper_ = true;
            } else if (i_ < len_ && StringList.quickEq(fieldName_, prefixFunction(THAT))) {
                fieldName_ = args_.get(i_).trim();
                i_++;
            } else if (i_ < len_ && StringList.quickEq(fieldName_, prefixFunction(CLASS_CHOICE))) {
                fieldName_ = args_.get(i_).trim();
                i_++;
                accessSuper_ = false;
            } else if (i_ < len_ && StringList.quickEq(fieldName_, prefixFunction(SUPER_ACCESS_FCT))) {
                fieldName_ = args_.get(i_).trim();
                i_++;
            }
            boolean aff_ = i_ < len_;
            ClassArgumentMatching fromCl_ = new ClassArgumentMatching(str_);
            FieldResult r_ = OperationNode.getDeclaredCustField(_conf, false, fromCl_, !accessFromSuper_, accessSuper_, fieldName_, false, aff_);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                UndefinedFieldError und_ = new UndefinedFieldError();
                for (String c: str_) {
                    String base_ = Templates.getIdFromAllTypes(c);
                    und_.setClassName(base_);
                    und_.setId(fieldName_);
                    und_.setFileName(_conf.getCurrentFileName());
                    und_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(und_);
                }
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            affField = aff_;
            fieldId = r_.getId().getClassField();
            String out_ = r_.getId().getType();
            returnFieldType = out_;
            foundClass = r_.getId().getDeclaringClass();
            ancestor = r_.getAnc();
            boolean static_ = r_.getId().isStaticField();
            shiftArgument = !static_;
            StringList params_ = new StringList();
            if (aff_) {
                String type_ = args_.get(i_).trim();
                String arg_ = _conf.resolveCorrectType(type_);
                StringMap<StringList> map_ = new StringMap<StringList>();
                String glClass_ = _conf.getGlobalClass();
                if (glClass_ != null) {
                    for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                        map_.put(t.getName(), t.getConstraints());
                    }
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(out_);
                mapping_.setMapping(map_);
                if (!Templates.isCorrect(mapping_, _conf)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(cast_);
                }
                params_.add(arg_);
                //setter
            }
            String fct_ = formatFieldReturn(_conf, static_, params_, out_, shiftArgument);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        OperationNode o_ = m_.getFirstChild();
        while (o_.getNextSibling() != this) {
            o_ = o_.getNextSibling();
        }
        if (o_ instanceof StaticAccessOperation) {
            str_ = resolveCorrectTypes(_conf, false, fromType_);
            int i_ = 3;
            boolean aff_ = i_ < len_;
            ClassArgumentMatching fromCl_ = new ClassArgumentMatching(str_);
            FieldResult r_ = OperationNode.getDeclaredCustField(_conf, false, fromCl_, true, true, fieldName_, false, aff_);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                UndefinedFieldError und_ = new UndefinedFieldError();
                for (String c: str_) {
                    String base_ = Templates.getIdFromAllTypes(c);
                    und_.setClassName(base_);
                    und_.setId(fieldName_);
                    und_.setFileName(_conf.getCurrentFileName());
                    und_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(und_);
                }
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            affField = aff_;
            fieldId = r_.getId().getClassField();
            String out_ = r_.getId().getType();
            returnFieldType = out_;
            foundClass = r_.getId().getDeclaringClass();
            ancestor = r_.getAnc();
            StringList params_ = new StringList();
            if (aff_) {
                String type_ = args_.get(i_).trim();
                String arg_ = _conf.resolveCorrectType(type_);
                StringMap<StringList> map_ = new StringMap<StringList>();
                String glClass_ = _conf.getGlobalClass();
                if (glClass_ != null) {
                    for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                        map_.put(t.getName(), t.getConstraints());
                    }
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(out_);
                mapping_.setMapping(map_);
                if (!Templates.isCorrect(mapping_, _conf)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(cast_);
                }
                params_.add(arg_);
                //setter
            }
            String fct_ = formatFieldReturn(_conf, true, params_, out_, false);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        boolean stCtx_ = isStaticAccess();
        str_ = resolveCorrectTypes(_conf, !stCtx_, fromType_);
        int i_ = 3;
        boolean accessFromSuper_ = false;
        boolean accessSuper_ = true;
        if (i_ < len_ && StringList.quickEq(fieldName_, prefixFunction(SUPER_ACCESS))) {
            fieldName_ = args_.get(i_).trim();
            i_++;
            accessFromSuper_ = true;
        } else if (i_ < len_ && StringList.quickEq(fieldName_, prefixFunction(THAT))) {
            fieldName_ = args_.get(i_).trim();
            i_++;
        } else if (i_ < len_ && StringList.quickEq(fieldName_, prefixFunction(CLASS_CHOICE))) {
            fieldName_ = args_.get(i_).trim();
            i_++;
            accessSuper_ = false;
        } else if (i_ < len_ && StringList.quickEq(fieldName_, prefixFunction(SUPER_ACCESS_FCT))) {
            fieldName_ = args_.get(i_).trim();
            i_++;
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
        boolean aff_ = i_ < len_;
        ClassArgumentMatching fromCl_ = new ClassArgumentMatching(str_);
        FieldResult r_ = OperationNode.getDeclaredCustField(_conf, stCtx_, fromCl_, !accessFromSuper_, accessSuper_, fieldName_, false, aff_);
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            UndefinedFieldError und_ = new UndefinedFieldError();
            for (String c: str_) {
                String base_ = Templates.getIdFromAllTypes(c);
                und_.setClassName(base_);
                und_.setId(fieldName_);
                und_.setFileName(_conf.getCurrentFileName());
                und_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(und_);
            }
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        affField = aff_;
        fieldId = r_.getId().getClassField();
        String out_ = r_.getId().getType();
        returnFieldType = out_;
        foundClass = r_.getId().getDeclaringClass();
        ancestor = r_.getAnc();
        boolean static_ = r_.getId().isStaticField();
        StringList params_ = new StringList();
        if (aff_) {
            String type_ = args_.get(i_).trim();
            String arg_ = _conf.resolveCorrectType(type_);
            Mapping mapping_ = new Mapping();
            mapping_.setArg(arg_);
            mapping_.setParam(out_);
            mapping_.setMapping(maps_);
            if (!Templates.isCorrect(mapping_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(cast_);
            }
            params_.add(arg_);
            //setter
        }
        String fct_ = formatFieldReturn(_conf, static_, params_, out_, false);
        setResultClass(new ClassArgumentMatching(fct_));
    }

    private void processOperator(Analyzable _conf, LgNames stds_,
            StringList args_, CustList<ClassArgumentMatching> methodTypes_) {
        String operator_ = args_.get(1).trim();
        if (!isIntermediateDottedOperation()) {
            int i_ = 2;
            MethodId argsRes_ = resolveArguments(i_, _conf, EMPTY_STRING, false, args_);
            if (argsRes_ == null) {
                return;
            }
            for (String s: argsRes_.getParametersTypes()) {
                methodTypes_.add(new ClassArgumentMatching(s));
            }
            ClassMethodIdReturn id_ = OperationNode.getOperator(_conf, operator_, ClassArgumentMatching.toArgArray(methodTypes_));
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
            String fct_ = formatReturn(_conf, false, id_, false);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        int i_ = 2;
        MethodId argsRes_ = resolveArguments(i_, _conf, EMPTY_STRING, false, args_);
        if (argsRes_ == null) {
            return;
        }
        methodTypes_.add(previousResultClass);
        for (String s: argsRes_.getParametersTypes()) {
            methodTypes_.add(new ClassArgumentMatching(s));
        }
        ClassMethodIdReturn id_ = OperationNode.getOperator(_conf, operator_, ClassArgumentMatching.toArgArray(methodTypes_));
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
        shiftArgument = true;
        String fct_ = formatReturn(_conf, true, id_, false);
        setResultClass(new ClassArgumentMatching(fct_));
    }

    private void processArray(Analyzable _conf, LgNames stds_,
            StringList args_, int len_,
            CustList<ClassArgumentMatching> methodTypes_, String cl_) {
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
        realId = new ConstructorId(out_, parts_, true);
        parts_.add(out_);
        StringBuilder fct_ = new StringBuilder(stds_.getAliasFct());
        fct_.append(Templates.TEMPLATE_BEGIN);
        fct_.append(parts_.join(Templates.TEMPLATE_SEP));
        fct_.append(Templates.TEMPLATE_END);
        setResultClass(new ClassArgumentMatching(fct_.toString()));
    }
    private MethodId resolveArguments(int _from,Analyzable _conf, String _fromType, String _name,boolean _static, StringList _params){
        StringList out_ = new StringList();
        LgNames stds_ = _conf.getStandards();
        int len_ = _params.size();
        int vararg_ = -1;
        for (int i = _from; i < len_; i++) {
            String arg_ = ContextEl.removeDottedSpaces(_params.get(i));
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
                    return null;
                }
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            arg_ = _conf.resolveCorrectAccessibleType(type_, _fromType);
            out_.add(arg_);
        }
        return new MethodId(_static, _name, out_, vararg_ != -1);
    }
    private MethodId resolveArguments(int _from,Analyzable _conf, String _name,boolean _static, StringList _params){
        StringList out_ = new StringList();
        LgNames stds_ = _conf.getStandards();
        int len_ = _params.size();
        int vararg_ = -1;
        for (int i = _from; i < len_; i++) {
            String arg_ = ContextEl.removeDottedSpaces(_params.get(i));
            String type_;
            boolean wrap_ = false;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    VarargError varg_ = new VarargError();
                    varg_.setFileName(_conf.getCurrentFileName());
                    varg_.setRc(_conf.getCurrentLocation());
                    varg_.setMethodName(VAR_ARG);
                    _conf.getClasses().addError(varg_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return null;
                }
                wrap_ = true;
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            arg_ = _conf.resolveCorrectType(type_);
            if (wrap_) {
                arg_ = PrimitiveTypeUtil.getPrettyArrayType(arg_);
            }
            out_.add(arg_);
        }
        return new MethodId(_static, _name, out_, vararg_ != -1);
    }
    private StringList resolveCorrectTypes(Analyzable _an, boolean _exact, String _type) {
        String type_ = _an.resolveCorrectType(_type, _exact);
        return InvokingOperation.getBounds(type_, _an);
    }
    private String formatReturn(Analyzable _an, boolean _op,ClassMethodIdReturn _id, boolean _demand) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getAliasFct();
        String returnType_ = _id.getReturnType();
        StringList paramsReturn_ = new StringList();
        MethodId id_ = _id.getId().getConstraints();
        StringList params_ = id_.getParametersTypes();
        int start_ = 0;
        if (_op) {
            start_ = 1;
        }
        if (!id_.isStaticMethod() && _demand) {
            paramsReturn_.add(foundClass);
        }
        if (id_.isVararg()) {
            for (String p: params_.mid(start_, params_.size() - 1)) {
                String p_ = p;
                paramsReturn_.add(p_);
            }
            String p_ = params_.last();
            paramsReturn_.add(PrimitiveTypeUtil.getPrettyArrayType(p_));
        } else {
            for (String p: params_.mid(start_)) {
                String p_ = p;
                paramsReturn_.add(p_);
            }
        }
        paramsReturn_.add(returnType_);
        return StringList.concat(fctBase_, Templates.TEMPLATE_BEGIN, paramsReturn_.join(Templates.TEMPLATE_SEP), Templates.TEMPLATE_END);
    }
    private String formatFieldReturn(Analyzable _an, boolean _static, StringList _params, String _returnType, boolean _demand) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getAliasFct();
        StringList paramsReturn_ = new StringList();
        if (!_static && _demand) {
            paramsReturn_.add(foundClass);
        }
        paramsReturn_.addAllElts(_params);
        paramsReturn_.add(_returnType);
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
        if (realId == null && method == null) {
            String formatType_ = _conf.getOperationPageEl().formatVarType(returnFieldType, _conf);
            LambdaFieldStruct l_ = new LambdaFieldStruct(clArg_, ownerType_, fieldId, shiftArgument, ancestor,affField, formatType_);
            l_.setInstanceCall(_previous);
            arg_.setStruct(l_);
            return arg_;
        }
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
