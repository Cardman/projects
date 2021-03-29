package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundSwitch;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.FormattedParameters;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractRefectMethodPageEl extends AbstractRefectCommonMethodPageEl {

    private boolean calledMethod;
    private boolean calledAfter;

    private CustList<Argument> args = new CustList<Argument>();
    private final Argument array;
    private Argument rightArg;
    private final ExecMemberCallingsBlock callee;
    public AbstractRefectMethodPageEl(Argument _instance,Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance, _metaInfo);
        array = _array;
        callee = _metaInfo.getCallee();
    }

    ExecMemberCallingsBlock getCallee() {
        return callee;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        LgNames stds_ = _context.getStandards();
        if (!keep(_context, _stack)) {
            return false;
        }
        if (!calledMethod) {
            calledMethod = true;
            MethodId mid_ = getMetaInfo().getRealId();
            Struct struct_ = getArray().getStruct();
            if (!(struct_ instanceof ArrayStruct)) {
                String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_, _stack)));
                return false;
            }
            args.addAllElts(((ArrayStruct)struct_).listArgs());
            if (getMetaInfo().isExpCast()) {
                if (args.size() + 1 != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.size() + 1, mid_.getParametersTypes().size()).toString(), null_, _stack)));
                    return false;
                }
            } else if (!StringUtil.quickEq(mid_.getName(),"[]=")) {
                if (args.size() != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.size(), mid_.getParametersTypes().size()).toString(), null_, _stack)));
                    return false;
                }
            } else {
                if (args.size() != mid_.getParametersTypes().size() + 1) {
                    String null_;
                    null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.size(), mid_.getParametersTypes().size() + 1).toString(), null_, _stack)));
                    return false;
                }
                rightArg = args.last();
                args = args.left(args.size()-1);
            }
        }
        if (!calledAfter) {
            setWrapException(false);
            MethodId mid_ = getMetaInfo().getRealId();
            Argument arg_ = prepare(_context, getClassName(), mid_, getInstance(), args, rightArg, _stack);
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

    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right, StackCall _stack) {
        if (callee instanceof ExecAbstractSwitchMethod) {
            ArgumentListCall l_ = ExecTemplates.wrapAndCallDirectSw(_args);
            FormattedParameters formatted_ = ExecTemplates.checkParamsSw(_context, _className, getPair().getType(), (ExecAbstractSwitchMethod) callee, _instance, getMetaInfo().getCache(), l_, getAccessKind(), _stack);
            if (_context.callsOrException(_stack)) {
                return Argument.createVoid();
            }
            Parameters parameters_ = formatted_.getParameters();
            _stack.setCallingState(new CustomFoundSwitch(_instance,formatted_.getFormattedClass(),getPair().getType(),(ExecAbstractSwitchMethod) callee,parameters_.getCache(),new Argument(parameters_.getRefParameters().firstValue().getValue(_stack,_context))));
            return Argument.createVoid();
        }
        ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(getPair(),_className,_instance,_args,_context, getAccessKind());
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, _className, getPair(), _instance, getMetaInfo().getCache(), l_, _right, getAccessKind(), getMethodName(), _stack);
    }

    Argument getArray() {
        return array;
    }
}
