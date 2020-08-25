package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.analyze.opers.FctOperation;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCloneOperation extends ExecInvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    protected ExecCloneOperation(FctOperation _fct) {
        super(_fct);
        methodName = _fct.getMethodName();
        classMethodId = _fct.getClassMethodId();
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ContextEl _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        return cloneArray(_previous, _arguments, _conf, classMethodId);
    }

    public static Argument cloneArray(Argument _previous, CustList<Argument> _arguments, ContextEl _conf, ClassMethodId _classMethodId) {
        MethodId methodId_ = _classMethodId.getConstraints();
        String classNameFound_;
        Argument prev_ = new Argument();
        classNameFound_ = _classMethodId.getClassName();
        Struct argPrev_ = _previous.getStruct();
        prev_.setStruct(ExecTemplates.getParent(0, classNameFound_, argPrev_, _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        CustList<ExecOperationNode> chidren_ = new CustList<ExecOperationNode>();
        CustList<Argument> firstArgs_ = listArguments(chidren_, -1, "", _arguments);
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_, methodId_, prev_, firstArgs_, null);
    }

}
