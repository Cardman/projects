package cards.president;
import java.util.Iterator;

import cards.consts.MixCardsChoice;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.*;
import code.util.core.IndexConstants;


public final class DealPresident implements Iterable<HandPresident> {

    public static final int  NUMERO_UTILISATEUR = 0;

    /** Ensemble des mains des joueurs */
    private CustList<HandPresident> deal = new CustList<HandPresident>();
    /** donneur est un entier allant de 0 a nombre de joueurs-1 */
    private int dealer;
    /** nombre de parties jouees depuis le lancement */
    private long nbDeals;
    /** Pile de distribution pour initialiser la donne */
//    private HandPresident deck;

    public DealPresident(){}
    public DealPresident(long _nombreDeParties) {
        //nombreDeParties_ est_ necessaire_ pour_ savoir_ si_ c'est_ la_ premiere_ fois_ qu_'une_ partie_ est_ joue_,
        //pile est_ necessaire_ pour_ savoir_ si_ on_ ne_ distribue_ jamais_ jouees_ depuis_ le_ lancement_
        nbDeals = _nombreDeParties;
//        deck = _ppile;
    }

    /** Utilise pour editer une partie non solitaire */
    public DealPresident(CustList<HandPresident> _pdonne, int _pdonneur) {
        deal = _pdonne;
        dealer = _pdonneur;
    }

    public DealPresident(DealPresident _deal) {
        deal = new CustList<HandPresident>();
        for (HandPresident h: _deal) {
            HandPresident h_ = new HandPresident();
            h_.ajouterCartes(h);
            deal.add(h_);
        }
        dealer = _deal.dealer;
        nbDeals = _deal.nbDeals;
    }

//    /** Initialise de maniere aleatoire le premier donneur */
//    public void setRandomDealer(RulesPresident _regles, AbstractGenerator _gene) {
//        // On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
////        dealer = (byte) (_regles.getNbPlayers() * MonteCarlo
////                .randomDouble());
//        dealer = (byte)MonteCarloUtil.randomLong(_regles.getNbPlayers(),_gene);
//    }

    /**
    Apres une partie la joueur apres le donneur actuel devient le nouveau
    donneur
    */
    public void donneurSuivant(int _nouveauDonneur, RulesPresident _regles) {
        dealer = _nouveauDonneur;
        // On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
        dealer++;
        dealer %= _regles.getNbPlayers();
    }

    /**
    Distribue les cartes de maniere aleatoire ou non selon les parametres de
    distribution, on ne tient pas compte du sens de distribution
    */
    public void initDonne(RulesPresident _regles,AbstractGenerator _gene, HandPresident _ppile) {
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

    /**
    On distribue les cartes en les battant ceci est essentiel pour le
    solitaire car la fin de partie de solitaire ne depend pas a priori de la
    distribution au debut
    */
    private void donnerEnBattant(RulesPresident _regles,AbstractGenerator _gene) {

        int nbJrs_ = _regles.getNbPlayers();
        for (int i = IndexConstants.FIRST_INDEX; i < nbJrs_; i++) {
            deal.add(new HandPresident());
        }
        HandPresident m = HandPresident.stack(_regles.getNbStacks());
//        for (int i = CustList.FIRST_INDEX; i < n; i++) {
//            m.ajouterCartes(HandPresident.pileBase());
//        }
        int nombreTotalCarteJoueurs_ = m.total();

        for (int i = IndexConstants.FIRST_INDEX; i < nombreTotalCarteJoueurs_; i++) {
            deal.get(i % nbJrs_).ajouter(m.tirerUneCarteAleatoire(_gene));
        }
    }

    /**
    On distribue les cartes sans les cartes ce qui ressemble plus a la
    realite On ne tient pas compte du sens de distribution
    */
    private void donnerSansBattre(RulesPresident _regles, HandPresident _ppile) {
        _ppile.couper();
        /* On recupere_ le_ nombre_ de_ joueurs_ jouant_ au_ tarot_ */
        int nbJrs_ = _regles.getNbPlayers();
        /* On prepare_ les_ mains_ des_ joueurs_ */
        for (int i = IndexConstants.FIRST_INDEX; i < nbJrs_; i++) {
            deal.add(new HandPresident());
        }
        Ints ordreDisributionJoueurs_;
        ordreDisributionJoueurs_ = _regles.getSortedPlayersAfter(dealer);
        while (!_ppile.estVide()) {
            //i == nombre_ de_ cartes_ a donner_
            for (int j : ordreDisributionJoueurs_) {
                if (_ppile.estVide()) {
                    break;
                }
                deal.get(j).ajouter(_ppile.jouer(IndexConstants.FIRST_INDEX));
            }
        }
    }

    void ajouterCartes(int _joueur,HandPresident _main) {
        deal.get(_joueur).ajouterCartes(_main);
    }

    void supprimerCartes(int _joueur) {
        deal.get(_joueur).supprimerCartes();
    }
    void supprimerCartes(int _joueur,HandPresident _main) {
        deal.get(_joueur).supprimerCartes(_main);
    }

    void trier(int _joueur, boolean _decroissant, boolean _reverse) {
        deal.get(_joueur).sortCards(_decroissant, _reverse);
    }

    public HandPresident hand(int _i) {
        return deal.get(_i);
    }
    /** Renvoie la main de l'utilisateur */
    public HandPresident hand() {
        return deal.get(NUMERO_UTILISATEUR);
    }

    public int nombreDeMains() {
        return deal.size();
    }

    @Override
    public Iterator<HandPresident> iterator() {
        return deal.iterator();
    }
    public CustList<HandPresident> getDeal() {
        return deal;
    }
    public void setDeal(CustList<HandPresident> _deal) {
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
