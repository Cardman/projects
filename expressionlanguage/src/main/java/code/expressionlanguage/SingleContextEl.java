package code.expressionlanguage;

import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;
import code.util.ObjectMap;

public class SingleContextEl extends ContextEl {

    public SingleContextEl() {
        // TODO Auto-generated constructor stub
    }

    public SingleContextEl(int _stackOverFlow) {
        super(_stackOverFlow);
        // TODO Auto-generated constructor stub
    }

    public SingleContextEl(DefaultLockingClass _lock, Initializer _init,
            Options _options, KeyWords _keyWords) {
        super(_lock, _init, _options, _keyWords);
        // TODO Auto-generated constructor stub
    }

    public SingleContextEl(int _stackOverFlow, DefaultLockingClass _lock,
            Initializer _init, Options _options, KeyWords _keyWords) {
        super(_stackOverFlow, _lock, _init, _options, _keyWords);
        // TODO Auto-generated constructor stub
    }

    public SingleContextEl(ContextEl _context, String _className, String _name,
            int _ordinal, ObjectMap<ClassField, Struct> _fields, Struct _parent) {
        super(_context, _className, _name, _ordinal, _fields, _parent);
        // TODO Auto-generated constructor stub
    }

}
