package cards.tarot;
import java.util.Iterator;

import code.maths.LgInt;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.annot.RwXml;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.ChoiceTarot;
import cards.tarot.enumerations.DealingTarot;

@RwXml
public final class DealTarot implements Iterable<HandTarot> {

    public static final byte NUMERO_UTILISATEUR = 0;

    public static final int NB_CARDS = HandTarot.pileBase().total();

    /** Ensemble des mains des joueurs */
    private EqList<HandTarot> deal = new EqList<HandTarot>();
    /** donneur est un entier allant de 0 a nombre de joueurs-1 */
    private byte dealer;
    /** nombre de parties jouees depuis le lancement */
    private long nbDeals;
    /** Pile de distribution pour initialiser la donne */
    private transient HandTarot deck;

    public DealTarot(){}
    public DealTarot(long _nombreDeParties, HandTarot _ppile) {
        //nombreDeParties_ est_ necessaire_ pour_ savoir_ si_ c'est_ la_ premiere_ fois_ qu_'une_ partie_ est_ joue_,
        //pile est_ necessaire_ pour_ savoir_ si_ on_ ne_ distribue_ jamais_ jouees_ depuis_ le_ lancement_
        nbDeals = _nombreDeParties;
        deck = _ppile;
    }

    // appele au chargement d'une partie, entrainement tarot
    public DealTarot(long _nombreDeParties) {
        // nombreDeParties_ est_ necessaire_ pour_ savoir_ si_ c'est_ la_ premiere_ fois_ qu_'une_ partie_ est_ joue_,
        nbDeals = _nombreDeParties;
    }

    /** Utilise pour editer une partie non solitaire */
    public DealTarot(EqList<HandTarot> _pdonne, byte _pdonneur) {
        deal = _pdonne;
        dealer = _pdonneur;
    }

    DealTarot(DealTarot _deal) {
        deal = new EqList<HandTarot>();
        for (HandTarot h: _deal) {
            HandTarot h_ = new HandTarot();
            h_.ajouterCartes(h);
            deal.add(h_);
        }
        dealer = _deal.dealer;
        nbDeals = _deal.nbDeals;
    }

    /** Initialise le donneur pour editer une partie */
    public void initDonneur(byte _b) {
        // On initialise_ le_ donneur par_ l'editeur_
        dealer = _b;
    }

    public long getNombreDeParties() {
        return nbDeals;
    }

    public byte getDonneur() {
        return dealer;
    }

    /** Initialise de maniere aleatoire le premier donneur */
    public void setRandomDealer(RulesTarot _regles) {
        // On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
//        dealer = (byte) (_regles.getRepartition().getNombreJoueurs() * MonteCarlo.randomDouble());
        dealer = (byte)AbMonteCarlo.randomInt(_regles.getRepartition().getNombreJoueurs());
    }

    /**
    Apres une partie la joueur apres le donneur actuel devient le nouveau
    donneur
    */
    public void donneurSuivant(byte _nouveauDonneur, RulesTarot _regles) {
        dealer = _nouveauDonneur;
        // On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
        dealer++;
        dealer %= _regles.getRepartition().getNombreJoueurs();
    }

    /**
    Distribue les cartes de maniere aleatoire ou non selon les parametres de
    distribution, on ne tient pas compte du sens de distribution
    */
    public void initDonne(RulesTarot _regles) {
        if (_regles.getCartesBattues() == MixCardsChoice.EACH_DEAL) {
            donnerEnBattant(_regles);
        } else if (_regles.getCartesBattues() == MixCardsChoice.EACH_LAUNCHING
                || _regles.getCartesBattues() == MixCardsChoice.ONCE_ONLY) {
            if (nbDeals == 0) {
                donnerEnBattant(_regles);
            } else {
                donnerSansBattre(_regles);
            }
        } else {
            donnerSansBattre(_regles);
        }
    }

    /** Pour l&#39;entra&icirc;nement surtout au tarot */
    public void initDonne(ChoiceTarot _choix, byte _nombreCartes,
            byte _nombreJoueurs, RulesTarot _regles) {
        /* Les deux_ nombres_ donnent_ le_ nombre_ d atouts_ avec_ Excuse */
        byte minAtout_ = 0;
        byte maxAtout_ = 0;
        byte atoutsTires_ = 0;
        byte autresCartesTirer_ = 0;
        EqList<LgInt> fonctionRepartition_;
        LgInt alea_;
        if (_choix == ChoiceTarot.HUNT_SMALL) {
            if (_nombreCartes == 24) {
                minAtout_ = 15;
                maxAtout_ = 21;
            } else if (_nombreCartes == 18) {
                minAtout_ = 13;
                maxAtout_ = 18;
            } else if (_nombreCartes == 15) {
                minAtout_ = 10;
                maxAtout_ = 15;
            } else if (_nombreCartes == 12) {
                minAtout_ = 9;
                maxAtout_ = 12;
            } else {
                minAtout_ = 10;
                maxAtout_ = 14;
            }
            //fonctionRepartition_ = new LgInt[maxAtout_ - minAtout_ + 1];
            fonctionRepartition_ = new EqList<LgInt>();
            GameTarot.setChargementSimulation(GameTarot.getChargementSimulation()
                    + 90 / (maxAtout_ - minAtout_ + 1));
            fonctionRepartition_.add(LgInt.multiply(
                    LgInt.among(new LgInt(minAtout_), new LgInt(21)),
                    LgInt.among(new LgInt(_nombreCartes), new LgInt(56))));
//            fonctionRepartition_[CustList.FIRST_INDEX] = LgInt.multiply(
//                    LgInt.among(new LgInt(minAtout_), new LgInt(21)),
//                    LgInt.among(new LgInt(_nombreCartes), new LgInt(56)));
            byte index_ = (byte) (minAtout_ + 1);
            for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
                GameTarot.setChargementSimulation(GameTarot
                        .getChargementSimulation() + 90 / (maxAtout_ - minAtout_ + 1));
                fonctionRepartition_.add(LgInt.plus(
                        fonctionRepartition_.last(), LgInt
                        .multiply(LgInt.among(new LgInt(evenement_),
                                new LgInt(21)), LgInt.among(
                                new LgInt(_nombreCartes - evenement_),
                                new LgInt(56)))));
//                fonctionRepartition_[evenement_ - minAtout_] = LgInt.plus(
//                        fonctionRepartition_[evenement_ - minAtout_ - 1], LgInt
//                                .multiply(LgInt.among(new LgInt(evenement_),
//                                        new LgInt(21)), LgInt.among(
//                                        new LgInt(_nombreCartes - evenement_),
//                                        new LgInt(56))));
            }
//            alea_ = fonctionRepartition_[fonctionRepartition_.length - 1]
//                    .multiply(Math.random());
//            alea_ = fonctionRepartition_.last()
//                    .multiply(MonteCarlo.randomDouble());
            alea_ = AbMonteCarlo.randomLgInt(fonctionRepartition_.last());
            GameTarot.setChargementSimulation(95);
            for (byte evenement_ = minAtout_; evenement_ <= maxAtout_; evenement_++) {
//                if (LgInt.lowerEq(alea_, fonctionRepartition_[evenement_
//                        - minAtout_])) {
//                    atoutsTires_ = evenement_;
//                    break;
//                }
                if (LgInt.lowerEq(alea_, fonctionRepartition_.get(evenement_
                        - minAtout_))) {
                    atoutsTires_ = evenement_;
                    break;
                }
            }
            autresCartesTirer_ = (byte) (_nombreCartes - atoutsTires_);
            for (int i = CustList.FIRST_INDEX; i <= _nombreJoueurs; i++) {
                deal.add(new HandTarot());
            }
            HandTarot atouts_ = new HandTarot();
            HandTarot autresCartes_ = new HandTarot();
            atouts_.ajouter(CardTarot.excuse());
            atouts_.ajouterCartes(HandTarot.atoutsSansExcuse());
            autresCartes_.ajouterCartes(HandTarot.cartesCouleurs());
            for (byte tirage_ = CustList.SIZE_EMPTY; tirage_ < atoutsTires_; tirage_++) {
                deal.get(NUMERO_UTILISATEUR).ajouter(atouts_.tirerUneCarteAleatoire());
            }
            for (byte tirage_ = CustList.SIZE_EMPTY; tirage_ < autresCartesTirer_; tirage_++) {
                deal.get(NUMERO_UTILISATEUR).ajouter(autresCartes_.tirerUneCarteAleatoire());
            }
            deck = new HandTarot();
            deck.ajouterCartes(atouts_);
            deck.ajouterCartes(autresCartes_);
            byte reste_ = (byte) (NB_CARDS - _nombreCartes * _nombreJoueurs);
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
                if (joueur_ == NUMERO_UTILISATEUR) {
                    continue;
                }
                for (byte indiceCarte_ = CustList.SIZE_EMPTY; indiceCarte_ < _nombreCartes; indiceCarte_++) {
                    deal.get(joueur_).ajouter(deck.tirerUneCarteAleatoire());
                }
            }
            for (int i = CustList.SIZE_EMPTY; i < reste_; i++) {
                deal.last().ajouter(deck.jouer(0));
            }
            setRandomDealer(_regles);
        } else {
            if (_choix == ChoiceTarot.SAVE_SMALL) {
                minAtout_ = 1;
                if (_nombreCartes == 24) {
                    maxAtout_ = 5;
                } else if (_nombreCartes == 18) {
                    maxAtout_ = 4;
                } else if (_nombreCartes == 15 || _nombreCartes == 12) {
                    maxAtout_ = 3;
                } else {
                    maxAtout_ = 2;
                }
            } else if (_choix == ChoiceTarot.LEAD_SMALL_BOUND) {
                if (_nombreCartes == 24) {
                    minAtout_ = 14;
                    maxAtout_ = 21;
                } else if (_nombreCartes == 18) {
                    minAtout_ = 12;
                    maxAtout_ = 17;
                } else if (_nombreCartes == 15) {
                    minAtout_ = 9;
                    maxAtout_ = 14;
                } else if (_nombreCartes == 12) {
                    minAtout_ = 8;
                    maxAtout_ = 11;
                } else {
                    minAtout_ = 9;
                    maxAtout_ = 13;
                }
            }
//            fonctionRepartition_ = new LgInt[maxAtout_ - minAtout_ + 1];
            fonctionRepartition_ = new EqList<LgInt>();
            GameTarot.setChargementSimulation(GameTarot.getChargementSimulation()
                    + 90 / (maxAtout_ - minAtout_ + 1));
//            fonctionRepartition_[0] = LgInt.multiply(LgInt.among(new LgInt(
//                    minAtout_), new LgInt(21)), LgInt.among(new LgInt(
//                            _nombreCartes - minAtout_ - 1), new LgInt(56)));
//            fonctionRepartition_[0] = LgInt.multiply(LgInt.among(new LgInt(
//                    minAtout_), new LgInt(21)), LgInt.among(new LgInt(
//                    _nombreCartes - minAtout_ - 1), new LgInt(56)));
            fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                    minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                    _nombreCartes - minAtout_ - 1), new LgInt(56))));
            byte index_ = (byte) (minAtout_ + 1);
            for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
                GameTarot.setChargementSimulation(GameTarot
                        .getChargementSimulation() + 90 / (maxAtout_ - minAtout_ + 1));
//                fonctionRepartition_[evenement_ - minAtout_] = LgInt.plus(
//                        fonctionRepartition_[evenement_ - minAtout_ - 1], LgInt
//                        .multiply(LgInt.among(new LgInt(evenement_),
//                                new LgInt(21)), LgInt.among(
//                                        new LgInt(_nombreCartes - evenement_
//                                                - 1), new LgInt(56))));
                fonctionRepartition_.add(LgInt.plus(
                        fonctionRepartition_.last(), LgInt
                                .multiply(LgInt.among(new LgInt(evenement_),
                                        new LgInt(21)), LgInt.among(
                                        new LgInt(_nombreCartes - evenement_
                                                - 1), new LgInt(56)))));
            }
//            alea_ = fonctionRepartition_[fonctionRepartition_.length - 1]
//                    .multiply(Math.random());
//            alea_ = fonctionRepartition_.last()
//                    .multiply(MonteCarlo.randomDouble());
            alea_ = AbMonteCarlo.randomLgInt(fonctionRepartition_.last());
            GameTarot.setChargementSimulation(95);
            for (byte evenement_ = minAtout_; evenement_ <= maxAtout_; evenement_++) {
//                if (LgInt.lowerEq(alea_, fonctionRepartition_[evenement_
//                        - minAtout_])) {
//                    atoutsTires_ = evenement_;
//                    break;
//                }
                if (LgInt.lowerEq(alea_, fonctionRepartition_.get(evenement_
                        - minAtout_))) {
                    atoutsTires_ = evenement_;
                    break;
                }
            }
            autresCartesTirer_ = (byte) (_nombreCartes - atoutsTires_ - 1);
            for (int i = CustList.FIRST_INDEX; i <= _nombreJoueurs; i++) {
                deal.add(new HandTarot());
            }
            HandTarot atouts_ = new HandTarot();
            atouts_.ajouter(CardTarot.excuse());
            atouts_.ajouterCartes(HandTarot.atoutsSansExcuse());
            atouts_.jouer(CardTarot.petit());
            HandTarot autresCartes_ = new HandTarot();
            autresCartes_.ajouterCartes(HandTarot.cartesCouleurs());
            for (byte tirage_ = CustList.SIZE_EMPTY; tirage_ < atoutsTires_; tirage_++) {
                deal.get(NUMERO_UTILISATEUR).ajouter(atouts_.tirerUneCarteAleatoire());
            }
            deal.first().ajouter(CardTarot.petit());
            for (byte tirage_ = CustList.SIZE_EMPTY; tirage_ < autresCartesTirer_; tirage_++) {
                deal.get(NUMERO_UTILISATEUR).ajouter(autresCartes_.tirerUneCarteAleatoire());
            }
            deck = new HandTarot();
            deck.ajouterCartes(atouts_);
            deck.ajouterCartes(autresCartes_);
            byte reste_ = (byte) (NB_CARDS - _nombreCartes * _nombreJoueurs);
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
                if (joueur_ == NUMERO_UTILISATEUR) {
                    continue;
                }
                for (byte indiceCarte_ = CustList.SIZE_EMPTY; indiceCarte_ < _nombreCartes; indiceCarte_++) {
                    deal.get(joueur_).ajouter(deck.tirerUneCarteAleatoire());
                }
            }
            for (int i = CustList.SIZE_EMPTY; i < reste_; i++) {
                deal.last().ajouter(deck.jouer(0));
            }
            setRandomDealer(_regles);
        }
    }

    /**
    On distribue les cartes en les battant ceci est essentiel pour le
    solitaire car la fin de partie de solitaire ne depend pas a priori de la
    distribution au debut
    */
    private void donnerEnBattant(RulesTarot _regles) {

        byte nbJrs_ = (byte) _regles.getRepartition().getNombreJoueurs();
        for (int i = CustList.FIRST_INDEX; i <= nbJrs_; i++) {
            deal.add(new HandTarot());
        }
        HandTarot m = HandTarot.pileBase();
        byte nombreTotalCarteJoueurs_ = (byte) (nbJrs_ * _regles
                .getRepartition().getNombreCartesParJoueur());
        byte nombreCarteChien_ = (byte) _regles.getRepartition()
                .getNombreCartesChien();

        for (int i = CustList.FIRST_INDEX; i < nombreTotalCarteJoueurs_; i++) {
            deal.get(i % nbJrs_).ajouter(m.tirerUneCarteAleatoire());
        }
        for (int i = CustList.FIRST_INDEX; i < nombreCarteChien_; i++) {
            deal.last().ajouter(m.jouer(CustList.FIRST_INDEX));
        }
    }

    /**
    On distribue les cartes sans les cartes ce qui ressemble plus a la
    realite On ne tient pas compte du sens de distribution
    */
    private void donnerSansBattre(RulesTarot _regles) {
        deck.couper();
        DealingTarot repartition_ = _regles.getRepartition();
        /* On recupere_ le_ nombre_ de_ joueurs_ jouant_ au_ tarot_ */
        byte nbJrs_ = (byte) repartition_.getNombreJoueurs();
        /* On prepare_ les_ mains_ des_ joueurs_ */
        for (int i = CustList.FIRST_INDEX; i <= nbJrs_; i++) {
            deal.add(new HandTarot());
        }
        int iterations_ = CustList.SIZE_EMPTY;
        Numbers<Byte> ordreDisributionJoueurs_;
        ordreDisributionJoueurs_ = _regles.getRepartition().getSortedPlayersAfter(dealer);
        NumberMap<Integer,Integer> distributionChien_ = repartition_.getDistributionAuChien();
        for (int i : repartition_.getDistribution()) {
            //i == nombre_ de_ cartes_ a donner_
            for (int j : ordreDisributionJoueurs_) {
                for (int k = CustList.FIRST_INDEX; k < i; k++) {
                    deal.get(j).ajouter(deck.jouer(CustList.FIRST_INDEX));
                }
                if(distributionChien_.contains(iterations_)) {
                    int nbCartes_ = distributionChien_.getVal(iterations_);
                    for (int k = CustList.FIRST_INDEX; k < nbCartes_; k++) {
                        deal.last().ajouter(deck.jouer(CustList.FIRST_INDEX));
                    }
                }
                iterations_++;
            }
        }
    }

    void jouer(CardTarot _ct) {
        deal.get(NUMERO_UTILISATEUR).jouer(_ct);
    }
    void jouer(byte _joueur,CardTarot _ct) {
        deal.get(_joueur).jouer(_ct);
    }
    void ajouter(byte _joueurAyantJoue, CardTarot _carte) {
        deal.get(_joueurAyantJoue).ajouter(_carte);
    }
    void ajouterCartes(byte _joueur,HandTarot _main) {
        deal.get(_joueur).ajouterCartes(_main);
    }
    void ajouterCartesUtilisateur(HandTarot _main) {
        deal.get(NUMERO_UTILISATEUR).ajouterCartes(_main);
    }
    void supprimerCartes(byte _joueur) {
        deal.get(_joueur).supprimerCartes();
    }
    void supprimerCartes(byte _joueur,HandTarot _main) {
        deal.get(_joueur).supprimerCartes(_main);
    }
    void ajouterUtilisateur(CardTarot _ct) {
        deal.get(NUMERO_UTILISATEUR).ajouter(_ct);
    }
    void trier(byte _joueur, EnumList<Suit> _couleurs, boolean _decroissant) {
        deal.get(_joueur).trier(_couleurs, _decroissant);
    }
    public HandTarot derniereMain() {
        return deal.last();
    }

    public HandTarot main(byte _i) {
        return deal.get(_i);
    }
    /** Renvoie la main de l'utilisateur */
    public HandTarot main() {
        return deal.get(NUMERO_UTILISATEUR);
    }

    EqList<HandTarot> getDonne() {
        return deal;
    }

    public byte nombreDeMains() {
        return (byte) deal.size();
    }

    @Override
    public Iterator<HandTarot> iterator() {
        return deal.iterator();
    }
    public EqList<HandTarot> getDeal() {
        return deal;
    }
    public void setDeal(EqList<HandTarot> _deal) {
        deal = _deal;
    }
    public byte getDealer() {
        return dealer;
    }
    public void setDealer(byte _dealer) {
        dealer = _dealer;
    }
    public long getNbDeals() {
        return nbDeals;
    }
    public void setNbDeals(long _nbDeals) {
        nbDeals = _nbDeals;
    }
}
