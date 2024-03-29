package aiki.gui.dialogs;


import aiki.facade.FacadeGame;
import aiki.game.HostPokemonDuo;
import aiki.gui.WindowAiki;
import aiki.gui.listeners.SelectHostedPokemon;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.map.Condition;
import aiki.map.places.Place;
import aiki.map.pokemon.PokemonPlayer;
import aiki.sml.Resources;
import aiki.util.Coords;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.ShortTreeMap;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ConsultHosts {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.consulthosts";

    private static final String TITLE = "title";

    private static final String TITLE_DETAIL = "titleDetail";

    private static final String STEPS = "steps";

    private static final String FREE = "free";

    private static final String SPACE = " ";
    private final AbsDialog absDialog;

    private FacadeGame facade;

    private StringMap<String> messages;

//    private MainWindow window;
    private WindowAiki window;

    public ConsultHosts(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setConsultHosts(WindowAiki _frame, FacadeGame _facade) {
        _frame.getConsultHosts().init(_frame, _facade);
    }

    private void init(WindowAiki _frame, FacadeGame _facade) {
        absDialog.setDialogIcon(_frame.getImageFactory(),_frame.getCommonFrame());
        window = _frame;
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _frame.getLanguageKey(), absDialog.getAccessFile());
        //super(_frame, true);
//        window = _frame;
        absDialog.setTitle(messages.getVal(TITLE));
        facade = _facade;
        AbsPanel contentPane_ = window.getCompoFactory().newGrid(0,1);
        ShortTreeMap<Condition> hostsByPlace_;
        hostsByPlace_ = new ShortTreeMap<Condition>();
        for (Coords c: facade.getMap().getHostPokemons()) {
            if (hostsByPlace_.contains(c.getNumberPlace())) {
                hostsByPlace_.getVal(c.getNumberPlace()).add(c);
            } else {
                hostsByPlace_.put(c.getNumberPlace(), Condition.newList(c));
            }
        }
        for (short p: hostsByPlace_.getKeys()) {
            Place pl_ = facade.getMap().getPlace(p);
            AbsPanel hosting_ = window.getCompoFactory().newGrid(0,1);
            AbsPlainLabel place_ = window.getCompoFactory().newPlainLabel(pl_.getName());
            hosting_.add(place_);
            for (Coords c: hostsByPlace_.getVal(p)) {
                AbsPanel hostingLoc_ = window.getCompoFactory().newGrid(0,1);
                HostPokemonDuo host_ = facade.getGame().getHostedPk().getVal(c);
                String rem_ = messages.getVal(STEPS);
                if (host_.isFree()) {
                    hostingLoc_.add(window.getCompoFactory().newPlainLabel(messages.getVal(FREE)));
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
                first_.addActionListener(new SelectHostedPokemon(this, true, c));
                hostingLoc_.add(first_);
                pk_ = host_.getSecondPokemon();
                gender_ = facade.translateGenders(pk_.getGender());
                AbsButton second_ = _frame.getCompoFactory().newPlainButton(StringUtil.concat(facade.translatePokemon(pk_.getName()),SPACE,gender_));
                second_.addActionListener(new SelectHostedPokemon(this, false, c));
                hostingLoc_.add(second_);
                hosting_.add(hostingLoc_);
            }
            contentPane_.add(hosting_);
        }
        absDialog.setContentPane(contentPane_);
        //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    public void seeHostedPokemon(boolean _first, Coords _coords) {
//        AbstractThread thread_ = window.getPreparedPkThread();
        AikiNatLgNamesNavigation task_ = window.getPreparedPkTask();
//        if (thread_ == null || thread_.isAlive() || task_ == null) {
//            return;
//        }
        facade.setHostedPokemon(_first, _coords);
        showHtmlDialog(facade,task_,facade.getLanguage());
    }

    private void showHtmlDialog(FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg) {
//        DialogHtmlData.setDialogHtmlData(this, messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
        DialogHtmlData.setDialogHtmlData(window, absDialog, messages.getVal(TITLE_DETAIL), _dataBase,_pre,_lg);
    }
}
