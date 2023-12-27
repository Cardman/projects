package applications.gui;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.gui.unit.WindowUnit;
import code.gui.AppFactories;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class AppUnitEvent extends AbstractEvent {

    private final AppFactories cdmFactory;
    AppUnitEvent(WindowApps _window, AppFactories _cdm, AbstractAtomicInteger _at) {
        super(_window,_at);
        cdmFactory = _cdm;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowUnit.APPS_UNIT, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingAppUnitTests l_;
        l_ = new LaunchingAppUnitTests(_window.getFrames(),cdmFactory);
        l_.launch(lg_);
    }
}
