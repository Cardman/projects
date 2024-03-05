package cards.gui.panels;

import cards.gui.*;
import cards.gui.containers.*;
import cards.gui.labels.*;
import cards.gui.panels.events.ListenerCards;
import cards.gui.panels.events.ListenerTricks;
import code.gui.*;
import code.scripts.messages.cards.*;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;

public abstract class PanelTricksHandsUniqCard<T> implements ViewablePanelTricksHands {

    protected static final String DEFAULT ="Default";
    private final AbsPanel cards;
    private AbsPanel tricks;
    private final AbsPanel selectedTrick;
    private final AbsPanel hands;
    private final NumComboBox trickNumber;
    private final NumComboBox cardNumberTrick;
    private final ChangeableTitle parent;
    private final WindowCardsInt window;
    private final AbsPanel container;
    private final IntCardConverter<T> converter;
    protected PanelTricksHandsUniqCard(ChangeableTitle _parent,
                                       StringList _pseudos,
                                       WindowCardsInt _window, int _nbPlayers, IntCardConverter<T> _conv) {
        parent = _parent;
        window = _window;
        converter = _conv;
        container = window.getCompoFactory().newBorder();
        cards=window.getCompoFactory().newLineBox();
        AbsPanel players_ = window.getCompoFactory().newGrid(0,1);
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_nbPlayers; joueur_++) {
            players_.add(WindowCards.getBlankCard(window,_pseudos, joueur_));
        }
        int nbBots_ = _nbPlayers - 1;
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            players_.add(WindowCards.getBlankCard(window,_pseudos, joueur_));
        }
        cards.add(players_);
        tricks = window.getCompoFactory().newGrid(0,1);
        cards.add(tricks);
        selectedTrick = window.getCompoFactory().newGrid(0,1);
        cards.add(selectedTrick);
        hands=window.getCompoFactory().newGrid(0,1);
        trickNumber=new NumComboBox(window.getFrames());
        cardNumberTrick=new NumComboBox(window.getFrames());
    }

    protected void init(int _nbPlayers) {
        cards(_nbPlayers);
        end(_nbPlayers);
    }

    private void end(int _nbPlayers) {
        TranslationsLg lg_ = window.getFrames().currentLg();
        StringMap<String> messages_ = ContainerSingleImpl.file(lg_);
        AbsPanel selectionGameState_=window.getCompoFactory().newLineBox();
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_TRICK)));
        int off_ = offset();
        int nbTricksNumbers_ = tricksSize()+2-off_;
        for(byte indicePli_ = IndexConstants.FIRST_INDEX; indicePli_<nbTricksNumbers_; indicePli_++) {
            trickNumber.addItem(indicePli_);
        }
        trickNumber.getCombo().repaint();
        trickNumber.setListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber.self());
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_CARD)));
        for(byte indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<= _nbPlayers; indiceJoueur_++) {
            cardNumberTrick.addItem(indiceJoueur_);
        }
        cardNumberTrick.getCombo().repaint();
        cardNumberTrick.setListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick.self());
        container.add(selectionGameState_,GuiConstants.BORDER_LAYOUT_SOUTH);
    }

    protected void cards(int _nbPlayers) {
        hands(_nbPlayers);
        discards();
        container.add(cards,GuiConstants.BORDER_LAYOUT_CENTER);
    }

    private void discards() {
        TranslationsLg lg_ = window.getFrames().currentLg();
        AbsPanel sousPanneau3_;
        AbsPanel sousPanneau2_=window.getCompoFactory().newGrid(0,1);
        sousPanneau3_= window.getCompoFactory().newLineBox();
        for (GraphicCard<T> c: new ContainerSingUtil<T>(converter).getGraphicCardsGene(window, lg_,derniereMain())) {
            sousPanneau3_.add(c.getPaintableLabel());
        }
        sousPanneau2_.add(sousPanneau3_);
        CustList<CustList<T>> left_ = tricks(offset());
        if (!left_.isEmpty()) {
            sousPanneau3_= window.getCompoFactory().newLineBox();
            for (GraphicCard<T> c: new ContainerSingUtil<T>(converter).getGraphicCardsGene(window, lg_, left_.first())) {
                sousPanneau3_.add(c.getPaintableLabel());
            }
            sousPanneau2_.add(sousPanneau3_);
        }
        cards.add(sousPanneau2_);
    }

    protected void hands(int _nbPlayers) {
        int nbBots_ = _nbPlayers - 1;
        TranslationsLg lg_ = window.getFrames().currentLg();
        AbsPanel sousPanneau3_;
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_< _nbPlayers; joueur_++) {
            sousPanneau3_= window.getCompoFactory().newLineBox();
            for (GraphicCard<T> c: new ContainerSingUtil<T>(converter).getGraphicCardsGene(window, lg_,list(joueur_))) {
                sousPanneau3_.add(c.getPaintableLabel());
            }
            hands.add(sousPanneau3_);
        }
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_< nbBots_; joueur_++) {
            hands.add(window.getCompoFactory().newLineBox());
        }
        cards.add(hands);
    }

    protected abstract int tricksSize();
    protected abstract CustList<T> list(byte _i);
    protected abstract CustList<T> derniereMain();
    protected abstract int offset();
    protected abstract int nbPlayers();
    protected abstract CustList<CustList<T>> tricks(int _nb);
    protected abstract CustList<CustList<T>> tricks();
    protected abstract CustList<Integer> tricksStarters();
    protected abstract int restituteFull();
    protected abstract void restitute();

    @Override
    public void changeTrick() {

        int numeroPli_=restituteFull();
        hands.removeAll();
        CustList<CustList<T>> tricks_ = tricks();
        CustList<Integer> tricksStarts_ = tricksStarters();
        TranslationsLg lg_ = window.getFrames().currentLg();
        int nombreJoueurs_ = nbPlayers();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
            for (GraphicCard<T> c: new ContainerSingUtil<T>(converter).getGraphicCardsGene(window,lg_,list(joueur_))) {
                sousPanneau4_.add(c.getPaintableLabel());
            }
            hands.add(sousPanneau4_);
        }
        int nbBots_ = nombreJoueurs_;
        nbBots_--;
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            hands.add(window.getCompoFactory().newLineBox());
        }
        selectedTrick.removeAll();
        int offset_ = offset();
        if(numeroPli_>0) {
            int indexTr_ = numeroPli_ - 1 + offset_;
            int entameur_=tricksStarts_.get(indexTr_);
            byte indice_=0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                selectedTrick.add(etiquette2_);
                indice_++;
            }
            for(T carte_:tricks_.get(indexTr_)) {
                GraphicCard<T> carteGraphique2_=new GraphicCard<T>(converter, carte_, true, window.getFrames(), lg_);
                carteGraphique2_.setPreferredSize(Carpet.getMaxDimension());
                AbsMetaLabelCard.paintCard(window.getImageFactory(), carteGraphique2_);
                selectedTrick.add(carteGraphique2_.getPaintableLabel());
                indice_++;
            }
            while(indice_<2*nombreJoueurs_-1) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString((long)indice_-nombreJoueurs_));
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
            int indexTr_ = indicePli_ - 1 + offset_;
            int entameur_=tricksStarts_.get(indexTr_);
            byte indice_=0;
            while(indice_<entameur_) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(indice_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                tr_.add(etiquette2_, insert(indicePli_, indice_));
                indice_++;
            }
            for(T carte_:tricks_.get(indexTr_)) {
                GraphicCard<T> carteGraphique2_=new GraphicCard<T>(converter, carte_, true, window.getFrames(), lg_);
                carteGraphique2_.setPreferredSize(Carpet.getMaxDimension());
                AbsMetaLabelCard.paintCard(window.getImageFactory(), carteGraphique2_);
                tr_.add(carteGraphique2_.getPaintableLabel(), insert(indicePli_, indice_));
                indice_++;
            }
            while(indice_<nombreJoueurs_*2-1) {
                AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString((long)indice_-nombreJoueurs_));
                etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
                etiquette2_.setOpaque(true);
                etiquette2_.setBackground(GuiConstants.WHITE);
                tr_.add(etiquette2_, insert(indicePli_, indice_));
                indice_++;
            }
        }
        cards.add(tricks,indexRem_);
        parent.pack();
    }

    private static int insert(int _indicePli, int _indice) {
        return _indicePli * (_indice + 1) - 1;
    }

    @Override
    public void changeCard() {

        byte numeroPli_=(byte)(trickNumber.getSelectedIndex() - 1);
        if(numeroPli_<1) {
            return;
        }
        byte numeroCarte_=(byte)cardNumberTrick.getSelectedIndex();
        numeroCarte_--;
        CustList<CustList<T>> tricks_ = tricks();
        CustList<Integer> tricksStarts_ = tricksStarters();
        restitute();
        hands.removeAll();
        TranslationsLg lg_ = window.getFrames().currentLg();
        int nombreJoueurs_ = nbPlayers();
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
            AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
            for (GraphicCard<T> c: new ContainerSingUtil<T>(converter).getGraphicCardsGene(window,lg_,list(joueur_))) {
                sousPanneau4_.add(c.getPaintableLabel());
            }
            hands.add(sousPanneau4_);
        }
        int nbBots_ = nombreJoueurs_;
        nbBots_--;
        for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            hands.add(window.getCompoFactory().newLineBox());
        }
        selectedTrick.removeAll();
        int offset_ = offset();
        int indexTr_ = numeroPli_ - 1 + offset_;
        int entameur_=tricksStarts_.get(indexTr_);
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
        for(T carte_:tricks_.get(indexTr_)) {
            if(indice2_<=numeroCarte_) {
                GraphicCard<T> carteGraphique2_=new GraphicCard<T>(converter, carte_, true, window.getFrames(), lg_);
                carteGraphique2_.setPreferredSize(Carpet.getMaxDimension());
                AbsMetaLabelCard.paintCard(window.getImageFactory(), carteGraphique2_);
                selectedTrick.add(carteGraphique2_.getPaintableLabel());
                indice_++;
                indice2_++;
            } else {
                break;
            }
        }
        while(indice_<2*nombreJoueurs_-1) {
            AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString((long)indice_-nombreJoueurs_));
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
}
