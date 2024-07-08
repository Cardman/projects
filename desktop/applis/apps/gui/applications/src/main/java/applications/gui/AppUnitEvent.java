package applications.gui;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.gui.unit.WindowUnit;
import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;

public final class AppUnitEvent extends AbstractEvent {

    AppUnitEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowUnit.APPS_UNIT, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingAppUnitTests l_;
        l_ = new LaunchingAppUnitTests(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
