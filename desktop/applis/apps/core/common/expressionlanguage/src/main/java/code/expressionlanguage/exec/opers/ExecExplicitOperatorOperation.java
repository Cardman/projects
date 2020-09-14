package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ExplicitOperatorOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.IdMap;

public final class ExecExplicitOperatorOperation extends ExecInvokingOperation {

    private String lastType;

    private int naturalVararg;

    private String className;
    private MethodAccessKind kind;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private int offsetOper;

    public ExecExplicitOperatorOperation(ExplicitOperatorOperation _fct,ContextEl _context) {
        super(_fct);
        named = fetchFunction(_context,_fct.getRootNumber(),_fct.getMemberNumber());
        rootBlock = fetchType(_context,_fct.getRootNumber());
        kind = getKind(_fct.getClassMethodId());
        className = getType(_fct.getClassMethodId());
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        offsetOper = _fct.getOffsetOper();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String classNameFound_ = className;
        classNameFound_ = ClassMethodId.formatType(classNameFound_,_conf, kind);
        String lastType_ = ClassMethodId.formatType(rootBlock,classNameFound_,lastType, kind);
        CustList<Argument> first_ = listNamedArguments(_nodes, chidren_).getArguments();
        CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType_, first_);
        checkParametersOperators(new DefaultExiting(_conf),_conf, rootBlock, named, firstArgs_, className,kind);
    }

    public int getOffsetOper() {
        return offsetOper;
    }

}
