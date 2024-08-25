package aiki.gui.dialogs;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorItem;
import aiki.gui.dialogs.events.ClosingSelectItem;
import aiki.sml.MessagesPkGame;
import aiki.sml.MessagesRenderPaginatorItem;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class SelectItem extends SelectDialog {
    public static final int USE = 0;
    public static final int BUY = 1;
    public static final int SELL = 2;
//    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectitem";

//    private static final String TITLE = "title";

//    private static final String GIVE = "give";

//    private static final String SET_FIELDS = "setFields";

//    private boolean ok;

//    private boolean give;

    private AbsCustCheckBox giveCheckBox;

    private final AbsCompoFactory compo;

    private int act;
    private PaginatorItem paginatorItem;

    public SelectItem(AbstractProgramInfos _infos, WindowAiki _window) {
        super(_infos.getFrameFactory(), _window);
//        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _infos.getCompoFactory();
    }

    @Override
    protected AbsWindowListenerClosing build() {
        return new ClosingSelectItem(this);
    }

    public static void setSelectItem(WindowAiki _parent, FacadeGame _facade, int _action) {
        _parent.getSelectItem().init(_parent, _facade, _action);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, int _action) {
        _parent.getModal().set(true);
        act = _action;
        getSelectDial().setIconImage(_parent.getCommonFrame().getImageIconFrame());
        StringMap<String> messages_ = fileIt(_parent.getFrames().currentLg());
//        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(messages_.getVal(MessagesRenderPaginatorItem.TITLE));
        setFacade(_facade);
        initOk();
//        ok = false;
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        paginatorItem = new PaginatorItem(_parent, pag_, getSelectDial(), _facade, _action == BUY);
        contentPane_.add(compo.newAbsScrollPane(paginatorItem.getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        if (_action == USE) {
            giveCheckBox = _parent.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPaginatorItem.GIVE));
//            giveCheckBox.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent _arg0) {
//                    give = giveCheckBox.isSelected();
//                }
//            });
            buttons_.add(giveCheckBox);
        }
        buttons(_parent,buttons_);
//        AbsButton ok_ = _parent.getCompoFactory().newPlainButton(WindowAiki.OK);
//        ok_.addActionListener(new ValidateSelectionEvent(this));
//        buttons_.add(ok_);
//        AbsButton cancel_ = _parent.getCompoFactory().newPlainButton(messages.getVal(CANCEL));
//        cancel_.addActionListener(new ClosingSelectButtonEvt(getSelectDial(), _parent));
//        buttons_.add(cancel_);
        contentPane_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        getSelectDial().setContentPane(contentPane_);
//        getSelectDial().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        getSelectDial().pack();
        getSelectDial().setVisible(true);
    }

    public static StringMap<String> fileIt(TranslationsLg _lg) {
        return MessagesPkGame.getPaginatorSelItContentTr(MessagesPkGame.getAppliTr(_lg)).getMapping();
    }
//    @Override
    public void closeWindow() {
        getMainWindow().getModal().set(false);
        getFacade().clearFiltersItem();
        getSelectDial().setVisible(false);
    }

    @Override
    public void validateChoice() {
        okChoice();
        closeWindow();
        if (act != USE) {
            getMainWindow().getScenePanel().afterSelectItemBuy();
        } else {
            getMainWindow().getScenePanel().afterSelectItemPk();
        }
    }

    public PaginatorItem getPaginatorItem() {
        return paginatorItem;
    }

    public AbsCustCheckBox getGiveCheckBox() {
        return giveCheckBox;
    }

    public static boolean isSelectedIndex(SelectItem _dialog) {
        return _dialog.getFacade().getLineItem() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }
//
//    public static boolean isOk(SelectItem _dialog) {
//        return _dialog.isSelected();
//    }

    public static boolean isGive(SelectItem _dialog) {
        return _dialog.giveCheckBox.isSelected();
    }

    //    public static void setGive(boolean _give) {
//        DIALOG.giveCheckBox.setSelected(_give);
////        DIALOG.give = _give;
//    }
}
