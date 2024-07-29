package applications.gui;

import code.converterimages.gui.WindowConverter;
import applications.code.converterimages.main.LaunchingConverter;
import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;

public final class ConverterEvent extends AbstractEvent {

    ConverterEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, WindowConverter.APPS_CONVERTER, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingConverter l_;
        l_ = new LaunchingConverter(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }
}
