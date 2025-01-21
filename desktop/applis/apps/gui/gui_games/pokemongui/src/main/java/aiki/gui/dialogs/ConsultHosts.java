package aiki.gui.dialogs;


import aiki.facade.FacadeGame;
import aiki.game.HostPokemonDuo;
import aiki.gui.WindowAiki;
import aiki.gui.components.PkDetailContent;
import aiki.gui.listeners.SelectHostedPokemon;
import aiki.main.PkNonModalEvent;
import aiki.map.Condition;
import aiki.map.places.Place;
import aiki.map.pokemon.PokemonPlayer;
import aiki.sml.MessagesPkGame;
import aiki.sml.MessagesRenderConsultHost;
import aiki.util.Coords;
import code.gui.*;
import code.gui.files.MessagesGuiFct;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.EntryCust;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ConsultHosts {
//    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.consulthosts";

//    private static final String TITLE = "title";

//    private static final String TITLE_DETAIL = "titleDetail";

//    private static final String STEPS = "steps";

//    private static final String FREE = "free";

    private static final String SPACE = " ";
    private final AbsCommonFrame absDialog;

    private FacadeGame facade;

//    private MainWindow window;
    private WindowAiki window;
    private final AbsPanel mainComponent;
    private final PkDetailContent pkDetailContent;

    public ConsultHosts(AbstractProgramInfos _frameFactory) {
        pkDetailContent = new PkDetailContent(_frameFactory);
        absDialog = _frameFactory.getFrameFactory().newCommonFrame();
//        absDialog.setAccessFile(DIALOG_ACCESS);
        mainComponent = _frameFactory.getCompoFactory().newBorder();
    }

    public static void setConsultHosts(WindowAiki _frame, FacadeGame _facade) {
        _frame.getConsultHosts().init(_frame, _facade);
    }

    private void init(WindowAiki _frame, FacadeGame _facade) {
        mainComponent.removeAll();
        absDialog.setIconImage(_frame.getCommonFrame().getImageIconFrame());
        window = _frame;
        StringMap<String> messages_ = file(_frame.getFrames().currentLg());
//        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _frame.getLanguageKey(), absDialog.getAccessFile());
        //super(_frame, true);
//        window = _frame;
        absDialog.setTitle(messages_.getVal(MessagesRenderConsultHost.TITLE));
        facade = _facade;
        AbsPanel contentPane_ = window.getCompoFactory().newGrid(0,1);
        IntTreeMap<Condition> hostsByPlace_ = facade.groupedHost();
        for (EntryCust<Integer, Condition> p: hostsByPlace_.entryList()) {
            Place pl_ = facade.getMap().getPlace(p.getKey());
            AbsPanel hosting_ = window.getCompoFactory().newGrid(0,1);
            AbsPlainLabel place_ = window.getCompoFactory().newPlainLabel(pl_.getName());
            hosting_.add(place_);
            for (Coords c: p.getValue()) {
                AbsPanel hostingLoc_ = window.getCompoFactory().newGrid(0,1);
                HostPokemonDuo host_ = facade.getGame().getHostedPk().getVal(c);
                String rem_ = messages_.getVal(MessagesRenderConsultHost.STEPS);
                if (host_.isFree()) {
                    hostingLoc_.add(window.getCompoFactory().newPlainLabel(messages_.getVal(MessagesRenderConsultHost.FREE)));
                    hostingLoc_.setBackground(GuiConstants.WHITE);
                    hosting_.add(hostingLoc_);
                    continue;
                }
                hostingLoc_.setBackground(GuiConstants.YELLOW);
                AbsPlainLabel steps_ = window.getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(rem_, NumberUtil.max(facade.getRemaingingSteps(c), 0)));
                hostingLoc_.add(steps_);
                PokemonPlayer pk_;
                String gender_;
                pk_ = host_.getFirstPokemon();
                gender_ = facade.translateGenders(pk_.getGender());
                AbsButton first_ = _frame.getCompoFactory().newPlainButton(StringUtil.concat(facade.translatePokemon(pk_.getName()),SPACE,gender_));
                first_.addActionListener(new PkNonModalEvent(_frame.getModal()),new SelectHostedPokemon(this, true, c));
                hostingLoc_.add(first_);
                pk_ = host_.getSecondPokemon();
                gender_ = facade.translateGenders(pk_.getGender());
                AbsButton second_ = _frame.getCompoFactory().newPlainButton(StringUtil.concat(facade.translatePokemon(pk_.getName()),SPACE,gender_));
                second_.addActionListener(new PkNonModalEvent(_frame.getModal()),new SelectHostedPokemon(this, false, c));
                hostingLoc_.add(second_);
                hosting_.add(hostingLoc_);
            }
            contentPane_.add(hosting_);
        }
        mainComponent.add(contentPane_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
        mainComponent.add(pkDetailContent.getContent(), MessagesGuiFct.BORDER_LAYOUT_EAST);
        absDialog.setContentPane(mainComponent);
        //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    public static StringMap<String> file(TranslationsLg _lg) {
        return MessagesPkGame.getConsultHostContentTr(MessagesPkGame.getAppliTr(_lg)).getMapping();
    }
    public AbsCommonFrame getAbsDialog() {
        return absDialog;
    }

    public PkDetailContent getPkDetailContent() {
        return pkDetailContent;
    }

    public void seeHostedPokemon(boolean _first, Coords _coords) {
//        AbstractThread thread_ = window.getPreparedPkThread();
//        AikiNatLgNamesNavigation task_ = window.getPreparedPkTask();
//        if (thread_ == null || thread_.isAlive() || task_ == null) {
//            return;
//        }
        facade.setHostedPokemon(_first, _coords);
        pkDetailContent.group(window,facade, absDialog, null);
        absDialog.pack();
//        showHtmlDialog(facade,task_,facade.getLanguage());
    }

//    private void showHtmlDialog(FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg) {
//        DialogHtmlData.setDialogHtmlData(this, messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
//        DialogHtmlData.setDialogHtmlData(window, absDialog, messages.getVal(TITLE_DETAIL), _dataBase,_pre,_lg);
//    }
}
