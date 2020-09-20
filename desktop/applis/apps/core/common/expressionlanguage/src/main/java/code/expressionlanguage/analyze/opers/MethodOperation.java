package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.exec.opers.ReductibleOperable;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;
import code.util.*;

public abstract class MethodOperation extends OperationNode implements ReductibleOperable {

    private OperationNode firstChild;

    private IntTreeMap<String> children;
    private CustList<CustList<PartOffset>> partOffsetsChildren = new CustList<CustList<PartOffset>>();
    private CustList<PartOffset> partOffsetsEnd = new CustList<PartOffset>();


    public MethodOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        children = new IntTreeMap<String>();
        calculateChildren();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        tryCalculateNode(this, _page);
    }
    public static void tryCalculateNode(MethodOperation _par, AnalyzedPageEl _page) {
        CustList<OperationNode> children_ = _par.getChildrenNodes();
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        _par.quickCalculate(_page);
    }
    public void quickCalculate(AnalyzedPageEl _page) {
    }

    public final void appendChild(OperationNode _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        OperationNode child_ = firstChild;
        while (true) {
            OperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final CustList<OperationNode> getChildrenNodes() {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode elt_ = getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    abstract void calculateChildren();

    @Override
    public final OperationNode getFirstChild() {
        return firstChild;
    }

    public final IntTreeMap< String> getChildren() {
        return children;
    }

    public static void processEmptyError(OperationNode _o, StringList _errs) {
        if (isEmptyError(_o)) {
            addEmptyError(_o, _errs);
        }
    }

    private static void addEmptyError(OperationNode _op, StringList _out) {
        if (!_out.isEmpty()) {
            return;
        }
        _out.addAllElts(_op.getErrs());
    }
    public static boolean isEmptyError(OperationNode _op) {
        if (_op instanceof ErrorPartOperation) {
            return true;
        }
        if (_op instanceof BadInstancingOperation) {
            return true;
        }
        return _op instanceof BadDottedOperation;
    }
    public CustList<CustList<PartOffset>> getPartOffsetsChildren() {
        return partOffsetsChildren;
    }

    public CustList<PartOffset> getPartOffsetsEnd() {
        return partOffsetsEnd;
    }
}
