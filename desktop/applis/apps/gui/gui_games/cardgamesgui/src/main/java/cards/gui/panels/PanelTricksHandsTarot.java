package cards.gui.panels;

import java.awt.Color;


import javax.swing.SwingConstants;

import cards.gui.WindowCards;
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
import code.gui.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public class PanelTricksHandsTarot implements ViewablePanelTricksHands {

    private static final String ACCESS = "cards.gui.panels.paneltrickshandstarot";
    private static final String DEFAULT ="Default";
    private static final String TRICK ="trick";
    private static final String CARD ="card";
    private StringMap<String> messages = new StringMap<String>();
    private final AbsPanel cards;
    private AbsPanel tricks;
    private final AbsPanel selectedTrick;
    private final AbsPanel hands;
    private final NumComboBox trickNumber;
    private final NumComboBox cardNumberTrick;
    private final TricksHandsTarot tricksHands;
    private final ChangeableTitle parent;

    private final byte numberPlayers;
    private final DisplayingTarot displayingTarot;
    private final WindowCards window;
    private final AbsPanel container;
    public PanelTricksHandsTarot(ChangeableTitle _parent,
            TricksHandsTarot _tricksHands,
            byte _numberPlayers,
            StringList _pseudos,
            DisplayingTarot _displayingTarot, WindowCards _window) {
        window = _window;
        String lg_ = window.getLanguageKey();
        numberPlayers = _numberPlayers;
        displayingTarot = _displayingTarot;
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, lg_, ACCESS);
        parent = _parent;
        tricksHands = _tricksHands;
        DealTarot dealt_ = tricksHands.getDistribution();
        CustList<TrickTarot> tricks_ = tricksHands.getTricks();
        container = window.getCompoFactory().newBorder();
        cards=window.getCompoFactory().newLineBox();
        AbsPanel players_ = window.getCompoFactory().newGrid(0,1);
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_numberPlayers; joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
        int nbBots_ = _numberPlayers - 1;
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            players_.add(getBlankCard(_pseudos, joueur_));
        }
        cards.add(players_);
        tricks = window.getCompoFactory().newGrid(0,1);
        cards.add(tricks);
        selectedTrick = window.getCompoFactory().newGrid(0,1);
        cards.add(selectedTrick);
        hands=window.getCompoFactory().newGrid(0,1);
        AbsPanel sousPanneau3_;
        //boolean entered_ = false;
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_numberPlayers; joueur_++) {
            sousPanneau3_= window.getCompoFactory().newLineBox();
            for (GraphicTarotCard c: ContainerTarot.getGraphicCards(window, lg_, dealt_.hand(joueur_))) {
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
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            hands.add(window.getCompoFactory().newLineBox());
        }
        cards.add(hands);
        AbsPanel sousPanneau2_=window.getCompoFactory().newGrid(0,1);
        sousPanneau3_= window.getCompoFactory().newLineBox();
        for (GraphicTarotCard c: ContainerTarot.getGraphicCards(window, lg_, dealt_.derniereMain())) {
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
            sousPanneau3_= window.getCompoFactory().newLineBox();
            for (GraphicTarotCard c: ContainerTarot.getGraphicCards(window, lg_, tricks_.first())) {
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
        container.add(cards,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel selectionGameState_=window.getCompoFactory().newLineBox();
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages.getVal(TRICK)));
        int[] numerosPlis_;
        numerosPlis_=new int[tricks_.size()+1];
        int nbTricksNumbers_ = numerosPlis_.length;
        for(byte indicePli_ = IndexConstants.FIRST_INDEX; indicePli_<nbTricksNumbers_; indicePli_++) {
            numerosPlis_[indicePli_]=indicePli_-1;
        }
        trickNumber=new NumComboBox(window.getFrames(),window.getFrames().getGeneComboBox(), numerosPlis_);
        trickNumber.setListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber.self());
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages.getVal(CARD)));
        int[] numerosJoueurs_=new int[_numberPlayers];
        for(byte indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<_numberPlayers; indiceJoueur_++) {
            numerosJoueurs_[indiceJoueur_]=indiceJoueur_+1;
        }
        cardNumberTrick=new NumComboBox(window.getFrames(),window.getFrames().getGeneComboBox(), numerosJoueurs_);
        cardNumberTrick.setListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick.self());
        container.add(selectionGameState_,GuiConstants.BORDER_LAYOUT_SOUTH);
    }

    private AbsPlainLabel getBlankCard(StringList _nicknames, byte _player) {
        AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(_nicknames.get(_player));
        etiquette2_.setOpaque(true);
        etiquette2_.setBackground(Color.WHITE);
        etiquette2_.setPreferredSize(GraphicTarotCard.getMaxDimension());
        return etiquette2_;
    }

    @Override
    public void changeTrick() {
        String lg_ = window.getLanguageKey();
        byte numeroSelectionne_=(byte)NumberUtil.parseInt(trickNumber.getCurrent().toString());
        byte numeroPli_=numeroSelectionne_;
        CustList<TrickTarot> tricks_ = tricksHands.getTricks();
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingTarot, numberPlayers, numeroPli_);

        hands.removeAll();
        DealTarot restoredDeal_ = tricksHands.getDistribution();
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
            for (GraphicTarotCard c: ContainerTarot.getGraphicCards(window, lg_, restoredDeal_.hand(joueur_))) {
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
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            hands.add(window.getCompoFactory().newLineBox());
        }
        selectedTrick.removeAll();
        if(numeroPli_>0) {
            byte entameur_=tricks_.get(numeroPli_).getEntameur();
            byte indice_=0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
            for(CardTarot carte_:tricks_.get(numeroPli_)) {
                GraphicTarotCard carteGraphique2_=new GraphicTarotCard(window.getImageFactory(), lg_, carte_,SwingConstants.RIGHT,true, window.getCompoFactory());
                carteGraphique2_.setPreferredSize(GraphicTarotCard.getMaxDimension());
                selectedTrick.add(carteGraphique2_);
                indice_++;
            }
            while(indice_<2*numberPlayers-1) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString((long)indice_-numberPlayers));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
        }
        tricks.removeAll();
        int indexRem_ = cards.remove(tricks);
        AbsPanel tr_;
        if(numeroPli_>1) {
            tr_ = window.getCompoFactory().newGrid(0,numeroPli_ - 1);
        } else {
            tr_ = window.getCompoFactory().newGrid(0,1);
        }
        tricks = tr_;
        for(byte indicePli_=1;indicePli_<numeroPli_;indicePli_++) {
            byte entameur_=tricks_.get(indicePli_).getEntameur();
            byte indice_=0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(Color.WHITE);
                tr_.add(etiquette2_,indicePli_*(indice_+1)-1);
                indice_++;
            }
            for(CardTarot carte_:tricks_.get(indicePli_)) {
                GraphicTarotCard carteGraphique2_=new GraphicTarotCard(window.getImageFactory(), lg_, carte_,SwingConstants.RIGHT,true, window.getCompoFactory());
                carteGraphique2_.setPreferredSize(GraphicTarotCard.getMaxDimension());
                tr_.add(carteGraphique2_,indicePli_*(indice_+1)-1);
                indice_++;
            }
            while(indice_<numberPlayers*2-1) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString((long)indice_-numberPlayers));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
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
        String lg_ = window.getLanguageKey();
        byte numeroSelectionne_=(byte)NumberUtil.parseInt(trickNumber.getCurrent().toString());
        byte numeroPli_=numeroSelectionne_;
        if(numeroPli_<1) {
            return;
        }
        CustList<TrickTarot> tricks_ = tricksHands.getTricks();
        byte numeroCarte_=(byte)NumberUtil.parseInt(cardNumberTrick.getCurrent().toString());
        numeroCarte_--;
        tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingTarot, numberPlayers, numeroPli_, numeroCarte_);

        hands.removeAll();
        DealTarot restoredDeal_ = tricksHands.getDistribution();
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
            for (GraphicTarotCard c: ContainerTarot.getGraphicCards(window, lg_, restoredDeal_.hand(joueur_))) {
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
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            hands.add(window.getCompoFactory().newLineBox());
        }
        selectedTrick.removeAll();
        byte entameur_=tricks_.get(numeroPli_).getEntameur();
        byte indice_=0;
        byte indice2_=0;
        while(indice_<entameur_) {
            AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
            etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
            etiquette2_.setOpaque(true);
            etiquette2_.setBackground(Color.WHITE);
            selectedTrick.add(etiquette2_);
            indice_++;
        }
        for(CardTarot carte_:tricks_.get(numeroPli_)) {
            if(indice2_<=numeroCarte_) {
                GraphicTarotCard carteGraphique2_=new GraphicTarotCard(window.getImageFactory(), lg_, carte_,SwingConstants.RIGHT,true, window.getCompoFactory());
                carteGraphique2_.setPreferredSize(GraphicTarotCard.getMaxDimension());
                selectedTrick.add(carteGraphique2_);
                indice_++;
                indice2_++;
            } else {
                break;
            }
        }
        while(indice_<2*numberPlayers-1) {
            AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString((long)indice_-numberPlayers));
            etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
            etiquette2_.setOpaque(true);
            etiquette2_.setBackground(Color.WHITE);
            selectedTrick.add(etiquette2_);
            indice_++;
        }
        parent.pack();

    }

    public AbsPanel getContainer() {
        return container;
    }
}
