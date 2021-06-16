package applications.gui;

import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import applications.main.LaunchingApplications;
import cards.main.CardFactories;
import cards.main.LaunchingCards;
import code.converterimages.main.LaunchingConverter;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.GuiFactroy;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.*;
import code.gui.Panel;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.minirts.LaunchingDemo;
import code.player.main.LaunchingPlayer;
import code.renders.LaunchingRenders;
import code.threads.AbstractAtomicInteger;
import code.util.CustList;
import code.util.consts.Constants;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class MainWindow extends GroupFrame {

    private static final String APPLICATIONS = "Applications";

    private final LabelButton buttonPokemon;

    private final LabelButton buttonCards;
    private final LabelButton buttonApps;
    private final LabelButton buttonTests;
    private final LabelButton buttonRenders;
    private final LabelButton buttonDemo;
    private final LabelButton buttonPlayer;
    private final LabelButton buttonConverter;

    private final CustButtonGroup group = new CustButtonGroup();

    private final CustList<RadioButton> radios = new CustList<RadioButton>();

    public MainWindow(String _lg, AbstractProgramInfos _list, CardFactories _cardFactories, AikiFactory _aikiFactory, GuiFactroy _guiFact) {
        super(_lg, _list);
        setFocusableWindowState(true);
        setTitle(APPLICATIONS);
        Panel panel_ = Panel.newPageBox();
        Panel linePokemon_ = Panel.newLineBox();
        buttonPokemon = new LabelButton(LaunchingPokemon.getIcon());
        AbstractAtomicInteger at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingPokemon.getMainWindowClass(), at_);
        buttonPokemon.addMouseListener(new PokemonEvent(this,at_, _aikiFactory));
        linePokemon_.add(buttonPokemon);
        panel_.add(linePokemon_);
        Panel lineCards_ = Panel.newLineBox();
        buttonCards = new LabelButton(LaunchingCards.getIcon());
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingCards.getMainWindowClass(),at_);
        buttonCards.addMouseListener(new CardsEvent(this,at_,_cardFactories));
        lineCards_.add(buttonCards);
        panel_.add(lineCards_);
        Panel lineTests_ = Panel.newLineBox();
        buttonTests = new LabelButton("3");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingAppUnitTests.getMainWindowClass(),at_);
        buttonTests.addMouseListener(new AppUnitEvent(this,at_));
        lineTests_.add(buttonTests);
        panel_.add(lineTests_);
        Panel lineRenders_ = Panel.newLineBox();
        buttonRenders = new LabelButton("4");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingRenders.getMainWindowClass(),at_);
        buttonRenders.addMouseListener(new RenderEvent(this,at_));
        lineRenders_.add(buttonRenders);
        panel_.add(lineRenders_);
        Panel lineDemo_ = Panel.newLineBox();
        buttonDemo = new LabelButton("5");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingDemo.getMainWindowClass(),at_);
        buttonDemo.addMouseListener(new DemoEvent(this,at_));
        lineDemo_.add(buttonDemo);
        panel_.add(lineDemo_);
        Panel linePlayer_ = Panel.newLineBox();
        buttonPlayer = new LabelButton(LaunchingPlayer.getIcon());
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingPlayer.getMainWindowClass(),at_);
        buttonPlayer.addMouseListener(new PlayerEvent(this,at_));
        linePlayer_.add(buttonPlayer);
        panel_.add(linePlayer_);
        Panel lineConverter_ = Panel.newLineBox();
        buttonConverter = new LabelButton("7");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingConverter.getMainWindowClass(),at_);
        buttonConverter.addMouseListener(new ConverterEvent(this,at_));
        lineConverter_.add(buttonConverter);
        panel_.add(lineConverter_);
        Panel lineApp_ = Panel.newLineBox();
        buttonApps = new LabelButton("8");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingFull.getMainWindowClass(),at_);
        buttonApps.addMouseListener(new AppsEvent(this,at_, _guiFact));
        lineApp_.add(buttonApps);
        panel_.add(lineApp_);
        panel_.add(new Clock());
        for (String l: Constants.getAvailableLanguages()) {
            RadioButton radio_ = new RadioButton(Constants.getDisplayLanguage(l));
            radio_.addActionListener(new SetLanguage(l, getFrames()));
            radio_.setSelected(StringUtil.quickEq(l,_lg));
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
        int nb_ = IndexConstants.SIZE_EMPTY;
        for (AbstractAtomicInteger a: getFrames().getCounts().values()) {
            if (a.get() > 0) {
                nb_++;
            }
        }
        if (nb_ > IndexConstants.SIZE_EMPTY) {
            return;
        }
        writeCoords();
        nativeExit();
    }
    @Override
    public boolean canChangeLanguage() {
        return true;
    }
    @Override
    public void changeLanguage(String _language) {
        if (canChangeLanguageAll()) {
            setLanguageKey(_language);
            SoftApplicationCore.saveLanguage(LaunchingApplications.getTempFolder(getFrames()), _language,getStreams());
            for (GroupFrame g: getFrames().getFrames()) {
                g.changeLanguage(_language);
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
            r.setSelected(StringUtil.quickEq(r.getText(), Constants.getDisplayLanguage(_language)));
        }
        pack();
    }

    private void writeCoords() {
        Point point_=getLocation();
        int x_ = point_.x;
        int y_ = point_.y;
        SoftApplicationCore.saveCoords(LaunchingApplications.getTempFolder(getFrames()), LaunchingApplications.COORDS, x_, y_,getStreams());
    }

    @Override
    public String getApplicationName() {
        return "applications";
    }
}
