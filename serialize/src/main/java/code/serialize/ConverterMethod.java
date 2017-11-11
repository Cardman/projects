package code.serialize;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoSuchConverterMethodException;
import code.util.CustList;
import code.xml.FromAndToString;

public final class ConverterMethod {

    private static final String NAME = "name";

    private ConverterMethod() {
    }

    public static boolean isPrimitivableClass(Class<?> _cl) {
        Method method_ = ConverterMethod.getFromStringMethod(_cl);
        return method_ != null;
    }

    public static Object newObject(Class<?> _cl, String _arg) {
        Method method_ = ConverterMethod.getFromStringMethod(_cl);
        if (method_ == null) {
            throw new NoSuchConverterMethodException(_cl.toString());
        }
        try {
            method_ = ConverterMethod.getFromStringMethod(_cl);
            return method_.invoke(null, _arg);
        } catch (Throwable _0) {
            throw new InvokingException(_0);
        }
    }

    public static Object newInstance(Constructor<?> _method, Object... _args) {
        try {
            return _method.newInstance(_args);
        } catch (InvocationTargetException _0) {
            throw new InvokingException(_0.getTargetException());
        } catch (Throwable _0) {
            return null;
        }
    }

    public static String getName(Object _instance) {
        try {
            Class<?> cl_ = _instance.getClass();
            Method m_ = cl_.getMethod(NAME);
            return (String) invokeMethod(m_, _instance);
        } catch (Exception _0) {
            return null;
        }
    }

    public static Object invokeMethod(Method _method, Object _instance, Object... _args) {
        try {
            return _method.invoke(_instance, _args);
        } catch (IllegalAccessException _0) {
            throw new BadAccessException(_0, _method.toString());
        } catch (InvocationTargetException _0) {
            throw new InvokingException(_0.getTargetException());
        }
    }

    public static Object getField(Field _field, Object _instance) {
        try {
            return _field.get(_instance);
        } catch (IllegalAccessException _0) {
            throw new BadAccessException(_0, _field.getName());
        }
    }

    public static void setField(Field _field, Object _instance, Object _ref) {
        try {
            _field.set(_instance, _ref);
        } catch (IllegalAccessException _0) {
            throw new BadAccessException(_0, _field.getName());
        }
    }

    public static Method getFromStringMethod(Class<?> _class) {
        return getFromStringMethod(_class, String.class);
    }

    public static Method getFromStringMethod(Class<?> _class, Class<?> _classArg) {
        Method method_ = null;
        for (Method methClass_: _class.getMethods()) {
            if (!Modifier.isStatic(methClass_.getModifiers())) {
                continue;
            }
            if (!methClass_.isAnnotationPresent(FromAndToString.class)) {
                continue;
            }
            //void methods return class is void.class
            Class<?> returnType_ = methClass_.getReturnType();
            if (returnType_ == null) {
                continue;
            }
            if (!_class.isAssignableFrom(returnType_)) {
                continue;
            }
            Class<?>[] param_ = methClass_.getParameterTypes();
            if (param_.length != CustList.ONE_ELEMENT) {
                continue;
            }
            if (param_[CustList.FIRST_INDEX] != _classArg) {
                continue;
            }
            if (method_ == null) {
                method_ = methClass_;
            } else {
                method_ = null;
                break;
            }
        }
        return method_;
    }

    public static Method getToStringMethod(Class<?> _class) {
        return getToStringMethod(_class, String.class);
    }

    public static Method getToStringMethod(Class<?> _class, Class<?> _classReturn) {
        Method method_ = null;
        for (Method methClass_: _class.getMethods()) {
            if (Modifier.isStatic(methClass_.getModifiers())) {
                continue;
            }
            if (!methClass_.isAnnotationPresent(FromAndToString.class)) {
                continue;
            }
            if (methClass_.getReturnType() != _classReturn) {
                continue;
            }
            Class<?>[] param_ = methClass_.getParameterTypes();
            if (param_.length > CustList.SIZE_EMPTY) {
                continue;
            }
            if (method_ == null) {
                method_ = methClass_;
            } else {
                method_ = null;
                break;
            }
        }
        return method_;
    }
}
