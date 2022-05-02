package code.player.gui;

public final class RecordingSong implements Runnable {
    private final String fileName;
    private final WindowRecorder window;

    public RecordingSong(String _f, WindowRecorder _w) {
        this.fileName = _f;
        this.window = _w;
    }

    @Override
    public void run() {
        window.tryRecord(fileName);
    }
}
