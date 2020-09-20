package code.expressionlanguage.assign.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public abstract class AssOperationNode {

    private AssMethodOperation parent;

    private AssOperationNode nextSibling;

    private Argument argument;

    private int indexInEl;
    private int indexBegin;

    private final int indexChild;

    private AnaClassArgumentMatching resultClass;

    AssOperationNode(OperationNode _ex) {
        indexInEl = _ex.getIndexInEl();
        indexBegin = _ex.getIndexBegin();
        indexChild = _ex.getIndexChild();
        resultClass = _ex.getResultClass();
        argument = _ex.getArgument();
    }
    public static AssOperationNode createAssOperationNode(OperationNode _anaNode) {
        if (_anaNode instanceof ConstantOperation) {
            ConstantOperation c_ = (ConstantOperation) _anaNode;
            return new AssConstantOperation(c_);
        }
        if (_anaNode instanceof AbstractTernaryOperation) {
            AbstractTernaryOperation t_ = (AbstractTernaryOperation) _anaNode;
            return new AssTernaryOperation(t_);
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            if (n_.getClassMethodId() == null) {
                if (_anaNode instanceof UnaryBooleanOperation) {
                    UnaryBooleanOperation a_ = (UnaryBooleanOperation) _anaNode;
                    return new AssUnaryBooleanOperation(a_);
                }
            }
        }
        if (_anaNode instanceof AndOperation) {
            AndOperation t_ = (AndOperation) _anaNode;
            return new AssAndOperation(t_);
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation t_ = (OrOperation) _anaNode;
            return new AssOrOperation(t_);
        }
        if (_anaNode instanceof IdOperation) {
            IdOperation f_ = (IdOperation) _anaNode;
            if (f_.isStandard()) {
                return new AssIdOperation(f_);
            }
        }
        if (_anaNode instanceof ThisOperation||_anaNode instanceof StaticAccessOperation) {
            return new AssAccessorOperation((LeafOperation) _anaNode);
        }
        if (_anaNode instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation s_ = (SettableAbstractFieldOperation) _anaNode;
            if (s_.getFieldMetaInfo() != null) {
                return new AssSettableFieldOperation(s_);
            }
        }
        if (_anaNode instanceof VariableOperation) {
            VariableOperation s_ = (VariableOperation) _anaNode;
            return new AssStdVariableOperation(s_);
        }
        if (_anaNode instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation s_ = (MutableLoopVariableOperation) _anaNode;
            return new AssStdVariableOperation(s_);
        }
        if (_anaNode instanceof DotOperation) {
            DotOperation m_ = (DotOperation) _anaNode;
            return new AssDotOperation(m_);
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            return new AssAffectationOperation(a_);
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation a_ = (SemiAffectationOperation) _anaNode;
            return new AssSemiAffectationOperation(a_);
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation a_ = (CompoundAffectationOperation) _anaNode;
            return new AssCompoundAffectationOperation(a_);
        }
        if (_anaNode instanceof MethodOperation) {
            if (((MethodOperation)_anaNode).getChildrenNodes().size() > 1) {
                return new AssStdMethodOperation(_anaNode);
            }
        }
        if (_anaNode instanceof LeafOperation) {
            return new AssStdLeafOperation(_anaNode);
        }
        return new AssStdUnaryMethodOperation(_anaNode);
    }
    public static AssOperationNode createAssSimOperationNode(OperationNode _anaNode) {
        if (_anaNode instanceof IdOperation) {
            IdOperation f_ = (IdOperation) _anaNode;
            if (f_.isStandard()) {
                return new AssSimIdOperation(f_);
            }
        }
        if (_anaNode instanceof VariableOperation) {
            VariableOperation s_ = (VariableOperation) _anaNode;
            return new AssSimStdVariableOperation(s_);
        }
        if (_anaNode instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation s_ = (MutableLoopVariableOperation) _anaNode;
            return new AssSimStdVariableOperation(s_);
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            return new AssSimAffectationOperation(a_);
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation a_ = (SemiAffectationOperation) _anaNode;
            return new AssSimReadWriteAffectationOperation(a_);
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation a_ = (CompoundAffectationOperation) _anaNode;
            return new AssSimReadWriteAffectationOperation(a_);
        }
        if (_anaNode instanceof MethodOperation) {
            if (((MethodOperation)_anaNode).getChildrenNodes().size() > 1) {
                return new AssSimStdMethodOperation(_anaNode);
            }
        }
        if (_anaNode instanceof LeafOperation) {
            return new AssSimStdLeafOperation(_anaNode);
        }
        return new AssSimStdUnaryMethodOperation(_anaNode);
    }
    public final void setRelativeOffsetPossibleAnalyzable(AnalyzedPageEl _page) {
        _page.setOffset(indexBegin+indexInEl);
    }
    public final void tryAnalyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        analyzeAssignmentAfter(_ass,_a, _page);
    }
    public abstract void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page);
    final boolean isFirstChild() {
        AssMethodOperation par_ = getParent();
        if (par_ == null) {
            return true;
        }
        return isFirstChildInParent();
    }

    final boolean isFirstChildInParent() {
        int ind_ = 0;
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
    public final AnaClassArgumentMatching getResultClass() {
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
