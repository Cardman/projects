package code.formathtml.classes;
import code.util.StringList;
import code.util.ints.MathFactory;

public class SimpleMathFactory implements MathFactory {

    @Override
    public Object evaluateDirectlyRate(String _numExp) {
        long long_ = 0;
        for (String p: StringList.splitChars(_numExp, '+')) {
            long_ += Long.parseLong(p);
        }
        return long_;
    }

    @Override
    public Boolean evaluateDirectlyBoolean(String _booleanExp) {
        boolean or_ = false;
        for (String p: StringList.splitChars(_booleanExp, '|')) {
            if (StringList.quickEq(p, Boolean.TRUE.toString())) {
                or_ = true;
                break;
            }
            if (StringList.quickEq(p, Boolean.FALSE.toString())) {
                continue;
            }
            if (p.contains("!=")) {
                StringList p_ = StringList.splitStrings(p, "!=");
                if (!StringList.quickEq(p_.first(), p_.last())) {
                    or_ = true;
                    break;
                }
                continue;
            }
            StringList p_ = StringList.splitChars(p, '=');
            if (StringList.quickEq(p_.first(), p_.last())) {
                or_ = true;
                break;
            }
        }
        return or_;
    }
}
