package code.expressionlanguage.types;

import java.lang.reflect.Type;

public final class NativeTypeUtil {

    private static final String WILD_CARD = "?";

    private NativeTypeUtil() {
    }

    public static String getFormattedType(String _typeName, String _foundTypeName, int _nbParams, Type _type) {
        if (_foundTypeName.contains(WILD_CARD)) {
            return _typeName;
        }
        if (_nbParams > 0) {
            return _typeName;
        }
        return getPrettyType(_type);
    }

    public static String getPrettyType(Type _type) {
        NativeType root_ = NativeType.createNativeType(_type, 0, null);
        StringBuilder type_ = new StringBuilder();
        type_.append(root_.getBegin());
        NativeType current_ = root_;
        while (true) {
            NativeType next_ = current_.getFirstChild();
            if (next_ != null) {
                type_.append(next_.getBegin());
                current_ = next_;
                continue;
            }
            next_ = current_.getNextSibling();
            if (next_ != null) {
                type_.append(next_.getPrefixBegin());
                current_ = next_;
                continue;
            }
            ParentType parentType_ = current_.getParent();
            if (parentType_ == null) {
                break;
            }
            type_.append(parentType_.getEnd());
            next_ = parentType_.getNextSibling();
            while (next_ == null) {
                ParentType parent_ = parentType_.getParent();
                if (parent_ == null) {
                    break;
                }
                type_.append(parent_.getEnd());
                next_ = parent_.getNextSibling();
                parentType_ = parent_;
            }
            if (next_ == null) {
                break;
            }
            type_.append(next_.getPrefixBegin());
            current_ = next_;
        }
        return type_.toString();
    }
}
