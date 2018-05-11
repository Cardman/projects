package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.EmptyPartError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringMap;

public final class EmptyPartOperation extends AbstractFieldOperation {

    public EmptyPartOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    ArgumentCall getCommonArgument(Argument _previous, ContextEl _conf) {
        return null;
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        String argClName_;
        EmptyPartError emptyPart_ = new EmptyPartError();
        emptyPart_.setFileName(_conf.getCurrentFileName());
        emptyPart_.setRc(_conf.getCurrentLocation());
        _conf.getClasses().getErrorsDet().add(emptyPart_);
        argClName_ = _conf.getStandards().getAliasObject();
        setResultClass(new ClassArgumentMatching(argClName_));    
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
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
    public void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {    
    }

}
