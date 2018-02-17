package code.expressionlanguage.opers;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtilAdv;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassArgumentMatchingAdv;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.types.NativeTypeUtil;
import code.serialize.exceptions.BadAccessException;
import code.util.CustList;
import code.util.StringList;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class FctOperationAdv extends InvokingOperationAdv {

    private static final int BOOLEAN_ARGS = 3;

    private String methodName;

    private Method method;

    private ConstructorId constId;

    private ClassMethodId classMethodId;
    private MethodId realId;

    private boolean staticMethod;

    private boolean ternary;

    private boolean superConstructorCall;

    private boolean otherConstructorClass;

    private boolean staticChoiceMethod;
    private boolean staticChoiceMethodTemplate;

    private boolean superAccessMethod;

    private boolean foundBound;
    private boolean correctTemplate = true;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private void analyzeNativeClass(ContextEl _conf, String _subType, boolean _failIfError) {
        if (new ClassArgumentMatchingAdv(_subType).getClassOrNull() == null) {
            throw new RuntimeClassNotFoundException(StringList.concat(_subType,RETURN_LINE,_conf.joinPages()));
        }
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        if (PrimitiveTypeUtilAdv.isPrimitive(_subType, _conf)) {
//            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
            throw new PrimitiveTypeException(StringList.concat(_subType,RETURN_LINE,_conf.joinPages()));
        }
        ClassArgumentMatchingAdv clVar_ = new ClassArgumentMatchingAdv(_subType);
        String trimMeth_ = methodName.trim();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        int varargOnly_ = lookOnlyForVarArg();
        Method m_ = getDeclaredMethod(_failIfError, _conf, varargOnly_, isStaticAccess(), clVar_, trimMeth_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (m_ == null) {
            return;
        }
        if (!canBeUsed(m_, _conf)) {
            if (_failIfError) {
//                setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
                throw new BadAccessException(StringList.concat(m_.toGenericString(),RETURN_LINE,_conf.joinPages()));
            }
            return;
        }
        method = m_;
        if (m_.isVarArgs() && varargOnly_ == -1) {
            Class<?>[] params_ = m_.getParameterTypes();
            naturalVararg = params_.length - 1;
            lastType = NativeTypeUtil.getPrettyType(params_[naturalVararg]);
            lastType = PrimitiveTypeUtilAdv.getQuickComponentType(lastType);
        }
        staticMethod = Modifier.isStatic(m_.getModifiers());
        setAccess(m_, _conf);
        int nbParams_ = m_.getTypeParameters().length;
        Type type_ = m_.getGenericReturnType();
        String pre_ = NativeTypeUtil.getFormattedType(m_.getReturnType().getName(), type_.toString(), nbParams_, type_);
//        setResultClass(new ClassArgumentMatching(pre_));
        foundBound = true;
    }

    ArgumentCall getArgument(Argument _previous, CustList<Argument> _arguments, ContextEl _conf) {
//        if (classMethodId == null) {
//            firstArgs_ = listArguments(chidren_, naturalVararg, lastType, _arguments, _conf);
//            Object obj_ = arg_.getObject();
//            if (!staticMethod && arg_.isNull()) {
//                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//            }
////            if (firstArgs_.isEmpty()) {
////                if (StringList.quickEq(trimMeth_, GET_CLASS)) {
////                    Argument argres_ = new Argument();
////                    ClassMetaInfo res_ = new ClassMetaInfo(arg_.getObjectClassName(_conf), null, null, null, null, null,false,false);
////                    argres_.setObject(res_);
////                    return ArgumentCall.newArgument(argres_);
////                }
////            }
////            String clCur_ = getPreviousResultClass().getName();
//            int nbParams_ = method.getTypeParameters().length;
//            Type type_ = method.getGenericReturnType();
//            String pre_ = NativeTypeUtil.getFormattedType(method.getReturnType().getName(), type_.toString(), nbParams_, type_);
//            Struct ret_ = invokeMethod(_conf, 0, naturalVararg > -1, EMPTY_STRING, method, obj_, pre_, Argument.toArgArray(firstArgs_));
//            Argument argres_ = new Argument();
//            argres_.setStruct(ret_);
//            return ArgumentCall.newArgument(argres_);
//        }
        return null;
    }
    @Override
    boolean isCallMethodCtor() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    void calculateChildren() {
        // TODO Auto-generated method stub

    }

}
