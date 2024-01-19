package code.gui.files;

import code.gui.AbsTextField;
import code.gui.AbsTxtComponent;
import code.gui.events.AbsActionListener;
import code.gui.events.AfterValidateText;
import code.gui.events.DefValidateText;
import code.stream.AbstractFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public class SubmitKeyEvent implements AbsActionListener,AfterValidateText {

    private final FileDialogContent dialog;
    private final AbsTextField txt;

    public SubmitKeyEvent(FileDialogContent _dialog, AbsTextField _t) {
        dialog = _dialog;
        txt = _t;
    }

    @Override
    public void act(AbsTxtComponent _compo, String _typed) {
        check(_typed);
        txt.setText(_typed);
        txt.setCaretPosition(_typed.length());
    }

    @Override
    public void action() {
        if (dialog instanceof FileSaveDialogContent) {
            return;
        }
        dialog.submitIfVisible();
    }

    @Override
    public StringList filter(String _look, CustList<String> _dict) {
        check(_look);
        return DefValidateText.filt(_look, _dict);
    }

    private void check(String _look) {
        dialog.getErrors().setText("");
        if (existFile(_look)) {
            String path_ = StringUtil.concat(dialog.getCurrentFolder(), _look);
            StringMap<String> messages_ = FileDialog.getAppliTr(dialog.getProgramInfos().getTranslations().getMapping().getVal(dialog.getLang())).getMapping().getVal(FileSaveDialog.FILE_SAVE_DIAL).getMapping();
            dialog.getErrors().setText(StringUtil.simpleStringsFormat(messages_.getVal(MessagesFileSaveDialog.BODY_CONF), path_));
        }
    }
    private boolean existFile(String _look) {
        if (dialog instanceof FileSaveDialogContent) {
            String path_ = StringUtil.concat(dialog.getCurrentFolder(), _look);
            AbstractFile file_ = dialog.getProgramInfos().getFileCoreStream().newFile(path_);
            return file_.exists();
        }
        return false;
    }

}
