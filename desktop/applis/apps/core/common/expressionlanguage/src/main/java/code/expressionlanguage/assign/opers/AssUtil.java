package code.expressionlanguage.assign.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.blocks.AssFieldBlock;
import code.expressionlanguage.assign.blocks.AssForMutableIterativeLoop;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AssUtil {
    private AssUtil() {
    }
    public static CustList<AssOperationNode> getExecutableNodes(CustList<ExecOperationNode> _list) {
        if (_list == null||_list.isEmpty()) {
            return new CustList<AssOperationNode>();
        }
        CustList<AssOperationNode> out_ = new CustList<AssOperationNode>();
        ExecOperationNode root_ = _list.last();
        ExecOperationNode current_ = root_;
        AssOperationNode exp_ = AssOperationNode.createAssOperationNode(current_);
        while (current_ != null) {
            ExecOperationNode op_ = current_.getFirstChild();
            if (exp_ instanceof AssMethodOperation && op_ != null) {
                AssOperationNode loc_ = AssOperationNode.createAssOperationNode(op_);
                ((AssMethodOperation)exp_).appendChild(loc_);
                exp_ = loc_;
                current_ = op_;
                continue;
            }
            while (true) {
                setup(exp_);
                out_.add(exp_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    AssOperationNode loc_ = AssOperationNode.createAssOperationNode(op_);
                    AssMethodOperation par_ = exp_.getParent();
                    par_.appendChild(loc_);
                    exp_ = loc_;
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == null) {
                    current_ = null;
                    break;
                }
                AssMethodOperation par_ = exp_.getParent();
                if (op_ == root_) {
                    setup(par_);
                    out_.add(par_);
                    current_ = null;
                    break;
                }
                current_ = op_;
                exp_ = par_;
            }
        }
        return out_;
    }

    private static void setup(AssOperationNode exp_) {
        if (exp_ instanceof AssAffectationOperation) {
            ((AssAffectationOperation)exp_).setup();
        }
        if (exp_ instanceof AssSemiAffectationOperation) {
            ((AssSemiAffectationOperation)exp_).setup();
        }
        if (exp_ instanceof AssCompoundAffectationOperation) {
            ((AssCompoundAffectationOperation)exp_).setup();
        }
    }

    public static void getSortedDescNodes(AssignedVariablesBlock _a, AssOperationNode _root, AssBlock _b, ContextEl _context) {
        _b.defaultAssignmentBefore(_context, _a,_root);
        AssOperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                _b.defaultAssignmentAfter(_context,  _a,_root);
                break;
            }
            c_ = getAnalyzedNext(_a,_b,c_, _root, _context);
        }
    }

    private static AssOperationNode getAnalyzedNext(AssignedVariablesBlock _a, AssBlock _b,
                                                    AssOperationNode _current, AssOperationNode _root, ContextEl _context) {

        AssOperationNode next_ = _current.getFirstChild();
        if (next_ != null) {
            ((AssMethodOperation) _current).tryAnalyzeAssignmentBefore(_context,_b,_a, next_);
            return next_;
        }
        AssOperationNode current_ = _current;
        while (true) {
            current_.tryAnalyzeAssignmentAfter(_context,_b,_a);
            next_ = current_.getNextSibling();
            AssMethodOperation par_ = current_.getParent();
            if (par_ instanceof AssMultMethodOperation &&next_ != null) {
                ((AssMultMethodOperation)par_).tryAnalyzeAssignmentBeforeNextSibling(_context, _b,_a,next_, current_);
                return next_;
            }
            if (par_ == _root) {
                par_.tryAnalyzeAssignmentAfter(_context,_b,_a);
                return null;
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }
    public static boolean checkFinalField(ContextEl _conf, AssBlock _as,AssSettableFieldOperation _cst, StringMap<Assignment> _ass) {
        boolean fromCurClass_ = _cst.isFromCurrentClass(_conf);
        ClassField cl_ = _cst.getFieldId();
        String fieldName_ = cl_.getFieldName();
        if (stepForLoop(_conf,_as)) {
            return true;
        }
        StringMap<Boolean> ass_ = new StringMap<Boolean>();
        for (EntryCust<String,Assignment> e: _ass.entryList()) {
            ass_.addEntry(e.getKey(),e.getValue().isUnassignedAfter());
        }
        return checkFinalReadOnly(_conf, _as,_cst, ass_, fromCurClass_, cl_, fieldName_);
    }
    private static boolean checkFinalReadOnly(ContextEl _conf, AssBlock _as,AssSettableFieldOperation _cst, StringMap<Boolean> _ass, boolean _fromCurClass, ClassField _cl, String _fieldName) {
        boolean checkFinal_;
        if (_conf.isAssignedFields()) {
            checkFinal_ = true;
        } else if (_conf.isAssignedStaticFields()) {
            FieldInfo meta_ = ContextUtil.getFieldInfo(_conf,_cl);
            if (meta_.isStaticField()) {
                checkFinal_ = true;
            } else if (!_fromCurClass) {
                checkFinal_ = true;
            } else {
                if (isDeclaringField(_cst,_as)) {
                    checkFinal_ = false;
                } else {
                    checkFinal_ = false;
                    for (EntryCust<String, Boolean> e: _ass.entryList()) {
                        if (!StringList.quickEq(e.getKey(), _fieldName)) {
                            continue;
                        }
                        if (e.getValue()) {
                            continue;
                        }
                        checkFinal_ = true;
                    }
                }
            }
        } else if (!_fromCurClass) {
            checkFinal_ = true;
        } else {
            if (isDeclaringField(_cst,_as)) {
                checkFinal_ = false;
            } else {
                checkFinal_ = false;
                for (EntryCust<String, Boolean> e: _ass.entryList()) {
                    if (!StringList.quickEq(e.getKey(), _fieldName)) {
                        continue;
                    }
                    if (e.getValue()) {
                        continue;
                    }
                    checkFinal_ = true;
                }
            }
        }
        return checkFinal_;
    }

    public static boolean isDeclaringField(AssOperationNode _var, AssBlock _as) {
        if (!(_as instanceof AssFieldBlock)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }
    public static boolean checkFinalVar(ContextEl _conf, Assignment _ass, AssBlock _a) {
        if (!_ass.isUnassignedAfter()) {
            return true;
        }
        return stepForLoop(_conf,_a);
    }
    private static boolean stepForLoop(ContextEl _conf, AssBlock _as) {
        if (_as instanceof AssForMutableIterativeLoop) {
            return _conf.getAnalyzing().getForLoopPartState() == ForLoopPart.STEP;
        }
        return false;
    }
    public static void setAssignments(AssOperationNode _current, AssBlock _ass, AssignedVariablesBlock _a) {
        Argument arg_ = _current.getArgument();
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(_current);
        CustList<StringMap<AssignmentBefore>> assM_ = vars_.getMutableLoopBefore().getVal(_current);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(_current);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> assAfM_ = new CustList<StringMap<Assignment>>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();

        if (arg_ != null&&arg_.getStruct() instanceof BooleanStruct) {
            //boolean constant assignment
            for (StringMap<AssignmentBefore> s: assB_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    BooleanAssignment b_ = new BooleanAssignment();
                    if (BooleanStruct.isTrue(arg_.getStruct())) {
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
                    if (BooleanStruct.isTrue(arg_.getStruct())) {
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
                if (BooleanStruct.isTrue(arg_.getStruct())) {
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
            ass_.addAllElts(AssignmentsUtil.assignAfter(false,assB_));
            assAfM_.addAllElts(AssignmentsUtil.assignAfter(false,assM_));
            assA_.putAllMap(AssignmentsUtil.assignAfter(false,assF_));
        }
        vars_.getVariables().put(_current, ass_);
        vars_.getMutableLoop().put(_current, assAfM_);
        vars_.getFields().put(_current, assA_);
    }

    public static boolean isDeclaringLoopVariable(AssMutableLoopVariableOperation _var, ContextEl _an) {
        if (!isDeclaringLoopVariable(_an)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }

    static boolean isDeclaringLoopVariable(ContextEl _an) {
        return _an.getAnalyzing().getForLoopPartState() == ForLoopPart.INIT;
    }
    public static boolean isDeclaringVariable(AssOperationNode _var) {
        AssMethodOperation par_ = _var.getParent();
        if (par_ == null) {
            return true;
        }
        if (par_ instanceof AssDeclaringOperation) {
            return true;
        }
        if (par_ instanceof AssAffectationOperation) {
            if (par_.getParent() == null) {
                return _var == par_.getFirstChild();
            }
            if (par_.getParent() instanceof AssDeclaringOperation) {
                return _var == par_.getFirstChild();
            }
        }
        return false;
    }

    public static CustList<AssOperationNode> filterInvoking(CustList<AssOperationNode> _list) {
        CustList<AssOperationNode> out_ = new CustList<AssOperationNode>();
        for (AssOperationNode o: _list) {
            if (o instanceof AssStaticInitOperation) {
                continue;
            }
            out_.add(o);
        }
        return out_;
    }
}
