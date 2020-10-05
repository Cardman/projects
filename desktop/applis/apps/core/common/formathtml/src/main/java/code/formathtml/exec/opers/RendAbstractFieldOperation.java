package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public abstract class RendAbstractFieldOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted,RendCallable {

    private ExecFieldOperationContent fieldOperationContent;

    public RendAbstractFieldOperation(ExecOperationContent _content, ExecFieldOperationContent _fieldOperationContent) {
        super(_content);
        fieldOperationContent = _fieldOperationContent;
    }

    public int getOff() {
        return fieldOperationContent.getOff();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument arg_ = processCall(this, this, previous_,_nodes, _conf, null, _advStandards, _context);
        if (_context.callsOrException()) {
            return;
        }
        boolean simple_ = false;
        if (this instanceof RendSettableFieldOperation) {
            RendSettableFieldOperation s_ = (RendSettableFieldOperation) this;
            if (s_.resultCanBeSet()) {
                simple_ = true;
            }
        }
        if (simple_) {
            setQuickNoConvertSimpleArgument(arg_, _nodes, _context);
        } else {
            setSimpleArgument(arg_, _conf,_nodes, _context);
        }
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return fieldOperationContent.isIntermediate();
    }
    abstract Argument getCommonArgument(Argument _previous, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx);


    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        return getCommonArgument(_previous,_conf, _advStandards, _context);
    }

}
