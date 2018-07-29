package code.expressionlanguage;

import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldableStruct;
import code.util.CustList;
import code.util.StringList;

public final class NewAnnotationPageEl extends AbstractCallingInstancingPageEl implements ForwardPageEl {

    private StringList names;
    private CustList<Argument> args;
    @Override
    public void tryProcessEl(ContextEl _context) {
        int len_ = names.size();
        FieldableStruct str_ = (FieldableStruct) getGlobalArgument().getStruct();
        String className_ = str_.getClassName();
        for (int i = 0; i <len_; i++) {
            String name_ = names.get(i);
            Argument value_ = args.get(i);
            str_.setStruct(new ClassField(className_, name_), value_.getStruct());
        }
        setNullReadWrite();
    }
    public StringList getNames() {
        return names;
    }
    public void setNames(StringList _names) {
        names = _names;
    }
    public CustList<Argument> getArgs() {
        return args;
    }
    public void setArgs(CustList<Argument> _args) {
        args = _args;
    }
    
}
