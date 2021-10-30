package code.expressionlanguage.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public abstract class FctClassDefaultInstanceAbs implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        String className_ = ((ClassMetaInfo)_instance).getFormatted().getFormatted();
        String id_ = StringExpUtil.getIdFromAllTypes(className_);
        GeneType type_ = _cont.getClassBody(id_);
        if (type_ != null) {
            String res_ = ExecTemplates.correctClassPartsDynamicWildCard(className_, _cont);
            if (res_.isEmpty()) {
                String null_ = lgNames_.getContent().getCoreNames().getAliasIllegalType();
                _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getClassIssue(_cont, className_, null_, _stackCall)));
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
        }
        if (type_ instanceof StandardType) {
            StandardType stdType_ = (StandardType) type_;
            DfInstancer instancer_ = stdType_.getInstancer();
            if (instancer_ == null) {
                String null_ = lgNames_.getContent().getCoreNames().getAliasAbstractTypeErr();
                _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getClassIssue(_cont, className_, null_, _stackCall)));
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
            return instancer_.call(_exit,_cont,_firstArgs,_stackCall);
        }
        if (!(type_ instanceof ExecRootBlock)) {
            String null_ = lgNames_.getContent().getCoreNames().getAliasIllegalType();
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getClassIssue(_cont, className_, null_, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        if (MetaInfoUtil.isAbstractType(type_)) {
            String null_ = lgNames_.getContent().getCoreNames().getAliasAbstractTypeErr();
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getClassIssue(_cont, className_, null_, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return spec(_exit, _cont, _instance, _firstArgs, _stackCall);
    }
    public abstract ArgumentWrapper spec(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall);
}
