package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.AbstractMethod;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.BadOperandsNumber;
import code.expressionlanguage.errors.custom.IllegalCallCtorByType;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.errors.custom.UndefinedFieldError;
import code.expressionlanguage.errors.custom.UndefinedMethodError;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.errors.custom.VarargError;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class LambdaOperation extends VariableLeafOperation implements PossibleIntermediateDotted {

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
        previousResultClass = new ClassArgumentMatching(EMPTY_STRING);
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
            badCall_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(badCall_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String fromType_ = ContextEl.removeDottedSpaces(args_.first());
        CustList<ClassArgumentMatching> methodTypes_ = new CustList<ClassArgumentMatching>();
        KeyWords keyWords_ = _conf.getKeyWords();
        String operator_ = keyWords_.getKeyWordOperator();
        String new_ = keyWords_.getKeyWordNew();
        if (StringList.quickEq(fromType_, operator_)) {
            processOperator(_conf, stds_, args_, methodTypes_);
            return;
        }
        String name_ = args_.get(1).trim();
        if (name_.isEmpty()) {
            processField(_conf, stds_, m_, args_, len_, fromType_);
            return;
        }
        if (StringList.quickEq(name_, new_)) {
            processInstance(_conf, stds_, args_, len_, fromType_, methodTypes_);
            return;
        }
        processMethod(_conf, stds_, m_, args_, len_, fromType_, methodTypes_,
                name_);
    }

    private void processMethod(Analyzable _conf, LgNames _stds,
            MethodOperation _m, StringList _args, int _len, String _fromType,
            CustList<ClassArgumentMatching> _methodTypes, String _name) {
        StringList str_;
        String name_ = _name;
        if (!isIntermediateDottedOperation()) {
            int i_ = 2;
            boolean staticChoiceMethod_ = false;
            boolean accessFromSuper_ = false;
            boolean accessSuper_ = true;
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordSuper_ = keyWords_.getKeyWordSuper();
            String keyWordThat_ = keyWords_.getKeyWordThat();
            String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
            String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
            String keyWordId_ = keyWords_.getKeyWordId();
            if (i_ < _len && StringList.quickEq(name_, keyWordSuper_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                accessFromSuper_ = true;
            } else if (i_ < _len && StringList.quickEq(name_, keyWordThat_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
            } else if (i_ < _len && StringList.quickEq(name_, keyWordClasschoice_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                accessSuper_ = false;
            } else if (i_ < _len && StringList.quickEq(name_, keyWordSuperaccess_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
            } else {
                polymorph = true;
            }
            int vararg_ = -1;
            MethodId argsRes_;
            ClassMethodId feed_ = null;
            if (i_ < _len && StringList.quickEq(_args.get(i_).trim(), keyWordId_)) {
                boolean staticFlag_ = false;
                if (i_ + 1 < _len) {
                    String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
                    if (StringList.quickEq(_args.get(i_ + 1).trim(), keyWordStatic_)) {
                        i_++;
                        staticFlag_ = true;
                    }
                }
                String type_ = _conf.resolveCorrectType(_fromType, !staticFlag_);
                str_ = resolveCorrectTypes(_conf, !staticFlag_, _fromType);
                String cl_ = Templates.getIdFromAllTypes(type_);
                argsRes_ = resolveArguments(i_+1, _conf, cl_, staticFlag_, _args);
                if (argsRes_ == null) {
                    return;
                }
                boolean varargFct_ = argsRes_.isVararg();
                StringList params_ = argsRes_.getParametersTypes();
                feed_ = new ClassMethodId(cl_, new MethodId(staticFlag_, name_, params_, varargFct_));
                for (String s: argsRes_.getParametersTypes()) {
                    String format_ = Templates.wildCardFormatParam(staticFlag_, type_, s, _conf);
                    if (format_ == null) {
                        StaticAccessError static_ = new StaticAccessError();
                        static_.setFileName(_conf.getCurrentFileName());
                        static_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getClasses().addError(static_);
                        return;
                    }
                    _methodTypes.add(new ClassArgumentMatching(format_));
                }
            } else {
                str_ = resolveCorrectTypes(_conf, true, _fromType);
                argsRes_ = resolveArguments(i_, _conf, _args);
                if (argsRes_ == null) {
                    return;
                }
                if (argsRes_.isVararg()) {
                    vararg_ = _len- i_;
                }
                for (String s: argsRes_.getParametersTypes()) {
                    _methodTypes.add(new ClassArgumentMatching(s));
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
                if (!StringList.quickEq(name_, _stds.getAliasClone())) {
                    StringList classesNames_ = new StringList();
                    UndefinedMethodError undefined_ = new UndefinedMethodError();
                    MethodModifier mod_ = MethodModifier.FINAL;
                    undefined_.setClassName(str_);
                    undefined_.setId(new MethodId(mod_, name_, classesNames_));
                    undefined_.setFileName(_conf.getCurrentFileName());
                    undefined_.setIndexFile(_conf.getCurrentLocationIndex());
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
                String foundClass_ = PrimitiveTypeUtil.getPrettyArrayType(_stds.getAliasObject());
                MethodId id_ = new MethodId(false, name_, new StringList());
                method = new ClassMethodId(foundClass_, id_);
                shiftArgument = true;
                StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
                fct_.append(Templates.TEMPLATE_BEGIN);
                fct_.append(foundClass);
                fct_.append(Templates.TEMPLATE_SEP);
                fct_.append(foundClass);
                fct_.append(Templates.TEMPLATE_END);
                setResultClass(new ClassArgumentMatching(fct_.toString()));
                if (isIntermediateDottedOperation()) {
                    Argument arg_ = getPreviousArgument();
                    checkNull(arg_,_conf);
                }
                return;
            }
            ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, false, str_, name_, accessSuper_, accessFromSuper_, false,feed_, ClassArgumentMatching.toArgArray(_methodTypes));
            if (!id_.isFoundMethod()) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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
                    abs_.setIndexFile(_conf.getCurrentLocationIndex());
                    abs_.setFileName(_conf.getCurrentFileName());
                    _conf.getClasses().addError(abs_);
                    return;
                }
            }
            return;
        }
        OperationNode o_ = _m.getFirstChild();
        while (o_.getNextSibling() != this) {
            o_ = o_.getNextSibling();
        }
        if (o_ instanceof StaticAccessOperation) {
            str_ = resolveCorrectTypes(_conf, false, _fromType);
            int vararg_ = -1;
            MethodId argsRes_;
            ClassMethodId feed_ = null;
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordId_ = keyWords_.getKeyWordId();
            if (2 < _len && StringList.quickEq(_args.get(2).trim(), keyWordId_)) {
                String nameId_ = _args.get(3).trim();
                String cl_ = _conf.resolveAccessibleIdType(nameId_);
                if (cl_.isEmpty()) {
                    setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                    return;
                }
                argsRes_ = resolveArguments(4, _conf, cl_, true, _args);
                if (argsRes_ == null) {
                    return;
                }
                boolean varargFct_ = argsRes_.isVararg();
                StringList params_ = argsRes_.getParametersTypes();
                feed_ = new ClassMethodId(cl_, new MethodId(true, name_, params_, varargFct_));
            } else {
                argsRes_ = resolveArguments(2, _conf, _args);
                if (argsRes_ == null) {
                    return;
                }
                if (argsRes_.isVararg()) {
                    vararg_ = _len- 2;
                }
            }
            for (String s: argsRes_.getParametersTypes()) {
                _methodTypes.add(new ClassArgumentMatching(s));
            }
            ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, true, str_, name_, true, false, false, feed_, ClassArgumentMatching.toArgArray(_methodTypes));
            if (!id_.isFoundMethod()) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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
        boolean stCtx_;
        int vararg_ = -1;
        int i_ = 2;
        boolean staticChoiceMethod_ = false;
        boolean accessFromSuper_ = false;
        boolean accessSuper_ = true;
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordId_ = keyWords_.getKeyWordId();
        if (i_ < _len && StringList.quickEq(name_, keyWordSuper_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            accessFromSuper_ = true;
        } else if (i_ < _len && StringList.quickEq(name_, keyWordThat_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
        } else if (i_ < _len && StringList.quickEq(name_, keyWordClasschoice_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            accessSuper_ = false;
        } else if (i_ < _len && StringList.quickEq(name_, keyWordSuperaccess_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
        } else {
            polymorph = true;
        }
        MethodId argsRes_;
        ClassMethodId feed_ = null;
        if (i_ < _len && StringList.quickEq(_args.get(i_).trim(), keyWordId_)) {
            stCtx_ = false;
            if (i_ + 1 < _len) {
                String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
                if (StringList.quickEq(_args.get(i_ + 1).trim(), keyWordStatic_)) {
                    i_++;
                    stCtx_ = true;
                }
            }
            str_ = resolveCorrectTypes(_conf, !stCtx_, _fromType);
            String type_ = _conf.resolveCorrectType(_fromType, !stCtx_);
            String cl_ = Templates.getIdFromAllTypes(type_);
            if (cl_.isEmpty()) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            argsRes_ = resolveArguments(i_+1, _conf, cl_, stCtx_, _args);
            if (argsRes_ == null) {
                return;
            }
            boolean varargFct_ = argsRes_.isVararg();
            StringList params_ = argsRes_.getParametersTypes();
            feed_ = new ClassMethodId(cl_, new MethodId(stCtx_, name_, params_, varargFct_));
            for (String s: argsRes_.getParametersTypes()) {
                String format_ = Templates.wildCardFormatParam(stCtx_, type_, s, _conf);
                if (format_ == null) {
                    StaticAccessError static_ = new StaticAccessError();
                    static_.setFileName(_conf.getCurrentFileName());
                    static_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(static_);
                    return;
                }
                _methodTypes.add(new ClassArgumentMatching(format_));
            }
        } else {
            stCtx_ = false;
            str_ = resolveCorrectTypes(_conf, true, _fromType);
            argsRes_ = resolveArguments(i_, _conf, _args);
            if (argsRes_ == null) {
                return;
            }
            if (argsRes_.isVararg()) {
                vararg_ = _len- i_;
            }
            for (String s: argsRes_.getParametersTypes()) {
                _methodTypes.add(new ClassArgumentMatching(s));
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
            if (!StringList.quickEq(name_, _stds.getAliasClone())) {
                StringList classesNames_ = new StringList();
                UndefinedMethodError undefined_ = new UndefinedMethodError();
                MethodModifier mod_ = MethodModifier.FINAL;
                undefined_.setClassName(bounds_);
                undefined_.setId(new MethodId(mod_, name_, classesNames_));
                undefined_.setFileName(_conf.getCurrentFileName());
                undefined_.setIndexFile(_conf.getCurrentLocationIndex());
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
            String foundClass_ = PrimitiveTypeUtil.getPrettyArrayType(_stds.getAliasObject());
            MethodId id_ = new MethodId(false, name_, new StringList());
            method = new ClassMethodId(foundClass_, id_);
            StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
            fct_.append(Templates.TEMPLATE_BEGIN);
            fct_.append(foundClass);
            fct_.append(Templates.TEMPLATE_END);
            setResultClass(new ClassArgumentMatching(fct_.toString()));
            if (isIntermediateDottedOperation()) {
                Argument arg_ = getPreviousArgument();
                checkNull(arg_,_conf);
            }
            return;
        }
        Mapping map_ = new Mapping();
        map_.setArg(new ClassArgumentMatching(bounds_));
        map_.setParam(new ClassArgumentMatching(str_));
        StringMap<StringList> maps_ = new StringMap<StringList>();
        String glClass_ = _conf.getGlobalClass();
        for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
            maps_.put(t.getName(), t.getConstraints());
        }
        map_.setMapping(maps_);
        if (!Templates.isCorrectOrNumbers(map_, _conf)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(map_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(cast_);
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, stCtx_, str_, name_, accessSuper_, accessFromSuper_, false, feed_, ClassArgumentMatching.toArgArray(_methodTypes));
        if (!id_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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
                abs_.setIndexFile(_conf.getCurrentLocationIndex());
                abs_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(abs_);
            }
        }
    }

    private void processInstance(Analyzable _conf, LgNames _stds,
            StringList _args, int _len, String _fromType,
            CustList<ClassArgumentMatching> _methodTypes) {
        if (!isIntermediateDottedOperation()) {
            String cl_ = _conf.resolveCorrectType(_fromType);
            if (cl_.startsWith(ARR)) {
                processArray(_conf, _stds, _args, _len, _methodTypes, cl_);
                return;
            }
        }
        int vararg_ = -1;
        MethodId argsRes_;
        ConstructorId feed_ = null;
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        if (_len > 2 &&StringList.quickEq(_args.get(2).trim(), keyWordId_)) {
            String type_ = _conf.resolveCorrectType(_fromType, true);
            String cl_ = Templates.getIdFromAllTypes(type_);
            argsRes_ = resolveArguments(3, _conf, cl_, false, _args);
            if (argsRes_ == null) {
                return;
            }
            boolean varargFct_ = argsRes_.isVararg();
            StringList params_ = argsRes_.getParametersTypes();
            feed_ = new ConstructorId(cl_, params_, varargFct_);
            for (String s: argsRes_.getParametersTypes()) {
                String format_ = Templates.wildCardFormatParam(false, type_, s, _conf);
                if (format_ == null) {
                    StaticAccessError static_ = new StaticAccessError();
                    static_.setFileName(_conf.getCurrentFileName());
                    static_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(static_);
                    return;
                }
                _methodTypes.add(new ClassArgumentMatching(format_));
            }
        } else {
            int i_ = 2;
            argsRes_ = resolveArguments(i_, _conf, _args);
            if (argsRes_ == null) {
                return;
            }
            if (argsRes_.isVararg()) {
                vararg_ = _len- i_;
            }
            for (String s: argsRes_.getParametersTypes()) {
                _methodTypes.add(new ClassArgumentMatching(s));
            }
        }
        if (!isIntermediateDottedOperation()) {
            String cl_ = _conf.resolveCorrectType(_fromType);
            String id_ = Templates.getIdFromAllTypes(cl_);
            GeneType g_ = _conf.getClassBody(id_);
            if (g_ == null || PrimitiveTypeUtil.isPrimitive(id_, _conf) || g_.isAbstractType()) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(cl_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
                return;
            }
            for (String p:Templates.getAllTypes(cl_).mid(1)){
                if (p.startsWith(Templates.SUB_TYPE)) {
                    IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                    call_.setType(cl_);
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(call_);
                }
                if (p.startsWith(Templates.SUP_TYPE)) {
                    IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                    call_.setType(cl_);
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(call_);
                }
            }
            ConstrustorIdVarArg ctorRes_;
            ctorRes_ = getDeclaredCustConstructor(_conf, vararg_, new ClassArgumentMatching(cl_), feed_, ClassArgumentMatching.toArgArray(_methodTypes));
            realId = ctorRes_.getRealId();
            ConstructorId fid_ = ctorRes_.getConstId();
            StringList parts_ = new StringList();
            if (!g_.isStaticType()) {
                //From analyze
                StringList innerParts_ = Templates.getAllInnerTypes(cl_);
                parts_.add(innerParts_.mid(0, innerParts_.size() - 1).join(".."));
            }
            StringList params_ = fid_.getParametersTypes();
            if (fid_.isVararg()) {
                for (String p: params_.mid(0, params_.size() - 1)) {
                    parts_.add(p);
                }
                String p_ = params_.last();
                parts_.add(PrimitiveTypeUtil.getPrettyArrayType(p_));
            } else {
                for (String p: params_) {
                    parts_.add(p);
                }
            }
            foundClass = cl_;
            parts_.add(cl_);
            StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
            fct_.append(Templates.TEMPLATE_BEGIN);
            fct_.append(parts_.join(Templates.TEMPLATE_SEP));
            fct_.append(Templates.TEMPLATE_END);
            setResultClass(new ClassArgumentMatching(fct_.toString()));
            return;
        }
        String cl_ = _fromType.trim();
        if (cl_.startsWith("..")) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(static_);
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
                ok_ = false;
                continue;
            }
            for (String p:Templates.getAllTypes(o).mid(1)){
                if (p.startsWith(Templates.SUB_TYPE)) {
                    IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                    call_.setType(o);
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(call_);
                    ok_ = false;
                }
                if (p.startsWith(Templates.SUP_TYPE)) {
                    IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                    call_.setType(o);
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(call_);
                    ok_ = false;
                }
            }
        }
        if (!ok_) {
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(static_);
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        String sub_ = ownersMap_.getKeys().first();
        String sup_ = ownersMap_.values().first();
        String new_ = Templates.getFullTypeByBases(sub_, sup_, _conf);
        if (new_ == null) {
            Block bl_ = _conf.getCurrentBlock();
            AccessingImportingBlock r_ = _conf.getAnalyzing().getImporting();
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(cl_);
            un_.setFileName(r_.getFile().getFileName());
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(un_);
            cl_ = _conf.getStandards().getAliasObject();
        } else {
            StringList partsArgs_ = new StringList();
            for (String a: Templates.getAllTypes(cl_).mid(1)) {
                String res_ = _conf.resolveCorrectType(a);
                partsArgs_.add(res_);
            }
            if (partsArgs_.isEmpty()) {
                cl_ = StringList.concat(new_,"..",idClass_);
            } else {
                cl_ = StringList.concat(new_,"..",idClass_,"<",partsArgs_.join(","),">");
            }
            StringMap<StringList> vars_ = _conf.getCurrentConstraints();
            if (!Templates.isCorrectTemplateAll(cl_, vars_, _conf, true)) {
                Block bl_ = _conf.getCurrentBlock();
                AccessingImportingBlock r_ = _conf.getAnalyzing().getImporting();
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(cl_);
                un_.setFileName(r_.getFile().getFileName());
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(un_);
                cl_ = _conf.getStandards().getAliasObject();
            }
        }
        foundClass = cl_;
        shiftArgument = true;
        String id_ = Templates.getIdFromAllTypes(cl_);
        GeneType g_ = _conf.getClassBody(id_);
        if (g_ == null || g_.isAbstractType()) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(cl_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        for (String p:Templates.getAllTypes(cl_).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(cl_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(cl_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
            }
        }
        ConstrustorIdVarArg ctorRes_;
        ctorRes_ = getDeclaredCustConstructor(_conf, vararg_, new ClassArgumentMatching(cl_), feed_, ClassArgumentMatching.toArgArray(_methodTypes));
        realId = ctorRes_.getRealId();
        ConstructorId fid_ = ctorRes_.getConstId();
        StringList parts_ = new StringList();
        StringList params_ = fid_.getParametersTypes();
        if (fid_.isVararg()) {
            for (String p: params_.mid(0, params_.size() - 1)) {
                parts_.add(p);
            }
            String p_ = params_.last();
            parts_.add(PrimitiveTypeUtil.getPrettyArrayType(p_));
        } else {
            for (String p: params_) {
                parts_.add(p);
            }
        }
        parts_.add(cl_);
        StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
        fct_.append(Templates.TEMPLATE_BEGIN);
        fct_.append(parts_.join(Templates.TEMPLATE_SEP));
        fct_.append(Templates.TEMPLATE_END);
        setResultClass(new ClassArgumentMatching(fct_.toString()));
    }

    private void processField(Analyzable _conf, LgNames _stds,
            MethodOperation _m, StringList _args, int _len, String _fromType) {
        StringList str_;
        if (_len < 3) {
            BadOperandsNumber badCall_ = new BadOperandsNumber();
            badCall_.setOperandsNumber(0);
            badCall_.setFileName(_conf.getCurrentFileName());
            badCall_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(badCall_);
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        String fieldName_ = _args.get(2).trim();
        if (!isIntermediateDottedOperation()) {
            str_ = resolveCorrectTypes(_conf, true, _fromType);
            int i_ = 3;
            boolean accessFromSuper_ = false;
            boolean accessSuper_ = true;
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordSuper_ = keyWords_.getKeyWordSuper();
            String keyWordThat_ = keyWords_.getKeyWordThat();
            String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
            String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
            if (i_ < _len && StringList.quickEq(fieldName_, keyWordSuper_)) {
                fieldName_ = _args.get(i_).trim();
                i_++;
                accessFromSuper_ = true;
            } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordThat_)) {
                fieldName_ = _args.get(i_).trim();
                i_++;
            } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordClasschoice_)) {
                fieldName_ = _args.get(i_).trim();
                i_++;
                accessSuper_ = false;
            } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordSuperaccess_)) {
                fieldName_ = _args.get(i_).trim();
                i_++;
            }
            boolean aff_ = i_ < _len;
            ClassArgumentMatching fromCl_ = new ClassArgumentMatching(str_);
            FieldResult r_ = OperationNode.getDeclaredCustField(_conf, false, fromCl_, !accessFromSuper_, accessSuper_, fieldName_, false, aff_);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                UndefinedFieldError und_ = new UndefinedFieldError();
                for (String c: str_) {
                    String base_ = Templates.getIdFromAllTypes(c);
                    und_.setClassName(base_);
                    und_.setId(fieldName_);
                    und_.setFileName(_conf.getCurrentFileName());
                    und_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(und_);
                }
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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
                String type_ = _args.get(i_).trim();
                String arg_ = _conf.resolveCorrectType(type_);
                StringMap<StringList> map_ = new StringMap<StringList>();
                String glClass_ = _conf.getGlobalClass();
                for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                    map_.put(t.getName(), t.getConstraints());
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(out_);
                mapping_.setMapping(map_);
                if (!Templates.isCorrectOrNumbers(mapping_, _conf)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(cast_);
                }
                params_.add(arg_);
                //setter
            }
            String fct_ = formatFieldReturn(_conf, static_, params_, out_, shiftArgument);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        OperationNode o_ = _m.getFirstChild();
        while (o_.getNextSibling() != this) {
            o_ = o_.getNextSibling();
        }
        if (o_ instanceof StaticAccessOperation) {
            str_ = resolveCorrectTypes(_conf, false, _fromType);
            int i_ = 3;
            boolean aff_ = i_ < _len;
            ClassArgumentMatching fromCl_ = new ClassArgumentMatching(str_);
            FieldResult r_ = OperationNode.getDeclaredCustField(_conf, false, fromCl_, true, true, fieldName_, false, aff_);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                UndefinedFieldError und_ = new UndefinedFieldError();
                for (String c: str_) {
                    String base_ = Templates.getIdFromAllTypes(c);
                    und_.setClassName(base_);
                    und_.setId(fieldName_);
                    und_.setFileName(_conf.getCurrentFileName());
                    und_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(und_);
                }
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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
                String type_ = _args.get(i_).trim();
                String arg_ = _conf.resolveCorrectType(type_);
                StringMap<StringList> map_ = new StringMap<StringList>();
                String glClass_ = _conf.getGlobalClass();
                for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                    map_.put(t.getName(), t.getConstraints());
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(out_);
                mapping_.setMapping(map_);
                if (!Templates.isCorrectOrNumbers(mapping_, _conf)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
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
        str_ = resolveCorrectTypes(_conf, !stCtx_, _fromType);
        int i_ = 3;
        boolean accessFromSuper_ = false;
        boolean accessSuper_ = true;
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        if (i_ < _len && StringList.quickEq(fieldName_, keyWordSuper_)) {
            fieldName_ = _args.get(i_).trim();
            i_++;
            accessFromSuper_ = true;
        } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordThat_)) {
            fieldName_ = _args.get(i_).trim();
            i_++;
        } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordClasschoice_)) {
            fieldName_ = _args.get(i_).trim();
            i_++;
            accessSuper_ = false;
        } else if (i_ < _len && StringList.quickEq(fieldName_, keyWordSuperaccess_)) {
            fieldName_ = _args.get(i_).trim();
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
        for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
            maps_.put(t.getName(), t.getConstraints());
        }
        map_.setMapping(maps_);
        if (!Templates.isCorrectOrNumbers(map_, _conf)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(map_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(cast_);
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        boolean aff_ = i_ < _len;
        ClassArgumentMatching fromCl_ = new ClassArgumentMatching(str_);
        FieldResult r_ = OperationNode.getDeclaredCustField(_conf, stCtx_, fromCl_, !accessFromSuper_, accessSuper_, fieldName_, false, aff_);
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            UndefinedFieldError und_ = new UndefinedFieldError();
            for (String c: str_) {
                String base_ = Templates.getIdFromAllTypes(c);
                und_.setClassName(base_);
                und_.setId(fieldName_);
                und_.setFileName(_conf.getCurrentFileName());
                und_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(und_);
            }
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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
            String type_ = _args.get(i_).trim();
            String arg_ = _conf.resolveCorrectType(type_);
            Mapping mapping_ = new Mapping();
            mapping_.setArg(arg_);
            mapping_.setParam(out_);
            mapping_.setMapping(maps_);
            if (!Templates.isCorrectOrNumbers(mapping_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(cast_);
            }
            params_.add(arg_);
            //setter
        }
        String fct_ = formatFieldReturn(_conf, static_, params_, out_, false);
        setResultClass(new ClassArgumentMatching(fct_));
    }

    private void processOperator(Analyzable _conf, LgNames _stds,
            StringList _args, CustList<ClassArgumentMatching> _methodTypes) {
        String operator_ = _args.get(1).trim();
        if (!isIntermediateDottedOperation()) {
            int i_ = 2;
            MethodId argsRes_ = resolveArguments(i_, _conf, _args);
            if (argsRes_ == null) {
                return;
            }
            for (String s: argsRes_.getParametersTypes()) {
                _methodTypes.add(new ClassArgumentMatching(s));
            }
            ClassMethodIdReturn id_ = OperationNode.getOperator(_conf, operator_, ClassArgumentMatching.toArgArray(_methodTypes));
            if (!id_.isFoundMethod()) {
                setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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
        MethodId argsRes_ = resolveArguments(i_, _conf, _args);
        if (argsRes_ == null) {
            return;
        }
        _methodTypes.add(previousResultClass);
        for (String s: argsRes_.getParametersTypes()) {
            _methodTypes.add(new ClassArgumentMatching(s));
        }
        ClassMethodIdReturn id_ = OperationNode.getOperator(_conf, operator_, ClassArgumentMatching.toArgArray(_methodTypes));
        if (!id_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
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

    private void processArray(Analyzable _conf, LgNames _stds,
            StringList _args, int _len,
            CustList<ClassArgumentMatching> _methodTypes, String _cl) {
        int i_ = 2;
        StringList parts_ = new StringList();
        boolean err_ = false;
        for (int i = i_; i < _len; i++) {
            String arg_ = ContextEl.removeDottedSpaces(_args.get(i));
            parts_.add(arg_);
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(arg_);
            if (!clArg_.isNumericInt(_conf)) {
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
                un_.setOperands(clArg_);
                _conf.getClasses().addError(un_);
                err_ = true;
            }
            _methodTypes.add(clArg_);
        }
        if (_methodTypes.isEmpty()) {
            BadOperandsNumber badCall_ = new BadOperandsNumber();
            badCall_.setOperandsNumber(0);
            badCall_.setFileName(_conf.getCurrentFileName());
            badCall_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(badCall_);
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        if (err_) {
            setResultClass(new ClassArgumentMatching(_stds.getAliasObject()));
            return;
        }
        String elt_ = _cl.substring(ARR.length());
        String out_ = PrimitiveTypeUtil.getPrettyArrayType(elt_, _methodTypes.size());
        foundClass = out_;
        realId = new ConstructorId(out_, parts_, true);
        parts_.add(out_);
        StringBuilder fct_ = new StringBuilder(_stds.getAliasFct());
        fct_.append(Templates.TEMPLATE_BEGIN);
        fct_.append(parts_.join(Templates.TEMPLATE_SEP));
        fct_.append(Templates.TEMPLATE_END);
        setResultClass(new ClassArgumentMatching(fct_.toString()));
    }
    private MethodId resolveArguments(int _from, Analyzable _conf, String _fromType, boolean _static, StringList _params){
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
                    varg_.setIndexFile(_conf.getCurrentLocationIndex());
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
        return new MethodId(_static, OperationNode.EMPTY_STRING, out_, vararg_ != -1);
    }
    private MethodId resolveArguments(int _from, Analyzable _conf, StringList _params){
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
                    varg_.setIndexFile(_conf.getCurrentLocationIndex());
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
        return new MethodId(false, OperationNode.EMPTY_STRING, out_, vararg_ != -1);
    }
    private static StringList resolveCorrectTypes(Analyzable _an, boolean _exact, String _type) {
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
                paramsReturn_.add(p);
            }
            String p_ = params_.last();
            paramsReturn_.add(PrimitiveTypeUtil.getPrettyArrayType(p_));
        } else {
            for (String p: params_.mid(start_)) {
                paramsReturn_.add(p);
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
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        previousResultClass = _previousResultClass;
        setStaticAccess(_staticAccess);
    }

    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public ClassMethodId getMethod() {
        return method;
    }

    public String getFoundClass() {
        return foundClass;
    }

    public int getAncestor() {
        return ancestor;
    }

    public boolean isShiftArgument() {
        return shiftArgument;
    }

    public boolean isPolymorph() {
        return polymorph;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public ConstructorId getRealId() {
        return realId;
    }

    public ClassField getFieldId() {
        return fieldId;
    }

    public boolean isAffField() {
        return affField;
    }

    public String getReturnFieldType() {
        return returnFieldType;
    }
    
}
