package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReflectConstructorPageEl extends AbstractReflectPageEl {
    
    private boolean initClass;
    private boolean calledMethod;
    @Override
    public boolean checkCondition(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        ConstructorMetaInfo method_ = NumParsers.getCtor(getGlobalArgument().getStruct());
        String className_ = method_.getClassName();
        String id_ = StringExpUtil.getIdFromAllTypes(className_);
        GeneType type_ = _context.getClassBody(id_);
        boolean static_ = type_.isStaticType();
        if (ContextUtil.isAbstractType(type_)) {
            String null_;
            null_ = stds_.getAliasIllegalArg();
            _context.setException(new ErrorStruct(_context,null_));
            return false;
        }
        String res_ = ExecTemplates.correctClassPartsDynamic(className_, _context, true);
        if (res_.isEmpty()) {
            String null_;
            null_ = stds_.getAliasIllegalArg();
            _context.setException(new ErrorStruct(_context,null_));
            return false;
        }
        if (!initClass) {
            initClass = true;
            if (static_ && ExecutingUtil.hasToExit(_context,res_)) {
                setWrapException(true);
                return false;
            }
        }
        setWrapException(false);
        if (!calledMethod) {
            calledMethod = true;
            ConstructorId mid_ = method_.getRealId();
            CustList<Argument> args_ = new CustList<Argument>();
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
                args_.add(a_);
            }
            Argument previous_;
            if (static_) {
                if (args_.size() != mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
                previous_ = Argument.createVoid();
            } else {
                if (args_.size() != 1 + mid_.getParametersTypes().size()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _context.setException(new ErrorStruct(_context,null_));
                    return false;
                }
                previous_ = args_.first();
                args_ = args_.mid(1);
            }
            Argument arg_ = ExecInvokingOperation.instancePrepare(_context, res_, mid_, previous_, args_);
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

}
