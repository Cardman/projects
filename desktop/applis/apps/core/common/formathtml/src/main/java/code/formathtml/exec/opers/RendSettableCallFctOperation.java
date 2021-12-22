package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public abstract class RendSettableCallFctOperation extends RendInvokingOperation implements RendSettableElResult {
    private final ExecArrContent arrContent;
    protected RendSettableCallFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation);
        arrContent = _arrContent;
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        return trySetArgument(_nodes, _context, _right, _rendStack);
    }

    @Override
    public Argument calculateCompoundString(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        Argument left_ = pair_.getArgument();
        Argument res_ = ExecCatOperation.localSumDiff(left_, _right, _context);
        return trySetArgument(_nodes, _context, res_, _rendStack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, StringList _cl, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        Argument left_ = pair_.getArgument();
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _cl, _context);
        return trySetArgument(_nodes, _context, res_, _rendStack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _op, Argument _right, byte _cl, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        Argument left_ = pair_.getArgument();
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _op, _cl, _context, _rendStack);
        return trySetArgument(_nodes, _context, res_, _rendStack);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _op, boolean _post, byte _cast, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        Argument left_ = pair_.getArgument();
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        trySetArgument(_nodes, _context, res_, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        trySetArgument(_nodes, _context, _right, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private Argument trySetArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _res, RendStackCall _stackCall) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        return processCall(ExecTemplates.trySetArgument(_conf, _res, pair_, _stackCall.getStackCall()),_conf,_stackCall).getValue();
    }
}
