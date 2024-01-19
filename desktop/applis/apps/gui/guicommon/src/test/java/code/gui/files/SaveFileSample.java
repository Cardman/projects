package code.gui.files;

public final class SaveFileSample implements AbsSaveFile {
    @Override
    public String save(String _path) {
        return _path;
    }
}
