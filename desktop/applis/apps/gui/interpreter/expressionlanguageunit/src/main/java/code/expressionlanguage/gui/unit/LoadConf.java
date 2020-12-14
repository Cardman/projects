package code.expressionlanguage.gui.unit;

public final class LoadConf implements Runnable {
    private final SimpleFilesFrame filesFrame;
    private final String selected;

    public LoadConf(SimpleFilesFrame _filesFrame,String _selected) {
        this.filesFrame = _filesFrame;
        selected = _selected;
    }

    @Override
    public void run() {
        filesFrame.setFilePath(selected);
    }
}
