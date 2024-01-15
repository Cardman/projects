package cards.gui;

import cards.belote.sml.*;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.animations.*;
import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.gui.menus.*;
import cards.president.sml.*;
import cards.tarot.sml.*;
import code.gui.*;
import code.gui.initialize.*;
import code.stream.*;
import code.threads.AbstractFutureParam;
import code.util.*;
import code.util.core.*;

public final class WindowCardsCore {


    public static final String CST_TIMING = "timing";

    static final String CST_INTERACT = "interact";

    static final String CST_LANGUAGE = "language";

    static final String CST_DISPLAYING = "displaying";

    private ContainerGame containerGame;
    private final Clock clock;
    private final FacadeCards facadeCards = new FacadeCards();

    private EnabledMenu parameters;
    private EnabledMenu timing;
    private EnabledMenu interact;
    private EnabledMenu language;
    private EnabledMenu displaying;
    private final IdMap<GameEnum,EnabledMenu> displayingGames = new IdMap<GameEnum,EnabledMenu>();

    private final StringMap<StringMap<PreparedPagesCards>> preparedBelote;
    private final StringMap<StringMap<PreparedPagesCards>> preparedPresident;
    private final StringMap<StringMap<PreparedPagesCards>> preparedTarot;
    private final DialogDisplayingBelote dialogDisplayingBelote;
    private final DialogDisplayingTarot dialogDisplayingTarot;
    private final DialogDisplayingPresident dialogDisplayingPresident;
    private final DialogHelpBelote dialogHelpBelote;
    private final DialogHelpPresident dialogHelpPresident;
    private final DialogHelpTarot dialogHelpTarot;
    private final DialogRulesBelote dialogRulesBelote;
    private final DialogRulesPresident dialogRulesPresident;
    private final DialogRulesTarot dialogRulesTarot;
    private final DialogTricksBelote dialogTricksBelote;
    private final DialogTricksPresident dialogTricksPresident;
    private final DialogTricksTarot dialogTricksTarot;
    private final DialogTeamsPlayers dialogTeamsPlayers;
    private final DialogSoft dialogSoft;
    private AbstractFutureParam<StringMap<StringMap<int[][]>>> taskLoading;
    private AbstractFutureParam<StringMap<StringMap<int[][]>>> taskLoadingMiniDef;
    private AbstractFutureParam<StringMap<StringMap<int[][]>>> taskLoadingMiniSel;
    public WindowCardsCore(String _lg, AbstractProgramInfos _list,
                           StringMap<StringMap<PreparedPagesCards>> _belote,
                           StringMap<StringMap<PreparedPagesCards>> _president,
                           StringMap<StringMap<PreparedPagesCards>> _tarot) {
        dialogDisplayingBelote = new DialogDisplayingBelote(_list);
        dialogDisplayingTarot = new DialogDisplayingTarot(_list);
        dialogDisplayingPresident = new DialogDisplayingPresident(_list);
        dialogHelpBelote = new DialogHelpBelote(_list);
        dialogHelpPresident = new DialogHelpPresident(_list);
        dialogHelpTarot = new DialogHelpTarot(_list);
        dialogRulesBelote = new DialogRulesBelote(_list);
        dialogRulesPresident = new DialogRulesPresident(_list);
        dialogRulesTarot = new DialogRulesTarot(_list);
        dialogTricksBelote = new DialogTricksBelote(_list);
        dialogTricksPresident = new DialogTricksPresident(_list);
        dialogTricksTarot = new DialogTricksTarot(_list);
        dialogTeamsPlayers = new DialogTeamsPlayers(_list);
        dialogSoft = new DialogSoft(_list);
        preparedBelote = _belote;
        preparedPresident = _president;
        preparedTarot = _tarot;
        clock = new Clock(_list);
        facadeCards.init(WindowCards.getTempFolderSl(_list),_list,_lg);
    }
    public void manageSoft(GroupFrame _inst,WindowCardsInt _cards, String _key) {
        DialogSoft.initDialogSoft(_inst.getMessages().getVal(_key), _cards);
        DialogSoft.setDialogSoft(_key, _cards);
        getFacadeCards().setParametres(DialogSoft.getParametres(dialogSoft));
        getFacadeCards().getParametres().sauvegarder(StringUtil.concat(WindowCards.getTempFolderSl(_cards.getFrames()),FacadeCards.PARAMS),_cards.getStreams());
        containerGame.setSettings(getFacadeCards().getParametres());
    }

    public void changerNombreDePartiesEnQuittant(GroupFrame _inst) {
        facadeCards.changerNombreDePartiesEnQuittant(WindowCards.getTempFolderSl(_inst.getFrames()),_inst.getFrames());
    }
    public void manageLanguage(GroupFrame _inst) {
//        if (!_inst.canChangeLanguageAll()) {
//            GuiBaseUtil.showDialogError(GuiConstants.ERROR_MESSAGE, _inst.getCommonFrame());
//            return;
//        }
        GuiBaseUtil.setLanguageDialog(_inst,_inst, _inst.getMessages().getVal(CST_LANGUAGE));
        String langue_ = GuiBaseUtil.getStaticLanguage(_inst.getLanguageDialog());
        AbstractProgramInfos infos_ = _inst.getFrames();
        String value_ = StringUtil.nullToEmpty(langue_);
        GuiBaseUtil.changeStaticLanguage(value_, infos_, infos_.getCommon());
        StreamLanguageUtil.saveLanguage(WindowCards.getTempFolder(_inst.getFrames()), value_,infos_.getStreams());
    }
    public void displayingGame(WindowCardsInt _inst,GameEnum _game) {
        String lg_ = _inst.getLanguageKey();
        if (_game == GameEnum.BELOTE) {
            DialogDisplayingBelote.setDialogDisplayingBelote(_game.toString(lg_), _inst);
            getFacadeCards().setDisplayingBelote(DialogDisplayingBelote.getDisplaying(_inst.getDialogDisplayingBelote()));
            StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(_inst.getFrames()),FacadeCards.DISPLAY_BELOTE), DocumentWriterBeloteUtil.setDisplayingBelote(getFacadeCards().getDisplayingBelote()),_inst.getStreams());
            containerGame.setDisplayingBelote(getFacadeCards().getDisplayingBelote());
        } else if (_game == GameEnum.PRESIDENT) {
            DialogDisplayingPresident.setDialogDisplayingPresident(_game.toString(lg_), _inst);
            getFacadeCards().setDisplayingPresident(DialogDisplayingPresident.getDisplaying(_inst.getDialogDisplayingPresident()));
            StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(_inst.getFrames()),FacadeCards.DISPLAY_PRESIDENT), DocumentWriterPresidentUtil.setDisplayingPresident(getFacadeCards().getDisplayingPresident()),_inst.getStreams());
            containerGame.setDisplayingPresident(getFacadeCards().getDisplayingPresident());
        } else if (_game == GameEnum.TAROT) {
            DialogDisplayingTarot.setDialogDisplayingTarot(_game.toString(lg_), _inst);
            getFacadeCards().setDisplayingTarot(DialogDisplayingTarot.getDisplaying(_inst.getDialogDisplayingTarot()));
            StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(_inst.getFrames()),FacadeCards.DISPLAY_TAROT), DocumentWriterTarotUtil.setDisplayingTarot(getFacadeCards().getDisplayingTarot()),_inst.getStreams());
            containerGame.setDisplayingTarot(getFacadeCards().getDisplayingTarot());
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

    public void commonParametersMenu(EnabledMenu _params,GroupFrame _inst, WindowCardsInt _cards) {
        parameters = _params;
        String lg_ = _cards.getLanguageKey();
        timing= _inst.getCompoFactory().newMenuItem(_inst.getMessages().getVal(CST_TIMING));
        timing.addActionListener(new ManageSoftEvent(_cards, CST_TIMING));
        timing.setAccelerator(GuiConstants.VK_F4,0);
        parameters.addMenuItem(timing);
        interact= _inst.getCompoFactory().newMenuItem(_inst.getMessages().getVal(CST_INTERACT));
        interact.addActionListener(new ManageSoftEvent(_cards, CST_INTERACT));
        interact.setAccelerator(GuiConstants.VK_F5,0);
        parameters.addMenuItem(interact);
        language= _inst.getCompoFactory().newMenuItem(_inst.getMessages().getVal(CST_LANGUAGE));
        language.addActionListener(new ManageLanguageEventCards(_cards));
//        if (Standalone.isStandalone()) {
//            language.setAccelerator(KeyStroke.getKeyStroke(F_SIX));
//            parameters.add(language);
//        }
        language.setAccelerator(GuiConstants.VK_F6,0);
        parameters.addMenuItem(language);
        /* Partie/Editer "Permet d'editer n'importe quelle partie de cartes et accessible n'importe quand"*/
        displaying= _inst.getCompoFactory().newMenu(_inst.getMessages().getVal(CST_DISPLAYING));
        EnabledMenu sousSousMenu_ = _inst.getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
        sousSousMenu_.addActionListener(new DisplayingGameEvent(_cards, GameEnum.BELOTE));
        displaying.addMenuItem(sousSousMenu_);
        displayingGames.put(GameEnum.BELOTE, sousSousMenu_);
        sousSousMenu_ = _inst.getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousSousMenu_.addActionListener(new DisplayingGameEvent(_cards, GameEnum.PRESIDENT));
        displaying.addMenuItem(sousSousMenu_);
        displayingGames.put(GameEnum.PRESIDENT, sousSousMenu_);
        sousSousMenu_ = _inst.getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
        sousSousMenu_.addActionListener(new DisplayingGameEvent(_cards, GameEnum.TAROT));
        displaying.addMenuItem(sousSousMenu_);
        displayingGames.put(GameEnum.TAROT, sousSousMenu_);
        parameters.addMenuItem(displaying);
        _inst.getJMenuBar().add(parameters);
    }

    public IdMap<GameEnum, EnabledMenu> getDisplayingGames() {
        return displayingGames;
    }

    public EnabledMenu getDisplaying() {
        return displaying;
    }

    public EnabledMenu getParameters() {
        return parameters;
    }

    public EnabledMenu getTiming() {
        return timing;
    }

    public EnabledMenu getInteract() {
        return interact;
    }

    public EnabledMenu getLanguage() {
        return language;
    }

    public DialogTeamsPlayers getDialogTeamsPlayers() {
        return dialogTeamsPlayers;
    }

    public DialogSoft getDialogSoft() {
        return dialogSoft;
    }

    public StringMap<StringMap<PreparedPagesCards>> getPreparedBelote() {
        return preparedBelote;
    }
    public StringMap<StringMap<PreparedPagesCards>> getPreparedPresident() {
        return preparedPresident;
    }
    public StringMap<StringMap<PreparedPagesCards>> getPreparedTarot() {
        return preparedTarot;
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
    public DialogHelpBelote getDialogHelpBelote() {
        return dialogHelpBelote;
    }
    public DialogHelpPresident getDialogHelpPresident() {
        return dialogHelpPresident;
    }
    public DialogHelpTarot getDialogHelpTarot() {
        return dialogHelpTarot;
    }
    public DialogRulesBelote getDialogRulesBelote() {
        return dialogRulesBelote;
    }
    public DialogRulesPresident getDialogRulesPresident() {
        return dialogRulesPresident;
    }
    public DialogRulesTarot getDialogRulesTarot() {
        return dialogRulesTarot;
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

    public StringMap<StringMap<int[][]>> getImages() {
        return getTaskLoading().attendreResultat();
    }

    public StringMap<StringMap<int[][]>> getImagesMiniDef() {
        return getTaskLoadingMiniDef().attendreResultat();
    }

    public StringMap<StringMap<int[][]>> getImagesMiniSel() {
        return getTaskLoadingMiniSel().attendreResultat();
    }

    public AbstractFutureParam<StringMap<StringMap<int[][]>>> getTaskLoading() {
        return taskLoading;
    }

    public void setTaskLoading(AbstractFutureParam<StringMap<StringMap<int[][]>>> _t) {
        this.taskLoading = _t;
    }

    public AbstractFutureParam<StringMap<StringMap<int[][]>>> getTaskLoadingMiniDef() {
        return taskLoadingMiniDef;
    }

    public void setTaskLoadingMiniDef(AbstractFutureParam<StringMap<StringMap<int[][]>>> _t) {
        this.taskLoadingMiniDef = _t;
    }

    public AbstractFutureParam<StringMap<StringMap<int[][]>>> getTaskLoadingMiniSel() {
        return taskLoadingMiniSel;
    }

    public void setTaskLoadingMiniSel(AbstractFutureParam<StringMap<StringMap<int[][]>>> _t) {
        this.taskLoadingMiniSel = _t;
    }

    public Clock getClock() {
        return clock;
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
}
