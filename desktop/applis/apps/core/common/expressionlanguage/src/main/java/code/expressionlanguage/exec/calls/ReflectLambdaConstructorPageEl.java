package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.InstanceParamChecker;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ReflectLambdaConstructorPageEl extends AbstractReflectConstructorPageEl {

    private final ConstructorMetaInfo metaInfo;

    private final Struct argument;
    private final ArgumentListCall array;
    private final int ref;

    public ReflectLambdaConstructorPageEl(Struct _argument, ArgumentListCall _array, ConstructorMetaInfo _metaInfo, int _r) {
        super(true);
        argument = _argument;
        array = _array;
        metaInfo = _metaInfo;
        setGlobalArgumentStruct(_metaInfo);
        ref = _r;
    }

    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!keep(metaInfo.getDeclType(),_context, _stack)) {
            return false;
        }
        ExecFormattedRootBlock res_ = metaInfo.getFormatted();
        setWrapException(false);
        setCheckingEntryExit(true);
        if (checkParams(_stack,metaInfo)) {
            return false;
        }
        setCheckingEntryExit(false);
        if (isCalledMethod()) {
            return postArg(metaInfo,_stack);
        }
        setCalledMethod(true);
        ConstructorId mid_ = metaInfo.getRealId();
        Struct argCtor_ = NullStruct.NULL_VALUE;
        ExecTypeFunction pair_ = metaInfo.getPair();
        ExecRootBlock execSuperClass_ = pair_.getType();
        if (execSuperClass_ != null) {
            new InstanceParamChecker(pair_, array, "", -1).checkParams(res_, argument, null, _context, _stack);
        }
        if (metaInfo.getStandardType() != null) {
            argCtor_ = ParamCheckerUtil.instancePrepareStd(_context, metaInfo.getStandardConstructor(), mid_, array, _stack).getValue();
        }
        return end(_context,_stack,metaInfo,argCtor_);
    }

    public ArgumentListCall getArray() {
        return array;
    }

    public ConstructorMetaInfo getMetaInfo() {
        return metaInfo;
    }

    @Override
    protected ExecFormattedRootBlock getFormatted() {
        return metaInfo.getFormatted();
    }

    public int getRef() {
        return ref;
    }
}
