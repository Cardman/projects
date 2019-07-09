package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.consts.GameType;
import cards.consts.Suit;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EnumMap;
import code.util.*;
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
    private byte taker=CustList.INDEX_NOT_FOUND_ELT;
    /** Ce sont les primes, miseres ou poignees annoncees par le(s) joueur(s)*/
    private CustList<DeclareHandBelote> declares=new CustList<DeclareHandBelote>();
    private CustList<DeclaresBelote> declaresPts=new CustList<DeclaresBelote>();
    private CustList<HandBelote> declaresBeloteRebelote = new CustList<HandBelote>();
    private Shorts declaresBeloteRebelotePts = new Shorts();
    private BooleanList wonLastTrick = new BooleanList();
    /**Le contrat permet de dire quel va etre le deroulement
    de la partie*/
    private BidBeloteSuit bid=new BidBeloteSuit();
    /**Ce sont les plis faits par les joueurs*/
    private CustList<TrickBelote> tricks = new CustList<TrickBelote>();
    /**Fin du premier tour d'enchere a la belote<br/>
    est vrai si et seulement si c'est la fin des encheres de premier tour*/
    private boolean endBidsFirstRound;
    /**PliBelote en cours d'etre joue*/
    private TrickBelote progressingTrick = new TrickBelote(CustList.INDEX_NOT_FOUND_ELT);

    /**Entameur du pli qui est en cours d'etre joue*/
    private byte starter;
    /**Ensembe des contrats annonces*/
    private CustList<BidBeloteSuit> bids=new CustList<BidBeloteSuit>();
    /**Ramasseur du pli qui vient d'etre joue*/
    private byte trickWinner;
    /**Scores cumules au cours des parties
    Chaque nombre (Short) represente un score pour le joueur*/
    private Shorts scores=new Shorts();
    private boolean simulationWithBids;
    /**Nombre de fois qu'a ete joue la partie (partie fini)*/
    private long number;
    private RulesBelote rules=new RulesBelote();
    private byte lastHasBid = CustList.INDEX_NOT_FOUND_ELT;

    private BidBeloteSuit lastBid=new BidBeloteSuit();

    private CardBelote playedCard = CardBelote.WHITE;

    private String error = "";
    /**Constructeur permettant le chargement d'une partie de belote*/
    public GameBelote() {}

    public GameBelote(GameType _type, DealBelote _donne, RulesBelote _regles) {
        type=_type;
        deal=_donne;
        rules = _regles;
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i_ = CustList.FIRST_INDEX;i_<nombreJoueurs_;i_++) {
            scores.add((short) 0);
        }
        //A la belote le jeu se deroule avec deux equipes de deux joueurs
        //Chaque joueur fait equipe avec celui qui est en face
        /*Initialise les annonces*/
        for (int j_ = CustList.FIRST_INDEX;j_<nombreJoueurs_;j_++) {
            declaresBeloteRebelote.add(new HandBelote());
            declaresBeloteRebelotePts.add((short) 0);
            wonLastTrick.add(false);
            declares.add(new DeclareHandBelote());
            declaresPts.add(DeclaresBelote.UNDEFINED);
        }
    }

    void loadGame() {
        byte player_ = playerAfter(deal.getDonneur());
        taker = CustList.INDEX_NOT_FOUND_ELT;
        BidBeloteSuit bid_ = new BidBeloteSuit();
        if (rules.dealAll()) {
            endBidsFirstRound = false;
            for (BidBeloteSuit b: bids) {
                if (b.getPoints() > bid_.getPoints()) {
                    taker = player_;
                    bid_ = b;
                }
                player_ = playerAfter(player_);
            }
            bid = bid_;
        } else {
            endBidsFirstRound = bids.size() >= getNombreDeJoueurs();
            for (BidBeloteSuit b: bids) {
                if (b.strongerThan(bid_)) {
                    taker = player_;
                    bid_ = b;
                }
                player_ = playerAfter(player_);
            }
            bid = bid_;
        }
        if (!tricks.isEmpty()) {
            starter = progressingTrick.getEntameur();
            trickWinner = progressingTrick.getEntameur();
        } else if (!progressingTrick.estVide()) {
            starter = progressingTrick.getEntameur();
            trickWinner = progressingTrick.getEntameur();
        } else if (keepBidding()) {
            starter = playerAfter(deal.getDonneur());
            trickWinner = starter;
        } else {
            if (bid.getPoints() >= HandBelote.pointsTotauxDixDeDer(bid)) {
                starter = progressingTrick.getEntameur();
                trickWinner = progressingTrick.getEntameur();
            } else {
                starter = playerAfter(deal.getDonneur());
                trickWinner = starter;
            }
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
            declaresPts.set(p, declares.get(p).getAnnonce());
        }
    }

    public RulesBelote getRegles() {
        return getRules();
    }
    /**Renvoie le nombre de joueurs jouant a la partie*/
    public byte getNombreDeJoueurs() {
        return (byte) rules.getRepartition().getNombreJoueurs();
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
        for (byte p: orderedPlayers(deal.getDonneur())) {
            if (deal.main(p).total() < getRegles().getRepartition().getNombreCartesParJoueur()) {
                completed_ = false;
                break;
            }
        }
        if (getRegles().dealAll()) {
            int pts_ = getContrat().getPoints();
            if (pts_ >= HandBelote.pointsTotauxDixDeDer(getContrat())) {
                setEntameur(getPreneur());
            } else {
                byte nombreDeJoueurs_ = getNombreDeJoueurs();
                setEntameur((byte)((deal.getDonneur()+1)%nombreDeJoueurs_));
            }
            return false;
        }
        if (completed_) {
            return true;
        }
        completerDonne();
        return false;
    }
    public void completerDonne() {
        deal.completerDonne(taker,rules);
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        setEntameur((byte)((deal.getDonneur()+1)%nombreDeJoueurs_));
    }
    public void initPartie() {
        taker=-1;
        bids=new CustList<BidBeloteSuit>();
        endBidsFirstRound=false;
        bid=new BidBeloteSuit();
        progressingTrick=new TrickBelote((byte) -1);
        Shorts scores_=getScores();
        byte nombreJoueurs_=getNombreDeJoueurs();
        for (int joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
            /*Initialise les annonces*/
            scores_.set(joueur_, (short) 0);
            declaresBeloteRebelote.set(joueur_, new HandBelote());
            declaresBeloteRebelotePts.set(joueur_, (short) 0);
            wonLastTrick.set(joueur_, false);
            declares.set(joueur_, new DeclareHandBelote());
            declaresPts.set(joueur_, DeclaresBelote.UNDEFINED);
        }
        tricks = new CustList<TrickBelote>();
    }
    public void simuler() {
        simulationWithBids = false;
        BidBeloteSuit contratTmp_;
        Bytes players_ = orderedPlayers(playerAfter(getDistribution().getDonneur()));
        if (rules.dealAll()) {
            byte joueur_ = playerAfter(getDistribution().getDonneur());
            while (true) {
                contratTmp_ = strategieContrat();
                ajouterContrat(contratTmp_, joueur_);
                if (!keepBidding()) {
                    break;
                }
                joueur_ = playerAfter(joueur_);
            }
        } else {
            boolean finished_ = false;
            for (byte joueur_ : players_) {
                contratTmp_ = strategieContrat();
                ajouterContrat(contratTmp_, joueur_);
                if (!keepBidding()) {
                    finished_ = true;
                    break;
                }
            }
            if (!finished_) {
                finEncherePremierTour();
                for (byte joueur_ : players_) {
                    contratTmp_ = strategieContrat();
                    ajouterContrat(contratTmp_, joueur_);
                    if (!keepBidding()) {
                        break;
                    }
                }
            }
        }
        if (!bid.jouerDonne()) {
            return;
        }
        simulationWithBids = true;
        completerDonne();
        while (true) {
            setPliEnCours();
            for (byte joueur_ : orderedPlayers(starter)) {
                currentPlayerHasPlayed(joueur_);
            }
            if (premierTour()) {
                annulerAnnonces();
            }
            if (getDistribution().main().estVide()) {
                /*Il y a dix de der*/
                ajouterPliEnCours();
                setDixDeDer(getRamasseur());
                break;
            }
            ajouterPliEnCours();
        }
    }
    public HandBelote mainUtilisateurTriee(DisplayingBelote _regles) {
        HandBelote main_ = new HandBelote();
        main_.ajouterCartes(getDistribution().main());
        if(!getContrat().getEnchere().jouerDonne()) {
            main_.setOrdre(_regles.getOrdreAvantEncheres());
            main_.trier(_regles.getCouleurs(),_regles.getDecroissant(),_regles.getOrdreAvantEncheres());
        } else if(getContrat().getEnchere().getCouleurDominante()) {
            main_.trier(_regles.getCouleurs(),_regles.getDecroissant(),couleurAtout());
        } else {
            main_.setOrdre(getContrat().getEnchere().getOrdre());
            main_.trier(_regles.getCouleurs(),_regles.getDecroissant(),_regles.getOrdreAvantEncheres());
        }
        return main_;
    }
    public void jouer(CardBelote _carteJouee) {
        deal.jouer(_carteJouee);
    }

    public void jouer(byte _joueur, CardBelote _ct) {
        deal.jouer(_joueur,_ct);
    }

    public boolean getSimulationAvecContrats() {
        return simulationWithBids;
    }

    public boolean autorise(CardBelote _c) {
        HandBelote main_=getDistribution().main(playerHavingToPlay());
        return playableCards(main_.couleurs(bid)).contient(_c);
    }

    public HandBelote cartesBeloteRebelote() {
        HandBelote cartes_ = new HandBelote();
        if(!bid.getCouleurDominante()) {
            return cartes_;
        }
        return GameBeloteCommonPlaying.cartesBeloteRebelote(bid);
    }
    public boolean autoriseBeloteRebelote() {
        return autoriseBeloteRebelote(DealBelote.NUMERO_UTILISATEUR);
    }
    public boolean autoriseBeloteRebelote(byte _numero) {
        HandBelote cartesAnnoncer_ = cartesBeloteRebelote();
        HandBelote cartesAnnconcees_ = getAnnoncesBeloteRebelote(_numero);
        boolean cartesAbsentesAnnoncees_ = true;
        HandBelote mainJoueur_=getDistribution().main(_numero);
        for(CardBelote c: cartesAnnoncer_) {
            if(mainJoueur_.contient(c)) {
                continue;
            }
            if(cartesAnnconcees_.contient(c)) {
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
    /**for multi player*/
    public boolean playerHasAlreadyBidded(byte _player) {
        BidBeloteSuit bid_ =strategieContrat();
        int nbBids_ = tailleContrats();
        int lastPlayer_ = lastHasBid;
        ajouterContrat(bid_,_player);
        if (getRegles().dealAll()) {
            if (lastPlayer_ > -1 && (lastPlayer_ + 1) % getNombreDeJoueurs() != _player) {
                return true;
            }
            lastBid = bid_;
            return false;
        }
        if (nbBids_ == tailleContrats()) {
            return true;
        }
        if (tailleContrats() == getNombreDeJoueurs()) {
            finEncherePremierTour();
        }
        lastBid = bid_;
        return false;
    }
    public BidBeloteSuit getLastBid() {
        return lastBid;
    }
    public BidBeloteSuit strategieContrat() {
        GameBeloteBid g_ = getGameBeloteBid();
        return g_.strategieContrat();
    }

    public GameBeloteBid getGameBeloteBid() {
        byte numero_=playerHavingToBid();
        HandBelote mj_=getDistribution().main(numero_);
        HandBelote last_ = new HandBelote();
        if (!rules.dealAll()) {
            last_.ajouter(deal.derniereMain().premiereCarte());
        }
        return new GameBeloteBid(mj_,rules, bid,endBidsFirstRound,last_);
    }

    public void finEncherePremierTour() {
        endBidsFirstRound=true;
    }

    public void ajouterContrat(BidBeloteSuit _c, byte _t) {
        if (lastHasBid == -1) {
            lastHasBid = _t;
        } else if (lastHasBid == _t) {
            return;
        }
        lastHasBid = _t;
        bids.add(_c);
        if (_c.jouerDonne()) {
            setContrat(_c);
            setPreneur(_t);
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
    public BidBeloteSuit getContrat() {
        return bid;
    }
    public void setContrat(BidBeloteSuit _pcontrat) {
        bid=_pcontrat;
    }
    public boolean keepBidding() {
        if (getRegles().dealAll()) {
            int lastBid_ = bids.size();
            lastBid_--;
            int iter_ = CustList.FIRST_INDEX;
            int maxPts_ = RulesBelote.getPoints().last();
            if (bid.getPoints() == maxPts_) {
                return false;
            }
            if (bids.size() >= getNombreDeJoueurs()) {
                boolean fold_ = true;
                int i_ = lastBid_;
                while (true) {
                    iter_++;
                    if (iter_ >= getNombreDeJoueurs()) {
                        break;
                    }
                    //iter_ < getNombreDeJoueurs() <= bids.size() => iter_ < bids.size()
                    //iter_ + i_ == bids.size() && iter_ <= bids.size() => i_ >= 0
                    if (bids.get(i_).jouerDonne()) {
                        fold_ = false;
                        break;
                    }
                    i_--;
                }
                return !fold_;
            }
            return true;
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
    public boolean keepPlayingCurrentTrick() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        return progressingTrick.total() < nombreDeJoueurs_;
    }
    public boolean keepPlayingCurrentGame() {
        for (byte p: orderedPlayers(starter)) {
            if (!getDistribution().main(p).estVide()) {
                return true;
            }
        }
        return false;
    }
    CustList<BidBeloteSuit> maximumBid() {
        CustList<BidBeloteSuit> contratsMax_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        for (BidBeloteSuit b: getGameBeloteBid().allowedBids()) {
            if (!b.jouerDonne()) {
                continue;
            }
            if (b.getEnchere().getForce() == bid_.getEnchere().getForce()) {
                contratsMax_.add(b);
            }
            if(b.estDemandable(bid_)) {
                contratsMax_.clear();
                contratsMax_.add(b);
                bid_ = b;
            }
        }
        return contratsMax_;
    }
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

    void setTrickWinner(byte _trickWinner) {
        trickWinner = _trickWinner;
    }

    void setEndBidsFirstRound(boolean _endBidsFirstRound) {
        endBidsFirstRound = _endBidsFirstRound;
    }

    public boolean premierTour() {
        return tricks.size()==0;
    }
    /**Appele au debut d'un pli mais pas d'une partie*/
    void setEntameur() {
        starter=trickWinner;
    }
    public byte getEntameur() {
        return starter;
    }

    public void setPliEnCours() {
        progressingTrick=new TrickBelote(new HandBelote(),starter);

    }
    public TrickBelote getPliEnCours() {
        return getProgressingTrick();
    }
    public boolean annoncerBeloteRebelote(byte _numeroJoueur, CardBelote _ct) {
        return cartesBeloteRebelote().contient(_ct) && autoriseBeloteRebelote(_numeroJoueur);
    }
    public DeclareHandBelote strategieAnnonces() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().main(numero_);
        GameBeloteTrickInfo info_ = getDoneTrickInfo();
        GameBeloteTeamsRelation team_ = getTeamsRelation();
        GameBeloteDeclaring g_ = new GameBeloteDeclaring(info_,team_,mainJoueur_,declares);
        return g_.strategieAnnonces();
    }
    public void annoncer(byte _joueurCourant) {
        declares.set(_joueurCourant, strategieAnnonces());
        declaresPts.set(_joueurCourant,declares.get(_joueurCourant).getAnnonce());
    }
    public DeclareHandBelote getAnnonce(byte _joueurCourant) {
        return declares.get(_joueurCourant);
    }
    public void annulerAnnonces() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().main(numero_);
        GameBeloteTrickInfo info_ = getDoneTrickInfo();
        GameBeloteTeamsRelation team_ = getTeamsRelation();
        GameBeloteDeclaring g_ = new GameBeloteDeclaring(info_,team_,mainJoueur_,declares);
        g_.annulerAnnonces();
    }
    public void setAnnoncesBeloteRebelote(byte _b, CardBelote _carte) {
        declaresBeloteRebelote.get(_b).ajouter(_carte);
        if(declaresBeloteRebelote.get(_b).contientCartes(GameBeloteCommonPlaying.cartesBeloteRebelote(bid))) {
            declaresBeloteRebelotePts.set(_b, (short) DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints());
        }
    }
    public HandBelote getAnnoncesBeloteRebelote(byte _numero) {
        return declaresBeloteRebelote.get(_numero);
    }
    public void ajouterUneCarteDansPliEnCours(byte _numero, CardBelote _c) {
        jouer(_numero,_c);
        ajouterUneCarteDansPliEnCours(_c);
    }
    /**for multi player*/
    public boolean currentPlayerHasPlayed(byte _player) {
        if (getPliEnCours().aJoue(_player,getNombreDeJoueurs())) {
            return true;
        }
        playedCard = strategieJeuCarteUnique();
        tryDeclareBeloteRebelote(_player, playedCard);
        if (premierTour()) {
            annoncer(_player);
        }
        ajouterUneCarteDansPliEnCours(_player, getCarteJouee());
        return false;
    }

    void tryDeclareBeloteRebelote(byte _player, CardBelote _playedCard) {
        if (annoncerBeloteRebelote(_player, _playedCard)) {
            setAnnoncesBeloteRebelote(_player, _playedCard);
        }
    }

    public CardBelote getCarteJouee() {
        return playedCard;
    }
    public CardBelote strategieJeuCarteUnique() {
        if(progressingTrick.estVide()) {
            return entame();
        }
        return enCours();
    }
    private CardBelote entame() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().main(numero_);
        GameBeloteTrickInfo info_ = getDoneTrickInfo();
        GameBeloteTeamsRelation team_ = getTeamsRelation();
        GameBeloteBeginTrick g_ = new GameBeloteBeginTrick(info_,team_,mainJoueur_);
        return g_.entame();
    }

    private CardBelote enCours() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().main(numero_);
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
        if (!rules.dealAll()) {
            last_.ajouter(deal.derniereMain().premiereCarte());
        }
        gameTarotTrickInfo_.addSeenDeck(last_,getTeamsRelation());
        return gameTarotTrickInfo_;
    }
    HandBelote playableCards(EnumMap<Suit,HandBelote> _repartitionMain) {
        /*Ensemble des cartes jouees sur ce pli*/
        GameBeloteCommonPlaying g_ = new GameBeloteCommonPlaying(getDoneTrickInfo(),getTeamsRelation());
        return g_.playableCards(_repartitionMain);
    }

    /**A la fin d'un pli on ramasse les cartes
    et on les ajoute dans des tas*/
    public void ajouterUneCarteDansPliEnCours(CardBelote _c) {
        progressingTrick.ajouter(_c);
    }

    public Suit couleurAtout() {
        return bid.getCouleur();
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
    void ajouterPliEnCours() {
        trickWinner=progressingTrick.getRamasseur(bid);
        tricks.add(progressingTrick);
        if(!getDistribution().main().estVide()) {
            setEntameur();
        }
    }
    public void ajouterDixDeDerPliEnCours() {
        ajouterPliEnCours();
        setDixDeDer(trickWinner);
    }
    void setDixDeDer(byte _b) {
        if(!getDistribution().main().estVide()) {
            return;
        }
        wonLastTrick.set(_b, true);
    }
    public boolean getDixDeDer(byte _b) {
        return wonLastTrick.get(_b);
    }

    public HandBelote empiler() {
        HandBelote m=new HandBelote();
        if(!getContrat().jouerDonne()) {
            for(HandBelote main_:getDistribution()) {
                m.ajouterCartes(main_);
            }
        } else {
            for(TrickBelote pli_:tricks) {
                m.ajouterCartes(pli_.getCartes());
            }
        }
        return m;
    }
    public void restituerMainsDepartRejouerDonne(CustList<TrickBelote> _plisFaits,byte _nombreJoueurs) {
        if (_plisFaits.isEmpty()) {
            return;
        }
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
            getDistribution().supprimerCartes(joueur_);
        }
        for(TrickBelote pli_:_plisFaits) {
            for(CardBelote carte_:pli_) {
                getDistribution().ajouter(pli_.joueurAyantJoue(carte_),carte_);
            }
        }
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
            getDistribution().supprimerCartes(joueur_,getDistribution().derniereMain());
        }
    }

    Bytes orderedPlayers(byte _leader) {
        return rules.getRepartition().getSortedPlayers(_leader);
    }

    public byte playerHavingToBid() {
        return (byte)((getDistribution().getDonneur()+1+bids.size())%getNombreDeJoueurs());
    }
    public byte playerHavingToPlay() {
        return getPliEnCours().getNextPlayer(getNombreDeJoueurs());
    }
    public byte playerAfter(byte _player) {
        return rules.getRepartition().getNextPlayer(_player);
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

    public BooleanList getWonLastTrick() {
        return wonLastTrick;
    }

    public void setWonLastTrick(BooleanList _wonLastTrick) {
        wonLastTrick = _wonLastTrick;
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

}
