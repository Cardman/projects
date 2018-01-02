package aiki.fight.util;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Displayable;

@CheckedData
public final class TypeDamageBoost implements Displayable {

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

    public String getType() {
        return type;
    }

    public Rate getBoost() {
        return boost;
    }

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(type);
        str_.append(SEPARATOR);
        str_.append(boost.toNumberString());
        return str_.toString();
    }
}
