package aiki.gui.dialogs;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorMove;
import aiki.gui.dialogs.events.ClosingSelectTm;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPaginatorMove;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class SelectTm extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selecttm";

//    private static final String TITLE = "title";

//    private boolean ok;

    private final AbsCompoFactory compo;

    private boolean buying;
    private PaginatorMove paginatorMove;

    public SelectTm(AbstractProgramInfos _infos, WindowAiki _window) {
        super(_infos.getFrameFactory(), _window);
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _infos.getCompoFactory();
    }

    @Override
    protected AbsWindowListenerClosing build() {
        return new ClosingSelectTm(this);
    }

    public static void setSelectTm(WindowAiki _parent, FacadeGame _facade, boolean _buy) {
        _parent.getSelectTm().init(_parent, _facade, _buy);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, boolean _buy) {
        _parent.getModal().set(true);
        buying = _buy;
        getSelectDial().setIconImage(_parent.getCommonFrame().getImageIconFrame());
        StringMap<String> messages_ = fileMv(_parent.getFrames().currentLg());
//        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(messages_.getVal(MessagesRenderPaginatorMove.TITLE));
        setFacade(_facade);
//        ok = false;
        initOk();
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        paginatorMove = new PaginatorMove(_parent, pag_, getSelectDial(), _facade, _buy);
        contentPane_.add(compo.newAbsScrollPane(paginatorMove.getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compo.newLineBox();
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

    public static StringMap<String> fileMv(TranslationsLg _lg) {
        return GamesPk.getPaginatorSelMvContentTr(GamesPk.getAppliTr(_lg)).getMapping();
    }
//    @Override
    public void closeWindow() {
        getMainWindow().getModal().set(false);
        getFacade().clearFiltersMove();
        getSelectDial().setVisible(false);
    }

    @Override
    public void validateChoice() {
        okChoice();
        closeWindow();
        if (buying) {
            getMainWindow().getScenePanel().afterSelectBuy();
        } else {
            getMainWindow().getScenePanel().afterSelectLearn();
        }
    }

    public PaginatorMove getPaginatorMove() {
        return paginatorMove;
    }

    public static boolean isSelectedIndex(SelectTm _dialog) {
        return _dialog.getFacade().getLineMove() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk(SelectTm _dialog) {
        return _dialog.isSelected();
    }

}
