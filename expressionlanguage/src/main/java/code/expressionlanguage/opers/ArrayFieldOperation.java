package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.EmptyPartError;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.UndefinedFieldError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class ArrayFieldOperation extends AbstractFieldOperation {
    private static final String LENGTH = "length";

    public ArrayFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String argClName_;
        if (str_.isEmpty()) {
            EmptyPartError emptyPart_ = new EmptyPartError();
            emptyPart_.setFileName(_conf.getCurrentFileName());
            emptyPart_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(emptyPart_);
            argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        LgNames stds_ = _conf.getStandards();
        str_ = StringList.removeAllSpaces(str_);
        ClassArgumentMatching cl_;
        if (isIntermediateDottedOperation()) {
            cl_ = getPreviousResultClass();
        } else {
            cl_ = new ClassArgumentMatching(_conf.getGlobalClass());
        }
        if (cl_ == null || cl_.getName() == null) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(static_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (StringList.quickEq(str_, LENGTH)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
            return;
        }
        UndefinedFieldError und_ = new UndefinedFieldError();
        und_.setClassName(cl_.getName());
        und_.setFileName(str_);
        und_.setFileName(_conf.getCurrentFileName());
        und_.setRc(_conf.getCurrentLocation());
        _conf.getClasses().getErrorsDet().add(und_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
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
            for (EntryCust<ClassField, AssignmentBefore> e: assF_.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                assA_.put(e.getKey(), bf_.assignAfter(isBool_));
            }
        }
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }
    @Override
    ArgumentCall getCommonArgument(Argument _previous, ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument arg_ = _previous;
        a_ = new Argument();
        if (arg_.isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        } else {
            a_.setStruct(new IntStruct(LgNames.getLength(arg_.getObject())));
        }
        return ArgumentCall.newArgument(a_);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
        if (!isIntermediateDottedOperation()) {
            return;
        }
        Argument arg_ = getPreviousArgument();
        Argument a_ = new Argument();
        if (arg_.isNull()) {
            return;
        } else {
            a_.setStruct(new IntStruct(LgNames.getLength(arg_.getObject())));
        }
        setSimpleArgumentAna(a_,_conf);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        if (isCalculated()) {
            return;
        }
        Argument arg_ = getPreviousArgument();
        Argument a_ = new Argument();
        if (arg_ == null || arg_.isNull()) {
            return;
        } else {
            a_.setStruct(new IntStruct(LgNames.getLength(arg_.getObject())));
        }
        setSimpleArgumentAna(a_,_conf);
    }
}
