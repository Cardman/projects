package aiki.fight.util;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Displayable;

@CheckedData
public final class EfficiencyRate implements Displayable {

    private static final char SEPARATOR = ';';

    private final Rate eff;

    private final Rate hpRate;

    public EfficiencyRate(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        eff = new Rate(elements_.first());
        hpRate = new Rate(elements_.last());
    }

    public EfficiencyRate(Rate _eff, Rate _hpRate) {
        eff = _eff;
        hpRate = _hpRate;
    }

    @FromAndToString
    public static EfficiencyRate newEfficiencyRate(String _string) {
        return new EfficiencyRate(_string);
    }

    public Rate getEff() {
        return eff;
    }

    public Rate getHpRate() {
        return hpRate;
    }

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(eff.toNumberString());
        str_.append(SEPARATOR);
        str_.append(hpRate.toNumberString());
        return str_.toString();
    }
}
