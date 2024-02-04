package code.gui.files;

public final class ContinueLoadFileSample implements AbsContinueLoadFile {
    @Override
    public void next(FileOpenDialogContent _content) {
        _content.getPostFileDialogEvent().title(_content.getSelectedPath());
    }
}
