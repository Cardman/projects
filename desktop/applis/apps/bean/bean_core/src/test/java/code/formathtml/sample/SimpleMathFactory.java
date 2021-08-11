package code.formathtml.sample;
import code.util.StringList;
import code.util.core.StringUtil;
import code.formathtml.nat.MathFactory;

public class SimpleMathFactory implements MathFactory {

    @Override
    public Object evaluateDirectlyRate(String _numExp) {
        long long_ = 0;
        for (String p: StringUtil.splitChars(_numExp, '+')) {
            long_ += Long.parseLong(p);
        }
        return long_;
    }

    @Override
    public Boolean evaluateDirectlyBoolean(String _booleanExp) {
        boolean or_ = false;
        for (String p: StringUtil.splitChars(_booleanExp, '|')) {
            if (StringUtil.quickEq(p, Boolean.TRUE.toString())) {
                or_ = true;
                break;
            }
            if (StringUtil.quickEq(p, Boolean.FALSE.toString())) {
                continue;
            }
            if (p.contains("!=")) {
                StringList p_ = StringUtil.splitStrings(p, "!=");
                if (!StringUtil.quickEq(p_.first(), p_.last())) {
                    or_ = true;
                    break;
                }
                continue;
            }
            StringList p_ = StringUtil.splitChars(p, '=');
            if (StringUtil.quickEq(p_.first(), p_.last())) {
                or_ = true;
                break;
            }
        }
        return or_;
    }
}
