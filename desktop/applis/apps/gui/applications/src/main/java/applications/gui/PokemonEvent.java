package applications.gui;

import aiki.sml.MessagesPkGame;
import applications.main.LaunchingPokemon;
import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;

public final class PokemonEvent extends AbstractEvent {

    PokemonEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, MessagesPkGame.PK, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingPokemon l_;
        l_ = new LaunchingPokemon(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }

}
