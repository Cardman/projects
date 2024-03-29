package aiki.gui.dialogs;




import aiki.gui.dialogs.events.ClosingSelectItem;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorItem;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class SelectItem extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectitem";

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private static final String GIVE = "give";

//    private static final String SET_FIELDS = "setFields";

//    private boolean ok;

//    private boolean give;

    private AbsCustCheckBox giveCheckBox;

    private StringMap<String> messages;
    private final AbsCompoFactory compo;

    public SelectItem(AbstractProgramInfos _infos) {
        super(_infos.getFrameFactory());
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _infos.getCompoFactory();
    }

    @Override
    protected AbsCloseableDialog build() {
        return new ClosingSelectItem(this);
    }

    public static void setSelectItem(WindowAiki _parent, FacadeGame _facade, boolean _buy, boolean _sell) {
        _parent.getSelectItem().init(_parent, _facade, _buy, _sell);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, boolean _buy, boolean _sell) {
        getSelectDial().setDialogIcon(_parent.getImageFactory(),_parent.getCommonFrame());
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(messages.getVal(TITLE));
        setFacade(_facade);
        initOk();
//        ok = false;
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        contentPane_.add(compo.newAbsScrollPane(new PaginatorItem(_parent,pag_, getSelectDial(), _facade, !_sell).getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        if (!_buy) {
            giveCheckBox = _parent.getCompoFactory().newCustCheckBox(messages.getVal(GIVE));
//            giveCheckBox.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent _arg0) {
//                    give = giveCheckBox.isSelected();
//                }
//            });
            buttons_.add(giveCheckBox);
        }
        AbsButton ok_ = _parent.getCompoFactory().newPlainButton(WindowAiki.OK);
        ok_.addActionListener(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        AbsButton cancel_ = _parent.getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        cancel_.addActionListener(new ClosingDialogEvent(getSelectDial(),getBuilt()));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        getSelectDial().setContentPane(contentPane_);
//        getSelectDial().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        getSelectDial().pack();
    }

//    @Override
    public void closeWindow() {
        getFacade().clearFiltersItem();
        getSelectDial().closeWindow();
    }

    public static boolean isSelectedIndex(SelectItem _dialog) {
        _dialog.getSelectDial().setVisible(true);
        return _dialog.getFacade().getLineItem() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk(SelectItem _dialog) {
        return _dialog.isSelected();
    }

    public static boolean isGive(SelectItem _dialog) {
        return _dialog.giveCheckBox.isSelected();
    }

    //    public static void setGive(boolean _give) {
//        DIALOG.giveCheckBox.setSelected(_give);
////        DIALOG.give = _give;
//    }
}
