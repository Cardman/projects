package cards.gui.containers;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingConstants;

import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.gui.animations.PreparedPagesCards;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicTarotCard;
import cards.gui.panels.CarpetTarot;
import cards.main.LaunchingCards;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.TarotResoucesAccess;
import cards.tarot.sml.DocumentReaderTarotUtil;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesTarotTarot;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.core.StringUtil;

public class ContainerTarot extends ContainerGame{

    public static final String EMPTY="";
    protected static final String TAB="\t";
    /**Renvoie tous les scores de toutes les parties non solitaires*/
    private CustList<Longs> scores=new CustList<Longs>();
    /**Maximum des valeurs absolues des scores centr&eacute;s par rapport &agrave; la moyenne*/
    private long maxAbsoluScore;
    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
    private boolean partieSauvegardee;
    /**Vrai si et seulement si au moins une partie aleatoire a ete jouee depuis le dernier passage dans le menu principal*/
    private boolean partieAleatoireJouee;
    private final AtomicBoolean arretDemo = new AtomicBoolean();
    private boolean canBid;
    private boolean canCall;
    private boolean canDiscard;
    private boolean canExcludeTrumps;
    private boolean canPlay;
    private boolean discardCall;
    /**Carte survol&eacute;e par la souris*/
    private Panel panelDiscardedTrumps;
    private HandTarot currentIncludedTrumps = new HandTarot();
    private HandTarot currentExcludedTrumps = new HandTarot();
    private ScrollPane scrollDeclaringHandful;
    private SplitPane declaringHandful;
    private Panel includedTrumpsForHandful;
    private Panel excludedTrumpsForHandful;
    private EnumMap<Miseres,Boolean> selectedMiseres = new EnumMap<Miseres,Boolean>();
    private ScrollPane scrollCallableCards;
    private Panel panelCallableCards;
    private Handfuls choosenHandful = Handfuls.NO;
    private CardTarot carteSurvoleeTarot;
    private TextArea infoCurrentHandful;
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
        pseudosTwo_.addAllElts(pseudos_.left(_nbPlayers - 1));
        return pseudosTwo_;
    }
    public String pseudo() {
        return getPseudosJoueurs().getPseudo();
    }
    /**Permet de charger une main de distribution
    a partir d'un fichier
     * @param _tmpUserFolderSl*/
    protected static HandTarot chargerPileTarot(AbstractProgramInfos _tmpUserFolderSl) {
        return DocumentReaderTarotUtil.getHandTarot(StreamTextFile.contentsOfFile(
                StringUtil.concat(LaunchingCards.getTempFolderSl(_tmpUserFolderSl),FileConst.DECK_FOLDER,
                        StreamTextFile.SEPARATEUR,GameEnum.TAROT.name(),FileConst.DECK_EXT),_tmpUserFolderSl.getFileCoreStream(), _tmpUserFolderSl.getStreams()));
    }

    public static CustList<GraphicTarotCard> getGraphicCards(String _lg, Iterable<CardTarot> _hand) {
        CustList<GraphicTarotCard> list_;
        list_ = new CustList<GraphicTarotCard>();
        boolean entered_ = false;
        for(CardTarot c: _hand) {
            GraphicTarotCard carte_=new GraphicTarotCard(_lg, c,SwingConstants.RIGHT,!entered_);
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
        //
    }
    public void prendreCartesChien() {
        //
    }
    public void validateDog() {
        //
    }
    public Panel getPanelDiscardedTrumps() {
        return panelDiscardedTrumps;
    }
    public void setPanelDiscardedTrumps(Panel _panelDiscardedTrumps) {
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

    public ScrollPane getScrollDeclaringHandful() {
        return scrollDeclaringHandful;
    }
    protected void setScrollDeclaringHandful(ScrollPane _scrollDeclaringHandful) {
        scrollDeclaringHandful = _scrollDeclaringHandful;
    }
    protected Panel getIncludedTrumpsForHandful() {
        return includedTrumpsForHandful;
    }
    protected void setIncludedTrumpsForHandful(Panel _includedTrumpsForHandful) {
        includedTrumpsForHandful = _includedTrumpsForHandful;
    }
    protected Panel getExcludedTrumpsForHandful() {
        return excludedTrumpsForHandful;
    }
    protected void setExcludedTrumpsForHandful(Panel _excludedTrumpsForHandful) {
        excludedTrumpsForHandful = _excludedTrumpsForHandful;
    }
    protected SplitPane getDeclaringHandful() {
        return declaringHandful;
    }
    protected void setDeclaringHandful(SplitPane _declaringHandful) {
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
    protected ScrollPane getScrollCallableCards() {
        return scrollCallableCards;
    }
    protected void setScrollCallableCards(ScrollPane _scrollCallableCards) {
        scrollCallableCards = _scrollCallableCards;
    }
    protected boolean isPartieAleatoireJouee() {
        return partieAleatoireJouee;
    }
    protected void setPartieAleatoireJouee(boolean _partieAleatoireJouee) {
        partieAleatoireJouee = _partieAleatoireJouee;
    }
    protected Panel getPanelCallableCards() {
        return panelCallableCards;
    }
    protected void setPanelCallableCards(Panel _panelCallableCards) {
        panelCallableCards = _panelCallableCards;
    }
    protected boolean isPartieSauvegardee() {
        return partieSauvegardee;
    }
    protected void setPartieSauvegardee(boolean _partieSauvegardee) {
        partieSauvegardee = _partieSauvegardee;
    }
    public CustList<Longs> getScores() {
        return scores;
    }
    protected void setScores(CustList<Longs> _scores) {
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
        return arretDemo.get();
    }
    public void setArretDemo(boolean _arretDemo) {
        arretDemo.set(_arretDemo);
    }

    public CardTarot getCarteSurvoleeTarot() {
        return carteSurvoleeTarot;
    }
    public void setCarteSurvoleeTarot(CardTarot _carteSurvoleeTarot) {
        carteSurvoleeTarot = _carteSurvoleeTarot;
    }
    public TextArea getInfoCurrentHandful() {
        return infoCurrentHandful;
    }
    public void setInfoCurrentHandful(TextArea _infoCurrentHandful) {
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
    public String readResource() {
        return MessagesTarotTarot.ms().getVal(StringUtil.concat(TarotResoucesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/", TarotResoucesAccess.NOM_FICHIER));
    }

    public PreparedPagesCards retrieve(String _conf) {
        return getOwner().getPreparedTarot().getVal(_conf).getVal(getOwner().getLanguageKey());
    }
}
