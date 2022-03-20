package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.AbstractReflectPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.InvokeTargetErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.EntryCust;

public final class DefaultLockingClass {

    private final RootStatus classes = new RootStatus();

    public void init(ContextEl _context) {
        Classes cl_ = _context.getClasses();
        for (ExecRootBlock r: cl_.getClassBodies()) {
            classes.addEntry(r, InitClassState.NOT_YET);
        }
    }
    public void initAlwaysSuccess() {
        for (EntryCust<ExecRootBlock, InitClassState> e: classes.entryList()) {
            if (e.getValue() != InitClassState.SUCCESS) {
                e.setValue(InitClassState.NOT_YET);
            }
        }
    }
    public void initClass(ExecRootBlock _className) {
        classes.put(_className, InitClassState.PROGRESSING);
    }
    public InitClassState getState(ExecRootBlock _className) {
        return classes.getVal(_className);
    }
    public void successClass(ExecRootBlock _className) {
        classes.put(_className, InitClassState.SUCCESS);
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
        ExecRootBlock curClass_ = _lastPage.getGlobalClass().getRootBlock();
        errorClass(curClass_, _stackCall);
        return new CausingErrorStruct(_cause,_context, _stackCall);
    }
    private void errorClass(ExecRootBlock _className, StackCall _stackCall) {
        _stackCall.getInitializingTypeInfos().failInitEnums();
        classes.put(_className, InitClassState.ERROR);
    }
}
