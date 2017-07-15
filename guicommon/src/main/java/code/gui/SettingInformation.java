package code.gui;

/**Thread safe class*/
public final class SettingInformation extends Thread {

    private FileOpenDialog dialog;

    private int searchedFiles;

    private int foundFiles;

    public SettingInformation(FileOpenDialog _dialog, int _searchedFiles,
            int _foundFiles) {
        dialog = _dialog;
        searchedFiles = _searchedFiles;
        foundFiles = _foundFiles;
    }

    @Override
    public void run() {
        dialog.setInformations(searchedFiles, foundFiles);
    }
}
