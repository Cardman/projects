package code.expressionlanguage;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.structs.AbsFieldableStruct;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class LaunchableStruct extends AbsFieldableStruct implements Runnable {

    private final ContextEl original;


    protected LaunchableStruct(ContextEl _original, String _className,
                       String _name, int _ordinal,
                       CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName) {
        super(_className, _fields, _parent, _parentClassName, _ordinal, _name);
        this.original = _original;
    }

    protected LaunchableStruct(ContextEl _original, String _className,
                           CustList<ClassFieldStruct> _fields, ExecNamedFunctionBlock _n, LambdaStruct _f) {
        super(_className, _fields, _n, _f);
        this.original = _original;
    }

    public ContextEl getOriginal() {
        return original;
    }

    public CommonExecutionInfos getExecutionInfos() {
        return original.getExecutionInfos();
    }

}
