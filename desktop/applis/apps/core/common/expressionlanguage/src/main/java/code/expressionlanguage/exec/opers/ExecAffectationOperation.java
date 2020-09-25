package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.util.IdMap;

public final class ExecAffectationOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private ExecSettableElResult settable;

    private int opOffset;

    public ExecAffectationOperation(ExecOperationContent _opCont, int _opOffset) {
        super(_opCont);
        opOffset = _opOffset;
    }

    public void setup() {
        settable = tryGetSettable(this);
    }
    static ExecSettableElResult tryGetSettable(ExecMethodOperation _operation) {
        ExecOperationNode root_ = getFirstToBeAnalyzed(_operation);
        ExecSettableElResult elt_;
        if (!(root_ instanceof ExecAbstractDotOperation)) {
            elt_ = castTo(root_);
        } else {
            ExecOperationNode beforeLast_ = ((ExecMethodOperation)root_).getChildrenNodes().last();
            elt_ = castTo(beforeLast_);
        }
        return elt_;
    }
    private static ExecSettableElResult castTo(ExecOperationNode _op) {
        if (_op instanceof ExecSettableElResult) {
            return (ExecSettableElResult) _op;
        }
        return null;
    }
    public static ExecOperationNode getFirstToBeAnalyzed(ExecMethodOperation _operation) {
        ExecOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof ExecIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }
    public ExecSettableElResult getSettable() {
        return settable;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        if (((ExecOperationNode) settable).getParent() instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = ((ExecOperationNode) settable).getParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(_conf.getLastPage(), NullStruct.NULL_VALUE,_conf, getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
        }
        ExecOperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = getArgument(_nodes, right_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset,_conf);
        Argument arg_ = settable.calculateSetting(_nodes, _conf, rightArg_);
        setSimpleArgument(arg_, _conf, _nodes);
    }

}
