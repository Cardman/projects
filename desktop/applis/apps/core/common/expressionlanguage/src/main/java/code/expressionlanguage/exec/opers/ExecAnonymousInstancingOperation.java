package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.AnonymousInstancingOperation;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecAnonymousInstancingOperation extends
        ExecInvokingOperation {

    private ExecRootBlock rootBlock;
    private ExecNamedFunctionBlock ctor;
    private String methodName;

    private String className;

    private int naturalVararg;

    private String lastType;
    public ExecAnonymousInstancingOperation(AnonymousInstancingOperation _s) {
        super(_s);
        setExecAnonymousInstancingOperation(_s);
    }
    public void setExecAnonymousInstancingOperation(AnonymousInstancingOperation _s) {
        methodName = _s.getMethodName();
        className = _s.getClassName();
        naturalVararg = _s.getNaturalVararg();
        lastType = _s.getLastType();
    }
    public void setExecAnonymousInstancingOperation(AnonymousInstancingOperation _s,ContextEl _context) {
        setExecAnonymousInstancingOperation(_s);
        rootBlock = _context.getAnalyzing().getMapTypes().getValue(_s.getBlock().getNumberAll());
        ctor = fetchFunction(_context,_s.getRootNumber(),_s.getMemberNumber());
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                         ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        PageEl page_ = _conf.getLastPage();
        className_ = page_.formatVarType(className, _conf);
        String base_ = StringExpUtil.getIdFromAllTypes(className_);
        if (ExecutingUtil.hasToExit(_conf,base_)) {
            return Argument.createVoid();
        }
        String lastType_ = ExecTemplates.quickFormat(rootBlock,className_, lastType);
        ArgumentList argumentList_ = listNamedArguments(_nodes, chidren_);
        CustList<Argument> first_ = argumentList_.getArguments();
        CustList<ExecOperationNode> filter_ = argumentList_.getFilter();
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, first_);
        return instancePrepareFormat(_conf.getLastPage(),_conf, className_,rootBlock,ctor, _previous, firstArgs_, "", -1);
    }
}
