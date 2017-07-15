package cards.belote;
import code.format.Format;
import code.maths.Rate;
import code.stream.StreamTextFile;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;
import code.util.annot.RwXml;
import code.util.consts.Constants;
import cards.belote.comparators.BidBeloteSuitComparator;
import cards.belote.comparators.DeclareHandBeloteComparator;
import cards.belote.comparators.GameStrengthGreatFirstHandBeloteComparator;
import cards.belote.comparators.GameStrengthGreatHandBeloteComparator;
import cards.belote.comparators.GameStrengthLowHandBeloteComparator;
import cards.belote.comparators.GameStrengthLowLastHandBeloteComparator;
import cards.belote.comparators.HandBeloteGreatNbPtsCards;
import cards.belote.comparators.HandBeloteLongLengthComparator;
import cards.belote.comparators.HandBeloteLowNbPtsCards;
import cards.belote.comparators.HandBeloteShortLengthComparator;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.BonusBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.consts.CardChar;
import cards.consts.EndGameState;
import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Order;
import cards.consts.PossibleTrickWinner;
import cards.consts.Status;
import cards.consts.Suit;
/**
 */
@RwXml
public final class GameBelote {
    private static volatile int _chargementSimulation_;
    private static final String GAME_BELOTE = "cards.belote.GameBelote";
    private static final String FOLDER = "resources_cards/classes";

    private static final String OVER_TRUMP_FOE = "overTrumpFoe";

    private static final String OVER_TRUMP_PARTNER = "overTrumpPartner";

    private static final String PLAY_STRONGER_CARD = "playStrongerCard";

    private static final String PLAY_SUIT = "playSuit";

    private static final String TRUMP_FOE = "trumpFoe";

    private static final String UNDER_TRUMP_FOE = "underTrumpFoe";

    private static final String UNDER_TRUMP_PARTNER = "underTrumpPartner";

    private static final String HAND_VALUE_SUIT = "handValueSuit";

    private static final String HAND_VALUE_NO_SUIT = "handValueNoSuit";

    private static final String OVERBID_DUE = "overbidDue";

    private static final String PLAY_BELOTE_REBELOTE= "playBeloteRebelote";

    private static final String SINGLE_CARD= "singleCard";

    private static final String GET_CARDS_BY_SUIT_CARDS = "getCardsBySuitCards";

    private static final String GET_CARDS_BY_TRUMP_CARDS = "getCardsByTrumpCards";

    private static final String FOE_WIN_TRICK = "foeWinTrick";

    private static final String PARTNER_WIN_TRICK = "partnerWinTrick";

    private static final String RETURN_LINE = "\n";

    private static final String EMPTY = "";

    /**Le type d'une partie est aleatoire ou encore edite ou encore un entrainement*/
    private GameType type;
    /**Contient toutes les cartes au debut de chaque partie*/
    private DealBelote deal;
    /**Au debut on a besoin d'un variable preneur pour stocker le joueur ayant annonce temporairement le plus haut contrat
    De plus, le choix par defaut de -1 pour le preneur sert pour le tarot lorsque
    personne ne prend un contrat et chacun joue pour soi*/
    private transient byte taker=CustList.INDEX_NOT_FOUND_ELT;
    /** Ce sont les primes, miseres ou poignees annoncees par le(s) joueur(s)*/
    private EqList<DeclareHandBelote> declares=new EqList<DeclareHandBelote>();
    private EqList<HandBelote> declaresBeloteRebelote = new EqList<HandBelote>();
    private BooleanList wonLastTrick = new BooleanList();
    /**Le contrat permet de dire quel va etre le deroulement
    de la partie*/
    private transient BidBeloteSuit bid=new BidBeloteSuit();
    /**Ce sont les plis faits par les joueurs*/
    private CustList<TrickBelote> tricks = new CustList<TrickBelote>();
    /**Fin du premier tour d'enchere a la belote<br/>
    est vrai si et seulement si c'est la fin des encheres de premier tour*/
    private transient boolean endBidsFirstRound;
    /**PliBelote en cours d'etre joue*/
    private TrickBelote progressingTrick = new TrickBelote((byte)CustList.INDEX_NOT_FOUND_ELT);

    /**Entameur du pli qui est en cours d'etre joue*/
    private transient byte starter;
    /**Ensembe des contrats annonces*/
    private EqList<BidBeloteSuit> bids=new EqList<BidBeloteSuit>();
    /**Ramasseur du pli qui vient d'etre joue*/
    private transient byte trickWinner;
    /**Scores cumules au cours des parties
    Chaque nombre (Short) represente un score pour le joueur*/
    private Numbers<Short> scores=new Numbers<Short>();
    private transient boolean simulationWithBids;
    /**Nombre de fois qu'a ete joue la partie (partie fini)*/
    private long number;
    private RulesBelote rules=new RulesBelote();
    private transient byte lastHasBid = CustList.INDEX_NOT_FOUND_ELT;

    private transient BidBeloteSuit lastBid=new BidBeloteSuit();

    private transient CardBelote playedCard = CardBelote.WHITE;

    private transient HandBelote cardsToBePlayed = new HandBelote();

    private transient String gameError = EMPTY;
    private transient String reason = EMPTY;
    private final transient String file = Format.getClassProperties(GAME_BELOTE);
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
            //if existePreneur()
        }
    }

    public RulesBelote getRegles() {
        return rules;
    }
    /**Renvoie le nombre de joueurs jouant a la partie*/
    public byte getNombreDeJoueurs() {
        return (byte) rules.getRepartition().getNombreJoueurs();
    }
    public long getNombre() {
        return number;
    }
    public void setNombre() {
        number++;
    }
    public Numbers<Short> getScores() {
        return new Numbers<Short>(scores);
    }
    public int getDiffAttackPointsMinusDefensePoints() {
        int scoreDef_ = 0;
        for (byte p: adversaires(taker)) {
            scoreDef_ = scores.get(p);
            break;
        }
        return scores.get(taker) - scoreDef_;
    }
    public GameType getType() {
        return type;
    }
    public DealBelote getDistribution() {
        return deal;
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
        cardsToBePlayed = new HandBelote();
        gameError = EMPTY;
    }
    public void simuler(String _loc) {
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
                if(annoncerBeloteRebelote(joueur_,ct_,_loc)) {
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

    private boolean surCoupeObligatoirePartenaire() {
        return rules.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP
                ||rules.getGestionCoupePartenaire()==BeloteTrumpPartner.OVERTRUMP_ONLY;
    }
    private boolean sousCoupeObligatoirePartenaire() {
        return rules.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP
                || rules.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_ONLY;
    }
    private boolean sousCoupeObligatoireAdversaire() {
        return rules.getSousCoupeAdv();
    }
    public boolean autorise(CardBelote _c, String _loc) {
        cardsToBePlayed = new HandBelote();
        cardsToBePlayed.ajouter(_c);
        gameError = EMPTY;
        HandBelote main_=getDistribution().main(playerHavingToPlay());
        return cartesJouables(main_.couleurs(bid),_loc).contient(_c);
    }

    public String getErreurDeJeu() {
        return gameError;
    }

    static HandBelote cartesAtouts(Suit _couleur) {
        return HandBelote.couleurComplete(_couleur, Order.TRUMP);
    }
    static HandBelote cartesCouleurs(Suit _couleur) {
        return HandBelote.couleurComplete(_couleur,Order.SUIT);
    }
    public HandBelote cartesBeloteRebelote() {
        HandBelote cartes_ = new HandBelote();
        if(!bid.getCouleurDominante()) {
            return cartes_;
        }
        for(CardBelote c: cartesAtouts(couleurAtout())) {
            if(c.getNomFigure() == CardChar.KING) {
                cartes_.ajouter(c);
            }
            if(c.getNomFigure() == CardChar.QUEEN) {
                cartes_.ajouter(c);
            }
        }
        return cartes_;
    }
    public boolean autoriseBeloteRebelote(String _loc) {
        return autoriseBeloteRebelote(DealBelote.NUMERO_UTILISATEUR,_loc);
    }
    public boolean autoriseBeloteRebelote(byte _numero, String _loc) {
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
            if(!autorise(c,_loc)) {
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
        reason = EMPTY;
        BidBeloteSuit contratJoueur_;
        try {
            contratJoueur_=contrat();
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            try {
                contratJoueur_ = allowedBids().first();
            } catch (RuntimeException _1) {
                contratJoueur_ = new BidBeloteSuit();
            }
        }
        if (getRegles().dealAll()) {
            return contratJoueur_;
        }
        if(contratJoueur_.estDemandable(bid)) {
            return contratJoueur_;
        }
        if(!contratJoueur_.getCouleurDominante()) {
            formatOverBid(contratJoueur_);
        } else {
            formatClassicBid(contratJoueur_);
        }
        return new BidBeloteSuit();
    }

    private void formatClassicBid(BidBeloteSuit _contratJoueur) {
        reason += format(HAND_VALUE_SUIT, _contratJoueur.getCouleur(), bid.getEnchere())+RETURN_LINE;
        reason += format(OVERBID_DUE);
    }

    private void formatOverBid(BidBeloteSuit _contratJoueur) {
        reason += format(HAND_VALUE_NO_SUIT, _contratJoueur.getEnchere(), bid.getEnchere())+RETURN_LINE;
        reason += format(OVERBID_DUE);
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
    BidBeloteSuit contrat() {
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
                if(cartesMaitresses(repartition_, new HandBelote().couleurs(enchereBeloteLoc_),enchereBeloteLoc_).getVal(c2_).estVide()) {
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
                    if (pts_ > points_.getMinimum()) {
                        enchereCouleur_.setEnchere(enchereCouleurDominante_);
                        enchereCouleur_.setCouleur(couleurMax_);
                        enchereCouleur_.setPoints(points_.getMinimum());
                        return enchereCouleur_;
                    }
                }
            } else if(e_ != BidBelote.FOLD) {
                int pts_ = (int)
                        Rate.multiply(couleurPointsFictifsContrats_.getVal(e_),
                                new Rate(HandBelote.pointsTotauxDixDeDer(enchereCouleur_))).ll();
                if (!points_.isEmpty()) {
                    if (pts_ > points_.getMinimum()) {
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
    public boolean getFinEncherePremierTour() {
        return endBidsFirstRound;
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
        return progressingTrick;
    }
    public boolean annoncerBeloteRebelote(byte _numeroJoueur, CardBelote _ct, String _loc) {
        reason = EMPTY;
        if(bid.getCouleurDominante()) {
            if(cartesBeloteRebelote().contient(_ct) &&autoriseBeloteRebelote(_numeroJoueur,_loc)) {
                reason=format(PLAY_BELOTE_REBELOTE, DeclaresBeloteRebelote.BELOTE_REBELOTE);
                return true;
            }
        }
        return false;
    }
    public DeclareHandBelote strategieAnnonces(byte _joueurCourant) {
        reason = EMPTY;
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
            return;
        }
        //annuler les annonces de l'equipe les plus faibles a la fin du premier tour
    }
    public void setAnnoncesBeloteRebelote(CardBelote _carte) {
        declaresBeloteRebelote.get(DealBelote.NUMERO_UTILISATEUR).ajouter(_carte);
    }
    public void setAnnoncesBeloteRebelote(byte _b, CardBelote _carte) {
        declaresBeloteRebelote.get(_b).ajouter(_carte);
    }
    public HandBelote getCartesAnnoncablesBeloteRebelote() {
        HandBelote main_ = cartesBeloteRebelote();
        main_.supprimerCartes(getAnnoncesBeloteRebelote(DealBelote.NUMERO_UTILISATEUR));
        return main_;
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
        reason = EMPTY;
        if(progressingTrick.estVide()) {
            byte numero_=playerHavingToPlay();
            HandBelote mainJoueur_=getDistribution().main(numero_);
            EnumMap<Suit,HandBelote> repartition_=mainJoueur_.couleurs(bid);
            HandBelote cartesJouables_=playableCards(repartition_);
            try {
                CardBelote card_ = entame();
                if (!cartesJouables_.contient(card_)) {
                    return cartesJouables_.premiereCarte();
                }
                return card_;
            } catch (RuntimeException _0) {
                _0.printStackTrace();
                return cartesJouables_.premiereCarte();
            }
        }
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().main(numero_);
        EnumMap<Suit,HandBelote> repartition_=mainJoueur_.couleurs(bid);
        HandBelote cartesJouables_=playableCards(repartition_);
        try {
            CardBelote card_ = enCours();
            if (!cartesJouables_.contient(card_)) {
                return cartesJouables_.premiereCarte();
            }
            return card_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return cartesJouables_.premiereCarte();
        }
    }
    private CardBelote entame() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().main(numero_);
        EnumMap<Suit,HandBelote> repartition_=mainJoueur_.couleurs(bid);
        HandBelote cartesJouables_=playableCards(repartition_);
        if(cartesJouables_.total()==1) {
            return cartesJouables_.premiereCarte();
        }
        if(bid.getCouleurDominante()) {
            return entameCouleurDominante(numero_, mainJoueur_, cartesJouables_);
        }
        return entameSansAtoutToutAtout(numero_, mainJoueur_, cartesJouables_);
    }
    private CardBelote entameCouleurDominante(byte _numero,
            HandBelote _mainJoueur,
            HandBelote _cartesJouables) {
        Suit couleurAtout_=couleurAtout();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        HandBelote cartesJouees_=cartesJouees();
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=cartesJouees_.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_=new EnumMap<Suit,EqList<HandBelote>>();
        CustList<TrickBelote> plisFaits_=unionPlis();
        if(_cartesJouables.total()==1) {
            return _cartesJouables.premiereCarte();
        }
        for(Suit couleur_:couleurs()) {
            suites_.put(couleur_,main(repartition_,couleur_).eclater(repartitionCartesJouees_,bid));
        }
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=cartesPossibles(repartitionCartesJouees_,plisFaits_,repartition_, _numero,couleurAtout_);
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandBelote>>> hypotheses_ = cartesCertaines(cartesPossibles_);
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=hypotheses_.getVal(Hypothesis.SURE);
        boolean strictMaitreAtout_;
        EnumList<Suit> couleursMaitres_=couleursMaitres(suites_, repartitionCartesJouees_, cartesPossibles_,_numero);
        EnumMap<Suit,HandBelote> cartesMaitresses_=cartesMaitresses(repartition_,repartitionCartesJouees_,bid);
        Numbers<Byte> appele_=partenaires(taker);
        Numbers<Byte> partenaire_=partenaires(_numero);
        Numbers<Byte> adversaire_ =adversaires(_numero);
        boolean maitreJeu_;

        strictMaitreAtout_=strictMaitreAtout(cartesPossibles_,_numero,suite(suites_,couleurAtout_),repartitionCartesJouees_);
        maitreJeu_=strictMaitreAtout_&&couleursMaitres_.size()==couleursNonAtouts().size();
        EnumList<Suit> couleursNonAtouts_=couleursNonAtouts();
        if(maitreJeu_) {
            return jeuMainMaitresseCouleurDominante(suites_,
                    repartition_,
                    couleursMaitres_,
                    couleurAtout_);
        }
        if(main(repartition_,couleurAtout_).total()==_mainJoueur.total()) {
            /*Si le joueur ne possede que de l'atout*/
            if(suite(suites_,couleurAtout_).size()==1) {
                if(main(suites_,couleurAtout_,0).total()==main(cartesMaitresses_,couleurAtout_).total()) {
                    /*Si le joueur n'a que des atouts maitres*/
                    return main(repartition_,couleurAtout_).premiereCarte();
                }
                return main(repartition_,couleurAtout_).derniereCarte();
            }
            if(main(cartesMaitresses_,couleurAtout_).estVide()) {
                return main(repartition_,couleurAtout_).derniereCarte();
            }
            if(_mainJoueur.total()<=2*main(cartesMaitresses_,couleurAtout_).total()) {
                /*La main du joueur contient plus d atouts maitres que des atouts non maitres*/
                return main(suites_,couleurAtout_,0).premiereCarte();
            }
            return main(repartition_,couleurAtout_).derniereCarte();
        }
        if(main(repartition_,couleurAtout_).total()+1==_mainJoueur.total()) {
            /*On cherche la couleur autre que l'atout non vide*/
            EnumList<Suit> couleursNonAtoutNonVides_ = couleursNonAtoutNonVides(repartition_, couleursNonAtouts_);
            Suit couleurNonAtout_ = couleursNonAtoutNonVides_.first();
            if(!main(cartesMaitresses_,couleurNonAtout_).estVide()) {
                if(main(suites_,couleurAtout_,0).total()==main(cartesMaitresses_,couleurAtout_).total()) {
                    /*Si le joueur n'a que des atouts maitres*/
                    return main(repartition_,couleurAtout_).premiereCarte();
                }
                if(main(repartition_,couleurNonAtout_).premiereCarte().points(bid)==0) {
                    return main(repartition_,couleurNonAtout_).premiereCarte();
                }
                if(_mainJoueur.total()==2) {
                    return main(repartition_,couleurNonAtout_).premiereCarte();
                }
                if(main(cartesMaitresses_,couleurAtout_).estVide()) {
                    return main(repartition_,couleurNonAtout_).premiereCarte();
                }
                return main(repartition_,couleurAtout_).premiereCarte();
            }
            EnumList<Suit> couleursPouvantEtreCoupees_ =
                    couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonAtoutNonVides_);
            if(egaliteCouleurs(couleursPouvantEtreCoupees_, couleursNonAtoutNonVides_)) {
                return main(repartition_,couleurNonAtout_).premiereCarte();
            }
            if(strictMaitreAtout_) {
                return main(repartition_,couleurAtout_).premiereCarte();
            }
            if(main(repartition_,couleurAtout_).total()==main(cartesMaitresses_,couleurAtout_).total()) {
                Numbers<Byte> joueursPouvantCouper_ = joueursPouvantCouperCouleurs(_mainJoueur, adversaire_, bid, cartesPossibles_, couleursNonAtoutNonVides_);
                if(egaliteJoueurs(joueursPouvantCouper_,adversaire_)) {
                    return main(repartition_,couleurAtout_).premiereCarte();
                }
            }
            return main(repartition_,couleurNonAtout_).premiereCarte();
        }
        EnumList<Suit> couleursNonVides_ = couleursNonAtoutNonVides(_mainJoueur, couleursNonAtouts_);
        if(_numero==taker) {
            EnumList<Suit> couleursMaitressesAvecPoints_ = couleursAvecCarteMaitresse(_mainJoueur, cartesJouees_, bid, couleursNonVides_);
            couleursMaitressesAvecPoints_ = couleursAvecPoints(_mainJoueur, bid, couleursMaitressesAvecPoints_);
            if(egaliteCouleurs(couleursMaitressesAvecPoints_, couleursNonVides_)) {
                if(pasAtoutJoueurs(adversaire_, cartesPossibles_, couleurAtout_)) {
                    if(!couleursNonVides_.isEmpty()) {
                        /*Si il existe une carte de couleur autre que l'atout*/
                        return carteMaitresse(couleursNonVides_, cartesMaitresses_, _mainJoueur, cartesJouees_, cartesPossibles_, _numero, suites_);
                    }
                }
                if(strictMaitreAtout_) {
                    return main(repartition_,couleurAtout_).premiereCarte();
                }
                EnumList<Suit> couleurs_=couleursPouvantEtreCoupees(adversaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
                if(!couleurs_.isEmpty()) {
                    return faireCouperAdv(couleurs_,repartition_,repartitionCartesJouees_);
                }
                couleurs_=couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
                if(!couleurs_.isEmpty()) {
                    return faireCouperAppele(couleurs_,repartition_,repartitionCartesJouees_);
                }
                couleurs_ = couleursOuvertes(plisFaits_, couleursNonVides_);
                if(!couleurs_.isEmpty()) {
                    return ouvrirCouleur(couleurs_,repartition_);
                }
                return ouvrirCouleur(couleursNonVides_,repartition_);
            }
            EnumList<Suit> couleurs_=couleursSansCarteMaitresse(_mainJoueur, cartesJouees_, bid, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return faireCouperAdv(couleurs_, repartition_, repartitionCartesJouees_);
            }
            return faireCouperAdv(couleursNonVides_, repartition_, repartitionCartesJouees_);
        }
        //Appele
        if(appele_.containsObj(_numero)) {
            if(!main(cartesCertaines_,couleurAtout_,taker).estVide()) {
                if(!main(repartition_,couleurAtout_).estVide()) {
                    boolean cartesMaitressesJouees_ = true;
                    CardBelote carteCertaine_ = main(cartesCertaines_,couleurAtout_,taker).premiereCarte();
                    boolean possedeDeuxiemeCarteForte_ = false;
                    for(CardBelote c: HandBelote.couleurComplete(couleurAtout_, Order.TRUMP)) {
                        if(c.strength(couleurAtout_, bid)
                                < carteCertaine_.strength(couleurAtout_, bid)) {
                            if(!main(repartition_,couleurAtout_).contient(c)) {
                                continue;
                            }
                            boolean possedeDeuxiemeCarte_ = true;
                            for(CardBelote c2_: HandBelote.couleurComplete(couleurAtout_, Order.TRUMP)) {
                                if(c2_.strength(couleurAtout_, bid)
                                        < c.strength(couleurAtout_, bid)) {
                                    continue;
                                }
                                if(c2_ == c) {
                                    continue;
                                }
                                if(c2_ == carteCertaine_) {
                                    continue;
                                }
                                if(repartitionCartesJouees_.getVal(couleurAtout_).contient(c2_)) {
                                    continue;
                                }
                                possedeDeuxiemeCarte_ = false;
                                break;
                            }
                            possedeDeuxiemeCarteForte_ = possedeDeuxiemeCarte_;
                            continue;
                        }
                        if(repartitionCartesJouees_.getVal(couleurAtout_).contient(c)) {
                            continue;
                        }
                        cartesMaitressesJouees_ = false;
                    }
                    if(cartesMaitressesJouees_) {
                        boolean troisCouleursCarteMaitre_=true;
                        for(Suit couleur_:couleursNonAtouts_) {
                            if (main(cartesMaitresses_,couleur_).estVide()) {
                                troisCouleursCarteMaitre_ = false;
                            }
                        }
                        if(troisCouleursCarteMaitre_||!possedeDeuxiemeCarteForte_) {
                            return main(repartition_,couleurAtout_).premiereCarte();
                        }
                    }
                }
            }
            if(!main(repartition_,couleurAtout_).estVide()&&main(repartition_,couleurAtout_).derniereCarte().points(bid) < 8) {
                return main(repartition_,couleurAtout_).derniereCarte();
            }
            if (!getDistribution().derniereMain().estVide()) {
                CardBelote carteDessus_=getDistribution().derniereMain().premiereCarte();
                Suit couleurDessus_=carteDessus_.couleur();

                if(couleurDessus_!=couleurAtout_
                        &&!main(cartesCertaines_,couleurDessus_,taker).estVide()) {
                    boolean cartesMaitressesJouees_ = true;
                    CardBelote carteCertaine_ = main(cartesCertaines_,couleurDessus_,taker).premiereCarte();
                    for(CardBelote c: HandBelote.couleurComplete(couleurDessus_, Order.SUIT)) {
                        if(c.strength(couleurDessus_, bid)
                                < carteCertaine_.strength(couleurDessus_, bid)) {
                            continue;
                        }
                        if(repartitionCartesJouees_.getVal(couleurDessus_).contient(c)) {
                            continue;
                        }
                        cartesMaitressesJouees_ = false;
                    }
                    if(cartesMaitressesJouees_) {
                        if(tours(couleurDessus_, plisFaits_).isEmpty()&&!main(repartition_,couleurDessus_).estVide()) {
                            return main(repartition_,couleurDessus_).premiereCarte();
                        }
                    }
                }
            }


            EnumList<Suit> couleurs_=couleursNonOuvertesNonVides(_mainJoueur, plisFaits_, couleursNonAtouts_);
            /*On considere que l'appele est place apres le preneur*/
            if(!couleurs_.isEmpty()) {
                return ouvrir(couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
            }
            couleurs_ = couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
            couleurs_ = couleursAvecPoints(_mainJoueur, bid, couleurs_);
            if(!couleurs_.isEmpty()) {
                return faireCouperPreneurFigure(couleurs_,repartition_);
            }
            couleurs_ = couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
            couleurs_ = couleursAvecNbPointsInfEg(_mainJoueur, bid, couleurs_, 4);
            if(!couleurs_.isEmpty()) {
                return faireTomberPointsPourPreneur(couleurs_,repartition_,repartitionCartesJouees_);
            }
            couleurs_ = couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
            couleurs_ = couleursNonCoupeeParJoueurs(_mainJoueur, adversaire_, bid, cartesPossibles_, cartesCertaines_, couleurs_);
            if(!couleurs_.isEmpty()) {
                return ouvrir(couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
            }
            couleurs_ = couleursPouvantEtreCoupees(adversaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return faireCouperAdv(couleurs_,repartition_,repartitionCartesJouees_);
            }
            return faireCouperAdv(couleursNonVides_,repartition_,repartitionCartesJouees_);
        }
        EnumList<Suit> couleurs_=couleursNonOuvertesNonVides(_mainJoueur, plisFaits_, couleursNonVides_);
        /*Cas d'un defenseur*/
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
        }
        couleurs_ = couleursPouvantEtreCoupees(adversaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return faireCouperAdv(couleurs_,repartition_,repartitionCartesJouees_);
        }
        couleurs_ = couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
        couleurs_ = couleursNonCoupeeParJoueurs(_mainJoueur, adversaire_, bid, cartesPossibles_, cartesCertaines_, couleurs_);

        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
        }
        return faireCouperAdv(couleursNonVides_,repartition_,repartitionCartesJouees_);
    }
    private CardBelote entameSansAtoutToutAtout(byte _numero,
            HandBelote _mainJoueur,
            HandBelote _cartesJouables) {
        Suit couleurAtout_=couleurAtout();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        HandBelote cartesJouees_=cartesJouees();
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=cartesJouees_.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_=new EnumMap<Suit,EqList<HandBelote>>();
        CustList<TrickBelote> plisFaits_=unionPlis();
        if(_cartesJouables.total()==1) {
            return _cartesJouables.premiereCarte();
        }
        for(Suit couleur_:couleurs()) {
            suites_.put(couleur_,main(repartition_,couleur_).eclater(repartitionCartesJouees_,bid));
        }
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=cartesPossibles(repartitionCartesJouees_,plisFaits_,repartition_, _numero,couleurAtout_);
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandBelote>>> hypotheses_ = cartesCertaines(cartesPossibles_);
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=hypotheses_.getVal(Hypothesis.SURE);
        EnumList<Suit> couleursMaitres_=couleursMaitres(suites_, repartitionCartesJouees_, cartesPossibles_,_numero);
        EnumMap<Suit,HandBelote> cartesMaitresses_=cartesMaitresses(repartition_,repartitionCartesJouees_,bid);
        Numbers<Byte> appele_=partenaires(taker);
        Numbers<Byte> adversaire_ =adversaires(_numero);
        boolean maitreJeu_;
        /*Jeu sans atout ou tout atout*/
        maitreJeu_=couleursMaitres_.size()==couleurs().size();
        EnumList<Suit> couleursNonAtouts_=couleurs();
        if(maitreJeu_) {
            return jeuMainMaitresseCouleursEgales(repartition_,couleursMaitres_);
        }
        EnumList<Suit> couleursNonVides_ = couleursNonAtoutNonVides(_mainJoueur, couleursNonAtouts_);
        if(_numero==taker) {
            EnumList<Suit> couleursMaitressesAvecPoints_ = couleursAvecCarteMaitresse(_mainJoueur, cartesJouees_, bid, couleursNonVides_);
            couleursMaitressesAvecPoints_ = couleursAvecPoints(_mainJoueur, bid, couleursMaitressesAvecPoints_);
            EnumList<Suit> couleurs_=new EnumList<Suit>();
            if(egaliteCouleurs(couleursNonVides_, couleursMaitressesAvecPoints_)) {
                /*Il existe une carte de couleur autre que l'atout*/
                return carteMaitresse(couleursNonVides_, cartesMaitresses_, _mainJoueur, cartesJouees_, cartesPossibles_, _numero, suites_);
            }
            couleurs_ = couleursSansCarteMaitresse(_mainJoueur, cartesJouees_, bid, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return faireCouperAdv(couleurs_, repartition_, repartitionCartesJouees_);
            }
            return faireCouperAdv(couleursNonVides_, repartition_, repartitionCartesJouees_);
        }
        EnumList<Suit> couleurs_=new EnumList<Suit>();
        if(appele_.containsObj(_numero)) {
            if (!getDistribution().derniereMain().estVide()) {
                Suit couleurDessus_=getDistribution().derniereMain().premiereCarte().couleur();
                if(!main(cartesCertaines_,couleurDessus_,taker).estVide()) {
                    boolean cartesMaitressesJouees_ = true;
                    CardBelote carteCertaine_ = main(cartesCertaines_,couleurDessus_,taker).premiereCarte();
                    for(CardBelote c: cartesAtouts(couleurDessus_)) {
                        if(c.strength(couleurDessus_, bid)
                                < carteCertaine_.strength(couleurDessus_, bid)) {
                            continue;
                        }
                        if(repartitionCartesJouees_.getVal(couleurDessus_).contient(c)) {
                            continue;
                        }
                        cartesMaitressesJouees_ = false;
                    }
                    if(cartesMaitressesJouees_) {
                        if(tours(couleurDessus_, plisFaits_).isEmpty()&&!main(repartition_,couleurDessus_).estVide()) {
                            return main(repartition_,couleurDessus_).premiereCarte();
                        }
                    }
                }
            }
            /*On considere que l'appele est place apres le preneur*/
            couleurs_ = couleursNonOuvertesNonVides(_mainJoueur, plisFaits_, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return ouvrir(couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
            }
            couleurs_ = couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return ouvrir(couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
            }
            return faireCouperAdv(couleursNonVides_,repartition_,repartitionCartesJouees_);
        }
        /*Cas d'un defenseur*/
        couleurs_ = couleursNonOuvertesNonVides(_mainJoueur, plisFaits_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
        }
        couleurs_ = couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
        }
        return faireCouperAdv(couleursNonVides_,repartition_,repartitionCartesJouees_);
    }
    private CardBelote jeuMainMaitresseCouleurDominante(
            EnumMap<Suit,EqList<HandBelote>> _suites,
            EnumMap<Suit,HandBelote> _repartition,
            EnumList<Suit> _couleursMaitres,
            Suit _couleurAtout) {
        if(!suite(_suites,_couleurAtout).isEmpty()) {
            reason += format(GET_CARDS_BY_TRUMP_CARDS);
            return main(_suites,_couleurAtout,0).premiereCarte();
        }
        EnumList<Suit> couleurs_ = couleursNonAtoutNonVides(_repartition, _couleursMaitres);
        reason += format(GET_CARDS_BY_SUIT_CARDS);
        return main(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jeuMainMaitresseCouleursEgales(
            EnumMap<Suit,HandBelote> _repartition,
            EnumList<Suit> _couleursMaitres) {
        EnumList<Suit> couleurs_ = couleursNonAtoutNonVides(_repartition, _couleursMaitres);
        reason += format(GET_CARDS_BY_SUIT_CARDS);
        return main(_repartition,couleurs_.first()).premiereCarte();
    }
    public static byte nombreCartesPoints(EnumMap<Suit,HandBelote> _repartition,BidBeloteSuit _contrat,Suit _couleur) {
        return _repartition.getVal(_couleur).nombreCartesPoints(_contrat);
    }
    private CardBelote faireTomberPointsPourPreneur(
            EnumList<Suit> _couleurs,EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_repartitionCartesJouees, _couleurs);
        couleurs_ = couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = couleursLesPlusBasses(_repartition, bid, couleurs_);
        return main(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote faireCouperPreneurFigure(
            EnumList<Suit> _couleurs,EnumMap<Suit,HandBelote> _repartition) {
        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return main(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote ouvrir(EnumList<Suit> _couleurs,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _cartesMaitresses) {
        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_cartesMaitresses, _couleurs);
        //maitre
        couleurs_ = couleursLesPlusCourtes(_repartitionCartesJouees, couleurs_);
        //jouees
        couleurs_ = couleursLesPlusLongues(_repartition, couleurs_);
        //longues
        couleurs_ = couleursLesPlusBasses(_repartition, bid, couleurs_);
        //basses
        if(!main(_cartesMaitresses,couleurs_.first()).estVide()) {
            return main(_repartition,couleurs_.first()).premiereCarte();
        }
        return main(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote ouvrirCouleur(
            EnumList<Suit> _couleurs,EnumMap<Suit,HandBelote> _repartition) {
        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = couleursLesPlusBasses(_repartition, bid, couleurs_);
        return main(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote carteMaitresse(
            EnumList<Suit> _couleurs,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            HandBelote _repartition,
            HandBelote _repartitionCartesJouees,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,byte _numero,
            EnumMap<Suit,EqList<HandBelote>> _suites) {
        CustList<EnumList<Suit>> couleursMaitres_=couleursMaitresSansReprise(_suites,_couleurs,_repartition.couleurs(bid),_cartesPossibles,_repartitionCartesJouees,_numero);
        for(EnumList<Suit> g: couleursMaitres_) {
            for(Suit c: g) {
                return main(_repartition.couleurs(bid),c).premiereCarte();
            }
        }
        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_cartesMaitresses, _couleurs);
        //maitre
        couleurs_ = couleursLesPlusLongues(_repartitionCartesJouees.couleurs(bid), couleurs_);
        //jouees
        couleurs_ = couleursLesPlusLongues(_repartition.couleurs(bid), couleurs_);
        //longues
        return main(_repartition.couleurs(bid),couleurs_.first()).premiereCarte();
    }
    private CustList<EnumList<Suit>> couleursMaitresSansReprise(
            EnumMap<Suit,EqList<HandBelote>> _suites,
            EnumList<Suit> _couleurs,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            HandBelote _cartesJouees,byte _numero) {
        EnumList<Suit> couleursMaitres_=couleursMaitres(_suites, _cartesJouees.couleurs(bid), _cartesPossibles, _numero);
        couleursMaitres_ = intersectionCouleurs(couleursMaitres_, _couleurs);
        return couleursTrieesPlusCourtes(_repartition, couleursMaitres_);
    }
    private CardBelote faireCouperAdv(EnumList<Suit> _couleurs,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_repartitionCartesJouees, _couleurs);
        couleurs_ = couleursLesPlusLongues(_repartition, couleurs_);
        couleurs_ = couleursLesPlusBasses(_repartition, bid, couleurs_);
        return main(_repartition,couleurs_.first()).derniereCarte();
    }
    private static CardBelote faireCouperAppele(
            EnumList<Suit> _couleurs,EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursLesPlusCourtes(_repartitionCartesJouees, _couleurs);
        couleurs_ = couleursLesPlusCourtes(_repartition, couleurs_);
        return main(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote enCours() {
        byte numero_=playerHavingToPlay();
        HandBelote mainJoueur_=getDistribution().main(numero_);
        EnumMap<Suit,HandBelote> repartition_=mainJoueur_.couleurs(bid);
        HandBelote cartesJouables_=playableCards(repartition_);
        if(cartesJouables_.total()==1) {
            reason = format(SINGLE_CARD);
            return cartesJouables_.premiereCarte();
        }
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        EnumMap<Suit,HandBelote> repartitionJouables_=cartesJouables_.couleurs(bid);
        if(bid.getCouleurDominante()) {
            Suit couleurAtout_=couleurAtout();
            if (couleurDemandee_ != couleurAtout_) {
                if(!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                    return fournirCouleurOrdinaireCouleurDominante(numero_,mainJoueur_,cartesJouables_);
                }
                if(!repartitionJouables_.getVal(couleurAtout_).estVide()) {
                    return coupeCouleurDominante(numero_,mainJoueur_,cartesJouables_);
                }
                return defausseCouleurOrdinaireCouleurDominante(numero_,mainJoueur_);
            }
            //entame atout
            if(!repartitionJouables_.getVal(couleurAtout_).estVide()) {
                return fournirAtoutCouleurDominante(numero_, mainJoueur_, cartesJouables_);
            }
            return defausseAtoutCouleurDominante(numero_, mainJoueur_);
        }
        if(bid.ordreCouleur()) {
            if(!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                return fournirCouleurOrdinaireSansAtout(numero_, mainJoueur_, cartesJouables_);
            }
            return defausseCouleurOrdinaireSansAtout(numero_, mainJoueur_);
        }
        //jeu tout atout
        if(!main(repartitionJouables_,couleurDemandee_).estVide()) {
            return fournirAtoutToutAtout(numero_, mainJoueur_, cartesJouables_);
        }
        return defausseAtoutToutAtout(numero_, mainJoueur_);
    }
    private CardBelote fournirCouleurOrdinaireCouleurDominante(byte _numero,
            HandBelote _mainJoueur,
            HandBelote _cartesJouables) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> appele_=partenaires(taker);
        Numbers<Byte> partenaire_ = partenaires(_numero);
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_= info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        boolean maitreJeu_ = info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=equipeQuiVaFairePliCouleurDominante(info_, carteForte_, _numero);
        boolean carteMaitresse_;
        byte maxTwo_;
        EnumMap<Suit,HandBelote> repartitionJouables_=_cartesJouables.couleurs(bid);

        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            reason += format(FOE_WIN_TRICK);
            return main(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            reason += format(PARTNER_WIN_TRICK);

            for(byte joueur_:adversaire_) {
                if(!main(cartesPossibles_,couleurDemandee_,joueur_).estVide()) {
                    if(main(cartesPossibles_,couleurDemandee_,joueur_).premiereCarte().strength(couleurDemandee_,bid)
                            >main(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
            }
            if(main(repartitionJouables_,couleurDemandee_).nombreCartesPoints(bid)>1) {
                return cartePlusPetitePoints(suite(suites_,couleurDemandee_),bid);
            }
            return main(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        TrickBelote dernierPli_;
        EqList<HandBelote> cartesRelMaitres_;
        Numbers<Byte> tours_=tours(couleurDemandee_, plisFaits_);
        Numbers<Byte> dernieresCoupes_;
        Numbers<Byte> dernieresDefausses_;
        Numbers<Byte> joueursSusceptiblesDeCouper_=new Numbers<Byte>();


        //Si le joueur ne coupe pas et ne se defause pas sur la couleur demandee
        cartesRelMaitres_=cartesRelativementMaitre(suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_,couleurAtout_, cartesCertaines_,carteForte_);
        if(main(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)
                ||carteForte_.couleur()==couleurAtout_) {
            /*Si le joueur ne peut pas prendre la main*/
            if(main(repartitionJouables_,couleurDemandee_).premiereCarte().points(bid)<1||maitreJeu_) {
                /*Si le joueur ne possede pas de figure ou est maitre du jeu*/
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le joueur possede au moins une figure*/
            if(tours_.isEmpty()) {
                /*Si cette couleur est entamee pour la premiere fois*/
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                    boolean partenaireMaitre_ = true;
                    for(byte j: adversaireNonJoue_) {
                        HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                        if(couleurPossible_.estVide()) {
                            continue;
                        }
                        byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                        if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                            continue;
                        }
                        partenaireMaitre_ = false;
                        break;
                    }
                    if(partenaireMaitre_) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            dernierPli_=plisFaits_.get(tours_.last());
            dernieresCoupes_=dernierPli_.joueursCoupes(couleurAtout_);
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);
            /*Maintenant on aborde au moins le deuxieme tour*/
            if(dernieresCoupes_.isEmpty()) {
                /*Si le dernier pli n'est pas coupe a cette couleur*/
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    if(carteForte_.couleur()==couleurAtout_) {
                        /*L'espoir fait vivre*/
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                    boolean partenaireMaitre_ = true;
                    for(byte j: adversaireNonJoue_) {
                        HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                        if(couleurPossible_.estVide()) {
                            continue;
                        }
                        byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                        if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                            continue;
                        }
                        partenaireMaitre_ = false;
                        break;
                    }
                    if(partenaireMaitre_) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Maintenant on sait qu'au dernier tour le pli a ete coupe*/
            if(partenaire_.containsObj(ramasseur(plisFaits_,tours_.last()))) {
                if(carteForte_.couleur()==couleurAtout_) {
                    /*L'espoir fait vivre*/
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                boolean partenaireMaitre_ = true;
                for(byte j: adversaireNonJoue_) {
                    HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                    if(couleurPossible_.estVide()) {
                        continue;
                    }
                    byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                    if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                        continue;
                    }
                    partenaireMaitre_ = false;
                    break;
                }
                if(partenaireMaitre_) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
            }
            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant on sait que le joueur peut prendre la main*/
        if(main(repartitionJouables_,couleurDemandee_).premiereCarte().points(bid)<1) {
            //si aucun adv non joue ne possede de figure
            if(maitreJeu_||defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                if(!cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
            }
            if(tours_.isEmpty()) {
                return main(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant le joueur peut prendre la main avec une figure a la couleur demandee*/
        if(_numero==taker) {
            if(tours_.isEmpty()) {
                if(maitreJeu_ && !cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(suite(suites_,couleurDemandee_).size()==1||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede de figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(suite(suites_,couleurDemandee_).size()==1||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede de figure
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            /*C'est au moins le deuxieme tour*/
            dernierPli_=plisFaits_.get(tours_.last());
            dernieresCoupes_=dernierPli_.joueursCoupes(couleurAtout_);
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);

            for(byte joueur_:joueursNonJoue_) {
                if(peutCouper(couleurDemandee_, joueur_, cartesPossibles_,couleurAtout_)) {
                    joueursSusceptiblesDeCouper_.add(joueur_);
                }
            }
            if(!joueursSusceptiblesDeCouper_.isEmpty()) {
                for(byte joueur_:adversaire_) {
                    if(joueursSusceptiblesDeCouper_.containsObj(joueur_)) {
                        return main(repartition_,couleurDemandee_).derniereCarte();
                    }
                }
                if(maitreJeu_) {
                    maxTwo_=0;
                    for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                        if(joueur_!=_numero) {
                            maxTwo_=(byte)Math.max(main(cartesPossibles_,couleurDemandee_,joueur_).total(),maxTwo_);
                        }
                    }
                    if(main(suites_,couleurDemandee_,0).total()>maxTwo_) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    return main(repartition_,couleurDemandee_).derniereCarte();
                }
                return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
            }
            /*Si la coupe semble improbable*/
            if(!dernieresDefausses_.isEmpty()&&tours_.size()==1) {
                if(maitreJeu_ && !cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(suite(suites_,couleurDemandee_).size()==1
                            ||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede de figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(suite(suites_,couleurDemandee_).size()==1||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede de figure
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
            if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                return main(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Appele*/
        if(appele_.containsObj(_numero)) {
            if(tours_.isEmpty()) {
                if(maitreJeu_ && !cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!joueursNonJoue_.containsObj(taker)) {
                    /*Si l'appele joue apres le preneur*/
                    if (!getDistribution().derniereMain().estVide()) {
                        CardBelote carteDessus_=getDistribution().derniereMain().premiereCarte();
                        boolean peutCouperAdvNonJoue_ = false;
                        for(byte j: adversaireNonJoue_) {
                            if(!peutCouper(couleurDemandee_,j,cartesPossibles_,couleurAtout_)) {
                                continue;
                            }
                            peutCouperAdvNonJoue_ = true;
                            break;
                        }
                        if(!peutCouperAdvNonJoue_) {
                            //si aucun adv non joue ne ramasser le pli => cartesRelMaitres si existe
                            carteMaitresse_=true;
                            for(byte j: adversaireNonJoue_) {
                                HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                                if(couleurPossible_.estVide()) {
                                    continue;
                                }
                                byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                                if(carteDessus_.strength(couleurDemandee_,bid)>max_) {
                                    continue;
                                }
                                carteMaitresse_ = false;
                                break;
                            }
                            if(carteMaitresse_&&!cartesRelMaitres_.isEmpty()) {
                                if(cartesRelMaitres_.size()==1||cartesRelMaitres_.get(1).premiereCarte().points(bid)<1) {
                                    return cartesRelMaitres_.get(0).premiereCarte();
                                }
                                return cartesRelMaitres_.get(1).premiereCarte();
                            }
                        }
                        if(carteDessus_.points(bid)>0) {
                            /*Si l'appele joue avant le preneur et la carte du dessus vaut des points.*/
                            peutCouperAdvNonJoue_ = false;
                            for(byte j: adversaireNonJoue_) {
                                if(!peutCouper(couleurDemandee_,j,cartesPossibles_,couleurAtout_)) {
                                    continue;
                                }
                                peutCouperAdvNonJoue_ = true;
                                break;
                            }
                            if(!peutCouperAdvNonJoue_) {
                                //si aucun adv non joue ne ramasser le pli => jeuFigureHauteDePlusFaibleSuite
                                carteMaitresse_=true;
                                for(byte j: adversaireNonJoue_) {
                                    HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                                    if(couleurPossible_.estVide()) {
                                        continue;
                                    }
                                    byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                                    if(carteDessus_.strength(couleurDemandee_,bid)>max_) {
                                        continue;
                                    }
                                    carteMaitresse_ = false;
                                    break;
                                }
                                if(carteMaitresse_) {
                                    return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                                }
                            }
                        }
                    }
                }
                if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(suite(suites_,couleurDemandee_).size()==1||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adversaire non joue ne possede une figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(suite(suites_,couleurDemandee_).size()==1
                                ||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)
                                ||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adversaire non joue ne possede une figure
                if(partenaire_.containsObj(ramasseurVirtuel_)
                        &&defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            dernierPli_=plisFaits_.get(tours_.last());
            dernieresCoupes_=dernierPli_.joueursCoupes(couleurAtout_);
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);
            /*Deuxieme tour pour un appele ne coupant pas la couleur demandee differente de l'atout*/
            for(byte joueur_:joueursNonJoue_) {
                if(peutCouper(couleurDemandee_, joueur_, cartesPossibles_,couleurAtout_)) {
                    joueursSusceptiblesDeCouper_.add(joueur_);
                }
            }
            if(!joueursSusceptiblesDeCouper_.isEmpty()) {
                for(byte joueur_:adversaire_) {
                    if(joueursSusceptiblesDeCouper_.containsObj(joueur_)) {
                        return main(repartition_,couleurDemandee_).derniereCarte();
                    }
                }
                if(maitreJeu_) {
                    maxTwo_=0;
                    for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                        if(joueur_!=_numero) {
                            maxTwo_=(byte)Math.max(main(cartesPossibles_,couleurDemandee_,joueur_).total(),maxTwo_);
                        }
                    }
                    if(main(suites_,couleurDemandee_,0).total()>maxTwo_) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    return main(repartition_,couleurDemandee_).derniereCarte();
                }
                return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
            }
            /*Si la coupe semble improbable*/
            if(!dernieresDefausses_.isEmpty()&&tours_.size()==1) {
                if(maitreJeu_ && !cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(suite(suites_,couleurDemandee_).size()==1
                            ||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede une figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(suite(suites_,couleurDemandee_).size()==1
                                ||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede une figure a la couleur demandee
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
            if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                return main(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Defenseur*/
        if(maitreJeu_ && !cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        boolean pasAtoutAdvNonJoue_ = pasAtoutJoueurs(adversaireNonJoue_, cartesPossibles_, couleurAtout_);
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        if(pasAtoutAdvNonJoue_) {
            return sauveQuiPeutFigure(cartesPossibles_,suite(suites_,couleurDemandee_), cartesRelMaitres_, adversaireNonJoue_, couleurDemandee_);
        }
        if(tours_.isEmpty()) {
            if(!joueursNonJoue_.containsObj(taker)||carteForte_.points(bid)>0) {
                /*Si le joueur (defenseur) va jouer apres le preneur et il reste des joueurs susceptibles d'etre l'appele ou il existe une figure que peut prendre le joueur*/
                if(!cartesRelMaitres_.isEmpty()) {
                    if(cartesRelMaitres_.size()==1||cartesRelMaitres_.get(1).premiereCarte().points(bid)<1) {
                        return main(suites_,couleurDemandee_,0).premiereCarte();
                    }
                    return cartesRelMaitres_.get(1).premiereCarte();
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()&&cartesRelMaitres_.get(0).total()==1&&main(repartitionJouables_,couleurDemandee_).total()==2) {
                if(suite(suites_,couleurDemandee_).size()==1) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    boolean pasMonteeAdvNonJoueSurPartenaire_ = true;
                    for(byte j: adversaireNonJoue_) {
                        if(defausse(couleurDemandee_,j,cartesPossibles_,bid)) {
                            continue;
                        }
                        HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                        if(!couleurPossible_.estVide()) {
                            byte max_ = main(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid);
                            if(max_ < carteForte_.strength(couleurDemandee_,bid)) {
                                continue;
                            }
                        }
                        pasMonteeAdvNonJoueSurPartenaire_ = false;
                        break;
                    }
                    //si tous les adv non joue se defaussent ou ne peuvent pas monter sur le partenaire
                    if(pasMonteeAdvNonJoueSurPartenaire_) {
                        return main(repartitionJouables_,couleurDemandee_).carte(1);
                    }
                }
                return main(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant on est au moins au deuxieme tour*/
        return main(repartition_,couleurDemandee_).derniereCarte();
    }
    private CardBelote coupeCouleurDominante(byte _numero,
            HandBelote _mainJoueur,
            HandBelote _cartesJouables) {
        Suit couleurAtout_=couleurAtout();
        EnumMap<Suit,HandBelote> repartitionJouables_=_cartesJouables.couleurs(bid);
        if(main(repartitionJouables_,couleurAtout_).total()==_cartesJouables.total()) {
            return coupeObligatoireCouleurDominante(_numero, _mainJoueur, _cartesJouables);
        }
        return coupeFacultativeCouleurDominante(_numero, _mainJoueur, _cartesJouables);
    }
    private CardBelote coupeObligatoireCouleurDominante(byte _numero,
            HandBelote _mainJoueur,
            HandBelote _cartesJouables) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,EqList<HandBelote>> suites_= info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        boolean maitreJeu_ = info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=equipeQuiVaFairePliCouleurDominante(info_, carteForte_, _numero);
        EnumMap<Suit,HandBelote> repartitionJouables_=_cartesJouables.couleurs(bid);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            reason += format(FOE_WIN_TRICK);
            /*Si le joueur est oblige de couper la couleur demandee*/
            return main(repartitionJouables_,couleurAtout_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            if(suite(suites_,couleurAtout_).size()==1) {
                return main(repartitionJouables_,couleurAtout_).premiereCarte();
            }
            return main(repartitionJouables_,couleurAtout_).derniereCarte();
        }
        EqList<HandBelote> cartesRelMaitres_;
        Numbers<Byte> tours_=tours(couleurDemandee_, plisFaits_);
        cartesRelMaitres_=cartesRelativementMaitre(suite(suites_,couleurAtout_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurAtout_, couleurAtout_, cartesCertaines_,carteForte_);
        /*Coupe obligatoire (ou surcoupe ou sous-coupe)*/
        return coupe(repartitionJouables_,cartesPossibles_,cartesMaitresses_,suites_,adversaireNonJoue_,couleurAtout_,couleurDemandee_,tours_,carteForte_,cartesRelMaitres_,maitreJeu_);
    }
    private CardBelote coupeFacultativeCouleurDominante(byte _numero,
            HandBelote _mainJoueur,
            HandBelote _cartesJouables) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumList<Suit> couleursMaitres_=info_.getCouleursMaitresses();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=strictCouleursMaitres(suites_,repartitionCartesJouees_,cartesPossibles_,_numero);
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        boolean maitreJeu_ = info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=equipeQuiVaFairePliCouleurDominante(info_, carteForte_, _numero);
        boolean carteMaitresse_;
        EnumMap<Suit,HandBelote> repartitionJouables_=_cartesJouables.couleurs(bid);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur se defausse*/
            return defausseCouleurDemandeeSurAdversaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
        }
        EqList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(suite(suites_,couleurAtout_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurAtout_, couleurAtout_, cartesCertaines_,carteForte_);
        Numbers<Byte> tours_=tours(couleurDemandee_, plisFaits_);
        /*Coupe possible non obligatoire, car partenaire.contains(ramasseurVirtuel)*/
        if(main(repartitionJouables_,couleurAtout_).premiereCarte().strength(couleurDemandee_, bid)>carteForte_.strength(couleurDemandee_, bid)) {
            //surcoupe possible non obligatoire
            if(maitreJeu_ && !cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            if(suite(suites_,couleurAtout_).size()==1
                    &&couleursMaitres_.size()==couleursNonAtouts().size()
                    &&main(repartitionJouables_,couleurAtout_).premiereCarte().points(bid)>0) {
                //si aucun adv non joue ne peut surcouper ==> plus grande carte
                boolean nePeutSurcouper_ = true;
                for(byte j: adversaireNonJoue_) {
                    if(nePeutCouper(couleurDemandee_, j, cartesPossibles_, cartesCertaines_,bid.getCouleur())) {
                        continue;
                    }
                    if(main(cartesPossibles_,couleurAtout_,j).premiereCarte().strength(couleurDemandee_,bid)
                            <main(repartitionJouables_,couleurAtout_).premiereCarte().strength(couleurDemandee_,bid)) {
                        continue;
                    }
                    nePeutSurcouper_ = false;
                    break;
                }
                if(nePeutSurcouper_) {
                    return main(repartitionJouables_,couleurAtout_).premiereCarte();
                }
            }
            if(!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                return coupe(repartitionJouables_,cartesPossibles_,cartesMaitresses_,suites_,adversaireNonJoue_,couleurAtout_,couleurDemandee_,tours_,carteForte_,cartesRelMaitres_,maitreJeu_);
            }
            return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
        }
        //sous coupe possible non obligatoire
        if(maitreJeu_&&main(repartitionJouables_,couleurAtout_).derniereCarte().points(bid)<10) {
            carteMaitresse_=true;
            for(Suit couleur_:couleursNonAtouts()) {
                if(!main(repartition_,couleur_).estVide()) {
                    if (!(main(repartition_,couleur_).derniereCarte().points(bid)>0)) {
                        carteMaitresse_ = false;
                    }
                }
            }
            if(carteMaitresse_) {
                return main(repartitionJouables_,couleurAtout_).derniereCarte();
            }
        }
        return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
    }
    private CardBelote fournirAtoutCouleurDominante(byte _numero,
            HandBelote _mainJoueur,
            HandBelote _cartesJouables) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> partenaire_ = partenaires(_numero);
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        boolean maitreJeu_ = info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=equipeQuiVaFairePliCouleurDominante(info_, carteForte_, _numero);
        EnumMap<Suit,HandBelote> repartitionJouables_=_cartesJouables.couleurs(bid);
        EqList<HandBelote> suitesJouables_ = repartitionJouables_.getVal(couleurAtout_).eclater(info_.getRepartitionCartesJouees(), bid);
        //entame atout
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            reason += format(FOE_WIN_TRICK);
            /*La couleur demandee est atout*/
            return main(repartition_,couleurAtout_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            reason += format(PARTNER_WIN_TRICK);

            if(suitesJouables_.size()==1) {
                return main(repartitionJouables_,couleurAtout_).premiereCarte();
            }
            for(byte joueur_:adversaire_) {
                if(!main(cartesPossibles_,couleurAtout_,joueur_).estVide()) {
                    if(main(cartesPossibles_,couleurAtout_,joueur_).premiereCarte().strength(couleurDemandee_,bid)
                            >main(repartitionJouables_,couleurAtout_).premiereCarte().strength(couleurDemandee_,bid)) {
                        //Il existe un adversaire pouvant surcouper le pli avec un atout relativement maitre sur le joueur courant
                        if(joueursNonJoue_.containsAllObj(partenaire_)) {
                            return cartePlusPetitePoints(suitesJouables_,bid);
                        }
                        return main(repartitionJouables_,couleurAtout_).premiereCarte();
                    }
                }
            }
            return main(repartitionJouables_,couleurAtout_).derniereCarte();
        }
        //peut ramasser

        EqList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(suitesJouables_, cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_,couleurDemandee_, cartesCertaines_,carteForte_);
        if(main(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)) {
            return main(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        //peut monter
        if(maitreJeu_ && !cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        boolean nePeutMonterSurCarteForte_ = true;
        for(byte j: adversaireNonJoue_) {
            if(main(cartesPossibles_,couleurDemandee_,j).estVide()) {
                //si pas d'atout
                continue;
            }
            if(main(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)) {
                continue;
            }
            nePeutMonterSurCarteForte_ = false;
            break;
        }
        //si aucun adv non joue ne possede pas d'atout sup ou egal a la carte forte
        if(nePeutMonterSurCarteForte_ && !cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
            byte min_=-1;
            byte forceMin_ = 0;
            for(byte j: adversaireNonJoue_) {
                byte forceMinLoc_ = main(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid);
                if(forceMinLoc_ > forceMin_) {
                    forceMin_ = forceMinLoc_;
                }
            }
            //pour prendre la main, il faut avoir une carte superieur ou egal au max des cartes des adversaires n'ayant pas joue
            for(HandBelote suite_:suitesJouables_) {
                if(suite_.premiereCarte().strength(couleurDemandee_,bid)>forceMin_) {
                    min_++;
                } else {
                    break;
                }
            }
            return suitesJouables_.get(min_).premiereCarte();
        }
        return main(repartitionJouables_,couleurDemandee_).derniereCarte();
    }
    private CardBelote defausseCouleurOrdinaireCouleurDominante(byte _numero,
            HandBelote _mainJoueur) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        Suit couleurAtout_= info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=strictCouleursMaitres(suites_,repartitionCartesJouees_,cartesPossibles_,_numero);
        Numbers<Byte> partenaire_ = partenaires(_numero);
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        Numbers<Byte> tours_=tours(couleurDemandee_, plisFaits_);
        if(adversaireNonJoue_.isEmpty()) {
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
            }
        }
        if(tours_.isEmpty()) {
            if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
            }
        }
        return defausseCouleurDemandeeSurAdversaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
    }
    private CardBelote defausseAtoutCouleurDominante(byte _numero,
            HandBelote _mainJoueur) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=strictCouleursMaitres(suites_,repartitionCartesJouees_,cartesPossibles_,_numero);
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        PossibleTrickWinner ramasseurCertain_=equipeQuiVaFairePliCouleurDominante(info_, carteForte_, _numero);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            reason += format(FOE_WIN_TRICK);
            /*La couleur demandee est atout*/
            /*Maintenant le joueur se defausse sur demande d'atout*/
            return defausseAtoutSurAdversaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurAtout_, couleursStrictementMaitresses_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            reason += format(PARTNER_WIN_TRICK);
            /*Maintenant le joueur se defausse*/
            return defausseAtoutSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurAtout_, couleursStrictementMaitresses_);
        }
        return defausseAtoutSurAdversaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurAtout_, couleursStrictementMaitresses_);
    }
    private CardBelote fournirCouleurOrdinaireSansAtout(byte _numero,
            HandBelote _mainJoueur,
            HandBelote _cartesJouables) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> appele_=partenaires(taker);
        Numbers<Byte> partenaire_ = partenaires(_numero);
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        boolean maitreJeu_=info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=equipeQuiVaFairePliSansAtout(info_, carteForte_, _numero);
        boolean carteMaitresse_;
        EnumMap<Suit,HandBelote> repartitionJouables_=_cartesJouables.couleurs(bid);

        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            reason += format(FOE_WIN_TRICK);

            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            reason += format(PARTNER_WIN_TRICK);


            for(byte joueur_:adversaire_) {
                if(!main(cartesPossibles_,couleurDemandee_,joueur_).estVide()) {
                    if(main(cartesPossibles_,couleurDemandee_,joueur_).premiereCarte().strength(couleurDemandee_,bid)>main(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
            }
            if(main(repartitionJouables_,couleurDemandee_).nombreCartesPoints(bid)>1) {
                return cartePlusPetitePoints(suite(suites_,couleurDemandee_),bid);
            }
            return main(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        TrickBelote dernierPli_;
        EqList<HandBelote> cartesRelMaitres_;
        Numbers<Byte> tours_=tours(couleurDemandee_, plisFaits_);
        Numbers<Byte> dernieresDefausses_;

        //Si le joueur ne se defause pas sur la couleur demandee
        cartesRelMaitres_=cartesRelativementMaitre(suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_,couleurAtout_, cartesCertaines_,carteForte_);
        if(main(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)<
                carteForte_.strength(couleurDemandee_,bid)) {
            /*Si le joueur ne peut pas prendre la main*/
            if(main(repartitionJouables_,couleurDemandee_).premiereCarte().points(bid)<1||maitreJeu_) {
                /*Si le joueur ne possede pas de figure ou est maitre du jeu*/
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le joueur possede au moins une figure*/
            if(tours_.isEmpty()) {
                /*Si cette couleur est entamee pour la premiere fois*/
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                    boolean partenaireMaitre_ = true;
                    for(byte j: adversaireNonJoue_) {
                        HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                        if(couleurPossible_.estVide()) {
                            continue;
                        }
                        byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                        if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                            continue;
                        }
                        partenaireMaitre_ = false;
                        break;
                    }
                    if(partenaireMaitre_) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            dernierPli_=plisFaits_.get(tours_.last());
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);
            /*Maintenant on aborde au moins le deuxieme tour*/
            /*Si le dernier pli n'est pas coupe a cette couleur*/
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                boolean partenaireMaitre_ = true;
                for(byte j: adversaireNonJoue_) {
                    HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                    if(couleurPossible_.estVide()) {
                        continue;
                    }
                    byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                    if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                        continue;
                    }
                    partenaireMaitre_ = false;
                    break;
                }
                if(partenaireMaitre_) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
            }
            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant on sait que le joueur peut prendre la main*/
        if(main(repartitionJouables_,couleurDemandee_).premiereCarte().points(bid)<1) {
            //si aucun adv non joue ne possede de figure
            if(maitreJeu_||defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                if(!cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
            }
            if(tours_.isEmpty()) {
                return main(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant le joueur peut prendre la main avec une figure a la couleur demandee*/
        if(_numero==taker) {
            if(tours_.isEmpty()) {
                if(maitreJeu_) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(suite(suites_,couleurDemandee_).size()==1||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede de figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if (suite(suites_,couleurDemandee_).size()==1) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaire_.containsObj(ramasseurVirtuel_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede de figure
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            /*C'est au moins le deuxieme tour*/
            dernierPli_=plisFaits_.get(tours_.last());
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);

            /*Si la coupe semble improbable*/
            if(!dernieresDefausses_.isEmpty()&&tours_.size()==1) {
                if(maitreJeu_) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(suite(suites_,couleurDemandee_).size()==1||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede de figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)&&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if (suite(suites_,couleurDemandee_).size()==1) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaire_.containsObj(ramasseurVirtuel_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede de figure
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
            if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                return main(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Appele*/
        if(appele_.containsObj(_numero)) {
            if(tours_.isEmpty()) {
                if(maitreJeu_) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!joueursNonJoue_.containsObj(taker)) {
                    /*Si l'appele joue apres le preneur*/
                    //si aucun adv non joue ne ramasser le pli => cartesRelMaitres si existe
                    if (!getDistribution().derniereMain().estVide()) {
                        CardBelote carteDessus_=getDistribution().derniereMain().premiereCarte();
                        carteMaitresse_=true;
                        for(byte j: adversaireNonJoue_) {
                            HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                            if(couleurPossible_.estVide()) {
                                continue;
                            }
                            byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                            if(carteDessus_.strength(couleurDemandee_,bid)>max_) {
                                continue;
                            }
                            carteMaitresse_ = false;
                            break;
                        }
                        if(carteMaitresse_&&!cartesRelMaitres_.isEmpty()) {
                            if(cartesRelMaitres_.size()==1||cartesRelMaitres_.get(1).premiereCarte().points(bid)<1) {
                                return cartesRelMaitres_.get(0).premiereCarte();
                            }
                            return cartesRelMaitres_.get(1).premiereCarte();
                        }
                        if(carteDessus_.points(bid)>0) {
                            /*Si l'appele joue avant le preneur et la carte du dessus vaut des points.*/
                            //si aucun adv non joue ne ramasser le pli => jeuFigureHauteDePlusFaibleSuite
                            carteMaitresse_=true;
                            for(byte j: adversaireNonJoue_) {
                                HandBelote couleurPossible_ = main(cartesPossibles_,couleurDemandee_,j);
                                if(couleurPossible_.estVide()) {
                                    continue;
                                }
                                byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                                if(carteDessus_.strength(couleurDemandee_,bid)>max_) {
                                    continue;
                                }
                                carteMaitresse_ = false;
                                break;
                            }
                            if(carteMaitresse_) {
                                return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                            }
                        }
                    }
                }
                if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(suite(suites_,couleurDemandee_).size()==1||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adversaire non joue ne possede une figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(suite(suites_,couleurDemandee_).size()==1
                                ||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)
                                ||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adversaire non joue ne possede une figure
                if(partenaire_.containsObj(ramasseurVirtuel_)
                        &&defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            dernierPli_=plisFaits_.get(tours_.last());
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);
            /*Deuxieme tour pour un appele ne coupant pas la couleur demandee differente de l'atout*/
            /*Si la coupe semble improbable*/
            if(!dernieresDefausses_.isEmpty()&&tours_.size()==1) {
                if(maitreJeu_) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(suite(suites_,couleurDemandee_).size()==1||main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede une figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if (suite(suites_,couleurDemandee_).size()==1) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (main(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaire_.containsObj(ramasseurVirtuel_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(suite(suites_,couleurDemandee_),bid);
                    }
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede une figure a la couleur demandee
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return main(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
            if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
                return main(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Defenseur*/
        if(maitreJeu_) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        return sauveQuiPeutFigure(cartesPossibles_,suite(suites_,couleurDemandee_), cartesRelMaitres_, adversaireNonJoue_, couleurDemandee_);
    }
    private CardBelote defausseCouleurOrdinaireSansAtout(byte _numero,
            HandBelote _mainJoueur) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();

        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=strictCouleursMaitres(suites_,repartitionCartesJouees_,cartesPossibles_,_numero);
        Numbers<Byte> partenaire_ = partenaires(_numero);
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=equipeQuiVaFairePliSansAtout(info_, carteForte_, _numero);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            reason += format(FOE_WIN_TRICK);

            /*Maintenant le joueur se defausse*/
            return defausseCouleurDemandeeSurAdversaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            reason += format(PARTNER_WIN_TRICK);

            return defausseCouleurDemandeeSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
        }
        Numbers<Byte> tours_=tours(couleurDemandee_, plisFaits_);
        //Defausse
        if(adversaireNonJoue_.isEmpty()) {
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                return defausseCouleurDemandeeSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
            }
        }
        if(tours_.isEmpty()) {
            if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                return defausseCouleurDemandeeSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
            }
        }
        return defausseCouleurDemandeeSurAdversaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
    }
    private CardBelote fournirAtoutToutAtout(byte _numero,
            HandBelote _mainJoueur,
            HandBelote _cartesJouables) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        boolean maitreJeu_=info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=equipeQuiVaFairePliToutAtout(info_, carteForte_, _numero);
        EnumMap<Suit,HandBelote> repartitionJouables_=_cartesJouables.couleurs(bid);
        //jeu tout atout
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            reason += format(FOE_WIN_TRICK);

            return main(repartition_,couleurDemandee_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            reason += format(PARTNER_WIN_TRICK);


            for(byte joueur_:adversaire_) {
                if(!main(cartesPossibles_,couleurDemandee_,joueur_).estVide()) {
                    if(main(cartesPossibles_,couleurDemandee_,joueur_).premiereCarte().strength(couleurDemandee_,bid)>main(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)) {
                        return main(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
            }
            if(main(repartitionJouables_,couleurDemandee_).nombreCartesPoints(bid)>1) {
                return cartePlusPetitePoints(suite(suites_,couleurDemandee_),bid);
            }
            return main(repartitionJouables_,couleurDemandee_).derniereCarte();

        }

        EqList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_,couleurDemandee_, cartesCertaines_,carteForte_);
        if(main(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)) {
            return main(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        if(maitreJeu_) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        boolean nePeutMonterSurCarteForte_ = true;
        for(byte j: adversaireNonJoue_) {
            if(main(cartesPossibles_,couleurDemandee_,j).estVide()) {
                //si pas d'atout
                continue;
            }
            if(main(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)) {
                continue;
            }
            nePeutMonterSurCarteForte_ = false;
            break;
        }
        //si aucun adv non joue ne possede pas d'atout sup ou egal a la carte forte
        if(nePeutMonterSurCarteForte_) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(!main(cartesMaitresses_,couleurDemandee_).estVide()) {
            byte min_=-1;
            byte forceMin_ = 0;
            for(byte j: adversaireNonJoue_) {
                byte forceMinLoc_ = main(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid);
                if(forceMinLoc_ > forceMin_) {
                    forceMin_ = forceMinLoc_;
                }
            }
            //pour prendre la main, il faut avoir une carte superieur ou egal au max des cartes des adversaires n'ayant pas joue
            for(HandBelote suite_:suite(suites_,couleurDemandee_)) {
                if(suite_.premiereCarte().strength(couleurDemandee_,bid)>forceMin_) {
                    min_++;
                } else {
                    break;
                }
            }
            return main(suites_,couleurDemandee_,min_).premiereCarte();
        }
        return main(repartitionJouables_,couleurDemandee_).derniereCarte();

    }
    private CardBelote defausseAtoutToutAtout(byte _numero,
            HandBelote _mainJoueur) {
        BeloteInfoPliEnCours info_ = initInformations(_numero,_mainJoueur);
        byte nombreJoueurs_=getNombreDeJoueurs();
        EnumMap<Suit,HandBelote> repartition_=_mainJoueur.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=strictCouleursMaitres(suites_,repartitionCartesJouees_,cartesPossibles_,_numero);
        Numbers<Byte> adversaire_ = adversaires(_numero);
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = new Numbers<Byte>();
        for(byte b: joueursNonJoue_) {
            if(!adversaire_.containsObj(b)) {
                continue;
            }
            adversaireNonJoue_.add(b);
        }
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        PossibleTrickWinner ramasseurCertain_=equipeQuiVaFairePliToutAtout(info_, carteForte_, _numero);
        //jeu tout atout
        //
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            reason += format(FOE_WIN_TRICK);

            /*Si le joueur se defausse*/
            return defausseAtoutSurAdversaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleursStrictementMaitresses_);

        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            reason += format(PARTNER_WIN_TRICK);

            return defausseAtoutSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleursStrictementMaitresses_);
        }
        return defausseAtoutSurAdversaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleursStrictementMaitresses_);

    }
    private BeloteInfoPliEnCours initInformations(
            byte _joueurCourant,
            HandBelote _cartes) {
        EnumMap<Suit,HandBelote> repartition_ = _cartes.couleurs(bid);
        Numbers<Byte> joueursNonJoue_ = joueursNAyantPasJoue(_joueurCourant);
        CustList<TrickBelote> plisFaits_ = unionPlis();
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        EnumMap<Suit,HandBelote> repartitionCartesJouees_ = cartesJouees_.couleurs(bid);
        Numbers<Byte> joueursJoue_ = new Numbers<Byte>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreDeJoueurs_; joueur_++) {
            if (!joueursNonJoue_.containsObj(joueur_) && joueur_ != _joueurCourant) {
                joueursJoue_.add(joueur_);
            }
        }
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = cartesPossibles(
                repartitionCartesJouees_,
                plisFaits_,
                repartition_,
                _joueurCourant,
                couleurAtout());

        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandBelote>>> hypotheses_ = cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        byte ramasseurVirtuel_ = progressingTrick.getRamasseurPliEnCours(nombreDeJoueurs_,bid);
        EnumMap<Suit,EqList<HandBelote>> suitesTouteCouleur_ = new EnumMap<Suit,EqList<HandBelote>>();

        for (Suit i : couleurs()) {
            suitesTouteCouleur_.put(i,repartition_.getVal(i).eclater(
                    repartitionCartesJouees_, bid));
            //les couleurs sont classees comme si elles etaient demandees
        }


        boolean maitreAtout_ = true;
        if(bid.getCouleurDominante()) {
            maitreAtout_ = strictMaitreAtout(cartesPossibles_,_joueurCourant,suite(suitesTouteCouleur_,couleurAtout()),repartitionCartesJouees_);
        }
        EnumList<Suit> couleursMaitresses_ = couleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_, cartesPossibles_,_joueurCourant);
        EnumMap<Suit,HandBelote> cartesMaitresses_ = cartesMaitresses(
                repartition_, repartitionCartesJouees_,bid);
        boolean maitreJeu_ = maitreAtout_ && couleursMaitresses_.size() == Suit.couleursOrdinaires().size();

        BeloteInfoPliEnCours info_ = new BeloteInfoPliEnCours();
        info_.setContrat(bid);
        info_.setJoueursNonJoue(joueursNonJoue_);
        info_.setJoueursJoue(joueursJoue_);
        info_.setPlisFaits(plisFaits_);
        info_.setCartesJouees(cartesJouees_);
        info_.setRepartitionCartesJouees(repartitionCartesJouees_);
        info_.setCartesPossibles(cartesPossibles_);
        info_.setCartesCertaines(cartesCertaines_);
        info_.setRamasseurVirtuel(ramasseurVirtuel_);
        info_.setSuitesTouteCouleur(suitesTouteCouleur_);
        info_.setMaitreAtout(maitreAtout_);
        info_.setCouleursMaitresses(couleursMaitresses_);
        info_.setCartesMaitresses(cartesMaitresses_);
        info_.setMaitreJeu(maitreJeu_);
        //depend de partie et de cartesJouables
        //carteEntamee
        //joueursNonJoue
        //joueursConfianceNonJoue
        //joueursNonConfianceNonJoue
        //CustList<PliBelote> plisFaits
        //CustList<Byte> joueursJoue
        return info_;
    }
    /**Retourne vrai si et seulement si le joueur ne peut pas fournir la couleur donnee et peut couper avec un atout*/
    private static boolean peutCouper(Suit _couleur,byte _numero,EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, Suit _couleurAtout) {
        return main(_cartesPossibles,_couleur,_numero).estVide()&&!main(_cartesPossibles,_couleurAtout,_numero).estVide();
    }
    /**Renvoie un entier 0 si joueur de non confiance qui va faire le pli 1 si joueur de confiance va faire le pli et -1 sinon
    @param cartes_possibles l'ensemble des cartes probablement possedees par les joueurs
    @param cartes_certaines l'ensemble des cartes surement possedees par les joueurs
    @param ramasseur_virtuel le joueur, qui sans les cartes jouees par les derniers joueurs du pli est ramasseur
    @param _carteForte la carte qui est en train de dominer le pli
    @param joueurs_non_joue l'ensemble des joueurs n'ayant pas encore joue leur carte
    @param _numero le numero du joueur qui va jouer*/
    private PossibleTrickWinner equipeQuiVaFairePliCouleurDominante(
            BeloteInfoPliEnCours _info,
            CardBelote _carteForte,byte _numero) {
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Numbers<Byte> joueursNonJoue_=_info.getJoueursNonJoue();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        Numbers<Byte> joueursConfiance_=new Numbers<Byte>();
        Numbers<Byte> partenaire_ = partenaires(_numero);
        Numbers<Byte> adversaires_=adversaires(_numero);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        Suit couleurAtout_=couleurAtout();
        boolean ramasseurVirtuelEgalCertain_=false;
        Numbers<Byte> joueursNonConfianceNonJoue_=intersectionJoueurs(joueursNonJoue_, adversaires_);
        Numbers<Byte> joueursConfianceNonJoue_=intersectionJoueurs(joueursNonJoue_, partenaire_);
        Numbers<Byte> joueursJoue_=autresJoueurs(joueursNonJoue_, getNombreDeJoueurs());
        /*Le contrat n est ni sans-atout ni tout atout.*/
        if(_carteForte.couleur()==couleurAtout_&&couleurDemandee_!=couleurAtout_) {
            /*Le pli est coupe*/
            if(!main(cartesCertaines_,couleurDemandee_,_numero).estVide()||main(cartesCertaines_,couleurAtout_,_numero).estVide()||
                    main(cartesCertaines_,couleurAtout_,_numero).premiereCarte().strength(couleurDemandee_,bid)
                    <_carteForte.strength(couleurDemandee_,bid)) {
                /*Le joueur numero ne peut pas prendre la main*/
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    /*On cherche a savoir si le ramasseur virtuel (joueur de confiance) va avec sa coupe sur la couleur demandee dominer tous les atouts des joueurs de non confiance eventuels*/
                    if(ramasseurBatAdvSur(_info,joueursNonConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                    ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                    if(existeJoueurNonJoueBattantAdv(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurDemandee_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                    ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                    if(existeJoueurNonJoueBattantPtm(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, joueursJoue_, couleurDemandee_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                    ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                    if(existeJoueurAdvRamBatAdvSur(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                        return PossibleTrickWinner.FOE_TEAM;
                    }
                    /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                    ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                    if(existeJoueurAdvRamBatPtmSur(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, joueursJoue_, couleurDemandee_)) {
                        return PossibleTrickWinner.FOE_TEAM;
                    }
                    return PossibleTrickWinner.UNKNOWN;
                }
                /*ramasseurVirtuel n'est pas un joueur de confiance pour le joueur numero*/
                /*On cherche a savoir si le ramasseur virtuel (joueur de non confiance) bat tous les joueurs de confiance n'ayant pas joue*/
                if(ramasseurBatAdvSur(_info,joueursConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurNonJoueBattantAdv(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurNonJoueBattantPtm(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, joueursJoue_, couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurAdvRamBatAdvSur(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurAdvRamBatPtmSur(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, joueursJoue_, couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
                /*Fin joueurDeConfiance.contains(ramasseurVirtuel)*/
            }
            /*Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()||cartesCertaines.get(1).get(numero).estVide()||cartesCertaines.get(1).get(numero).premiereCarte().getValeur()<carteForte.getValeur()
            (fin test de possibilite pour le joueur numero de prendre le pli)*/
            /*Le joueur numero peut prendre la main en surcoupant le ramasseur virtuel*/
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJoueurBatAdvNum(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, _numero, couleurDemandee_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJoueurBatPtmNum(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, joueursJoue_, _numero, couleurDemandee_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJoueurBatAdvNum(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, _numero, couleurDemandee_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJoueurBatPtmNum(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_,joueursJoue_, _numero, couleurDemandee_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        if(_carteForte.couleur()==couleurDemandee_&&couleurDemandee_!=couleurAtout_) {
            /*La couleur demandee n'est pas de l'atout et le pli n'est pas coupe*/
            ramasseurVirtuelEgalCertain_=false;
            for(byte joueur_:joueursConfianceNonJoue_) {
                if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if(ramasseurVirtuelEgalCertain_) {
                ramasseurVirtuelEgalCertain_=true;
                for(byte joueur_:joueursNonConfianceNonJoue_) {
                    if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_,_info.getCouleurAtout()))) {
                        ramasseurVirtuelEgalCertain_ = false;
                    }
                }
                if(ramasseurVirtuelEgalCertain_) {
                    return PossibleTrickWinner.TEAM;
                }
                if(existeJoueurNonJoueBattantAdv(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if(existeJoueurNonJoueBattantPtm(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,joueursJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if(existeJoueurNonJoueBattantAdv(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if(existeJoueurNonJoueBattantPtm(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_,joueursJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            ramasseurVirtuelEgalCertain_=false;
            for(byte joueur_:joueursNonConfianceNonJoue_) {
                if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if(ramasseurVirtuelEgalCertain_) {
                for(byte joueur_:joueursConfianceNonJoue_) {
                    if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_,_info.getCouleurAtout()))) {
                        ramasseurVirtuelEgalCertain_ = false;
                    }
                }
                if(ramasseurVirtuelEgalCertain_) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if(existeJoueurNonJoueBattantAdv(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if(existeJoueurNonJoueBattantPtm(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_,joueursJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if(existeJoueurNonJoueBattantAdv(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if(existeJoueurNonJoueBattantPtm(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,joueursJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            if(!main(cartesPossibles_,couleurDemandee_,_numero).estVide()
                    &&main(cartesPossibles_,couleurDemandee_,_numero).premiereCarte().strength(couleurDemandee_,bid)>_carteForte.strength(couleurDemandee_,bid)) {
                /*Si le joueur numero peut prendre la main sans couper*/
                /*On ne sait pas si un joueur n'ayant pas joue va couper le pli ou non*/
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    if(ramasseurBatSsCprAdv(_info,joueursNonConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    return PossibleTrickWinner.UNKNOWN;
                }
                /*Fin joueursDeConfiance.contains(ramasseurVirtuel)*/
                return PossibleTrickWinner.UNKNOWN;
            }
            /*Fin si le joueur numero peut prendre la main sans couper*/
            if(peutCouper(couleurDemandee_,_numero,cartesPossibles_,_info.getCouleurAtout())) {
                /*Si le joueur numero peut prendre la main en coupant*/
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurBatAdvNum(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, _numero, couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurBatPtmNum(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, joueursJoue_, _numero, couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurBatAdvNum(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, _numero, couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurBatPtmNum(_info,joueursConfiance_, joueursNonConfianceNonJoue_, joueursJoue_, _numero, couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*Le joueur numero ne peut pas prendre la main*/
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                if(ramasseurBatSsCprAdv(_info,joueursNonConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*Fin joueursDeConfiance.contains(ramasseurVirtuel)*/
            /*Maintenant le ramasseur virtuel n'est pas un joueur de confiance*/
            if(ramasseurBatSsCprAdv(_info,joueursConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        /*Le pli n'est pas coupe et la couleur demandee est l'atout*/
        if(main(cartesCertaines_,couleurAtout_,_numero).estVide()
                ||main(cartesCertaines_,couleurAtout_,_numero).premiereCarte().strength(couleurDemandee_,bid)<_carteForte.strength(couleurDemandee_,bid)) {
            /*Si le joueur numero ne peut pas prendre la main sur demande d'atout*/
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                /*Si le ramasseur virtuel (de confiance, ici) domine certainement les joueurs de non confiance n'ayant pas joue*/
                if(ramasseurBatAdvDemat(_info,joueursNonConfianceNonJoue_, couleurAtout_, _carteForte)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatAdvDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurAtout_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatPtmDemat(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,joueursJoue_, couleurAtout_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatAdvSurDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, _carteForte, couleurAtout_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatPtmSurDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, joueursJoue_, _carteForte, couleurAtout_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*ramasseurVirtuel n'est pas un joueur de confiance pour le joueur numero*/
            /*Si le ramasseur virtuel (de non confiance, ici) domine certainement les joueurs de non confiance n'ayant pas joue*/
            if(ramasseurBatAdvDemat(_info,joueursConfianceNonJoue_, couleurAtout_, _carteForte)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatAdvDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurAtout_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatPtmDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, joueursJoue_, couleurAtout_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatAdvSurDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, _carteForte, couleurAtout_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatPtmSurDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, joueursJoue_, _carteForte, couleurAtout_)) {
                return PossibleTrickWinner.TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
            /*Fin joueurDeConfiance.contains(ramasseurVirtuel)*/
        }
        /*Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()||cartesCertaines.get(1).get(numero).estVide()||cartesCertaines.get(1).get(numero).premiereCarte().getValeur()<carteForte.getValeur()
            (fin test de possibilite pour le joueur numero de prendre le pli)*/
        /*Le joueur numero peut prendre la main en utilisant un atout sur demande d'atout*/
        /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatAdvNumDemat(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,_numero, couleurAtout_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatPtmNumDemat(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,joueursJoue_,_numero, couleurAtout_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatAdvNumDemat(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_, _numero, couleurAtout_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatPtmNumDemat(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_,joueursJoue_,_numero, couleurAtout_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    private PossibleTrickWinner equipeQuiVaFairePliSansAtout(
            BeloteInfoPliEnCours _info,
            CardBelote _carteForte,byte _numero) {

        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        Numbers<Byte> joueursNonJoue_=_info.getJoueursNonJoue();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        Numbers<Byte> partenaire_ = partenaires(_numero);
        Numbers<Byte> adversaires_=adversaires(_numero);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        Numbers<Byte> joueursNonConfianceNonJoue_=intersectionJoueurs(joueursNonJoue_, adversaires_);
        Numbers<Byte> joueursConfianceNonJoue_=intersectionJoueurs(joueursNonJoue_, partenaire_);

        /*La couleur demandee n'est pas de l'atout et le pli n'est pas coupe*/
        if(!main(cartesPossibles_,couleurDemandee_,_numero).estVide()
                &&main(cartesPossibles_,couleurDemandee_,_numero).premiereCarte().strength(couleurDemandee_,bid)>_carteForte.strength(couleurDemandee_,bid)) {
            /*Si le joueur numero peut prendre la main sans couper*/
            /*On ne sait pas si un joueur n'ayant pas joue va couper le pli ou non*/
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                if(ramasseurBatSsCprAdv(_info,joueursNonConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*Fin joueursDeConfiance.contains(ramasseurVirtuel)*/
            return PossibleTrickWinner.UNKNOWN;
        }
        /*Fin si le joueur numero peut prendre la main sans couper*/
        /*Le joueur numero ne peut pas prendre la main*/
        if(partenaire_.containsObj(ramasseurVirtuel_)) {
            if(ramasseurBatSsCprAdv(_info,joueursNonConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                return PossibleTrickWinner.TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        /*Fin joueursDeConfiance.contains(ramasseurVirtuel)*/
        /*Maintenant le ramasseur virtuel n'est pas un joueur de confiance*/
        if(ramasseurBatSsCprAdv(_info,joueursConfianceNonJoue_, couleurDemandee_, _carteForte)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }
    private PossibleTrickWinner equipeQuiVaFairePliToutAtout(
            BeloteInfoPliEnCours _info,
            CardBelote _carteForte,byte _numero) {

        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Numbers<Byte> joueursNonJoue_=_info.getJoueursNonJoue();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        Numbers<Byte> partenaire_ = partenaires(_numero);
        Numbers<Byte> adversaires_=adversaires(_numero);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        Numbers<Byte> joueursNonConfianceNonJoue_=intersectionJoueurs(joueursNonJoue_, adversaires_);
        Numbers<Byte> joueursConfianceNonJoue_=intersectionJoueurs(joueursNonJoue_, partenaire_);
        Numbers<Byte> joueursJoue_=autresJoueurs(joueursNonJoue_, getNombreDeJoueurs());

        /*Le pli n'est pas coupe et la couleur demandee est l'atout*/
        if(main(cartesCertaines_,couleurDemandee_,_numero).estVide()
                ||main(cartesCertaines_,couleurDemandee_,_numero).premiereCarte().strength(couleurDemandee_,_info.getContrat())<_carteForte.strength(couleurDemandee_,_info.getContrat())) {
            /*Si le joueur numero ne peut pas prendre la main sur demande d'atout*/
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                /*Si le ramasseur virtuel (de confiance, ici) domine certainement les joueurs de non confiance n'ayant pas joue*/
                if(ramasseurBatAdvDemat(_info,joueursNonConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatAdvDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatPtmDemat(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,joueursJoue_, couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatAdvSurDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, _carteForte, couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatPtmSurDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, joueursJoue_, _carteForte, couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*ramasseurVirtuel n'est pas un joueur de confiance pour le joueur numero*/
            /*Si le ramasseur virtuel (de non confiance, ici) domine certainement les joueurs de non confiance n'ayant pas joue*/
            if(ramasseurBatAdvDemat(_info,joueursConfianceNonJoue_, couleurDemandee_, _carteForte)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatAdvDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurDemandee_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatPtmDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, joueursJoue_, couleurDemandee_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatAdvSurDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, _carteForte, couleurDemandee_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatPtmSurDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, joueursJoue_, _carteForte, couleurDemandee_)) {
                return PossibleTrickWinner.TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
            /*Fin joueurDeConfiance.contains(ramasseurVirtuel)*/
        }
        /*Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()||cartesCertaines.get(1).get(numero).estVide()||cartesCertaines.get(1).get(numero).premiereCarte().getValeur()<carteForte.getValeur()
            (fin test de possibilite pour le joueur numero de prendre le pli)*/
        /*Le joueur numero peut prendre la main en utilisant un atout sur demande d'atout*/
        /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatAdvNumDemat(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,_numero, couleurDemandee_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatPtmNumDemat(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,joueursJoue_,_numero, couleurDemandee_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatAdvNumDemat(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_, _numero, couleurDemandee_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatPtmNumDemat(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_,joueursJoue_,_numero, couleurDemandee_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }
    /**Retourne vrai si et seulement si le joueur ne peut pas couper avec un atout, car il possede encore de la couleur demandee ou ne possede pas d atout*/
    private static boolean nePeutCouper(Suit _couleur,byte _numero,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EnumMap<Suit,EqList<HandBelote>> _cartesCertaines,
            Suit _couleurAtout) {
        return main(_cartesPossibles,_couleurAtout,_numero).estVide()||!main(_cartesCertaines,_couleur,_numero).estVide();
    }
    private static boolean existeJouBatPtmNumDemat(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue,byte _numero,
            Suit _couleurDemandee) {
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(!main(cartesPossibles_,_couleurDemandee,joueur_).estVide()) {
                byte maxForce_=main(cartesPossibles_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemandee,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for(byte joueur2_:_joueursJoue) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemandee,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(maxForce_>main(cartesPossibles_,_couleurDemandee,_numero).premiereCarte().strength(_couleurDemandee,contrat_))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJouBatAdvNumDemat(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            byte _numero,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(!main(cartesCertaines_,_couleurDemandee,joueur_).estVide()) {
                byte maxForce_=main(cartesCertaines_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemandee,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(maxForce_>main(cartesPossibles_,_couleurDemandee,_numero).premiereCarte().strength(_couleurDemandee,contrat_))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJouBatPtmSurDemat(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue,
            CardBelote _carteForte,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(!main(cartesPossibles_,_couleurDemandee,joueur_).estVide()) {
                byte maxForce_=main(cartesPossibles_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemandee,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for(byte joueur2_:_joueursJoue) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemandee,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(maxForce_>_carteForte.strength(_couleurDemandee,contrat_))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJouBatAdvSurDemat(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            CardBelote _carteForte,
            Suit _couleurDemadee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(!main(cartesCertaines_,_couleurDemadee,joueur_).estVide()) {
                byte maxForce_=main(cartesCertaines_,_couleurDemadee,joueur_).premiereCarte().strength(_couleurDemadee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemadee,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,_couleurDemadee,joueur2_).premiereCarte().strength(_couleurDemadee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(maxForce_>_carteForte.strength(_couleurDemadee,contrat_))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJouBatPtmDemat(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(!main(cartesPossibles_,_couleurDemandee,joueur_).estVide()) {
                byte maxForce_=main(cartesPossibles_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemandee,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for(byte joueur2_:_joueursJoue) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemandee,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJouBatAdvDemat(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(!main(cartesCertaines_,couleurAtout_,joueur_).estVide()) {
                byte maxForce_=main(cartesCertaines_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemandee,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean ramasseurBatAdvDemat(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Suit _couleurDemandee,CardBelote _carteForte) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        boolean ramasseurDeter_=true;
        boolean ramasseurVirtuelEgalCertain_=false;
        byte maxForce_=_carteForte.strength(_couleurDemandee,contrat_);
        for(byte joueur_:_equipeABattre) {
            ramasseurVirtuelEgalCertain_=main(cartesPossibles_,_couleurDemandee,joueur_).estVide();
            if(!ramasseurVirtuelEgalCertain_) {
                if (maxForce_>main(cartesPossibles_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJoueurBatPtmNum(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue,
            byte _numero,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        Suit couleurAtout_=_info.getCouleurAtout();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(vaCouper(_couleurDemandee, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                byte maxForce_=main(cartesPossibles_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!main(cartesCertaines_,_couleurDemandee,joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for(byte joueur2_:_joueursJoue) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(maxForce_>main(cartesCertaines_,couleurAtout_,_numero).premiereCarte().strength(_couleurDemandee,contrat_))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJoueurBatAdvNum(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            byte _numero,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        Suit couleurAtout_=_info.getCouleurAtout();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(vaCouper(_couleurDemandee, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                byte maxForce_=main(cartesCertaines_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!main(cartesCertaines_,_couleurDemandee,joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(maxForce_>main(cartesCertaines_,couleurAtout_,_numero).premiereCarte().strength(_couleurDemandee,contrat_))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJoueurAdvRamBatPtmSur(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(vaCouper(_couleurDemandee, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                byte maxForce_=main(cartesPossibles_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!main(cartesCertaines_,_couleurDemandee,joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for(byte joueur2_:_joueursJoue) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJoueurAdvRamBatAdvSur(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            Suit _couleurDemandee,
            CardBelote _carteForte) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(vaCouper(_couleurDemandee, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (main(cartesCertaines_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_) >main(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!main(cartesCertaines_,_couleurDemandee,joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(main(cartesCertaines_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_)>_carteForte.strength(_couleurDemandee,contrat_))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJoueurNonJoueBattantPtm(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(vaCouper(_couleurDemandee, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                byte maxForce_=main(cartesPossibles_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!main(cartesCertaines_,_couleurDemandee,joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for(byte joueur2_:_joueursJoue) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>main(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean existeJoueurNonJoueBattantAdv(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Numbers<Byte> _equipeDom,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=false;
        boolean ramasseurVirtuelEgalCertain_=false;
        for(byte joueur_:_equipeDom) {
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            boolean joueurBatAdversaire_ = true;
            if(vaCouper(_couleurDemandee,joueur_,cartesPossibles_,cartesCertaines_,couleurAtout_)) {
                for(byte joueur2_:_equipeABattre) {
                    ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (main(cartesCertaines_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_) >main(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!main(cartesCertaines_,_couleurDemandee,joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    private static boolean ramasseurBatAdvSur(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Suit _couleurDemandee,
            CardBelote _carteForte) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurVirtuelEgalCertain_=false;
        boolean ramasseurDeter_=true;
        byte maxForce_=_carteForte.strength(_couleurDemandee,contrat_);
        for(byte joueur_:_equipeABattre) {
            ramasseurVirtuelEgalCertain_=main(cartesPossibles_,couleurAtout_,joueur_).estVide();
            if(!ramasseurVirtuelEgalCertain_) {
                if (maxForce_>main(cartesPossibles_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (!main(cartesCertaines_,_couleurDemandee,joueur_).estVide()) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }
    private static boolean ramasseurBatSsCprAdv(
            BeloteInfoPliEnCours _info,
            Numbers<Byte> _equipeABattre,
            Suit _couleurDemandee,
            CardBelote _carteForte) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurVirtuelEgalCertain_=false;
        boolean ramasseurDeter_=true;
        byte maxForce_=_carteForte.strength(_couleurDemandee,contrat_);
        for(byte joueur_:_equipeABattre) {
            ramasseurVirtuelEgalCertain_=!main(cartesCertaines_,_couleurDemandee,joueur_).estVide();
            if(ramasseurVirtuelEgalCertain_) {
                if (!(maxForce_>main(cartesPossibles_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_))) {
                    ramasseurVirtuelEgalCertain_ = false;
                }
            }
            if (defausse(_couleurDemandee, joueur_, cartesPossibles_,contrat_)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }
    //adversaires non joues
    private boolean defausseOuPasDeFigure(
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Suit _couleurDemandee,
            Numbers<Byte> _joueurs) {
        boolean defausseOuPasDeFigure_ = true;
        for(byte j: _joueurs) {
            if(defausse(_couleurDemandee, j, _cartesPossibles,bid)) {
                continue;
            }
            if(nePeutPasAvoirFigures(_cartesPossibles, j, _couleurDemandee, bid)) {
                continue;
            }
            defausseOuPasDeFigure_ = false;
        }
        return defausseOuPasDeFigure_;
    }
    private static boolean defausse(Suit _couleur,byte _joueur,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, BidBeloteSuit _contrat) {
        if(_contrat.getCouleurDominante()) {
            return main(_cartesPossibles,_contrat.getCouleur(),_joueur).estVide()&&main(_cartesPossibles,_couleur,_joueur).estVide();
        }
        return main(_cartesPossibles,_couleur,_joueur).estVide();
    }
    /**Est vrai si et seulement si on est sur que le joueur va couper le pli a la couleur demandee*/
    private static boolean vaCouper(Suit _couleur,byte _joueur,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EnumMap<Suit,EqList<HandBelote>> _cartesCertaines,
            Suit _couleurAtout) {
        return main(_cartesPossibles,_couleur,_joueur).estVide()&&!main(_cartesCertaines,_couleurAtout,_joueur).estVide();
    }

    private CardBelote coupe(
            EnumMap<Suit,HandBelote> _repartitionJouables,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumMap<Suit,EqList<HandBelote>> _suites,
            Numbers<Byte> _adversaireNonJoue,
            Suit _couleurAtout, Suit _couleurDemandee,
            Numbers<Byte> _tours,CardBelote _carteForte,
            EqList<HandBelote> _cartesRelMaitres,boolean _maitreJeu) {
        byte nombrePointsPli_;
        if(main(_repartitionJouables,_couleurAtout).premiereCarte().strength(_couleurDemandee, bid)<_carteForte.strength(_couleurDemandee, bid)) {
            //sous coupe
            return main(_repartitionJouables,_couleurAtout).derniereCarte();
        }
        if(_maitreJeu && !_cartesRelMaitres.isEmpty()) {
            //sur coupe ou coupe
            return _cartesRelMaitres.last().premiereCarte();
        }
        /*Deuxieme tour ou plus si surcoupe possible*/
        boolean surcoupePossible_ = false;
        for(byte j: _adversaireNonJoue) {
            if(main(_cartesPossibles,_couleurAtout,j).estVide()) {
                continue;
            }
            if(!main(_cartesPossibles,_couleurDemandee,j).estVide()) {
                continue;
            }
            surcoupePossible_ = true;
            break;
        }
        //si pas d'adv non joue => pas de surcoupe possible
        if(!_tours.isEmpty()
                &&surcoupePossible_) {
            if(suite(_suites,_couleurAtout).size()==1||main(_cartesMaitresses,_couleurAtout).estVide()) {
                return main(_repartitionJouables,_couleurAtout).derniereCarte();
            }
            nombrePointsPli_=0;
            for(CardBelote carte_:progressingTrick) {
                nombrePointsPli_+=carte_.points(bid);
            }
            if(nombrePointsPli_>=10&&!_cartesRelMaitres.isEmpty()) {
                return _cartesRelMaitres.last().premiereCarte();
            }
            return main(_repartitionJouables,_couleurAtout).derniereCarte();
        }
        if(suite(_suites,_couleurAtout).size()==1) {
            return main(_repartitionJouables,_couleurAtout).premiereCarte();
        }
        if(!main(_cartesMaitresses,_couleurAtout).estVide()) {
            return suite(_suites,_couleurAtout).last().premiereCarte();
        }
        if(main(_repartitionJouables,_couleurAtout).premiereCarte().points(bid)>0) {
            return main(_repartitionJouables,_couleurAtout).premiereCarte();
        }
        return main(_repartitionJouables,_couleurAtout).derniereCarte();
    }

    /**Est vrai si et seulement si le partenaire du joueur qui va jouer domine l'adversaire n'ayant pas joue (uniquement si le partenaire est maitre temporairement du pli)*/
    private boolean partenaireBatAdversaireNonJoue(
            Numbers<Byte> _adversaireNonJoue,Suit _couleurDemandee,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            CardBelote _carteForte) {
        boolean adversairesBattus_ = true;
        for(byte j:_adversaireNonJoue) {
            if(main(_cartesPossibles,_couleurDemandee,j).estVide()) {
                continue;
            }
            if(_carteForte.strength(_couleurDemandee, bid)>main(_cartesPossibles,_couleurDemandee,j).premiereCarte().strength(_couleurDemandee, bid)) {
                continue;
            }
            adversairesBattus_ = false;
            break;
        }
        return adversairesBattus_;
    }
    //adversairenonjoue
    private static boolean pasAtoutJoueurs(Numbers<Byte> _joueurs,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Suit _couleurAtout) {
        for (byte j: _joueurs) {
            if (!pasAtout(j, _cartesPossibles, _couleurAtout)) {
                return false;
            }
        }
        return true;
    }
    private static boolean pasAtout(
            byte _joueur,EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Suit _couleurAtout) {
        return main(_cartesPossibles,_couleurAtout,_joueur).estVide();
    }

    private CardBelote sauveQuiPeutFigure(
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EqList<HandBelote> _suites,
            EqList<HandBelote> _cartesRelMaitres,Numbers<Byte> _adversaireNonJoue,
            Suit _couleurDemandee) {
        //si aucun adversaire ne possede une carte a point dans la couleur demandee, alors les points peuvent etre sauves
        boolean aucuneCartePointsAdvNonJoue_ = true;
        for(byte j: _adversaireNonJoue) {
            if(main(_cartesPossibles,_couleurDemandee,j).estVide()) {
                continue;
            }
            if(main(_cartesPossibles,_couleurDemandee,j).premiereCarte().points(bid)<1) {
                continue;
            }
            aucuneCartePointsAdvNonJoue_ = false;
            break;
        }
        if(aucuneCartePointsAdvNonJoue_) {
            return jeuFigureHauteDePlusFaibleSuite(_suites,bid);
        }
        if(!_cartesRelMaitres.isEmpty()) {
            if(_cartesRelMaitres.size()==1||_cartesRelMaitres.get(1).premiereCarte().points(bid)<1) {
                return _suites.get(0).premiereCarte();
            }
            return _cartesRelMaitres.get(1).premiereCarte();
        }
        return _suites.last().derniereCarte();
    }
    //adversairenonjoue
    private static boolean nePeutPasAvoirFigures(EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,byte _joueur, Suit _couleurDemandee, BidBeloteSuit _contrat) {
        return main(_cartesPossibles,_couleurDemandee,_joueur).estVide()||main(_cartesPossibles,_couleurDemandee,_joueur).premiereCarte().points(_contrat)==0;
    }
    private EqList<HandBelote> cartesRelativementMaitre(
            EqList<HandBelote> _suites,EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Numbers<Byte> _joueursNonJoue, Suit _couleurDemandee,
            Suit _couleurJoueur,Suit _couleurAtout,
            EnumMap<Suit,EqList<HandBelote>> _cartesCertaines,CardBelote _carteForte) {
        byte maxValeur_=0;
        EqList<HandBelote> suitesBis_=new EqList<HandBelote>();
        if(_couleurJoueur==_couleurAtout&&_couleurDemandee!=_couleurAtout) {
            for(byte joueur_:_joueursNonJoue) {
                if(!main(_cartesPossibles,_couleurJoueur,joueur_).estVide()&&main(_cartesCertaines,_couleurDemandee,joueur_).estVide()) {
                    maxValeur_=(byte)Math.max(main(_cartesPossibles,_couleurJoueur,joueur_).premiereCarte().strength(_couleurDemandee,bid),maxValeur_);
                }
            }
        } else {
            for(byte joueur_:_joueursNonJoue) {
                if(!main(_cartesPossibles,_couleurJoueur,joueur_).estVide()) {
                    maxValeur_=(byte)Math.max(main(_cartesPossibles,_couleurJoueur,joueur_).premiereCarte().strength(_couleurDemandee,bid),maxValeur_);
                }
            }
        }
        maxValeur_=(byte)Math.max(maxValeur_,_carteForte.strength(_couleurDemandee,bid));
        for(HandBelote suite_:_suites) {
            if(suite_.premiereCarte().strength(_couleurDemandee,bid)>maxValeur_) {
                suitesBis_.add(suite_);
            } else {
                break;
            }
        }
        return suitesBis_;
    }

    HandBelote playableCards(EnumMap<Suit,HandBelote> _repartitionMain) {
        return cartesJouables(_repartitionMain,Constants.getLanguage());
    }
    HandBelote cartesJouables(EnumMap<Suit,HandBelote> _repartitionMain, String _loc) {
        /*Ensemble des cartes jouees sur ce pli*/
        byte numero_=playerHavingToPlay();
        HandBelote m=progressingTrick.getCartes();
        HandBelote cartesJouables_=new HandBelote();
        if(progressingTrick.estVide()) {
            //L'entame est libre a la belote
            for(HandBelote couleur_:_repartitionMain.values()) {
                cartesJouables_.ajouterCartes(couleur_);
            }
            return cartesJouables_;
        }
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        Suit couleurAtout_=couleurAtout();
        byte ramasseurVirtuel_=progressingTrick.getRamasseurPliEnCours(getNombreDeJoueurs(), bid);
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,getNombreDeJoueurs());
        HandBelote leadingSuit_ = main(_repartitionMain,couleurDemandee_);
        if(bid.getCouleurDominante()) {
            HandBelote trumps_ = main(_repartitionMain,couleurAtout_);
            byte valeurForte_=carteForte_.strength(couleurDemandee_,bid);
            if(couleurAtout_==couleurDemandee_) {
                //Nombre d'atouts dans la main du joueur
                if(trumps_.estVide()) {
                    for(HandBelote couleur_:_repartitionMain.values()) {
                        cartesJouables_.ajouterCartes(couleur_);
                    }
                    return cartesJouables_;
                }
                if(trumps_.derniereCarte().strength(couleurDemandee_,bid)>valeurForte_) {
                    cartesJouables_.ajouterCartes(trumps_);
                    if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                        gameError=Format.formatter(FOLDER, file, _loc, PLAY_SUIT, couleurDemandee_.toString(_loc));
                    }
                    return cartesJouables_;
                }
                if(trumps_.premiereCarte().strength(couleurDemandee_,bid)<valeurForte_) {
                    cartesJouables_.ajouterCartes(trumps_);
                    if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                        gameError=Format.formatter(FOLDER, file, _loc, PLAY_SUIT, couleurDemandee_.toString(_loc));
                    }
                    return cartesJouables_;
                }
                byte indexTrump_ = CustList.FIRST_INDEX;
                while (trumps_.carte(indexTrump_).strength(couleurDemandee_,bid)>valeurForte_) {
                    cartesJouables_.ajouter(trumps_.carte(indexTrump_));
                    indexTrump_++;
                }
                if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                    gameError=Format.formatter(FOLDER, file, _loc, PLAY_STRONGER_CARD, carteForte_.toString(_loc));
                }
                return cartesJouables_;
            }
            if(!leadingSuit_.estVide()) {
                cartesJouables_.ajouterCartes(leadingSuit_);
                if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                    gameError=Format.formatter(FOLDER, file, _loc, PLAY_SUIT, couleurDemandee_.toString(_loc));
                }
                return cartesJouables_;
            }
            if(trumps_.estVide()) {
                for(HandBelote couleur_:_repartitionMain.values()) {
                    cartesJouables_.ajouterCartes(couleur_);
                }
                return cartesJouables_;
            }
            if(memeEquipe(ramasseurVirtuel_, numero_)) {
                /*Le partenaire est maitre temporairement*/
                if(surCoupeObligatoirePartenaire()) {
                    if(sousCoupeObligatoirePartenaire()) {
                        if(trumps_.premiereCarte().strength(couleurDemandee_,bid)<valeurForte_) {
                            cartesJouables_.ajouterCartes(trumps_);
                            if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                                gameError=Format.formatter(FOLDER, file, _loc, UNDER_TRUMP_PARTNER, couleurAtout_.toString(_loc));
                            }
                            return cartesJouables_;
                        }
                    }
                    if(trumps_.derniereCarte().strength(couleurDemandee_,bid)>valeurForte_) {
                        cartesJouables_.ajouterCartes(trumps_);
                        if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                            gameError=Format.formatter(FOLDER, file, _loc, OVER_TRUMP_PARTNER, couleurAtout_.toString(_loc));
                        }
                        return cartesJouables_;
                    }
                    if(trumps_.premiereCarte().strength(couleurDemandee_,bid)>valeurForte_) {
                        byte indexTrump_ = CustList.FIRST_INDEX;
                        while (trumps_.carte(indexTrump_).strength(couleurDemandee_,bid)>valeurForte_) {
                            cartesJouables_.ajouter(trumps_.carte(indexTrump_));
                            indexTrump_++;
                        }
                        if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                            gameError=Format.formatter(FOLDER, file, _loc, PLAY_STRONGER_CARD, carteForte_.toString(_loc));
                        }
                        return cartesJouables_;
                    }
//                    (!sousCoupeObligatoirePartenaire()||
//                    main(repartitionMain,couleurAtout).premiereCarte().force(couleurDemandee,contrat)>valeurForte)
                    //main(repartitionMain,couleurAtout).premiereCarte().force(couleurDemandee,contrat)<valeurForte
                    //!sousCoupeObligatoirePartenaire() && main(repartitionMain,couleurAtout).premiereCarte().force(couleurDemandee,contrat)<valeurForte
                }
                if(sousCoupeObligatoirePartenaire()) {
                    if(trumps_.premiereCarte().strength(couleurDemandee_,bid)<valeurForte_) {
                        cartesJouables_.ajouterCartes(trumps_);
                        if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                            gameError=Format.formatter(FOLDER, file, _loc, UNDER_TRUMP_PARTNER, couleurAtout_.toString(_loc));
                        }
                        return cartesJouables_;
                    }
                }
                for(HandBelote couleur_:_repartitionMain.values()) {
                    cartesJouables_.ajouterCartes(couleur_);
                }
                return cartesJouables_;
            }
            HandBelote trumpsTrick_ = main(m.couleurs(bid),couleurAtout_);
            if(trumpsTrick_.estVide()) {
                /*PliBelote non coupe*/
                cartesJouables_.ajouterCartes(trumps_);
                if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                    gameError=Format.formatter(FOLDER, file, _loc, TRUMP_FOE, carteForte_.toString(_loc));
                }
                return cartesJouables_;
            }
            /*PliBelote coupe par un adversaire*/
            if(trumps_.derniereCarte().strength(couleurDemandee_,bid)>valeurForte_) {
                cartesJouables_.ajouterCartes(trumps_);
                if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                    gameError=Format.formatter(FOLDER, file, _loc, OVER_TRUMP_FOE, couleurAtout_.toString(_loc));
                }
                return cartesJouables_;
            }
            if(trumps_.premiereCarte().strength(couleurDemandee_,bid)>valeurForte_) {
                byte indexTrump_ = CustList.FIRST_INDEX;
                while (trumps_.carte(indexTrump_).strength(couleurDemandee_,bid)>valeurForte_) {
                    cartesJouables_.ajouter(trumps_.carte(indexTrump_));
                    indexTrump_++;
                }
                if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                    gameError=Format.formatter(FOLDER, file, _loc, PLAY_STRONGER_CARD, carteForte_.toString(_loc));
                }
                return cartesJouables_;
            }
            if(!sousCoupeObligatoireAdversaire()) {
                for(HandBelote couleur_:_repartitionMain.values()) {
                    cartesJouables_.ajouterCartes(couleur_);
                }
                return cartesJouables_;
            }
            cartesJouables_.ajouterCartes(trumps_);
            if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                gameError=Format.formatter(FOLDER, file, _loc, UNDER_TRUMP_FOE, couleurAtout_.toString(_loc));
            }
            return cartesJouables_;
        }
        if(bid.ordreCouleur()) {
            if(leadingSuit_.estVide()) {
                for(HandBelote couleur_:_repartitionMain.values()) {
                    cartesJouables_.ajouterCartes(couleur_);
                }
                return cartesJouables_;
            }
            cartesJouables_.ajouterCartes(leadingSuit_);
            if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                gameError=Format.formatter(FOLDER, file, _loc, PLAY_SUIT, couleurDemandee_.toString(_loc));
            }
            return cartesJouables_;
        }
        if(leadingSuit_.estVide()) {
            for(HandBelote couleur_:_repartitionMain.values()) {
                cartesJouables_.ajouterCartes(couleur_);
            }
            return cartesJouables_;
        }
        byte valeurForte_=carteForte_.strength(couleurDemandee_,bid);
        if(leadingSuit_.derniereCarte().strength(couleurDemandee_,bid)>valeurForte_
                ||leadingSuit_.premiereCarte().strength(couleurDemandee_,bid)<valeurForte_) {
            cartesJouables_.ajouterCartes(leadingSuit_);
            if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
                gameError=Format.formatter(FOLDER, file, _loc, PLAY_SUIT, couleurDemandee_.toString(_loc));
            }
            return cartesJouables_;
        }
        byte indexTrump_ = CustList.FIRST_INDEX;
        while (leadingSuit_.carte(indexTrump_).strength(couleurDemandee_,bid)>valeurForte_) {
            cartesJouables_.ajouter(leadingSuit_.carte(indexTrump_));
            indexTrump_++;
        }
        if(!cartesJouables_.contientCartes(cardsToBePlayed)) {
            gameError=Format.formatter(FOLDER, file, _loc, PLAY_STRONGER_CARD, carteForte_.toString(_loc));
        }
        return cartesJouables_;
    }

    public HandBelote cartesJouees() {
        HandBelote cartesJouees_=new HandBelote();
        for(TrickBelote pli_:tricks) {
            cartesJouees_.ajouterCartes(pli_.getCartes());
        }
        return cartesJouees_;
    }
    private EnumList<Suit> couleursMaitres(
            EnumMap<Suit,EqList<HandBelote>> _suites,
            EnumMap<Suit,HandBelote> _cartesJouees,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, byte _numero) {
        EnumList<Suit> couleurs_=new EnumList<Suit>();
        if(!bid.getCouleurDominante()) {
            if(bid.ordreCouleur()) {
                for(Suit couleur_:couleurs()) {
                    if(completelyPlayedSuit(_cartesJouees, couleur_)||suite(_suites,couleur_).isEmpty()) {
                        couleurs_.add(couleur_);
                    } else {
                        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                                _numero, couleur_);
                        int maitres_ = getNbLeadingSuitCards(_suites,
                                _cartesJouees, couleur_);
                        if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                            couleurs_.add(couleur_);
                        }
                    }
                }
            } else {
                for(Suit couleur_:couleurs()) {
                    if(completelyPlayedSuit(_cartesJouees, couleur_)||suite(_suites,couleur_).isEmpty()) {
                        couleurs_.add(couleur_);
                    } else {
                        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                                _numero, couleur_);
                        int maitres_ = getNbLeadingTrumpCards(_suites,
                                _cartesJouees, couleur_);
                        if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                            couleurs_.add(couleur_);
                        }
                    }
                }
            }
        } else {
            for(Suit couleur_:couleursNonAtouts()) {
                if(completelyPlayedSuit(_cartesJouees, couleur_)||suite(_suites,couleur_).isEmpty()) {
                    couleurs_.add(couleur_);
                } else {
                    int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                            _numero, couleur_);
                    int maitres_ = getNbLeadingSuitCards(_suites, _cartesJouees,
                            couleur_);
                    if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                        couleurs_.add(couleur_);
                    }
                }
            }
        }
        return couleurs_;
    }
    private EnumList<Suit> strictCouleursMaitres(EnumMap<Suit,EqList<HandBelote>> _suites,
            EnumMap<Suit,HandBelote> _cartesJouees,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, byte _numero) {
        EnumList<Suit> couleurs_=new EnumList<Suit>();
        if(!bid.getCouleurDominante()) {
            if(bid.ordreCouleur()) {
                for(Suit couleur_:couleurs()) {
                    if(completelyPlayedSuit(_cartesJouees, couleur_)) {
                        couleurs_.add(couleur_);
                    } else if(!suite(_suites,couleur_).isEmpty()) {
                        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                                _numero, couleur_);
                        int maitres_ = getNbLeadingSuitCards(_suites,
                                _cartesJouees, couleur_);
                        if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                            couleurs_.add(couleur_);
                        }
                    }
                }
            } else {
                for(Suit couleur_:couleurs()) {
                    if(completelyPlayedSuit(_cartesJouees, couleur_)) {
                        couleurs_.add(couleur_);
                    } else if(!suite(_suites,couleur_).isEmpty()) {
                        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                                _numero, couleur_);
                        int maitres_ = getNbLeadingTrumpCards(_suites,
                                _cartesJouees, couleur_);
                        if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                            couleurs_.add(couleur_);
                        }
                    }
                }
            }
        } else {
            for(Suit couleur_:couleursNonAtouts()) {
                if(completelyPlayedSuit(_cartesJouees, couleur_)) {
                    couleurs_.add(couleur_);
                } else if(!suite(_suites,couleur_).isEmpty()) {
                    int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                            _numero, couleur_);
                    int maitres_ = getNbLeadingSuitCards(_suites, _cartesJouees,
                            couleur_);
                    if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                        couleurs_.add(couleur_);
                    }
                }
            }
        }
        return couleurs_;
    }

    private static boolean completelyPlayedSuit(EnumMap<Suit,HandBelote> _playedCards,
            Suit _suit) {
        return main(_playedCards,_suit).total()==HandBelote.couleurComplete(_suit, Order.SUIT).total();
    }

    private static int getNbMaxPossPlayerCards(
            EnumMap<Suit,EqList<HandBelote>> _possibleCards, byte _player, Suit _suit) {
        int max_=CustList.SIZE_EMPTY;
        /*max designe le nombre maximal de cartes probablement possedees par un joueur a une couleur donnee*/
        int nbSeqs_ = suite(_possibleCards,_suit).size();
        for(int joueur_=CustList.FIRST_INDEX;joueur_<nbSeqs_;joueur_++) {
            if(joueur_!=_player) {
                max_=Math.max(main(_possibleCards,_suit,joueur_).total(),max_);
            }
        }
        return max_;
    }

    private int getNbLeadingTrumpCards(EnumMap<Suit,EqList<HandBelote>> _seqs,
            EnumMap<Suit,HandBelote> _playedCards, Suit _suit) {
        boolean existeCarteMaitresse_=true;
        CardBelote c=main(_seqs,_suit,CustList.FIRST_INDEX).premiereCarte();
        HandBelote cartesCouleurs_ = cartesCouleurs(_suit);
        cartesCouleurs_.setOrdre(Order.TRUMP);
        cartesCouleurs_.trierUnicolore(true);
        for(CardBelote carte_:cartesCouleurs_) {
            if(carte_.strength(_suit,bid)<=c.strength(_suit,bid)) {
                break;
            }
            if(!main(_playedCards,_suit).contient(carte_)&&!main(_seqs,_suit,CustList.FIRST_INDEX).contient(carte_)) {
                existeCarteMaitresse_=false;
                break;
            }
        }


        int maitres_ = CustList.SIZE_EMPTY;
        if(existeCarteMaitresse_) {
            maitres_ = main(_seqs,_suit,CustList.FIRST_INDEX).total();
        }
        return maitres_;
    }

    private int getNbLeadingSuitCards(EnumMap<Suit,EqList<HandBelote>> _seqs,
            EnumMap<Suit,HandBelote> _playedCards, Suit _suit) {
        boolean existeCarteMaitresse_=true;
        CardBelote c=main(_seqs,_suit,CustList.FIRST_INDEX).premiereCarte();
        for(CardBelote carte_:cartesCouleurs(_suit)) {
            if(carte_.strength(_suit,bid)<=c.strength(_suit,bid)) {
                break;
            }
            if(!main(_playedCards,_suit).contient(carte_)&&!main(_seqs,_suit,CustList.FIRST_INDEX).contient(carte_)) {
                existeCarteMaitresse_=false;
                break;
            }
        }
        int maitres_ = CustList.SIZE_EMPTY;
        if(existeCarteMaitresse_) {
            maitres_ = main(_seqs,_suit,CustList.FIRST_INDEX).total();
        }
        return maitres_;
    }
    /**@throws NullPointerException si un des arguments est null*/
    private static boolean egaliteCouleurs(EnumList<Suit> _couleurs1, EnumList<Suit> _couleurs2) {
        return _couleurs1.containsAllObj(_couleurs2) && _couleurs2.containsAllObj(_couleurs1);
    }

    private static EnumList<Suit> intersectionCouleurs(EnumList<Suit> _couleurs1, EnumList<Suit> _couleurs2) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs1) {
            if(!_couleurs2.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }


    private static EnumList<Suit> couleursNonAtoutNonVides(
            HandBelote _main, EnumList<Suit> _couleurs) {
        return couleursNonAtoutAyantNbCartesSupEg(_main,_couleurs,1);
    }
    private static EnumList<Suit> couleursNonAtoutAyantNbCartesSupEg(
            HandBelote _main, EnumList<Suit> _couleurs, int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.tailleCouleur(couleur_) < _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursNonAtoutNonVides(
            EnumMap<Suit,HandBelote> _main, EnumList<Suit> _couleurs) {
        return couleursNonAtoutAyantNbCartesSupEg(_main,_couleurs,1);
    }
    private static EnumList<Suit> couleursNonAtoutAyantNbCartesSupEg(
            EnumMap<Suit,HandBelote> _main, EnumList<Suit> _couleurs, int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.getVal(couleur_).tailleCouleur(couleur_) < _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }


    private static EnumList<Suit> couleursAvecNbPointsInfEg(HandBelote _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs,
            int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.couleur(_contrat,couleur_);
            if (main_.nombreCartesPoints(_contrat) > _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursAvecPoints(HandBelote _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.couleur(_contrat,couleur_);
            if (main_.nombreCartesPoints(_contrat) == 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursAvecPoints(EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.getVal(couleur_);
            if (main_.nombreCartesPoints(_contrat) == 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursSansPoint(EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.getVal(couleur_);
            if (main_.nombreCartesPoints(_contrat) != 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursAvecCarteMaitresse(HandBelote _main,
            HandBelote _cartesJouees, BidBeloteSuit _contrat, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandBelote> couleursMains_ = _main.couleurs(_contrat);
        EnumMap<Suit,HandBelote> cartesJouees_ = _cartesJouees.couleurs(_contrat);
        for (Suit couleur_ : _couleurs) {
            HandBelote cartesMaitresses_ = cartesMaitresses(couleursMains_,
                    cartesJouees_,_contrat).getVal(couleur_);
            if (!cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursSansCarteMaitresse(HandBelote _main,
            HandBelote _cartesJouees, BidBeloteSuit _contrat, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandBelote> couleursMains_ = _main.couleurs(_contrat);
        EnumMap<Suit,HandBelote> cartesJouees_ = _cartesJouees.couleurs(_contrat);
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            HandBelote cartesMaitresses_ = cartesMaitresses(couleursMains_,
                    cartesJouees_,_contrat).getVal(couleur_);
            if (cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursNonEntamees(
            CustList<TrickBelote> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumList<Suit> toutesCouleursOrdinaires_ = couleurs();
        for (TrickBelote pli_ : _plis) {
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (toutesCouleursOrdinaires_.containsObj(couleurDemandee_)) {
                couleursOuvertes_.add(couleurDemandee_);
            }
        }
        for (Suit couleur_ : _couleurs) {
            if (!couleursOuvertes_.containsObj(couleur_)) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    private static EnumList<Suit> couleursNonOuvertesNonVides(HandBelote _main,
            CustList<TrickBelote> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = couleursNonAtoutAyantNbCartesSupEg(_main, _couleurs, 1);
        return couleursNonEntamees(_plis,couleurs_);
    }

    private static EnumList<Suit> couleursOuvertes(
            CustList<TrickBelote> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumList<Suit> toutesCouleursOrdinaires_ = couleurs();
        for (TrickBelote pli_ : _plis) {
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (toutesCouleursOrdinaires_.containsObj(couleurDemandee_)) {
                couleursOuvertes_.add(couleurDemandee_);
            }
        }
        for (Suit couleur_ : _couleurs) {
            if (!couleursOuvertes_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }


    private static EnumList<Suit> couleursCoupeePar(HandBelote _main,
            byte _joueur, BidBeloteSuit _contrat, EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EnumMap<Suit,EqList<HandBelote>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandBelote> couleursMains_ = _main.couleurs(_contrat);
        for (Suit couleur_ : _couleurs) {
            if (!_cartesPossibles.getVal(couleur_).get(_joueur).estVide()) {
                continue;
            }
            if (_cartesCertaines.getVal(_contrat.getCouleur()).get(_joueur).estVide()) {
                continue;
            }
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursPouvantEtreCoupees(
            Numbers<Byte> _joueurs,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Suit _couleurAtout, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(joueursSusceptiblesCoupe(_cartesPossibles, couleur_, _couleurAtout, _joueurs).isEmpty()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursDefausseeParJoueurs(
            Numbers<Byte> _joueurs, BidBeloteSuit _contrat, EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleursDefausses_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            for (Suit couleur_ : _couleurs) {
                if(couleursDefausses_.containsObj(couleur_)) {
                    continue;
                }
                if(!defausse(couleur_, joueur_, _cartesPossibles, _contrat)) {
                    continue;
                }
                couleursDefausses_.add(couleur_);
            }
        }
        return couleursDefausses_;
    }
    private static EnumList<Suit> couleursNonCoupeeParJoueurs(HandBelote _main,
            Numbers<Byte> _joueurs, BidBeloteSuit _contrat, EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EnumMap<Suit,EqList<HandBelote>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleursCoupees_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            EnumList<Suit> couleursLoc_ = couleursCoupeePar(_main,
                    joueur_, _contrat, _cartesPossibles,
                    _cartesCertaines, _couleurs);
            for (Suit couleur_ : couleursLoc_) {
                if(couleursCoupees_.containsObj(couleur_)) {
                    continue;
                }
                couleursCoupees_.add(couleur_);
            }
        }
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(couleursCoupees_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursLesPlusCourtes(EnumMap<Suit,HandBelote> _main,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusCourtes(_main, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusCourtes(
            EnumMap<Suit,HandBelote> _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandBeloteShortLengthComparator(_main));
    }

    private static EnumList<Suit> couleursAvecLePlusPetitNbPoints(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusPetitNbPoints(_main,_contrat,_couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusPetitNbPoints(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>(_couleurs);
        return couleurs_.getGroupsSameCompare(new HandBeloteLowNbPtsCards(_main, _contrat));
    }

    private static EnumList<Suit> couleursLesPlusLongues(
            EnumMap<Suit,HandBelote> _main,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusLongues(_main, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusLongues(
            EnumMap<Suit,HandBelote> _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandBeloteLongLengthComparator(_main));
    }
    private static EnumList<Suit> couleursAvecLePlusGrandNbPoints(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusGrandNbPoints(_main,_contrat,_couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusGrandNbPoints(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandBeloteGreatNbPtsCards(_main, _contrat));
    }


    private static EnumList<Suit> couleursLesPlusBasses(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusBasses(_main, _contrat,_couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusBasses(
            EnumMap<Suit,HandBelote> _main,BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowHandBeloteComparator(_main, _contrat));
    }


    private static EnumList<Suit> couleursLesPlusHautes(EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusHautes(_main, _contrat, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusHautes(
            EnumMap<Suit,HandBelote> _main, BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthGreatHandBeloteComparator(_main, _contrat));
    }


    private static EnumList<Suit> couleursAvecLaPlusPetiteCarteBasse(EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return couleursTrieesAvecLaPlusPetiteCarteBasse(_main, _contrat, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesAvecLaPlusPetiteCarteBasse(
            EnumMap<Suit,HandBelote> _main, BidBeloteSuit _contrat, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowLastHandBeloteComparator(_main, _contrat));
    }

    private static EnumList<Suit> couleursAvecLaPlusGrandeFigure(EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return couleursTrieesAvecLaPlusGrandeFigure(_main, _contrat, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesAvecLaPlusGrandeFigure(
            EnumMap<Suit,HandBelote> _main, BidBeloteSuit _contrat, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthGreatFirstHandBeloteComparator(_main, _contrat));
    }
    /**Retourne l'ensemble des cartes maitresses dans leur propre couleur.
    Pour recuperer la couleur n i il faut ecrire cartesMaitresses.get(i-2)*/
    private static EnumMap<Suit,HandBelote> cartesMaitresses(
            EnumMap<Suit,HandBelote> _couleurs,
            EnumMap<Suit,HandBelote> _cartesJouees,
            BidBeloteSuit _contrat) {
        Suit couleurAtout_ = _contrat.getCouleur();
        EnumMap<Suit,HandBelote> suits_=new EnumMap<Suit,HandBelote>();
        for (Suit couleur_: couleurs()) {
            Order ordre_;
            if(!_contrat.getCouleurDominante()) {
                ordre_ = _contrat.getOrdre();
            } else if(couleur_ == couleurAtout_) {
                ordre_ = Order.TRUMP;
            } else {
                ordre_ = Order.SUIT;
            }
            HandBelote couleurTotale_ = HandBelote.couleurComplete(couleur_, ordre_);
            HandBelote cartes_=main(_couleurs,couleur_);
            HandBelote suite_=new HandBelote();
            HandBelote cartesJoueesOuPossedees_=new HandBelote();
            cartesJoueesOuPossedees_.ajouterCartes(main(_cartesJouees,couleur_));
            //C'est la reunion des cartes jouees dans le jeu et de celles du joueur
            cartesJoueesOuPossedees_.ajouterCartes(cartes_);
            cartesJoueesOuPossedees_.trierUnicolore(true);


            HandBelote cartesMaitresses_ = new HandBelote();
            int nbPlayedOrOwnedCards_ = cartesJoueesOuPossedees_.total();
            for (byte c = CustList.FIRST_INDEX; c < nbPlayedOrOwnedCards_; c++) {
                if (!CardBelote.eq(cartesJoueesOuPossedees_.carte(c),
                        couleurTotale_.carte(c))) {
                    break;
                }
                if (!cartes_.contient(cartesJoueesOuPossedees_.carte(c))) {
                    continue;
                }
                cartesMaitresses_.ajouter(cartesJoueesOuPossedees_.carte(c));
            }
            int nbLeadingCards_ = cartesMaitresses_.total();
            if (nbLeadingCards_ + nbLeadingCards_ + _cartesJouees.getVal(couleur_).total() >= couleurTotale_
                    .total()) {
                for (CardBelote carte_ : cartes_) {
                    if (!cartesMaitresses_.contient(carte_)) {
                        cartesMaitresses_.ajouter(carte_);
                    }
                }
            }

            suits_.put(couleur_,suite_);
        }
        return suits_;
    }
    /**Utilise pour dire si le joueur de carte est strictement maitre a l'atout (c'est-a-dire s'il peut ramasser tous les atouts en ayant la main) uniquement pour un jeu ou une couleur domine les autres*/
    private boolean strictMaitreAtout(EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, byte _numero,EqList<HandBelote> _suites,EnumMap<Suit,HandBelote> _cartesJouees) {
        Suit couleurAtout_=couleurAtout();
        int max_=CustList.SIZE_EMPTY;
        /*max designe le nombre maximal de cartes probablement possedees par un joueur*/
        int nbPlayers_ = suite(_cartesPossibles,couleurAtout_).size();
        for(int joueur_=CustList.FIRST_INDEX;joueur_<nbPlayers_;joueur_++) {
            //La taille de cartesPossibles correspond au nombre de joueurs
            if(joueur_!=_numero) {
                max_=Math.max(main(_cartesPossibles,couleurAtout_,joueur_).total(),max_);
            }
        }
        /*Fin for int joueur=0;joueur<cartesPossibles.get(1).size();joueur++
        (Fin boucle sur le calcul de la valeur maximale possible des atouts*/
        if(max_==CustList.SIZE_EMPTY) {
            /*S'il est impossible que les autres joueurs aient de l'atout*/
            return true;
        }
        if(main(_cartesJouees,couleurAtout_).total()==HandBelote.couleurComplete(couleurAtout_, Order.TRUMP).total()) {
            return true;
        }
        if(_suites.isEmpty()) {
            return false;
        }
        boolean existeAtoutMaitre_=true;
        CardBelote atoutPetitSuiteHaute_=_suites.first().premiereCarte();
        for(CardBelote carte_:cartesAtouts(couleurAtout_)) {
            if(carte_.strength(couleurAtout_,bid)<=atoutPetitSuiteHaute_.strength(couleurAtout_,bid)) {
                break;
            }
            if(!main(_cartesJouees,couleurAtout_).contient(carte_)&&!_suites.first().contient(carte_)) {
                existeAtoutMaitre_=false;
                break;
            }
        }
        return existeAtoutMaitre_&&_suites.first().total()>=max_;
    }

    private static CardBelote jeuFigureHauteDePlusFaibleSuite(EqList<HandBelote> _suites, BidBeloteSuit _contrat) {
        if(_suites.size()==CustList.ONE_ELEMENT) {
            return _suites.first().premiereCarte();
        }
        return cartePlusPetitePoints(_suites,_contrat);
    }
    private static CardBelote cartePlusPetitePoints(EqList<HandBelote> _suites, BidBeloteSuit _contrat) {
        EqList<HandBelote> suitesPoints_ = new EqList<HandBelote>();
        EqList<HandBelote> suitesZeroPoints_ = new EqList<HandBelote>();
        for(HandBelote suite_:_suites) {
            HandBelote points_ = new HandBelote();
            HandBelote zeroPoints_ = new HandBelote();
            for(CardBelote c: suite_) {
                if(c.points(_contrat) > 0) {
                    points_.ajouter(c);
                } else {
                    zeroPoints_.ajouter(c);
                }
            }
            if(!points_.estVide()) {
                suitesPoints_.add(points_);
            }
            if(!zeroPoints_.estVide()) {
                suitesZeroPoints_.add(zeroPoints_);
            }
        }
        if(!suitesZeroPoints_.isEmpty()) {
            return suitesZeroPoints_.last().premiereCarte();
        }
        return suitesPoints_.last().premiereCarte();
    }

    private CardBelote defausseAtoutSurAdversaireCouleurDominante(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurAtout,EnumList<Suit> _couleursStrictementMaitresses) {

        EnumList<Suit> couleursNonVides_=couleursNonAtoutNonVides(_repartition,couleursNonAtouts());
        if(main(_repartitionCartesJouees,_couleurAtout).total()>5) {
            if(_couleursStrictementMaitresses.size()==couleursNonAtouts().size()) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            EnumList<Suit> couleurs_=couleursSansPoint(_repartition,bid,couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
            }
            return jeuPetiteCarteCouleurFigure(couleursNonVides_, _repartition, _repartitionCartesJouees);
        }
        /*Moins de 6 atouts sont joues*/
        EnumList<Suit> couleurs_=couleursSansPoint(_repartition,bid,couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(couleursNonVides_, _repartition, _repartitionCartesJouees);
    }
    private CardBelote defausseAtoutSurAdversaireCouleursEgales(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=couleursNonAtoutNonVides(_repartition,couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==couleurs().size() - 1) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        EnumList<Suit> couleurs_=couleursSansPoint(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(couleursNonVides_, _repartition, _repartitionCartesJouees);
    }
    private CardBelote defausseAtoutSurPartenaireCouleurDominante(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurAtout,EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=couleursNonAtoutNonVides(_repartition,couleursNonAtouts());
        boolean carteMaitresse_;
        if(main(_repartitionCartesJouees,_couleurAtout).total()>5) {
            if(_couleursStrictementMaitresses.size()==couleursNonAtouts().size()) {
                carteMaitresse_=true;
                for(Suit couleur_:_couleursStrictementMaitresses) {
                    if (!(main(_cartesMaitresses,couleur_).total()==main(_repartition,couleur_).total())) {
                        carteMaitresse_ = false;
                    }
                }
                if(carteMaitresse_) {
                    EnumList<Suit> couleursFigures_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
                    if(!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                    }
                }
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            /*Il peut exister une couleur pour se defausser non strictement maitresse*/
            EnumList<Suit> couleurs_=couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleurs_);
            }
            couleurs_ = couleursAvecPoints(_repartition, bid, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
            }
            return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);
        }
        /*Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur demandee sont jouees*/
        if(_couleursStrictementMaitresses.size()==couleursNonAtouts().size()) {
            carteMaitresse_=true;
            for(Suit couleur_:_couleursStrictementMaitresses) {
                if (!(main(_cartesMaitresses,couleur_).total()==main(_repartition,couleur_).total())) {
                    carteMaitresse_ = false;
                }
            }
            if(carteMaitresse_) {
                EnumList<Suit> couleursFigures_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
                if(!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                }
            }
        }
        EnumList<Suit> couleurs_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);
    }
    private CardBelote defausseAtoutSurPartenaireCouleursEgales(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        boolean carteMaitresse_;
        EnumList<Suit> couleursNonVides_=couleursNonAtoutNonVides(_repartition,couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==couleurs().size() - 1) {
            carteMaitresse_=true;
            for(Suit couleur_:_couleursStrictementMaitresses) {
                if (!(main(_cartesMaitresses,couleur_).total()==main(_repartition,couleur_).total())) {
                    carteMaitresse_ = false;
                }
            }
            if(carteMaitresse_) {
                EnumList<Suit> couleursFigures_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
                if(!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                }
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur pour se defausser non strictement maitresse*/
        EnumList<Suit> couleurs_=couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleurs_);
        }
        couleurs_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);

    }
    private CardBelote defausseCouleurDemandeeSurAdversaireCouleurDominante(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,Suit _couleurAtout,
            EnumList<Suit> _couleursStrictementMaitresses) {

        EnumList<Suit> couleursNonVides_=couleursNonAtoutNonVides(_repartition,couleursNonAtouts());
        EnumList<Suit> couleursAutreQueDemandeeEtAtout_ = couleursNonAtouts();
        couleursAutreQueDemandeeEtAtout_.removeObj(_couleurDemandee);
        if((main(_repartitionCartesJouees,_couleurAtout).total()>6)&&main(_repartitionCartesJouees,_couleurDemandee).total()>6) {
            if(_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandeeEtAtout_)) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            EnumList<Suit> couleurs_=couleursSansPoint(_repartition, bid, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
            }
            return jeuPetiteCarteCouleurFigure(couleursNonVides_, _repartition, _repartitionCartesJouees);
        }
        /*Moins de 7 atouts sont joues ou moins de 7 cartes de la couleur demandee sont jouees*/
        EnumList<Suit> couleurs_=couleursSansPoint(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(couleursNonVides_, _repartition, _repartitionCartesJouees);
    }
    private CardBelote defausseCouleurDemandeeSurAdversaireCouleursEgales(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=couleursNonAtoutNonVides(_repartition,couleursNonAtouts());
        if(main(_repartitionCartesJouees,_couleurDemandee).total()>6) {
            if(_couleursStrictementMaitresses.size()==couleurs().size() - 1) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            EnumList<Suit> couleurs_=couleursSansPoint(_repartition, bid, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
            }
            return jeuPetiteCarteCouleurFigure(couleursNonVides_, _repartition, _repartitionCartesJouees);
        }
        /*Moins de 7 atouts sont joues ou moins de 7 cartes de la couleur demandee sont jouees*/
        EnumList<Suit> couleurs_=couleursSansPoint(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(couleursNonVides_, _repartition, _repartitionCartesJouees);
    }
    private CardBelote defausseCouleurDemandeeSurPartenaireCouleurDominante(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,Suit _couleurAtout,
            EnumList<Suit> _couleursStrictementMaitresses) {

        EnumList<Suit> couleursNonVides_=couleursNonAtoutNonVides(_repartition,couleursNonAtouts());
        EnumList<Suit> couleursAutreQueDemandeeEtAtout_ = couleursNonAtouts();
        couleursAutreQueDemandeeEtAtout_.removeObj(_couleurDemandee);
        boolean carteMaitresse_;
        if((main(_repartitionCartesJouees,_couleurAtout).total()>6)&&main(_repartitionCartesJouees,_couleurDemandee).total()>6) {
            if(_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandeeEtAtout_)) {
                carteMaitresse_=true;
                for(Suit couleur_:_couleursStrictementMaitresses) {
                    if (!(main(_cartesMaitresses,couleur_).total()==main(_repartition,couleur_).total()&&_couleurAtout!=couleur_)) {
                        carteMaitresse_ = false;
                    }
                }
                if(carteMaitresse_) {
                    EnumList<Suit> couleursFigures_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
                    if(!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                    }
                }
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            /*Il peut exister une couleur non strictement maitresse pour se defausser*/
            EnumList<Suit> couleurs_=couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleurs_);
            }
            couleurs_ = couleursAvecPoints(_repartition, bid, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
            }
            return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);
        }
        /*Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur demandee sont jouees*/
        if(_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandeeEtAtout_)) {
            carteMaitresse_=true;
            for(Suit couleur_:_couleursStrictementMaitresses) {
                if (!(main(_cartesMaitresses,couleur_).total()==main(_repartition,couleur_).total()&&_couleurAtout!=couleur_)) {
                    carteMaitresse_ = false;
                }
            }
            if(carteMaitresse_) {
                EnumList<Suit> couleursFigures_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
                if(!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                }
            }
        }
        EnumList<Suit> couleurs_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);
    }
    private CardBelote defausseCouleurDemandeeSurPartenaireCouleursEgales(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=couleursNonAtoutNonVides(_repartition,couleursNonAtouts());
        boolean carteMaitresse_;
        if(main(_repartitionCartesJouees,_couleurDemandee).total()>6) {
            if(_couleursStrictementMaitresses.size()==couleurs().size() - 1) {
                carteMaitresse_=true;
                for(Suit couleur_:_couleursStrictementMaitresses) {
                    if (!(main(_cartesMaitresses,couleur_).total()==main(_repartition,couleur_).total())) {
                        carteMaitresse_ = false;
                    }
                }
                if(carteMaitresse_) {
                    EnumList<Suit> couleursFigures_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
                    if(!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                    }
                }
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            /*Il peut exister une couleur non strictement maitresse pour se defausser*/
            EnumList<Suit> couleurs_=couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleurs_);
            }
            couleurs_ = couleursAvecPoints(_repartition, bid, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
            }
            return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);
        }
        /*Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur demandee sont jouees*/
        if(_couleursStrictementMaitresses.size()==couleurs().size() - 1) {
            carteMaitresse_=true;
            for(Suit couleur_:_couleursStrictementMaitresses) {
                if (!(main(_cartesMaitresses,couleur_).total()==main(_repartition,couleur_).total())) {
                    carteMaitresse_ = false;
                }
            }
            if(carteMaitresse_) {
                EnumList<Suit> couleursFigures_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
                if(!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                }
            }
        }
        EnumList<Suit> couleurs_=couleursAvecPoints(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);
    }
    private EnumList<Suit> couleursNonAtouts() {
        EnumList<Suit> couleursNonAtouts_=new EnumList<Suit>();
        if(bid.getCouleurDominante()) {
            for(Suit couleur_:couleurs()) {
                if(couleur_!=couleurAtout()) {
                    couleursNonAtouts_.add(couleur_);
                }
            }
            return couleursNonAtouts_;
        }
        return couleurs();
    }
    private static EnumList<Suit> couleurs() {
        return Suit.couleursOrdinaires();
    }
    private CardBelote sauverFiguresDefausse(
            EnumList<Suit> _couleursFigures,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursAvecLePlusGrandNbPoints(_repartition, bid, _couleursFigures);
        couleurs_ = couleursLesPlusCourtes(_repartition, couleurs_);
        couleurs_ = couleursLesPlusLongues(_repartitionCartesJouees, couleurs_);
        couleurs_ = couleursAvecLaPlusGrandeFigure(_repartition, bid, couleurs_);
        return main(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jeuPetiteCarteCouleurFigure(
            EnumList<Suit> _couleurs, EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursAvecLaPlusPetiteCarteBasse(_repartition, bid, _couleurs);
        couleurs_ = couleursLesPlusCourtes(_repartition, _couleurs);
        couleurs_ = couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        couleurs_ = couleursLesPlusCourtes(_repartitionCartesJouees, _couleurs);
        return main(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuGrandeCarteDefausseMaitre(
            EnumList<Suit> _couleursFigures,
            EnumMap<Suit,HandBelote> _repartition) {
        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_repartition, _couleursFigures);
        couleurs_ = couleursLesPlusHautes(_repartition, bid, couleurs_);
        couleurs_ = couleursAvecLePlusGrandNbPoints(_repartition, bid, couleurs_);
        return main(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jouerPetiteCarteDefausse(
            EnumList<Suit> _couleurs,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursLesPlusCourtes(_repartition, _couleurs);
        couleurs_ = couleursLesPlusCourtes(_repartitionCartesJouees, _couleurs);
        couleurs_ = couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return main(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuPetiteDefausseMaitre(
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumMap<Suit,HandBelote> _repartition,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = couleursLesPlusCourtes(_cartesMaitresses, couleurs_);
        couleurs_ = couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return main(_repartition,couleurs_.first()).derniereCarte();
    }
    public EnumMap<Suit,EqList<HandBelote>> cartesPossibles(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            CustList<TrickBelote> _plisFaits,
            EnumMap<Suit,HandBelote> _repartition,
            byte _numero,
            Suit _couleurAtout) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        EnumMap<Suit,EqList<HandBelote>> m=new EnumMap<Suit,EqList<HandBelote>>();
        for(Suit couleur_:couleurs()) {
            //On fait une boucle sur les couleurs autres que l'atout
            if(_couleurAtout!=couleur_&&!bid.ordreAtout()) {
                m.put(couleur_,cartesPossibles(couleur_,main(_repartitionCartesJouees,couleur_),_plisFaits,main(_repartition,couleur_),_numero));
            } else {
                m.put(couleur_,atoutsPossibles(couleur_,main(_repartitionCartesJouees,couleur_),_plisFaits,main(_repartition,couleur_),_numero));
            }
            for(DeclareHandBelote a:declares) {
                HandBelote mainCouleur_ = a.getMain().couleurs(bid).getVal(couleur_);
                for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreDeJoueurs_;joueur_++) {
                    if(joueur_ == a.getJoueur()) {
                        continue;
                    }
                    m.getVal(couleur_).get(joueur_).supprimerCartes(mainCouleur_);
                }
            }
        }
        return m;
    }
    public EnumMap<Hypothesis,EnumMap<Suit,EqList<HandBelote>>> cartesCertaines(
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles) {
        Suit couleurAtout_=couleurAtout();
        Numbers<Byte> joueursRepartitionConnue_=new Numbers<Byte>();
        Numbers<Byte> joueursRepartitionConnue2_=new Numbers<Byte>();
        Numbers<Byte> joueursRepartitionConnueMemo_=new Numbers<Byte>();
        Numbers<Byte> joueursRepartitionInconnue_=new Numbers<Byte>();
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandBelote>>> retour_ = new EnumMap<Hypothesis,EnumMap<Suit,EqList<HandBelote>>>();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=new EnumMap<Suit,EqList<HandBelote>>();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = new EnumMap<Suit,EqList<HandBelote>>(
                _cartesPossibles);
        byte nombreJoueurs_=getNombreDeJoueurs();
        int nombreDApparitionCarte_=0;
        /*Indique le nombre de mains pour les cartes possibles ou apparait la carte*/
        for(Suit couleur_:couleurs()) {
            cartesCertaines_.put(couleur_,new EqList<HandBelote>());
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                suite(cartesCertaines_,couleur_).add(new HandBelote());
            }
        }
        int nombreCartesPossiblesJoueur_=0;
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
            nombreCartesPossiblesJoueur_=0;
            for(Suit couleur_:couleurs()) {
                nombreCartesPossiblesJoueur_+=main(cartesPossibles_,couleur_,joueur_).total();
            }
            if(nombreCartesPossiblesJoueur_==getDistribution().main(joueur_).total()) {
                /*L'ensemble des cartes d'un joueur reellement possedees est inclus dans l'ensemble des cartes probablement possedees par ce joueur*/
                for(Suit couleur_:couleurs()) {
                    main(cartesCertaines_,couleur_,joueur_).ajouterCartes(main(cartesPossibles_,couleur_,joueur_));
                }
                joueursRepartitionConnue_.add(joueur_);
                joueursRepartitionConnueMemo_.add(joueur_);
            }
        }
        /*La condition !joueursRepartitionConnue.isEmpty() est vraie car la methode est appelee pour un point de vue particulier de joueur*/
        while(!joueursRepartitionConnue_.isEmpty()) {
        /*Tant qu'on arrive a deduire la repartition exacte des joueurs on boucle sur l'ensemble des joueurs dont la
        repartition vient juste d'etre connue pour eliminer les cartes impossibles d'etre possedees par les joueurs*/
            for(byte joueur_:joueursRepartitionConnue_) {
                for (byte joueur2_ = CustList.FIRST_INDEX;joueur2_<nombreJoueurs_;joueur2_++) {
                    if(!joueursRepartitionConnueMemo_.containsObj(joueur2_)) {
                        for(Suit couleur_:couleurs()) {
                            main(cartesPossibles_,couleur_,joueur2_).supprimerCartes(main(cartesCertaines_,couleur_,joueur_));
                        }
                    }
                    nombreCartesPossiblesJoueur_=0;
                    for(Suit couleur_:couleurs()) {
                        nombreCartesPossiblesJoueur_+=main(cartesPossibles_,couleur_,joueur2_).total();
                    }
                    if(nombreCartesPossiblesJoueur_==getDistribution().main(joueur2_).total()&&!joueursRepartitionConnueMemo_.containsObj(joueur2_)) {
                        for(Suit couleur_:couleurs()) {
                            main(cartesCertaines_,couleur_,joueur2_).supprimerCartes();
                            main(cartesCertaines_,couleur_,joueur2_).ajouterCartes(main(cartesPossibles_,couleur_,joueur2_));
                        }
                        joueursRepartitionConnue2_.add(joueur2_);
                        joueursRepartitionConnueMemo_.add(joueur2_);
                    }
                }
            }
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                if(!joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                    joueursRepartitionInconnue_.add(joueur_);
                }
            }
            for(byte joueur_:joueursRepartitionInconnue_) {
                for(Suit couleur_:couleurs()) {
                    for(CardBelote carte_:main(cartesPossibles_,couleur_,joueur_)) {
                        nombreDApparitionCarte_=0;
                        for (byte joueur2_ = CustList.FIRST_INDEX;joueur2_<nombreJoueurs_;joueur2_++) {
                            if(main(cartesPossibles_,couleur_,joueur2_).contient(carte_)) {
                                nombreDApparitionCarte_++;
                            }
                        }
                        if(nombreDApparitionCarte_==1&&!main(cartesCertaines_,couleur_,joueur_).contient(carte_)) {
                            /*Si une carte n'apparait que dans une main ou il est possible qu'elle apparaisse alors elle ne peut etre que dans cette main*/
                            main(cartesCertaines_,couleur_,joueur_).ajouter(carte_);
                        }
                    }
                }
                nombreCartesPossiblesJoueur_=0;
                for(Suit couleur_:couleurs()) {
                    nombreCartesPossiblesJoueur_+=main(cartesCertaines_,couleur_,joueur_).total();
                }
                if(nombreCartesPossiblesJoueur_==getDistribution().main(joueur_).total()&&!joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                    for(Suit couleur_:couleurs()) {
                        main(cartesPossibles_,couleur_,joueur_).supprimerCartes();
                        main(cartesPossibles_,couleur_,joueur_).ajouterCartes(main(cartesCertaines_,couleur_,joueur_));
                        if(bid.getCouleurDominante()) {
                            if(couleur_!=couleurAtout_) {
                                main(cartesPossibles_,couleur_,joueur_).setOrdre(Order.SUIT);
                            }
                        } else if(bid.ordreCouleur()) {
                            main(cartesPossibles_,couleur_,joueur_).setOrdre(Order.SUIT);
                        }
                        main(cartesPossibles_,couleur_,joueur_).trierUnicolore(true);
                    }
                    joueursRepartitionConnueMemo_.add(joueur_);
                    joueursRepartitionConnue2_.add(joueur_);
                }
            }
            joueursRepartitionInconnue_.clear();
            joueursRepartitionConnue_.clear();
            joueursRepartitionConnue_.addAllElts(joueursRepartitionConnue2_);
            joueursRepartitionConnue2_.clear();
        }
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
            if(!joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                joueursRepartitionInconnue_.add(joueur_);
            }
        }
        for(byte joueur_:joueursRepartitionInconnue_) {
            for(Suit couleur_:couleurs()) {
                if(bid.getCouleurDominante()) {
                    if(couleur_!=couleurAtout_) {
                        main(cartesCertaines_,couleur_,joueur_).setOrdre(Order.SUIT);
                    }
                } else if(bid.ordreCouleur()) {
                    main(cartesCertaines_,couleur_,joueur_).setOrdre(Order.SUIT);
                }
                main(cartesCertaines_,couleur_,joueur_).trierUnicolore(true);
            }
        }
        retour_.put(Hypothesis.POSSIBLE, cartesPossibles_);
        retour_.put(Hypothesis.SURE, cartesCertaines_);
        return retour_;
    }
    /**Retourne l'ensemble des atouts probablement possedes par les autres joueurs
    @param numero*/
    private EqList<HandBelote> atoutsPossibles(
            Suit _couleurAtout,
            HandBelote _atoutsJoues,
            CustList<TrickBelote> _plisFaits,
            HandBelote _atoutsJoueur, byte _numero) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        EqList<HandBelote> m=new EqList<HandBelote>();
        boolean defausse_=false;
        byte nombreJoueurs_=getNombreDeJoueurs();
        for (byte j = CustList.FIRST_INDEX;j<nombreJoueurs_;j++) {
            m.add(new HandBelote());
            if (j == _numero) {
                m.last().ajouterCartes(_atoutsJoueur);
                continue;
            }
            for(CardBelote carte_:cartesAtouts(_couleurAtout)) {
                if(!_atoutsJoues.contient(carte_)&&!_atoutsJoueur.contient(carte_)) {
                    if (getDistribution().derniereMain().estVide()) {
                        m.last().ajouter(carte_);
                    } else if(!CardBelote.eq(getDistribution().derniereMain().premiereCarte(), carte_)||j==taker) {
                        m.last().ajouter(carte_);
                    }
                }
            }
            if(bid.getCouleurDominante()) {
                defausse_=false;
                for(Suit couleur_:couleurs()) {
                    if (defausseBelote(couleur_,j,_plisFaits)) {
                        defausse_ = true;
                    }
                }
                if(defausse_) {
                    //Les joueurs se defaussant sur atout ou couleur demandee ne peuvent pas avoir de l'atout
                    m.get(j).supprimerCartes();
                }
            } else {
                if(neFournitPas(_couleurAtout, j, _plisFaits)) {
                    m.get(j).supprimerCartes();
                }
            }
        }
        if(bid.getCouleurDominante()) {
            boolean surCoupeObligPart_=surCoupeObligatoirePartenaire();
            boolean sousCoupeObligPart_=sousCoupeObligatoirePartenaire();
            boolean sousCoupeObligAdv_=sousCoupeObligatoireAdversaire();
            HandBelote cartesAnnonces_ = cartesBeloteRebelote();
            for(TrickBelote p: _plisFaits) {
                for(CardBelote c: p) {
                    byte joueur_ = p.joueurAyantJoue(c);
                    if(declaresBeloteRebelote.get(joueur_).contient(c)) {
                        continue;
                    }
                    for (byte j = CustList.FIRST_INDEX;j<nombreJoueurs_;j++) {
                        if(j == _numero) {
                            continue;
                        }
                        if(j == joueur_) {
                            continue;
                        }
                        //Les autres joueurs ne peuvent pas posseder les cartes a annoncer par unicite
                        m.get(j).supprimerCartes(cartesAnnonces_);
                    }
                }
            }
            CustList<TrickBelote> plis_ = new CustList<TrickBelote>(_plisFaits);
            plis_.add(progressingTrick);
            for(TrickBelote pli_:plis_) {
                Suit couleurDemande_=pli_.couleurDemandee();
                if(couleurDemande_==_couleurAtout) {
                    continue;
                }
                for(CardBelote c: pli_) {
                    if(c.couleur() == couleurDemande_) {
                        continue;
                    }
                    byte joueur_ = pli_.joueurAyantJoue(c,nombreDeJoueurs_);
                    if(joueur_ == _numero) {
                        continue;
                    }
                    Numbers<Byte> joueursAvant_ = pli_.joueursAyantJoueAvant(joueur_,nombreDeJoueurs_);
                    byte forceLoc_ = c.strength(couleurDemande_, bid);
                    byte max_ = 0;
                    byte ramasseurVirtuel_ = joueur_;
                    if(c.couleur() == _couleurAtout) {
                        //coupe
                        boolean sousCoupe_ = false;
                        //joueursAvant non vide
                        for(byte j: joueursAvant_) {
                            CardBelote carte_ = pli_.carteDuJoueur(j,nombreDeJoueurs_);
                            byte forceLoc2_ = carte_.strength(couleurDemande_, bid);
                            if(forceLoc2_ < forceLoc_) {
                                continue;
                            }
                            sousCoupe_ = true;
                            if(forceLoc2_ < max_) {
                                continue;
                            }
                            max_ = forceLoc2_;
                            ramasseurVirtuel_ = j;
                        }
                        if(ramasseurVirtuel_ == joueur_) {
                            continue;
                        }
                        HandBelote cartesExclues_ = new HandBelote();
                        if(memeEquipe(ramasseurVirtuel_, joueur_)) {
                            if(sousCoupe_) {
                                //max > 0
                                if(surCoupeObligPart_ || sousCoupeObligPart_) {
                                    //supprimer les atouts superieurs a celui joue du partenaire
                                    for(CardBelote c2_: m.get(joueur_)) {
                                        if(c2_.strength(couleurDemande_, bid) < max_) {
                                            continue;
                                        }
                                        cartesExclues_.ajouter(c2_);
                                    }
                                }
                            }
                        } else {
                            if(sousCoupe_) {
                                //max > 0
                                //supprimer les atouts superieurs a celui joue de l'adversaire
                                for(CardBelote c2_: m.get(joueur_)) {
                                    if(c2_.strength(couleurDemande_, bid) < max_) {
                                        continue;
                                    }
                                    cartesExclues_.ajouter(c2_);
                                }
                            }
                        }
                        m.get(joueur_).supprimerCartes(cartesExclues_);
                        continue;
                    }
                    //defausse
                    boolean defausseSurAtout_ = false;
                    //joueursAvant non vide
                    for(byte j: joueursAvant_) {
                        CardBelote carte_ = pli_.carteDuJoueur(j,nombreDeJoueurs_);
                        byte forceLoc2_ = carte_.strength(couleurDemande_, bid);
                        if(forceLoc2_ == 0) {
                            continue;
                        }
                        if(carte_.couleur() == _couleurAtout) {
                            defausseSurAtout_ = true;
                        }
                        if(forceLoc2_ < max_) {
                            continue;
                        }
                        max_ = forceLoc2_;
                        ramasseurVirtuel_ = j;
                    }
                    HandBelote cartesExclues_ = new HandBelote();
                    if(memeEquipe(ramasseurVirtuel_, joueur_)) {
                        if(!defausseSurAtout_) {
                            //defausse sur une carte de couleur ordinaire du partenaire
                            if(surCoupeObligPart_) {
                                m.get(joueur_).supprimerCartes();
                            }
                        } else {
                            //defausse sur un atout du partenaire
                            if(surCoupeObligPart_) {
                                for(CardBelote c2_: m.get(joueur_)) {
                                    if(c2_.strength(couleurDemande_, bid) < max_) {
                                        continue;
                                    }
                                    cartesExclues_.ajouter(c2_);
                                }
                            }
                            if(sousCoupeObligAdv_) {
                                for(CardBelote c2_: m.get(joueur_)) {
                                    if(c2_.strength(couleurDemande_, bid) > max_) {
                                        continue;
                                    }
                                    cartesExclues_.ajouter(c2_);
                                }
                            }
                        }
                        m.get(joueur_).supprimerCartes(cartesExclues_);
                        continue;
                    }
                    //!memeEquipe(ramasseurVirtuel, joueur)
                    if(!defausseSurAtout_) {
                        //defausse sur une carte de couleur ordinaire d'un adversaire
                        m.get(joueur_).supprimerCartes();
                    } else {
                        //defausse sur un atout d'un adversaire
                        for(CardBelote c2_: m.get(joueur_)) {
                            if(c2_.strength(couleurDemande_, bid) < max_) {
                                continue;
                            }
                            cartesExclues_.ajouter(c2_);
                        }
                        if(sousCoupeObligAdv_) {
                            for(CardBelote c2_: m.get(joueur_)) {
                                if(c2_.strength(couleurDemande_, bid) > max_) {
                                    continue;
                                }
                                cartesExclues_.ajouter(c2_);
                            }
                        }
                    }
                    m.get(joueur_).supprimerCartes(cartesExclues_);
                }
            }
        }
        CustList<TrickBelote> plis_ = new CustList<TrickBelote>(_plisFaits);
        plis_.add(progressingTrick);
        for(TrickBelote pli_:plis_) {
            Suit couleurDemande_=pli_.couleurDemandee();
            if(couleurDemande_!=_couleurAtout) {
                continue;
            }
            //couleurDemande == couleurAtout
            for(CardBelote c: pli_) {
                byte joueur_ = pli_.joueurAyantJoue(c,nombreDeJoueurs_);
                if(joueur_ == _numero) {
                    continue;
                }
                Numbers<Byte> joueursAvant_ = pli_.joueursAyantJoueAvant(joueur_,nombreDeJoueurs_);
                byte forceLoc_ = c.strength(couleurDemande_, bid);
                byte max_ = 0;
                byte ramasseurVirtuel_ = joueur_;
                //joueursAvant non vide
                for(byte j: joueursAvant_) {
                    CardBelote carte_ = pli_.carteDuJoueur(j,nombreDeJoueurs_);
                    byte forceLoc2_ = carte_.strength(couleurDemande_, bid);
                    if(forceLoc2_ < forceLoc_) {
                        continue;
                    }
                    if(forceLoc2_ < max_) {
                        continue;
                    }
                    max_ = forceLoc2_;
                    ramasseurVirtuel_ = j;
                }
                if(ramasseurVirtuel_ == joueur_) {
                    continue;
                }
                HandBelote cartesExclues_ = new HandBelote();
                for(CardBelote c2_: m.get(joueur_)) {
                    if(c2_.strength(couleurDemande_, bid) < max_) {
                        continue;
                    }
                    cartesExclues_.ajouter(c2_);
                }
                m.get(joueur_).supprimerCartes(cartesExclues_);
            }

        }
        byte joueur_ = 0;
        for (HandBelote main_ : m) {
            if (joueur_ == nombreDeJoueurs_) {
                break;
            }
            if (m.get(joueur_).estVide()) {
                joueur_++;
                continue;
            }
            HandBelote atoutsFiltres_ = sousCoupeBelote(_couleurAtout,_numero, joueur_,
                    main_, _plisFaits);
            m.set(joueur_, atoutsFiltres_);
            joueur_++;
        }
        return m;
    }
    private HandBelote sousCoupeBelote(Suit _couleur,byte _joueurCourant, byte _numero,
            HandBelote _atoutsPossibles, CustList<TrickBelote> _unionPlis) {
        HandBelote retour_ = new HandBelote();
        retour_.ajouterCartes(_atoutsPossibles);
        HandBelote mainCourante_ = deal.main(_joueurCourant);
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        CustList<TrickBelote> plis_ = new CustList<TrickBelote>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (TrickBelote pli_ : _unionPlis) {
            CardBelote carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.couleur() != _couleur) {
                continue;
            }
            byte entameur_ = pli_.getEntameur();
            byte ramasseur_ = pli_.getRamasseur(bid);
            boolean ramasseurAvantNumero_ = false;
            byte i_ = 0;
            while (i_ < nombreDeJoueurs_) {
                byte joueur_ = (byte) ((entameur_ + i_) % nombreDeJoueurs_);
                if (joueur_ == ramasseur_) {
                    ramasseurAvantNumero_ = true;
                    break;
                }
                if (joueur_ == _numero) {
                    ramasseurAvantNumero_ = false;
                    break;
                }
                i_++;
            }
            if (!ramasseurAvantNumero_) {
                continue;
            }
            if (memeEquipe(ramasseur_, _numero)) {
                continue;
            }
            // Plis sous coupes (couleur demandee) ou avec un atout joue en
            // dessous du ramasseur (demande atout)
            plis_.add(pli_);
        }
        HandBelote cartesVues_ = new HandBelote(Order.TRUMP);
        cartesVues_.ajouterCartes(cartesJouees_.couleurs(bid).getVal(_couleur));
        cartesVues_.ajouterCartes(mainCourante_.couleurs(bid).getVal(_couleur));
        cartesVues_.trierUnicolore(true);
        for (TrickBelote pli_ : plis_) {
            CardBelote carteObservee_ = pli_.carteDuJoueur(_numero);
            Suit couleurDemandee_ = pli_.couleurDemandee();
            HandBelote mainLocale_ = new HandBelote();
            //supprimer les atouts sous la carte observee
            for(CardBelote carte_: cartesAtouts(_couleur)) {
                if (carte_.strength(couleurDemandee_,bid) < carteObservee_
                        .strength(couleurDemandee_,bid)) {
                    mainLocale_.ajouter(carte_);
                }
            }
            retour_.supprimerCartes(mainLocale_);
        }
        return retour_;
    }

    boolean memeEquipe(byte _numero1,byte _numero2) {
        if (aPourDefenseur(_numero1)) {
            return aPourDefenseur(_numero2);
        }
        return !aPourDefenseur(_numero2);
    }
    /**Retourne l'ensemble des cartes d'une meme couleur autre que l'atout probablement possedees par les autres joueurs on tient compte du pli en cours
    @param numero*/
    private EqList<HandBelote> cartesPossibles(Suit _couleur,HandBelote _cartesJouees,CustList<TrickBelote> _plisFaits,HandBelote _cartesJoueur, byte _numero) {
        EqList<HandBelote> m=new EqList<HandBelote>();
        byte nombreJoueurs_=getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
            m.add(new HandBelote());
            if(joueur_ == _numero) {
                m.last().ajouterCartes(_cartesJoueur);
                continue;
            }
            for(CardBelote carte_:cartesCouleurs(_couleur)) {
                if(!_cartesJouees.contient(carte_)&&!_cartesJoueur.contient(carte_)) {
                    if (getDistribution().derniereMain().estVide()) {
                        m.last().ajouter(carte_);
                    } else if (!CardBelote.eq(getDistribution().derniereMain().premiereCarte(), carte_)||joueur_==taker) {
                        m.last().ajouter(carte_);
                    }
                }
            }
            if(neFournitPas(_couleur, joueur_, _plisFaits)) {
                //Les joueurs se defaussant sur atout ou couleur demandee ne peuvent pas avoir de l'atout
                m.get(joueur_).supprimerCartes();
            }
        }
        if(progressingTrick.couleurDemandee()==_couleur) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                if(joueur_ == _numero) {
                    continue;
                }
                if(progressingTrick.aJoue(joueur_, nombreJoueurs_)) {
                    /*Si un joueur a joue une carte autre que l'Excuse et pas de la couleur demandee dans le pli en cours, alors il coupe ou se defausse*/
                    CardBelote carteJouee_=progressingTrick.carteDuJoueur(joueur_,nombreJoueurs_);
                    if(carteJouee_.couleur()!=_couleur) {
                        m.get(joueur_).supprimerCartes();
                    }
                }
            }
        }
        return m;
    }
    private static boolean neFournitPas(Suit _couleur, byte _joueur,CustList<TrickBelote> _plisFaits) {
        int lastIndex_ = _plisFaits.getLastIndex();
        for(int indicePli_= lastIndex_;indicePli_>=CustList.FIRST_INDEX;indicePli_--) {
            TrickBelote pli_=_plisFaits.get(indicePli_);
            if(pli_.couleurDemandee()==_couleur) {
                if(pli_.carteDuJoueur(_joueur).couleur()!=_couleur) {
                    return true;
                }
            }
        }
        return false;
    }
    /**Retourne vrai si et seulement si le joueur ne peut pas jouer atout sur demande d'atout ou couper quand il le faut
    (sur un adversaire ayant joue une carte de la couleur demandee forte virtuellement)*/
    private boolean defausseBelote(Suit _couleur, byte _joueur,CustList<TrickBelote> _plisFaits) {
        if(bid.getCouleurDominante()) {
            Suit couleurAtout_=couleurAtout();
            boolean surCoupeObligPart_=surCoupeObligatoirePartenaire();
            boolean sousCoupeObligPart_=sousCoupeObligatoirePartenaire();
            boolean sousCoupeObligAdv_=sousCoupeObligatoireAdversaire();
            if(_couleur==couleurAtout_) {
                /*Si la couleur demandee est celle de l'atout*/
                int lastIndex_ = _plisFaits.getLastIndex();
                for(int indicePli_=lastIndex_;indicePli_>=CustList.FIRST_INDEX;indicePli_--) {
                    TrickBelote pli_=_plisFaits.get(indicePli_);
                    if(pli_.couleurDemandee()==_couleur) {
                        if(pli_.carteDuJoueur(_joueur).couleur()!=_couleur) {
                            return true;
                        }
                    }
                }
            } else {
                int lastIndex_ = _plisFaits.getLastIndex();
                for(int indicePli_=lastIndex_;indicePli_>=CustList.FIRST_INDEX;indicePli_--) {
                    TrickBelote pli_=_plisFaits.get(indicePli_);
                    if(pli_.couleurDemandee()!=_couleur) {
                        continue;
                    }
                    Suit couleurJoueur_=pli_.carteDuJoueur(_joueur).couleur();
                    if(couleurJoueur_==_couleur) {
                        continue;
                    }
                    if(couleurJoueur_==couleurAtout_) {
                        continue;
                    }
                    //defausse de joueur
                    Numbers<Byte> joueurs_=pli_.joueursAyantJoueAvant(_joueur);
                    if(joueurs_.isEmpty()) {
                        continue;
                    }
                    //Le pli n'est pas vide.
                    CardBelote carteForte_ = pli_.premiereCarte();
                    byte forcePremiereCarte_ = carteForte_.strength(_couleur,bid);
                    byte ramasseurAvant_ = pli_.getEntameur();
                    boolean doitCouper_ = false;
                    for(byte j: joueurs_) {
                        CardBelote carte_ = pli_.carteDuJoueur(j);
                        byte forceCarte_ = carte_.strength(_couleur,bid);
                        if (forceCarte_ > forcePremiereCarte_) {
                            forcePremiereCarte_ = forceCarte_;
                            carteForte_ = carte_;
                            ramasseurAvant_ = j;
                        }
                    }
                    if(memeEquipe(_joueur, ramasseurAvant_)) {
                        if(carteForte_.couleur() == couleurAtout_) {
                            doitCouper_ = surCoupeObligPart_ && sousCoupeObligPart_;
                        } else {
                            doitCouper_ = surCoupeObligPart_;
                        }
                    } else {
                        if(carteForte_.couleur() == couleurAtout_) {
                            doitCouper_ = sousCoupeObligAdv_;
                        } else {
                            doitCouper_ = true;
                        }
                    }
                    if(doitCouper_) {
                        return true;
                    }
                }
            }
        } else {
            int lastIndex_ = _plisFaits.getLastIndex();
            for(int indicePli_=lastIndex_;indicePli_>=CustList.FIRST_INDEX;indicePli_--) {
                TrickBelote pli_=_plisFaits.get(indicePli_);
                if(pli_.couleurDemandee()==_couleur) {
                    Suit couleurJoueJoueur_=pli_.carteDuJoueur(_joueur).couleur();
                    if(couleurJoueJoueur_!=_couleur) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static Numbers<Byte> tours(Suit _couleur,CustList<TrickBelote> _plisFaits) {
        Numbers<Byte> tricksNumbers_=new Numbers<Byte>();
        byte key_ = CustList.FIRST_INDEX;
        for(TrickBelote pli_:_plisFaits) {
            if(pli_.couleurDemandee()==_couleur) {
                tricksNumbers_.add(key_);
            }
            key_++;
        }
        return tricksNumbers_;
    }
    /**A la fin d'un pli on ramasse les cartes
    et on les ajoute dans des tas*/
    public void ajouterUneCarteDansPliEnCours(CardBelote _c) {
        progressingTrick.ajouter(_c);
    }
    private static HandBelote main(EnumMap<Suit,HandBelote> _mains,Suit _couleur) {
        return _mains.getVal(_couleur);
    }
    private static EqList<HandBelote> suite(EnumMap<Suit,EqList<HandBelote>> _mains,Suit _couleur) {
        return _mains.getVal(_couleur);
    }
    private static HandBelote main(EnumMap<Suit,EqList<HandBelote>> _mains,Suit _couleur,int _indice2) {
        return _mains.getVal(_couleur).get(_indice2);
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
    public byte ramasseur(CustList<TrickBelote> _plisFaits,byte _numeroPli) {
        if(_numeroPli<_plisFaits.size()) {
            return _plisFaits.get(_numeroPli).getRamasseur(bid);
        }
        return trickWinner;
    }
    public int pointsAttaqueSansPrime() {
        int nbPointsAtt_=0;
        for (TrickBelote pli_:getPlisAttaque()) {
            for(CardBelote carte_:pli_) {
                nbPointsAtt_+=carte_.points(bid);
            }
        }
        return nbPointsAtt_;
    }
    public int pointsAttackWithBonus() {
        int nbPoints_ = pointsAttaqueSansPrime();
        for (Numbers<Byte> t: playersBelongingToSameTeam()) {
            if (t.containsObj(taker)) {
                for (byte p: t) {
                    for (short s: pointsAnnoncesPrimes(p)) {
                        nbPoints_+= s;
                    }
                }
            }
        }
        nbPoints_ += valeurCapot();
        return nbPoints_;
    }
    public int pointsDefenseSansPrime() {
        int nbPointsDef_=0;
        for (TrickBelote pli_:getPlisDefense()) {
            for(CardBelote carte_:pli_) {
                nbPointsDef_+=carte_.points(bid);
            }
        }
        return nbPointsDef_;
    }
    public int pointsDefenseWithBonus() {
        int nbPoints_ = pointsDefenseSansPrime();
        for (Numbers<Byte> t: playersBelongingToSameTeam()) {
            if (!t.containsObj(taker)) {
                for (byte p: t) {
                    for (short s: pointsAnnoncesPrimes(p)) {
                        nbPoints_+= s;
                    }
                }
            }
        }
        return nbPoints_;
    }
    public Numbers<Short> pointsAnnoncesPrimes(byte _joueur) {
        Numbers<Short> totaux_=new Numbers<Short>();
        if(bid.getCouleurDominante()) {
            if(declaresBeloteRebelote.get(_joueur).contientCartes(cartesBeloteRebelote())) {
                totaux_.add((short) DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints());
            }
        }
        if(wonLastTrick.get(_joueur)) {
            totaux_.add((short) BonusBelote.LAST_TRICK.getPoints());
        }
        totaux_.add((short) declares.get(_joueur).getAnnonce().getPoints());
        return totaux_;
    }
    public int valeurCapot() {
        if(getPlisDefense().isEmpty()) {
            /*Le capot n est fait que si l attaque a fait tous les plis*/
            return 100;
        }
        return 0;
    }
    public int scoreDefinitifAttaque(int _scoreTmpAttaque,int _scoreTmpDefense) {
        if (!rules.getComptePointsClassique()) {
            return _scoreTmpAttaque;
        }
        if (getRegles().dealAll()) {
            if (bid.getPoints() == HandBelote.pointsTotauxDixDeDer()) {
                if (!getPlisDefense().isEmpty()) {
                    return 0;
                }
            }
            if (_scoreTmpAttaque >= bid.getPoints()) {
                return _scoreTmpAttaque;
            }
            return 0;
        }
        if(_scoreTmpAttaque>=_scoreTmpDefense) {
            return _scoreTmpAttaque;
        }
        /*L attaque est dedans*/
        return 0;
    }
    public int scoreDefinitifDefense(int _scoreDefinitifAttaque, int _scoreTmpDefense) {
        int sum_ = HandBelote.pointsTotauxDixDeDer(bid);
        if (_scoreDefinitifAttaque == 0) {
            return sum_;
        }
        return _scoreTmpDefense;
    }
    public void scores(int _scoreDefinitifAttaque,int _scoreDefinitifDefense) {
        byte nombreJoueurs_=getNombreDeJoueurs();
        Numbers<Short> scores_=getScores();
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
            if(!aPourDefenseur(joueur_)) {
                scores_.set(joueur_,(short)_scoreDefinitifAttaque);
            } else {
                scores_.set(joueur_,(short)_scoreDefinitifDefense);
            }
        }
    }
    /**Renvoie 1, si l utilisateur gagne la partie,<br>
    0, s il y a match nul,<br>
    -1, sinon*/
    public EndGameState getUserState(byte _user) {
        Numbers<Short> scores_=getScores();
        short userScore_ = scores_.get(_user);
        boolean slam_ = getPlisDefense().isEmpty();
        for (byte p: adversaires(_user)) {
            if(userScore_<scores_.get(p)) {
                return EndGameState.LOOSE;
            }
            if(userScore_==scores_.get(p) && !slam_) {
                return EndGameState.EQUALLITY;
            }
        }
        return EndGameState.WIN;
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
        if(getDistribution().main().estVide()) {
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
    public void restituerMainsDepart(DisplayingBelote _displaying,CustList<TrickBelote> _plisFaits,byte _nombreJoueurs) {
        for(TrickBelote pli_:_plisFaits) {
            for(CardBelote carte_:pli_) {
                getDistribution().ajouter(pli_.joueurAyantJoue(carte_),carte_);
            }
        }
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
            getDistribution().supprimerCartes(joueur_,getDistribution().derniereMain());
        }
        if(bid.getCouleurDominante()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),couleurAtout());
            }
        } else if(bid.ordreCouleur()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().setOrdre(joueur_,Order.SUIT);
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),Order.SUIT);
            }
        } else if(bid.ordreAtout()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().setOrdre(joueur_,Order.TRUMP);
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),Order.TRUMP);
            }
        }
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
    private CustList<TrickBelote> getPlisAttaque() {
        CustList<TrickBelote> tricks_ = new CustList<TrickBelote>();
        for (TrickBelote t: tricks) {
            if (aPourDefenseur(t.getRamasseur(bid))) {
                continue;
            }
            tricks_.add(t);
        }
        return tricks_;
    }
    private CustList<TrickBelote> getPlisDefense() {
        CustList<TrickBelote> tricks_ = new CustList<TrickBelote>();
        for (TrickBelote t: tricks) {
            if (!aPourDefenseur(t.getRamasseur(bid))) {
                continue;
            }
            tricks_.add(t);
        }
        return tricks_;
    }
    public boolean aPourDefenseur(byte _numero) {
        return _numero!=taker&&!partenaires(taker).containsObj(_numero);
    }

    public Numbers<Byte> orderedPlayers(byte _leader) {
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
        byte leader_ = getPliEnCours().getEntameur();
        for (byte p: rules.getRepartition().getSortedPlayers(leader_)) {
            if (getPliEnCours().aJoue(p, getNombreDeJoueurs())) {
                continue;
            }
            return p;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
//        byte playerHavingToPlay_ = leader_;
//        int nbCards_ = getPliEnCours().total();
//        for (byte b=CustList.FIRST_INDEX;b<nbCards_;b++) {
//            playerHavingToPlay_ = playerAfter(playerHavingToPlay_);
//        }
//        return playerHavingToPlay_;
    }
    public byte playerAfter(byte _player) {
        return rules.getRepartition().getNextPlayer(_player);
    }

    public Numbers<Byte> adversaires(byte _numero) {
        Numbers<Byte> adversaires_ = new Numbers<Byte>();
        DealingBelote repartitionCourante_ = rules.getRepartition();
        if(repartitionCourante_ == DealingBelote.CLASSIC_2_VS_2) {
            byte player_ = playerAfter(_numero);
            adversaires_.add(player_);
            player_ = playerAfter(player_);
            player_ = playerAfter(player_);
            adversaires_.add(player_);
        }
        if(repartitionCourante_ == DealingBelote.COINCHE_2_VS_2) {
            byte player_ = playerAfter(_numero);
            adversaires_.add(player_);
            player_ = playerAfter(player_);
            player_ = playerAfter(player_);
            adversaires_.add(player_);
        }
        return adversaires_;
    }
    public Numbers<Byte> partenaires(byte _numero) {
        Numbers<Byte> partenaires_ = new Numbers<Byte>();
        DealingBelote repartitionCourante_ = rules.getRepartition();
        if(repartitionCourante_ == DealingBelote.CLASSIC_2_VS_2) {
            byte player_ = playerAfter(_numero);
            player_ = playerAfter(player_);
            partenaires_.add(player_);
        }
        if(repartitionCourante_ == DealingBelote.COINCHE_2_VS_2) {
            byte player_ = playerAfter(_numero);
            player_ = playerAfter(player_);
            partenaires_.add(player_);
        }
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


    /**@throws NullPointerException si un des arguments est null*/
    private static boolean egaliteJoueurs(Numbers<Byte> _joueurs1, Numbers<Byte> _joueurs2) {
        return _joueurs1.containsAllObj(_joueurs2) && _joueurs2.containsAllObj(_joueurs1);
    }
    public boolean isSameTeam(Numbers<Byte> _players) {
        int nbPlayers_ = _players.size();
        for (byte i=CustList.SECOND_INDEX;i<nbPlayers_;i++) {
            if (!memeEquipe(_players.getPrev(i), _players.get(i))) {
                return false;
            }
        }
        return true;
    }
    public EqList<Numbers<Byte>> playersBelongingToSameTeam() {
        EqList<Numbers<Byte>> teams_ = new EqList<Numbers<Byte>>();
        Numbers<Byte> takerTeam_ = partenaires(taker);
        takerTeam_.add(taker);
        teams_.add(takerTeam_);
        Numbers<Byte> takerFoeTeam_ = adversaires(taker);
        teams_.add(takerFoeTeam_);
        return teams_;
    }
    private static Numbers<Byte> intersectionJoueurs(Numbers<Byte> _joueurs1, Numbers<Byte> _joueurs2) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte j : _joueurs1) {
            if(!_joueurs2.containsObj(j)) {
                continue;
            }
            joueurs_.add(j);
        }
        return joueurs_;
    }

    private static Numbers<Byte> autresJoueurs(Numbers<Byte> _joueurs,
            byte _nombreJoueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (!_joueurs.containsObj(joueur_)) {
                joueurs_.add(joueur_);
            }
        }
        return joueurs_;
    }


    /**
    Retourne l'ensemble des joueurs n'ayant pas joue autre que le joueur
    numero
    */
    private Numbers<Byte> joueursNAyantPasJoue(byte _numero) {
        Numbers<Byte> joueursNAyantPasJoue_ = new Numbers<Byte>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            if (joueur_ != _numero && !progressingTrick.aJoue(joueur_, nombreJoueurs_)) {
                joueursNAyantPasJoue_.add(joueur_);
            }
        }
        return joueursNAyantPasJoue_;
    }


    private static Numbers<Byte> joueursPouvantCouperCouleurs(HandBelote _main,
            Numbers<Byte> _joueurs, BidBeloteSuit _contrat,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, EnumList<Suit> _couleurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if(joueurs_.containsObj(joueur_)) {
                continue;
            }
            for (Suit couleur_ : _couleurs) {
                if (_main.couleur(_contrat,couleur_).estVide()) {
                    continue;
                }
                if (peutCouper(couleur_, joueur_, _cartesPossibles, _contrat.getCouleur())) {
                    joueurs_.add(joueur_);
                    break;
                }
            }
        }
        return joueurs_;
    }

    private static Numbers<Byte> joueursSusceptiblesCoupe(
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Suit _couleurDemandee,
            Suit _couleurAtout,
            Numbers<Byte> _joueurs) {
        Numbers<Byte> joueursSusceptiblesDeCouper_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (peutCouper(_couleurDemandee, joueur_, _cartesPossibles, _couleurAtout)) {
                joueursSusceptiblesDeCouper_
                .add(joueur_);
            }
        }
        return joueursSusceptiblesDeCouper_;
    }

    public String getRaison() {
        return reason;
    }

    private String format(String _key, Object... _vars) {
        return Format.formatter(FOLDER, file, Constants.getLanguage(), _key, _vars);
    }

    public void sauvegarder(String _nomFichier) {
        StreamTextFile.saveObject(_nomFichier, this);
    }

    public static int getChargementSimulation() {
        return _chargementSimulation_;
    }

    public static void setChargementSimulation(int _chargementSimulation) {
        GameBelote._chargementSimulation_ = _chargementSimulation;
    }
}
