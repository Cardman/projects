package cards.gui.containers;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.gui.animations.LoadingVideo;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicTarotCard;
import cards.gui.panels.CarpetTarot;
import cards.main.LaunchingCards;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.datacheck.ObjectComponents;
import code.gui.LabelButton;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.Numbers;
import code.util.StringList;

public class ContainerTarot extends ContainerGame{

    public static final String EMPTY="";
    public static final char RETURN_LINE_CHAR='\n';
    protected static final String TAB="\t";
    /**Renvoie tous les scores de toutes les parties non solitaires*/
    private CustList<Numbers<Long>> scores=new CustList<Numbers<Long>>();
    /**Maximum des valeurs absolues des scores centr&eacute;s par rapport &agrave; la moyenne*/
    private long maxAbsoluScore;
    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
    private boolean partieSauvegardee;
    /**Vrai si et seulement si au moins une partie aleatoire a ete jouee depuis le dernier passage dans le menu principal*/
    private boolean partieAleatoireJouee;
    private LoadingVideo animChargement;
    private volatile boolean arretDemo;
    private boolean canBid;
    private boolean canCall;
    private boolean canDiscard;
    private boolean canExcludeTrumps;
    private boolean canPlay;
    private boolean discardCall;
    /**Carte survol&eacute;e par la souris*/
    private JPanel panelDiscardedTrumps;
    private ButtonGroup listHandfuls = new ButtonGroup();
    private HandTarot currentIncludedTrumps = new HandTarot();
    private HandTarot currentExcludedTrumps = new HandTarot();
    private JScrollPane scrollDeclaringHandful;
    private JSplitPane declaringHandful;
    private JPanel includedTrumpsForHandful;
    private JPanel excludedTrumpsForHandful;
    private EnumMap<Miseres,Boolean> selectedMiseres = new EnumMap<Miseres,Boolean>();
    private JScrollPane scrollCallableCards;
    private JPanel panelCallableCards;
    private Handfuls choosenHandful = Handfuls.NO;
    private CardTarot carteSurvoleeTarot;
    private JTextArea infoCurrentHandful;
    private LabelButton validateDog;
    private LabelButton slamButton;


    ContainerTarot(MainWindow _window) {
        super(_window);
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    public StringList pseudosTarot(byte _nbPlayers) {
        StringList pseudosTwo_=new StringList();
        pseudosTwo_.add(pseudo());
        StringList pseudos_ = getPseudosJoueurs().getPseudosTarot();
        pseudosTwo_.addAllElts(pseudos_.mid(0, _nbPlayers - 1));
        return pseudosTwo_;
    }
    public String pseudo() {
        return getPseudosJoueurs().getPseudo();
    }
    /**Permet de charger une main de distribution
    a partir d'un fichier*/
    protected static HandTarot chargerPileTarot() {
        HandTarot pile_=(HandTarot)StreamTextFile.loadObject(LaunchingCards.getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+GameEnum.TAROT.name()+FileConst.DECK_EXT);
        ObjectComponents.checkObjectNotNull(pile_);
        return pile_;
    }

    public static CustList<GraphicTarotCard> getGraphicCards(Iterable<CardTarot> _hand) {
        CustList<GraphicTarotCard> list_;
        list_ = new CustList<GraphicTarotCard>();
        boolean entered_ = false;
        for(CardTarot c: _hand) {
            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
            carte_.setPreferredSize(entered_);
            list_.add(carte_);
            entered_ = true;
        }
        return list_;
    }

    public CarpetTarot tapisTarot() {
        return getTapis().getTapisTarot();
    }
    public void annonceTarotChelem() {
    }
    public void prendreCartesChien() {
    }
    public void validateDog() {
    }
    protected LoadingVideo getAnimChargement() {
        return animChargement;
    }
    protected void setAnimChargement(LoadingVideo _animChargement) {
        animChargement = _animChargement;
    }
    public JPanel getPanelDiscardedTrumps() {
        return panelDiscardedTrumps;
    }
    public void setPanelDiscardedTrumps(JPanel _panelDiscardedTrumps) {
        panelDiscardedTrumps = _panelDiscardedTrumps;
    }
    public boolean isCanDiscard() {
        return canDiscard;
    }
    public void setCanDiscard(boolean _canDiscard) {
        canDiscard = _canDiscard;
    }
    public boolean isCanBid() {
        return canBid;
    }
    public void setCanBid(boolean _canBid) {
        canBid = _canBid;
    }
    public boolean isDiscardCall() {
        return discardCall;
    }
    protected void setDiscardCall(boolean _discardCall) {
        discardCall = _discardCall;
    }
    public boolean isCanCall() {
        return canCall;
    }
    public void setCanCall(boolean _canCall) {
        canCall = _canCall;
    }
    public boolean isCanExcludeTrumps() {
        return canExcludeTrumps;
    }
    protected void setCanExcludeTrumps(boolean _canExcludeTrumps) {
        canExcludeTrumps = _canExcludeTrumps;
    }
    public HandTarot getCurrentIncludedTrumps() {
        return currentIncludedTrumps;
    }
    protected void setCurrentIncludedTrumps(HandTarot _currentIncludedTrumps) {
        currentIncludedTrumps = _currentIncludedTrumps;
    }
    public HandTarot getCurrentExcludedTrumps() {
        return currentExcludedTrumps;
    }
    protected void setCurrentExcludedTrumps(HandTarot _currentExcludedTrumps) {
        currentExcludedTrumps = _currentExcludedTrumps;
    }
    public Handfuls getChoosenHandful() {
        return choosenHandful;
    }
    public void setChoosenHandful(Handfuls _choosenHandful) {
        choosenHandful = _choosenHandful;
    }
    protected ButtonGroup getListHandfuls() {
        return listHandfuls;
    }
    protected void setListHandfuls(ButtonGroup _listHandfuls) {
        listHandfuls = _listHandfuls;
    }
    public JScrollPane getScrollDeclaringHandful() {
        return scrollDeclaringHandful;
    }
    protected void setScrollDeclaringHandful(JScrollPane _scrollDeclaringHandful) {
        scrollDeclaringHandful = _scrollDeclaringHandful;
    }
    protected JPanel getIncludedTrumpsForHandful() {
        return includedTrumpsForHandful;
    }
    protected void setIncludedTrumpsForHandful(JPanel _includedTrumpsForHandful) {
        includedTrumpsForHandful = _includedTrumpsForHandful;
    }
    protected JPanel getExcludedTrumpsForHandful() {
        return excludedTrumpsForHandful;
    }
    protected void setExcludedTrumpsForHandful(JPanel _excludedTrumpsForHandful) {
        excludedTrumpsForHandful = _excludedTrumpsForHandful;
    }
    protected JSplitPane getDeclaringHandful() {
        return declaringHandful;
    }
    protected void setDeclaringHandful(JSplitPane _declaringHandful) {
        declaringHandful = _declaringHandful;
    }
    protected long getMaxAbsoluScore() {
        return maxAbsoluScore;
    }
    protected void setMaxAbsoluScore(long _maxAbsoluScore) {
        maxAbsoluScore = _maxAbsoluScore;
    }
    public boolean isCanPlay() {
        return canPlay;
    }
    public void setCanPlay(boolean _canPlay) {
        canPlay = _canPlay;
    }
    protected JScrollPane getScrollCallableCards() {
        return scrollCallableCards;
    }
    protected void setScrollCallableCards(JScrollPane _scrollCallableCards) {
        scrollCallableCards = _scrollCallableCards;
    }
    protected boolean isPartieAleatoireJouee() {
        return partieAleatoireJouee;
    }
    protected void setPartieAleatoireJouee(boolean _partieAleatoireJouee) {
        partieAleatoireJouee = _partieAleatoireJouee;
    }
    protected JPanel getPanelCallableCards() {
        return panelCallableCards;
    }
    protected void setPanelCallableCards(JPanel _panelCallableCards) {
        panelCallableCards = _panelCallableCards;
    }
    protected boolean isPartieSauvegardee() {
        return partieSauvegardee;
    }
    protected void setPartieSauvegardee(boolean _partieSauvegardee) {
        partieSauvegardee = _partieSauvegardee;
    }
    public CustList<Numbers<Long>> getScores() {
        return scores;
    }
    protected void setScores(CustList<Numbers<Long>> _scores) {
        scores = _scores;
    }
    public EnumList<Miseres> getAllowedMiseres() {
        EnumList<Miseres> l_;
        l_ = new EnumList<Miseres>();
        for (EntryCust<Miseres,Boolean> e: selectedMiseres.entryList()) {
            if (e.getValue()) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }
    public EnumMap<Miseres,Boolean> getSelectedMiseres() {
        return selectedMiseres;
    }
    protected void setSelectedMiseres(EnumMap<Miseres,Boolean> _selectedMiseres) {
        selectedMiseres = _selectedMiseres;
    }
    public boolean isArretDemo() {
        return arretDemo;
    }
    public void setArretDemo(boolean _arretDemo) {
        arretDemo = _arretDemo;
    }

    public CardTarot getCarteSurvoleeTarot() {
        return carteSurvoleeTarot;
    }
    public void setCarteSurvoleeTarot(CardTarot _carteSurvoleeTarot) {
        carteSurvoleeTarot = _carteSurvoleeTarot;
    }
    public JTextArea getInfoCurrentHandful() {
        return infoCurrentHandful;
    }
    public void setInfoCurrentHandful(JTextArea _infoCurrentHandful) {
        infoCurrentHandful = _infoCurrentHandful;
    }
    public LabelButton getValidateDog() {
        return validateDog;
    }
    protected void setValidateDog(LabelButton _validateDog) {
        validateDog = _validateDog;
    }
    public LabelButton getSlamButton() {
        return slamButton;
    }
    protected void setSlamButton(LabelButton _slamButton) {
        slamButton = _slamButton;
    }
}
