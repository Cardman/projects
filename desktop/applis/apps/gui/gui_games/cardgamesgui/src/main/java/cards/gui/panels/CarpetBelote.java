package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.labels.GraphicBeloteCard;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TextArea;
import code.util.*;
import code.util.StringList;
import code.util.core.StringUtil;

public class CarpetBelote {

    private static final String EMPTY="";
    private static final String RETURN_LINE="\n";

    private Panel centerDeck;

    private final IntMap<GraphicBeloteCard> cards = new IntMap<GraphicBeloteCard>();

    /**sens de distribution des cartes*/
    private boolean horaire;
    private Panel container;

    public static CarpetBelote initTapisBelote(String _lg, int _nombreDeJoueurs,boolean _horaire,StringList _pseudos,int _nombre) {
        Panel container_ = Panel.newGrid(0,3);
        CarpetBelote c_ = new CarpetBelote();
        c_.horaire=_horaire;
        c_.container = container_;
        if(_nombreDeJoueurs==4) {
            for (int i = 0; i < 9; i++) {
                Panel surPanneau_;
                surPanneau_= Panel.newLineBox();
                if (i % 2 == 1) {

                    GraphicBeloteCard carte_ = new GraphicBeloteCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicBeloteCard
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
                    surPanneau_.setPreferredSize(GraphicBeloteCard.getDimensionForSeveralCards(_nombre));
                    c_.centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                container_.add(surPanneau_);
            }
        } else if(_nombreDeJoueurs==6) {
            for(int i=0;i<12;i++) {
                Panel surPanneau_=Panel.newLineBox();
                Panel panneau_=Panel.newBorder();
                TextArea jta_=new TextArea(EMPTY,3,0);
                jta_.setEditable(false);
                if(i==3) {
                    surPanneau_= Panel.newLineBox();
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(4),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==5) {
                    surPanneau_= Panel.newLineBox();
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(4),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==6) {
                    surPanneau_= Panel.newLineBox();
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(5),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==8) {
                    surPanneau_= Panel.newLineBox();
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(5),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==1) {
                    surPanneau_= Panel.newLineBox();
                    jta_.append(StringUtil.concat(_pseudos.get(3),RETURN_LINE));
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==10) {
                    surPanneau_= Panel.newLineBox();
                    jta_.append(StringUtil.concat(_pseudos.get(0),RETURN_LINE));
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==4) {
                    surPanneau_= Panel.newLineBox();
                    surPanneau_.setPreferredSize(GraphicBeloteCard.getDimensionForSeveralCards(_nombre));
                }
                surPanneau_.setBackground(new Color(0,125,0));
                container_.add(surPanneau_);
            }
        } else if(_nombreDeJoueurs==3) {
            for(int i=0;i<9;i++) {
                Panel surPanneau_;
                surPanneau_= Panel.newLineBox();
                Panel panneau_=Panel.newBorder();
                TextArea jta_=new TextArea(EMPTY,3,0);
                jta_.setEditable(false);
                if(i==0) {
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==2) {
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==7) {
                    jta_.append(StringUtil.concat(_pseudos.get(0),RETURN_LINE));
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==4) {
                    surPanneau_.setPreferredSize(GraphicBeloteCard.getDimensionForSeveralCards(_nombre));
                }
                surPanneau_.setBackground(new Color(0,125,0));
                container_.add(surPanneau_);
            }
        } else {
            for(int i=0;i<9;i++) {
                Panel surPanneau_=Panel.newLineBox();
                Panel panneau_=Panel.newBorder();
                TextArea jta_=new TextArea(EMPTY,3,0);
                jta_.setEditable(false);
                if(i==0) {
                    surPanneau_= Panel.newLineBox();
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(3),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==2) {
                    surPanneau_= Panel.newLineBox();
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(3),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==3) {
                    surPanneau_= Panel.newLineBox();
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(4),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==5) {
                    surPanneau_= Panel.newLineBox();
                    if(c_.horaire) {
                        jta_.append(StringUtil.concat(_pseudos.get(4),RETURN_LINE));
                    } else {
                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==7) {
                    surPanneau_= Panel.newLineBox();
                    jta_.append(StringUtil.concat(_pseudos.get(0),RETURN_LINE));
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==4) {
                    surPanneau_= Panel.newLineBox();
                    surPanneau_.setPreferredSize(GraphicBeloteCard.getDimensionForSeveralCards(_nombre));
                }
                surPanneau_.setBackground(new Color(0,125,0));
                container_.add(surPanneau_);
            }
        }
        return c_;
    }

    public void retirerCartes() {
        centerDeck.removeAll();
        centerDeck.validate();
    }

    public void setTalonBelote(String _lg, HandBelote _m) {
        GraphicBeloteCard cg_=new GraphicBeloteCard(_lg,_m.premiereCarte(),SwingConstants.RIGHT,true);
        cg_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
        centerDeck.add(cg_);
    }

    /**Place les dos des cartes (une pour chaque joueur) sur le tapis avant et apres chaque pli*/
    public void setCartesBeloteJeu(byte _nombreDeJoueurs, String _lg) {
        for(byte joueur_=0;joueur_<_nombreDeJoueurs;joueur_++) {
            GraphicBeloteCard place_ = cards.getVal((int) joueur_);
            place_.setJeu(_lg);
            place_.repaintLabel();
        }
    }
    /**Met a jour la carte a jouer d'un joueur
    donne en fonction du nombre de joueurs*/
    public void setCarteBelote(String _lg, byte _joueur,CardBelote _m) {
        GraphicBeloteCard place_= cards.getVal((int) _joueur);
        place_.setCarteEnJeu(_lg, _m);
        place_.repaintLabel();
    }

    public Panel getContainer() {
        return container;
    }
}
