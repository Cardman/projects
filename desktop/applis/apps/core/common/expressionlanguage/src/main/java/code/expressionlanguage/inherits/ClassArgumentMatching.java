package code.expressionlanguage.inherits;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.structs.*;
import code.util.StringList;

public final class ClassArgumentMatching {

    private ClassArgumentMatching() {

    }

    public static byte getPrimitiveCast(String _className, LgNames stds_) {
        return getPrimitiveCast(new StringList(_className),stds_);
    }

    public static byte getPrimitiveCast(StringList _className, LgNames stds_) {
        byte cast_ = 8;
        for (String b: _className) {
            PrimitiveType pr_ = stds_.getPrimitiveTypes().getVal(b);
            if (pr_ != null) {
                cast_ = (byte)Math.min(cast_,pr_.getCastNb());
            }
        }
        if (cast_ == 8) {
            return -1;
        }
        return cast_;
    }

}
