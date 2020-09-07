package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ConstructorId;
import code.formathtml.Configuration;
import code.formathtml.util.RendArgumentList;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private String methodName;

    private ConstructorId constId;

    private String className;

    private String fieldName = EMPTY_STRING;
    private int blockIndex = -1;

    private int naturalVararg = -1;

    private String lastType = EMPTY_STRING;
    private ExecRootBlock rootBlock;
    private ExecNamedFunctionBlock ctor;
    public RendStandardInstancingOperation(StandardInstancingOperation _s, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor) {
        super(_s);
        methodName = _s.getMethodName();
        constId = _s.getConstId();
        className = _s.getClassName();
        fieldName = _s.getFieldName();
        blockIndex = _s.getBlockIndex();
        naturalVararg = _s.getNaturalVararg();
        lastType = _s.getLastType();
        rootBlock = _rootBlock;
        ctor = _ctor;
    }

    public RendStandardInstancingOperation(ClassArgumentMatching _res,
                                 ConstructorId _constId, ExecRootBlock _rootBlock) {
        super(0,_res,0,false,null);
        constId = _constId;
        className = constId.getName();
        methodName = constId.getName();
        rootBlock = _rootBlock;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_,_nodes, Argument.createVoid(), _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Argument _arguments,
                                Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        PageEl page_ = _conf.getPageEl();
        className_ = page_.formatVarType(className, _conf.getContext());
        String base_ = StringExpUtil.getIdFromAllTypes(className_);
        if (_conf.hasToExit(base_)) {
            return Argument.createVoid();
        }
        String lastType_ = ExecTemplates.quickFormat(rootBlock,className_, lastType);
        RendArgumentList args_ = RendInvokingOperation.listNamedArguments(_all, chidren_);
        CustList<Argument> first_ = args_.getArguments();
        CustList<RendDynOperationNode> filter_ = args_.getFilter();
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, first_);
        return ExecInvokingOperation.instancePrepareFormat(_conf.getPageEl(),_conf.getContext(), className_,rootBlock,ctor, _previous, firstArgs_, fieldName, blockIndex);
    }

}
