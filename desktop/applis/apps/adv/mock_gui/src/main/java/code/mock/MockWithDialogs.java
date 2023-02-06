package code.mock;

import code.gui.*;
import code.gui.events.SetterLanguage;

public final class MockWithDialogs implements WithDialogs {

    private final MockSetterLanguage mockSetterLanguage = new MockSetterLanguage();
    private final MockFileFolerDialog mockFileFolerDialog;
    private final ConfirmDialogTextAbs mockConfirmDialogTextAbs;
    private final ConfirmDialogAnsAbs mockConfirmDialogAnsAbs;
    public MockWithDialogs(MockAbsProgramInfos _pr) {
        mockFileFolerDialog = _pr.getMockFileFolerDialog();
        mockConfirmDialogTextAbs = _pr.getConfirmDialogText();
        mockConfirmDialogAnsAbs = _pr.getConfirmDialogAns();
    }

    @Override
    public ConfirmDialogTextAbs getConfirmDialogText() {
        return mockConfirmDialogTextAbs;
    }

    @Override
    public ConfirmDialogAnsAbs getConfirmDialogAns() {
        return mockConfirmDialogAnsAbs;
    }

    @Override
    public SetterLanguage getLanguageDialog() {
        return mockSetterLanguage;
    }

    @Override
    public FolderOpenDialogAbs getFolderOpenDialogInt() {
        return getMockFileFolerDialog();
    }

    @Override
    public FileOpenDialogAbs getFileOpenDialogInt() {
        return getMockFileFolerDialog();
    }

    @Override
    public FileSaveDialogAbs getFileSaveDialogInt() {
        return getMockFileFolerDialog();
    }

    public MockFileFolerDialog getMockFileFolerDialog() {
        return mockFileFolerDialog;
    }
}
