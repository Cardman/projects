package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public abstract class AbstractRefectMethodPageEl extends AbstractRefectCommonMethodPageEl {

    private boolean calledMethod;


    private ArrayStruct args = new ArrayStruct(0,"");
    private final Argument array;
    private Argument rightArg;
    private final ExecMemberCallingsBlock callee;
    private final boolean ref;
    protected AbstractRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo, AbstractPreparer _preparer, boolean _refer) {
        super(_instance, _metaInfo, _preparer,false);
        array = _array;
        callee = _metaInfo.getCallee();
        ref = _refer;
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

    private boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!keep(_context, _stack)) {
            return false;
        }
        if (!calledMethod && !checkCallPhase(_context, _stack)) {
            return false;
        }
        return callPhase(_context, _stack);
    }

    private boolean checkCallPhase(ContextEl _context, StackCall _stack) {
        LgNames stds_ = _context.getStandards();
        calledMethod = true;
        MethodId mid_ = getMetaInfo().getRealId();
        Struct struct_ = getArray().getStruct();
        if (!(struct_ instanceof ArrayStruct)) {
            String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_, _stack)));
            return false;
        }
        args = (ArrayStruct)struct_;
        if (getMetaInfo().isExpCast()) {
            if (args.getLength() + 1 != mid_.getParametersTypesLength()) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.getLength() + 1, mid_.getParametersTypesLength()).toString(), null_, _stack)));
                return false;
            }
        } else if (!StringUtil.quickEq(mid_.getName(),"[]=")) {
            if (args.getLength() != mid_.getParametersTypesLength()) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.getLength(), mid_.getParametersTypesLength()).toString(), null_, _stack)));
                return false;
            }
        } else {
            if (args.getLength() != mid_.getParametersTypesLength() + 1) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args.getLength(), mid_.getParametersTypesLength() + 1).toString(), null_, _stack)));
                return false;
            }
            rightArg = new Argument(args.get(args.getLength()-1));
        }
        return true;
    }

    Argument prepareCall(ContextEl _context, StackCall _stack) {
        return prepare(_context, args, rightArg, _stack);
    }
    abstract Argument prepare(ContextEl _context, ArrayStruct _args, Argument _right, StackCall _stack);

    boolean isRef() {
        return ref;
    }

    Argument getArray() {
        return array;
    }
}
