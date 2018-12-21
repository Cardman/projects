package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.errors.custom.UndefinedFieldError;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
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
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.text.ElUtil;
import code.expressionlanguage.text.OperationsSequence;
import code.util.CustList;
import code.util.EntryCust;
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
