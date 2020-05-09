package cards.tarot;
import java.util.Iterator;

import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.ChoiceTarot;
import cards.tarot.enumerations.DealingTarot;
import code.maths.LgInt;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;
import code.util.*;


public final class DealTarot implements Iterable<HandTarot> {

    public static final byte NUMERO_UTILISATEUR = 0;

    public static final int NB_CARDS = 78;

    /** Ensemble des mains des joueurs */
    private CustList<HandTarot> deal = new CustList<HandTarot>();
    /** donneur est un entier allant de 0 a nombre de joueurs-1 */
    private byte dealer;
    /** nombre de parties jouees depuis le lancement */
    private long nbDeals;
    /** Pile de distribution pour initialiser la donne */
    private HandTarot deck;

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
    public DealTarot(CustList<HandTarot> _pdonne, byte _pdonneur) {
        deal = _pdonne;
        dealer = _pdonneur;
    }

    DealTarot(DealTarot _deal) {
        deal = new CustList<HandTarot>();
        for (HandTarot h: _deal) {
            HandTarot h_ = new HandTarot();
            h_.ajouterCartes(h);
            deal.add(h_);
        }
        dealer = _deal.dealer;
        nbDeals = _deal.nbDeals;
    }

    /** Initialise de maniere aleatoire le premier donneur */
    public void setRandomDealer(RulesTarot _regles) {
        // On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
//        dealer = (byte) (_regles.getRepartition().getNombreJoueurs() * MonteCarlo.randomDouble());
        dealer = (byte)AbMonteCarlo.randomLong(_regles.getRepartition().getNombreJoueurs());
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
    public void initDonne(ChoiceTarot _choix,
                          RulesTarot _regles) {
        /* Les deux_ nombres_ donnent_ le_ nombre_ d atouts_ avec_ Excuse */
        byte minAtout_ = 0;
        byte maxAtout_ = 0;
        int nbPlayers_ = _regles.getRepartition().getNombreJoueurs();
        int nbCards_ = _regles.getRepartition().getNombreCartesParJoueur();
        byte autresCartesTirer_;
        CustList<LgInt> fonctionRepartition_;
        LgInt alea_;
        if (_choix == ChoiceTarot.HUNT_SMALL) {
            if (nbCards_ == 24) {
                minAtout_ = 15;
                maxAtout_ = 21;
            } else if (nbCards_ == 18) {
                minAtout_ = 13;
                maxAtout_ = 18;
            } else if (nbCards_ == 15) {
                minAtout_ = 10;
                maxAtout_ = 15;
            } else if (nbCards_ == 12) {
                minAtout_ = 9;
                maxAtout_ = 12;
            } else {
                minAtout_ = 10;
                maxAtout_ = 14;
            }
            fonctionRepartition_ = new CustList<LgInt>();
            fonctionRepartition_.add(LgInt.multiply(
                    LgInt.among(new LgInt(minAtout_), new LgInt(21)),
                    LgInt.among(new LgInt(nbCards_), new LgInt(56))));
            byte index_ = (byte) (minAtout_ + 1);
            for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
                fonctionRepartition_.add(LgInt.plus(
                        fonctionRepartition_.last(), LgInt
                        .multiply(LgInt.among(new LgInt(evenement_),
                                new LgInt(21)), LgInt.among(
                                new LgInt(nbCards_ - evenement_),
                                new LgInt(56)))));
            }
            alea_ = AbMonteCarlo.randomLgInt(fonctionRepartition_.last());
            byte atoutsTires_ = chosenTrumps(minAtout_, maxAtout_, fonctionRepartition_, alea_);
            autresCartesTirer_ = (byte) (nbCards_ - atoutsTires_);
            for (int i = CustList.FIRST_INDEX; i <= nbPlayers_; i++) {
                deal.add(new HandTarot());
            }
            HandTarot atouts_ = new HandTarot();
            HandTarot autresCartes_ = new HandTarot();
            atouts_.ajouter(CardTarot.excuse());
            atouts_.ajouterCartes(HandTarot.atoutsSansExcuse());
            autresCartes_.ajouterCartes(HandTarot.cartesCouleurs());
            feedUserHand(autresCartesTirer_, atoutsTires_, atouts_, autresCartes_);
            deck = new HandTarot();
            deck.ajouterCartes(atouts_);
            deck.ajouterCartes(autresCartes_);
            byte reste_ = (byte) (NB_CARDS - nbCards_ * nbPlayers_);
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers_; joueur_++) {
                if (joueur_ == NUMERO_UTILISATEUR) {
                    continue;
                }
                for (byte indiceCarte_ = CustList.SIZE_EMPTY; indiceCarte_ < nbCards_; indiceCarte_++) {
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
                if (nbCards_ == 24) {
                    maxAtout_ = 5;
                } else if (nbCards_ == 18) {
                    maxAtout_ = 4;
                } else if (nbCards_ == 15 || nbCards_ == 12) {
                    maxAtout_ = 3;
                } else {
                    maxAtout_ = 2;
                }
            } else {
                if (nbCards_ == 24) {
                    minAtout_ = 14;
                    maxAtout_ = 21;
                } else if (nbCards_ == 18) {
                    minAtout_ = 12;
                    maxAtout_ = 17;
                } else if (nbCards_ == 15) {
                    minAtout_ = 9;
                    maxAtout_ = 14;
                } else if (nbCards_ == 12) {
                    minAtout_ = 8;
                    maxAtout_ = 11;
                } else {
                    minAtout_ = 9;
                    maxAtout_ = 13;
                }
            }
            fonctionRepartition_ = new CustList<LgInt>();
            fonctionRepartition_.add(LgInt.multiply(LgInt.among(new LgInt(
                    minAtout_), new LgInt(21)), LgInt.among(new LgInt(
                    nbCards_ - minAtout_ - 1), new LgInt(56))));
            byte index_ = (byte) (minAtout_ + 1);
            for (byte evenement_ = index_; evenement_ <= maxAtout_; evenement_++) {
                fonctionRepartition_.add(LgInt.plus(
                        fonctionRepartition_.last(), LgInt
                                .multiply(LgInt.among(new LgInt(evenement_),
                                        new LgInt(21)), LgInt.among(
                                        new LgInt(nbCards_ - evenement_
                                                - 1), new LgInt(56)))));
            }
            alea_ = AbMonteCarlo.randomLgInt(fonctionRepartition_.last());
            byte atoutsTires_ = chosenTrumps(minAtout_, maxAtout_, fonctionRepartition_, alea_);
            autresCartesTirer_ = (byte) (nbCards_ - atoutsTires_ - 1);
            for (int i = CustList.FIRST_INDEX; i <= nbPlayers_; i++) {
                deal.add(new HandTarot());
            }
            HandTarot atouts_ = new HandTarot();
            atouts_.ajouter(CardTarot.excuse());
            atouts_.ajouterCartes(HandTarot.atoutsSansExcuse());
            atouts_.jouer(CardTarot.petit());
            HandTarot autresCartes_ = new HandTarot();
            autresCartes_.ajouterCartes(HandTarot.cartesCouleurs());
            deal.first().ajouter(CardTarot.petit());
            feedUserHand(autresCartesTirer_, atoutsTires_, atouts_, autresCartes_);
            deck = new HandTarot();
            deck.ajouterCartes(atouts_);
            deck.ajouterCartes(autresCartes_);
            byte reste_ = (byte) (NB_CARDS - nbCards_ * nbPlayers_);
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers_; joueur_++) {
                if (joueur_ == NUMERO_UTILISATEUR) {
                    continue;
                }
                for (byte indiceCarte_ = CustList.SIZE_EMPTY; indiceCarte_ < nbCards_; indiceCarte_++) {
                    deal.get(joueur_).ajouter(deck.tirerUneCarteAleatoire());
                }
            }
            for (int i = CustList.SIZE_EMPTY; i < reste_; i++) {
                deal.last().ajouter(deck.jouer(0));
            }
            setRandomDealer(_regles);
        }
    }

    public void feedUserHand(byte _autresCartesTirer, byte _atoutsTires, HandTarot _atouts, HandTarot _autresCartes) {
        for (byte tirage_ = CustList.SIZE_EMPTY; tirage_ < _atoutsTires; tirage_++) {
            deal.get(NUMERO_UTILISATEUR).ajouter(_atouts.tirerUneCarteAleatoire());
        }
        for (byte tirage_ = CustList.SIZE_EMPTY; tirage_ < _autresCartesTirer; tirage_++) {
            deal.get(NUMERO_UTILISATEUR).ajouter(_autresCartes.tirerUneCarteAleatoire());
        }
    }

    static byte chosenTrumps(byte _minAtout, byte _maxAtout, CustList<LgInt> _fonctionRepartition, LgInt _alea) {
        byte atoutsTires_ = _maxAtout;
        for (byte evenement_ = _minAtout; evenement_ <= _maxAtout; evenement_++) {
            if (LgInt.lowerEq(_alea, _fonctionRepartition.get(evenement_
                    - _minAtout))) {
                atoutsTires_ = evenement_;
                break;
            }
        }
        return atoutsTires_;
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
        Bytes ordreDisributionJoueurs_;
        ordreDisributionJoueurs_ = _regles.getRepartition().getSortedPlayersAfter(dealer);
        IntMap<Integer> distributionChien_ = repartition_.getDistributionAuChien();
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

    void jouer(byte _joueur,CardTarot _ct) {
        deal.get(_joueur).jouer(_ct);
    }
    void ajouter(byte _joueurAyantJoue, CardTarot _carte) {
        deal.get(_joueurAyantJoue).ajouter(_carte);
    }
    void ajouterCartes(byte _joueur,HandTarot _main) {
        deal.get(_joueur).ajouterCartes(_main);
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

    public HandTarot hand(byte _i) {
        return deal.get(_i);
    }
    /** Renvoie la main de l'utilisateur */
    public HandTarot hand() {
        return deal.get(NUMERO_UTILISATEUR);
    }

    public byte nombreDeMains() {
        return (byte) deal.size();
    }

    @Override
    public Iterator<HandTarot> iterator() {
        return deal.iterator();
    }
    public CustList<HandTarot> getDeal() {
        return deal;
    }
    public void setDeal(CustList<HandTarot> _deal) {
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
