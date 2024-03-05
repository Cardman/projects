package code.gui;

public interface WithDialogs {

    ConfirmDialogTextAbs getConfirmDialogText();
    ConfirmDialogAnsAbs getConfirmDialogAns();
//    SetterLanguage getLanguageDialog();
    FolderOpenDialogAbs getFolderOpenDialogInt();
    FileOpenDialogAbs getFileOpenDialogInt();
    FileSaveDialogAbs getFileSaveDialogInt();
}
