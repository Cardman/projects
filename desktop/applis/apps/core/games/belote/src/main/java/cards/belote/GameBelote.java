package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.consts.GameType;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdMap;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

/**
 */

public final class GameBelote {

    /**Le type d'une partie est aleatoire ou encore edite ou encore un entrainement*/
    private GameType type;
    /**Contient toutes les cartes au debut de chaque partie*/
    private DealBelote deal;
    /**Au debut on a besoin d'un variable preneur pour stocker le joueur ayant annonce temporairement le plus haut contrat
    De plus, le choix par defaut de -1 pour le preneur sert pour le tarot lorsque
    personne ne prend un contrat et chacun joue pour soi*/
    private byte taker=IndexConstants.INDEX_NOT_FOUND_ELT;
    /** Ce sont les primes, miseres ou poignees annoncees par le(s) joueur(s)*/
    private CustList<DeclareHandBelote> declares=new CustList<DeclareHandBelote>();
    private final CustList<DeclaresBelote> declaresPts=new CustList<DeclaresBelote>();
    private CustList<HandBelote> declaresBeloteRebelote = new CustList<HandBelote>();
    private final Shorts declaresBeloteRebelotePts = new Shorts();
    private CustList<BoolVal> wonLastTrick = new CustList<BoolVal>();
    /**Le contrat permet de dire quel va etre le deroulement
    de la partie*/
    private BidBeloteSuit bid=new BidBeloteSuit();
    /**Ce sont les plis faits par les joueurs*/
    private CustList<TrickBelote> tricks = new CustList<TrickBelote>();
    /**Fin du premier tour d'enchere a la belote<br/>
    est vrai si et seulement si c'est la fin des encheres de premier tour*/
//    private boolean endBidsFirstRound;
    /**PliBelote en cours d'etre joue*/
    private TrickBelote progressingTrick = new TrickBelote(IndexConstants.INDEX_NOT_FOUND_ELT);

    /**Entameur du pli qui est en cours d'etre joue*/
    private byte starter;
    /**Ensembe des contrats annonces*/
    private CustList<BidBeloteSuit> bids=new CustList<BidBeloteSuit>();
    /**Ramasseur du pli qui vient d'etre joue*/
    private byte trickWinner;
    /**Scores cumules au cours des parties
    Chaque nombre (Short) represente un score pour le joueur*/
    private Shorts scores=new Shorts();
    /**Nombre de fois qu'a ete joue la partie (partie fini)*/
    private long number;
    private RulesBelote rules=new RulesBelote();
//    private byte lastHasBid = IndexConstants.INDEX_NOT_FOUND_ELT;

//    private BidBeloteSuit lastBid=new BidBeloteSuit();

//    private CardBelote playedCard = CardBelote.WHITE;

    private String error = "";
    private boolean ended;
    private ReasonPlayBelote reason = ReasonPlayBelote.NOTHING;
    /**Constructeur permettant le chargement d'une partie de belote*/
    public GameBelote() {}

    public GameBelote(GameType _type, DealBelote _donne, RulesBelote _regles) {
        type=_type;
        deal=_donne;
        rules = _regles;
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i_ = IndexConstants.FIRST_INDEX; i_<nombreJoueurs_; i_++) {
            scores.add((short) 0);
        }
        //A la belote le jeu se deroule avec deux equipes de deux joueurs
        //Chaque joueur fait equipe avec celui qui est en face
        /*Initialise les annonces*/
        for (int j_ = IndexConstants.FIRST_INDEX; j_<nombreJoueurs_; j_++) {
            declaresBeloteRebelote.add(new HandBelote());
            declaresBeloteRebelotePts.add((short) 0);
            wonLastTrick.add(BoolVal.FALSE);
            declares.add(new DeclareHandBelote());
            declaresPts.add(DeclaresBelote.UNDEFINED);
        }
    }

    void loadGame() {
        deal.setDealer((byte) (NumberUtil.mod(deal.getDealer(), getNombreDeJoueurs())));
        byte player_ = playerAfter(deal.getDealer());
        taker = IndexConstants.INDEX_NOT_FOUND_ELT;
        bid = bid(player_);
        patchBids();
        if (rules.getDealing().getDiscarded() > 0 && getTricks().isEmpty() && getPreneur() != DealBelote.NUMERO_UTILISATEUR && progressingTrick.total() == rules.getDealing().getDiscarded()) {
            fwd(progressingTrick.getCartes());
        }
        if (rules.getDealing().getDiscarded() > 0 && progressingTrick.foundFirst(tricks)) {
            setEntameurPremier();
        }
        if (progressingTrick.foundLast(tricks)) {
            progressingTrick = new TrickBelote(progressingTrick.getRamasseurPliEnCours(getNombreDeJoueurs(), bid));
        }
        if (!noPlayed()) {
            initStarters();
            starter = progressingTrick.getEntameur();
            trickWinner = progressingTrick.getEntameur();
            if (progressingTrick.total() == getNombreDeJoueurs()) {
                ajouterDixDeDerPliEnCours();
            }
        } else if (keepBidding()) {
            starter = playerAfter(deal.getDealer());
            trickWinner = starter;
        } else {
            firstStarter();
            trickWinner = starter;
//            if (bid.getPoints() >= HandBelote.pointsTotauxDixDeDer(bid)) {
//                starter = progressingTrick.getEntameur();
//                trickWinner = progressingTrick.getEntameur();
//            } else {
//                starter = playerAfter(deal.getDealer());
//                trickWinner = starter;
//            }
        }
        declaresBeloteRebelotePts.clear();
        declaresPts.clear();
        byte nb_ = getNombreDeJoueurs();
        for (int p = 0; p < nb_; p++) {
            declaresBeloteRebelotePts.add((short) 0);
            declaresPts.add(DeclaresBelote.UNDEFINED);
        }
        for (int p = 0; p < nb_; p++) {
            if(declaresBeloteRebelote.get(p).contientCartes(GameBeloteCommonPlaying.cartesBeloteRebelote(bid))) {
                declaresBeloteRebelotePts.set(p, (short) DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints());
            }
            declaresPts.set(p, declares.get(p).getDeclare());
        }
    }

    private void patchBids() {
        if (getPreneur() > -1 && (!noPlayedClassic() || getDeal().hand(getPreneur()).total() != getRegles().getDealing().getNombreCartesParJoueur())) {
            while (keepBidding()) {
                ajouterContrat(new BidBeloteSuit());
            }
        }
    }

    private void initStarters() {
        firstStarter();
        byte leader_ = getEntameur();
        for (TrickBelote t: tricks.mid(RulesBelote.offset(rules))) {
            t.setEntameur(leader_);
            leader_ = t.getRamasseur(bid);
        }
        progressingTrick.setEntameur(leader_);
    }

    private BidBeloteSuit bid(byte _pl) {
        byte player_ = _pl;
        BidBeloteSuit bid_ = new BidBeloteSuit();
        if (rules.withBidPointsForAllPlayers()) {
//            endBidsFirstRound = false;
            for (BidBeloteSuit b: bids) {
                if (b.getPoints() > bid_.getPoints()) {
                    taker = player_;
                    bid_ = b;
                }
                player_ = playerAfter(player_);
            }
        } else {
//            endBidsFirstRound = bids.size() >= getNombreDeJoueurs();
            for (BidBeloteSuit b: bids) {
                if (b.strongerThan(bid_)) {
                    taker = player_;
                    bid_ = b;
                }
                player_ = playerAfter(player_);
            }
        }
        return bid_;
    }

    public RulesBelote getRegles() {
        return getRules();
    }
    /**Renvoie le nombre de joueurs jouant a la partie*/
    public byte getNombreDeJoueurs() {
        return (byte) rules.getDealing().getId().getNombreJoueurs();
    }
    public long getNombre() {
        return getNumber();
    }
    public void setNombre() {
        number++;
    }
    public Shorts getScoresRef() {
        return scores;
    }
    public Shorts getScores() {
        return new Shorts(scores);
    }

    public GameType getType() {
        return type;
    }
    public DealBelote getDistribution() {
        return getDeal();
    }
    /**for multi player*/
    public boolean completedDeal() {
        boolean completed_ = true;
        for (byte p: getDeal().orderedPlayersBegin(getRegles())) {
            if (deal.hand(p).total() < getRegles().getDealing().getNombreCartesParJoueur()) {
                completed_ = false;
                break;
            }
        }
//        if (getRegles().dealAll()) {
//            int pts_ = getBid().getPoints();
//            if (pts_ >= HandBelote.pointsTotauxDixDeDer(getBid())) {
//                setEntameur(getPreneur());
//            } else {
//                byte nombreDeJoueurs_ = getNombreDeJoueurs();
//                setEntameur((byte)((deal.getDealer()+1)%nombreDeJoueurs_));
//            }
//            return false;
//        }
        if (getRegles().dealAll()) {
            setEntameurPremier();
            return false;
        }
        if (completed_) {
            return true;
        }
        completerDonne();
        return false;
    }
    public int completerDonne() {
        deal.completerDonne(taker,rules);
        return setEntameurPremier();
//        byte nombreDeJoueurs_ = getNombreDeJoueurs();
//        setEntameur((byte)((deal.getDealer()+1)%nombreDeJoueurs_));
    }
    public void initPartie() {
        taker=-1;
        bids=new CustList<BidBeloteSuit>();
//        endBidsFirstRound=false;
        bid=new BidBeloteSuit();
        progressingTrick=new TrickBelote((byte) -1);
        Shorts scores_=getScores();
        byte nombreJoueurs_=getNombreDeJoueurs();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
            /*Initialise les annonces*/
            scores_.set(joueur_, (short) 0);
            declaresBeloteRebelote.set(joueur_, new HandBelote());
            declaresBeloteRebelotePts.set(joueur_, (short) 0);
            wonLastTrick.set(joueur_, BoolVal.FALSE);
            declares.set(joueur_, new DeclareHandBelote());
            declaresPts.set(joueur_, DeclaresBelote.UNDEFINED);
        }
        tricks = new CustList<TrickBelote>();
    }
    public boolean simuler(SimulatingBelote _simu) {
        ended = false;
//        _simu.prepare();
//        _simu.sleepSimu(500);
//        _simu.beginDemo();
//        _simu.displayUserHand(mainUtilisateurTriee(_simu.getDisplaying()));
//        _simu.pause();
//        Bytes players_ = orderedPlayers(playerAfter(getDistribution().getDealer()));
        Bytes players_ = _simu.players(this);
        byte nbPl_ = getNombreDeJoueurs();
        if (rules.withBidPointsForAllPlayers()) {
//            byte joueur_ = playerAfter(getDistribution().getDealer());
            while (keepBidding()) {
                bidSimulate(_simu);
//                _simu.nextRound(bids.size(),nbPl_);
                if (_simu.stoppedRound(bids.size(),nbPl_) == AbstractSimulatingBelote.STATE_STOPPED) {
//                    _simu.stopDemo();
                    return false;
                }
//                if (_simu.stopped()) {
//                    _simu.stopDemo();
//                    return;
//                }
//                joueur_ = playerAfter(joueur_);
            }
        } else {
            boolean en_ = bidRoundSimulate(players_, _simu);
            en_ = secRoundSimulate(en_,players_,_simu);
            if (!en_) {
//                _simu.stopDemo();
                return false;
            }
        }
        return simuPlayCards(_simu);
    }

    boolean secRoundSimulate(boolean _enabled,Bytes _players,SimulatingBelote _simu) {
        if (!_enabled) {
            return false;
        }
        if (_simu.keepBidding(this)) {
//            finEncherePremierTour();
//            byte nbPl_ = getNombreDeJoueurs();
//            _simu.secRound(nbPl_);
            return bidRoundSimulate(_players,_simu);
        }
        return true;
    }

    boolean bidRoundSimulate(Bytes _players,SimulatingBelote _simu) {
        int nb_ = _players.size();
        for (int i = 0; i < nb_; i++) {
            bidSimulate(_simu);
            if (_simu.stopped() == AbstractSimulatingBelote.STATE_STOPPED) {
//            if (_simu.stoppedDemo() == AbstractSimulatingBelote.STATE_STOPPED
//            if (_simu.stopped()) {
                return false;
            }
        }
        return true;
    }

    boolean simuPlayCards(SimulatingBelote _simu) {
        if (_simu.noBid(this)) {
//            _simu.noBid();
            ended = true;
            return true;
        }
        if (rules.getDealing().getDiscarded() > 0) {
            _simu.ecarter(this);
        }
        simuComplete(_simu);
        int staterSimu_ = _simu.completerDonne(this);
//        if (changeFirstLeader()) {
//            _simu.declareSlam(taker,bid);
//        }
//        _simu.displayUserHand(mainUtilisateurTriee(_simu.getDisplaying()));
//        _simu.sleepSimu(1000);
//        _simu.displayLineReturn();
//        _simu.beginPlay();
        while (true) {
            Bytes pls_ = orderedPlayers((byte) staterSimu_);
            int s_ = pls_.size();
            for (int i = 0; i < s_; i++) {
                _simu.play(this);
                if (_simu.stopped() == AbstractSimulatingBelote.STATE_STOPPED) {
//                if (_simu.stoppedDemo() == AbstractSimulatingBelote.STATE_STOPPED)
//                if (_simu.stopped())
//                    _simu.stopDemo();
                    return false;
                }
            }
//            for (byte joueur_ : pls_) {
//                beforeCards(_simu, joueur_);
//                _simu.sleepSimu(1000);
////                _simu.pause();
////                currentPlayerHasPlayed(_simu.getInt(),joueur_);
//                endCards(_simu, joueur_, playCard(_simu.getInt()));
//                if (_simu.stopped()) {
//                    _simu.stopDemo();
//                    return;
//                }
//            }
            int next_ = _simu.ajouterDixDeDerPliEnCours(this);
//            _simu.displayTrickWinner(trickWinner);
//            _simu.sleepSimu(4000);
////            _simu.pause();
//            _simu.clearCarpet(getNombreDeJoueurs());
            if (getDistribution().hand().estVide()) {
                break;
            }
            staterSimu_ = next_;
        }
        /*Il y a dix de der*/
//        _simu.displayLastTrick(trickWinner);
//        _simu.endDeal();
        ended = true;
        return true;
    }

    private void simuComplete(SimulatingBelote _simu) {
        if (!rules.dealAll()) {
            Bytes players_ = getDeal().orderedPlayersBegin(getRegles());
            int step_ = _simu.dealCardsStep(deal.getDealer());
            for (int nb_: rules.getDealing().getDistributionFin()) {
                for (byte p:players_) {
                    int gotCards_ = nb_;
                    if(p==taker) {
                        gotCards_--;
                    }
                    step_ = _simu.dealCardStep(step_,gotCards_,p);
                }
            }
        }
    }

    public void firstRound() {
        if (premierTour()) {
            annulerAnnonces();
        }
    }

//    private void endCards(SimulatingBelote _simu, byte _joueur, CardBelote _cb) {
//        if (premierTour()) {
//            _simu.declare(_joueur,getAnnonce(_joueur));
//        }
//        _simu.belReb(cartesBeloteRebelote(),_cb, _joueur);
//        _simu.played(_joueur,_cb);
//        if(_joueur ==DealBelote.NUMERO_UTILISATEUR) {
//            _simu.displayUserHand(mainUtilisateurTriee(_simu.getDisplaying()));
//        }
//    }
//
//    private void beforeCards(SimulatingBelote _simu, byte _joueur) {
//        if (pliEnCoursEstVide()) {
//            _simu.firstCardPlaying(_joueur);
//        } else {
//            _simu.nextCardPlaying(_joueur);
//        }
//    }

    void bidSimulate(SimulatingBelote _simu) {
        if (!keepBidding()) {
            return;
        }
        _simu.bid(this);
//        _simu.actingBid(_p);
//        _simu.sleepSimu(500);
//        BidBeloteSuit contratTmp_ = _simu.getInt().strategieContrat(this);
//        _simu.actedBid(_p,contratTmp_);
//        ajouterContrat(contratTmp_);
    }

    public HandBelote mainUtilisateurTriee(DisplayingBelote _regles) {
        HandBelote main_ = new HandBelote();
        main_.ajouterCartes(getDistribution().hand());
//        if(!getBid().getBid().jouerDonne()) {
////            main_.setOrdre(_regles.getOrderBeforeBids());
//            main_.trier(_regles.getDisplaying().getSuits(), _regles.getDisplaying().isDecreasing(),_regles.getOrderBeforeBids());
//        } else if(getBid().getBid().getCouleurDominante()) {
//            main_.trier(_regles.getDisplaying().getSuits(), _regles.getDisplaying().isDecreasing(),couleurAtout());
//        } else {
////            main_.setOrdre(getBid().getBid().getOrdre());
//            main_.trier(_regles.getDisplaying().getSuits(), _regles.getDisplaying().isDecreasing(),_regles.getOrderBeforeBids());
//        }
        if (getBid().jouerDonne()) {
            main_.trier(_regles.getDisplaying().getSuits(), _regles.getDisplaying().isDecreasing(),getBid());
        } else {
            main_.trier(_regles.getDisplaying().getSuits(), _regles.getDisplaying().isDecreasing(),_regles.getOrderBeforeBids());
        }

        return main_;
    }

    public HandBelote strategieEcart() {
        HandBelote mainPreneur_ = getDistribution().hand(taker);
        return discarding(mainPreneur_);
    }
    private HandBelote discarding(HandBelote _taker) {
        return strategieEcart(_taker);
    }

    private HandBelote strategieEcart(HandBelote _taker) {
        int tailleChien_ = getDistribution().derniereMain().total();
        GameBeloteBid gb_ = new GameBeloteBid(_taker,rules,bid,false, new HandBelote());
        GameBeloteDiscard g_ = new GameBeloteDiscard(gb_,tailleChien_);
        return g_.strategieEcart();
    }



    public void ecarter(IntGameBelote _ia) {
        getDeal().hand(getPreneur()).ajouterCartes(deal.derniereMain());
        //On ajoute les cartes du chien au preneur pour en ecarter d'autres
        HandBelote mt_=_ia.strategieEcart(this);
        //Le preneur ecarte les cartes qu'il veut
        fwd(mt_);

        ajouterChelem(_ia.annoncerUnChelem(this));

//        setStarterIfSlam();
    }

    private void fwd(HandBelote _mt) {
        getDeal().hand(getPreneur()).supprimerCartes(_mt);
        progressingTrick = new TrickBelote(taker);
        ajouterCartesDansPliEnCours(_mt);
        tricks.add(progressingTrick);
    }

    void ajouterCartesDansPliEnCours(HandBelote _mt) {
        for(CardBelote cb_: _mt) {
            progressingTrick.ajouter(cb_);
        }
    }
    public boolean annoncerUnChelem() {
        HandBelote mainJoueur_ = getDistribution().hand(getPreneur());
        GameBeloteBid gb_ = new GameBeloteBid(mainJoueur_,rules,bid,false, new HandBelote());
        GameBeloteDiscard g_ = new GameBeloteDiscard(gb_,0);
        return g_.majorDominantCards();
    }
    public void ajouterChelemUtilisateur() {
        ajouterChelem(true);
    }
    void ajouterChelem(boolean _annonce) {
        if (_annonce) {
            bid.setPoints(RulesBelote.MOST);
        }
    }

    public void invaliderAjoutCarteEcart(CardBelote _carte) {
        progressingTrick.retirer(_carte);
        deal.hand(getPreneur()).ajouter(_carte);
    }
    public void ajouterCartesUtilisateur() {
        progressingTrick = new TrickBelote(getPreneur());
        deal.hand(getPreneur()).ajouterCartes(deal.derniereMain());
    }
    public int validateDiscard() {
//        int d_ = rules.getDealing().getDiscarded();
//        if (d_ > 0) {
//            tricks.add(progressingTrick);
//            setEntameurPremier();
//        }
        tricks.add(progressingTrick);
        return setEntameurPremier();
    }
    public void jouer(byte _joueur, CardBelote _ct) {
        deal.jouer(_joueur,_ct);
    }

    public boolean autorise(CardBelote _c) {
        return autorise().contient(_c);
    }

    public HandBelote autorise() {
        HandBelote main_=getDistribution().hand(playerHavingToPlay());
        return playableCards(main_.couleurs(bid));
    }

    public HandBelote cartesBeloteRebelote() {
        HandBelote cartes_ = new HandBelote();
        if(!bid.getCouleurDominante()) {
            return cartes_;
        }
        return GameBeloteCommonPlaying.cartesBeloteRebelote(bid);
    }
    public boolean autoriseBeloteRebelote() {
        HandBelote cartesAnnoncer_ = cartesBeloteRebelote();
        byte next_ = playerHavingToPlay();
        HandBelote cartesAnnconcees_ = getAnnoncesBeloteRebelote(next_);
        boolean cartesAbsentesAnnoncees_ = true;
        HandBelote mainJoueur_=getDistribution().hand(next_);
        for(CardBelote c: cartesAnnoncer_) {
            if (mainJoueur_.contient(c) || cartesAnnconcees_.contient(c)) {
                continue;
            }
            cartesAbsentesAnnoncees_ = false;
        }
        if(!cartesAbsentesAnnoncees_) {
            return false;
        }
        HandBelote main_ = new HandBelote();
        for(CardBelote c: cartesAnnoncer_) {
            //carte c: non annoncee
            if(!autorise(c)) {
                continue;
            }
            main_.ajouter(c);
        }
        return !main_.estVide();
    }
    public boolean playerHasAlreadyBidded(byte _player) {
        return playerHasAlreadyBidded(new DefGameBelote(),_player);
    }
    /**for multi player*/
    public boolean playerHasAlreadyBidded(IntGameBelote _g,byte _player) {
//        int nbBids_ = tailleContrats();
//        int lastPlayer_ = lastHasBid;
        if (hasBid(_player)) {
            return true;
        }
//        BidBeloteSuit bid_ =_g.strategieContrat(this);
//        ajouterContrat(bid_,_player);
//        if (getRegles().dealAll()) {
//            if (lastPlayer_ > -1 && (lastPlayer_ + 1) % getNombreDeJoueurs() != _player) {
//                return true;
//            }
//            lastBid = bid_;
//            return false;
//        }
//        if (nbBids_ == tailleContrats()) {
//            return true;
//        }
//        if (tailleContrats() == getNombreDeJoueurs()) {
//            finEncherePremierTour();
//        }
//        lastBid = bid(_g, _player);
        bid(_g);
        return false;
    }
    public BidBeloteSuit bid(IntGameBelote _g) {
        BidBeloteSuit bid_ =_g.strategieContrat(this);
        ajouterContrat(bid_);
//        lastBid = bid_;
        return bid_;
    }

    public boolean hasBid(byte _player) {
        int pl_ = (rules.getDealing().getId().getNextPlayer(deal.getDealer()) + bids.size()) % getNombreDeJoueurs();
        return pl_ != _player;
//        return lastHasBid > -1 && (lastHasBid + 1) % getNombreDeJoueurs() != _player;
    }

    //    public BidBeloteSuit getLastBid() {
//        return lastBid;
//    }
    public BidBeloteSuit strategieContrat() {
        GameBeloteBid g_ = getGameBeloteBid();
        return g_.strategieContrat();
    }

    public CustList<BidBeloteSuit> filter(CustList<BidBeloteSuit> _allow) {
        CustList<BidBeloteSuit> ls_ = new CustList<BidBeloteSuit>();
        for (BidBeloteSuit b: _allow) {
            if (b.estDemandable(getBid())) {
                ls_.add(b);
            }
        }
        return ls_;
    }
    public GameBeloteBid getGameBeloteBid() {
        byte numero_=playerHavingToBid();
        HandBelote mj_=getDistribution().hand(numero_);
        HandBelote last_ = new HandBelote();
        if (!rules.withBidPointsForAllPlayers()) {
            last_.ajouter(deal.derniereMain().premiereCarte());
        }
        return new GameBeloteBid(mj_,rules, bid,bids.size()>=getNombreDeJoueurs(),last_);
    }

//    public void finEncherePremierTour() {
//        endBidsFirstRound=true;
//    }

    public void ajouterContrat(BidBeloteSuit _c) {
        //
//        if (lastHasBid != -1 && lastHasBid == _t) {
//        if (lastHasBid != -1 && (lastHasBid + 1) % getNombreDeJoueurs() != _t) {
//        if (hasBid(_t)) {
//            return;
//        }
//        lastHasBid = _t;
        bids.add(_c);
        if (_c.jouerDonne()) {
            setBid(_c);
            setPreneur((byte) ((deal.getDealer() + bids.size()) % getNombreDeJoueurs()));
        }
    }
    public int tailleContrats() {
        return bids.size();
    }
    public BidBeloteSuit contrat(int _i) {
        return bids.get(_i);
    }
    public CustList<BidBeloteSuit> tousContrats() {
        return bids;
    }

    public void setBid(BidBeloteSuit _pcontrat) {
        bid=_pcontrat;
    }
    public boolean keepBidding() {
        if (getRegles().withBidPointsForAllPlayers()) {
            return keepBiddingDealAll();
        }
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (BidBeloteSuit e: getGameBeloteBid().allowedBids()) {
            if (!e.jouerDonne()) {
                continue;
            }
            if (e.estDemandable(bid)) {
                return tailleContrats() < nombreDeJoueurs_ + nombreDeJoueurs_;
            }
        }
        return false;
    }

    private boolean keepBiddingDealAll() {
        int maxPts_ = RulesBelote.getPoints().last();
        if (bid.getPoints() == maxPts_) {
            return false;
        }
        if (bids.size() < getNombreDeJoueurs()) {
            return true;
        }
        boolean fold_ = true;
        int iter_ = IndexConstants.FIRST_INDEX+1;
        while (iter_ < getNombreDeJoueurs()) {
            //iter_ >= 1 => -iter_ <= -1 => bids.size()-iter_ <= bids.size()-1
            //bids.size() >= getNombreDeJoueurs() => bids.size() - iter_ >= getNombreDeJoueurs() - iter_
            //getNombreDeJoueurs() >= iter_ => getNombreDeJoueurs() - iter_ >= 0
            //bids.size() - iter_ >= getNombreDeJoueurs() - iter_ >= 0 => bids.size() - iter_ >= 0
            if (bids.get(bids.size()-iter_).jouerDonne()) {
                fold_ = false;
                break;
            }
            iter_++;
        }
        return !fold_;
    }

    public boolean keepPlayingCurrentTrick() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        return progressingTrick.total() < nombreDeJoueurs_;
    }
    public boolean keepPlayingCurrentGame() {
        for (byte p: getDeal().orderedPlayersBegin(getRegles())) {
            if (!getDistribution().hand(p).estVide()) {
                return true;
            }
        }
        return false;
    }
    public int setEntameurPremier() {
        firstStarter();
        progressingTrick=new TrickBelote(starter);

        return getEntameur();
    }

    private void firstStarter() {
        if (changeFirstLeader()) {
            setEntameur(getPreneur());
        } else {
            byte nombreDeJoueurs_ = getNombreDeJoueurs();
            setEntameur((byte)((deal.getDealer()+1)%nombreDeJoueurs_));
        }
    }

    public boolean changeFirstLeader() {
        return getRegles().dealAll() && getBid().getPoints() >= RulesBelote.MOST;
    }

//    CustList<BidBeloteSuit> maximumBid() {
//        CustList<BidBeloteSuit> contratsMax_ = new CustList<BidBeloteSuit>();
//        BidBeloteSuit bid_ = new BidBeloteSuit();
//        for (BidBeloteSuit b: getGameBeloteBid().allowedBids()) {
//            if (!b.jouerDonne()) {
//                continue;
//            }
//            if (b.getBid().getForce() == bid_.getBid().getForce()) {
//                contratsMax_.add(b);
//            }
//            if(b.estDemandable(bid_)) {
//                contratsMax_.clear();
//                contratsMax_.add(b);
//                bid_ = b;
//            }
//        }
//        return contratsMax_;
//    }
    public void setPreneur(byte _ppreneur) {
        taker=_ppreneur;
    }
    public byte getPreneur() {
        return taker;
    }
    /**Appelee au debut d'une partie*/
    public void setEntameur(byte _i) {
        starter=_i;
    }

    public boolean premierTour() {
        if (rules.getDealing().getDiscarded() > 0) {
            return tricks.size() <= 1;
        }
        return tricks.size()==0;
    }

    public byte getEntameur() {
        return starter;
    }

    public boolean pliEnCoursEstVide() {
        return getPliEnCours().estVide();
    }

    public Suit couleurDemandee() {
        return getPliEnCours().couleurDemandee();
    }

    public TrickBelote getPliEnCours() {
        return getProgressingTrick();
    }
    public boolean annoncerBeloteRebelote(CardBelote _ct) {
        return cartesBeloteRebelote().contient(_ct) && autoriseBeloteRebelote();
    }
    public DeclareHandBelote strategieAnnonces() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().hand(numero_);
        GameBeloteTrickInfo info_ = getDoneTrickInfo();
        GameBeloteTeamsRelation team_ = getTeamsRelation();
        GameBeloteDeclaring g_ = new GameBeloteDeclaring(info_,team_,mainJoueur_,declares);
        return g_.strategieAnnonces();
    }
    public void annoncer() {
        byte numero_=playerHavingToPlay();
        declares.set(numero_, strategieAnnonces());
        declaresPts.set(numero_,declares.get(numero_).getDeclare());
    }
    public DeclareHandBelote getAnnonce(byte _joueurCourant) {
        return declares.get(_joueurCourant);
    }
    public void annulerAnnonces() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().hand(numero_);
        GameBeloteTrickInfo info_ = getDoneTrickInfo();
        GameBeloteTeamsRelation team_ = getTeamsRelation();
        GameBeloteDeclaring g_ = new GameBeloteDeclaring(info_,team_,mainJoueur_,declares);
        g_.annulerAnnonces();
    }
    public void setAnnoncesBeloteRebelote(CardBelote _carte) {
        byte next_ = playerHavingToPlay();
        declaresBeloteRebelote.get(next_).ajouter(_carte);
        if(declaresBeloteRebelote.get(next_).contientCartes(GameBeloteCommonPlaying.cartesBeloteRebelote(bid))) {
            declaresBeloteRebelotePts.set(next_, (short) DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints());
        }
    }
    public HandBelote getAnnoncesBeloteRebelote(byte _numero) {
        return declaresBeloteRebelote.get(_numero);
    }
    public void ajouterUneCarteDansPliEnCoursPreneur(CardBelote _c) {
        jouer(taker,_c);
        progressingTrick.ajouter(_c);
    }
    public void ajouterUneCarteDansPliEnCoursJoue(CardBelote _c) {
        jouer(playerHavingToPlay(),_c);
        progressingTrick.ajouter(_c);
    }
    public boolean currentPlayerHasPlayed(byte _player) {
        return currentPlayerHasPlayed(new DefGameBelote(),_player);
    }
    /**for multi player*/
    public boolean currentPlayerHasPlayed(IntGameBelote _ia,byte _player) {
        if (aJoue(_player)) {
            return true;
        }
        playCard(_ia);
        return false;
    }

    public boolean aJoue(byte _player) {
        return getPliEnCours().aJoue(_player, getNombreDeJoueurs());
    }

    public CardBelote playCard(IntGameBelote _ia) {
        CardBelote playedCard_ = _ia.strategieJeuCarteUnique(this);
        tryDeclareBeloteRebelote(playedCard_);
        premierTourAnnonce();
        ajouterUneCarteDansPliEnCoursJoue(playedCard_);
        return playedCard_;
    }

    public void premierTourAnnonce() {
        if (premierTour()) {
            annoncer();
        }
    }

    public void tryDeclareBeloteRebelote(CardBelote _playedCard) {
        if (annoncerBeloteRebelote(_playedCard)) {
            setAnnoncesBeloteRebelote(_playedCard);
        }
    }

//    public CardBelote getCarteJouee() {
//        return playedCard;
//    }
    public CardBelote strategieJeuCarteUnique() {
        if(progressingTrick.estVide()) {
            return entame();
        }
        return enCours();
    }
    private CardBelote entame() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().hand(numero_);
        GameBeloteTrickInfo info_ = getDoneTrickInfo();
        GameBeloteTeamsRelation team_ = getTeamsRelation();
        GameBeloteBeginTrick g_ = new GameBeloteBeginTrick(info_,team_,mainJoueur_);
        return g_.entame();
    }

    private CardBelote enCours() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().hand(numero_);
        GameBeloteTrickInfo info_ = getDoneTrickInfo();
        GameBeloteTeamsRelation team_ = getTeamsRelation();
        GameBeloteProgTrick g_ = new GameBeloteProgTrick(info_,team_,mainJoueur_);
        return g_.enCours();
    }

    public GameBeloteTrickInfo getDoneTrickInfo() {
        Ints handLengths_ = new Ints();
        for (HandBelote h: deal) {
            handLengths_.add(h.total());
        }
        GameBeloteTrickInfo gameTarotTrickInfo_ = new GameBeloteTrickInfo(progressingTrick, tricks,
                declares,declaresBeloteRebelote,
                bid,handLengths_);
        HandBelote last_ = new HandBelote();
        if (!rules.withBidPointsForAllPlayers()) {
            last_.ajouter(deal.derniereMain().premiereCarte());
        }
        gameTarotTrickInfo_.addSeenDeck(last_,getTeamsRelation());
        return gameTarotTrickInfo_;
    }
    HandBelote playableCards(IdMap<Suit,HandBelote> _repartitionMain) {
        /*Ensemble des cartes jouees sur ce pli*/
        GameBeloteCommonPlaying g_ = new GameBeloteCommonPlaying(getDoneTrickInfo(),getTeamsRelation());
        HandBelote pl_ = g_.playableCards(_repartitionMain);
        reason = g_.getReason();
        return pl_;
    }

    public ReasonPlayBelote getReason() {
        return reason;
    }

//    /**A la fin d'un pli on ramasse les cartes
//    et on les ajoute dans des tas*/
//    public void ajouterUneCarteDansPliEnCours(CardBelote _c) {
//        progressingTrick.ajouter(_c);
//    }

    public Suit couleurAtout() {
        return bid.getSuit();
    }
    public byte getRamasseur() {
        return trickWinner;
    }

    public EndBeloteGame getEndBeloteGame() {
        GameBeloteTeamsRelation t_ = getTeamsRelation();
        return new EndBeloteGame(t_,declaresPts,declaresBeloteRebelotePts,wonLastTrick,bid,tricks);
    }

    public GameBeloteTeamsRelation getTeamsRelation() {
        return new GameBeloteTeamsRelation(taker,rules);
    }

    public int ajouterDixDeDerPliEnCours() {
        firstRound();
        trickWinner=progressingTrick.getRamasseur(bid);
        tricks.add(progressingTrick);
        starter=trickWinner;
        progressingTrick=new TrickBelote(starter);
        if (getDistribution().hand().estVide()) {
            wonLastTrick.set(trickWinner, BoolVal.TRUE);
        }
        return progressingTrick.getEntameur();
    }

    public boolean getDixDeDer(byte _b) {
        return wonLastTrick.get(_b) == BoolVal.TRUE;
    }

    public HandBelote empiler() {
        HandBelote m=new HandBelote();
        if(!getBid().jouerDonne()) {
            for(HandBelote main_:getDistribution()) {
                m.ajouterCartes(main_);
            }
        } else {
            for(TrickBelote pli_:tricks) {
                m.ajouterCartes(pli_.getCartes());
            }
            if (rules.splitHand()) {
                m.ajouterCartes(HandBelote.low(getDistribution().derniereMain()));
            }
        }
        return m;
    }
    public void restituerMainsDepartRejouerDonne() {
        if (tricks.isEmpty()) {
            initPartie();
            return;
        }
        byte nb_ = getNombreDeJoueurs();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nb_; joueur_++) {
            getDistribution().supprimerCartes(joueur_);
        }
        for(TrickBelote pli_:tricks.mid(RulesBelote.offset(rules))) {
            for(CardBelote carte_:pli_) {
                getDistribution().ajouter(pli_.joueurAyantJoue(carte_),carte_);
            }
        }
        TricksHandsBelote.endRestore(getDistribution().getDeal(),taker,tricks,rules);
        initPartie();
    }

    public TrickBelote discardedCards() {
        return discardedCards(getTricks(), getPliEnCours());
    }

    public static TrickBelote discardedCards(CustList<TrickBelote> _tricks, TrickBelote _pliEnCours) {
        TrickBelote discardedCards_;
        if (_tricks.isEmpty()) {
            discardedCards_ = _pliEnCours;
        } else {
            discardedCards_ = _tricks.first();
        }
        return discardedCards_;
    }

    public boolean noPlayed() {
        if (rules.getDealing().getDiscarded() > 0) {
            return getTricks().isEmpty();
        }
        return noPlayedClassic();
    }

    public boolean noPlayedClassic() {
        return getTricks().isEmpty() && pliEnCoursEstVide();
    }
    Bytes orderedPlayers(byte _leader) {
        return rules.getDealing().getId().getSortedPlayers(_leader);
    }

    public byte playerHavingToBid() {
        return (byte)((getDistribution().getDealer()+1+bids.size())%getNombreDeJoueurs());
    }
    public byte playerHavingToPlay() {
        return getPliEnCours().getNextPlayer(getNombreDeJoueurs());
    }
    public byte playerAfter(byte _player) {
        return rules.getDealing().getId().getNextPlayer(_player);
    }


    public boolean isSameTeam(Bytes _players) {
        GameBeloteTeamsRelation g_ = new GameBeloteTeamsRelation(taker,rules);
        return g_.isSameTeam(_players);
    }
    public CustList<Bytes> playersBelongingToSameTeam() {
        GameBeloteTeamsRelation g_ = new GameBeloteTeamsRelation(taker,rules);
        return g_.playersBelongingToSameTeam();
    }


    public BidBeloteSuit getBid() {
        return bid;
    }

    public DealBelote getDeal() {
        return deal;
    }

    public void setDeal(DealBelote _deal) {
        deal = _deal;
    }

    public CustList<DeclareHandBelote> getDeclares() {
        return declares;
    }

    public void setDeclares(CustList<DeclareHandBelote> _declares) {
        declares = _declares;
    }

    public CustList<HandBelote> getDeclaresBeloteRebelote() {
        return declaresBeloteRebelote;
    }

    public void setDeclaresBeloteRebelote(CustList<HandBelote> _declaresBeloteRebelote) {
        declaresBeloteRebelote = _declaresBeloteRebelote;
    }

    public CustList<BoolVal> getWonLastTrick() {
        return wonLastTrick;
    }

    public void setWonLastTrick(CustList<BoolVal> _wonLastTrick) {
        wonLastTrick = _wonLastTrick;
    }

    public boolean addCurrentTrick() {
        return !tricks.isEmpty() || RulesBelote.offset(getRules()) == 0;
    }
    public CustList<TrickBelote> getTricks() {
        return tricks;
    }

    public void setTricks(CustList<TrickBelote> _tricks) {
        tricks = _tricks;
    }

    public TrickBelote getProgressingTrick() {
        return progressingTrick;
    }

    public void setProgressingTrick(TrickBelote _progressingTrick) {
        progressingTrick = _progressingTrick;
    }

    public CustList<BidBeloteSuit> getBids() {
        return bids;
    }

    public void setBids(CustList<BidBeloteSuit> _bids) {
        bids = _bids;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long _number) {
        number = _number;
    }

    public RulesBelote getRules() {
        return rules;
    }

    public void setRules(RulesBelote _rules) {
        rules = _rules;
    }

    public void setType(GameType _type) {
        type = _type;
    }

    public void setScores(Shorts _scores) {
        scores = _scores;
    }

    public String getError() {
        return error;
    }

    public void setError(String _error) {
        error = _error;
    }

    public boolean isEnded() {
        return ended;
    }
}
