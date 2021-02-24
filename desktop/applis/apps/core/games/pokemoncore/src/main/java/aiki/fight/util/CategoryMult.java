package aiki.fight.util;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class CategoryMult implements Displayable {

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
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        category = elements_.first();
        mult = (short) NumberUtil.parseInt(elements_.last());
    }

    
    public static CategoryMult newCategoryMult(String _string) {
        return new CategoryMult(_string);
    }

    public boolean eq(CategoryMult _obj) {
        if (!NumberUtil.eq(mult, _obj.mult)) {
            return false;
        }
        return StringUtil.quickEq(category, _obj.category);
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

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(category);
        str_.append(SEPARATOR);
        str_.append(mult);
        return str_.toString();
    }
}
