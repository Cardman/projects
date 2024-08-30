package applications.gui;

import applications.code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.gui.unit.MessagesCdmFullGui;
import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;

public final class AppUnitEvent extends AbstractEvent {

    AppUnitEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, MessagesCdmFullGui.APPS_UNIT, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingAppUnitTests l_;
        l_ = new LaunchingAppUnitTests(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }
}
