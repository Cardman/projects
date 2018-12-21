package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.ArrayFieldOperation;
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
    Argument getCommonArgument(Argument _previous, ExecutableCode _conf) {
        Argument a_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOff(), _conf);
        Argument arg_ = _previous;
        Struct inst_ = arg_.getStruct();
        if (inst_ instanceof ArrayStruct) {
            ArrayStruct arr_ = (ArrayStruct) inst_;
            a_ = new Argument();
            a_.setStruct(new IntStruct(arr_.getInstance().length));
            return a_;
        }
        String npe_;
        npe_ = _conf.getStandards().getAliasNullPe();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        String argCl_ = arg_.getObjectClassName(_conf.getContextEl());
        String arrObj_ = _conf.getStandards().getAliasObject();
        arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(arrObj_);
        _conf.setException(new ErrorStruct(_conf, StringList.concat(argCl_,RETURN_LINE,arrObj_,RETURN_LINE),npe_));
        a_ = new Argument();
        return a_;
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        Argument arg_ = getPreviousArgument();
        Argument a_ = new Argument();
        if (arg_ == null ||!(arg_.getStruct() instanceof ArrayStruct)) {
            return;
        }
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        a_.setStruct(new IntStruct(arr_.getInstance().length));
        setSimpleArgumentAna(a_,_conf);
    }
}
