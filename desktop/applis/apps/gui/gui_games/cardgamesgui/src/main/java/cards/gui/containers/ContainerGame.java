package cards.gui.containers;

import cards.belote.DisplayingBelote;
import cards.belote.RulesBelote;
import cards.facade.FacadeCards;
import cards.facade.Games;
import cards.facade.Nicknames;
import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.animations.CardAnimState;
import cards.gui.panels.Carpet;
import cards.gui.panels.MiniCarpet;
import cards.president.DisplayingPresident;
import cards.president.RulesPresident;
import cards.tarot.DisplayingTarot;
import cards.tarot.RulesTarot;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
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
//    private final AbstractAtomicBoolean pause;
    private AbsTextArea events;
    private MiniCarpet mini;
    /**Est vrai si et seulement si le jeu est en pause*/
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
//    protected ContainerGame(WindowCards _window) {
////        pseudosJoueurs=new Nicknames(_window.getLanguageKey());
////        pause = _window.getThreadFactory().newAtomicBoolean();
//
////        setWindow(_window);
//        setParametres(_window.getParametresLogiciel());
//        setReglesTarot(_window.getReglesTarot());
//        setReglesPresident(_window.getReglesPresident());
//        setReglesBelote(_window.getReglesBelote());
//        setDisplayingBelote(_window.getDisplayingBelote());
//        setDisplayingPresident(_window.getDisplayingPresident());
//        setDisplayingTarot(_window.getDisplayingTarot());
//        setPseudosJoueurs(_window.getPseudosJoueurs());
//
//        setMessages(_window.getMessages());
//    }
    protected ContainerGame() {
    }
    protected ContainerGame(ContainerNoGame _window) {
//        pseudosJoueurs=new Nicknames(_window.getLanguageKey());
//        pause = _window.getThreadFactory().newAtomicBoolean();

//        setWindow(_window);
        setParametres(_window.getParametres());
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
        String fileName_ = StringUtil.concat(WindowCards.getTempFolderSl(_tmpUserFolderSl),FacadeCards.DECK_FOLDER,StreamTextFile.SEPARATEUR,FacadeCards.DECK_FILE);
        String content_ = StreamTextFile.contentsOfFile(fileName_,_tmpUserFolderSl.getFileCoreStream(),_tmpUserFolderSl.getStreams());
        StringList vl_= FacadeCards.retrieveLines(content_);
        //Si l'action de battre les cartes est faite a chaque lancement
        //de logiciel alors le nombre de parties est remis a zero lors
        //d'une fermeture de logiciel
        vl_.set(_game.ordinal(), Long.toString(_nbGames + 1));
        StreamTextFile.saveTextFile(fileName_, StringUtil.join(vl_, LINE_RETURN),_tmpUserFolderSl.getStreams());
    }

    public void ajouterTexteDansZone(String _texte) {
        getEvents().append(_texte);
    }
    public boolean playingSingleGame() {
        return getPar().enCoursDePartie();
    }

    protected static long chargerNombreDeParties(GameEnum _jeu, AbstractProgramInfos _tmpUserFolderSl) {
        String fileName_ = StringUtil.concat(WindowCards.getTempFolderSl(_tmpUserFolderSl),FacadeCards.DECK_FOLDER,StreamTextFile.SEPARATEUR,FacadeCards.DECK_FILE);
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
    public void setMessages(StringMap<String> _messages) {
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
    public void setParametres(SoftParams _parametres) {
        parametres = _parametres;
    }
    public AbsTextArea getEvents() {
        return events;
    }
    public void setEvents(AbsTextArea _events) {
        events = _events;
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
    public void setPar(Games _par) {
        par = _par;
    }
    public RulesBelote getReglesBelote() {
        return reglesBelote;
    }
    public void setReglesBelote(RulesBelote _reglesBelote) {
        reglesBelote = _reglesBelote;
    }
    public RulesPresident getReglesPresident() {
        return reglesPresident;
    }
    public void setReglesPresident(RulesPresident _reglesPresident) {
        reglesPresident = _reglesPresident;
    }
    public RulesTarot getReglesTarot() {
        return reglesTarot;
    }
    public void setReglesTarot(RulesTarot _reglesTarot) {
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
    public Nicknames getPseudosJoueurs() {
        return pseudosJoueurs;
    }
    public void setPseudosJoueurs(Nicknames _pseudosJoueurs) {
        pseudosJoueurs = _pseudosJoueurs;
    }
    public void saveCurrentGame(String _file, TechStreams _tech) {
        getPar().sauvegarderPartieEnCours(_file, _tech);
    }
//    public AbsMenu getFile() {
//        return window.getFile();
//    }

//    public AbsMenuItem getLoad() {
//        return window.getLoad();
//    }

//    public AbsMenu getDeal() {
//        return window.getDeal();
//    }

//    public AbsMenuItem getMultiStop() {
//        return window.getMultiStop();
//    }

//    public AbsMenu getParameters() {
//        return window.getParameters();
//    }

//    public IdMap<GameEnum,AbsMenuItem> getRulesGames() {
//        return window.getRulesGames();
//    }

//    public AbsMenuItem getPlayers() {
//        return window.getPlayers();
//    }
//
//    public AbsMenuItem getLaunching() {
//        return window.getLaunching();
//    }
//
//    public AbsMenuItem getTiming() {
//        return window.getTiming();
//    }
//
//    public AbsMenuItem getInteract() {
//        return window.getInteract();
//    }

//    public AbsMenuItem getLanguage() {
//        return window.getLanguage();
//    }
//
//    public AbsMenu getDisplaying() {
//        return window.getDisplaying();
//    }

//    public IdMap<GameEnum,AbsMenuItem> getDisplayingGames() {
//        return window.getDisplayingGames();
//    }

//    public void setOwner(Ownable _owner) {
//        window.setOwner(_owner);
//    }

    public CardAnimState getState() {
        return state;
    }

    public void setState(CardAnimState _s) {
        this.state = _s;
    }

}

