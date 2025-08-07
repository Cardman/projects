package applications.gui;

import aiki.gui.components.editor.*;
import applications.main.*;
import code.gui.*;

public final class PokemonEditorEvent extends AbstractEvent {

    PokemonEditorEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, MessagesPkEditor.PK_EDITOR, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingPokemonEditor l_;
        l_ = new LaunchingPokemonEditor(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }

}
