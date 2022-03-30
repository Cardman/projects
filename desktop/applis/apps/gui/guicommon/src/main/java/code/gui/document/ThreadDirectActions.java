package code.gui.document;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.NatRendStackCall;
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
        NatRendStackCall rendStackCall_ = new NatRendStackCall();
        Navigation navigation = page_.getNavigation();
        BeanLgNames _stds = page_.getStandards();
        String textToBeChanged_ = ((BeanNatCommonLgNames)_stds).initializeRendSessionDoc(navigation, rendStackCall_);
        navigation.setupText(textToBeChanged_, _stds, rendStackCall_.getDocument(), rendStackCall_.getHtmlPage());
        afterActionWithoutRemove();
    }
}
