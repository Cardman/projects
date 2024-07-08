package applications.gui;

import aiki.gui.WindowAiki;
import applications.main.LaunchingApplications;
import cards.gui.WindowCards;
import code.gui.*;
import code.gui.events.*;
import code.gui.files.FileDialog;
import code.gui.images.MetaDimension;
import code.gui.images.MetaPoint;
import code.player.gui.WindowPlayer;
import code.stream.StreamLanguageUtil;
import code.util.CustList;
import code.util.core.StringUtil;






public final class WindowApps extends GroupFrame implements AbsOpenQuit {

    private static final String APPLICATIONS = "Applications";

    private final AbsButton buttonPokemon;

    private final AbsButton buttonCards;
    private final AbsButton buttonApps;
    private final AbsButton buttonTests;
    private final AbsButton buttonRenders;
    private final AbsButton buttonDemo;
    private final AbsButton buttonPlayer;
    private final AbsButton buttonConverter;
    private final AbsButton buttonNet;

    private final CustButtonGroup group = new CustButtonGroup();

    private final CustList<AbsRadioButton> radios = new CustList<AbsRadioButton>();
    private final WithAppFactories withAppFactories;

    public WindowApps(String _lg, WithAppFactories _list) {
        super(_list.getProgramInfos());
        withAppFactories = _list;
        GuiBaseUtil.choose(_lg, this, _list.getProgramInfos().getCommon());
        setFocusableWindowState(true);
        setTitle(APPLICATIONS);
        AbsPanel panel_ = getCompoFactory().newPageBox();
        AbsPanel linePokemon_ = getCompoFactory().newLineBox();
        buttonPokemon = _list.getProgramInfos().getCompoFactory().newImgButton(WindowAiki.getIcon(getImageFactory()));
//        AbstractAtomicInteger at_ = _list.getProgramInfos().getThreadFactory().newAtomicInteger(0);
//        _list.getProgramInfos().getCounts().addEntry(WindowAiki.APPS_AIKI, at_);
//        _list.getProgramInfos().getButtons().addEntry(WindowAiki.APPS_AIKI, buttonPokemon);
        buttonPokemon.addActionListener(new PokemonEvent(this, buttonPokemon));
        linePokemon_.add(buttonPokemon);
        panel_.add(linePokemon_);
        AbsPanel lineCards_ = getCompoFactory().newLineBox();
        buttonCards = _list.getProgramInfos().getCompoFactory().newImgButton(WindowCards.getIcon(getImageFactory()));
//        at_ = _list.getProgramInfos().getThreadFactory().newAtomicInteger(0);
//        _list.getProgramInfos().getCounts().addEntry(WindowCards.APP_CARDS,at_);
//        _list.getProgramInfos().getButtons().addEntry(WindowCards.APP_CARDS, buttonCards);
        buttonCards.addActionListener(new CardsEvent(this, buttonCards));
        lineCards_.add(buttonCards);
        panel_.add(lineCards_);
        AbsPanel lineTests_ = getCompoFactory().newLineBox();
        buttonTests = getCompoFactory().newPlainButton("3");
//        at_ = _list.getProgramInfos().getThreadFactory().newAtomicInteger(0);
//        _list.getProgramInfos().getCounts().addEntry(WindowUnit.APPS_UNIT,at_);
//        _list.getProgramInfos().getButtons().addEntry(WindowUnit.APPS_UNIT, buttonTests);
        buttonTests.addActionListener(new AppUnitEvent(this, buttonTests));
        lineTests_.add(buttonTests);
        panel_.add(lineTests_);
        AbsPanel lineRenders_ = getCompoFactory().newLineBox();
        buttonRenders = getCompoFactory().newPlainButton("4");
//        at_ = _list.getProgramInfos().getThreadFactory().newAtomicInteger(0);
//        _list.getProgramInfos().getCounts().addEntry(WindowRenders.APPS_RENDERS_SITES,at_);
//        _list.getProgramInfos().getButtons().addEntry(WindowRenders.APPS_RENDERS_SITES, buttonRenders);
        buttonRenders.addActionListener(new RenderEvent(this, buttonRenders));
        lineRenders_.add(buttonRenders);
        panel_.add(lineRenders_);
        AbsPanel lineDemo_ = getCompoFactory().newLineBox();
        buttonDemo = getCompoFactory().newPlainButton("5");
//        at_ = _list.getProgramInfos().getThreadFactory().newAtomicInteger(0);
//        _list.getProgramInfos().getCounts().addEntry(WindowRts.APPS_RTS,at_);
//        _list.getProgramInfos().getButtons().addEntry(WindowRts.APPS_RTS, buttonDemo);
        buttonDemo.addActionListener(new DemoEvent(this, buttonDemo));
        lineDemo_.add(buttonDemo);
        panel_.add(lineDemo_);
        AbsPanel linePlayer_ = getCompoFactory().newLineBox();
        buttonPlayer = _list.getProgramInfos().getCompoFactory().newImgButton(WindowPlayer.getIcon(getImageFactory()));
//        at_ = _list.getProgramInfos().getThreadFactory().newAtomicInteger(0);
//        _list.getProgramInfos().getCounts().addEntry(WindowPlayer.APPS_MUSICPLAYER,at_);
//        _list.getProgramInfos().getButtons().addEntry(WindowPlayer.APPS_MUSICPLAYER, buttonPlayer);
        buttonPlayer.addActionListener(new PlayerEvent(this, buttonPlayer));
        linePlayer_.add(buttonPlayer);
        panel_.add(linePlayer_);
        AbsPanel lineConverter_ = getCompoFactory().newLineBox();
        buttonConverter = getCompoFactory().newPlainButton("7");
//        at_ = _list.getProgramInfos().getThreadFactory().newAtomicInteger(0);
//        _list.getProgramInfos().getCounts().addEntry(WindowConverter.APPS_CONVERTER,at_);
//        _list.getProgramInfos().getButtons().addEntry(WindowConverter.APPS_CONVERTER,buttonConverter);
        buttonConverter.addActionListener(new ConverterEvent(this, buttonConverter));
        lineConverter_.add(buttonConverter);
        panel_.add(lineConverter_);
        AbsPanel lineApp_ = getCompoFactory().newLineBox();
        buttonApps = getCompoFactory().newPlainButton("8");
//        at_ = _list.getProgramInfos().getThreadFactory().newAtomicInteger(0);
//        _list.getProgramInfos().getCounts().addEntry(WindowFull.APPS_LAUNCHER,at_);
//        _list.getProgramInfos().getButtons().addEntry(WindowFull.APPS_LAUNCHER, buttonApps);
        buttonApps.addActionListener(new AppsEvent(this, buttonApps));
        lineApp_.add(buttonApps);
        panel_.add(lineApp_);
        AbsPanel lineNet_ = getCompoFactory().newLineBox();
        buttonNet = getCompoFactory().newPlainButton("9");
//        at_ = _list.getProgramInfos().getThreadFactory().newAtomicInteger(0);
//        _list.getProgramInfos().getCounts().addEntry(WindowNetWork.APPS_NETWORK,at_);
//        _list.getProgramInfos().getButtons().addEntry(WindowNetWork.APPS_NETWORK, buttonNet);
        buttonNet.addActionListener(new NetWorkEvent(this, buttonNet));
        lineNet_.add(buttonNet);
        panel_.add(lineNet_);
        panel_.add(new Clock(_list.getProgramInfos()));
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
        StreamLanguageUtil.saveLanguage(LaunchingApplications.getTempFolder(getFrames()), _language,getStreams());
        for (AbsOpenQuit g: getFrames().getFrames()) {
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
            r.setSelected(StringUtil.quickEq(r.getText(), getFrames().getDisplayLanguages().getVal(_language)));
        }
        pack();
    }

    private void writeCoords() {
        MetaPoint point_=getLocation();
        int x_ = point_.getXcoord();
        int y_ = point_.getYcoord();
        FileDialog.saveCoords(LaunchingApplications.getTempFolder(getFrames()), LaunchingApplications.COORDS, x_, y_,getStreams());
    }

    public WithAppFactories getWithAppFactories() {
        return withAppFactories;
    }

    public AbsButton getButtonPokemon() {
        return buttonPokemon;
    }

    public AbsButton getButtonCards() {
        return buttonCards;
    }

    public AbsButton getButtonApps() {
        return buttonApps;
    }

    public AbsButton getButtonTests() {
        return buttonTests;
    }

    public AbsButton getButtonRenders() {
        return buttonRenders;
    }

    public AbsButton getButtonDemo() {
        return buttonDemo;
    }

    public AbsButton getButtonPlayer() {
        return buttonPlayer;
    }

    public AbsButton getButtonConverter() {
        return buttonConverter;
    }

    public AbsButton getButtonNet() {
        return buttonNet;
    }

    @Override
    public String getApplicationName() {
        return "applications";
    }
}
