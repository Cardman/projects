package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.UndefinedFieldError;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ReductibleOperable;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public abstract class SettableAbstractFieldOperation extends
        AbstractFieldOperation implements SettableElResult, ReductibleOperable {

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
        String fieldName_ = getFieldName();
        boolean baseAccess_ = isBaseAccess();
        boolean superAccess_ = isSuperAccess();
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
            checkNull(arg_,_conf);
        }
    }

    abstract ClassArgumentMatching getFrom(Analyzable _an);
    abstract String getFieldName();
    abstract boolean isBaseAccess();
    abstract boolean isSuperAccess();
    public abstract int getDelta();

    @Override
    public final void setCatenizeStrings() {
        catString = true;
    }

    @Override
    public final void setVariable(boolean _variable) {
        variable = _variable;
    }

    public final void setStaticAccess(boolean _staticAccess) {
        staticAccess = _staticAccess;
    }

    public final boolean isStaticAccess() {
        return staticAccess;
    }
    @Override
    public void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        setPreviousResultClass(_previousResultClass);
        setStaticAccess(_staticAccess);
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
        String id_ = Templates.getIdFromAllTypes(gl_);
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
        trySet(_conf, this, fieldMetaInfo);
    }
    public static void trySet(Analyzable _conf, Operable _oper, FieldInfo _info) {
        if (_info == null) {
            return;
        }
        if (!_info.isStaticField()) {
            return;
        }
        if (!_info.isFinalField()) {
            return;
        }
        Classes cl_ = _conf.getClasses();
        ClassField fieldId_ = _info.getClassField();
        if (!cl_.isCustomType(fieldId_.getClassName())) {
            ResultErrorStd res_ = _conf.getStandards().getSimpleResult(_conf, fieldId_);
            Argument arg_ = Argument.createVoid();
            arg_.setStruct(res_.getResult());
            _oper.setSimpleArgumentAna(arg_,_conf);
            return;
        }
        if (ElUtil.isInlineDeclaringField(_oper, _conf)) {
            Argument arg_ = Argument.createVoid();
            _oper.setSimpleArgument(arg_);
            return;
        }
        Struct str_ = cl_.getStaticField(fieldId_);
        if (str_ != null) {
            Argument arg_ = Argument.createVoid();
            arg_.setStruct(str_);
            _oper.setSimpleArgumentAna(arg_,_conf);
        }
    }
    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        Argument arg_ = getArgument();
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getContextEl().getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        CustList<StringMap<AssignmentBefore>> assM_ = vars_.getMutableLoopBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> assAfM_ = new CustList<StringMap<Assignment>>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        if (arg_ != null) {
            ConstantOperation.setAssignments(this,_conf);
            return;
        }
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        ass_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assB_));
        assAfM_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assM_));
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
        } else if (_conf.getContextEl().isAssignedStaticFields()) {
            FieldInfo meta_ = _conf.getFieldInfo(cl_);
            if (meta_.isStaticField()) {
                procField_ = false;
            }
        }
        if (_conf.getContextEl().isAssignedFields()) {
            procField_ = false;
        }
        if (procField_) {
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
            }
            if (getParent() == null) {
                assA_.put(cl_.getFieldName(),Assignment.assign(isBool_,false,true));
            }
        }
        assA_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assF_));
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
