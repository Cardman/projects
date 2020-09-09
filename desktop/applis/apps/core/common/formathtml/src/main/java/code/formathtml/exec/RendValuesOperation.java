package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ValuesOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendValuesOperation extends RendLeafOperation implements RendCalculableOperation,RendCallable {

    private String className;
    private int argOffset;
    private ExecRootBlock rootBlock;

    public RendValuesOperation(ValuesOperation _v, ContextEl _cont) {
        super(_v);
        className = _v.getClassName();
        argOffset = _v.getArgOffset();
        rootBlock = ExecOperationNode.fetchType(_cont, _v.getNumberEnum());
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument argres_ = processCall(this, this, Argument.createVoid(),_nodes, Argument.createVoid(), _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    Argument getCommonArgument(Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+argOffset, _conf);
        return ExecInvokingOperation.tryGetEnumValues(new AdvancedExiting(_conf), _conf.getContext(),rootBlock,ClassCategory.ENUM);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Argument _arguments, Configuration _conf, Argument _right) {
        return getCommonArgument(_conf);
    }
}
