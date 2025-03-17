package cards.tarot;
import java.util.Iterator;

import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.ChoiceTarot;
import cards.tarot.enumerations.DealingTarot;
import code.maths.LgInt;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.CustList;
import code.util.IdList;
import code.util.*;
import code.util.core.IndexConstants;


public final class DealTarot implements Iterable<HandTarot> {

    public static final int NUMERO_UTILISATEUR = 0;

    public static final int NB_CARDS = 78;
    private static final int NB_THREE = 24;
    private static final int NB_FOUR = 18;
    private static final int NB_FIVE = 15;
    private static final int NB_SIX = 12;
    private static final int NB_THREE_MAX = 21;
    private static final int NB_FIVE_MAX = 14;
    private static final int NB_FOUR_MIN = 13;
    private static final int NB_FIVE_MIN = 10;
    private static final int NB_SAVE_THREE_MAX = 5;
    private static final int NB_SAVE_FOUR_MAX = 4;
    private static final int NB_SAVE_GREAT_MAX = 3;

    /** Ensemble des mains des joueurs */
    private CustList<HandTarot> deal = new CustList<HandTarot>();
    /** donneur est un entier allant de 0 a nombre de joueurs-1 */
    private int dealer;
    /** nombre de parties jouees depuis le lancement */
    private long nbDeals;
    /** Pile de distribution pour initialiser la donne */

    public DealTarot(){}

    // appele au chargement d'une partie, entrainement tarot
    public DealTarot(long _nombreDeParties) {
        // nombreDeParties_ est_ necessaire_ pour_ savoir_ si_ c'est_ la_ premiere_ fois_ qu_'une_ partie_ est_ joue_,
        nbDeals = _nombreDeParties;
    }

    /** Utilise pour editer une partie non solitaire */
    public DealTarot(CustList<HandTarot> _pdonne, int _pdonneur) {
        deal = _pdonne;
        dealer = _pdonneur;
    }

    public DealTarot(DealTarot _deal) {
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
    public void setRandomDealer(RulesTarot _regles, AbstractGenerator _gene) {
        // On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
//        dealer = (byte) (_regles.getRepartition().getNombreJoueurs() * MonteCarlo.randomDouble());
        dealer = (int) MonteCarloUtil.randomLong(_regles.getDealing().getId().getNombreJoueurs(),_gene);
    }

    /**
    Apres une partie la joueur apres le donneur actuel devient le nouveau
    donneur
    */
    public void donneurSuivant(int _nouveauDonneur, RulesTarot _regles) {
        dealer = _nouveauDonneur;
        // On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
        dealer++;
        dealer %= _regles.getDealing().getId().getNombreJoueurs();
    }

    /**
    Distribue les cartes de maniere aleatoire ou non selon les parametres de
    distribution, on ne tient pas compte du sens de distribution
    */
    public void initDonne(RulesTarot _regles,AbstractGenerator _gene, HandTarot _ppile) {
        if (_regles.getCommon().getMixedCards() == MixCardsChoice.EACH_DEAL) {
            donnerEnBattant(_regles,_gene);
        } else if (_regles.getCommon().getMixedCards() == MixCardsChoice.EACH_LAUNCHING
                || _regles.getCommon().getMixedCards() == MixCardsChoice.ONCE_ONLY) {
            if (nbDeals == 0) {
                donnerEnBattant(_regles,_gene);
            } else {
                donnerSansBattre(_regles,_ppile);
            }
        } else {
            donnerSansBattre(_regles,_ppile);
        }
    }

    /** Pour l&#39;entra&icirc;nement surtout au tarot */
    public void initDonne(ChoiceTarot _choix,
                          RulesTarot _regles, AbstractGenerator _gene) {
        /* Les deux_ nombres_ donnent_ le_ nombre_ d atouts_ avec_ Excuse */
        int nbPlayers_ = _regles.getDealing().getId().getNombreJoueurs();
        int nbCards_ = _regles.getDealing().getNombreCartesParJoueur();
        int minAtout_ = min(_choix, nbCards_);
        int maxAtout_ = max(_choix, nbCards_);
        int atoutsTires_;
        int autresCartesTirer_;
        HandTarot atouts_;
        HandTarot autresCartes_;
        if (_choix == ChoiceTarot.HUNT_SMALL) {
            CustList<LgInt> fonctionRepartition_ = repartitionHunt(nbCards_, minAtout_, maxAtout_, nbCards_, 0L);
            LgInt alea_ = MonteCarloUtil.randomLgInt(fonctionRepartition_.last(), _gene);
            atoutsTires_ = chosenTrumps(minAtout_, maxAtout_, fonctionRepartition_, alea_);
            autresCartesTirer_ = nbCards_ - atoutsTires_;
            for (int i = IndexConstants.FIRST_INDEX; i <= nbPlayers_; i++) {
                deal.add(new HandTarot());
            }
            atouts_ = new HandTarot();
            autresCartes_ = new HandTarot();
            atouts_.ajouter(CardTarot.excuse());
            atouts_.ajouterCartes(HandTarot.atoutsSansExcuse());
            autresCartes_.ajouterCartes(HandTarot.cartesCouleurs());
        } else {
            CustList<LgInt> fonctionRepartition_ = repartitionHunt(nbCards_, minAtout_, maxAtout_, nbCards_ - minAtout_ - 1L, 1L);
            LgInt alea_ = MonteCarloUtil.randomLgInt(fonctionRepartition_.last(), _gene);
            atoutsTires_ = chosenTrumps(minAtout_, maxAtout_, fonctionRepartition_, alea_);
            autresCartesTirer_ = nbCards_ - atoutsTires_ - 1;
            for (int i = IndexConstants.FIRST_INDEX; i <= nbPlayers_; i++) {
                deal.add(new HandTarot());
            }
            atouts_ = new HandTarot();
            atouts_.ajouter(CardTarot.excuse());
            atouts_.ajouterCartes(HandTarot.atoutsSansExcuse());
            atouts_.jouer(CardTarot.petit());
            autresCartes_ = new HandTarot();
            autresCartes_.ajouterCartes(HandTarot.cartesCouleurs());
            deal.first().ajouter(CardTarot.petit());
        }
        feedUserHand(autresCartesTirer_, atoutsTires_, atouts_, autresCartes_,_gene);
        HandTarot deck_ = new HandTarot();
        deck_.ajouterCartes(atouts_);
        deck_.ajouterCartes(autresCartes_);
        dealToPlayrs(_regles, _gene, nbPlayers_, nbCards_, deck_);
    }

    public static CustList<LgInt> repartitionHunt(long _nbCards, int _minAtout, int _maxAtout, long _nb, long _d) {
        CustList<LgInt> fonctionRepartition_ = new CustList<LgInt>();
        fonctionRepartition_.add(LgInt.multiply(
                LgInt.among(new LgInt(_minAtout), new LgInt(NB_THREE_MAX)),
                LgInt.among(new LgInt(_nb), new LgInt(56))));
        int index_ = _minAtout + 1;
        for (int evenement_ = index_; evenement_ <= _maxAtout; evenement_++) {
            fonctionRepartition_.add(LgInt.plus(
                    fonctionRepartition_.last(), LgInt
                    .multiply(LgInt.among(new LgInt(evenement_),
                            new LgInt(NB_THREE_MAX)), LgInt.among(
                            new LgInt(_nbCards - evenement_ - _d),
                            new LgInt(56)))));
        }
        return fonctionRepartition_;
    }

    private int max(ChoiceTarot _choix, long _nbCards) {
        if (_choix == ChoiceTarot.HUNT_SMALL) {
            return sw(_nbCards, NB_THREE_MAX, NB_FOUR, NB_FIVE, NB_SIX, NB_FIVE_MAX);
        }
        if (_choix == ChoiceTarot.SAVE_SMALL) {
            return sw(_nbCards,NB_SAVE_THREE_MAX,NB_SAVE_FOUR_MAX,NB_SAVE_GREAT_MAX,NB_SAVE_GREAT_MAX,2);
        }
        return sw(_nbCards,NB_THREE_MAX,17,NB_FIVE_MAX,11,NB_FOUR_MIN);
    }

    private int min(ChoiceTarot _choix, long _nbCards) {
        if (_choix == ChoiceTarot.HUNT_SMALL) {
            return sw(_nbCards,NB_FIVE,NB_FOUR_MIN,NB_FIVE_MIN,9,NB_FIVE_MIN);
        }
        if (_choix == ChoiceTarot.SAVE_SMALL) {
            return sw(_nbCards,1,1,1,1,1);
        }
        return sw(_nbCards,NB_FIVE_MAX,NB_SIX,9,8,9);
    }

    private int sw(long _nbCards, int _three, int _four, int _five, int _six, int _other) {
        int nb_;
        if (_nbCards == NB_THREE) {
            nb_ = _three;
        } else if (_nbCards == NB_FOUR) {
            nb_ = _four;
        } else if (_nbCards == NB_FIVE) {
            nb_ = _five;
        } else if (_nbCards == NB_SIX) {
            nb_ = _six;
        } else {
            nb_ = _other;
        }
        return nb_;
    }

    private void dealToPlayrs(RulesTarot _regles, AbstractGenerator _gene, int _nbPlayers, int _nbCards, HandTarot _ppile) {
        int reste_ = NB_CARDS - _nbCards * _nbPlayers;
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
            if (joueur_ == NUMERO_UTILISATEUR) {
                continue;
            }
            for (int indiceCarte_ = IndexConstants.SIZE_EMPTY; indiceCarte_ < _nbCards; indiceCarte_++) {
                deal.get(joueur_).ajouter(_ppile.tirerUneCarteAleatoire(_gene));
            }
        }
        for (int i = IndexConstants.SIZE_EMPTY; i < reste_; i++) {
            deal.last().ajouter(_ppile.jouer(0));
        }
        setRandomDealer(_regles, _gene);
    }

    public void feedUserHand(int _autresCartesTirer, int _atoutsTires, HandTarot _atouts, HandTarot _autresCartes,AbstractGenerator _gene) {
        for (int tirage_ = IndexConstants.SIZE_EMPTY; tirage_ < _atoutsTires; tirage_++) {
            deal.get(NUMERO_UTILISATEUR).ajouter(_atouts.tirerUneCarteAleatoire(_gene));
        }
        for (int tirage_ = IndexConstants.SIZE_EMPTY; tirage_ < _autresCartesTirer; tirage_++) {
            deal.get(NUMERO_UTILISATEUR).ajouter(_autresCartes.tirerUneCarteAleatoire(_gene));
        }
    }

    static int chosenTrumps(int _minAtout, int _maxAtout, CustList<LgInt> _fonctionRepartition, LgInt _alea) {
        int atoutsTires_ = _maxAtout;
        for (int evenement_ = _minAtout; evenement_ <= _maxAtout; evenement_++) {
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
    private void donnerEnBattant(RulesTarot _regles,AbstractGenerator _gene) {

        int nbJrs_ = _regles.getDealing().getId().getNombreJoueurs();
        ajouterMainVidesLarge(deal, nbJrs_);
        HandTarot m = HandTarot.pileBase();
        int nombreTotalCarteJoueurs_ = nbJrs_ * _regles.getDealing().getNombreCartesParJoueur();
        int nombreCarteChien_ = _regles.getDealing()
                .getNombreCartesChien();

        for (int i = IndexConstants.FIRST_INDEX; i < nombreTotalCarteJoueurs_; i++) {
            deal.get(i % nbJrs_).ajouter(m.tirerUneCarteAleatoire(_gene));
        }
        for (int i = IndexConstants.FIRST_INDEX; i < nombreCarteChien_; i++) {
            deal.last().ajouter(m.jouer(IndexConstants.FIRST_INDEX));
        }
    }

    static void ajouterMainVidesLarge(CustList<HandTarot> _deal, int _nbJrs) {
        ajouterMainVides(_deal, _nbJrs+1);
    }

    static void ajouterMainVides(CustList<HandTarot> _deal, int _nbJrs) {
        for (int i = IndexConstants.FIRST_INDEX; i < _nbJrs; i++) {
            _deal.add(new HandTarot());
        }
    }

    /**
    On distribue les cartes sans les cartes ce qui ressemble plus a la
    realite On ne tient pas compte du sens de distribution
    */
    private void donnerSansBattre(RulesTarot _regles, HandTarot _ppile) {
        _ppile.couper();
        DealingTarot repartition_ = _regles.getDealing();
        /* On recupere_ le_ nombre_ de_ joueurs_ jouant_ au_ tarot_ */
        int nbJrs_ = repartition_.getId().getNombreJoueurs();
        /* On prepare_ les_ mains_ des_ joueurs_ */
        ajouterMainVidesLarge(deal, nbJrs_);
        int iterations_ = IndexConstants.SIZE_EMPTY;
        Ints ordreDisributionJoueurs_;
        ordreDisributionJoueurs_ = _regles.getDealing().getId().getSortedPlayersAfter(dealer);
        IntMap<Integer> distributionChien_ = repartition_.getDistributionAuChien();
        for (int i : repartition_.getDistribution()) {
            //i == nombre_ de_ cartes_ a donner_
            for (int j : ordreDisributionJoueurs_) {
                for (int k = IndexConstants.FIRST_INDEX; k < i; k++) {
                    deal.get(j).ajouter(_ppile.jouer(IndexConstants.FIRST_INDEX));
                }
                if(distributionChien_.contains(iterations_)) {
                    int nbCartes_ = distributionChien_.getVal(iterations_);
                    for (int k = IndexConstants.FIRST_INDEX; k < nbCartes_; k++) {
                        deal.last().ajouter(_ppile.jouer(IndexConstants.FIRST_INDEX));
                    }
                }
                iterations_++;
            }
        }
    }

    void jouer(int _joueur,CardTarot _ct) {
        deal.get(_joueur).jouer(_ct);
    }
    void ajouter(int _joueurAyantJoue, CardTarot _carte) {
        deal.get(_joueurAyantJoue).ajouter(_carte);
    }
    void ajouterCartes(int _joueur,HandTarot _main) {
        deal.get(_joueur).ajouterCartes(_main);
    }

    void supprimerCartes(int _joueur) {
        deal.get(_joueur).supprimerCartes();
    }
    void supprimerCartes(int _joueur,HandTarot _main) {
        deal.get(_joueur).supprimerCartes(_main);
    }
    void trier(int _joueur, IdList<Suit> _couleurs, boolean _decroissant) {
        deal.get(_joueur).trier(_couleurs, _decroissant);
    }
    public HandTarot derniereMain() {
        return deal.last();
    }

    public HandTarot hand(int _i) {
        return deal.get(_i);
    }
    /** Renvoie la main de l'utilisateur */
    public HandTarot hand() {
        return deal.get(NUMERO_UTILISATEUR);
    }

    public int nombreDeMains() {
        return deal.size();
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
    public int getDealer() {
        return dealer;
    }
    public void setDealer(int _dealer) {
        dealer = _dealer;
    }
    public long getNbDeals() {
        return nbDeals;
    }
    public void setNbDeals(long _nbDeals) {
        nbDeals = _nbDeals;
    }
}
