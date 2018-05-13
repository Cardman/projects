package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.InvokingConstructor;
import code.expressionlanguage.InvokingMethod;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class ChoiceFctOperation extends InvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;
    private MethodId realId;

    private boolean staticMethod;

    private boolean staticChoiceMethodTemplate;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    public ChoiceFctOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    boolean isCallMethodCtor() {
        return true;
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        int varargOnly_ = lookOnlyForVarArg();
        LgNames stds_ = _conf.getStandards();
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_conf.isStaticContext());
        }
        String className_ = methodName.substring(0, methodName.lastIndexOf(PAR_RIGHT));
        int lenPref_ = methodName.indexOf(PAR_LEFT) + 1;
        className_ = className_.substring(lenPref_);
        className_ = StringList.removeAllSpaces(className_);
        if (className_.contains(Templates.TEMPLATE_BEGIN)) {
            staticChoiceMethodTemplate = true;
            if (!checkCorrect(_conf, className_, true, getIndexInEl()+off_ + lenPref_)) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
        } else {
            if (!checkExistBase(_conf, false, className_, true, getIndexInEl()+off_ + lenPref_)) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
        }
        String clCurName_ = className_;
        if (hasVoidPrevious(clCurName_, _conf)) {
            return;
        }
        StringList bounds_ = getBounds(clCurName_, _conf);
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        if (hasVoidArguments(chidren_, firstArgs_, off_, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);

        trimMeth_ = StringList.removeAllSpaces(methodName.substring(methodName.lastIndexOf(PAR_RIGHT)+1));
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(), bounds_, trimMeth_, false, false, ClassArgumentMatching.toArgArray(firstArgs_));
        if (!clMeth_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
            return;
        }
        if (clMeth_.isAbstractMethod()) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
            AbstractMethod abs_ = new AbstractMethod();
            abs_.setClassName(clMeth_.getRealClass());
            abs_.setSgn(clMeth_.getRealId().getSignature());
            abs_.setRc(_conf.getCurrentLocation());
            abs_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().getErrorsDet().add(abs_);
            setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
            return;
        }
        classMethodId = clMeth_.getId();
        realId = clMeth_.getRealId();
        if (clMeth_.isVarArgToCall()) {
            StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        staticMethod = clMeth_.isStaticMethod();
        unwrapArgsFct(chidren_, realId, naturalVararg, lastType, firstArgs_, _conf);
        setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        if (children_.isEmpty()) {
            boolean isBool_;
            isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
            for (EntryCust<ClassField, AssignmentBefore> e: vars_.getFieldsBefore().getVal(this).entryList()) {
                AssignmentBefore b_ = e.getValue();
                fieldsAfter_.put(e.getKey(), b_.assignAfter(isBool_));
            }
            for (StringMap<AssignmentBefore> s: vars_.getVariablesBefore().getVal(this)) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore b_ = e.getValue();
                    sm_.put(e.getKey(), b_.assignAfter(isBool_));
                }
                variablesAfter_.add(sm_);
            }
            vars_.getFields().put(this, fieldsAfter_);
            vars_.getVariables().put(this, variablesAfter_);
            return;
        }
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);

        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        ArgumentCall argres_ = getArgument(previous_, arguments_, _conf);
        if (argres_.isInitClass()) {
            ProcessMethod.initializeClass(argres_.getInitClass().getClassName(), _conf.getContextEl());
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        Argument res_;
        if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            res_ = ProcessMethod.instanceArgument(i_.getClassName(), i_.getCurrentObject(), i_.getId(), i_.getArguments(), _conf.getContextEl());
        } else if (argres_.isInvokeMethod()) {
            InvokingMethod i_ = argres_.getInvokeMethod();
            res_ = ProcessMethod.calculateArgument(i_.getGl(), i_.getClassName(), i_.getId(), i_.getArguments(), _conf.getContextEl());
        } else {
            res_ = argres_.getArgument();
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
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = _nodes.getVal(this).getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        ArgumentCall argres_ = getArgument(previous_, arguments_, _conf);
        Argument res_ = argres_.getArgument();
        if (argres_.isInitClass()) {
            _conf.setInitClass(new NotInitializedClass(argres_.getInitClass().getClassName()));
        } else if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            _conf.setCallCtor(new CustomFoundConstructor(i_.getClassName(), i_.getFieldName(), i_.getOrdinal(), i_.getId(), i_.getCurrentObject(), i_.getArguments(), i_.getInstanceStep()));
        } else if (argres_.isInvokeMethod()) {
            InvokingMethod i_ = argres_.getInvokeMethod();
            _conf.setCallMethod(new CustomFoundMethod(i_.getGl(), i_.getClassName(), i_.getId(), i_.getArguments()));
        } else {
            setSimpleArgument(res_, _conf, _nodes);
        }
        return res_;
    }
    ArgumentCall getArgument(Argument _previous, CustList<Argument> _arguments, ExecutableCode _conf) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        String cast_;
        null_ = stds_.getAliasNullPe();
        cast_ = stds_.getAliasCast();
        CustList<Argument> firstArgs_;
        Argument arg_ = _previous;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        if (!staticMethod) {
            if (arg_.isNull()) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                Argument a_ = new Argument();
                return ArgumentCall.newArgument(a_);
            }

            classNameFound_ = classMethodId.getClassName();

            String argClassName_ = arg_.getObjectClassName(_conf.getContextEl());
            if (staticChoiceMethodTemplate) {
                classNameFound_ = Templates.format(argClassName_, classNameFound_, _conf);
                Mapping map_ = new Mapping();
                map_.setArg(argClassName_);
                map_.setParam(classNameFound_);
                if (!Templates.isCorrect(map_, _conf)) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                    Argument a_ = new Argument();
                    return ArgumentCall.newArgument(a_);
                }
                String base_ = StringList.getAllTypes(classNameFound_).first();
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.format(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            } else {
                classNameFound_ = StringList.getAllTypes(classNameFound_).first();
                String baseArgClassName_ = StringList.getAllTypes(argClassName_).first();
                if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, baseArgClassName_, _conf)) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(baseArgClassName_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                    Argument a_ = new Argument();
                    return ArgumentCall.newArgument(a_);
                }
                classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
                methodId_ = realId.format(classNameFound_, _conf);
                if (!methodId_.isVararg()) {
                    lastType_ = EMPTY_STRING;
                    naturalVararg_ = -1;
                } else {
                    if (methodId_.getParametersTypes().size() != _arguments.size()) {
                        lastType_ = methodId_.getParametersTypes().last();
                        naturalVararg_ = methodId_.getParametersTypes().size() - 1;
                    } else {
                        Mapping map_ = new Mapping();
                        String param_ = methodId_.getParametersTypes().last();
                        param_ = PrimitiveTypeUtil.getPrettyArrayType(param_);
                        String argClass_ = _arguments.last().getObjectClassName(_conf.getContextEl());
                        map_.setArg(argClass_);
                        map_.setParam(param_);
                        if (argClass_ != null && !Templates.isCorrect(map_, _conf)) {
                            lastType_ = methodId_.getParametersTypes().last();
                            naturalVararg_ = methodId_.getParametersTypes().size() - 1;
                        }
                    }
                }
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            }
            methodId_ = realId;
        } else {
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            classNameFound_ = classMethodId.getClassName();
            if (classes_.isCustomType(classNameFound_)) {
                InitClassState res_ = classes_.getLocks().getState(_conf.getContextEl(), classNameFound_);
                if (res_ == InitClassState.NOT_YET) {
                    InitializatingClass inv_ = new InitializatingClass(classNameFound_);
                    return ArgumentCall.newCall(inv_);
                }
                if (res_ == InitClassState.ERROR) {
                    CausingErrorStruct causing_ = new CausingErrorStruct(classNameFound_);
                    _conf.setException(causing_);
                    return ArgumentCall.newArgument(Argument.createVoid());
                }
            }
        }
        StringList params_ = new StringList();
        if (!staticMethod) {
            String className_ = stds_.getStructClassName(arg_.getStruct(), _conf.getContextEl());
            String classFormat_ = classNameFound_;
            classFormat_ = Templates.getFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_ == null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                Argument a_ = new Argument();
                return ArgumentCall.newArgument(a_);
            }
            int i_ = 0;
            for (String c: methodId_.getParametersTypes()) {
                String c_ = c;
                c_ = Templates.format(classFormat_, c_, _conf);
                if (i_ + 1 == methodId_.getParametersTypes().size() && methodId_.isVararg()) {
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        } else {
            int i_ = 0;
            for (String c: methodId_.getParametersTypes()) {
                String c_ = c;
                if (i_ + 1 == methodId_.getParametersTypes().size() && methodId_.isVararg()) {
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        }
        int i_ = CustList.FIRST_INDEX;
        for (Argument a: firstArgs_) {
            if (i_ < params_.size()) {
                Struct str_ = a.getStruct();
                if (!str_.isNull()) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(a.getObjectClassName(_conf.getContextEl()));
                    mapping_.setParam(params_.get(i_));
                    if (!Templates.isCorrect(mapping_, _conf)) {
                        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                        _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                        Argument a_ = new Argument();
                        return ArgumentCall.newArgument(a_);
                    }
                }
            }
            i_++;
        }
        if (!classes_.isCustomType(classNameFound_)) {
            ClassMethodId dyn_ = new ClassMethodId(classNameFound_, methodId_);
            ResultErrorStd res_ = LgNames.invokeMethod(_conf.getContextEl(), naturalVararg > -1, dyn_, arg_.getStruct(), Argument.toArgArray(firstArgs_));
            if (res_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
                Argument a_ = new Argument();
                return ArgumentCall.newArgument(a_);
            }
            Argument argRes_ = new Argument();
            argRes_.setStruct(res_.getResult());
            return ArgumentCall.newArgument(argRes_);
        }
        InvokingMethod inv_ = new InvokingMethod(arg_, classNameFound_, methodId_, firstArgs_);
        return ArgumentCall.newCall(inv_);
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
