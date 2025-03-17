package cards.gui.containers;

import cards.belote.DisplayingBelote;
import cards.belote.RulesBelote;
import cards.facade.*;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.animations.CardAnimState;
import cards.gui.panels.Carpet;
import cards.gui.panels.MiniCarpet;
import cards.president.DisplayingPresident;
import cards.president.RulesPresident;
import cards.tarot.DisplayingTarot;
import cards.tarot.RulesTarot;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;
import code.util.core.IndexConstants;

public abstract class ContainerGame {

    public static final String INTRODUCTION_PTS = ":";

    public static final String EMPTY_STRING = "";
    public static final String RETURN_LINE="\n";
    public static final int USER_INSTANT = 0;
    public static final int END_GAME = 1;
    public static final int CLICK_TRICK = 2;
    static final String SPACE = " ";

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
    private final Games par = new Games();
//    private boolean carteEntree;
//    private boolean carteSortie;
//    private boolean threadAnime;
//    private boolean aJoueCarte;
//    private final AbstractAtomicBoolean pause;
    private AbsTextArea events;
    private MiniCarpet mini;
    /**Est vrai si et seulement si le jeu est en pause*/
    private CardAnimState state;
    /**Parametres de lancement, de jouerie*/
    private SoftParams parametres=new SoftParams();
//    private StringMap<String> messages = new StringMap<String>();
    private DisplayingBelote displayingBelote;
    private DisplayingPresident displayingPresident;
    private DisplayingTarot displayingTarot;
    private IntMap<AbsPlainLabel> handfuls = new IntMap<AbsPlainLabel>();
    private IntMap<AbsPanel> declaredHandfuls = new IntMap<AbsPanel>();
    private final Carpet tapis = new Carpet();
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
    protected ContainerGame(WindowCardsInt _window) {
//        pseudosJoueurs=new Nicknames(_window.getLanguageKey());
//        pause = _window.getThreadFactory().newAtomicBoolean();

//        setWindow(_window);
        setReglesTarot(_window.getReglesTarot());
        setReglesPresident(_window.getReglesPresident());
        setReglesBelote(_window.getReglesBelote());
        setDisplayingBelote(_window.getDisplayingBelote());
        setDisplayingPresident(_window.getDisplayingPresident());
        setDisplayingTarot(_window.getDisplayingTarot());
    }
    public void update(WindowCards _window) {
        setParametres(_window.getParametresLogiciel());
        setPseudosJoueurs(_window.getPseudosJoueurs());

//        setMessages(_window.getMessages());
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

    public AbsPanel buildDeclHands(int _nbPlayers, CustList<String> _pseudos, AbstractProgramInfos _api) {
        initHandfuls();
        AbsPanel handfuls_ = _api.getCompoFactory().newGrid();
        for (int i = IndexConstants.FIRST_INDEX; i<_nbPlayers; i++) {
            AbsPlainLabel lab_ = _api.getCompoFactory().newPlainLabel(_pseudos.get(i));
            lab_.left();
            Carpet.add(_api.getCompoFactory(),handfuls_,lab_,false);
            AbsPlainLabel handful_ = _api.getCompoFactory().newPlainLabel(EMPTY_STRING);
            Carpet.add(_api.getCompoFactory(),handfuls_,handful_,false);
            getHandfuls().put(i, handful_);
            AbsPanel declaredHandful_ = _api.getCompoFactory().newLineBox();
            Carpet.add(_api.getCompoFactory(),handfuls_,declaredHandful_,true);
            getDeclaredHandfuls().put(i, declaredHandful_);
        }
        return handfuls_;
    }

    public void initHandfuls() {
        setHandfuls(new IntMap<AbsPlainLabel>());
        setDeclaredHandfuls(new IntMap<AbsPanel>());
    }
    protected static void changerNombreDeParties(GameEnum _game, long _nbGames, AbstractProgramInfos _tmpUserFolderSl, int _nbStacks) {
        FacadeCards.changerNombreDeParties(_game,_nbGames,WindowCards.getTempFolderSl(_tmpUserFolderSl),_tmpUserFolderSl, _nbStacks);
    }

    public void ajouterTexteDansZone(String _texte) {
        getEvents().append(_texte);
    }
    public boolean playingSingleGame() {
        return getPar().enCoursDePartie();
    }

    protected static long chargerNombreDeParties(GameEnum _jeu, AbstractProgramInfos _tmpUserFolderSl, int _nbStacks) {
        return FacadeCards.chargerNombreDeParties(_jeu,WindowCards.getTempFolderSl(_tmpUserFolderSl),_tmpUserFolderSl,_nbStacks);
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

//    public boolean isCarteEntree() {
//        return carteEntree;
//    }
//    public void setCarteEntree(boolean _carteEntree) {
//        carteEntree = _carteEntree;
//    }
//    public boolean isCarteSortie() {
//        return carteSortie;
//    }
//    public void setCarteSortie(boolean _carteSortie) {
//        carteSortie = _carteSortie;
//    }
    public IntMap<AbsPanel> getDeclaredHandfuls() {
        return declaredHandfuls;
    }
    public void setDeclaredHandfuls(IntMap<AbsPanel> _declaredHandfuls) {
        declaredHandfuls = _declaredHandfuls;
    }
//    public StringMap<String> getMessages() {
//        return messages;
//    }
//    public void setMessages(StringMap<String> _messages) {
//        messages = _messages;
//    }
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

    public IntMap<AbsPlainLabel> getHandfuls() {
        return handfuls;
    }
    public void setHandfuls(IntMap<AbsPlainLabel> _handfuls) {
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

//    public boolean isThreadAnime() {
//        return threadAnime;
//    }
//    public void setThreadAnime(boolean _threadAnime) {
//        threadAnime = _threadAnime;
//    }
    public Games getPar() {
        return par;
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
    public void panelHand(AbsPanel _panelHand) {
        _panelHand.setBackground(GuiConstants.BLUE);
        _panelHand.validate();
        setPanelHand(_panelHand);
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
    public void saveCurrentGame(CardGamesStream _cs, String _file) {
        getPar().sauvegarderPartieEnCours(_cs,_file);
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

