package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import aiki.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.PaginatorPokemon;
import aiki.gui.dialogs.events.SeePkDetailEvent;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import aiki.map.pokemon.UsablePokemon;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.document.RenderedPage;
import code.gui.events.ClosingDialogEvent;
import code.util.StringMap;

public final class SelectPokemon extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.SelectPokemon";

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

    private SelectPokemon() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setSelectPokemon(MainWindow _parent, FacadeGame _facade, boolean _storage) {
        DIALOG.init(_parent, _facade, _storage);
    }

    private void init(MainWindow _parent, FacadeGame _facade, boolean _storage) {
        //super(_parent, true);
        setDialogIcon(_parent);
        messages = getMessages(Resources.MESSAGES_FOLDER);
//        window = _parent;
        setTitle(messages.getVal(TITLE));
        facade = _facade;
        storage = _storage;
        initOk();
//        ok = false;
        Panel contentPane_ = new Panel();
        contentPane_.setLayout(new BorderLayout());
        contentPane_.add(new ScrollPane(new PaginatorPokemon(this, _facade)), BorderLayout.CENTER);
        Panel buttons_ = new Panel();
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
        UsablePokemon p_ = facade.getSelectedPokemonFirstBox();
        if (p_ == null) {
            return;
        }
        RenderedPage session_;
        session_ = new RenderedPage(new JScrollPane());
        session_.setLanguage(facade.getLanguage());
        session_.setDataBase(facade);
        //session_.setFiles(facade.getData().getWebPk(), new Map<String,String>());
//        session_.setFiles(facade.getData().getWebPk(), Resources.ACCESS_TO_DEFAULT_FILES);
        session_.setFiles(Resources.ACCESS_TO_DEFAULT_FILES);
//        try {
//            session_.setFiles(facade.getData().getWebPk());
//            if (window.isSuccessfulCompile()) {
//                session_.initialize(Resources.CONFIG_PK);
//            } else {
//                session_.initialize(Resources.ACCESS_TO_DEFAULT_PK);
//            }
//        } catch (Exception e_) {
//            e_.printStackTrace();
//        }

//        try {
//            session_.initialize(Resources.CONFIG_PK);
//        } catch (Exception e_) {
//            try {
//                CompilingBeans.loadDefaultClassesAndClear();
//                session_.setRelativeFiles(Resources.ACCESS_TO_DEFAULT_FILES);
//                session_.initialize(Resources.ACCESS_TO_DEFAULT_PK);
//            } catch (Exception e2_) {
//                e_.printStackTrace();
//                return;
//            }
//        }
        showHtmlDialog(session_);
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

    private static void showHtmlDialog(RenderedPage _session) {
//        DialogHtmlData.setDialogHtmlData(DIALOG, DIALOG.messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
        DialogHtmlData.setDialogHtmlData(DIALOG, DIALOG.messages.getVal(TITLE_DETAIL), _session);
    }

    public static void setVisible() {
        DIALOG.setVisible(true);
    }
}
