package applications.gui;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.gui.unit.WindowUnit;
import code.gui.AbsButton;

public final class AppUnitEvent extends AbstractEvent {

    AppUnitEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but, WindowUnit.APPS_UNIT);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingAppUnitTests l_;
        l_ = new LaunchingAppUnitTests(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
