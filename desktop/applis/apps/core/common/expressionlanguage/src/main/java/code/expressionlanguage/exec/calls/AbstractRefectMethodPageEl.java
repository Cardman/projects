package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public abstract class AbstractRefectMethodPageEl extends AbstractReflectPageEl {

    private boolean initClass;
    private ClassMethodId methodToCall;
    private ExecNamedFunctionBlock methodToCallBody;
    private ExecRootBlock methodToCallType;
    private boolean calledMethod;
    private boolean calledAfter;
    private CustList<Argument> args = new CustList<Argument>();
    private Argument instance;
    private Argument rightArg;

    protected boolean initDefault(ContextEl _cont) {
        MethodMetaInfo method_ = NumParsers.getMethod(getGlobalArgument().getStruct());
        return method_.isWideStatic()&&ExecutingUtil.hasToExit(_cont,method_.getClassName());
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        MethodMetaInfo method_ = NumParsers.getMethod(getGlobalArgument().getStruct());
        if (!initClass) {
            initClass = true;
            if (initType(_context)) {
                setWrapException(true);
                return false;
            }
        }
        setWrapException(false);
        if (methodToCallBody == null) {
            if (!method_.isWideStatic()) {
                Argument instance_ = getArguments().first();
                if (instance_.isNull()) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
            }
            if (isAbstract(_context)) {
                String null_;
                null_ = stds_.getAliasIllegalArg();
                _context.setException(new ErrorStruct(_context,null_));
                return false;
            }
            String className_ = method_.getClassName();
            if (isPolymorph(_context)) {
                Struct instance_ = getArguments().first().getStruct();
                ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorph(_context, instance_, method_.getDeclaring(), method_.getCalleeInv());
                methodToCall = new ClassMethodId(polymorph_.getClassName(),method_.getRealId());
                methodToCallBody = polymorph_.getOverridableBlock();
                methodToCallType = polymorph_.getRootBlock();
            } else {
                methodToCall = new ClassMethodId(className_, method_.getRealId());
                methodToCallBody = method_.getCalleeInv();
                methodToCallType = method_.getDeclaring();
            }
        }
        if (!calledMethod) {
            calledMethod = true;
            MethodId mid_ = method_.getRealId();
            if (method_.isWideStatic()) {
                instance = new Argument();
            } else {
                instance = getArguments().first();
            }
            Struct struct_ = getArguments().last().getStruct();
            if (!(struct_ instanceof ArrayStruct)) {
                String null_;
                null_ = stds_.getAliasNullPe();
                _context.setException(new ErrorStruct(_context,null_));
                return false;
            }
            for (Struct a: ((ArrayStruct)struct_).getInstance()) {
                Argument a_ = new Argument();
                a_.setStruct(a);
                args.add(a_);
            }
            if (method_.isExpCast()) {
                if (args.size() + 1 != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
            } else if (!StringList.quickEq(mid_.getName(),"[]=")) {
                if (args.size() != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
            } else {
                if (args.size() != mid_.getParametersTypes().size() + 1) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
                rightArg = args.last();
                args = args.mid(0,args.size()-1);
            }
        }
        if (!calledAfter) {
            setWrapException(false);
            String className_ = methodToCall.getClassName();
            MethodId mid_ = method_.getRealId();
            Argument arg_ = prepare(_context, className_, mid_, instance, args, rightArg);
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
        MethodMetaInfo method_ = NumParsers.getMethod(getGlobalArgument().getStruct());
        return ExecInvokingOperation.callPrepare(new DefaultExiting(_context), _context, _className,methodToCallType, _mid, _instance,method_.getCache(), _args, _right,methodToCallBody);
    }

    ExecNamedFunctionBlock getMethodToCallBody() {
        return methodToCallBody;
    }

    ExecRootBlock getMethodToCallType() {
        return methodToCallType;
    }

    abstract boolean initType(ContextEl _context);
    abstract boolean isAbstract(ContextEl _context);
    abstract boolean isPolymorph(ContextEl _context);
}
