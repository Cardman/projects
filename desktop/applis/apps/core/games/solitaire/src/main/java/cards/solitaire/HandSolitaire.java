package cards.solitaire;

import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.IdList;
import code.util.core.IndexConstants;

import java.util.Iterator;

public final class HandSolitaire implements Iterable<CardSolitaire> {

    private IdList<CardSolitaire> cards=new IdList<CardSolitaire>();

    public HandSolitaire() {
    }

    public HandSolitaire(HandSolitaire _hand) {
        cards.addAllElts(_hand.cards);
    }

    public static HandSolitaire nullToEmpty(HandSolitaire _t) {
        if (_t == null) {
            return new HandSolitaire();
        }
        return _t;
    }
    public static HandSolitaire create(CardSolitaire[] _cards) {
        HandSolitaire h_ = new HandSolitaire();
        for (CardSolitaire c : _cards) {
            h_.ajouter(c);
        }
        return h_;
    }
    public static HandSolitaire stack(int _nbStacks) {
        HandSolitaire liste_ = new HandSolitaire();
        for (int i = IndexConstants.FIRST_INDEX; i < _nbStacks; i++) {
            liste_.ajouterCartes(HandSolitaire.pileBase());
        }
        return liste_;
    }

    public static HandSolitaire pileBase() {
        HandSolitaire liste_ = new HandSolitaire();
        liste_.ajouter(CardSolitaire.HEART_KING);
        liste_.ajouter(CardSolitaire.HEART_QUEEN);
        liste_.ajouter(CardSolitaire.HEART_JACK);
        liste_.ajouter(CardSolitaire.HEART_10);
        liste_.ajouter(CardSolitaire.HEART_9);
        liste_.ajouter(CardSolitaire.HEART_8);
        liste_.ajouter(CardSolitaire.HEART_7);
        liste_.ajouter(CardSolitaire.HEART_6);
        liste_.ajouter(CardSolitaire.HEART_5);
        liste_.ajouter(CardSolitaire.HEART_4);
        liste_.ajouter(CardSolitaire.HEART_3);
        liste_.ajouter(CardSolitaire.HEART_2);
        liste_.ajouter(CardSolitaire.HEART_1);
        liste_.ajouter(CardSolitaire.SPADE_KING);
        liste_.ajouter(CardSolitaire.SPADE_QUEEN);
        liste_.ajouter(CardSolitaire.SPADE_JACK);
        liste_.ajouter(CardSolitaire.SPADE_10);
        liste_.ajouter(CardSolitaire.SPADE_9);
        liste_.ajouter(CardSolitaire.SPADE_8);
        liste_.ajouter(CardSolitaire.SPADE_7);
        liste_.ajouter(CardSolitaire.SPADE_6);
        liste_.ajouter(CardSolitaire.SPADE_5);
        liste_.ajouter(CardSolitaire.SPADE_4);
        liste_.ajouter(CardSolitaire.SPADE_3);
        liste_.ajouter(CardSolitaire.SPADE_2);
        liste_.ajouter(CardSolitaire.SPADE_1);
        liste_.ajouter(CardSolitaire.DIAMOND_KING);
        liste_.ajouter(CardSolitaire.DIAMOND_QUEEN);
        liste_.ajouter(CardSolitaire.DIAMOND_JACK);
        liste_.ajouter(CardSolitaire.DIAMOND_10);
        liste_.ajouter(CardSolitaire.DIAMOND_9);
        liste_.ajouter(CardSolitaire.DIAMOND_8);
        liste_.ajouter(CardSolitaire.DIAMOND_7);
        liste_.ajouter(CardSolitaire.DIAMOND_6);
        liste_.ajouter(CardSolitaire.DIAMOND_5);
        liste_.ajouter(CardSolitaire.DIAMOND_4);
        liste_.ajouter(CardSolitaire.DIAMOND_3);
        liste_.ajouter(CardSolitaire.DIAMOND_2);
        liste_.ajouter(CardSolitaire.DIAMOND_1);
        liste_.ajouter(CardSolitaire.CLUB_KING);
        liste_.ajouter(CardSolitaire.CLUB_QUEEN);
        liste_.ajouter(CardSolitaire.CLUB_JACK);
        liste_.ajouter(CardSolitaire.CLUB_10);
        liste_.ajouter(CardSolitaire.CLUB_9);
        liste_.ajouter(CardSolitaire.CLUB_8);
        liste_.ajouter(CardSolitaire.CLUB_7);
        liste_.ajouter(CardSolitaire.CLUB_6);
        liste_.ajouter(CardSolitaire.CLUB_5);
        liste_.ajouter(CardSolitaire.CLUB_4);
        liste_.ajouter(CardSolitaire.CLUB_3);
        liste_.ajouter(CardSolitaire.CLUB_2);
        liste_.ajouter(CardSolitaire.CLUB_1);
        return liste_;
    }

    public boolean validStack(int _nbStacks) {
        for (CardSolitaire c: pileBase()) {
            int nbUses_ = IndexConstants.SIZE_EMPTY;
            for (CardSolitaire e: cards) {
                if (c == e) {
                    nbUses_++;
                }
            }
            if (nbUses_ != _nbStacks) {
                return false;
            }
        }
        return true;
    }
    /**<ol>
     <li>Choisit un nombre aleatoire entre 0 et le nombre total de carte dans la main moins une carte
     d'ou total() x Math.random() puis Math.floor(total() x Math.random())</li>
     <li>puis retire la carte choisie de la main courante et la retourne</li>
     </ol>
     @return la carte aleatoire choisie*/
    CardSolitaire tirerUneCarteAleatoire(AbstractGenerator _gene) {
//        return jouer((int)Math.floor(total()*MonteCarlo.randomDouble()));
        return jouer(MonteCarloUtil.randomLong(total(),_gene));
        //0<=total()*Math.random()<total()
        //Donc 0<=Math.floor(total()*Math.random())<Math.floor(total())=total()
    }

    /**Joue une carte a la position a
     @return la carte a jouer*/
    public CardSolitaire jouer(int _a) {
        CardSolitaire c_ = cards.get(_a);
        cards.remove(_a);
        return c_;
    }

    /**Renvoie le nombre total de cartes dans la main*/
    public int total() {
        return cards.size();
    }
    /**Renvoie la carte a la position i*/
    public CardSolitaire carte(int _i) {
        return cards.get(_i);
    }
    public CardSolitaire premiereCarte() {
        return cards.first();
    }
    public CardSolitaire derniereCarte() {
        return cards.last();
    }

//    public boolean containsCards(HandSolitaire _hand) {
//        for (CardSolitaire c: _hand) {
//            Ints indexesHand_ = cards.indexesOfObj(c);
//            Ints indexesOtherHand_ = _hand.cards.indexesOfObj(c);
//            if (indexesHand_.size() < indexesOtherHand_.size()) {
//                return false;
//            }
//        }
//        return true;
//    }

    public void supprimerCartes() {
        cards.clear();
    }

    public void supprimerCarte(int _index) {
        cards.remove(_index);
    }

//    public void supprimerCartes(HandSolitaire _hand) {
//        for (CardSolitaire c: _hand) {
//            int s_ = cards.size();
//            for (int i = IndexConstants.FIRST_INDEX; i < s_; i++) {
//                if (cards.get(i) == c) {
//                    cards.remove(i);
//                    break;
//                }
//            }
//        }
//    }
    /**Ajoute une carte a la fin de la main
     et precise le jeu
     @param _t la carte a ajouter*/
    public void ajouter(CardSolitaire _t) {
        cards.add(_t);
    }

    /**Ajoute les cartes d'une main dans la main courante
     a la fin de celle-ci les cartes ajoutees conservent le
     meme ordre qu'a l'etat initial
     @param _cartes la main a ajouter*/
    public void ajouterCartes(HandSolitaire _cartes) {
        for(CardSolitaire carte_:_cartes) {
            cards.add(carte_);
        }
    }

    public boolean estVide() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<CardSolitaire> iterator() {
        return cards.iterator();
    }

    public IdList<CardSolitaire> getCards() {
        return cards;
    }

    public void setCards(IdList<CardSolitaire> _cards) {
        cards = _cards;
    }
}
