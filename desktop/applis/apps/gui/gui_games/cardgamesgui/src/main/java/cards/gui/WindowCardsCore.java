package cards.gui;

import cards.belote.*;
import cards.belote.sml.*;
import cards.consts.MixCardsChoice;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.facade.sml.*;
import cards.gui.animations.*;
import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.gui.menus.*;
import cards.main.*;
import cards.president.*;
import cards.president.sml.*;
import cards.tarot.*;
import cards.tarot.sml.*;
import code.gui.*;
import code.gui.initialize.*;
import code.stream.*;
import code.util.*;
import code.util.core.*;

public final class WindowCardsCore {


    public static final String CST_TIMING = "timing";

    static final String CST_INTERACT = "interact";

    static final String CST_LANGUAGE = "language";

    static final String CST_DISPLAYING = "displaying";

    private static final char LINE_RETURN = '\n';
    private ContainerGame containerGame;
    private final Clock clock;

    /**Parametres de lancement, de jouerie*/
    private SoftParams parametres=new SoftParams();
    /**
     des pseudonymes*/
    private Nicknames pseudosJoueurs;
    private RulesBelote reglesBelote=new RulesBelote();
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    private RulesPresident reglesPresident=new RulesPresident();
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private RulesTarot reglesTarot=new RulesTarot();
    private DisplayingTarot displayingTarot = new DisplayingTarot();

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
    private StringMap<StringMap<String>> images = new StringMap<StringMap<String>>();
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
        pseudosJoueurs=new Nicknames(_lg);
        clock = new Clock(_list);
        reglesBelote = DocumentReaderBeloteUtil.getRulesBelote(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.RULES_BELOTE),_list.getFileCoreStream(),_list.getStreams()));
        if (!reglesBelote.isValidRules()) {
            reglesBelote = new RulesBelote();
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.RULES_BELOTE), DocumentWriterBeloteUtil.setRulesBelote(reglesBelote),_list.getStreams());
        }
        displayingBelote = DocumentReaderBeloteUtil.getDisplayingBelote(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.DISPLAY_BELOTE),_list.getFileCoreStream(),_list.getStreams()));
        displayingBelote.validate();
        reglesPresident = DocumentReaderPresidentUtil.getRulesPresident(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.RULES_PRESIDENT),_list.getFileCoreStream(),_list.getStreams()));
        if (!reglesPresident.isValidRules()) {
            reglesPresident = new RulesPresident();
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(reglesPresident),_list.getStreams());
        }
        displayingPresident = DocumentReaderPresidentUtil.getDisplayingPresident(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.DISPLAY_PRESIDENT),_list.getFileCoreStream(),_list.getStreams()));
        displayingPresident.validate();
        reglesTarot = DocumentReaderTarotUtil.getRulesTarot(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.RULES_TAROT),_list.getFileCoreStream(),_list.getStreams()));
        if (!reglesTarot.isValidRules()) {
            reglesTarot = new RulesTarot();
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.RULES_TAROT), DocumentWriterTarotUtil.setRulesTarot(reglesTarot),_list.getStreams());
        }
        displayingTarot = DocumentReaderTarotUtil.getDisplayingTarot(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.DISPLAY_TAROT),_list.getFileCoreStream(),_list.getStreams()));
        displayingTarot.validate();
        parametres = DocumentReaderCardsUnionUtil.getSoftParams(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.PARAMS),_list.getFileCoreStream(),_list.getStreams()));
        parametres.setDelays();
//        parametres.setLocale(_locale);

        pseudosJoueurs = DocumentReaderCardsUnionUtil.getNicknames(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.PLAYERS),_list.getFileCoreStream(),_list.getStreams()));
        if (!pseudosJoueurs.isValidNicknames()) {
            pseudosJoueurs = new Nicknames(_lg);
            pseudosJoueurs.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(_list),FileConst.PLAYERS),_list.getStreams());
        }

    }
    public void manageSoft(GroupFrame _inst,WindowCardsInt _cards, String _key) {
        DialogSoft.initDialogSoft(_inst.getMessages().getVal(_key), _cards);
        DialogSoft.setDialogSoft(_key, _cards);
        parametres=DialogSoft.getParametres(dialogSoft);
        parametres.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(_cards.getFrames()),FileConst.PARAMS),_cards.getStreams());
        containerGame.setSettings(parametres);
    }

    public void changerNombreDePartiesEnQuittant(GroupFrame _inst) {
        String fileName_ = StringUtil.concat(LaunchingCards.getTempFolderSl(_inst.getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,FileConst.DECK_FILE);
        String content_ = StreamTextFile.contentsOfFile(fileName_,_inst.getFileCoreStream(),_inst.getStreams());
        StringList vl_=new StringList();
        boolean read_ = true;
        StringList lines_ = new StringList();
        if (content_ != null) {
            lines_.addAllElts(StringUtil.splitChars(content_, LINE_RETURN));
        } else {
            read_ = false;
        }
        int total_ = GameEnum.values().length;
        if (lines_.size() < total_) {
            read_ = false;
        }
        if (read_) {
            for (int indice_ = IndexConstants.FIRST_INDEX; indice_<total_; indice_++) {
                vl_.add(lines_.get(indice_));
            }
        } else {
            vl_=new StringList();
            for (int indice_ = IndexConstants.FIRST_INDEX; indice_ < total_; indice_++) {
                vl_.add("0");
            }
        }
        //Si l'action de battre les cartes est faite a chaque lancement
        //de logiciel alors le nombre de parties est remis a zero lors
        //d'une fermeture de logiciel

        if(reglesPresident.getCommon().getMixedCards() == MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(GameEnum.PRESIDENT.ordinal(), "0");
        }
        if(reglesBelote.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(GameEnum.BELOTE.ordinal(), "0");
        }
        if(reglesTarot.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(GameEnum.TAROT.ordinal(), "0");
        }
        StreamTextFile.saveTextFile(fileName_, StringUtil.join(vl_, LINE_RETURN),_inst.getStreams());
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
        StreamLanguageUtil.saveLanguage(LaunchingCards.getTempFolder(_inst.getFrames()), value_,infos_.getStreams());
    }
    public void displayingGame(WindowCardsInt _inst,GameEnum _game) {
        String lg_ = _inst.getLanguageKey();
        if (_game == GameEnum.BELOTE) {
            DialogDisplayingBelote.setDialogDisplayingBelote(_game.toString(lg_), _inst);
            displayingBelote=DialogDisplayingBelote.getDisplaying(_inst.getDialogDisplayingBelote());
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_inst.getFrames()),FileConst.DISPLAY_BELOTE), DocumentWriterBeloteUtil.setDisplayingBelote(displayingBelote),_inst.getStreams());
            containerGame.setDisplayingBelote(displayingBelote);
        } else if (_game == GameEnum.PRESIDENT) {
            DialogDisplayingPresident.setDialogDisplayingPresident(_game.toString(lg_), _inst);
            displayingPresident=DialogDisplayingPresident.getDisplaying(_inst.getDialogDisplayingPresident());
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_inst.getFrames()),FileConst.DISPLAY_PRESIDENT), DocumentWriterPresidentUtil.setDisplayingPresident(displayingPresident),_inst.getStreams());
            containerGame.setDisplayingPresident(displayingPresident);
        } else if (_game == GameEnum.TAROT) {
            DialogDisplayingTarot.setDialogDisplayingTarot(_game.toString(lg_), _inst);
            displayingTarot=DialogDisplayingTarot.getDisplaying(_inst.getDialogDisplayingTarot());
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(_inst.getFrames()),FileConst.DISPLAY_TAROT), DocumentWriterTarotUtil.setDisplayingTarot(displayingTarot),_inst.getStreams());
            containerGame.setDisplayingTarot(displayingTarot);
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

    public Nicknames getPseudosJoueurs() {
        return pseudosJoueurs;
    }

    public void setPseudosJoueurs(Nicknames _p) {
        this.pseudosJoueurs = _p;
    }

    public RulesBelote getReglesBelote() {
        return reglesBelote;
    }

    public void setReglesBelote(RulesBelote _r) {
        this.reglesBelote = _r;
    }

    public DisplayingBelote getDisplayingBelote() {
        return displayingBelote;
    }
    public RulesPresident getReglesPresident() {
        return reglesPresident;
    }

    public void setReglesPresident(RulesPresident _r) {
        this.reglesPresident = _r;
    }

    public DisplayingPresident getDisplayingPresident() {
        return displayingPresident;
    }
    public RulesTarot getReglesTarot() {
        return reglesTarot;
    }

    public void setReglesTarot(RulesTarot _r) {
        this.reglesTarot = _r;
    }

    public DisplayingTarot getDisplayingTarot() {
        return displayingTarot;
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

    public StringMap<StringMap<String>> getImages() {
        return images;
    }
    public void setImages(StringMap<StringMap<String>> _i) {
        this.images = _i;
    }
    public Clock getClock() {
        return clock;
    }

    public SoftParams getParametres() {
        return parametres;
    }

    public ContainerGame getContainerGame() {
        return containerGame;
    }

    public void setContainerGame(ContainerGame _c) {
        this.containerGame = _c;
    }
}
