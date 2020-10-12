package applications.gui;

import aiki.main.LaunchingPokemon;
import code.gui.GroupFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class PokemonEvent extends MouseAdapter {
    private MainWindow window;
    PokemonEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (LaunchingPokemon.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingPokemon.getMainWindowClass(), window.getFrames())) {
            LaunchingPokemon.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingPokemon l_;
        l_ = new LaunchingPokemon();
        l_.launch(lg_, window.getFrames());
    }
}
