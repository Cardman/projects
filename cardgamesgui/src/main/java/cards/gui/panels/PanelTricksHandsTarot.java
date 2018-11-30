package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cards.gui.MainWindow;
import cards.gui.containers.ContainerTarot;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicTarotCard;
import cards.gui.panels.events.ListenerCards;
import cards.gui.panels.events.ListenerTricks;
import cards.tarot.DealTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.TrickTarot;
import cards.tarot.TricksHandsTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.ChangeableTitle;
import code.gui.NumComboBox;
import code.gui.Panel;
import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public class PanelTricksHandsTarot extends Panel implements ViewablePanelTricksHands {

    private static final String ACCESS = "cards.gui.panels.paneltrickshandstarot";
    private static final String DEFAULT ="Default";
    private static final String TRICK ="trick";
    private static final String CARD ="card";
    private StringMap<String> messages = new StringMap<String>();
    private Panel cards;
    private Panel tricks;
    private Panel selectedTrick;
    private Panel hands;
    private NumComboBox trickNumber;
    private NumComboBox cardNumberTrick;
    private TricksHandsTarot tricksHands;
    private ChangeableTitle parent;

    private byte numberPlayers;
    private DisplayingTarot displayingTarot;
    private MainWindow window;
    public PanelTricksHandsTarot(ChangeableTitle _parent,
            TricksHandsTarot _tricksHands,
            byte _numberPlayers,
            StringList _pseudos,
            DisplayingTarot _displayingTarot, MainWindow _window) {
        window = _window;
        String lg_ = window.getLanguageKey();
        numberPlayers = _numberPlayers;
        displayingTarot = _displayingTarot;
        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, lg_, ACCESS);
        parent = _parent;
        tricksHands = _tricksHands;
        DealTarot dealt_ = tricksHands.getDistribution();
        CustList<TrickTarot> tricks_ = tricksHands.getTricks();

        setLayout(new BorderLayout());
        cards=new Panel();
        Panel players_ = new Panel(new GridLayout(0,1));
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<_numberPlayers;joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
        int nbBots_ = _numberPlayers - 1;
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
        cards.add(players_);
        tricks = new Panel(new GridLayout(0,1));
        cards.add(tricks);
        selectedTrick = new Panel(new GridLayout(0,1));
        cards.add(selectedTrick);
        hands=new Panel(new GridLayout(0,1));
        Panel sousPanneau3_;
        //boolean entered_ = false;
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_numberPlayers;joueur_++) {
            sousPanneau3_=new Panel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicTarotCard c: ContainerTarot.getGraphicCards(lg_, dealt_.main(joueur_))) {
                sousPanneau3_.add(c);
            }
//            entered_ = false;
//            for(CardTarot c: dealt_.main(joueur_))
//            {
//                GraphicTarotCard carteGraphique_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau3_.add(carteGraphique_);
//                entered_ = true;
//            }
            hands.add(sousPanneau3_);
        }
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            hands.add(new Panel(new FlowLayout(FlowLayout.LEFT,0,0)));
        }
        cards.add(hands);
        Panel sousPanneau2_=new Panel(new GridLayout(0,1));
        sousPanneau3_=new Panel(new FlowLayout(FlowLayout.LEFT,0,0));
        for (GraphicTarotCard c: ContainerTarot.getGraphicCards(lg_, dealt_.derniereMain())) {
            sousPanneau3_.add(c);
        }
//        entered_ = false;
//        for(CardTarot c: dealt_.derniereMain())
//        {
//            GraphicTarotCard carteGraphique_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carteGraphique_.setPreferredSize(entered_);
//            sousPanneau3_.add(carteGraphique_);
//            entered_ = true;
//        }
        sousPanneau2_.add(sousPanneau3_);
        if (!tricks_.isEmpty()) {
            sousPanneau3_=new Panel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicTarotCard c: ContainerTarot.getGraphicCards(lg_, tricks_.first())) {
                sousPanneau3_.add(c);
            }
//            entered_ = false;
//            for(CardTarot c: tricks_.first())
//            {
//                GraphicTarotCard carteGraphique_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau3_.add(carteGraphique_);
//                entered_ = true;
//            }
            sousPanneau2_.add(sousPanneau3_);
        }
        cards.add(sousPanneau2_);
        add(cards,BorderLayout.CENTER);
        Panel selectionGameState_=new Panel();
        selectionGameState_.add(new JLabel(messages.getVal(TRICK)));
        Integer[] numerosPlis_;
        numerosPlis_=new Integer[tricks_.size()+1];
        int nbTricksNumbers_ = numerosPlis_.length;
        for(byte indicePli_=CustList.FIRST_INDEX;indicePli_<nbTricksNumbers_;indicePli_++) {
            numerosPlis_[indicePli_]=indicePli_-1;
        }
        trickNumber=new NumComboBox(numerosPlis_);
        trickNumber.setListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber);
        selectionGameState_.add(new JLabel(messages.getVal(CARD)));
        Integer[] numerosJoueurs_=new Integer[_numberPlayers];
        for(byte indiceJoueur_=CustList.FIRST_INDEX;indiceJoueur_<_numberPlayers;indiceJoueur_++) {
            numerosJoueurs_[indiceJoueur_]=indiceJoueur_+1;
        }
        cardNumberTrick=new NumComboBox(numerosJoueurs_);
        cardNumberTrick.setListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick);
        add(selectionGameState_,BorderLayout.SOUTH);
    }

    private static JLabel getBlankCard(StringList _nicknames, byte _player) {
        JLabel etiquette2_=new JLabel(_nicknames.get(_player));
        etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
        etiquette2_.setOpaque(true);
        etiquette2_.setBackground(Color.WHITE);
        etiquette2_.setPreferredSize(GraphicTarotCard.getMaxDimension());
        return etiquette2_;
    }

    @Override
    public void changeTrick() {
        String lg_ = window.getLanguageKey();
        byte numeroSelectionne_=Byte.parseByte(trickNumber.getCurrent().toString());
        byte numeroPli_=numeroSelectionne_;
        CustList<TrickTarot> tricks_ = tricksHands.getTricks();
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingTarot, numberPlayers, numeroPli_);

        hands.removeAll();
        hands.setLayout(new GridLayout(0,1));
        DealTarot restoredDeal_ = tricksHands.getDistribution();
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
            Panel sousPanneau4_=new Panel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicTarotCard c: ContainerTarot.getGraphicCards(lg_, restoredDeal_.main(joueur_))) {
                sousPanneau4_.add(c);
            }
//            boolean entered_ = false;
//            for(CardTarot c: restoredDeal_.main(joueur_))
//            {
//                GraphicTarotCard carteGraphique_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau4_.add(carteGraphique_);
//                entered_ = true;
//            }
            hands.add(sousPanneau4_);
        }
        int nbBots_ = numberPlayers;
        nbBots_--;
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            hands.add(new Panel(new FlowLayout(FlowLayout.LEFT,0,0)));
        }
        selectedTrick.removeAll();
        if(numeroPli_>0) {
            selectedTrick.setLayout(new GridLayout(0,1));
            byte entameur_=tricks_.get(numeroPli_).getEntameur();
            byte indice_=0;
            while(indice_<entameur_) {
                JLabel etiquette2_=new JLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
            for(CardTarot carte_:tricks_.get(numeroPli_)) {
                GraphicTarotCard carteGraphique2_=new GraphicTarotCard(lg_, carte_,SwingConstants.RIGHT,true);
                carteGraphique2_.setPreferredSize(GraphicTarotCard.getMaxDimension());
                selectedTrick.add(carteGraphique2_);
                indice_++;
            }
            while(indice_<2*numberPlayers-1) {
                JLabel etiquette2_=new JLabel(Long.toString(indice_-numberPlayers));
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
            if(numeroPli_==numeroSelectionne_) {
                tricks.setLayout(new GridLayout(0,numeroPli_-1));
            } else if(numeroPli_==numeroSelectionne_+1&&numeroSelectionne_>1) {
                tricks.setLayout(new GridLayout(0,numeroSelectionne_-1));
            }
        }
        boolean passe2_=false;
        for(byte indicePli_=1;indicePli_<numeroPli_;indicePli_++) {
            byte entameur_=tricks_.get(indicePli_).getEntameur();
            byte indice_=0;
            if(!passe2_) {
                while(indice_<entameur_) {
                    JLabel etiquette2_=new JLabel(Long.toString(indice_));
                    etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                    etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setBackground(Color.WHITE);
                    tricks.add(etiquette2_,indicePli_*(indice_+1)-1);
                    indice_++;
                }
                for(CardTarot carte_:tricks_.get(indicePli_)) {
                    GraphicTarotCard carteGraphique2_=new GraphicTarotCard(lg_, carte_,SwingConstants.RIGHT,true);
                    carteGraphique2_.setPreferredSize(GraphicTarotCard.getMaxDimension());
                    tricks.add(carteGraphique2_,indicePli_*(indice_+1)-1);
                    indice_++;
                }
                while(indice_<numberPlayers*2-1) {
                    JLabel etiquette2_=new JLabel(Long.toString(indice_-numberPlayers));
                    etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                    etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setBackground(Color.WHITE);
                    tricks.add(etiquette2_,indicePli_*(indice_+1)-1);
                    indice_++;
                }
            } else {
                while(indice_<entameur_) {
                    JLabel etiquette2_=new JLabel(Long.toString(indice_));
                    etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                    etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setBackground(Color.WHITE);
                    tricks.add(etiquette2_,(indicePli_-1)*(indice_+1)-1);
                    indice_++;
                }
                for(CardTarot carte_:tricks_.get(indicePli_)) {
                    GraphicTarotCard carteGraphique2_=new GraphicTarotCard(lg_, carte_,SwingConstants.RIGHT,true);
                    carteGraphique2_.setPreferredSize(GraphicTarotCard.getMaxDimension());
                    tricks.add(carteGraphique2_,(indicePli_-1)*(indice_+1)-1);
                    indice_++;
                }
                while(indice_<numberPlayers*2-1) {
                    JLabel etiquette2_=new JLabel(Long.toString(indice_-numberPlayers));
                    etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                    etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                    etiquette2_.setOpaque(true);
                    etiquette2_.setBackground(Color.WHITE);
                    tricks.add(etiquette2_,(indicePli_-1)*(indice_+1)-1);
                    indice_++;
                }
            }
        }
        parent.pack();

    }
    @Override
    public void changeCard() {
        String lg_ = window.getLanguageKey();
        byte numeroSelectionne_=Byte.parseByte(trickNumber.getCurrent().toString());
        byte numeroPli_=numeroSelectionne_;
        if(numeroPli_<1) {
            return;
        }
        CustList<TrickTarot> tricks_ = tricksHands.getTricks();
        byte numeroCarte_=Byte.parseByte(cardNumberTrick.getCurrent().toString());
        numeroCarte_--;
        tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingTarot, numberPlayers, numeroPli_, numeroCarte_);

        hands.removeAll();
        hands.setLayout(new GridLayout(0,1));
        DealTarot restoredDeal_ = tricksHands.getDistribution();
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
            Panel sousPanneau4_=new Panel(new FlowLayout(FlowLayout.LEFT,0,0));
            for (GraphicTarotCard c: ContainerTarot.getGraphicCards(lg_, restoredDeal_.main(joueur_))) {
                sousPanneau4_.add(c);
            }
//            boolean entered_ = false;
//            for(CardTarot c: restoredDeal_.main(joueur_))
//            {
//                GraphicTarotCard carteGraphique_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//                carteGraphique_.setPreferredSize(entered_);
//                sousPanneau4_.add(carteGraphique_);
//                entered_ = true;
//            }
            hands.add(sousPanneau4_);
        }
        int nbBots_ = numberPlayers;
        nbBots_--;
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            hands.add(new Panel(new FlowLayout(FlowLayout.LEFT,0,0)));
        }
        selectedTrick.removeAll();
        if(numeroPli_>0) {
            selectedTrick.setLayout(new GridLayout(0,1));
            byte entameur_=tricks_.get(numeroPli_).getEntameur();
            byte indice_=0;
            byte indice2_=0;
            while(indice_<entameur_) {
                JLabel etiquette2_=new JLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
            for(CardTarot carte_:tricks_.get(numeroPli_)) {
                if(indice2_<=numeroCarte_) {
                    GraphicTarotCard carteGraphique2_=new GraphicTarotCard(lg_, carte_,SwingConstants.RIGHT,true);
                    carteGraphique2_.setPreferredSize(GraphicTarotCard.getMaxDimension());
                    selectedTrick.add(carteGraphique2_);
                    indice_++;
                    indice2_++;
                } else {
                    break;
                }
            }
            while(indice_<2*numberPlayers-1) {
                JLabel etiquette2_=new JLabel(Long.toString(indice_-numberPlayers));
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
