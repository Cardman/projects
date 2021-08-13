package code.player.gui;

public class UpdateSongTimeEvent implements Runnable {

    private final MainWindow window;

    public UpdateSongTimeEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.setElapsedTime();
    }

}
