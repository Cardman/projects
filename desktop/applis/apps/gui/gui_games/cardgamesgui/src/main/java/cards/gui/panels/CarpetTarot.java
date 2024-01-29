package cards.gui.panels;


import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.GraphicTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.sml.util.TranslationsLg;
import code.util.*;
import code.util.core.IndexConstants;

public class CarpetTarot {

    /** sens de distribution des cartes */
    private AbsPanel centerDeck;

    private final IntMap<GraphicTarotCard> cards = new IntMap<GraphicTarotCard>();
    private AbsPanel container;

    public static CarpetTarot initTapisTarot(TranslationsLg _lg, int _nombreDeJoueurs, boolean _horaire, int _nombre, AbsCompoFactory _compoFactory) {
        CarpetTarot c_ = new CarpetTarot();
        AbsPanel cont_;
        if (_nombreDeJoueurs == 4) {
            cont_ = _compoFactory.newGrid(0, 3);
            for (int i = 0; i < 9; i++) {
                AbsPanel surPanneau_ = _compoFactory.newLineBox();
                int k_ = Carpet.keyFour(_horaire, i);
                popup(_lg, _nombre, _compoFactory, c_, surPanneau_, k_);
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                cont_.add(surPanneau_);
            }
        } else if (_nombreDeJoueurs == 6) {
            cont_ = _compoFactory.newPageBox();
            AbsPanel sub1_ = _compoFactory.newGrid(0,4);
            AbsPanel sub2_ = _compoFactory.newGrid(0,3);
            AbsPanel sub3_ = _compoFactory.newGrid(0,4);
            for (int i = 0; i < 12; i++) {
                if (i == 7) {
                    continue;
                }
                int k_ = Carpet.keySix(_horaire, i);
                AbsPanel surPanneau_ = _compoFactory.newLineBox();
                popup(_lg, _nombre, _compoFactory, c_, surPanneau_, k_);
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                Carpet.choose(sub1_, sub2_, sub3_, i, surPanneau_);
            }
            cont_.add(sub1_);
            cont_.add(sub2_);
            cont_.add(sub3_);
//            AbsPanel sub_ = _compoFactory.newGrid(0,4);
//            for (int i = 0; i < 4; i++) {
//                AbsPanel surPanneau_ = _compoFactory.newLineBox();
//                int k_ = keySix(_horaire, i);
//                if (k_ >= 0) {
//                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
//                            true, _compoFactory);
//                    carte_.setPreferredSize(Carpet
//                            .getMaxDimension());
//                    c_.cards.put(k_, carte_);
//                    surPanneau_.add(carte_.getPaintableLabel());
//                }
//                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
//                sub_.add(surPanneau_);
//            }
//            cont_.add(sub_);
//            sub_ = _compoFactory.newGrid(0,3);
//            for (int i = 4; i < 7; i++) {
//                AbsPanel surPanneau_ = _compoFactory.newLineBox();
//                int k_ = keySix(_horaire, i);
//                if (k_ >= 0) {
//                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
//                            true, _compoFactory);
//                    carte_.setPreferredSize(Carpet
//                            .getMaxDimension());
//                    c_.cards.put(k_, carte_);
//                    surPanneau_.add(carte_.getPaintableLabel());
//                } else if (k_ == -1){
//                    surPanneau_ = _compoFactory.newLineBox();
//                    surPanneau_.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
//                    c_.centerDeck = surPanneau_;
//                }
//                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
//                sub_.add(surPanneau_);
//            }
//            cont_.add(sub_);
//            sub_ = _compoFactory.newGrid(0,4);
//            for (int i = 8; i < 12; i++) {
//                AbsPanel surPanneau_ = _compoFactory.newLineBox();
//                int k_ = keySix(_horaire, i);
//                if (k_ >= 0) {
//                    GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
//                            true, _compoFactory);
//                    carte_.setPreferredSize(Carpet
//                            .getMaxDimension());
//                    c_.cards.put(k_, carte_);
//                    surPanneau_.add(carte_.getPaintableLabel());
//                }
//                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
//                sub_.add(surPanneau_);
//            }
//            cont_.add(sub_);
        } else if (_nombreDeJoueurs == 3) {
            cont_ = _compoFactory.newGrid(0, 3);
            for (int i = 0; i < 9; i++) {
                int k_ = Carpet.keyThree(_horaire, i);
                AbsPanel surPanneau_ = _compoFactory.newLineBox();
                popup(_lg, _nombre, _compoFactory, c_, surPanneau_, k_);
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                cont_.add(surPanneau_);
            }
        } else {
            cont_ = _compoFactory.newGrid(0, 3);
            for (int i = 0; i < 9; i++) {
                AbsPanel surPanneau_ = _compoFactory.newLineBox();
                int k_ = Carpet.keyFive(_horaire, i);
                popup(_lg, _nombre, _compoFactory, c_, surPanneau_, k_);
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                cont_.add(surPanneau_);
            }
        }
        cont_.setBackground(GuiConstants.newColor(0, 125, 0));
        c_.container=cont_;
        return c_;
    }

    private static void popup(TranslationsLg _lg, int _nombre, AbsCompoFactory _compoFactory, CarpetTarot _c, AbsPanel _surPanneau, int _k) {
        if (_k >= 0) {
            GraphicTarotCard carte_ = new GraphicTarotCard(_lg,
                    true, _compoFactory);
            carte_.setPreferredSize(Carpet
                    .getMaxDimension());
            _c.cards.put(_k, carte_);
            _surPanneau.add(carte_.getPaintableLabel());
        } else if (_k == -1) {
            _surPanneau.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
            _c.centerDeck = _surPanneau;
        }
    }

    public void retirerCartes() {
        centerDeck.removeAll();
        centerDeck.validate();
    }

//    public void supprimerCarteTalon() {
//        centerDeck.remove(0);
//        centerDeck.repaint();
//    }

    public void setEcart(TranslationsLg _lg, HandTarot _m, AbsCompoFactory _compoFactory) {
        centerDeck.removeAll();
        setTalonTarot(_lg,_m, _compoFactory);
    }

    public void setTalonTarot(TranslationsLg _lg, HandTarot _m, AbsCompoFactory _compoFactory) {
        centerDeck.setBackground(GuiConstants.newColor(0, 125, 0));
        boolean entered_ = false;
        int nbCards_ = _m.total();
        for (byte b = IndexConstants.FIRST_INDEX; b < nbCards_; b++) {
            GraphicTarotCard cg_ = new GraphicTarotCard(_lg,
                    !entered_, _compoFactory);
            cg_.setPreferredSize(entered_);
            centerDeck.add(cg_.getPaintableLabel());
            entered_ = true;
        }
        centerDeck.validate();
    }

    /**
    Place les dos des cartes (une pour chaque joueur) sur le tapis avant et
    apres chaque pli
    */
    public void setCartesTarotJeu(AbstractImageFactory _fact,TranslationsLg _lg, byte _nombreDeJoueurs) {
        for (byte joueur_ = 0; joueur_ < _nombreDeJoueurs; joueur_++) {
            GraphicTarotCard place_ = cards.getVal((int) joueur_);
            place_.setJeu(_lg);
            AbsMetaLabelCard.paintCard(_fact,place_);
        }
    }

    /**
    Met a jour la carte a jouer d'un joueur donne en fonction du nombre de
    joueurs
    */
    public void setCarteTarot(AbstractImageFactory _fact, TranslationsLg _lg, byte _joueur, CardTarot _m) {
        GraphicTarotCard place_ = cards.getVal((int) _joueur);
        place_.setCarteEnJeu(_fact,_lg,_m,_m.getId());
        AbsMetaLabelCard.paintCard(_fact,place_);
    }

    public AbsPanel getCenterDeck() {
        return centerDeck;
    }

    public AbsPanel getContainer() {
        return container;
    }
}
