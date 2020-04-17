package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.utilcompo.CustInitializer;
import code.expressionlanguage.utilcompo.LgNamesUtils;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.Struct;
import code.util.ObjectMap;

public class GuiInitializer extends CustInitializer {
    private final WindowSetStruct windows = new WindowSetStruct(false);
    @Override
    protected Struct init(ContextEl _context, Struct _parent, String _className, String _fieldName, int _ordinal, ObjectMap<ClassField, Struct> _fields) {
        return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
    }

    public WindowSetStruct getWindows() {
        return windows;
    }
}
