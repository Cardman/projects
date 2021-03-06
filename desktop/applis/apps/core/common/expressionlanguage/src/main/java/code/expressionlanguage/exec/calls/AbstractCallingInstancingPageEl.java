package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.calls.util.NotInitializedFields;

import code.expressionlanguage.exec.inherits.Parameters;


public abstract class AbstractCallingInstancingPageEl extends AbstractPageEl implements ForwardPageEl {

    private boolean calledImplicitConstructor;

    private boolean firstField;

    private ExecRootBlock blockRootSuperType;

    public boolean isFirstField() {
        return firstField;
    }

    public void setFirstField(boolean _firstField) {
        firstField = _firstField;
    }
    @Override
    public final boolean checkCondition(ContextEl _context, StackCall _stack) {
        boolean implicitConstr_ = false;
        ExecBlock blockRoot_ = getBlockRoot();
        if (!(blockRoot_ instanceof ExecConstructorBlock)) {
            //No constructor found in the current class => call the super one
            implicitConstr_ = true;
        } else if (((ExecConstructorBlock)blockRoot_).implicitConstr()) {
            //Constructor found in the current class but no explicit call to super in the code => call the super one
            implicitConstr_ = true;
        }
        if (implicitConstr_) {
            ExecRootBlock blockRootType_ = getBlockRootType();
            if (blockRootType_ instanceof ExecUniqueRootedBlock) {
                //class or enum (included inner enum)
                String id_ = blockRootType_.getImportedDirectGenericSuperClass();
                if (!calledImplicitConstructor && blockRootSuperType != null) {
                    calledImplicitConstructor = true;
                    Argument global_ = getGlobalArgument();
                    _stack.setCallingState(new CustomFoundConstructor(_stack.formatVarType(id_), blockRootSuperType.getEmptyCtorPair(), EMPTY_STRING, -1, global_, new Parameters(), InstancingStep.USING_SUPER_IMPL));
                    return false;
                }
                //the super constructor is called here
            }
            boolean initFields_ = false;
            ExecBlock bl_ = null;
            if (blockRoot_ instanceof ExecBracedBlock) {
                bl_ = ((ExecBracedBlock)blockRoot_).getFirstChild();
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
                String curClass_ = getGlobalClass();
                _stack.setCallingState(new NotInitializedFields(curClass_, blockRootType_, global_));
                return false;
            }
            //fields of the current class are initialized if there is no interface constructors to call
        }
        return true;
    }

    public void setBlockRootTypes(ExecRootBlock _blockRootType) {
        setBlockRootType(_blockRootType);
        if (getBlockRootType() instanceof ExecUniqueRootedBlock) {
            blockRootSuperType = getBlockRootType().getUniqueType();
        }
    }
}
