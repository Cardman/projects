package code.gui.document;

import code.bean.nat.BeanNatCommonLgNames;
import code.formathtml.Navigation;
import code.formathtml.util.BeanLgNames;

public final class ThreadDirectActions extends AbstractThreadActions {

    ThreadDirectActions(RenderedPage _page) {
        super(_page);
        getPage().start();
    }

    @Override
    public void run() {
        RenderedPage page_ = getPage();
        Navigation navigation = page_.getNavigation();
        BeanLgNames _stds = page_.getStandards();
        ((BeanNatCommonLgNames)_stds).initializeRendSessionDoc(navigation);
        afterActionWithoutRemove();
    }
}
