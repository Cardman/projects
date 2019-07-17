package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.Struct;

public final class RendInternGlobalOperation extends RendLeafOperation implements RendCalculableOperation {
    private int off;
    public RendInternGlobalOperation(InternGlobalOperation _i) {
        super(_i);
        off = _i.getOff();
    }


    @Override
    public void calculate(ExecutableCode _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf);
    }

    Argument getCommonArgument(ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        Struct struct_ = _conf.getInternGlobal();
        Argument a_ = new Argument();
        a_.setStruct(struct_);
        return a_;
    }

}
