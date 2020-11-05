package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
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
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument arg_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        setRelOffsetPossibleLastPage(getOff(), _conf);
        Struct inst_ = _previous.getStruct();
        int len_ = getLength(inst_,_conf);
        if (inst_ instanceof ArrayStruct) {
            return new Argument(new IntStruct(len_));
        }
        String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
        setRelativeOffsetPossibleLastPage(_conf);
        _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_)));
        return new Argument();
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
