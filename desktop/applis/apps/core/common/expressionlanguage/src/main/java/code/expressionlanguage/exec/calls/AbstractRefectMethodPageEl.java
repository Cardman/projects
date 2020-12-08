package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecTemplates;
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

    public AbstractRefectMethodPageEl(Argument _instance,Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance, _metaInfo);
        array = _array;
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        if (!keep(_context)) {
            return false;
        }
        if (!calledMethod) {
            calledMethod = true;
            MethodId mid_ = getMetaInfo().getRealId();
            Struct struct_ = getArray().getStruct();
            if (!(struct_ instanceof ArrayStruct)) {
                String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_)));
                return false;
            }
            args.addAllElts(((ArrayStruct)struct_).listArgs());
            if (getMetaInfo().isExpCast()) {
                if (args.size() + 1 != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.size() + 1, mid_.getParametersTypes().size()).toString(), null_)));
                    return false;
                }
            } else if (!StringUtil.quickEq(mid_.getName(),"[]=")) {
                if (args.size() != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.size(), mid_.getParametersTypes().size()).toString(), null_)));
                    return false;
                }
            } else {
                if (args.size() != mid_.getParametersTypes().size() + 1) {
                    String null_;
                    null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.size(), mid_.getParametersTypes().size() + 1).toString(), null_)));
                    return false;
                }
                rightArg = args.last();
                args = args.left(args.size()-1);
            }
        }
        if (!calledAfter) {
            setWrapException(false);
            MethodId mid_ = getMetaInfo().getRealId();
            Argument arg_ = prepare(_context, getClassName(), mid_, getInstance(), args, rightArg);
            if (_context.getCallingState() instanceof NotInitializedClass) {
                setWrapException(true);
                return false;
            }
            calledAfter = true;
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(getPair(),_className,_instance,_args,_context, getAccessKind());
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, _className, getPair(), _instance, getMetaInfo().getCache(), l_, _right, getAccessKind(), getMethodName());
    }

    Argument getArray() {
        return array;
    }
}
