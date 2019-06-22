package cards.gui.panels;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

import cards.gui.labels.GraphicTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.Panel;
import code.util.CustList;
import code.util.*;

public class CarpetTarot extends Panel {

    /** sens de distribution des cartes */
    private boolean horaire;
    private Panel centerDeck;

    private IntMap<GraphicTarotCard> cards = new IntMap<GraphicTarotCard>();

    public CarpetTarot() {
    }

    public void initTapisTarot(String _lg, int _nombreDeJoueurs, boolean _horaire, int _nombre) {
        horaire = _horaire;
        if (_nombreDeJoueurs == 4) {
            setLayout(new GridLayout(0, 3));
            for (int i = 0; i < 9; i++) {
                Panel surPanneau_ = new Panel();
                surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                if (i % 2 == 1) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (i == 1) {
                        cards.put(2, carte_);
                    } else if (i == 3) {
                        if (horaire) {
                            cards.put(1, carte_);
                        } else {
                            cards.put(3, carte_);
                        }
                    } else if (i == 5) {
                        if (horaire) {
                            cards.put(3, carte_);
                        } else {
                            cards.put(1, carte_);
                        }
                    } else {
                        cards.put(0, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 4) {
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                add(surPanneau_);
            }
        } else if (_nombreDeJoueurs == 6) {
            GridBagLayout grid_ = new GridBagLayout();
            setLayout(grid_);
            for (int i = 0; i < 12; i++) {
                Panel surPanneau_ = new Panel();
                if (i == 1) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(2, carte_);
                    } else {
                        cards.put(4, carte_);
                    }
                    surPanneau_.add(carte_);
                    GridBagConstraints c = new GridBagConstraints();
                    grid_.setConstraints(surPanneau_.getComponent(), c);
                } else if (i == 7) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(4, carte_);
                    } else {
                        cards.put(2, carte_);
                    }
                    surPanneau_.add(carte_);
                    GridBagConstraints c = new GridBagConstraints();
                    c.gridwidth = GridBagConstraints.REMAINDER;
                    grid_.setConstraints(surPanneau_.getComponent(), c);
                } else if (i == 4) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(1, carte_);
                    } else {
                        cards.put(5, carte_);
                    }
                    surPanneau_.add(carte_);
                    GridBagConstraints c = new GridBagConstraints();
                    grid_.setConstraints(surPanneau_.getComponent(), c);
                } else if (i == 10) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(5, carte_);
                    } else {
                        cards.put(1, carte_);
                    }
                    surPanneau_.add(carte_);
                    GridBagConstraints c = new GridBagConstraints();
                    grid_.setConstraints(surPanneau_.getComponent(), c);
                } else if (i == 2) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    cards.put(3, carte_);
                    surPanneau_.add(carte_);
                    GridBagConstraints c = new GridBagConstraints();
                    grid_.setConstraints(surPanneau_.getComponent(), c);
                } else if (i == 9) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    cards.put(0, carte_);
                    surPanneau_.add(carte_);
                    GridBagConstraints c = new GridBagConstraints();
                    grid_.setConstraints(surPanneau_.getComponent(), c);
                } else if (i == 5) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER, 0,
                            0));
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    centerDeck = surPanneau_;
                    GridBagConstraints c = new GridBagConstraints();
                    c.gridwidth = 2;
                    grid_.setConstraints(surPanneau_.getComponent(), c);
                } else if ((i+1) % 4 == 0){
                    GridBagConstraints c = new GridBagConstraints();
                    c.gridwidth = GridBagConstraints.REMAINDER;
                    grid_.setConstraints(surPanneau_.getComponent(), c);
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                add(surPanneau_);
            }
        } else if (_nombreDeJoueurs == 3) {
            setLayout(new GridLayout(0, 3));
            for (int i = 0; i < 9; i++) {
                Panel surPanneau_ = new Panel();
                surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                if (i == 0) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(1, carte_);
                    } else {
                        cards.put(2, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 2) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(2, carte_);
                    } else {
                        cards.put(1, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 7) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    cards.put(0, carte_);
                    surPanneau_.add(carte_);
                } else if (i == 4) {
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    centerDeck = surPanneau_;
                }
            }
        } else {
            setLayout(new GridLayout(0, 3));
            for (int i = 0; i < 9; i++) {
                Panel surPanneau_ = new Panel();
                if (i == 0) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(2, carte_);
                    } else {
                        cards.put(3, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 2) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(3, carte_);
                    } else {
                        cards.put(2, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 3) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(1, carte_);
                    } else {
                        cards.put(4, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 5) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    if (horaire) {
                        cards.put(4, carte_);
                    } else {
                        cards.put(1, carte_);
                    }
                    surPanneau_.add(carte_);
                } else if (i == 7) {
                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicTarotCard
                            .getMaxDimension());
                    cards.put(0, carte_);
                    surPanneau_.add(carte_);
                } else if (i == 4) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER, 0,
                            0));
                    surPanneau_.setPreferredSize(GraphicTarotCard.getDimensionForSeveralCards(_nombre));
                    centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                add(surPanneau_);
            }
        }
        setBackground(new Color(0, 125, 0));
    }

    public void retirerCartes() {
        centerDeck.removeAll();
        centerDeck.validate();
        centerDeck.repaint();
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
        for (byte b = CustList.FIRST_INDEX; b < nbCards_; b++) {
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
            place_.repaint();
        }
    }

    /**
    Met a jour la carte a jouer d'un joueur donne en fonction du nombre de
    joueurs
    */
    public void setCarteTarot(String _lg,byte _joueur, CardTarot _m) {
        GraphicTarotCard place_ = cards.getVal((int) _joueur);
        place_.setCarteEnJeu(_lg,_m);
        place_.repaint();
    }

    public Panel getCenterDeck() {
        return centerDeck;
    }
}
