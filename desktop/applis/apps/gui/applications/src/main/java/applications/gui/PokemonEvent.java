package applications.gui;

import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;

import java.util.concurrent.atomic.AtomicInteger;

public final class PokemonEvent extends AbstractEvent {

    private final AikiFactory aikiFactory;

    PokemonEvent(MainWindow _window, AtomicInteger _at, AikiFactory _aikiFactory) {
        super(_window,_at);
        aikiFactory = _aikiFactory;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingPokemon.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingPokemon l_;
        l_ = new LaunchingPokemon(_window.getFrames(), aikiFactory);
        l_.launch(lg_);
    }

}
