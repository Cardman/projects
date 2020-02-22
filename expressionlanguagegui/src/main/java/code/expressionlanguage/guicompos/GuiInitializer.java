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
        String base_ = Templates.getIdFromAllTypes(_className);
        String actList_ = ((LgNamesGui) _context.getStandards()).getAliasActionListener();
        String mouseList_ = ((LgNamesGui) _context.getStandards()).getAliasMouseListener();
        String wheelList_ = ((LgNamesGui) _context.getStandards()).getAliasWheelListener();
        String keyList_ = ((LgNamesGui) _context.getStandards()).getAliasKeyListener();
        String windowList_ = ((LgNamesGui) _context.getStandards()).getAliasWindowListener();
        String listSel_ = ((LgNamesGui) _context.getStandards()).getAliasListSelection();
        String change_ = ((LgNamesGui) _context.getStandards()).getAliasChangeListener();
        String tree_ = ((LgNamesGui) _context.getStandards()).getAliasTreeListener();
        String table_ = ((LgNamesGui) _context.getStandards()).getAliasTableListener();
        String run_ = ((LgNamesUtils)_context.getStandards()).getAliasRunnable();
        if (PrimitiveTypeUtil.canBeUseAsArgument(actList_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(run_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(keyList_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(mouseList_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(windowList_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(listSel_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(change_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(tree_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(table_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(wheelList_, base_, _context)) {
            return new EventStruct(_context,_className,_fieldName,_ordinal,_fields,_parent);
        }
        return super.init(_context, _parent, _className, _fieldName, _ordinal, _fields);
    }

    public WindowSetStruct getWindows() {
        return windows;
    }
}
