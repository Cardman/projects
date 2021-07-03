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
    public int match(Argument _arg, ContextEl _cont) {
        Struct struct_ = _arg.getStruct();
        String name_ = NumParsers.getNameOfEnum(struct_);
        String className_ = struct_.getClassName(_cont);
        int i_ = 0;
        for (Argument a: stdValues) {
            if (struct_.sameReference(a.getStruct())) {
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
