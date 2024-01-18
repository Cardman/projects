package code.gui.files;

/**Thread safe class*/
public final class SettingInformation implements Runnable {

    private final FileOpenDialogContent dialog;

    private final long searchedFiles;

    private final long foundFiles;

    public SettingInformation(FileOpenDialogContent _dialog, long _searchedFiles,
                              long _foundFiles) {
        dialog = _dialog;
        searchedFiles = _searchedFiles;
        foundFiles = _foundFiles;
    }

    @Override
    public void run() {
        dialog.setInformations(searchedFiles, foundFiles);
    }
}
