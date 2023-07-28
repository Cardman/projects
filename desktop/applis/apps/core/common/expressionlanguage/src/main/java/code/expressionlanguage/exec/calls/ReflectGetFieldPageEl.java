package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.CheckedExecOperationNodeInfos;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.DbgStackStopper;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class ReflectGetFieldPageEl extends AbstractLambdaVariable {

    private boolean initClass;
    private final FieldMetaInfo metaInfo;

    private final Argument argument;

    public ReflectGetFieldPageEl(Argument _argument, FieldMetaInfo _metaInfo, boolean _lambda) {
        super(_lambda);
        argument = _argument;
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }

    @Override
    Argument prepare(ContextEl _context, StackCall _stack) {
        LgNames stds_ = _context.getStandards();
        if (!initClass) {
            initClass = true;
            if (metaInfo.isStaticField() && _context.getExiting().hasToExit(_stack, metaInfo.getFormatted().getRootBlock())) {
                return Argument.createVoid();
            }
        }
        String baseClass_ = metaInfo.getFormatted().getFormatted();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        if (stds_.getStandards().contains(baseClass_)) {
            String name_ =metaInfo.getName();
            ClassField id_ = new ClassField(baseClass_, name_);
            return new Argument(stds_.getSimpleResult(id_));
        }
        Argument arg_ = ExecFieldTemplates.getField(metaInfo, argument, _context, _stack);
        if (_context.callsOrException(_stack)) {
            return Argument.createVoid();
        }
        return arg_;
    }

    @Override
    boolean stopAt(ContextEl _context, StackCall _stack) {
        return _stack.getStopper().isStopAtRefField(metaInfo, _context,_stack);
    }

    @Override
    public CheckedExecOperationNodeInfos infos(ContextEl _context, StackCall _stackCall) {
        if (AbstractLambdaVariable.stopMetaField(metaInfo, _context, _stackCall)) {
            _stackCall.getStackState().resetVisit(true);
            Struct instance_ = ArgumentListCall.toStr(argument);
            ClassField cf_ = new ClassField(StringExpUtil.getIdFromAllTypes(metaInfo.getFormatted().getFormatted()), metaInfo.getName());
            return new CheckedExecOperationNodeInfos(cf_, DbgStackStopper.READ, formatted(_context, cf_, instance_), instance_, null);
        }
        return null;
    }
}
