package code.gui;

import code.gui.events.SetterLanguage;

public interface WithDialogs {

    ConfirmDialogTextAbs getConfirmDialogText();
    ConfirmDialogAnsAbs getConfirmDialogAns();
    SetterLanguage getLanguageDialog();
    FolderOpenDialogAbs getFolderOpenDialogInt();
    FileOpenDialogAbs getFileOpenDialogInt();
    FileSaveDialogAbs getFileSaveDialogInt();
}
