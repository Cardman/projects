package aiki.fight.util;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.StringList;
import code.xml.FromAndToString;

@CheckedData
public final class TypeDamageBoost {

    private static final char SEPARATOR = ';';

    private final String type;

    private final Rate boost;

    public TypeDamageBoost(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        type = elements_.first();
        boost = new Rate(elements_.last());
    }

    public TypeDamageBoost(String _type, Rate _boost) {
        type = _type;
        boost = _boost;
    }

    @FromAndToString
    public static TypeDamageBoost newTypeDamageBoost(String _string) {
        return new TypeDamageBoost(_string);
    }

    @FromAndToString
    @Override
    public String toString() {
        return type+SEPARATOR+boost;
    }

    public String getType() {
        return type;
    }

    public Rate getBoost() {
        return boost;
    }
}
