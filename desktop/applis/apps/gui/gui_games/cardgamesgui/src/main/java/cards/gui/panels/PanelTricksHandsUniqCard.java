package cards.gui.panels;

import cards.gui.*;
import cards.gui.containers.*;
import cards.gui.labels.*;
import cards.gui.panels.events.ListenerCards;
import cards.gui.panels.events.ListenerTricks;
import code.gui.*;
import code.gui.files.MessagesGuiFct;
import code.scripts.messages.cards.*;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;

public abstract class PanelTricksHandsUniqCard<T> implements ViewablePanelTricksHands {

    protected static final String DEFAULT ="";
    private final AbsPanel cards;
    private final AbsPanel tricks;
    private final AbsPanel selectedTrick;
    private final AbsPanel hands;
    private final NumComboBox trickNumber;
    private final NumComboBox cardNumberTrick;
    private final AbsCommonFrame parent;
    private final WindowCardsInt window;
    private final AbsPanel container;
    private final IntCardConverter<T> converter;
    protected PanelTricksHandsUniqCard(AbsCommonFrame _parent,
                                       StringList _pseudos,
                                       WindowCardsInt _window, int _nbPlayers, IntCardConverter<T> _conv) {
        parent = _parent;
        window = _window;
        converter = _conv;
        container = window.getCompoFactory().newBorder();
        cards=window.getCompoFactory().newLineBox();
        AbsPanel players_ = window.getCompoFactory().newGrid();
        for(int joueur_ = IndexConstants.FIRST_INDEX; joueur_<_nbPlayers; joueur_++) {
            players_.add(WindowCards.getBlankCard(window,_pseudos, joueur_),WindowCardsCore.ctsRem(window.getCompoFactory()));
        }
        int nbBots_ = _nbPlayers - 1;
        for(int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            players_.add(WindowCards.getBlankCard(window,_pseudos, joueur_),WindowCardsCore.ctsRem(window.getCompoFactory()));
        }
        cards.add(players_);
        tricks = window.getCompoFactory().newLineBox();
        cards.add(tricks);
        selectedTrick = window.getCompoFactory().newGrid();
        cards.add(selectedTrick);
        hands=window.getCompoFactory().newGrid();
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
        for(int indicePli_ = IndexConstants.FIRST_INDEX; indicePli_<nbTricksNumbers_; indicePli_++) {
            trickNumber.addItem(indicePli_);
        }
        trickNumber.getCombo().repaint();
        trickNumber.setListener(new ListenerTricks(this));
        selectionGameState_.add(trickNumber.self());
        selectionGameState_.add(window.getCompoFactory().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_CARD)));
        for(int indiceJoueur_ = IndexConstants.FIRST_INDEX; indiceJoueur_<= _nbPlayers; indiceJoueur_++) {
            cardNumberTrick.addItem(indiceJoueur_);
        }
        cardNumberTrick.getCombo().repaint();
        cardNumberTrick.setListener(new ListenerCards(this));
        selectionGameState_.add(cardNumberTrick.self());
        container.add(selectionGameState_, MessagesGuiFct.BORDER_LAYOUT_SOUTH);
    }

    protected void cards(int _nbPlayers) {
        hands(_nbPlayers);
        discards();
        container.add(window.getCompoFactory().newAbsScrollPane(cards), MessagesGuiFct.BORDER_LAYOUT_CENTER);
    }

    private void discards() {
        AbsPanel sousPanneau2_=window.getCompoFactory().newGrid();
        AbsPanel sousPanneau4_ = buildHand(derniereMain());
        sousPanneau2_.add(sousPanneau4_,WindowCardsCore.ctsRem(window.getCompoFactory()));
        CustList<TrickCardContentDto<T>> left_ = tricks(offset());
        if (!left_.isEmpty()) {
            sousPanneau2_.add(buildHand(left_.first().getCards()),WindowCardsCore.ctsRem(window.getCompoFactory()));
        }
        cards.add(sousPanneau2_);
    }

    protected void hands(int _nbPlayers) {
        updateHands();
        updateBots(_nbPlayers);
        cards.add(hands);
    }

    protected abstract int tricksSize();
    protected abstract CustList<T> list(int _i);
    protected abstract CustList<T> derniereMain();
    protected abstract int offset();
    protected abstract int nbPlayers();
    protected abstract CustList<TrickCardContentDto<T>> tricks(int _nb);
    protected abstract CustList<TrickCardContentDto<T>> tricks();
    protected abstract int restituteFull();
    protected abstract void restitute();

    @Override
    public void changeTrick() {

        int numeroPli_=restituteFull();
        hands.removeAll();
        CustList<TrickCardContentDto<T>> tricks_ = tricks();
        int nombreJoueurs_ = updateHands();
        updateBots(nombreJoueurs_);
        selectedTrick.removeAll();
        int offset_ = offset();
        if(numeroPli_>0) {
            int indexTr_ = numeroPli_ - 1 + offset_;
            int s_ = tricks_.get(indexTr_).getCards().size();
            cardNumberTrick.removeAllItems();
            for (int i =0; i <= s_; i++) {
                cardNumberTrick.addItem(i);
            }
            cardNumberTrick.selectItem(s_);
        } else {
            cardNumberTrick.removeAllItems();
            cardNumberTrick.selectItem(-1);
        }
        cardNumberTrick.getCombo().repaint();
        tricks.removeAll();
        for(int indicePli_=1;indicePli_<numeroPli_;indicePli_++) {
            AbsPanel g_ = window.getCompoFactory().newGrid();
            int indexTr_ = indicePli_ - 1 + offset_;
            buildTrickPanel(tricks_, indexTr_, g_);
            tricks.add(g_);
        }
        parent.pack();
        changeCard();
    }

    private void buildTrickPanel(CustList<TrickCardContentDto<T>> _tricks, int _indexTr, AbsPanel _g) {
        TrickCardContentDto<T> trick_ = _tricks.get(_indexTr);
        int entameur_= trick_.getStarter();
        int indice_ = begin(_g, entameur_);
        for(T carte_: trick_.getCards()) {
            addCard(carte_, _g);
            indice_++;
        }
        end(indice_, _g);
    }

    private AbsPlainLabel etiquette(int _nb) {
        AbsPlainLabel etiquette2_=window.getCompoFactory().newPlainLabel(Long.toString(_nb));
        etiquette2_.setPreferredSize(Carpet.getMaxDimension());
        etiquette2_.setFont(DEFAULT,GuiConstants.BOLD,50);
        etiquette2_.setOpaque(true);
        etiquette2_.setBackground(GuiConstants.WHITE);
        return etiquette2_;
    }

    @Override
    public void changeCard() {

        int numeroPli_= trickNumber.getSelectedIndex() - 1;
        if(numeroPli_<1) {
            return;
        }
        int numeroCarte_= cardNumberTrick.getSelectedIndex();
        numeroCarte_--;
        CustList<TrickCardContentDto<T>> tricks_ = tricks();
        restitute();
        hands.removeAll();
        int nombreJoueurs_ = updateHands();
        updateBots(nombreJoueurs_);
        selectedTrick.removeAll();
        int offset_ = offset();
        int indexTr_ = numeroPli_ - 1 + offset_;
        TrickCardContentDto<T> trick_ = tricks_.get(indexTr_);
        int entameur_= trick_.getStarter();
        int indice_ = begin(selectedTrick, entameur_);
        int indice2_ = 0;
        for(T carte_: trick_.getCards()) {
            if(indice2_<=numeroCarte_) {
                addCard(carte_, selectedTrick);
                indice_++;
                indice2_++;
            } else {
                break;
            }
        }
        end(indice_, selectedTrick);
        parent.pack();

    }

    private void addCard(T _carte, AbsPanel _g) {
        TranslationsLg lg_ = window.getFrames().currentLg();
        GraphicCard<T> carteGraphique2_=new GraphicCard<T>(converter, _carte, true, window.getFrames(), lg_);
        carteGraphique2_.setPreferredSize(Carpet.getMaxDimension());
        AbsMetaLabelCard.paintCard(window.getImageFactory(), carteGraphique2_);
        _g.add(carteGraphique2_.getPaintableLabel(),WindowCardsCore.ctsRem(window.getCompoFactory()));
    }

    private void end(int _from, AbsPanel _g) {
        int nombreJoueurs_ = nbPlayers();
        int indice_ = _from;
        while(indice_ <2* nombreJoueurs_ -1) {
            AbsPlainLabel etiquette2_ = etiquette(indice_ - nombreJoueurs_);
            _g.add(etiquette2_,WindowCardsCore.ctsRem(window.getCompoFactory()));
            indice_++;
        }
    }

    private int begin(AbsPanel _g, int _until) {
        int indice_= 0;
        while(indice_< _until) {
            AbsPlainLabel etiquette2_ = etiquette(indice_);
            _g.add(etiquette2_,WindowCardsCore.ctsRem(window.getCompoFactory()));
            indice_++;
        }
        return indice_;
    }

    private int updateHands() {
        int nombreJoueurs_ = nbPlayers();
        for(int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
            Carpet.add(window.getCompoFactory(), hands, buildHand(list(joueur_)), true);
        }
        return nombreJoueurs_;
    }

    private AbsPanel buildHand(CustList<T> _hand) {
//        TranslationsLg lg_ = window.getFrames().currentLg();
//        AbsPanel sousPanneau4_= window.getCompoFactory().newLineBox();
//        for (GraphicCard<T> c: new ContainerSingUtil<T>(converter).getGraphicCardsGene(window, lg_, _hand)) {
//            sousPanneau4_.add(c.getPaintableLabel());
//        }
        return new ContainerSingUtil<T>(converter).getGraphicCardsGenePanel(window.getFrames(),_hand);
//        return sousPanneau4_;
    }

    private void updateBots(int _nb) {
        int nbBots_ = _nb - 1;
        for(int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbBots_; joueur_++) {
            AbsPanel line_ = window.getCompoFactory().newLineBox();
            AbsPlainLabel lab_ = WindowCards.whiteLabel(window, 1);
            lab_.setOpaque(false);
            line_.add(lab_);
            Carpet.add(window.getCompoFactory(), hands, line_, true);
        }
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
