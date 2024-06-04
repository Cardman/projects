package applications.gui;

import code.converterimages.gui.WindowConverter;
import code.converterimages.main.LaunchingConverter;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class ConverterEvent extends AbstractEvent {

    ConverterEvent(WindowApps _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowConverter.APPS_CONVERTER, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingConverter l_;
        l_ = new LaunchingConverter(_window.getWithAppFactories());
        l_.launch(lg_);
    }
}
