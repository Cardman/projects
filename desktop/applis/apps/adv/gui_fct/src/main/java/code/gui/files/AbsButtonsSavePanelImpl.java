package code.gui.files;

import code.gui.AbsButton;
import code.gui.events.AbsActionListener;
import code.util.StringMap;

public abstract class AbsButtonsSavePanelImpl implements AbsButtonsSavePanel {
    protected AbsButtonsSavePanelImpl(){}
    @Override
    public void build(FileSaveDialogContent _content) {
        StringMap<String> messages_ = MessagesGuiFct.getAppliTr(_content.getProgramInfos().currentLg()).getMapping().getVal(MessagesGuiFct.FILE_SAVE_DIAL).getMapping();
        AbsButton action_ = _content.getCompoFactory().newPlainButton(messages_.getVal(MessagesFileSaveDialog.SAVE));
        action_.addActionListener(buildEvent(_content));
        _content.getButtons().add(action_);
        action_ = _content.getCompoFactory().newPlainButton(messages_.getVal(MessagesFileSaveDialog.CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(_content));
        _content.getButtons().add(action_);
    }
    protected abstract AbsActionListener buildEvent(FileSaveDialogContent _content);
}
