package applications.gui;

import aiki.gui.WindowAiki;
import aiki.main.LaunchingPokemon;
import code.gui.AbsButton;

public final class PokemonEvent extends AbstractEvent {

    PokemonEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but, WindowAiki.APPS_AIKI);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingPokemon l_;
        l_ = new LaunchingPokemon(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }

}
