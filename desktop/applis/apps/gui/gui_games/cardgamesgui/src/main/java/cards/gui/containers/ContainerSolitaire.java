package cards.gui.containers;

import cards.facade.enumerations.*;
import cards.gui.*;
import cards.gui.containers.events.*;
import cards.gui.labels.*;
import cards.main.*;
import cards.solitaire.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.files.*;
import code.gui.images.*;
import code.scripts.messages.cards.*;
import code.sml.util.*;
import code.util.*;

public final class ContainerSolitaire extends ContainerSingleImpl {
    private final WindowCards win;
    private int group = -1;
    private int card = -1;
    private SolitaireType solitaireType;
    private AbsPanel all;
    private AbsButton nextDe;

    public ContainerSolitaire(WindowCards _window) {
        super(_window);
        win = _window;
    }

    public void load() {
        setSolitaireType(partieSolitaire().type());
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabled(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabled(true);
        //Desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
        win.changeMenuSimuEnabled(false);
//        setPasse(false);
//        getPaused().set(PAUSE_ALIVE);
//        win.getPausingCardsAnims().alive(this);
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
        getConsulting().setEnabled(false);
//        setChangerPileFin(false);
//        setaJoueCarte(false);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        getOwner().setTitle(GameEnum.CLASSIC.toString(lg_));
        placerSolitaire();
    }

    public void modify() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabled(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabled(true);
        //Activer les conseils
        getConsulting().setEnabled(false);
        getPar().jouerSolitaire(getOwner().baseWindow().getFirstDealSolitaire().deal(this, getSolitaireType()));
        placerSolitaire();
    }
    public void editerSolitaire(AbsDealSolitaire _partie) {
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabled(false);
//        setPasse(false);
//        getPaused().set(PAUSE_ALIVE);
//        win.getPausingCardsAnims().alive(this);
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
//        setaJoueCarte(false);
//        setPartieSauvegardee(false);
        getPar().jouerSolitaire(_partie);
        //Desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
        win.changeMenuSimuEnabled(false);
        setChangerPileFin(false);
        placerSolitaire();
    }
    private void placerSolitaire() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabled(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabled(true);
        //Activer les conseils
        getConsulting().setEnabled(true);
        //Desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
        win.changeMenuSimuEnabled(false);
        AbsDealSolitaire partie_=partieSolitaire();
        partie_.fwd();
        placerIhmSolitaire();
    }
    public void placerIhmSolitaire() {
        if (partieSolitaire().finish()) {
            finPartieSolitaire();
            return;
        }
        getPane().removeAll();
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        container_.add(getOwner().getCompoFactory().newPlainLabel(helpMenuTip()), MessagesGuiFct.BORDER_LAYOUT_NORTH);
        AbsDealSolitaire partie_=partieSolitaire();
        CustList<HandSolitaire> hands_ = partie_.getHands();
        int len_ = hands_.size();
        all = getOwner().getCompoFactory().newLineBox();
        AbsPanel line_= all;
        for (int i = 0; i < len_; i++) {
            AbsPanel sousPanneau4_= getOwner().getFrames().getCompoFactory().newPageBox();
            IdList<CardSolitaire> cards_ = hands_.get(i).getCards();
            CustList<SolitaireGraphicCard> groupCards_ = ContainerSolitaireUtil.getGraphicCardsGene(getOwner().getFrames(), lg_, cards_, i == group, card);
            int count_ = groupCards_.size();
            for (int j = 0; j < count_; j++) {
                SolitaireGraphicCard c = groupCards_.get(j);
                addEvent(c.getPaintableLabel(),new RefreshSolitaireEvent(this, i,j),i,j);
                c.getPaintableLabel().top();
                sousPanneau4_.add(c.getPaintableLabel());
            }
            if (cards_.isEmpty()) {
                AbsPaintableLabel lab_ = getOwner().getFrames().getCompoFactory().newAbsPaintableLabel();
                lab_.addMouseListener(new RefreshSolitaireEvent(this, i,0));
                MetaDimension dim_ = SolitaireGraphicCard.getDimension(false);
                lab_.setPreferredSize(dim_);
                AbstractImageFactory img_ = getOwner().getFrames().getImageFactory();
                AbstractImage icon_ = img_.newImageArgb(dim_.getWidth(), dim_.getHeight());
                icon_.setColor(GuiConstants.WHITE);
                icon_.fillRect(0,0,dim_.getWidth(), dim_.getHeight());
                icon_.setColor(GuiConstants.BLACK);
                icon_.drawRect(0,0,dim_.getWidth()-1, dim_.getHeight()-1);
                lab_.setIcon(img_, icon_);
                lab_.top();
                sousPanneau4_.add(lab_);
            }
            sousPanneau4_.top();
            line_.add(sousPanneau4_);
        }
        container_.add(line_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
        container_.add(panelHand(), MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newAbsScrollPane(container_));
        panel_.add(getWindow().getClock().getComponent());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
        pack();
    }
    private void addEvent(AbsPaintableLabel _lab, AbsMouseListenerIntRel _evt, int _i, int _j) {
        if (getGroup() == -1) {
            if (partieSolitaire().canBeSelected(_i, _j)) {
                _lab.addMouseListener(new CardsNonModalEvent(win),_evt);
            }
            return;
        }
        if (getGroup() == _i && getCard() == _j || partieSolitaire().canBePlayed(getGroup(), getCard(), _i)) {
            _lab.addMouseListener(new CardsNonModalEvent(win),_evt);
        }
    }
    public void finPartieSolitaire() {
        getPane().removeAll();
        /*Descativer aide au jeu*/
        getHelpGame().setEnabled(false);
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
        getConsulting().setEnabled(false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
//        setThreadAnime(false);
        win.changeStreamsMenusEnabled(true);
        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
        container_.add(onglets_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=getOwner().getCompoFactory().newPageBox();
        panneau_.add(getOwner().getCompoFactory().newPlainLabel(Long.toString(partieSolitaire().getActions().size())));
        AbsPanel buttons_ = getOwner().getCompoFactory().newLineBox();
        nextDe = getOwner().getCompoFactory().newPlainButton(file().getVal(MessagesGuiCards.MAIN_KEEP_PLAYING_DEAL));
        AbsButton bouton_= nextDe;
        bouton_.addActionListener(new CardsNonModalEvent(win),new NextSolitaireEvent(this));
        buttons_.add(bouton_);
//        _single.setNextDeal(bouton_);
//        resultButtons(buttons_,this);
        AbsButton stop_=getOwner().getCompoFactory().newPlainButton(file().getVal(MessagesGuiCards.MAIN_STOP));
        stop_.addActionListener(new CardsNonModalEvent(win),new StopPlayingEvent(win));
        buttons_.add(stop_);
        // bouton_.addActionListener(new CardsNonModalEvent(_cont),new StopPlayingEvent(_cont.window()));
        panneau_.add(buttons_);
        panneau_.add(getWindow().getClock().getComponent());
        panneau_.add(getWindow().getLastSavedGameDate());
        container_.add(panneau_, MessagesGuiFct.BORDER_LAYOUT_SOUTH);

        setContentPane(container_);
        pack();
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int _g) {
        this.group = _g;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int _c) {
        this.card = _c;
    }

    public AbsDealSolitaire partieSolitaire() {
        return getPar().partieSolitaire();
    }

    public SolitaireType getSolitaireType() {
        return solitaireType;
    }

    public void setSolitaireType(SolitaireType _s) {
        this.solitaireType = _s;
    }

    public AbsPanel getAll() {
        return all;
    }

    public AbsButton getNextDe() {
        return nextDe;
    }
}
