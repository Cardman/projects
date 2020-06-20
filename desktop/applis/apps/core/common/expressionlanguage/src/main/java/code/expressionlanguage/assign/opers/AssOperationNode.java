package code.expressionlanguage.assign.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;

public abstract class AssOperationNode {

    private AssMethodOperation parent;

    private AssOperationNode nextSibling;

    private Argument argument;

    private int indexInEl;
    private int indexBegin;

    private final int indexChild;

    private ClassArgumentMatching resultClass;

    AssOperationNode(ExecOperationNode _ex) {
        indexInEl = _ex.getIndexInEl();
        indexBegin = _ex.getIndexBegin();
        indexChild = _ex.getIndexChild();
        resultClass = _ex.getResultClass();
        argument = _ex.getArgument();
    }
    public static AssOperationNode createAssOperationNode(ExecOperationNode _anaNode) {
        if (_anaNode instanceof ExecStaticInitOperation) {
            ExecStaticInitOperation c_ = (ExecStaticInitOperation) _anaNode;
            return new AssStaticInitOperation(c_);
        }
        if (_anaNode instanceof ExecConstantOperation) {
            ExecConstantOperation c_ = (ExecConstantOperation) _anaNode;
            return new AssConstantOperation(c_);
        }
        if (_anaNode instanceof ExecDeclaringOperation) {
            ExecDeclaringOperation d_ = (ExecDeclaringOperation) _anaNode;
            return new AssDeclaringOperation(d_);
        }
        if (_anaNode instanceof ExecTernaryOperation) {
            ExecTernaryOperation t_ = (ExecTernaryOperation) _anaNode;
            return new AssTernaryOperation(t_);
        }
        if (_anaNode instanceof ExecAndOperation) {
            ExecAndOperation t_ = (ExecAndOperation) _anaNode;
            return new AssAndOperation(t_);
        }
        if (_anaNode instanceof ExecOrOperation) {
            ExecOrOperation t_ = (ExecOrOperation) _anaNode;
            return new AssOrOperation(t_);
        }
        if (_anaNode instanceof ExecStaticAccessOperation) {
            ExecLeafOperation f_ = (ExecLeafOperation) _anaNode;
            return new AssStaticAccessOperation(f_);
        }
        if (_anaNode instanceof ExecIdOperation) {
            ExecIdOperation f_ = (ExecIdOperation) _anaNode;
            return new AssIdOperation(f_);
        }
        if (_anaNode instanceof ExecThisOperation) {
            ExecThisOperation f_ = (ExecThisOperation) _anaNode;
            return new AssThisOperation(f_);
        }
        if (_anaNode instanceof ExecSettableFieldOperation) {
            ExecSettableFieldOperation s_ = (ExecSettableFieldOperation) _anaNode;
            return new AssSettableFieldOperation(s_);
        }
        if (_anaNode instanceof ExecMutableLoopVariableOperation) {
            ExecMutableLoopVariableOperation m_ = (ExecMutableLoopVariableOperation) _anaNode;
            return new AssMutableLoopVariableOperation(m_);
        }
        if (_anaNode instanceof ExecVariableOperation) {
            ExecVariableOperation m_ = (ExecVariableOperation) _anaNode;
            return new AssVariableOperation(m_);
        }
        if (_anaNode instanceof ExecDotOperation) {
            ExecDotOperation m_ = (ExecDotOperation) _anaNode;
            return new AssDotOperation(m_);
        }
        if (_anaNode instanceof ExecAffectationOperation) {
            ExecAffectationOperation a_ = (ExecAffectationOperation) _anaNode;
            return new AssAffectationOperation(a_);
        }
        if (_anaNode instanceof ExecSemiAffectationOperation) {
            ExecSemiAffectationOperation a_ = (ExecSemiAffectationOperation) _anaNode;
            return new AssSemiAffectationOperation(a_);
        }
        if (_anaNode instanceof ExecCompoundAffectationOperation) {
            ExecCompoundAffectationOperation a_ = (ExecCompoundAffectationOperation) _anaNode;
            return new AssCompoundAffectationOperation(a_);
        }
        if (_anaNode instanceof ExecUnaryBooleanOperation) {
            ExecUnaryBooleanOperation a_ = (ExecUnaryBooleanOperation) _anaNode;
            return new AssUnaryBooleanOperation(a_);
        }
        if (_anaNode instanceof ExecCurrentInvokingConstructor) {
            ExecCurrentInvokingConstructor a_ = (ExecCurrentInvokingConstructor) _anaNode;
            return new AssCurrentInvokingConstructor(a_);
        }
        if (_anaNode instanceof ExecMethodOperation) {
            if (((ExecMethodOperation)_anaNode).getChildrenNodes().size() > 1) {
                return new AssStdMethodOperation(_anaNode);
            }
        }
        if (_anaNode instanceof ExecLeafOperation) {
            return new AssStdLeafOperation(_anaNode);
        }
        return new AssStdUnaryMethodOperation(_anaNode);
    }
    public final void setRelativeOffsetPossibleAnalyzable(ContextEl _cont) {
        _cont.getAnalyzing().setOffset(indexBegin+indexInEl);
    }
    public final void tryAnalyzeAssignmentAfter(ContextEl _conf,AssBlock _ass, AssignedVariablesBlock _a) {
        analyzeAssignmentAfter(_conf,_ass,_a);
    }
    public abstract void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a);
    final boolean isFirstChild() {
        AssMethodOperation par_ = getParent();
        if (par_ == null) {
            return true;
        }
        return isFirstChildInParent();
    }

    final boolean isFirstChildInParent() {
        AssMethodOperation par_ = getParent();
        int ind_ = 0;
        if (par_.getFirstChild() instanceof AssStaticInitOperation) {
            ind_++;
        }
        return getIndexChild() == ind_;
    }

    public int getIndexChild() {
        return indexChild;
    }

    public abstract AssOperationNode getFirstChild();

    public final AssOperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(AssOperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }
    public final ClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public final AssMethodOperation getParent() {
        return parent;
    }

    public Argument getArgument() {
        return argument;
    }

    public final void setParent(AssMethodOperation _parent) {
        parent = _parent;
    }
}
