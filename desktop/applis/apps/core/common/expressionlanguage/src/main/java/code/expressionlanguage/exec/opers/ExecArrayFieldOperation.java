package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.opers.ArrayFieldOperation;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class ExecArrayFieldOperation extends ExecAbstractFieldOperation {

    public ExecArrayFieldOperation(ArrayFieldOperation _a) {
        super(_a);
    }

    @Override
    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        Argument a_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOff(), _conf);
        Struct inst_ = _previous.getStruct();
        int len_ = getLength(inst_,_conf);
        if (inst_ instanceof ArrayStruct) {
            a_ = new Argument();
            a_.setStruct(new IntStruct(len_));
            return a_;
        }
        String npe_;
        npe_ = _conf.getStandards().getAliasNullPe();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        String argCl_ = _previous.getObjectClassName(_conf);
        String arrObj_ = _conf.getStandards().getAliasObject();
        arrObj_ = StringExpUtil.getPrettyArrayType(arrObj_);
        _conf.setException(new ErrorStruct(_conf, StringList.concat(argCl_,RETURN_LINE,arrObj_,RETURN_LINE),npe_));
        a_ = new Argument();
        return a_;
    }

    public static int getLength(Struct _str, ContextEl _cont) {
        return getArray(_str,_cont).getInstance().length;
    }

    public static ArrayStruct getArray(Struct _str, ContextEl _cont) {
        if (_str instanceof ArrayStruct) {
            return (ArrayStruct) _str;
        }
        String arr_ = StringExpUtil.getPrettyArrayType(_cont.getStandards().getAliasObject());
        return new ArrayStruct(new Struct[0], arr_);
    }
}
