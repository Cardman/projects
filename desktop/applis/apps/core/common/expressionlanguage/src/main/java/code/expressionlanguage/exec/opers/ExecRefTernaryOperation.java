package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.IdMap;

public final class ExecRefTernaryOperation extends ExecMethodOperation implements AtomicExecCalculableOperation,ExecSettableElResult {

    private final int offsetLocal;
    private final ExecArrContent arrContent;

    public ExecRefTernaryOperation(ExecOperationContent _opCont, int _offsetLocal,ExecArrContent _arrContent) {
        super(_opCont);
        offsetLocal = _offsetLocal;
        arrContent = _arrContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(offsetLocal, _stack);
        ArgumentsPair ch_ = getChosenArgumentsPair(_nodes);
        AbstractWrapper res_ = ch_.getWrapper();
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        ExecHelper.fwdWrapper(pair_,ch_);
        Argument arg_ = ExecTemplates.getArgValue(res_, _conf, _stack);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    private ArgumentsPair getChosenArgumentsPair(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        ArgumentsPair arg_;
        if (BooleanStruct.isTrue(ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(getChildrenNodes(),0)).getArgument().getStruct())) {
            arg_ = ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(getChildrenNodes(),1));
        } else {
            arg_ = ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(getChildrenNodes(),2));
        }
        return arg_;
    }

    @Override
    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        return trySetArgument(_nodes, _conf, _right, _stack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, StackCall _stack) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        Argument left_ = ExecTemplates.getArgValue(pair_.getWrapper(), _conf, _stack);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, arrContent.isCatString(), _cl.getNames(), _cast, _stack);
        return trySetArgument(_nodes, _conf, res_, _stack);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, boolean _post, byte _cast, StackCall _stack) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        Argument left_ = ExecTemplates.getArgValue(pair_.getWrapper(), _conf, _stack);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        trySetArgument(_nodes, _conf, res_, _stack);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        return trySetArgument(_nodes, _conf, _right, _stack);
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right, StackCall _stack) {
        trySetArgument(_nodes, _conf, _right, _stack);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private Argument trySetArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _res, StackCall _stackCall) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        return ExecTemplates.trySetArgument(_conf, _res, pair_, _stackCall);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

}
