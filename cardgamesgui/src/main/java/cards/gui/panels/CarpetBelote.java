package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.labels.GraphicBeloteCard;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.util.NumberMap;
import code.util.StringList;

public class CarpetBelote extends Panel {

    private static final String EMPTY="";
    private static final String RETURN_LINE="\n";

    private Panel centerDeck;

    private NumberMap<Integer,GraphicBeloteCard> cards = new NumberMap<Integer,GraphicBeloteCard>();

    /**sens de distribution des cartes*/
    private boolean horaire;
    public CarpetBelote() {}

    public void initTapisBelote(String _lg, int _nombreDeJoueurs,boolean _horaire,StringList _pseudos,int _nombre) {
        setLayout(new GridLayout(0,3));
        horaire=_horaire;
        if(_nombreDeJoueurs==4) {
            setLayout(new GridLayout(0, 3));
            for (int i = 0; i < 9; i++) {
                Panel surPanneau_ = new Panel();
                surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                if (i % 2 == 1) {

                    GraphicBeloteCard carte_ = new GraphicBeloteCard(_lg,
                            SwingConstants.RIGHT, true);
                    carte_.setPreferredSize(GraphicBeloteCard
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
                    surPanneau_.setPreferredSize(GraphicBeloteCard.getDimensionForSeveralCards(_nombre));
                    centerDeck = surPanneau_;
                }
                surPanneau_.setBackground(new Color(0, 125, 0));
                add(surPanneau_);
            }
        } else if(_nombreDeJoueurs==6) {
            for(int i=0;i<12;i++) {
                Panel surPanneau_=new Panel();
                Panel panneau_=new Panel();
                panneau_.setLayout(new BorderLayout());
                JTextArea jta_=new JTextArea(EMPTY);
                jta_.setRows(3);
                jta_.setEditable(false);
                if(i==3) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(2),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(4),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==5) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(4),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(2),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==6) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(1),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(5),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==8) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(5),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(1),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==1) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    jta_.append(StringList.concat(_pseudos.get(3),RETURN_LINE));
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==10) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    jta_.append(StringList.concat(_pseudos.get(0),RETURN_LINE));
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==4) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    surPanneau_.setPreferredSize(GraphicBeloteCard.getDimensionForSeveralCards(_nombre));
                }
                surPanneau_.setBackground(new Color(0,125,0));
                add(surPanneau_);
            }
        } else if(_nombreDeJoueurs==3) {
            for(int i=0;i<9;i++) {
                Panel surPanneau_=new Panel();
                surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                Panel panneau_=new Panel();
                panneau_.setLayout(new BorderLayout());
                JTextArea jta_=new JTextArea(EMPTY);
                jta_.setRows(3);
                jta_.setEditable(false);
                if(i==0) {
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(1),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(2),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==2) {
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(2),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(1),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==7) {
                    jta_.append(StringList.concat(_pseudos.get(0),RETURN_LINE));
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
                add(surPanneau_);
            }
        } else {
            for(int i=0;i<9;i++) {
                Panel surPanneau_=new Panel();
                Panel panneau_=new Panel();
                panneau_.setLayout(new BorderLayout());
                JTextArea jta_=new JTextArea(EMPTY);
                jta_.setRows(3);
                jta_.setEditable(false);
                if(i==0) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(2),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(3),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==2) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(3),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(2),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==3) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(1),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(4),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==5) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
                    if(horaire) {
                        jta_.append(StringList.concat(_pseudos.get(4),RETURN_LINE));
                    } else {
                        jta_.append(StringList.concat(_pseudos.get(1),RETURN_LINE));
                    }
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==7) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    jta_.append(StringList.concat(_pseudos.get(0),RETURN_LINE));
                    ScrollPane ascenseur_=new ScrollPane(jta_);
                    ascenseur_.setPreferredSize(new Dimension(100,50));
                    panneau_.add(ascenseur_,BorderLayout.NORTH);
                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg,SwingConstants.RIGHT,true);
                    carte_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    panneau_.add(carte_,BorderLayout.CENTER);
                    surPanneau_.add(panneau_);
                } else if(i==4) {
                    surPanneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    surPanneau_.setPreferredSize(GraphicBeloteCard.getDimensionForSeveralCards(_nombre));
                }
                surPanneau_.setBackground(new Color(0,125,0));
                add(surPanneau_);
            }
        }
    }

    public void retirerCartes() {
        centerDeck.removeAll();
        centerDeck.repaint();
    }
    public void supprimerCarteTalon() {
        centerDeck.remove(0);
        centerDeck.repaint();
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
            place_.repaint();
        }
    }
    /**Met a jour la carte a jouer d'un joueur
    donne en fonction du nombre de joueurs*/
    public void setCarteBelote(String _lg, byte _joueur,CardBelote _m) {
        GraphicBeloteCard place_= cards.getVal((int) _joueur);
        place_.setCarteEnJeu(_lg, _m);
        place_.repaint();
    }
}
