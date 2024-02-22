package cards.gui.containers;


import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.events.ListenerCardTarotHandful;
import cards.gui.events.SelectHandfulEvent;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.GraphicTarotCard;
import cards.gui.labels.HandfulLabel;
import cards.gui.labels.MiniCard;
import cards.gui.panels.CarpetTarot;
import cards.main.CardNatLgNamesNavigation;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractFutureParam;
import code.util.*;

public abstract class ContainerTarot extends ContainerSingleImpl{

    public static final String EMPTY="";
    protected static final String TAB="\t";
    private final AbstractAtomicBoolean arretDemo;
//    private boolean canBid;
//    private boolean canDiscard;
//    private boolean canExcludeTrumps;
//    private boolean canPlay;
    /**Carte survol&eacute;e par la souris*/
    private AbsPanel panelDiscardedTrumps;
    private HandTarot currentIncludedTrumps = new HandTarot();
    private HandTarot currentExcludedTrumps = new HandTarot();
    private AbsScrollPane scrollDeclaringHandful;
    private AbsSplitPane declaringHandful;
    private AbsPanel includedTrumpsForHandful;
    private AbsPanel excludedTrumpsForHandful;
    private IdMap<Miseres,AbsCustCheckBox> selectedMiseres = new IdMap<Miseres,AbsCustCheckBox>();
    private AbsScrollPane scrollCallableCards;
    private AbsPanel panelCallableCards;
    private Handfuls choosenHandful = Handfuls.NO;
    private CardTarot carteSurvoleeTarot;
    private AbsTextArea infoCurrentHandful;
    private AbsButton validateDog;
    private AbsButton slamButton;
    private final IdList<BidTarot> bids = new IdList<BidTarot>();
    private final IdMap<Handfuls, HandfulLabel> handfulsRadio = new IdMap<Handfuls, HandfulLabel>();


    ContainerTarot(WindowCardsInt _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicBoolean();
    }
    public static void displayTrumpsForHandful(ContainerPlayableTarot _cont, HandTarot _trumps) {
        _cont.getScrollDeclaringHandful().setVisible(!_trumps.estVide());
        int sum_ = _cont.getCurrentIncludedTrumps().total() + _cont.getCurrentExcludedTrumps().total();
        if (sum_ == 0) {
            _cont.setCurrentIncludedTrumps(_trumps);
        }
        _cont.getCurrentIncludedTrumps().trier(_cont.getDisplayingTarot().getDisplaying().getSuits(), _cont.getDisplayingTarot().getDisplaying().isDecreasing());
        _cont.getCurrentExcludedTrumps().trier(_cont.getDisplayingTarot().getDisplaying().getSuits(), _cont.getDisplayingTarot().getDisplaying().isDecreasing());
        updateCardsInPanelTarotHandful(_cont);
        _cont.getOwner().pack();
        //PackingWindowAfter.pack(this, true);
        _cont.getDeclaringHandful().setDividerLocation(_cont.getDeclaringHandful().getWidth()*9/10);
    }
    public static void updateHandfulButtons(ContainerPlayableTarot _container,IdList<Handfuls> _all, IdList<Handfuls> _enabled, IdMap<Handfuls,Integer> _req) {
        TranslationsLg lg_ = _container.getOwner().getFrames().currentLg();
        AbsPanel panneau_=_container.getPanneauBoutonsJeu();
        AbsPanel handFuls_ = _container.getOwner().getCompoFactory().newPageBox();
        AbsTextArea txt_ = _container.getOwner().getCompoFactory().newTextArea(EMPTY_STRING, 1, 15);
        txt_.setEditable(false);
        _container.setInfoCurrentHandful(txt_);
        AbsScrollPane scroll_ = _container.getOwner().getCompoFactory().newAbsScrollPane(_container.getInfoCurrentHandful());
        scroll_.setPreferredSize(new MetaDimension(_container.getEvents().getWidth(),70));
        handFuls_.add(scroll_);
        _container.getHandfulsRadio().clear();
        for (Handfuls h: _all) {
            HandfulLabel radio_ = new HandfulLabel(_container.getOwner().getCompoFactory());
            radio_.setSuit(h, lg_);
            radio_.setSelected(_container.getChoosenHandful());
            if (_enabled.containsObj(h)) {
                if (_req.contains(h)) {
                    radio_.addMouseListener(new SelectHandfulEvent( _container, h,_req.getVal(h)));
                } else {
                    radio_.addMouseListener(new SelectHandfulEvent(_container, h, 0));
                }
            }
            handFuls_.add(radio_.getPaintableLabel());
            _container.getHandfulsRadio().addEntry(h,radio_);
            AbsMetaLabelCard.paintCard(_container.getOwner().getImageFactory(), radio_);
        }
        panneau_.add(handFuls_);
    }
    public static void updateCardsInPanelTarotHandful(ContainerPlayableTarot _cont) {
        updateCardsInPanelTarotHandful(_cont,_cont.getIncludedTrumpsForHandful(), _cont.getCurrentIncludedTrumps(), true);
        updateCardsInPanelTarotHandful(_cont,_cont.getExcludedTrumpsForHandful(), _cont.getCurrentExcludedTrumps(), false);
    }

    private static void updateCardsInPanelTarotHandful(ContainerPlayableTarot _cont,AbsPanel _panel, HandTarot _hand, boolean _included) {
        _panel.removeAll();
        TranslationsLg lg_ = _cont.getOwner().getFrames().currentLg();
        for(CardTarot c: _hand) {
            MiniCard carte_=new MiniCard(lg_, _cont.getOwner(), c.getId().nb());
            carte_.addMouseListener(new ListenerCardTarotHandful(_cont, c,_included));
            _panel.add(carte_.getPaintableLabel());
            AbsMetaLabelCard.paintCard(_cont.getWindow().getImageFactory(),carte_);
        }
        _panel.validate();
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
//    public boolean isCanDiscard() {
//        return canDiscard;
//    }
//    public void setCanDiscard(boolean _canDiscard) {
//        canDiscard = _canDiscard;
//    }
//    public boolean isCanBid() {
//        return canBid;
//    }
//    public void setCanBid(boolean _canBid) {
//        canBid = _canBid;
//    }
//    public boolean isCanExcludeTrumps() {
//        return canExcludeTrumps;
//    }
//    protected void setCanExcludeTrumps(boolean _canExcludeTrumps) {
//        canExcludeTrumps = _canExcludeTrumps;
//    }
    public HandTarot getCurrentIncludedTrumps() {
        return currentIncludedTrumps;
    }
    public void setCurrentIncludedTrumps(HandTarot _currentIncludedTrumps) {
        currentIncludedTrumps = _currentIncludedTrumps;
    }
    public HandTarot getCurrentExcludedTrumps() {
        return currentExcludedTrumps;
    }
    public void setCurrentExcludedTrumps(HandTarot _currentExcludedTrumps) {
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
    public AbsPanel getIncludedTrumpsForHandful() {
        return includedTrumpsForHandful;
    }
    protected void setIncludedTrumpsForHandful(AbsPanel _includedTrumpsForHandful) {
        includedTrumpsForHandful = _includedTrumpsForHandful;
    }
    public AbsPanel getExcludedTrumpsForHandful() {
        return excludedTrumpsForHandful;
    }
    protected void setExcludedTrumpsForHandful(AbsPanel _excludedTrumpsForHandful) {
        excludedTrumpsForHandful = _excludedTrumpsForHandful;
    }
    public AbsSplitPane getDeclaringHandful() {
        return declaringHandful;
    }
    protected void setDeclaringHandful(AbsSplitPane _declaringHandful) {
        declaringHandful = _declaringHandful;
    }
//    public boolean isCanPlay() {
//        return canPlay;
//    }
//    public void setCanPlay(boolean _canPlay) {
//        canPlay = _canPlay;
//    }
    public AbsScrollPane getScrollCallableCards() {
        return scrollCallableCards;
    }
    protected void setScrollCallableCards(AbsScrollPane _scrollCallableCards) {
        scrollCallableCards = _scrollCallableCards;
    }
    public AbsPanel getPanelCallableCards() {
        return panelCallableCards;
    }
    protected void setPanelCallableCards(AbsPanel _panelCallableCards) {
        panelCallableCards = _panelCallableCards;
    }
    public IdList<Miseres> getAllowedMiseres() {
        IdList<Miseres> l_;
        l_ = new IdList<Miseres>();
        for (EntryCust<Miseres,AbsCustCheckBox> e: selectedMiseres.entryList()) {
            if (e.getValue().isSelected()) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }
    public IdMap<Miseres,AbsCustCheckBox> getSelectedMiseres() {
        return selectedMiseres;
    }
    protected void setSelectedMiseres(IdMap<Miseres,AbsCustCheckBox> _selectedMiseres) {
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

    public IdList<BidTarot> getBids() {
        return bids;
    }

    public IdMap<Handfuls, HandfulLabel> getHandfulsRadio() {
        return handfulsRadio;
    }
}
