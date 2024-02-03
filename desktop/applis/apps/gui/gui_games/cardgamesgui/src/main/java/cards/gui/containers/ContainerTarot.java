package cards.gui.containers;



import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.labels.GraphicTarotCard;
import cards.gui.panels.CarpetTarot;
import cards.main.CardNatLgNamesNavigation;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractFutureParam;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.StringList;
import code.util.core.BoolVal;

public abstract class ContainerTarot extends ContainerSingleImpl{

    public static final String EMPTY="";
    protected static final String TAB="\t";
    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
    private boolean partieSauvegardee;
    /**Vrai si et seulement si au moins une partie aleatoire a ete jouee depuis le dernier passage dans le menu principal*/
    private boolean partieAleatoireJouee;
    private final AbstractAtomicBoolean arretDemo;
    private boolean canBid;
    private boolean canCall;
    private boolean canDiscard;
    private boolean canExcludeTrumps;
    private boolean canPlay;
    private boolean discardCall;
    /**Carte survol&eacute;e par la souris*/
    private AbsPanel panelDiscardedTrumps;
    private HandTarot currentIncludedTrumps = new HandTarot();
    private HandTarot currentExcludedTrumps = new HandTarot();
    private AbsScrollPane scrollDeclaringHandful;
    private AbsSplitPane declaringHandful;
    private AbsPanel includedTrumpsForHandful;
    private AbsPanel excludedTrumpsForHandful;
    private IdMap<Miseres,BoolVal> selectedMiseres = new IdMap<Miseres,BoolVal>();
    private AbsScrollPane scrollCallableCards;
    private AbsPanel panelCallableCards;
    private Handfuls choosenHandful = Handfuls.NO;
    private CardTarot carteSurvoleeTarot;
    private AbsTextArea infoCurrentHandful;
    private AbsButton validateDog;
    private AbsButton slamButton;


    ContainerTarot(WindowCardsInt _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicBoolean();
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
    a partir d'un fichier  */
    protected HandTarot chargerPileTarot() {
        return getOwner().baseWindow().getFacadeCards().getNicknamesCrud().getCardGamesCrud().tarot();
    }

    public static CustList<GraphicTarotCard> getGraphicCards(WindowCardsInt _fact, TranslationsLg _lg, CustList<CardTarot> _hand) {
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        CustList<GraphicTarotCard> list_;
        list_ = new CustList<GraphicTarotCard>();
        boolean entered_ = false;
        for(CardTarot c: _hand) {
            GraphicTarotCard carte_=new GraphicTarotCard(_fact.getFrames(),_lg, c, !entered_);
            carte_.setPreferredSize(entered_);
            int w_ = carte_.getWidth();
            int h_ = carte_.getHeight();
            AbstractImage img_ = imageFactory_.newImageArgb(w_, h_);
            img_.setFont(carte_.getPaintableLabel());
            carte_.paintComponent(img_);
            carte_.setIcon(imageFactory_,img_);
            list_.add(carte_);
            entered_ = true;
        }
        return list_;
    }

    public CarpetTarot tapisTarot() {
        return getTapis().getTapisTarot();
    }
    public AbsPanel getPanelDiscardedTrumps() {
        return panelDiscardedTrumps;
    }
    public void setPanelDiscardedTrumps(AbsPanel _panelDiscardedTrumps) {
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

    public AbsScrollPane getScrollDeclaringHandful() {
        return scrollDeclaringHandful;
    }
    protected void setScrollDeclaringHandful(AbsScrollPane _scrollDeclaringHandful) {
        scrollDeclaringHandful = _scrollDeclaringHandful;
    }
    protected AbsPanel getIncludedTrumpsForHandful() {
        return includedTrumpsForHandful;
    }
    protected void setIncludedTrumpsForHandful(AbsPanel _includedTrumpsForHandful) {
        includedTrumpsForHandful = _includedTrumpsForHandful;
    }
    protected AbsPanel getExcludedTrumpsForHandful() {
        return excludedTrumpsForHandful;
    }
    protected void setExcludedTrumpsForHandful(AbsPanel _excludedTrumpsForHandful) {
        excludedTrumpsForHandful = _excludedTrumpsForHandful;
    }
    protected AbsSplitPane getDeclaringHandful() {
        return declaringHandful;
    }
    protected void setDeclaringHandful(AbsSplitPane _declaringHandful) {
        declaringHandful = _declaringHandful;
    }
    public boolean isCanPlay() {
        return canPlay;
    }
    public void setCanPlay(boolean _canPlay) {
        canPlay = _canPlay;
    }
    protected AbsScrollPane getScrollCallableCards() {
        return scrollCallableCards;
    }
    protected void setScrollCallableCards(AbsScrollPane _scrollCallableCards) {
        scrollCallableCards = _scrollCallableCards;
    }
    protected boolean isPartieAleatoireJouee() {
        return partieAleatoireJouee;
    }
    protected void setPartieAleatoireJouee(boolean _partieAleatoireJouee) {
        partieAleatoireJouee = _partieAleatoireJouee;
    }
    protected AbsPanel getPanelCallableCards() {
        return panelCallableCards;
    }
    protected void setPanelCallableCards(AbsPanel _panelCallableCards) {
        panelCallableCards = _panelCallableCards;
    }
    protected boolean isPartieSauvegardee() {
        return partieSauvegardee;
    }
    protected void setPartieSauvegardee(boolean _partieSauvegardee) {
        partieSauvegardee = _partieSauvegardee;
    }
    public IdList<Miseres> getAllowedMiseres() {
        IdList<Miseres> l_;
        l_ = new IdList<Miseres>();
        for (EntryCust<Miseres,BoolVal> e: selectedMiseres.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }
    public IdMap<Miseres,BoolVal> getSelectedMiseres() {
        return selectedMiseres;
    }
    protected void setSelectedMiseres(IdMap<Miseres,BoolVal> _selectedMiseres) {
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
    public AbsTextArea getInfoCurrentHandful() {
        return infoCurrentHandful;
    }
    public void setInfoCurrentHandful(AbsTextArea _infoCurrentHandful) {
        infoCurrentHandful = _infoCurrentHandful;
    }
    public AbsButton getValidateDog() {
        return validateDog;
    }
    protected void setValidateDog(AbsButton _validateDog) {
        validateDog = _validateDog;
    }
    public AbsButton getSlamButton() {
        return slamButton;
    }
    protected void setSlamButton(AbsButton _slamButton) {
        slamButton = _slamButton;
    }
    public StringMap<String> readResource() {
        return Games.getCommonTarotTr(Games.getAppliTr(getOwner().getFrames().currentLg())).getMapping();
    }

    public AbstractFutureParam<CardNatLgNamesNavigation> retrieve(String _conf) {
        return getOwner().getPrepared().getVal(_conf);
    }
}
