package cards.gui;

import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.gui.menus.*;
import cards.main.CardNatLgNamesNavigation;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractFutureParam;
import code.util.*;

public final class WindowCardsCore {


    private ContainerGame containerGame;
    private final Clock clock;
    private final FacadeCards facadeCards;

    private final EnabledMenu language;
    private final EnabledMenu displaying;
    private final IdMap<GameEnum,EnabledMenu> displayingGames = new IdMap<GameEnum,EnabledMenu>();

    private StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> prepared;
    private final DialogDisplayingBelote dialogDisplayingBelote;
    private final DialogDisplayingTarot dialogDisplayingTarot;
    private final DialogDisplayingPresident dialogDisplayingPresident;
    private final DialogTricksBelote dialogTricksBelote;
    private final DialogTricksPresident dialogTricksPresident;
    private final DialogTricksTarot dialogTricksTarot;
    private final DialogTeamsPlayers dialogTeamsPlayers;
    private final IntArtCardGames ia;
    private IntFirstDealBelote firstDealBelote;
    private IntFirstDealPresident firstDealPresident;
    private IntFirstDealTarot firstDealTarot;
    private final ReportingFrame reportingFrame;

    public WindowCardsCore(WindowCardsInt _cards,CardGamesStream _nicknames, AbstractProgramInfos _list, IntArtCardGames _ia, AbstractAtomicBoolean _modal, EnabledMenu _lgMenu) {
        ia = _ia;
        reportingFrame = ReportingFrame.newInstance(_list);
        language = _lgMenu;
        facadeCards = new FacadeCards(_nicknames);
        TranslationsLg lg_ = _list.currentLg();
        /* Partie/Editer "Permet d'editer n'importe quelle partie de cartes et accessible n'importe quand"*/
        displaying= _list.getCompoFactory().newMenu(_cards.getMenusMessages().getVal(MessagesGuiCards.CST_DISPLAYING));
        EnabledMenu sousSousMenu_ = _list.getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
        sousSousMenu_.addActionListener(new DisplayingGameEvent(_cards, GameEnum.BELOTE));
        displaying.addMenuItem(sousSousMenu_);
        dialogDisplayingBelote = new DialogDisplayingBelote(_list, sousSousMenu_);
        displayingGames.put(GameEnum.BELOTE, sousSousMenu_);
        sousSousMenu_ = _list.getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousSousMenu_.addActionListener(new DisplayingGameEvent(_cards, GameEnum.PRESIDENT));
        displaying.addMenuItem(sousSousMenu_);
        dialogDisplayingTarot = new DialogDisplayingTarot(_list, sousSousMenu_);
        displayingGames.put(GameEnum.PRESIDENT, sousSousMenu_);
        sousSousMenu_ = _list.getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
        sousSousMenu_.addActionListener(new DisplayingGameEvent(_cards, GameEnum.TAROT));
        dialogDisplayingPresident = new DialogDisplayingPresident(_list, sousSousMenu_);
        displaying.addMenuItem(sousSousMenu_);
        displayingGames.put(GameEnum.TAROT, sousSousMenu_);
        dialogTricksBelote = new DialogTricksBelote(_list,_modal);
        dialogTricksPresident = new DialogTricksPresident(_list,_modal);
        dialogTricksTarot = new DialogTricksTarot(_list,_modal);
        dialogTeamsPlayers = new DialogTeamsPlayers(_list,_modal);
        clock = new Clock(_list);
        facadeCards.init(WindowCards.getTempFolderSl(_list),_list);
        setFirstDealBelote(new DefFirstDealBelote());
        setFirstDealPresident(new DefFirstDealPresident());
        setFirstDealTarot(new DefFirstDealTarot());
    }

    public void changerNombreDePartiesEnQuittant(GroupFrame _inst) {
        facadeCards.changerNombreDePartiesEnQuittant(WindowCards.getTempFolderSl(_inst.getFrames()),_inst.getFrames());
    }
    public void manageLanguage(AbsOpenQuit _inst, WindowCardsInt _win, LanguageDialogButtons _dial) {
//        if (!_inst.canChangeLanguageAll()) {
//            GuiBaseUtil.showDialogError(GuiConstants.ERROR_MESSAGE, _inst.getCommonFrame());
//            return;
//        }
        _dial.init(_win.getCommonFrame(), _win.getFrames(),_win.getMenusMessages().getVal(MessagesGuiCards.CST_LANGUAGE),_inst);
//        GuiBaseUtil.setLanguageDialog(_inst,_inst, _win.getMenusMessages().getVal(MessagesGuiCards.CST_LANGUAGE));
//        String langue_ = GuiBaseUtil.getStaticLanguage(_inst.getLanguageDialog());
//        AbstractProgramInfos infos_ = _inst.getFrames();
//        String value_ = StringUtil.nullToEmpty(langue_);
//        GuiBaseUtil.changeStaticLanguage(value_, infos_, infos_.getCommon());
//        StreamLanguageUtil.saveLanguage(WindowCards.getTempFolder(_inst.getFrames()), value_,infos_.getStreams());
    }
    public void closeWindows() {
        dialogDisplayingBelote.closeWindow();
        dialogDisplayingPresident.closeWindow();
        dialogDisplayingTarot.closeWindow();
    }
    public void displayingGame(WindowCardsInt _inst,GameEnum _game) {
        TranslationsLg lg_ = _inst.getFrames().currentLg();
        if (_game == GameEnum.BELOTE) {
            DialogDisplayingBelote.setDialogDisplayingBelote(_game.toString(lg_), _inst);
            DialogDisplayingBelote.getDisplaying(_inst.getDialogDisplayingBelote());
        }
        if (_game == GameEnum.PRESIDENT) {
            DialogDisplayingPresident.setDialogDisplayingPresident(_game.toString(lg_), _inst);
            DialogDisplayingPresident.getDisplaying(_inst.getDialogDisplayingPresident());
        }
        if (_game == GameEnum.TAROT) {
            DialogDisplayingTarot.setDialogDisplayingTarot(_game.toString(lg_), _inst);
            DialogDisplayingTarot.getDisplaying(_inst.getDialogDisplayingTarot());
        }
    }
//    void initParametersMenu(GroupFrame _inst,WindowCardsInt _cards) {
//        /* Parametres */
//        parameters=_inst.getCompoFactory().newMenu(_inst.getMessages().getVal(CST_PARAMETERS));
////        AbsMenuItem sousMenu_=getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
////        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.BELOTE));
////        sousMenu_.setAccelerator(GuiConstants.VK_B, GuiConstants.SHIFT_DOWN_MASK);
////        parameters.addMenuItem(sousMenu_);
////        rulesGames.put(GameEnum.BELOTE, sousMenu_);
////        sousMenu_=getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
////        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.PRESIDENT));
////        sousMenu_.setAccelerator(GuiConstants.VK_P, GuiConstants.SHIFT_DOWN_MASK);
////        parameters.addMenuItem(sousMenu_);
////        rulesGames.put(GameEnum.PRESIDENT, sousMenu_);
////        sousMenu_=getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
////        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.TAROT));
////        sousMenu_.setAccelerator(GuiConstants.VK_T, GuiConstants.SHIFT_DOWN_MASK);
////        parameters.addMenuItem(sousMenu_);
////        rulesGames.put(GameEnum.TAROT, sousMenu_);
////        players=getCompoFactory().newMenuItem(getMessages().getVal(CST_PLAYERS));
////        players.addActionListener(new ManageNicknameEvent(this));
////        players.setAccelerator(GuiConstants.VK_J, GuiConstants.CTRL_DOWN_MASK + GuiConstants.ALT_DOWN_MASK);
////        parameters.addMenuItem(players);
////        launching=getCompoFactory().newMenuItem(getMessages().getVal(CST_LAUNCHING));
////        launching.addActionListener(new ManageSoftEvent(this, CST_LAUNCHING));
////        launching.setAccelerator(GuiConstants.VK_L, GuiConstants.CTRL_DOWN_MASK);
////        parameters.addMenuItem(launching);
//        commonParametersMenu(_inst, _cards);
//    }

    public void commonParametersMenu(EnabledMenu _params, GroupFrame _inst) {
//        if (language != null) {
//            language.addActionListener(new ManageLanguageEventCards(_cards));
//            language.setAccelerator(GuiConstants.VK_F6,0);
//            _params.addMenuItem(language);
//        }
//        language= _inst.getCompoFactory().newMenuItem(_cards.getMenusMessages().getVal(MessagesGuiCards.CST_LANGUAGE));
//        language.addActionListener(new ManageLanguageEventCards(_cards));
////        if (Standalone.isStandalone()) {
////            language.setAccelerator(KeyStroke.getKeyStroke(F_SIX));
////            parameters.add(language);
////        }
//        language.setAccelerator(GuiConstants.VK_F6,0);
//        _params.addMenuItem(language);
        _params.addMenuItem(displaying);
        _inst.getJMenuBar().add(_params);
    }

    public static AbsGridConstraints ctsRem(AbsCompoFactory _compo, boolean _rem) {
        AbsGridConstraints cts_ = _compo.newGridCts();
        if (_rem) {
            cts_.gridwidth(GuiConstants.REMAINDER);
        }
        return cts_;
    }
    public static AbsGridConstraints ctsRem(AbsCompoFactory _compo) {
        AbsGridConstraints cts_ = _compo.newGridCts();
        cts_.gridwidth(GuiConstants.REMAINDER);
        return cts_;
    }

    public static AbsGridConstraints cts(AbsCompoFactory _compo) {
        return _compo.newGridCts();
    }
    public IdMap<GameEnum, EnabledMenu> getDisplayingGames() {
        return displayingGames;
    }

    public EnabledMenu getDisplaying() {
        return displaying;
    }

    public EnabledMenu getLanguage() {
        return language;
    }

    public DialogTeamsPlayers getDialogTeamsPlayers() {
        return dialogTeamsPlayers;
    }

    public StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> getPrepared() {
        return prepared;
    }

    public void setPrepare(StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> _p) {
        this.prepared = _p;
    }

    public DialogDisplayingBelote getDialogDisplayingBelote() {
        return dialogDisplayingBelote;
    }
    public DialogDisplayingTarot getDialogDisplayingTarot() {
        return dialogDisplayingTarot;
    }
    public DialogDisplayingPresident getDialogDisplayingPresident() {
        return dialogDisplayingPresident;
    }
    public DialogTricksBelote getDialogTricksBelote() {
        return dialogTricksBelote;
    }
    public DialogTricksPresident getDialogTricksPresident() {
        return dialogTricksPresident;
    }
    public DialogTricksTarot getDialogTricksTarot() {
        return dialogTricksTarot;
    }

    public Clock getClock() {
        return clock;
    }

    public IntArtCardGames getIa() {
        return ia;
    }

    public FacadeCards getFacadeCards() {
        return facadeCards;
    }

    public ContainerGame getContainerGame() {
        return containerGame;
    }

    public void setContainerGame(ContainerGame _c) {
        this.containerGame = _c;
    }

    public IntFirstDealBelote getFirstDealBelote() {
        return firstDealBelote;
    }

    public void setFirstDealBelote(IntFirstDealBelote _f) {
        this.firstDealBelote = _f;
    }

    public IntFirstDealPresident getFirstDealPresident() {
        return firstDealPresident;
    }

    public void setFirstDealPresident(IntFirstDealPresident _f) {
        this.firstDealPresident = _f;
    }

    public IntFirstDealTarot getFirstDealTarot() {
        return firstDealTarot;
    }

    public void setFirstDealTarot(IntFirstDealTarot _f) {
        this.firstDealTarot = _f;
    }

    public ReportingFrame getReportingFrame() {
        return reportingFrame;
    }
}
