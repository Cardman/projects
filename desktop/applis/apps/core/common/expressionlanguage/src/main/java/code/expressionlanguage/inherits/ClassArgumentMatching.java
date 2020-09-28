package code.expressionlanguage.inherits;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.StringList;

public final class ClassArgumentMatching {

    private ClassArgumentMatching() {

    }

    public static byte getPrimitiveCast(String _className, PrimitiveTypes _primTypes) {
        return getPrimitiveCast(new StringList(_className), _primTypes);
    }

    public static byte getPrimitiveCast(StringList _className, PrimitiveTypes _primTypes) {
        byte max_ = _primTypes.getMaxWrap();
        byte cast_ = max_;
        for (String b: _className) {
            PrimitiveType pr_ = _primTypes.getPrimitiveTypes().getVal(b);
            if (pr_ != null) {
                cast_ = (byte)Math.min(cast_,pr_.getCastNb());
            }
        }
        if (cast_ == max_) {
            return -1;
        }
        return cast_;
    }

}
