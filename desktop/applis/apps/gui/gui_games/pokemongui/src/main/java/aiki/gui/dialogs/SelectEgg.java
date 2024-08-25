package aiki.gui.dialogs;




import aiki.gui.components.walk.events.ConsultEggEvent;
import aiki.gui.dialogs.events.ClosingSelectEgg;
import aiki.sml.MessagesPkGame;
import aiki.sml.MessagesRenderPaginatorEgg;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorEgg;
import code.gui.*;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.StringMap;

public final class SelectEgg extends SelectDialog {
//    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectegg";

//    private static final String TITLE = "title";

//    private boolean ok;

    private final AbsCompoFactory compo;

    private int lineBack;
    private boolean consulting;
    private PaginatorEgg paginatorEgg;

    public SelectEgg(AbstractProgramInfos _infos, WindowAiki _window) {
        super(_infos.getFrameFactory(), _window);
//        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _infos.getCompoFactory();
    }

    @Override
    protected AbsWindowListenerClosing build() {
        return new ClosingSelectEgg(this);
    }

    public static void setSelectEgg(WindowAiki _parent, FacadeGame _facade, SelectEgg _dialog, boolean _consult) {
        _dialog.init(_parent, _facade, _consult);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, boolean _consult) {
        _parent.getModal().set(true);
        lineBack = _facade.getLineEgg();
        consulting = _consult;
        getSelectDial().setIconImage(_parent.getCommonFrame().getImageIconFrame());
        //
//        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(fileEgg(_parent.getFrames().currentLg()).getVal(MessagesRenderPaginatorEgg.TITLE));
        setFacade(_facade);
        initOk();
//        ok = false;
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        paginatorEgg = new PaginatorEgg(_parent, pag_, getSelectDial(), _facade);
        contentPane_.add(compo.newAbsScrollPane(paginatorEgg.getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
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

    public static StringMap<String> fileEgg(TranslationsLg _lg) {
        return MessagesPkGame.getPaginatorSelEggContentTr(MessagesPkGame.getAppliTr(_lg)).getMapping();
    }
    @Override
    public void validateChoice() {
        okChoice();
        getMainWindow().getModal().set(false);
        closeWindow();
        if (consulting) {
            ConsultEggEvent.consult(lineBack,getFacade());
            return;
        }
        getMainWindow().getScenePanel().afterSelectEgg();
    }

//    @Override
    public void closeWindow() {
        getMainWindow().getModal().set(false);
        getFacade().clearFiltersEgg();
        getSelectDial().setVisible(false);
        if (!isSelected()) {
            getFacade().setLineEggs(lineBack);
        }
    }

//    public static boolean isSelectedIndex(SelectEgg _dialog) {
//        return _dialog.getFacade().getLineEgg() != IndexConstants.INDEX_NOT_FOUND_ELT;
//    }
//
//    public static boolean isOk(SelectEgg _dialog) {
//        return _dialog.isSelected();
//    }

    public PaginatorEgg getPaginatorEgg() {
        return paginatorEgg;
    }
}
