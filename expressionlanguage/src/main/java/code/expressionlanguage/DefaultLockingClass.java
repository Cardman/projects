package code.expressionlanguage;

import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.StaticBlock;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.InvokeTargetErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;
import code.util.StringMap;

public class DefaultLockingClass {

    private StringMap<InitClassState> classes = new StringMap<InitClassState>();
    private StringList alwayasInit = new StringList();

    public void init(ContextEl _context, StringList _success) {
        Classes cl_ = _context.getClasses();
        for (RootBlock r: cl_.getClassBodies()) {
            String name_ = r.getFullName();
            boolean succ_ = true;
            for (Block b: Classes.getDirectChildren(r)) {
                if (b instanceof InfoBlock) {
                    InfoBlock f_ = (InfoBlock) b;
                    if (!f_.isStaticField()) {
                        continue;
                    }
                    for (String n: f_.getFieldName()) {
                        Struct feed_ = cl_.getStaticField(new ClassField(name_, n));
                        if (feed_ == null) {
                            succ_ = false;
                        }
                    }
                }
                if (b instanceof StaticBlock && !_success.containsStr(name_)) {
                    succ_ = false;
                }
            }
            if (succ_) {
                alwayasInit.add(name_);
                classes.put(name_, InitClassState.SUCCESS);
            } else {
                classes.put(name_, InitClassState.NOT_YET);
            }
        }
    }
    public StringList getAlwayasInit() {
        return alwayasInit;
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
        if (_context.getClasses().getLocks().getAlwayasInit().containsStr(_className)) {
            return InitClassState.SUCCESS;
        }
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
            if (pageEl_ instanceof AbstractReflectPageEl) {
                AbstractReflectPageEl p_ = (AbstractReflectPageEl) pageEl_;
                if (p_.isWrapException()) {
                    InvokeTargetErrorStruct causing_ = new InvokeTargetErrorStruct(_cause);
                    _context.setException(causing_);
                    return;
                }
            }
            return;
        }
        String curClass_ = pageEl_.getGlobalClass();
        errorClass(_context, curClass_);
        CausingErrorStruct causing_ = new CausingErrorStruct(_cause);
        _context.setException(causing_);
    }
    public void errorClass(ContextEl _context, String _className) {
        _context.failInitEnums();
        String base_ = Templates.getIdFromAllTypes(_className);
        classes.put(base_, InitClassState.ERROR);
    }
    protected StringMap<InitClassState> getClasses() {
        return classes;
    }
}
