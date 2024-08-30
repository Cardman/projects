package applications.gui;

import applications.code.expressionlanguage.guicompos.LaunchingFull;
import code.expressionlanguage.gui.unit.MessagesCdmFullGui;
import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;

public final class AppsEvent extends AbstractEvent {

    AppsEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, MessagesCdmFullGui.APPS_LAUNCHER, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingFull l_;
        l_ = new LaunchingFull(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }
}
