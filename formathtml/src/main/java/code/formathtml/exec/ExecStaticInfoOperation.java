package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Templates;
import code.expressionlanguage.opers.StaticInfoOperation;
import code.expressionlanguage.opers.exec.ReductibleOperable;

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
    public void calculate(ExecutableCode _conf) {
        Argument a_ = new Argument();
        String classStr_ = _conf.getOperationPageEl().formatVarType(className, _conf);
        a_.setStruct(_conf.getExtendedClassMetaInfo(classStr_));
        setSimpleArgument(a_, _conf);
    }

}
