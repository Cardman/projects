package code.gui.document;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatNavigation;

public interface PreparedAnalyzed extends Runnable {
    NatNavigation getNavigation();

    BeanNatCommonLgNames getBeanNatLgNames();
}
