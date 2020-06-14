package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.StandardInstancingOperation;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecStandardInstancingOperation extends
        ExecInvokingOperation {

    private String methodName;

    private ConstructorId constId;

    private String className;

    private String fieldName;
    private int blockIndex;

    private int naturalVararg;

    private String lastType;
    public ExecStandardInstancingOperation(StandardInstancingOperation _s) {
        super(_s);
        methodName = _s.getMethodName();
        constId = _s.getConstId();
        className = _s.getClassName();
        fieldName = _s.getFieldName();
        blockIndex = _s.getBlockIndex();
        naturalVararg = _s.getNaturalVararg();
        lastType = _s.getLastType();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = filterInvoking(chidren_, _nodes);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(Argument _previous,CustList<Argument> _arguments,
                         ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<ExecOperationNode> filter_ = filterInvoking(chidren_);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        if (!fieldName.isEmpty()) {
            off_ -= _conf.getLastPage().getTranslatedOffset();
            off_ -= fieldName.length();
            off_ --;
        }
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        PageEl page_ = _conf.getLastPage();
        className_ = page_.formatVarType(className, _conf);
        if (fieldName.isEmpty()) {
            String base_ = Templates.getIdFromAllTypes(className_);
            if (_conf.hasToExit(base_)) {
                return Argument.createVoid();
            }
        }
        String lastType_ = Templates.quickFormat(className_, lastType, _conf);
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, _arguments);
        return instancePrepareFormat(_conf.getLastPage(),_conf, className_, constId, _previous, firstArgs_, fieldName, blockIndex);
    }

    public String getClassName() {
        return className;
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }
}
