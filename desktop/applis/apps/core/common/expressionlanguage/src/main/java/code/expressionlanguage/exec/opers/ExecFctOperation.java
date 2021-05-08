package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecFctOperation extends ExecSettableCallFctOperation {

    private final ExecInstFctContent instFctContent;
    private final ExecTypeFunction pair;

    public ExecFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        instFctContent = _instFctContent;
        pair = _pair;
    }

    public ExecFctOperation(ExecClassArgumentMatching _res,
                            ClassMethodId _classMethodId,
                            int _child, int _order, ExecArrContent _arrContent, ExecTypeFunction _pair) {
        super(_child,_res,_order,true,_arrContent);
        instFctContent = new ExecInstFctContent(_classMethodId);
        pair = _pair;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(instFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        String classNameFound_ = getClassName();
        Struct argPrev_ = previous_.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, argPrev_, _conf, _stack));
        Argument res_;
        if (_conf.callsOrException(_stack)) {
            res_ = new Argument();
        } else {
            Struct pr_ = prev_.getStruct();
            ExecOverrideInfo polymorph_ = polymorphOrSuper(instFctContent.isStaticChoiceMethod(), _conf, pr_, classNameFound_, pair);
            ExecTypeFunction pair_ = polymorph_.getPair();
            classNameFound_ = polymorph_.getClassName();
            res_ = callPrepare(_conf, classNameFound_, pair_, prev_, null, fetchFormattedArgs(_nodes, _conf, pr_, pair.getType(), instFctContent.getLastType(), instFctContent.getNaturalVararg(),null), MethodAccessKind.INSTANCE, _stack);
        }
        setResult(res_, _conf, _nodes, _stack);
    }

    public String getClassName() {
        return instFctContent.getClassName();
    }

}
