package code.gui;

public interface WithDialogs {
    FileOpenDialog getFileOpenDialog();
    FileSaveDialog getFileSaveDialog();
    FolderOpenDialog getFolderOpenDialog();
    ConfirmDialog getConfirmDialog();
    ConfirmDialogTextAbs getConfirmDialogText();
    ConfirmDialogAnsAbs getConfirmDialogAns();
    LanguageDialog getLanguageDialog();
    FolderOpenDialogAbs getFolderOpenDialogInt();
    FileOpenDialogAbs getFileOpenDialogInt();
    FileSaveDialogAbs getFileSaveDialogInt();
}
