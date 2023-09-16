package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractRefectLambdaMethodPageEl extends AbstractRefectCommonMethodPageEl {

    private final ArgumentListCall original;
    private final ArgumentListCall array = new ArgumentListCall();
    private final int ref;
    private Struct parent;
    private final LambdaMethodStruct lambdaMethodStruct;

    protected AbstractRefectLambdaMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, AbstractPreparer _preparer, int _r, LambdaMethodStruct _lms) {
        super(_lms.getInstanceCall(), _metaInfo, _preparer,true);
        original = _array;
        ref = _r;
        lambdaMethodStruct = _lms;
    }

    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!tryRetPar(_context, _stack)) {
            return false;
        }
        if (!keep(_context, _stack, ArgumentListCall.toStr(parent))) {
            return false;
        }
        return callPhase(_context, _stack);
    }
    private boolean tryRetPar(ContextEl _context, StackCall _stack) {
        if (parent != null) {
            return true;
        }
        Struct arj_ = AbstractRefectLambdaMethodPageEl.adjustedInstance(_stack, lambdaMethodStruct, original.getArgumentWrappers());
        if (arj_ == null) {
            String npe_ = _context.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, npe_, _stack)));
            return false;
        }
        parent = arj_;
        AbstractRefectLambdaMethodPageEl.trySetArgs(lambdaMethodStruct, original, array);
        return true;
    }
    public static void trySetArgs(LambdaMethodStruct _l,ArgumentListCall _array, ArgumentListCall _call) {
        CustList<ArgumentWrapper> argumentWrappers_ = _array.getArgumentWrappers();
        CustList<ArgumentWrapper> formal_ = formal(argumentWrappers_, _l.getMethodName());
        Argument right_ = right(_l, argumentWrappers_);
        String meth_ = _l.getMethodName();
        Struct instanceStruct_ = _l.getInstanceCall().getStruct();
        if (!_l.isShiftInstance()) {
            _call.getArgumentWrappers().addAllElts(formal_);
            _call.setRight(right_);
        } else if (StringExpUtil.isOper(meth_)) {
            formal_.add(0,new ArgumentWrapper(new Argument(instanceStruct_),null));
            _call.getArgumentWrappers().addAllElts(formal_);
            _call.setRight(right_);
        } else {
            CustList<ArgumentWrapper> arr_ = formal_.mid(1);
            _call.getArgumentWrappers().addAllElts(arr_);
            _call.setRight(right_);
        }
        if (right_ == null && _array.getRight() != null) {
            _call.getArgumentWrappers().add(new ArgumentWrapper(_array.getRight(),null));
        }
    }
    private static Argument right(LambdaMethodStruct _ls, CustList<ArgumentWrapper> _w) {
        Argument right_;
        if (StringUtil.quickEq(_ls.getMethodName(),"[]=")||StringUtil.quickEq(_ls.getMethodName(),"[:]=")) {
            right_ = ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(_w));
        } else {
            right_ = null;
        }
        return right_;
    }

    public static Struct adjustedInstance(StackCall _stackCall, LambdaMethodStruct _lda, CustList<ArgumentWrapper> _formal) {
        int nbAncestors_ = _lda.getAncestor();
        boolean static_ = _lda.getKind() != MethodAccessKind.INSTANCE;
        String meth_ = _lda.getMethodName();
        Struct instanceStruct_ = _lda.getInstanceCall().getStruct();
        if (!_lda.isShiftInstance()) {
            return parentRet(static_, nbAncestors_, instanceStruct_, _stackCall);
        }
        if (StringExpUtil.isOper(meth_)) {
            return NullStruct.NULL_VALUE;
        }
        Struct value_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(_formal)).getStruct();
        return parentRet(static_, nbAncestors_, value_, _stackCall);
    }
    public static Struct parentRet(boolean _static, int _nbAnc, Struct _instanceStruct, StackCall _stackCall) {
        if (!_static) {
            Struct par_ = ExecFieldTemplates.getParent(_nbAnc, _instanceStruct, _stackCall);
            if (par_ == NullStruct.NULL_VALUE) {
                return null;
            }
            return par_;
        }
        return NullStruct.NULL_VALUE;
    }

    public static CustList<ArgumentWrapper> formal(CustList<ArgumentWrapper> _argsCallList, String _meth) {
        CustList<ArgumentWrapper> formal_;
        if (StringUtil.quickEq(_meth,"[]=")||StringUtil.quickEq(_meth,"[:]=")) {
            formal_ = _argsCallList.left(_argsCallList.size()-1);
        } else {
            formal_ = _argsCallList;
        }
        return formal_;
    }
    @Override
    protected boolean checkParams(ContextEl _context, StackCall _stack) {
        return false;
    }

    @Override
    protected boolean postArg(StackCall _stack) {
        return true;
    }

    Argument prepareCall(ContextEl _context, StackCall _stack) {
        return prepare(_context, array, _stack);
    }

    public Struct getParent() {
        return parent;
    }

    public ArgumentListCall getArray() {
        return array;
    }

    abstract Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack);

    public int getRef() {
        return ref;
    }
}
