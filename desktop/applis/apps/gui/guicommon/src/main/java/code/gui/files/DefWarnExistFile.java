package code.gui.files;

import code.gui.AbsDialog;
import code.gui.GuiConstants;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.AbstractFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DefWarnExistFile implements AbsWarnExistFile {
    private final AbsDialog dialog;

    public DefWarnExistFile(AbsDialog _d) {
        this.dialog = _d;
    }

    @Override
    public boolean skipSave(AbstractProgramInfos _pr, String _path, String _lg) {
        StringMap<String> messages_ = FileDialog.getAppliTr(_pr.getTranslations().getMapping().getVal(_lg)).getMapping().getVal(FileSaveDialog.FILE_SAVE_DIAL).getMapping();
        AbstractFile file_ = _pr.getFileCoreStream().newFile(_path);
        if (file_.exists()) {
            String mes_ = StringUtil.simpleStringsFormat(messages_.getVal(MessagesFileSaveDialog.BODY_CONF), _path);
            int answer_ = _pr.getConfirmDialogAns().input(
                    dialog,null,
                    mes_, messages_.getVal(MessagesFileSaveDialog.TITLE_CONF),
                    _lg,
                    GuiConstants.YES_NO_OPTION);
            return answer_ == GuiConstants.NO_OPTION;
        }
        return false;
    }
}
