package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AbstractReplacingType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.util.Ints;

public final class FctClassArrayNewInstance extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        String clDyn_ = instanceClass_.getFormatted().getFormatted();
        if (instanceClass_.isTypeWildCard() || instanceClass_.isRefType()) {
            _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        if (clDyn_.contains(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
            _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        if (instanceClass_.isTypeVoid()) {
            _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, _cont.getStandards().getContent().getReflect().getAliasClassNotFoundError(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        if (!ExecInherits.correctNbParameters(clDyn_,_cont)) {
            _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Ints dims_ = new Ints();
        if (!(arg_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        for (Struct s: ((ArrayStruct) arg_).list()) {
            int dim_ = NumParsers.convertToNumber(s).intStruct();
            dims_.add(dim_);
        }
        Struct res_ = ExecArrayTemplates.newCustomArrayOrExc(clDyn_, dims_, _cont, _stackCall);
        if (res_ instanceof ErrorStruct) {
            _stackCall.setCallingState(new CustomFoundExc(res_));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(res_);
    }
}
