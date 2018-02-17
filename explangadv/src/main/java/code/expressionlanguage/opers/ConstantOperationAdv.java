package code.expressionlanguage.opers;

import java.lang.reflect.Field;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.opers.util.ClassArgumentMatchingAdv;
import code.expressionlanguage.stds.LgNames;
import code.serialize.exceptions.BadAccessException;
import code.util.StringList;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ConstantOperationAdv extends OperationNodeAdv {
//    ArgumentCall getCommonArgument(Argument _argument, Argument _previous, ContextEl _conf,
//            String _op) {
//        LgNames stds_ = _conf.getStandards();
//        String null_;
//        String cast_;
//        null_ = stds_.getAliasNullPe();
//        cast_ = stds_.getAliasCast();
//        Argument a_ = new Argument();
//        int relativeOff_ = getOperations().getOffset();
//        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
//        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        PageEl ip_ = _conf.getLastPage();
//        if (getOperations().getConstType() == ConstType.THIS_KEYWORD) {
//            Struct struct_ = ip_.getGlobalArgument().getStruct();
//            a_ = new Argument();
//            a_.setStruct(struct_);
//            return ArgumentCall.newArgument(a_);
//        }
//        if (fieldId != null) {
//            Classes classes_ = _conf.getClasses();
//            Argument arg_ = _previous;
//            if (resultCanBeSet()) {
//                return ArgumentCall.newArgument(arg_);
//            }
//            String className_ = fieldId.getClassName();
//            if (fieldMetaInfo.isStaticField()) {
//                if (classes_.isCustomType(className_)) {
//                    if (!_conf.getClasses().isInitialized(className_)) {
//                        _conf.getClasses().initialize(className_);
//                        InitializatingClass inv_ = new InitializatingClass(className_);
//                        return ArgumentCall.newCall(inv_);
//                    }
//                    Struct struct_ = classes_.getStaticField(fieldId);
//                    a_ = new Argument();
//                    a_.setStruct(struct_);
//                    return ArgumentCall.newArgument(a_);
//                }
//                ResultErrorStd res_ = LgNames.getField(_conf, fieldId, NullStruct.NULL_VALUE);
//                if (res_.getError() != null) {
//                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
//                }
//                a_ = new Argument();
//                a_.setStruct(res_.getResult());
//                return ArgumentCall.newArgument(a_);
//            }
//            if (arg_.isNull()) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//            }
//            String argClassName_ = arg_.getObjectClassName(_conf);
//            String classNameFound_ = fieldId.getClassName();
//            String base_ = StringList.getAllTypes(argClassName_).first();
//            if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
//                throw new InvokeException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
//            }
//            if (arg_.getStruct() instanceof FieldableStruct) {
//                Struct struct_ = ((FieldableStruct) arg_.getStruct()).getStruct(fieldId);
//                a_ = new Argument();
//                a_.setStruct(struct_);
//                return ArgumentCall.newArgument(a_);
//            }
//            Struct default_ = arg_.getStruct();
//            ResultErrorStd res_ = LgNames.getField(_conf, fieldId, default_);
//            if (res_.getError() != null) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
//            }
//            a_ = new Argument();
//            a_.setStruct(res_.getResult());
//            return ArgumentCall.newArgument(a_);
//        }
//        OperationsSequence op_ = getOperations();
//        if (op_.getConstType() == ConstType.PARAM) {
//            LocalVariable locVar_ = ip_.getParameters().getVal(variableName);
//            a_ = new Argument();
//            a_.setStruct(locVar_.getStruct());
//            return ArgumentCall.newArgument(a_);
//        }
//        if (op_.getConstType() == ConstType.CATCH_VAR) {
//            LocalVariable locVar_ = ip_.getCatchVars().getVal(variableName);
//            a_ = new Argument();
//            a_.setStruct(locVar_.getStruct());
//            return ArgumentCall.newArgument(a_);
//        }
//        if (op_.getConstType() == ConstType.LOC_VAR) {
//            if (resultCanBeSet()) {
//                return ArgumentCall.newArgument(Argument.createVoid());
//            }
//            LocalVariable locVar_ = ip_.getLocalVars().getVal(variableName);
//            a_ = new Argument();
//            a_.setStruct(locVar_.getStruct());
//            return ArgumentCall.newArgument(a_);
//        }
//        if (op_.getConstType() == ConstType.LOOP_INDEX) {
//            LoopVariable locVar_ = ip_.getVars().getVal(variableName);
//            a_ = new Argument();
//            ClassArgumentMatching clArg_ = new ClassArgumentMatching(locVar_.getIndexClassName());
//            a_.setStruct(PrimitiveTypeUtil.convertObject(clArg_, new LongStruct(locVar_.getIndex()), _conf));
//            return ArgumentCall.newArgument(a_);
//        }
//        if (op_.getConstType() == ConstType.LOOP_VAR) {
//            LoopVariable locVar_ = ip_.getVars().getVal(variableName);
//            a_ = new Argument();
//            a_.setStruct(locVar_.getStruct());
//            return ArgumentCall.newArgument(a_);
//        }
//        ClassArgumentMatching cl_;
//        if (isIntermediateDottedOperation()) {
//            cl_ = getPreviousResultClass();
//        } else {
//            cl_ = new ClassArgumentMatching(_conf.getLastPage().getGlobalClass());
//        }
//        Argument arg_ = _previous;
//        if (cl_.isArray()) {
//            if (arg_.isNull()) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//            }
//            a_ = new Argument();
//            a_.setStruct(new IntStruct(LgNames.getLength(arg_.getObject())));
//            return ArgumentCall.newArgument(a_);
//        }
//        if (resultCanBeSet()) {
//            return ArgumentCall.newArgument(arg_);
//        }
//        Object obj_ = arg_.getObject();
//        if (!Modifier.isStatic(field.getModifiers()) && obj_ == null) {
//            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//        }
//        Object res_;
//        try {
//            res_ = ConverterMethod.getField(field, obj_);
//        } catch (Throwable _0) {
//            String err_ = _conf.getStandards().getAliasError();
//            throw new ErrorCausingException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
//        }
//        a_ = new Argument();
//        Type type_ = field.getGenericType();
//        String pre_ = NativeTypeUtil.getFormattedType(field.getType().getName(), type_.toString(), 0, type_);
//        a_.setStruct(StdStruct.wrapStd(res_, pre_));
//        return ArgumentCall.newArgument(a_);
//    }
//    Argument getCommonSetting(Argument _argument, Argument _previous, ContextEl _conf, String _op) {
//        PageEl ip_ = _conf.getLastPage();
//        LgNames stds_ = _conf.getStandards();
//        String null_;
//        String cast_;
//        null_ = stds_.getAliasNullPe();
//        cast_ = stds_.getAliasCast();
//        int relativeOff_ = getOperations().getOffset();
//        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
//        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        OperationsSequence op_ = getOperations();
//        if (op_.getConstType() == ConstType.LOC_VAR) {
//            LocalVariable locVar_ = ip_.getLocalVars().getVal(variableName);
//            Argument left_ = new Argument();
//            left_.setStruct(locVar_.getStruct());
//            Argument right_ = ip_.getRightArgument();
//            String formattedClassVar_ = locVar_.getClassName();
//            formattedClassVar_ = _conf.getLastPage().formatVarType(formattedClassVar_, _conf);
//            if (PrimitiveTypeUtil.primitiveTypeNullObject(formattedClassVar_, right_.getStruct(), _conf)) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//            }
//            if (!right_.isNull() && !NumericOperation.convert(_op)) {
//                Mapping mapping_ = new Mapping();
//                String base_ = right_.getObjectClassName(_conf);
//                mapping_.setArg(base_);
//                mapping_.setParam(formattedClassVar_);
//                if (!Templates.isCorrect(mapping_, _conf)) {
//                    throw new InvokeException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,formattedClassVar_,RETURN_LINE,_conf.joinPages())),cast_));
//                }
//            }
//            Argument res_;
//            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
//            if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
//                ClassArgumentMatching cl_ = new ClassArgumentMatching(locVar_.getClassName());
//                res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
//            }
//            locVar_.setStruct(res_.getStruct());
//            return res_;
//        }
//        Argument argument_ = _argument;
//        Argument right_ = ip_.getRightArgument();
//        Argument left_ = new Argument();
//        Argument res_;
//        if (fieldId != null) {
//            String fieldType_;
//            if (!fieldMetaInfo.isStaticField()) {
//                String argClassName_ = argument_.getObjectClassName(_conf);
//                String classNameFound_ = fieldId.getClassName();
//                classNameFound_ = StringList.getAllTypes(classNameFound_).first();
//                classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
//                fieldType_ = fieldMetaInfo.getRealType();
//                fieldType_ = Templates.format(classNameFound_, fieldType_, _conf);
//            } else {
//                fieldType_ = fieldMetaInfo.getRealType();
//            }
//            Classes classes_ = _conf.getClasses();
//            if (PrimitiveTypeUtil.primitiveTypeNullObject(fieldType_, right_.getStruct(), _conf)) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//            }
//            Struct structField_ = null;
//            String className_ = fieldId.getClassName();
//            if (fieldMetaInfo.isStaticField()) {
//                structField_ = classes_.getStaticField(fieldId);
//                if (!right_.isNull() && !NumericOperation.convert(_op)) {
//                    Mapping map_ = new Mapping();
//                    String rightClass_ = right_.getObjectClassName(_conf);
//                    map_.setArg(rightClass_);
//                    map_.setParam(fieldType_);
//                    if (!Templates.isCorrect(map_, _conf)) {
//                        throw new InvokeException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
//                    }
//                }
//                left_.setStruct(structField_);
//                res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
//                if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
//                    ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
//                    res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
//                }
//                if (classes_.isCustomType(className_)) {
//                    classes_.initializeStaticField(fieldId, res_.getStruct());
//                    Argument a_ = res_;
//                    return a_;
//                }
//                ResultErrorStd result_ = LgNames.getField(_conf, fieldId, NullStruct.NULL_VALUE);
//                if (result_.getError() != null) {
//                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
//                }
//                structField_ = result_.getResult();
//                left_.setStruct(structField_);
//                res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
//                result_ = LgNames.setField(_conf, fieldId, NullStruct.NULL_VALUE, res_.getStruct());
//                if (result_.getError() != null) {
//                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
//                }
//                Argument a_ = res_;
//                return a_;
//            }
//            if (argument_.isNull()) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//            }
//            String argClassName_ = argument_.getObjectClassName(_conf);
//            String classNameFound_ = fieldId.getClassName();
//            String base_ = StringList.getAllTypes(argClassName_).first();
//            if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
//                throw new InvokeException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
//            }
//            if (argument_.getStruct() instanceof FieldableStruct) {
//                structField_ = ((FieldableStruct) argument_.getStruct()).getStruct(fieldId);
//                if (!right_.isNull() && !NumericOperation.convert(_op)) {
//                    Mapping map_ = new Mapping();
//                    String rightClass_ = right_.getObjectClassName(_conf);
//                    map_.setArg(rightClass_);
//                    map_.setParam(fieldType_);
//                    if (!Templates.isCorrect(map_, _conf)) {
//                        throw new InvokeException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
//                    }
//                }
//                left_.setStruct(structField_);
//                res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
//                if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
//                    ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
//                    res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
//                }
//                ((FieldableStruct) argument_.getStruct()).setStruct(fieldId, res_.getStruct());
//                Argument a_ = res_;
//                return a_;
//            }
//            ResultErrorStd result_ = LgNames.getField(_conf, fieldId, argument_.getStruct());
//            if (result_.getError() != null) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
//            }
//            structField_ = result_.getResult();
//            left_.setStruct(structField_);
//            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
//            result_ = LgNames.setField(_conf, fieldId, argument_.getStruct(), res_.getStruct());
//            if (result_.getError() != null) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
//            }
//            Argument a_ = res_;
//            return a_;
//        }
//        if (!Modifier.isStatic(field.getModifiers())) {
//            if (argument_.isNull()) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//            }
//        }
//        Object obj_ = argument_.getStruct().getInstance();
//        Object field_ = ConverterMethod.getField(field, obj_);
//        Type type_ = field.getGenericType();
//        String pre_ = NativeTypeUtil.getFormattedType(field.getType().getName(), type_.toString(), 0, type_);
//        left_.setStruct(StdStruct.wrapStd(field_, pre_));
//        if (right_.isNull() && field.getType().isPrimitive()) {
//            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//        }
//        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
//        ConverterMethod.setField(field, obj_, res_.getObject());
//        Argument a_ = res_;
//        return a_;
//    }
    private void analyzeNativeField(ContextEl _conf, String _key) {
        ClassArgumentMatchingAdv cl_;
//        if (isIntermediateDottedOperation()) {
//            cl_ = getPreviousResultClass();
//        } else {
//            cl_ = new ClassArgumentMatching(_conf.getLastPage().getGlobalClass());
//        }
        cl_ = new ClassArgumentMatchingAdv(_conf.getLastPage().getGlobalClass());
        if (cl_ == null || cl_.getName() == null) {
            throw new NullGlobalObjectException(_conf.joinPages());
        }
        LgNames stds_ = _conf.getStandards();
        String stringType_;
        stringType_ = stds_.getAliasString();
//        if (cl_.isArray()) {
//            if (StringList.quickEq(_key, LENGTH)) {
//                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
//                return;
//            }
//            throw new NoSuchDeclaredFieldException(StringList.concat(cl_.getName(),RETURN_LINE,_key,RETURN_LINE,_conf.joinPages()));
//        }
        if (cl_.getClassOrNull() == null) {
            throw new RuntimeClassNotFoundException(StringList.concat(cl_.getName(),RETURN_LINE,_conf.joinPages()));
        }
        Field f_ = getDeclaredField(_conf, cl_, _key);
        if (!canBeUsed(f_, _conf)) {
            throw new BadAccessException(StringList.concat(f_.getDeclaringClass().getName(),DOT,_key,RETURN_LINE,_conf.joinPages()));
        }
//        if (Modifier.isFinal(f_.getModifiers())) {
//            if (resultCanBeSet()) {
//                finalField = true;
//            }
//        }
//        if (isStaticAccess() && !Modifier.isStatic(f_.getModifiers())) {
//            throw new StaticAccessException(_conf.joinPages());
//        }
//        field = f_;
//        setAccess(field, _conf);
//        Type type_ = f_.getGenericType();
//        String pre_ = NativeTypeUtil.getFormattedType(f_.getType().getName(), type_.toString(), 0, type_);
//        setResultClass(new ClassArgumentMatching(pre_));
    }
}
