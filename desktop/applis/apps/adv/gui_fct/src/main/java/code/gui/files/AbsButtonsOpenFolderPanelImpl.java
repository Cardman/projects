package code.gui.files;

import code.gui.AbsButton;
import code.gui.events.AbsActionListener;
import code.util.StringMap;

public abstract class AbsButtonsOpenFolderPanelImpl implements AbsButtonsOpenFolderPanel {
    protected AbsButtonsOpenFolderPanelImpl(){}
    @Override
    public void build(FolderOpenDialogContent _content) {
        StringMap<String> messages_ = FileFrame.getAppliTr(_content.getProgramInfos().currentLg()).getMapping().getVal(FolderOpenFrame.FOLDER_OPEN_DIAL).getMapping();
        AbsButton action_ = _content.getCompoFactory().newPlainButton(messages_.getVal(MessagesFolderOpenDialog.OPEN));
        action_.addActionListener(buildEvent(_content));
        _content.getButtons().add(action_);
        action_ = _content.getCompoFactory().newPlainButton(messages_.getVal(MessagesFolderOpenDialog.CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(_content));
        _content.getButtons().add(action_);
    }
    protected abstract AbsActionListener buildEvent(FolderOpenDialogContent _content);
}
