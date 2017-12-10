package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cards.belote.DealBelote;
import cards.belote.DisplayingBelote;
import cards.belote.TrickBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.containers.ContainerBelote;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicBeloteCard;
import cards.gui.panels.events.ListenerCards;
import cards.gui.panels.events.ListenerTricks;
import code.gui.NumComboBox;
import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class PanelTricksHandsBelote extends JPanel implements ViewablePanelTricksHands {

    private static final String ACCESS = "cards.gui.panels.PanelTricksHandsBelote";
    private static final String EMPTY ="";
    private static final String DEFAULT ="Default";
    private static final String TRICK ="trick";
    private static final String CARD ="card";
    private StringMap<String> messages = new StringMap<String>();
    private JPanel cards;
    private JPanel tricks;
    private JPanel selectedTrick;
    private JPanel hands;
    private NumComboBox trickNumber;
    private NumComboBox cardNumberTrick;
    private TricksHandsBelote tricksHands;
    private Window parent;
    private byte numberPlayers;
    private DisplayingBelote displayingBelote;

    public PanelTricksHandsBelote(Window _parent,
            TricksHandsBelote _tricksHands,
            byte _numberPlayers,
            StringList _pseudos,
            DisplayingBelote _displayingBelote) {
        numberPlayers = _numberPlayers;
        displayingBelote = _displayingBelote;
        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(),ACCESS);
        parent = _parent;
        tricksHands = _tricksHands;
        DealBelote dealt_ = tricksHands.getDistribution();
        CustList<TrickBelote> tricks_ = tricksHands.getTricks();
        setLayout(new BorderLayout());
        cards=new JPanel();
        JPanel players_ = new JPanel(new GridLayout(0,1));
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<_numberPlayers;joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
        int nbBots_ = _numberPlayers - 1;
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
        cards.add(players_);
        tricks = new JPanel(new GridLayout(0,1));
        cards.add(tricks);
        selectedTrick = new JPanel(new GridLayout(0,1));
        cards.add(selectedTrick);
        hands=new JPanel(new GridLayout(0,1));
        JPanel sousPanneau3_;
        //boolean entered_ = false;
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_numberPlayers;joueur_++) {
            sousPanneau3_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(dealt_.main(joueur_))) {
                sousPanneau3_.add(c);
            }
//            entered_ = false;
//            for(CardBelote c: dealt_.main(joueur_))
//            {
//                GraphicBeloteCard carteGraphique_=new GraphicBeloteCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau3_.add(carteGraphique_);
//                entered_ = true;
//            }
            hands.add(sousPanneau3_);
        }
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            hands.add(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
        }
        cards.add(hands);
        JPanel sousPanneau2_=new JPanel(new GridLayout(0,1));
        sousPanneau3_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(dealt_.derniereMain())) {
            sousPanneau3_.add(c);
        }
//        entered_ = false;
//        for(CardBelote c: dealt_.derniereMain())
//        {
//            GraphicBeloteCard carteGraphique_=new GraphicBeloteCard(c,SwingConstants.RIGHT,!entered_);
//            carteGraphique_.setPreferredSize(entered_);
//            sousPanneau3_.add(carteGraphique_);
//        }
        sousPanneau2_.add(sousPanneau3_);
        cards.add(sousPanneau2_);
        add(cards,BorderLayout.CENTER);
        JPanel selectionGameState_=new JPanel();
        selectionGameState_.add(new JLabel(messages.getVal(TRICK)));
        Integer[] numerosPlis_;
        numerosPlis_=new Integer[tricks_.size()+2];
        int nbTricksNumbers_ = numerosPlis_.length;
        for(byte indicePli_=CustList.FIRST_INDEX;indicePli_<nbTricksNumbers_;indicePli_++) {
            numerosPlis_[indicePli_]=indicePli_-1;
        }
        trickNumber=new NumComboBox(numerosPlis_);
        trickNumber.addActionListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber);
        selectionGameState_.add(new JLabel(messages.getVal(CARD)));
        Integer[] numerosJoueurs_=new Integer[_numberPlayers];
        for(byte indiceJoueur_=CustList.FIRST_INDEX;indiceJoueur_<_numberPlayers;indiceJoueur_++) {
            numerosJoueurs_[indiceJoueur_]=indiceJoueur_+1;
        }
        cardNumberTrick=new NumComboBox(numerosJoueurs_);
        cardNumberTrick.addActionListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick);
        add(selectionGameState_,BorderLayout.SOUTH);
    }

    private static JLabel getBlankCard(StringList _nicknames, byte _player) {
        JLabel etiquette2_=new JLabel(_nicknames.get(_player));
        etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
        etiquette2_.setOpaque(true);
        etiquette2_.setBackground(Color.WHITE);
        etiquette2_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
        return etiquette2_;
    }

    @Override
    public void changeTrick() {

        byte numeroPli_=Byte.parseByte(trickNumber.getCurrent().toString());
        tricksHands.restituerMains(displayingBelote, numberPlayers, numeroPli_);
        hands.removeAll();
        hands.setLayout(new GridLayout(0,1));
        DealBelote dealt_ = tricksHands.getDistribution();
        CustList<TrickBelote> tricks_ = tricksHands.getTricks();
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
            JPanel sousPanneau4_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(dealt_.main(joueur_))) {
                sousPanneau4_.add(c);
            }
//            boolean entered_ = false;
//            for(CardBelote c: dealt_.main(joueur_))
//            {
//                GraphicBeloteCard carteGraphique_=new GraphicBeloteCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau4_.add(carteGraphique_);
//                entered_ = true;
//            }
            hands.add(sousPanneau4_);
        }
        int nbBots_ = numberPlayers;
        nbBots_ --;
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            hands.add(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
        }
        selectedTrick.removeAll();
        if(numeroPli_>0) {
            selectedTrick.setLayout(new GridLayout(0,1));
            byte entameur_=tricks_.get(numeroPli_-1).getEntameur();
            byte indice_=0;
            while(indice_<entameur_) {
                JLabel etiquette2_=new JLabel(EMPTY+indice_);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
            for(CardBelote carte_:tricks_.get(numeroPli_-1)) {
                GraphicBeloteCard carteGraphique2_=new GraphicBeloteCard(carte_,SwingConstants.RIGHT,true);
                carteGraphique2_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                selectedTrick.add(carteGraphique2_);
                indice_++;
            }
            while(indice_<2*numberPlayers-1) {
                JLabel etiquette2_=new JLabel(EMPTY+(indice_-numberPlayers));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
        }
        tricks.removeAll();
        if(numeroPli_>1) {
            tricks.setLayout(new GridLayout(0,numeroPli_ - 1));
        }
        for(byte indicePli_=1;indicePli_<numeroPli_ ;indicePli_++) {
            byte entameur_=tricks_.get(indicePli_-1).getEntameur();
            byte indice_=0;
            for(indice_=0;indice_<entameur_;indice_++) {
                JLabel etiquette2_=new JLabel(EMPTY+indice_);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                tricks.add(etiquette2_,indicePli_*(indice_+1)-1);
            }
            for(CardBelote carte_:tricks_.get(indicePli_ - 1)) {
                GraphicBeloteCard carteGraphique2_=new GraphicBeloteCard(carte_,SwingConstants.RIGHT,true);
                carteGraphique2_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                tricks.add(carteGraphique2_,indicePli_*(indice_+1)-1);
                indice_++;
            }
            while(indice_<numberPlayers*2-1) {
                JLabel etiquette2_=new JLabel(EMPTY+(indice_-numberPlayers));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                tricks.add(etiquette2_,indicePli_*(indice_+1)-1);
                indice_++;
            }
        }
        parent.pack();
    }

    @Override
    public void changeCard() {

        byte numeroPli_=Byte.parseByte(trickNumber.getCurrent().toString());
        if(numeroPli_<1) {
            return;
        }
        byte numeroCarte_=Byte.parseByte(cardNumberTrick.getCurrent().toString());
        numeroCarte_--;
        DealBelote dealt_ = tricksHands.getDistribution();
        CustList<TrickBelote> tricks_ = tricksHands.getTricks();
        tricksHands.restituerMains(displayingBelote, numberPlayers,numeroPli_,numeroCarte_);
        hands.removeAll();
        hands.setLayout(new GridLayout(0,1));
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
            JPanel sousPanneau4_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(dealt_.main(joueur_))) {
                sousPanneau4_.add(c);
            }
//            boolean entered_ = false;
//            for(CardBelote c: dealt_.main(joueur_))
//            {
//                GraphicBeloteCard carteGraphique_=new GraphicBeloteCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau4_.add(carteGraphique_);
//            }
            hands.add(sousPanneau4_);
        }
        int nbBots_ = numberPlayers;
        nbBots_ --;
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            hands.add(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
        }
        selectedTrick.removeAll();
        if(numeroPli_>0) {
            selectedTrick.setLayout(new GridLayout(0,1));
            byte entameur_=tricks_.get(numeroPli_-1).getEntameur();
            byte indice_=0;
            byte indice2_=0;
            while(indice_<entameur_) {
                JLabel etiquette2_=new JLabel(EMPTY+indice_);
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
            for(CardBelote carte_:tricks_.get(numeroPli_-1)) {
                if(indice2_<=numeroCarte_) {
                    GraphicBeloteCard carteGraphique2_=new GraphicBeloteCard(carte_,SwingConstants.RIGHT,true);
                    carteGraphique2_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    selectedTrick.add(carteGraphique2_);
                    indice_++;
                    indice2_++;
                } else {
                    break;
                }
            }
            while(indice_<2*numberPlayers-1) {
                JLabel etiquette2_=new JLabel(EMPTY+(indice_-numberPlayers));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
        }
        parent.pack();

    }
}
