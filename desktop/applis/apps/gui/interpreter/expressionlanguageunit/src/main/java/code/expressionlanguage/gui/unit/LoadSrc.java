package code.expressionlanguage.gui.unit;

public final class LoadSrc implements Runnable {
    private final SimpleFilesFrame filesFrame;

    public LoadSrc(SimpleFilesFrame _filesFrame) {
        this.filesFrame = _filesFrame;
    }

    @Override
    public void run() {
        filesFrame.src();
    }
}
