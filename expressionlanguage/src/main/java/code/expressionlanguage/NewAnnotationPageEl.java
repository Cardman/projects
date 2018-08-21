package code.expressionlanguage;

import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldableStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.StringMap;

public final class NewAnnotationPageEl extends AbstractCallingInstancingPageEl implements ForwardPageEl {

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
        setArgumentForConstructor();
        setNullReadWrite();
    }
    public StringMap<String> getNames() {
        return names;
    }
    public void setNames(StringMap<String> _names) {
        names = _names;
    }
    public CustList<Argument> getArgs() {
        return args;
    }
    public void setArgs(CustList<Argument> _args) {
        args = _args;
    }
    
}
