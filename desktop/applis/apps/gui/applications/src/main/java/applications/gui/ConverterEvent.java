package applications.gui;

import code.converterimages.gui.WindowConverter;
import code.converterimages.main.LaunchingConverter;
import code.gui.AbsButton;

public final class ConverterEvent extends AbstractEvent {

    ConverterEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but, WindowConverter.APPS_CONVERTER);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingConverter l_;
        l_ = new LaunchingConverter(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
