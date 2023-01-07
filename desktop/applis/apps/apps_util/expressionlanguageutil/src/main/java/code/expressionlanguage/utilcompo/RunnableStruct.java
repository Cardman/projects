package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class RunnableStruct extends LaunchableStruct {


    RunnableStruct(RunnableContextEl _original, String _className,
                   String _name, int _ordinal,
                   CustList<ClassFieldStruct> _fields, Struct _parent, String _parendClassName) {
        super(_original,_className,_name,_ordinal,_fields,_parent,_parendClassName);
    }
    @Override
    public void run() {
        RunnableContextEl r_ = new RunnableContextEl(this, getExecutionInfos(), getArgs());
        setupThread(r_);
        invoke(this,r_, ((LgNamesWithNewAliases) r_.getStandards()).getExecutingBlocks().getRunnableType(), ((LgNamesWithNewAliases) r_.getStandards()).getExecutingBlocks().getRunMethod(), new ArgumentListCall());
    }

    public static void invoke(Struct _instance, RunnableContextEl _r, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _method, ArgumentListCall _argList) {
        String base_ = StringExpUtil.getIdFromAllTypes(_instance.getClassName(_r));
        ExecOverrideInfo mId_ = _rootBlock.getRedirections().getVal(_method,base_);
        if (mId_ == null) {
            _r.getCustInit().removeThreadFromList(_r);
            return;
        }
        Argument arg_ = new Argument(_instance);
        RunnableStruct.invoke(arg_, mId_.getClassName(), _r, mId_.getPair(), StackCall.newInstance(InitPhase.NOTHING,_r), _argList);
    }
    public static Argument invoke(Struct _global, ExecFormattedRootBlock _class, RunnableContextEl _cont, ExecTypeFunction _pair, StackCall _stackCall, ArgumentListCall _argList) {
        return invoke(new Argument(_global),_class,_cont,_pair,_stackCall,_argList);
    }
    public static Argument invoke(Argument _global, ExecFormattedRootBlock _class, RunnableContextEl _cont, ExecTypeFunction _pair, StackCall _stackCall, ArgumentListCall _argList) {
        Parameters parameters_ = ExecTemplates.wrapAndCall(_pair, _class, _global, _cont, _stackCall, _argList);
        Argument arg_ = ProcessMethod.calculate(new CustomFoundMethod(_global, _class, _pair, parameters_), _cont, _stackCall).getValue();
        boolean err_ = _cont.getCustInit().prExc(_cont, _stackCall);
        if (err_) {
            return null;
        }
        return arg_;
    }
    public static Argument reflect(RunnableContextEl _cont, AbstractReflectElement _ref, StackCall _stackCall) {
        Argument arg_ = ProcessMethod.calculate(_ref, _cont, _stackCall).getValue();
        _cont.getCustInit().prExc(_cont, _stackCall);
        return arg_;
    }
    public static long setupThread(RunnableContextEl _r) {
        long nb_ = _r.getCustInit().increment();
        StringBuilder dtPart_ = new StringBuilder();
        dtPart_.append(CustAliases.getDateTimeText(_r.getCurrentThreadFactory()));
        dtPart_.append("__");
        dtPart_.append(nb_);
        dtPart_.append(".txt");
        _r.getCustInit().putNewCustTreadIdDate(_r, dtPart_.toString());
        return nb_;
    }
}
