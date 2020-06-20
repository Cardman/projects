package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.utilcompo.CustInitializer;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public class GuiInitializer extends CustInitializer {
    private final WindowSetStruct windows = new WindowSetStruct(false);
    @Override
    protected Struct init(ContextEl _context, Struct _parent, String _className, String _fieldName, int _ordinal, CustList<ClassFieldStruct> _fields) {
        return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
    }

    public WindowSetStruct getWindows() {
        return windows;
    }
}
