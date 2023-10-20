package code.expressionlanguage.structs;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.util.CustList;

public final class InnerCustStruct extends AbsFieldableStruct {

    public InnerCustStruct(String _className,
                           CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName,
                           int _ordinal, String _name) {
        super(_className, _fields, _parent, _parentClassName, _ordinal, _name);
    }

    public InnerCustStruct(String _className,
                           CustList<ClassFieldStruct> _fields, ExecNamedFunctionBlock _n, LambdaStruct _f) {
        super(_className, _fields, _n, _f);
    }


}
