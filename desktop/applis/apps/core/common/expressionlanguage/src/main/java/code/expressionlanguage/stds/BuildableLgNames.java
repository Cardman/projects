package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.AbstractConstantsCalculator;

public interface BuildableLgNames {
    void build();
    LgNamesContent getContent();

    AbstractConstantsCalculator newConstantsCalculator();
}
