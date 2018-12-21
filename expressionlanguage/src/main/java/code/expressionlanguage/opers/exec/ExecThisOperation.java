package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ThisOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecThisOperation extends ExecVariableLeafOperation implements ExecPossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private int nbAncestors;
    private int off;

    public ExecThisOperation(ThisOperation _t) {
        super(_t);
        previousResultClass = _t.getPreviousResultClass();
        intermediate = _t.isIntermediate();
        nbAncestors = _t.getNbAncestors();
        off = _t.getOff();
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    Argument getCommonArgument(ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        Struct struct_ = ip_.getGlobalArgument().getStruct();
        Argument a_ = new Argument();
        a_.setStruct(struct_);
        if (isIntermediateDottedOperation()) {
            String c_ = getResultClass().getNames().first();
            a_.setStruct(PrimitiveTypeUtil.getParent(nbAncestors, c_, a_.getStruct(), _conf));
        }
        return a_;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public Argument getPreviousArgument() {
        return null;
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
    }

}
