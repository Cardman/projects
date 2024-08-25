package cards.gui.containers;


import cards.belote.*;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.MessagesCardGames;
import cards.gui.WindowCardsCore;
import cards.gui.WindowCardsInt;
import cards.gui.animations.*;
import cards.gui.containers.events.*;
import cards.gui.events.*;
import cards.gui.labels.*;
import cards.gui.panels.CarpetBelote;
import cards.main.CardNatLgNamesNavigation;
import code.gui.*;
import code.scripts.messages.cards.*;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractFutureParam;
import code.util.*;
import code.util.core.*;

public abstract class ContainerBelote extends ContainerSingleImpl {

    public static final String EMPTY="";
    public static final String TAB="\t";

    private AbsPanel panneauBoutonsJeuPoints;

    private final AbstractAtomicInteger arretDemo;

    private int pts;
    private final CustList<LabelPoints> pointsButtons = new CustList<LabelPoints>();
    private final CustList<SuitLabel> bidsButtons = new CustList<SuitLabel>();
    private final CustList<BidBeloteSuit> bids = new CustList<BidBeloteSuit>();
    private Suit suit = Suit.UNDEFINED;
    private BidBelote bidType = BidBelote.FOLD;
    private AbsButton bidOk;
    private AbsButton fold;
//    private CardBelote carteSurvoleeBelote;
    private AbsCustCheckBox beloteRebelote;
    private AbsCustCheckBox beloteDeclare;
    private AbsButton seeDiscard;
    private AbsButton takeCardDiscard;
    private AbsButton validateDiscard;
    private AbsButton slamButton;
    private HandBelote takerCardsDiscard = new HandBelote();
    ContainerBelote(WindowCardsInt _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicInteger();
        setBeloteRebelote(_window.getCompoFactory().newCustCheckBox());
        setBeloteDeclare(_window.getCompoFactory().newCustCheckBox());
    }

    public IntCardConverter<CardBelote> converter() {
        return new BeloteCardConverter();
    }
    public int getEcart() {
        return tapisBelote().getEcart();
    }

    public AbsPanel getCenterDeck() {
        return tapisBelote().getCenterDeck();
    }
    public IdList<CardBelote> ecartables() {
        return getTakerCardsDiscard().getCards();
    }
    public void clearBids() {
        getPanneauBoutonsJeu().removeAll();
        getBids().clear();
    }

    public CustList<BidBeloteSuit> getBids() {
        return bids;
    }

    public static int index(CustList<BidBeloteSuit> _bids, BidBeloteSuit _bid) {
        int s_ = _bids.size();
        for (int i = 0; i < s_; i++) {
            BidBeloteSuit b_ = _bids.get(i);
            if (b_.getBid() == _bid.getBid() && b_.getSuit() == _bid.getSuit() && b_.getPoints() == _bid.getPoints()) {
                return i;
            }
        }
        return -1;
    }
    public void setPoints(int _points) {
        setPts(_points);
        for (LabelPoints l: pointsButtons) {
            l.setSelected(_points);
        }
        updateBidOk();
        //ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+getPts()+RETURN_LINE_CHAR);
    }

    public void setBid(BidBeloteSuit _suit) {
        setSuit(_suit.getSuit());
        setBidType(_suit.getBid());
        for (SuitLabel l: bidsButtons) {
            l.setSelected(_suit);
        }
        for (SuitLabel l: bidsButtons) {
            AbsMetaLabelCard.paintCard(getWindow().getImageFactory(), l);
        }
        updateBidOk();
        //ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+bid_+RETURN_LINE_CHAR);
    }

    private void updateBidOk() {
        getBidOk().setEnabled(getBidType().jouerDonne() && getPts() > 0);
    }

    public static CustList<GraphicCard<CardBelote>> getGraphicCards(WindowCardsInt _fact, TranslationsLg _lg, CustList<CardBelote> _hand) {
//        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
//        CustList<GraphicCard<CardBelote>> list_;
//        list_ = new CustList<GraphicCard<CardBelote>>();
//        boolean entered_ = false;
//        for(CardBelote c: _hand) {
//            GraphicCard<CardBelote> carte_=new GraphicCard<CardBelote>(new BeloteCardConverter(), GameEnum.BELOTE, c, !entered_,_fact.getFrames(),_lg);
//            carte_.setPreferredSize(entered_);
//            AbsMetaLabelCard.paintCard(imageFactory_, carte_);
//            list_.add(carte_);
//            entered_ = true;
//        }
        return new ContainerSingUtil<CardBelote>(new BeloteCardConverter()).getGraphicCardsGene(_fact.getFrames(),_lg,_hand);
    }

    protected void panel(int _nbPlayers, CustList<String> _pseudos,AbsPanel _container, AbsPanel _panneau2) {
        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(buildDeclHands(_nbPlayers, _pseudos, getOwner().getFrames()));
        _panneau2.add(scroll_);
        AbsPanel sousPanneau_ = getOwner().getCompoFactory().newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        _panneau2.add(sousPanneau_);
        _container.add(_panneau2, GuiConstants.BORDER_LAYOUT_EAST);
    }

    public static void bidButtons(ContainerPlayableBelote _playable, RulesBelote _rules, int _pts, BidBeloteSuit _bid, CustList<BidBeloteSuit> _bids) {
        TranslationsLg lg_ = _playable.getOwner().getFrames().currentLg();
        if (!_rules.withBidPointsForAllPlayers()) {
            for(BidBeloteSuit e: _bids) {
                ajouterBoutonContratBelote(_playable,Games.toString(e, lg_),e,e.estDemandable(_bid));
//                ajouterBoutonContratBelote(Games.toString(e, lg_),e,e.estDemandable(contrat_));
            }
        } else {
            addButtonsForCoinche(_playable, _pts,_rules.getListeEncheresAutorisees());
        }
    }
    public static void ajouterBoutonContratBelote(ContainerPlayableBelote _playable, String _texte,BidBeloteSuit _action,boolean _apte) {
        AbsPanel panneau_=_playable.getPanneauBoutonsJeu();
        AbsButton bouton_=_playable.getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratBelote(_action));
        bouton_.addActionListener(_playable.guard(),_playable.bid(_action));
        bouton_.setEnabled(_apte);
        if (!_apte) {
            TranslationsLg lg_ = _playable.getOwner().getFrames().currentLg();
            bouton_.setToolTipText(StringUtil.simpleStringsFormat(_playable.file().getVal(MessagesGuiCards.MAIN_CANT_BID), Games.toString(_action,lg_)));
        }
        panneau_.add(bouton_);
        _playable.getBids().add(_action);
    }
    public static void addButtonsForCoinche(ContainerPlayableBelote _playable, int _pts, IdList<BidBelote> _partie) {
        Ints points_ = RulesBelote.getPoints();
        int size_ = points_.size();
        _playable.setPanneauBoutonsJeuPoints(_playable.getOwner().getCompoFactory().newGrid());
        _playable.getPointsButtons().clear();
        TranslationsLg lg_ = _playable.getOwner().getFrames().currentLg();
        for (int i = 0; i < size_; i++) {
            int p_ = points_.get(i);
            LabelPoints label_ = new LabelPoints(p_, _playable.getOwner().getCompoFactory());
            label_.setEnabledLabel(_pts < p_);
            label_.setToolTipText(Long.toString(p_));
            label_.getButton().addActionListener(_playable.guard(),new SelectPointsEvent(_playable, p_));
            _playable.getPointsButtons().add(label_);
            _playable.getPanneauBoutonsJeuPoints().add(label_.getButton(), WindowCardsCore.ctsRem(_playable.getWindow().getCompoFactory(),(i+1)%3==0));
        }
        _playable.getPanneauBoutonsJeu().add(_playable.getPanneauBoutonsJeuPoints());
//        clickedBid = false;
//        clickedPass = false;
        _playable.setBidOk(_playable.getOwner().getCompoFactory().newPlainButton(_playable.file().getVal(MessagesGuiCards.MAIN_OK)));
        _playable.getBidOk().setEnabled(false);
        _playable.getBidOk().addActionListener(_playable.guard(),new BidEvent(_playable));
        AbsPanel panel_ = _playable.getOwner().getCompoFactory().newGrid();
        _playable.getBidsButtons().clear();
        _playable.getBids().clear();
        CustList<BidBeloteSuit> bidsAll_ = GameBeloteBid.baseBidsDealAll(_partie);
        for (BidBeloteSuit b: bidsAll_) {
            SuitLabel suitLabel_ = new SuitLabel(_playable.getOwner().getCompoFactory());
            suitLabel_.setSuit(b, lg_);
            suitLabel_.addMouseListener(new SelectSuitEvent(_playable,b));
            panel_.add(suitLabel_.getPaintableLabel(), ContainerSingleBelote.ctsRem(panel_, bidsAll_, _playable.getWindow().getCompoFactory()));
            _playable.getBidsButtons().add(suitLabel_);
            AbsMetaLabelCard.paintCard(_playable.getOwner().getImageFactory(), suitLabel_);
            _playable.getBids().add(b);
        }
//        AbsPanel panelSuits_ = getOwner().getCompoFactory().newLineBox();
//        for (Suit s: Suit.couleursOrdinaires()) {
//            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
//            BidBeloteSuit bid_ = new BidBeloteSuit();
//            bid_.setSuit(s);
//            bid_.setBid(BidBelote.SUIT);
//            suitLabel_.setSuit(bid_, lg_);
//            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
//            panelSuits_.add(suitLabel_.getPaintableLabel());
//            getBidsButtons().add(suitLabel_);
//        }
//        panel_.add(panelSuits_);
//        AbsPanel panelBids_ = getOwner().getCompoFactory().newLineBox();
//        for (BidBelote b: BidBelote.getNonZeroBids()) {
//            if (b.getCouleurDominante()) {
//                continue;
//            }
//            if (_partie.getRegles().getAllowedBids().getVal(b) != BoolVal.TRUE) {
//                continue;
//            }
//            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
//            BidBeloteSuit bid_ = new BidBeloteSuit();
//            bid_.setBid(b);
//            suitLabel_.setSuit(bid_, lg_);
//            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
//
//            panelBids_.add(suitLabel_.getPaintableLabel());
//            getBidsButtons().add(suitLabel_);
//        }
//        panel_.add(panelBids_);
        AbsButton buttonSuit_ = _playable.getOwner().getCompoFactory().newPlainButton(Games.toString(BidBelote.FOLD,lg_));
        //clickedTwo = false;
        buttonSuit_.addActionListener(_playable.guard(),new FoldEvent(_playable));
        panel_.add(buttonSuit_,WindowCardsCore.cts(_playable.getWindow().getCompoFactory()));
        _playable.setFold(buttonSuit_);
        panel_.add(_playable.getBidOk(),WindowCardsCore.cts(_playable.getWindow().getCompoFactory()));
        _playable.getPanneauBoutonsJeu().add(panel_);
    }

    public void declare(DeclareHandBelote _annonceMain) {
        AbsCustCheckBox beloteDeclare_ = getBeloteDeclare();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if(_annonceMain.getDeclare() != DeclaresBelote.UNDEFINED) {
//                annonceBelote = false;
            AbsPanel panneau_ =getPanneauBoutonsJeu();
//                AbsCustCheckBox caseCoche_ = getOwner().getCompoFactory().newCustCheckBox(StringUtil.concat(Games.toString(annonceMain_.getDeclare(),lg_),INTRODUCTION_PTS,Games.toString(annonceMain_.getHand(),lg_)));
            beloteDeclare_.setText(StringUtil.concat(Games.toString(_annonceMain.getDeclare(), lg_),INTRODUCTION_PTS,Games.toString(_annonceMain.getHand(), lg_)));
//                caseCoche_.addActionListener(new ChangeBeloteDeclareEvent(this));
            panneau_.add(beloteDeclare_);
        }
    }

    public void firstRound(byte _joueur, String _pseudo, BidBeloteSuit _bid, DeclareHandBelote _decl, IntCardsCallEvents _interceptor) {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        _interceptor.call(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(_decl.getDeclare(), lg_),RETURN_LINE)));
//            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),, getOwner().getFrames());
//            ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+usDecl_.getAnnonce()+RETURN_LINE_CHAR);
        if(!_decl.getHand().estVide()) {
            AbsPlainLabel label_ = getHandfuls().getVal(_joueur);
            _interceptor.call(new SettingText(label_, Games.toString(_decl.getDeclare(), lg_)));
//                ThreadInvoker.invokeNow(getOwner().getThreadFactory(),, getOwner().getFrames());
//                getHandfuls().getVal(_joueur).setText(usDecl_.getAnnonce().toString());
        }
        _decl.getHand().trier(getDisplayingBelote(), _bid);

        AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
        _interceptor.call(new DeclaringThread(panelToSet_, _decl, getOwner()));
//            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),, getOwner().getFrames());
//            panelToSet_.removeAll();
//            for(CardBelote c: usDecl_.getMain())
//            {
//                MiniBeloteCard carte_=new MiniBeloteCard(c);
//                panelToSet_.add(carte_);
//            }
    }
    public CustList<LabelPoints> getPointsButtons() {
        return pointsButtons;
    }
    public CustList<SuitLabel> getBidsButtons() {
        return bidsButtons;
    }
    public StringList pseudosBelote(byte _nbPlayers) {
        StringList pseudosTwo_=new StringList();
        pseudosTwo_.add(pseudo());
        StringList pseudos_ = getPseudosJoueurs().getPseudosBelote();
        pseudosTwo_.addAllElts(pseudos_.left(_nbPlayers - 1));
        return pseudosTwo_;
    }
    /**Permet de charger une main de distribution
    a partir d'un fichier  */
    protected HandBelote chargerPileBelote() {
        return getOwner().baseWindow().getFacadeCards().getNicknamesCrud().getCardGamesCrud().belote();
    }
    protected HandBelote chargerPileBeloteShort() {
        return getOwner().baseWindow().getFacadeCards().getNicknamesCrud().getCardGamesCrud().belote24();
    }
    public String pseudo() {
        return getPseudosJoueurs().getPseudo();
    }

    public AbsCustCheckBox getBeloteRebelote() {
        return beloteRebelote;
    }

    public void setBeloteRebelote(AbsCustCheckBox _b) {
        this.beloteRebelote = _b;
    }

    public AbsCustCheckBox getBeloteDeclare() {
        return beloteDeclare;
    }

    public void setBeloteDeclare(AbsCustCheckBox _d) {
        this.beloteDeclare = _d;
    }

    public CarpetBelote tapisBelote() {
        return getTapis().getTapisBelote();
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

    public AbsButton getFold() {
        return fold;
    }

    public void setFold(AbsButton _f) {
        this.fold = _f;
    }

    public AbsButton getBidOk() {
        return bidOk;
    }
    public void setBidOk(AbsButton _bidOk) {
        bidOk = _bidOk;
    }
    protected int getPts() {
        return pts;
    }
    protected void setPts(int _pts) {
        pts = _pts;
    }
    protected BidBelote getBidType() {
        return bidType;
    }
    protected void setBidType(BidBelote _bidType) {
        bidType = _bidType;
    }
    public AbsPanel getPanneauBoutonsJeuPoints() {
        return panneauBoutonsJeuPoints;
    }
    public void setPanneauBoutonsJeuPoints(AbsPanel _panneauBoutonsJeuPoints) {
        panneauBoutonsJeuPoints = _panneauBoutonsJeuPoints;
    }
    public AbsButton getValidateDiscard() {
        return validateDiscard;
    }
    protected void setValidateDiscard(AbsButton _validateDog) {
        validateDiscard = _validateDog;
    }

    public AbsButton getSeeDiscard() {
        return seeDiscard;
    }

    public void setSeeDiscard(AbsButton _b) {
        this.seeDiscard = _b;
    }

    public AbsButton getTakeCardDiscard() {
        return takeCardDiscard;
    }

    public void setTakeCardDiscard(AbsButton _b) {
        this.takeCardDiscard = _b;
    }

    public AbsButton getSlamButton() {
        return slamButton;
    }
    protected void setSlamButton(AbsButton _slamButton) {
        slamButton = _slamButton;
    }
//    public CardBelote getCarteSurvoleeBelote() {
//        return carteSurvoleeBelote;
//    }
//    public void setCarteSurvoleeBelote(CardBelote _carteSurvoleeBelote) {
//        carteSurvoleeBelote = _carteSurvoleeBelote;
//    }
    protected Suit getSuit() {
        return suit;
    }
    protected void setSuit(Suit _suit) {
        suit = _suit;
    }
    public StringMap<String> readResource() {
        return MessagesCardGames.getCommonBeloteTr(readResourceAppli()).getMapping();
//        return MessagesBeloteBelote.ms().getVal(StringUtil.concat(BeloteResoucesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/", BeloteResoucesAccess.NOM_FICHIER));
//        return ResourceFiles.ressourceFichier(StringUtil.concat(BeloteResoucesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR, BeloteResoucesAccess.NOM_FICHIER));
    }

    public AbstractFutureParam<CardNatLgNamesNavigation> retrieve(String _conf) {
        return getOwner().getPrepared().getVal(_conf);
    }

    public HandBelote getTakerCardsDiscard() {
        return takerCardsDiscard;
    }

    public void setTakerCardsDiscard(HandBelote _t) {
        this.takerCardsDiscard = _t;
    }
}

