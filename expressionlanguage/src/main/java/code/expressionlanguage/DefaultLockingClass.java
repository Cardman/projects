package code.expressionlanguage;

import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.AbstractReflectPageEl;
import code.expressionlanguage.calls.StaticInitPageEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.InvokeTargetErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public class DefaultLockingClass {

    private StringMap<InitClassState> classes = new StringMap<InitClassState>();

    public final void init(ContextEl _context) {
        Classes cl_ = _context.getClasses();
        for (RootBlock r: cl_.getClassBodies()) {
            String name_ = r.getFullName();
            classes.addEntry(name_, InitClassState.NOT_YET);
        }
    }
    public StringList initAlwaysSuccess() {
        StringList notInit_ = new StringList();
        for (EntryCust<String, InitClassState> e: classes.entryList()) {
            if (e.getValue() != InitClassState.SUCCESS) {
                notInit_.add(e.getKey());
                e.setValue(InitClassState.NOT_YET);
            }
        }
        return notInit_;
    }
    public final void initClass(String _className) {
        String base_ = Templates.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.PROGRESSING);
    }
    public final InitClassState getState(String _className) {
        String base_ = Templates.getIdFromAllTypes(_className);
        return classes.getVal(base_);
    }
    public InitClassState getState(ContextEl _context, String _className) {
        String base_ = Templates.getIdFromAllTypes(_className);
        InitClassState old_ = classes.getVal(base_);
        if (old_ == InitClassState.NOT_YET) {
            classes.put(base_, InitClassState.PROGRESSING);
        }
        return old_;
    }
    public void successClass(String _className) {
        String base_ = Templates.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.SUCCESS);
    }
    public final Struct processErrorClass(ContextEl _context, Struct _cause) {
        AbstractPageEl pageEl_ = _context.getLastPage();
        if (!(pageEl_ instanceof StaticInitPageEl)) {
            if (pageEl_ instanceof AbstractReflectPageEl) {
                AbstractReflectPageEl p_ = (AbstractReflectPageEl) pageEl_;
                if (p_.isWrapException()) {
                    return new InvokeTargetErrorStruct(_cause,_context);
                }
            }
            return _cause;
        }
        String curClass_ = pageEl_.getGlobalClass();
        errorClass(_context, curClass_);
        return new CausingErrorStruct(_cause,_context);
    }
    private void errorClass(ContextEl _context, String _className) {
        _context.getInitializingTypeInfos().failInitEnums();
        String base_ = Templates.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.ERROR);
    }
}
