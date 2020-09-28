package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendInternGlobalOperation extends RendLeafOperation implements RendCalculableOperation {
    private int off;
    public RendInternGlobalOperation(ExecOperationContent _content, int _off) {
        super(_content);
        off = _off;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    Argument getCommonArgument(Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        Struct struct_ = _conf.getInternGlobal();
        return new Argument(struct_);
    }

}
