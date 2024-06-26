package cards.gui.panels;

import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.GraphicCard;
import cards.gui.labels.IntCardConverter;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.IntMap;

public abstract class AbsCarpet<T> {

    /** sens de distribution des cartes */
    private AbsPanel centerDeck;

    private final IntMap<GraphicCard<T>> cards = new IntMap<GraphicCard<T>>();
    private final AbsPanel container;
    private final int ecart;
    protected AbsCarpet(TranslationsLg _lg, int _nombreDeJoueurs, boolean _horaire, int _nombre, AbstractProgramInfos _compoFactory) {
        ecart = _nombre;
        if (_nombreDeJoueurs == 4) {
            container= fourPlayers(_lg, _horaire, _nombre, _compoFactory);
        } else if (_nombreDeJoueurs == 2) {
            container= twoPlayers(_lg, _nombre, _compoFactory);
        } else if (_nombreDeJoueurs == 6) {
            container= sixPlayers(_lg, _horaire, _nombre, _compoFactory);
//            cont_.add(sub1_);
//            cont_.add(sub2_);
//            cont_.add(sub3_);
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
            container= threePlayers(_lg, _horaire, _nombre, _compoFactory);
        } else {
            container= fivePlayers(_lg, _horaire, _nombre, _compoFactory);
        }
    }

    private AbsPanel fivePlayers(TranslationsLg _lg, boolean _horaire, int _nombre, AbstractProgramInfos _compoFactory) {
        AbsCompoFactory compo_ = _compoFactory.getCompoFactory();
        AbsPanel cont_ = compo_.newGrid();
        for (int i = 0; i < 9; i++) {
            int k_ = Carpet.keyFive(_horaire, i);
            AbsPanel surPanneau_ = Carpet.surPanneau(compo_, k_);
            popup(_lg, _nombre, _compoFactory, surPanneau_, k_);
            surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
            Carpet.add(compo_,cont_,surPanneau_,(i+1) % 3 == 0);
        }
        cont_.setBackground(GuiConstants.newColor(0, 125, 0));
        return cont_;
    }

    private AbsPanel threePlayers(TranslationsLg _lg, boolean _horaire, int _nombre, AbstractProgramInfos _compoFactory) {
        AbsCompoFactory compo_ = _compoFactory.getCompoFactory();
        AbsPanel cont_ = compo_.newGrid();
        for (int i = 0; i < 9; i++) {
            int k_ = Carpet.keyThree(_horaire, i);
            AbsPanel surPanneau_ = Carpet.surPanneau(compo_, k_);
            popup(_lg, _nombre, _compoFactory, surPanneau_, k_);
            surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
            Carpet.add(compo_,cont_,surPanneau_,(i+1) % 3 == 0);
        }
        cont_.setBackground(GuiConstants.newColor(0, 125, 0));
        return cont_;
    }

    private AbsPanel sixPlayers(TranslationsLg _lg, boolean _horaire, int _nombre, AbstractProgramInfos _compoFactory) {
        AbsCompoFactory compo_ = _compoFactory.getCompoFactory();
        AbsPanel cont_ = compo_.newGrid();
        for (int i = 0; i < 12; i++) {
            if (i == 7) {
                continue;
            }
            int k_ = Carpet.keySix(_horaire, i);
            AbsPanel surPanneau_ = Carpet.surPanneau(compo_, k_);
            popup(_lg, _nombre, _compoFactory, surPanneau_, k_);
            surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
            Carpet.add(compo_,cont_,surPanneau_,width(i));
        }
        cont_.setBackground(GuiConstants.newColor(0, 125, 0));
        return cont_;
    }

    private AbsPanel twoPlayers(TranslationsLg _lg, int _nombre, AbstractProgramInfos _compoFactory) {
        AbsCompoFactory compo_ = _compoFactory.getCompoFactory();
        AbsPanel cont_ = compo_.newGrid();
        for (int i = 0; i < 9; i++) {
            int k_ = Carpet.keyTwo(i);
            AbsPanel surPanneau_ = Carpet.surPanneau(compo_, k_);
            popup(_lg, _nombre, _compoFactory, surPanneau_, k_);
            surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
            Carpet.add(compo_,cont_,surPanneau_,(i+1) % 3 == 0);
        }
        cont_.setBackground(GuiConstants.newColor(0, 125, 0));
        return cont_;
    }

    private AbsPanel fourPlayers(TranslationsLg _lg, boolean _horaire, int _nombre, AbstractProgramInfos _compoFactory) {
        AbsCompoFactory compo_ = _compoFactory.getCompoFactory();
        AbsPanel cont_ = compo_.newGrid();
        for (int i = 0; i < 9; i++) {
            int k_ = Carpet.keyFour(_horaire, i);
            AbsPanel surPanneau_ = Carpet.surPanneau(compo_, k_);
            popup(_lg, _nombre, _compoFactory, surPanneau_, k_);
            surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
            Carpet.add(compo_,cont_,surPanneau_,(i+1) % 3 == 0);
        }
        cont_.setBackground(GuiConstants.newColor(0, 125, 0));
        return cont_;
    }

    private static int width(int _i) {
        if (_i == 3 || _i == 6 || _i == 11) {
            return GuiConstants.REMAINDER;
        }
        if (_i == 5) {
            return 2;
        }
        return 1;
    }

    private void popup(TranslationsLg _lg, int _nombre, AbstractProgramInfos _compoFactory, AbsPanel _surPanneau, int _k) {
        AbsCompoFactory compo_ = _compoFactory.getCompoFactory();
        if (_k >= 0) {
            GraphicCard<T> carte_ = new GraphicCard<T>(converter(), true,
                    compo_, _lg);
            carte_.setPreferredSize(Carpet
                    .getMaxDimension());
            AbsMetaLabelCard.paintCard(_compoFactory.getImageFactory(),carte_);
            cards.put(_k, carte_);
            _surPanneau.add(carte_.getPaintableLabel(),compo_.newGridCts());
        } else if (_k == -1) {
            _surPanneau.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
            centerDeck = _surPanneau;
        }
    }
    protected abstract IntCardConverter<T> converter();

    public AbsPanel getCenterDeck() {
        return centerDeck;
    }

    public int getEcart() {
        return ecart;
    }

    public AbsPanel getContainer() {
        return container;
    }

    public IntMap<GraphicCard<T>> getCards() {
        return cards;
    }
}
