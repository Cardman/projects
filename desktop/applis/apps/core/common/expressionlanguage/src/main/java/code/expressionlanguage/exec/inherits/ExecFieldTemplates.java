package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.core.StringUtil;

public final class ExecFieldTemplates {
    private static final String RETURN_LINE = "\n";

    private ExecFieldTemplates() {
    }

    /** nb calls of getParent - super type - arg object
     use class parent of object
     */
    public static Struct getParent(int _nbAncestors, Struct _current, ContextEl _an, StackCall _stackCall) {
        Struct out_ = getParent(_nbAncestors, _current, _stackCall);
        if (out_ == NullStruct.NULL_VALUE) {
            String npe_ = _an.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, npe_, _stackCall)));
            return _current;
        }
        return out_;
    }

    public static Struct getParent(int _nbAncestors, Struct _current, StackCall _stackCall) {
        Struct arg_ = _current;
        for (int i = 0; i < _nbAncestors; i++) {
            Struct enc_ = arg_;
            Struct par_ = enc_.getParent();
            _stackCall.getInitializingTypeInfos().addSensibleField(enc_, par_);
            arg_=par_;
        }
        return Argument.getNull(arg_);
    }

    static String getBadCastMessage(String _classNameFound, String _className) {
        return StringUtil.concat(_className, RETURN_LINE, _classNameFound, RETURN_LINE);
    }

    public static Argument getField(FieldMetaInfo _meta, Argument _previous, ContextEl _conf, StackCall _stackCall) {
        String baseClass_ = _meta.getFormatted().getFormatted();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        String type_ = _meta.getType();

        ClassField fieldId_ = new ClassField(baseClass_, fieldName_);
        if (isStaticField_) {
            return getStaticField(_conf.getExiting(),_meta.getFormatted().getRootBlock(), type_, _conf, _stackCall, fieldId_);
        }
        return getInstanceField(_previous, _conf, _stackCall, fieldId_);
    }

    public static Argument getSafeInstanceField(int _anc, Argument _previous, ContextEl _conf, StackCall _stackCall, ClassField _fieldId) {
        Argument prev_ = new Argument(getParent(_anc, _previous.getStruct(), _conf, _stackCall));
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        return getInstanceField(prev_, _conf, _stackCall, _fieldId);
    }

    public static Argument getInstanceField(Argument _previous, ContextEl _conf, StackCall _stackCall, ClassField _fieldId) {
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String className_ = _fieldId.getClassName();
        Struct previous_ = _previous.getStruct();
        String argClassName_ = previous_.getClassName(_conf);
        if (!(previous_ instanceof FieldableStruct)) {
            if (previous_ == NullStruct.NULL_VALUE) {
                String npe_;
                npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
                return Argument.createVoid();
            }
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(className_, argClassName_), cast_, _stackCall)));
            return _previous;
        }
        ClassFieldStruct entry_ = ((FieldableStruct) previous_).getEntryStruct(_fieldId);
        if (entry_ == null) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(className_, argClassName_), cast_, _stackCall)));
            return _previous;
        }
        Struct struct_ = entry_.getStruct();
        _stackCall.getInitializingTypeInfos().addSensibleField(previous_, struct_);
        return new Argument(struct_);
    }

    public static Argument getStaticField(AbstractExiting _exit, GeneType _init, String _ret, ContextEl _conf, StackCall _stackCall, ClassField _fieldId) {
        Classes classes_ = _conf.getClasses();
        if (_exit.hasToExit(_stackCall, _init)) {
            return Argument.createVoid();
        }
        Struct struct_ = classes_.getStaticField(_fieldId,_ret, _conf);
        String className_ = _fieldId.getClassName();
        _stackCall.getInitializingTypeInfos().addSensibleField(className_, struct_, _stackCall);
        return new Argument(struct_);
    }

    public static Argument setField(FieldMetaInfo _meta, Argument _previous, Argument _right, ContextEl _conf, StackCall _stackCall) {
        ExecRootBlock declaring_ = _meta.getDeclaring();
        if (declaring_ == null) {
            LgNames stds_ = _conf.getStandards();
            String npe_;
            npe_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return Argument.createVoid();
        }
        String baseClass_ = _meta.getFormatted().getFormatted();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        boolean isFinalField_ = _meta.isFinalField();
        String type_ = _meta.getType();
        ClassField fieldId_ = new ClassField(baseClass_, fieldName_);
        if (isStaticField_) {
            LgNames stds_ = _conf.getStandards();
            if (isFinalField_) {
                String npe_;
                npe_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
                return Argument.createVoid();
            }
            return setStaticField(_conf.getExiting(), declaring_, type_, _right, _conf, _stackCall, fieldId_);
        }
        return setInstanceField(_previous, _right, _conf, _stackCall, fieldId_, new ExecTypeReturn(declaring_, type_));
    }

    public static Argument setSafeInstanceField(int _anc, Argument _previous, Argument _right, ContextEl _conf, StackCall _stackCall, ClassField _fieldId, ExecTypeReturn _ex) {
        Argument prev_ = new Argument(getParent(_anc, _previous.getStruct(), _conf, _stackCall));
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        return setInstanceField(prev_, _right, _conf, _stackCall, _fieldId, _ex);
    }

    public static Argument setInstanceField(Argument _previous, Argument _right, ContextEl _conf, StackCall _stackCall, ClassField _fieldId, ExecTypeReturn _ex) {
        String className_ = _fieldId.getClassName();
        LgNames stds_ = _conf.getStandards();
        Struct previous_ = _previous.getStruct();
        String argClassName_ = previous_.getClassName(_conf);
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        if (!(previous_ instanceof FieldableStruct)) {
            if (previous_ == NullStruct.NULL_VALUE) {
                String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
                return Argument.createVoid();
            }
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(className_, argClassName_), cast_, _stackCall)));
            return Argument.createVoid();
        }
        ClassFieldStruct entry_ = ((FieldableStruct) previous_).getEntryStruct(_fieldId);
        if (entry_ == null) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(className_, argClassName_), cast_, _stackCall)));
            return Argument.createVoid();
        }
        String fieldType_ = formatType(_conf, _ex.getRootBlock(), _ex.getReturnType(),argClassName_);
        if (!ExecInheritsAdv.checkQuick(fieldType_, _right.getStruct().getClassName(_conf), _conf, _stackCall)) {
            return Argument.createVoid();
        }
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(previous_)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return _right;
        }
        entry_.setStruct(_right.getStruct());
        return _right;
    }

    public static Argument setStaticField(AbstractExiting _exit, GeneType _init, String _returnType, Argument _right, ContextEl _conf, StackCall _stackCall, ClassField _fieldId) {
        Classes classes_ = _conf.getClasses();
        if (_exit.hasToExit(_stackCall, _init)) {
            return Argument.createVoid();
        }
        if (!ExecInheritsAdv.checkQuick(_returnType, _right.getStruct().getClassName(_conf), _conf, _stackCall)) {
            return Argument.createVoid();
        }
        String className_ = _fieldId.getClassName();
        if (_stackCall.getInitializingTypeInfos().isSensibleField(className_, _stackCall)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return _right;
        }
        NumParsers.getStaticFieldMap(className_, classes_.getStaticFields()).set(_fieldId.getFieldName(), _right.getStruct());
        return _right;
    }

    public static String formatType(ContextEl _conf, ExecRootBlock _rootBlock, String _lastType, String _cl) {
        String base_ = _rootBlock.getFullName();
        String clGen_ = ExecInherits.getFullObject(_cl, base_, _conf);
        return ExecInherits.quickFormat(_rootBlock,clGen_, _lastType);
    }
}
