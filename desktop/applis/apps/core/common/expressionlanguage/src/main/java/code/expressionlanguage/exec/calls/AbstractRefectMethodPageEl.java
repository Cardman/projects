package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecTemplates;
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
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_context, _stack)) {
            return;
        }
        setNullReadWrite();
    }

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
            Argument arg_ = prepare(_context, args, rightArg, _stack);
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

    abstract Argument prepare(ContextEl _context, CustList<Argument> _args, Argument _right, StackCall _stack);

    Argument getArray() {
        return array;
    }
}
