package cards.gui.containers;






import cards.belote.*;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.*;
import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.*;
import cards.gui.animations.*;
import cards.gui.containers.events.BidEvent;
import cards.gui.containers.events.EndDealEvent;
import cards.gui.containers.events.FoldEvent;
import cards.gui.containers.events.NextTrickEvent;
import cards.gui.containers.events.ReplayEvent;
import cards.gui.containers.events.StopPlayingEvent;
import cards.gui.dialogs.*;
import cards.gui.events.ListenerBidBeloteSingle;
import cards.gui.events.ListenerCardBeloteSingleGame;
import cards.gui.events.SelectPointsEvent;
import cards.gui.events.SelectSuitEvent;
import cards.gui.labels.*;
import cards.gui.panels.CarpetBelote;
import cards.gui.panels.MiniCarpet;
import cards.gui.panels.PanelTricksHandsBelote;
//import cards.network.common.select.TeamsPlayers;
import cards.main.CardNatLgNamesNavigation;
import cards.main.CardsNonModalEvent;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class ContainerSingleBelote extends ContainerBelote implements ContainerSingle,ContainerPlayableBelote {

    private BidBeloteSuit contratUtilisateurBelote = new BidBeloteSuit();
//    private boolean annonceBelote;
//    private boolean annonceBeloteRebelote;

//    private boolean clickedBid;
//    private boolean clickedPass;
    private final WindowCards win;

    public ContainerSingleBelote(WindowCards _window) {
        super(_window);
        win = _window;
    }
    public HandBelote userHand() {
        GameBelote partie_=partieBelote();
        //Les regles de la belote ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        return partie_.mainUtilisateurTriee(getDisplayingBelote());
    }
    public GameBelote partieBelote() {
        return getPar().partieBelote();
    }
    public void jouerBelote(byte _joueur, String _pseudo) {
        GameBelote partie_=partieBelote();
        CardBelote ct_=getOwner().baseWindow().getIa().getBelote().strategieJeuCarteUnique(partie_);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if(partie_.annoncerBeloteRebelote(_joueur,ct_)) {
            partie_.setAnnoncesBeloteRebelote(_joueur,ct_);
            getOwner().getCompoFactory().invokeNow(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toStringBeloteReb(lg_),RETURN_LINE)));
//            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),, getOwner().getFrames());
//            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+DeclaresBeloteRebelote.BELOTE_REBELOTE+RETURN_LINE_CHAR);
        }
        if (partie_.premierTour()) {
            partie_.annoncer(_joueur);
            DeclareHandBelote usDecl_ = partie_.getAnnonce(_joueur);
            getOwner().getCompoFactory().invokeNow(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(usDecl_.getDeclare(),lg_),RETURN_LINE)));
//            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),, getOwner().getFrames());
//            ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+usDecl_.getAnnonce()+RETURN_LINE_CHAR);
            if(!usDecl_.getHand().estVide()) {
                AbsPlainLabel label_ = getHandfuls().getVal(_joueur);
                getOwner().getCompoFactory().invokeNow(new SettingText(label_, Games.toString(usDecl_.getDeclare(),lg_)));
//                ThreadInvoker.invokeNow(getOwner().getThreadFactory(),, getOwner().getFrames());
//                getHandfuls().getVal(_joueur).setText(usDecl_.getAnnonce().toString());
            }
            usDecl_.getHand().trier(getDisplayingBelote(), partie_.getBid());

            AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
            getOwner().getCompoFactory().invokeNow(new DeclaringThread(panelToSet_, usDecl_, getOwner()));
//            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),, getOwner().getFrames());
//            panelToSet_.removeAll();
//            for(CardBelote c: usDecl_.getMain())
//            {
//                MiniBeloteCard carte_=new MiniBeloteCard(c);
//                panelToSet_.add(carte_);
//            }
        }
        partie_.jouer(_joueur,ct_);
        partie_.ajouterUneCarteDansPliEnCours(ct_);
        tapisBelote().setCarteBelote(getWindow().getImageFactory(),lg_,_joueur,ct_);
    }
    /**Met en place l'ihm pour l'utilisateur lorsqu'une partie est editee ou chargee d'un fichier*/

    public void load() {
        //Activer le menu Fichier/Sauvegarder
        MenuItemUtils.setEnabledMenu(getSave(),true);
        //Activer le menu Fichier/Changer de mode
        MenuItemUtils.setEnabledMenu(getChange(),true);
        //Desactiver le menu Partie/Demo
        MenuItemUtils.setEnabledMenu(getDemo(),false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        setChangerPileFin(false);
//        setaJoueCarte(false);
        byte nombreDeJoueurs_;
        GameBelote partie_=partieBelote();
        nombreDeJoueurs_=partie_.getNombreDeJoueurs();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        getOwner().setTitle(GameEnum.BELOTE.toString(lg_));
        placerBelote();
//        pack();
        StringList pseudos_=pseudosBelote();
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        //Desactiver les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
        for(BidBeloteSuit b: partie_.tousContrats()) {
            String pseudo_ = pseudos_.get(player_);
            ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
            player_ = partie_.playerAfter(player_);
        }
//        if(partie_.keepBidding()) {
//            //Desactiver les conseils
//            MenuItemUtils.setEnabledMenu(getConsulting(),false);
//            afficherMainUtilisateurBelote(false);
//            byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
//            for(BidBeloteSuit b: partie_.tousContrats()) {
//                String pseudo_ = pseudos_.get(player_);
//                ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
//                player_ = partie_.playerAfter(player_);
//            }
////            byte debut_= partie_.playerHavingToBid();
////            if(debut_ != DealBelote.NUMERO_UTILISATEUR) {
////                thread(new AnimationBidBelote(this));
////            } else {
////                MenuItemUtils.setEnabledMenu(getConsulting(),true);
////                bidButtons();
////                pack();
////                if(partie_.keepBidding()) {
////                    //Activer les conseils
////                    MenuItemUtils.setEnabledMenu(getConsulting(),true);
////                    bidButtons();
////                } else if(partie_.getBid().jouerDonne()) {
////                    getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, partie_.getPreneur());
////                    getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, partie_.getTeamsRelation().partenaires(partie_.getPreneur()).first());
////                    addButtonNextTrickBelote(file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
////                } else {
////                    addButtonEndDealBelote(file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
////                }
////            }
////            return;
//        }
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        if (partie_.noPlayed()) {
            HandBelote stack_ = new HandBelote();
            stack_.ajouterCartes(partie_.getDeal().derniereMain());
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreDeJoueurs_; joueur_++) {
                partie_.getDeal().hand(joueur_).supprimerCartes(stack_);
            }
            afficherMainUtilisateurBelote(false);
            AfterAnimationBidBelote.buttons(this);
//            if (!partie_.getRegles().dealAll() && partie_.getDistribution().hand().total() == partie_.getRegles().getDealing().getNombreCartesParJoueur()) {
////                TrickBelote pliEnCours_=partie_.getPliEnCours();
////                for(byte p:pliEnCours_.playersHavingPlayed(nombreDeJoueurs_)) {
////                    tapisBelote().setCarteBelote(getWindow().getImageFactory(),lg_,p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
////                }
////                if (!partie_.keepPlayingCurrentGame()) {
////                    finPartieBelote();
////                    pack();
////                    return;
////                }
//                thread(new AnimationCardBelote(this));
//            } else
//            if(partie_.getBid().jouerDonne()) {
//                getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, partie_.getPreneur());
//                getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, partie_.getTeamsRelation().partenaires(partie_.getPreneur()).first());
//                addButtonNextTrickBelote(file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
//                pack();
//            } else {
//                addButtonEndDealBelote(file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
//                pack();
//            }
            return;
        }
        if (!partie_.keepPlayingCurrentGame()) {
            finPartieBelote();
            pack();
            return;
        }
        TrickBelote pliEnCours_=partie_.getPliEnCours();
        for(byte p:pliEnCours_.playersHavingPlayed(nombreDeJoueurs_)) {
            tapisBelote().setCarteBelote(getWindow().getImageFactory(),lg_,p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
        }
        afficherMainUtilisateurBelote(false);
        pack();
        thread(new AnimationCardBelote(this));
    }

    public void bidButtons() {
        GameBelote partie_=partieBelote();
        BidBeloteSuit contrat_= partie_.getBid();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if (!partie_.getRegles().dealAll()) {
            for(BidBeloteSuit e: partie_.getGameBeloteBid().allowedBids()) {
                ajouterBoutonContratBelote(Games.toString(e, lg_),e,e.estDemandable(contrat_));
            }
        } else {
            addButtonsForCoinche(partie_);
        }
    }

    public void addButtonsForCoinche(GameBelote _partie) {
        Ints points_ = RulesBelote.getPoints();
        int size_ = points_.size();
        setPanneauBoutonsJeuPoints(getOwner().getCompoFactory().newGrid());
        getPointsButtons().clear();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (int i = 0; i < size_; i++) {
            int p_ = points_.get(i);
            LabelPoints label_ = new LabelPoints(p_, getOwner().getCompoFactory());
            label_.setEnabledLabel(_partie.getBid().getPoints() < p_);
            label_.setToolTipText(Long.toString(p_));
            label_.getButton().addActionListener(new CardsNonModalEvent(this),new SelectPointsEvent(this, p_));
            getPointsButtons().add(label_);
            getPanneauBoutonsJeuPoints().add(label_.getButton(),WindowCardsCore.ctsRem(getWindow().getCompoFactory(),(i+1)%3==0));
        }
        getPanneauBoutonsJeu().add(getPanneauBoutonsJeuPoints());
//        clickedBid = false;
//        clickedPass = false;
        setBidOk(getOwner().getCompoFactory().newPlainButton(file().getVal(MessagesGuiCards.MAIN_OK)));
        getBidOk().setEnabled(false);
        getBidOk().addActionListener(new CardsNonModalEvent(this),new BidEvent(this));
        AbsPanel panel_ = getOwner().getCompoFactory().newGrid();
        getBidsButtons().clear();
        getBids().clear();
        CustList<BidBeloteSuit> bidsAll_ = GameBeloteBid.baseBidsDealAll(_partie.getRegles().getListeEncheresAutorisees());
        for (BidBeloteSuit b: bidsAll_) {
            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
            suitLabel_.setSuit(b, lg_);
            suitLabel_.addMouseListener(new SelectSuitEvent(this,b));
            panel_.add(suitLabel_.getPaintableLabel(),WindowCardsCore.ctsRem(getWindow().getCompoFactory(),(panel_.getComponentCount()+1)%4==0 || (panel_.getComponentCount()+1)%bidsAll_.size()==0));
            getBidsButtons().add(suitLabel_);
            getBids().add(b);
        }
//        AbsPanel panelSuits_ = getOwner().getCompoFactory().newLineBox();
//        for (Suit s: Suit.couleursOrdinaires()) {
//            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
//            BidBeloteSuit bid_ = new BidBeloteSuit();
//            bid_.setSuit(s);
//            bid_.setBid(BidBelote.SUIT);
//            suitLabel_.setSuit(bid_, lg_);
//            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
//            panelSuits_.add(suitLabel_.getPaintableLabel());
//            getBidsButtons().add(suitLabel_);
//        }
//        panel_.add(panelSuits_);
//        AbsPanel panelBids_ = getOwner().getCompoFactory().newLineBox();
//        for (BidBelote b: BidBelote.getNonZeroBids()) {
//            if (b.getCouleurDominante()) {
//                continue;
//            }
//            if (_partie.getRegles().getAllowedBids().getVal(b) != BoolVal.TRUE) {
//                continue;
//            }
//            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
//            BidBeloteSuit bid_ = new BidBeloteSuit();
//            bid_.setBid(b);
//            suitLabel_.setSuit(bid_, lg_);
//            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
//
//            panelBids_.add(suitLabel_.getPaintableLabel());
//            getBidsButtons().add(suitLabel_);
//        }
//        panel_.add(panelBids_);
        AbsButton buttonSuit_ = getOwner().getCompoFactory().newPlainButton(Games.toString(BidBelote.FOLD,lg_));
        //clickedTwo = false;
        buttonSuit_.addActionListener(new CardsNonModalEvent(this),new FoldEvent(this));
        panel_.add(buttonSuit_,WindowCardsCore.cts(getWindow().getCompoFactory()));
        setFold(buttonSuit_);
        panel_.add(getBidOk(),WindowCardsCore.cts(getWindow().getCompoFactory()));
        getPanneauBoutonsJeu().add(panel_);
    }
    @Override
    public void bid() {
//        if (clickedBid) {
//            return;
//        }
//        clickedBid = true;
//        if (!isCanBid()) {
//            return;
//        }
//        setCanBid(false);
        clearBids();
        contratUtilisateurBelote=new BidBeloteSuit();
        contratUtilisateurBelote.setSuit(getSuit());
        contratUtilisateurBelote.setBid(getBidType());
        contratUtilisateurBelote.setPoints(getPts());
        thread(new AnimationBidBelote(this));
    }

    @Override
    public void fold() {
//        if (clickedPass) {
//            return;
//        }
//        clickedPass = true;
//        if (!isCanBid()) {
//            return;
//        }
//        setCanBid(false);
        clearBids();
        contratUtilisateurBelote=new BidBeloteSuit();
        thread(new AnimationBidBelote(this));
    }
    public void ajouterBoutonContratBelote(String _texte,BidBeloteSuit _action,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratBelote(_action));
        bouton_.addActionListener(new CardsNonModalEvent(this),new ListenerBidBeloteSingle(this,_action));
        bouton_.setEnabled(_apte);
        if (!_apte) {
            TranslationsLg lg_ = getOwner().getFrames().currentLg();
            bouton_.setToolTipText(StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_BID), Games.toString(_action,lg_)));
        }
        panneau_.add(bouton_);
        getBids().add(_action);
    }
    public void addButtonNextTrickBelote(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(this),new NextTrickEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonEndDealBelote(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(this),new EndDealEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
//    private void addButtonKeepPlayingDealBelote(AbsPanel _panneau,String _texte) {
//        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new CardsNonModalEvent(this),new KeepPlayingRandomEvent(this));
//        _panneau.add(bouton_);
//    }
//    private void addButtonKeepPlayingEditedDealBelote(AbsPanel _panneau,String _texte) {
//        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new CardsNonModalEvent(this),new KeepPlayingEditedEvent(this));
//        _panneau.add(bouton_);
//    }
    private void addButtonStopPlayingBelote(AbsPanel _panneau,String _texte) {
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(this),new StopPlayingEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonReplayDealBelote(AbsPanel _panneau,String _texte) {
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(this),new ReplayEvent(this));
        _panneau.add(bouton_);
    }
    public void placerBoutonsAvantJeuUtilisateurBelote() {
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
//        setRaisonCourante(EMPTY);
        GameBelote partie_=partieBelote();
        afficherMainUtilisateurBelote(true);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        AbsCustCheckBox belReb_ = getBeloteRebelote();
        belReb_.setSelected(false);
        if(!partie_.cartesBeloteRebelote().estVide()) {
//            annonceBeloteRebelote = false;
            AbsPanel panneau_ =getPanneauBoutonsJeu();
            belReb_.setText(Games.toStringBeloteReb(lg_));
            belReb_.setEnabled(partie_.autoriseBeloteRebelote());
//            belReb_.addActionListener(new ChangeBeloteRebeloteEvent(this));
            panneau_.add(belReb_);
        }
        AbsCustCheckBox beloteDeclare_ = getBeloteDeclare();
        beloteDeclare_.setSelected(false);
        if(partie_.premierTour()) {
            DeclareHandBelote annonceMain_ = partie_.strategieAnnonces();
            if(annonceMain_.getDeclare() != DeclaresBelote.UNDEFINED) {
//                annonceBelote = false;
                AbsPanel panneau_ =getPanneauBoutonsJeu();
//                AbsCustCheckBox caseCoche_ = getOwner().getCompoFactory().newCustCheckBox(StringUtil.concat(Games.toString(annonceMain_.getDeclare(),lg_),INTRODUCTION_PTS,Games.toString(annonceMain_.getHand(),lg_)));
                beloteDeclare_.setText(StringUtil.concat(Games.toString(annonceMain_.getDeclare(),lg_),INTRODUCTION_PTS,Games.toString(annonceMain_.getHand(),lg_)));
//                caseCoche_.addActionListener(new ChangeBeloteDeclareEvent(this));
                panneau_.add(beloteDeclare_);
            }
        }
    }

    public void placerBoutonsFinPliUtilisateurBelote() {
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        GameBelote partie_=partieBelote();
        if(!partie_.keepPlayingCurrentGame()) {
            addButtonEndDealBelote(file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
        } else {
            addButtonNextTrickBelote(file().getVal(MessagesGuiCards.MAIN_NEXT_TRICK), true);
        }
    }

    public void editerBelote(GameBelote _partie) {
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
//        setaJoueCarte(false);
        setPartieSauvegardee(false);
        getPar().jouerBelote(_partie);
        //Desactiver le menu Partie/Demo
        MenuItemUtils.setEnabledMenu(getDemo(),false);
        setChangerPileFin(false);
        mettreEnPlaceIhmBelote();
    }
    private void placerBelote() {
        //Activer le menu Fichier/Sauvegarder
        MenuItemUtils.setEnabledMenu(getSave(),true);
        //Activer le menu Fichier/Changer de mode
        MenuItemUtils.setEnabledMenu(getChange(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        //Desactiver le menu Partie/Demo
        MenuItemUtils.setEnabledMenu(getDemo(),false);
        placerIhmBelote();
    }
    private void placerIhmBelote() {
        getPane().removeAll();
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(helpMenuTip()),GuiConstants.BORDER_LAYOUT_NORTH);
        GameBelote partie_=partieBelote();
        StringList pseudos_ = pseudosBelote();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        CarpetBelote tapis_ = CarpetBelote.initTapisBelote(lg_, getDisplayingBelote().getDisplaying().isClockwise(), 1, getOwner().getCompoFactory());
        getTapis().setTapisBelote(tapis_);
        container_.add(tapis_.getContainer(),GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_;
        panneau_= getOwner().getCompoFactory().newLineBox();
        panneau_.setBackground(GuiConstants.BLUE);
        setPanelHand(panneau_);
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=getOwner().getCompoFactory().newPageBox();
        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
        setMini(MiniCarpet.newCarpet(getWindow().getImageFactory(),partie_.getNombreDeJoueurs(), getDisplayingBelote().getDisplaying().isClockwise(),pseudos_, getOwner().getCompoFactory()));
        panneau2_.add(getMiniPanel());
        setHandfuls(new ByteMap<AbsPlainLabel>());
        setDeclaredHandfuls(new ByteMap<AbsPanel>());
        AbsPanel declaredHandfuls_ = getOwner().getCompoFactory().newPageBox();
        int nbPlayers_ = partie_.getNombreDeJoueurs();
        for (byte i = IndexConstants.FIRST_INDEX; i<nbPlayers_; i++) {
            AbsPanel declaredHandfulGroup_ = getOwner().getCompoFactory().newLineBox();
            AbsPlainLabel lab_ = getOwner().getCompoFactory().newPlainLabel(pseudos_.get(i));
            declaredHandfulGroup_.add(lab_);
            AbsPlainLabel handful_ = getOwner().getCompoFactory().newPlainLabel(EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            getHandfuls().put(i, handful_);
            AbsPanel declaredHandful_ = getOwner().getCompoFactory().newLineBox();
            declaredHandfulGroup_.add(declaredHandful_);
            getDeclaredHandfuls().put(i, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);
        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
        if (!partie_.getDistribution().derniereMain().estVide()) {
            tapisBelote().setTalonBelote(getWindow(),lg_,partie_.getDistribution().derniereMain());
        }
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newAbsScrollPane(container_));
        panel_.add(getWindow().getClock());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }
    private void mettreEnPlaceIhmBelote() {
        placerBelote();
        afficherMainUtilisateurBelote(false);
//        pack();
        AfterAnimationBidBelote.buttons(this);
//        GameBelote partie_=partieBelote();
//        byte debut_= partie_.playerHavingToBid();
//        if(debut_ != DealBelote.NUMERO_UTILISATEUR) {
//            thread(new AnimationBidBelote(this));
//        } else {
//            bidButtons();
//            pack();
//        }
    }
    @Override
    public void modify() {
        //Activer le menu Fichier/Sauvegarder
        MenuItemUtils.setEnabledMenu(getSave(),true);
        //Activer le menu Fichier/Changer de mode
        MenuItemUtils.setEnabledMenu(getChange(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        HandBelote pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        pile_ = chargerPileBelote();
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.BELOTE, getOwner().getFrames(), 0);
        DealBelote donne_;
        if(nb_==0||!getPar().enCoursDePartie()) {
            setChangerPileFin(true);
            donne_=new DealBelote(nb_);
            donne_.setRandomDealer(getReglesBelote().getDealing().getId().getNombreJoueurs(),getOwner().getGenerator());
            donne_.initDonne(getReglesBelote(),getDisplayingBelote(),getOwner().getGenerator(),pile_);
            getPar().jouerBelote(new GameBelote(GameType.RANDOM,donne_,getReglesBelote()));
        } else {
            GameBelote partie_=partieBelote();
            donne_=new DealBelote(nb_);
            donne_.donneurSuivant(partie_.getDistribution().getDealer(),partie_.getNombreDeJoueurs());
            donne_.initDonne(partie_.getRegles(),getDisplayingBelote(),getOwner().getGenerator(),partie_.empiler());
            getPar().jouerBelote(new GameBelote(GameType.RANDOM,donne_,partie_.getRegles()));
        }
        mettreEnPlaceIhmBelote();
    }
    private void debutPliBelote() {
        //Activer le sous-menu conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        //Activer le sous-menu aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        StringList pseudos_=pseudosBelote();
        GameBelote partie_=partieBelote();
        /*Si on n'a pas encore fait de pli a la belote*/
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if(partie_.premierTour()) {
            partie_.completerDonne();
            if (!partie_.getDistribution().derniereMain().estVide()) {
                tapisBelote().retirerCartes();
            }
//            if (partie_.getRegles().dealAll()) {
//                int pts_ = partie_.getBid().getPoints();
//                if (pts_ >= HandBelote.pointsTotauxDixDeDer(partie_.getBid())) {
//                    partie_.setEntameur(partie_.getPreneur());
//                }
//            }
            if(partie_.getBid().getCouleurDominante()) {
                Suit couleurAtout_=partie_.couleurAtout();
                ajouterTexteDansZone(StringUtil.concat(pseudos_.get(partie_.getPreneur()),INTRODUCTION_PTS,Games.toString(couleurAtout_,lg_),RETURN_LINE));
            } else {
                ajouterTexteDansZone(StringUtil.concat(pseudos_.get(partie_.getPreneur()),INTRODUCTION_PTS,Games.toString(partie_.getBid(),lg_),RETURN_LINE));
            }
        }
        /*On affiche la main de l'utilisateur avec des ecouteurs sur les cartes et on supprime tous les boutons de l'ihm places a droite avant d'executer un eventuel Thread*/
        clearBids();
        AbsPanel panneau_=getPanneauBoutonsJeu();
        panneau_.validate();
//        setRaisonCourante(getMessages().getVal(WindowCards.WAIT_TURN));
        setThreadAnime(true);
        thread(new AnimationCardBelote(this));


    }

    private void afficherMainUtilisateurBelote(boolean _ecouteur) {
        if (!_ecouteur) {
            setCarteEntree(false);
            setCarteSortie(false);
        }
        //Les regles de la belote ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        HandBelote mainUtilisateur_=userHand();
        /*On place les cartes de l'utilisateur*/
        setCanPlay(_ecouteur);
        updateCardsInPanelBelote(getPanelHand(),mainUtilisateur_);
        getWindow().pack();
    }
    public void finPliBelote(CardBelote _carteJouee, boolean _belReb) {
        CardBelote played_ = getOwner().baseWindow().getIa().getBelote().strategieJeuCarteUniqueUser(_carteJouee);
        setCanPlay(false);
        //Activer le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),true);
        //Desactiver le sous-menu conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        GameBelote partie_=partieBelote();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if(partie_.premierTour()) {
            if(getBeloteDeclare().isSelected()) {
                partie_.annoncer(DealBelote.NUMERO_UTILISATEUR);
            }
//            if(getBeloteDeclare().isSelected()) {

                DeclareHandBelote usDecl_ = partie_.getAnnonce(DealBelote.NUMERO_UTILISATEUR);
                ajouterTexteDansZone(StringUtil.concat(pseudo(),INTRODUCTION_PTS,Games.toString(usDecl_.getDeclare(),lg_),RETURN_LINE));
                if(!usDecl_.getHand().estVide()) {
                    getHandfuls().getVal(DealBelote.NUMERO_UTILISATEUR).setText(Games.toString(usDecl_.getDeclare(),lg_));
                }
                AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(DealBelote.NUMERO_UTILISATEUR);
                panelToSet_.removeAll();
                for (GraphicBeloteCard c: getGraphicCards(getWindow(),lg_,usDecl_.getHand().getCards())) {
                    panelToSet_.add(c.getPaintableLabel());
                }
                panelToSet_.validate();
//                boolean entered_ = false;
//                for(CardBelote c: usDecl_.getMain())
//                {
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(c,SwingConstants.RIGHT,!entered_);
//                    carte_.setPreferredSize(entered_);
//                    panelToSet_.add(carte_);
//                    entered_ = true;
//                }
//            }
        }
        /*L'utilisateur joue sa carte*/
        partie_.jouer(played_);
        partie_.ajouterUneCarteDansPliEnCours(played_);
        if (_belReb) {
            partie_.setAnnoncesBeloteRebelote(DealBelote.NUMERO_UTILISATEUR,played_);
            ajouterTexteDansZone(StringUtil.concat(pseudo(),INTRODUCTION_PTS,Games.toStringBeloteReb(lg_),RETURN_LINE));
        }
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
//        setRaisonCourante(getMessages().getVal(WindowCards.END_TRICK));
        afficherMainUtilisateurBelote(false);
        tapisBelote().setCarteBelote(getWindow().getImageFactory(),lg_,DealBelote.NUMERO_UTILISATEUR,played_);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        clearBids();
        thread(new AnimationCardBelote(this));
        setThreadAnime(true);

    }

    public void finPartieBelote() {
        getPane().removeAll();
        /*Descativer aide au jeu*/
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        if(isChangerPileFin()) {
            GameBelote partie_=partieBelote();
            getOwner().baseWindow().getFacadeCards().getNicknamesCrud().getCardGamesCrud().belote(partie_.empiler());
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        StringList pseudos_=new StringList(pseudosBelote());
        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
        GameBelote partie_=partieBelote();
        if(partie_.getType()==GameType.RANDOM) {
            setPartieAleatoireJouee(true);
            if(isChangerPileFin()) {
                changerNombreDeParties(GameEnum.BELOTE, partie_.getDistribution().getNbDeals(), getOwner().getFrames(),0);
            }
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(partie_);
        res_.getRes().setUser(DealBelote.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(pseudos_), getScores());
        res_.getRes().setGeneral(readCoreResourceSuit());
        res_.getRes().setSpecific(readResource());
        Games.setMessages(res_.getRes(),getOwner().getFrames().currentLg());
        setScores(res_.getRes().getScores());
        RenderedPage editor_;
        CardNatLgNamesNavigation sOne_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE).attendreResultat();
        ((BeloteStandards)sOne_.getBeanNatLgNames()).setDataBase(res_);
        editor_ = FrameGeneralHelp.initialize(sOne_, getOwner().getFrames());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_RESULTS_PAGE),editor_.getScroll());
        if(partie_.getBid().jouerDonne()) {
            CardNatLgNamesNavigation sTwo_ = retrieve(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE).attendreResultat();
            ((BeloteStandards)sTwo_.getBeanNatLgNames()).setDataBase(res_);
            editor_ = FrameGeneralHelp.initialize(sTwo_, getOwner().getFrames());
            editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
            onglets_.add(file().getVal(MessagesGuiCards.MAIN_DETAIL_RESULTS_PAGE),editor_.getScroll());
        }
        if(partie_.getType()==GameType.RANDOM) {
            updateGraphicLines(onglets_,res_.getRes(),nombreJoueurs_,pseudos_);
//            Ints couleurs_=couleursCourbes(getOwner().getGenerator());
//            couleurs_.add(GuiConstants.RED);
//            couleurs_.add(GuiConstants.GREEN);
//            couleurs_.add(GuiConstants.BLUE);
//            if(nombreJoueurs_>3) {
//                couleurs_.add(GuiConstants.YELLOW);
//            }
//            if(nombreJoueurs_>4) {
//                couleurs_.add(GuiConstants.MAGENTA);
//            }
//            if(nombreJoueurs_>5) {
//                couleurs_.add(GuiConstants.CYAN);
//            }
//            if(nombreJoueurs_>6) {
//                couleurs_.add(GuiConstants.ORANGE);
//            }
//            if(nombreJoueurs_>7) {
//                couleurs_.add(GuiConstants.newColor(128,64,0));
//            }
//            if(nombreJoueurs_>8) {
//                couleurs_.add(GuiConstants.newColor(128,128,0));
//            }
//            if(nombreJoueurs_>9) {
//                couleurs_.add(GuiConstants.newColor(128,0,255));
//            }
//            Graphic graphique_=new Graphic(getScores(),new Longs(res_.getRes().getSums()),new CustList<Rate>(res_.getRes().getSigmas()),couleurs_, getOwner().getCompoFactory());
//            Rate derniereMoyenne_=new Rate(res_.getRes().getSums().last(),nombreJoueurs_);
//            CustList<Rate> scoresCentresMoyenne_=new CustList<Rate>();
//            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
//                scoresCentresMoyenne_.add(Rate.minus(new Rate(getScores().last().get(joueur_)), derniereMoyenne_));
//            }
//            scoresCentresMoyenne_.add(Rate.multiply(new Rate(3), res_.getRes().getSigmas().last()));
//            Rate max_ = Rate.zero();
//            for(Rate maximum_:scoresCentresMoyenne_) {
//                if (Rate.strGreater(maximum_.absNb(), max_)) {
//                    max_ = maximum_.absNb();
//                }
//            }
//            setMaxAbsoluScore(NumberUtil.max(max_.ll(),getMaxAbsoluScore()));
//            int dimy_=(int)getMaxAbsoluScore();
//            graphique_.setPreferredSize(new MetaDimension(2000,dimy_));
//            AbsScrollPane locScroll_ = getOwner().getCompoFactory().newAbsScrollPane(graphique_.getPaintableLabel());
//            graphique_.setLocation(0,(600-dimy_)/2);
//            AbsMetaLabelCard.paintCard(getWindow().getImageFactory(),graphique_);
//            locScroll_.setPreferredSize(new MetaDimension(300,200));
//            AbsPanel panneau_=getOwner().getCompoFactory().newBorder();
//            panneau_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowCards.SCORES_EVOLUTION_DETAIL)),GuiConstants.BORDER_LAYOUT_NORTH);
//            panneau_.add(locScroll_,GuiConstants.BORDER_LAYOUT_CENTER);
//            GraphicKey legende_=new GraphicKey(pseudos_,couleurs_, lg_, getOwner().getCompoFactory());
//            legende_.setPreferredSize(new MetaDimension(300,15*(nombreJoueurs_+1)));
//            AbsMetaLabelCard.paintCard(getWindow().getImageFactory(),legende_);
//            locScroll_=getOwner().getCompoFactory().newAbsScrollPane(legende_.getPaintableLabel());
//            locScroll_.setPreferredSize(new MetaDimension(300,100));
//            panneau_.add(locScroll_,GuiConstants.BORDER_LAYOUT_SOUTH);
//            onglets_.add(getMessages().getVal(WindowCards.SCORES_EVOLUTION),panneau_);
        }
        GameBelote game_ = partieBelote();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(getReglesBelote());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getBid());
        tricksHands_.tricks(game_);
        tricksHands_.sortHands(getDisplayingBelote(), game_.getNombreDeJoueurs());
        WindowCardsInt ow_ = getOwner();
        AbsScrollPane ascenseur_ = getOwner().getCompoFactory().newAbsScrollPane(new PanelTricksHandsBelote(ow_.getCommonFrame(), tricksHands_, nombreJoueurs_, pseudosBelote(), getDisplayingBelote(), ow_).getContainer());
        ascenseur_.setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS),ascenseur_);
        container_.add(onglets_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_ = getOwner().getCompoFactory().newPageBox();
        AbsPanel buttons_ = getOwner().getCompoFactory().newLineBox();
        resultButtons(buttons_,this);
//        GameType type_;
//        long nombreParties_;
//        type_=partie_.getType();
//        nombreParties_=partie_.getNombre();
//        int nombreTotalParties_= partie_.getRegles().getCommon().getNbDeals();
//        if(type_==GameType.EDIT&&nombreParties_<nombreTotalParties_) {
//            addButtonKeepPlayingEditedDealBelote(buttons_, file().getVal(MessagesGuiCards.MAIN_KEEP_PLAYING_EDITED_DEAL));
//        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&isPartieAleatoireJouee()||type_==GameType.RANDOM) {
//            addButtonKeepPlayingDealBelote(buttons_, file().getVal(MessagesGuiCards.MAIN_KEEP_PLAYING_DEAL));
//        }
        addButtonReplayDealBelote(buttons_, file().getVal(MessagesGuiCards.MAIN_REPLAY_DEAL));
        addButtonStopPlayingBelote(buttons_, file().getVal(MessagesGuiCards.MAIN_STOP));
        panneau_.add(buttons_);
        panneau_.add(getWindow().getClock());
        panneau_.add(getWindow().getLastSavedGameDate());
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        if(type_!=GameType.EDIT) {
//            partie_.setNombre();
//        }
        partie_.setNombre();
        setContentPane(container_);
    }

    @Override
    public GameType getGameType() {
        return partieBelote().getType();
    }

    @Override
    public long nombreParties() {
        return partieBelote().getNombre();
    }

    @Override
    public long nombreTotalParties() {
        return partieBelote().getRegles().getCommon().getNbDeals();
    }

    /**Pseudos utilis&eacute;s*/
    public StringList pseudosBelote() {
        return pseudosBelote(partieBelote().getNombreDeJoueurs());
    }

    private void updateCardsInPanelBelote(AbsPanel _panel, HandBelote _hand) {
        _panel.removeAll();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        GameBelote game_ = partieBelote();
        HandBelote autorise_;
        if (isCanPlay()) {
            autorise_ = game_.autorise();
        } else {
            autorise_ = new HandBelote();
        }
        for (GraphicBeloteCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            //c.addMouseListener(new ListenerCardBeloteSingleGame(this,c.getCard()));
            if (isCanPlay()) {
                if (autorise_.contient(c.getCard())) {
                    c.addMouseListener(new ListenerCardBeloteSingleGame(this,c.getCard()));
                } else {
                    String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(c.getCard(),lg_));
                    String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,Games.autoriseBelote(game_,lg_));
                    c.getPaintableLabel().setToolTipText(finalMessage_);
                }
            }
            _panel.add(c.getPaintableLabel());
        }
        _panel.validate();
//        boolean entered_ = false;
//        for(CardBelote c: _hand)
//        {
//            GraphicBeloteCard carte_=new GraphicBeloteCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
////            carte_.addMouseListener(new EcouteurCarteBeloteJeu(_hand.carte(indice_)));
//            carte_.addMouseListener(new ListenerCardBeloteSingleGame(this,c));
//            _panel.add(carte_);
//            entered_ = true;
//        }
    }
//    public boolean isAnnonceBeloteRebelote() {
//        return annonceBeloteRebelote;
//    }

    public void showTeams() {
        GameBelote game_ = partieBelote();
        TeamsPlayers teams_ = new TeamsPlayers();
        teams_.setTeams(game_.playersBelongingToSameTeam());
        DialogTeamsPlayers.initDialogTeamsPlayers(getOwner());
        DialogTeamsPlayers.setDialogTeamsPlayers(pseudosBelote(), teams_, getOwner().getDialogTeamsPlayers(), getOwner().getCompoFactory());
    }
    @Override
    public void showTricksHands() {
        GameBelote game_ = partieBelote();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(getReglesBelote());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getBid());
        tricksHands_.tricks(game_);
        DialogTricksBelote.setDialogTricksBelote(file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS_BELOTE), getWindow());
        WindowCardsInt ow_ = getOwner();
        DialogTricksBelote.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosBelote(), getDisplayingBelote(),ow_);

    }
    @Override
    public void conseil() {

        GameBelote partie_=partieBelote();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        StringMap<String> messages_ = file(lg_);
        if(partie_.keepBidding()) {
            if (!partie_.getRegles().dealAll()) {
                BidBeloteSuit enchereCouleur_=getOwner().baseWindow().getIa().getBelote().strategieContrat(partie_);
                String mesBid_;

                BidBelote enchere_ = enchereCouleur_.getBid();
                if(enchere_ == BidBelote.FOLD ||
                        !enchere_.getCouleurDominante()) {
                    mesBid_ = StringUtil.simpleStringsFormat(messages_.getVal(MessagesGuiCards.MAIN_CONSULT_BELOTE_BID), Games.toString(enchere_,lg_));
                } else {
                    mesBid_ = StringUtil.simpleStringsFormat(messages_.getVal(MessagesGuiCards.MAIN_CONSULT_BELOTE_BID_SUIT), Games.toString(enchereCouleur_.getSuit(),lg_));
                }
                getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),mesBid_, getMessages().getVal(WindowCards.CONSULT_TITLE), GuiConstants.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(getOwner(),mesBid_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
            } else {
                BidBeloteSuit enchereCouleur_=getOwner().baseWindow().getIa().getBelote().strategieContrat(partie_);
                String mesBid_;

                BidBelote enchere_ = enchereCouleur_.getBid();
                if(enchere_ == BidBelote.FOLD) {
                    mesBid_ = StringUtil.simpleStringsFormat(
                            messages_.getVal(MessagesGuiCards.MAIN_CONSULT_BELOTE_BID),
                            Games.toString(enchere_,lg_));
                } else if(!enchere_.getCouleurDominante()) {
                    mesBid_ = StringUtil.simpleStringsFormat(
                            messages_.getVal(MessagesGuiCards.MAIN_CONSULT_BELOTE_BID_POINTS),
                            Games.toString(enchere_,lg_), Long.toString(enchereCouleur_.getPoints()));
                } else {
                    mesBid_ = StringUtil.simpleStringsFormat(
                            messages_.getVal(MessagesGuiCards.MAIN_CONSULT_BELOTE_BID_SUIT_POINTS),
                            Games.toString(enchereCouleur_.getSuit(),lg_), Long.toString(enchereCouleur_.getPoints()));
                }
                getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),mesBid_,
                        getMessages().getVal(WindowCards.CONSULT_TITLE), GuiConstants.INFORMATION_MESSAGE);
            }
        } else {
            String message_ = StringUtil.simpleStringsFormat(
                    messages_.getVal(MessagesGuiCards.MAIN_CONSULT_PLAYER),
                    Games.toString(getOwner().baseWindow().getIa().getBelote().strategieJeuCarteUnique(partie_),lg_));
            getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),message_,
                    getMessages().getVal(WindowCards.CONSULT_TITLE), GuiConstants.INFORMATION_MESSAGE);
        }

    }
    @Override
    public void aideAuJeu() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        GameBelote partie_=partieBelote();
        HandBelote mainUtilisateur_=partie_.getDistribution().hand();
        GameBeloteTrickInfo info_ = partie_.getDoneTrickInfo();
        BidBeloteSuit contrat_= partie_.getBid();
        Suit couleurAtout_=partie_.couleurAtout();
        HandBelote cartesJouees_=info_.cartesJouees();
        IdMap<Suit,HandBelote> repartitionCartesJouees_=cartesJouees_.couleurs(contrat_);
        DialogHelpBelote.setTitleDialog(getOwner(), StringUtil.concat(file().getVal(MessagesGuiCards.MAIN_HELP_GAME),SPACE,GameEnum.BELOTE.toString(lg_)));
        IntGameBelote ia_ = getOwner().baseWindow().getIa().getBelote();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= ia_.cartesPossibles(info_,mainUtilisateur_);
        IdMap<Hypothesis,IdMap<Suit,CustList<HandBelote>>> hypotheses_ = ia_.cartesCertaines(info_,cartesPossibles_);
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_=hypotheses_.getVal(Hypothesis.SURE);
        Suit firstSuit_;
        if (partie_.getPliEnCours().estVide()) {
            firstSuit_ = couleurAtout_;
        } else {
            firstSuit_ = partie_.getPliEnCours().couleurDemandee();
        }
        getOwner().getDialogHelpBelote().setDialogueBelote(cartesPossibles_,cartesCertaines_,repartitionCartesJouees_,firstSuit_,contrat_,pseudosBelote(), getOwner().getFrames().currentLg());
    }

    public BidBeloteSuit getContratUtilisateurBelote() {
        return contratUtilisateurBelote;
    }

    public void setContratUtilisateurBelote(
            BidBeloteSuit _contratUtilisateurBelote) {
        contratUtilisateurBelote = _contratUtilisateurBelote;
    }

    @Override
    public void nextTrick() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        GameBelote partie_ = partieBelote();
        tapisBelote().setCartesBeloteJeu(getWindow().getImageFactory(),partie_.getNombreDeJoueurs(), lg_);
        debutPliBelote();
    }
    @Override
    public void endDeal() {
        finPartieBelote();
        pack();
    }
    @Override
    public void keepPlayingRandom() {
        setChangerPileFin(true);
        modify();
    }
    @Override
    public void keepPlayingEdited() {
        GameBelote partie_=partieBelote();
        partie_.setNombre();
        HandBelote main_=partie_.empiler();
        DealBelote donne_=new DealBelote(0L);
        donne_.donneurSuivant(partie_.getDistribution().getDealer(),partie_.getNombreDeJoueurs());
        donne_.initDonne(partie_.getRegles(),getDisplayingBelote(),getOwner().getGenerator(),main_);
        getPar().jouerBelote(new GameBelote(GameType.EDIT,donne_,partie_.getRegles()));
        mettreEnPlaceIhmBelote();
    }
    @Override
    public void stopPlaying() {
        win.menuSoloGames();
    }
    @Override
    public void replay() {
        GameBelote partie_=partieBelote();
        CustList<TrickBelote> plisFaits_=partie_.getTricks();
        partie_.restituerMainsDepartRejouerDonne(plisFaits_, partie_.getNombreDeJoueurs());
        partie_.initPartie();
        mettreEnPlaceIhmBelote();
    }

    @Override
    public WindowCards window() {
        return win;
    }
}

