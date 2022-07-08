package aiki.gui.dialogs;




import aiki.gui.dialogs.events.ClosingSelectHealingItem;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorHealingItem;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class SelectHealingItem extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selecthealingitem";

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

//    private boolean ok;

    private StringMap<String> messages;
    private final AbsCompoFactory compo;

    public SelectHealingItem(AbstractProgramInfos _infos) {
        super(_infos.getFrameFactory());
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo= _infos.getCompoFactory();
    }

    @Override
    protected AbsCloseableDialog build() {
        return new ClosingSelectHealingItem(this);
    }

    public static void setSelectHealingItem(WindowAiki _parent, FacadeGame _facade) {
        _parent.getSelectHealingItem().init(_parent, _facade);
    }

    private void init(WindowAiki _parent, FacadeGame _facade) {
        getSelectDial().setDialogIcon(_parent.getImageFactory(),_parent.getCommonFrame());
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(messages.getVal(TITLE));
        setFacade(_facade);
//        ok = false;
        initOk();
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        contentPane_.add(compo.newAbsScrollPane(new PaginatorHealingItem(_parent,pag_, getSelectDial(), _facade).getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        AbsPlainButton ok_ = _parent.getCompoFactory().newPlainButton(WindowAiki.OK);
        ok_.addActionListener(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        AbsPlainButton cancel_ = _parent.getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        cancel_.addActionListener(new ClosingDialogEvent(getSelectDial(),getBuilt()));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        getSelectDial().setContentPane(contentPane_);
        getSelectDial().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        getSelectDial().pack();
    }

//    @Override
    public void closeWindow() {
        getFacade().clearFiltersHealingItem();
        getSelectDial().closeWindow();
    }

    public static boolean isSelectedIndex(SelectHealingItem _dialog) {
        _dialog.getSelectDial().setVisible(true);
        return _dialog.getFacade().getLineHealingItem() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk(SelectHealingItem _dialog) {
        return _dialog.isSelected();
    }

}
