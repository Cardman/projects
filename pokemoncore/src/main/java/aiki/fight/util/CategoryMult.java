package aiki.fight.util;
import code.sml.FromAndToString;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class CategoryMult implements Equallable<CategoryMult>, Displayable {

    private static final char SEPARATOR = ';';

    private String category;

    private short mult;

    public CategoryMult() {
    }

    public CategoryMult(String _category, short _mult) {
        category = _category;
        mult = _mult;
    }

    public CategoryMult(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        category = elements_.first();
        mult = Short.parseShort(elements_.last());
    }

    @FromAndToString
    public static CategoryMult newCategoryMult(String _string) {
        return new CategoryMult(_string);
    }
    @Override
    public boolean eq(CategoryMult _obj) {
        if (!Numbers.eq(mult, _obj.mult)) {
            return false;
        }
        if (!StringList.quickEq(category, _obj.category)) {
            return false;
        }
        return true;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String _category) {
        category = _category;
    }

    public short getMult() {
        return mult;
    }

    public void setMult(short _mult) {
        mult = _mult;
    }

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(category);
        str_.append(SEPARATOR);
        str_.append(mult);
        return str_.toString();
    }
}
