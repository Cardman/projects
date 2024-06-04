package code.gui.files;

public final class ContinueFileSample implements AbsContinueFile {
    @Override
    public void next(FileDialogContent _content) {
        _content.getPostFileDialogEvent().title(_content.getSelectedPath());
    }
}
