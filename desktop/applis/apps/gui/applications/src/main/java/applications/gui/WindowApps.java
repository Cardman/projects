package applications.gui;

import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import applications.main.LaunchingApplications;
import cards.main.CardFactories;
import cards.main.LaunchingCards;
import code.converterimages.main.LaunchingConverter;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.*;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaPoint;
import code.gui.initialize.AbstractProgramInfos;
import code.minirts.LaunchingDemo;
import code.network.LaunchingNetwork;
import code.player.main.LaunchingPlayer;
import code.renders.LaunchingRenders;
import code.threads.AbstractAtomicInteger;
import code.util.CustList;
import code.util.consts.Constants;
import code.util.core.StringUtil;






public final class WindowApps extends GroupFrame {

    private static final String APPLICATIONS = "Applications";

    private final AbsImgButton buttonPokemon;

    private final AbsImgButton buttonCards;
    private final AbsPlainButton buttonApps;
    private final AbsPlainButton buttonTests;
    private final AbsPlainButton buttonRenders;
    private final AbsPlainButton buttonDemo;
    private final AbsImgButton buttonPlayer;
    private final AbsPlainButton buttonConverter;
    private final AbsPlainButton buttonNet;

    private final CustButtonGroup group = new CustButtonGroup();

    private final CustList<AbsRadioButton> radios = new CustList<AbsRadioButton>();

    public WindowApps(String _lg, AbstractProgramInfos _list, CardFactories _cardFactories, AikiFactory _aikiFactory, CdmFactory _cdmFactory) {
        super(_lg, _list);
        setFocusableWindowState(true);
        setTitle(APPLICATIONS);
        AbsPanel panel_ = getCompoFactory().newPageBox();
        AbsPanel linePokemon_ = getCompoFactory().newLineBox();
        buttonPokemon = _list.getCompoFactory().newImgButton(LaunchingPokemon.getIcon(getImageFactory()));
        AbstractAtomicInteger at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingPokemon.getMainWindowClass(), at_);
        _list.getButtons().addEntry(LaunchingPokemon.getMainWindowClass(), buttonPokemon);
        buttonPokemon.addMouseList(new PokemonEvent(this,at_, _aikiFactory));
        linePokemon_.add(buttonPokemon);
        panel_.add(linePokemon_);
        AbsPanel lineCards_ = getCompoFactory().newLineBox();
        buttonCards = _list.getCompoFactory().newImgButton(LaunchingCards.getIcon(getImageFactory()));
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingCards.getMainWindowClass(),at_);
        _list.getButtons().addEntry(LaunchingCards.getMainWindowClass(), buttonCards);
        buttonCards.addMouseList(new CardsEvent(this,at_,_cardFactories));
        lineCards_.add(buttonCards);
        panel_.add(lineCards_);
        AbsPanel lineTests_ = getCompoFactory().newLineBox();
        buttonTests = getCompoFactory().newPlainButton("3");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingAppUnitTests.getMainWindowClass(),at_);
        _list.getButtons().addEntry(LaunchingAppUnitTests.getMainWindowClass(), buttonTests);
        buttonTests.addActionListener(new AppUnitEvent(this,_cdmFactory,at_));
        lineTests_.add(buttonTests);
        panel_.add(lineTests_);
        AbsPanel lineRenders_ = getCompoFactory().newLineBox();
        buttonRenders = getCompoFactory().newPlainButton("4");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingRenders.getMainWindowClass(),at_);
        _list.getButtons().addEntry(LaunchingRenders.getMainWindowClass(), buttonTests);
        buttonRenders.addActionListener(new RenderEvent(this,at_,_cdmFactory));
        lineRenders_.add(buttonRenders);
        panel_.add(lineRenders_);
        AbsPanel lineDemo_ = getCompoFactory().newLineBox();
        buttonDemo = getCompoFactory().newPlainButton("5");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingDemo.getMainWindowClass(),at_);
        _list.getButtons().addEntry(LaunchingDemo.getMainWindowClass(), buttonDemo);
        buttonDemo.addActionListener(new DemoEvent(this,at_));
        lineDemo_.add(buttonDemo);
        panel_.add(lineDemo_);
        AbsPanel linePlayer_ = getCompoFactory().newLineBox();
        buttonPlayer = _list.getCompoFactory().newImgButton(LaunchingPlayer.getIcon(getImageFactory()));
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingPlayer.getMainWindowClass(),at_);
        _list.getButtons().addEntry(LaunchingPlayer.getMainWindowClass(), buttonPlayer);
        buttonPlayer.addMouseList(new PlayerEvent(this,at_));
        linePlayer_.add(buttonPlayer);
        panel_.add(linePlayer_);
        AbsPanel lineConverter_ = getCompoFactory().newLineBox();
        buttonConverter = getCompoFactory().newPlainButton("7");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingConverter.getMainWindowClass(),at_);
        _list.getButtons().addEntry(LaunchingConverter.getMainWindowClass(),buttonConverter);
        buttonConverter.addActionListener(new ConverterEvent(this,at_));
        lineConverter_.add(buttonConverter);
        panel_.add(lineConverter_);
        AbsPanel lineApp_ = getCompoFactory().newLineBox();
        buttonApps = getCompoFactory().newPlainButton("8");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingFull.getMainWindowClass(),at_);
        _list.getButtons().addEntry(LaunchingFull.getMainWindowClass(), buttonApps);
        buttonApps.addActionListener(new AppsEvent(this,at_,_cdmFactory));
        lineApp_.add(buttonApps);
        panel_.add(lineApp_);
        AbsPanel lineNet_ = getCompoFactory().newLineBox();
        buttonNet = getCompoFactory().newPlainButton("9");
        at_ = _list.getThreadFactory().newAtomicInteger(0);
        _list.getCounts().addEntry(LaunchingNetwork.getMainWindowClass(),at_);
        _list.getButtons().addEntry(LaunchingNetwork.getMainWindowClass(), buttonNet);
        buttonNet.addActionListener(new NetWorkEvent(this,at_,_cardFactories,_aikiFactory,buttonNet));
        lineNet_.add(buttonNet);
        panel_.add(lineNet_);
        panel_.add(new Clock(_list));
//        for (String l: Constants.getAvailableLanguages()) {
//            RadioButton radio_ = new RadioButton(Constants.getDisplayLanguage(l));
//            radio_.addActionListener(new SetLanguage(l, getFrames()));
//            radio_.setSelected(StringUtil.quickEq(l,_lg));
//            group.add(radio_);
//            panel_.add(radio_);
//            radios.add(radio_);
//        }
        panel_.setPreferredSize(new MetaDimension(256, 192));
        setContentPane(panel_);
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
        setVisible(true);
        pack();
    }
    @Override
    public void quit() {
//        int nb_ = IndexConstants.SIZE_EMPTY;
//        for (AbstractAtomicInteger a: getFrames().getCounts().values()) {
//            if (a.get() > 0) {
//                nb_++;
//            }
//        }
//        if (nb_ > IndexConstants.SIZE_EMPTY) {
//            return;
//        }
        writeCoords();
        nativeExit();
    }
//    @Override
//    public boolean canChangeLanguage() {
//        return true;
//    }
    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
        SoftApplicationCore.saveLanguage(LaunchingApplications.getTempFolder(getFrames()), _language,getStreams());
        for (AbsGroupFrame g: getFrames().getFrames()) {
            g.changeLanguage(_language);
        }
        selectLangagueButton(_language);
//        if (canChangeLanguageAll()) {
//            setLanguageKey(_language);
//            SoftApplicationCore.saveLanguage(LaunchingApplications.getTempFolder(getFrames()), _language,getStreams());
//            for (AbsGroupFrame g: getFrames().getFrames()) {
//                g.changeLanguage(_language);
//            }
//            selectLangagueButton(_language);
//        } else {
//            selectLangagueButton(getLanguageKey());
//            GuiBaseUtil.showDialogError(GuiConstants.ERROR_MESSAGE, this.getCommonFrame());
//        }
    }

    private void selectLangagueButton(String _language) {
        for (AbsRadioButton r: radios) {
            r.setSelected(false);
        }
        for (AbsRadioButton r: radios) {
            r.setSelected(StringUtil.quickEq(r.getText(), Constants.getDisplayLanguage(_language)));
        }
        pack();
    }

    private void writeCoords() {
        MetaPoint point_=getLocation();
        int x_ = point_.getXcoord();
        int y_ = point_.getYcoord();
        SoftApplicationCore.saveCoords(LaunchingApplications.getTempFolder(getFrames()), LaunchingApplications.COORDS, x_, y_,getStreams());
    }

    @Override
    public String getApplicationName() {
        return "applications";
    }
}
