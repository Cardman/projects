package code.expressionlanguage;

import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.ObjectMap;
import code.util.StringList;

public class CustInitializer extends DefaultInitializer {

    @Override
    protected Struct init(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal, 
            ObjectMap<ClassField, Struct> _fields) {
        String base_ = StringList.getAllTypes(_className).first();
        String run_ = ((LgNamesUtils)_context.getStandards()).getAliasRunnable();
        if (PrimitiveTypeUtil.canBeUseAsArgument(run_, base_, _context)) {
            return new ContextEl(_context, _className, _fieldName, _ordinal, _fields, _parent);
        }
        return super.init(_context, _parent, _className, _fieldName, _ordinal, _fields);
    }
    @Override
    public void loopCalling(ContextEl _owner) {
        int sizeBk_ = _owner.nbPages() - 1;
        while (true) {
            try {
                Boolean res_ = _owner.removeCall(sizeBk_);
                if (res_ == null) {
                    return;
                }
                if (res_) {
                    continue;
                }
                _owner.processTags();
                AbstractPageEl abs_ = _owner.processAfterOperation();
                if (abs_ != null) {
                    addPage(_owner, abs_);
                }
                if (_owner.getException() != null) {
                    return;
                }
            } catch (OutOfMemoryError _0_) {
                _owner.setException(_owner.getMemoryError());
                _owner.getThrowing().removeBlockFinally(_owner);
                if (_owner.getException() != null) {
                    return;
                }
            }
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
