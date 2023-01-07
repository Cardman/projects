package code.expressionlanguage;

import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.util.*;

public abstract class LaunchableFunctionalStruct extends AbstractFunctionalInstanceImpl implements Runnable,
        FieldableStruct {

    private final CustList<ClassFieldStruct> fields;
    private final CommonExecutionInfos executionInfos;
    private final StringList args;
    protected LaunchableFunctionalStruct(String _className, LambdaStruct _functional,
                                         CustList<ClassFieldStruct> _fields, RunnableContextEl _contextEl, ExecNamedFunctionBlock _named) {
        super(_className, _functional, _named);
        fields = _fields;
        executionInfos = _contextEl.getExecutionInfos();
        args = _contextEl.getArgs();
    }

    @Override
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }

    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public StringList getArgs() {
        return args;
    }
}
