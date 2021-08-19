package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.CustInitializer;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicLong;
import code.util.CustList;

public class GuiInitializer extends CustInitializer {
    private final WindowSetStruct windows;

    public GuiInitializer(AbstractAtomicLong _value, AbstractInterceptor _concurrent) {
        super(_value,_concurrent);
        windows = new WindowSetStruct(false,_concurrent);
    }
    @Override
    protected Struct init(ContextEl _context, Struct _parent, ExecFormattedRootBlock _className, String _fieldName, int _ordinal, CustList<ClassFieldStruct> _fields) {
        return new EventStruct(_context,_className.getFormatted(),_fieldName,_ordinal,_fields,_parent, _parent.getClassName(_context));
    }

    public WindowSetStruct getWindows() {
        return windows;
    }
}
