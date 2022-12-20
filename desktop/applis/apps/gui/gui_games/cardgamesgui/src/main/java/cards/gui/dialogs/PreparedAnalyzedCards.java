package cards.gui.dialogs;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatNavigation;

public interface PreparedAnalyzedCards extends Runnable {
    NatNavigation getNavigation();

    BeanNatCommonLgNames getBeanNatLgNames();
}
