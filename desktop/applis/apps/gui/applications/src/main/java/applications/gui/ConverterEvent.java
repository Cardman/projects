package applications.gui;

import code.converterimages.gui.WindowConverter;
import code.converterimages.main.LaunchingConverter;
import code.gui.AppFactories;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class ConverterEvent extends AbstractEvent {

    private final AppFactories appFactories;
    ConverterEvent(WindowApps _window, AbstractAtomicInteger _at, AppFactories _fact) {
        super(_window,_at);
        appFactories = _fact;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowConverter.APPS_CONVERTER, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingConverter l_;
        l_ = new LaunchingConverter(_window.getFrames(),appFactories);
        l_.launch(lg_);
    }
}
