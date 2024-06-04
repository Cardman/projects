package code.gui.files;

import code.gui.AbsButton;
import code.gui.events.AbsActionListener;
import code.util.StringMap;

public abstract class AbsButtonsOpenPanelImpl implements AbsButtonsOpenPanel {
    protected AbsButtonsOpenPanelImpl(){}
    @Override
    public void build(FileOpenDialogContent _content) {
        StringMap<String> messages_ = FileFrame.getAppliTr(_content.getProgramInfos().currentLg()).getMapping().getVal(FileOpenFrame.FILE_OPEN_DIAL).getMapping();
        AbsButton action_ = _content.getCompoFactory().newPlainButton(messages_.getVal(MessagesFileOpenDialog.OPEN));
        action_.addActionListener(buildEvent(_content));
        _content.getButtons().add(action_);
        action_ = _content.getCompoFactory().newPlainButton(messages_.getVal(MessagesFileOpenDialog.CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(_content));
        _content.getButtons().add(action_);
    }
    protected abstract AbsActionListener buildEvent(FileOpenDialogContent _content);
}
