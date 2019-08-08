package code.expressionlanguage;

import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.Struct;
import code.util.ObjectMap;

public class GuiInitializer extends CustInitializer {
    @Override
    protected Struct init(ContextEl _context, Struct _parent, String _className, String _fieldName, int _ordinal, ObjectMap<ClassField, Struct> _fields) {
        String base_ = Templates.getIdFromAllTypes(_className);
        String actList_ = ((LgNamesGui) _context.getStandards()).getAliasActionListener();
        String mouseList_ = ((LgNamesGui) _context.getStandards()).getAliasMouseListener();
        String windowList_ = ((LgNamesGui) _context.getStandards()).getAliasWindowListener();
        String run_ = ((LgNamesUtils)_context.getStandards()).getAliasRunnable();
        if (PrimitiveTypeUtil.canBeUseAsArgument(actList_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(run_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(mouseList_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(windowList_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        return super.init(_context, _parent, _className, _fieldName, _ordinal, _fields);
    }
}
