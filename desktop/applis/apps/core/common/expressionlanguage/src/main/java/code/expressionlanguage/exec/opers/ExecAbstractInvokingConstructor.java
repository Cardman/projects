package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.AbstractInvokingConstructor;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecAbstractInvokingConstructor extends ExecInvokingOperation {

    private ConstructorId constId;
    private String classFromName;

    private String lastType;

    private int naturalVararg;
    private int offsetOper;
    public ExecAbstractInvokingConstructor(AbstractInvokingConstructor _abs) {
        super(_abs);
        constId = _abs.getConstId();
        lastType = _abs.getLastType();
        naturalVararg = _abs.getNaturalVararg();
        offsetOper = _abs.getOffsetOper();
        classFromName = _abs.getClassFromName();
    }

    public int getOffsetOper() {
        return offsetOper;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    abstract Argument getArgument(CustList<Argument> _arguments, ContextEl _conf);

    public final ConstructorId getConstId() {
        return constId;
    }

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getClassFromName() {
        return classFromName;
    }
}
