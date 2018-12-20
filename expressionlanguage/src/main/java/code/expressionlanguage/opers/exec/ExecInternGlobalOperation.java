package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.InternGlobalOperation;
import code.expressionlanguage.structs.Struct;

public final class ExecInternGlobalOperation extends ExecLeafOperation implements DirectExecCalculableOperation {
    private int off;
    public ExecInternGlobalOperation(InternGlobalOperation _i) {
        super(_i);
        off = _i.getOff();
    }


    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }


    @Override
    public void calculate(ExecutableCode _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
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
