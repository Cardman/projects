package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecStandardInstancingOperation extends
        ExecInvokingOperation {

    private String methodName;

    private String className;

    private String fieldName;
    private int blockIndex;

    private int naturalVararg;

    private String lastType;
    private ExecRootBlock rootBlock;
    private ExecNamedFunctionBlock ctor;
    public ExecStandardInstancingOperation(StandardInstancingOperation _s, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor) {
        super(_s);
        methodName = _s.getMethodName();
        className = _s.getClassName();
        fieldName = _s.getFieldName();
        blockIndex = _s.getBlockIndex();
        naturalVararg = _s.getNaturalVararg();
        lastType = _s.getLastType();
        rootBlock = _rootBlock;
        ctor = _ctor;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                         ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        if (!fieldName.isEmpty()) {
            off_ -= _conf.getLastPage().getTranslatedOffset();
            off_ -= fieldName.length();
            off_ --;
        }
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        PageEl page_ = _conf.getLastPage();
        className_ = page_.formatVarType(getClassName(), _conf);
        if (fieldName.isEmpty()) {
            String base_ = StringExpUtil.getIdFromAllTypes(className_);
            if (ExecutingUtil.hasToExit(_conf,base_)) {
                return Argument.createVoid();
            }
        }
        String lastType_ = ExecTemplates.quickFormat(rootBlock,className_, lastType);
        ArgumentList argumentList_ = listNamedArguments(_nodes, chidren_);
        CustList<Argument> first_ = argumentList_.getArguments();
        CustList<ExecOperationNode> filter_ = argumentList_.getFilter();
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, first_);
        return instancePrepareFormat(_conf.getLastPage(),_conf, className_,rootBlock,getCtor(), _previous, firstArgs_, fieldName, blockIndex);
    }

    public String getClassName() {
        return className;
    }

    public ExecNamedFunctionBlock getCtor() {
        return ctor;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }
}
