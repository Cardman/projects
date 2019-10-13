package applications.gui;

import aiki.main.LaunchingPokemon;
import applications.main.LaunchingApplications;
import cards.main.LaunchingCards;
import code.converterimages.main.LaunchingConverter;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.*;
import code.gui.Panel;
import code.gui.events.QuittingEvent;
import code.minirts.LaunchingDemo;
import code.player.main.LaunchingPlayer;
import code.renders.LaunchingRenders;
import code.util.CustList;
import code.util.StringList;
import code.util.consts.Constants;

import javax.swing.*;
import java.awt.*;

public final class MainWindow extends GroupFrame {

    private static final String APPLICATIONS = "Applications";

    private LabelButton buttonPokemon;

    private LabelButton buttonCards;
    private LabelButton buttonApps;
    private LabelButton buttonTests;
    private LabelButton buttonRenders;
    private LabelButton buttonDemo;
    private LabelButton buttonPlayer;
    private LabelButton buttonConverter;

    private CustButtonGroup group = new CustButtonGroup();

    private CustList<RadioButton> radios = new CustList<RadioButton>();

    public MainWindow(String _lg) {
        super(_lg);
        setFocusableWindowState(true);
        setTitle(APPLICATIONS);
        Panel panel_ = Panel.newPageBox();
        Panel linePokemon_ = Panel.newLineBox();
        buttonPokemon = new LabelButton(LaunchingPokemon.getIcon());
        buttonPokemon.addMouseListener(new PokemonEvent(this));
        linePokemon_.add(buttonPokemon);
        panel_.add(linePokemon_);
        Panel lineCards_ = Panel.newLineBox();
        buttonCards = new LabelButton(LaunchingCards.getIcon());
        buttonCards.addMouseListener(new CardsEvent(this));
        lineCards_.add(buttonCards);
        panel_.add(lineCards_);
        Panel lineTests_ = Panel.newLineBox();
        buttonTests = new LabelButton("3");
        buttonTests.addMouseListener(new AppUnitEvent(this));
        lineTests_.add(buttonTests);
        panel_.add(lineTests_);
        Panel lineRenders_ = Panel.newLineBox();
        buttonRenders = new LabelButton("4");
        buttonRenders.addMouseListener(new RenderEvent(this));
        lineRenders_.add(buttonRenders);
        panel_.add(lineRenders_);
        Panel lineDemo_ = Panel.newLineBox();
        buttonDemo = new LabelButton("5");
        buttonDemo.addMouseListener(new DemoEvent(this));
        lineDemo_.add(buttonDemo);
        panel_.add(lineDemo_);
        Panel linePlayer_ = Panel.newLineBox();
        buttonPlayer = new LabelButton(LaunchingPlayer.getIcon());
        buttonPlayer.addMouseListener(new PlayerEvent(this));
        linePlayer_.add(buttonPlayer);
        panel_.add(linePlayer_);
        Panel lineConverter_ = Panel.newLineBox();
        buttonConverter = new LabelButton("7");
        buttonConverter.addMouseListener(new ConverterEvent(this));
        lineConverter_.add(buttonConverter);
        panel_.add(lineConverter_);
        Panel lineApp_ = Panel.newLineBox();
        buttonApps = new LabelButton("8");
        buttonApps.addMouseListener(new AppsEvent(this));
        lineApp_.add(buttonApps);
        panel_.add(lineApp_);
        panel_.add(new Clock());
        for (String l: Constants.getAvailableLanguages()) {
            RadioButton radio_ = new RadioButton(Constants.getDisplayLanguage(l));
            radio_.addActionListener(new SetLanguage(l));
            radio_.setSelected(StringList.quickEq(l,_lg));
            group.add(radio_);
            panel_.add(radio_);
            radios.add(radio_);
        }
        panel_.setPreferredSize(new Dimension(256, 192));
        setContentPane(panel_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
        setVisible(true);
        pack();
    }
    @Override
    public void quit() {
        int nb_ = CustList.SIZE_EMPTY;
        if (LaunchingPokemon.alreadyLaunched()) {
            nb_++;
        }
        if (LaunchingCards.alreadyLaunched()) {
            nb_++;
        }
        if (LaunchingAppUnitTests.alreadyLaunched()) {
            nb_++;
        }
        if (LaunchingRenders.alreadyLaunched()) {
            nb_++;
        }
        if (LaunchingDemo.alreadyLaunched()) {
            nb_++;
        }
        if (LaunchingPlayer.alreadyLaunched()) {
            nb_++;
        }
        if (LaunchingConverter.alreadyLaunched()) {
            nb_++;
        }
        if (LaunchingFull.alreadyLaunched()) {
            nb_++;
        }
        if (nb_ > CustList.SIZE_EMPTY) {
            return;
        }
        writeCoords();
        ProcessUtil.exit();
    }
    @Override
    public boolean canChangeLanguage() {
        return true;
    }
    @Override
    public void changeLanguage(String _language) {
        if (GroupFrame.canChangeLanguageAll()) {
            setLanguageKey(_language);
            SoftApplicationCore.saveLanguage(LaunchingApplications.getTempFolder(), _language);
            int i_ = CustList.SECOND_INDEX;
            while (i_ < GroupFrame.getFrameCount()) {
                GroupFrame.getFrame(i_).changeLanguage(_language);
                i_++;
            }
            selectLangagueButton(_language);
        } else {
            selectLangagueButton(getLanguageKey());
            GroupFrame.showDialogError(this);
        }
    }

    private void selectLangagueButton(String _language) {
        for (RadioButton r: radios) {
            r.setSelected(false);
        }
        for (RadioButton r: radios) {
            if (StringList.quickEq(r.getText(),Constants.getDisplayLanguage(_language))) {
                r.setSelected(true);
            } else {
                r.setSelected(false);
            }
        }
        pack();
    }

    private void writeCoords() {
        Point point_=getLocation();
        int x_ = point_.x;
        int y_ = point_.y;
        SoftApplicationCore.saveCoords(LaunchingApplications.getTempFolder(), LaunchingApplications.COORDS, x_, y_);
    }

    @Override
    public String getApplicationName() {
        return "applications";
    }
}
