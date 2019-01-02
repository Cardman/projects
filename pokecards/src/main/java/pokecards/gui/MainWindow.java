package pokecards.gui;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import pokecards.main.LaunchingPokecards;
import aiki.main.LaunchingPokemon;
import cards.main.LaunchingCards;
import code.gui.Clock;
import code.gui.GroupFrame;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.SetStyle;
import code.gui.SoftApplicationCore;
import code.gui.ThreadUtil;
import code.gui.events.QuittingEvent;
import code.util.CustList;
import code.util.StringList;
import code.util.consts.Constants;

public final class MainWindow extends GroupFrame {

    private static final String POKE_CARDS = "Poke Cards";

    private LabelButton buttonPokemon;

    private LabelButton buttonCards;

    private ButtonGroup group = new ButtonGroup();

    private CustList<JRadioButton> radios = new CustList<JRadioButton>();

    public MainWindow(String _lg) {
        super(_lg);
        setFocusableWindowState(true);
        setTitle(POKE_CARDS);
        Panel panel_ = new Panel();
        panel_.setLayout(new BoxLayout(panel_.getComponent(), BoxLayout.PAGE_AXIS));
        Panel linePokemon_ = new Panel();
        buttonPokemon = new LabelButton(new ImageIcon(LaunchingPokemon.getIcon()));
        buttonPokemon.addMouseListener(new PokemonEvent(this));
        linePokemon_.add(buttonPokemon);
        panel_.add(linePokemon_);
        Panel lineCards_ = new Panel();
        buttonCards = new LabelButton(new ImageIcon(LaunchingCards.getIcon()));
        buttonCards.addMouseListener(new CardsEvent(this));
        lineCards_.add(buttonCards);
        panel_.add(lineCards_);
        panel_.add(new Clock());
        for (String l: Constants.getAvailableLanguages()) {
            JRadioButton radio_ = new JRadioButton(Constants.getDisplayLanguage(l));
            radio_.addMouseListener(new SetLanguage(l));
            radio_.setSelected(StringList.quickEq(l,_lg));
            group.add(radio_);
            panel_.add(radio_);
            radios.add(radio_);
        }
        panel_.setPreferredSize(new Dimension(256, 192));
        setContentPane(panel_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
        SetStyle.setupStyle(getFrame());
        setVisible(true);
        pack();
    }
    @Override
    public void quit() {
        int nb_ = CustList.SIZE_EMPTY;
        if (LaunchingPokemon.alreadyLaunched()) {
            nb_ ++;
        }
        if (LaunchingCards.alreadyLaunched()) {
            nb_ ++;
        }
        if (nb_ > CustList.SIZE_EMPTY) {
            return;
        }
        writeCoords();
        ThreadUtil.exit();
    }
    @Override
    public boolean canChangeLanguage() {
        return true;
    }
    @Override
    public void changeLanguage(String _language) {
        if (GroupFrame.canChangeLanguageAll()) {
            setLanguageKey(_language);
            SoftApplicationCore.saveLanguage(LaunchingPokecards.getTempFolder(), _language);
            int i_ = CustList.SECOND_INDEX;
            try {
                while (true) {
                    GroupFrame.getFrame(i_).changeLanguage(_language);
                    i_ ++;
                }
            } catch (RuntimeException _0) {
            }
            selectLangagueButton(_language);
        } else {
            selectLangagueButton(getLanguageKey());
            GroupFrame.showDialogError(this);
        }
    }

    private void selectLangagueButton(String _language) {
        for (JRadioButton r: radios) {
            r.setSelected(false);
        }
        for (JRadioButton r: radios) {
            if (StringList.quickEq(r.getText(),Constants.getDisplayLanguage(_language))) {
                r.setSelected(true);
            } else {
                r.setSelected(false);
            }
        }
        pack();
    }

    private void writeCoords() {
        Point point_=getFrame().getLocation();
        int x_ = point_.x;
        int y_ = point_.y;
        SoftApplicationCore.saveCoords(LaunchingPokecards.getTempFolder(), LaunchingPokecards.COORDS, x_, y_);
    }

    @Override
    public String getApplicationName() {
        return "pokecards";
    }
}
