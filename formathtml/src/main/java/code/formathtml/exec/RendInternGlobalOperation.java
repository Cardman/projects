package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.IdMap;

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

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    Argument getCommonArgument(ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        Struct struct_ = _conf.getInternGlobal();
        Argument a_ = new Argument();
        a_.setStruct(struct_);
        return a_;
    }

}
