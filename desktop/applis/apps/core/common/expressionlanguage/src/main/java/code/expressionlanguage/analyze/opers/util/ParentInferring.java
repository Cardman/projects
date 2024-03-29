package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.opers.*;

public final class ParentInferring {
    private final OperationNode operationChild;
    private final OperationNode operation;
    private final int nbParentsInfer;

    private ParentInferring(OperationNode _operationChild, OperationNode _operation, int _nbParentsInfer) {
        this.operationChild = _operationChild;
        this.operation = _operation;
        this.nbParentsInfer = _nbParentsInfer;
    }

    public static ParentInferring getParentInferring(OperationNode _from) {
        OperationNode current_;
        MethodOperation m_;
        int nbParentsInfer_ = 0;
        current_ = _from;
        m_ = _from.getParent();
        while (m_ != null) {
            if (!(m_ instanceof ElementArrayInstancing) && !(m_ instanceof InferArrayInstancing)) {
                if (!(m_ instanceof IdOperation) && !(m_ instanceof FirstOptOperation) && !(m_ instanceof WrappOperation) && (!(m_ instanceof AbstractTernaryOperation) || m_.getFirstChild() == current_)) {
                    break;
                }
            } else {
                nbParentsInfer_++;
            }
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
        return new ParentInferring(current_, m_,nbParentsInfer_);
    }

    public OperationNode getOperationChild() {
        return operationChild;
    }

    public OperationNode getOperation() {
        return operation;
    }

    public int getNbParentsInfer() {
        return nbParentsInfer;
    }
}
