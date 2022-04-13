package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecFctOperation extends ExecSettableCallFctOperation {

    private final ExecTypeFunctionInst inst;

    public ExecFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        inst = new ExecTypeFunctionInst(_instFctContent,_pair);
    }

    public ExecFctOperation(ExecClassArgumentMatching _res,
                            int _child, int _order, ExecArrContent _arrContent, ExecTypeFunction _pair, ExecFormattedRootBlock _formattedType) {
        super(_child,_res,_order,true,_arrContent);
        inst = new ExecTypeFunctionInst(new ExecInstFctContent(_formattedType),_pair);
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        setRelOffsetPossibleLastPage(inst.getInst().getMethodName(), _stack);
        Argument res_ = prep(_conf, _stack, previous_, buildInfos(_nodes), inst);
        setResult(res_, _conf, _nodes, _stack);
    }

    public static Argument prep(ContextEl _conf, StackCall _stack, Argument _previous, CustList<ExecOperationInfo> _infos, ExecTypeFunctionInst _inst) {
        ExecFormattedRootBlock formattedType_ = _inst.getInst().getFormattedType();
        Struct argPrev_ = _previous.getStruct();
        Argument prev_ = new Argument(ExecFieldTemplates.getParent(_inst.getInst().getAnc(), argPrev_, _conf, _stack));
        Argument res_;
        if (_conf.callsOrException(_stack)) {
            res_ = new Argument();
        } else {
            Struct pr_ = prev_.getStruct();
            ExecOverrideInfo polymorph_ = polymorphOrSuper(_inst.getInst().isStaticChoiceMethod(), _conf, pr_, formattedType_, _inst.getPair());
            ExecTypeFunction pair_ = polymorph_.getPair();
            ExecFormattedRootBlock classNameFound_ = polymorph_.getClassName();
            res_ = new MethodParamChecker(pair_, fetchFormattedArgs(_conf, _stack, pr_,_inst, _infos), MethodAccessKind.INSTANCE).checkParams(classNameFound_, prev_, null, _conf, _stack);
        }
        return res_;
    }

}
