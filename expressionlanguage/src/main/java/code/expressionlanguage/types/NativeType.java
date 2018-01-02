package code.expressionlanguage.types;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import code.util.StringList;

abstract class NativeType {

    static final String EMPTY_STRING = "";
    static final String PREFIX = "#";
    static final String ARRAY = "[";
    static final String TEMPLATE_BEGIN = "<";
    static final String TEMPLATE_SEP = ",";
    static final String TEMPLATE_END = ">";

    private Type type;

    private ParentType parent;

    private int index;

    private String prefix = EMPTY_STRING;

    NativeType(Type _type, ParentType _parent, int _index) {
        type = _type;
        parent = _parent;
        index = _index;
        if (index > 0) {
            prefix = TEMPLATE_SEP;
        }
    }

    abstract NativeType getFirstChild();

    final NativeType getNextSibling() {
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
        return createNativeType(args_[index + 1], index + 1, temp_);
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

    final String getPrefixBegin() {
        return StringList.concat(getPrefix(),getBegin());
    }

    abstract String getBegin();

    final ParentType getParent() {
        return parent;
    }

    final Type getType() {
        return type;
    }

    final String getPrefix() {
        return prefix;
    }
}
