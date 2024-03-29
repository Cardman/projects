package cards.tarot;
import java.util.Iterator;

import cards.consts.SortedPlayers;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.*;


public final class TrickTarot implements Iterable<CardTarot> {
    /**Entameur du pli*/
    private byte starter;
    /**cards est l'ensemble de cartes jouees pendant le pli a la belote ou au tarot*/
    private HandTarot cards=new HandTarot();

    public TrickTarot() {}
    TrickTarot(byte _pentameur) {
        initPli(_pentameur);
    }

    TrickTarot(HandTarot _pm, byte _pentameur) {
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

    private void initPli(byte _pentameur) {
        starter=_pentameur;
    }

//    public void setSeenByAllPlayers(boolean _seenByAllPlayers) {
//        seenByAllPlayers = _seenByAllPlayers;
//    }
//    public boolean getVuParToutJoueur() {
//        return isSeenByAllPlayers();
//    }

    public byte getNextPlayer(byte _nbPlayer) {
        return (byte) ((starter + total()) % _nbPlayer);
    }
    /**Retourne l'entameur du pli*/
    public byte getEntameur() {
        return getStarter();
    }

    public HandTarot getCartes() {
        return getCards();
    }

    byte getRamasseur() {
        return getRamasseur((byte) total());
    }
    /**Indique le joueur qui doit ramasser le pli au tarot
    pour entamer l'eventuel suivant
    @param _nombreJoueurs nombre de joueurs qui jouent a cette partie*/
    public byte getRamasseur(byte _nombreJoueurs) {
        byte max_=0;
        byte i=0;
        byte position_=0;
        Suit demande_=couleurDemandee();
        byte valForce_;
        for(CardTarot c:cards) {
            valForce_=c.strength(demande_);
            if(valForce_>max_) {
                position_=i;
                max_=valForce_;
            }
            i++;
        }
        byte ramasseur_ = position_;
        //Ramasseur est_ la_ position_ du_ ramasseur_ par_ rapport_ a l'entameur
        //On calcule_ la_ position_ de_ ramasseur_ par_ rapport_ a celle_ de_ l'utilisateur_
        return (byte) ((ramasseur_+getEntameur())%_nombreJoueurs);
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

    public CustList<HandTarot> completeCurrent(byte _nb, boolean _add) {
        CustList<HandTarot> ls_ = new CustList<HandTarot>();
        for (int i = 0; i < _nb; i++) {
            ls_.add(new HandTarot());
        }
        if (_add) {
            for (byte b: joueursAyantJoue(_nb)) {
                ls_.get(b).ajouter(carteDuJoueur(b, _nb));
            }
        }
        return ls_;
    }
    //Pli en cours
    public Bytes joueursAyantJoue(byte _nombreDeJoueurs) {
        return new SortedPlayers(_nombreDeJoueurs).joueursAyantJoue(starter,total());
    }

    byte joueurAyantJoue(CardTarot _c) {
        return joueurAyantJouePliEnCours(_c,(byte) total());
    }
    byte joueurAyantJouePliEnCours(CardTarot _c,byte _nombreDeJoueurs) {
        if(!contient(_c)) {
            return -1;
        }
        byte position_=(byte)cards.position(_c);
        return (byte)((position_+getEntameur())%_nombreDeJoueurs);
    }
    Bytes joueursAyantJoueAvant(byte _pnumero, DealingTarot _d) {
        return _d.getId().joueursAyantJoueAvant(_pnumero,starter,total());
    }

    Bytes joueursAyantJoueApres(byte _pnumero, DealingTarot _d) {
        byte nombreDeJoueurs_ = (byte) total();
        Bytes all_ = GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_);
        Bytes before_ = joueursAyantJoueAvant(_pnumero, _d);
        for (byte b: before_) {
            all_.removeAllLong(b);
        }
        all_.removeAllLong(_pnumero);
        return all_;
    }

    //Pli en cours
    public boolean aJoue(byte _joueur,byte _nombreDeJoueurs) {
        return new SortedPlayers(_nombreDeJoueurs).aJoue(_joueur,total(),getEntameur());
    }
    public CardTarot carteDuJoueur(byte _joueur) {
        return carteDuJoueur(_joueur, (byte) total());
    }
    /**Retourne la carte du joueur de variable joueur en fonction du nombre de joueurs et du pli ayant la plus petite longueur*/
    public CardTarot carteDuJoueur(byte _joueur,byte _nombreDeJoueurs) {
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
    Bytes joueursCoupes() {
        return joueursCoupes((byte) total());
    }
    /**Retourne l'ensemble des joueurs qui coupent ce pli<br>
    <ol><li>si la couleur demandee est de l'atout alors on cherche l'ensemble des joueurs n'ayant pas joue de l'atout(Excuse incluse)</li>
    <li>sinon on cherche les joueurs ayant joue de l'atout sur une couleur</li></ol>
    Ces joueurs sont classes par ordre chronologique de jeu*/
    Bytes joueursCoupes(byte _nombreDeJoueurs) {
        Bytes coupes_=new Bytes();
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
    Bytes joueursDefausses(byte _nbPlayers) {
        Bytes coupes_=new Bytes();
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
        setStarter((byte) _i);
    }
    public byte getStarter() {
        return starter;
    }
    public void setStarter(byte _starter) {
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
