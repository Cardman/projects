package aiki.gui.dialogs;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorPokemon;
import aiki.gui.components.PkDetailContent;
import aiki.gui.dialogs.events.ClosingSelectPokemon;
import aiki.gui.dialogs.events.SeePkDetailEvent;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import aiki.map.pokemon.UsablePokemon;
import aiki.sml.Resources;
import code.gui.AbsButton;
import code.gui.AbsCloseableDialog;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class SelectPokemon extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectpokemon";

    private static final String TITLE = "title";

//    private static final String TITLE_DETAIL = "titleDetail";

    private static final String CANCEL = "cancel";

    private static final String DETAIL = "detail";
    private final AbsPanel mainComponent;

//    private MainWindow window;

    private boolean storage;

//    private boolean ok;

    private WindowAiki window;
    private final AbsCompoFactory compo;
    private final PkDetailContent pkDetailContent;
    public SelectPokemon(AbstractProgramInfos _infos) {
        super(_infos.getFrameFactory());
        pkDetailContent = new PkDetailContent(_infos);
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _infos.getCompoFactory();
        mainComponent = compo.newBorder();
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
        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
//        window = _parent;
        getSelectDial().setTitle(messages_.getVal(TITLE));
        setFacade(_facade);
        storage = _storage;
        initOk();
//        ok = false;
        mainComponent.removeAll();
        AbsPanel pag_ = compo.newPageBox();
        mainComponent.add(compo.newAbsScrollPane(new PaginatorPokemon(_parent,pag_, getSelectDial(), _facade).getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        AbsButton detail_ = _parent.getCompoFactory().newPlainButton(messages_.getVal(DETAIL));
        detail_.addActionListener(new SeePkDetailEvent(this));
        buttons_.add(detail_);
        AbsButton ok_ = _parent.getCompoFactory().newPlainButton(WindowAiki.OK);
        ok_.addActionListener(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        AbsButton cancel_ = _parent.getCompoFactory().newPlainButton(messages_.getVal(CANCEL));
        cancel_.addActionListener(new ClosingDialogEvent(getSelectDial(),getBuilt()));
        buttons_.add(cancel_);
        mainComponent.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        mainComponent.add(pkDetailContent.getContent(), GuiConstants.BORDER_LAYOUT_EAST);
        getSelectDial().setContentPane(mainComponent);
        //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getSelectDial().pack();
    }

    public void seePkDetail() {
//        AbstractThread thread_ = window.getPreparedPkThread();
//        AikiNatLgNamesNavigation task_ = window.getPreparedPkTask();
//        if (thread_ == null || thread_.isAlive() || task_ == null) {
//            return;
//        }
        UsablePokemon p_ = getFacade().getSelectedPokemonFirstBox();
        if (p_ == null) {
            return;
        }

        pkDetailContent.group(window, getFacade(),window.getPreparedPkTask(), getFacade().getLanguage(), getSelectDial());
        getSelectDial().pack();

//        showHtmlDialog(getFacade(),task_, getFacade().getLanguage());
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

//    private void showHtmlDialog(FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg) {
////        DialogHtmlData.setDialogHtmlData(DIALOG, DIALOG.messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
//        DialogHtmlData.setDialogHtmlData(window, getSelectDial(), messages.getVal(TITLE_DETAIL), _dataBase,_pre,_lg);
//    }

    public static void setVisible(SelectPokemon _dialog) {
        _dialog.getSelectDial().setVisible(true);
    }

}
