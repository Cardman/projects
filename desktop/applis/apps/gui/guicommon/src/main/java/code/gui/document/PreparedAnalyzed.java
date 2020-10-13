package code.gui.document;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.ContextEl;
import code.formathtml.Navigation;

public interface PreparedAnalyzed extends Runnable {
    Navigation getNavigation();
    ContextEl getContext();
    BeanNatLgNames getBeanNatLgNames();
}
