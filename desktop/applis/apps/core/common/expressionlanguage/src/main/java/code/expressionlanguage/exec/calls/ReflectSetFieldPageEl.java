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
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class ReflectSetFieldPageEl extends AbstractLambdaVariable {

    private boolean initClass;
    private final FieldMetaInfo metaInfo;

    private final Argument first;
    private final Argument last;

    public ReflectSetFieldPageEl(Argument _first, Argument _last, FieldMetaInfo _metaInfo, boolean _lambda) {
        super(_lambda);
        first = _first;
        last = _last;
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }

    @Override
    Argument prepare(ContextEl _context, StackCall _stack) {
        if (!initClass) {
            initClass = true;
            if (metaInfo.isStaticField() && _context.getExiting().hasToExit(_stack, metaInfo.getFormatted().getRootBlock())) {
                return Argument.createVoid();
            }
        }
        Argument arg_ = ExecFieldTemplates.setField(metaInfo, first, last, _context, _stack);
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
            _stackCall.getBreakPointInfo().getStackState().resetVisit(true);
            Struct instance_ = ArgumentListCall.toStr(first);
            Struct right_ = ArgumentListCall.toStr(last);
            ClassField cf_ = new ClassField(StringExpUtil.getIdFromAllTypes(metaInfo.getFormatted().getFormatted()), metaInfo.getName());
            return new CheckedExecOperationNodeInfos(cf_, DbgStackStopper.WRITE, formatted(_context, cf_, instance_), instance_, right_);
        }
        return null;
    }
}
