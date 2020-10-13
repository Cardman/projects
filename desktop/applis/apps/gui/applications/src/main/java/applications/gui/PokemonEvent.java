package applications.gui;

import aiki.main.LaunchingPokemon;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;

import java.util.concurrent.atomic.AtomicInteger;

public final class PokemonEvent extends AbstractEvent {
    PokemonEvent(MainWindow _window, AtomicInteger at_) {
        super(_window,at_);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingPokemon.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingPokemon l_;
        l_ = new LaunchingPokemon(_window.getFrames());
        l_.launch(lg_);
    }

}
