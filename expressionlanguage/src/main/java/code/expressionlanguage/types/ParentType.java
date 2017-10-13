package code.expressionlanguage.types;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class ParentType extends NativeType {

    private boolean initFirstChild;

    private NativeType firstChild;

    ParentType(Type _type, ParentType _parent, int _index) {
        super(_type, _parent, _index);
    }

    @Override
    final NativeType getFirstChild() {
        if (initFirstChild) {
            return firstChild;
        }
        initFirstChild = true;
        Type type_ = getType();
        if (type_ instanceof Class<?>) {
            Class<?> child_ = ((Class<?>)type_).getComponentType();
            firstChild = createNativeType(child_, 0, this);
            return firstChild;
        }
        if (type_ instanceof ParameterizedType) {
            Type child_ = ((ParameterizedType)type_).getActualTypeArguments()[0];
            firstChild = createNativeType(child_, 0, this);
            return firstChild;
        }
        Type child_ = ((GenericArrayType)type_).getGenericComponentType();
        firstChild = createNativeType(child_, 0, this);
        return firstChild;
    }
}
