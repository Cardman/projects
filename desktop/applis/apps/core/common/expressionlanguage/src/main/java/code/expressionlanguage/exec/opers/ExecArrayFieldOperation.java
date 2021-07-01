package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecArrayFieldOperation extends ExecAbstractFieldOperation {

    public ExecArrayFieldOperation(ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont) {
        super(_opCont, _fieldCont);
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Struct inst_ = previous_.getStruct();
        int len_ = getLength(inst_, _conf);
        Argument arg_;
        if (inst_ instanceof ArrayStruct) {
            setRelOffsetPossibleLastPage(getOff(), _stack);
            arg_ = new Argument(new IntStruct(len_));
        } else {
            String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stack)));
            arg_ = new Argument();
        }
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    public static int getLength(Struct _str, ContextEl _cont) {
        return getArray(_str,_cont).getLength();
    }

    public static ArrayStruct getArray(Struct _str, ContextEl _cont) {
        if (_str instanceof ArrayStruct) {
            return (ArrayStruct) _str;
        }
        String arr_ = StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getCoreNames().getAliasObject());
        return new ArrayStruct(0, arr_);
    }
}
