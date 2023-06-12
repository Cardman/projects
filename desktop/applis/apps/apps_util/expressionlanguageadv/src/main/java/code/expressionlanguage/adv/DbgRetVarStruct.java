package code.expressionlanguage.adv;

import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.Struct;

public final class DbgRetVarStruct extends DbgAbsNodeStruct {

    private final Struct eval;
    private final String className;
    public DbgRetVarStruct(ResultContext _r, AbstractWrapper _w) {
        super(_r);
        className = _w.getClassName(StackCall.newInstance(InitPhase.NOTHING, _r.getContext()),_r.getContext());
        eval = _w.getValue();
    }

    @Override
    public Struct value() {
        return eval;
    }

    @Override
    public String str() {
        return className;
    }

}
