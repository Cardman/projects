package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ExecSwitchValuesList {
    private final CustList<Argument> stdValues;
    private final CustList<ClassField> enumValues;

    public ExecSwitchValuesList(CustList<Argument> _stdValues, CustList<ClassField> _enumValues) {
        this.stdValues = _stdValues;
        this.enumValues = _enumValues;
    }

    public int match(Struct _arg, ContextEl _cont) {
        String name_ = NumParsers.getNameOfEnum(_arg);
        String className_ = _arg.getClassName(_cont);
        int i_ = 0;
        for (Argument a: stdValues) {
            if (_arg.sameReference(a.getStruct())) {
                return i_;
            }
            i_++;
        }
        for (ClassField c: enumValues) {
            if (StringUtil.quickEq(c.getClassName(),className_)&&StringUtil.quickEq(c.getFieldName(), name_)) {
                return i_;
            }
            i_++;
        }
        return -1;
    }
}
