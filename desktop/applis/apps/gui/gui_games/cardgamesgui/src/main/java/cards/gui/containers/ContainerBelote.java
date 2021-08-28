package cards.gui.containers;

import javax.swing.SwingConstants;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.BeloteResoucesAccess;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.sml.DocumentReaderBeloteUtil;
import cards.consts.Suit;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.animations.PreparedPagesCards;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicBeloteCard;
import cards.gui.labels.LabelPoints;
import cards.gui.labels.SuitLabel;
import cards.gui.panels.CarpetBelote;
import cards.main.LaunchingCards;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;

import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesBeloteBelote;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.core.StringUtil;

public class ContainerBelote extends ContainerGame {

    public static final String EMPTY="";
    public static final String TAB="\t";

    private AbsPanel panneauBoutonsJeuPoints;

    /**Renvoie tous les scores de toutes les parties non solitaires*/
    private CustList<Longs> scores=new CustList<Longs>();
    /**Maximum des valeurs absolues des scores centr&eacute;s par rapport &agrave; la moyenne*/
    private long maxAbsoluScore;
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
    private int pts;
    private final CustList<LabelPoints> pointsButtons = new CustList<LabelPoints>();
    private final CustList<SuitLabel> bidsButtons = new CustList<SuitLabel>();
    private Suit suit = Suit.UNDEFINED;
    private BidBelote bidType = BidBelote.FOLD;
    private AbsPlainButton bidOk;
    private CardBelote carteSurvoleeBelote;
    ContainerBelote(WindowCards _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicBoolean();
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    public void setPoints(int _points) {
        pts = _points;
        for (LabelPoints l: pointsButtons) {
            l.setSelected(_points);
        }
        if (getBidType().getCouleurDominante()) {
            getBidOk().setEnabled(true);
        } else {
            getBidOk().setEnabled(getBidType().jouerDonne());
        }
        //ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+getPts()+RETURN_LINE_CHAR);
    }

    public void setBid(BidBeloteSuit _suit) {
        setSuit(_suit.getCouleur());
        setBidType(_suit.getEnchere());
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setCouleur(suit);
        bid_.setEnchere(bidType);
        for (SuitLabel l: bidsButtons) {
            l.setSelected(_suit);
        }
        for (SuitLabel l: bidsButtons) {
            l.repaintLabel(getWindow().getImageFactory());
        }
        getBidOk().setEnabled(getPts() > 0);
        //ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+bid_+RETURN_LINE_CHAR);
    }

    public static CustList<GraphicBeloteCard> getGraphicCards(WindowCards _fact, String _lg, Iterable<CardBelote> _hand) {
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        AbsCompoFactory compoFactory_ = _fact.getCompoFactory();
        CustList<GraphicBeloteCard> list_;
        list_ = new CustList<GraphicBeloteCard>();
        boolean entered_ = false;
        for(CardBelote c: _hand) {
            GraphicBeloteCard carte_=new GraphicBeloteCard(imageFactory_,_lg, c,SwingConstants.RIGHT,!entered_, compoFactory_);
            carte_.setPreferredSize(entered_);
            list_.add(carte_);
            entered_ = true;
        }
        return list_;
    }

    public void bid() {
        //
    }
    public void fold() {
        //
    }
    public void invertBeloteRebelote() {
        //
    }
    public void invertBeloteDeclare() {
        //
    }
    protected CustList<LabelPoints> getPointsButtons() {
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
    a partir d'un fichier
     * @param _tmpUserFolderSl*/
    protected static HandBelote chargerPileBelote(AbstractProgramInfos _tmpUserFolderSl) {
        return DocumentReaderBeloteUtil.getHandBelote(StreamTextFile.contentsOfFile(
                  StringUtil.concat(LaunchingCards.getTempFolderSl(_tmpUserFolderSl),FileConst.DECK_FOLDER,
                          StreamTextFile.SEPARATEUR,GameEnum.BELOTE.name(),FileConst.DECK_EXT),_tmpUserFolderSl.getFileCoreStream(),_tmpUserFolderSl.getStreams()));
    }
    public String pseudo() {
        return getPseudosJoueurs().getPseudo();
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
    public CustList<Longs> getScores() {
        return scores;
    }
    protected void setScores(CustList<Longs> _scores) {
        scores = _scores;
    }
    protected AbsPlainButton getBidOk() {
        return bidOk;
    }
    protected void setBidOk(AbsPlainButton _bidOk) {
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
    public boolean isCanBid() {
        return canBid;
    }
    public void setCanBid(boolean _canBid) {
        canBid = _canBid;
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
    protected long getMaxAbsoluScore() {
        return maxAbsoluScore;
    }
    protected void setMaxAbsoluScore(long _maxAbsoluScore) {
        maxAbsoluScore = _maxAbsoluScore;
    }
    protected boolean isPartieSauvegardee() {
        return partieSauvegardee;
    }
    protected void setPartieSauvegardee(boolean _partieSauvegardee) {
        partieSauvegardee = _partieSauvegardee;
    }
    protected boolean isPartieAleatoireJouee() {
        return partieAleatoireJouee;
    }
    protected void setPartieAleatoireJouee(boolean _partieAleatoireJouee) {
        partieAleatoireJouee = _partieAleatoireJouee;
    }
    public String readResource() {
        return MessagesBeloteBelote.ms().getVal(StringUtil.concat(BeloteResoucesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/", BeloteResoucesAccess.NOM_FICHIER));
//        return ResourceFiles.ressourceFichier(StringUtil.concat(BeloteResoucesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR, BeloteResoucesAccess.NOM_FICHIER));
    }

    public PreparedPagesCards retrieve(String _conf) {
        return getOwner().getPreparedBelote().getVal(_conf).getVal(getOwner().getLanguageKey());
    }
}

