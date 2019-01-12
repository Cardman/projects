package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.StaticInfoOperation;
import code.util.IdMap;

public final class ExecStaticInfoOperation extends ExecVariableLeafOperation implements ReductibleOperable {

    private String className;

    public ExecStaticInfoOperation(StaticInfoOperation _s) {
        super(_s);
        className = _s.getClassName();
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        if (className.contains(Templates.PREFIX_VAR_TYPE)) {
            return;
        }
        Argument a_ = new Argument();
        a_.setStruct(_conf.getExtendedClassMetaInfo(className));
        setSimpleArgumentAna(a_, _conf);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument a_ = new Argument();
        String classStr_ = _conf.getOperationPageEl().formatVarType(className, _conf);
        a_.setStruct(_conf.getExtendedClassMetaInfo(classStr_));
        setSimpleArgument(a_, _conf, _nodes);
    }

}
