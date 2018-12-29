package cards.belote;
import java.util.Iterator;

import cards.belote.enumerations.CardBelote;
import cards.consts.MixCardsChoice;
import cards.consts.Order;
import cards.consts.Suit;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;
import code.util.Numbers;


public final class DealBelote implements Iterable<HandBelote> {

    public static final byte NUMERO_UTILISATEUR = 0;

    /**Ensemble des mains des joueurs*/
    private EqList<HandBelote> deal=new EqList<HandBelote>();
    /**donneur est un entier allant de 0 a nombre de joueurs-1*/
    private byte dealer;
    /**nombre de parties jouees depuis le lancement*/
    private long nbDeals;
    /**Pile de distribution pour initialiser la donne*/
    private HandBelote deck;

    public DealBelote() {}
    public DealBelote(long _nombreDeParties,
            HandBelote _ppile) {
        //info_ est_ le_ vecteur_ d'informations_ pour_ le_ jeu_, nombreDeParties_ est_ necessaire_ pour_ savoir_ si_ c'est_
        //la_ premiere_ fois_ qu_'une_ partie_ est_ joue_, pile est_ necessaire_ pour_ savoir_ si_ on_ ne_ distribue_ jamais_
        nbDeals=_nombreDeParties;
        //jouees_ depuis_ le_ lancement_
        deck=_ppile;
    }

    public DealBelote(long _nombreDeParties) {
        //j est_ le_ jeu_ a jouer_, info_ est_ le_ vecteur_ d'informations_ pour_ le_ jeu_, nombreDeParties_ est_ necessaire_ pour_ savoir_ si_ c'est_
        //la_ premiere_ fois_ qu_'une_ partie_ est_ joue_, pile est_ necessaire_ pour_ savoir_ si_ on_ ne_ distribue_ jamais_
        nbDeals=_nombreDeParties;
        //jouees_ depuis_ le_ lancement_
    }

    public DealBelote(EqList<HandBelote> _pdonne,
            byte _pdonneur) {
        deal=_pdonne;
        dealer=_pdonneur;
    }

    DealBelote(DealBelote _deal) {
        deal = new EqList<HandBelote>();
        for (HandBelote h: _deal) {
            HandBelote h_ = new HandBelote();
            h_.ajouterCartes(h);
            deal.add(h_);
        }
        dealer = _deal.dealer;
        nbDeals = _deal.nbDeals;
    }
    /**Initialise de maniere aleatoire le premier donneur*/
    public void setRandomDealer(int _nbJoueurs) {
        //On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
//        dealer=(byte)(_nbJoueurs*MonteCarlo.randomDouble());
        dealer = (byte)AbMonteCarlo.randomLong(_nbJoueurs);
    }
    /**Initialise le donneur pour editer une partie*/
    public void initDonneur(byte _b) {
        //On initialise_ le_ donneur par_ l'editeur_
        dealer=_b;
    }
    /**Apres une partie la joueur apres le donneur actuel devient le nouveau donneur*/
    public void donneurSuivant(byte _nouveauDonneur,int _nbJoueurs) {
        dealer=_nouveauDonneur;
        //On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
        dealer++;
        dealer%=_nbJoueurs;
    }
    public long getNombreDeParties() {
        return nbDeals;
    }
    public byte getDonneur() {
        return dealer;
    }
    /**Distribue les cartes de maniere aleatoire ou non selon les parametres de distribution, on ne tient pas compte du sens de distribution*/
    public void initDonne(RulesBelote _regles, DisplayingBelote _display) {
        if(_regles.getCartesBattues()==MixCardsChoice.EACH_DEAL) {
            donnerEnBattant(_regles,_display);
        } else if(_regles.getCartesBattues()==MixCardsChoice.EACH_LAUNCHING||_regles.getCartesBattues()==MixCardsChoice.ONCE_ONLY) {
            if(nbDeals==0) {
                donnerEnBattant(_regles,_display);
            } else {
                donnerSansBattre(_regles);
            }
        } else {
            donnerSansBattre(_regles);
        }
    }

    /**On distribue les cartes sans les cartes ce qui ressemble plus a la realite
    On ne tient pas compte du sens de distribution*/
    private void donnerSansBattre(RulesBelote _regles) {
        deck.couper();
        /*On cree_ les_ mains_ des_ joueurs_ puis_ le_ talon_ qui_ sera_ distribue_
        apres_ les_ encheres_*/
        int nbHands_ = _regles.getRepartition().getNombreJoueurs();
        nbHands_++;
        for (int i = CustList.FIRST_INDEX; i < nbHands_; i++) {
            deal.add(new HandBelote());
        }
        /*On donne les_ cartes_ aux_ joueurs_.
        Le nombre_ de_ cartes_ donnes_ par_ joueur_ est_ de_ 3 puis_ 2*/
        //int nbJoueurs_ = _regles.getRepartition().getNombreJoueurs();
        Numbers<Byte> ordreDisributionJoueurs_;
        ordreDisributionJoueurs_ = _regles.getRepartition().getSortedPlayersAfter(dealer);

        for(int i: _regles.getRepartition().getDistributionDebut()) {
            for (int j : ordreDisributionJoueurs_) {
                for (int k = CustList.FIRST_INDEX; k < i; k++) {
                    deal.get(j).ajouter(deck.jouer(CustList.FIRST_INDEX));
                }
            }
        }
        /*On ajoute_ le_ reste_ des_ cartes_ dans_ le_ talon_*/
        int total_ = deck.total();
        for (int i = CustList.FIRST_INDEX; i < total_; i++) {
            deal.last().ajouter(deck.jouer(CustList.FIRST_INDEX));
        }
    }
    /**On distribue les cartes en les battant
    ceci est essentiel pour le solitaire car la fin de partie de solitaire
    ne depend pas a priori de la distribution au debut*/
    private void donnerEnBattant(RulesBelote _regles, DisplayingBelote _displaying) {
        int nbJoueurs_ = _regles.getRepartition().getNombreJoueurs();
        int nbHands_ = nbJoueurs_;
        nbHands_++;
        for (int i = CustList.FIRST_INDEX; i < nbHands_; i++) {
            deal.add(new HandBelote());
        }
        HandBelote m = HandBelote.pileBase();
        int nbCartesJoueurDebut_ = _regles.getRepartition().getFirstCards();
        int nbCartesJoueursDebut_ = nbCartesJoueurDebut_ * _regles.getRepartition().getNombreJoueurs();
        for (int i = CustList.FIRST_INDEX; i < nbCartesJoueursDebut_; i++) {
            //On distribue_ les_ 1eres cartes_ des_ joueurs_ aleatoirement_
            deal.get(i/nbCartesJoueurDebut_).ajouter(m.tirerUneCarteAleatoire());
        }
        if (!_regles.dealAll()) {
            int total_ = m.total();
            for (int i = CustList.SECOND_INDEX; i < total_; i++) {
                deal.last().ajouter(m.tirerUneCarteAleatoire());
            }
            deal.last().ajouter(m.premiereCarte());
        }
        for (int i = CustList.FIRST_INDEX; i < nbJoueurs_; i++) {
            deal.get(i).trier(_displaying.getCouleurs(), _displaying.getDecroissant(), _displaying.getOrdreAvantEncheres());
        }

    }

    void completerDonne(byte _preneur,RulesBelote _regles) {
        HandBelote talon_=new HandBelote();
        talon_.ajouterCartes(derniereMain());
        /*Copie du_ talon_ original_ pour_ donner_ des_ cartes_ aux_ joueurs_*/
        if(talon_.estVide()) {
            return;
        }
        main(_preneur).ajouter(talon_.jouer(CustList.FIRST_INDEX));
        //Le preneur_ prend_ la_ carte_ du_ dessus_
        boolean dejaCommence_ = false;
        Numbers<Byte> ordreDisributionJoueurs_;
        ordreDisributionJoueurs_ = _regles.getRepartition().getSortedPlayersAfter(dealer);
        for(int i: _regles.getRepartition().getDistributionFin()) {
            for (int j : ordreDisributionJoueurs_) {
                for (int k = CustList.SECOND_INDEX; k < i; k++) {
                    deal.get(j).ajouter(talon_.jouer(CustList.FIRST_INDEX));
                }
                if(j!=_preneur || dejaCommence_) {
                    deal.get(j).ajouter(talon_.jouer(CustList.FIRST_INDEX));
                }
            }
            dejaCommence_ = true;
        }
    }
    /**Renvoie la main de l'utilisateur*/
    public HandBelote main() {
        return deal.get(NUMERO_UTILISATEUR);
    }
    public void jouer(CardBelote _carteJouee) {
        deal.get(NUMERO_UTILISATEUR).jouer(_carteJouee);
    }
    public HandBelote derniereMain() {
        return deal.last();
    }
    public HandBelote main(byte _i) {
        return deal.get(_i);
    }
    public void ajouter(byte _preneur, CardBelote _jouer) {
        deal.get(_preneur).ajouter(_jouer);
    }
    public void setOrdre(byte _joueur, Order _couleur) {
        deal.get(_joueur).setOrdre(_couleur);
    }
    public void trier(byte _joueur, EnumList<Suit> _couleurs, boolean _decroissant, Order _couleur) {
        deal.get(_joueur).trier(_couleurs, _decroissant, _couleur);
    }
    public void trier(byte _joueur, EnumList<Suit> _couleurs, boolean _decroissant, Suit _carteAppelee) {
        deal.get(_joueur).trier(_couleurs, _decroissant, _carteAppelee);
    }
    public void jouer(byte _preneur, CardBelote _carteJouee) {
        deal.get(_preneur).jouer(_carteJouee);
    }
    public void supprimerCartes(byte _joueur, HandBelote _derniereMain) {
        deal.get(_joueur).supprimerCartes(_derniereMain);
    }
    public void supprimerCartes(byte _joueur) {
        deal.get(_joueur).supprimerCartes();
    }

    public byte nombreDeMains() {
        return (byte)deal.size();
    }

    EqList<HandBelote> getDonne() {
        return deal;
    }

    @Override
    public Iterator<HandBelote> iterator() {
        return deal.iterator();
    }
    public EqList<HandBelote> getDeal() {
        return deal;
    }
    public void setDeal(EqList<HandBelote> _deal) {
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
