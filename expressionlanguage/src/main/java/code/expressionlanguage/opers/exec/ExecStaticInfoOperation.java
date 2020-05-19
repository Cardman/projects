package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutingUtil;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.StaticInfoOperation;
import code.util.IdMap;

public final class ExecStaticInfoOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation {

    private String className;

    public ExecStaticInfoOperation(StaticInfoOperation _s) {
        super(_s);
        className = _s.getClassName();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument a_ = new Argument();
        String classStr_ = _conf.getLastPage().formatVarType(className, _conf);
        a_.setStruct(ExecutingUtil.getClassMetaInfo(_conf,classStr_));
        setSimpleArgument(a_, _conf, _nodes);
    }

}
