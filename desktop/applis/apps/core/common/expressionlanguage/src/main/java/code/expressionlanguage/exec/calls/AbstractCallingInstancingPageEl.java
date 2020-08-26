package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.calls.util.NotInitializedFields;
import code.expressionlanguage.exec.calls.util.ReadWrite;

import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.StringList;


public abstract class AbstractCallingInstancingPageEl extends AbstractPageEl implements ForwardPageEl {

    private boolean calledImplicitConstructor;

    private boolean firstField;

    public boolean isFirstField() {
        return firstField;
    }

    public void setFirstField(boolean _firstField) {
        firstField = _firstField;
    }

    @Override
    public void tryProcessEl(ContextEl _context) {
        //constructor walk through (class, enum, interface)
        ReadWrite rw_ = getReadWrite();
        ExecBlock en_ = rw_.getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context);
            return;
        }
        setNullReadWrite();
    }
    @Override
    public final boolean checkCondition(ContextEl _context) {
        Classes classes_ = _context.getClasses();
        boolean implicitConstr_ = false;
        ExecConstructorBlock ctor_ = (ExecConstructorBlock) getBlockRoot();
        if (ctor_ == null) {
            //No constructor found in the current class => call the super one
            implicitConstr_ = true;
        } else if (ctor_.implicitConstr()) {
            //Constructor found in the current class but no explicit call to super in the code => call the super one
            implicitConstr_ = true;
        }
        if (implicitConstr_) {
            String curClass_ = getGlobalClass();
            String curClassBase_ = StringExpUtil.getIdFromAllTypes(curClass_);
            ExecRootBlock class_ = classes_.getClassBody(curClassBase_);
            if (class_ instanceof ExecUniqueRootedBlock) {
                //class or enum (included inner enum)
                ExecUniqueRootedBlock root_ = (ExecUniqueRootedBlock) class_;
                String id_ = root_.getImportedDirectGenericSuperClass();
                String superClassBase_ = StringExpUtil.getIdFromAllTypes(id_);
                ExecRootBlock execSuperClass_ = classes_.getClassBody(superClassBase_);
                if (!calledImplicitConstructor && execSuperClass_ != null) {
                    calledImplicitConstructor = true;
                    ConstructorId super_ = new ConstructorId(superClassBase_, new StringList(), false);
                    Argument global_ = getGlobalArgument();
                    CustList<ExecConstructorBlock> ctors_ = ExecBlock.getConstructorBodiesById(_context, superClassBase_, super_);
                    ExecConstructorBlock e_ = null;
                    if (!ctors_.isEmpty()) {
                        e_ = ctors_.first();
                    }
                    _context.setCallingState(new CustomFoundConstructor(formatVarType(id_,_context), execSuperClass_,EMPTY_STRING, -1, e_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER_IMPL));
                    return false;
                }
                //the super constructor is called here
            }
            boolean initFields_ = false;
            ExecBlock bl_ = null;
            if (ctor_ != null) {
                bl_ = ctor_.getFirstChild();
            }
            if (!(bl_ instanceof ExecLine)) {
                initFields_ = true;
            } else {
                ExecLine l_ = (ExecLine) bl_;
                if (!l_.isCallInts()) {
                    initFields_ = true;
                }
            }
            //initialize fields if there is no interface constructors to call
            if (!firstField && initFields_) {
                firstField = true;
                Argument global_ = getGlobalArgument();
                _context.setCallingState(new NotInitializedFields(curClass_, global_));
                return false;
            }
            //fields of the current class are initialized if there is no interface constructors to call
        }
        return true;
    }

    @Override
    public final void forwardTo(AbstractPageEl _page, ContextEl _context) {
        Argument a_ = getGlobalArgument();
        _page.receive(a_, _context);
    }
}
