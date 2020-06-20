package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.DefaultValueOperation;
import code.util.IdMap;

public final class ExecDefaultValueOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation {

    private String className;

    ExecDefaultValueOperation(DefaultValueOperation _l) {
        super(_l);
        className = _l.getClassName();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument a_ = new Argument();
        String classStr_ = _conf.getLastPage().formatVarType(className, _conf);
        a_.setStruct(PrimitiveTypeUtil.defaultValue(classStr_,_conf));
        setSimpleArgument(a_, _conf, _nodes);
    }
}
