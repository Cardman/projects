package code.gui.files;

import code.gui.AbsButton;
import code.util.StringMap;

public final class AdvButtonsSavePanel implements AbsButtonsSavePanel {
    private final AbsSaveFile saveFile;
    private final AbsContinueFile continueFile;

    public AdvButtonsSavePanel(AbsSaveFile _s,AbsContinueFile _c) {
        this.saveFile = _s;
        this.continueFile = _c;
    }

    @Override
    public void build(FileSaveDialogContent _content) {
        StringMap<String> messages_ = FileFrame.getAppliTr(_content.getProgramInfos().currentLg()).getMapping().getVal(FileFrame.CONFIRM).getMapping();
        AbsButton button_ = _content.getProgramInfos().getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.YES));
        button_.addActionListener(new SaveSelectFileEvent(saveFile,continueFile,_content));
        _content.getButtons().add(button_);
        button_ = _content.getProgramInfos().getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.NO));
        button_.addActionListener(new SkipSelectFileEvent(continueFile,_content));
        _content.getButtons().add(button_);
        button_ = _content.getProgramInfos().getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.CANCEL));
        button_.addActionListener(new CancelSelectFileEvent(_content));
        _content.getButtons().add(button_);
    }
}
