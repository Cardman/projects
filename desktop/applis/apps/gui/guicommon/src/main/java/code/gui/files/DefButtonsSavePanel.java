package code.gui.files;

import code.gui.AbsButton;
import code.util.StringMap;

public final class DefButtonsSavePanel implements AbsButtonsSavePanel {
    @Override
    public void build(FileSaveDialogContent _content) {
        StringMap<String> messages_ = FileDialog.getAppliTr(_content.getProgramInfos().getTranslations().getMapping().getVal(_content.getLang())).getMapping().getVal(FileSaveDialog.FILE_SAVE_DIAL).getMapping();
        AbsButton action_ = _content.getCompoFactory().newPlainButton(messages_.getVal(MessagesFileSaveDialog.SAVE));
        action_.addActionListener(new SubmitMouseEvent(_content));
        _content.getButtons().add(action_);
        action_ = _content.getCompoFactory().newPlainButton(messages_.getVal(MessagesFileSaveDialog.CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(_content));
        _content.getButtons().add(action_);
    }
}
