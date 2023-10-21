package cards.gui.panels;


import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.containers.ContainerPresident;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.GraphicPresidentCard;
import cards.gui.panels.events.ListenerCards;
import cards.gui.panels.events.ListenerTricks;
import cards.president.*;
import code.gui.*;
import code.util.CustList;
import code.util.IntMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public class PanelTricksHandsPresident implements ViewablePanelTricksHands {

    private static final String ACCESS = "cards.gui.panels.paneltrickshandspresident";
    private static final String CURRENT_TRICK = "";
    private static final String EMPTY =CURRENT_TRICK;
    private static final String SPACE ="space";
    private static final String DEFAULT ="Default";
    private static final String TRICK ="trick";
    private static final String CARD ="card";
    private final StringMap<String> messages;
    private final AbsPanel cards;
    private AbsPanel selectedTrick;
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
        String lg_ = window.getLanguageKey();
        numberPlayers = _numberPlayers;
        displayingPresident = _displayingPresident;
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, lg_, ACCESS);
        parent = _parent;
        tricksHands = _tricksHands;
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers, (byte) -1);
        DealPresident dealt_ = tricksHands.getDistribution();
//        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        container = window.getCompoFactory().newBorder();
        cards=window.getCompoFactory().newLineBox();
        AbsPanel players_ = window.getCompoFactory().newGrid(0,1);
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_numberPlayers; joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
//        int nbBots_ = _numberPlayers - 1;
//        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
//            players_.add(getBlankCard(_pseudos, joueur_));
//        }
        cards.add(players_);
        selectedTrick = window.getCompoFactory().newGrid(0,1);
        cards.add(selectedTrick);
        hands=window.getCompoFactory().newGrid(0,1);
        AbsPanel sousPanneau3_;
        //boolean entered_ = false;
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_numberPlayers; joueur_++) {
            sousPanneau3_= window.getCompoFactory().newLineBox();
            for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(window,lg_,dealt_.hand(joueur_).getCards())) {
                sousPanneau3_.add(c.getPaintableLabel());
            }
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
        cards.add(hands);
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
        cards.add(sousPanneau2_);
        container.add(cards,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel selectionGameState_=window.getCompoFactory().newLineBox();
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages.getVal(TRICK)));
//        Integer[] numerosPlis_;
//        numerosPlis_=new Integer[tricks_.size()+2];
        int nbTricks_ = tricksHands.getFilledTricksCount();
//        int nbNumbers_ = nbTricks_ + 2;
        int nbNumbers_ = nbTricks_ + 1;
//        numerosPlis_=new Integer[nbNumbers_];
        IntMap<String> map_ = new IntMap<String>();
//        Ints list_ = new Ints();
        map_.put(-2, EMPTY);
        for(byte indicePli_ = IndexConstants.FIRST_INDEX; indicePli_<nbNumbers_; indicePli_++) {
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
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages.getVal(CARD)));
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
    }

    private AbsPlainLabel getBlankCard(StringList _nicknames, byte _player) {
        AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(_nicknames.get(_player));
        etiquette2_.setOpaque(true);
        etiquette2_.setBackground(GuiConstants.WHITE);
        etiquette2_.setPreferredSize(GraphicPresidentCard.getMaxDimension());
        return etiquette2_;
    }

    @Override
    public void changeTrick() {
        String lg_ = window.getLanguageKey();
//        Object o_ = trickNumber.getSelectedItem();
        CoordsHandsMap panels_ = new CoordsHandsMap();
//        if (StringList.eq(CURRENT_TRICK, String.valueOf(o_)))
        if (trickNumber.getSelectedItem().isEmpty()) {
            CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
            tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers);
            hands.removeAll();
            DealPresident dealt_ = tricksHands.getDistribution();
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
                AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(window,lg_,dealt_.hand(joueur_).getCards())) {
                    sousPanneau4_.add(c.getPaintableLabel());
                }
                hands.add(sousPanneau4_);
            }
            byte entameur_=tricksHands.getProgressingTrick().getEntameur();
            byte indice_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                row_++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            int nb_ = tricksHands.getProgressingTrick().getNombreDeCartesParJoueur();
            for(HandPresident h_:tricksHands.getProgressingTrick()) {
                AbsPanel cards_ = window.getCompoFactory().newLineBox();
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(window,lg_,h_.getCards())) {
                    cards_.add(c.getPaintableLabel());
                    cardsLab_.add(c);
                }
                if (h_.estVide()) {
                    AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
                    etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setForeground(GuiConstants.WHITE);
                    etiquette2_.setBackground(GuiConstants.WHITE);
                    cards_.add(etiquette2_);
                }
//                indice_++;
                panels_.put(new CoordsHands(col_, row_), cards_);
                row_++;
                if (row_ % numberPlayers == 0) {
                    row_ = 0;
                    col_++;
                }
            }
//            indice_=0;
            while(row_ + 1 <numberPlayers) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(GuiConstants.WHITE);
                etiquette2_.setBackground(GuiConstants.WHITE);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            selectedTrick.removeAll();
            int indexRem_ = cards.remove(selectedTrick);
            AbsPanel gr_ = window.getCompoFactory().newGrid(0, col_ + 1);
            for (CoordsHands c: panels_.getKeys()) {
                gr_.add(panels_.getVal(c));
            }
            repaintCards(cardsLab_);
            selectedTrick = gr_;
            cards.add(gr_,indexRem_);
            int nbCards_ = tricksHands.getProgressingTrick().total();
            int[] numerosJoueurs_=new int[nbCards_+1];
            for(byte indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<=nbCards_; indiceJoueur_++) {
                numerosJoueurs_[indiceJoueur_]= indiceJoueur_;
            }
//            cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
            cardNumberTrick.setItems(numerosJoueurs_);
            parent.pack();
            return;
        }
//        byte numeroPli_=Byte.parseByte(o_.toString());
        CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
        int numeroPli_= NumberUtil.parseInt(trickNumber.getSelectedItem());
        numeroPli_ = tricksHands.getFilledTricksIndex(numeroPli_);
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers, (byte) numeroPli_);
        hands.removeAll();
        DealPresident dealt_ = tricksHands.getDistribution();
        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
            for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(window,lg_,dealt_.hand(joueur_).getCards())) {
                sousPanneau4_.add(c.getPaintableLabel());
            }
//            boolean entered_ = false;
//            for(CardPresident c: dealt_.main(joueur_))
//            {
//                GraphicPresidentCard carteGraphique_=new GraphicPresidentCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau4_.add(carteGraphique_);
//                entered_ = true;
//            }
            hands.add(sousPanneau4_);
        }
//        int nbBots_ = numberPlayers;
//        nbBots_ --;
//        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
//            hands.add(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
//        }
        selectedTrick.removeAll();
        if(numeroPli_>=0) {
//            int sum_ = tricks_.get(numeroPli_-1).total() + tricks_.get(numeroPli_-1).getEntameur();
            int nb_ = tricks_.get(numeroPli_).getNombreDeCartesParJoueur();
            byte entameur_=tricks_.get(numeroPli_).getEntameur();
            byte indice_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                row_++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            for(HandPresident h_:tricks_.get(numeroPli_)) {
                AbsPanel cards_ = window.getCompoFactory().newLineBox();
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(window,lg_,h_.getCards())) {
                    cards_.add(c.getPaintableLabel());
                    cardsLab_.add(c);
                }
                if (h_.estVide()) {
                    AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
                    etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setForeground(GuiConstants.WHITE);
                    etiquette2_.setBackground(GuiConstants.WHITE);
                    cards_.add(etiquette2_);
                }
                panels_.put(new CoordsHands(col_, row_), cards_);
                row_++;
                if (row_ % numberPlayers == 0) {
                    row_ = 0;
                    col_++;
                }
            }
            while(row_ + 1 <numberPlayers) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(GuiConstants.WHITE);
                etiquette2_.setBackground(GuiConstants.WHITE);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            int indexRem_ = cards.remove(selectedTrick);
            AbsPanel gr_ = window.getCompoFactory().newGrid(0, col_ + 1);
            for (CoordsHands c: panels_.getKeys()) {
                gr_.add(panels_.getVal(c));
            }
            selectedTrick = gr_;
            cards.add(gr_,indexRem_);
            repaintCards(cardsLab_);
            int nbCards_ = tricks_.get(numeroPli_).total();
            int[] numerosJoueurs_=new int[nbCards_ + 1];
            for(byte indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<=nbCards_; indiceJoueur_++) {
                numerosJoueurs_[indiceJoueur_]= indiceJoueur_;
            }
//            cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
            cardNumberTrick.setItems(numerosJoueurs_);
        }
        parent.pack();
    }

    @Override
    public void changeCard() {
        String lg_ = window.getLanguageKey();
        CoordsHandsMap panels_ = new CoordsHandsMap();
//        if (StringList.eq(CURRENT_TRICK,String.valueOf(o_)))
        if (trickNumber.getSelectedItem().isEmpty()) {
            CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
            if (cardNumberTrick.getSelectedItem().isEmpty()) {
                return;
            }
            byte numeroCarte_=(byte) NumberUtil.parseInt(cardNumberTrick.getSelectedItem());
            numeroCarte_--;
            DealPresident dealt_ = tricksHands.getDistribution();
            tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingPresident, numberPlayers, numeroCarte_);
            hands.removeAll();
            for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
                AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(window, lg_,dealt_.hand(joueur_).getCards())) {
                    sousPanneau4_.add(c.getPaintableLabel());
                }
                hands.add(sousPanneau4_);
            }
            byte entameur_=tricksHands.getProgressingTrick().getEntameur();
            byte indice_=0;
            byte indice2_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                row_++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            int nb_ = tricksHands.getProgressingTrick().getNombreDeCartesParJoueur();
            for(HandPresident h_ :tricksHands.getProgressingTrick()) {
                if(indice2_<=numeroCarte_) {
                    AbsPanel cards_ = window.getCompoFactory().newLineBox();
                    for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(window,lg_,h_.getCards())) {
                        cards_.add(c.getPaintableLabel());
                        cardsLab_.add(c);
                    }
                    if (h_.estVide()) {
                        AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
                        etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                        etiquette2_.setOpaque(true);
                        etiquette2_.setForeground(GuiConstants.WHITE);
                        etiquette2_.setBackground(GuiConstants.WHITE);
                        cards_.add(etiquette2_);
                    }
                    panels_.put(new CoordsHands(col_, row_), cards_);
                    row_++;
                    if (row_ % numberPlayers == 0) {
                        row_ = 0;
                        col_++;
                    }
                    indice2_++;
                } else {
                    break;
                }
            }
            while(row_ + 1 <numberPlayers) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(GuiConstants.WHITE);
                etiquette2_.setBackground(GuiConstants.WHITE);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            selectedTrick.removeAll();
            int indexRem_ = cards.remove(selectedTrick);
            AbsPanel gr_ = window.getCompoFactory().newGrid(0, col_ + 1);
            for (CoordsHands c: panels_.getKeys()) {
                gr_.add(panels_.getVal(c));
            }
            repaintCards(cardsLab_);
            selectedTrick = gr_;
            cards.add(gr_,indexRem_);
            parent.pack();
            return;
        }
//        Object o_ = trickNumber.getSelectedItem();
//        byte numeroPli_=Byte.parseByte(o_.toString());
        int numeroPli_=NumberUtil.parseInt(trickNumber.getSelectedItem());
        if (cardNumberTrick.getSelectedItem().isEmpty()) {
            return;
        }
        CustList<AbsMetaLabelCard> cardsLab_ = new CustList<AbsMetaLabelCard>();
        numeroPli_ = tricksHands.getFilledTricksIndex(numeroPli_);
        byte numeroCarte_=(byte)NumberUtil.parseInt(cardNumberTrick.getSelectedItem());
        numeroCarte_--;
        DealPresident dealt_ = tricksHands.getDistribution();
        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingPresident, numberPlayers, (byte) numeroPli_,numeroCarte_);
        hands.removeAll();
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
            for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(window,lg_,dealt_.hand(joueur_).getCards())) {
                sousPanneau4_.add(c.getPaintableLabel());
            }
//            boolean entered_ = false;
//            for(CardPresident c: dealt_.main(joueur_))
//            {
//                GraphicPresidentCard carteGraphique_=new GraphicPresidentCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau4_.add(carteGraphique_);
//            }
            hands.add(sousPanneau4_);
        }
//        int nbBots_ = numberPlayers;
//        nbBots_ --;
//        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
//            hands.add(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
//        }
        selectedTrick.removeAll();
        if(numeroPli_>=0) {
            int nb_ = tricks_.get(numeroPli_).getNombreDeCartesParJoueur();
            byte entameur_=tricks_.get(numeroPli_).getEntameur();
            byte indice_=0;
            byte indice2_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                row_++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            for(HandPresident h_ :tricks_.get(numeroPli_)) {
                if(indice2_<=numeroCarte_) {
                    AbsPanel cards_ = window.getCompoFactory().newLineBox();
                    for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(window,lg_,h_.getCards())) {
                        cards_.add(c.getPaintableLabel());
                        cardsLab_.add(c);
                    }
                    if (h_.estVide()) {
                        AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
                        etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                        etiquette2_.setOpaque(true);
                        etiquette2_.setForeground(GuiConstants.WHITE);
                        etiquette2_.setBackground(GuiConstants.WHITE);
                        cards_.add(etiquette2_);
                    }
                    panels_.put(new CoordsHands(col_, row_), cards_);
                    row_++;
                    if (row_ % numberPlayers == 0) {
                        row_ = 0;
                        col_++;
                    }
                    indice2_++;
                } else {
                    break;
                }
            }
            while(row_ + 1 <numberPlayers) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(SPACE);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(GuiConstants.WHITE);
                etiquette2_.setBackground(GuiConstants.WHITE);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            int indexRem_ = cards.remove(selectedTrick);
            AbsPanel gr_ = window.getCompoFactory().newGrid(0, col_ + 1);
            for (CoordsHands c: panels_.getKeys()) {
                gr_.add(panels_.getVal(c));
            }
            selectedTrick = gr_;
            cards.add(gr_,indexRem_);
        }
        repaintCards(cardsLab_);
        parent.pack();

    }
    private void repaintCards(CustList<AbsMetaLabelCard> _list) {
        AbsMetaLabelCard.repaintChildren(_list, window.getImageFactory());
//        for (GraphicPresidentCard g: _list) {
//            int w_ = g.getWidth();
//            int h_ = g.getHeight();
//            AbstractImage img_ = window.getImageFactory().newImageArgb(w_, h_);
//            img_.setFont(g);
//            g.paintComponent(img_);
//            g.setIcon(window.getImageFactory(),img_);
//        }
    }

    public AbsPanel getContainer() {
        return container;
    }
}
