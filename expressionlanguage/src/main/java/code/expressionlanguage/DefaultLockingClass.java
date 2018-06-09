package code.expressionlanguage;

import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.StringMap;

public class DefaultLockingClass {

    private StringMap<InitClassState> classes = new StringMap<InitClassState>();

    public void init(ContextEl _context) {
        for (RootBlock r: _context.getClasses().getClassBodies()) {
            String name_ = r.getFullName();
            classes.put(name_, InitClassState.NOT_YET);
        }
    }
    public void initClass(String _className) {
        String base_ = Templates.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.PROGRESSING);
    }
    public InitClassState getState(String _className) {
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
    public void successClass(ContextEl _context, String _className) {
        String base_ = Templates.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.SUCCESS);
    }
    public void processErrorClass(ContextEl _context, Struct _cause) {
        AbstractPageEl pageEl_ = _context.getLastPage();
        if (!(pageEl_ instanceof StaticInitPageEl)) {
            return;
        }
        String curClass_ = pageEl_.getGlobalClass();
        errorClass(_context, curClass_);
        CausingErrorStruct causing_ = new CausingErrorStruct(_cause);
        _context.setException(causing_);
    }
    public void errorClass(ContextEl _context, String _className) {
        String base_ = Templates.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.ERROR);
    }
    protected StringMap<InitClassState> getClasses() {
        return classes;
    }
}
