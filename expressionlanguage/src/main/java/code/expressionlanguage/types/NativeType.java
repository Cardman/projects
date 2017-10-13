package code.expressionlanguage.types;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class NativeType {

    private boolean initNextSibling;

    private Type type;

    private NativeType nextSibling;

    private ParentType parent;

    private int index;

    NativeType(Type _type, ParentType _parent, int _index) {
        type = _type;
        parent = _parent;
        index = _index;
    }

    abstract NativeType getFirstChild();

    final NativeType getNextSibling() {
        if (initNextSibling) {
            return nextSibling;
        }
        initNextSibling = true;
        ParentType parent_ = getParent();
        if (!(parent_ instanceof NativeTemplate)) {
            return null;
        }
        NativeTemplate temp_ = (NativeTemplate) parent_;
        ParameterizedType type_ = (ParameterizedType) temp_.getType();
        Type[] args_ = type_.getActualTypeArguments();
        if (index + 1 >= args_.length) {
            return null;
        }
        nextSibling = createNativeType(args_[index + 1], index + 1, temp_);
        return nextSibling;
    }
    static NativeType createNativeType(Type _type, int _index, ParentType _parent) {
        if (_type instanceof Class<?>) {
            if (!((Class<?>)_type).isArray()) {
                return new NativeClass(_type, _parent, _index);
            }
            return new NativeArray(_type, _parent, _index);
        }
        if (_type instanceof ParameterizedType) {
            return new NativeTemplate(_type, _parent, _index);
        }
        if (_type instanceof GenericArrayType) {
            return new NativeArray(_type, _parent, _index);
        }
        return new NativeVariable(_type, _parent, _index);
    }

    final ParentType getParent() {
        return parent;
    }

    final int getIndex() {
        return index;
    }

    final Type getType() {
        return type;
    }
}
