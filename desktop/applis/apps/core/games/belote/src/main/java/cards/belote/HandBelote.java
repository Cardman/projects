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
import code.util.core.NumberUtil;

/**
 */

public final class HandBelote implements Iterable<CardBelote> {

//    private Order order;
    private IdList<CardBelote> cards=new IdList<CardBelote>();
    public HandBelote() {
//        order=Order.TRUMP;
    }

    public HandBelote(HandBelote _main) {
//        order = _main.order;
        cards.addAllElts(_main.cards);
    }
    public static HandBelote create(CardBelote[] _cards) {
        HandBelote h_ = new HandBelote();
        for (CardBelote c : _cards) {
            h_.ajouter(c);
        }
        return h_;
    }

    public static HandBelote nullToEmpty(HandBelote _h) {
        if (_h == null) {
            return new HandBelote();
        }
        return _h;
    }

    public static HandBelote low(HandBelote _stack) {
        HandBelote low_ = new HandBelote();
        for (CardBelote c: _stack) {
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setBid(BidBelote.SUIT);
            bid_.setSuit(c.getId().getCouleur());
            if (c.points(bid_) == 0) {
                low_.ajouter(c);
            }
        }
        return low_;
    }

    public boolean validStack() {
        return equalsSet(this, pileBase());
        //        return CustList.equalsSet(cards, pileBase().cards, true);
    }

    public static HandBelote pileBase() {
        HandBelote liste_ = new HandBelote();
        liste_.ajouter(CardBelote.HEART_JACK);
        liste_.ajouter(CardBelote.HEART_9);
        liste_.ajouter(CardBelote.HEART_1);
        liste_.ajouter(CardBelote.HEART_10);
        liste_.ajouter(CardBelote.HEART_KING);
        liste_.ajouter(CardBelote.HEART_QUEEN);
        liste_.ajouter(CardBelote.HEART_8);
        liste_.ajouter(CardBelote.HEART_7);
        liste_.ajouter(CardBelote.SPADE_JACK);
        liste_.ajouter(CardBelote.SPADE_9);
        liste_.ajouter(CardBelote.SPADE_1);
        liste_.ajouter(CardBelote.SPADE_10);
        liste_.ajouter(CardBelote.SPADE_KING);
        liste_.ajouter(CardBelote.SPADE_QUEEN);
        liste_.ajouter(CardBelote.SPADE_8);
        liste_.ajouter(CardBelote.SPADE_7);
        liste_.ajouter(CardBelote.DIAMOND_JACK);
        liste_.ajouter(CardBelote.DIAMOND_9);
        liste_.ajouter(CardBelote.DIAMOND_1);
        liste_.ajouter(CardBelote.DIAMOND_10);
        liste_.ajouter(CardBelote.DIAMOND_KING);
        liste_.ajouter(CardBelote.DIAMOND_QUEEN);
        liste_.ajouter(CardBelote.DIAMOND_8);
        liste_.ajouter(CardBelote.DIAMOND_7);
        liste_.ajouter(CardBelote.CLUB_JACK);
        liste_.ajouter(CardBelote.CLUB_9);
        liste_.ajouter(CardBelote.CLUB_1);
        liste_.ajouter(CardBelote.CLUB_10);
        liste_.ajouter(CardBelote.CLUB_KING);
        liste_.ajouter(CardBelote.CLUB_QUEEN);
        liste_.ajouter(CardBelote.CLUB_8);
        liste_.ajouter(CardBelote.CLUB_7);
        return liste_;
    }
    public static HandBelote reunion(IdMap<Suit,HandBelote> _all) {
        HandBelote h_ = new HandBelote();
        for (EntryCust<Suit,HandBelote> e: _all.entryList()) {
            h_.ajouterCartes(e.getValue());
        }
        return h_;
    }
    public static HandBelote couleurComplete(Suit _couleur, Order _ordre) {
        return couleurComplete(_couleur,build(_ordre));
    }
    public static HandBelote couleurComplete(Suit _couleur, BidBeloteSuit _ordre) {
        HandBelote liste_ = new HandBelote();
        for(CardBelote carte_: pileBase()) {
            if(carte_.getId().getCouleur() != _couleur) {
                continue;
            }
            liste_.ajouter(carte_);
        }
        liste_.trierUnicolore(true,_ordre);
        return liste_;
    }

    static BidBeloteSuit build(Order _ordre) {
        BidBeloteSuit bid_ = new BidBeloteSuit();
        if (_ordre == Order.TRUMP) {
            bid_.setBid(BidBelote.ALL_TRUMP);
        } else {
            bid_.setBid(BidBelote.SUIT);
        }
        return bid_;
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
        return jouer(MonteCarloUtil.randomLong(total(),_gene));
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
            if(c.getId().getCouleur() ==_couleur) {
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
        enchereBeloteLoc_.setSuit(c_);
        enchereBeloteLoc_.setBid(_b);
        IdMap<Suit,HandBelote> repartition_=couleurs(enchereBeloteLoc_);
        HandBelote rem_ = HandBelote.pileBase();
        rem_.supprimerCartes(this);
        IdMap<Suit,HandBelote> other_ = rem_.couleurs(enchereBeloteLoc_);
        int pointsFictifs_ = 0;
        for(Suit c2_: GameBeloteCommon.couleurs()) {
            pointsFictifs_ += getPointsCount(_nbPlayers,enchereBeloteLoc_,c2_,repartition_,other_);
        }
        pointsFictifs_ *= _nbFinalCards;
        pointsFictifs_ /= count_;
        return pointsFictifs_;
    }
    IdMap<Suit,Long> pointsAvg(int _nbPlayers, int _nbFinalCards) {
        IdMap<Suit,Long> couleurPointsFictifs_ = new IdMap<Suit,Long>();
        HandBelote rem_ = HandBelote.pileBase();
        rem_.supprimerCartes(this);
        int count_ = total();
        for(Suit c: GameBeloteCommon.couleurs()) {
            //c: couleur atout
            BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
            enchereBeloteLoc_.setSuit(c);
            enchereBeloteLoc_.setBid(BidBelote.SUIT);
            IdMap<Suit,HandBelote> repartition_=couleurs(enchereBeloteLoc_);
            IdMap<Suit,HandBelote> other_ = rem_.couleurs(enchereBeloteLoc_);
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
    static long getPointsCount(int _nbPlayers, BidBeloteSuit _bid, Suit _curSuit, IdMap<Suit,HandBelote> _currHand, IdMap<Suit,HandBelote> _other) {
        Suit trumSuit_ = _bid.getSuit();
        long pointsFictifs_ = 0;
        HandBelote zerosLoc_ = new HandBelote();
        for(Suit c3_: GameBeloteCommon.couleurs()) {
            if (c3_ == trumSuit_ || c3_ == _curSuit) {
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
            sum_ /= NumberUtil.max(1,all_.total());
            pointsFictifs_+=c3_.points(_bid)+sum_;
        }
        return pointsFictifs_;
    }


    public static Order order(BidBeloteSuit _contrat, Suit _couleur) {
        Order ordre_;
        if(!_contrat.getCouleurDominante()) {
            ordre_ = _contrat.getOrdre();
        } else if(_couleur == _contrat.getSuit()) {
            ordre_ = Order.TRUMP;
        } else {
            ordre_ = Order.SUIT;
        }
        return ordre_;
    }
    HandBelote cartesPlisAssures(BidBeloteSuit _enchere) {
        if(estVide()) {
            return new HandBelote();
        }
        HandBelote cartesAssurantMin_ = cartesPlisAssuresMin(_enchere);
        IdMap<Suit,HandBelote> repartitionCartesJouees_ = new HandBelote().couleurs(_enchere);
        CustList<HandBelote> suitesMin_ = cartesAssurantMin_.eclater(repartitionCartesJouees_, _enchere);
        CustList<HandBelote> suites_ = eclater(repartitionCartesJouees_,_enchere);
        HandBelote cartesAssurantMax_ = new HandBelote();
        for(HandBelote m: suitesMin_) {
            for(HandBelote m2_: suites_) {
                if (m2_.derniereCarte() == m.derniereCarte()) {
                    if (m2_.premiereCarte() != m.premiereCarte()) {
                        cartesAssurantMax_.getCards().addAllElts(m2_.getCards().left(m.total()));
                        break;
                    }
                    cartesAssurantMax_.ajouterCartes(m);
                }
            }
        }
        return cartesAssurantMax_;
    }
    private HandBelote cartesPlisAssuresMin(BidBeloteSuit _ordre) {
        if(estVide()) {
            return new HandBelote();
        }
        HandBelote cartes_ = new HandBelote();
        Suit couleurAtout_ = premiereCarte().getId().getCouleur();
        int nombrePlis_=0;
        int cartesManquantes_=0;
        int cartesPossedees_=0;
        int indiceCarte_=0;
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
                nombrePlis_= cartesPossedees_-cartesManquantes_;
            }
        }
        return cartes_;
    }

    int nombreDePlisMinAssures(Order _ordre) {
        return cartesPlisAssuresMin(build(_ordre)).total();
    }
    IdMap<Suit,CustList<HandBelote>> eclaterTout(IdMap<Suit,HandBelote> _repartitionCartesJouees,
                                                 BidBeloteSuit _enchere) {
        IdMap<Suit,CustList<HandBelote>> suitesTouteCouleur_ = new IdMap<Suit,CustList<HandBelote>>();

        IdMap<Suit, HandBelote> couleurs_ = couleurs(_enchere);
        for (EntryCust<Suit, HandBelote> i : couleurs_.entryList()) {
            suitesTouteCouleur_.addEntry(i.getKey(),i.getValue().eclater(
                    _repartitionCartesJouees, _enchere));
            //les couleurs sont classees comme si elles etaient demandees
        }
        return suitesTouteCouleur_;
    }
    CustList<HandBelote> eclater(
            IdMap<Suit,HandBelote> _repartitionCartesJouees,
            BidBeloteSuit _enchere) {
        if(estVide()) {
            return new CustList<HandBelote>();
        }
        Suit couleur_= premiereCarte().getId().getCouleur();
        CustList<HandBelote> suites_=new CustList<HandBelote>();
        boolean ajouterVec_ = true;
        for(CardBelote carte_:couleurComplete(couleur_,_enchere)) {
            if (!_repartitionCartesJouees.getVal(couleur_).contient(carte_)) {
                if (!contient(carte_)) {
                    ajouterVec_ = true;
                    continue;
                }
                if (ajouterVec_) {
                    sortIfNotEmpty(suites_,_enchere);
                    suites_.add(new HandBelote());
                }
                ajouterVec_ = false;
                suites_.last().ajouter(carte_);
            }
        }
        sortIfNotEmpty(suites_,_enchere);
        return suites_;
    }

    private static void sortIfNotEmpty(CustList<HandBelote> _suites, BidBeloteSuit _ord) {
        if(!_suites.isEmpty()) {
            _suites.last().trierUnicolore(true,_ord);
        }
    }

    public HandBelote couleur(BidBeloteSuit _contrat, Suit _couleur) {
        return couleurs(_contrat).getVal(_couleur);
    }
    public IdMap<Suit,HandBelote> couleurs(BidBeloteSuit _contrat) {
        IdMap<Suit,HandBelote> mains_=new IdMap<Suit,HandBelote>();
        for(CardBelote c: cards) {
            Suit couleur_ = c.getId().getCouleur();
//            boolean found_ = false;
            int index_ = mains_.indexOfEntry(couleur_);
            if (index_ >= 0) {
                mains_.getValue(index_).ajouter(c);
            } else {
                HandBelote h_ = new HandBelote();
                h_.ajouter(c);
                mains_.addEntry(couleur_, h_);
            }
//            for (EntryCust<Suit,HandBelote> e: mains_.entryList()) {
//                if (e.getKey() == couleur_) {
//                    e.getValue().ajouter(c);
//                    found_ = true;
//                    break;
//                }
//            }
//            if (!found_) {
//                HandBelote h_ = new HandBelote();
//                h_.ajouter(c);
//                mains_.addEntry(couleur_, h_);
//            }
//            if(!mains_.contains(couleur_)) {
//                mains_.addEntry(couleur_, new HandBelote());
//            }
//            mains_.getVal(couleur_).ajouter(c);
        }
        for(EntryCust<Suit,HandBelote> c:mains_.entryList()) {
            c.getValue().cards.sortElts(new GameStrengthCardBeloteComparator(c.getKey(), _contrat, true));
        }
        for(Suit c:Suit.couleursOrdinaires()) {
            int index_ = mains_.indexOfEntry(c);
            if (index_ >= 0) {
//            if(mains_.contains(c))
                continue;
            }
            mains_.addEntry(c, new HandBelote());
        }
        return mains_;
    }

    public DeclareHandBelote annonce(IdList<DeclaresBelote> _annoncesAutorisees,
            BidBeloteSuit _enchere) {
        CustList<DeclareHandBelote> annoncesPossibles_ = new CustList<DeclareHandBelote>();
        HandBelote deck_ = HandBelote.pileBase();
        for(DeclaresBelote a: _annoncesAutorisees) {
            if(a.estConstante()) {
                cstAnnonce(annoncesPossibles_, a, deck_);
                continue;
            }
            varAnnonce(_enchere, annoncesPossibles_, a, deck_);
        }
        annoncesPossibles_.sortElts(new DeclareHandBeloteComparator(_enchere));
        DeclareHandBelote annonce_ = new DeclareHandBelote();
        if(!annoncesPossibles_.isEmpty()) {
            annonce_= annoncesPossibles_.get(0);
        }
        return annonce_;
    }

    private void varAnnonce(BidBeloteSuit _enchere, CustList<DeclareHandBelote> _annoncesPossibles, DeclaresBelote _a, HandBelote _stack) {
        IdMap<Suit,HandBelote> mains_ = couleurs(_enchere);
        for(EntryCust<Suit, HandBelote> c: mains_.entryList()) {
            HandBelote annonceMemeCouleur_ = c.getValue();
            if(annonceMemeCouleur_.total() < _a.nombreCartes()) {
                continue;
            }
            varIterAnnonce(_annoncesPossibles, _a, c.getKey(), annonceMemeCouleur_, _stack);
        }
    }

    private void varIterAnnonce(CustList<DeclareHandBelote> _annoncesPossibles, DeclaresBelote _a, Suit _c, HandBelote _annonceMemeCouleur, HandBelote _stack) {
        HandBelote mainTriee_ = fullSuit(_c, _stack);
        _annonceMemeCouleur.cards.sortElts(new DeclareStrengthCardBeloteComparator());
        mainTriee_.cards.sortElts(new DeclareStrengthCardBeloteComparator());
        CustList<HandBelote> suites_ = new CustList<HandBelote>();
        boolean ajouterVec_ = true;
        for(CardBelote c2_: mainTriee_) {
            if(!_annonceMemeCouleur.contient(c2_)) {
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
            int nbCartesAnnonce_ = _a.nombreCartes();
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
            annonceLoc_.setDeclare(_a);
            _annoncesPossibles.add(annonceLoc_);
        }
    }

    private static HandBelote fullSuit(Suit _c, HandBelote _stack) {
        HandBelote mainTriee_ = new HandBelote();
        for(CardBelote c2_: _stack) {
            if(c2_.getId().getCouleur() == _c) {
                mainTriee_.ajouter(c2_);
            }
        }
        return mainTriee_;
    }

    private void cstAnnonce(CustList<DeclareHandBelote> _annoncesPossibles, DeclaresBelote _a, HandBelote _deck) {
        HandBelote cartesMemeHauteur_ = new HandBelote();
        if(_a.getFigure() != CardChar.UNDEFINED) {
            for(CardBelote c: _deck) {
                if(c.getId().getNomFigure() == _a.getFigure()) {
                    cartesMemeHauteur_.ajouter(c);
                }
            }
        }else {
            for(CardBelote c: _deck) {
                if(c.getId().getValeur() == _a.getValeur()) {
                    cartesMemeHauteur_.ajouter(c);
                }
            }
        }
        if(cards.containsAllObj(cartesMemeHauteur_.cards)) {
            DeclareHandBelote annonceLoc_ = new DeclareHandBelote();
            annonceLoc_.setHand(cartesMemeHauteur_);
            annonceLoc_.setDeclare(_a);
            _annoncesPossibles.add(annonceLoc_);
        }
    }

//    public void setOrdre(Order _pordre) {
//        order=_pordre;
//    }
//    void trierUnicolore(boolean _decroissant) {
//        trierUnicolore(_decroissant,order);
//    }
    void trierUnicolore(boolean _decroissant, BidBeloteSuit _pordre) {
        if(estVide()) {
            return;
        }
        Suit couleur_ = premiereCarte().getId().getCouleur();
        sortList(_decroissant,couleur_, cards,order(_pordre,couleur_));
    }

    public static void sortList(boolean _decroissant, Suit _couleur, CustList<CardBelote> _list, Order _order) {
        _list.sortElts(new GameStrengthCardBeloteComparator(tr(_order, _couleur), _couleur, _decroissant));
    }

    private static Suit tr(Order _ord, Suit _suit) {
        if(_ord==Order.TRUMP) {
            return _suit;
        }else {
            return Suit.UNDEFINED;
        }
    }


    public void trier(IdList<Suit> _couleurs, boolean _decroissant,Order _ordreTri) {
//        IdList<CardBelote> nouvelleMain_ = new IdList<CardBelote>();
        BidBeloteSuit b_ = build(_ordreTri);
//        for(Suit s_: _couleurs) {
//            HandBelote mainCouleur_ = couleur(s_);
//            mainCouleur_.trierUnicolore(_decroissant,b_);
//            nouvelleMain_.addAllElts(mainCouleur_.cards);
//        }
//        cards.clear();
//        cards.addAllElts(nouvelleMain_);
        trier(_couleurs,_decroissant,b_);
    }

    public HandBelote couleur(Suit _s) {
        HandBelote mainCouleur_ = new HandBelote();
        for(CardBelote carte_: cards) {
            if(carte_.getId().getCouleur() == _s) {
                mainCouleur_.ajouter(carte_);
            }
        }
        return mainCouleur_;
    }

    public void trier(DisplayingBelote _dis,BidBeloteSuit _couleurAtout) {
//        if (_couleurAtout.getCouleurDominante()) {
//            trier(_dis.getDisplaying().getSuits(), _dis.getDisplaying().isDecreasing(), _couleurAtout.getSuit());
//        } else {
//            trier(_dis.getDisplaying().getSuits(), _dis.getDisplaying().isDecreasing(), _couleurAtout.getOrdre());
//        }
        trier(_dis.getDisplaying().getSuits(), _dis.getDisplaying().isDecreasing(), _couleurAtout);
    }
    /**Appelee apres un contrat couleur ou autre couleur*/
    public void trier(IdList<Suit> _couleurs, boolean _decroissant,Suit _couleurAtout) {
//        order=Order.SUIT;
//        IdList<CardBelote> nouvelleMain_ = new IdList<CardBelote>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(_couleurAtout);
//        for(Suit couleur_: _couleurs) {
//            HandBelote mainCouleur_ = couleur(couleur_);
//            HandBelote mainCouleur_ = new HandBelote();
//            for(CardBelote carte_: cards) {
//                if(carte_.getId().getCouleur() == couleur_) {
//                    mainCouleur_.ajouter(carte_);
//                }
//            }
//            Order o_;
//            if(couleur_ == _couleurAtout) {
//                o_ = Order.TRUMP;
//            }else {
//                o_ = Order.SUIT;
//            }
//            mainCouleur_.trierUnicolore(_decroissant,bid_);
//            nouvelleMain_.addAllElts(mainCouleur_.cards);
//        }
//
//
//
//        cards.clear();
//        cards.addAllElts(nouvelleMain_);
        trier(_couleurs,_decroissant,bid_);
        //On trie_ les_ cartes_ d'atout_
    }
    public void trier(IdList<Suit> _couleurs, boolean _decroissant,BidBeloteSuit _couleurAtout) {
        IdList<CardBelote> nouvelleMain_ = new IdList<CardBelote>();
        for(Suit couleur_: _couleurs) {
            HandBelote mainCouleur_ = couleur(couleur_);
            mainCouleur_.trierUnicolore(_decroissant,_couleurAtout);
            nouvelleMain_.addAllElts(mainCouleur_.cards);
        }



        cards.clear();
        cards.addAllElts(nouvelleMain_);
    }

    int nombreCartesPoints(BidBeloteSuit _contrat) {
        return cartesPoints(_contrat).total();
    }

    HandBelote cartesPoints(BidBeloteSuit _contrat) {
        HandBelote h_ = new HandBelote();
        for(CardBelote carte_:cards) {
            if(carte_.points(_contrat)>0) {
                h_.ajouter(carte_);
            }
        }
        return h_;
    }

    HandBelote cartesNonPoints(BidBeloteSuit _contrat) {
        HandBelote h_ = new HandBelote();
        for(CardBelote carte_:cards) {
            if(carte_.points(_contrat) == 0) {
                h_.ajouter(carte_);
            }
        }
        return h_;
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
                break;
            }
        }
        return id_;
    }

    public IdList<CardBelote> getCards() {
        return cards;
    }
    public void setCards(IdList<CardBelote> _cards) {
        cards = _cards;
    }

    public void removeCardIfPresent(CardBelote _c) {
        cards.removeAllObj(_c);
    }
}
