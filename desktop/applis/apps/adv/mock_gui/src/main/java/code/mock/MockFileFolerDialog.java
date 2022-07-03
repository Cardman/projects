package code.mock;

import code.gui.*;

public final class MockFileFolerDialog implements FolderOpenDialogAbs, FileSaveDialogAbs, FileOpenDialogAbs {

    private final MockTyping<String> answers;

    public MockFileFolerDialog(String[] _s) {
        this.answers = MockTypingUtil.wrap(_s);
    }
    @Override
    public String input(AbsCommonFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        return answers.incr("");
    }

    @Override
    public String input(AbsCommonFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        return answers.incr("");
    }

    @Override
    public String input(AbsCommonFrame _w, String _language, boolean _currentFolderRoot) {
        return answers.incr("");
    }
}
