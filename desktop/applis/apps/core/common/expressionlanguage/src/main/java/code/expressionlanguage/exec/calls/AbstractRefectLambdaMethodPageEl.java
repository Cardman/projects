package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.MethodLambdaParentRetriever;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractRefectLambdaMethodPageEl extends AbstractRefectCommonMethodPageEl {

    private final int ref;
    private final MethodLambdaParentRetriever methodLambdaParentRetriever;
    private boolean entered;

    protected AbstractRefectLambdaMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, AbstractPreparer _preparer, int _r, LambdaMethodStruct _lms) {
        super(_lms.getInstanceCall(), _metaInfo, _preparer,true);
        methodLambdaParentRetriever = new MethodLambdaParentRetriever(_array,_lms);
        ref = _r;
    }

    protected AbstractRefectLambdaMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, AbstractPreparer _preparer, int _r, LambdaMethodStruct _lms,AbstractParamReflectCheckerStepping _a) {
        super(_lms.getInstanceCall(), _metaInfo, _preparer,true, _a);
        methodLambdaParentRetriever = new MethodLambdaParentRetriever(_array,_lms);
        ref = _r;
    }

    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        entered = true;
        if (!methodLambdaParentRetriever.retrieve(_context, _stack)) {
            return false;
        }
        if (!keep(_context, _stack, methodLambdaParentRetriever.getParent())) {
            return false;
        }
        return callPhase(_context, _stack);
    }

    public static void trySetArgs(LambdaMethodStruct _l,ArgumentListCall _array, ArgumentListCall _call) {
        CustList<ArgumentWrapper> merged_ = new CustList<ArgumentWrapper>();
        merged_.addAllElts(_array.getArgumentWrappers());
        if (_array.getRight() != null) {
            merged_.add(new ArgumentWrapper(_array.getRight(),null));
        }
        CustList<ArgumentWrapper> formal_ = formal(merged_, _l.getMethodName());
        Struct right_ = right(_l, merged_);
        String meth_ = _l.getMethodName();
        Struct instanceStruct_ = _l.getInstanceCall();
        if (!_l.isShiftInstance()) {
            _call.getArgumentWrappers().addAllElts(formal_);
            _call.setRight(right_);
        } else if (StringExpUtil.isOper(meth_)) {
            formal_.add(0,new ArgumentWrapper(instanceStruct_,null));
            _call.getArgumentWrappers().addAllElts(formal_);
            _call.setRight(right_);
        } else {
            CustList<ArgumentWrapper> arr_ = formal_.mid(1);
            _call.getArgumentWrappers().addAllElts(arr_);
            _call.setRight(right_);
        }
    }
    private static Struct right(LambdaMethodStruct _ls, CustList<ArgumentWrapper> _w) {
        Struct right_;
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
        Struct instanceStruct_ = _lda.getInstanceCall();
        if (!_lda.isShiftInstance()) {
            return parentRet(static_, nbAncestors_, instanceStruct_, _stackCall);
        }
        if (StringExpUtil.isOper(meth_)) {
            return NullStruct.NULL_VALUE;
        }
        Struct value_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(_formal));
        return parentRet(static_, nbAncestors_, value_, _stackCall);
    }

    public static Struct originalInstance(LambdaMethodStruct _lda, CustList<ArgumentWrapper> _formal) {
        String meth_ = _lda.getMethodName();
        Struct instanceStruct_ = _lda.getInstanceCall();
        if (!_lda.isShiftInstance()) {
            return instanceStruct_;
        }
        if (StringExpUtil.isOper(meth_)) {
            return NullStruct.NULL_VALUE;
        }
        return ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(_formal));
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

    Struct prepareCall(ContextEl _context, StackCall _stack) {
        return prepare(_context, methodLambdaParentRetriever.getArray(), _stack);
    }

    public boolean isEntered() {
        return entered;
    }

    public boolean isCheckedParent() {
        return methodLambdaParentRetriever.getParent() == null;
    }
    public Struct getParent() {
        return methodLambdaParentRetriever.getParent();
    }

    public Struct getOriginalInstance() {
        return methodLambdaParentRetriever.getOriginalInstance();
    }
    public int getAncestor(){
        return methodLambdaParentRetriever.getAncestor();
    }
    public ArgumentListCall getArray() {
        return methodLambdaParentRetriever.getArray();
    }

    abstract Struct prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack);

    public int getRef() {
        return ref;
    }
}
