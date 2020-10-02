package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStdFctContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecStdFctOperation extends ExecInvokingOperation {

    private ExecStdFctContent stdFctContent;

    public ExecStdFctOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStdFctContent _stdFctContent) {
        super(_opCont, _intermediateDottedOperation);
        stdFctContent = _stdFctContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(stdFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        MethodId methodId_ = stdFctContent.getClassMethodId().getConstraints();
        String classNameFound_;
        Argument prev_;
        if (!stdFctContent.isStaticMethod()) {
            classNameFound_ = stdFctContent.getClassMethodId().getClassName();
            Struct argPrev_ = _previous.getStruct();
            prev_ = new Argument(ExecTemplates.getParent(0, classNameFound_, argPrev_, _conf));
            if (_conf.callsOrException()) {
                return new Argument();
            }
        } else {
            prev_ = new Argument();
        }
        classNameFound_ = stdFctContent.getClassMethodId().getClassName();
        CustList<Argument> firstArgs_ = getArgs(_nodes);
        return callStd(_conf.getExiting(),_conf, classNameFound_, methodId_, prev_, firstArgs_, null);
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        return fectchArgs(_nodes, stdFctContent.getLastType(), stdFctContent.getNaturalVararg());
    }

    public ClassMethodId getClassMethodId() {
        return stdFctContent.getClassMethodId();
    }

    public int getNaturalVararg() {
        return stdFctContent.getNaturalVararg();
    }
}
