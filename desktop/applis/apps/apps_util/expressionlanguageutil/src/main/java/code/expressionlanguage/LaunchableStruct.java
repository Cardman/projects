package code.expressionlanguage;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.structs.AbsFieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.CustList;

public abstract class LaunchableStruct extends AbsFieldableStruct implements Runnable {

    private final RunnableContextEl original;


    protected LaunchableStruct(RunnableContextEl _original, String _className,
                       String _name, int _ordinal,
                       CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName) {
        super(_className, _fields, _parent, _parentClassName, _ordinal, _name);
        this.original = _original;
    }

    public RunnableContextEl getOriginal() {
        return original;
    }

    public CommonExecutionInfos getExecutionInfos() {
        return original.getExecutionInfos();
    }

}
