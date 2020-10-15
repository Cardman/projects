package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.blocks.AssForMutableIterativeLoop;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AssUtil {
    private AssUtil() {
    }

    public static CustList<AssOperationNode> getExecutableNodes(OperationNode _root) {
        CustList<AssOperationNode> out_ = new CustList<AssOperationNode>();
        if (_root == null) {
            return out_;
        }
        OperationNode current_ = _root;
        AssOperationNode exp_ = AssOperationNode.createAssOperationNode(current_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
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
                if (op_ == _root) {
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

    public static CustList<AssOperationNode> getSimExecutableNodes(OperationNode _root) {
        CustList<AssOperationNode> out_ = new CustList<AssOperationNode>();
        if (_root == null) {
            return out_;
        }
        OperationNode current_ = _root;
        AssOperationNode exp_ = AssOperationNode.createAssSimOperationNode(current_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (exp_ instanceof AssMethodOperation && op_ != null) {
                AssOperationNode loc_ = AssOperationNode.createAssSimOperationNode(op_);
                ((AssMethodOperation)exp_).appendChild(loc_);
                exp_ = loc_;
                current_ = op_;
                continue;
            }
            while (true) {
                setupSim(exp_);
                out_.add(exp_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    AssOperationNode loc_ = AssOperationNode.createAssSimOperationNode(op_);
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
                if (op_ == _root) {
                    setupSim(par_);
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
    private static void setup(AssOperationNode _exp) {
        if (_exp instanceof AssAffectationOperation) {
            ((AssAffectationOperation)_exp).setup();
        }
        if (_exp instanceof AssSemiAffectationOperation) {
            ((AssSemiAffectationOperation)_exp).setup();
        }
        if (_exp instanceof AssCompoundAffectationOperation) {
            ((AssCompoundAffectationOperation)_exp).setup();
        }
    }
    private static void setupSim(AssOperationNode _exp) {
        if (_exp instanceof AssSimAffectationOperation) {
            ((AssSimAffectationOperation)_exp).setup();
        }
        if (_exp instanceof AssSimReadWriteAffectationOperation) {
            ((AssSimReadWriteAffectationOperation)_exp).setup();
        }
    }

    public static void getSortedDescNodes(AssignedVariablesBlock _a, AssOperationNode _root, AssBlock _b, AnalyzedPageEl _page) {
        getSortedDescNodes(_a,_root,_b, false, _page);
    }
    public static void getSortedDescNodes(AssignedVariablesBlock _a, AssOperationNode _root, AssBlock _b, boolean _callingThis, AnalyzedPageEl _page) {
        _b.defaultAssignmentBefore(_a,_root, _page);
        AssOperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                _b.defaultAssignmentAfter(_a,_callingThis, _page);
                break;
            }
            c_ = getAnalyzedNext(_a,_b,c_, _root, _page);
        }
    }
    public static void getSimSortedDescNodes(AssignedVariablesBlock _a, AssOperationNode _root, AssBlock _b, AnalyzedPageEl _page) {
        AssOperationNode c_ = _root;
        while (c_ != null) {
            c_ = getSimAnalyzedNext(_a, _b, c_, _root, _page);
        }
    }

    private static AssOperationNode getAnalyzedNext(AssignedVariablesBlock _a, AssBlock _b,
                                                    AssOperationNode _current, AssOperationNode _root, AnalyzedPageEl _page) {

        AssOperationNode next_ = _current.getFirstChild();
        if (next_ != null) {
            ((AssMethodOperation) _current).tryAnalyzeAssignmentBefore(_b,_a, next_);
            return next_;
        }
        AssOperationNode current_ = _current;
        while (true) {
            current_.tryAnalyzeAssignmentAfter(_b,_a, _page);
            next_ = current_.getNextSibling();
            AssMethodOperation par_ = current_.getParent();
            if (par_ instanceof AssMultMethodOperation &&next_ != null) {
                ((AssMultMethodOperation)par_).tryAnalyzeAssignmentBeforeNextSibling(_b,_a,next_, current_);
                return next_;
            }
            if (par_ == _root) {
                par_.tryAnalyzeAssignmentAfter(_b,_a, _page);
                return null;
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }

    private static AssOperationNode getSimAnalyzedNext(AssignedVariablesBlock _a, AssBlock _b,
                                                       AssOperationNode _current, AssOperationNode _root, AnalyzedPageEl _page) {

        AssOperationNode next_ = _current.getFirstChild();
        if (next_ != null) {
            return next_;
        }
        AssOperationNode current_ = _current;
        while (true) {
            current_.tryAnalyzeAssignmentAfter(_b,_a, _page);
            next_ = current_.getNextSibling();
            AssMethodOperation par_ = current_.getParent();
            if (next_ != null) {
                return next_;
            }
            if (par_ == _root) {
                par_.tryAnalyzeAssignmentAfter(_b,_a, _page);
                return null;
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }
    public static boolean checkFinalField(AssBlock _as, AssSettableFieldOperation _cst, StringMap<Assignment> _ass, AnalyzedPageEl _page) {
        boolean fromCurClass_ = _cst.isFromCurrentClass(_page);
        ClassField cl_ = _cst.getFieldId();
        String fieldName_ = cl_.getFieldName();
        if (stepForLoop(_as, _page)) {
            return true;
        }
        StringMap<Boolean> ass_ = new StringMap<Boolean>();
        for (EntryCust<String,Assignment> e: _ass.entryList()) {
            ass_.addEntry(e.getKey(),e.getValue().isUnassignedAfter());
        }
        return checkFinalReadOnly(_cst, ass_, fromCurClass_, fieldName_, ContextUtil.getFieldInfo(cl_, _page), _page);
    }
    private static boolean checkFinalReadOnly(AssSettableFieldOperation _cst, StringMap<Boolean> _ass, boolean _fromCurClass, String _fieldName, FieldInfo _meta, AnalyzedPageEl _page) {
        boolean checkFinal_;
        if (_page.isAssignedFields()) {
            checkFinal_ = true;
        } else if (_page.isAssignedStaticFields()) {
            if (_meta.isStaticField()) {
                checkFinal_ = true;
            } else if (!_fromCurClass) {
                checkFinal_ = true;
            } else {
                if (_cst.isDeclare()) {
                    checkFinal_ = false;
                } else {
                    checkFinal_ = false;
                    for (EntryCust<String, Boolean> e: _ass.entryList()) {
                        if (!StringUtil.quickEq(e.getKey(), _fieldName)) {
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
            if (_cst.isDeclare()) {
                checkFinal_ = false;
            } else {
                checkFinal_ = false;
                for (EntryCust<String, Boolean> e: _ass.entryList()) {
                    if (!StringUtil.quickEq(e.getKey(), _fieldName)) {
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

    public static boolean checkFinalVar(Assignment _ass, AssBlock _a, AnalyzedPageEl _page) {
        if (!_ass.isUnassignedAfter()) {
            return true;
        }
        return stepForLoop(_a, _page);
    }
    private static boolean stepForLoop(AssBlock _as, AnalyzedPageEl _page) {
        if (_as instanceof AssForMutableIterativeLoop) {
            return _page.getForLoopPartState() == ForLoopPart.STEP;
        }
        return false;
    }
    public static void setAssignments(AssOperationNode _current, AssBlock _ass, AssignedVariablesBlock _a) {
        Argument arg_ = _current.getArgument();
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<AssignmentBefore> assB_ = vars_.getVariablesBefore().getVal(_current);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(_current);
        StringMap<Assignment> ass_ = new StringMap<Assignment>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        Argument value_ = Argument.getNullableValue(arg_);
        if (value_.getStruct() instanceof BooleanStruct) {
            //boolean constant assignment
            for (EntryCust<String, AssignmentBefore> e: assB_.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                BooleanAssignment b_ = new BooleanAssignment();
                if (BooleanStruct.isTrue(value_.getStruct())) {
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
                ass_.put(e.getKey(), b_);
            }
            for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                BooleanAssignment b_ = new BooleanAssignment();
                if (BooleanStruct.isTrue(value_.getStruct())) {
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
            ass_.putAllMap(AssignmentsUtil.assignAfter(false,assB_));
            assA_.putAllMap(AssignmentsUtil.assignAfter(false,assF_));
        }
        vars_.getVariables().put(_current, ass_);
        vars_.getFields().put(_current, assA_);
    }

}
