package applications.gui;

import code.converterimages.main.LaunchingConverter;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public final class ConverterEvent extends AbstractEvent {

    ConverterEvent(MainWindow _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingConverter.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingConverter l_;
        l_ = new LaunchingConverter(_window.getFrames());
        l_.launch(lg_);
    }
}
