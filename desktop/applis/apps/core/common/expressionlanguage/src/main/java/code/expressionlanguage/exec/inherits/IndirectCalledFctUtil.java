package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class IndirectCalledFctUtil {
    private IndirectCalledFctUtil() {
    }

    public static Argument processRandCode(Argument _argument, ContextEl _conf, StackCall _stackCall) {
        StringMap<ExecTypeFunction> redir_ = _conf.getClasses().getRandCodeMethodsToCallBodies();
        return getOrRedirect(new RandCodeNativeFct(),_argument, _conf, _stackCall, redir_);
    }

    public static Argument processString(Argument _argument, ContextEl _conf, StackCall _stackCall) {
        StringMap<ExecTypeFunction> redir_ = _conf.getClasses().getToStringMethodsToCallBodies();
        return getOrRedirect(new StrNativeFct(),_argument, _conf, _stackCall, redir_);
    }

    private static Argument getOrRedirect(NativeFct _nat, Argument _argument, ContextEl _conf, StackCall _stackCall, StringMap<ExecTypeFunction> _redir) {
        Struct struct_ = Argument.getNullableValue(_argument).getStruct();
        String argClassName_ = struct_.getClassName(_conf);
        String idCl_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        ExecTypeFunction valBody_ = _redir.getVal(idCl_);
        ExecFormattedRootBlock clCall_ = ExecFormattedRootBlock.defValue();
        ExecTypeFunction p_ = new ExecTypeFunction((ExecRootBlock) null,null);
        if (valBody_ != null) {
            ExecOverrideInfo polymorphMethod_ = ExecInvokingOperation.polymorph(_conf, struct_, valBody_);
            p_ = polymorphMethod_.getPair();
            clCall_ = ExecFormattedRootBlock.getFullObject(argClassName_, polymorphMethod_.getClassName(), _conf);
        }
        if (p_.getFct() == null) {
            return new Argument(_nat.compute(_argument, _conf));
        }
        Parameters parameters_ = new Parameters();
        Argument out_ = new Argument(struct_);
        _stackCall.setCallingState(new CustomFoundMethod(out_,clCall_, p_, parameters_));
        return out_;
    }

    public static Argument tryGetEnumValues(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category, StackCall _stackCall) {
        if (isNotEnumType(_r, _category)) {
            return new Argument();
        }
        return getEnumValues(_exit, _r, _cont, _stackCall);
    }

    public static Argument tryGetEnumValue(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category, Argument _clArg, StackCall _stackCall) {
        if (isNotEnumType(_r, _category)) {
            return new Argument();
        }
        return getEnumValue(_exit, _r, _clArg, _cont, _stackCall);
    }

    private static boolean isNotEnumType(ExecRootBlock _r, ClassCategory _category) {
        return _r == null || _category != ClassCategory.ENUM;
    }

    private static Argument getEnumValues(AbstractExiting _exit, ExecRootBlock _root, ContextEl _conf, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _root)) {
            return Argument.createVoid();
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
        return new Argument(array_);
    }

    private static Argument getEnumValue(AbstractExiting _exit, ExecRootBlock _root, Argument _name, ContextEl _conf, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _root)) {
            return Argument.createVoid();
        }
        Struct name_ = _name.getStruct();
        if (!(name_ instanceof StringStruct)) {
            return new Argument();
        }
        Classes classes_ = _conf.getClasses();
        String enumName_ = _root.getFullName();
        for (ExecInnerTypeOrElement b: _root.getEnumElements()) {
            String fieldName_ = b.getUniqueFieldName();
            if (StringUtil.quickEq(fieldName_, ((StringStruct) name_).getInstance())) {
                Struct str_ = classes_.getStaticField(new ClassField(enumName_, fieldName_),b.getImportedClassName(),_conf);
                _stackCall.getInitializingTypeInfos().addSensibleField(enumName_, str_, _stackCall);
                return new Argument(str_);
            }
        }
        return new Argument();
    }
}
