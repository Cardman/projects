package code.serialize;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoSuchConverterMethodException;
import code.serialize.exceptions.RuntimeInstantiationException;
import code.util.CustList;
import code.xml.FromAndToString;

public final class ConverterMethod {

    private ConverterMethod() {
    }

    public static boolean isPrimitivableClass(Class<?> _cl) {
        try {
//            if (Primitivable.class.isAssignableFrom(_cl)) {
//                Class<? extends Primitivable> subClass_ = _cl.asSubclass(Primitivable.class);
//                Constructor<?> constr_ = subClass_.getDeclaredConstructor(String.class);
//                return constr_.getAnnotation(RwXml.class) != null || constr_.isAccessible();
//            }
            Method method_ = ConverterMethod.getFromStringMethod(_cl);
            return method_ != null;
        } catch (RuntimeException _0) {
            return false;
        }
    }

    public static Object newObject(Class<?> _cl, String _arg) {
        Method method_ = null;
        try {
//            if (Primitivable.class.isAssignableFrom(_cl)) {
//                Class<? extends Primitivable> subClass_ = _cl.asSubclass(Primitivable.class);
//                Constructor<?> constr_ = subClass_.getDeclaredConstructor(String.class);
//                if (!constr_.isAccessible()) {
//                    if (constr_.getAnnotation(RwXml.class) == null) {
//                        throw new ClassCastException(_cl.getName());
//                    }
//                }
//                constr_.setAccessible(constr_.getAnnotation(RwXml.class)!=null);
//                return constr_.newInstance(_arg);
//            }
            method_ = ConverterMethod.getFromStringMethod(_cl);
            if (method_ == null) {
                throw new NoSuchConverterMethodException(_cl.toString());
            }
            return method_.invoke(null, _arg);
//        } catch (InstantiationException _0) {
//            throw new RuntimeInstantiationException(_0);
//        } catch (NoSuchMethodException _0) {
//            throw new NoSuchDeclaredMethodException(_0);
        } catch (IllegalAccessException _0) {
            throw new BadAccessException(_0, method_.getName());
        } catch (InvocationTargetException _0) {
            throw new InvokingException(_0);
        }
    }
    
    static Object invokePublicMethod(Method _method, Object _instance) {
        try {
            return _method.invoke(_instance);
        } catch (IllegalAccessException _0) {
            return null;
        } catch (InvocationTargetException _0) {
            throw new InvokingException(_0);
        }
    }
    
    public static Object newInstance(Constructor<?> _method, Object... _args) {
        try {
            return _method.newInstance(_args);
        } catch (IllegalAccessException _0) {
            return new BadAccessException(_0, _method.toString());
        } catch (InvocationTargetException _0) {
            throw new InvokingException(_0, _0.getTargetException());
        } catch (InstantiationException _0) {
            throw new RuntimeInstantiationException(_0);
        }
    }

    public static Object invokeMethod(Method _method, Object _instance, Object... _args) {
        try {
            return _method.invoke(_instance, _args);
        } catch (IllegalAccessException _0) {
            return new BadAccessException(_0, _method.toString());
        } catch (InvocationTargetException _0) {
            throw new InvokingException(_0, _0.getTargetException());
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
        Method method_ = null;
        for (Method methClass_: _class.getMethods()) {
            if (!Modifier.isStatic(methClass_.getModifiers())) {
                continue;
            }
            FromAndToString annot_ = methClass_.getAnnotation(FromAndToString.class);
            if (annot_ == null) {
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
            if (param_[CustList.FIRST_INDEX] != String.class) {
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
        Method method_ = null;
        for (Method methClass_: _class.getMethods()) {
            if (Modifier.isStatic(methClass_.getModifiers())) {
                continue;
            }
            FromAndToString annot_ = methClass_.getAnnotation(FromAndToString.class);
            if (annot_ == null) {
                continue;
            }
            if (methClass_.getReturnType() != String.class) {
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
