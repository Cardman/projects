package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import cards.gui.MainWindow;
import cards.gui.containers.ContainerPresident;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicPresidentCard;
import cards.gui.panels.events.ListenerCards;
import cards.gui.panels.events.ListenerTricks;
import cards.president.DealPresident;
import cards.president.DisplayingPresident;
import cards.president.HandPresident;
import cards.president.TrickPresident;
import cards.president.TricksHandsPresident;
import code.gui.*;
import code.sml.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.NatCmpTreeMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public class PanelTricksHandsPresident implements ViewablePanelTricksHands {

    private static final String ACCESS = "cards.gui.panels.paneltrickshandspresident";
    private static final String CURRENT_TRICK = "";
    private static final String EMPTY =CURRENT_TRICK;
    private static final String SPACE ="space";
    private static final String DEFAULT ="Default";
    private static final String TRICK ="trick";
    private static final String CARD ="card";
    private final StringMap<String> messages;
    private final Panel cards;
    private Panel selectedTrick;
    private final Panel hands;

    private final IntTreeComboBox trickNumber;
    private final IntTreeComboBox cardNumberTrick;
    private final TricksHandsPresident tricksHands;
    private final ChangeableTitle parent;
    private final byte numberPlayers;
    private final DisplayingPresident displayingPresident;
    private final MainWindow window;
    private final Panel container;

    public PanelTricksHandsPresident(ChangeableTitle _parent,
            TricksHandsPresident _tricksHands,
            byte _numberPlayers,
            StringList _pseudos,
            DisplayingPresident _displayingPresident, MainWindow _window) {
        window = _window;
        String lg_ = window.getLanguageKey();
        numberPlayers = _numberPlayers;
        displayingPresident = _displayingPresident;
        messages = MainWindow.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, lg_, ACCESS);
        parent = _parent;
        tricksHands = _tricksHands;
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers, (byte) -1);
        DealPresident dealt_ = tricksHands.getDistribution();
//        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        container = Panel.newBorder();
        cards=Panel.newLineBox();
        Panel players_ = Panel.newGrid(0,1);
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_numberPlayers; joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
//        int nbBots_ = _numberPlayers - 1;
//        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
//            players_.add(getBlankCard(_pseudos, joueur_));
//        }
        cards.add(players_);
        selectedTrick = Panel.newGrid(0,1);
        cards.add(selectedTrick);
        hands=Panel.newGrid(0,1);
        Panel sousPanneau3_;
        //boolean entered_ = false;
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_numberPlayers; joueur_++) {
            sousPanneau3_= Panel.newLineBox();
            for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,dealt_.hand(joueur_))) {
                sousPanneau3_.add(c);
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
        Panel sousPanneau2_=Panel.newGrid(0,1);
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
        container.add(cards,BorderLayout.CENTER);
        Panel selectionGameState_=Panel.newLineBox();
        selectionGameState_.add(new TextLabel(messages.getVal(TRICK)));
//        Integer[] numerosPlis_;
//        numerosPlis_=new Integer[tricks_.size()+2];
        int nbTricks_ = tricksHands.getFilledTricksCount();
//        int nbNumbers_ = nbTricks_ + 2;
        int nbNumbers_ = nbTricks_ + 1;
//        numerosPlis_=new Integer[nbNumbers_];
        IntMap<String> map_ = new IntMap<String>();
//        Ints list_ = new Ints();
        for(byte indicePli_ = IndexConstants.FIRST_INDEX; indicePli_<nbNumbers_; indicePli_++) {
//            numerosPlis_[indicePli_]=indicePli_-1;
//            list_.add(indicePli_-1);
            map_.put(indicePli_-1, Long.toString(indicePli_-1L));
        }
        map_.put(null, EMPTY);
        //Add this line to store at last the current trick
//        list_.add(null);
//        numerosPlis_[nbNumbers_ - 1] = CURRENT_TRICK;
//        trickNumber=new NumComboBox(numerosPlis_);
        trickNumber=new IntTreeComboBox(window.getFrames().getGeneComboBox().createCombo(new StringList(),-1));
        trickNumber.setWithDefaultValue(true);
        trickNumber.refresh(map_);
        trickNumber.setListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber.self());
        selectionGameState_.add(new TextLabel(messages.getVal(CARD)));
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
        cardNumberTrick=new IntTreeComboBox(window.getFrames().getGeneComboBox().createCombo(new StringList(),-1));
//        Integer[] numerosJoueurs_=new Integer[_numberPlayers];
//        for(byte indiceJoueur_=CustList.FIRST_INDEX;indiceJoueur_<_numberPlayers;indiceJoueur_++) {
//            numerosJoueurs_[indiceJoueur_]=indiceJoueur_+1;
//        }
//        cardNumberTrick=new JComboBox<>(numerosJoueurs_);
//        cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
        cardNumberTrick.setListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick.self());
        container.add(selectionGameState_,BorderLayout.SOUTH);
    }

    private static TextLabel getBlankCard(StringList _nicknames, byte _player) {
        TextLabel etiquette2_=new TextLabel(_nicknames.get(_player));
        etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
        etiquette2_.setOpaque(true);
        etiquette2_.setBackground(Color.WHITE);
        etiquette2_.setPreferredSize(GraphicPresidentCard.getMaxDimension());
        return etiquette2_;
    }

    @Override
    public void changeTrick() {
        String lg_ = window.getLanguageKey();
//        Object o_ = trickNumber.getSelectedItem();
        NatCmpTreeMap<CoordsHands, CustComponent> panels_ = new NatCmpTreeMap<CoordsHands, CustComponent>();
//        if (StringList.eq(CURRENT_TRICK, String.valueOf(o_)))
        if (trickNumber.isSelectNullCurrent()) {
            tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers);
            hands.removeAll();
            DealPresident dealt_ = tricksHands.getDistribution();
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
                Panel sousPanneau4_= Panel.newLineBox();
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,dealt_.hand(joueur_))) {
                    sousPanneau4_.add(c);
                }
                hands.add(sousPanneau4_);
            }
            byte entameur_=tricksHands.getProgressingTrick().getEntameur();
            byte indice_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                row_++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            int nb_ = tricksHands.getProgressingTrick().getNombreDeCartesParJoueur();
            for(HandPresident h_:tricksHands.getProgressingTrick()) {
                Panel cards_ = Panel.newLineBox();
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,h_)) {
                    cards_.add(c);
                }
                if (h_.estVide()) {
                    TextLabel etiquette2_=new TextLabel(SPACE);
                    etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setForeground(Color.WHITE);
                    etiquette2_.setBackground(Color.WHITE);
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
                TextLabel etiquette2_=new TextLabel(SPACE);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(Color.WHITE);
                etiquette2_.setBackground(Color.WHITE);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            selectedTrick.removeAll();
            int indexRem_ = cards.remove(selectedTrick);
            Panel gr_ = Panel.newGrid(0, col_ + 1);
            for (CoordsHands c: panels_.getKeys()) {
                gr_.add(panels_.getVal(c));
            }
            gr_.repaintSecondChildren();
            selectedTrick = gr_;
            cards.add(gr_,indexRem_);
            int nbCards_ = tricksHands.getProgressingTrick().total();
            Integer[] numerosJoueurs_=new Integer[nbCards_+1];
            for(byte indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<=nbCards_; indiceJoueur_++) {
                numerosJoueurs_[indiceJoueur_]=(int) indiceJoueur_;
            }
//            cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
            cardNumberTrick.setItems(numerosJoueurs_);
            parent.pack();
            return;
        }
//        byte numeroPli_=Byte.parseByte(o_.toString());
        int numeroPli_= trickNumber.getCurrent();
        numeroPli_ = tricksHands.getFilledTricksIndex(numeroPli_);
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers, (byte) numeroPli_);
        hands.removeAll();
        DealPresident dealt_ = tricksHands.getDistribution();
        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
            Panel sousPanneau4_= Panel.newLineBox();
            for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,dealt_.hand(joueur_))) {
                sousPanneau4_.add(c);
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
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                row_++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            for(HandPresident h_:tricks_.get(numeroPli_)) {
                Panel cards_ = Panel.newLineBox();
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,h_)) {
                    cards_.add(c);
                }
                if (h_.estVide()) {
                    TextLabel etiquette2_=new TextLabel(SPACE);
                    etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setForeground(Color.WHITE);
                    etiquette2_.setBackground(Color.WHITE);
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
                TextLabel etiquette2_=new TextLabel(SPACE);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(Color.WHITE);
                etiquette2_.setBackground(Color.WHITE);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            int indexRem_ = cards.remove(selectedTrick);
            Panel gr_ = Panel.newGrid(0, col_ + 1);
            for (CoordsHands c: panels_.getKeys()) {
                gr_.add(panels_.getVal(c));
            }
            selectedTrick = gr_;
            cards.add(gr_,indexRem_);
            gr_.repaintSecondChildren();
            int nbCards_ = tricks_.get(numeroPli_).total();
            Integer[] numerosJoueurs_=new Integer[nbCards_ + 1];
            for(byte indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<=nbCards_; indiceJoueur_++) {
                numerosJoueurs_[indiceJoueur_]=(int) indiceJoueur_;
            }
//            cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
            cardNumberTrick.setItems(numerosJoueurs_);
        }
        parent.pack();
    }

    @Override
    public void changeCard() {
        String lg_ = window.getLanguageKey();
        NatCmpTreeMap<CoordsHands, CustComponent> panels_ = new NatCmpTreeMap<CoordsHands, CustComponent>();
//        if (StringList.eq(CURRENT_TRICK,String.valueOf(o_)))
        if (trickNumber.isSelectNullCurrent()) {
            Integer selected_ = cardNumberTrick.getCurrent();
            if (selected_ == null) {
                return;
            }
            byte numeroCarte_=(byte)(int)selected_;
            numeroCarte_--;
            DealPresident dealt_ = tricksHands.getDistribution();
            tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingPresident, numberPlayers, numeroCarte_);
            hands.removeAll();
            for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
                Panel sousPanneau4_= Panel.newLineBox();
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,dealt_.hand(joueur_))) {
                    sousPanneau4_.add(c);
                }
                hands.add(sousPanneau4_);
            }
            byte entameur_=tricksHands.getProgressingTrick().getEntameur();
            byte indice_=0;
            byte indice2_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                row_++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            int nb_ = tricksHands.getProgressingTrick().getNombreDeCartesParJoueur();
            for(HandPresident h_ :tricksHands.getProgressingTrick()) {
                if(indice2_<=numeroCarte_) {
                    Panel cards_ = Panel.newLineBox();
                    for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,h_)) {
                        cards_.add(c);
                    }
                    if (h_.estVide()) {
                        TextLabel etiquette2_=new TextLabel(SPACE);
                        etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                        etiquette2_.setOpaque(true);
                        etiquette2_.setForeground(Color.WHITE);
                        etiquette2_.setBackground(Color.WHITE);
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
                TextLabel etiquette2_=new TextLabel(SPACE);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(Color.WHITE);
                etiquette2_.setBackground(Color.WHITE);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            selectedTrick.removeAll();
            int indexRem_ = cards.remove(selectedTrick);
            Panel gr_ = Panel.newGrid(0, col_ + 1);
            for (CoordsHands c: panels_.getKeys()) {
                gr_.add(panels_.getVal(c));
            }
            gr_.repaintSecondChildren();
            selectedTrick = gr_;
            cards.add(gr_,indexRem_);
            parent.pack();
            return;
        }
//        Object o_ = trickNumber.getSelectedItem();
//        byte numeroPli_=Byte.parseByte(o_.toString());
        int numeroPli_=trickNumber.getCurrent();
        Integer selected_ = cardNumberTrick.getCurrent();
        if (selected_ == null) {
            return;
        }
        numeroPli_ = tricksHands.getFilledTricksIndex(numeroPli_);
        byte numeroCarte_=(byte)(int)selected_;
        numeroCarte_--;
        DealPresident dealt_ = tricksHands.getDistribution();
        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingPresident, numberPlayers, (byte) numeroPli_,numeroCarte_);
        hands.removeAll();
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
            Panel sousPanneau4_= Panel.newLineBox();
            for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,dealt_.hand(joueur_))) {
                sousPanneau4_.add(c);
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
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                row_++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            for(HandPresident h_ :tricks_.get(numeroPli_)) {
                if(indice2_<=numeroCarte_) {
                    Panel cards_ = Panel.newLineBox();
                    for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,h_)) {
                        cards_.add(c);
                    }
                    if (h_.estVide()) {
                        TextLabel etiquette2_=new TextLabel(SPACE);
                        etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                        etiquette2_.setOpaque(true);
                        etiquette2_.setForeground(Color.WHITE);
                        etiquette2_.setBackground(Color.WHITE);
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
                TextLabel etiquette2_=new TextLabel(SPACE);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(Color.WHITE);
                etiquette2_.setBackground(Color.WHITE);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            int indexRem_ = cards.remove(selectedTrick);
            Panel gr_ = Panel.newGrid(0, col_ + 1);
            for (CoordsHands c: panels_.getKeys()) {
                gr_.add(panels_.getVal(c));
            }
            selectedTrick = gr_;
            cards.add(gr_,indexRem_);
        }
        selectedTrick.repaintSecondChildren();
        parent.pack();

    }

    public Panel getContainer() {
        return container;
    }
}
