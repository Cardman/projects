package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public final class ExecArrayFieldOperation extends ExecAbstractFieldOperation {

    public ExecArrayFieldOperation(ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont) {
        super(_opCont, _fieldCont);
    }

    @Override
    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        setRelOffsetPossibleLastPage(getOff(), _conf);
        Struct inst_ = _previous.getStruct();
        int len_ = getLength(inst_,_conf);
        if (inst_ instanceof ArrayStruct) {
            return new Argument(new IntStruct(len_));
        }
        String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
        setRelativeOffsetPossibleLastPage(_conf);
        _conf.setCallingState(new ErrorStruct(_conf, npe_));
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
