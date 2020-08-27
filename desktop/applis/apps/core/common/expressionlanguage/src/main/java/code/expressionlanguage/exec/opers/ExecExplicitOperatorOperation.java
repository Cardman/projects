package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ExplicitOperatorOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.util.CustList;
import code.util.IdMap;

public final class ExecExplicitOperatorOperation extends ExecInvokingOperation {

    private String lastType;

    private int naturalVararg;

    private ClassMethodId classMethodId;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private int offsetOper;

    public ExecExplicitOperatorOperation(ExplicitOperatorOperation _fct,ContextEl _context) {
        super(_fct);
        named = fetchFunction(_context,_fct.getRootNumber(),_fct.getMemberNumber());
        rootBlock = fetchType(_context,_fct.getRootNumber());
        classMethodId = _fct.getClassMethodId();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        offsetOper = _fct.getOffsetOper();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        String classNameFound_ = classMethodId.getClassName();
        classNameFound_ = classMethodId.formatType(classNameFound_,_conf);
        String lastType_ = classMethodId.formatType(rootBlock,classNameFound_,lastType);
        CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType_, arguments_);
        Argument prev_ = new Argument();
        checkParametersOperators(new DefaultExiting(_conf),_conf, classMethodId,rootBlock, named,prev_,firstArgs_);
    }

    public int getOffsetOper() {
        return offsetOper;
    }

}
