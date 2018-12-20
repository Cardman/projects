package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.errors.custom.UndefinedFieldError;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class SettableAbstractFieldOperation extends
        AbstractFieldOperation implements SettableElResult {

    private boolean variable;
    private FieldInfo fieldMetaInfo;
    private boolean staticAccess;

    private boolean catString;

    private int anc;

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
        boolean import_ = false;
        if (!isIntermediateDottedOperation()) {
            import_ = true;
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
        boolean affect_ = false;
        if (getParent() instanceof DotOperation && isIntermediateDottedOperation()) {
            if (getParent().getParent() instanceof AffectationOperation && getParent().getParent().getFirstChild() == getParent()) {
                affect_ = true;
            } else if (getParent().getParent() instanceof CompoundAffectationOperation && getParent().getParent().getFirstChild() == getParent()) {
                affect_ = true;
            } else if (getParent().getParent() instanceof SemiAffectationOperation) {
                affect_ = true;
            }
        } else if (getParent() instanceof AffectationOperation && getParent().getFirstChild() == this) {
            affect_ = true;
        } else if (getParent() instanceof CompoundAffectationOperation && getParent().getFirstChild() == this) {
            affect_ = true;
        } else if (getParent() instanceof SemiAffectationOperation) {
            affect_ = true;
        }
        FieldResult r_;
        FieldInfo e_;
        r_ = getDeclaredCustField(_conf, isStaticAccess(), cl_, baseAccess_, superAccess_, fieldName_, import_, affect_);
        anc = r_.getAnc();
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            UndefinedFieldError und_ = new UndefinedFieldError();
            for (String c: cl_.getNames()) {
                String base_ = Templates.getIdFromAllTypes(c);
                und_.setClassName(base_);
                und_.setId(fieldName_);
                und_.setFileName(_conf.getCurrentFileName());
                und_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(und_);
            }
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        e_ = r_.getId();
        fieldMetaInfo = e_;
        String c_ = fieldMetaInfo.getType();
        setResultClass(new ClassArgumentMatching(c_));
        if (isIntermediateDottedOperation() && !fieldMetaInfo.isStaticField()) {
            Argument arg_ = getPreviousArgument();
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(static_);
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
    final Argument getCommonArgument(Argument _previous, ExecutableCode _conf) {
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        off_ += getIndexInEl()+getOperations().getDelimiter().getIndexBegin();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        boolean staticField_ = fieldMetaInfo.isStaticField();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        Argument previous_ = new Argument();
        if (!staticField_) {
            previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        }
        if (_conf.getContextEl().hasException()) {
            return Argument.createVoid();
        }
        return InvokingOperation.getField(className_, fieldName_, staticField_, previous_, _conf, off_);
    }
    
    public final ClassField getFieldId() {
        if (fieldMetaInfo == null) {
            return null;
        }
        return fieldMetaInfo.getClassField();
    }

    public final boolean matchFieldId(ClassField _id) {
        if (fieldMetaInfo == null) {
            return false;
        }
        return fieldMetaInfo.getClassField().eq(_id);
    }
    public final boolean isFromCurrentClass(Analyzable _an) {
        if (fieldMetaInfo == null) {
            return false;
        }
        ClassField clField_ = fieldMetaInfo.getClassField();
        String gl_ = _an.getGlobalClass();
        if (gl_ == null) {
            return false;
        }
        String id_ = Templates.getIdFromAllTypes(_an.getGlobalClass());
        if (isFirstChild()) {
            return StringList.quickEq(clField_.getClassName(), id_);
        }
        MethodOperation par_ = getParent();
        if (!(par_ instanceof DotOperation)) {
            return false;
        }
        if (par_.getFirstChild() instanceof ThisOperation) {
            return StringList.quickEq(clField_.getClassName(), id_);
        }
        if (par_.getFirstChild() instanceof StaticAccessOperation) {
            return StringList.quickEq(clField_.getClassName(), id_);
        }
        return false;
    }
    public final FieldInfo getFieldMetaInfo() {
        return fieldMetaInfo;
    }

    @Override
    public final void tryCalculateNode(Analyzable _conf) {
        if (fieldMetaInfo == null) {
            return;
        }
        if (!fieldMetaInfo.isStaticField()) {
            return;
        }
        Classes cl_ = _conf.getClasses();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        if (!cl_.isCustomType(fieldId_.getClassName())) {
            ResultErrorStd res_ = _conf.getStandards().getSimpleResult(_conf, fieldId_);
            if (res_.getResult() != null) {
                Argument arg_ = Argument.createVoid();
                arg_.setStruct(res_.getResult());
                setSimpleArgumentAna(arg_,_conf);
            }
            return;
        }
        if (_conf.isGearConst() && ElUtil.isDeclaringField(this, _conf) && fieldMetaInfo.isFinalField()) {
            Argument arg_ = Argument.createVoid();
            setSimpleArgument(arg_);
            return;
        }
        Struct str_ = cl_.getStaticField(fieldId_);
        if (str_ != null) {
            Argument arg_ = Argument.createVoid();
            arg_.setStruct(str_);
            setSimpleArgumentAna(arg_,_conf);
        }
    }
    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        Argument arg_ = getArgument();
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        CustList<StringMap<AssignmentBefore>> assM_ = vars_.getMutableLoopBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> assAfM_ = new CustList<StringMap<Assignment>>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        if (arg_ != null) {
            if (arg_.getStruct() instanceof BooleanStruct) {
                Boolean value_ = ((BooleanStruct)arg_.getStruct()).getInstance();
                //boolean constant assignment
                for (StringMap<AssignmentBefore> s: assB_) {
                    StringMap<Assignment> sm_ = new StringMap<Assignment>();
                    for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                        AssignmentBefore bf_ = e.getValue();
                        BooleanAssignment b_ = new BooleanAssignment();
                        if (value_) {
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
                for (StringMap<AssignmentBefore> s: assM_) {
                    StringMap<Assignment> sm_ = new StringMap<Assignment>();
                    for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                        AssignmentBefore bf_ = e.getValue();
                        BooleanAssignment b_ = new BooleanAssignment();
                        if (value_) {
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
                    assAfM_.add(sm_);
                }
                for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    BooleanAssignment b_ = new BooleanAssignment();
                    if (value_) {
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
                for (StringMap<AssignmentBefore> s: assM_) {
                    StringMap<Assignment> sm_ = new StringMap<Assignment>();
                    for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                        AssignmentBefore bf_ = e.getValue();
                        sm_.put(e.getKey(), bf_.assignAfter(false));
                    }
                    assAfM_.add(sm_);
                }
                for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    assA_.put(e.getKey(), bf_.assignAfter(false));
                }
            }
        } else {
            boolean isBool_;
            isBool_ = getResultClass().isBoolType(_conf);
            for (StringMap<AssignmentBefore> s: assB_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    sm_.put(e.getKey(), bf_.assignAfter(isBool_));
                }
                ass_.add(sm_);
            }
            for (StringMap<AssignmentBefore> s: assM_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    sm_.put(e.getKey(), bf_.assignAfter(isBool_));
                }
                assAfM_.add(sm_);
            }
            boolean procField_ = isFromCurrentClass(_conf);
            ClassField cl_ = getFieldId();
            MethodOperation par_ = getParent();
            if (par_ instanceof AffectationOperation && isFirstChild()) {
                procField_ = false;
            } else {
                if (par_ instanceof DotOperation) {
                    boolean cancelCheck_ = false;
                    if (par_.getFirstChild() instanceof ThisOperation) {
                        cancelCheck_ = true;
                    } else if (par_.getFirstChild() instanceof StaticAccessOperation) {
                        cancelCheck_ = true;
                    }
                    if (cancelCheck_) {
                        if (par_.getParent() instanceof AffectationOperation && par_.isFirstChild()) {
                            procField_ = false;
                        }
                    }
                }
            }
            if (cl_ == null) {
                procField_ = false;
            } else if (_conf.isAssignedStaticFields()) {
                FieldInfo meta_ = _conf.getFieldInfo(cl_);
                if (meta_.isStaticField()) {
                    procField_ = false;
                }
            }
            if (_conf.isAssignedFields()) {
                procField_ = false;
            }
            if (!procField_) {
                for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    assA_.put(e.getKey(), bf_.assignAfter(isBool_));
                }
            } else {
                for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                    if (StringList.quickEq(e.getKey(),cl_.getFieldName()) && !e.getValue().isAssignedBefore()) {
                        FieldInfo meta_ = _conf.getFieldInfo(cl_);
                        if (meta_.isFinalField() && !ElUtil.isDeclaringField(this, _conf)) {
                            //error if final field
                            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
                            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                            un_.setFileName(_conf.getCurrentFileName());
                            un_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.getClasses().addError(un_);
                        }
                    }
                    AssignmentBefore bf_ = e.getValue();
                    assA_.put(e.getKey(), bf_.assignAfter(isBool_));
                }
            }
        }
        vars_.getVariables().put(this, ass_);
        vars_.getMutableLoop().put(this, assAfM_);
        vars_.getFields().put(this, assA_);
    }
    @Override
    public final Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument arg_ = getCommonSetting(previous_, _conf, _right);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    @Override
    public final void calculateSetting(ExecutableCode _conf, Argument _right) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument arg_ = getCommonSetting(previous_, _conf, _right);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            arg_ = getCommonSetting(previous_, _conf, _right);
        }
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }
    @Override
    public final Argument calculateCompoundSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument current_ = ElUtil.getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        Argument arg_ = getCommonCompoundSetting(previous_, store_, _conf, _op, _right);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    @Override
    public final void calculateCompoundSetting(ExecutableCode _conf, String _op,
            Argument _right) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument current_ = getArgument();
        Struct store_ = current_.getStruct();
        Argument arg_ = getCommonCompoundSetting(previous_, store_, _conf, _op, _right);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }
    @Override
    public final Argument calculateSemiSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument current_ = ElUtil.getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        Argument arg_ = getCommonSemiSetting(previous_, store_, _conf, _op, _post);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    @Override
    public final void calculateSemiSetting(ExecutableCode _conf, String _op,
            boolean _post) {
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
        Argument arg_ = getCommonSemiSetting(previous_, store_, _conf, _op, _post);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
        
    }
    final Argument getCommonSetting(Argument _previous, ExecutableCode _conf, Argument _right) {
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        off_ += getIndexInEl()+getOperations().getDelimiter().getIndexBegin();
        String fieldType_ = fieldMetaInfo.getRealType();
        boolean isStatic_ = fieldMetaInfo.isStaticField();
        boolean isFinal_ = fieldMetaInfo.isFinalField();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        Argument previous_ = new Argument();
        if (!isStatic_) {
            previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        }
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return Argument.createVoid();
        }
        //Come from code directly so constant static fields can be initialized here
        return InvokingOperation.setField(className_, fieldName_, isStatic_, isFinal_, false, fieldType_, previous_, _right, _conf, off_);
    }
    final Argument getCommonCompoundSetting(Argument _previous, Struct _store, ExecutableCode _conf, String _op, Argument _right) {
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument();
        Argument res_;

        String fieldType_;
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            fieldType_ = fieldMetaInfo.getRealType();
            left_.setStruct(_store);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
            res_ = NumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                return res_;
            }
            if (classes_.isCustomType(className_)) {
                if (_conf.getContextEl().isSensibleField(fieldId_.getClassName())) {
                    _conf.getContextEl().failInitEnums();
                    return _right;
                }
                classes_.initializeStaticField(fieldId_, res_.getStruct());
                Argument a_ = res_;
                return a_;
            }
            ResultErrorStd result_;
            result_ = LgNames.setField(_conf.getContextEl(), fieldId_, NullStruct.NULL_VALUE, res_.getStruct());
            if (result_.getError() != null) {
                _conf.setException(new ErrorStruct(_conf,result_.getError()));
                return res_;
            }
            Argument a_ = res_;
            return a_;
        }
        Argument previous_ = new Argument();
        previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        left_.setStruct(_store);
        fieldType_ = _conf.getStandards().getStructClassName(_store, _conf.getContextEl());
        ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
        res_ = NumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return res_;
        }
        if (previous_.getStruct() instanceof FieldableStruct) {
            if (_conf.getContextEl().isContainedSensibleFields(previous_.getStruct())) {
                _conf.getContextEl().failInitEnums();
                return _right;
            }
            ((FieldableStruct) previous_.getStruct()).setStruct(fieldId_, res_.getStruct());
            Argument a_ = res_;
            return a_;
        }
        ResultErrorStd result_;
        result_ = LgNames.setField(_conf.getContextEl(), fieldId_, previous_.getStruct(), res_.getStruct());
        if (result_.getError() != null) {
            _conf.setException(new ErrorStruct(_conf,result_.getError()));
            return res_;
        }
        Argument a_ = res_;
        return a_;
    }
    final Argument getCommonSemiSetting(Argument _previous, Struct _store, ExecutableCode _conf, String _op, boolean _post) {
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument();
        Argument res_;

        String fieldType_;
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            fieldType_ = fieldMetaInfo.getRealType();
            left_.setStruct(_store);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
            res_ = NumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                return res_;
            }
            if (classes_.isCustomType(className_)) {
                if (_conf.getContextEl().isSensibleField(fieldId_.getClassName())) {
                    _conf.getContextEl().failInitEnums();
                    return res_;
                }
                classes_.initializeStaticField(fieldId_, res_.getStruct());
                return SemiAffectationOperation.getPrePost(_post, left_, res_);
            }
            ResultErrorStd result_;
            result_ = LgNames.setField(_conf.getContextEl(), fieldId_, NullStruct.NULL_VALUE, res_.getStruct());
            if (result_.getError() != null) {
                _conf.setException(new ErrorStruct(_conf,result_.getError()));
                return res_;
            }
            return SemiAffectationOperation.getPrePost(_post, left_, res_);
        }
        Argument previous_ = new Argument();
        previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, _previous.getStruct(), _conf));
        left_.setStruct(_store);
        fieldType_ = _conf.getStandards().getStructClassName(_store, _conf.getContextEl());
        ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
        res_ = NumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return res_;
        }
        if (previous_.getStruct() instanceof FieldableStruct) {
            if (_conf.getContextEl().isContainedSensibleFields(previous_.getStruct())) {
                _conf.getContextEl().failInitEnums();
                return res_;
            }
            ((FieldableStruct) previous_.getStruct()).setStruct(fieldId_, res_.getStruct());
            return SemiAffectationOperation.getPrePost(_post, left_, res_);
        }
        ResultErrorStd result_;
        result_ = LgNames.setField(_conf.getContextEl(), fieldId_, previous_.getStruct(), res_.getStruct());
        if (result_.getError() != null) {
            _conf.setException(new ErrorStruct(_conf,result_.getError()));
            return res_;
        }
        return SemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<OperationNode, ArgumentsPair> _nodes, Argument _right) {
        return endCalculate(_conf, _nodes, false, null, _right);
    }
    @Override
    public Argument endCalculate(ExecutableCode _conf, Argument _right) {
        return endCalculate(_conf, false, null, _right);
    }
    @Override
    public Argument endCalculate(ContextEl _conf,
            IdMap<OperationNode, ArgumentsPair> _nodes, boolean _post,
            Argument _stored, Argument _right) {
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            if (classes_.isCustomType(className_)) {
                if (_conf.isSensibleField(fieldId_.getClassName())) {
                    _conf.failInitEnums();
                    return _right;
                }
                classes_.initializeStaticField(fieldId_, _right.getStruct());
                Argument a_ = SemiAffectationOperation.getPrePost(_post, _stored, _right);
                setSimpleArgument(a_, _conf, _nodes);
                return a_;
            }
            ResultErrorStd result_;
            result_ = LgNames.setField(_conf.getContextEl(), fieldId_, NullStruct.NULL_VALUE, _right.getStruct());
            if (result_.getError() != null) {
                _conf.setException(new ErrorStruct(_conf,result_.getError()));
                return _right;
            }
            Argument a_ = SemiAffectationOperation.getPrePost(_post, _stored, _right);
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Argument previousNode_ = getPreviousArg(this, _nodes, _conf);
        Argument previous_ = new Argument();
        previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, previousNode_.getStruct(), _conf));
        if (previous_.getStruct() instanceof FieldableStruct) {
            if (_conf.isContainedSensibleFields(previous_.getStruct())) {
                _conf.failInitEnums();
                return _right;
            }
            ((FieldableStruct) previous_.getStruct()).setStruct(fieldId_, _right.getStruct());
            Argument a_ = SemiAffectationOperation.getPrePost(_post, _stored, _right);
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        ResultErrorStd result_;
        result_ = LgNames.setField(_conf.getContextEl(), fieldId_, previous_.getStruct(), _right.getStruct());
        if (result_.getError() != null) {
            _conf.setException(new ErrorStruct(_conf,result_.getError()));
            return _right;
        }
        Argument a_ = SemiAffectationOperation.getPrePost(_post, _stored, _right);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public Argument endCalculate(ExecutableCode _conf, boolean _post,
            Argument _stored, Argument _right) {
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        String className_ = fieldId_.getClassName();
        if (fieldMetaInfo.isStaticField()) {
            if (classes_.isCustomType(className_)) {
                classes_.initializeStaticField(fieldId_, _right.getStruct());
                Argument a_ = SemiAffectationOperation.getPrePost(_post, _stored, _right);
                setSimpleArgument(a_, _conf);
                return a_;
            }
            ResultErrorStd result_;
            result_ = LgNames.setField(_conf.getContextEl(), fieldId_, NullStruct.NULL_VALUE, _right.getStruct());
            if (result_.getError() != null) {
                _conf.setException(new ErrorStruct(_conf,result_.getError()));
                return _right;
            }
            Argument a_ = SemiAffectationOperation.getPrePost(_post, _stored, _right);
            setSimpleArgument(a_, _conf);
            return a_;
        }
        Argument previousNode_;
        if (isIntermediateDottedOperation()) {
            previousNode_ = getPreviousArgument();
        } else {
            previousNode_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument previous_ = new Argument();
        previous_.setStruct(PrimitiveTypeUtil.getParent(anc, className_, previousNode_.getStruct(), _conf));
        if (previous_.getStruct() instanceof FieldableStruct) {
            ((FieldableStruct) previous_.getStruct()).setStruct(fieldId_, _right.getStruct());
            Argument a_ = SemiAffectationOperation.getPrePost(_post, _stored, _right);
            setSimpleArgument(a_, _conf);
            return a_;
        }
        ResultErrorStd result_;
        result_ = LgNames.setField(_conf.getContextEl(), fieldId_, previous_.getStruct(), _right.getStruct());
        if (result_.getError() != null) {
            _conf.setException(new ErrorStruct(_conf,result_.getError()));
            return _right;
        }
        Argument a_ = SemiAffectationOperation.getPrePost(_post, _stored, _right);
        setSimpleArgument(a_, _conf);
        return a_;
    }
    public boolean isVariable() {
        return variable;
    }
    public boolean isCatString() {
        return catString;
    }
    public int getAnc() {
        return anc;
    }
    
}
