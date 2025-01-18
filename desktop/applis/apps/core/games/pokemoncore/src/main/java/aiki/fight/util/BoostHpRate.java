package aiki.fight.util;
import code.maths.Rate;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class BoostHpRate implements Displayable {

    private static final char SEPARATOR = ';';

    private final int boost;

    private final Rate hpRate;

    public BoostHpRate(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        boost = NumberUtil.parseInt(elements_.first());
        hpRate = new Rate(elements_.last());
    }

    public BoostHpRate(int _boost, Rate _hpRate) {
        boost = _boost;
        hpRate = _hpRate;
    }

    
    public static BoostHpRate newBoostHpRate(String _string) {
        return new BoostHpRate(_string);
    }

    public int getBoost() {
        return boost;
    }

    public Rate getHpRate() {
        return hpRate;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(boost);
        str_.append(SEPARATOR);
        str_.append(hpRate.toNumberString());
        return str_.toString();
    }
}
