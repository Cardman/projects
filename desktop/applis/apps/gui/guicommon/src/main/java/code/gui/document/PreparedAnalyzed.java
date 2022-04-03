package code.gui.document;

import code.bean.nat.BeanNatCommonLgNames;
import code.formathtml.Navigation;

public interface PreparedAnalyzed extends Runnable {
    Navigation getNavigation();

    BeanNatCommonLgNames getBeanNatLgNames();
}
