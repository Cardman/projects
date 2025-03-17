package cards.tarot;
import java.util.Iterator;

import cards.consts.SortedPlayers;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.*;


public final class TrickTarot implements Iterable<CardTarot> {
    /**Entameur du pli*/
    private int starter;
    /**cards est l'ensemble de cartes jouees pendant le pli a la belote ou au tarot*/
    private HandTarot cards=new HandTarot();

    public TrickTarot() {}
    TrickTarot(int _pentameur) {
        initPli(_pentameur);
    }

    TrickTarot(HandTarot _pm, int _pentameur) {
        initPli(_pentameur);
        setCards(_pm);
    }

    public boolean foundLast(CustList<TrickTarot> _tricks) {
        boolean found_ = false;
        for (TrickTarot t: _tricks.right(1)) {
            if (HandTarot.equalsSet(t.getCartes(), getCartes())) {
                found_ = true;
                break;
            }
        }
        return found_;
    }

    private void initPli(int _pentameur) {
        starter=_pentameur;
    }

//    public void setSeenByAllPlayers(boolean _seenByAllPlayers) {
//        seenByAllPlayers = _seenByAllPlayers;
//    }
//    public boolean getVuParToutJoueur() {
//        return isSeenByAllPlayers();
//    }

    public int getNextPlayer(int _nbPlayer) {
        return (starter + total()) % _nbPlayer;
    }
    /**Retourne l'entameur du pli*/
    public int getEntameur() {
        return getStarter();
    }

    public HandTarot getCartes() {
        return getCards();
    }

    int getRamasseur() {
        return getRamasseur(total());
    }
    /**Indique le joueur qui doit ramasser le pli au tarot
    pour entamer l'eventuel suivant
    @param _nombreJoueurs nombre de joueurs qui jouent a cette partie*/
    public int getRamasseur(int _nombreJoueurs) {
        int max_=0;
        int i=0;
        int position_=0;
        Suit demande_=couleurDemandee();
        int valForce_;
        for(CardTarot c:cards) {
            valForce_=c.strength(demande_);
            if(valForce_>max_) {
                position_=i;
                max_=valForce_;
            }
            i++;
        }
        int ramasseur_ = position_;
        //Ramasseur est_ la_ position_ du_ ramasseur_ par_ rapport_ a l'entameur
        //On calcule_ la_ position_ de_ ramasseur_ par_ rapport_ a celle_ de_ l'utilisateur_
        return (ramasseur_+getEntameur())%_nombreJoueurs;
        //On renvoie_ le_ ramasseur_ du_ pli_ courant_
    }
    void retirer(CardTarot _ct) {
        cards.jouer(_ct);
    }
    void ajouter(CardTarot _c) {
        cards.ajouter(_c);
    }
    public CardTarot carte(int _i) {
        return cards.carte(_i);
    }
    public CardTarot premiereCarteNonExc() {
        CardTarot first_ = premiereCarte();
        if (first_ == CardTarot.EXCUSE) {
            return carte(1);
        }
        return first_;
    }
    public CardTarot premiereCarte() {
        return cards.premiereCarte();
    }

    public CustList<HandTarot> completeCurrent(int _nb, boolean _add) {
        CustList<HandTarot> ls_ = new CustList<HandTarot>();
        for (int i = 0; i < _nb; i++) {
            ls_.add(new HandTarot());
        }
        if (_add) {
            for (int b: joueursAyantJoue(_nb)) {
                ls_.get(b).ajouter(carteDuJoueur(b, _nb));
            }
        }
        return ls_;
    }
    //Pli en cours
    public Ints joueursAyantJoue(int _nombreDeJoueurs) {
        return new SortedPlayers(_nombreDeJoueurs).joueursAyantJoue(starter,total());
    }

    int joueurAyantJoue(CardTarot _c) {
        return joueurAyantJouePliEnCours(_c, total());
    }
    int joueurAyantJouePliEnCours(CardTarot _c,int _nombreDeJoueurs) {
        if(!contient(_c)) {
            return -1;
        }
        int position_= cards.position(_c);
        return (position_+getEntameur())%_nombreDeJoueurs;
    }
    Ints joueursAyantJoueAvant(int _pnumero, DealingTarot _d) {
        return _d.getId().joueursAyantJoueAvant(_pnumero,starter,total());
    }

    Ints joueursAyantJoueApres(int _pnumero, DealingTarot _d) {
        int nombreDeJoueurs_ = total();
        Ints all_ = GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_);
        Ints before_ = joueursAyantJoueAvant(_pnumero, _d);
        for (int b: before_) {
            all_.removeAllLong(b);
        }
        all_.removeAllLong(_pnumero);
        return all_;
    }

    //Pli en cours
    public boolean aJoue(int _joueur,int _nombreDeJoueurs) {
        return new SortedPlayers(_nombreDeJoueurs).aJoue(_joueur,total(),getEntameur());
    }
    public CardTarot carteDuJoueur(int _joueur) {
        return carteDuJoueur(_joueur, total());
    }
    /**Retourne la carte du joueur de variable joueur en fonction du nombre de joueurs et du pli ayant la plus petite longueur*/
    public CardTarot carteDuJoueur(int _joueur,int _nombreDeJoueurs) {
        return carte(new SortedPlayers(_nombreDeJoueurs).index(_joueur,getEntameur(),total()));
    }
    /**Retourne la couleur demandee du pli*/
    public Suit couleurDemandee() {
        if(estVide()) {
            return Suit.UNDEFINED;
        }
        if(premiereCarte() != CardTarot.EXCUSE) {
            return premiereCarte().getId().getCouleur();
        }
        if (total() == 1) {
            //L'Excuse est_ la_ premiere_ carte_ du_ pli_
            //donc_ la_ couleur_ demandee_ n'est_ pas_ encore_ definie_
            return Suit.UNDEFINED;
        }
        return carte(1).getId().getCouleur();
    }
    Ints joueursCoupes() {
        return joueursCoupes(total());
    }
    /**Retourne l'ensemble des joueurs qui coupent ce pli<br>
    <ol><li>si la couleur demandee est de l'atout alors on cherche l'ensemble des joueurs n'ayant pas joue de l'atout(Excuse incluse)</li>
    <li>sinon on cherche les joueurs ayant joue de l'atout sur une couleur</li></ol>
    Ces joueurs sont classes par ordre chronologique de jeu*/
    Ints joueursCoupes(int _nombreDeJoueurs) {
        Ints coupes_=new Ints();
        Suit couleur_;
        couleur_=couleurDemandee();
        if(couleur_==Suit.TRUMP) {
            for(CardTarot c: cards) {
                if(c.getId().getCouleur() !=Suit.TRUMP) {
                    coupes_.add(joueurAyantJouePliEnCours(c,_nombreDeJoueurs));
                }
            }
        } else {
            for(CardTarot c: cards) {
                if(c.getId().getCouleur() ==Suit.TRUMP) {
                    coupes_.add(joueurAyantJouePliEnCours(c,_nombreDeJoueurs));
                }
            }
        }
        return coupes_;
    }
    /**Retourne l'ensemble des joueurs qui se defaussent sur ce pli<br>
    <ol><li>si la couleur demandee est de l'atout alors on cherche l'ensemble des joueurs n'ayant pas joue de l'atout(Excuse incluse)</li>
    <li>sinon on cherche les joueurs ayant joue une autre couleur que celle demandee</li></ol>
    Ces joueurs sont classes par ordre chronologique de jeu*/
    Ints joueursDefausses(int _nbPlayers) {
        Ints coupes_=new Ints();
        Suit couleur_;
        couleur_=couleurDemandee();
        if(couleur_==Suit.TRUMP) {
            for(CardTarot c: cards) {
                if(c.getId().getCouleur() !=Suit.TRUMP) {
                    coupes_.add(joueurAyantJouePliEnCours(c,_nbPlayers));
                }
            }
        } else {
            for(CardTarot c: cards) {
                if(c.getId().getCouleur() !=Suit.TRUMP&& c.getId().getCouleur() !=couleur_) {
                    coupes_.add(joueurAyantJouePliEnCours(c,_nbPlayers));
                }
            }
        }
        return coupes_;
    }

    /**Retourne vrai si et seulement si la carte est jouee dans ce pli*/
    boolean contient(CardTarot _c) {
        return cards.contient(_c);
    }
    /**Si tous les joueurs ne joue qu'une carte alors total() renvoie le nombre de cartes du pli
    sinon il renvoie le nombre total de cartes divise par le nombres de cartes jouees par joueur pour le pli*/
    public int total() {
        return cards.total();
    }
    public boolean estVide() {
        return cards.estVide();
    }

    @Override
    public Iterator<CardTarot> iterator() {
        return cards.iterator();
    }

    void setEntameur(int _i) {
        setStarter(_i);
    }
    public int getStarter() {
        return starter;
    }
    public void setStarter(int _starter) {
        starter = _starter;
    }
    public HandTarot getCards() {
        return cards;
    }
    public void setCards(HandTarot _cards) {
        cards = _cards;
    }
//    public boolean isSeenByAllPlayers() {
//        return seenByAllPlayers;
//    }
}
