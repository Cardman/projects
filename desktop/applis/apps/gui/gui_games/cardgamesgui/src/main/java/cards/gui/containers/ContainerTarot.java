package cards.gui.containers;


import cards.consts.Role;
import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.animations.AddTextEvents;
import cards.gui.animations.HandfulThread;
import cards.gui.animations.SettingText;
import cards.gui.events.ListenerCardTarotHandful;
import cards.gui.events.SelectHandfulEvent;
import cards.gui.labels.*;
import cards.gui.panels.CarpetTarot;
import cards.main.CardNatLgNamesNavigation;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractFutureParam;
import code.util.*;
import code.util.core.StringUtil;

public abstract class ContainerTarot extends ContainerSingleImpl{

    public static final String EMPTY="";
    protected static final String TAB="\t";
    private final AbstractAtomicInteger arretDemo;
//    private boolean canBid;
//    private boolean canDiscard;
//    private boolean canExcludeTrumps;
//    private boolean canPlay;
    /**Carte survol&eacute;e par la souris*/
    private AbsPanel panelDiscardedTrumps;
    private HandTarot currentIncludedTrumps = new HandTarot();
    private final HandTarot currentExcludedTrumps = new HandTarot();
    private AbsScrollPane scrollDeclaringHandful;
    private AbsSplitPane declaringHandful;
    private AbsPanel includedTrumpsForHandful;
    private AbsPanel excludedTrumpsForHandful;
    private IdMap<Miseres,AbsCustCheckBox> selectedMiseres = new IdMap<Miseres,AbsCustCheckBox>();
    private AbsScrollPane scrollCallableCards;
    private AbsPanel panelCallableCards;
    private Handfuls choosenHandful = Handfuls.NO;
//    private CardTarot carteSurvoleeTarot;
    private AbsTextArea infoCurrentHandful;
    private AbsButton seeDog;
    private AbsButton takeCardDog;
    private AbsButton validateDog;
    private AbsButton slamButton;
    private final IdList<BidTarot> bids = new IdList<BidTarot>();
    private final IdMap<Handfuls, HandfulLabel> handfulsRadio = new IdMap<Handfuls, HandfulLabel>();
    private BidTarot contratUtilisateur = BidTarot.FOLD;
    private CardTarot calledCard = CardTarot.WHITE;
    private HandTarot takerCardsDog = new HandTarot();


    ContainerTarot(WindowCardsInt _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicInteger();
    }

    public IntCardConverter<CardTarot> converter() {
        return new TarotCardConverter();
    }
    public int getEcart() {
        return tapisTarot().getEcart();
    }

    protected void panel(int _nbPlayers, StringList _pseudos,AbsPanel _container, AbsPanel _panneau2) {
        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(buildDeclHands(_nbPlayers, _pseudos, getOwner().getFrames()));
        _panneau2.add(scroll_);
        setPanelCallableCards(getOwner().getCompoFactory().newLineBox());
        setScrollCallableCards(getOwner().getCompoFactory().newAbsScrollPane(getPanelCallableCards()));
        getScrollCallableCards().setVisible(false);
        _panneau2.add(getScrollCallableCards());
        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        _panneau2.add(sousPanneau_);
        setPanelDiscardedTrumps(getOwner().getCompoFactory().newLineBox());
        getPanelDiscardedTrumps().setVisible(false);
        _panneau2.add(getPanelDiscardedTrumps());
        _container.add(_panneau2,GuiConstants.BORDER_LAYOUT_EAST);
    }

    public static void ajouterBoutonContratsTarot(ContainerPlayableTarot _playable,CustList<BidTarot> _bids, BidTarot _m) {
        TranslationsLg lg_ = _playable.getOwner().getFrames().currentLg();
        for(BidTarot b: _bids) {
            ajouterBoutonContratTarot(_playable,Games.toString(b, lg_),b,b.estDemandable(_m));
        }
    }
    public static void ajouterBoutonContratTarot(ContainerPlayableTarot _playable,String _texte,BidTarot _action,boolean _apte) {
        AbsPanel panneau_ = _playable.getPanneauBoutonsJeu();
        AbsButton bouton_ = _playable.getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratTarot(_action));
        bouton_.addActionListener(_playable.guard(), _playable.bid(_action));
        bouton_.setEnabled(_apte);
        if (!_apte) {
            TranslationsLg lg_ = _playable.getOwner().getFrames().currentLg();
            bouton_.setToolTipText(StringUtil.simpleStringsFormat(_playable.file().getVal(MessagesGuiCards.MAIN_CANT_BID), Games.toString(_action, lg_)));
        }
        panneau_.add(bouton_);
        _playable.getBids().add(_action);
    }
    public String errorHandful(boolean _ecouteur, RulesTarot _regles) {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        String finalMessageHandful_;
        if (_ecouteur) {
            Handfuls ch_ = getChoosenHandful();
            if (ch_ != Handfuls.NO) {
                HandTarot handful_ = getCurrentIncludedTrumps();
                if (!GameTarot.isValidHandful(_regles,ch_, handful_, getCurrentExcludedTrumps())) {
                    String messErr_ = Games.isValidHandfulMessage(_regles, ch_, handful_, getCurrentExcludedTrumps(), lg_);
                    String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_DECLARE_DETAIL), Games.toString(ch_,lg_));
                    finalMessageHandful_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,messErr_);
                } else {
                    finalMessageHandful_ ="";
                }
            } else {
                finalMessageHandful_ = "";
            }
        } else {
            finalMessageHandful_ = "";
        }
        return finalMessageHandful_;
    }

    public void firstRound(byte _joueur, String _pseudo, IdList<Handfuls> _declHand, IdList<Miseres> _miseres, HandTarot _hand, IntCardsCallEvents _interceptor) {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for(Handfuls annonce_: _declHand) {
            _interceptor.call(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_, lg_),RETURN_LINE)));
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
        }
        for(Miseres annonce_: _miseres) {
            _interceptor.call(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_, lg_),RETURN_LINE)));
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
        }
        if(!_hand.estVide()) {
            AbsPlainLabel label_ = getHandfuls().getVal(_joueur);
            _interceptor.call(new SettingText(label_, Games.toString(_declHand.first(), lg_)));
//                    getHandfuls().getVal(_joueur).setText(annoncesPoignees_.first().toString());
        }
        _hand.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
        _interceptor.call(new HandfulThread(_hand, panelToSet_, getWindow()));
    }

    public void miseres(RulesTarot _regles, AbsPanel _panneau) {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        AbsPanel miseresPanel_ = getOwner().getCompoFactory().newPageBox();
        for(Miseres po_: _regles.getMiseres()) {
            AbsCustCheckBox check_ = getOwner().getCompoFactory().newCustCheckBox(Games.toString(po_, lg_));
            //check_.addChangeListener(new ListenerMiseres(check_,po_));
//                check_.addActionListener(new ListenerMiseresTarot(this,check_,po_));
            getSelectedMiseres().put(po_, check_);
            miseresPanel_.add(check_);
        }
        _panneau.add(miseresPanel_);
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
    public static void updateHandfulButtons(ContainerPlayableTarot _container, RulesTarot _rules) {
        IdList<Handfuls> all_ = new IdList<Handfuls>();
        IdList<Handfuls> enabled_ = new IdList<Handfuls>();
        all_.addAllElts(Handfuls.getNonDeclarableHandFuls());
        enabled_.addAllElts(Handfuls.getNonDeclarableHandFuls());
        for (Handfuls h: Handfuls.getDeclarableHandFuls()) {
            if (!_rules.poigneeAutorisee(h)) {
                continue;
            }
            int diff_ = _container.getCurrentIncludedTrumps().total()-_rules.getAllowedHandfuls().getVal(h);
            if (diff_ >= 0) {
                enabled_.add(h);
            }
//            if (poignees_.containsObj(h)) {
//                enabled_.add(h);
//            }
            all_.add(h);
        }
        updateHandfulButtons(_container,all_,enabled_,_rules.getAllowedHandfuls());
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
            if (_enabled.containsObj(h)) {
                if (_req.contains(h)) {
                    radio_.getButton().addActionListener(new SelectHandfulEvent( _container, h,_req.getVal(h)));
                } else {
                    radio_.getButton().addActionListener(new SelectHandfulEvent(_container, h, 0));
                }
            } else {
                radio_.disable();
            }
            radio_.setSelected(_container.getChoosenHandful());
            handFuls_.add(radio_.getButton());
            _container.getHandfulsRadio().addEntry(h,radio_);
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
        _panel.setSize(_panel.getPreferredSizeValue());
        _cont.getWindow().pack();
    }

    public void called(HandTarot _called, String _pseudos) {
        if (_called.estVide()) {
            return;
        }
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        ajouterTexteDansZone(StringUtil.concat(_pseudos,
                ContainerGame.INTRODUCTION_PTS,Games.toString(_called, lg_),ContainerGame.RETURN_LINE));
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

    public static CustList<GraphicCard<CardTarot>> getGraphicCards(WindowCardsInt _fact, TranslationsLg _lg, CustList<CardTarot> _hand) {
//        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
//        CustList<GraphicCard<CardTarot>> list_;
//        list_ = new CustList<GraphicCard<CardTarot>>();
//        boolean entered_ = false;
//        for(CardTarot c: _hand) {
//            GraphicCard<CardTarot> carte_=new GraphicCard<CardTarot>(new TarotCardConverter(), GameEnum.TAROT,c, !entered_, _fact.getFrames(), _lg);
//            carte_.setPreferredSize(entered_);
//            int w_ = carte_.getWidth();
//            int h_ = carte_.getHeight();
//            AbstractImage img_ = imageFactory_.newImageArgb(w_, h_);
//            img_.setFont(carte_.getPaintableLabel());
//            carte_.paintComponent(img_);
//            carte_.setIcon(imageFactory_,img_);
//            list_.add(carte_);
//            entered_ = true;
//        }
        return new ContainerSingUtil<CardTarot>(new TarotCardConverter()).getGraphicCardsGene(_fact.getFrames(),_lg,_hand);
//        return list_;
    }

    public static void callCard(ContainerTarot _cont, GameTarot _gt,byte _joueur, String _pseudo, CardTarot _ct, IntCardsCallEvents _interceptor) {
        TranslationsLg lg_ = _cont.getOwner().getFrames().currentLg();
        if(_gt.getCarteAppelee().contient(_ct)) {
            _cont.getMini().setStatus(_cont.getWindow().getImageFactory(), Role.CALLED_PLAYER, _joueur);
            _interceptor.call(new AddTextEvents(_cont, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(Role.CALLED_PLAYER,lg_))));
//            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+Status.CALLED_PLAYER.toString());
        }
    }
    public void border(GraphicCard<CardTarot> _c) {
        if (calledCard == _c.getCard()) {
            _c.getPaintableLabel().setLineBorder(GuiConstants.RED);
        } else {
            _c.getPaintableLabel().setLineBorder(GuiConstants.BLACK);
        }
    }

    public AbsPanel getCenterDeck() {
        return tapisTarot().getCenterDeck();
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
    public AbstractAtomicInteger getArretDemo() {
        return arretDemo;
    }
//    public boolean isArretDemo() {
//        return arretDemo.get();
//    }
//    public void setArretDemo(boolean _arretDemo) {
//        arretDemo.set(_arretDemo);
//    }

//    public CardTarot getCarteSurvoleeTarot() {
//        return carteSurvoleeTarot;
//    }
//    public void setCarteSurvoleeTarot(CardTarot _carteSurvoleeTarot) {
//        carteSurvoleeTarot = _carteSurvoleeTarot;
//    }
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

    public AbsButton getSeeDog() {
        return seeDog;
    }

    public void setSeeDog(AbsButton _s) {
        this.seeDog = _s;
    }

    public AbsButton getTakeCardDog() {
        return takeCardDog;
    }

    public void setTakeCardDog(AbsButton _s) {
        this.takeCardDog = _s;
    }

    public StringMap<String> readResource() {
        return Games.getCommonTarotTr(readResourceAppli()).getMapping();
    }

    public AbstractFutureParam<CardNatLgNamesNavigation> retrieve(String _conf) {
        return getOwner().getPrepared().getVal(_conf);
    }

    public IdList<BidTarot> getBids() {
        return bids;
    }

    public HandTarot getTakerCardsDog() {
        return takerCardsDog;
    }

    public void setTakerCardsDog(HandTarot _t) {
        this.takerCardsDog = _t;
    }

    public IdMap<Handfuls, HandfulLabel> getHandfulsRadio() {
        return handfulsRadio;
    }

    public CardTarot getCalledCard() {
        return calledCard;
    }

    public void setCalledCard(CardTarot _c) {
        this.calledCard = _c;
    }

    public BidTarot getContratUtilisateur() {
        return contratUtilisateur;
    }

    public void setContratUtilisateur(BidTarot _contratUtilisateur) {
        contratUtilisateur = _contratUtilisateur;
    }

}
