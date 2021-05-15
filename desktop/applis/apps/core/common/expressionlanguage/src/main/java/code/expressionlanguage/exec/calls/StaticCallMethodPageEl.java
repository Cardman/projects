package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.AbstractParamChecker;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.StaticCallParamChecker;
import code.expressionlanguage.exec.inherits.SwitchParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class StaticCallMethodPageEl extends AbstractRefectMethodPageEl {

    public StaticCallMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont, StackCall _stack) {
        return initDefault(_cont, _stack);
    }

    @Override
    boolean isAbstract(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, CustList<Argument> _args, Argument _right, StackCall _stack) {
        ExecFormattedRootBlock className_ = getClassName();
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(className_.getFormatted(), _context);
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
            ab_ = new SwitchParamChecker(pair_.getType(), (ExecAbstractSwitchMethod) callee_, _args, MethodAccessKind.STATIC_CALL);
        } else {
            ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(pair_, className_,Argument.createVoid(), _args, _context, MethodAccessKind.STATIC_CALL);
            ab_ = new StaticCallParamChecker(pair_, l_);
        }
        return ab_.checkParams(className_,Argument.createVoid(), cache_, _context, _stack);
    }

}
