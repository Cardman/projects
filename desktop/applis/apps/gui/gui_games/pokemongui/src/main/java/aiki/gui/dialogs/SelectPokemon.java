package aiki.gui.dialogs;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorPokemon;
import aiki.gui.components.PkDetailContent;
import aiki.gui.components.walk.events.ConsultPokemonEvent;
import aiki.gui.dialogs.events.ClosingSelectPokemon;
import aiki.gui.dialogs.events.SeePkDetailEvent;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPaginatorPk;
import code.gui.AbsButton;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.StringMap;

public final class SelectPokemon extends SelectDialog {
//    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectpokemon";

//    private static final String TITLE = "title";

//    private static final String TITLE_DETAIL = "titleDetail";

//    private static final String DETAIL = "detail";
    private final AbsPanel mainComponent;

//    private MainWindow window;

    private boolean storage;

//    private boolean ok;

    private WindowAiki window;
    private final AbsCompoFactory compo;
    private final PkDetailContent pkDetailContent;
    private boolean consulting;
    private int lineBack;
    private PaginatorPokemon paginatorPokemon;

    public SelectPokemon(AbstractProgramInfos _infos, WindowAiki _window) {
        super(_infos.getFrameFactory(), _window);
        pkDetailContent = new PkDetailContent(_infos);
//        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _infos.getCompoFactory();
        mainComponent = compo.newBorder();
    }

    @Override
    protected AbsWindowListenerClosing build() {
        return new ClosingSelectPokemon(this);
    }

    public static void setSelectPokemon(WindowAiki _parent, FacadeGame _facade, boolean _storage, SelectPokemon _dialog, boolean _consult) {
        _dialog.init(_parent, _facade, _storage, _consult);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, boolean _storage, boolean _consult) {
        //super(_parent, true);
        _parent.getModal().set(true);
        lineBack = _facade.getLineFirstBox();
        consulting = _consult;
        getSelectDial().setIconImage(_parent.getCommonFrame().getImageIconFrame());
        window = _parent;
//        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        StringMap<String> messages_ = filePk(_parent.getFrames().currentLg());
//        window = _parent;
        getSelectDial().setTitle(messages_.getVal(MessagesRenderPaginatorPk.TITLE));
        setFacade(_facade);
        storage = _storage;
        initOk();
//        ok = false;
        mainComponent.removeAll();
        AbsPanel pag_ = compo.newPageBox();
        AbsButton detail_ = _parent.getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPaginatorPk.DETAIL));
        paginatorPokemon = new PaginatorPokemon(_parent, pag_, getSelectDial(), _facade, detail_);
        mainComponent.add(compo.newAbsScrollPane(paginatorPokemon.getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        detail_.addActionListener(new SeePkDetailEvent(this));
        buttons_.add(detail_);
        buttons(_parent,buttons_);
//        AbsButton ok_ = _parent.getCompoFactory().newPlainButton(WindowAiki.OK);
//        ok_.addActionListener(new ValidateSelectionEvent(this));
//        buttons_.add(ok_);
//        AbsButton cancel_ = _parent.getCompoFactory().newPlainButton(messages_.getVal(CANCEL));
//        cancel_.addActionListener(new ClosingSelectButtonEvt(getSelectDial(), _parent));
//        buttons_.add(cancel_);
        mainComponent.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        mainComponent.add(pkDetailContent.getContent(), GuiConstants.BORDER_LAYOUT_EAST);
        getSelectDial().setContentPane(mainComponent);
        //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getSelectDial().pack();
        setVisible(this);
    }

    public static StringMap<String> filePk(TranslationsLg _lg) {
        return GamesPk.getPaginatorSelPkContentTr(GamesPk.getAppliTr(_lg)).getMapping();
    }
    public void seePkDetail() {
//        AbstractThread thread_ = window.getPreparedPkThread();
//        AikiNatLgNamesNavigation task_ = window.getPreparedPkTask();
//        if (thread_ == null || thread_.isAlive() || task_ == null) {
//            return;
//        }
//        UsablePokemon p_ = getFacade().getSelectedPokemonFirstBox();
//        if (p_ == null) {
//            return;
//        }

        pkDetailContent.group(window, getFacade(),window.getPreparedPkTask(), getFacade().getLanguage(), getSelectDial(), null);
        getSelectDial().pack();
//        showHtmlDialog(getFacade(),task_, getFacade().getLanguage());
    }

    @Override
    public void validateChoice() {
        okChoice();
        if (!storage) {
            getFacade().clearFoundResultsStoragePokemon();
        }
        closeWindow();
        if (consulting) {
            ConsultPokemonEvent.consult(lineBack,getFacade());
            return;
        }
        getMainWindow().getScenePanel().afterSelectPk();
    }

    @Override
    public void closeWindow() {
        getMainWindow().getModal().set(false);
        getFacade().clearFiltersFirstBox();
        getSelectDial().setVisible(false);
        if (!isOk()) {
            getFacade().setLinePokemonFirstBox(lineBack);
        }
    }

    private boolean isOk() {
        return isSelected();
    }

//    public static boolean isStaticOk(SelectPokemon _dialog) {
//        return _dialog.isOk();
//    }

//    private void showHtmlDialog(FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg) {
////        DialogHtmlData.setDialogHtmlData(DIALOG, DIALOG.messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
//        DialogHtmlData.setDialogHtmlData(window, getSelectDial(), messages.getVal(TITLE_DETAIL), _dataBase,_pre,_lg);
//    }

    public static void setVisible(SelectPokemon _dialog) {
        _dialog.getSelectDial().setVisible(true);
    }

    public PkDetailContent getPkDetailContent() {
        return pkDetailContent;
    }

    public PaginatorPokemon getPaginatorPokemon() {
        return paginatorPokemon;
    }
}
