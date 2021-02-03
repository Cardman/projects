package cards.gui.panels;
import java.awt.Color;

import javax.swing.SwingConstants;

import cards.gui.labels.GraphicTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.Panel;
import code.util.*;
import code.util.core.IndexConstants;

public class CarpetTarot {

    /** sens de distribution des cartes */
    private boolean horaire;
    private Panel centerDeck;

    private final IntMap<GraphicTarotCard> cards = new IntMap<GraphicTarotCard>();
    private Panel container;

    public static CarpetTarot initTapisTarot(String _lg, int _nombreDeJoueurs, boolean _horaire, int _nombre) {
        CarpetTarot c_ = new CarpetTarot();
        Panel cont_;
        c_.horaire = _horaire;
        if (_nombreDeJoueurs == 4) {
            cont_ = Panel.newGrid(0, 3);
            for (int i = 0; i < 9; i++) {
                Panel surPanneau_;
                surPanneau_= Panel.newLineBox();
                if (i % 2 == 1) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (i == 1) {
                        c_.cards.put(2, carte_);
                    } else if (i == 3) {
                        if (c_.horaire) {
                            c_.cards.put(1, carte_);
                        } else {
                            c_.cards.put(3, carte_);
                        }
                    } else if (i == 5) {
                        if (c_.horaire) {
                            c_.cards.put(3, carte_);
                        } else {
                            c_.cards.put(1, carte_);
                        }
                    } else {
                        c_.cards.put(0, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 4) {
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    c_.centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                cont_.add(surPanneau_);
            }
        } else if (_nombreDeJoueurs == 6) {
            cont_ = Panel.newPageBox();
            Panel sub_ = Panel.newGrid(0,4);
            for (int i = 0; i < 4; i++) {
                Panel surPanneau_ = Panel.newLineBox();
                if (i == 1) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(2, carte_);
                    } else {
                        c_.cards.put(4, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 2) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    c_.cards.put(3, carte_);
                    surPanneau_.add(carte_);
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                sub_.add(surPanneau_);
            }
            cont_.add(sub_);
            sub_ = Panel.newGrid(0,3);
            for (int i = 4; i < 7; i++) {
                Panel surPanneau_ = Panel.newLineBox();
                if (i == 6) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(4, carte_);
                    } else {
                        c_.cards.put(2, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 4) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(1, carte_);
                    } else {
                        c_.cards.put(5, carte_);
                    }
                    surPanneau_.add(carte_);
                } else {
                    surPanneau_ = Panel.newLineBox();
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    c_.centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                sub_.add(surPanneau_);
            }
            cont_.add(sub_);
            sub_ = Panel.newGrid(0,4);
            for (int i = 8; i < 12; i++) {
                Panel surPanneau_ = Panel.newLineBox();
                if (i == 10) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(5, carte_);
                    } else {
                        c_.cards.put(1, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 9) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    c_.cards.put(0, carte_);
                    surPanneau_.add(carte_);
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                sub_.add(surPanneau_);
            }
            cont_.add(sub_);
        } else if (_nombreDeJoueurs == 3) {
            cont_ = Panel.newGrid(0, 3);
            for (int i = 0; i < 9; i++) {
                Panel surPanneau_;
                surPanneau_= Panel.newLineBox();
                if (i == 0) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(1, carte_);
                    } else {
                        c_.cards.put(2, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 2) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(2, carte_);
                    } else {
                        c_.cards.put(1, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 7) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    c_.cards.put(0, carte_);
                    surPanneau_.add(carte_);
                } else if (i == 4) {
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    c_.centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                cont_.add(surPanneau_);
            }
        } else {
            cont_ = Panel.newGrid(0, 3);
            for (int i = 0; i < 9; i++) {
                Panel surPanneau_ = Panel.newLineBox();
                if (i == 0) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(2, carte_);
                    } else {
                        c_.cards.put(3, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 2) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(3, carte_);
                    } else {
                        c_.cards.put(2, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 3) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(1, carte_);
                    } else {
                        c_.cards.put(4, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 5) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(4, carte_);
                    } else {
                        c_.cards.put(1, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 7) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    c_.cards.put(0, carte_);
                    surPanneau_.add(carte_);
                } else if (i == 4) {
                    surPanneau_= Panel.newLineBox();
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    c_.centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                cont_.add(surPanneau_);
            }
        }
        cont_.setBackground(new Color(0, 125, 0));
        c_.container=cont_;
        return c_;
    }

    public void retirerCartes() {
        centerDeck.removeAll();
        centerDeck.validate();
    }

//    public void supprimerCarteTalon() {
//        centerDeck.remove(0);
//        centerDeck.repaint();
//    }

    public void setEcart(String _lg, HandTarot _m) {
        centerDeck.removeAll();
        setTalonTarot(_lg,_m);
    }

    public void setTalonTarot(String _lg, HandTarot _m) {
        centerDeck.setBackground(new Color(0, 125, 0));
        boolean entered_ = false;
        int nbCards_ = _m.total();
        for (byte b = IndexConstants.FIRST_INDEX; b < nbCards_; b++) {
            GraphicTarotCard cg_ = new GraphicTarotCard(_lg,
                    SwingConstants.RIGHT, !entered_);
            cg_.setPreferredSize(entered_);
            centerDeck.add(cg_);
            entered_ = true;
        }
        centerDeck.validate();
    }

    /**
    Place les dos des cartes (une pour chaque joueur) sur le tapis avant et
    apres chaque pli
    */
    public void setCartesTarotJeu(String _lg,byte _nombreDeJoueurs) {
        for (byte joueur_ = 0; joueur_ < _nombreDeJoueurs; joueur_++) {
            GraphicTarotCard place_ = cards.getVal((int) joueur_);
            place_.setJeu(_lg);
            place_.repaintLabel();
        }
    }

    /**
    Met a jour la carte a jouer d'un joueur donne en fonction du nombre de
    joueurs
    */
    public void setCarteTarot(String _lg,byte _joueur, CardTarot _m) {
        GraphicTarotCard place_ = cards.getVal((int) _joueur);
        place_.setCarteEnJeu(_lg,_m);
        place_.repaintLabel();
    }

    public Panel getCenterDeck() {
        return centerDeck;
    }

    public Panel getContainer() {
        return container;
    }
}
