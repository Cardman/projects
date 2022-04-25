package cards.gui.containers;

import cards.belote.DisplayingBelote;
import cards.belote.RulesBelote;
import cards.consts.CoreResourcesAccess;
import cards.facade.Games;
import cards.facade.Nicknames;
import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.animations.CardAnimState;
import cards.gui.dialogs.FileConst;
import cards.gui.panels.Carpet;
import cards.gui.panels.MiniCarpet;
import cards.main.LaunchingCards;
import cards.president.DisplayingPresident;
import cards.president.RulesPresident;
import cards.tarot.DisplayingTarot;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.ChoiceTarot;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesCommonCommon;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class ContainerGame implements Containable {

    public static final String INTRODUCTION_PTS = ":";

    public static final String EMPTY_STRING = "";
    public static final String RETURN_LINE="\n";
    static final String SPACE = " ";

    private static final char LINE_RETURN = '\n';
    private AbsPanel actionsHistory;
    private AbsPanel panneauBoutonsJeu;
    private AbsPanel panelHand;
    private WindowCards window;
    /**Parametres d'informations sur
    des pseudonymes*/
    private Nicknames pseudosJoueurs;
    private RulesTarot reglesTarot=new RulesTarot();
    private RulesPresident reglesPresident=new RulesPresident();
    private RulesBelote reglesBelote=new RulesBelote();
    /**La partie actuellement jouee*/
    private Games par = new Games();
    private boolean carteEntree;
    private boolean carteSortie;
    private String raisonCourante=EMPTY_STRING;
    private boolean threadAnime;
    private boolean aJoueCarte;
    private final AbstractAtomicBoolean pause;
    private AbsTextArea events;
    private MiniCarpet mini;
    /**Est vrai si et seulement si le jeu est en pause*/
    private final AbstractAtomicBoolean passe;
    private CardAnimState state;
    /**Parametres de lancement, de jouerie*/
    private SoftParams parametres=new SoftParams();
    private StringMap<String> messages = new StringMap<String>();
    private DisplayingBelote displayingBelote;
    private DisplayingPresident displayingPresident;
    private DisplayingTarot displayingTarot;
    private ByteMap<AbsPlainLabel> handfuls = new ByteMap<AbsPlainLabel>();
    private ByteMap<AbsPanel> declaredHandfuls = new ByteMap<AbsPanel>();
    private Carpet tapis = new Carpet();
    private boolean changerPileFin;
    public ContainerGame(WindowCards _window) {
        pseudosJoueurs=new Nicknames(_window.getLanguageKey());
        pause = _window.getThreadFactory().newAtomicBoolean();
        passe = _window.getThreadFactory().newAtomicBoolean();
        setWindow(_window);
        setParametres(_window.getParametresLogiciel());
        setReglesTarot(_window.getReglesTarot());
        setReglesPresident(_window.getReglesPresident());
        setReglesBelote(_window.getReglesBelote());
        setDisplayingBelote(_window.getDisplayingBelote());
        setDisplayingPresident(_window.getDisplayingPresident());
        setDisplayingTarot(_window.getDisplayingTarot());
        setPseudosJoueurs(_window.getPseudosJoueurs());

        setMessages(_window.getMessages());
    }

    @Override
    public boolean isSimple() {
        return true;
    }

//    public void pause() {
//        if(isPasse()) {
//            pause.set(!pause.get());
//        }
//        while(pause.get()) {
//            if(!isPasse()) {
//                pause.set(!pause.get());
//            }
//        }
//    }

    protected static void changerNombreDeParties(GameEnum _game, long _nbGames, AbstractProgramInfos _tmpUserFolderSl) {
        String fileName_ = StringUtil.concat(LaunchingCards.getTempFolderSl(_tmpUserFolderSl),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,FileConst.DECK_FILE);
        String content_ = StreamTextFile.contentsOfFile(fileName_,_tmpUserFolderSl.getFileCoreStream(),_tmpUserFolderSl.getStreams());
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
        vl_.set(_game.ordinal(), Long.toString(_nbGames + 1));
        StreamTextFile.saveTextFile(fileName_, StringUtil.join(vl_, LINE_RETURN),_tmpUserFolderSl.getStreams());
    }

    public void ajouterTexteDansZone(String _texte) {
        getEvents().append(_texte);
    }

    public void revalidate() {
        getWindow().revalidateFrame();
    }

    public void pack() {
        getWindow().pack();
    }

    public final WindowCards getOwner() {
        return getWindow();
    }
    protected AbsPanel getPane() {
        return getWindow().getPane();
    }
    public void setContentPane(AbsPanel _container) {
        getWindow().setContentPane(_container);
    }
    public void saveCurrentGame(String _file) {
        getPar().sauvegarderPartieEnCours(_file, getWindow().getStreams());
    }
    public boolean playingSingleGame() {
        return getPar().enCoursDePartie();
    }

    protected static long chargerNombreDeParties(GameEnum _jeu, AbstractProgramInfos _tmpUserFolderSl) {
        String fileName_ = StringUtil.concat(LaunchingCards.getTempFolderSl(_tmpUserFolderSl),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,FileConst.DECK_FILE);
        String content_ = StreamTextFile.contentsOfFile(fileName_,_tmpUserFolderSl.getFileCoreStream(),_tmpUserFolderSl.getStreams());
        if (content_ == null) {
            return 0L;
        }
        StringList lines_ = StringUtil.splitChars(content_, LINE_RETURN);
        lines_.removeAllString(EMPTY_STRING);
        return NumberUtil.parseLongZero(lines_.get(_jeu.ordinal()));
    }
    public void setNicknames(Nicknames _pseudosJoueurs) {
        setPseudosJoueurs(_pseudosJoueurs);
    }
    public void setDisplayingBelote(DisplayingBelote _displayingBelote) {
        displayingBelote = _displayingBelote;
    }
    public void setDisplayingPresident(DisplayingPresident _displayingPresident) {
        displayingPresident = _displayingPresident;
    }
    public void setDisplayingTarot(DisplayingTarot _displayingTarot) {
        displayingTarot = _displayingTarot;
    }
    public void setSettings(SoftParams _parametres) {
        setParametres(_parametres);
    }
    public void setRulesBelote(RulesBelote _reglesBelote) {
        setReglesBelote(_reglesBelote);
    }
    public void setRulesPresident(RulesPresident _reglesTarot) {
        setReglesPresident(_reglesTarot);
    }
    public void setRulesTarot(RulesTarot _reglesTarot) {
        setReglesTarot(_reglesTarot);
    }

    public void finirParties() {
        getPar().finirParties();
    }
    public boolean isaJoueCarte() {
        return aJoueCarte;
    }
    public void setaJoueCarte(boolean _aJoueCarte) {
        aJoueCarte = _aJoueCarte;
    }
    public boolean isCarteEntree() {
        return carteEntree;
    }
    public void setCarteEntree(boolean _carteEntree) {
        carteEntree = _carteEntree;
    }
    public boolean isCarteSortie() {
        return carteSortie;
    }
    public void setCarteSortie(boolean _carteSortie) {
        carteSortie = _carteSortie;
    }
    public ByteMap<AbsPanel> getDeclaredHandfuls() {
        return declaredHandfuls;
    }
    public void setDeclaredHandfuls(ByteMap<AbsPanel> _declaredHandfuls) {
        declaredHandfuls = _declaredHandfuls;
    }
    public StringMap<String> getMessages() {
        return messages;
    }
    protected void setMessages(StringMap<String> _messages) {
        messages = _messages;
    }
    public DisplayingBelote getDisplayingBelote() {
        return displayingBelote;
    }
    public DisplayingTarot getDisplayingTarot() {
        return displayingTarot;
    }
    public DisplayingPresident getDisplayingPresident() {
        return displayingPresident;
    }
    public Carpet getTapis() {
        return tapis;
    }
    protected void setTapis(Carpet _tapis) {
        tapis = _tapis;
    }
    public ByteMap<AbsPlainLabel> getHandfuls() {
        return handfuls;
    }
    public void setHandfuls(ByteMap<AbsPlainLabel> _handfuls) {
        handfuls = _handfuls;
    }
    protected boolean isChangerPileFin() {
        return changerPileFin;
    }
    public void setChangerPileFin(boolean _changerPileFin) {
        changerPileFin = _changerPileFin;
    }
    public AbsPanel getMiniPanel() {
        return mini.getContainer();
    }
    public MiniCarpet getMini() {
        return mini;
    }
    public void setMini(MiniCarpet _mini) {
        mini = _mini;
    }
    public SoftParams getParametres() {
        return parametres;
    }
    protected void setParametres(SoftParams _parametres) {
        parametres = _parametres;
    }
    public AbsTextArea getEvents() {
        return events;
    }
    public void setEvents(AbsTextArea _events) {
        events = _events;
    }
    public boolean isPasse() {
        return passe.get();
    }
    public void setPasse(boolean _passe) {
        passe.set(_passe);
    }
    public String getRaisonCourante() {
        return raisonCourante;
    }
    public void setRaisonCourante(String _raisonCourante) {
        raisonCourante = _raisonCourante;
    }
    public boolean isThreadAnime() {
        return threadAnime;
    }
    public void setThreadAnime(boolean _threadAnime) {
        threadAnime = _threadAnime;
    }
    public Games getPar() {
        return par;
    }
    protected void setPar(Games _par) {
        par = _par;
    }
    protected RulesBelote getReglesBelote() {
        return reglesBelote;
    }
    protected void setReglesBelote(RulesBelote _reglesBelote) {
        reglesBelote = _reglesBelote;
    }
    protected RulesPresident getReglesPresident() {
        return reglesPresident;
    }
    protected void setReglesPresident(RulesPresident _reglesPresident) {
        reglesPresident = _reglesPresident;
    }
    protected RulesTarot getReglesTarot() {
        return reglesTarot;
    }
    protected void setReglesTarot(RulesTarot _reglesTarot) {
        reglesTarot = _reglesTarot;
    }
    public AbsPanel getPanelHand() {
        return panelHand;
    }
    public void setPanelHand(AbsPanel _panelHand) {
        panelHand = _panelHand;
    }

    public AbsPanel getActionsHistory() {
        return actionsHistory;
    }

    public void setActionsHistory(AbsPanel _actionsHistory) {
        actionsHistory = _actionsHistory;
    }

    public AbsPanel getPanneauBoutonsJeu() {
        return panneauBoutonsJeu;
    }
    public void setPanneauBoutonsJeu(AbsPanel _panneauBoutonsJeu) {
        panneauBoutonsJeu = _panneauBoutonsJeu;
    }
    public WindowCards getWindow() {
        return window;
    }
    protected void setWindow(WindowCards _window) {
        window = _window;
    }
    public Nicknames getPseudosJoueurs() {
        return pseudosJoueurs;
    }
    protected void setPseudosJoueurs(Nicknames _pseudosJoueurs) {
        pseudosJoueurs = _pseudosJoueurs;
    }

    public AbsMenu getFile() {
        return window.getFile();
    }

    public AbsMenuItem getLoad() {
        return window.getLoad();
    }

    public AbsMenuItem getSave() {
        return window.getSave();
    }

    public AbsMenuItem getChange() {
        return window.getChange();
    }

    public AbsMenuItem getExit() {
        return window.getExit();
    }

    public AbsMenu getDeal() {
        return window.getDeal();
    }

    public AbsMenuItem getConsulting() {
        return window.getConsulting();
    }

    public AbsCheckBoxMenuItem getPause() {
        return window.getPause();
    }

    public AbsMenuItem getHelpGame() {
        return window.getHelpGame();
    }

    public AbsMenuItem getTricksHands() {
        return window.getTricksHands();
    }

    public AbsMenuItem getTeams() {
        return window.getTeams();
    }

    public AbsMenu getEdit() {
        return window.getEdit();
    }

    public EnumMap<GameEnum,AbsMenuItem> getEditGames() {
        return window.getEditGames();
    }

    public AbsMenu getDemo() {
        return window.getDemo();
    }

    public EnumMap<GameEnum,AbsMenuItem> getDemoGames() {
        return window.getDemoGames();
    }

    public AbsMenu getTraining() {
        return window.getTraining();
    }

    public EnumMap<ChoiceTarot,AbsMenuItem> getTrainingTarot() {
        return window.getTrainingTarot();
    }

    public AbsMenuItem getMultiStop() {
        return window.getMultiStop();
    }

    public AbsMenu getParameters() {
        return window.getParameters();
    }

    public EnumMap<GameEnum,AbsMenuItem> getRulesGames() {
        return window.getRulesGames();
    }

    public AbsMenuItem getPlayers() {
        return window.getPlayers();
    }

    public AbsMenuItem getLaunching() {
        return window.getLaunching();
    }

    public AbsMenuItem getTiming() {
        return window.getTiming();
    }

    public AbsMenuItem getInteract() {
        return window.getInteract();
    }

    public AbsMenuItem getLanguage() {
        return window.getLanguage();
    }

    public AbsMenu getDisplaying() {
        return window.getDisplaying();
    }

    public EnumMap<GameEnum,AbsMenuItem> getDisplayingGames() {
        return window.getDisplayingGames();
    }

    public AbsMenu getHelp() {
        return window.getHelp();
    }

    public AbsMenuItem getGeneralHelp() {
        return window.getGeneralHelp();
    }

    public void setOwner(Ownable _owner) {
        window.setOwner(_owner);
    }

    public String readCoreResource() {
        return MessagesCommonCommon.ms().getVal(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/",CoreResourcesAccess.NOM_FICHIER));
//        return ResourceFiles.ressourceFichier(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR,CoreResourcesAccess.NOM_FICHIER));
    }

    public CardAnimState getState() {
        return state;
    }

    public void setState(CardAnimState _s) {
        this.state = _s;
    }

    public void thread(Runnable _animContratBelote) {
        getOwner().getThreadFactory().newStartedThread(_animContratBelote);
    }

}

