package aiki.gui.dialogs;
import java.awt.BorderLayout;

import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.PaginatorPokemon;
import aiki.gui.dialogs.events.SeePkDetailEvent;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import aiki.map.pokemon.UsablePokemon;
import code.formathtml.Navigation;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.document.RenderedPage;
import code.gui.events.ClosingDialogEvent;
import code.util.StringMap;

public final class SelectPokemon extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectpokemon";

    private static final SelectPokemon DIALOG = new SelectPokemon();

    private static final String TITLE = "title";

    private static final String TITLE_DETAIL = "titleDetail";

    private static final String CANCEL = "cancel";

    private static final String DETAIL = "detail";

    private FacadeGame facade;

//    private MainWindow window;

    private boolean storage;

//    private boolean ok;

    private StringMap<String> messages;
    private MainWindow window;

    private SelectPokemon() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setSelectPokemon(MainWindow _parent, FacadeGame _facade, boolean _storage) {
        DIALOG.init(_parent, _facade, _storage);
    }

    private void init(MainWindow _parent, FacadeGame _facade, boolean _storage) {
        //super(_parent, true);
        setDialogIcon(_parent);
        window = _parent;
        messages = getMessages(_parent,Resources.MESSAGES_FOLDER);
//        window = _parent;
        setTitle(messages.getVal(TITLE));
        facade = _facade;
        storage = _storage;
        initOk();
//        ok = false;
        Panel contentPane_ = Panel.newBorder();
        Panel pag_ = Panel.newPageBox();
        contentPane_.add(new ScrollPane(new PaginatorPokemon(_parent,pag_, this, _facade).getContainer()), BorderLayout.CENTER);
        Panel buttons_ = Panel.newLineBox();
        LabelButton detail_ = new LabelButton(messages.getVal(DETAIL));
        detail_.addMouseListener(new SeePkDetailEvent(this));
        buttons_.add(detail_);
        LabelButton ok_ = new LabelButton(MainWindow.OK);
        ok_.addMouseListener(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        LabelButton cancel_ = new LabelButton(messages.getVal(CANCEL));
        cancel_.addMouseListener(new ClosingDialogEvent(this));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, BorderLayout.SOUTH);
        setContentPane(contentPane_);
        //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
    }

    public void seePkDetail() {
        Thread thread_ = window.getPreparedPkThread();
        PreparedRenderedPages task_ = window.getPreparedPkTask();
        if (thread_ == null || thread_.isAlive() || task_ == null) {
            return;
        }
        UsablePokemon p_ = facade.getSelectedPokemonFirstBox();
        if (p_ == null) {
            return;
        }
        RenderedPage session_;
        session_ = new RenderedPage(new ScrollPane());
        showHtmlDialog(session_,facade,task_.getNavigation(),facade.getLanguage());
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
        super.closeWindow();
    }

    public static boolean isSelectedIndex() {
        DIALOG.setVisible(true);
        return DIALOG.facade.getSelectedPokemonFirstBox() != null;
    }

    private boolean isOk() {
        return isSelected();
    }

    public static boolean isStaticOk() {
        return DIALOG.isOk();
    }

    private void showHtmlDialog(RenderedPage _session, Object _dataBase, Navigation _navigation, String _lg) {
//        DialogHtmlData.setDialogHtmlData(DIALOG, DIALOG.messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
        DialogHtmlData.setDialogHtmlData(window, DIALOG, DIALOG.messages.getVal(TITLE_DETAIL), _session,_dataBase,_navigation,_lg);
    }

    public static void setVisible() {
        DIALOG.setVisible(true);
    }
}
