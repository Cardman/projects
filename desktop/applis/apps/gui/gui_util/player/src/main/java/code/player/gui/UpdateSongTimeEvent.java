package code.player.gui;

public class UpdateSongTimeEvent implements Runnable {

    private final WindowPlayer window;

    public UpdateSongTimeEvent(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.setElapsedTime();
    }

}
