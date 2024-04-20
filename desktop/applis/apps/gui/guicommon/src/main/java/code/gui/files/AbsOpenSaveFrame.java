package code.gui.files;

import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;
import code.util.StringMap;

public abstract class AbsOpenSaveFrame extends FileFrame {
    private AbsButton mainAction;
    private AbsButton closeAction;
    private AbsTabbedPane tabs;
    private AbsPlainLabel labSave;
    private AbsPlainLabel labOpen;
    protected AbsOpenSaveFrame(AbstractProgramInfos _frameFact, AbstractAtomicBoolean _m) {
        super(_frameFact, _m);
    }

    protected void common() {
        AbsCompoFactory compoFactory_ = getPrInfos().getCompoFactory();
        tabs = compoFactory_.newAbsTabbedPane();
        labSave = compoFactory_.newPlainLabel("");
        labOpen = compoFactory_.newPlainLabel("");
    }
    protected void common(AbsSaveFile _save, AbsContinueFile _c, FileDialogContent _o, FileDialogContent _s) {
        AbsCompoFactory compoFactory_ = getPrInfos().getCompoFactory();
        AbsPanel contentPane_ = compoFactory_.newBorder();
        contentPane_.add(tabs, GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compoFactory_.newPageBox();
        buttons_.add(labSave);
        buttons_.add(labOpen);

        StringMap<String> messages_ = FileDialog.getAppliTr(getPrInfos().currentLg()).getMapping().getVal(ConfirmDialog.CONFIRM).getMapping();
        AbsButton button_ = compoFactory_.newPlainButton(messages_.getVal(MessagesConfirmDialog.YES));
        mainAction = button_;
        button_.addActionListener(new SaveOpenSelectFileEvent(this, _save, _c, _s, _o));
        buttons_.add(button_);
//        button_ = _content.getProgramInfos().getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.NO));
//        button_.addActionListener(new SkipSelectFileEvent(_c,_content));
//        _content.getButtons().add(button_);
        button_ = compoFactory_.newPlainButton(messages_.getVal(MessagesConfirmDialog.NO));
        closeAction = button_;
        button_.addActionListener(new CloseSelectFileEvent(getClosing()));
        buttons_.add(button_);
        contentPane_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);

        getFrame().setContentPane(contentPane_);
        getFrame().pack();
        getFrame().setVisible(true);
    }

    public AbsButton getMainAction() {
        return mainAction;
    }

    public AbsButton getCloseAction() {
        return closeAction;
    }

    public AbsTabbedPane getTabs() {
        return tabs;
    }

    public AbsPlainLabel getLabOpen() {
        return labOpen;
    }

    public AbsPlainLabel getLabSave() {
        return labSave;
    }
}
