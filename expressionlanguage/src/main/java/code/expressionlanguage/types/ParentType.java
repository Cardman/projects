package code.expressionlanguage.types;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class ParentType extends NativeType {

    ParentType(Type _type, ParentType _parent, int _index) {
        super(_type, _parent, _index);
    }

    @Override
    final NativeType getFirstChild() {
        Type type_ = getType();
        if (type_ instanceof Class<?>) {
            Class<?> child_ = ((Class<?>)type_).getComponentType();
            return createNativeType(child_, 0, this);
        }
        if (type_ instanceof ParameterizedType) {
            Type child_ = ((ParameterizedType)type_).getActualTypeArguments()[0];
            return createNativeType(child_, 0, this);
        }
        Type child_ = ((GenericArrayType)type_).getGenericComponentType();
        return createNativeType(child_, 0, this);
    }

    abstract String getEnd();
}
