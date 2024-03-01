package cards.gui.containers;


import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.labels.*;
import cards.gui.panels.CarpetBelote;
import cards.main.CardNatLgNamesNavigation;
import code.gui.AbsButton;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractFutureParam;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

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
    private AbsButton validateDiscard;
    private AbsButton slamButton;
    ContainerBelote(WindowCardsInt _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicInteger();
        setBeloteRebelote(_window.getCompoFactory().newCustCheckBox());
        setBeloteDeclare(_window.getCompoFactory().newCustCheckBox());
    }

    public IntCardConverter<CardBelote> converter() {
        return new BeloteCardConverter();
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
        return new ContainerSingUtil<CardBelote>(new BeloteCardConverter()).getGraphicCardsGene(_fact,_lg,_hand);
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
    public AbsButton getValidateDiscard() {
        return validateDiscard;
    }
    protected void setValidateDiscard(AbsButton _validateDog) {
        validateDiscard = _validateDog;
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
        return Games.getCommonBeloteTr(readResourceAppli()).getMapping();
//        return MessagesBeloteBelote.ms().getVal(StringUtil.concat(BeloteResoucesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/", BeloteResoucesAccess.NOM_FICHIER));
//        return ResourceFiles.ressourceFichier(StringUtil.concat(BeloteResoucesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR, BeloteResoucesAccess.NOM_FICHIER));
    }

    public AbstractFutureParam<CardNatLgNamesNavigation> retrieve(String _conf) {
        return getOwner().getPrepared().getVal(_conf);
    }
}

