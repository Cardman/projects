package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.opers.AbstractUnaryOperation;

public abstract class ExecAbstractUnaryOperation extends ExecReflectableOpering {

    public ExecAbstractUnaryOperation(AbstractUnaryOperation _a) {
        super(_a);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        AbstractUnaryOperation.setArg(_conf,this);
    }
}
