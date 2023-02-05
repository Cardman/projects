package applications.gui;

import code.gui.CdmFactory;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.renders.LaunchingRenders;
import code.threads.AbstractAtomicInteger;

public final class RenderEvent extends AbstractEvent {

    private final CdmFactory cdmFactory;
    RenderEvent(WindowApps _window, AbstractAtomicInteger _at, CdmFactory _cdm) {
        super(_window,_at);
        cdmFactory = _cdm;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(LaunchingRenders.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingRenders l_;
        l_ = new LaunchingRenders(_window.getFrames(),cdmFactory);
        l_.launch(lg_);
    }
}
