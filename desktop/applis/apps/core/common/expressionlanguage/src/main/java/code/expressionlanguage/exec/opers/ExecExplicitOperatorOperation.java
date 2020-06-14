package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.ExplicitOperatorOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
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
        CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType, arguments_);
        Argument prev_ = new Argument();
        checkParametersOperators(new DefaultExiting(_conf),_conf, classMethodId, prev_,firstArgs_);
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
