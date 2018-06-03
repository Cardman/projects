package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.UndefinedFieldError;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.FieldableStruct;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class SettableAbstractFieldOperation extends
        AbstractFieldOperation implements SettableElResult {

    private boolean variable;
    private ClassField fieldId;
    private FieldInfo fieldMetaInfo;
    private boolean staticAccess;

    private boolean catString;

    public SettableAbstractFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }
    @Override
    public final void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (!isIntermediateDottedOperation()) {
            staticAccess = _conf.isStaticContext();
        }
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching cl_ = getFrom(_conf);
        if (cl_ == null) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String fieldName_ = getFieldName(_conf);
        boolean baseAccess_ = isBaseAccess(_conf);
        boolean superAccess_ = isSuperAccess(_conf);
        FieldResult r_;
        FieldInfo e_;
        r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, baseAccess_, superAccess_, fieldName_);
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            UndefinedFieldError und_ = new UndefinedFieldError();
            String base_ = StringList.getAllTypes(cl_.getName()).first();
            und_.setClassName(base_);
            und_.setId(fieldName_);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        e_ = r_.getId();
        fieldMetaInfo = e_;
        String c_ = fieldMetaInfo.getType();
        fieldId = new ClassField(e_.getDeclaringBaseClass(), e_.getName());
        setResultClass(new ClassArgumentMatching(c_));
        if (isIntermediateDottedOperation() && !fieldMetaInfo.isStaticField()) {
            Argument arg_ = getPreviousArgument();
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(static_);
            }
            getPreviousResultClass().setCheckOnlyNullPe(true);
        }
    }

    abstract ClassArgumentMatching getFrom(Analyzable _an);
    abstract String getFieldName(Analyzable _an);
    abstract boolean isBaseAccess(Analyzable _an);
    abstract boolean isSuperAccess(Analyzable _an);
    @Override
    public final boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public final void setCatenizeStrings() {
        catString = true;
    }

    @Override
    public final void setVariable(boolean _variable) {
        variable = _variable;
    }
    @Override
    public final void setStaticAccess(boolean _staticAccess) {
        staticAccess = _staticAccess;
    }
    @Override
    public final boolean isStaticAccess() {
        return staticAccess;
    }
    @Override
    final ArgumentCall getCommonArgument(Argument _previous, ExecutableCode _conf) {
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);

        Classes classes_ = _conf.getClasses();
        Argument arg_ = _previous;
        if (resultCanBeSet()) {
            return ArgumentCall.newArgument(arg_);
        }
        String className_ = fieldId.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            if (classes_.isCustomType(className_)) {
                InitClassState res_ = classes_.getLocks().getState(_conf.getContextEl(), className_);
                if (res_ == InitClassState.NOT_YET) {
                    InitializatingClass inv_ = new InitializatingClass(className_);
                    return ArgumentCall.newCall(inv_);
                }
                if (res_ == InitClassState.ERROR) {
                    CausingErrorStruct causing_ = new CausingErrorStruct(className_);
                    _conf.setException(causing_);
                    return ArgumentCall.newArgument(Argument.createVoid());
                }
                Struct struct_ = classes_.getStaticField(fieldId);
                a_ = new Argument();
                a_.setStruct(struct_);
                return ArgumentCall.newArgument(a_);
            }
            ResultErrorStd res_ = LgNames.getField(_conf.getContextEl(), fieldId, NullStruct.NULL_VALUE);
            a_ = new Argument();
            if (res_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
            } else {
                a_.setStruct(res_.getResult());
            }
            return ArgumentCall.newArgument(a_);
        }
        String argClassName_ = arg_.getObjectClassName(_conf.getContextEl());
        String classNameFound_ = fieldId.getClassName();
        String base_ = StringList.getAllTypes(argClassName_).first();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
            return ArgumentCall.newArgument(arg_);
        }
        if (arg_.getStruct() instanceof FieldableStruct) {
            Struct struct_ = ((FieldableStruct) arg_.getStruct()).getStruct(fieldId);
            a_ = new Argument();
            a_.setStruct(struct_);
            return ArgumentCall.newArgument(a_);
        }
        Struct default_ = arg_.getStruct();
        ResultErrorStd res_ = LgNames.getField(_conf.getContextEl(), fieldId, default_);
        a_ = new Argument();
        if (res_.getError() != null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
        } else {
            a_.setStruct(res_.getResult());
        }
        return ArgumentCall.newArgument(a_);
    }

    @Override
    public final Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = _nodes.getVal(this).getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        Argument current_ = _nodes.getVal(this).getArgument();
        Struct store_;
        if (current_ != null) {
            store_ = current_.getStruct();
        } else {
            store_ = NullStruct.NULL_VALUE;
        }
        Argument arg_ = getCommonSetting(previous_, store_, _conf, _op, _post);
        if (_conf.getException() == null) {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }

    @Override
    public final void calculateSetting(ExecutableCode _conf, String _op, boolean _post) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument current_ = getArgument();
        Struct store_;
        if (current_ != null) {
            store_ = current_.getStruct();
        } else {
            store_ = NullStruct.NULL_VALUE;
        }
        Argument arg_ = getCommonSetting(previous_, store_, _conf, _op, _post);
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }
    final Argument getCommonSetting(Argument _previous, Struct _store, ExecutableCode _conf, String _op, boolean _post) {
        PageEl ip_ = _conf.getOperationPageEl();
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument right_ = ip_.getRightArgument();
        Argument left_ = new Argument();
        Argument res_;

        String fieldType_;
        Classes classes_ = _conf.getClasses();
        Struct structField_ = null;
        String className_ = fieldId.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            fieldType_ = fieldMetaInfo.getRealType();
            if (resultCanBeSet()) {
                if (classes_.isCustomType(className_)) {
                    structField_ = classes_.getStaticField(fieldId);
                } else {
                    ResultErrorStd result_ = LgNames.getField(_conf.getContextEl(), fieldId, NullStruct.NULL_VALUE);
                    if (result_.getError() != null) {
                        _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
                        return Argument.createVoid();
                    }
                    structField_ = result_.getResult();
                }
                Struct check_ = structField_;
                if (getParent() instanceof AffectationOperation || _conf.isCheckAffectation()) {
                    check_ = right_.getStruct();
                }
                if (!check_.isNull() && !NumericOperation.convert(_op)) {
                    Mapping map_ = new Mapping();
                    String rightClass_ = stds_.getStructClassName(check_, _conf.getContextEl());
                    map_.setArg(rightClass_);
                    map_.setParam(fieldType_);
                    if (!Templates.isCorrect(map_, _conf)) {
                        _conf.setException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
                        return Argument.createVoid();
                    }
                }
                left_.setStruct(structField_);                
            } else {
                left_.setStruct(_store);
            }
            ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString, cl_);
            if (_conf.getException() != null) {
                return res_;
            }
            if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
                res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
            }
            if (classes_.isCustomType(className_)) {
                classes_.initializeStaticField(fieldId, res_.getStruct());
                Argument a_ = res_;
                if (_post) {
                    return left_;
                }
                return a_;
            }
            ResultErrorStd result_;
            result_ = LgNames.setField(_conf.getContextEl(), fieldId, NullStruct.NULL_VALUE, res_.getStruct());
            if (result_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
                return res_;
            }
            Argument a_ = res_;
            if (_post) {
                return left_;
            }
            return a_;
        }
        if (resultCanBeSet()) {
            String argClassName_ = _previous.getObjectClassName(_conf.getContextEl());
            String classNameFound_ = fieldId.getClassName();
            classNameFound_ = StringList.getAllTypes(classNameFound_).first();
            classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
            fieldType_ = fieldMetaInfo.getRealType();
            fieldType_ = Templates.format(classNameFound_, fieldType_, _conf);
            String base_ = StringList.getAllTypes(argClassName_).first();
            classNameFound_ = fieldId.getClassName();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                return Argument.createVoid();
            }
            if (_previous.getStruct() instanceof FieldableStruct) {
                structField_ = ((FieldableStruct) _previous.getStruct()).getStruct(fieldId);
                Struct check_ = structField_;
                if (getParent() instanceof AffectationOperation || _conf.isCheckAffectation()) {
                    check_ = right_.getStruct();
                }
                if (!check_.isNull() && !NumericOperation.convert(_op)) {
                    Mapping map_ = new Mapping();
                    String rightClass_ = stds_.getStructClassName(check_, _conf.getContextEl());
                    map_.setArg(rightClass_);
                    map_.setParam(fieldType_);
                    if (!Templates.isCorrect(map_, _conf)) {
                        _conf.setException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
                        return Argument.createVoid();
                    }
                }
                left_.setStruct(structField_);
            } else {
                ResultErrorStd result_ = LgNames.getField(_conf.getContextEl(), fieldId, _previous.getStruct());
                if (result_.getError() != null) {
                    _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
                    return Argument.createVoid();
                }
                structField_ = result_.getResult();
                left_.setStruct(structField_);
            }
        } else {
            left_.setStruct(_store);
            fieldType_ = _store.getClassName(_conf);
        }
        ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString, cl_);
        if (_conf.getException() != null) {
            return res_;
        }
        if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
            res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
        }
        if (_previous.getStruct() instanceof FieldableStruct) {
            ((FieldableStruct) _previous.getStruct()).setStruct(fieldId, res_.getStruct());
            if (_post) {
                return left_;
            }
            Argument a_ = res_;
            return a_;
        }
        ResultErrorStd result_;
        result_ = LgNames.setField(_conf.getContextEl(), fieldId, _previous.getStruct(), res_.getStruct());
        if (result_.getError() != null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
            return res_;
        }
        Argument a_ = res_;
        if (_post) {
            return left_;
        }
        return a_;
    }
    public final ClassField getFieldId() {
        return fieldId;
    }
    public final FieldInfo getFieldMetaInfo() {
        return fieldMetaInfo;
    }

    @Override
    public final void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
        if (fieldId != null && fieldMetaInfo.isStaticField()) {
            int index_ = _list.indexOfObj(new SortedClassField(fieldId));
            if (index_ < 0) {
                ResultErrorStd res_ = _conf.getStandards().getSimpleResult(_conf, fieldId);
                if (res_.getResult() != null) {
                    Argument arg_ = Argument.createVoid();
                    arg_.setStruct(res_.getResult());
                    setSimpleArgumentAna(arg_,_conf);
                }
                return;
            }
            SortedClassField found_ = _list.get(index_);
            if (found_.isOk()) {
                Argument arg_ = Argument.createVoid();
                arg_.setStruct(found_.getStruct());
                setSimpleArgumentAna(arg_,_conf);
            }
        }
    }

    @Override
    public final void tryCalculateNode(Analyzable _conf) {
        if (isCalculated()) {
            return;
        }
        if (fieldId != null && fieldMetaInfo.isStaticField()) {
            Classes cl_ = _conf.getClasses();
            if (!cl_.isCustomType(fieldId.getClassName())) {
                ResultErrorStd res_ = _conf.getStandards().getSimpleResult(_conf, fieldId);
                if (res_.getResult() != null) {
                    Argument arg_ = Argument.createVoid();
                    arg_.setStruct(res_.getResult());
                    setSimpleArgumentAna(arg_,_conf);
                }
                return;
            }
            Struct str_ = cl_.getStaticField(fieldId);
            if (str_ != null) {
                Argument arg_ = Argument.createVoid();
                arg_.setStruct(str_);
                setSimpleArgumentAna(arg_,_conf);
            }
        }
    }
    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        Argument arg_ = getArgument();
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        ObjectMap<ClassField,AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,Assignment> assA_ = new ObjectMap<ClassField,Assignment>();
        if (arg_ != null) {
            Object obj_ = arg_.getObject();
            if (obj_ instanceof Boolean) {
                //boolean constant assignment
                for (StringMap<AssignmentBefore> s: assB_) {
                    StringMap<Assignment> sm_ = new StringMap<Assignment>();
                    for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                        AssignmentBefore bf_ = e.getValue();
                        BooleanAssignment b_ = new BooleanAssignment();
                        if ((Boolean)obj_) {
                            b_.setAssignedAfterWhenFalse(true);
                            b_.setUnassignedAfterWhenFalse(true);
                            b_.setAssignedAfterWhenTrue(bf_.isAssignedBefore());
                            b_.setUnassignedAfterWhenTrue(bf_.isUnassignedBefore());
                        } else {
                            b_.setAssignedAfterWhenTrue(true);
                            b_.setUnassignedAfterWhenTrue(true);
                            b_.setAssignedAfterWhenFalse(bf_.isAssignedBefore());
                            b_.setUnassignedAfterWhenFalse(bf_.isUnassignedBefore());
                        }
                        sm_.put(e.getKey(), b_);
                    }
                    ass_.add(sm_);
                }
                for (EntryCust<ClassField, AssignmentBefore> e: assF_.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    BooleanAssignment b_ = new BooleanAssignment();
                    if ((Boolean)obj_) {
                        b_.setAssignedAfterWhenFalse(true);
                        b_.setUnassignedAfterWhenFalse(true);
                        b_.setAssignedAfterWhenTrue(bf_.isAssignedBefore());
                        b_.setUnassignedAfterWhenTrue(bf_.isUnassignedBefore());
                    } else {
                        b_.setAssignedAfterWhenTrue(true);
                        b_.setUnassignedAfterWhenTrue(true);
                        b_.setAssignedAfterWhenFalse(bf_.isAssignedBefore());
                        b_.setUnassignedAfterWhenFalse(bf_.isUnassignedBefore());
                    }
                    assA_.put(e.getKey(), b_);
                }
            } else {
                //simple assignment
                for (StringMap<AssignmentBefore> s: assB_) {
                    StringMap<Assignment> sm_ = new StringMap<Assignment>();
                    for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                        AssignmentBefore bf_ = e.getValue();
                        sm_.put(e.getKey(), bf_.assignAfter(false));
                    }
                    ass_.add(sm_);
                }
                for (EntryCust<ClassField, AssignmentBefore> e: assF_.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    assA_.put(e.getKey(), bf_.assignAfter(false));
                }
            }
        } else {
            LgNames lgNames_ = _conf.getStandards();
            String aliasBoolean_ = lgNames_.getAliasBoolean();
            boolean isBool_;
            isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
            for (StringMap<AssignmentBefore> s: assB_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    sm_.put(e.getKey(), bf_.assignAfter(isBool_));
                }
                ass_.add(sm_);
            }
            boolean procField_ = false;
            ClassField cl_ = getFieldId();
            if (cl_ != null) {
                if (isFirstChild()) {
                    procField_ = true;
                } else {
                    int index_ = getIndexChild() - 1;
                    OperationNode opPr_ = getParent().getChildrenNodes().get(index_);
                    if (opPr_ instanceof ThisOperation) {
                        if (StringList.quickEq(opPr_.getResultClass().getName(), _conf.getGlobalClass())) {
                            procField_ = true;
                        }
                    }
                    if (!procField_) {
                        if (opPr_ instanceof StaticAccessOperation) {
                            if (StringList.quickEq(opPr_.getResultClass().getName(), _conf.getGlobalClass())) {
                                procField_ = true;
                            }
                        }
                    }
                }
            }
            if (getParent() instanceof AffectationOperation && getParent().getFirstChild() == this) {
                procField_ = false;
            }
            for (EntryCust<ClassField, AssignmentBefore> e: assF_.entryList()) {
                if (procField_ && e.getKey().eq(cl_) && !e.getValue().isAssignedBefore()) {
                    ClassMetaInfo meta_ = _conf.getClassMetaInfo(cl_.getClassName());
                    if (meta_.getFieldsInfos().getVal(cl_.getFieldName()).isFinalField()) {
                        //error if final field
                        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(un_);
                    }
                }
                AssignmentBefore bf_ = e.getValue();
                assA_.put(e.getKey(), bf_.assignAfter(isBool_));
            }
        }
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }
}
