package code.expressionlanguage;

import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.util.ObjectMap;

public class CustInitializer extends DefaultInitializer {

    @Override
    protected Struct init(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal, 
            ObjectMap<ClassField, Struct> _fields) {
        String base_ = Templates.getIdFromAllTypes(_className);
        String run_ = ((LgNamesUtils)_context.getStandards()).getAliasRunnable();
        if (PrimitiveTypeUtil.canBeUseAsArgument(run_, base_, _context)) {
            return new ContextEl(_context, _className, _fieldName, _ordinal, _fields, _parent);
        }
        return super.init(_context, _parent, _className, _fieldName, _ordinal, _fields);
    }
    @Override
    protected boolean simpleCall(ContextEl _owner) {
        try {
            return super.simpleCall(_owner);
        } catch (OutOfMemoryError _0) {
            _owner.setException(_owner.getMemoryError());
            _owner.getThrowing().removeBlockFinally(_owner);
            return !_owner.hasException();
        }
    }
    @Override
    public String getInterfaceTask(LgNames _stds) {
        return ((LgNamesUtils)_stds).getAliasRunnable();
    }
    @Override
    public String getRunTask(LgNames _stds) {
        return ((LgNamesUtils)_stds).getAliasRun();
    }
}
