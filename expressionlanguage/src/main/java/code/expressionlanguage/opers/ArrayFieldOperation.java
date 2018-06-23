package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.UndefinedFieldError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.SortedClassField;
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
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching cl_ = getPreviousResultClass();
        if (StringList.quickEq(str_, LENGTH)) {
            Argument arg_ = getPreviousArgument();
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
            }
            cl_.setCheckOnlyNullPe(true);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
            return;
        }
        UndefinedFieldError und_ = new UndefinedFieldError();
        und_.setClassName(cl_.getName());
        und_.setFileName(str_);
        und_.setFileName(_conf.getCurrentFileName());
        und_.setRc(_conf.getCurrentLocation());
        _conf.getClasses().addError(und_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
    }
    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        ObjectMap<ClassField,AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,Assignment> assA_ = new ObjectMap<ClassField,Assignment>();
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
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }
    @Override
    Argument getCommonArgument(Argument _previous, ExecutableCode _conf) {
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument arg_ = _previous;
        a_ = new Argument();
        a_.setStruct(new IntStruct(LgNames.getLength(arg_.getObject())));
        return a_;
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
