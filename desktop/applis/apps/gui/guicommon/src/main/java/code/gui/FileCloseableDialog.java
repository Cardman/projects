package code.gui;

public final class FileCloseableDialog implements AbsCloseableDialog {
    private final FileDialog fileDialog;

    public FileCloseableDialog(FileDialog _f) {
        this.fileDialog = _f;
    }

    @Override
    public void closeWindow() {
        if (fileDialog instanceof FileOpenDialog) {
            ((FileOpenDialog)fileDialog).setKeepSearching(false);
        }
    }
}
