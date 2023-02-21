package code.player.gui;

public final class PlayingSong implements Runnable {
    private final WindowRecorder window;

    public PlayingSong(WindowRecorder _w) {
        this.window = _w;
    }

    @Override
    public void run() {
        window.play();
    }
}
