package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.blocks.AssForMutableIterativeLoop;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class AssUtil {
    private AssUtil() {
    }

    public static CustList<AssOperationNode> getExecutableNodes(OperationNode _root) {
        if (_root == null) {
            return  new CustList<AssOperationNode>();
        }
        return getExecutableNodesNotNull(_root);
    }

    private static CustList<AssOperationNode> getExecutableNodesNotNull(OperationNode _root) {
        CustList<AssOperationNode> out_ = new CustList<AssOperationNode>();
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
            while (current_ != null) {
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
                } else if (op_ == _root) {
                    AssMethodOperation par_ = exp_.getParent();
                    setup(par_);
                    out_.add(par_);
                    current_ = null;
                } else {
                    current_ = op_;
                    exp_ = exp_.getParent();
                }
            }
        }
        return out_;
    }

    public static CustList<AssOperationNode> getSimExecutableNodes(OperationNode _root) {
        if (_root == null) {
            return new CustList<AssOperationNode>();
        }
        return getSimExecutableNodesNotNull(_root);
    }

    private static CustList<AssOperationNode> getSimExecutableNodesNotNull(OperationNode _root) {
        CustList<AssOperationNode> out_ = new CustList<AssOperationNode>();
        OperationNode currentSim_ = _root;
        AssOperationNode exp_ = AssOperationNode.createAssSimOperationNode(currentSim_);
        while (currentSim_ != null) {
            OperationNode op_ = currentSim_.getFirstChild();
            if (exp_ instanceof AssMethodOperation && op_ != null) {
                AssOperationNode loc_ = AssOperationNode.createAssSimOperationNode(op_);
                ((AssMethodOperation)exp_).appendChild(loc_);
                exp_ = loc_;
                currentSim_ = op_;
                continue;
            }
            while (currentSim_ != null) {
                setupSim(exp_);
                out_.add(exp_);
                op_ = currentSim_.getNextSibling();
                if (op_ != null) {
                    AssOperationNode loc_ = AssOperationNode.createAssSimOperationNode(op_);
                    AssMethodOperation par_ = exp_.getParent();
                    par_.appendChild(loc_);
                    exp_ = loc_;
                    currentSim_ = op_;
                    break;
                }
                op_ = currentSim_.getParent();
                if (op_ == null) {
                    currentSim_ = null;
                } else if (op_ == _root) {
                    AssMethodOperation par_ = exp_.getParent();
                    setupSim(par_);
                    out_.add(par_);
                    currentSim_ = null;
                } else {
                    currentSim_ = op_;
                    exp_ = exp_.getParent();
                }
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
            ((AssOperationNodeFull)current_).analyzeAssignmentAfter(_b,_a, _page);
            next_ = current_.getNextSibling();
            AssMethodOperation par_ = current_.getParent();
            if (par_ instanceof AssMultMethodOperation &&next_ != null) {
                ((AssMultMethodOperation)par_).tryAnalyzeAssignmentBeforeNextSibling(_b,_a,next_, current_);
                return next_;
            }
            if (par_ == _root) {
                ((AssOperationNodeFull)par_).analyzeAssignmentAfter(_b,_a, _page);
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
            analyze(current_,_b,_a, _page);
            next_ = current_.getNextSibling();
            AssMethodOperation par_ = current_.getParent();
            if (next_ != null) {
                return next_;
            }
            if (par_ == _root) {
                analyze(par_,_b,_a, _page);
                return null;
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }
    private static void analyze(AssOperationNode _current,AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        if (_current instanceof AssOperationNodeSim) {
            ((AssOperationNodeSim)_current).analyzeSimAssignmentAfter(_ass, _a, _page);
        }
    }
    public static boolean checkFinalField(AssBlock _as, AssSettableFieldOperation _cst, StringMap<Assignment> _ass, AnalyzedPageEl _page) {
        boolean fromCurClass_ = _cst.isFromCurrentClass(_page);
        ClassField cl_ = _cst.getFieldId();
        String fieldName_ = cl_.getFieldName();
        if (stepForLoop(_as, _page)) {
            return true;
        }
        StringMap<BoolVal> ass_ = new StringMap<BoolVal>();
        for (EntryCust<String,Assignment> e: _ass.entryList()) {
            ass_.addEntry(e.getKey(), ComparatorBoolean.of(e.getValue().isUnassignedAfter()));
        }
        return checkFinalReadOnly(_cst, ass_, fromCurClass_, fieldName_, _page, _cst.getFieldMetaInfo().isStaticField());
    }
    private static boolean checkFinalReadOnly(AssSettableFieldOperation _cst, StringMap<BoolVal> _ass, boolean _fromCurClass, String _fieldName, AnalyzedPageEl _page, boolean _staticField) {
        boolean checkFinal_;
        if (_page.isAssignedFields()) {
            checkFinal_ = true;
        } else if (_page.isAssignedStaticFields()) {
            if (_staticField) {
                checkFinal_ = true;
            } else {
                checkFinal_ = checkFinal(_cst, _ass, _fromCurClass, _fieldName);
            }
        } else {
            checkFinal_ = checkFinal(_cst, _ass, _fromCurClass, _fieldName);
        }
        return checkFinal_;
    }

    private static boolean checkFinal(AssSettableFieldOperation _cst, StringMap<BoolVal> _ass, boolean _fromCurClass, String _fieldName) {
        boolean checkFinal_;
        if (!_fromCurClass) {
            checkFinal_ = true;
        } else {
            checkFinal_ = checkFinal(_cst, _ass, _fieldName);
        }
        return checkFinal_;
    }

    private static boolean checkFinal(AssSettableFieldOperation _cst, StringMap<BoolVal> _ass, String _fieldName) {
        boolean checkFinal_;
        if (_cst.isDeclare()) {
            checkFinal_ = false;
        } else {
            checkFinal_ = checkFinal(_ass, _fieldName);
        }
        return checkFinal_;
    }

    private static boolean checkFinal(StringMap<BoolVal> _ass, String _fieldName) {
        boolean checkFinal_;
        checkFinal_ = false;
        for (EntryCust<String, BoolVal> e: _ass.entryList()) {
            if (!StringUtil.quickEq(e.getKey(), _fieldName) || e.getValue() == BoolVal.TRUE) {
                continue;
            }
            checkFinal_ = true;
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
                BooleanAssignment b_ = boolAssignment(value_, bf_);
                ass_.put(e.getKey(), b_);
            }
            for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                BooleanAssignment b_ = boolAssignment(value_, bf_);
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

    private static BooleanAssignment boolAssignment(Argument _value, AssignmentBefore _bf) {
        BooleanAssignment b_ = new BooleanAssignment();
        if (BooleanStruct.isTrue(_value.getStruct())) {
            b_.setAssignedAfterWhenFalse(true);
            b_.setUnassignedAfterWhenFalse(true);
            b_.setAssignedAfterWhenTrue(_bf.isAssignedBefore());
            b_.setUnassignedAfterWhenTrue(_bf.isUnassignedBefore());
        } else {
            b_.setAssignedAfterWhenTrue(true);
            b_.setUnassignedAfterWhenTrue(true);
            b_.setAssignedAfterWhenFalse(_bf.isAssignedBefore());
            b_.setUnassignedAfterWhenFalse(_bf.isUnassignedBefore());
        }
        return b_;
    }

}
