package cards.tarot;

import cards.consts.CardChar;
import cards.consts.Suit;
import cards.tarot.comparators.BidTarotComparator;
import cards.tarot.enumerations.*;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;

public final class GameTarotBid {
    private HandTarot currentHand;
    private RulesTarot rules;
    private EnumList<BidTarot> bids;
    private BidTarot bid;

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
        int atouts_ = couleurs_.getVal(CardTarot.excuse().couleur()).total() + couleurs_.getVal(Suit.TRUMP).total();
        boolean chelem_ = estUnJeuDeChelem(couleurs_, new HandTarot().couleurs(), rules, cartesAppeler(), nombreJoueurs_);
        if (chelem_) {
            BidTarot e_ = BidTarot.FOLD;
            for(BidTarot e: allowedBids()) {
                if(!e.isFaireTousPlis()) {
                    continue;
                }
                if(e.estDemandable(e_)) {
                    e_ = e;
                }
            }
            if(e_.isJouerDonne()) {
                return e_;
            }
        }
        if (chelem_) {
            BidTarot e_ = BidTarot.FOLD;
            for(BidTarot e: allowedBids()) {
                if(e.estDemandable(e_)) {
                    e_ = e;
                }
            }
            if(e_.isJouerDonne()) {
                return e_;
            }
        }
        Suit couleurAtout_ = Suit.TRUMP;
        HandTarot trumps_ = couleurs_.getVal(couleurAtout_);
        EqList<HandTarot> suitesAtouts_ = trumps_.eclaterDebutPartie();
        int valeurAtout_;
        int valeurAtoutMoyen_;
        int valeurAtoutMajeur_;
        int nbAtoutsQuinzeAuVingtEtUn_ = 0;
        int nbAtoutsSeptAuQuatorze_ = 0;
        int nbAtoutsMajeursConsecutifs_ = 0;
        HandTarot bouts_ = getOulderInHand(couleurs_);
        for (HandTarot main_ : suitesAtouts_) {
            if (main_.total() > 1 && main_.premiereCarte().valeur() > 14) {
                nbAtoutsMajeursConsecutifs_ += main_.total();
            }
        }
        if (!trumps_.estVide()) {
            if (trumps_.premiereCarte().valeur() > 14) {
                nbAtoutsQuinzeAuVingtEtUn_++;
            } else if (trumps_.premiereCarte().valeur() > 6) {
                nbAtoutsSeptAuQuatorze_++;
            }
        }
        int nbTrumps_ = trumps_.total();
        for (int indiceCarte_ = CustList.SECOND_INDEX; indiceCarte_ < nbTrumps_; indiceCarte_++) {
            if (trumps_.carte(indiceCarte_).valeur() > 14) {
                nbAtoutsQuinzeAuVingtEtUn_++;
            } else if (trumps_.carte(indiceCarte_).valeur() > 6) {
                boolean continuer_ = false;
                for (HandTarot main_ : suitesAtouts_) {
                    if (main_.premiereCarte().valeur() <= 14) {
                        break;
                    }
                    if (main_.contient(trumps_.carte(
                            indiceCarte_))) {
                        nbAtoutsQuinzeAuVingtEtUn_++;
                        continuer_ = true;
                        break;
                    }
                }
                if (continuer_) {
                    continue;
                }
                nbAtoutsSeptAuQuatorze_++;
            } else {
                boolean continuer_ = false;
                for (HandTarot main_ : suitesAtouts_) {
                    if (main_.premiereCarte().valeur() <= 14) {
                        break;
                    }
                    if (main_.contient(trumps_.carte(
                            indiceCarte_))) {
                        nbAtoutsQuinzeAuVingtEtUn_++;
                        continuer_ = true;
                        break;
                    }
                }
                if (continuer_) {
                    continue;
                }
                for (HandTarot main_ : suitesAtouts_) {
                    if (main_.premiereCarte().valeur() <= 6) {
                        break;
                    }
                    if (main_.contient(trumps_.carte(
                            indiceCarte_))) {
                        nbAtoutsSeptAuQuatorze_++;
                        break;
                    }
                }
            }
        }
        int total_ = 0;
        int valeurVingtEtUnSeul_;
        int valeurExcuseSeule_;
        int valeurVingtEtUnExcuse_;
        int valeurPetitBout_;
        int valeurMariageRoiDame_;
        // roi - dame
        int valeurRoiSeul_;
        int valeurDameSeul_;
        int valeurCavalier_;
        int valeurValet_;
        int valeurCoupe_;
        int valeurLongue_;
        if (nombreJoueurs_ == 3) {
            if (atouts_ <= 7) {
                valeurPetitBout_ = 0;
            } else if (atouts_ <= 12) {
                valeurPetitBout_ = 2;
            } else {
                valeurPetitBout_ = 4;
            }
            if (atouts_ <= 12) {
                valeurAtoutMoyen_ = 1;
                valeurAtout_ = 3;
                valeurAtoutMajeur_ = 2;
                valeurVingtEtUnSeul_ = 4;
                valeurExcuseSeule_ = 2;
                valeurVingtEtUnExcuse_ = 10;
                valeurMariageRoiDame_ = 10;
                valeurRoiSeul_ = 6;
                valeurDameSeul_ = 4;
                valeurCavalier_ = 3;
                valeurValet_ = 1;
                valeurCoupe_ = 1;
                valeurLongue_ = 3;
                total_ += nbAtoutsMajeursConsecutifs_;
            } else {
                valeurAtoutMoyen_ = 2;
                valeurAtout_ = 4;
                valeurAtoutMajeur_ = 3;
                valeurVingtEtUnSeul_ = 6;
                valeurExcuseSeule_ = 3;
                valeurVingtEtUnExcuse_ = 14;
                valeurMariageRoiDame_ = 18;
                valeurRoiSeul_ = 8;
                valeurDameSeul_ = 7;
                valeurCavalier_ = 3;
                valeurValet_ = 2;
                valeurCoupe_ = 2;
                valeurLongue_ = 6;
                total_ += nbAtoutsMajeursConsecutifs_ * 2;
            }
        } else if (nombreJoueurs_ == 4) {
            if (atouts_ <= 6) {
                valeurPetitBout_ = 0;
            } else if (atouts_ <= 9) {
                valeurPetitBout_ = 3;
            } else {
                valeurPetitBout_ = 6;
            }
            valeurCavalier_ = 2;
            valeurValet_ = 1;
            if (atouts_ <= 9) {
                valeurAtout_ = 4;
                valeurAtoutMoyen_ = 2;
                valeurAtoutMajeur_ = 3;
                valeurVingtEtUnSeul_ = 6;
                valeurExcuseSeule_ = 3;
                valeurVingtEtUnExcuse_ = 12;
                valeurMariageRoiDame_ = 8;
                valeurRoiSeul_ = 5;
                valeurDameSeul_ = 3;
                valeurCoupe_ = 2;
                valeurLongue_ = 4;
                total_ += nbAtoutsMajeursConsecutifs_ * 2;
            } else {
                valeurAtout_ = 5;
                valeurAtoutMoyen_ = 3;
                valeurAtoutMajeur_ = 4;
                valeurVingtEtUnSeul_ = 8;
                valeurExcuseSeule_ = 4;
                valeurVingtEtUnExcuse_ = 16;
                valeurMariageRoiDame_ = 12;
                valeurRoiSeul_ = 6;
                valeurDameSeul_ = 4;
                valeurCoupe_ = 4;
                valeurLongue_ = 8;
                total_ += nbAtoutsMajeursConsecutifs_ * 4;
            }
        } else if (nombreJoueurs_ == 5) {
            if (atouts_ <= 5) {
                valeurPetitBout_ = 0;
            } else if (atouts_ <= 7) {
                valeurPetitBout_ = 3;
            } else {
                valeurPetitBout_ = 6;
            }
            valeurCavalier_ = 2;
            valeurValet_ = 1;
            if (atouts_ <= 7) {
                valeurAtout_ = 4;
                valeurAtoutMoyen_ = 2;
                valeurAtoutMajeur_ = 3;
                valeurVingtEtUnSeul_ = 6;
                valeurExcuseSeule_ = 3;
                valeurVingtEtUnExcuse_ = 12;
                valeurMariageRoiDame_ = 8;
                valeurRoiSeul_ = 5;
                valeurDameSeul_ = 3;
                valeurCoupe_ = 2;
                valeurLongue_ = 4;
                total_ += nbAtoutsMajeursConsecutifs_ * 2;
            } else {
                valeurAtout_ = 5;
                valeurAtoutMoyen_ = 3;
                valeurAtoutMajeur_ = 4;
                valeurVingtEtUnSeul_ = 8;
                valeurExcuseSeule_ = 4;
                valeurVingtEtUnExcuse_ = 16;
                valeurMariageRoiDame_ = 12;
                valeurRoiSeul_ = 6;
                valeurDameSeul_ = 4;
                valeurCoupe_ = 4;
                valeurLongue_ = 8;
                total_ += nbAtoutsMajeursConsecutifs_ * 4;
            }
        } else {
            /* 6 joueurs */
            if (atouts_ <= 4) {
                valeurPetitBout_ = 0;
            } else if (atouts_ <= 6) {
                valeurPetitBout_ = 5;
            } else {
                valeurPetitBout_ = 10;
            }
            valeurAtoutMoyen_ = 3;
            valeurCavalier_ = 2;
            valeurValet_ = 1;
            if (atouts_ <= 6) {
                valeurAtout_ = 5;
                valeurAtoutMajeur_ = 4;
                valeurVingtEtUnSeul_ = 8;
                valeurExcuseSeule_ = 5;
                valeurVingtEtUnExcuse_ = 16;
                valeurMariageRoiDame_ = 6;
                valeurRoiSeul_ = 4;
                valeurDameSeul_ = 2;
                valeurCoupe_ = 3;
                valeurLongue_ = 5;
                total_ += nbAtoutsMajeursConsecutifs_ * 3;
            } else {
                valeurAtout_ = 7;
                valeurAtoutMajeur_ = 7;
                valeurVingtEtUnSeul_ = 10;
                valeurExcuseSeule_ = 7;
                valeurVingtEtUnExcuse_ = 21;
                valeurMariageRoiDame_ = 9;
                valeurRoiSeul_ = 5;
                valeurDameSeul_ = 3;
                valeurCoupe_ = 7;
                valeurLongue_ = 11;
                total_ += nbAtoutsMajeursConsecutifs_ * 6;
            }
        }
        if (atouts_ == 0) {
            valeurCoupe_ = 0;
            valeurLongue_ = 0;
        }
        int nombreLimiteLongue_;
        int totalCouleur_=0;
        for(Suit c: Suit.couleursOrdinaires()) {
            totalCouleur_+= HandTarot.couleurComplete(c).total();
        }
        if(totalCouleur_ > 0) {
            totalCouleur_ /= Suit.couleursOrdinaires().size();
        }
        if (totalCouleur_ % nombreJoueurs_ == 0) {
            nombreLimiteLongue_ = totalCouleur_ / nombreJoueurs_ + 1;
        } else {
            nombreLimiteLongue_ = totalCouleur_ / nombreJoueurs_ + 2;
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
        total_ += valeurAtout_ * atouts_ + valeurAtoutMajeur_ * nbAtoutsQuinzeAuVingtEtUn_
                + valeurAtoutMoyen_ * nbAtoutsSeptAuQuatorze_;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot mt_ = couleurs_.getVal(couleur_);
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
                total_ += valeurLongue_;
            }
            if (mt_.estVide()) {
                total_ += valeurCoupe_;
            }
        }
        int petite_;
        int garde_;
        if (nombreJoueurs_ == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
            petite_ = 90;
            garde_ = 140;
        } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getNombreJoueurs()) {
            if (rules.getRepartition().getAppel() == CallingCard.WITHOUT) {
                petite_ = 90;
                garde_ = 160;
            } else if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
                petite_ = 50;
                garde_ = 90;
            } else {
                petite_ = 60;
                garde_ = 110;
            }
        } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getNombreJoueurs()) {
            if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
                petite_ = 50;
                garde_ = 90;
            } else {
                petite_ = 90;
                garde_ = 150;
            }
        } else {
            if (rules.getRepartition() == DealingTarot.DEAL_1_VS_4) {
                petite_ = 80;
                garde_ = 130;
            } else {
                petite_ = 60;
                garde_ = 100;
            }
        }
        boolean sansAppel_ = rules.getRepartition().getAppel() == CallingCard.DEFINED
                || rules.getRepartition().getAppel() == CallingCard.WITHOUT;
        if (total_ >= garde_) {
            if (nombreJoueurs_ == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
                BidTarot f_ = tryGetStrongBid(sansAppel_,12);
                if (f_.isJouerDonne()) {
                    return f_;
                }
            } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getNombreJoueurs()) {
                BidTarot f_ = tryGetStrongBid(sansAppel_,9);
                if (f_.isJouerDonne()) {
                    return f_;
                }
            } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_2_CALL_KING.getNombreJoueurs()) {
                BidTarot f_ = tryGetStrongBid(sansAppel_,7);
                if (f_.isJouerDonne()) {
                    return f_;
                }
            } else {
                // nombreJoueurs == 6
                BidTarot f_ = tryGetStrongBid(sansAppel_,6);
                if (f_.isJouerDonne()) {
                    return f_;
                }
            }
        }
        BidTarot c_;
        if (total_ < petite_) {
            c_ = BidTarot.FOLD;
        } else if (total_ < garde_ && contratAccepte(BidTarot.TAKE)) {
            c_ = BidTarot.TAKE;
        } else {
            c_ = BidTarot.GUARD;
        }
        if (c_.estDemandable(bid)) {
            if (c_ == BidTarot.TAKE) {
                if (bids.size() == nombreJoueurs_ - 1) {
                    c_ = BidTarot.GUARD;
                }
            }
            return c_;
        }
        return BidTarot.FOLD;
    }
    static boolean estUnJeuDeChelem(EnumMap<Suit,HandTarot> _couleurs,
                                    EnumMap<Suit,HandTarot> _cartesJouees,
                                    RulesTarot _infos, HandTarot _cartesAppeler,byte _joueurs) {
        if (estUnJeuDeChelemSur(_couleurs,_cartesJouees)) {
            return true;
        }
        if (!maitreAtoutPourChelem(_couleurs,_joueurs)) {
            return false;
        }
        byte nombreCouleursLargMait_ = nbCouleursLargementMaitresses(
                _couleurs, _joueurs);
        if (_joueurs == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
            return nombreCouleursLargMait_ == Suit.couleursOrdinaires().size();
        }
        byte nombreCouleursPseuMait_ = nbCouleursPseudoMaitresses(_couleurs,
                _cartesAppeler,
                _joueurs);
        boolean avecAppel_ = _infos.getRepartition().getAppel() == CallingCard.KING;
        if (_infos.getRepartition().getAppel() == CallingCard.CHARACTER_CARD) {
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
        byte atouts_ = (byte) (_couleurs.getVal(CardTarot.excuse().couleur()).total() + _couleurs.getVal(Suit.TRUMP).total());
        byte atoutsMaitres_ = nbAtoutsMaitres(_couleurs);
        if (_joueurs == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
            if (atouts_ > 20) {
                return atoutsMaitres_ > 0;
            }
            if (atouts_ > 18) {
                return atoutsMaitres_ > 1;
            }
            if (atouts_ > 16) {
                return atoutsMaitres_ > 2;
            }
            if (atouts_ > 14) {
                return atoutsMaitres_ > 3;
            }
            if (atouts_ > 12) {
                return atoutsMaitres_ > 4;
            }
            if (atouts_ > 10) {
                return atoutsMaitres_ > 5;
            }
            if (atouts_ > 8) {
                return atoutsMaitres_ > 6;
            }
            return false;
        }
        if (_joueurs == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getNombreJoueurs()) {
            if (atouts_ > 15) {
                return atoutsMaitres_ > 1;
            }
            if (atouts_ > 13) {
                return atoutsMaitres_ > 2;
            }
            if (atouts_ > 11) {
                return atoutsMaitres_ > 3;
            }
            if (atouts_ > 9) {
                return atoutsMaitres_ > 4;
            }
            if (atouts_ > 7) {
                return atoutsMaitres_ > 5;
            }
            return false;
        }
        if (_joueurs == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getNombreJoueurs()) {
            if (atouts_ > 10) {
                return atoutsMaitres_ > 2;
            }
            if (atouts_ > 8) {
                return atoutsMaitres_ > 3;
            }
            if (atouts_ > 6) {
                return atoutsMaitres_ > 4;
            }
            if (atouts_ == 6) {
                return atoutsMaitres_ > 5;
            }
            return false;
        }
        if (atouts_ == 15) {
            return atoutsMaitres_ > 1;
        }
        if (atouts_ > 12) {
            return atoutsMaitres_ > 2;
        }
        if (atouts_ > 10) {
            return atoutsMaitres_ > 3;
        }
        if (atouts_ > 8) {
            return atoutsMaitres_ > 4;
        }
        if (atouts_ > 6) {
            return atoutsMaitres_ > 5;
        }
        return false;
    }

    BidTarot tryGetStrongBid(boolean _withoutCall, int _trumps) {
        EnumMap<Suit,HandTarot> couleurs_ = currentHand.couleurs();
        int atouts_ = couleurs_.getVal(CardTarot.excuse().couleur()).total() + couleurs_.getVal(Suit.TRUMP).total();
        HandTarot bouts_ = getOulderInHand(couleurs_);
        byte nombreJoueurs_ = getNombreDeJoueurs();
        int nbCouleurs_ = Suit.couleursOrdinaires().size();
        if (_withoutCall) {
            if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs()) == nbCouleurs_) {
                if (atouts_ >= _trumps) {
                    BidTarot e_ = getStrongBid(bouts_);
                    if(e_.isJouerDonne()) {
                        return e_;
                    }
                }
            }
        } else if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs())
                + nbCouleursPseudoMaitresses(couleurs_, cartesAppeler(), nombreJoueurs_) == nbCouleurs_) {
            if (atouts_ >= _trumps) {
                BidTarot e_ = getStrongBid(bouts_);
                if(e_.isJouerDonne()) {
                    return e_;
                }
            }
        }
        return BidTarot.FOLD;
    }
    BidTarot getStrongBid(HandTarot _bouts) {
        BidTarot e_ = BidTarot.FOLD;
        PlayingDog jeuChien_ = PlayingDog.WITHOUT;
        if (_bouts.total() >= 2) {
            jeuChien_ = PlayingDog.AGAINST;
        } else if (_bouts.contient(CardTarot.vingtEtUn())) {
            jeuChien_ = PlayingDog.AGAINST;
        }
        for(BidTarot e: allowedBids()) {
            if(e.getJeuChien() != jeuChien_) {
                continue;
            }
            if(e.estDemandable(e_)) {
                e_ = e;
            }
        }
        return e_;
    }
    static HandTarot getOulderInHand(EnumMap<Suit, HandTarot> _couleurs) {
        HandTarot trumps_ = _couleurs.getVal(Suit.TRUMP);
        HandTarot bouts_ = new HandTarot();
        if (!_couleurs.getVal(CardTarot.EXCUSE.couleur()).estVide()) {
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
        for (BidTarot b: BidTarot.values()) {
            if (!contratAccepte(b)) {
                continue;
            }
            bids_.add(b);
        }
        bids_.sortElts(new BidTarotComparator());
        return bids_;
    }

    static boolean estUnJeuDeChelemSur(EnumMap<Suit,HandTarot> _couleurs, EnumMap<Suit,HandTarot> _cartesJouees) {
        int nbTr_ = nbAtoutsMaitres(_couleurs) + _cartesJouees.getVal(Suit.TRUMP).total() + _couleurs.getVal(Suit.TRUMP).total();
        int nbFullTr_ = HandTarot.atoutsSansExcuse().total() + _couleurs.getVal(CardTarot.excuse().couleur()).total();
        if (nbTr_ == nbFullTr_) {
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
        if (_nombreJoueurs == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
            return nbCartesMaitresses(_couleurs, new HandTarot().couleurs(), _noCouleur) > 5;
        }
        if (_nombreJoueurs == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getNombreJoueurs()) {
            return nbCartesMaitresses(_couleurs, new HandTarot().couleurs(), _noCouleur) > 4;
        }
        if (_nombreJoueurs == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getNombreJoueurs()) {
            return nbCartesMaitresses(_couleurs, new HandTarot().couleurs(), _noCouleur) > 2;
        }
        return nbCartesMaitresses(_couleurs, new HandTarot().couleurs(), _noCouleur) > 3;
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
        if (nombreCartesMaitresses_ > totalCouleur_/2) {
            return true;
        }
        int nb_ = nombreCartesMaitresses_ + _couleurs.getVal(_noCouleur).total();
        if (nb_ > totalCouleur_) {
            return true;
        }
        return nb_ > totalCouleur_ - 1 && !_couleurs.getVal(CardTarot.EXCUSE.couleur()).estVide();
    }

    static int nbCartesMaitresses(EnumMap<Suit,HandTarot> _couleurs,
                                  EnumMap<Suit,HandTarot> _cartesJouees,
                                  Suit _noCouleur) {
        HandTarot couleur_ = _couleurs.getVal(_noCouleur);
        EqList<HandTarot> suites_ = couleur_.eclaterEnCours(_cartesJouees, _noCouleur);
        HandTarot cartesJoueesOuPossedes_ = new HandTarot();
        cartesJoueesOuPossedes_.ajouterCartes(couleur_);
        cartesJoueesOuPossedes_.ajouterCartes(_cartesJouees.getVal(_noCouleur));
        cartesJoueesOuPossedes_.trierParForceEnCours(_noCouleur);
        HandTarot couleurComplete_ = HandTarot.couleurComplete(_noCouleur);
        couleurComplete_.trierParForceEnCours(_noCouleur);
        boolean cartesMaitressesToutesJoueesOuPossedes_ = true;
        for(CardTarot carte_: couleurComplete_) {
            if(!cartesJoueesOuPossedes_.contient(carte_)) {
                cartesMaitressesToutesJoueesOuPossedes_ = false;
                break;
            }
            if(couleur_.contient(carte_)) {
                break;
            }
        }
        if(cartesMaitressesToutesJoueesOuPossedes_) {
            return suites_.first().total();
        }
        return 0;
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
            HandTarot cartesMaitresses_ = new HandTarot();
            int length_ = cartesJoueesOuPossedees_.total();
            for (byte c = CustList.FIRST_INDEX; c < length_; c++) {
                if (!CardTarot.eq(cartesJoueesOuPossedees_.carte(c),
                        couleurTotale_.carte(c))) {
                    break;
                }
                if (!cartes_.contient(cartesJoueesOuPossedees_.carte(c))) {
                    continue;
                }
                cartesMaitresses_.ajouter(cartesJoueesOuPossedees_.carte(c));
            }
            int nbLeadingCards_ = cartesMaitresses_.total();
            if (nbLeadingCards_ + nbLeadingCards_ + _playedCards.getVal(i).total() >= couleurTotale_
                    .total()) {
                for (CardTarot carte_ : cartes_) {
                    if (!cartesMaitresses_.contient(carte_)) {
                        cartesMaitresses_.ajouter(carte_);
                    }
                }
            }
            suits_.put(i,cartesMaitresses_);
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
        if (rules.getRepartition().getAppel() == CallingCard.KING) {
            int nbAppeles_ = rules.getRepartition().getNbAppeles();
            if (currentHand.tailleRois() < HandTarot.charCards(CardChar.KING).total()) {
                main_.ajouterCartes(HandTarot.charCards(CardChar.KING));
                if (currentHand.tailleRois() + nbAppeles_ > HandTarot.charCards(CardChar.KING).total()) {
                    //nbAppeles > 0
                    if (currentHand.tailleDames() < HandTarot.charCards(CardChar.QUEEN).total()) {
                        main_.ajouterCartes(HandTarot.charCards(CardChar.QUEEN));
                        if (currentHand.tailleDames() + nbAppeles_ > HandTarot.charCards(CardChar.QUEEN).total()) {
                            if (currentHand.tailleCavaliers() < HandTarot.charCards(CardChar.KNIGHT).total()) {
                                main_.ajouterCartes(HandTarot.charCards(CardChar.KNIGHT));
                                if (currentHand.tailleCavaliers() + nbAppeles_ > HandTarot.charCards(CardChar.KNIGHT).total()) {
                                    if (currentHand.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                        main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                        if (currentHand.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                            main_.ajouter(CardTarot.petit());
                                            main_.ajouter(CardTarot.vingtEtUn());
                                            main_.ajouter(CardTarot.excuse());
                                        }
                                    } else {
                                        main_.ajouter(CardTarot.petit());
                                        main_.ajouter(CardTarot.vingtEtUn());
                                        main_.ajouter(CardTarot.excuse());
                                    }
                                }
                            } else {
                                if (currentHand.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                    if (currentHand.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                        main_.ajouter(CardTarot.petit());
                                        main_.ajouter(CardTarot.vingtEtUn());
                                        main_.ajouter(CardTarot.excuse());
                                    }
                                } else {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            }
                        }
                    } else {
                        if (currentHand.tailleCavaliers() < HandTarot.charCards(CardChar.KNIGHT).total()) {
                            main_.ajouterCartes(HandTarot.charCards(CardChar.KNIGHT));
                            if (currentHand.tailleCavaliers() + nbAppeles_ > HandTarot.charCards(CardChar.KNIGHT).total()) {
                                if (currentHand.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                    if (currentHand.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                        main_.ajouter(CardTarot.petit());
                                        main_.ajouter(CardTarot.vingtEtUn());
                                        main_.ajouter(CardTarot.excuse());
                                    }
                                } else {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            }
                        } else {
                            if (currentHand.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                if (currentHand.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            } else {
                                main_.ajouter(CardTarot.petit());
                                main_.ajouter(CardTarot.vingtEtUn());
                                main_.ajouter(CardTarot.excuse());
                            }
                        }
                    }
                }
            } else {
                if (currentHand.tailleDames() < HandTarot.charCards(CardChar.QUEEN).total()) {
                    main_.ajouterCartes(HandTarot.charCards(CardChar.QUEEN));
                    if (currentHand.tailleDames() + nbAppeles_ > HandTarot.charCards(CardChar.QUEEN).total()) {
                        if (currentHand.tailleCavaliers() < HandTarot.charCards(CardChar.KNIGHT).total()) {
                            main_.ajouterCartes(HandTarot.charCards(CardChar.KNIGHT));
                            if (currentHand.tailleCavaliers() + nbAppeles_ > HandTarot.charCards(CardChar.KNIGHT).total()) {
                                if (currentHand.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                    if (currentHand.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                        main_.ajouter(CardTarot.petit());
                                        main_.ajouter(CardTarot.vingtEtUn());
                                        main_.ajouter(CardTarot.excuse());
                                    }
                                } else {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            }
                        } else {
                            if (currentHand.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                if (currentHand.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            } else {
                                main_.ajouter(CardTarot.petit());
                                main_.ajouter(CardTarot.vingtEtUn());
                                main_.ajouter(CardTarot.excuse());
                            }
                        }
                    }
                } else {
                    if (currentHand.tailleCavaliers() < HandTarot.charCards(CardChar.KNIGHT).total()) {
                        main_.ajouterCartes(HandTarot.charCards(CardChar.KNIGHT));
                        if (currentHand.tailleCavaliers() + nbAppeles_ > HandTarot.charCards(CardChar.KNIGHT).total()) {
                            if (currentHand.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                if (currentHand.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            } else {
                                main_.ajouter(CardTarot.petit());
                                main_.ajouter(CardTarot.vingtEtUn());
                                main_.ajouter(CardTarot.excuse());
                            }
                        }
                    } else {
                        if (currentHand.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                            main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                            if (currentHand.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                main_.ajouter(CardTarot.petit());
                                main_.ajouter(CardTarot.vingtEtUn());
                                main_.ajouter(CardTarot.excuse());
                            }
                        } else {
                            main_.ajouter(CardTarot.petit());
                            main_.ajouter(CardTarot.vingtEtUn());
                            main_.ajouter(CardTarot.excuse());
                        }
                    }
                }
            }
        } else if (rules.getRepartition().getAppel() == CallingCard.CHARACTER_CARD) {
            main_.ajouterCartes(HandTarot.figuesCouleurs());
            main_.ajouter(CardTarot.petit());
            main_.ajouter(CardTarot.vingtEtUn());
            main_.ajouter(CardTarot.excuse());
        }
        return main_;
    }
    boolean contratAccepte(BidTarot _enchere) {
        return rules.getContrats().getVal(_enchere);
    }
    byte getNombreDeJoueurs() {
        return (byte) rules.getRepartition().getNombreJoueurs();
    }

    RulesTarot getRules() {
        return rules;
    }

    HandTarot getCurrentHand() {
        return currentHand;
    }
}
