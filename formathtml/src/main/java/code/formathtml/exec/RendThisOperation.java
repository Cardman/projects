package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ThisOperation;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendThisOperation extends RendLeafOperation implements RendCalculableOperation {

    private int off;

    public RendThisOperation(ThisOperation _t) {
        super(_t);
        off = _t.getOff();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    Argument getCommonArgument(ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        Struct struct_ = ip_.getGlobalArgument().getStruct();
        Argument a_ = new Argument();
        a_.setStruct(struct_);
        return a_;
    }

}
