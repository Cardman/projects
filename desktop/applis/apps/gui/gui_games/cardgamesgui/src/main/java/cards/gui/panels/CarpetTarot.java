package cards.gui.panels;


import javax.swing.SwingConstants;

import cards.gui.labels.GraphicTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.*;
import code.util.core.IndexConstants;

public class CarpetTarot {

    /** sens de distribution des cartes */
    private boolean horaire;
    private AbsPanel centerDeck;

    private final IntMap<GraphicTarotCard> cards = new IntMap<GraphicTarotCard>();
    private AbsPanel container;

    public static CarpetTarot initTapisTarot(String _lg, int _nombreDeJoueurs, boolean _horaire, int _nombre, AbsCompoFactory _compoFactory) {
        CarpetTarot c_ = new CarpetTarot();
        AbsPanel cont_;
        c_.horaire = _horaire;
        if (_nombreDeJoueurs == 4) {
            cont_ = _compoFactory.newGrid(0, 3);
            for (int i = 0; i < 9; i++) {
                AbsPanel surPanneau_;
                surPanneau_= _compoFactory.newLineBox();
                if (i % 2 == 1) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true, _compoFactory);
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
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                cont_.add(surPanneau_);
            }
        } else if (_nombreDeJoueurs == 6) {
            cont_ = _compoFactory.newPageBox();
            AbsPanel sub_ = _compoFactory.newGrid(0,4);
            for (int i = 0; i < 4; i++) {
                AbsPanel surPanneau_ = _compoFactory.newLineBox();
                if (i == 1) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true, _compoFactory);
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
                            SwingConstants.RIGHT, true, _compoFactory);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    c_.cards.put(3, carte_);
                    surPanneau_.add(carte_);
                }
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                sub_.add(surPanneau_);
            }
            cont_.add(sub_);
            sub_ = _compoFactory.newGrid(0,3);
            for (int i = 4; i < 7; i++) {
                AbsPanel surPanneau_ = _compoFactory.newLineBox();
                if (i == 6) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true, _compoFactory);
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
                            SwingConstants.RIGHT, true, _compoFactory);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (c_.horaire) {
                        c_.cards.put(1, carte_);
                    } else {
                        c_.cards.put(5, carte_);
                    }
                    surPanneau_.add(carte_);
                } else {
                    surPanneau_ = _compoFactory.newLineBox();
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    c_.centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                sub_.add(surPanneau_);
            }
            cont_.add(sub_);
            sub_ = _compoFactory.newGrid(0,4);
            for (int i = 8; i < 12; i++) {
                AbsPanel surPanneau_ = _compoFactory.newLineBox();
                if (i == 10) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true, _compoFactory);
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
                            SwingConstants.RIGHT, true, _compoFactory);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    c_.cards.put(0, carte_);
                    surPanneau_.add(carte_);
                }
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                sub_.add(surPanneau_);
            }
            cont_.add(sub_);
        } else if (_nombreDeJoueurs == 3) {
            cont_ = _compoFactory.newGrid(0, 3);
            for (int i = 0; i < 9; i++) {
                AbsPanel surPanneau_;
                surPanneau_= _compoFactory.newLineBox();
                if (i == 0) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true, _compoFactory);
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
                            SwingConstants.RIGHT, true, _compoFactory);
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
                            SwingConstants.RIGHT, true, _compoFactory);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    c_.cards.put(0, carte_);
                    surPanneau_.add(carte_);
                } else if (i == 4) {
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    c_.centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                cont_.add(surPanneau_);
            }
        } else {
            cont_ = _compoFactory.newGrid(0, 3);
            for (int i = 0; i < 9; i++) {
                AbsPanel surPanneau_ = _compoFactory.newLineBox();
                if (i == 0) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true, _compoFactory);
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
                            SwingConstants.RIGHT, true, _compoFactory);
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
                            SwingConstants.RIGHT, true, _compoFactory);
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
                            SwingConstants.RIGHT, true, _compoFactory);
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
                            SwingConstants.RIGHT, true, _compoFactory);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    c_.cards.put(0, carte_);
                    surPanneau_.add(carte_);
                } else if (i == 4) {
                    surPanneau_= _compoFactory.newLineBox();
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    c_.centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                cont_.add(surPanneau_);
            }
        }
        cont_.setBackground(GuiConstants.newColor(0, 125, 0));
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

    public void setEcart(String _lg, HandTarot _m, AbsCompoFactory _compoFactory) {
        centerDeck.removeAll();
        setTalonTarot(_lg,_m, _compoFactory);
    }

    public void setTalonTarot(String _lg, HandTarot _m, AbsCompoFactory _compoFactory) {
        centerDeck.setBackground(GuiConstants.newColor(0, 125, 0));
        boolean entered_ = false;
        int nbCards_ = _m.total();
        for (byte b = IndexConstants.FIRST_INDEX; b < nbCards_; b++) {
            GraphicTarotCard cg_ = new GraphicTarotCard(_lg,
                    SwingConstants.RIGHT, !entered_, _compoFactory);
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
    public void setCartesTarotJeu(AbstractImageFactory _fact,String _lg, byte _nombreDeJoueurs) {
        for (byte joueur_ = 0; joueur_ < _nombreDeJoueurs; joueur_++) {
            GraphicTarotCard place_ = cards.getVal((int) joueur_);
            place_.setJeu(_lg);
            place_.repaintLabel(_fact);
        }
    }

    /**
    Met a jour la carte a jouer d'un joueur donne en fonction du nombre de
    joueurs
    */
    public void setCarteTarot(AbstractImageFactory _fact,String _lg,byte _joueur, CardTarot _m) {
        GraphicTarotCard place_ = cards.getVal((int) _joueur);
        place_.setCarteEnJeu(_fact,_lg,_m);
        place_.repaintLabel(_fact);
    }

    public AbsPanel getCenterDeck() {
        return centerDeck;
    }

    public AbsPanel getContainer() {
        return container;
    }
}
