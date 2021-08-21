package aiki.gui.dialogs;
import java.awt.Color;

import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.game.HostPokemonDuo;
import aiki.gui.WindowAiki;
import aiki.gui.listeners.SelectHostedPokemon;
import aiki.map.places.Place;
import aiki.map.pokemon.PokemonPlayer;
import aiki.util.Coords;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.initialize.AbsFrameFactory;
import code.threads.AbstractThread;
import code.util.EqList;
import code.util.*;
import code.util.StringMap;
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

    public ConsultHosts(AbsFrameFactory _frameFactory) {
        absDialog = _frameFactory.newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setConsultHosts(WindowAiki _frame, FacadeGame _facade) {
        _frame.getConsultHosts().init(_frame, _facade);
    }

    private void init(WindowAiki _frame, FacadeGame _facade) {
        absDialog.setDialogIcon(_frame.getImageFactory(),_frame);
        window = _frame;
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _frame.getLanguageKey(), absDialog.getAccessFile());
        //super(_frame, true);
//        window = _frame;
        absDialog.setTitle(messages.getVal(TITLE));
        facade = _facade;
        Panel contentPane_ = Panel.newGrid(0,1);
        ShortTreeMap<EqList<Coords>> hostsByPlace_;
        hostsByPlace_ = new ShortTreeMap<EqList<Coords>>();
        for (Coords c: facade.getMap().getHostPokemons()) {
            if (hostsByPlace_.contains(c.getNumberPlace())) {
                hostsByPlace_.getVal(c.getNumberPlace()).add(c);
            } else {
                hostsByPlace_.put(c.getNumberPlace(), new EqList<Coords>(c));
            }
        }
        for (short p: hostsByPlace_.getKeys()) {
            Place pl_ = facade.getMap().getPlace(p);
            Panel hosting_ = Panel.newGrid(0,1);
            TextLabel place_ = new TextLabel(pl_.getName());
            hosting_.add(place_);
            for (Coords c: hostsByPlace_.getVal(p)) {
                Panel hostingLoc_ = Panel.newGrid(0,1);
                HostPokemonDuo host_ = facade.getGame().getHostedPk().getVal(c);
                String rem_ = messages.getVal(STEPS);
                if (host_.isFree()) {
                    hostingLoc_.add(new TextLabel(messages.getVal(FREE)));
                    hostingLoc_.setBackground(Color.WHITE);
                    hosting_.add(hostingLoc_);
                    continue;
                }
                hostingLoc_.setBackground(Color.YELLOW);
                TextLabel steps_ = new TextLabel(StringUtil.simpleNumberFormat(rem_, Math.max(facade.getRemaingingSteps(c), 0)));
                hostingLoc_.add(steps_);
                PokemonPlayer pk_;
                String gender_;
                pk_ = host_.getFirstPokemon();
                gender_ = facade.translateGenders(pk_.getGender());
                LabelButton first_ = new LabelButton(StringUtil.concat(facade.translatePokemon(pk_.getName()),SPACE,gender_));
                first_.addMouseList(new SelectHostedPokemon(this, true, c));
                hostingLoc_.add(first_);
                pk_ = host_.getSecondPokemon();
                gender_ = facade.translateGenders(pk_.getGender());
                LabelButton second_ = new LabelButton(StringUtil.concat(facade.translatePokemon(pk_.getName()),SPACE,gender_));
                second_.addMouseList(new SelectHostedPokemon(this, false, c));
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
        AbstractThread thread_ = window.getPreparedPkThread();
        PreparedRenderedPages task_ = window.getPreparedPkTask();
        if (thread_ == null || thread_.isAlive() || task_ == null) {
            return;
        }
        facade.setHostedPokemon(_first, _coords);
        RenderedPage session_;
        session_ = new RenderedPage(new ScrollPane(), window.getFrames());
        showHtmlDialog(session_,facade,task_,facade.getLanguage());
    }

    private void showHtmlDialog(RenderedPage _session, FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
//        DialogHtmlData.setDialogHtmlData(this, messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
        DialogHtmlData.setDialogHtmlData(window, absDialog, messages.getVal(TITLE_DETAIL), _session,_dataBase,_pre,_lg);
    }
}
