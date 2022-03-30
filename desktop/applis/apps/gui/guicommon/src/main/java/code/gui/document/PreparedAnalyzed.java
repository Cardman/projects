package code.gui.document;

import code.bean.nat.BeanNatLgNames;
import code.formathtml.Navigation;

public interface PreparedAnalyzed extends Runnable {
    Navigation getNavigation();

    BeanNatLgNames getBeanNatLgNames();
}
