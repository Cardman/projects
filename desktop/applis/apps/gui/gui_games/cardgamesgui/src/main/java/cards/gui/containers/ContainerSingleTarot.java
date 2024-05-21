package cards.gui.containers;






import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Role;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.*;
import cards.gui.animations.*;
import cards.gui.containers.events.*;
import cards.gui.dialogs.*;
import cards.gui.events.*;
import cards.gui.labels.*;
import cards.gui.panels.CarpetTarot;
import cards.gui.panels.MiniCarpet;
import cards.gui.panels.PanelTricksHandsTarot;
//import cards.network.common.select.TeamsPlayers;
import cards.main.CardNatLgNamesNavigation;
import cards.main.CardsNonModalEvent;
import cards.tarot.*;
import cards.tarot.beans.TarotStandards;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.ChoiceTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.PlayingDog;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public class ContainerSingleTarot extends ContainerTarot implements ContainerSinglePausable<CardTarot>,ContainerPlayableTarot,ContainerSin,ContainerSingleWithDiscard<CardTarot> {

    private BidTarot contratUtilisateur = BidTarot.FOLD;
    private final WindowCards win;
    private CardTarot calledCard = CardTarot.WHITE;
    private final ContainerSinglePausableContent<CardTarot> contentPausable = new ContainerSinglePausableContent<CardTarot>();
    private PanelTricksHandsTarot panelTricksHandsTarot;

    public ContainerSingleTarot(WindowCards _window) {
        super(_window);
        update(_window);
        initButtonValidateDogTarot();
        initSlamButtonTarot();
        win = _window;
    }

    public HandTarot userHand() {
        GameTarot partie_=partieTarot();
        //Les regles de la belote ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        return partie_.mainUtilisateurTriee(getDisplayingTarot());
    }
//    public ContainerSingleTarot(WindowCards _window, String _file) {
//        super(_window);
//        initButtonValidateDogTarot();
//        initSlamButtonTarot();
//    }

    private void placerTarot() {
        //Activer le menu Fichier/Sauvegarder
        MenuItemUtils.setEnabledMenu(getSave(),true);
        //Activer le menu Fichier/Changer de mode
        MenuItemUtils.setEnabledMenu(getChange(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        //Desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
        window().changeMenuSimuEnabled(false);
        placerIhmTarot();
    }
    private void placerIhmTarot() {
        getPane().removeAll();
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(helpMenuTip()),GuiConstants.BORDER_LAYOUT_NORTH);
        GameTarot partie_=partieTarot();
        StringList pseudos_ = pseudosTarot();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        CarpetTarot tapis_ = CarpetTarot.initTapisTarot(lg_, partie_.getNombreDeJoueurs(), getDisplayingTarot().getDisplaying().isClockwise(), partie_.getDistribution().derniereMain().total(), getOwner().getFrames());
        getTapis().setTapisTarot(tapis_);
        container_.add(tapis_.getContainer(),GuiConstants.BORDER_LAYOUT_CENTER);
//        panelHand(getOwner().getCompoFactory().newLineBox());
//        AbsPanel panneau_=getOwner().getCompoFactory().newLineBox();
////        panneau_.add(getPanelHand());
//        panneau_.add(panelHand());
//        panneau_.setBackground(GuiConstants.BLUE);
        container_.add(panelHand(),GuiConstants.BORDER_LAYOUT_SOUTH);

        AbsPanel panneau2_=getOwner().getCompoFactory().newPageBox();
//        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY,8, 30));
//        getEvents().setEditable(false);
//        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
        panneau2_.add(events());
        setMini(MiniCarpet.newCarpet(getWindow().getImageFactory(),partie_.getNombreDeJoueurs(), getDisplayingTarot().getDisplaying().isClockwise(),pseudos_, getOwner().getCompoFactory()));
        panneau2_.add(getMiniPanel());
        setIncludedTrumpsForHandful(getOwner().getCompoFactory().newLineBox());
        AbsScrollPane scrollIncl_ = getOwner().getCompoFactory().newAbsScrollPane(getIncludedTrumpsForHandful());
        scrollIncl_.setPreferredSize(new MetaDimension(125,60));
        setExcludedTrumpsForHandful(getOwner().getCompoFactory().newLineBox());
        AbsScrollPane scrollExc_ = getOwner().getCompoFactory().newAbsScrollPane(getExcludedTrumpsForHandful());
        scrollExc_.setPreferredSize(new MetaDimension(125,60));
        setDeclaringHandful(getOwner().getCompoFactory().newHorizontalSplitPane(scrollIncl_,scrollExc_));
        getDeclaringHandful().setContinuousLayout(true);
        getDeclaringHandful().setOneTouchExpandable(true);
        setScrollDeclaringHandful(getOwner().getCompoFactory().newAbsScrollPane(getDeclaringHandful()));
        getScrollDeclaringHandful().setPreferredSize(new MetaDimension(250,60));
        getScrollDeclaringHandful().setVisible(false);
        panneau2_.add(getScrollDeclaringHandful());
//        setHandfuls(new ByteMap<AbsPlainLabel>());
//        setDeclaredHandfuls(new ByteMap<AbsPanel>());
//        AbsPanel declaredHandfuls_ = getOwner().getCompoFactory().newPageBox();
        int nbPlayers_ = partie_.getNombreDeJoueurs();
//        for (byte i = IndexConstants.FIRST_INDEX; i<nbPlayers_; i++) {
//            AbsPanel declaredHandfulGroup_ = getOwner().getCompoFactory().newLineBox();
//            AbsPlainLabel lab_ = getOwner().getCompoFactory().newPlainLabel(pseudos_.get(i));
//            declaredHandfulGroup_.add(lab_);
//            AbsPlainLabel handful_ = getOwner().getCompoFactory().newPlainLabel(EMPTY_STRING);
//            declaredHandfulGroup_.add(handful_);
//            getHandfuls().put(i, handful_);
//            AbsPanel declaredHandful_ = getOwner().getCompoFactory().newLineBox();
//            declaredHandfulGroup_.add(declaredHandful_);
//            getDeclaredHandfuls().put(i, declaredHandful_);
//            declaredHandfuls_.add(declaredHandfulGroup_);
//        }
        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(buildDeclHands(nbPlayers_,pseudos_));
        panneau2_.add(scroll_);
        setPanelCallableCards(getOwner().getCompoFactory().newLineBox());
        setScrollCallableCards(getOwner().getCompoFactory().newAbsScrollPane(getPanelCallableCards()));
        getScrollCallableCards().setVisible(false);
        panneau2_.add(getScrollCallableCards());
        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        setPanelDiscardedTrumps(getOwner().getCompoFactory().newLineBox());
        getPanelDiscardedTrumps().setVisible(false);
        panneau2_.add(getPanelDiscardedTrumps());
        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
        tapisTarot().setTalonTarot(lg_,partie_.getDistribution().derniereMain(), getOwner());
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newAbsScrollPane(container_));
        panel_.add(getWindow().getClock());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }

    public GameTarot partieTarot() {
        return getPar().partieTarot();
    }
    public void jouerDonneEntrainement(ChoiceTarot _ct) {
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        setChangerPileFin(false);

//        setPasse(false);
//        getPaused().set(PAUSE_ALIVE);
        window().getPausingCardsAnims().alive(this);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
//        setaJoueCarte(false);
        DealTarot donne_=new DealTarot(0L);
        donne_.initDonne(_ct, getReglesTarot(),getOwner().getGenerator());
        getPar().jouerTarot(new GameTarot(GameType.TRAINING,donne_,getReglesTarot()));
        mettreEnPlaceIhmTarot();
    }
    /**Met en place l'ihm pour l'utilisateur lorsqu'une partie est editee ou chargee d'un fichier*/
    public void load() {
        //Activer le menu Fichier/Sauvegarder
        MenuItemUtils.setEnabledMenu(getSave(),true);
        //Activer le menu Fichier/Changer de mode
        MenuItemUtils.setEnabledMenu(getChange(),true);
        //Desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
        window().changeMenuSimuEnabled(false);
//        setPasse(false);
//        getPaused().set(PAUSE_ALIVE);
        window().getPausingCardsAnims().alive(this);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        setChangerPileFin(false);
//        setaJoueCarte(false);
        GameTarot partie_=partieTarot();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        getOwner().setTitle(GameEnum.TAROT.toString(lg_));
        placerTarot();
        pack();
//        StringList pseudos_=pseudosTarot();
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        patchCall();
        if (!partie_.avecContrat()) {
            playingPhase();
            //Desactiver les conseils
//            MenuItemUtils.setEnabledMenu(getConsulting(),false);
////            for (TrickTarot t: partie_.getTricks())
////            for (TrickTarot t: partie_.getTricks().mid(1)) {
//////                if (!t.getVuParToutJoueur()) {
//////                    continue;
//////                }
////                Bytes players_ = partie_.orderedPlayers(t.getEntameur());
////                for (byte p: players_) {
////                    addTextInAreaByLoading(p,pseudos_.get(p),t.carteDuJoueur(p,nombreDeJoueurs_));
////                }
////            }
////            TrickTarot pliEnCours_=partie_.getPliEnCours();
////            Bytes joueurs_=pliEnCours_.joueursAyantJoue(nombreDeJoueurs_);
////            for (byte p: joueurs_) {
////                addTextInAreaByLoading(p,pseudos_.get(p),partie_.getPliEnCours().carteDuJoueur(p,partie_.getNombreDeJoueurs()));
////            }
////            for(byte p:pliEnCours_.joueursAyantJoue(nombreDeJoueurs_)) {
////                tapisTarot().setCarteTarot(getWindow().getImageFactory(),lg_, p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
////            }
//            retrieveInfos();
////            if (partie_.premierTour() && pliEnCours_.estVide() && !pliEnCours_.getVuParToutJoueur()) {
////                partie_.setPliEnCours(true);
////            }
//            MenuItemUtils.setEnabledMenu(getHelpGame(),true);
//            if (!partie_.keepPlayingCurrentGame()) {
//                finPartieTarot();
//                pack();
//                return;
//            }
//            thread(new AnimationCardTarot(this));
            return;
        }
        if (!partie_.getTricks().isEmpty()) {
            takerStatus();
            playingPhase();
            return;
        }
        if(partie_.keepBidding()) {
            //Desactiver les conseils
            MenuItemUtils.setEnabledMenu(getConsulting(),false);
            afficherMainUtilisateurTarot(false);
            bids();
            bidButtons();
//            if(partie_.playerHavingToBid() != DealTarot.NUMERO_UTILISATEUR) {
//                thread(new AnimationBidTarot(this));
//            } else {
//                //Activer les conseils
//                MenuItemUtils.setEnabledMenu(getConsulting(),true);
////                setCanBid(true);
//                for(BidTarot ench_:partie_.allowedBids()) {
//                    ajouterBoutonContratTarot(Games.toString(ench_,lg_),ench_,ench_.estDemandable(contrat_));
//                }
//                pack();
//            }
            return;
        }
//        if(partie_.isCallingState() && partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR && !partie_.getRegles().getDiscardAfterCall() && userHasDiscarded()) {
//            callingPhase();
//            return;
//        }
        if (partie_.getPreneur() != DealTarot.NUMERO_UTILISATEUR || partie_.getContrat().getJeuChien() != PlayingDog.WITH) {
            MenuItemUtils.setEnabledMenu(getConsulting(),false);
            afficherMainUtilisateurTarot(false);
            //                afficherMainUtilisateurTarot(false);
//                if (partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR) {
//                    if (partie_.getContrat()!=BidTarot.SLAM) {
////                        ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
//                        MenuItemUtils.setEnabledMenu(getConsulting(),true);
//                        getSlamButton().setEnabled(true);
////                        getSlamButton().setVisible(true);
//                        getPanneauBoutonsJeu().add(getSlamButton());
//                    }
//                    addButtonNextTrickTarot(file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
//                } else {
            AfterAnimationBidTarot.afterBid(this, BoolVal.TRUE);
//            if(partie_.pasJeuApresPasse()) {
//                addButtonEndDealTarot(file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
//            } else {
//                partie_.initPlayWithoutBid();
//                addMainCardGameTarot(true);
//            }
//                }
            pack();
            return;
            //            if (!partie_.getPliEnCours().getVuParToutJoueur()) {
//                afficherMainUtilisateurTarot(false);
//                if (partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR) {
//                    if (partie_.getContrat()!=BidTarot.SLAM) {
////                        ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
//                        MenuItemUtils.setEnabledMenu(getConsulting(),true);
//                        getSlamButton().setEnabled(true);
//                        getSlamButton().setVisible(true);
//                        getPanneauBoutonsJeu().add(getSlamButton());
//                    }
//                    addButtonNextTrickTarot(file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
//                } else {
//                    addButtonNextTrickTarot(file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
//                }
//                pack();
//                return;
//            }
        }
        takerStatus();
        if (partie_.appelSimple()) {
            afficherMainUtilisateurTarot(false);
            placerBoutonsAppel();
            window().changeStreamsMenusEnabled(true);
            pack();
            return;
        }
//        if (partie_.getPreneur() != DealTarot.NUMERO_UTILISATEUR) {
//            MenuItemUtils.setEnabledMenu(getConsulting(),false);
//            bids();
//            if(!partie_.getCarteAppelee().estVide()) {
//                String pseudo_ = pseudos_.get(partie_.getPreneur());
//                ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),RETURN_LINE));
//            }
//            afficherMainUtilisateurTarot(false);
//            addButtonSeeDogTarot(file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
//            pack();
//            return;
//            //                if(!partie_.getPliEnCours().getVuParToutJoueur()) {
////                    byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
////                    for(BidTarot b: partie_.getBids()) {
////                        String pseudo_ = pseudos_.get(player_);
////                        ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
////                        player_ = partie_.playerAfter(player_);
////                    }
////                    if(!partie_.getCarteAppelee().estVide()) {
////                        String pseudo_ = pseudos_.get(partie_.getPreneur());
////                        ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),RETURN_LINE));
////                    }
////                    setChien(partie_.getDistribution().derniereMain(),false);
////                    afficherMainUtilisateurTarot(false);
////                    addButtonNextTrickTarot(file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
////                    pack();
////                    return;
////                }
//        }
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        boolean existCard_ = userHasDiscarded();
        if (!partie_.getPliEnCours().estVide()) {
            MenuItemUtils.setEnabledMenu(getConsulting(),false);
            TrickTarot ecart_=partie_.getPliEnCours();
//            setChien(ecart_.getCartes(),true);
            //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), ecart_.total()==partie_.getDistribution().derniereMain().total());
//                        getValidateDog().setEnabled(ecart_.total()==partie_.getDistribution().derniereMain().total());
            getPanneauBoutonsJeu().add(getValidateDog());
//                        if (ecart_.total()==partie_.getDistribution().derniereMain().total()) {
////                            ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
//                            getSlamButton().setEnabled(true);
//                            getSlamButton().setVisible(true);
//                            getPanneauBoutonsJeu().add(getSlamButton());
//                        }
            getPanneauBoutonsJeu().add(getSlamButton());
            updateButtons(ecart_.total()==partie_.getDistribution().derniereMain().total());
//            afficherMainUtilisateurTarotChien();
            possibleCallAfterDiscard();
        } else if (existCard_) {
            tapisTarot().retirerCartes();
            getPanneauBoutonsJeu().removeAll();
            getPanneauBoutonsJeu().add(getValidateDog());
            getPanneauBoutonsJeu().add(getSlamButton());
            updateButtons(false);
//
//                        if (partie_.getRegles().getDiscardAfterCall()) {
//                            getValidateDog().setEnabled(false);
//                            getPanneauBoutonsJeu().add(getValidateDog());
//                            //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
//                        } else {
//                            placerBoutonsAppelApres();
//                            pack();
//                        }
//            afficherMainUtilisateurTarotChien();
            possibleCallAfterDiscard();
        } else {
//            setChien(partie_.getDistribution().derniereMain(),false);
            addButtonTakeDogCardsTarot(file().getVal(MessagesGuiCards.MAIN_TAKE_CARDS), true);
//            afficherMainUtilisateurTarot(false);
            new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanels(false);
            new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanelTarotDog(getCenterDeck(),partie_.getDeal().derniereMain().getCards(),false);
        }
        pack();
        //        MenuItemUtils.setEnabledMenu(getConsulting(),false);
//        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
//        afficherMainUtilisateurTarot(false);
//        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
//            discardedTrumps();
////            HandTarot atouts_=partie_.getTricks().first().getCartes().couleur(Suit.TRUMP);
////            if(!atouts_.estVide()) {
////                for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,atouts_.getCards())) {
////                    getPanelDiscardedTrumps().add(c.getPaintableLabel());
////                }
//////                boolean entered_ = false;
//////                for(CardTarot c: atouts_)
//////                {
//////                    GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//////                    carte_.setPreferredSize(entered_);
//////                    getPanelDiscardedTrumps().add(carte_);
//////                    entered_ = true;
//////                }
////                getPanelDiscardedTrumps().setVisible(true);
////                getPanelDiscardedTrumps().validate();
//////                AbsMetaLabelCard.repaintChildren(getPanelDiscardedTrumps(),getWindow().getImageFactory());
////            }
//        }
//        retrieveInfos();
//        if (!partie_.keepPlayingCurrentGame()) {
//            finPartieTarot();
//            pack();
//            return;
//        }
//        thread(new AnimationCardTarot(this));
    }

    private void patchCall() {
        GameTarot partie_=partieTarot();
        if (!partie_.getCarteAppelee().estVide()) {
            setCalledCard(partie_.getCarteAppelee().premiereCarte());
        } else {
            setCalledCard(CardTarot.WHITE);
        }
    }

    private void possibleCallAfterDiscard() {
        GameTarot partie_=partieTarot();
        if (!partie_.getRegles().getDiscardAfterCall()) {
            placerBoutonsAppelApres();
        }
        new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanels(true);
    }

    private void takerStatus() {
        GameTarot partie_=partieTarot();
        if (partie_.getContrat().isJouerDonne()) {
            getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, partie_.getPreneur());
        }
    }

//    private void callingPhase() {
//        GameTarot partie_=partieTarot();
////        if (partie_.getPreneur() != DealTarot.NUMERO_UTILISATEUR) {
////            loadIa();
////            pack();
////            return;
////        }
//        if (partie_.getRegles().getDiscardAfterCall()) {
//            bids();
//            afficherMainUtilisateurTarot(false);
//            placerBoutonsAppel();
//            pack();
//        } else {
//            boolean existCard_ = userHasDiscarded();
//            if (!existCard_) {
////                setChien(partie_.getDistribution().derniereMain(),false);
//                MenuItemUtils.setEnabledMenu(getConsulting(),false);
//                addButtonTakeDogCardsTarot(file().getVal(MessagesGuiCards.MAIN_TAKE_CARDS), true);
////                afficherMainUtilisateurTarot(false);
//                new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanels(false);
//                new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanelTarotDog(getCenterDeck(),partie_.getDeal().derniereMain().getCards(),false);
//            } else {
//                TrickTarot ecart_= partie_.getPliEnCours();
//                MenuItemUtils.setEnabledMenu(getConsulting(),ecart_.estVide());
////                setChien(ecart_.getCartes(),true);
////                afficherMainUtilisateurTarotChien();
//                new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanels(true);
//                placerBoutonsAppelApres();
//                pack();
//            }
////                if(partie_.getTricks().isEmpty()) {
////                    boolean existCard_ = userHasDiscarded();
////                    if (!existCard_) {
////                        MenuItemUtils.setEnabledMenu(getConsulting(),false);
////                        addButtonTakeDogCardsTarot(file().getVal(MessagesGuiCards.MAIN_TAKE_CARDS), true);
////                        afficherMainUtilisateurTarot(false);
////                    } else {
////                        TrickTarot ecart_=partie_.getPliEnCours();
////                        MenuItemUtils.setEnabledMenu(getConsulting(),ecart_.estVide());
////                        setChien(ecart_.getCartes(),true);
////                        afficherMainUtilisateurTarotChien();
////                        placerBoutonsAppelApres();
////                        updateButtons();
////                        pack();
////                    }
////                } else {
////                    TrickTarot ecart_=partie_.getPliEnCours();
////                    MenuItemUtils.setEnabledMenu(getConsulting(),ecart_.estVide());
////                    setChien(ecart_.getCartes(),true);
////                    getValidateDog().setEnabled(ecart_.total()==partie_.getDistribution().derniereMain().total());
////                    getPanneauBoutonsJeu().add(getValidateDog());
////                    //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), ecart_.total()==partie_.getDistribution().derniereMain().total());
////                    afficherMainUtilisateurTarotChien();
////                    placerBoutonsAppelApres();
////                    pack();
////                }
//        }
//        pack();
//    }

    private void playingPhase() {
        GameTarot partie_=partieTarot();
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        afficherMainUtilisateurTarot(false);
//        if (partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR && partie_.getContrat().getJeuChien() != PlayingDog.WITH && !partie_.getContrat().isFaireTousPlis()) {
//            HandTarot played_ = new HandTarot();
//            for (TrickTarot t: partie_.getTricks()) {
//                played_.ajouterCartes(t.getCartes());
//            }
//            played_.ajouterCartes(partie_.getPliEnCours().getCartes());
//            if (partie_.getDeal().derniereMain().contientCartes(played_)) {
//                getSlamButton().setEnabled(true);
//                getPanneauBoutonsJeu().add(getSlamButton());
//                addMainCardGameTarot(true);
//                pack();
//                return;
//            }
//        }
        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            discardedTrumps();
//            HandTarot atouts_=partie_.getTricks().first().getCartes().couleur(Suit.TRUMP);
//            if(!atouts_.estVide()) {
//                for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,atouts_.getCards())) {
//                    getPanelDiscardedTrumps().add(c.getPaintableLabel());
//                }
////                boolean entered_ = false;
////                for(CardTarot c: atouts_)
////                {
////                    GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
////                    carte_.setPreferredSize(entered_);
////                    getPanelDiscardedTrumps().add(carte_);
////                    entered_ = true;
////                }
//                getPanelDiscardedTrumps().setVisible(true);
//                getPanelDiscardedTrumps().validate();
////                AbsMetaLabelCard.repaintChildren(getPanelDiscardedTrumps(),getWindow().getImageFactory());
//            }
        }
        retrieveInfos();
        if (!partie_.keepPlayingCurrentGame()) {
            finPartieTarot();
            pack();
            return;
        }
        thread(new AnimationCardTarot(this));
    }

    private void bids() {
        GameTarot partie_=partieTarot();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        StringList pseudos_=pseudosTarot();
        byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
        for(BidTarot b: partie_.getBids()) {
            String pseudo_ = pseudos_.get(player_);
            ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b, lg_),RETURN_LINE));
            player_ = partie_.playerAfter(player_);
        }
    }

//    private void loadIa() {
//        GameTarot partie_=partieTarot();
//        if (partie_.getRegles().getDiscardAfterCall()) {
//            AfterAnimationBidTarot.discardAfterCallIa(this);
//        } else {
//            AfterAnimationBidTarot.callAfterDiscardIa(this);
//        }
//    }

    private boolean userHasDiscarded() {
        GameTarot partie_=partieTarot();
        boolean existCard_ = false;
        for (CardTarot c: partie_.getDistribution().derniereMain()) {
            if (partie_.getDistribution().hand().contient(c)) {
                existCard_ = true;
                break;
            }
        }
        return existCard_;
    }

    private void retrieveInfos() {
        StringList pseudos_=pseudosTarot();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        byte nombreDeJoueurs_;
        GameTarot partie_=partieTarot();
        nombreDeJoueurs_=partie_.getNombreDeJoueurs();
        //for (TrickTarot t: partie_.getTricks())
        CustList<TrickTarot> tricks_ = partie_.getTricks();
        for (TrickTarot t: tricks_.mid(1)) {
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
            Bytes players_ = partie_.orderedPlayers(t.getEntameur());
            for (byte p: players_) {
                calledCard(t.carteDuJoueur(p, nombreDeJoueurs_), pseudos_.get(p), p);
//                addTextInAreaByLoading(p, pseudos_.get(p),t.carteDuJoueur(p, nombreDeJoueurs_));
            }
        }
//        if (tricks_.isEmpty()) {
//            pack();
//            return;
//        }
        TrickTarot pliEnCours_= partie_.getPliEnCours();
        Bytes joueurs_=pliEnCours_.joueursAyantJoue(nombreDeJoueurs_);
        for (byte p: joueurs_) {
            addTextInAreaByLoading(p, pseudos_.get(p), partie_.getPliEnCours().carteDuJoueur(p, partie_.getNombreDeJoueurs()));
        }
        for(byte p:pliEnCours_.joueursAyantJoue(nombreDeJoueurs_)) {
            tapisTarot().setCarteTarot(getWindow().getImageFactory(), lg_,p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
        }
        pack();
    }

    public void ajouterBoutonContratTarot(String _texte,BidTarot _action,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratTarot(_action));
        bouton_.addActionListener(new CardsNonModalEvent(this),new ListenerBidTarotSingle(this,_action));
        bouton_.setEnabled(_apte);
        if (!_apte) {
            TranslationsLg lg_ = getOwner().getFrames().currentLg();
            bouton_.setToolTipText(StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_BID), Games.toString(_action,lg_)));
        }
        panneau_.add(bouton_);
        getBids().add(_action);
    }
    private void initSlamButtonTarot() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(Games.toString(BidTarot.SLAM,lg_));
        bouton_.addActionListener(new CardsNonModalEvent(this),new SlamEvent(this));
        setSlamButton(bouton_);
    }
//    public void ajouterBoutonJeuChelemTarot(String _texte,boolean _apte) {
//        JPanel panneau_=getPanneauBoutonsJeu();
//        LabelButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                annonceTarotChelem();
//            }
//        });
//        bouton_.setEnabledLabel(_apte);
//        panneau_.add(bouton_);
//    }
    public void addButtonSeeDogTarot(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(this),new SeeDogEvent(this));
        bouton_.setEnabled(_apte);
        setSeeDog(bouton_);
        panneau_.add(bouton_);
    }
    private void addButtonTakeDogCardsTarot(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(this),new TakeDogEvent(this));
        bouton_.setEnabled(_apte);
        setTakeCardDog(bouton_);
        panneau_.add(bouton_);
    }
    private void initButtonValidateDogTarot() {
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME));
        bouton_.addActionListener(new CardsNonModalEvent(this),new ValidateDogEvent(this));
        setValidateDog(bouton_);
    }
    @Override
    public void validateDog() {
        GameTarot partie_ = partieTarot();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        setCanDiscard(false);
        updateCardsInPanelTarotJeu(false);
        beforeCardPlaying();
//        addPossibleDiscarded();
//        partie_.addCurTrick();
//        discardedTrumps();
//        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
//            partie_.addCurTrick();
//            discardedTrumps();
////            HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
////            if(!atouts_.estVide()) {
////                getPanelDiscardedTrumps().removeAll();
////                for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,atouts_.getCards())) {
////                    getPanelDiscardedTrumps().add(c.getPaintableLabel());
////                }
//////                boolean entered_ = false;
//////                for(CardTarot c: atouts_)
//////                {
//////                    GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//////                    carte_.setPreferredSize(entered_);
//////                    getPanelDiscardedTrumps().add(carte_);
//////                    entered_ = true;
//////                }
////                getPanelDiscardedTrumps().setVisible(true);
////                getPanelDiscardedTrumps().validate();
////                //pack();
////            }
//        }
        tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain(), getOwner());
        tapisTarot().setCartesTarotJeu(getWindow().getImageFactory(),lg_,partie_.getNombreDeJoueurs());
        getScrollCallableCards().setVisible(false);
        changeEnable();
//        if(partie_.premierTour()) {
//            byte donneur_=partie_.getDistribution().getDealer();
//            if(!partie_.chelemAnnonce()) {
//                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
//                partie_.setEntameur(partie_.playerAfter(donneur_));
//            }
//            partie_.setPliEnCours(true);
//        }

        launchAnimCards();
    }
//    public void addButtonValidateDogTarot(String _texte,boolean _apte) {
//        JPanel panneau_=getPanneauBoutonsJeu();
//        LabelButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                GameTarot partie_ = partieTarot();
//                setCanDiscard(false);
//                if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
//                    partie_.ajouterPliAttaque();
//                    HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
//                    if(!atouts_.estVide()) {
//                        getPanelDiscardedTrumps().removeAll();
//                        for (GraphicTarotCard c: getGraphicCards(atouts_)) {
//                            getPanelDiscardedTrumps().add(c);
//                        }
////                        boolean entered_ = false;
////                        for(CardTarot c: atouts_)
////                        {
////                            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
////                            carte_.setPreferredSize(entered_);
////                            getPanelDiscardedTrumps().add(carte_);
////                            entered_ = true;
////                        }
//                        getPanelDiscardedTrumps().setVisible(true);
//                        getPanelDiscardedTrumps().validate();
//                        getPanelDiscardedTrumps().repaint();
//                        //pack();
//                    }
//                }
//                tapisTarot().setEcart(partie_.getDistribution().derniereMain());
//                tapisTarot().setCartesTarotJeu(partie_.getNombreDeJoueurs());
//                getScrollCallableCards().setVisible(false);
//                debutPliTarot(false);
//            }
//        });
//        bouton_.setEnabledLabel(_apte);
//        panneau_.add(bouton_);
//        setValidateDog(bouton_);
//    }
    public void addButtonEndDealTarot(String _texte,boolean _apte) {
        contentPausable.addButtonEndDeal(this,_texte,_apte);
    }
    public void addMainCardGameTarot(boolean _apte) {
        contentPausable.addMainCardGame(this,_apte);
    }
    public void addButtonNextTrickTarot(boolean _apte) {
        contentPausable.addButtonNextTrick(this,_apte);
    }
//    private void addButtonKeepPlayingDealTarot(AbsPanel _panneau,String _texte) {
//        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new CardsNonModalEvent(this),new KeepPlayingRandomEvent(this));
//        _panneau.add(bouton_);
//    }
//    private void addButtonKeepPlayingEditedDealTarot(AbsPanel _panneau,String _texte) {
//        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new CardsNonModalEvent(this),new KeepPlayingEditedEvent(this));
//        _panneau.add(bouton_);
//    }
    private void addButtonStopPlayingTarot(AbsPanel _panneau, String _texte) {
        contentPausable.addButtonStopPlaying(this, _panneau, _texte);
    }
    private void addButtonReplayDealTarot(AbsPanel _panneau, String _texte) {
        contentPausable.addButtonReplayDeal(this, _panneau, _texte);
    }

    @Override
    public void refreshCurrentHand() {
        afficherMainUtilisateurTarot(true);
    }

    public void placerBoutonsAvantJeuUtilisateurTarot() {
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
//        partieTarot().changerConfiance();
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        setChoosenHandful(Handfuls.NO);
        afficherMainUtilisateurTarot(true);
//        setRaisonCourante(EMPTY);
        setSelectedMiseres(new IdMap<Miseres,AbsCustCheckBox>());
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        GameTarot partie_=partieTarot();
        if(partie_.premierTourNoMisere()) {
//            setCanExcludeTrumps(true);
            IdList<Handfuls> poignees_ = partie_.getAnnoncesPoigneesPossibles(DealTarot.NUMERO_UTILISATEUR);
            RulesTarot regles_=partie_.getRegles();
            HandTarot trumps_ = GameTarotCommonPlaying.atoutsPoignee(partie_.getDistribution().hand().couleurs());
            displayTrumpsForHandful(trumps_);
            IdList<Handfuls> all_ = new IdList<Handfuls>();
            IdList<Handfuls> enabled_ = new IdList<Handfuls>();
            all_.addAllElts(Handfuls.getNonDeclarableHandFuls());
            enabled_.addAllElts(Handfuls.getNonDeclarableHandFuls());
            for (Handfuls h: Handfuls.getDeclarableHandFuls()) {
                if (!regles_.poigneeAutorisee(h)) {
                    continue;
                }
                if (poignees_.containsObj(h)) {
                    enabled_.add(h);
                }
                all_.add(h);
            }
            updateHandfulButtons(this,all_,enabled_,regles_.getAllowedHandfuls());
            AbsPanel panneau_=getPanneauBoutonsJeu();
//            AbsPanel handFuls_ = getOwner().getCompoFactory().newPageBox();
//            AbsTextArea txt_ = getOwner().getCompoFactory().newTextArea(EMPTY_STRING, 1, 15);
//            txt_.setEditable(false);
//            setInfoCurrentHandful(txt_);
//            AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(getInfoCurrentHandful());
//            scroll_.setPreferredSize(new MetaDimension(getEvents().getWidth(),70));
//            handFuls_.add(scroll_);
//            getHandfulsRadio().clear();
//            CustList<AbsRadioButton> list_ = new CustList<AbsRadioButton>();
//            for (Handfuls h: Handfuls.getNonDeclarableHandFuls()) {
//                AbsRadioButton radio_ = getOwner().getCompoFactory().newRadioButton(Games.toString(h,lg_));
//                list_.add(radio_);
//                radio_.addMouseListener(new ListenerNoHandfulTarot(this, radio_, h,list_));
//                handFuls_.add(radio_);
//                getHandfulsRadio().addEntry(h,radio_);
//            }
//            for (Handfuls h: Handfuls.getDeclarableHandFuls()) {
//                if (!regles_.poigneeAutorisee(h)) {
//                    continue;
//                }
//                AbsRadioButton radio_ = getOwner().getCompoFactory().newRadioButton(Games.toString(h,lg_));
//                list_.add(radio_);
//                radio_.setEnabled(poignees_.containsObj(h));
//                radio_.addMouseListener(new ListenerHandfulTarot(regles_.getAllowedHandfuls().getVal(h), radio_, this, h,list_));
//                handFuls_.add(radio_);
//                getHandfulsRadio().addEntry(h,radio_);
//            }
//            panneau_.add(handFuls_);
            AbsPanel miseresPanel_ = getOwner().getCompoFactory().newPageBox();
            for(Miseres po_:regles_.getMiseres()) {
                AbsCustCheckBox check_ = getOwner().getCompoFactory().newCustCheckBox(Games.toString(po_,lg_));
                //check_.addChangeListener(new ListenerMiseres(check_,po_));
//                check_.addActionListener(new ListenerMiseresTarot(this,check_,po_));
                getSelectedMiseres().put(po_, check_);
                miseresPanel_.add(check_);
            }
            panneau_.add(miseresPanel_);
        }
    }
    public void placerBoutonsFinPliUtilisateurTarot() {
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        GameTarot partie_=partieTarot();
        if(!partie_.keepPlayingCurrentGame()) {
            addButtonEndDealTarot(file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
        } else {
            addButtonNextTrickTarot(true);
        }
    }

    public void placerBoutonsAppel() {
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        getPanneauBoutonsJeu().removeAll();
        updateCardsInPanelTarotCallBeforeDog(true);
        getScrollCallableCards().setVisible(true);
    }
    public void placerBoutonsAppelApres() {
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        getPanneauBoutonsJeu().removeAll();
        setCalledCard(CardTarot.WHITE);
        updateCardsInPanelTarotCallAfterDog();
        getScrollCallableCards().setVisible(true);
        getValidateDog().setEnabled(false);
        getPanneauBoutonsJeu().add(getValidateDog());
        getSlamButton().setEnabled(false);
//        getSlamButton().setVisible(true);
        getPanneauBoutonsJeu().add(getSlamButton());
        updateButtons();
        pack();
    }

    private void changeEnable() {
        //Activer le sous-menu conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        //Activer le sous-menu aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
    }

    private void launchAnimCards() {
        /*On affiche la main de l'utilisateur avec des ecouteurs sur les cartes et on supprime tous les boutons de l'ihm places a droite avant d'executer un eventuel Thread*/
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
//        setRaisonCourante(getMessages().getVal(WindowCards.WAIT_TURN));
//        setThreadAnime(true);
        thread(new AnimationCardTarot(this));
    }

    @Override
    public void annonceChelem() {
        GameTarot partie_=partieTarot();
        getScrollCallableCards().setVisible(false);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        partie_.ajouterChelemUtilisateur();
        beforeCardPlaying();
//        if(!partie_.chelemAnnonce()) {
//            int choix_=getOwner().getConfirmDialogAns().input(getOwner().getCommonFrame(),getMessages().getVal(WindowCards.ASK_SLAM),getMessages().getVal(WindowCards.ASK_SLAM_TITLE), lg_,GuiConstants.YES_NO_OPTION);
//            if(choix_!=GuiConstants.YES_OPTION) {
//                getScrollCallableCards().setVisible(true);
//                return;
//            }
            getPanneauBoutonsJeu().removeAll();
            ajouterTexteDansZone(StringUtil.concat(StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_DECLARING_SLAM), pseudo()),RETURN_LINE));
//            setCanDiscard(false);
            updateCardsInPanelTarotJeu(false);
//            addPossibleDiscarded();
//            if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
//                partie_.addCurTrick();
//                discardedTrumps();
//                HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
//                if(!atouts_.estVide()) {
//                    for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,atouts_.getCards())) {
//                        getPanelDiscardedTrumps().add(c.getPaintableLabel());
//                    }
////                    boolean entered_ = false;
////                    for(CardTarot c: atouts_)
////                    {
////                        GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT, !entered_);
////                        carte_.setPreferredSize(entered_);
////                        getPanelDiscardedTrumps().add(carte_);
////                        entered_ = true;
////                    }
//                    getPanelDiscardedTrumps().setVisible(true);
//                    getPanelDiscardedTrumps().validate();
////                    AbsMetaLabelCard.repaintChildren(getPanelDiscardedTrumps(),getWindow().getImageFactory());
//                    //pack();
//                }
//            }
            getPanneauBoutonsJeu().validate();
            tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain(), getOwner());
            tapisTarot().setCartesTarotJeu(getWindow().getImageFactory(),lg_,partie_.getNombreDeJoueurs());
        changeEnable();
//        if(partie_.premierTour()) {
//            byte donneur_=partie_.getDistribution().getDealer();
//            if(!partie_.chelemAnnonce()) {
//                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
//                partie_.setEntameur(partie_.playerAfter(donneur_));
//            }
//            partie_.setPliEnCours(true);
//        }

        launchAnimCards();
        //        }
        //pack();
    }

    private void beforeCardPlaying() {
        GameTarot partie_=partieTarot();
        if (!partie_.getTricks().isEmpty()) {
            partie_.firstLead();
            return;
        }
        if (partie_.getContrat().getJeuChien() != PlayingDog.WITH) {
            partie_.gererChienInconnu();
        }
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if (!partie_.getRegles().getDiscardAfterCall() && partie_.isCallingState()) {
            CardTarot called_ = getCalledCard();
            HandTarot cartesAppel_ = new HandTarot();
            cartesAppel_.ajouter(called_);
            partie_.initConfianceAppeleUtilisateur(getOwner().baseWindow().getIa().getTarot().strategieAppelUser(cartesAppel_));
            ajouterTexteDansZone(StringUtil.concat(pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(called_, lg_),ContainerGame.RETURN_LINE));
//            if (called_ != CardTarot.WHITE) {
//                HandTarot cartesAppel_ = new HandTarot();
//                cartesAppel_.ajouter(called_);
//                partie_.initConfianceAppeleUtilisateur(getOwner().baseWindow().getIa().getTarot().strategieAppelUser(cartesAppel_));
//                ajouterTexteDansZone(StringUtil.concat(pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(called_, lg_),ContainerGame.RETURN_LINE));
//            }
        }
        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            HandTarot atouts_ = partie_.addCurTrickDiscarded();
            discardedTrumps(this,atouts_);
        } else {
            partie_.firstLead();
        }
    }

    private void addTextInAreaByLoading(byte _joueur, String _pseudo, CardTarot _ct) {
//        GameTarot partie_=partieTarot();
//        if(partie_.premierTourNoMisere()) {
//            firstRound(_joueur, _pseudo);
//        }
        firstRound(_joueur, _pseudo);
        calledCard(_ct, _pseudo, _joueur);
//        pack();
    }

    private void firstRound(byte _joueur, String _pseudo) {
        GameTarot partie_=partieTarot();
        HandTarot poignee_=partie_.getPoignee(_joueur);
        IdList<Handfuls> decHand_ = partie_.getAnnoncesPoignees(_joueur);
        IdList<Miseres> miseres_ = partie_.getAnnoncesMiseres(_joueur);
        firstRound(_joueur, _pseudo, decHand_, miseres_, poignee_, new DirectCardsCallEvents());
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        for(Handfuls annonce_: decHand_) {
//            ajouterTexteDansZone(StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_, lg_),RETURN_LINE));
//        }
//        for(Miseres annonce_: miseres_) {
//            ajouterTexteDansZone(StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_, lg_),RETURN_LINE));
//        }
//        if(!poignee_.estVide()) {
//            getHandfuls().getVal(_joueur).setText(Games.toString(decHand_.first(), lg_));
//        }
//        poignee_.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
//        AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
//        panelToSet_.removeAll();
//        for(CardTarot c: poignee_) {
//            MiniCard carte_=new MiniCard(lg_, getOwner(), c.getId().nb());
//            panelToSet_.add(carte_.getPaintableLabel());
//            AbsMetaLabelCard.paintCard(getWindow().getImageFactory(),carte_);
//        }
    }

    public void jouerTarot(byte _joueur, String _pseudo) {
        GameTarot partie_=partieTarot();
        CardTarot ct_= getOwner().baseWindow().getIa().getTarot().changerConfianceJeuCarteUnique(partie_);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if(partie_.premierTourNoMisere()) {
            IdList<Handfuls> annoncesPoignees_ = getOwner().baseWindow().getIa().getTarot().handful(partie_.getAnnoncesPoignees(_joueur));
            IdList<Miseres> annoncesMiseres_ = getOwner().baseWindow().getIa().getTarot().misere(partie_.getAnnoncesMiseres(_joueur));
            HandTarot poignee_=getOwner().baseWindow().getIa().getTarot().handfulCard(partie_.getPoignee(_joueur));
            firstRound(_joueur, _pseudo, annoncesPoignees_, annoncesMiseres_, poignee_, new IndirectCardsCallEvents(getOwner().getCompoFactory()));
//                panelToSet_.removeAll();
//                for(CardTarot c:poignee_)
//                {
//                    MiniTarotCard carte_=new MiniTarotCard(c);
//                    panelToSet_.add(carte_);
//                }
//                panelToSet_.validate();
            //pack();
        }
        callCard(_joueur,_pseudo,ct_,new IndirectCardsCallEvents(getOwner().getCompoFactory()));
//        if(partie_.getCarteAppelee().contient(ct_)) {
//            getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, _joueur);
//            getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(Role.CALLED_PLAYER,lg_))));
////            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+Status.CALLED_PLAYER.toString());
//        }
        partie_.ajouterUneCarteDansPliEnCours(ct_);
        tapisTarot().setCarteTarot(getWindow().getImageFactory(),lg_,_joueur,ct_);
    }

    private void firstRound(byte _joueur, String _pseudo, IdList<Handfuls> _declHand, IdList<Miseres> _miseres, HandTarot _hand, IntCardsCallEvents _interceptor) {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for(Handfuls annonce_: _declHand) {
            _interceptor.call(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_, lg_),RETURN_LINE)));
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
        }
        for(Miseres annonce_: _miseres) {
            _interceptor.call(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_, lg_),RETURN_LINE)));
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
        }
        if(!_hand.estVide()) {
            AbsPlainLabel label_ = getHandfuls().getVal(_joueur);
            _interceptor.call(new SettingText(label_, Games.toString(_declHand.first(), lg_)));
//                    getHandfuls().getVal(_joueur).setText(annoncesPoignees_.first().toString());
        }
        _hand.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
        _interceptor.call(new HandfulThread(_hand, panelToSet_, getWindow()));
    }

    private void callCard(byte _joueur, String _pseudo, CardTarot _ct, IntCardsCallEvents _interceptor) {
        callCard(this,partieTarot(),_joueur,_pseudo,_ct,_interceptor);
//        GameTarot partie_=partieTarot();
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        if(partie_.getCarteAppelee().contient(_ct)) {
//            getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, _joueur);
//            _interceptor.call(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(Role.CALLED_PLAYER,lg_))));
////            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+Status.CALLED_PLAYER.toString());
//        }
    }

    private void afficherMainUtilisateurTarot(boolean _ecouteur) {
//        if (!_ecouteur) {
//            setCarteEntree(false);
//            setCarteSortie(false);
//        }
//        GameTarot partie_=partieTarot();
        //Les regles du tarot ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
//        HandTarot mainUtilisateur_=partie_.mainUtilisateurTriee(getDisplayingTarot());
        /*On place les cartes de l'utilisateur*/
//        setCanPlay(_ecouteur);
        updateCardsInPanelTarotJeu(_ecouteur);
        getWindow().pack();
    }
    public void finPliTarot(CardTarot _carteJouee) {
        updateCardsInPanelTarotJeu(false);
        CardTarot played_ = getOwner().baseWindow().getIa().getTarot().changerConfianceJeuCarteUniqueUser(_carteJouee);
        //Activer le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(getPause(),true);
        //Desactiver le sous-menu conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        GameTarot partie_=partieTarot();
        if (partie_.premierTourNoMisere()) {
            firstRound(DealTarot.NUMERO_UTILISATEUR, pseudo());
        }
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        calledCard(played_, pseudo(), DealTarot.NUMERO_UTILISATEUR);
        /*L'utilisateur joue sa carte*/
        partie_.ajouterUneCarteDansPliEnCours(played_);
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
//        setRaisonCourante(getMessages().getVal(WindowCards.END_TRICK));
        afficherMainUtilisateurTarot(false);
        tapisTarot().setCarteTarot(getWindow().getImageFactory(),lg_,DealTarot.NUMERO_UTILISATEUR,played_);
        //Desactiver le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(getPause(),false);
        launchAnimCards();
//        setThreadAnime(true);

    }

    private void calledCard(CardTarot _playedCard, String _pseudo, byte _player) {
        callCard(_player,_pseudo,_playedCard,new DirectCardsCallEvents());
//        GameTarot partie_=partieTarot();
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        if(partie_.getCarteAppelee().contient(_playedCard)) {
//            getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, _player);
//            ajouterTexteDansZone(StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(Role.CALLED_PLAYER, lg_)));
//        }
    }

    public void finPartieTarot() {
        getPane().removeAll();
        /*Descativer aide au jeu*/
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();

        if(isChangerPileFin()) {
            GameTarot partie_=partieTarot();
            getOwner().baseWindow().getFacadeCards().getNicknamesCrud().getCardGamesCrud().tarot(partie_.empiler());
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
//        setThreadAnime(false);
        window().changeStreamsMenusEnabled(true);
        StringList pseudos_=new StringList(pseudosTarot());
        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();

        GameTarot partie_=partieTarot();
        if (partie_.getType() == GameType.RANDOM && isChangerPileFin()) {
            changerNombreDeParties(GameEnum.TAROT, partie_.getDistribution().getNbDeals(), getOwner().getFrames(), 0);
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();

        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(partie_);
        res_.getRes().setUser(DealTarot.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(pseudos_), getScores());
        Games.setMessages(res_.getRes(),getOwner().getFrames().currentLg());
        setScores(res_.getRes().getScores());
        res_.getRes().setGeneral(readCoreResourceSuit());
        res_.getRes().setSpecific(readResource());
        res_.getRes().setGeneralCards(readCoreResourceCards());

        RenderedPage editor_;
        CardNatLgNamesNavigation sOne_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT).attendreResultat();
        ((TarotStandards)sOne_.getBeanNatLgNames()).setDataBase(res_);
        editor_ = FrameGeneralHelp.initialize(sOne_, getOwner().getFrames(), win.getGuardRender());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_RESULTS_PAGE),editor_.getScroll());
        CardNatLgNamesNavigation sTwo_ = retrieve(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT).attendreResultat();
        ((TarotStandards)sTwo_.getBeanNatLgNames()).setDataBase(res_);
        editor_ = FrameGeneralHelp.initialize(sTwo_, getOwner().getFrames(), win.getGuardRender());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_DETAIL_RESULTS_PAGE),editor_.getScroll());
        if(partie_.getType()==GameType.RANDOM) {
            updateGraphicLines(onglets_,res_.getRes(),nombreJoueurs_,pseudos_);
//            Ints couleurs_=couleursCourbes(getOwner().getGenerator());
//            Ints couleurs_=new Ints();
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
        GameTarot game_=partieTarot();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.tricks(game_);
        tricksHands_.sortHands(getDisplayingTarot(), game_.getNombreDeJoueurs());
        WindowCardsInt ow_ = getOwner();
        PanelTricksHandsTarot end_ = new PanelTricksHandsTarot(ow_.getCommonFrame(), tricksHands_,
                nombreJoueurs_,
                pseudosTarot(),
                getDisplayingTarot(), ow_);
        panelTricksHandsTarot = end_;
        AbsCustComponent panelCards_ = end_.getContainer();
        panelCards_.setPreferredSize(new MetaDimension(850,850));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS),panelCards_);
        container_.add(onglets_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=getOwner().getCompoFactory().newPageBox();
        AbsPanel buttons_ = getOwner().getCompoFactory().newLineBox();
        resultButtons(buttons_,this);
//        GameType type_;
//        long nombreParties_;
//        type_=partie_.getType();
//        nombreParties_=partie_.getNumber();
//        int nombreTotalParties_= partie_.getRegles().getCommon().getNbDeals();
//        if(type_==GameType.EDIT&&nombreParties_<nombreTotalParties_) {
//            addButtonKeepPlayingEditedDealTarot(buttons_, file().getVal(MessagesGuiCards.MAIN_KEEP_PLAYING_EDITED_DEAL));
//        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&isPartieAleatoireJouee()||type_==GameType.RANDOM) {
//            addButtonKeepPlayingDealTarot(buttons_, file().getVal(MessagesGuiCards.MAIN_KEEP_PLAYING_DEAL));
//        }
        addButtonReplayDealTarot(buttons_, file().getVal(MessagesGuiCards.MAIN_REPLAY_DEAL));
        addButtonStopPlayingTarot(buttons_, file().getVal(MessagesGuiCards.MAIN_STOP));
        panneau_.add(buttons_);
        panneau_.add(getWindow().getClock());
        panneau_.add(getWindow().getLastSavedGameDate());
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        if(type_!=GameType.EDIT) {
//            partie_.setNombre();
//        }

        setContentPane(container_);
    }

    @Override
    public GameType getGameType() {
        return partieTarot().getType();
    }

    @Override
    public long nombreParties() {
        return partieTarot().getNumber();
    }

    @Override
    public long nombreTotalParties() {
        return partieTarot().getRegles().getCommon().getNbDeals();
    }
    @Override
    public void modify() {
        MenuItemUtils.setEnabledMenu(getSave(),true);
        MenuItemUtils.setEnabledMenu(getChange(),true);
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
//        HandTarot pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
//        pile_ = chargerPileTarot();
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.TAROT, getOwner().getFrames(), 0);
//        DealTarot donne_;
        if(nb_==0||!getPar().enCoursDePartieTarot()) {
            setChangerPileFin(true);
//            donne_=new DealTarot(nb_,pile_);
//            donne_.setRandomDealer(getReglesTarot(),getOwner().getGenerator());
//            donne_.initDonne(getReglesTarot(),getOwner().getGenerator());
            getPar().jouerTarot(getWindow().baseWindow().getFirstDealTarot().deal(this,getReglesTarot(),nb_));
        } else {
            GameTarot partie_=partieTarot();
            DealTarot donne_=getOwner().baseWindow().getIa().getTarot().empiler(nb_, partie_,getOwner().getGenerator());
            getPar().jouerTarot(new GameTarot(GameType.RANDOM,donne_,partie_.getRegles()));
//            GameTarot partie_=partieTarot();
//            donne_=new DealTarot(nb_,partie_.empiler());
//            donne_.donneurSuivant(partie_.getDistribution().getDealer(),partie_.getRegles());
//            donne_.initDonne(partie_.getRegles(),getOwner().getGenerator());
//            getPar().jouerTarot(new GameTarot(GameType.RANDOM,donne_,partie_.getRegles()));
        }
        mettreEnPlaceIhmTarot();
    }
    public void editerTarot(GameTarot _partie) {
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
//        setPasse(false);
//        getPaused().set(PAUSE_ALIVE);
        window().getPausingCardsAnims().alive(this);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
//        setaJoueCarte(false);
//        setPartieSauvegardee(false);
        getPar().jouerTarot(_partie);
        //Desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
        window().changeMenuSimuEnabled(false);
        setChangerPileFin(false);
        mettreEnPlaceIhmTarot();
    }
    private void mettreEnPlaceIhmTarot() {
        placerTarot();
        afficherMainUtilisateurTarot(false);
        pack();
        GameTarot partie_=partieTarot();
        if(partie_.avecContrat()) {
            bidButtons();
        } else {
            firstTrick();
        }
    }

    public void bidButtons() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        GameTarot partie_=partieTarot();
        getPanneauBoutonsJeu().removeAll();
        getBids().clear();
        if (partie_.playerHavingToBid() != DealTarot.NUMERO_UTILISATEUR) {
            MenuItemUtils.setEnabledMenu(getConsulting(),false);
            thread(new AnimationBidTarot(this));
        } else {
            //Activer les conseils
            MenuItemUtils.setEnabledMenu(getConsulting(),true);
            for(BidTarot b: partie_.allowedBids()) {
                ajouterBoutonContratTarot(Games.toString(b, lg_),b,b.estDemandable(partie_.getContrat()));
            }
            pack();
        }
    }

    public StringList pseudosTarot() {
        return pseudosTarot(partieTarot().getNombreDeJoueurs());
    }

    public void voirChien() {
        GameTarot partie_=partieTarot();
//        setChien(partie_.getDistribution().derniereMain(),false);
        new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanels(false);
        AbsPanel boutons_=getPanneauBoutonsJeu();
        boutons_.removeAll();
        new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanelTarotDog(getCenterDeck(),partie_.getDeal().derniereMain().getCards(),false);
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            addButtonTakeDogCardsTarot(file().getVal(MessagesGuiCards.MAIN_TAKE_CARDS), true);
        } else {
            HandTarot atouts_;
            if (!partie_.getRegles().getDiscardAfterCall()) {
                atouts_ = partie_.appelApresEcart(getOwner().baseWindow().getIa().getTarot());
                called();
//                if(!partie_.getCarteAppelee().estVide()) {
//                    ajouterTexteDansZone(StringUtil.concat(pseudosTarot().get(partie_.getPreneur()),INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),RETURN_LINE));
//                }
            } else {
                atouts_ = partie_.ecarter(getOwner().baseWindow().getIa().getTarot());
            }
            discardedTrumps(this,atouts_);
            partie_.firstLead();
//            HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
//            getPanelDiscardedTrumps().removeAll();
//            if(!atouts_.estVide()) {
//                for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,atouts_.getCards())) {
//                    getPanelDiscardedTrumps().add(c.getPaintableLabel());
//                }
////                boolean entered_ = false;
////                for(CardTarot c: atouts_)
////                {
////                    GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
////                    carte_.setPreferredSize(!entered_);
////                    getPanelDiscardedTrumps().add(carte_);
////                    entered_ = true;
////                }
//                getPanelDiscardedTrumps().setVisible(true);
//                getPanelDiscardedTrumps().validate();
//            }
            addMainCardGameTarot(true);
        }
        //boutons_.validate();
        pack();
    }

    private void discardedTrumps() {
        HandTarot atouts_=partieTarot().getTricks().first().getCartes().couleur(Suit.TRUMP);
        discardedTrumps(this,atouts_);
    }

    public static void discardedTrumps(ContainerTarot _cont,HandTarot _atouts) {
        TranslationsLg lg_ = _cont.getOwner().getFrames().currentLg();
        _cont.getPanelDiscardedTrumps().removeAll();
        if(!_atouts.estVide()) {
            for (GraphicCard<CardTarot> c: getGraphicCards(_cont.getWindow(),lg_, _atouts.getCards())) {
                _cont.getPanelDiscardedTrumps().add(c.getPaintableLabel());
            }
            _cont.getPanelDiscardedTrumps().setVisible(true);
            _cont.getPanelDiscardedTrumps().setSize(_cont.getPanelDiscardedTrumps().getPreferredSizeValue());
        }
        _cont.pack();
    }
    public void called() {
        GameTarot partie_=partieTarot();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        ajouterTexteDansZone(StringUtil.concat(pseudosTarot().get(partie_.getPreneur()),
                ContainerGame.INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(), lg_),ContainerGame.RETURN_LINE));
    }
    @Override
    public void prendreCartesChien() {
        GameTarot partie_=partieTarot();
        partie_.ajouterCartesUtilisateur();
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        tapisTarot().retirerCartes();
//        afficherMainUtilisateurTarotChien();
        getPanneauBoutonsJeu().removeAll();
        if (partie_.getRegles().getDiscardAfterCall()) {
            getValidateDog().setEnabled(false);
            getPanneauBoutonsJeu().add(getValidateDog());
//            getSlamButton().setVisible(true);
            getSlamButton().setEnabled(false);
            getPanneauBoutonsJeu().add(getSlamButton());
            //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
        }
        possibleCallAfterDiscard();
        pack();
    }

    @Override
    public void discard(CardTarot _t) {
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        GameTarot partie_=partieTarot();
        partie_.ajouterUneCarteDansPliEnCoursPreneur(getOwner().baseWindow().getIa().getTarot().discard(_t));
    }

    @Override
    public void restore(CardTarot _t) {
        GameTarot partie_=partieTarot();
        CardTarot r_ = getOwner().baseWindow().getIa().getTarot().restore(_t);
        partie_.retirerUneCarteDuChien(r_);
        MenuItemUtils.setEnabledMenu(getConsulting(),partie_.getPliEnCours().estVide());
    }

    @Override
    public IdList<CardTarot> ecartables() {
        return partieTarot().ecartables().getCards();
    }

    @Override
    public IdList<CardTarot> hand() {
        GameTarot partie_=partieTarot();
        //Les regles du tarot ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        HandTarot mainUtilisateur_=partie_.mainUtilisateurTriee(getDisplayingTarot());
        return mainUtilisateur_.getCards();
    }

    @Override
    public AbsPanel getCenterDeck() {
        return tapisTarot().getCenterDeck();
    }

    @Override
    public int getEcart() {
        return tapisTarot().getEcart();
    }

    @Override
    public IdList<CardTarot> discarded() {
        GameTarot partie_=partieTarot();
        HandTarot mainUtilisateur_=new HandTarot();
        mainUtilisateur_.ajouterCartes(partie_.getPliEnCours().getCartes());
        return mainUtilisateur_.getCards();
    }

    @Override
    public String errMessage(IdList<CardTarot> _must, CardTarot _t) {
        if (discarded().containsObj(_t)) {
            return "";
        }
        if (_must.containsObj(_t)) {
            HandTarot all_ = new HandTarot();
            all_.setCards(ecartables());
            all_.supprimerCartes(partieTarot().getPliEnCours().getCartes());
            HandTarot tr_ = all_.couleur(Suit.TRUMP);
            if (_t.getId().getCouleur() != Suit.TRUMP || tr_.total() >= all_.total()) {
                return "";
            }
        }
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        String mesCard_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_DISCARD), Games.toString(_t,lg_));
        String mesReason_ = Games.autoriseMessEcartDe(GameTarot.reasonDiscard(_t),_t,lg_).toString();
        return StringUtil.concat(mesCard_,ContainerGame.RETURN_LINE,mesReason_);
    }

    public void afterHands() {
        GameTarot partie_=partieTarot();
        if (partie_.getRegles().getDiscardAfterCall()) {
//            JPanel boutons_=getPanneauBoutonsJeu();
//            LabelButton valide_=(LabelButton)boutons_.getComponent(0);
//            getValidateDog().setEnabled(partie_.getPliEnCours().total()==partie_.getDistribution().derniereMain().total());
            //            if(boutons_.getComponentCount()==2) {
//                boutons_.remove(1);
//            }
//            getSlamButton().setVisible(false);
            boolean chienFait_ = partie_.getPliEnCours().total()== partie_.getDistribution().derniereMain().total();
//            getValidateDog().setEnabled(chienFait_);
//            getSlamButton().setEnabled(chienFait_);
            updateButtons(chienFait_);
        } else {
            updateCardsInPanelTarotCallAfterDog();
            updateButtons();
        }
        pack();
    }

    public void updateButtons() {
        GameTarot partie_=partieTarot();
        boolean chienFait_;
        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            chienFait_ = partie_.getPliEnCours().total() == partie_.getDistribution().derniereMain().total() && getCalledCard() != CardTarot.WHITE;
        } else {
            chienFait_ = getCalledCard() != CardTarot.WHITE;
        }
        updateButtons(chienFait_);
    }

    private void updateButtons(boolean _chienFait) {
        GameTarot partie_=partieTarot();
        getValidateDog().setEnabled(_chienFait);
        boolean slam_ = _chienFait && partie_.getContrat() != BidTarot.SLAM;
        getSlamButton().setEnabled(slam_);
        if (partie_.getRegles().getDiscardAfterCall()) {
            MenuItemUtils.setEnabledMenu(getConsulting(),partie_.getPliEnCours().estVide()||slam_);
        }
    }

    @Override
    public void displayTrumps() {
        GameTarot partie_=partieTarot();
        displayTrumpsForHandful(GameTarotCommonPlaying.atoutsPoignee(partie_.getDistribution().hand().couleurs()));
    }

    @Override
    public int required() {
        GameTarot partie_=partieTarot();
        RulesTarot regles_ = partie_.getRegles();
        return regles_.getAllowedHandfuls().getVal(getChoosenHandful());
    }

    public void displayTrumpsForHandful(HandTarot _trumps) {
        displayTrumpsForHandful(this,_trumps);
//        getScrollDeclaringHandful().setVisible(!_trumps.estVide());
//        if (getCurrentIncludedTrumps().estVide() && getCurrentExcludedTrumps().estVide()) {
//            setCurrentIncludedTrumps(_trumps);
//        }
//        getCurrentIncludedTrumps().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
//        getCurrentExcludedTrumps().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
//        updateCardsInPanelTarotHandful(this);
        //pack();
//        getDeclaringHandful().setDividerLocation(getDeclaringHandful().getWidth()*9/10);
    }

    public void updateCardsInPanelTarotCallBeforeDog(boolean _canCall) {
        getPanelCallableCards().removeAll();
        GameTarot partie_ = partieTarot();
        HandTarot callableCards_ = partie_.callableCards();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (GraphicCard<CardTarot> c: getGraphicCards(getWindow(),lg_,callableCards_.getCards())) {
            if (_canCall) {
                c.addMouseListener(new ListenerCardTarotSingleCallBeforeDog(this,c.getCard()));
            }
            getPanelCallableCards().add(c.getPaintableLabel());
        }
//        boolean entered_ = false;
//        for(CardTarot c:_hand) {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
////            carte_.addMouseListener(new EcouteurCarteTarotCallBeforeDog(c));
//            carte_.addMouseListener(new ListenerCardTarotSingleBeforeDog(this,c));
//            _panel.add(carte_);
//            entered_ = true;
//        }
        getPanelCallableCards().setSize(getPanelCallableCards().getPreferredSizeValue());
    }
    public void updateCardsInPanelTarotCallAfterDog() {
        getPanelCallableCards().removeAll();
        GameTarot partie_ = partieTarot();

        HandTarot callableCards_ = partie_.callableCards();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (GraphicCard<CardTarot> c: getGraphicCards(getWindow(),lg_,callableCards_.getCards())) {
//            if (_canCall) {
//                if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
//                    if (partie_.getPliEnCours().total() != partie_.getDistribution().derniereMain().total()) {
//                        int remove_ = partie_.getDistribution().derniereMain().total();
//                        remove_ -= partie_.getPliEnCours().total();
//                        String mesCard_ = StringUtil.simpleNumberFormat(file().getVal(MessagesGuiCards.MAIN_HAS_TO_DISCARD), remove_);
//                        c.getPaintableLabel().setToolTipText(mesCard_);
//                    } else {
//                        c.addMouseListener(new ListenerCardTarotSingleCallAfterDog(this,c.getCard()));
//                    }
//                } else {
//                    c.addMouseListener(new ListenerCardTarotSingleCallAfterDog(this,c.getCard()));
//                }
//                c.addMouseListener(new ListenerCardTarotSingleCallAfterDog(this,c.getCard()));
//            }
            c.addMouseListener(new ListenerCardTarotSingleCallAfterDog(this,c.getCard()));
            border(c);
            getPanelCallableCards().add(c.getPaintableLabel());
        }
        getPanelCallableCards().setSize(getScrollCallableCards().getPreferredSizeValue());
    }

    private void border(GraphicCard<CardTarot> _c) {
        if (calledCard == _c.getCard()) {
            _c.getPaintableLabel().setLineBorder(GuiConstants.RED);
        } else {
            _c.getPaintableLabel().setLineBorder(GuiConstants.BLACK);
        }
    }

    public CardTarot getCalledCard() {
        return calledCard;
    }

    public void setCalledCard(CardTarot _c) {
        this.calledCard = _c;
    }

    public void updateCardsInPanelTarotJeu(boolean _ecouteur) {
        GameTarot partie_=partieTarot();
        //Les regles du tarot ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        HandTarot mainUtilisateur_=partie_.mainUtilisateurTriee(getDisplayingTarot());
        getPanelHand().removeAll();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        String err_ = errorHandful(_ecouteur);
        HandTarot auto_;
        if (_ecouteur) {
            auto_ = partie_.autorise();
        } else {
            auto_ = new HandTarot();
        }
        for (GraphicCard<CardTarot> c: getGraphicCards(getWindow(),lg_,mainUtilisateur_.getCards())) {
            if (_ecouteur) {
                if (err_.isEmpty() && auto_.contient(c.getCard())) {
                    c.addMouseListener(new ListenerCardTarotSingleGame(this, c.getCard()));
                } else {
                    String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(c.getCard(),lg_));
                    String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,Games.autoriseTarot(partie_, lg_));
                    if (err_.isEmpty()) {
                        c.getPaintableLabel().setToolTipText(finalMessage_);
                    } else {
                        c.getPaintableLabel().setToolTipText(StringUtil.concat(finalMessage_,ContainerGame.RETURN_LINE,err_));
                    }
                }
            }
            getPanelHand().add(c.getPaintableLabel());
        }
        getPanelHand().setSize(getPanelHand().getPreferredSizeValue());
//        boolean entered_ = false;
//        for(CardTarot c: _hand)
//        {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(!entered_);
////            carte_.addMouseListener(new EcouteurCarteTarotJeu(_hand.carte(indice_)));
//            carte_.addMouseListener(new ListenerCardTarotSingleGame(this, c));
//            _panel.add(carte_);
//            entered_ = true;
//        }
    }
    private String errorHandful(boolean _ecouteur){
        RulesTarot regles_=partieTarot().getRegles();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        String finalMessageHandful_;
        if (_ecouteur) {
            Handfuls ch_ = getChoosenHandful();
            if (ch_ != Handfuls.NO) {
                HandTarot handful_ = getCurrentIncludedTrumps();
                if (!GameTarot.isValidHandful(regles_,ch_, handful_, getCurrentExcludedTrumps())) {
                    String messErr_ = Games.isValidHandfulMessage(regles_, ch_, handful_, getCurrentExcludedTrumps(), lg_);
                    String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_DECLARE_DETAIL), Games.toString(ch_,lg_));
                    finalMessageHandful_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,messErr_);
                } else {
                    finalMessageHandful_ ="";
                }
            } else {
                finalMessageHandful_ = "";
            }
        } else {
            finalMessageHandful_ = "";
        }
        return finalMessageHandful_;
    }
    @Override
    public void conseil() {
        GameTarot partie_=partieTarot();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if(partie_.keepBidding()) {
            String message_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CONSULT_TAROT_BID), Games.toString(getOwner().baseWindow().getIa().getTarot().strategieContrat(partie_),lg_));
            ajouterTexteDansZoneConseil(message_);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else if(partie_.isCallingState()) {
            if (!partie_.getRegles().getDiscardAfterCall()) {
                if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                    CallDiscard cartesAppeler_ = getOwner().baseWindow().getIa().getTarot().strategieAppelApresEcart(partie_,true);
                    String message_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CONSULT_TAROT_CALL),
                            Games.toString(cartesAppeler_.getCarteAppelee(),lg_),RETURN_LINE);
                    message_ = StringUtil.concat(message_, StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CONSULT_TAROT_DISCARD), Games.toString(cartesAppeler_.getEcartAFaire(),lg_)));
//                    ajouterTexteDansZoneConseil(message_);
                    slamConsult(cartesAppeler_.isChelem(), message_);
                    //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
                } else {
                    HandTarot cartesAppeler_ = getOwner().baseWindow().getIa().getTarot().strategieAppel(partie_);
//                    if(cartesAppeler_.estVide()) {
//                        return;
//                    }
                    String message_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CONSULT_TAROT_CALL), Games.toString(cartesAppeler_,lg_));
                    ajouterTexteDansZoneConseil(message_);
                    //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                HandTarot cartesAppeler_ = getOwner().baseWindow().getIa().getTarot().strategieAppel(partie_);
//                if(cartesAppeler_.estVide()) {
//                    return;
//                }
                String message_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CONSULT_TAROT_CALL), Games.toString(cartesAppeler_,lg_));
                ajouterTexteDansZoneConseil(message_);
                //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(partie_.getPliEnCours().estVide() && partie_.getTricks().isEmpty()) {
//        } else if(partie_.getPliEnCours().estVide() && !partie_.getPliEnCours().getVuParToutJoueur()) {
            String message_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CONSULT_TAROT_DISCARD), Games.toString(getOwner().baseWindow().getIa().getTarot().strategieEcart(partie_),lg_));
            ajouterTexteDansZoneConseil(message_);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else if(partie_.getTricks().isEmpty()) {
//        } else if(partie_.getContrat()!=BidTarot.SLAM && partie_.getTricks().isEmpty()) {
//        } else if(partie_.getContrat()!=BidTarot.SLAM && (partie_.getTricks().isEmpty() || !partie_.getPliEnCours().getVuParToutJoueur())) {
            slamConsult(getOwner().baseWindow().getIa().getTarot().annoncerUnChelem(partie_), "");
//            ajouterTexteDansZone(getWindow().getCommonFrame(),EMPTY_STRING,getMessages().getVal(WindowCards.CONSULT_TITLE), GuiConstants.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),partie_.getRaison(),getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else {
            String message_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CONSULT_PLAYER), Games.toString(getOwner().baseWindow().getIa().getTarot().changerConfianceJeuCarteUniqueQuick(partie_),lg_));
            ajouterTexteDansZoneConseil(message_);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        }

    }
    private void slamConsult(boolean _slam, String _added) {
        String add_;
        if (_added.isEmpty()) {
            add_ = "";
        } else {
            add_ = _added + RETURN_LINE;
        }
        if (_slam) {
            ajouterTexteDansZoneConseil(add_+file().getVal(MessagesGuiCards.MAIN_CONSULT_TAROT_SLAM)+RETURN_LINE);
        } else {
            ajouterTexteDansZoneConseil(add_+file().getVal(MessagesGuiCards.MAIN_CONSULT_TAROT_NO_SLAM)+RETURN_LINE);
        }
    }

    public void showTeams() {
        GameTarot game_ = partieTarot();
        TeamsPlayers teams_ = new TeamsPlayers();
        teams_.setTeams(game_.getTeamsRelation().teams());
        win.getModal().set(true);
        DialogTeamsPlayers.initDialogTeamsPlayers(getOwner());
        DialogTeamsPlayers.setDialogTeamsPlayers(pseudosTarot(), teams_, getOwner().getDialogTeamsPlayers(), getOwner().getCompoFactory());
    }
    @Override
    public void showTricksHands() {
        GameTarot game_=partieTarot();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.tricks(game_);
        WindowCardsInt ow_ = getOwner();
        win.getModal().set(true);
        DialogTricksTarot.setDialogTricksTarot(file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS_TAROT), ow_);
        DialogTricksTarot.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosTarot(), getDisplayingTarot(),ow_);
    }
    @Override
    public void aideAuJeu() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        GameTarot partie_=partieTarot();
        HandTarot mainUtilisateur_=partie_.getDistribution().hand();
        IntGameTarot ia_ = getOwner().baseWindow().getIa().getTarot();
        GameTarotTrickInfo doneTrickInfo_ = ia_.gameTarotTrickInfo(partie_);
        HandTarot cartesJouees_= GameTarotTrickInfo.enCours(partie_);
        IdMap<Suit,HandTarot> repartitionCartesJouees_=cartesJouees_.couleurs();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_= ia_.cartesPossibles(doneTrickInfo_,mainUtilisateur_);
        win.getDialogHelpTarot().setTitleDialog(win, StringUtil.concat(file().getVal(MessagesGuiCards.MAIN_HELP_GAME),SPACE,GameEnum.TAROT.toString(lg_)));
        IdMap<Hypothesis,IdMap<Suit,CustList<HandTarot>>> hypotheses_ = ia_.cartesCertaines(doneTrickInfo_,cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_= hypotheses_.getVal(Hypothesis.SURE);
        win.getModal().set(true);
        win.getDialogHelpTarot().setDialogueTarot(cartesPossibles_,cartesCertaines_,repartitionCartesJouees_,pseudosTarot(), lg_);
    }

    public BidTarot getContratUtilisateur() {
        return contratUtilisateur;
    }

    public void setContratUtilisateur(BidTarot _contratUtilisateur) {
        contratUtilisateur = _contratUtilisateur;
    }

    @Override
    public void firstTrick() {
        GameTarot partie_ = partieTarot();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain(), getOwner());
        tapisTarot().setCartesTarotJeu(getWindow().getImageFactory(),lg_,partie_.getNombreDeJoueurs());
        getScrollCallableCards().setVisible(false);
        beforeCardPlaying();
        changeEnable();
//        partieTarot().firstLead();
//        if(partie_.premierTour()) {
//            byte donneur_=partie_.getDistribution().getDealer();
//            if(!partie_.chelemAnnonce()) {
//                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
//                partie_.setEntameur(partie_.playerAfter(donneur_));
//            }
//            partie_.setPliEnCours(true);
//        }

        launchAnimCards();
    }

    @Override
    public void nextTrick() {
        GameTarot partie_ = partieTarot();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain(), getOwner());
        tapisTarot().setCartesTarotJeu(getWindow().getImageFactory(),lg_,partie_.getNombreDeJoueurs());
        getScrollCallableCards().setVisible(false);
        changeEnable();
//        if(partie_.premierTour()) {
//            byte donneur_=partie_.getDistribution().getDealer();
//            if(!partie_.chelemAnnonce()) {
//                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
//                partie_.setEntameur(partie_.playerAfter(donneur_));
//            }
//            partie_.setPliEnCours(true);
//        }

        launchAnimCards();
    }

    @Override
    public void endDeal() {
        finPartieTarot();
        pack();
    }

    @Override
    public void keepPlayingRandom() {
        setChangerPileFin(true);
        modify();
    }

    @Override
    public void keepPlayingEdited() {
//        GameTarot partie_=partieTarot();
//        HandTarot main_=partie_.empiler();
//        DealTarot donne_=new DealTarot(0L,main_);
//        donne_.donneurSuivant(partie_.getDistribution().getDealer(),partie_.getRegles());
//        donne_.initDonne(partie_.getRegles(),getOwner().getGenerator());
//        getPar().jouerTarot(new GameTarot(GameType.EDIT,donne_,partie_.getRegles()));
        GameTarot partie_=partieTarot();
        DealTarot donne_=getOwner().baseWindow().getIa().getTarot().empiler(0L, partie_,getOwner().getGenerator());
        getPar().jouerTarot(new GameTarot(GameType.RANDOM,donne_,partie_.getRegles()));
        partieTarot().setNombre();
        mettreEnPlaceIhmTarot();
    }

    @Override
    public void stopPlaying() {
        win.menuSoloGames();
    }

    @Override
    public void replay() {
        GameTarot partie_=partieTarot();
        partie_.restituerMainsDepartRejouerDonne();
        mettreEnPlaceIhmTarot();
    }

    @Override
    public WindowCards window() {
        return win;
    }

    public ContainerSinglePausableContent<CardTarot> getContentPausable() {
        return contentPausable;
    }

    public PanelTricksHandsTarot getPanelTricksHandsTarot() {
        return panelTricksHandsTarot;
    }
}

