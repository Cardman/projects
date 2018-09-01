package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.VarargError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.LambdaMethodStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class LambdaOperation extends LeafOperation implements PossibleIntermediateDotted {
    private static final String TRUE_STRING = "true";
    private static final String FALSE_STRING = "false";

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
//        int indexStaticFlag_ = -1;
        int len_ = args_.size();
        String fromType_ = ContextEl.removeDottedSpaces(args_.first());
        fromType_ = _conf.resolveCorrectType(fromType_);
        StringList str_ = InvokingOperation.getBounds(fromType_, _conf);
        CustList<ClassArgumentMatching> methodTypes_ = new CustList<ClassArgumentMatching>();
        if (!isIntermediateDottedOperation()) {
            boolean stCtx_ = _conf.isStaticContext();
            String name_ = args_.get(1).trim();
            int vararg_ = -1;
            for (int i = 2; i < len_; i++) {
                String arg_ = ContextEl.removeDottedSpaces(args_.get(i));
                String type_;
                if (arg_.endsWith(VARARG_SUFFIX)) {
                    if (i + 1 == len_) {
                        //last type => error
                        VarargError varg_ = new VarargError();
                        varg_.setFileName(_conf.getCurrentFileName());
                        varg_.setRc(_conf.getCurrentLocation());
                        varg_.setMethodName(VAR_ARG);
                        _conf.getClasses().addError(varg_);
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                    vararg_ = i - 2;
                    type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
                    type_ = PrimitiveTypeUtil.getPrettyArrayType(type_);
                } else {
                    type_ = arg_;
                }
                arg_ = _conf.resolveCorrectType(type_);
                methodTypes_.add(new ClassArgumentMatching(arg_));
            }
            if (!stCtx_) {
                Mapping map_ = new Mapping();
                map_.setArg(_conf.getGlobalClass());
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
            }
            ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, stCtx_, str_, name_, true, false, true, ClassArgumentMatching.toArgArray(methodTypes_));
            if (!id_.isFoundMethod()) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String foundClass_ = id_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            String type_ = str_.first();
            foundClass = Templates.getFullTypeByBases(type_, foundClass_, _conf);
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            ancestor = id_.getAncestor();
            String fct_ = formatReturn(_conf, id_.getReturnType(), false);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        OperationNode o_ = m_.getFirstChild();
        while (o_.getNextSibling() != this) {
            o_ = o_.getNextSibling();
        }
        if (o_ instanceof StaticAccessOperation) {
            StaticAccessOperation prev_ = (StaticAccessOperation) o_;
            String bool_ = args_.get(1).trim();
            boolean static_;
            if (StringList.quickEq(bool_, prefixFunction(TRUE_STRING))) {
                static_ = true;
            } else if (StringList.quickEq(bool_, prefixFunction(FALSE_STRING))) {
                static_ = false;
            } else {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(VAR_ARG);
                _conf.getClasses().addError(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String name_ = args_.get(2).trim();
            int vararg_ = -1;
            for (int i = 3; i < len_; i++) {
                String arg_ = ContextEl.removeDottedSpaces(args_.get(i));
                String type_;
                if (arg_.endsWith(VARARG_SUFFIX)) {
                    if (i + 1 == len_) {
                        //last type => error
                        VarargError varg_ = new VarargError();
                        varg_.setFileName(_conf.getCurrentFileName());
                        varg_.setRc(_conf.getCurrentLocation());
                        varg_.setMethodName(VAR_ARG);
                        _conf.getClasses().addError(varg_);
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                    vararg_ = i - 3;
                    type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
                    type_ = PrimitiveTypeUtil.getPrettyArrayType(type_);
                } else {
                    type_ = arg_;
                }
                arg_ = _conf.resolveCorrectType(type_);
                methodTypes_.add(new ClassArgumentMatching(arg_));
            }
            String preCl_ = prev_.getResultClass().getNames().first();
            preCl_ = Templates.getIdFromAllTypes(preCl_);
            String forPreCl_ = Templates.format(_conf.getGlobalClass(), preCl_, _conf);
            Mapping map_ = new Mapping();
            map_.setArg(forPreCl_);
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
            ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, static_, str_, name_, true, false, true, ClassArgumentMatching.toArgArray(methodTypes_));
            if (!id_.isFoundMethod()) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String foundClass_ = id_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            String type_ = str_.first();
            foundClass = Templates.getFullTypeByBases(type_, foundClass_, _conf);
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            ancestor = id_.getAncestor();
            shiftArgument = !static_;
            String fct_ = formatReturn(_conf, id_.getReturnType(), !static_);
            setResultClass(new ClassArgumentMatching(fct_));
            return;
        }
        boolean stCtx_ = isStaticAccess();
        String name_ = args_.get(1).trim();
        int vararg_ = -1;
        for (int i = 2; i < len_; i++) {
            String arg_ = ContextEl.removeDottedSpaces(args_.get(i));
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 == len_) {
                    //last type => error
                    VarargError varg_ = new VarargError();
                    varg_.setFileName(_conf.getCurrentFileName());
                    varg_.setRc(_conf.getCurrentLocation());
                    varg_.setMethodName(VAR_ARG);
                    _conf.getClasses().addError(varg_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                vararg_ = i - 2;
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
        ClassMethodIdReturn id_ = OperationNode.getDeclaredCustMethod(_conf, vararg_, stCtx_, str_, name_, true, false, true, ClassArgumentMatching.toArgArray(methodTypes_));
        if (!id_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String foundClass_ = id_.getRealClass();
        foundClass_ = Templates.getIdFromAllTypes(foundClass_);
        String type_ = str_.first();
        foundClass = Templates.getFullTypeByBases(type_, foundClass_, _conf);
        MethodId idCt_ = id_.getRealId();
        method = new ClassMethodId(foundClass_, idCt_);
        ancestor = id_.getAncestor();
        String fct_ = formatReturn(_conf, id_.getReturnType(), false);
        setResultClass(new ClassArgumentMatching(fct_));
    }

    private String formatReturn(Analyzable _an, String _returnType, boolean _demand) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getAliasFct();
        StringList paramsReturn_ = new StringList();
        StringList params_ = method.getConstraints().getParametersTypes();
        if (!method.getConstraints().isStaticMethod() && _demand) {
            paramsReturn_.add(foundClass);
        }
        if (method.getConstraints().isVararg()) {
            for (String p: params_.mid(0, params_.size() - 1)) {
                paramsReturn_.add(p);
            }
            paramsReturn_.add(PrimitiveTypeUtil.getPrettyArrayType(params_.last()));
        } else {
            for (String p: params_) {
                paramsReturn_.add(p);
            }
        }
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
        String ownerType_ = method.getClassName();
        ownerType_ = _conf.getOperationPageEl().formatVarType(ownerType_, _conf);
        MethodId id_ = method.getConstraints();
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, id_, true, shiftArgument, ancestor);
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
