package code.gui;

public interface WithDialogs {

    ConfirmDialogTextAbs getConfirmDialogText();
    ConfirmDialogAnsAbs getConfirmDialogAns();
    LanguageDialog getLanguageDialog();
    FolderOpenDialogAbs getFolderOpenDialogInt();
    FileOpenDialogAbs getFileOpenDialogInt();
    FileSaveDialogAbs getFileSaveDialogInt();
}
