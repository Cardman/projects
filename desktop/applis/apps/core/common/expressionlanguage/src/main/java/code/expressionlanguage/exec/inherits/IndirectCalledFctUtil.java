package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.NativeFct;
import code.expressionlanguage.exec.util.RandCodeNativeFct;
import code.expressionlanguage.exec.util.StrNativeFct;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class IndirectCalledFctUtil {
    private IndirectCalledFctUtil() {
    }

    public static Struct processRandCode(Struct _argument, ContextEl _conf, StackCall _stackCall) {
        StringMap<ExecTypeFunction> redir_ = _conf.getClasses().getRandCodeMethodsToCallBodies();
        return getOrRedirect(new RandCodeNativeFct(),_argument, _conf, _stackCall, redir_);
    }

    public static Struct processString(Struct _argument, ContextEl _conf, StackCall _stackCall) {
        StringMap<ExecTypeFunction> redir_ = _conf.getClasses().getToStringMethodsToCallBodies();
        return getOrRedirect(new StrNativeFct(),_argument, _conf, _stackCall, redir_);
    }

    private static Struct getOrRedirect(NativeFct _nat, Struct _argument, ContextEl _conf, StackCall _stackCall, StringMap<ExecTypeFunction> _redir) {
        Struct struct_ = ArgumentListCall.getNull(_argument);
        String argClassName_ = struct_.getClassName(_conf);
        String idCl_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        ExecTypeFunction valBody_ = _redir.getVal(idCl_);
        if (valBody_ != null) {
            ExecTemplates.prepare(_conf,_stackCall,struct_,valBody_,new CustList<ArgumentWrapper>());
        }
        if (_conf.callsOrException(_stackCall)) {
            return NullStruct.NULL_VALUE;
        }
        return _nat.compute(_argument, _conf);
    }

    public static Struct tryGetEnumValues(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category, StackCall _stackCall) {
        if (isNotEnumType(_r, _category)) {
            return NullStruct.NULL_VALUE;
        }
        return getEnumValues(_exit, _r, _cont, _stackCall);
    }

    public static Struct tryGetEnumValue(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category, Struct _clArg, StackCall _stackCall) {
        if (isNotEnumType(_r, _category)) {
            return NullStruct.NULL_VALUE;
        }
        return getEnumValue(_exit, _r, _clArg, _cont, _stackCall);
    }

    private static boolean isNotEnumType(ExecRootBlock _r, ClassCategory _category) {
        return _r == null || _category != ClassCategory.ENUM;
    }

    private static Struct getEnumValues(AbstractExiting _exit, ExecRootBlock _root, ContextEl _conf, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _root)) {
            return NullStruct.NULL_VALUE;
        }
        String wc_ = _root.getWildCardElement();
        String id_ = StringExpUtil.getIdFromAllTypes(wc_);
        Classes classes_ = _conf.getClasses();
        CustList<Struct> enums_ = new CustList<Struct>();
        for (ExecInnerTypeOrElement b: _root.getEnumElements()) {
            String fieldName_ = b.getUniqueFieldName();
            Struct str_ = classes_.getStaticField(new ClassField(id_, fieldName_),b.getImportedClassName(),_conf);
            _stackCall.getInitializingTypeInfos().addSensibleField(id_, str_, _stackCall);
            enums_.add(str_);
        }
        String clArr_ = wc_;
        clArr_ = StringExpUtil.getPrettyArrayType(clArr_);
        ArrayStruct array_ = new ArrayStruct(enums_.size(), clArr_);
        int i_ = IndexConstants.FIRST_INDEX;
        for (Struct o: enums_) {
            array_.set(i_,o);
            i_++;
        }
        return array_;
    }

    private static Struct getEnumValue(AbstractExiting _exit, ExecRootBlock _root, Struct _name, ContextEl _conf, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _root)) {
            return NullStruct.NULL_VALUE;
        }
        if (!(_name instanceof StringStruct)) {
            return NullStruct.NULL_VALUE;
        }
        Classes classes_ = _conf.getClasses();
        String enumName_ = _root.getFullName();
        for (ExecInnerTypeOrElement b: _root.getEnumElements()) {
            String fieldName_ = b.getUniqueFieldName();
            if (StringUtil.quickEq(fieldName_, ((StringStruct) _name).getInstance())) {
                Struct str_ = classes_.getStaticField(new ClassField(enumName_, fieldName_),b.getImportedClassName(),_conf);
                _stackCall.getInitializingTypeInfos().addSensibleField(enumName_, str_, _stackCall);
                return str_;
            }
        }
        return NullStruct.NULL_VALUE;
    }
}
