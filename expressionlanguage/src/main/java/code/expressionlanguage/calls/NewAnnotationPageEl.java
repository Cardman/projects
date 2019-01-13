package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;

public final class NewAnnotationPageEl extends AbstractCallingInstancingPageEl {

    private StringMap<String> names;
    private CustList<Argument> args;
    @Override
    public void tryProcessEl(ContextEl _context) {
        int len_ = names.size();
        FieldableStruct str_ = (FieldableStruct) getGlobalArgument().getStruct();
        String className_ = str_.getClassName();
        for (int i = 0; i <len_; i++) {
            String name_ = names.getKey(i);
            Argument value_ = args.get(i);
            String s_ = names.getValue(i);
            if (!s_.isEmpty()) {
                ArrayStruct a_ = new ArrayStruct(new Struct[1], s_);
                a_.getInstance()[0] = value_.getStruct();
                str_.setStruct(new ClassField(className_, name_), a_);
                continue;
            }
            str_.setStruct(new ClassField(className_, name_), value_.getStruct());
        }
        setNullReadWrite();
    }
    public StringMap<String> getNames() {
        return names;
    }
    public void setNames(StringMap<String> _names) {
        names = _names;
    }

    public void setArgs(CustList<Argument> _args) {
        args = _args;
    }
    
}
