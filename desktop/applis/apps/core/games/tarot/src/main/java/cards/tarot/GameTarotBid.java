package cards.tarot;

import cards.consts.CardChar;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.PlayingDog;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class GameTarotBid {
    private final HandTarot currentHand;
    private final RulesTarot rules;
    private final EnumList<BidTarot> bids;
    private final BidTarot bid;

    public GameTarotBid(HandTarot _currentHand, RulesTarot _rules,
                        EnumList<BidTarot> _bids, BidTarot _bid) {
        currentHand = _currentHand;
        rules = _rules;
        bids = _bids;
        bid = _bid;
    }

    public BidTarot strategieContrat() {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        EnumMap<Suit,HandTarot> couleurs_ = currentHand.couleurs();
        int atouts_ = couleurs_.getVal(CardTarot.excuse().getId().getCouleur()).total() + couleurs_.getVal(Suit.TRUMP).total();
        boolean chelem_ = estUnJeuDeChelem(couleurs_, new HandTarot().couleurs(), rules, cartesAppeler());
        EnumList<BidTarot> bidTarotRule_ = allowedBids();
        if (chelem_) {
            return bidTarotRule_.last();
        }
        Suit couleurAtout_ = Suit.TRUMP;
        HandTarot trumps_ = couleurs_.getVal(couleurAtout_);
        CustList<HandTarot> suitesAtouts_ = trumps_.eclaterDebutPartie();
        int nbAtoutsMajeursConsecutifs_ = 0;
        HandTarot bouts_ = getOulderInHand(couleurs_);
        for (HandTarot main_ : suitesAtouts_) {
            if (main_.total() > 1 && main_.premiereCarte().getId().getValeur() > 14) {
                nbAtoutsMajeursConsecutifs_ += main_.total();
            }
        }
        int valeurAtout_ = valeur(atouts_, NumberUtil.wrapIntArray(3, 4), NumberUtil.wrapIntArray(4, 5), NumberUtil.wrapIntArray(4, 5), NumberUtil.wrapIntArray(5, 7));
        int valeurAtoutMajeur_ = valeur(atouts_, NumberUtil.wrapIntArray(2, 3), NumberUtil.wrapIntArray(3, 4), NumberUtil.wrapIntArray(3, 4), NumberUtil.wrapIntArray(4, 7));
        int valeurAtoutMoyen_ = valeur(atouts_, NumberUtil.wrapIntArray(1, 2), NumberUtil.wrapIntArray(2, 3), NumberUtil.wrapIntArray(2, 3), NumberUtil.wrapIntArray(3, 3));
        int valeurVingtEtUnSeul_ = valeur(atouts_, NumberUtil.wrapIntArray(4, 6), NumberUtil.wrapIntArray(6, 8), NumberUtil.wrapIntArray(6, 8), NumberUtil.wrapIntArray(8, 10));
        int valeurExcuseSeule_ = valeur(atouts_, NumberUtil.wrapIntArray(2, 3), NumberUtil.wrapIntArray(3, 4), NumberUtil.wrapIntArray(3, 4), NumberUtil.wrapIntArray(5, 7));
        int valeurVingtEtUnExcuse_ = valeur(atouts_, NumberUtil.wrapIntArray(10, 14), NumberUtil.wrapIntArray(12, 16), NumberUtil.wrapIntArray(12, 16), NumberUtil.wrapIntArray(16, 21));
        int valeurLongue_ = valeur(atouts_, NumberUtil.wrapIntArray(3, 6), NumberUtil.wrapIntArray(4, 8), NumberUtil.wrapIntArray(4, 8), NumberUtil.wrapIntArray(5, 11));
        int valeurCoupe_ = valeur(atouts_, NumberUtil.wrapIntArray(1, 2), NumberUtil.wrapIntArray(2, 4), NumberUtil.wrapIntArray(2, 4), NumberUtil.wrapIntArray(3, 7));
        int coeff_ = valeur(atouts_, NumberUtil.wrapIntArray(1,2), NumberUtil.wrapIntArray(2,4), NumberUtil.wrapIntArray(2,4), NumberUtil.wrapIntArray(3,6));
        int total_ = nbAtoutsMajeursConsecutifs_ * coeff_;
        int midSup_ = sum(trumps_, suitesAtouts_, valeurAtoutMajeur_, valeurAtoutMoyen_);
        total_ += valeurAtout_ * atouts_ + midSup_;
        int valeurPetitBout_;
        if (nombreJoueurs_ == 3) {
            valeurPetitBout_ = valeurPetitBout(atouts_,7,12,0,2,4);
        } else if (nombreJoueurs_ == 4) {
            valeurPetitBout_ = valeurPetitBout(atouts_,6,9,0,3,6);
        } else if (nombreJoueurs_ == 5) {
            valeurPetitBout_ = valeurPetitBout(atouts_,5,7,0,3,6);
        } else {
            /* 6 joueurs */
            valeurPetitBout_ = valeurPetitBout(atouts_,4,6,0,5,10);
        }
        if (atouts_ == 0) {
            valeurCoupe_ = 0;
            valeurLongue_ = 0;
        }
        if (bouts_.contient(CardTarot.petit())) {
            total_ += valeurPetitBout_;
        }
        if (bouts_.contient(CardTarot.excuse())
                && bouts_.contient(CardTarot.vingtEtUn())) {
            total_ += valeurVingtEtUnExcuse_;
        } else if (bouts_.contient(CardTarot.excuse())) {
            total_ += valeurExcuseSeule_;
        } else if (bouts_.contient(CardTarot.vingtEtUn())) {
            total_ += valeurVingtEtUnSeul_;
        }
        total_ += normalSuits(atouts_,couleurs_,valeurLongue_,valeurCoupe_);
        return end(atouts_, total_);
    }

    private BidTarot end(int _atouts, int _total) {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        int petite_;
        int garde_;
        if (nombreJoueurs_ == DealingTarot.DEAL_1_VS_2.getId().getNombreJoueurs()) {
            petite_ = 90;
            garde_ = 140;
        } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getId().getNombreJoueurs()) {
            if (rules.getDealing().getAppel() == CallingCard.WITHOUT) {
                petite_ = 90;
                garde_ = 160;
            } else if (rules.getDealing().getAppel() == CallingCard.DEFINED) {
                petite_ = 50;
                garde_ = 90;
            } else {
                petite_ = 60;
                garde_ = 110;
            }
        } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getId().getNombreJoueurs()) {
            if (rules.getDealing().getAppel() == CallingCard.DEFINED) {
                petite_ = 50;
                garde_ = 90;
            } else {
                petite_ = 90;
                garde_ = 150;
            }
        } else {
            if (rules.getDealing() == DealingTarot.DEAL_1_VS_4) {
                petite_ = 80;
                garde_ = 130;
            } else {
                petite_ = 60;
                garde_ = 100;
            }
        }
        return end(_atouts, _total, petite_, garde_);
    }

    private BidTarot end(int _atouts, int _total, int _petite, int _garde) {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        boolean sansAppel_ = rules.getDealing().getAppel() == CallingCard.DEFINED
                || rules.getDealing().getAppel() == CallingCard.WITHOUT;
        int limTr_ = lim(12, 9, 7, 6);
        if (_atouts >= limTr_ && _total >= _garde) {
            BidTarot f_ = tryGetStrongBid(sansAppel_);
            if (f_.isJouerDonne()) {
                return f_;
            }
        }
        BidTarot c_;
        if (_total < _petite) {
            c_ = BidTarot.FOLD;
        } else if (_total < _garde && contratAccepte(BidTarot.TAKE)) {
            c_ = BidTarot.TAKE;
        } else {
            c_ = BidTarot.GUARD;
        }
        if (c_.estDemandable(bid)) {
            if (c_ == BidTarot.TAKE && bids.size() == nombreJoueurs_ - 1) {
                c_ = BidTarot.GUARD;
            }
            return c_;
        }
        return BidTarot.FOLD;
    }

    private int normalSuits(int _atouts, EnumMap<Suit, HandTarot> _couleurs, int _valeurLongue, int _valeurCoupe) {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        int nombreLimiteLongue_;
        int totalCouleur_=0;
        for(Suit c: Suit.couleursOrdinaires()) {
            totalCouleur_+= HandTarot.couleurComplete(c).total();
        }
        totalCouleur_ /= Suit.couleursOrdinaires().size();
        nombreLimiteLongue_ = totalCouleur_ / nombreJoueurs_ + 2;
        int valeurRoiSeul_ = valeur(_atouts, NumberUtil.wrapIntArray(6, 8), NumberUtil.wrapIntArray(5, 6), NumberUtil.wrapIntArray(5, 6), NumberUtil.wrapIntArray(4, 5));
        int valeurDameSeul_ = valeur(_atouts, NumberUtil.wrapIntArray(4, 7), NumberUtil.wrapIntArray(3, 4), NumberUtil.wrapIntArray(3, 4), NumberUtil.wrapIntArray(2, 3));
        int valeurMariageRoiDame_ = valeur(_atouts, NumberUtil.wrapIntArray(10, 18), NumberUtil.wrapIntArray(8, 12), NumberUtil.wrapIntArray(8, 12), NumberUtil.wrapIntArray(6, 9));
        int valeurCavalier_ = valeur(_atouts, NumberUtil.wrapIntArray(3, 3), NumberUtil.wrapIntArray(2, 2), NumberUtil.wrapIntArray(2, 2), NumberUtil.wrapIntArray(2, 2));
        int valeurValet_ = valeur(_atouts, NumberUtil.wrapIntArray(1, 2), NumberUtil.wrapIntArray(1, 1), NumberUtil.wrapIntArray(1, 1), NumberUtil.wrapIntArray(1, 1));
        int total_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot mt_ = _couleurs.getVal(couleur_);
            int roi_ = mt_.tailleRois();
            int dame_ = mt_.tailleDames();
            int cavalier_ = mt_.tailleCavaliers();
            int valet_ = mt_.tailleValets();
            if (roi_ + dame_ == 2) {
                total_ += valeurMariageRoiDame_;
            } else if (roi_ == 1) {
                total_ += valeurRoiSeul_;
            } else if (dame_ == 1) {
                total_ += valeurDameSeul_;
            }
            total_ += cavalier_ * valeurCavalier_;
            total_ += valet_ * valeurValet_;
            if (mt_.total() >= nombreLimiteLongue_) {
                total_ += _valeurLongue;
            }
            if (mt_.estVide()) {
                total_ += _valeurCoupe;
            }
        }
        return total_;
    }

    private int sum(HandTarot _trumps, CustList<HandTarot> _suitesAtouts, int _valeurAtoutMajeur, int _valeurAtoutMoyen) {
        int nbTrumps_ = _trumps.total();
        int nbAtoutsQuinzeAuVingtEtUn_ = 0;
        int nbAtoutsSeptAuQuatorze_ = 0;
        for (int indiceCarte_ = IndexConstants.FIRST_INDEX; indiceCarte_ < nbTrumps_; indiceCarte_++) {
            if (_trumps.carte(indiceCarte_).getId().getValeur() > 14) {
                nbAtoutsQuinzeAuVingtEtUn_++;
            } else if (_trumps.carte(indiceCarte_).getId().getValeur() > 6) {
                boolean continuer_ = incr(_trumps.carte(
                        indiceCarte_), _suitesAtouts,14);
                if (continuer_) {
                    nbAtoutsQuinzeAuVingtEtUn_++;
                } else {
                    nbAtoutsSeptAuQuatorze_++;
                }
            } else {
                boolean continuer_ = incr(_trumps.carte(
                        indiceCarte_), _suitesAtouts,14);
                if (continuer_) {
                    nbAtoutsQuinzeAuVingtEtUn_++;
                    continue;
                }
                if (incr(_trumps.carte(
                        indiceCarte_), _suitesAtouts,6)) {
                    nbAtoutsSeptAuQuatorze_++;
                }
            }
        }
        return _valeurAtoutMajeur * nbAtoutsQuinzeAuVingtEtUn_
                + _valeurAtoutMoyen * nbAtoutsSeptAuQuatorze_;
    }

    private int lim(int _one, int _two, int _three, int _four) {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        if (nombreJoueurs_ == 3) {
            return _one;
        } else if (nombreJoueurs_ == 4){
            return _two;
        } else if (nombreJoueurs_ == 5) {
            return _three;
        } else {
            /* 6 joueurs */
            return _four;
        }
    }
    private int valeurSing(int _atouts, int _one, int _two) {
        if (_atouts <= lim(12, 9, 7, 6)) {
            return _one;
        }
        return _two;
    }
    private int valeur(int _atouts, int[] _one, int[] _two, int[] _three, int[] _four) {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        if (nombreJoueurs_ == 3) {
            return valeurSing(_atouts,_one[0],_one[1]);
        }
        if (nombreJoueurs_ == 4) {
            return valeurSing(_atouts,_two[0],_two[1]);
        }
        if (nombreJoueurs_ == 5) {
            return valeurSing(_atouts,_three[0],_three[1]);
        }
        /* 6 joueurs */
        return valeurSing(_atouts,_four[0],_four[1]);
    }
    private int valeurPetitBout(int _atouts,int _fbound, int _sbound, int _fvalue, int _svalue, int _tvalue) {
        if (_atouts <= _fbound) {
            return _fvalue;
        } else if (_atouts <= _sbound) {
            return _svalue;
        } else {
            return _tvalue;
        }
    }
    static boolean incr(CardTarot _c, CustList<HandTarot> _l, int _lim) {
        for (HandTarot main_ : _l) {
            if (main_.premiereCarte().getId().getValeur() <= _lim) {
                break;
            }
            if (main_.contient(_c)) {
                return true;
            }
        }
        return false;
    }
    static boolean estUnJeuDeChelem(EnumMap<Suit, HandTarot> _couleurs,
                                    EnumMap<Suit, HandTarot> _cartesJouees,
                                    RulesTarot _infos, HandTarot _cartesAppeler) {
        if (estUnJeuDeChelemSur(_couleurs,_cartesJouees)) {
            return true;
        }
        byte nbPlayers_ = (byte) _infos.getDealing().getId().getNombreJoueurs();
        if (!maitreAtoutPourChelem(_couleurs,nbPlayers_)) {
            return false;
        }
        byte nombreCouleursLargMait_ = nbCouleursLargementMaitresses(
                _couleurs, nbPlayers_);
        if (nbPlayers_ == DealingTarot.DEAL_1_VS_2.getId().getNombreJoueurs()) {
            return nombreCouleursLargMait_ == Suit.couleursOrdinaires().size();
        }
        byte nombreCouleursPseuMait_ = nbCouleursPseudoMaitresses(_couleurs,
                _cartesAppeler,
                nbPlayers_);
        boolean avecAppel_ = _infos.getDealing().getAppel() == CallingCard.KING;
        if (_infos.getDealing().getAppel() == CallingCard.CHARACTER_CARD) {
            avecAppel_ = true;
        }
        if (avecAppel_) {
            return nombreCouleursPseuMait_ == 1
                    && nombreCouleursLargMait_ == Suit.couleursOrdinaires().size() - 1 || nombreCouleursLargMait_ == Suit.couleursOrdinaires().size();
        }
        return nombreCouleursLargMait_ == Suit.couleursOrdinaires().size();
    }
    static boolean maitreAtoutPourChelem(EnumMap<Suit,HandTarot> _couleurs,
                                         byte _joueurs) {
        byte atouts_ = (byte) (_couleurs.getVal(CardTarot.excuse().getId().getCouleur()).total() + _couleurs.getVal(Suit.TRUMP).total());
        byte atoutsMaitres_ = nbAtoutsMaitres(_couleurs);
        int fr_ = 1;
        int to_ = 6;
        int nb_ = 14;
        if (_joueurs == DealingTarot.DEAL_1_VS_2.getId().getNombreJoueurs()) {
            fr_ = 0;
            to_ = 7;
            nb_ = 20;
        } else if (_joueurs == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getId().getNombreJoueurs()) {
            fr_ = 2;
        } else if (_joueurs == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getId().getNombreJoueurs()) {
            nb_ = 17;
        }
        for (int i = fr_; i < to_; i++) {
            if (atouts_ > nb_ - 2 * i) {
                return atoutsMaitres_ > i;
            }
        }
        return false;
    }

    BidTarot tryGetStrongBid(boolean _withoutCall) {
        EnumMap<Suit,HandTarot> couleurs_ = currentHand.couleurs();
        HandTarot bouts_ = getOulderInHand(couleurs_);
        byte nombreJoueurs_ = getNombreDeJoueurs();
        int nbCouleurs_ = Suit.couleursOrdinaires().size();
        if (_withoutCall) {
            if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs()) == nbCouleurs_) {
                BidTarot e_ = getStrongBid(bouts_);
                if(e_.isJouerDonne()) {
                    return e_;
                }
            }
        } else if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs())
                + nbCouleursPseudoMaitresses(couleurs_, cartesAppeler(), nombreJoueurs_) == nbCouleurs_) {
            BidTarot e_ = getStrongBid(bouts_);
            if(e_.isJouerDonne()) {
                return e_;
            }
        }
        return BidTarot.FOLD;
    }
    BidTarot getStrongBid(HandTarot _bouts) {
        BidTarot e_ = BidTarot.FOLD;
        PlayingDog jeuChien_ = PlayingDog.WITHOUT;
        if (_bouts.total() >= 2 || _bouts.contient(CardTarot.vingtEtUn())) {
            jeuChien_ = PlayingDog.AGAINST;
        }
        for(BidTarot e: allowedBids()) {
            if(e.getJeuChien() != jeuChien_) {
                continue;
            }
            e_ = e;
        }
        if (e_.estDemandable(bid)) {
            return e_;
        }
        return BidTarot.FOLD;
    }
    static HandTarot getOulderInHand(EnumMap<Suit, HandTarot> _couleurs) {
        HandTarot trumps_ = _couleurs.getVal(Suit.TRUMP);
        HandTarot bouts_ = new HandTarot();
        if (!_couleurs.getVal(CardTarot.EXCUSE.getId().getCouleur()).estVide()) {
            bouts_.ajouter(CardTarot.excuse());
        }
        if (trumps_.contient(CardTarot.vingtEtUn())) {
            bouts_.ajouter(CardTarot.vingtEtUn());
        }
        if (trumps_.contient(CardTarot.petit())) {
            bouts_.ajouter(CardTarot.petit());
        }
        return bouts_;
    }
    public EnumList<BidTarot> allowedBids() {
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        for (BidTarot b: BidTarot.getValidBids()) {
            if (!contratAccepte(b)) {
                continue;
            }
            bids_.add(b);
        }
        return bids_;
    }

    static boolean estUnJeuDeChelemSur(EnumMap<Suit,HandTarot> _couleurs, EnumMap<Suit,HandTarot> _cartesJouees) {
        int nbTr_ = nbAtoutsMaitres(_couleurs) + _cartesJouees.getVal(Suit.TRUMP).total() + _couleurs.getVal(Suit.TRUMP).total();
        int nbFullTr_ = HandTarot.atoutsSansExcuse().total() + _couleurs.getVal(CardTarot.excuse().getId().getCouleur()).total();
        if (nbTr_ >= nbFullTr_) {
            return nbCouleursMaitresses(_couleurs, _cartesJouees) == Suit.couleursOrdinaires().size();
        }
        return false;
    }

    static byte nbAtoutsMaitres(EnumMap<Suit,HandTarot> _repartition) {
        return (byte) _repartition.getVal(Suit.TRUMP).atoutsMaitres(new HandTarot().couleurs()).total();
    }

    static byte nbCouleursMaitresses(EnumMap<Suit,HandTarot> _couleurs, EnumMap<Suit,HandTarot> _cartesJouees) {
        byte nb_ = 0;
        for (Suit b : Suit.couleursOrdinaires()) {
            if (maitreDansUneCouleur(_couleurs, _cartesJouees, b)) {
                nb_++;
            }
        }
        return nb_;
    }

    static byte nbCouleursPseudoMaitresses(EnumMap<Suit,HandTarot> _couleurs,
                                                   HandTarot _cartesAppeler,
                                                   byte _nombreJoueurs) {
        byte nb_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if (pseudoMaitreDansUneCouleurContrat(_couleurs, couleur_,_cartesAppeler, _nombreJoueurs)) {
                nb_++;
            }
        }
        return nb_;
    }

    static byte nbCouleursLargementMaitresses(
            EnumMap<Suit,HandTarot> _couleurs, byte _nombreJoueurs) {
        byte nb_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if (largementMaitreDansUneCouleurAuDebut(_couleurs, couleur_, _nombreJoueurs)) {
                nb_++;
            }
        }
        return nb_;
    }

    static boolean pseudoMaitreDansUneCouleurContrat(
            EnumMap<Suit,HandTarot> _couleurs, Suit _noCouleur, HandTarot _cartesAppeler, byte _nombreJoueurs) {
        if (largementMaitreDansUneCouleurAuDebut(_couleurs, _noCouleur, _nombreJoueurs)) {
            return false;
        }
        return pseudoMaitreDansUneCouleur(_couleurs, _cartesAppeler, _noCouleur);
    }

    static boolean largementMaitreDansUneCouleurAuDebut(
            EnumMap<Suit,HandTarot> _couleurs, Suit _noCouleur, byte _nombreJoueurs) {
        if (maitreDansUneCouleur(_couleurs, new HandTarot().couleurs(), _noCouleur)) {
            return true;
        }
        return nbCartesMaitresses(_couleurs, new HandTarot().couleurs(), _noCouleur) > 8 - _nombreJoueurs;
    }

    static boolean pseudoMaitreDansUneCouleur(
            EnumMap<Suit,HandTarot> _couleurs, HandTarot _cartesAppeler, Suit _noCouleur) {
        return couleursPseudosMaitres(_couleurs, cartesPseudoMaitresses(_couleurs, _cartesAppeler, new HandTarot().couleurs())).containsObj(_noCouleur);
    }

    static boolean maitreDansUneCouleur(EnumMap<Suit,HandTarot> _couleurs,
                                                EnumMap<Suit,HandTarot> _cartesJouees,
                                                Suit _noCouleur) {
        int nombreCartesMaitresses_ = nbCartesMaitresses(_couleurs, _cartesJouees, _noCouleur);
        if (nombreCartesMaitresses_ == _couleurs.getVal(_noCouleur).total()) {
            return true;
        }
        byte totalCouleur_ = (byte) HandTarot.couleurComplete(_noCouleur).total();
        if (nombreCartesMaitresses_ >= totalCouleur_/2) {
            return true;
        }
        int nb_ = nombreCartesMaitresses_ + _couleurs.getVal(_noCouleur).total();
        return nb_ >= totalCouleur_;
    }

    static int nbCartesMaitresses(EnumMap<Suit,HandTarot> _couleurs,
                                  EnumMap<Suit,HandTarot> _cartesJouees,
                                  Suit _noCouleur) {
        HandTarot couleur_ = _couleurs.getVal(_noCouleur);
        CustList<HandTarot> suites_ = couleur_.eclaterEnCours(_cartesJouees, _noCouleur);
        HandTarot cartesJoueesOuPossedes_ = new HandTarot();
        cartesJoueesOuPossedes_.ajouterCartes(couleur_);
        cartesJoueesOuPossedes_.ajouterCartes(_cartesJouees.getVal(_noCouleur));
        cartesJoueesOuPossedes_.trierParForceEnCours(_noCouleur);
        HandTarot couleurComplete_ = HandTarot.couleurComplete(_noCouleur);
        couleurComplete_.trierParForceEnCours(_noCouleur);
        for(CardTarot carte_: couleurComplete_) {
            if(!cartesJoueesOuPossedes_.contient(carte_)) {
                return 0;
            }
            if(couleur_.contient(carte_)) {
                break;
            }
        }
        if (suites_.isEmpty()) {
            return 0;
        }
        return suites_.first().total();
    }
    static EnumMap<Suit,HandTarot> cartesPseudoMaitresses(
            EnumMap<Suit,HandTarot> _couleurs, HandTarot _autresCartes,
            EnumMap<Suit,HandTarot> _playedCards) {
        EnumMap<Suit,HandTarot> suits_ = new EnumMap<Suit,HandTarot>();
        HandTarot pileBase_ = HandTarot.pileBase();
        for (Suit i : Suit.couleursOrdinaires()) {
            HandTarot cartes_ = _couleurs.getVal(i);
            HandTarot couleurTotale_ = pileBase_.couleur(i);
            HandTarot cartesJoueesOuPossedees_ = new HandTarot();
            cartesJoueesOuPossedees_.ajouterCartes(_playedCards.getVal(i));
            cartesJoueesOuPossedees_.ajouterCartes(cartes_);
            for (CardTarot autre_ : _autresCartes.couleur(i)) {
                if (!cartesJoueesOuPossedees_.contient(autre_)) {
                    cartesJoueesOuPossedees_.ajouter(autre_);
                }
            }
            cartesJoueesOuPossedees_.trierParForceEnCours(i);
            suits_.put(i,GameTarotCommon.findLeading(cartes_,couleurTotale_,cartesJoueesOuPossedees_,_playedCards.getVal(i)));
        }
        return suits_;
    }
    static EnumList<Suit> couleursPseudosMaitres(
            EnumMap<Suit,HandTarot> _couleurs, EnumMap<Suit,HandTarot> _hashMap) {
        EnumList<Suit> nombre_ = new EnumList<Suit>();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if (_couleurs.getVal(couleur_).total() == _hashMap.getVal(
                    couleur_).total()
                    && !_couleurs.getVal(couleur_).estVide()) {
                nombre_.add(couleur_);
            }
        }
        return nombre_;
    }
    HandTarot callableCards() {
        GameTarotBid g_ = new GameTarotBid(currentHand,rules,bids,bid);
        return g_.cartesAppeler();
    }
    HandTarot cartesAppeler() {
        HandTarot main_ = new HandTarot();
        if (rules.getDealing().getAppel() == CallingCard.KING) {
            if (currentHand.tailleRois() < HandTarot.charCards(CardChar.KING).total()) {
                main_.ajouterCartes(HandTarot.charCards(CardChar.KING));
            } else if (currentHand.tailleDames() < HandTarot.charCards(CardChar.QUEEN).total()) {
                main_.ajouterCartes(HandTarot.charCards(CardChar.QUEEN));
            } else if (currentHand.tailleCavaliers() < HandTarot.charCards(CardChar.KNIGHT).total()) {
                main_.ajouterCartes(HandTarot.charCards(CardChar.KNIGHT));
            } else if (currentHand.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
            } else {
                main_.ajouter(CardTarot.petit());
                main_.ajouter(CardTarot.vingtEtUn());
                main_.ajouter(CardTarot.excuse());
            }
        } else if (rules.getDealing().getAppel() == CallingCard.CHARACTER_CARD) {
            main_.ajouterCartes(HandTarot.figuesCouleurs());
            main_.ajouter(CardTarot.petit());
            main_.ajouter(CardTarot.vingtEtUn());
            main_.ajouter(CardTarot.excuse());
        }
        return main_;
    }
    boolean contratAccepte(BidTarot _enchere) {
        return rules.getAllowedBids().getVal(_enchere) == BoolVal.TRUE;
    }
    byte getNombreDeJoueurs() {
        return (byte) rules.getDealing().getId().getNombreJoueurs();
    }

    HandTarot getCurrentHand() {
        return currentHand;
    }
}
