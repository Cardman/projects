package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FctClassDefaultInstance1 extends FctClassDefaultInstanceAbs {
    @Override
    public ArgumentWrapper spec(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        String className_ = ((ClassMetaInfo)_instance).getFormatted().getFormatted();
        String id_ = StringExpUtil.getIdFromAllTypes(className_);
        GeneType type_ = _cont.getClassBody(id_);
        ExecRootBlock root_ = (ExecRootBlock) type_;
        ExecFormattedRootBlock formType_ = new ExecFormattedRootBlock(root_, className_);
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct par_ = argumentWrappers_.get(0).getValue().getStruct();
        if (root_.withoutInstance()) {
            par_ = NullStruct.NULL_VALUE;
        } else {
            if (par_ == NullStruct.NULL_VALUE) {
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_cont, _stackCall)));
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
            String argCl_ = par_.getClassName(_cont);
            //From analyze
            StringList inners_ = StringExpUtil.getAllPartInnerTypes(className_);
            String param_ = StringUtil.join(inners_.left(inners_.size() - 2), "");
            if (!ExecInherits.isCorrectExecute(argCl_, param_, _cont)) {
                String cast_ = lgNames_.getContent().getCoreNames().getAliasCastType();
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getClassIssue(_cont, StringUtil.concat(argCl_, "\n", param_, "\n"), cast_, _stackCall)));
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
        }
        Initializer in_ = _cont.getInit();
        String genStr_ = root_.getGenericString();
        String form_ = ExecInherits.quickFormat(formType_, genStr_);
        par_ = in_.processInit(_cont, par_, new ExecFormattedRootBlock(root_, form_), "", 0);
        return new ArgumentWrapper(par_);
    }
}
