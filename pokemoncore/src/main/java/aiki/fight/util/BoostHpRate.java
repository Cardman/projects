package aiki.fight.util;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.StringList;
import code.xml.FromAndToString;

@CheckedData
public final class BoostHpRate {

    private static final char SEPARATOR = ';';

    private static final String EMPTY_STRING = "";

    private final byte boost;

    private final Rate hpRate;

    public BoostHpRate(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        boost = Byte.parseByte(elements_.first());
        hpRate = new Rate(elements_.last());
    }

    public BoostHpRate(byte _boost, Rate _hpRate) {
        boost = _boost;
        hpRate = _hpRate;
    }

    @FromAndToString
    public static BoostHpRate newBoostHpRate(String _string) {
        return new BoostHpRate(_string);
    }

    @FromAndToString
    @Override
    public String toString() {
        return EMPTY_STRING+boost+SEPARATOR+hpRate;
    }

    public byte getBoost() {
        return boost;
    }

    public Rate getHpRate() {
        return hpRate;
    }
}
