package cards.gui.panels;


import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.WindowCardsInt;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.GraphicBeloteCard;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.sml.util.TranslationsLg;
import code.util.IntMap;

public class CarpetBelote {

    private AbsPanel centerDeck;

    private final IntMap<GraphicBeloteCard> cards = new IntMap<GraphicBeloteCard>();

    private AbsPanel container;

    public static CarpetBelote initTapisBelote(TranslationsLg _lg, boolean _horaire, int _nombre, AbsCompoFactory _compoFactory) {
        AbsPanel container_ = _compoFactory.newGrid();
        CarpetBelote c_ = new CarpetBelote();
        c_.container = container_;
        for (int i = 0; i < 9; i++) {
            int k_ = Carpet.keyFour(_horaire, i);
            AbsPanel surPanneau_ = Carpet.surPanneau(_compoFactory,k_);
            popup(_lg, _nombre, _compoFactory, c_, surPanneau_, k_);
            surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
            Carpet.add(_compoFactory,container_,surPanneau_,(i+1) % 3 == 0);
        }
//        if(_nombreDeJoueurs==4) {
//
//            for (int i = 0; i < 9; i++) {
//                AbsPanel surPanneau_;
//                surPanneau_= _compoFactory.newLineBox();
//                if (i % 2 == 1) {
//
//                    GraphicBeloteCard carte_ = new GraphicBeloteCard(_lg,
//                            true, _compoFactory);
//                    carte_.setPreferredSize(Carpet
//                            .getMaxDimension());
//                    if (i == 1) {
//                        c_.cards.put(2, carte_);
//                    } else if (i == 3) {
//                        if (c_.horaire) {
//                            c_.cards.put(1, carte_);
//                        } else {
//                            c_.cards.put(3, carte_);
//                        }
//                    } else if (i == 5) {
//                        if (c_.horaire) {
//                            c_.cards.put(3, carte_);
//                        } else {
//                            c_.cards.put(1, carte_);
//                        }
//                    } else {
//                        c_.cards.put(0, carte_);
//                    }
//                    surPanneau_.add(carte_.getPaintableLabel());
//                } else if (i == 4) {
//                    surPanneau_.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
//                    c_.centerDeck = surPanneau_;
//                }
//                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
//                container_.add(surPanneau_);
//            }
//        } else if(_nombreDeJoueurs==6) {
//            for(int i=0;i<12;i++) {
//                AbsPanel surPanneau_=_compoFactory.newLineBox();
//                AbsPanel panneau_=_compoFactory.newBorder();
//                AbsTextArea jta_=_compoFactory.newTextArea(EMPTY,3,0);
//                jta_.setEditable(false);
//                if(i==3) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(4),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(), GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==5) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(4),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==6) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(5),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==8) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(5),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==1) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    jta_.append(StringUtil.concat(_pseudos.get(3),RETURN_LINE));
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==10) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    jta_.append(StringUtil.concat(_pseudos.get(0),RETURN_LINE));
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==4) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    surPanneau_.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
//                }
//                surPanneau_.setBackground(GuiConstants.newColor(0,125,0));
//                container_.add(surPanneau_);
//            }
//        } else if(_nombreDeJoueurs==3) {
//            for(int i=0;i<9;i++) {
//                AbsPanel surPanneau_;
//                surPanneau_= _compoFactory.newLineBox();
//                AbsPanel panneau_=_compoFactory.newBorder();
//                AbsTextArea jta_=_compoFactory.newTextArea(EMPTY,3,0);
//                jta_.setEditable(false);
//                if(i==0) {
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==2) {
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==7) {
//                    jta_.append(StringUtil.concat(_pseudos.get(0),RETURN_LINE));
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==4) {
//                    surPanneau_.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
//                }
//                surPanneau_.setBackground(GuiConstants.newColor(0,125,0));
//                container_.add(surPanneau_);
//            }
//        } else {
//            for(int i=0;i<9;i++) {
//                AbsPanel surPanneau_=_compoFactory.newLineBox();
//                AbsPanel panneau_=_compoFactory.newBorder();
//                AbsTextArea jta_=_compoFactory.newTextArea(EMPTY,3,0);
//                jta_.setEditable(false);
//                if(i==0) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(3),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==2) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(3),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(2),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==3) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(4),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==5) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    if(c_.horaire) {
//                        jta_.append(StringUtil.concat(_pseudos.get(4),RETURN_LINE));
//                    } else {
//                        jta_.append(StringUtil.concat(_pseudos.get(1),RETURN_LINE));
//                    }
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==7) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    jta_.append(StringUtil.concat(_pseudos.get(0),RETURN_LINE));
//                    AbsScrollPane ascenseur_=_compoFactory.newAbsScrollPane(jta_);
//                    ascenseur_.setPreferredSize(new MetaDimension(100,50));
//                    panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_NORTH);
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(_lg, true, _compoFactory);
//                    carte_.setPreferredSize(Carpet.getMaxDimension());
//                    panneau_.add(carte_.getPaintableLabel(),GuiConstants.BORDER_LAYOUT_CENTER);
//                    surPanneau_.add(panneau_);
//                } else if(i==4) {
//                    surPanneau_= _compoFactory.newLineBox();
//                    surPanneau_.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
//                }
//                surPanneau_.setBackground(GuiConstants.newColor(0,125,0));
//                container_.add(surPanneau_);
//            }
//        }
        return c_;
    }

    private static void popup(TranslationsLg _lg, int _nombre, AbsCompoFactory _compoFactory, CarpetBelote _c, AbsPanel _surPanneau, int _k) {
        if (_k >= 0) {
            GraphicBeloteCard carte_ = new GraphicBeloteCard(_lg,
                    true, _compoFactory);
            carte_.setPreferredSize(Carpet
                    .getMaxDimension());
            _c.cards.put(_k, carte_);
            _surPanneau.add(carte_.getPaintableLabel(),_compoFactory.newGridCts());
        } else if (_k == -1) {
            _surPanneau.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
            _c.centerDeck = _surPanneau;
        }
    }

    public void retirerCartes() {
        centerDeck.removeAll();
        centerDeck.validate();
    }

    public void setTalonBelote(WindowCardsInt _fact, TranslationsLg _lg, HandBelote _m) {
        GraphicBeloteCard cg_=new GraphicBeloteCard(_fact.getFrames(),_lg,_m.premiereCarte(), true);
        cg_.setPreferredSize(Carpet.getMaxDimension());
        centerDeck.add(cg_.getPaintableLabel());
    }

    /**Place les dos des cartes (une pour chaque joueur) sur le tapis avant et apres chaque pli*/
    public void setCartesBeloteJeu(AbstractImageFactory _fact, byte _nombreDeJoueurs, TranslationsLg _lg) {
        for(byte joueur_=0;joueur_<_nombreDeJoueurs;joueur_++) {
            GraphicBeloteCard place_ = cards.getVal((int) joueur_);
            place_.setJeu(_lg);
            AbsMetaLabelCard.paintCard(_fact,place_);
        }
    }
    /**Met a jour la carte a jouer d'un joueur
    donne en fonction du nombre de joueurs*/
    public void setCarteBelote(AbstractImageFactory _fact, TranslationsLg _lg, byte _joueur, CardBelote _m) {
        GraphicBeloteCard place_= cards.getVal((int) _joueur);
        place_.setCarteEnJeu(_fact,_lg, _m,_m.getId());
        AbsMetaLabelCard.paintCard(_fact,place_);
    }

    public AbsPanel getCenterDeck() {
        return centerDeck;
    }

    public AbsPanel getContainer() {
        return container;
    }
}
