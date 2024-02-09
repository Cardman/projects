package cards.gui.containers;



import cards.belote.BidBeloteSuit;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.GraphicBeloteCard;
import cards.gui.labels.LabelPoints;
import cards.gui.labels.SuitLabel;
import cards.gui.panels.CarpetBelote;
import cards.main.CardNatLgNamesNavigation;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsButton;

import code.gui.images.AbstractImageFactory;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractFutureParam;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public abstract class ContainerBelote extends ContainerSingleImpl {

    public static final String EMPTY="";
    public static final String TAB="\t";

    private AbsPanel panneauBoutonsJeuPoints;

    private final AbstractAtomicBoolean arretDemo;

    private boolean canCall;
    private boolean canDiscard;
    private boolean canExcludeTrumps;
    private boolean canPlay;
    private int pts;
    private final CustList<LabelPoints> pointsButtons = new CustList<LabelPoints>();
    private final CustList<SuitLabel> bidsButtons = new CustList<SuitLabel>();
    private final CustList<BidBeloteSuit> bids = new CustList<BidBeloteSuit>();
    private Suit suit = Suit.UNDEFINED;
    private BidBelote bidType = BidBelote.FOLD;
    private AbsButton bidOk;
    private AbsButton fold;
    private CardBelote carteSurvoleeBelote;
    private AbsCustCheckBox beloteRebelote;
    private AbsCustCheckBox beloteDeclare;
    ContainerBelote(WindowCardsInt _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicBoolean();
        setBeloteRebelote(_window.getCompoFactory().newCustCheckBox());
        setBeloteDeclare(_window.getCompoFactory().newCustCheckBox());
    }

    public static void completeCurrent(GameBelote _game, TricksHandsBelote _tricks) {
        for (byte b: _game.getProgressingTrick().playersHavingPlayed(_game.getNombreDeJoueurs())) {
            _tricks.getCardsHandsAtInitialState().get(b).ajouter(_game.getProgressingTrick().carteDuJoueur(b, _game.getNombreDeJoueurs()));
        }
    }

    public void clearBids() {
        getPanneauBoutonsJeu().removeAll();
        getBids().clear();
    }
    @Override
    public boolean isSimple() {
        return false;
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
        pts = _points;
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

    public static CustList<GraphicBeloteCard> getGraphicCards(WindowCardsInt _fact, TranslationsLg _lg, CustList<CardBelote> _hand) {
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        CustList<GraphicBeloteCard> list_;
        list_ = new CustList<GraphicBeloteCard>();
        boolean entered_ = false;
        for(CardBelote c: _hand) {
            GraphicBeloteCard carte_=new GraphicBeloteCard(_fact.getFrames(),_lg, c, !entered_);
            carte_.setPreferredSize(entered_);
            AbsMetaLabelCard.paintCard(imageFactory_, carte_);
            list_.add(carte_);
            entered_ = true;
        }
        return list_;
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
    public boolean isArretDemo() {
        return arretDemo.get();
    }
    public void setArretDemo(boolean _arretDemo) {
        arretDemo.set(_arretDemo);
    }

    public AbsButton getFold() {
        return fold;
    }

    public void setFold(AbsButton _f) {
        this.fold = _f;
    }

    public AbsButton getBidOk() {
        return bidOk;
    }
    protected void setBidOk(AbsButton _bidOk) {
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
    protected AbsPanel getPanneauBoutonsJeuPoints() {
        return panneauBoutonsJeuPoints;
    }
    protected void setPanneauBoutonsJeuPoints(AbsPanel _panneauBoutonsJeuPoints) {
        panneauBoutonsJeuPoints = _panneauBoutonsJeuPoints;
    }
    public boolean isCanPlay() {
        return canPlay;
    }
    public void setCanPlay(boolean _canPlay) {
        canPlay = _canPlay;
    }
    public CardBelote getCarteSurvoleeBelote() {
        return carteSurvoleeBelote;
    }
    public void setCarteSurvoleeBelote(CardBelote _carteSurvoleeBelote) {
        carteSurvoleeBelote = _carteSurvoleeBelote;
    }
    protected boolean isCanExcludeTrumps() {
        return canExcludeTrumps;
    }
    protected void setCanExcludeTrumps(boolean _canExcludeTrumps) {
        canExcludeTrumps = _canExcludeTrumps;
    }
    protected boolean isCanDiscard() {
        return canDiscard;
    }
    protected void setCanDiscard(boolean _canDiscard) {
        canDiscard = _canDiscard;
    }
    protected boolean isCanCall() {
        return canCall;
    }
    protected void setCanCall(boolean _canCall) {
        canCall = _canCall;
    }
    protected Suit getSuit() {
        return suit;
    }
    protected void setSuit(Suit _suit) {
        suit = _suit;
    }
    public StringMap<String> readResource() {
        return Games.getCommonBeloteTr(Games.getAppliTr(getOwner().getFrames().currentLg())).getMapping();
//        return MessagesBeloteBelote.ms().getVal(StringUtil.concat(BeloteResoucesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/", BeloteResoucesAccess.NOM_FICHIER));
//        return ResourceFiles.ressourceFichier(StringUtil.concat(BeloteResoucesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR, BeloteResoucesAccess.NOM_FICHIER));
    }

    public AbstractFutureParam<CardNatLgNamesNavigation> retrieve(String _conf) {
        return getOwner().getPrepared().getVal(_conf);
    }
}

