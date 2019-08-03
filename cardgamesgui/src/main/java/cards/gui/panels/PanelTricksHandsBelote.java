package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cards.belote.DealBelote;
import cards.belote.DisplayingBelote;
import cards.belote.TrickBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerBelote;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicBeloteCard;
import cards.gui.panels.events.ListenerCards;
import cards.gui.panels.events.ListenerTricks;
import code.gui.ChangeableTitle;
import code.gui.NumComboBox;
import code.gui.Panel;
import code.gui.TextLabel;
import code.sml.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public class PanelTricksHandsBelote implements ViewablePanelTricksHands {

    private static final String ACCESS = "cards.gui.panels.paneltrickshandsbelote";
    private static final String DEFAULT ="Default";
    private static final String TRICK ="trick";
    private static final String CARD ="card";
    private StringMap<String> messages;
    private Panel cards;
    private Panel tricks;
    private Panel selectedTrick;
    private Panel hands;
    private NumComboBox trickNumber;
    private NumComboBox cardNumberTrick;
    private TricksHandsBelote tricksHands;
    private ChangeableTitle parent;
    private byte numberPlayers;
    private DisplayingBelote displayingBelote;
    private MainWindow window;
    private Panel container;

    public PanelTricksHandsBelote(ChangeableTitle _parent,
            TricksHandsBelote _tricksHands,
            byte _numberPlayers,
            StringList _pseudos,
            DisplayingBelote _displayingBelote,
            MainWindow _window) {
        window= _window;
        numberPlayers = _numberPlayers;
        displayingBelote = _displayingBelote;
        String lg_ = window.getLanguageKey();
        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, lg_,ACCESS);
        parent = _parent;
        tricksHands = _tricksHands;
        DealBelote dealt_ = tricksHands.getDistribution();
        CustList<TrickBelote> tricks_ = tricksHands.getTricks();
        container = Panel.newBorder();
        cards=new Panel();
        Panel players_ = Panel.newGrid(0,1);
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<_numberPlayers;joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
        int nbBots_ = _numberPlayers - 1;
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
        cards.add(players_);
        tricks = Panel.newGrid(0,1);
        cards.add(tricks);
        selectedTrick = Panel.newGrid(0,1);
        cards.add(selectedTrick);
        hands=Panel.newGrid(0,1);
        Panel sousPanneau3_;
        //boolean entered_ = false;
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_numberPlayers;joueur_++) {
            sousPanneau3_=Panel.newFlow(FlowLayout.LEFT,0,0);
            for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(lg_,dealt_.hand(joueur_))) {
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
            hands.add(Panel.newFlow(FlowLayout.LEFT,0,0));
        }
        cards.add(hands);
        Panel sousPanneau2_=Panel.newGrid(0,1);
        sousPanneau3_=Panel.newFlow(FlowLayout.LEFT,0,0);
        for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(lg_,dealt_.derniereMain())) {
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
        container.add(cards,BorderLayout.CENTER);
        Panel selectionGameState_=new Panel();
        selectionGameState_.add(new TextLabel(messages.getVal(TRICK)));
        Integer[] numerosPlis_;
        numerosPlis_=new Integer[tricks_.size()+2];
        int nbTricksNumbers_ = numerosPlis_.length;
        for(byte indicePli_=CustList.FIRST_INDEX;indicePli_<nbTricksNumbers_;indicePli_++) {
            numerosPlis_[indicePli_]=indicePli_-1;
        }
        trickNumber=new NumComboBox(numerosPlis_);
        trickNumber.setListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber);
        selectionGameState_.add(new TextLabel(messages.getVal(CARD)));
        Integer[] numerosJoueurs_=new Integer[_numberPlayers];
        for(byte indiceJoueur_=CustList.FIRST_INDEX;indiceJoueur_<_numberPlayers;indiceJoueur_++) {
            numerosJoueurs_[indiceJoueur_]=indiceJoueur_+1;
        }
        cardNumberTrick=new NumComboBox(numerosJoueurs_);
        cardNumberTrick.setListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick);
        container.add(selectionGameState_,BorderLayout.SOUTH);
    }

    private static TextLabel getBlankCard(StringList _nicknames, byte _player) {
        TextLabel etiquette2_=new TextLabel(_nicknames.get(_player));
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
        DealBelote dealt_ = tricksHands.getDistribution();
        CustList<TrickBelote> tricks_ = tricksHands.getTricks();
        String lg_ = window.getLanguageKey();
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
            Panel sousPanneau4_=Panel.newFlow(FlowLayout.LEFT,0,0);
            for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(lg_,dealt_.hand(joueur_))) {
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
        nbBots_--;
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            hands.add(Panel.newFlow(FlowLayout.LEFT,0,0));
        }
        selectedTrick.removeAll();
        if(numeroPli_>0) {
            byte entameur_=tricks_.get(numeroPli_-1).getEntameur();
            byte indice_=0;
            while(indice_<entameur_) {
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
            for(CardBelote carte_:tricks_.get(numeroPli_-1)) {
                GraphicBeloteCard carteGraphique2_=new GraphicBeloteCard(lg_, carte_,SwingConstants.RIGHT,true);
                carteGraphique2_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                selectedTrick.add(carteGraphique2_);
                indice_++;
            }
            while(indice_<2*numberPlayers-1) {
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_-numberPlayers));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
        }
        tricks.removeAll();
        int indexRem_ = cards.remove(tricks);
        Panel tr_;
        if(numeroPli_>1) {
            tr_ = Panel.newGrid(0,numeroPli_ - 1);
        } else {
            tr_ = Panel.newGrid(0,1);
        }
        tricks = tr_;
        for(byte indicePli_=1;indicePli_<numeroPli_;indicePli_++) {
            byte entameur_=tricks_.get(indicePli_-1).getEntameur();
            byte indice_=0;
            while(indice_<entameur_) {
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                tr_.add(etiquette2_,indicePli_*(indice_+1)-1);
                indice_++;
            }
            for(CardBelote carte_:tricks_.get(indicePli_ - 1)) {
                GraphicBeloteCard carteGraphique2_=new GraphicBeloteCard(lg_, carte_,SwingConstants.RIGHT,true);
                carteGraphique2_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                tr_.add(carteGraphique2_,indicePli_*(indice_+1)-1);
                indice_++;
            }
            while(indice_<numberPlayers*2-1) {
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_-numberPlayers));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                tr_.add(etiquette2_,indicePli_*(indice_+1)-1);
                indice_++;
            }
        }
        cards.add(tricks,indexRem_);
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
        String lg_ = window.getLanguageKey();
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<numberPlayers;joueur_++) {
            Panel sousPanneau4_=Panel.newFlow(FlowLayout.LEFT,0,0);
            for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(lg_, dealt_.hand(joueur_))) {
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
        nbBots_--;
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nbBots_;joueur_++) {
            hands.add(Panel.newFlow(FlowLayout.LEFT,0,0));
        }
        selectedTrick.removeAll();
        if(numeroPli_>0) {
            byte entameur_=tricks_.get(numeroPli_-1).getEntameur();
            byte indice_=0;
            byte indice2_=0;
            while(indice_<entameur_) {
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_));
                etiquette2_.setHorizontalAlignment(SwingConstants.CENTER);
                etiquette2_.setFont(new Font(DEFAULT,Font.BOLD,50));
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
            for(CardBelote carte_:tricks_.get(numeroPli_-1)) {
                if(indice2_<=numeroCarte_) {
                    GraphicBeloteCard carteGraphique2_=new GraphicBeloteCard(lg_,carte_,SwingConstants.RIGHT,true);
                    carteGraphique2_.setPreferredSize(GraphicBeloteCard.getMaxDimension());
                    selectedTrick.add(carteGraphique2_);
                    indice_++;
                    indice2_++;
                } else {
                    break;
                }
            }
            while(indice_<2*numberPlayers-1) {
                TextLabel etiquette2_=new TextLabel(Long.toString(indice_-numberPlayers));
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

    public Panel getContainer() {
        return container;
    }
}
