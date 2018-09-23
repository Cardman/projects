package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.opers.InvokingOperation;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;

public final class ReflectConstructorPageEl extends AbstractReflectPageEl {
    
    private boolean initClass;
    private boolean calledMethod;
    @Override
    public boolean checkCondition(ContextEl _context) {
        ConstructorMetaInfo method_ = (ConstructorMetaInfo) getGlobalArgument().getStruct();
        String className_ = method_.getClassName();
        String id_ = Templates.getIdFromAllTypes(className_);
        GeneType type_ = _context.getClassBody(id_);
        boolean static_ = type_.isStaticType();
        if (type_.isAbstractType()) {
            LgNames stds_ = _context.getStandards();
            String null_;
            null_ = stds_.getAliasNullPe();
            _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
            return false;
        }
        String res_ = Templates.correctClassPartsDynamic(className_, _context, true);
        if (res_.isEmpty()) {
            LgNames stds_ = _context.getStandards();
            String null_;
            null_ = stds_.getAliasNullPe();
            _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
            return false;
        }
        if (!initClass) {
            initClass = true;
            if (static_ && InvokingOperation.hasToExit(_context, res_)) {
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
            if (struct_.isNull()) {
                LgNames stds_ = _context.getStandards();
                String null_;
                null_ = stds_.getAliasNullPe();
                _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
                return false;
            }
            for (Struct a: ((Struct[])struct_.getInstance())) {
                Argument a_ = new Argument();
                a_.setStruct(a);
                args_.add(a_);
            }
            Argument previous_;
            if (static_) {
                if (args_.size() != mid_.getParametersTypes().size()) {
                    LgNames stds_ = _context.getStandards();
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
                    return false;
                }
                previous_ = Argument.createVoid();
            } else {
                if (args_.size() != 1 + mid_.getParametersTypes().size()) {
                    LgNames stds_ = _context.getStandards();
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
                    return false;
                }
                previous_ = args_.first();
                args_ = args_.mid(1);
            }
            Argument arg_ = InvokingOperation.instancePrepare(_context, res_, mid_, previous_, args_);
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

}
