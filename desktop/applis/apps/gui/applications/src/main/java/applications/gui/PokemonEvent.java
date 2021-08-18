package applications.gui;

import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class PokemonEvent extends AbstractEvent {

    private final AikiFactory aikiFactory;

    PokemonEvent(WindowApps _window, AbstractAtomicInteger _at, AikiFactory _aikiFactory) {
        super(_window,_at);
        aikiFactory = _aikiFactory;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingPokemon.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingPokemon l_;
        l_ = new LaunchingPokemon(_window.getFrames(), aikiFactory);
        l_.launch(lg_);
    }

}
