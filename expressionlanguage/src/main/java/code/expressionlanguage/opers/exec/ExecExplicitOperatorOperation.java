package code.expressionlanguage.opers.exec;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ExplicitOperatorOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.IdMap;

public final class ExecExplicitOperatorOperation extends ExecInvokingOperation {

    private String lastType;

    private int naturalVararg;

    private ClassMethodId classMethodId;
    private int offsetOper;
    private CustList<PartOffset> partOffsets;

    public ExecExplicitOperatorOperation(ExplicitOperatorOperation _fct) {
        super(_fct);
        classMethodId = _fct.getClassMethodId();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        offsetOper = _fct.getOffsetOper();
        partOffsets = _fct.getPartOffsets();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType, arguments_, _conf);
        Argument prev_ = new Argument();
        prepareExplicitOperator(new DefaultExiting(_conf),_conf, firstArgs_, prev_, classMethodId);
    }

    public static Argument prepareExplicitOperator(AbstractExiting _exit, ContextEl _conf, CustList<Argument> firstArgs_, Argument prev_, ClassMethodId _classMethodId) {
        if (_exit.hasToExit(_classMethodId.getClassName())) {
            return Argument.createVoid();
        }
        return callPrepare(new DefaultExiting(_conf),_conf, _classMethodId.getClassName(), _classMethodId.getConstraints(), prev_, firstArgs_, null);
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getOffsetOper() {
        return offsetOper;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
