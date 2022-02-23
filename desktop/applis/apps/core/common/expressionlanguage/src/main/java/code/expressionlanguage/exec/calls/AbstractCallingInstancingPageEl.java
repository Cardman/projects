package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.NotInitializedFields;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.IdList;


public abstract class AbstractCallingInstancingPageEl extends AbstractPageEl implements ForwardPageEl {

    private boolean calledImplicitConstructor;

    private boolean firstField;
    private final IdList<ExecRootBlock> visited = new IdList<ExecRootBlock>();

    protected AbstractCallingInstancingPageEl(ExecFormattedRootBlock _global) {
        super(_global);
    }

    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        basicReceive(_wrap, _argument,_context, _stack);
    }

    public final boolean checkCondition(StackCall _stack) {
        ExecRootBlock blockRootType_ = getBlockRootType();
        //class or enum (included inner enum)
        ExecFormattedRootBlock formattedSuperClass_ = blockRootType_.getFormattedSuperClass();
        if (!calledImplicitConstructor && formattedSuperClass_ != null) {
            calledImplicitConstructor = true;
            Argument global_ = getGlobalArgument();
            _stack.setCallingState(new CustomFoundConstructor(StackCall.formatVarType(_stack,formattedSuperClass_), formattedSuperClass_.getRootBlock().getEmptyCtorPair(), global_));
            return false;
        }
        //the super constructor is called here
        for (ExecFormattedRootBlock f : blockRootType_.getInstanceInitImportedInterfaces()) {
            ExecRootBlock r_ = f.getRootBlock();
            if (visited.containsObj(r_)) {
                continue;
            }
            visited.add(r_);
            Argument global_ = getGlobalArgument();
            _stack.setCallingState(new CustomFoundConstructor(StackCall.formatVarType(_stack,f), r_.getEmptyCtorPair(), global_));
            return false;
        }
        ExecBlock blockRoot_ = getBlockRoot();
        ExecBlock bl_ = null;
        if (blockRoot_ instanceof ExecBracedBlock) {
            bl_ = ((ExecBracedBlock)blockRoot_).getFirstChild();
        }
//        boolean initFields_ = initFields(bl_);
//        //initialize fields if there is no interface constructors to call
//        if (!firstField && initFields_) {
//            firstField = true;
//            _stack.setCallingState(new NotInitializedFields(this));
//            return false;
//        }
//        //fields of the current class are initialized if there is no interface constructors to call
        return !hasToInitFields(bl_,_stack);
    }

    public boolean hasToInitFields(ExecBlock _bl, StackCall _stack) {
        boolean initFields_ = initFields(_bl);
        //initialize fields if there is no interface constructors to call
        if (!firstField && initFields_) {
            firstField = true;
            _stack.setCallingState(new NotInitializedFields(this));
            return true;
        }
        //fields of the current class are initialized if there is no interface constructors to call
        return false;
    }
    private static boolean initFields(ExecBlock _bl) {
        if (!(_bl instanceof ExecLine)) {
            return true;
        }
        ExecLine l_ = (ExecLine) _bl;
        return !l_.isCallInts();
    }

    public void blockRootTypes(ExecTypeFunction _pair) {
        ExecRootBlock type_ = _pair.getType();
        setBlockRoot(_pair.getFct());
        fileTypeOffset(type_);
    }

    public void fileTypeOffset(ExecRootBlock _type) {
        setFile(_type.getFile());
        globalOffset(_type.getIdRowCol());
    }
}
