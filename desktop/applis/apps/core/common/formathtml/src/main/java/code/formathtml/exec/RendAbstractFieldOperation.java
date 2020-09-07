package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.AbstractFieldOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendAbstractFieldOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted,RendCallable {

    private boolean intermediate;

    private Argument previousArgument;

    private int off;

    public RendAbstractFieldOperation(AbstractFieldOperation _a) {
        super(_a);
        intermediate = _a.isIntermediateDottedOperation();
        previousArgument = _a.getPreviousArgument();
        off = _a.getOff();
    }
    RendAbstractFieldOperation(Argument _a,int _indexChild, ClassArgumentMatching _res, int _order, boolean _int) {
        super(_indexChild,_res,_order);
        previousArgument = _a;
        intermediate = _int;
    }

    public int getOff() {
        return off;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument arg_ = processCall(this, this, previous_,_nodes, Argument.createVoid(), _conf, null);
        if (_conf.getContext().hasException()) {
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
            setQuickNoConvertSimpleArgument(arg_, _conf,_nodes);
        } else {
            setSimpleArgument(arg_, _conf,_nodes);
        }
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
    abstract Argument getCommonArgument(Argument _previous, Configuration _conf);

    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }


    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Argument _arguments, Configuration _conf, Argument _right) {
        return getCommonArgument(_previous,_conf);
    }

}
