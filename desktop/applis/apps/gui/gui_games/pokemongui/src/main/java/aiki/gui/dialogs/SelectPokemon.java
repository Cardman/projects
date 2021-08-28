package aiki.gui.dialogs;
import java.awt.BorderLayout;

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
import code.gui.initialize.AbsFrameFactory;
import code.threads.AbstractThread;
import code.util.StringMap;

public final class SelectPokemon extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectpokemon";

    private static final String TITLE = "title";

    private static final String TITLE_DETAIL = "titleDetail";

    private static final String CANCEL = "cancel";

    private static final String DETAIL = "detail";

    private FacadeGame facade;

//    private MainWindow window;

    private boolean storage;

//    private boolean ok;

    private StringMap<String> messages;
    private WindowAiki window;
    private final AbsCompoFactory compo;

    public SelectPokemon(AbsFrameFactory _frameFactory, AbsCompoFactory _compoFactory) {
        super(_frameFactory);
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _compoFactory;
    }

    public static void setSelectPokemon(WindowAiki _parent, FacadeGame _facade, boolean _storage, SelectPokemon _dialog) {
        _dialog.init(_parent, _facade, _storage);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, boolean _storage) {
        //super(_parent, true);
        getSelectDial().setDialogIcon(_parent.getImageFactory(),_parent);
        window = _parent;
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
//        window = _parent;
        getSelectDial().setTitle(messages.getVal(TITLE));
        facade = _facade;
        storage = _storage;
        initOk();
//        ok = false;
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        contentPane_.add(new ScrollPane(new PaginatorPokemon(_parent,pag_, getSelectDial(), _facade).getContainer()), BorderLayout.CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        LabelButton detail_ = new LabelButton(messages.getVal(DETAIL));
        detail_.addMouseList(new SeePkDetailEvent(this));
        buttons_.add(detail_);
        LabelButton ok_ = new LabelButton(WindowAiki.OK);
        ok_.addMouseList(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        LabelButton cancel_ = new LabelButton(messages.getVal(CANCEL));
        cancel_.addMouseList(new ClosingDialogEvent(this));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, BorderLayout.SOUTH);
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
        UsablePokemon p_ = facade.getSelectedPokemonFirstBox();
        if (p_ == null) {
            return;
        }
        RenderedPage session_;
        session_ = new RenderedPage(new ScrollPane(), window.getFrames());
        showHtmlDialog(session_,facade,task_,facade.getLanguage());
    }

    @Override
    public void validateChoice() {
        if (!storage) {
            facade.clearFoundResultsStoragePokemon();
        }
        super.validateChoice();
    }

    @Override
    public void closeWindow() {
        facade.clearFiltersFirstBox();
        getSelectDial().closeWindow();
    }

    public static boolean isSelectedIndex(SelectPokemon _dialog) {
        setVisible(_dialog);
        return _dialog.facade.getSelectedPokemonFirstBox() != null;
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
