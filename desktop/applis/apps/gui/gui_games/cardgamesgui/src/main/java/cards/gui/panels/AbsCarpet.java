package cards.gui.panels;

import cards.gui.labels.GraphicCard;
import cards.gui.labels.IntCardConverter;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.sml.util.TranslationsLg;
import code.util.IntMap;

public abstract class AbsCarpet<T> {

    /** sens de distribution des cartes */
    private AbsPanel centerDeck;

    private final IntMap<GraphicCard<T>> cards = new IntMap<GraphicCard<T>>();
    private final AbsPanel container;
    protected AbsCarpet(TranslationsLg _lg, int _nombreDeJoueurs, boolean _horaire, int _nombre, AbsCompoFactory _compoFactory) {
        AbsPanel cont_;
        if (_nombreDeJoueurs == 4) {
            cont_ = _compoFactory.newGrid();
            for (int i = 0; i < 9; i++) {
                int k_ = Carpet.keyFour(_horaire, i);
                AbsPanel surPanneau_ = Carpet.surPanneau(_compoFactory, k_);
                popup(_lg, _nombre, _compoFactory, surPanneau_, k_);
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                Carpet.add(_compoFactory,cont_,surPanneau_,(i+1) % 3 == 0);
            }
        } else if (_nombreDeJoueurs == 6) {
            cont_ = _compoFactory.newGrid();
            for (int i = 0; i < 12; i++) {
                if (i == 7) {
                    continue;
                }
                int k_ = Carpet.keySix(_horaire, i);
                AbsPanel surPanneau_ = Carpet.surPanneau(_compoFactory, k_);
                popup(_lg, _nombre, _compoFactory, surPanneau_, k_);
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                Carpet.add(_compoFactory,cont_,surPanneau_,width(i));
            }
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
            cont_ = _compoFactory.newGrid();
            for (int i = 0; i < 9; i++) {
                int k_ = Carpet.keyThree(_horaire, i);
                AbsPanel surPanneau_ = Carpet.surPanneau(_compoFactory, k_);
                popup(_lg, _nombre, _compoFactory, surPanneau_, k_);
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                Carpet.add(_compoFactory,cont_,surPanneau_,(i+1) % 3 == 0);
            }
        } else {
            cont_ = _compoFactory.newGrid();
            for (int i = 0; i < 9; i++) {
                int k_ = Carpet.keyFive(_horaire, i);
                AbsPanel surPanneau_ = Carpet.surPanneau(_compoFactory, k_);
                popup(_lg, _nombre, _compoFactory, surPanneau_, k_);
                surPanneau_.setBackground(GuiConstants.newColor(0, 125, 0));
                Carpet.add(_compoFactory,cont_,surPanneau_,(i+1) % 3 == 0);
            }
        }
        cont_.setBackground(GuiConstants.newColor(0, 125, 0));
        container=cont_;
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

    private void popup(TranslationsLg _lg, int _nombre, AbsCompoFactory _compoFactory, AbsPanel _surPanneau, int _k) {
        if (_k >= 0) {
            GraphicCard<T> carte_ = new GraphicCard<T>(converter(), true,
                    _compoFactory, _lg);
            carte_.setPreferredSize(Carpet
                    .getMaxDimension());
            cards.put(_k, carte_);
            _surPanneau.add(carte_.getPaintableLabel(),_compoFactory.newGridCts());
        } else if (_k == -1) {
            _surPanneau.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
            centerDeck = _surPanneau;
        }
    }
    protected abstract IntCardConverter<T> converter();

    public AbsPanel getCenterDeck() {
        return centerDeck;
    }

    public AbsPanel getContainer() {
        return container;
    }

    public IntMap<GraphicCard<T>> getCards() {
        return cards;
    }
}
