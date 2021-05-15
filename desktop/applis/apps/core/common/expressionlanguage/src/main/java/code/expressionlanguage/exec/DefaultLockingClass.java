package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.AbstractReflectPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.InvokeTargetErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.EntryCust;
import code.util.StringMap;

public final class DefaultLockingClass {

    private final StringMap<InitClassState> classes = new StringMap<InitClassState>();

    public void init(ContextEl _context) {
        Classes cl_ = _context.getClasses();
        for (ExecRootBlock r: cl_.getClassBodies()) {
            String name_ = r.getFullName();
            classes.addEntry(name_, InitClassState.NOT_YET);
        }
    }
    public void initAlwaysSuccess() {
        for (EntryCust<String, InitClassState> e: classes.entryList()) {
            if (e.getValue() != InitClassState.SUCCESS) {
                e.setValue(InitClassState.NOT_YET);
            }
        }
    }
    public void initClass(String _className) {
        String base_ = StringExpUtil.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.PROGRESSING);
    }
    public InitClassState getState(String _className) {
        String base_ = StringExpUtil.getIdFromAllTypes(_className);
        return classes.getVal(base_);
    }
    public InitClassState getProgressingState(String _className) {
        String base_ = StringExpUtil.getIdFromAllTypes(_className);
        InitClassState old_ = classes.getVal(base_);
        if (old_ == InitClassState.NOT_YET) {
            classes.put(base_, InitClassState.PROGRESSING);
        }
        return old_;
    }
    public void successClass(String _className) {
        String base_ = StringExpUtil.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.SUCCESS);
    }
    public Struct processErrorClass(ContextEl _context, Struct _cause, AbstractPageEl _lastPage, StackCall _stackCall) {
        if (!(_lastPage instanceof StaticInitPageEl)) {
            if (_lastPage instanceof AbstractReflectPageEl) {
                AbstractReflectPageEl p_ = (AbstractReflectPageEl) _lastPage;
                if (p_.isWrapException()) {
                    return new InvokeTargetErrorStruct(_cause,_context, _stackCall);
                }
            }
            return _cause;
        }
        String curClass_ = _lastPage.getGlobalClass().getFormatted();
        errorClass(curClass_, _stackCall);
        return new CausingErrorStruct(_cause,_context, _stackCall);
    }
    private void errorClass(String _className, StackCall _stackCall) {
        _stackCall.getInitializingTypeInfos().failInitEnums();
        String base_ = StringExpUtil.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.ERROR);
    }
}
