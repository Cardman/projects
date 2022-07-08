package aiki.gui.dialogs;


import aiki.gui.dialogs.events.ClosingSelectPokemon;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorPokemon;
import aiki.gui.dialogs.events.SeePkDetailEvent;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import aiki.map.pokemon.UsablePokemon;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractThread;
import code.util.StringMap;

public final class SelectPokemon extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectpokemon";

    private static final String TITLE = "title";

    private static final String TITLE_DETAIL = "titleDetail";

    private static final String CANCEL = "cancel";

    private static final String DETAIL = "detail";

//    private MainWindow window;

    private boolean storage;

//    private boolean ok;

    private StringMap<String> messages;
    private WindowAiki window;
    private final AbsCompoFactory compo;

    public SelectPokemon(AbstractProgramInfos _infos) {
        super(_infos.getFrameFactory());
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _infos.getCompoFactory();
    }

    @Override
    protected AbsCloseableDialog build() {
        return new ClosingSelectPokemon(this);
    }

    public static void setSelectPokemon(WindowAiki _parent, FacadeGame _facade, boolean _storage, SelectPokemon _dialog) {
        _dialog.init(_parent, _facade, _storage);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, boolean _storage) {
        //super(_parent, true);
        getSelectDial().setDialogIcon(_parent.getImageFactory(),_parent.getCommonFrame());
        window = _parent;
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
//        window = _parent;
        getSelectDial().setTitle(messages.getVal(TITLE));
        setFacade(_facade);
        storage = _storage;
        initOk();
//        ok = false;
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        contentPane_.add(compo.newAbsScrollPane(new PaginatorPokemon(_parent,pag_, getSelectDial(), _facade).getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        AbsPlainButton detail_ = _parent.getCompoFactory().newPlainButton(messages.getVal(DETAIL));
        detail_.addActionListener(new SeePkDetailEvent(this));
        buttons_.add(detail_);
        AbsPlainButton ok_ = _parent.getCompoFactory().newPlainButton(WindowAiki.OK);
        ok_.addActionListener(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        AbsPlainButton cancel_ = _parent.getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        cancel_.addActionListener(new ClosingDialogEvent(getSelectDial(),getBuilt()));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        getSelectDial().setContentPane(contentPane_);
        //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getSelectDial().pack();
    }

    public void seePkDetail() {
        AbstractThread thread_ = window.getPreparedPkThread();
        PreparedRenderedPages task_ = window.getPreparedPkTask();
        if (thread_ == null || thread_.isAlive() || task_ == null) {
            return;
        }
        UsablePokemon p_ = getFacade().getSelectedPokemonFirstBox();
        if (p_ == null) {
            return;
        }
        RenderedPage session_;
        session_ = new RenderedPage(compo.newAbsScrollPane(), window.getFrames());
        showHtmlDialog(session_, getFacade(),task_, getFacade().getLanguage());
    }

    @Override
    public void validateChoice() {
        if (!storage) {
            getFacade().clearFoundResultsStoragePokemon();
        }
        super.validateChoice();
    }

//    @Override
    public void closeWindow() {
        getFacade().clearFiltersFirstBox();
        getSelectDial().closeWindow();
    }

    public static boolean isSelectedIndex(SelectPokemon _dialog) {
        setVisible(_dialog);
        return _dialog.getFacade().getSelectedPokemonFirstBox() != null;
    }

    private boolean isOk() {
        return isSelected();
    }

    public static boolean isStaticOk(SelectPokemon _dialog) {
        return _dialog.isOk();
    }

    private void showHtmlDialog(RenderedPage _session, FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
//        DialogHtmlData.setDialogHtmlData(DIALOG, DIALOG.messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
        DialogHtmlData.setDialogHtmlData(window, getSelectDial(), messages.getVal(TITLE_DETAIL), _session,_dataBase,_pre,_lg);
    }

    public static void setVisible(SelectPokemon _dialog) {
        _dialog.getSelectDial().setVisible(true);
    }

}
