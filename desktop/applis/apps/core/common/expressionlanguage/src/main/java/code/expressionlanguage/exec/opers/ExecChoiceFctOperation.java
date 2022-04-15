package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecChoiceFctOperation extends ExecSettableCallFctOperation {

    private final ExecTypeFunctionInst inst;

    public ExecChoiceFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        inst = new ExecTypeFunctionInst(_instFctContent,_pair);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = inst.getInst().getMethodName();
        setRelOffsetPossibleLastPage(off_, _stack);
        Argument parent_ = new Argument(ExecFieldTemplates.getParent(inst.getInst().getAnc(), previous_.getStruct(), _conf, _stack));
        Argument res_ = prep(_conf, _stack, inst, parent_, fetchFormattedArgs(_conf, _stack, parent_.getStruct(), inst, buildInfos(_nodes)));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Argument prep(ContextEl _conf, StackCall _stack, ExecTypeFunctionInst _ins, Argument _parent, ArgumentListCall _args) {
        ExecFormattedRootBlock formattedType_ = _ins.getInst().getFormattedType();
        Argument res_;
        if (_conf.callsOrException(_stack)) {
            res_ = new Argument();
        } else {
            res_ = new MethodParamChecker(_ins.getPair(), _args, MethodAccessKind.INSTANCE).checkParams(formattedType_, _parent, null, _conf, _stack);
        }
        return res_;
    }

}
