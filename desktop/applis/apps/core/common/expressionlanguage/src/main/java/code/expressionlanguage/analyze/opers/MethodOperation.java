package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.exec.opers.ReductibleOperable;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;
import code.util.*;

public abstract class MethodOperation extends OperationNode implements ReductibleOperable {

    private OperationNode firstChild;

    private IntTreeMap<String> children;
    private CustList<CustList<PartOffset>> partOffsetsChildren = new CustList<CustList<PartOffset>>();
    private StringList errs = new StringList();
    private CustList<PartOffset> partOffsetsEnd = new CustList<PartOffset>();


    public MethodOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        children = new IntTreeMap<String>();
        calculateChildren();
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        tryCalculateNode(this, _conf);
    }
    public static void tryCalculateNode(MethodOperation _par, ContextEl _conf) {
        CustList<OperationNode> children_ = _par.getChildrenNodes();
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        _par.quickCalculate(_conf);
    }
    public void quickCalculate(ContextEl _conf) {
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

    public void retrieveErrs() {
        for (OperationNode o: getChildrenNodes()) {
            if (!isEmptyError(o)) {
                continue;
            }
            addEmptyError(o, errs);
        }
    }

    public static void addEmptyError(OperationNode _op, StringList _out) {
        if (_op instanceof ErrorPartOperation) {
            _out.add(((ErrorPartOperation) _op).getErr());
        }
        if (_op instanceof BadInstancingOperation) {
            _out.add(((BadInstancingOperation) _op).getErr());
        }
        if (_op instanceof BadDottedOperation) {
            _out.add(((BadDottedOperation) _op).getErr());
        }
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

    public StringList getErrs() {
        return errs;
    }

    public CustList<PartOffset> getPartOffsetsEnd() {
        return partOffsetsEnd;
    }
}
