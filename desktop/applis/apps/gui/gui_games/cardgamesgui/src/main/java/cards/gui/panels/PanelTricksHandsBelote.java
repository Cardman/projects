package cards.gui.panels;






import cards.belote.DealBelote;
import cards.belote.DisplayingBelote;
import cards.belote.TrickBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.WindowCardsInt;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerSingleImpl;
import cards.gui.labels.GraphicBeloteCard;
import cards.gui.panels.events.ListenerCards;
import cards.gui.panels.events.ListenerTricks;
import code.gui.*;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public class PanelTricksHandsBelote implements ViewablePanelTricksHands {

    private static final String DEFAULT ="Default";
    private final AbsPanel cards;
    private AbsPanel tricks;
    private final AbsPanel selectedTrick;
    private final AbsPanel hands;
    private final NumComboBox trickNumber;
    private final NumComboBox cardNumberTrick;
    private final TricksHandsBelote tricksHands;
    private final ChangeableTitle parent;
    private final byte numberPlayers;
    private final DisplayingBelote displayingBelote;
    private final WindowCardsInt window;
    private final AbsPanel container;

    public PanelTricksHandsBelote(ChangeableTitle _parent,
            TricksHandsBelote _tricksHands,
            byte _numberPlayers,
            StringList _pseudos,
            DisplayingBelote _displayingBelote,
                                  WindowCardsInt _window) {
        window= _window;
        numberPlayers = _numberPlayers;
        displayingBelote = _displayingBelote;
        TranslationsLg lg_ = window.getFrames().currentLg();
        StringMap<String> messages_ = ContainerSingleImpl.file(lg_);
        parent = _parent;
        tricksHands = _tricksHands;
        DealBelote dealt_ = tricksHands.getDistribution();
        CustList<TrickBelote> tricks_ = tricksHands.getTricks();
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
            for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(window,lg_,dealt_.hand(joueur_).getCards())) {
                sousPanneau3_.add(c.getPaintableLabel());
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
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            hands.add(window.getCompoFactory().newLineBox());
        }
        cards.add(hands);
        AbsPanel sousPanneau2_=window.getCompoFactory().newGrid(0,1);
        sousPanneau3_= window.getCompoFactory().newLineBox();
        for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(window,lg_,dealt_.derniereMain().getCards())) {
            sousPanneau3_.add(c.getPaintableLabel());
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
        container.add(cards,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel selectionGameState_=window.getCompoFactory().newLineBox();
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_TRICK)));
        int[] numerosPlis_;
        numerosPlis_=new int[tricks_.size()+2];
        int nbTricksNumbers_ = numerosPlis_.length;
        for(byte indicePli_ = IndexConstants.FIRST_INDEX; indicePli_<nbTricksNumbers_; indicePli_++) {
            numerosPlis_[indicePli_]=indicePli_-1;
        }
        trickNumber=new NumComboBox(window.getFrames(), numerosPlis_);
        trickNumber.setListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber.self());
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_CARD)));
        int[] numerosJoueurs_=new int[_numberPlayers];
        for(byte indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<_numberPlayers; indiceJoueur_++) {
            numerosJoueurs_[indiceJoueur_]=indiceJoueur_+1;
        }
        cardNumberTrick=new NumComboBox(window.getFrames(), numerosJoueurs_);
        cardNumberTrick.setListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick.self());
        container.add(selectionGameState_,GuiConstants.BORDER_LAYOUT_SOUTH);
        changeTrick();
        changeCard();
    }

    private AbsPlainLabel getBlankCard(StringList _nicknames, byte _player) {
        AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(_nicknames.get(_player));
        etiquette2_.setOpaque(true);
        etiquette2_.setBackground(GuiConstants.WHITE);
        etiquette2_.setPreferredSize(Carpet.getMaxDimension());
        return etiquette2_;
    }

    @Override
    public void changeTrick() {

        byte numeroPli_=(byte)NumberUtil.parseInt(trickNumber.getSelectedItem());
        tricksHands.restituerMains(displayingBelote, numberPlayers, numeroPli_);
        hands.removeAll();
        DealBelote dealt_ = tricksHands.getDistribution();
        CustList<TrickBelote> tricks_ = tricksHands.getTricks();
        TranslationsLg lg_ = window.getFrames().currentLg();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
            for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(window,lg_,dealt_.hand(joueur_).getCards())) {
                sousPanneau4_.add(c.getPaintableLabel());
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
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            hands.add(window.getCompoFactory().newLineBox());
        }
        selectedTrick.removeAll();
        if(numeroPli_>0) {
            byte entameur_=tricks_.get(numeroPli_-1).getEntameur();
            byte indice_=0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
            for(CardBelote carte_:tricks_.get(numeroPli_-1)) {
                GraphicBeloteCard carteGraphique2_=new GraphicBeloteCard(window.getFrames(),lg_, carte_, true);
                carteGraphique2_.setPreferredSize(Carpet.getMaxDimension());
                selectedTrick.add(carteGraphique2_.getPaintableLabel());
                indice_++;
            }
            while(indice_<2*numberPlayers-1) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString((long)indice_-numberPlayers));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
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
            byte entameur_=tricks_.get(indicePli_-1).getEntameur();
            byte indice_=0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                tr_.add(etiquette2_,indicePli_*(indice_+1)-1);
                indice_++;
            }
            for(CardBelote carte_:tricks_.get(indicePli_ - 1)) {
                GraphicBeloteCard carteGraphique2_=new GraphicBeloteCard(window.getFrames(),lg_, carte_, true);
                carteGraphique2_.setPreferredSize(Carpet.getMaxDimension());
                tr_.add(carteGraphique2_.getPaintableLabel(),indicePli_*(indice_+1)-1);
                indice_++;
            }
            while(indice_<numberPlayers*2-1) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString((long)indice_-numberPlayers));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                tr_.add(etiquette2_,indicePli_*(indice_+1)-1);
                indice_++;
            }
        }
        cards.add(tricks,indexRem_);
        parent.pack();
    }

    @Override
    public void changeCard() {

        byte numeroPli_=(byte)NumberUtil.parseInt(trickNumber.getSelectedItem());
        if(numeroPli_<1) {
            return;
        }
        byte numeroCarte_=(byte)NumberUtil.parseInt(cardNumberTrick.getSelectedItem());
        numeroCarte_--;
        DealBelote dealt_ = tricksHands.getDistribution();
        CustList<TrickBelote> tricks_ = tricksHands.getTricks();
        tricksHands.restituerMains(displayingBelote, numberPlayers,numeroPli_,numeroCarte_);
        hands.removeAll();
        TranslationsLg lg_ = window.getFrames().currentLg();
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<numberPlayers; joueur_++) {
            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
            for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(window, lg_, dealt_.hand(joueur_).getCards())) {
                sousPanneau4_.add(c.getPaintableLabel());
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
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            hands.add(window.getCompoFactory().newLineBox());
        }
        selectedTrick.removeAll();
        byte entameur_=tricks_.get(numeroPli_-1).getEntameur();
        byte indice_=0;
        byte indice2_=0;
        while(indice_<entameur_) {
            AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
            etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
            etiquette2_.setOpaque(true);
            etiquette2_.setBackground(GuiConstants.WHITE);
            selectedTrick.add(etiquette2_);
            indice_++;
        }
        for(CardBelote carte_:tricks_.get(numeroPli_-1)) {
            if(indice2_<=numeroCarte_) {
                GraphicBeloteCard carteGraphique2_=new GraphicBeloteCard(window.getFrames(),lg_,carte_, true);
                carteGraphique2_.setPreferredSize(Carpet.getMaxDimension());
                selectedTrick.add(carteGraphique2_.getPaintableLabel());
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
            etiquette2_.setBackground(GuiConstants.WHITE);
            selectedTrick.add(etiquette2_);
            indice_++;
        }
        parent.pack();

    }

    public AbsPanel getContainer() {
        return container;
    }

    public NumComboBox getTrickNumber() {
        return trickNumber;
    }

    public NumComboBox getCardNumberTrick() {
        return cardNumberTrick;
    }

    public TricksHandsBelote getTricksHands() {
        return tricksHands;
    }
}
