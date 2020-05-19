package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.StandardInstancingOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

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
        Argument argres_ = getArgument(previous_, arguments_, _conf);
        CallingState state_ = _conf.getContext().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContext());
            if (_conf.getContext().hasException()) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        processCall(_nodes,_conf,argres_);
    }

    Argument getArgument(Argument _previous, CustList<Argument> _arguments,
                         Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<RendDynOperationNode> filter_ = filterInvoking(chidren_);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        PageEl page_ = _conf.getPageEl();
        className_ = page_.formatVarType(className, _conf.getContext());
        String base_ = Templates.getIdFromAllTypes(className_);
        if (_conf.hasToExit(base_)) {
            return Argument.createVoid();
        }
        String lastType_ = Templates.quickFormat(className_, lastType, _conf.getContext());
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, _arguments, _conf);
        return ExecInvokingOperation.instancePrepare(_conf.getPageEl(),_conf.getContext(), className_, constId, _previous, firstArgs_, fieldName, blockIndex, true);
    }

}
