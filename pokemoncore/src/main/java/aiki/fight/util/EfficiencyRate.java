package aiki.fight.util;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.StringList;
import code.xml.FromAndToString;

@CheckedData
public final class EfficiencyRate {

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

    @FromAndToString
    @Override
    public String toString() {
        return eff.toString()+SEPARATOR+hpRate;
    }

    public Rate getEff() {
        return eff;
    }

    public Rate getHpRate() {
        return hpRate;
    }
}
