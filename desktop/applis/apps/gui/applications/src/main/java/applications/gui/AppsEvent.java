package applications.gui;

import code.expressionlanguage.guicompos.LaunchingFull;
import code.expressionlanguage.guicompos.WindowFull;
import code.gui.CdmFactory;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class AppsEvent extends AbstractEvent {

    private final CdmFactory cdmFactory;
    AppsEvent(WindowApps _window, AbstractAtomicInteger _at, CdmFactory _cdm) {
        super(_window,_at);
        cdmFactory = _cdm;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowFull.APPS_LAUNCHER, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingFull l_;
        l_ = new LaunchingFull(_window.getFrames(),cdmFactory);
        l_.launch(lg_);
    }
}
