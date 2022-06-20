package code.gui;

public interface WithDialogs {
    FileOpenDialog getFileOpenDialog();
    FileSaveDialog getFileSaveDialog();
    FolderOpenDialog getFolderOpenDialog();
    ConfirmDialog getConfirmDialog();
    LanguageDialog getLanguageDialog();
}
