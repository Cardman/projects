package cards.gui.containers;






import cards.consts.GameType;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.animations.AddTextEvents;
import cards.gui.animations.AnimationCardPresident;
import cards.gui.animations.SettingPresidentHand;
import cards.gui.animations.SettingPresidentStatus;
import cards.gui.containers.events.GiveCardsEvent;
import cards.gui.containers.events.NoPlayPresidentEvent;
import cards.gui.containers.events.ReplayEvent;
import cards.gui.containers.events.StopPlayingEvent;
import cards.gui.dialogs.*;
import cards.gui.events.ListenerCardPresidentSingleGame;
import cards.gui.labels.GraphicCard;
import cards.gui.panels.CarpetPresident;
import cards.gui.panels.PanelTricksHandsPresident;
import cards.main.CardNatLgNamesNavigation;
import cards.main.CardsNonModalEvent;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import cards.president.TrickPresident;
import cards.president.TricksHandsPresident;
import cards.president.beans.PresidentStandards;
import cards.president.enumerations.CardPresident;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class ContainerSinglePresident extends ContainerPresident implements
        ContainerSin,ContainerSingle<CardPresident>,ContainerPlayablePresident {
    private AbsButton replayButton;
    private AbsButton stopButton;
    //    private boolean clickedDiscard;
//    private boolean clickedNoPlay;
    private final WindowCards win;
    public ContainerSinglePresident(WindowCards _window) {
        super(_window);
        update(_window);
        win = _window;
    }
    public HandPresident userHand() {
        GamePresident partie_=partiePresident();
        return partie_.mainUtilisateurTriee(getDisplayingPresident());
    }
//    public ContainerSinglePresident(WindowCards _window, String _nomFichier) {
//        super(_window);
//    }

    public GamePresident partiePresident() {
        return getPar().partiePresident();
    }

    public void jouerPresident(String _pseudo) {
        GamePresident partie_=partiePresident();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        HandPresident h_ = partie_.addCardsToCurrentTrick(getOwner().baseWindow().getIa().getPresident());
        getOwner().getCompoFactory().invokeNow(new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(h_,lg_),RETURN_LINE)));
//        ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+h_+RETURN_LINE_CHAR);
        getOwner().getCompoFactory().invokeNow(new SettingPresidentStatus(this, partie_.getLastStatus(), partie_.nextPlayer()));
//        tapisPresident().setStatus(partie_.getLastStatus(), partie_.getNextPlayer());
//        tapisPresident().repaintValidate();
        getOwner().getCompoFactory().invokeNow(new SettingPresidentHand(this, h_));
//        tapisPresident().setTalonPresident(h_);
//        tapisPresident().repaintValidate();
    }

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
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        setChangerPileFin(false);
//        setaJoueCarte(false);
        GamePresident partie_=partiePresident();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        getOwner().setTitle(GameEnum.PRESIDENT.toString(lg_));
        placerPresident();
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        if (!partie_.keepPlayingCurrentGame()) {
            finPartiePresident();
            pack();
            return;
        }
        if (partie_.availableSwitchingCardsNotReady()) {
            MenuItemUtils.setEnabledMenu(getConsulting(),true);
            getReceivedCards().supprimerCartes();
            getReceivedCards().ajouterCartes(partie_.getSwitchedCards().get(partie_.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
            updateCardsInPanelPresidentReceived();
            getGivenCards().supprimerCartes();
            getVirtualHand().supprimerCartes();
            getVirtualHand().ajouterCartes(partie_.mainUtilisateurTriee(getDisplayingPresident()));
            updateCardsInPanelPresidentDiscard(this);
            addButtonsForDiscard();
            pack();
            return;
        }
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        if (partie_.availableSwitchingCards()) {
            fetchLooser(partie_);
            fetchWinner(partie_);
        }
        getNoPlay().setVisible(true);
        getNoPlay().setEnabled(false);
        tapisPresident().setStatus(getWindow().getImageFactory(),lg_,partie_.getLastStatus(), partie_.nextPlayer());
//            tapisPresident().repaintValidate();
        if (!partie_.getProgressingTrick().estVide()) {
            tapisPresident().setTalonPresident(getWindow().getImageFactory(),lg_,partie_.getProgressingTrick().getBestCards());
//                tapisPresident().repaintValidate();
        }
//        if (partie_.nextPlayer() == DealPresident.NUMERO_UTILISATEUR) {
//            tapisPresident().setStatus(getWindow().getImageFactory(),lg_,partie_.getLastStatus(), partie_.nextPlayer());
////            tapisPresident().repaintValidate();
//            if (!partie_.getProgressingTrick().estVide()) {
//                tapisPresident().setTalonPresident(getWindow().getImageFactory(),lg_,partie_.getProgressingTrick().getBestCards());
////                tapisPresident().repaintValidate();
//            }
//            placerBoutonsAvantJeuUtilisateurPresident();
//            pack();
//            return;
//        }
        afficherMainUtilisateurPresident(false);
        pack();
        thread(new AnimationCardPresident(this));
    }

    private void fetchWinner(GamePresident _g) {
        fetchWinner(this,_g);
//        Bytes w_ = _g.getWinners(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
//        if (!w_.isEmpty()) {
//            getReceivedCards().supprimerCartes();
//            getReceivedCards().ajouterCartes(_g.getSwitchedCards().get(_g.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
//            updateCardsInPanelPresidentReceived();
//            getGivenCards().supprimerCartes();
//            getGivenCards().ajouterCartes(_g.getSwitchedCards().get(DealPresident.NUMERO_UTILISATEUR));
//            updateCardsInPanelPresidentGiven();
//        }
    }

    private void fetchLooser(GamePresident _g) {
        fetchLooser(this,_g);
//        Bytes l_ = _g.getLoosers(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
//        if (!l_.isEmpty()) {
//            getReceivedCards().supprimerCartes();
//            getReceivedCards().ajouterCartes(_g.getSwitchedCards().get(_g.getMatchingWinner(DealPresident.NUMERO_UTILISATEUR)));
//            updateCardsInPanelPresidentReceived();
//            getGivenCards().supprimerCartes();
//            getGivenCards().ajouterCartes(_g.getSwitchedCards().get(DealPresident.NUMERO_UTILISATEUR));
//            updateCardsInPanelPresidentGiven();
//        }
    }

    public void addButtonsForDiscard() {
//        clickedDiscard = false;
        setGivingCardsOk(getOwner().getCompoFactory().newPlainButton(file().getVal(MessagesGuiCards.MAIN_OK)));
        getGivingCardsOk().setEnabled(false);
        getGivingCardsOk().addActionListener(new CardsNonModalEvent(this),new GiveCardsEvent(this));
        //        getPanneauBoutonsJeu().add(getGivingCardsOk());
        getActionsHistory().add(getGivingCardsOk());
    }

    @Override
    public void discard(byte _index) {
        super.discard(_index);
        updateCardsInPanelPresidentDiscard(this);
        updateDiscarding();
        pack();
    }

    @Override
    public void cancelDiscard(byte _index) {
        super.cancelDiscard(_index);
        updateCardsInPanelPresidentDiscard(this);
        updateDiscarding();
        pack();
    }

    private void updateDiscarding() {
        int max_ = partiePresident().numberGivenCards(DealPresident.NUMERO_UTILISATEUR);
        getGivingCardsOk().setEnabled(max_ == getGivenCards().total());
    }

    @Override
    public void discard() {
        //The deal is now ready
        getGivingCardsOk().setVisible(false);
        GamePresident g_ = partiePresident();
        g_.giveWorstCards(getOwner().baseWindow().getIa().getPresident().strategieEchangeUser(getGivenCards()));
        afficherMainUtilisateurPresident(false);
        getNoPlay().setVisible(true);
        getNoPlay().setEnabled(false);
        pack();
        thread(new AnimationCardPresident(this));
    }

//    public void addButtonNextTrickPresident(String _texte,boolean _apte) {
//        AbsPanel panneau_=getPanneauBoutonsJeu();
//        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new CardsNonModalEvent(this),new NextTrickEvent(this));
//        bouton_.setEnabled(_apte);
//        panneau_.add(bouton_);
//    }
//    public void addButtonEndDealPresident(String _texte,boolean _apte) {
//        AbsPanel panneau_=getPanneauBoutonsJeu();
//        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new CardsNonModalEvent(this),new EndDealEvent(this));
//        bouton_.setEnabled(_apte);
//        panneau_.add(bouton_);
//    }
//    private void addButtonKeepPlayingDealPresident(AbsPanel _panneau,String _texte) {
//        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new CardsNonModalEvent(this),new KeepPlayingRandomEvent(this));
//        _panneau.add(bouton_);
//    }
//    private void addButtonKeepPlayingEditedDealPresident(AbsPanel _panneau,String _texte) {
//        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new CardsNonModalEvent(this),new KeepPlayingEditedEvent(this));
//        _panneau.add(bouton_);
//    }
    private AbsButton addButtonStopPlayingPresident(AbsPanel _panneau,String _texte) {
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(this),new StopPlayingEvent(this));
        _panneau.add(bouton_);
        return bouton_;
    }
    private AbsButton addButtonReplayDealPresident(AbsPanel _panneau,String _texte) {
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new CardsNonModalEvent(this),new ReplayEvent(this));
        _panneau.add(bouton_);
        return bouton_;
    }

    public void placerBoutonsAvantJeuUtilisateurPresident() {
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
//        setRaisonCourante(EMPTY);
        afficherMainUtilisateurPresident(true);
        getPanneauBoutonsJeu().removeAll();
        updateCardsInPanelPresidentReceived();
        updateCardsInPanelPresidentGiven();
        getPanneauBoutonsJeu().add(getPanelGivenCards());
        getPanneauBoutonsJeu().add(getPanelReceivedCards());
        GamePresident g_ = partiePresident();
        noPlayText(g_.getStatus());
        boolean enabled_ = !g_.getProgressingTrick().estVide();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if (!g_.canPass()) {
            getNoPlay().setToolTipText(Games.canPassMess(g_, lg_));
            getNoPlay().setEnabled(false);
        } else {
            getNoPlay().setEnabled(enabled_);
        }
        //        getPanneauBoutonsJeu().add(getNoPlay());
        getPanneauBoutonsJeu().validate();
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
    }

//    public void placerBoutonsFinPliUtilisateurPresident() {
//        //Activer les conseils
//        MenuItemUtils.setEnabledMenu(getConsulting(),false);
//        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
//        GamePresident partie_=partiePresident();
//        if(!partie_.keepPlayingCurrentGame()) {
//            addButtonEndDealPresident(file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
//        } else {
//            addButtonNextTrickPresident(file().getVal(MessagesGuiCards.MAIN_NEXT_TRICK), true);
//        }
//    }

    public void editerPresident(GamePresident _partie) {
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
//        setPasse(false);
//        getPaused().set(PAUSE_ALIVE);
        window().getPausingCardsAnims().alive(this);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
//        setaJoueCarte(false);
//        setPartieSauvegardee(false);
        getPar().jouerPresident(_partie);
        //Desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
        window().changeMenuSimuEnabled(false);
        setChangerPileFin(false);
        mettreEnPlaceIhmPresident();
    }

    private void placerPresident() {
        //Activer le menu Fichier/Sauvegarder
        MenuItemUtils.setEnabledMenu(getSave(),true);
        //Activer le menu Fichier/Changer de mode
        MenuItemUtils.setEnabledMenu(getChange(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        //Desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
        window().changeMenuSimuEnabled(false);
        placerIhmPresident();
    }

    private void placerIhmPresident() {
        getPane().removeAll();
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        container_.add(getOwner().getCompoFactory().newPlainLabel(helpMenuTip()),GuiConstants.BORDER_LAYOUT_NORTH);
        GamePresident partie_=partiePresident();
        RulesPresident rules_ = partie_.getRules();
        CarpetPresident tapis_=new CarpetPresident();
        StringList pseudos_ = pseudosPresident();
        int nbMax_ = rules_.getNbStacks() * Suit.couleursOrdinaires().size();
        tapis_.initTapisPresident(lg_,pseudos_, partie_.getLastStatus(), NumberUtil.min(nbMax_, rules_.getNbMaxCardsPerPlayer()), getOwner().getCompoFactory());
        getTapis().setTapisPresident(tapis_);
        container_.add(tapis_.getContainer(),GuiConstants.BORDER_LAYOUT_CENTER);
//        AbsPanel panneau_ = panelHand();
//        AbsPanel panneau_ = getOwner().getCompoFactory().newLineBox();
//        panneau_.setBackground(GuiConstants.BLUE);
//        setPanelHand(panneau_);
//        panelHand(panneau_);
        container_.add(panelHand(),GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=getOwner().getCompoFactory().newPageBox();
//        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY,8, 30));
//        getEvents().setEditable(false);
//        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
        panneau2_.add(events());
        initHandfuls();
//        setHandfuls(new ByteMap<AbsPlainLabel>());
//        setDeclaredHandfuls(new ByteMap<AbsPanel>());
//        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0,1));
//        int nbPlayers_ = partie_.getNombreDeJoueurs();
        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
//        AbsPanel panelCards_ = getOwner().getCompoFactory().newLineBox();
        AbsPanel panelDiscard_ = getOwner().getCompoFactory().newLineBox();
        panelDiscard_.setTitledBorder(file().getVal(MessagesGuiCards.MAIN_GIVEN_CARDS));
//        panelCards_.add(panelDiscard_);
        setPanelGivenCards(panelDiscard_);
        AbsPanel panelRec_ = getOwner().getCompoFactory().newLineBox();
        panelRec_.setTitledBorder(file().getVal(MessagesGuiCards.MAIN_RECEIVED_CARDS));
//        panelCards_.add(panelRec_);
        setPanelReceivedCards(panelRec_);
        sousPanneau_.add(panelDiscard_);
        sousPanneau_.add(panelRec_);
        setNoPlay(getOwner().getCompoFactory().newPlainButton(EMPTY));
        getNoPlay().addActionListener(new CardsNonModalEvent(this),new NoPlayPresidentEvent(this));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(sousPanneau_));
        getNoPlay().setVisible(false);
        panneau2_.add(getNoPlay());
        setActionsHistory(panneau2_);
        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
        tapisPresident().setTalonPresident(getWindow().getImageFactory());
//        tapisPresident().repaintValidate();
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newAbsScrollPane(container_));
        panel_.add(getWindow().getClock());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }

    private void mettreEnPlaceIhmPresident() {
        placerPresident();
        afficherMainUtilisateurPresident(false);
        GamePresident game_ = partiePresident();
        game_.initCartesEchanges();
        game_.donnerMeilleuresCartes();
        if (game_.availableSwitchingCards()) {
            Bytes w_ = game_.getWinners(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
            if (!w_.isEmpty()) {
                game_.giveWorstCards(getOwner().baseWindow().getIa().getPresident(),w_);
                getReceivedCards().supprimerCartes();
                getReceivedCards().ajouterCartes(game_.getSwitchedCards().get(game_.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
                updateCardsInPanelPresidentReceived();
                getGivenCards().supprimerCartes();
                getVirtualHand().supprimerCartes();
                getVirtualHand().ajouterCartes(game_.mainUtilisateurTriee(getDisplayingPresident()));
                updateCardsInPanelPresidentDiscard(this);
                addButtonsForDiscard();
                pack();
                return;
            }
            game_.giveWorstCards(getOwner().baseWindow().getIa().getPresident());
            fetchLooser(game_);
        } else {
            game_.giveWorstCards(getOwner().baseWindow().getIa().getPresident());
        }
        getNoPlay().setVisible(true);
        getNoPlay().setEnabled(false);
        pack();
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        thread(new AnimationCardPresident(this));
    }

    @Override
    public void modify() {
        //Activer le menu Fichier/Sauvegarder
        MenuItemUtils.setEnabledMenu(getSave(),true);
        //Activer le menu Fichier/Changer de mode
        MenuItemUtils.setEnabledMenu(getChange(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.PRESIDENT, getOwner().getFrames(), getReglesPresident().getNbStacks());
        if(nb_==0||!getPar().enCoursDePartiePresident()) {
            setChangerPileFin(true);
            getPar().jouerPresident(getOwner().baseWindow().getFirstDealPresident().deal(this, getReglesPresident(),nb_));
        } else {
            GamePresident partie_=partiePresident();
            Bytes newRanks_ = partie_.getNewRanks();
            DealPresident donne_=getOwner().baseWindow().getIa().getPresident().empiler(nb_,partie_,getOwner().getGenerator());
            getPar().jouerPresident(new GamePresident(GameType.RANDOM,donne_, partie_.getRules(), newRanks_));
        }
        mettreEnPlaceIhmPresident();
    }

//    private void debutPliPresident() {
//        //Activer le sous-menu conseil
//        MenuItemUtils.setEnabledMenu(getConsulting(),false);
//        //Activer le sous-menu aide au jeu
//        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
//        /*Si on n'a pas encore fait de pli a la belote*/
//        /*On affiche la main de l'utilisateur avec des ecouteurs sur les cartes et on supprime tous les boutons de l'ihm places a droite avant d'executer un eventuel Thread*/
//        AbsPanel panneau_=getPanneauBoutonsJeu();
//        panneau_.removeAll();
//        panneau_.add(assemble());
////        panneau_.add(getNoPlay());
////        getNoPlay().setVisible(true);
////        getActionsHistory().repaint();
////        getActionsHistory().validate();
//        panneau_.validate();
////        setRaisonCourante(getMessages().getVal(WindowCards.WAIT_TURN));
//        setThreadAnime(true);
//        thread(new AnimationCardPresident(this));
//    }

    private void afficherMainUtilisateurPresident(boolean _ecouteur) {
//        if (!_ecouteur) {
//            setCarteEntree(false);
//            setCarteSortie(false);
//        }
        GamePresident partie_=partiePresident();
        //Les regles de la belote ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        HandPresident mainUtilisateur_=partie_.mainUtilisateurTriee(getDisplayingPresident());
        /*On place les cartes de l'utilisateur*/
        updateCardsInPanelPresident(getPanelHand(),mainUtilisateur_,_ecouteur);
        getWindow().pack();
    }

    @Override
    public void noPlay() {
//        if (!isCanPlay()) {
//            return;
//        }
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        GamePresident partie_=partiePresident();
//        if (!partie_.canPass()) {
//            String title_ = getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE);
//            getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), Games.canPassMess(partie_, lg_), title_, GuiConstants.ERROR_MESSAGE);
//            return;
//        }
        /*L'utilisateur joue sa carte*/
        processUserActions(partie_.noPlay(getOwner().baseWindow().getIa().getPresident()));
    }

    public void finPliPresident(CardPresident _carteJouee, byte _index) {
        GamePresident partie_=partiePresident();
        /*L'utilisateur joue sa carte*/
        processUserActions(partie_.addCardsToCurrentTrick(getOwner().baseWindow().getIa().getPresident(), _carteJouee, _index));
    }

    private void processUserActions(HandPresident _played) {
        GamePresident partie_=partiePresident();
        //Activer le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(getPause(),true);
        //Desactiver le sous-menu conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        ajouterTexteDansZone(StringUtil.concat(pseudo(),INTRODUCTION_PTS,Games.toString(_played,lg_),RETURN_LINE));
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
//        setRaisonCourante(getMessages().getVal(WindowCards.END_TRICK));
        afficherMainUtilisateurPresident(false);
        tapisPresident().setTalonPresident(getWindow().getImageFactory(),lg_, _played);
//        tapisPresident().repaintValidate();
        tapisPresident().setStatus(getWindow().getImageFactory(),lg_,partie_.getLastStatus(), partie_.nextPlayer());
//        tapisPresident().repaintValidate();
        //Desactiver le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(getPause(),false);
        updateCardsInPanelPresidentReceived();
        updateCardsInPanelPresidentGiven();
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().add(getPanelGivenCards());
        getPanneauBoutonsJeu().add(getPanelReceivedCards());
//        getNoPlay().setVisible(true);
//        getActionsHistory().repaint();
//        getActionsHistory().validate();
        getPanneauBoutonsJeu().validate();
//        setThreadAnime(true);
        thread(new AnimationCardPresident(this));
    }

    public void finPartiePresident() {
        getPane().removeAll();
        /*Descativer aide au jeu*/
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();

        if(isChangerPileFin()) {
            GamePresident partie_=partiePresident();
            getOwner().baseWindow().getFacadeCards().getNicknamesCrud().getCardGamesCrud().president(partie_.getRules().getNbStacks(),partie_.empiler());
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
//        setThreadAnime(false);
        window().changeStreamsMenusEnabled(true);
        StringList pseudos_=new StringList(pseudosPresident());
        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();

        GamePresident partie_=partiePresident();
        if (partie_.getType() == GameType.RANDOM && isChangerPileFin()) {
            changerNombreDeParties(GameEnum.PRESIDENT, partie_.getDeal().getNbDeals(), getOwner().getFrames(), partie_.getRules().getNbStacks());
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();

        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(partie_);
        res_.initialize(new StringList(pseudos_), getScores());
        res_.getRes().setUser(DealPresident.NUMERO_UTILISATEUR);
        Games.setMessages(res_.getRes(),getOwner().getFrames().currentLg());
        setScores(res_.getRes().getScores());

        RenderedPage editor_;
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT).attendreResultat();
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBase(res_);
        editor_ = FrameGeneralHelp.initialize(stds_, getOwner().getFrames());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_RESULTS_PAGE),editor_.getScroll());
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
        GamePresident game_=partiePresident();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        tricksHands_.sortHands(getDisplayingPresident(), game_.getNombreDeJoueurs());
        WindowCardsInt ow_ = getOwner();
        AbsCustComponent panelCards_ = new PanelTricksHandsPresident(ow_.getCommonFrame(), tricksHands_,
                nombreJoueurs_,
                pseudosPresident(),
                getDisplayingPresident(),ow_).getContainer();
        panelCards_.setPreferredSize(new MetaDimension(850,850));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS),panelCards_);
        container_.add(onglets_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=getOwner().getCompoFactory().newPageBox();
        AbsPanel buttons_ = getOwner().getCompoFactory().newLineBox();
        resultButtons(buttons_,this);
//        GameType type_;
//        long nombreParties_;
//        type_=partie_.getType();
//        nombreParties_= partie_.getNumber();
//        int nombreTotalParties_= partie_.getRules().getCommon().getNbDeals();
//        if(type_==GameType.EDIT&&nombreParties_<nombreTotalParties_) {
//            addButtonKeepPlayingEditedDealPresident(buttons_, file().getVal(MessagesGuiCards.MAIN_KEEP_PLAYING_EDITED_DEAL));
//        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&isPartieAleatoireJouee()||type_==GameType.RANDOM) {
//            addButtonKeepPlayingDealPresident(buttons_, file().getVal(MessagesGuiCards.MAIN_KEEP_PLAYING_DEAL));
//        }
        replayButton = addButtonReplayDealPresident(buttons_, file().getVal(MessagesGuiCards.MAIN_REPLAY_DEAL));
        stopButton = addButtonStopPlayingPresident(buttons_, file().getVal(MessagesGuiCards.MAIN_STOP));
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
        return partiePresident().getType();
    }

    @Override
    public long nombreParties() {
        return partiePresident().getNumber();
    }

    @Override
    public long nombreTotalParties() {
        return partiePresident().getRules().getCommon().getNbDeals();
    }

    /**Pseudos utilis&eacute;s*/
    public StringList pseudosPresident() {
        return pseudosPresident(partiePresident().getNombreDeJoueurs());
    }

    private void updateCardsInPanelPresident(AbsPanel _panel, HandPresident _hand, boolean _ecouteur) {
        _panel.removeAll();
        int str_ = 0;
        int iter_ = IndexConstants.FIRST_INDEX;
        byte index_ = IndexConstants.SECOND_INDEX;
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        GamePresident gamePresident_ = partiePresident();
        HandPresident playable_;
        if (_ecouteur) {
            playable_ = gamePresident_.cartesJouables();
        } else {
            playable_ = new HandPresident();
        }
        boolean rev_ = gamePresident_.isReversed();
        for (GraphicCard<CardPresident> c: getGraphicCards(this,_hand.getCards())) {
            int curStr_ = c.getCard().strength(rev_);
            if (iter_ > IndexConstants.FIRST_INDEX) {
                if (curStr_ == str_) {
                    index_++;
                } else {
                    index_ = IndexConstants.SECOND_INDEX;
                }
            }
            if (_ecouteur) {
                if (!playable_.getCardsByStrength(curStr_,rev_).estVide()){
                    c.addMouseListener(new ListenerCardPresidentSingleGame(this,c.getCard(), index_));
                } else {
                    StringBuilder mes_ = new StringBuilder(StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(c.getCard(),lg_)));
                    mes_.append(ContainerGame.RETURN_LINE);
                    mes_.append(Games.autorisePresident(gamePresident_, c.getCard(), index_, lg_));
                    String finalMessage_ = mes_.toString();
                    c.getPaintableLabel().setToolTipText(finalMessage_);
                }
            }
            str_ = curStr_;
            iter_++;
            _panel.add(c.getPaintableLabel());
        }
        _panel.setSize(_panel.getPreferredSizeValue());
    }

    @Override
    public void showTricksHands() {
        GamePresident game_ = partiePresident();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setReversed(game_.isReversed());
//        tricksHands_.setRules(getReglesPresident());
        tricksHands_.setDistributionCopy(game_.getDeal());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        WindowCardsInt ow_ = getOwner();
        win.getModal().set(true);
        DialogTricksPresident.setDialogTricksPresident(file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS_PRESIDENT), ow_);
        DialogTricksPresident.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosPresident(), getDisplayingPresident(),ow_);

    }

    @Override
    public void conseil() {
        GamePresident game_ = partiePresident();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if (game_.availableSwitchingCards()) {
            HandPresident d_ = getOwner().baseWindow().getIa().getPresident().strategieEchange(game_, DealPresident.NUMERO_UTILISATEUR);
            String message_;
            message_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CONSULT_PRESIDENT_GIVE), Games.toString(d_,lg_));
            ajouterTexteDansZoneConseil(message_);
            return;
        }
        HandPresident h_ = getOwner().baseWindow().getIa().getPresident().playedCards(game_);
        String message_;
        if (h_.estVide()) {
            message_ = file().getVal(MessagesGuiCards.MAIN_CONSULT_PRESIDENT_PASS);
        } else {
            message_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CONSULT_PLAYER), Games.toString(h_,lg_));
        }
        ajouterTexteDansZoneConseil(message_);
    }

    @Override
    public void aideAuJeu() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        GamePresident g_ = partiePresident();
        win.getDialogHelpPresident().setTitleDialog(win, StringUtil.concat(file().getVal(MessagesGuiCards.MAIN_HELP_GAME),SPACE,GameEnum.PRESIDENT.toString(lg_)));
        AbsBasicTreeMap<CardPresident, Byte> played_ = g_.getPlayedCardsByStrength();
        boolean reversed_ = g_.isReversed();
        int nbStacks_ = g_.getRules().getNbStacks();
        win.getModal().set(true);
        win.getDialogHelpPresident().setDialoguePresident(played_, reversed_, nbStacks_, getOwner().getFrames().currentLg());
    }

//    @Override
//    public void nextTrick() {
//        tapisPresident().setTalonPresident(getWindow().getImageFactory());
////        tapisPresident().repaintValidate();
//        debutPliPresident();
//    }
//
//    @Override
//    public void endDeal() {
//        finPartiePresident();
//        pack();
//    }

    @Override
    public void keepPlayingRandom() {
        setChangerPileFin(true);
        modify();
    }

    @Override
    public void keepPlayingEdited() {
        GamePresident partie_=partiePresident();
//        HandPresident main_=getOwner().baseWindow().getIa().getPresident().empiler(partie_);
        Bytes newRanks_ = partie_.getNewRanks();
        DealPresident donne_=getOwner().baseWindow().getIa().getPresident().empiler(0L,partie_,getOwner().getGenerator());
//        DealPresident donne_=new DealPresident(0L,main_);
//        donne_.donneurSuivant(partie_.getDeal().getDealer(), partie_.getRules());
//        donne_.initDonne(partie_.getRules(),getOwner().getGenerator());
        getPar().jouerPresident(new GamePresident(GameType.EDIT,donne_, partie_.getRules(),newRanks_));
        partiePresident().setNombre();
        mettreEnPlaceIhmPresident();
    }

    @Override
    public void stopPlaying() {
        win.menuSoloGames();
    }

    @Override
    public void replay() {
        GamePresident partie_=partiePresident();
        CustList<TrickPresident> plisFaits_=partie_.unionPlis();
        partie_.restituerMainsDepartRejouerDonne(plisFaits_, partie_.getNombreDeJoueurs());
        partie_.initPartie();
        mettreEnPlaceIhmPresident();
    }

    @Override
    public WindowCards window() {
        return win;
    }

    public AbsButton getReplayButton() {
        return replayButton;
    }

    public AbsButton getStopButton() {
        return stopButton;
    }
}
