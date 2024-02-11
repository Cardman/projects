package cards.belote;
import java.util.Iterator;

import cards.belote.enumerations.CardBelote;
import cards.consts.MixCardsChoice;
import cards.consts.Order;
import cards.consts.Suit;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.IdList;
import code.util.*;
import code.util.core.IndexConstants;


public final class DealBelote implements Iterable<HandBelote> {

    public static final byte NUMERO_UTILISATEUR = 0;

    /**Ensemble des mains des joueurs*/
    private CustList<HandBelote> deal=new CustList<HandBelote>();
    /**donneur est un entier allant de 0 a nombre de joueurs-1*/
    private byte dealer;
    /**nombre de parties jouees depuis le lancement*/
    private long nbDeals;
    /**Pile de distribution pour initialiser la donne*/

    public DealBelote() {}
    public DealBelote(long _nombreDeParties) {
        //info_ est_ le_ vecteur_ d'informations_ pour_ le_ jeu_, nombreDeParties_ est_ necessaire_ pour_ savoir_ si_ c'est_
        //la_ premiere_ fois_ qu_'une_ partie_ est_ joue_, pile est_ necessaire_ pour_ savoir_ si_ on_ ne_ distribue_ jamais_
        nbDeals=_nombreDeParties;
        //jouees_ depuis_ le_ lancement_
    }

    public DealBelote(CustList<HandBelote> _pdonne,
            byte _pdonneur) {
        deal=_pdonne;
        dealer=_pdonneur;
    }

    DealBelote(DealBelote _deal) {
        deal = new CustList<HandBelote>();
        for (HandBelote h: _deal) {
            HandBelote h_ = new HandBelote();
            h_.ajouterCartes(h);
            deal.add(h_);
        }
        dealer = _deal.dealer;
        nbDeals = _deal.nbDeals;
    }
//    /**Initialise de maniere aleatoire le premier donneur*/
//    public void setRandomDealer(int _nbJoueurs, AbstractGenerator _gene) {
//        //On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
////        dealer=(byte)(_nbJoueurs*MonteCarlo.randomDouble());
//        dealer = (byte)MonteCarloUtil.randomLong(_nbJoueurs,_gene);
//    }
    /**Apres une partie la joueur apres le donneur actuel devient le nouveau donneur*/
    public void donneurSuivant(byte _nouveauDonneur,int _nbJoueurs) {
        dealer=_nouveauDonneur;
        //On recupere_ le_ nombre_ de_ joueurs_ dans_ le_ cas_ d'un_ jeu_ non_ solitaire_
        dealer++;
        dealer%=_nbJoueurs;
    }
    /**Distribue les cartes de maniere aleatoire ou non selon les parametres de distribution, on ne tient pas compte du sens de distribution*/
    public void initDonne(RulesBelote _regles, DisplayingBelote _display,AbstractGenerator _gene,
                          HandBelote _ppile) {
        if(_regles.getCommon().getMixedCards() ==MixCardsChoice.EACH_DEAL) {
            donnerEnBattant(_regles,_display,_gene);
        } else if(_regles.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING|| _regles.getCommon().getMixedCards() ==MixCardsChoice.ONCE_ONLY) {
            if(nbDeals==0) {
                donnerEnBattant(_regles,_display,_gene);
            } else {
                donnerSansBattre(_regles,_ppile);
            }
        } else {
            donnerSansBattre(_regles,_ppile);
        }
    }

    /**On distribue les cartes sans les cartes ce qui ressemble plus a la realite
    On ne tient pas compte du sens de distribution*/
    private void donnerSansBattre(RulesBelote _regles,
                                  HandBelote _ppile) {
        _ppile.couper();
        /*On cree_ les_ mains_ des_ joueurs_ puis_ le_ talon_ qui_ sera_ distribue_
        apres_ les_ encheres_*/
        int nbHands_ = _regles.getDealing().getId().getNombreJoueurs();
        nbHands_++;
        ajouterMainVides(deal, nbHands_);
        /*On donne les_ cartes_ aux_ joueurs_.
        Le nombre_ de_ cartes_ donnes_ par_ joueur_ est_ de_ 3 puis_ 2*/
        //int nbJoueurs_ = _regles.getRepartition().getNombreJoueurs();
        Bytes ordreDisributionJoueurs_;
        ordreDisributionJoueurs_ = _regles.getDealing().getId().getSortedPlayersAfter(dealer);

        for(int i: _regles.getDealing().getDistributionDebut()) {
            for (int j : ordreDisributionJoueurs_) {
                for (int k = IndexConstants.FIRST_INDEX; k < i; k++) {
                    deal.get(j).ajouter(_ppile.jouer(IndexConstants.FIRST_INDEX));
                }
            }
        }
        /*On ajoute_ le_ reste_ des_ cartes_ dans_ le_ talon_*/
        int total_ = _ppile.total();
        for (int i = IndexConstants.FIRST_INDEX; i < total_; i++) {
            deal.last().ajouter(_ppile.jouer(IndexConstants.FIRST_INDEX));
        }
    }
    /**On distribue les cartes en les battant
    ceci est essentiel pour le solitaire car la fin de partie de solitaire
    ne depend pas a priori de la distribution au debut*/
    private void donnerEnBattant(RulesBelote _regles, DisplayingBelote _displaying,AbstractGenerator _gene) {
        int nbJoueurs_ = _regles.getDealing().getId().getNombreJoueurs();
        int nbHands_ = nbJoueurs_;
        nbHands_++;
        ajouterMainVides(deal, nbHands_);
        HandBelote m = HandBelote.pileBase();
        int nbCartesJoueurDebut_ = _regles.getDealing().getFirstCards();
        int nbCartesJoueursDebut_ = nbCartesJoueurDebut_ * _regles.getDealing().getId().getNombreJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nbCartesJoueursDebut_; i++) {
            //On distribue_ les_ 1eres cartes_ des_ joueurs_ aleatoirement_
            deal.get(i/nbCartesJoueurDebut_).ajouter(m.tirerUneCarteAleatoire(_gene));
        }
        if (!_regles.dealAll()) {
            int total_ = m.total();
            for (int i = IndexConstants.SECOND_INDEX; i < total_; i++) {
                deal.last().ajouter(m.tirerUneCarteAleatoire(_gene));
            }
            deal.last().ajouter(m.premiereCarte());
        }
        for (int i = IndexConstants.FIRST_INDEX; i < nbJoueurs_; i++) {
            deal.get(i).trier(_displaying.getDisplaying().getSuits(), _displaying.getDisplaying().isDecreasing(), _displaying.getOrderBeforeBids());
        }

    }

    static void ajouterMainVides(CustList<HandBelote> _list, int _nbHands) {
        for (int i = IndexConstants.FIRST_INDEX; i < _nbHands; i++) {
            _list.add(new HandBelote());
        }
    }

    void completerDonne(byte _preneur,RulesBelote _regles) {
        HandBelote talon_=new HandBelote();
        talon_.ajouterCartes(derniereMain());
        /*Copie du_ talon_ original_ pour_ donner_ des_ cartes_ aux_ joueurs_*/
        if(talon_.estVide()) {
            return;
        }
        hand(_preneur).ajouter(talon_.jouer(IndexConstants.FIRST_INDEX));
        //Le preneur_ prend_ la_ carte_ du_ dessus_
        Bytes ordreDisributionJoueurs_;
        ordreDisributionJoueurs_ = _regles.getDealing().getId().getSortedPlayersAfter(dealer);
        for(int i: _regles.getDealing().getDistributionFin()) {
            for (int j : ordreDisributionJoueurs_) {
                for (int k = IndexConstants.SECOND_INDEX; k < i; k++) {
                    deal.get(j).ajouter(talon_.jouer(IndexConstants.FIRST_INDEX));
                }
                if(j!=_preneur) {
                    deal.get(j).ajouter(talon_.jouer(IndexConstants.FIRST_INDEX));
                }
            }
        }
    }
    /**Renvoie la main de l'utilisateur*/
    public HandBelote hand() {
        return deal.get(NUMERO_UTILISATEUR);
    }
    public void jouer(CardBelote _carteJouee) {
        deal.get(NUMERO_UTILISATEUR).jouer(_carteJouee);
    }
    public HandBelote derniereMain() {
        return deal.last();
    }
    public HandBelote hand(byte _i) {
        return deal.get(_i);
    }
    public void ajouter(byte _preneur, CardBelote _jouer) {
        deal.get(_preneur).ajouter(_jouer);
    }

    public void trier(byte _joueur, IdList<Suit> _couleurs, boolean _decroissant, Order _couleur) {
        deal.get(_joueur).trier(_couleurs, _decroissant, _couleur);
    }
    public void trier(byte _joueur, IdList<Suit> _couleurs, boolean _decroissant, Suit _carteAppelee) {
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

    @Override
    public Iterator<HandBelote> iterator() {
        return deal.iterator();
    }
    public CustList<HandBelote> getDeal() {
        return deal;
    }
    public void setDeal(CustList<HandBelote> _deal) {
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
