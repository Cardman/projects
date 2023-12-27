package applications.gui;

import aiki.gui.WindowAiki;
import aiki.main.LaunchingPokemon;
import code.gui.AppFactories;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class PokemonEvent extends AbstractEvent {
    private final AppFactories appFactories;

    PokemonEvent(WindowApps _window, AbstractAtomicInteger _at, AppFactories _cdmFactory) {
        super(_window,_at);
        appFactories = _cdmFactory;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowAiki.APPS_AIKI, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingPokemon l_;
        l_ = new LaunchingPokemon(_window.getFrames(),appFactories);
        l_.launch(lg_);
    }

}
