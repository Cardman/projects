package code.expressionlanguage.gui.unit;

public final class LoadFiles implements Runnable {
    private final SimpleFilesFrame filesFrame;

    public LoadFiles(SimpleFilesFrame _filesFrame) {
        this.filesFrame = _filesFrame;
    }

    @Override
    public void run() {
        filesFrame.files();
    }
}
