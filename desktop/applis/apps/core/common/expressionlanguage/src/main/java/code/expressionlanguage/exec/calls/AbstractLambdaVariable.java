package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.CheckedExecOperationNodeInfos;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.FieldWrapper;
import code.expressionlanguage.exec.variables.InstanceFieldWrapper;
import code.expressionlanguage.exec.variables.StaticFieldWrapper;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public abstract class AbstractLambdaVariable extends AbstractBasicReflectPageEl {

    private boolean calledAfter;

    protected AbstractLambdaVariable(boolean _lambda) {
        super(_lambda);
    }
    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (_stack.getStopper().isStopAtRef(_context, _stack)) {
            return false;
        }
        _stack.getBreakPointInfo().getStackState().resetVisit(false);
        if (!calledAfter) {
            setWrapException(false);
            Argument arg_ = prepare(_context, _stack);
            if (_stack.getCallingState() instanceof NotInitializedClass) {
                setWrapException(true);
                return false;
            }
            calledAfter = true;
            if (_context.callsOrException(_stack)) {
                setWrapException(_stack.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    abstract Argument prepare(ContextEl _context, StackCall _stack);

    public CheckedExecOperationNodeInfos infosVisited(ContextEl _context, StackCall _stackCall){
        if (_stackCall.getBreakPointInfo().getStackState().visitedExp()) {
            return null;
        }
        return infos(_context, _stackCall);
    }
    public abstract CheckedExecOperationNodeInfos infos(ContextEl _context, StackCall _stackCall);

    public static ExecFormattedRootBlock formatted(ContextEl _ctx, FieldWrapper _w, ClassField _id) {
        if (_w instanceof StaticFieldWrapper) {
            return new ExecFormattedRootBlock(((StaticFieldWrapper)_w).getRoot());
        }
        Struct instance_ = value(_w);
        return formatted(_ctx, _id, instance_);
    }
    public static ExecFormattedRootBlock formatted(ContextEl _ctx, ClassField _id, Struct _inst) {
        String formatted_ = ExecInherits.getQuickFullTypeByBases(_inst.getClassName(_ctx), _id.getClassName(), _ctx);
        ExecRootBlock ex_ = _ctx.getClasses().getClassBody(_id.getClassName());
        return new ExecFormattedRootBlock(ex_, formatted_);
    }

    public static Struct value(AbstractWrapper _f) {
        if (_f instanceof InstanceFieldWrapper) {
            return ((InstanceFieldWrapper)_f).getParent();
        }
        return NullStruct.NULL_VALUE;
    }

    public static boolean stopMetaField(FieldMetaInfo _meta, ContextEl _context, StackCall _stackCall) {
        return !_meta.isStaticField() || _context.getExiting().state(_stackCall, _meta.getFormatted().getRootBlock(), null) == null;
    }
}
