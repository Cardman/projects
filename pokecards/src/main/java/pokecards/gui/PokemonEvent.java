package pokecards.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.GroupFrame;
import aiki.main.LaunchingPokemon;

public class PokemonEvent extends MouseAdapter {
    private MainWindow window;
    public PokemonEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public final void mouseReleased(MouseEvent _e) {
        if (LaunchingPokemon.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingPokemon.getMainWindowClass())) {
            LaunchingPokemon.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingPokemon l_;
        l_ = new LaunchingPokemon();
        l_.launch(lg_);
    }
}
