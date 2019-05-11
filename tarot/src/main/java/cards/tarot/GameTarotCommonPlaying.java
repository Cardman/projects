package cards.tarot;

import cards.consts.Suit;
import cards.tarot.comparators.GameTarotLeastDemandedSuitComparator;
import cards.tarot.comparators.GameTarotMostDemandedSuitComparator;
import cards.tarot.enumerations.CardTarot;
import code.maths.Rate;
import code.util.*;

public final class GameTarotCommonPlaying {
    private GameTarotCommonPlaying(){
    }

    /**
     Retourne vrai si et seulement si le joueur peut ramasser tous les atouts
     sans en perdre
     @param _cartesPossibles
     les cartes probablement possedees par les autres joueurs
     @param _numero
     le numero du joueur concerne
     @param _suites
     l'ensemble des suites d'atout du joueur concerne
     @param _cartesJouees
     l'ensemble de toutes les cartes jouees reparties dans toutes
     les couleurs
     */
    static boolean strictMaitreAtout(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EqList<HandTarot> _excusePossible, byte _numero,
            EqList<HandTarot> _suites, HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Suit couleurAtout_ = Suit.TRUMP;
        int nbTotalAtouts_ = HandTarot.atoutsSansExcuse().total();
        int max_ = CustList.SIZE_EMPTY;
        /*
        max designe le nombre maximal de cartes probablement
        possedees par un joueur
        */
        EqList<HandTarot> possibleTrumps_ = _cartesPossibles.getVal(couleurAtout_);
        int nbPlayers_ = possibleTrumps_.size() - 1;
        for (int joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers_; joueur_++) {
            // La taille de atoutsPossibles
            // correspond au nombre de joueurs+1
            if (joueur_ == _numero) {
                continue;
            }
            if (possibleTrumps_.get(joueur_).total()
                    + _excusePossible.get(joueur_).total() <= max_) {
                continue;
            }
            max_ = possibleTrumps_.get(joueur_).total()
                    + _excusePossible.get(joueur_).total();
        }
        /*
        Fin for int joueur=0;joueur<cartesPossibles.get(couleurAtout()).size()-1;joueur++
        (Fin boucle sur le calcul de la valeur maximale possible des atouts
        */
        if (max_ == CustList.SIZE_EMPTY) {
            /*
        S'il est impossible que les autres joueurs aient de
        l'atout (Excuse incluse)
        */
            return true;
        }
        if (cartesJouees_.getVal(couleurAtout_).total() == nbTotalAtouts_) {
            return true;
        }
        if (_suites.isEmpty()) {
            return false;
        }
        boolean existeAtoutMaitre_ = true;
        CardTarot atoutPetitSuiteHaute_ = _suites.first()
                .premiereCarte();
        for (CardTarot carte_ : HandTarot.atoutsAuDessusDe(atoutPetitSuiteHaute_)) {
            if (cartesJouees_.getVal(couleurAtout_).contient(carte_)) {
                continue;
            }
            if (_suites.first().contient(carte_)) {
                continue;
            }
            existeAtoutMaitre_ = false;
            break;
        }
        return existeAtoutMaitre_ && _suites.first().total() >= max_;
    }

    /**
     Est vrai si et seulement si le nombre
     2 x nb_atout_maitres_joueur+3 x nb_atout_non_maitres_joueur/2+atouts_jouees
     depasse strictement 21
     */
    static boolean maitreAtout(EqList<HandTarot> _suites,
                                       HandTarot _cartesJouees, boolean _excuseJouee, boolean _excuse) {
        int maitres_;
        int nonmaitres_;
        int nb_;
        CardTarot c;
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Suit couleurAtout_ = Suit.TRUMP;
        int nbTotalAtouts_ = HandTarot.atoutsSansExcuse().total();
        if (cartesJouees_.getVal(couleurAtout_).total() == nbTotalAtouts_) {
            return true;
        }
        if (_suites.isEmpty()) {
            return false;
        }
        boolean existeAtoutMaitre_ = true;
        c = _suites.first().premiereCarte();
        for (CardTarot carte_ : HandTarot.atoutsAuDessusDe(c)) {
            if (cartesJouees_.getVal(couleurAtout_).contient(carte_)) {
                continue;
            }
            if (_suites.first().contient(carte_)) {
                continue;
            }
            existeAtoutMaitre_ = false;
            break;
        }
        if (existeAtoutMaitre_) {
            maitres_ = _suites.first().total();
            nonmaitres_ = 0;
        } else {
            maitres_ = 0;
            nonmaitres_ = _suites.first().total();
        }
        int nbSeqs_ = _suites.size();
        for (int suite_ = CustList.SECOND_INDEX; suite_ < nbSeqs_; suite_++) {
            nonmaitres_ += _suites.get(suite_).total();
        }
        if (_suites.last().contient(CardTarot.petit())) {
            nonmaitres_--;
        }
        nb_ = 2 * maitres_ + 3 * nonmaitres_ / 2
                + cartesJouees_.getVal(couleurAtout_).total();
        if (_excuseJouee) {
            nb_++;
        }
        if (_suites.last().contient(CardTarot.petit())) {
            nb_++;
        }
        if (_excuse) {
            nb_++;
        }
        return nb_ > nbTotalAtouts_;
    }
    static EnumList<Suit> coupesFranchesStrictes(
            CustList<TrickTarot> _plisFaits, EnumMap<Suit,HandTarot> _repartitionCouleur, byte _numero) {
        EnumList<Suit> coupesFranchesStrictes_ = new EnumList<Suit>();
        for (Suit c: Suit.couleursOrdinaires()) {
            if (!_repartitionCouleur.getVal(c).estVide()) {
                continue;
            }
            boolean coupeToujours_ = true;
            for (TrickTarot pli_ : _plisFaits) {
                if (!pli_.getVuParToutJoueur()) {
                    continue;
                }
                if (pli_.couleurDemandee() != c) {
                    continue;
                }
                if (!(pli_.carteDuJoueur(_numero).couleur() == Suit.TRUMP)) {
                    coupeToujours_ = false;
                }
            }
            if (!coupeToujours_) {
                continue;
            }
            coupesFranchesStrictes_.add(c);
        }
        return coupesFranchesStrictes_;
    }
    static HandTarot cartesNonMaitresses(EnumMap<Suit,HandTarot> _couleurs,
                                         EnumMap<Suit,HandTarot> _cartesMaitresses,
                                         EnumMap<Suit,EqList<HandTarot>> _suites) {
        HandTarot cards_ = new HandTarot();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot cartesCouleurMaitresse_ = _cartesMaitresses
                    .getVal(couleur_);
            if (!_couleurs.getVal(couleur_).estVide()) {
                EqList<HandTarot> seqs_ = _suites.getVal(couleur_);
                if (cartesCouleurMaitresse_.estVide()) {
                    cards_.ajouterCartes(seqs_.first());
                }
                int nbSeqs_ = seqs_.size();
                for (byte indiceSuite_ = CustList.SECOND_INDEX; indiceSuite_ < nbSeqs_; indiceSuite_++) {
                    cards_.ajouterCartes(seqs_.get(indiceSuite_));
                }
            }
        }
        return cards_;
    }
    static Rate moyenneAtout(HandTarot _main, HandTarot _atoutsJoues,
                             EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _nombreJoueurs) {
        EqList<HandTarot> repartitionAtout_ = _cartesPossibles
                .getVal(Suit.TRUMP);
        byte nombreDefausses_ = 0;
        for (HandTarot main_ : repartitionAtout_) {
            if (!main_.estVide()) {
                continue;
            }
            nombreDefausses_++;
        }
        HandTarot atoutsNonJoues_ = new HandTarot();
        byte nombreJoueursAvecAtout_ = (byte) (_nombreJoueurs
                - nombreDefausses_ - 1);
        if (nombreJoueursAvecAtout_ == 0) {
            return Rate.zero();
        }
        for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
            if (_main.contient(atout_)) {
                continue;
            }
            if (!_atoutsJoues.contient(atout_)) {
                atoutsNonJoues_.ajouter(atout_);
            }
        }
        return new Rate(atoutsNonJoues_.total(), nombreJoueursAvecAtout_);
    }
    public static HandTarot atoutsPoignee(EnumMap<Suit,HandTarot> _repartition) {
        HandTarot m = new HandTarot();
        m.ajouterCartes(_repartition.getVal(Suit.TRUMP));
        if (!_repartition.getVal(CardTarot.excuse().couleur()).estVide()) {
            m.ajouter(CardTarot.excuse(), 0);
        }
        return m;
    }
    static Numbers<Byte> tours(Suit _couleur, CustList<TrickTarot> _plisFaits) {
        Numbers<Byte> tricksNumbers_ = new Numbers<Byte>();
        byte key_ = 0;
        for (TrickTarot pli_ : _plisFaits) {
            if (!pli_.getVuParToutJoueur()) {
                key_++;
                continue;
            }
            if (pli_.couleurDemandee() != _couleur) {
                key_++;
                continue;
            }
            tricksNumbers_.add(key_);
            key_++;
        }
        return tricksNumbers_;
    }
    public static byte ramasseur(CustList<TrickTarot> _plisFaits, byte _numeroPli) {
        return _plisFaits.get(_numeroPli).getRamasseur();
    }
    static Numbers<Byte> ramasseurs(CustList<TrickTarot> _plisFaits) {
        Numbers<Byte> ramasseurs_ = new Numbers<Byte>();
        for(TrickTarot pli_: _plisFaits) {
            if(!pli_.getVuParToutJoueur()) {
                continue;
            }
            byte ramasseur_ = pli_.getRamasseur();
            if(ramasseurs_.containsObj(ramasseur_)) {
                continue;
            }
            ramasseurs_.add(ramasseur_);
        }
        return ramasseurs_;
    }
    static EnumList<Suit> couleursLesPlusEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return couleursTrieesPlusEntameesParJoueurs(_plisFaits, _joueurs, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameTarotMostDemandedSuitComparator(_plisFaits, _joueurs));
    }

    static EnumList<Suit> couleursLesMoinsEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return couleursTrieesMoinsEntameesParJoueurs(_plisFaits, _joueurs, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesMoinsEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameTarotLeastDemandedSuitComparator(_plisFaits, _joueurs));
    }
}
