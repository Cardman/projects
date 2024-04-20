package aiki.gui.dialogs;




import aiki.gui.components.walk.events.ConsultEggEvent;
import aiki.gui.dialogs.events.ClosingSelectButtonEvt;
import aiki.gui.dialogs.events.ClosingSelectEgg;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorEgg;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.*;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class SelectEgg extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectegg";

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

//    private boolean ok;

    private StringMap<String> messages;
    private final AbsCompoFactory compo;

    private int lineBack;
    private boolean consulting;
    public SelectEgg(AbstractProgramInfos _infos, WindowAiki _window) {
        super(_infos.getFrameFactory(), _window);
        getSelectDial().setAccessFile(DIALOG_ACCESS);
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
        lineBack = getFacade().getLineEgg();
        consulting = _consult;
        getSelectDial().setIconImage(_parent.getCommonFrame().getImageIconFrame());
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(messages.getVal(TITLE));
        setFacade(_facade);
        initOk();
//        ok = false;
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        contentPane_.add(compo.newAbsScrollPane(new PaginatorEgg(_parent,pag_, getSelectDial(), _facade).getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        AbsButton ok_ = _parent.getCompoFactory().newPlainButton(WindowAiki.OK);
        ok_.addActionListener(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        AbsButton cancel_ = _parent.getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        cancel_.addActionListener(new ClosingSelectButtonEvt(getSelectDial(), _parent));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        getSelectDial().setContentPane(contentPane_);
//        getSelectDial().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        getSelectDial().pack();
        getSelectDial().setVisible(true);
    }

    @Override
    public void validateChoice() {
        getMainWindow().getModal().set(false);
        closeWindow();
        if (consulting) {
            ConsultEggEvent.consult(lineBack,getFacade());
            return;
        }
        getMainWindow().getScenePanel().afterSelectEgg(lineBack);
    }

//    @Override
    public void closeWindow() {
        getFacade().clearFiltersEgg();
        getSelectDial().setVisible(false);
    }

//    public static boolean isSelectedIndex(SelectEgg _dialog) {
//        return _dialog.getFacade().getLineEgg() != IndexConstants.INDEX_NOT_FOUND_ELT;
//    }

    public static boolean isOk(SelectEgg _dialog) {
        return _dialog.isSelected();
    }

}
