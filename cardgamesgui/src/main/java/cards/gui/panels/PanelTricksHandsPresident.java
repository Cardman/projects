package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
import code.gui.IntTreeComboBox;
import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.NatCmpTreeMap;
import code.util.NumberMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class PanelTricksHandsPresident extends JPanel implements ViewablePanelTricksHands {

    private static final String ACCESS = "cards.gui.panels.PanelTricksHandsPresident";
    private static final String CURRENT_TRICK = "";
    private static final String EMPTY =CURRENT_TRICK;
    private static final String SPACE ="space";
    private static final String DEFAULT ="Default";
    private static final String TRICK ="trick";
    private static final String CARD ="card";
    private StringMap<String> messages = new StringMap<String>();
    private JPanel cards;
    private JPanel selectedTrick;
    private JPanel hands;

    private IntTreeComboBox trickNumber;
    private IntTreeComboBox cardNumberTrick;
    private TricksHandsPresident tricksHands;
    private Window parent;
    private byte numberPlayers;
    private DisplayingPresident displayingPresident;

    public PanelTricksHandsPresident(Window _parent,
            TricksHandsPresident _tricksHands,
            byte _numberPlayers,
            StringList _pseudos,
            DisplayingPresident _displayingPresident) {
        numberPlayers = _numberPlayers;
        displayingPresident = _displayingPresident;
        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), ACCESS);
        parent = _parent;
        tricksHands = _tricksHands;
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers, (byte) -1);
        DealPresident dealt_ = tricksHands.getDistribution();
//        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        setLayout(new BorderLayout());
        cards=new JPanel();
        JPanel players_ = new JPanel(new GridLayout(0,1));
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<_numberPlayers;joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
//        int nbBots_ = _numberPlayers - 1;
//        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
//            players_.add(getBlankCard(_pseudos, joueur_));
//        }
        cards.add(players_);
        selectedTrick = new JPanel(new GridLayout(0,1));
        cards.add(selectedTrick);
        hands=new JPanel(new GridLayout(0,1));
        JPanel sousPanneau3_;
        //boolean entered_ = false;
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_numberPlayers;joueur_++) {
            sousPanneau3_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(dealt_.main(joueur_))) {
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
        JPanel sousPanneau2_=new JPanel(new GridLayout(0,1));
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
        add(cards,BorderLayout.CENTER);
        JPanel selectionGameState_=new JPanel();
        selectionGameState_.add(new JLabel(messages.getVal(TRICK)));
//        Integer[] numerosPlis_;
//        numerosPlis_=new Integer[tricks_.size()+2];
        int nbTricks_ = tricksHands.getFilledTricksCount();
//        int nbNumbers_ = nbTricks_ + 2;
        int nbNumbers_ = nbTricks_ + 1;
//        numerosPlis_=new Integer[nbNumbers_];
        NumberMap<Integer,String> map_ = new NumberMap<Integer,String>();
//        Numbers<Integer> list_ = new Numbers<Integer>();
        for(byte indicePli_=CustList.FIRST_INDEX;indicePli_<nbNumbers_;indicePli_++) {
//            numerosPlis_[indicePli_]=indicePli_-1;
//            list_.add(indicePli_-1);
            map_.put(indicePli_-1, String.valueOf(indicePli_-1));
        }
        map_.put(null, EMPTY);
        //Add this line to store at last the current trick
//        list_.add(null);
//        numerosPlis_[nbNumbers_ - 1] = CURRENT_TRICK;
//        trickNumber=new NumComboBox(numerosPlis_);
        trickNumber=new IntTreeComboBox();
        trickNumber.setWithDefaultValue(true);
        trickNumber.refresh(map_);
        trickNumber.addActionListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber);
        selectionGameState_.add(new JLabel(messages.getVal(CARD)));
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
        cardNumberTrick=new IntTreeComboBox();
//        Integer[] numerosJoueurs_=new Integer[_numberPlayers];
//        for(byte indiceJoueur_=CustList.FIRST_INDEX;indiceJoueur_<_numberPlayers;indiceJoueur_++) {
//            numerosJoueurs_[indiceJoueur_]=indiceJoueur_+1;
//        }
//        cardNumberTrick=new JComboBox<>(numerosJoueurs_);
//        cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
        cardNumberTrick.addActionListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick);
        add(selectionGameState_,BorderLayout.SOUTH);
    }

    private static JLabel getBlankCard(StringList _nicknames, byte _player) {
        JLabel etiquette2_=new JLabel(_nicknames.get(_player));
        etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
        etiquette2_.setOpaque(true);
        etiquette2_.setBackground(Color.WHITE);
        etiquette2_.setPreferredSize(GraphicPresidentCard.getMaxDimension());
        return etiquette2_;
    }

    @Override
    public void changeTrick() {
//        Object o_ = trickNumber.getSelectedItem();
        NatCmpTreeMap<CoordsHands, Component> panels_ = new NatCmpTreeMap<CoordsHands, Component>();
//        if (StringList.eq(CURRENT_TRICK, String.valueOf(o_)))
        if (trickNumber.isSelectNullCurrent()) {
            tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers);
            hands.removeAll();
            hands.setLayout(new GridLayout(0,1));
            DealPresident dealt_ = tricksHands.getDistribution();
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
                JPanel sousPanneau4_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(dealt_.main(joueur_))) {
                    sousPanneau4_.add(c);
                }
                hands.add(sousPanneau4_);
            }
            selectedTrick.removeAll();
//            selectedTrick.setLayout(new GridLayout(numberPlayers, 0));
            byte entameur_=tricksHands.getProgressingTrick().getEntameur();
            byte indice_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                JLabel etiquette2_=new JLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
                row_ ++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            int nb_ = tricksHands.getProgressingTrick().getNombreDeCartesParJoueur();
            for(HandPresident h_:tricksHands.getProgressingTrick()) {
                JPanel cards_ = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(h_)) {
                    cards_.add(c);
                }
                if (h_.estVide()) {
                    JLabel etiquette2_=new JLabel(SPACE);
                    etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setForeground(Color.WHITE);
                    etiquette2_.setBackground(Color.WHITE);
                    cards_.add(etiquette2_);
                }
//                indice_++;
                panels_.put(new CoordsHands(col_, row_), cards_);
                row_ ++;
                if (row_ % numberPlayers == 0) {
                    row_ = 0;
                    col_ ++;
                }
//                selectedTrick.add(cards_);
//                indice_++;
            }
//            indice_=0;
            while(row_ + 1 <numberPlayers) {
                JLabel etiquette2_=new JLabel(SPACE);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(Color.WHITE);
                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            selectedTrick.setLayout(new GridLayout(0, col_ + 1));
            for (CoordsHands c: panels_.getKeys()) {
                selectedTrick.add(panels_.getVal(c));
            }
            selectedTrick.repaint();
            int nbCards_ = tricksHands.getProgressingTrick().total();
            Integer[] numerosJoueurs_=new Integer[nbCards_+1];
            for(byte indiceJoueur_=CustList.FIRST_INDEX;indiceJoueur_<=nbCards_;indiceJoueur_++) {
                numerosJoueurs_[indiceJoueur_]=(int) indiceJoueur_;
            }
//            cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
            cardNumberTrick.setItems(numerosJoueurs_);
            parent.pack();
            return;
        }
//        byte numeroPli_=Byte.parseByte(o_.toString());
        byte numeroPli_= trickNumber.getCurrent().byteValue();
        numeroPli_ = (byte) tricksHands.getFilledTricksIndex(numeroPli_);
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingPresident, numberPlayers, numeroPli_);
        hands.removeAll();
        hands.setLayout(new GridLayout(0,1));
        DealPresident dealt_ = tricksHands.getDistribution();
        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
            JPanel sousPanneau4_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(dealt_.main(joueur_))) {
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
//            selectedTrick.setLayout(new GridLayout(numberPlayers, 0));
            byte entameur_=tricks_.get(numeroPli_).getEntameur();
            byte indice_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                JLabel etiquette2_=new JLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
                row_ ++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            for(HandPresident h_:tricks_.get(numeroPli_)) {
                JPanel cards_ = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(h_)) {
                    cards_.add(c);
                }
                if (h_.estVide()) {
                    JLabel etiquette2_=new JLabel(SPACE);
                    etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setForeground(Color.WHITE);
                    etiquette2_.setBackground(Color.WHITE);
                    cards_.add(etiquette2_);
                }
                panels_.put(new CoordsHands(col_, row_), cards_);
                row_ ++;
                if (row_ % numberPlayers == 0) {
                    row_ = 0;
                    col_ ++;
                }
//                selectedTrick.add(cards_);
//                indice_++;
            }
            while(row_ + 1 <numberPlayers) {
                JLabel etiquette2_=new JLabel(SPACE);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(Color.WHITE);
                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            selectedTrick.setLayout(new GridLayout(0, col_ + 1));
            for (CoordsHands c: panels_.getKeys()) {
                selectedTrick.add(panels_.getVal(c));
            }
//            while(indice_<2*numberPlayers-1) {
//                JLabel etiquette2_=new JLabel(Long.toString(indice_-numberPlayers));
//                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
//                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
//                etiquette2_.setOpaque(true);
//                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
//                indice_++;
//            }
            selectedTrick.repaint();
            int nbCards_ = tricks_.get(numeroPli_).total();
            Integer[] numerosJoueurs_=new Integer[nbCards_ + 1];
            for(byte indiceJoueur_=CustList.FIRST_INDEX;indiceJoueur_<=nbCards_;indiceJoueur_++) {
                numerosJoueurs_[indiceJoueur_]=(int) indiceJoueur_;
            }
//            cardNumberTrick.setModel(new DefaultComboBoxModel<Integer>(numerosJoueurs_));
            cardNumberTrick.setItems(numerosJoueurs_);
        }
        parent.pack();
    }

    @Override
    public void changeCard() {
        NatCmpTreeMap<CoordsHands, Component> panels_ = new NatCmpTreeMap<CoordsHands, Component>();
//        if (StringList.eq(CURRENT_TRICK,String.valueOf(o_)))
        if (trickNumber.isSelectNullCurrent()) {
            Integer selected_ = cardNumberTrick.getCurrent();
            if (selected_ == null) {
                return;
            }
            byte numeroCarte_=Byte.parseByte(selected_.toString());
            numeroCarte_--;
            DealPresident dealt_ = tricksHands.getDistribution();
            tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingPresident, numberPlayers, numeroCarte_);
            hands.removeAll();
            hands.setLayout(new GridLayout(0,1));
            for(byte joueur_=CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
                JPanel sousPanneau4_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
                for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(dealt_.main(joueur_))) {
                    sousPanneau4_.add(c);
                }
                hands.add(sousPanneau4_);
            }
            selectedTrick.removeAll();
//            selectedTrick.setLayout(new GridLayout(numberPlayers, 0));
            byte entameur_=tricksHands.getProgressingTrick().getEntameur();
            byte indice_=0;
            byte indice2_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                JLabel etiquette2_=new JLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
                row_ ++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            int nb_ = tricksHands.getProgressingTrick().getNombreDeCartesParJoueur();
            for(HandPresident h_ :tricksHands.getProgressingTrick()) {
                if(indice2_<=numeroCarte_) {
                    JPanel cards_ = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
                    for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(h_)) {
                        cards_.add(c);
                    }
                    if (h_.estVide()) {
                        JLabel etiquette2_=new JLabel(SPACE);
                        etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                        etiquette2_.setOpaque(true);
                        etiquette2_.setForeground(Color.WHITE);
                        etiquette2_.setBackground(Color.WHITE);
                        cards_.add(etiquette2_);
                    }
                    panels_.put(new CoordsHands(col_, row_), cards_);
                    row_ ++;
                    if (row_ % numberPlayers == 0) {
                        row_ = 0;
                        col_ ++;
                    }
//                    selectedTrick.add(cards_);
                    indice2_++;
                } else {
                    break;
                }
            }
            while(row_ + 1 <numberPlayers) {
                JLabel etiquette2_=new JLabel(SPACE);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(Color.WHITE);
                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            selectedTrick.setLayout(new GridLayout(0, col_ + 1));
            for (CoordsHands c: panels_.getKeys()) {
                selectedTrick.add(panels_.getVal(c));
            }
            selectedTrick.repaint();
            parent.pack();
            return;
        }
//        Object o_ = trickNumber.getSelectedItem();
//        byte numeroPli_=Byte.parseByte(o_.toString());
        byte numeroPli_=trickNumber.getCurrent().byteValue();
        Integer selected_ = cardNumberTrick.getCurrent();
        if (selected_ == null) {
            return;
        }
        numeroPli_ = (byte) tricksHands.getFilledTricksIndex(numeroPli_);
        byte numeroCarte_=Byte.parseByte(selected_.toString());
        numeroCarte_--;
        DealPresident dealt_ = tricksHands.getDistribution();
        CustList<TrickPresident> tricks_ = tricksHands.getTricks();
        tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingPresident, numberPlayers,numeroPli_,numeroCarte_);
        hands.removeAll();
        hands.setLayout(new GridLayout(0,1));
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
            JPanel sousPanneau4_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(dealt_.main(joueur_))) {
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
//            selectedTrick.setLayout(new GridLayout(0,1));
            int nb_ = tricks_.get(numeroPli_).getNombreDeCartesParJoueur();
//            selectedTrick.setLayout(new GridLayout(numberPlayers, 0));
            byte entameur_=tricks_.get(numeroPli_).getEntameur();
            byte indice_=0;
            byte indice2_=0;
            byte col_ = 0;
            byte row_ = 0;
            while(indice_<entameur_) {
                JLabel etiquette2_=new JLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
//                indice_++;
                row_ ++;
                panels_.put(new CoordsHands(0, indice_), etiquette2_);
                indice_++;
            }
            for(HandPresident h_ :tricks_.get(numeroPli_)) {
                if(indice2_<=numeroCarte_) {
                    JPanel cards_ = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
                    for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(h_)) {
                        cards_.add(c);
                    }
                    if (h_.estVide()) {
                        JLabel etiquette2_=new JLabel(SPACE);
                        etiquette2_.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(nb_));
                        etiquette2_.setOpaque(true);
                        etiquette2_.setForeground(Color.WHITE);
                        etiquette2_.setBackground(Color.WHITE);
                        cards_.add(etiquette2_);
                    }
//                    selectedTrick.add(cards_);
//                    indice_++;
                    panels_.put(new CoordsHands(col_, row_), cards_);
                    row_ ++;
                    if (row_ % numberPlayers == 0) {
                        row_ = 0;
                        col_ ++;
                    }
                    indice2_++;
                } else {
                    break;
                }
            }
            while(row_ + 1 <numberPlayers) {
                JLabel etiquette2_=new JLabel(SPACE);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setOpaque(true);
                etiquette2_.setForeground(Color.WHITE);
                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
                panels_.put(new CoordsHands(col_, row_), etiquette2_);
                row_++;
            }
            selectedTrick.setLayout(new GridLayout(0, col_ + 1));
            for (CoordsHands c: panels_.getKeys()) {
                selectedTrick.add(panels_.getVal(c));
            }
//            while(indice_<2*numberPlayers-1) {
//                JLabel etiquette2_=new JLabel(Long.toString(indice_-numberPlayers));
//                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
//                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
//                etiquette2_.setOpaque(true);
//                etiquette2_.setBackground(Color.WHITE);
//                selectedTrick.add(etiquette2_);
//                indice_++;
//            }
        }
        selectedTrick.repaint();
        parent.pack();

    }
}
