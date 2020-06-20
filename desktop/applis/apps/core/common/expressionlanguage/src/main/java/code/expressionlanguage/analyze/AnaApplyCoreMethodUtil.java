package code.expressionlanguage.analyze;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.AliasCharSequence;
import code.expressionlanguage.stds.AliasMath;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;

public final class AnaApplyCoreMethodUtil {
    private AnaApplyCoreMethodUtil() {
    }

    public static Struct invokeAnalyzisStdMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        Struct result_ = null;
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        String mathType_ = lgNames_.getAliasMath();
        String stringType_ = lgNames_.getAliasString();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, lgNames_.getAliasResources())) {
            if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesNamesLength())) {
                return ResourcesStruct.getResourceNamesLength(_cont);
            }
            if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesIndex())) {
                return ResourcesStruct.getResourceIndex(_cont,args_[0]);
            }
            if (StringList.quickEq(name_, lgNames_.getAliasReadResources())) {
                result_ = ResourcesStruct.getResource(_cont, getString(args_[0]));
            }
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            if (StringList.quickEq(name_, lgNames_.getAliasSameRef())) {
                result_= BooleanStruct.of(args_[0].sameReference(args_[1]));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetParent())) {
                Struct arg_ = args_[0];
                result_ = arg_.getParent();
                return result_;
            }
        }
        if (StringList.quickEq(type_, replType_)) {
            return ReplacementStruct.calculate(_cont, _method, _struct);
        }
        if (StringList.quickEq(type_, stringType_)
                || StringList.quickEq(type_, lgNames_.getAliasCharSequence())) {
            result_ = AliasCharSequence.invokeAnalyzisStdMethod(_cont, _method, _struct, _args);
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            return AliasMath.invokeAnalyzisStdMethod(_cont, _method, _args);
        }
        return NumberStruct.calculate(_cont, _method, _struct, args_);
    }

    public static Struct newAnalyzisInstanceStd(ContextEl _cont, ConstructorId _method, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String stringType_ = lgNames_.getAliasString();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, replType_)) {
            return ReplacementStruct.instantiate(args_);
        }
        if (StringList.quickEq(type_, stringType_)) {
            StringList list_ = _method.getParametersTypes();
            if (list_.size() == 0) {
                return new StringStruct("");
            }
            return null;
        }
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            return NumberStruct.instantiate(_cont, _method, args_);
        }
        return null;
    }

    public static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    public static StringStruct getString(Struct _previous) {
        if (_previous instanceof StringStruct) {
            return (StringStruct) _previous;
        }
        return new StringStruct("");
    }
}
