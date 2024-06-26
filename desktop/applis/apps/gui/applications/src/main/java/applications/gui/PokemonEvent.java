package applications.gui;

import aiki.gui.WindowAiki;
import aiki.main.LaunchingPokemon;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class PokemonEvent extends AbstractEvent {

    PokemonEvent(WindowApps _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowAiki.APPS_AIKI, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingPokemon l_;
        l_ = new LaunchingPokemon(_window.getWithAppFactories());
        l_.launch(lg_);
    }

}
