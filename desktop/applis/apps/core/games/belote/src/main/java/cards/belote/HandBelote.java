package cards.belote;
import java.util.Iterator;

import cards.belote.comparators.DeclareHandBeloteComparator;
import cards.belote.comparators.DeclareStrengthCardBeloteComparator;
import cards.belote.comparators.GameStrengthCardBeloteComparator;
import cards.belote.enumerations.*;
import cards.consts.CardChar;
import cards.consts.Order;
import cards.consts.Suit;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.*;
import code.util.core.IndexConstants;

/**
 */

public final class HandBelote implements Iterable<CardBelote> {

    private Order order;
    private EnumList<CardBelote> cards=new EnumList<CardBelote>();
    public HandBelote() {
        order=Order.TRUMP;
    }
    public HandBelote(Order _pordre) {
        order=_pordre;
    }

    HandBelote(HandBelote _main) {
        order = _main.order;
        cards.addAllElts(_main.cards);
    }

    public boolean validStack() {
        return equalsSet(this, pileBase());
        //        return CustList.equalsSet(cards, pileBase().cards, true);
    }

    public static int pointsTotauxDixDeDer() {
        int min_ = Integer.MAX_VALUE;
        for (Suit s: Suit.couleursOrdinaires()) {
            BidBeloteSuit bid_;
            bid_ = new BidBeloteSuit();
            bid_.setCouleur(s);
            bid_.setEnchere(BidBelote.SUIT);
            min_ = Math.min(pointsTotauxDixDeDer(bid_), min_);
        }
        return min_;
    }

    public static int pointsTotauxDixDeDer(BidBeloteSuit _enchere) {
        HandBelote liste_ = pileBase();
        int points_ = 0;
        for(CardBelote c: liste_) {
            points_ += c.points(_enchere);
        }
        points_ += BonusBelote.LAST_TRICK.getPoints();
        return points_;
    }

    public static HandBelote pileBase() {
        HandBelote liste_ = new HandBelote();
        for(CardBelote carte_:CardBelote.values()) {
            if(!carte_.isPlayable()) {
                continue;
            }
            liste_.ajouter(carte_);
        }
        return liste_;
    }
    public static HandBelote reunion(EnumMap<Suit,HandBelote> _all) {
        HandBelote h_ = new HandBelote();
        for (EntryCust<Suit,HandBelote> e: _all.entryList()) {
            h_.ajouterCartes(e.getValue());
        }
        return h_;
    }
    public static HandBelote couleurComplete(Suit _couleur, Order _ordre) {
        HandBelote liste_ = new HandBelote(_ordre);
        for(CardBelote carte_: pileBase()) {
            if(carte_.couleur() != _couleur) {
                continue;
            }
            liste_.ajouter(carte_);
        }
        liste_.trierUnicolore(true);
        return liste_;
    }
    /**On prend la premiere carte de la main puis on la place a la fin
    dans le but de deplacer la moitie d'une main apres l'autre*/
    void couper() {
        int taille_=total()/2;
        for (int i = IndexConstants.FIRST_INDEX; i<taille_; i++) {
            ajouter(jouer(IndexConstants.FIRST_INDEX));
        }
    }
    /**<ol>
    <li>Choisit un nombre aleatoire entre 0 et le nombre total de carte dans la main moins une carte
    d'ou total() x Math.random() puis Math.floor(total() x Math.random())</li>
    <li>puis retire la carte choisie de la main courante et la retourne</li>
    </ol>
    @return la carte aleatoire choisie*/
    CardBelote tirerUneCarteAleatoire(AbstractGenerator _gene) {
        //      return jouer((int)Math.floor(total()*MonteCarlo.randomDouble()));
        return jouer((int) MonteCarloUtil.randomLong(total(),_gene));
        //0<=total()*Math.random()<total()
        //Donc 0<=Math.floor(total()*Math.random())<Math.floor(total())=total()
    }
    /**Ajoute une carte a la fin de la main
    et precise le jeu
    @param _t la carte a ajouter*/
    public void ajouter(CardBelote _t) {
        cards.add(_t);
    }

    /**Ajoute les cartes d'une main dans la main courante
    a la fin de celle-ci les cartes ajoutees conservent le
    meme ordre qu'a l'etat initial
    @param _cartes la main a ajouter*/
    public void ajouterCartes(HandBelote _cartes) {
        for(CardBelote carte_:_cartes) {
            cards.add(carte_);
        }
    }
    /**Joue une carte a la position a
    @return la carte a jouer*/
    CardBelote jouer(int _a) {
        CardBelote c_ = cards.get(_a);
        cards.remove(_a);
        return c_;
    }
    public void jouer(CardBelote _c) {
        cards.remove(cards.indexOfObj(_c));
    }

    void supprimer(int _carte) {
        cards.remove(_carte);
    }

    /**Renvoie le nombre total de cartes dans la main*/
    public int total() {
        return cards.size();
    }
    /**Renvoie la carte a la position i*/
    public CardBelote carte(int _i) {
        return cards.get(_i);
    }
    public CardBelote premiereCarte() {
        return cards.first();
    }
    public CardBelote derniereCarte() {
        return cards.last();
    }
    /**Tester l'existence d'une carte dans une main*/
    public boolean contient(CardBelote _c) {
        return cards.containsObj(_c);
    }
    boolean contientCartes(HandBelote _cartesBeloteRebelote) {
        return cards.containsAllObj(_cartesBeloteRebelote.cards);
    }

    /**Supprime les cartes d'une main tout en conservant les eventuels doublons*/
    public void supprimerCartes(HandBelote _m) {
        int indice_;
        for(CardBelote c:_m) {
            indice_=position(c);
            if(indice_>-1) {
                supprimer(indice_);
            }
        }
    }
    public void supprimerCartes() {
        cards.clear();
    }
    int position(CardBelote _c) {
        return cards.indexOfObj(_c);
    }

    int tailleCouleur(Suit _couleur) {
        int taille_=0;
        for(CardBelote c:cards) {
            if(c.couleur()==_couleur) {
                taille_++;
            }
        }
        return taille_;
    }

    public boolean estVide() {
        return cards.isEmpty();
    }

    long pointsBid(int _nbPlayers, int _nbFinalCards, BidBelote _b) {
        Suit c_ = Suit.UNDEFINED;
        int count_ = total();
        BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
        enchereBeloteLoc_.setCouleur(c_);
        enchereBeloteLoc_.setEnchere(_b);
        EnumMap<Suit,HandBelote> repartition_=couleurs(enchereBeloteLoc_);
        HandBelote rem_ = HandBelote.pileBase();
        rem_.supprimerCartes(this);
        EnumMap<Suit,HandBelote> other_ = rem_.couleurs(enchereBeloteLoc_);
        int pointsFictifs_ = 0;
        for(Suit c2_: GameBeloteCommon.couleurs()) {
            pointsFictifs_ += getPointsCount(_nbPlayers,enchereBeloteLoc_,c2_,repartition_,other_);
        }
        pointsFictifs_ *= _nbFinalCards;
        pointsFictifs_ /= count_;
        return pointsFictifs_;
    }
    EnumMap<Suit,Long> pointsAvg(int _nbPlayers, int _nbFinalCards) {
        EnumMap<Suit,Long> couleurPointsFictifs_ = new EnumMap<Suit,Long>();
        HandBelote rem_ = HandBelote.pileBase();
        rem_.supprimerCartes(this);
        int count_ = total();
        for(Suit c: GameBeloteCommon.couleurs()) {
            //c: couleur atout
            BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
            enchereBeloteLoc_.setCouleur(c);
            enchereBeloteLoc_.setEnchere(BidBelote.SUIT);
            EnumMap<Suit,HandBelote> repartition_=couleurs(enchereBeloteLoc_);
            EnumMap<Suit,HandBelote> other_ = rem_.couleurs(enchereBeloteLoc_);
            //repartition est la repartition des cartes a la couleur d'atout c
            long pointsFictifs_ = 0;
            for(Suit c2_: GameBeloteCommon.couleurs()) {
                if(c2_ == c) {
                    continue;
                }
                pointsFictifs_ += getPointsCount(_nbPlayers,enchereBeloteLoc_,c2_,repartition_,other_);
            }
            pointsFictifs_ += getPointsCount(_nbPlayers,enchereBeloteLoc_,c,repartition_,other_);
            pointsFictifs_ *= _nbFinalCards;
            pointsFictifs_ /= count_;
            HandBelote cartes_ = GameBeloteCommonPlaying.cartesBeloteRebelote(enchereBeloteLoc_);
            if (contientCartes(cartes_)) {
                pointsFictifs_ += DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints();
            }
            couleurPointsFictifs_.put(c, pointsFictifs_);
        }
        return couleurPointsFictifs_;
    }
    static long getPointsCount(int _nbPlayers, BidBeloteSuit _bid, Suit _curSuit, EnumMap<Suit,HandBelote> _currHand, EnumMap<Suit,HandBelote> _other) {
        Suit trumSuit_ = _bid.getCouleur();
        long pointsFictifs_ = 0;
        HandBelote zerosLoc_ = new HandBelote();
        for(Suit c3_: GameBeloteCommon.couleurs()) {
            if (c3_ == trumSuit_) {
                continue;
            }
            if (c3_ == _curSuit) {
                continue;
            }
            zerosLoc_.ajouterCartes(_other.getVal(c3_));
        }
        //c2: couleur ordinaire
        HandBelote otherLoc_ = _other.getVal(_curSuit);
        HandBelote suit_ = _currHand.getVal(_curSuit);

        HandBelote cartesAssurantMax_ = suit_.cartesPlisAssures(_bid);
        for(CardBelote c3_: cartesAssurantMax_) {
            HandBelote all_ = new HandBelote();
            all_.ajouterCartes(zerosLoc_);
            for (CardBelote c4_: otherLoc_) {
                if (c4_.strength(_curSuit,_bid) < c3_.strength(_curSuit,_bid)) {
                    all_.ajouter(c4_);
                }
            }
            long sum_ = 0;
            for (CardBelote c4_: all_) {
                sum_ += c4_.points(_bid);
            }
            sum_ *= _nbPlayers - 1;
            sum_ /= all_.total();
            pointsFictifs_+=c3_.points(_bid)+sum_;
        }
        return pointsFictifs_;
    }
    HandBelote cartesPlisAssures(BidBeloteSuit _enchere) {
        if(estVide()) {
            return new HandBelote();
        }
        Suit couleur_ = premiereCarte().couleur();
        Order ordre_;
        if(!_enchere.getCouleurDominante()) {
            ordre_ = _enchere.getOrdre();
        }else if(couleur_ == _enchere.getCouleur()) {
            ordre_ = Order.TRUMP;
        }else {
            ordre_ = Order.SUIT;
        }
        HandBelote cartesAssurantMin_ = cartesPlisAssuresMin(ordre_);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_ = new HandBelote().couleurs(_enchere);
        CustList<HandBelote> suitesMin_ = cartesAssurantMin_.eclater(repartitionCartesJouees_, _enchere);
        CustList<HandBelote> suites_ = eclater(repartitionCartesJouees_,_enchere);
        HandBelote cartesAssurantMax_ = new HandBelote(ordre_);
        for(HandBelote m: suitesMin_) {
            for(HandBelote m2_: suites_) {
                if(m2_.derniereCarte() != m.derniereCarte()) {
                    continue;
                }
                if(m2_.premiereCarte() == m.premiereCarte()) {
                    cartesAssurantMax_.ajouterCartes(m);
                    continue;
                }
                cartesAssurantMax_.getCards().addAllElts(m2_.getCards().left(m.total()));
                break;
            }
        }
        return cartesAssurantMax_;
    }
    private HandBelote cartesPlisAssuresMin(Order _ordre) {
        if(estVide()) {
            return new HandBelote(_ordre);
        }
        HandBelote cartes_ = new HandBelote(_ordre);
        Suit couleurAtout_ = premiereCarte().couleur();
        byte nombrePlis_=0;
        byte cartesManquantes_=0;
        byte cartesPossedees_=0;
        byte indiceCarte_=0;
        for(CardBelote carte_: couleurComplete(couleurAtout_, _ordre)) {
            if(indiceCarte_<total()&&CardBelote.eq(carte(indiceCarte_), carte_)) {
                if(nombrePlis_>0) {
                    nombrePlis_++;
                }
                cartesPossedees_++;
                indiceCarte_++;
            } else {
                if(nombrePlis_>0) {
                    cartesManquantes_=0;
                    cartesPossedees_=0;
                }
                nombrePlis_=0;
                cartesManquantes_++;
            }
            if(cartesPossedees_>cartesManquantes_) {
                cartes_.ajouter(carte_);
                nombrePlis_=(byte)(cartesPossedees_-cartesManquantes_);
            }
        }
        return cartes_;
    }

    byte nombreDePlisMinAssures(Order _ordre) {
        return (byte) cartesPlisAssuresMin(_ordre).total();
    }
    EnumMap<Suit,CustList<HandBelote>> eclaterTout(EnumMap<Suit,HandBelote> _repartitionCartesJouees,
                                                 BidBeloteSuit _enchere) {
        EnumMap<Suit,CustList<HandBelote>> suitesTouteCouleur_ = new EnumMap<Suit,CustList<HandBelote>>();

        EnumMap<Suit, HandBelote> couleurs_ = couleurs(_enchere);
        for (Suit i : GameBeloteCommon.couleurs()) {
            suitesTouteCouleur_.put(i,couleurs_.getVal(i).eclater(
                    _repartitionCartesJouees, _enchere));
            //les couleurs sont classees comme si elles etaient demandees
        }
        return suitesTouteCouleur_;
    }
    CustList<HandBelote> eclater(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            BidBeloteSuit _enchere) {
        if(estVide()) {
            return new CustList<HandBelote>();
        }
        Suit couleur_=premiereCarte().couleur();
        CustList<HandBelote> suites_=new CustList<HandBelote>();
        boolean ajouterVec_ = true;
        Order ordre_;
        if(!_enchere.getCouleurDominante()) {
            ordre_ = _enchere.getOrdre();
        }else if(couleur_ == _enchere.getCouleur()) {
            ordre_ = Order.TRUMP;
        }else {
            ordre_ = Order.SUIT;
        }
        for(CardBelote carte_:couleurComplete(couleur_,ordre_)) {
            if(_repartitionCartesJouees.getVal(couleur_).contient(carte_)) {
                continue;
            }
            if(!contient(carte_)) {
                ajouterVec_ = true;
                continue;
            }
            if(ajouterVec_) {
                sortIfNotEmpty(suites_);
                suites_.add(new HandBelote(ordre_));
            }
            ajouterVec_ = false;
            suites_.last().ajouter(carte_);
        }
        sortIfNotEmpty(suites_);
        return suites_;
    }

    private static void sortIfNotEmpty(CustList<HandBelote> _suites) {
        if(!_suites.isEmpty()) {
            _suites.last().trierUnicolore(true);
        }
    }

    HandBelote couleur(BidBeloteSuit _contrat, Suit _couleur) {
        return couleurs(_contrat).getVal(_couleur);
    }
    public EnumMap<Suit,HandBelote> couleurs(BidBeloteSuit _contrat) {
        EnumMap<Suit,HandBelote> mains_=new EnumMap<Suit,HandBelote>();
        for(CardBelote c: cards) {
            if(!mains_.contains(c.couleur())) {
                mains_.put(c.couleur(), new HandBelote());
            }
            mains_.getVal(c.couleur()).ajouter(c);
        }
        for(Suit c:mains_.getKeys()) {
            mains_.getVal(c).cards.sortElts(new GameStrengthCardBeloteComparator(c, _contrat, true));
        }
        for(Suit c:Suit.couleursOrdinaires()) {
            if(mains_.contains(c)) {
                continue;
            }
            mains_.put(c, new HandBelote());
        }
        return mains_;
    }

    public DeclareHandBelote annonce(EnumList<DeclaresBelote> _annoncesAutorisees,
            BidBeloteSuit _enchere) {
        CustList<DeclareHandBelote> annoncesPossibles_ = new CustList<DeclareHandBelote>();
        for(DeclaresBelote a: _annoncesAutorisees) {
            if(a.estConstante()) {
                HandBelote cartesMemeHauteur_ = new HandBelote();
                if(a.getFigure() != CardChar.UNDEFINED) {
                    for(CardBelote c: HandBelote.pileBase()) {
                        if(c.getNomFigure() == a.getFigure()) {
                            cartesMemeHauteur_.ajouter(c);
                        }
                    }
                }else {
                    for(CardBelote c: HandBelote.pileBase()) {
                        if(c.valeur() == a.getValeur()) {
                            cartesMemeHauteur_.ajouter(c);
                        }
                    }
                }
                if(cards.containsAllObj(cartesMemeHauteur_.cards)) {
                    DeclareHandBelote annonceLoc_ = new DeclareHandBelote();
                    annonceLoc_.setHand(cartesMemeHauteur_);
                    annonceLoc_.setDeclare(a);
                    annoncesPossibles_.add(annonceLoc_);
                }
                continue;
            }
            EnumMap<Suit,HandBelote> mains_ = couleurs(_enchere);
            for(Suit c: mains_.getKeys()) {
                HandBelote annonceMemeCouleur_ = mains_.getVal(c);
                if(annonceMemeCouleur_.total() < a.nombreCartes()) {
                    continue;
                }
                HandBelote mainTriee_ = new HandBelote();
                for(CardBelote c2_: HandBelote.pileBase()) {
                    if(c2_.couleur() == c) {
                        mainTriee_.ajouter(c2_);
                    }
                }
                annonceMemeCouleur_.cards.sortElts(new DeclareStrengthCardBeloteComparator());
                mainTriee_.cards.sortElts(new DeclareStrengthCardBeloteComparator());
                CustList<HandBelote> suites_ = new CustList<HandBelote>();
                boolean ajouterVec_ = true;
                for(CardBelote c2_: mainTriee_) {
                    if(!annonceMemeCouleur_.contient(c2_)) {
                        ajouterVec_ = true;
                        continue;
                    }
                    if(ajouterVec_) {
                        suites_.add(new HandBelote());
                    }
                    ajouterVec_ = false;
                    suites_.last().ajouter(c2_);
                }
                for(HandBelote s: suites_) {
                    HandBelote annoncePossible_ = new HandBelote();
                    int nbCartesAnnonce_ = a.nombreCartes();
                    int nbIterations_ = s.total() -nbCartesAnnonce_+1;
                    for (int i = IndexConstants.FIRST_INDEX; i<nbIterations_; i++) {
                        for (int j = IndexConstants.FIRST_INDEX; j<nbCartesAnnonce_; j++) {
                            annoncePossible_.ajouter(s.carte(i+j));
                        }
                    }
                    if(annoncePossible_.estVide()) {
                        continue;
                    }
                    DeclareHandBelote annonceLoc_ = new DeclareHandBelote();
                    annonceLoc_.setHand(annoncePossible_);
                    annonceLoc_.setDeclare(a);
                    annoncesPossibles_.add(annonceLoc_);
                }
            }
        }
        annoncesPossibles_.sortElts(new DeclareHandBeloteComparator(_enchere));
        DeclareHandBelote annonce_ = new DeclareHandBelote();
        if(!annoncesPossibles_.isEmpty()) {
            annonce_= annoncesPossibles_.get(0);
        }
        return annonce_;
    }
    public void setOrdre(Order _pordre) {
        order=_pordre;
    }
    void trierUnicolore(boolean _decroissant) {
        if(estVide()) {
            return;
        }
        Suit couleur_ = premiereCarte().couleur();

        if(order==Order.TRUMP) {
            cards.sortElts(new GameStrengthCardBeloteComparator(couleur_, couleur_, _decroissant));
        }else {
            cards.sortElts(new GameStrengthCardBeloteComparator(Suit.UNDEFINED, couleur_, _decroissant));
        }
    }


    public void trier(EnumList<Suit> _couleurs, boolean _decroissant,Order _ordreTri) {
        EnumList<CardBelote> nouvelleMain_ = new EnumList<CardBelote>();
        for(Suit s_: _couleurs) {
            HandBelote mainCouleur_ = new HandBelote(_ordreTri);
            for(CardBelote carte_: cards) {
                if(carte_.couleur() == s_) {
                    mainCouleur_.ajouter(carte_);
                }
            }
            mainCouleur_.trierUnicolore(_decroissant);
            nouvelleMain_.addAllElts(mainCouleur_.cards);
        }
        cards.clear();
        cards.addAllElts(nouvelleMain_);
    }
    /**Appelee apres un contrat couleur ou autre couleur*/
    public void trier(EnumList<Suit> _couleurs, boolean _decroissant,Suit _couleurAtout) {
        order=Order.SUIT;
        EnumList<CardBelote> nouvelleMain_ = new EnumList<CardBelote>();
        for(Suit couleur_: _couleurs) {
            HandBelote mainCouleur_ = new HandBelote();
            for(CardBelote carte_: cards) {
                if(carte_.couleur() == couleur_) {
                    mainCouleur_.ajouter(carte_);
                }
            }
            if(couleur_ == _couleurAtout) {
                mainCouleur_.setOrdre(Order.TRUMP);
            }else {
                mainCouleur_.setOrdre(Order.SUIT);
            }
            mainCouleur_.trierUnicolore(_decroissant);
            nouvelleMain_.addAllElts(mainCouleur_.cards);
        }



        cards.clear();
        cards.addAllElts(nouvelleMain_);
        //On trie_ les_ cartes_ d'atout_
    }

    byte nombreCartesPoints(BidBeloteSuit _contrat) {
        byte nombreCartesPoints_=0;
        for(CardBelote carte_:cards) {
            if(carte_.points(_contrat)>0) {
                nombreCartesPoints_++;
            }
        }
        return nombreCartesPoints_;
    }

    public static boolean equalsSet(HandBelote _handOne, HandBelote _handTwo) {
        //        return CustList.equalsSet(_handOne.cards, _handTwo.cards, true);
        return CardBelote.equalsCards(_handOne.cards, _handTwo.cards);
    }

    @Override
    public Iterator<CardBelote> iterator() {
        return cards.iterator();
    }

    public boolean eq(HandBelote _g) {
        if(_g.total()!=total()) {
            return false;
        }
        boolean id_=true;
        int nbCards_ = total();
        for (int i = IndexConstants.FIRST_INDEX; i < nbCards_; i++) {
            if (!CardBelote.eq(_g.carte(i), carte(i))) {
                id_ = false;
            }
        }
        return id_;
    }

    public EnumList<CardBelote> getCards() {
        return cards;
    }
    public void setCards(EnumList<CardBelote> _cards) {
        cards = _cards;
    }

    public void removeCardIfPresent(CardBelote _c) {
        cards.removeAllObj(_c);
    }
}
