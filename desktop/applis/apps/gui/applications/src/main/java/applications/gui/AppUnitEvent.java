package applications.gui;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.gui.CdmFactory;
import code.gui.FrameUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class AppUnitEvent extends AbstractEvent {

    private final CdmFactory cdmFactory;
    AppUnitEvent(WindowApps _window, CdmFactory _cdm, AbstractAtomicInteger _at) {
        super(_window,_at);
        cdmFactory = _cdm;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return FrameUtil.tryToReopen(LaunchingAppUnitTests.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingAppUnitTests l_;
        l_ = new LaunchingAppUnitTests(cdmFactory);
        l_.launch(lg_);
    }
}
