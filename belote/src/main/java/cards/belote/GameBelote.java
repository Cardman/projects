package cards.belote;
import java.util.concurrent.atomic.AtomicInteger;

import cards.belote.comparators.BidBeloteSuitComparator;
import cards.belote.comparators.DeclareHandBeloteComparator;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.CardChar;
import cards.consts.GameType;
import cards.consts.Status;
import cards.consts.Suit;
import code.maths.Rate;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;
/**
 */

public final class GameBelote {

    private AtomicInteger chargementSimulation = new AtomicInteger(0);

    /**Le type d'une partie est aleatoire ou encore edite ou encore un entrainement*/
    private GameType type;
    /**Contient toutes les cartes au debut de chaque partie*/
    private DealBelote deal;
    /**Au debut on a besoin d'un variable preneur pour stocker le joueur ayant annonce temporairement le plus haut contrat
    De plus, le choix par defaut de -1 pour le preneur sert pour le tarot lorsque
    personne ne prend un contrat et chacun joue pour soi*/
    private byte taker=CustList.INDEX_NOT_FOUND_ELT;
    /** Ce sont les primes, miseres ou poignees annoncees par le(s) joueur(s)*/
    private EqList<DeclareHandBelote> declares=new EqList<DeclareHandBelote>();
    private EqList<HandBelote> declaresBeloteRebelote = new EqList<HandBelote>();
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
    private EqList<BidBeloteSuit> bids=new EqList<BidBeloteSuit>();
    /**Ramasseur du pli qui vient d'etre joue*/
    private byte trickWinner;
    /**Scores cumules au cours des parties
    Chaque nombre (Short) represente un score pour le joueur*/
    private Numbers<Short> scores=new Numbers<Short>();
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
            wonLastTrick.add(false);
            declares.add(new DeclareHandBelote());
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
        CustList<TrickBelote> tricks_ = unionPlis();
        if (!tricks_.isEmpty()) {
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
    public Numbers<Short> getScores() {
        return new Numbers<Short>(scores);
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
        bids=new EqList<BidBeloteSuit>();
        endBidsFirstRound=false;
        bid=new BidBeloteSuit();
        progressingTrick=new TrickBelote((byte) -1);
        Numbers<Short> scores_=getScores();
        byte nombreJoueurs_=getNombreDeJoueurs();
        for (int joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
            /*Initialise les annonces*/
            scores_.set(joueur_, (short) 0);
            declaresBeloteRebelote.set(joueur_, new HandBelote());
            wonLastTrick.set(joueur_, false);
            declares.set(joueur_, new DeclareHandBelote());
        }
        tricks = new CustList<TrickBelote>();
    }
    public void simuler() {
        simulationWithBids = false;
        BidBeloteSuit contratTmp_;
        Numbers<Byte> players_ = orderedPlayers(playerAfter(getDistribution().getDonneur()));
        if (rules.dealAll()) {
            byte joueur_ = playerAfter(getDistribution().getDonneur());
            while (true) {
                contratTmp_=strategieContrat();
                ajouterContrat(contratTmp_,joueur_);
                if (!keepBidding()) {
                    break;
                }
                joueur_ = playerAfter(joueur_);
            }
            setChargementSimulation(getChargementSimulation() + 20);
        } else {
            boolean finished_ = false;
            for(byte joueur_:players_) {
                contratTmp_=strategieContrat();
                ajouterContrat(contratTmp_,joueur_);
                if (!keepBidding()) {
                    finished_ = true;
                    break;
                }
            }
            setChargementSimulation(getChargementSimulation() + 10);
            if (!finished_) {
                finEncherePremierTour();
                for(byte joueur_:players_) {
                    contratTmp_=strategieContrat();
                    ajouterContrat(contratTmp_,joueur_);
                    if (!keepBidding()) {
                        break;
                    }
                }
            }
            setChargementSimulation(getChargementSimulation() + 10);
        }
        if(!bid.jouerDonne()) {
            setChargementSimulation(100);
            return;
        }
        simulationWithBids=true;
        completerDonne();
        setPliEnCours();
        boolean passe_=false;
        while(true) {
            if(passe_) {
                ajouterPliEnCours();
                setEntameur();
                setPliEnCours();
            }
            for(byte joueur_:orderedPlayers(starter)) {
                CardBelote ct_=strategieJeuCarteUnique();
                if(annoncerBeloteRebelote(joueur_,ct_)) {
                    setAnnoncesBeloteRebelote(joueur_,ct_);
                }
                if(!passe_) {
                    passe_=true;
                }
                getDistribution().jouer(joueur_,ct_);
                ajouterUneCarteDansPliEnCours(ct_);
            }
            if(getDistribution().main().estVide()) {
                /*Il y a dix de der*/
                ajouterPliEnCours();
                setDixDeDer(getRamasseur());
                break;
            }
            setChargementSimulation(getChargementSimulation() + 10);
        }
        setChargementSimulation(100);
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

    public boolean surCoupeObligatoirePartenaire() {
        return rules.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP
                ||rules.getGestionCoupePartenaire()==BeloteTrumpPartner.OVERTRUMP_ONLY;
    }
    public boolean sousCoupeObligatoirePartenaire() {
        return rules.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP
                || rules.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_ONLY;
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
        for(CardBelote c: GameBeloteCommonPlaying.cartesAtouts(couleurAtout())) {
            if(c.getNomFigure() == CardChar.KING) {
                cartes_.ajouter(c);
            }
            if(c.getNomFigure() == CardChar.QUEEN) {
                cartes_.ajouter(c);
            }
        }
        return cartes_;
    }
    public boolean autoriseBeloteRebelote() {
        return autoriseBeloteRebelote(DealBelote.NUMERO_UTILISATEUR);
    }
    public boolean autoriseBeloteRebelote(byte _numero) {
        if(!bid.getCouleurDominante()) {
            return false;
        }
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
            if(!mainJoueur_.contient(c)) {
                continue;
            }
            //carte c: non annoncee et dans la main
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
            if ((lastPlayer_ + 1) % getNombreDeJoueurs() != _player) {
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
        BidBeloteSuit contratJoueur_=contrat();
        if (getRegles().dealAll()) {
            return contratJoueur_;
        }
        if(contratJoueur_.estDemandable(bid)) {
            return contratJoueur_;
        }
        return new BidBeloteSuit();
    }

    public EqList<BidBeloteSuit> allowedBids() {
        EqList<BidBeloteSuit> encheres_ = new EqList<BidBeloteSuit>();
        if (getRegles().dealAll()) {
            for (Suit s: couleurs()) {
                for (int p: RulesBelote.getPoints()) {
                    if (bid.getPoints() >= p) {
                        continue;
                    }
                    BidBeloteSuit bid_;
                    bid_ = new BidBeloteSuit();
                    bid_.setCouleur(s);
                    bid_.setEnchere(BidBelote.SUIT);
                    bid_.setPoints(p);
                    encheres_.add(bid_);
                }
            }
            for (BidBelote b: BidBelote.getNonZeroBids()) {
                if (b.getCouleurDominante()) {
                    continue;
                }
                if (!getRegles().getEncheresAutorisees().getVal(b)) {
                    continue;
                }
                for (int p: RulesBelote.getPoints()) {
                    if (bid.getPoints() >= p) {
                        continue;
                    }
                    BidBeloteSuit bid_;
                    bid_ = new BidBeloteSuit();
                    bid_.setCouleur(Suit.UNDEFINED);
                    bid_.setEnchere(b);
                    bid_.setPoints(p);
                    encheres_.add(bid_);
                }
            }
            return encheres_;
        }
        Suit s_ = getDistribution().derniereMain().premiereCarte().couleur();
        for(BidBelote e: rules.getEncheresAutorisees().getKeys()) {
            if(!rules.getEncheresAutorisees().getVal(e)) {
                continue;
            }
            if (!e.getCouleurDominante()) {
                BidBeloteSuit e_ = new BidBeloteSuit();
                e_.setEnchere(e);
                encheres_.add(e_);
                continue;
            }
            boolean pasEncherePrio_ = true;
            for(BidBelote e2_: rules.getEncheresAutorisees().getKeys()) {
                if(e2_.estPrioritaire(e, !endBidsFirstRound)) {
                    pasEncherePrio_ = false;
                }
            }
            if(pasEncherePrio_) {
                if (!endBidsFirstRound) {
                    /*First round of bidding*/
                    BidBeloteSuit e_ = new BidBeloteSuit();
                    e_.setEnchere(e);
                    e_.setCouleur(s_);
                    encheres_.add(e_);
                    continue;
                }
                /*Second round of bidding*/
                for (Suit s: couleurs()) {
                    if (s == s_) {
                        continue;
                    }
                    BidBeloteSuit e_ = new BidBeloteSuit();
                    e_.setEnchere(e);
                    e_.setCouleur(s);
                    encheres_.add(e_);
                }
            }
        }
        encheres_.sortElts(new BidBeloteSuitComparator());
        return encheres_;
    }
    private BidBeloteSuit contrat() {
        byte numero_=(byte)((getDistribution().getDonneur()+1+bids.size())%getNombreDeJoueurs());
        HandBelote mj_=getDistribution().main(numero_);
        HandBelote reunion_=new HandBelote();
        reunion_.ajouterCartes(mj_);
        reunion_.ajouterCartes(getDistribution().derniereMain());
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        BidBelote enchereCouleurDominante_ = BidBelote.FOLD;
        EnumList<Suit> suits_ = new EnumList<Suit>();
        Numbers<Integer> points_ = new Numbers<Integer>();
        for(BidBeloteSuit e: allowedBids()) {
            points_.add(e.getPoints());
            if(!e.getCouleurDominante()) {
                continue;
            }
            enchereCouleurDominante_ = e.getEnchere();
            if (!suits_.containsObj(e.getCouleur())) {
                suits_.add(e.getCouleur());
            }
        }
        points_.removeDuplicates();
        points_.sort();
        EnumMap<Suit,Rate> couleurPointsFictifs_ = new EnumMap<Suit,Rate>();
        EnumMap<Suit,Rate> couleurPointsFictifsRequis_ = new EnumMap<Suit,Rate>();
        int nbCartesFictives_ = reunion_.total();
        int nbCartesFinales_ = rules.getRepartition().getNombreCartesParJoueur();
        for(Suit c: couleurs()) {
            //c: couleur atout
            BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
            enchereBeloteLoc_.setCouleur(c);
            enchereBeloteLoc_.setEnchere(BidBelote.SUIT);
            EnumMap<Suit,HandBelote> repartition_=reunion_.couleurs(enchereBeloteLoc_);
            //repartition est la repartition des cartes a la couleur d'atout c
            int pointsFictifs_ = 0;
            for(Suit c2_: couleurs()) {
                if(c2_ == c) {
                    continue;
                }
                //c2: couleur ordinaire
                HandBelote cartesAssurantMax_ = repartition_.getVal(c2_).cartesPlisAssures(enchereBeloteLoc_);
                for(CardBelote c3_: cartesAssurantMax_) {
                    pointsFictifs_+=c3_.points(enchereBeloteLoc_)+4;
                }
            }
            HandBelote cartesAssurantMax_ = repartition_.getVal(c).cartesPlisAssures(enchereBeloteLoc_);
            for(CardBelote c3_: cartesAssurantMax_) {
                pointsFictifs_+=c3_.points(enchereBeloteLoc_)+8;
            }
            couleurPointsFictifs_.put(c, new Rate(pointsFictifs_*nbCartesFinales_,HandBelote.pointsTotauxDixDeDer(enchereBeloteLoc_)*nbCartesFictives_));
            couleurPointsFictifsRequis_.put(c, new Rate(1,2));
            //Comparaison
        }
        EnumMap<BidBelote,Rate> couleurPointsFictifsContrats_ = new EnumMap<BidBelote,Rate>();
        EnumMap<BidBelote,Rate> couleurPointsFictifsContratsRequis_ = new EnumMap<BidBelote,Rate>();
        for(BidBelote e: rules.getEncheresAutorisees().getKeys()) {
            if(!rules.getEncheresAutorisees().getVal(e)) {
                continue;
            }
            if (e.getCouleurDominante()) {
                continue;
            }
            Suit c_ = Suit.UNDEFINED;
            BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
            enchereBeloteLoc_.setCouleur(c_);
            enchereBeloteLoc_.setEnchere(e);
            EnumMap<Suit,HandBelote> repartition_=reunion_.couleurs(enchereBeloteLoc_);
            int pointsFictifs_ = 0;
            boolean toutesCouleursAvecCarteMaitresse_ = true;
            for(Suit c2_: couleurs()) {
                if(GameBeloteCommon.cartesMaitresses(repartition_, new HandBelote().couleurs(enchereBeloteLoc_),enchereBeloteLoc_).getVal(c2_).estVide()) {
                    toutesCouleursAvecCarteMaitresse_ = false;
                    break;
                }
                HandBelote cartesAssurantMax_ = repartition_.getVal(c2_).cartesPlisAssures(enchereBeloteLoc_);
                for(CardBelote c3_: cartesAssurantMax_) {
                    pointsFictifs_+=c3_.points(enchereBeloteLoc_)+5;
                }
            }
            if(!toutesCouleursAvecCarteMaitresse_) {
                continue;
            }
            couleurPointsFictifsContrats_.put(e, new Rate(pointsFictifs_*nbCartesFinales_,HandBelote.pointsTotauxDixDeDer(enchereBeloteLoc_)*nbCartesFictives_));
            couleurPointsFictifsContratsRequis_.put(e, new Rate(1,2));
        }
        EnumMap<Suit,Rate> couleursCandidates_ = new EnumMap<Suit,Rate>();
        for(Suit c: suits_) {
            if(Rate.strLower(couleurPointsFictifs_.getVal(c), couleurPointsFictifsRequis_.getVal(c))) {
                continue;
            }
            couleursCandidates_.put(c,Rate.divide(couleurPointsFictifs_.getVal(c),couleurPointsFictifsRequis_.getVal(c)));
        }
        Suit couleurMax_ = Suit.UNDEFINED;
        Rate max_ = Rate.zero();
        for(Suit c: couleursCandidates_.getKeys()) {
            if(Rate.strGreater(couleursCandidates_.getVal(c), max_)) {
                couleurMax_ = c;
                max_ = couleursCandidates_.getVal(c);
            }
        }
        Rate max2_ = Rate.zero();
        if(couleurMax_ == Suit.UNDEFINED) {
            if(couleurPointsFictifsContrats_.isEmpty()) {
                return enchereCouleur_;
            }
        } else {
            max2_ = max_;
        }
        BidBelote e_ = BidBelote.FOLD;
        for(BidBelote e: couleurPointsFictifsContrats_.getKeys()) {
            if(Rate.strLower(couleurPointsFictifsContrats_.getVal(e), couleurPointsFictifsContratsRequis_.getVal(e))) {
                continue;
            }
            if(Rate.strGreater(couleurPointsFictifsContrats_.getVal(e), max2_)) {
                e_ = e;
                max2_ = couleurPointsFictifsContrats_.getVal(e);
            }
        }
        if (getRegles().dealAll()) {
            if(Rate.eq(max_,max2_)) {
                int pts_ = (int)
                        Rate.multiply(couleurPointsFictifs_.getVal(couleurMax_),
                                new Rate(HandBelote.pointsTotauxDixDeDer(enchereCouleur_))).ll();
                if (!points_.isEmpty()) {
                    if (pts_ > points_.first()) {
                        enchereCouleur_.setEnchere(enchereCouleurDominante_);
                        enchereCouleur_.setCouleur(couleurMax_);
                        enchereCouleur_.setPoints(points_.first());
                        return enchereCouleur_;
                    }
                }
            } else if(e_ != BidBelote.FOLD) {
                int pts_ = (int)
                        Rate.multiply(couleurPointsFictifsContrats_.getVal(e_),
                                new Rate(HandBelote.pointsTotauxDixDeDer(enchereCouleur_))).ll();
                if (!points_.isEmpty()) {
                    if (pts_ > points_.first()) {
                        enchereCouleur_.setEnchere(e_);
                        enchereCouleur_.setPoints(pts_);
                        return enchereCouleur_;
                    }
                }
            }
        } else {
            if(Rate.eq(max_,max2_)) {
                enchereCouleur_.setEnchere(enchereCouleurDominante_);
                enchereCouleur_.setCouleur(couleurMax_);
                enchereCouleur_.setPoints((int)
                        Rate.multiply(couleurPointsFictifs_.getVal(couleurMax_),
                            new Rate(HandBelote.pointsTotauxDixDeDer(enchereCouleur_))).ll());
                return enchereCouleur_;
            } else if(e_ != BidBelote.FOLD) {
                enchereCouleur_.setEnchere(e_);
                enchereCouleur_.setPoints((int)
                        Rate.multiply(couleurPointsFictifsContrats_.getVal(e_),
                                new Rate(HandBelote.pointsTotauxDixDeDer(enchereCouleur_))).ll());
                return enchereCouleur_;
            }
        }
        return enchereCouleur_;
    }

    public void finEncherePremierTour() {
        endBidsFirstRound=!endBidsFirstRound;
    }

    public void ajouterContrat(BidBeloteSuit _c, byte _t) {
        if (lastHasBid == -1) {
            lastHasBid = _t;
        } else if (lastHasBid == _t) {
            bids.add(_c);
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
    public EqList<BidBeloteSuit> tousContrats() {
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
                for (int i_ = lastBid_; i_ >= CustList.FIRST_INDEX; i_--) {
                    iter_++;
                    if (iter_ >= getNombreDeJoueurs()) {
                        break;
                    }
                    if (bids.get(i_).jouerDonne()) {
                        fold_ = false;
                        break;
                    }
                }
                return !fold_;
            }
            return true;
        }
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (BidBeloteSuit e: allowedBids()) {
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
    EqList<BidBeloteSuit> maximumBid() {
        EqList<BidBeloteSuit> contratsMax_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        for (BidBeloteSuit b: allowedBids()) {
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
        if(bid.getCouleurDominante()) {
            return cartesBeloteRebelote().contient(_ct) && autoriseBeloteRebelote(_numeroJoueur);
        }
        return false;
    }
    public DeclareHandBelote strategieAnnonces(byte _joueurCourant) {
        EnumList<DeclaresBelote> annoncesAutorisees_ = new EnumList<DeclaresBelote>();
        for(DeclaresBelote a: rules.getAnnoncesAutorisees().getKeys()) {
            if(!rules.getAnnoncesAutorisees().getVal(a)) {
                continue;
            }
            annoncesAutorisees_.add(a);
        }
        DeclareHandBelote annonce_ = getDistribution().main(_joueurCourant).annonce(annoncesAutorisees_, bid);
        annonce_.setJoueur(_joueurCourant);
        return annonce_;
    }
    public void annoncer(byte _joueurCourant) {
        declares.set(_joueurCourant, strategieAnnonces(_joueurCourant));
    }
    public DeclareHandBelote getAnnonce(byte _joueurCourant) {
        return declares.get(_joueurCourant);
    }
    public void annulerAnnonces() {
        EqList<DeclareHandBelote> annoncesLoc_ = new EqList<DeclareHandBelote>(declares);
        DeclareHandBeloteComparator comparateur_ =
                new DeclareHandBeloteComparator(bid.getCouleur());
        EqList<DeclareHandBelote> declarationsTakerTeam_ = new EqList<DeclareHandBelote>();
        Numbers<Byte> takerTeam_ = partenaires(getPreneur());
        takerTeam_.add(getPreneur());
        for (byte p: takerTeam_) {
            declarationsTakerTeam_.add(new DeclareHandBelote(annoncesLoc_.get(p)));
        }
        declarationsTakerTeam_.sortElts(new DeclareHandBeloteComparator(bid.getCouleur()));
        EqList<DeclareHandBelote> declarationsTakerFoesTeam_ = new EqList<DeclareHandBelote>();
        Numbers<Byte> takerFoesTeam_ = adversaires(getPreneur());
        for (byte p: takerFoesTeam_) {
            declarationsTakerFoesTeam_.add(new DeclareHandBelote(annoncesLoc_.get(p)));
        }
        declarationsTakerFoesTeam_.sortElts(new DeclareHandBeloteComparator(bid.getCouleur()));
        if (!declarationsTakerTeam_.isEmpty()) {
            if (declarationsTakerFoesTeam_.isEmpty()) {
                for (byte p: takerFoesTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
                return;
            }
            boolean equals_ = true;
            int min_ = Math.min(takerFoesTeam_.size(), takerTeam_.size());
            for (int i = CustList.FIRST_INDEX;i<min_;i++) {
                int res_ = comparateur_.compare(declarationsTakerTeam_.get(i), declarationsTakerFoesTeam_.get(i));
                if (res_ < 0) {
                    for (byte p: takerFoesTeam_) {
                        declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                    }
                    equals_ = false;
                    break;
                }
                if (res_ > 0) {
                    for (byte p: takerTeam_) {
                        declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                    }
                    equals_ = false;
                    break;
                }
            }
            if (!equals_) {
                return;
            }
            if (takerFoesTeam_.size() > takerTeam_.size()) {
                for (byte p: takerTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
            } else if (takerFoesTeam_.size() < takerTeam_.size()) {
                for (byte p: takerFoesTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
            } else {
                for (byte p: takerTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
                for (byte p: takerFoesTeam_) {
                    declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
                }
            }
            return;
        }
        if (!declarationsTakerFoesTeam_.isEmpty()) {
            for (byte p: takerTeam_) {
                declares.get(p).setAnnonce(DeclaresBelote.UNDEFINED);
            }
        }
        //annuler les annonces de l'equipe les plus faibles a la fin du premier tour
    }
    public void setAnnoncesBeloteRebelote(byte _b, CardBelote _carte) {
        declaresBeloteRebelote.get(_b).ajouter(_carte);
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
        annoncer(_player);
        playedCard = strategieJeuCarteUnique();
        ajouterUneCarteDansPliEnCours(_player, getCarteJouee());
        return false;
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
        Numbers<Integer> handLengths_ = new Numbers<Integer>();
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

    public HandBelote cartesJouees() {
        HandBelote cartesJouees_=new HandBelote();
        for(TrickBelote pli_:tricks) {
            cartesJouees_.ajouterCartes(pli_.getCartes());
        }
        return cartesJouees_;
    }

    private static EnumList<Suit> couleurs() {
        return Suit.couleursOrdinaires();
    }

    public boolean memeEquipe(byte _numero1, byte _numero2) {
        if (aPourDefenseur(_numero1)) {
            return aPourDefenseur(_numero2);
        }
        return !aPourDefenseur(_numero2);
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
    /**Inclut tous les plis sauf celui qui est en cours*/
    public CustList<TrickBelote> unionPlis() {
        return tricks;
    }

    public EndBeloteGame getEndBeloteGame() {
        GameBeloteTeamsRelation t_ = getTeamsRelation();
        return new EndBeloteGame(t_,declares,declaresBeloteRebelote,wonLastTrick,bid,tricks);
    }

    private GameBeloteTeamsRelation getTeamsRelation() {
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

    private boolean aPourDefenseur(byte _numero) {
        return _numero!=taker&&!partenaires(taker).containsObj(_numero);
    }

    Numbers<Byte> orderedPlayers(byte _leader) {
        return rules.getRepartition().getSortedPlayers(_leader);
    }

    public byte playerHavingToBid() {
        byte dealer_ = getDistribution().getDonneur();
        byte playerHavingToBid_ = playerAfter(dealer_);
        int nbBids_ = tailleContrats();
        for (byte b=CustList.FIRST_INDEX;b<nbBids_;b++) {
            playerHavingToBid_ = playerAfter(playerHavingToBid_);
        }
        return playerHavingToBid_;
    }
    public byte playerHavingToPlay() {
        return getPliEnCours().getNextPlayer(getNombreDeJoueurs());
    }
    public byte playerAfter(byte _player) {
        return rules.getRepartition().getNextPlayer(_player);
    }

    private Numbers<Byte> adversaires(byte _numero) {
        Numbers<Byte> adversaires_ = new Numbers<Byte>();
        byte player_ = playerAfter(_numero);
        adversaires_.add(player_);
        player_ = playerAfter(player_);
        player_ = playerAfter(player_);
        adversaires_.add(player_);
        return adversaires_;
    }
    public Numbers<Byte> partenaires(byte _numero) {
        Numbers<Byte> partenaires_ = new Numbers<Byte>();
        byte player_ = playerAfter(_numero);
        player_ = playerAfter(player_);
        partenaires_.add(player_);
        return partenaires_;
    }
    //methode utilisee pour l'affichage
    public Status statutDe(byte _numero) {
        if(_numero==taker) {
            return Status.TAKER;
        }
        if(partenaires(taker).containsObj(_numero)) {
            return Status.CALLED_PLAYER;
        }
        return Status.DEFENDER;
    }


    public boolean isSameTeam(Numbers<Byte> _players) {
        GameBeloteTeamsRelation g_ = new GameBeloteTeamsRelation(taker,rules);
        return g_.isSameTeam(_players);
    }
    public CustList<Numbers<Byte>> playersBelongingToSameTeam() {
        GameBeloteTeamsRelation g_ = new GameBeloteTeamsRelation(taker,rules);
        return g_.playersBelongingToSameTeam();
    }


    public BidBeloteSuit getBid() {
        return bid;
    }

    public int getChargementSimulation() {
        return chargementSimulation.get();
    }

    public void setChargementSimulation(int _chargementSimulation) {
        chargementSimulation.set(_chargementSimulation);
    }

    public DealBelote getDeal() {
        return deal;
    }

    public void setDeal(DealBelote _deal) {
        deal = _deal;
    }

    public EqList<DeclareHandBelote> getDeclares() {
        return declares;
    }

    public void setDeclares(EqList<DeclareHandBelote> _declares) {
        declares = _declares;
    }

    public EqList<HandBelote> getDeclaresBeloteRebelote() {
        return declaresBeloteRebelote;
    }

    public void setDeclaresBeloteRebelote(EqList<HandBelote> _declaresBeloteRebelote) {
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

    public EqList<BidBeloteSuit> getBids() {
        return bids;
    }

    public void setBids(EqList<BidBeloteSuit> _bids) {
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

    public void setScores(Numbers<Short> _scores) {
        scores = _scores;
    }

    public String getError() {
        return error;
    }

    public void setError(String _error) {
        error = _error;
    }

}
