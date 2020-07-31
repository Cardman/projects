package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ConstructorId;
import code.formathtml.Configuration;
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
    public RendStandardInstancingOperation(StandardInstancingOperation _s) {
        super(_s);
        methodName = _s.getMethodName();
        constId = _s.getConstId();
        className = _s.getClassName();
        fieldName = _s.getFieldName();
        blockIndex = _s.getBlockIndex();
        naturalVararg = _s.getNaturalVararg();
        lastType = _s.getLastType();
    }

    public RendStandardInstancingOperation(ClassArgumentMatching _res,
                                 ConstructorId _constId) {
        super(0,_res,0,false,null);
        constId = _constId;
        className = constId.getName();
        methodName = constId.getName();
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (RendDynOperationNode o: filterInvoking(chidren_)) {
            arguments_.add(getArgument(_nodes,o));
        }
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_, arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    public Argument getArgument(Argument _previous, CustList<Argument> _arguments,
                                Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<RendDynOperationNode> filter_ = filterInvoking(chidren_);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        PageEl page_ = _conf.getPageEl();
        className_ = page_.formatVarType(className, _conf.getContext());
        String base_ = StringExpUtil.getIdFromAllTypes(className_);
        if (_conf.hasToExit(base_)) {
            return Argument.createVoid();
        }
        String lastType_ = ExecTemplates.quickFormat(className_, lastType, _conf.getContext());
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, _arguments);
        return ExecInvokingOperation.instancePrepareFormat(_conf.getPageEl(),_conf.getContext(), className_, getConstId(), _previous, firstArgs_, fieldName, blockIndex);
    }

    public ConstructorId getConstId() {
        return constId;
    }
}
