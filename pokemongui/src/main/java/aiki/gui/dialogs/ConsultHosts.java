package aiki.gui.dialogs;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import aiki.Resources;
import aiki.facade.FacadeGame;
import aiki.game.HostPokemonDuo;
import aiki.gui.MainWindow;
import aiki.gui.listeners.SelectHostedPokemon;
import aiki.map.places.Place;
import aiki.map.pokemon.PokemonPlayer;
import aiki.util.Coords;
import code.gui.Dialog;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.document.RenderedPage;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class ConsultHosts extends Dialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.ConsultHosts";

    private static final ConsultHosts DIALOG = new ConsultHosts();

    private static final String TITLE = "title";

    private static final String TITLE_DETAIL = "titleDetail";

    private static final String STEPS = "steps";

    private static final String FREE = "free";

    private static final String SPACE = " ";

    private FacadeGame facade;

    private StringMap<String> messages;

//    private MainWindow window;
    private MainWindow window;
    private ConsultHosts() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setConsultHosts(MainWindow _frame, FacadeGame _facade) {
        DIALOG.init(_frame, _facade);
    }

    private void init(MainWindow _frame, FacadeGame _facade) {
        setDialogIcon(_frame);
        window = _frame;
        messages = getMessages(_frame,Resources.MESSAGES_FOLDER);
        //super(_frame, true);
//        window = _frame;
        setTitle(messages.getVal(TITLE));
        facade = _facade;
        Panel contentPane_ = new Panel();
        contentPane_.setLayout(new GridLayout(0,1));
        NatTreeMap<Short,EqList<Coords>> hostsByPlace_;
        hostsByPlace_ = new NatTreeMap<Short,EqList<Coords>>();
        for (Coords c: facade.getMap().getHostPokemons()) {
            if (hostsByPlace_.contains(c.getNumberPlace())) {
                hostsByPlace_.getVal(c.getNumberPlace()).add(c);
            } else {
                hostsByPlace_.put(c.getNumberPlace(), new EqList<Coords>(c));
            }
        }
        for (short p: hostsByPlace_.getKeys()) {
            Place pl_ = facade.getMap().getPlaces().getVal(p);
            Panel hosting_ = new Panel();
            hosting_.setLayout(new GridLayout(0,1));
            JLabel place_ = new JLabel(pl_.getName());
            hosting_.add(place_);
            for (Coords c: hostsByPlace_.getVal(p)) {
                Panel hostingLoc_ = new Panel();
                hostingLoc_.setLayout(new GridLayout(0,1));
                HostPokemonDuo host_ = facade.getGame().getHostedPk().getVal(c);
                String rem_ = messages.getVal(STEPS);
                if (host_.isFree()) {
                    hostingLoc_.add(new JLabel(messages.getVal(FREE)));
                    hostingLoc_.setBackground(Color.WHITE);
                    hosting_.add(hostingLoc_);
                    continue;
                }
                hostingLoc_.setBackground(Color.YELLOW);
                JLabel steps_ = new JLabel(StringList.simpleNumberFormat(rem_, Math.max(facade.getRemaingingSteps(c), 0)));
                hostingLoc_.add(steps_);
                PokemonPlayer pk_;
                String gender_;
                pk_ = host_.getFirstPokemon();
                gender_ = facade.translateGenders(pk_.getGender());
                LabelButton first_ = new LabelButton(StringList.concat(facade.translatePokemon(pk_.getName()),SPACE,gender_));
                first_.addMouseListener(new SelectHostedPokemon(this, true, c));
                hostingLoc_.add(first_);
                pk_ = host_.getSecondPokemon();
                gender_ = facade.translateGenders(pk_.getGender());
                LabelButton second_ = new LabelButton(StringList.concat(facade.translatePokemon(pk_.getName()),SPACE,gender_));
                second_.addMouseListener(new SelectHostedPokemon(this, false, c));
                hostingLoc_.add(second_);
                hosting_.add(hostingLoc_);
            }
            contentPane_.add(hosting_);
        }
        setContentPane(contentPane_);
        //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void seeHostedPokemon(boolean _first, Coords _coords) {
        facade.setHostedPokemon(_first, _coords);
        RenderedPage session_;
        session_ = new RenderedPage(new JScrollPane());
        session_.setLanguage(facade.getLanguage());
        session_.setDataBase(facade);
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

//        session_.setFiles(facade.getData().getWebPk(), new Map<String,String>());
//        try {
//            session_.initialize(Resources.CONFIG_PK);
//        } catch (Exception e_) {
//            try {
//                CompilingBeans.loadDefaultClassesAndClear();
//                session_.setRelativeFiles(Resources.ACCESS_TO_DEFAULT_FILES);
//                session_.initialize(Resources.ACCESS_TO_DEFAULT_PK);
//            } catch (Exception e2_) {
//                e2_.printStackTrace();
//                return;
//            }
//        }
        showHtmlDialog(session_);
    }

    private void showHtmlDialog(RenderedPage _session) {
//        DialogHtmlData.setDialogHtmlData(this, messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
        DialogHtmlData.setDialogHtmlData(window, this, messages.getVal(TITLE_DETAIL), _session);
    }
}
