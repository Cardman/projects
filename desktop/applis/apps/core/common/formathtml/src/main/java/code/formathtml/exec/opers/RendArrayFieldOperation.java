package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;

public final class RendArrayFieldOperation extends RendAbstractFieldOperation {

    public RendArrayFieldOperation(ExecOperationContent _content, ExecFieldOperationContent _fieldOperationContent) {
        super(_content, _fieldOperationContent);
    }

    @Override
    Argument getCommonArgument(Argument _previous, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOff(), _conf);
        Struct inst_ = _previous.getStruct();
        if (inst_ instanceof ArrayStruct) {
            ArrayStruct arr_ = (ArrayStruct) inst_;
            return new Argument(new IntStruct(arr_.getLength()));
        }
        String npe_;
        npe_ = _ctx.getStandards().getContent().getCoreNames().getAliasNullPe();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        _ctx.setException(new ErrorStruct(_ctx,npe_));
        return new Argument();
    }
}
