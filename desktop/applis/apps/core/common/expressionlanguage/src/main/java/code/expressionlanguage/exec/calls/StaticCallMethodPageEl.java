package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.AbstractParamChecker;
import code.expressionlanguage.exec.inherits.ExecInheritsAdv;
import code.expressionlanguage.exec.inherits.ReflectStaticCallParamChecker;
import code.expressionlanguage.exec.inherits.SwitchParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;

public final class StaticCallMethodPageEl extends AbstractRefectMethodPageEl {

    public StaticCallMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new DefInitPreparerDir(_metaInfo), _a);
    }

    @Override
    Argument prepare(ContextEl _context, ArrayRefState _args, Argument _right, StackCall _stack) {
        ExecFormattedRootBlock className_ = getClassName();
        String res_ = ExecInheritsAdv.correctClassPartsDynamicNotWildCard(className_.getFormatted(), _context);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_.getFormatted(), null_, _stack)));
            return Argument.createVoid();
        }
        MethodMetaInfo method_ = getMetaInfo();
        if (!StringExpUtil.customCast(className_.getFormatted())) {
            LgNames stds_ = _context.getStandards();
            String null_;
            null_ = stds_.getContent().getCoreNames().getAliasIllegalType();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_.getFormatted(), null_, _stack)));
            return Argument.createVoid();
        }
        ExecMemberCallingsBlock callee_ = getCallee();
        ExecTypeFunction pair_ = getPair();
        Cache cache_ = method_.getCache();
        AbstractParamChecker ab_;
        if (callee_ instanceof ExecAbstractSwitchMethod) {
            ab_ = new SwitchParamChecker((ExecAbstractSwitchMethod) callee_, _args.getArray().listArgs(), MethodAccessKind.STATIC_CALL);
        } else {
            ab_ = new ReflectStaticCallParamChecker(pair_, _args);
        }
        ab_.checkParams(className_,Argument.createVoid(), cache_, _context, _stack);
        return ArgumentListCall.toStr(NullStruct.NULL_VALUE);
    }

}
