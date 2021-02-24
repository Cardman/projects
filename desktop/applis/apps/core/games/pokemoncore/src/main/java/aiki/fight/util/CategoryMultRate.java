package aiki.fight.util;
import code.maths.Rate;

public final class CategoryMultRate {

    private final CategoryMult category;

    private final Rate rate;

    public CategoryMultRate(CategoryMult _category, Rate _rate) {
        this.category = _category;
        this.rate = _rate;
    }

    public CategoryMult getCategory() {
        return category;
    }

    public Rate getRate() {
        return rate;
    }

}
