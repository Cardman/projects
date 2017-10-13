package code.expressionlanguage.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public final class NativeTypeUtil {

    static final String PREFIX = "#";
    static final String ARRAY = "[";
    static final String TEMPLATE_BEGIN = "<";
    static final String TEMPLATE_SEP = ",";
    static final String TEMPLATE_END = ">";

    private NativeTypeUtil() {
    }

    public static String getPrettyType(Type _type) {
        NativeType root_ = NativeType.createNativeType(_type, 0, null);
        StringBuilder type_ = new StringBuilder();
        type_.append(begin(root_));
        NativeType current_ = root_;
        while (true) {
            NativeType next_ = current_.getFirstChild();
            if (next_ != null) {
                type_.append(begin(next_));
                current_ = next_;
                continue;
            }
            next_ = current_.getNextSibling();
            if (next_ != null) {
                type_.append(TEMPLATE_SEP);
                type_.append(begin(next_));
                current_ = next_;
                continue;
            }
            ParentType parentType_ = current_.getParent();
            if (parentType_ == null) {
                break;
            }
            type_.append(end(parentType_));
            next_ = parentType_.getNextSibling();
            while (next_ == null) {
                ParentType parent_ = parentType_.getParent();
                if (parent_ == null) {
                    break;
                }
                type_.append(end(parent_));
                next_ = parent_.getNextSibling();
                parentType_ = parent_;
            }
            if (next_ == null) {
                break;
            }
            type_.append(TEMPLATE_SEP);
            type_.append(begin(next_));
            current_ = next_;
        }
        return type_.toString();
    }
    static String begin(NativeType _type) {
        StringBuilder type_ = new StringBuilder();
        if (_type instanceof NativeArray) {
            type_.append(ARRAY);
        }
        if (_type instanceof NativeTemplate) {
            Class<?> cl_ = (Class<?>) ((ParameterizedType)((NativeTemplate)_type).getType()).getRawType();
            type_.append(cl_.getName()+TEMPLATE_BEGIN);
        }
        if (_type instanceof NativeClass) {
            Class<?> cl_ = (Class<?>)((NativeClass)_type).getType();
            type_.append(cl_.getName());
        }
        if (_type instanceof NativeVariable) {
            TypeVariable<?> cl_ = (TypeVariable<?>)((NativeVariable)_type).getType();
            type_.append(PREFIX+cl_.getName());
        }
        return type_.toString();
    }
    static String end(NativeType _type) {
        StringBuilder type_ = new StringBuilder();
        if (_type instanceof NativeTemplate) {
            type_.append(TEMPLATE_END);
        }
        return type_.toString();
    }
}
