package cards.president;
import java.util.Iterator;

import cards.consts.MixCardsChoice;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.annot.RwXml;

@RwXml
public final class DealPresident implements Iterable<HandPresident> {

    public static final byte NUMERO_UTILISATEUR = 0;

    public static final int NB_CARDS = HandPresident.pileBase().total();

    /** Ensemble des mains des joueurs */
    private EqList<HandPresident> deal = new EqList<HandPresident>();
    /** donneur est un entier allant de 0 a nombre de joueurs-1 */
    private byte dealer;
    /** nombre de parties jouees depuis le lancement */
    private long nbDeals;
    /** Pile de distribution pour initialiser la donne */
    private HandPresident deck;

    public DealPresident(){}
    public DealPresident(long _nombreDeParties, HandPresident _ppile) {
        //nombreDeParties_ est_ necessaire_ pour_ savoir_ si_ c'est_ la_ premiere_ fois_ qu_'une_ partie_ est_ joue_,
        //pile est_ necessaire_ pour_ savoir_ si_ on_ ne_ distribue_ jamais_ jouees_ depuis_ le_ lancement_
        nbDeals = _nombreDeParties;
        deck = _ppile;
    }

    // appele au chargement d'une partie, entrainement tarot
    public DealPresident(long _nombreDeParties) {
        // nombreDeParties_ est_ necessaire_ pour_ savoir_ si_ c'est_ la_ premiere_ fois_ qu_'une_ partie_ est_ joue_,
        nbDeals = _nombreDeParties;
    }

    /** Utilise pour editer une partie non solitaire */
    public DealPresident(EqList<HandPresident> _pdonne, byte _pdonneur) {
        deal = _pdonne;
        dealer = _pdonneur;
    }

    DealPresident(DealPresident _deal) {
        deal = new EqList<HandPresident>();
        for (HandPresident h: _deal) {
            HandPresident h_ = new HandPresident();
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
    public void setRandomDealer(RulesPresident _regles) {
        // On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
//        dealer = (byte) (_regles.getNbPlayers() * MonteCarlo
//                .randomDouble());
        dealer = (byte)AbMonteCarlo.randomInt(_regles.getNbPlayers());
    }

    /**
    Apres une partie la joueur apres le donneur actuel devient le nouveau
    donneur
    */
    public void donneurSuivant(byte _nouveauDonneur, RulesPresident _regles) {
        dealer = _nouveauDonneur;
        // On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
        dealer++;
        dealer %= _regles.getNbPlayers();
    }

    /**
    Distribue les cartes de maniere aleatoire ou non selon les parametres de
    distribution, on ne tient pas compte du sens de distribution
    */
    public void initDonne(RulesPresident _regles) {
        if (_regles.getMixedCards() == MixCardsChoice.EACH_DEAL) {
            donnerEnBattant(_regles);
        } else if (_regles.getMixedCards() == MixCardsChoice.EACH_LAUNCHING
                || _regles.getMixedCards() == MixCardsChoice.ONCE_ONLY) {
            if (nbDeals == 0) {
                donnerEnBattant(_regles);
            } else {
                donnerSansBattre(_regles);
            }
        } else {
            donnerSansBattre(_regles);
        }
    }

    /**
    On distribue les cartes en les battant ceci est essentiel pour le
    solitaire car la fin de partie de solitaire ne depend pas a priori de la
    distribution au debut
    */
    private void donnerEnBattant(RulesPresident _regles) {

        byte nbJrs_ = (byte) _regles.getNbPlayers();
        for (int i = CustList.FIRST_INDEX; i < nbJrs_; i++) {
            deal.add(new HandPresident());
        }
        HandPresident m = HandPresident.stack(_regles.getNbStacks());
//        for (int i = CustList.FIRST_INDEX; i < n; i++) {
//            m.ajouterCartes(HandPresident.pileBase());
//        }
        byte nombreTotalCarteJoueurs_ = (byte) (m.total());

        for (int i = CustList.FIRST_INDEX; i < nombreTotalCarteJoueurs_; i++) {
            deal.get(i % nbJrs_).ajouter(m.tirerUneCarteAleatoire());
        }
    }

    /**
    On distribue les cartes sans les cartes ce qui ressemble plus a la
    realite On ne tient pas compte du sens de distribution
    */
    private void donnerSansBattre(RulesPresident _regles) {
        deck.couper();
        /* On recupere_ le_ nombre_ de_ joueurs_ jouant_ au_ tarot_ */
        byte nbJrs_ = (byte) _regles.getNbPlayers();
        /* On prepare_ les_ mains_ des_ joueurs_ */
        for (int i = CustList.FIRST_INDEX; i < nbJrs_; i++) {
            deal.add(new HandPresident());
        }
        Numbers<Byte> ordreDisributionJoueurs_;
        ordreDisributionJoueurs_ = _regles.getSortedPlayersAfter(dealer);
        while (!deck.estVide()) {
            //i == nombre_ de_ cartes_ a donner_
            for (int j : ordreDisributionJoueurs_) {
                if (deck.estVide()) {
                    break;
                }
                deal.get(j).ajouter(deck.jouer(CustList.FIRST_INDEX));
            }
        }
    }

    void ajouterCartes(byte _joueur,HandPresident _main) {
        deal.get(_joueur).ajouterCartes(_main);
    }
    void ajouterCartesUtilisateur(HandPresident _main) {
        deal.get(NUMERO_UTILISATEUR).ajouterCartes(_main);
    }
    void supprimerCartes(byte _joueur) {
        deal.get(_joueur).supprimerCartes();
    }
    void supprimerCartes(byte _joueur,HandPresident _main) {
        deal.get(_joueur).supprimerCartes(_main);
    }

    void trier(byte _joueur, boolean _decroissant, boolean _reverse) {
        deal.get(_joueur).sortCards(_decroissant, _reverse);
    }

    public HandPresident derniereMain() {
        return deal.last();
    }

    public HandPresident main(byte _i) {
        return deal.get(_i);
    }
    /** Renvoie la main de l'utilisateur */
    public HandPresident main() {
        return deal.get(NUMERO_UTILISATEUR);
    }

    EqList<HandPresident> getDonne() {
        return deal;
    }

    public byte nombreDeMains() {
        return (byte) deal.size();
    }

    @Override
    public Iterator<HandPresident> iterator() {
        return deal.iterator();
    }
    public EqList<HandPresident> getDeal() {
        return deal;
    }
    public void setDeal(EqList<HandPresident> _deal) {
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
