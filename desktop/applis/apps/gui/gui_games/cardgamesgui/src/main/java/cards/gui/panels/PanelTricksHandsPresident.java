package cards.gui.panels;


import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.containers.ContainerSingUtil;
import cards.gui.containers.ContainerSingleImpl;
import cards.gui.labels.PresidentCardConverter;
import cards.gui.panels.events.ListenerCards;
import cards.gui.panels.events.ListenerTricks;
import cards.president.*;
import cards.president.enumerations.CardPresident;
import code.gui.*;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class PanelTricksHandsPresident implements ViewablePanelTricksHands {

    private static final String CURRENT_TRICK = "";
    private static final String EMPTY =CURRENT_TRICK;
    private static final String SPACE ="_";
    private static final String DEFAULT ="Default";
    private final AbsPanel tricks;
    private final AbsPanel selectedTrick;
    private final AbsPanel hands;

    private final IntTreeComboBox trickNumber;
    private final IntTreeComboBox cardNumberTrick;
    private final TricksHandsPresident tricksHands;
    private final ChangeableTitle parent;
    private final byte numberPlayers;
    private final DisplayingPresident displayingPresident;
    private final WindowCardsInt window;
    private final AbsPanel container;

    public PanelTricksHandsPresident(ChangeableTitle _parent,
            TricksHandsPresident _tricksHands,
            byte _numberPlayers,
            StringList _pseudos,
            DisplayingPresident _displayingPresident, WindowCardsInt _window) {
        window = _window;
        TranslationsLg lg_ = window.getFrames().currentLg();
        numberPlayers = _numberPlayers;
        displayingPresident = _displayingPresident;
        StringMap<String> messages_ = ContainerSingleImpl.file(lg_);
        parent = _parent;
        tricksHands = _tricksHands;
        DealPresident dealt_ = tricksHands.getDistribution();
//        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        container = window.getCompoFactory().newBorder();
        AbsPanel cards_ = window.getCompoFactory().newLineBox();
        AbsPanel players_ = window.getCompoFactory().newGrid(0,1);
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_numberPlayers; joueur_++) {
            players_.add(WindowCards.getBlankCard(window,_pseudos, joueur_));
        }
//        int nbBots_ = _numberPlayers - 1;
//        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
//            players_.add(getBlankCard(_pseudos, joueur_));
//        }
        cards_.add(players_);
        tricks = window.getCompoFactory().newLineBox();
        cards_.add(tricks);
        selectedTrick = window.getCompoFactory().newGrid();
        cards_.add(selectedTrick);
        hands=window.getCompoFactory().newGrid(0,1);
        //boolean entered_ = false;
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_numberPlayers; joueur_++) {
            AbsPanel sousPanneau3_ = feedCards(dealt_.hand(joueur_).getCards());
//            entered_ = false;
//            for(CardPresident c: dealt_.main(joueur_))
//            {
//                GraphicPresidentCard carteGraphique_=new GraphicPresidentCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau3_.add(carteGraphique_);
//                entered_ = true;
//            }
            hands.add(sousPanneau3_);
        }
//        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
//            hands.add(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
//        }
        cards_.add(hands);
        AbsPanel sousPanneau2_=window.getCompoFactory().newGrid(0,1);
//        sousPanneau3_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
//        for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(dealt_.derniereMain())) {
//            sousPanneau3_.add(c);
//        }
//        entered_ = false;
//        for(CardPresident c: dealt_.derniereMain())
//        {
//            GraphicPresidentCard carteGraphique_=new GraphicPresidentCard(c,SwingConstants.RIGHT,!entered_);
//            carteGraphique_.setPreferredSize(entered_);
//            sousPanneau3_.add(carteGraphique_);
//        }
//        sousPanneau2_.add(sousPanneau3_);
        cards_.add(sousPanneau2_);
        container.add(cards_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel selectionGameState_=window.getCompoFactory().newLineBox();
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_TRICK)));
//        Integer[] numerosPlis_;
//        numerosPlis_=new Integer[tricks_.size()+2];
        int nbTricks_ = tricksHands.getFilledTricksCount();
//        int nbNumbers_ = nbTricks_ + 2;
        int nbNumbers_ = nbTricks_ + 1;
//        numerosPlis_=new Integer[nbNumbers_];
        IntMap<String> map_ = new IntMap<String>();
//        Ints list_ = new Ints();
        map_.put(-2, EMPTY);
        for(int indicePli_ = IndexConstants.FIRST_INDEX; indicePli_<nbNumbers_; indicePli_++) {
//            numerosPlis_[indicePli_]=indicePli_-1;
//            list_.add(indicePli_-1);
            map_.put(indicePli_-1, Long.toString(indicePli_-1L));
        }
        //Add this line to store at last the current trick
//        list_.add(null);
//        numerosPlis_[nbNumbers_ - 1] = CURRENT_TRICK;
//        trickNumber=new NumComboBox(numerosPlis_);
        trickNumber=new IntTreeComboBox(GuiBaseUtil.combo(window.getImageFactory(),new StringList(),-1, window.getCompoFactory()));
        trickNumber.refresh(map_);
        trickNumber.setListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber.self());
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_CARD)));
//        if (nbTricks_ == CustList.SIZE_EMPTY) {
//            int nbCards_ = game.getProgressingTrick().total();
//            Integer[] numerosJoueurs_=new Integer[nbCards_+1];
//            for(byte indiceJoueur_=CustList.FIRST_INDEX;indiceJoueur_<=nbCards_;indiceJoueur_++) {
//                numerosJoueurs_[indiceJoueur_]=(int) indiceJoueur_;
//            }
//            cardNumberTrick=new JComboBox<>(numerosJoueurs_);
//            cardNumberTrick.setSelectedItem(CURRENT_TRICK);
//            pack = false;
//            changeTrick();
//        } else {
//            cardNumberTrick=new JComboBox<>();
//        }
        cardNumberTrick=new IntTreeComboBox(GuiBaseUtil.combo(window.getImageFactory(),new StringList(),-1, window.getCompoFactory()));
//        Integer[] numerosJoueurs_=new Integer[_numberPlayers];
//        for(byte indiceJoueur_=CustList.FIRST_INDEX;indiceJoueur_<_numberPlayers;indiceJoueur_++) {
//            numerosJoueurs_[indiceJoueur_]=indiceJoueur_+1;
//        }
//        cardNumberTrick=new JComboBox<>(numerosJoueurs_);
//        cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
        cardNumberTrick.setListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick.self());
        container.add(selectionGameState_,GuiConstants.BORDER_LAYOUT_SOUTH);
        changeTrick();
    }

    @Override
    public void changeTrick() {
//        TranslationsLg lg_ = window.getFrames().currentLg();
//        Object o_ = trickNumber.getSelectedItem();
//        if (StringList.eq(CURRENT_TRICK, String.valueOf(o_)))
        if (trickNumber.getSelectedIndex() == 0) {
            tricks.removeAll();
//            CoordsHandsMap panels_ = new CoordsHandsMap();
//            CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
            tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers);
//            byte entameur_=tricksHands.getProgressingTrick().getEntameur();
//            int indice_=0;
//            int col_ = 0;
//            int row_ = 0;
//            while(indice_<entameur_) {
//                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
//                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
//                etiquette2_.setOpaque(true);
//                etiquette2_.setBackground(GuiConstants.WHITE);
//                row_++;
//                panels_.put(new CoordsHands(0, indice_), etiquette2_);
//                indice_++;
//            }
//            int nb_ = tricksHands.getProgressingTrick().getNombreDeCartesParJoueur();
//            for(HandPresident h_:tricksHands.getProgressingTrick()) {
//                AbsPanel cards_ = window.getCompoFactory().newLineBox();
//                for (GraphicCard<CardPresident> c: ContainerPresident.getGraphicCards(window,lg_,h_.getCards(), new PresidentCardConverter())) {
//                    cards_.add(c.getPaintableLabel());
//                    cardsLab_.add(c);
//                    AbsMetaLabelCard.paintCard(window.getImageFactory(),c);
//                }
//                if (h_.estVide()) {
//                    AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
//                    etiquette2_.setPreferredSize(Carpet.getDimensionForSeveralCards(nb_));
//                    etiquette2_.setOpaque(true);
//                    etiquette2_.setForeground(GuiConstants.WHITE);
//                    etiquette2_.setBackground(GuiConstants.WHITE);
//                    cards_.add(etiquette2_);
//                }
////                indice_++;
//                panels_.put(new CoordsHands(col_, row_), cards_);
//                row_++;
//                if (row_ % numberPlayers == 0) {
//                    row_ = 0;
//                    col_++;
//                }
//            }
//            indice_=0;
//            while(row_ <numberPlayers) {
//                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
//                etiquette2_.setOpaque(true);
//                etiquette2_.setForeground(GuiConstants.WHITE);
//                etiquette2_.setBackground(GuiConstants.WHITE);
//                panels_.put(new CoordsHands(col_, row_), etiquette2_);
//                row_++;
//            }
//            int indexRem_ = cards.remove(selectedTrick);
//            AbsPanel gr_ = window.getCompoFactory().newGrid(0, col_ + 1);
//            for (CoordsHands c: panels_.getKeys()) {
//                gr_.add(panels_.getVal(c));
//            }
////            repaintCards(cardsLab_);
//            selectedTrick = gr_;
//            cards.add(gr_,indexRem_);
//            int nbCards_ = tricksHands.getProgressingTrick().total();
//            int[] numerosJoueurs_=new int[nbCards_+1];
//            for(int indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<=nbCards_; indiceJoueur_++) {
//                numerosJoueurs_[indiceJoueur_]= indiceJoueur_;
//            }
//            cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
//            cardNumberTrick.setItems(numerosJoueurs_);
            beforePlay();
            return;
        }
        if (trickNumber.getSelectedIndex() == 1) {
            tricks.removeAll();
            tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers, -1);
            beforePlay();
            return;
        }
//        CoordsHandsMap panels_ = new CoordsHandsMap();
//        byte numeroPli_=Byte.parseByte(o_.toString());
//        CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
        int numeroPli_= trickNumber.getSelectedIndex() - 2;
        int no_ = numeroPli_;
        numeroPli_ = tricksHands.getFilledTricksIndex(numeroPli_);
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers, numeroPli_);
        refreshHands();
//        hands.removeAll();
//        DealPresident dealt_ = tricksHands.getDistribution();
//        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
//        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
//            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
//            for (GraphicCard<CardPresident> c: ContainerPresident.getGraphicCards(window,lg_,dealt_.hand(joueur_).getCards(), new PresidentCardConverter())) {
//                sousPanneau4_.add(c.getPaintableLabel());
//            }
////            boolean entered_ = false;
////            for(CardPresident c: dealt_.main(joueur_))
////            {
////                GraphicPresidentCard carteGraphique_=new GraphicPresidentCard(c,SwingConstants.RIGHT,!entered_);
////                carteGraphique_.setPreferredSize(entered_);
////                sousPanneau4_.add(carteGraphique_);
////                entered_ = true;
////            }
//            hands.add(sousPanneau4_);
//        }
//        int nbBots_ = numberPlayers;
//        nbBots_ --;
//        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
//            hands.add(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
//        }
        tricks.removeAll();
        for (int i = 0; i < no_; i++) {
            AbsPanel g_ = window.getCompoFactory().newGrid();
            putCards(trickPresident(tricksHands.getFilledTricksIndex(i)),g_);
            tricks.add(g_);
        }
        selectedTrick.removeAll();
        currentTrick(trickPresident(numeroPli_));

//        if(tricks_.isValidIndex(numeroPli_)) {
//            currentTrick(tricks_.get(numeroPli_));
//        } else {
//            currentTrick(tricksHands.getProgressingTrick());
//        }
        parent.pack();
    }

    private void beforePlay() {
        refreshHands();
        selectedTrick.removeAll();
        parent.pack();
        cardNumberTrick.removeAllItems();
        cardNumberTrick.selectItem(-1);
        cardNumberTrick.getCombo().repaint();
        changeCard();
    }

    private void refreshHands() {
//        TranslationsLg lg_ = window.getFrames().currentLg();
        hands.removeAll();
        DealPresident dealt_ = tricksHands.getDistribution();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
            AbsPanel sousPanneau4_ = feedCards(dealt_.hand(joueur_).getCards());
            hands.add(sousPanneau4_);
        }
    }

    private void currentTrick(TrickPresident _current) {
//        TranslationsLg lg_ = window.getFrames().currentLg();
        putCards(_current, selectedTrick);
//            selectedTrick = gr_;
//            cards.add(gr_,indexRem_);
//            repaintCards(cardsLab_);
        cardNumberTrick.removeAllItems();
        int nbCards_ = _current.total();
//        int[] numerosJoueurs_=new int[nbCards_ + 1];
        for(int indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<=nbCards_; indiceJoueur_++) {
//            numerosJoueurs_[indiceJoueur_]= indiceJoueur_;
            cardNumberTrick.addItem(Integer.toString(indiceJoueur_));
        }
//            cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
//        cardNumberTrick.setItems(numerosJoueurs_);
        cardNumberTrick.selectItem(nbCards_);
        cardNumberTrick.getCombo().repaint();
        changeCard();
    }

    private void putCards(TrickPresident _current, AbsPanel _dest) {
        CoordsHandsMap panels_ = new CoordsHandsMap();
//        CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
        //            int sum_ = tricks_.get(numeroPli_-1).total() + tricks_.get(numeroPli_-1).getEntameur();
        int nb_ = _current.getNombreDeCartesParJoueur();
        byte entameur_= _current.getEntameur();
        int row_ = row(panels_, nb_, entameur_);
        int col_ = 0;
        for(HandPresident h_: _current) {
            buildCards(panels_, nb_, h_, new CoordsHands(col_, row_));

            row_ = incrRow(row_);
            col_ = incrCol(row_,col_);
//            col_ = incrCol(_current,indice2_,row_,col_);
            //            if (row_ == 0) {
//                col_++;
//            }
//            row_++;
//            if (row_ % numberPlayers == 0) {
//                row_ = 0;
//                col_++;
//            }
        }
        afterCards(panels_, nb_, row_, col_, _dest);
    }

    private void afterCards(CoordsHandsMap _panels, int _nb, int _row, int _col, AbsPanel _pr) {
        int col_ = _col;
        end(_panels, _nb, col_, _row);
        if (_row <= 0) {
            col_--;
        }
        roll(_panels, col_, _pr);
    }

    private void buildCards(CoordsHandsMap _panels, int _nb, HandPresident _h, CoordsHands _ch) {
        AbsPanel cards_ = feedCards(_h.getCards());
        if (_h.estVide()) {
            _panels.put(_ch, blank(_nb));
        } else {
            _panels.put(_ch, cards_);
        }
    }

    private AbsPanel feedCards(IdList<CardPresident> _hand) {
        return new ContainerSingUtil<CardPresident>(new PresidentCardConverter()).getGraphicCardsGenePanel(window,_hand);
//        AbsPanel cards_ = window.getCompoFactory().newLineBox();
//        TranslationsLg lg_ = window.getFrames().currentLg();
//        for (GraphicCard<CardPresident> c: ContainerPresident.getGraphicCards(window, lg_, _hand, new PresidentCardConverter())) {
//            _cards.add(c.getPaintableLabel());
//        }
//        return _cards;
    }

    @Override
    public void changeCard() {
//        TranslationsLg lg_ = window.getFrames().currentLg();
//        CoordsHandsMap panels_ = new CoordsHandsMap();
//        if (StringList.eq(CURRENT_TRICK,String.valueOf(o_)))
        if (trickNumber.getSelectedIndex() <= 1) {
            return;
//            CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
//            if (cardNumberTrick.getSelectedItem().isEmpty()) {
//                return;
//            }
//            int numeroCarte_= cardNumberTrick.getSelectedIndex();
//            numeroCarte_--;
//            DealPresident dealt_ = tricksHands.getDistribution();
//            tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingPresident, numberPlayers, numeroCarte_);
//            hands.removeAll();
//            for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
//                AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
//                for (GraphicCard<CardPresident> c: ContainerPresident.getGraphicCards(window, lg_,dealt_.hand(joueur_).getCards(), new PresidentCardConverter())) {
//                    sousPanneau4_.add(c.getPaintableLabel());
//                }
//                hands.add(sousPanneau4_);
//            }
//            byte entameur_=tricksHands.getProgressingTrick().getEntameur();
//            int indice_=0;
//            int indice2_=0;
//            int col_ = 0;
//            int row_ = 0;
//            while(indice_<entameur_) {
//                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
//                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
//                etiquette2_.setOpaque(true);
//                etiquette2_.setBackground(GuiConstants.WHITE);
//                row_++;
//                panels_.put(new CoordsHands(0, indice_), etiquette2_);
//                indice_++;
//            }
//            int nb_ = tricksHands.getProgressingTrick().getNombreDeCartesParJoueur();
//            for(HandPresident h_ :tricksHands.getProgressingTrick()) {
//                if(indice2_<=numeroCarte_) {
//                    AbsPanel cards_ = window.getCompoFactory().newLineBox();
//                    for (GraphicCard<CardPresident> c: ContainerPresident.getGraphicCards(window,lg_,h_.getCards(), new PresidentCardConverter())) {
//                        cards_.add(c.getPaintableLabel());
//                        cardsLab_.add(c);
//                        AbsMetaLabelCard.paintCard(window.getImageFactory(),c);
//                    }
//                    if (h_.estVide()) {
//                        AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
//                        etiquette2_.setPreferredSize(Carpet.getDimensionForSeveralCards(nb_));
//                        etiquette2_.setOpaque(true);
//                        etiquette2_.setForeground(GuiConstants.WHITE);
//                        etiquette2_.setBackground(GuiConstants.WHITE);
//                        cards_.add(etiquette2_);
//                    }
//                    panels_.put(new CoordsHands(col_, row_), cards_);
//                    row_++;
//                    if (row_ % numberPlayers == 0) {
//                        row_ = 0;
//                        col_++;
//                    }
//                    indice2_++;
//                } else {
//                    break;
//                }
//            }
//            while(row_ <numberPlayers) {
//                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
//                etiquette2_.setOpaque(true);
//                etiquette2_.setForeground(GuiConstants.WHITE);
//                etiquette2_.setBackground(GuiConstants.WHITE);
//                panels_.put(new CoordsHands(col_, row_), etiquette2_);
//                row_++;
//            }
//            selectedTrick.removeAll();
//            int indexRem_ = cards.remove(selectedTrick);
//            AbsPanel gr_ = window.getCompoFactory().newGrid(0, col_ + 1);
//            for (CoordsHands c: panels_.getKeys()) {
//                gr_.add(panels_.getVal(c));
//            }
////            repaintCards(cardsLab_);
//            selectedTrick = gr_;
//            cards.add(gr_,indexRem_);
//            parent.pack();
//            return;
        }
//        Object o_ = trickNumber.getSelectedItem();
//        byte numeroPli_=Byte.parseByte(o_.toString());
        int numeroPli_=trickNumber.getSelectedIndex() - 2;
//        if (cardNumberTrick.getSelectedItem().isEmpty()) {
//            return;
//        }
//        CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
        numeroPli_ = tricksHands.getFilledTricksIndex(numeroPli_);
        int numeroCarte_=cardNumberTrick.getSelectedIndex();
        numeroCarte_--;
//        DealPresident dealt_ = tricksHands.getDistribution();
//        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingPresident, numberPlayers, numeroPli_,numeroCarte_);
        refreshHands();
//        hands.removeAll();
//        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
//            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
//            for (GraphicCard<CardPresident> c: ContainerPresident.getGraphicCards(window,lg_,dealt_.hand(joueur_).getCards(), new PresidentCardConverter())) {
//                sousPanneau4_.add(c.getPaintableLabel());
//            }
////            boolean entered_ = false;
////            for(CardPresident c: dealt_.main(joueur_))
////            {
////                GraphicPresidentCard carteGraphique_=new GraphicPresidentCard(c,SwingConstants.RIGHT,!entered_);
////                carteGraphique_.setPreferredSize(entered_);
////                sousPanneau4_.add(carteGraphique_);
////            }
//            hands.add(sousPanneau4_);
//        }
//        int nbBots_ = numberPlayers;
//        nbBots_ --;
//        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
//            hands.add(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
//        }
        selectedTrick.removeAll();
        currentTrickCard(numeroCarte_, trickPresident(numeroPli_));
//        if(tricks_.isValidIndex(numeroPli_)) {
//            currentTrickCard(numeroCarte_, tricks_.get(numeroPli_));
//        } else {
//            currentTrickCard(numeroCarte_, tricksHands.getProgressingTrick());
//        }
//        repaintCards(cardsLab_);
        parent.pack();

    }
    private TrickPresident trickPresident(int _nb) {
        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        if(tricks_.isValidIndex(_nb)) {
            return tricks_.get(_nb);
        } else {
            return tricksHands.getProgressingTrick();
        }
    }

    private void currentTrickCard(int _numeroCarte, TrickPresident _current) {
//        CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
        CoordsHandsMap panels_ = new CoordsHandsMap();
//        TranslationsLg lg_ = window.getFrames().currentLg();
        int nb_ = _current.getNombreDeCartesParJoueur();
        byte entameur_= _current.getEntameur();
        int row_ = row(panels_, nb_, entameur_);
        int col_ = 0;
        int indice2_ = 0;
        for(HandPresident h_ : _current) {
            if(indice2_<= _numeroCarte) {
                buildCards(panels_, nb_, h_, new CoordsHands(col_, row_));
                row_ = incrRow(row_);
                col_ = incrCol(row_,col_);
//                col_ = incrCol(_current,indice2_,row_,col_);
                indice2_++;
            } else {
                break;
            }
        }
        afterCards(panels_, nb_, row_, col_, selectedTrick);
//            selectedTrick = gr_;
//            cards.add(gr_,indexRem_);


//            int indexRem_ = cards.remove(selectedTrick);
//            AbsPanel gr_ = window.getCompoFactory().newGrid(0, col_ + 1);
//            for (CoordsHands c: panels_.getKeys()) {
//                gr_.add(panels_.getVal(c));
//            }
//            selectedTrick = gr_;
//            cards.add(gr_,indexRem_);
    }
//    private int incrRow(TrickPresident _current, int _i, int _row) {
//        if (!_current.getCards().isValidIndex(_i+1) && (_row + 1)% numberPlayers == 0) {
//            return _row;
//        }
//        return incrRow(_row);
//    }

    private int incrRow(int _row) {
        int row_ = _row;
        row_++;
        if (row_ % numberPlayers == 0) {
            row_ = 0;
        }
        return row_;
    }

//    private int incrCol(TrickPresident _current, int _i,int _row, int _col) {
//        if (!_current.getCards().isValidIndex(_i+1) && _row == 0) {
//            return _col;
//        }
//        return incrCol(_row, _col);
//    }

    private int incrCol(int _row, int _col) {
        if (_row == 0) {
            return _col + 1;
        }
        return _col;
    }

    private int row(CoordsHandsMap _panels, int _nb, byte _entameur) {
        int row_ = 0;
        int indice_=0;
        while(indice_< _entameur) {
            AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
            etiquette2_.setPreferredSize(Carpet.getDimensionForSeveralCards(_nb));
            etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
            etiquette2_.setOpaque(true);
            etiquette2_.setBackground(GuiConstants.WHITE);
            row_++;
            _panels.put(new CoordsHands(0, indice_), etiquette2_);
            indice_++;
        }
        return row_;
    }

    private void end(CoordsHandsMap _panels, int _nb, int _col, int _row) {
        if (_row <= 0) {
            return;
        }
        int row_ = _row;
        while(row_ <numberPlayers) {
            CoordsHands k_ = new CoordsHands(_col, row_);
            _panels.put(k_, blank(_nb));
            row_++;
        }
    }

    private AbsPlainLabel blank(int _nb) {
        AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
        etiquette2_.setPreferredSize(Carpet.getDimensionForSeveralCards(_nb));
        etiquette2_.setOpaque(true);
        etiquette2_.setForeground(GuiConstants.WHITE);
        etiquette2_.setBackground(GuiConstants.WHITE);
        return etiquette2_;
    }

    private void roll(CoordsHandsMap _panels, int _col, AbsPanel _dest) {
        int it_ = 0;
        int br_ = NumberUtil.max(_col + 1, 1);
        for (CoordsHands c: _panels.getKeys()) {
//                selectedTrick.add(panels_.getVal(c),);
            Carpet.add(window.getCompoFactory(), _dest, _panels.getVal(c),(it_ + 1) % br_ == 0);
//                gr_.add(panels_.getVal(c));
            it_++;
        }
    }
//    private void repaintCards(CustList<AbsMetaLabelCard> _list) {
//        AbsMetaLabelCard.repaintChildren(_list, window.getImageFactory());
////        for (GraphicPresidentCard g: _list) {
////            int w_ = g.getWidth();
////            int h_ = g.getHeight();
////            AbstractImage img_ = window.getImageFactory().newImageArgb(w_, h_);
////            img_.setFont(g);
////            g.paintComponent(img_);
////            g.setIcon(window.getImageFactory(),img_);
////        }
//    }

    public AbsPanel getContainer() {
        return container;
    }

    public IntTreeComboBox getTrickNumber() {
        return trickNumber;
    }

    public IntTreeComboBox getCardNumberTrick() {
        return cardNumberTrick;
    }

    public TricksHandsPresident getTricksHands() {
        return tricksHands;
    }
}
