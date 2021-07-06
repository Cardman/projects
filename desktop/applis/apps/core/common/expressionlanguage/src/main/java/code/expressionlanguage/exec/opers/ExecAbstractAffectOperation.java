package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.util.IdMap;
import code.util.StringList;

public abstract class ExecAbstractAffectOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private ExecOperationNode settable;
    private ExecMethodOperation settableParent;
    private final int offset;
    private final StringList names;
    protected ExecAbstractAffectOperation(ExecOperationContent _m, int _offset, StringList _names) {
        super(_m);
        offset = _offset;
        names = _names;
    }

    public void setup() {
        settable = tryGetSettable(this);
        settableParent = tryGetSettableParent(this);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(offset, _stack);
        if (getSettableParent() instanceof ExecSafeDotOperation && getArgument(_nodes, getSettableParent().getFirstChild()).isNull()) {
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_nodes, this);
            pairBefore_.setEndCalculate(true);
            pairBefore_.setIndexImplicitSemiFrom(-1);
            pairBefore_.setIndexImplicitSemiTo(-1);
            pairBefore_.setIndexImplicitCompound(-1);
            pairBefore_.setCalledIndexer(true);
            setQuickConvertSimpleArgument(new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE, _conf, names, _stack)), _conf, _nodes, _stack);
            return;
        }
        calculateAffect(_nodes, _conf, _stack);
    }

    protected abstract void calculateAffect(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                            ContextEl _conf, StackCall _stack);
    static ExecOperationNode tryGetSettable(ExecMethodOperation _operation) {
        ExecOperationNode root_ = getFirstToBeAnalyzed(_operation);
        ExecOperationNode elt_;
        if (!(root_ instanceof ExecAbstractDotOperation)) {
            elt_ = root_;
        } else {
            elt_ = ExecHelper.getLastNode((ExecMethodOperation)root_);
        }
        return elt_;
    }
    static ExecMethodOperation tryGetSettableParent(ExecMethodOperation _operation) {
        ExecOperationNode root_ = getFirstToBeAnalyzed(_operation);
        ExecMethodOperation elt_;
        if (!(root_ instanceof ExecAbstractDotOperation)) {
            elt_ = ExecHelper.getParentOrNull(root_);
        } else {
            elt_ = (ExecMethodOperation)root_;
        }
        return elt_;
    }

    static ExecOperationNode getFirstToBeAnalyzed(ExecMethodOperation _operation) {
        ExecOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof ExecIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }

    protected StringList getNames() {
        return names;
    }

    public ExecOperationNode getSettable() {
        return settable;
    }

    public ExecMethodOperation getSettableParent() {
        return settableParent;
    }
}
