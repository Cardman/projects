package cards.main;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatNavigation;

public final class CardNatLgNamesNavigation {
    private final BeanNatCommonLgNames beanNatLgNames;
    private final NatNavigation navigation;

    public CardNatLgNamesNavigation(BeanNatCommonLgNames _b, NatNavigation _n) {
        this.beanNatLgNames = _b;
        this.navigation = _n;
    }

    public BeanNatCommonLgNames getBeanNatLgNames() {
        return beanNatLgNames;
    }

    public NatNavigation getNavigation() {
        return navigation;
    }
}
