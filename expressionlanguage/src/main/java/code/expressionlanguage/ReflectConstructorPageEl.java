package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.opers.InvokingOperation;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ReflectConstructorPageEl extends AbstractReflectPageEl {
    
    private boolean initClass;
    private boolean calledMethod;
    @Override
    public boolean checkCondition(ContextEl _context) {
        ConstructorMetaInfo method_ = (ConstructorMetaInfo) getGlobalArgument().getStruct();
        String className_ = method_.getClassName();
        String id_ = Templates.getIdFromAllTypes(className_);
        GeneType type_ = _context.getClassBody(id_);
        if (type_.isAbstractType()) {
            LgNames stds_ = _context.getStandards();
            String null_;
            null_ = stds_.getAliasNullPe();
            _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
            return false;
        }
        if (!Templates.correctClassPartsDynamic(className_, new StringMap<StringList>(), _context, true)) {
            LgNames stds_ = _context.getStandards();
            String null_;
            null_ = stds_.getAliasNullPe();
            _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
            return false;
        }
        if (!initClass) {
            initClass = true;
            if (InvokingOperation.hasToExit(_context, className_)) {
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
            if (args_.size() != mid_.getParametersTypes().size()) {
                LgNames stds_ = _context.getStandards();
                String null_;
                null_ = stds_.getAliasNullPe();
                _context.setException(new StdStruct(new CustomError(_context.joinPages()),null_));
                return false;
            }
            Argument arg_ = InvokingOperation.instancePrepare(_context, className_, mid_, Argument.createVoid(), args_);
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

}
