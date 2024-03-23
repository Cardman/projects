package aiki.main;

import aiki.beans.*;
import code.bean.nat.*;

public final class AikiNatLgNamesNavigation {
    private final PokemonStandards beanNatLgNames;
    private final NatNavigation navigation;

    public AikiNatLgNamesNavigation(PokemonStandards _b, NatNavigation _n) {
        this.beanNatLgNames = _b;
        this.navigation = _n;
    }

    public PokemonStandards getBeanNatLgNames() {
        return beanNatLgNames;
    }

    public NatNavigation getNavigation() {
        return navigation;
    }
}
