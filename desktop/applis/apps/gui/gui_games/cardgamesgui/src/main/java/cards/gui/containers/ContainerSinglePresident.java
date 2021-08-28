package cards.gui.containers;






import cards.consts.GameType;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.WindowCards;
import cards.gui.animations.AddTextEvents;
import cards.gui.animations.AnimationCardPresident;
import cards.gui.animations.SettingPresidentHand;
import cards.gui.animations.SettingPresidentStatus;
import cards.gui.containers.events.EndDealEvent;
import cards.gui.containers.events.GiveCardsEvent;
import cards.gui.containers.events.KeepPlayingEditedEvent;
import cards.gui.containers.events.KeepPlayingRandomEvent;
import cards.gui.containers.events.NextTrickEvent;
import cards.gui.containers.events.NoPlayPresidentEvent;
import cards.gui.containers.events.ReplayEvent;
import cards.gui.containers.events.StopPlayingEvent;
import cards.gui.dialogs.DialogHelpPresident;
import cards.gui.dialogs.DialogTricksPresident;
import cards.gui.dialogs.FileConst;
import cards.gui.events.ListenerCardPresidentDiscard;
import cards.gui.events.ListenerCardPresidentSingleGame;
import cards.gui.labels.Graphic;
import cards.gui.labels.GraphicKey;
import cards.gui.labels.GraphicPresidentCard;
import cards.gui.panels.CarpetPresident;
import cards.gui.panels.PanelTricksHandsPresident;
import cards.main.LaunchingCards;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import cards.president.TrickPresident;
import cards.president.TricksHandsPresident;
import cards.president.beans.PresidentStandards;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import cards.president.sml.DocumentWriterPresidentUtil;
import code.gui.*;
import code.gui.document.PreparedAnalyzed;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.maths.Rate;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.TreeMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class ContainerSinglePresident extends ContainerPresident implements
        ContainerSingle {

    private AnimationCardPresident animationPlaying;
//    private boolean clickedDiscard;
//    private boolean clickedNoPlay;

    public ContainerSinglePresident(WindowCards _window) {
        super(_window);
    }

    public ContainerSinglePresident(WindowCards _window, String _nomFichier) {
        super(_window);
    }

    public GamePresident partiePresident() {
        return getPar().partiePresident();
    }

    public void jouerPresident(byte _joueur,String _pseudo) {
        GamePresident partie_=partiePresident();
        String lg_ = getOwner().getLanguageKey();
        partie_.addCardsToCurrentTrick(_joueur);
        HandPresident h_ = partie_.getPlayedCards();
        ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(h_,lg_),RETURN_LINE)), getOwner().getFrames());
//        ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+h_+RETURN_LINE_CHAR);
        ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new SettingPresidentStatus(this, partie_.getLastStatus(), partie_.getNextPlayer()), getOwner().getFrames());
//        tapisPresident().setStatus(partie_.getLastStatus(), partie_.getNextPlayer());
//        tapisPresident().repaintValidate();
        ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new SettingPresidentHand(this, h_), getOwner().getFrames());
//        tapisPresident().setTalonPresident(h_);
//        tapisPresident().repaintValidate();
    }

    @Override
    public void load() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabledMenu(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabledMenu(true);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabledMenu(false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        getConsulting().setEnabledMenu(false);
        setChangerPileFin(false);
        setaJoueCarte(false);
        GamePresident partie_=partiePresident();
        String lg_ = getOwner().getLanguageKey();
        getOwner().setTitle(GameEnum.PRESIDENT.toString(lg_));
        placerPresident();
        getHelpGame().setEnabledMenu(false);
        if (partie_.availableSwitchingCards() && !partie_.readyToPlay()) {
            setCanDiscard(true);
            getConsulting().setEnabledMenu(true);
            getReceivedCards().supprimerCartes();
            getReceivedCards().ajouterCartes(partie_.getSwitchedCards().get(partie_.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
            updateCardsInPanelPresidentReceived();
            getGivenCards().supprimerCartes();
            getVirtualHand().supprimerCartes();
            getVirtualHand().ajouterCartes(partie_.mainUtilisateurTriee(getDisplayingPresident()));
            updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
            updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
            addButtonsForDiscard();
            pack();
            return;
        }
        if (!partie_.keepPlayingCurrentGame()) {
            finPartiePresident();
            pack();
            return;
        }
        getHelpGame().setEnabledMenu(true);
        if (partie_.availableSwitchingCards()) {
            Bytes l_ = partie_.getLoosers(new Bytes(DealPresident.NUMERO_UTILISATEUR));
            if (!l_.isEmpty()) {
                getReceivedCards().supprimerCartes();
                getReceivedCards().ajouterCartes(partie_.getSwitchedCards().get(partie_.getMatchingWinner(DealPresident.NUMERO_UTILISATEUR)));
                updateCardsInPanelPresidentReceived();
                getGivenCards().supprimerCartes();
                getGivenCards().ajouterCartes(partie_.getSwitchedCards().get(DealPresident.NUMERO_UTILISATEUR));
                updateCardsInPanelPresidentGiven();
            }
            Bytes w_ = partie_.getWinners(new Bytes(DealPresident.NUMERO_UTILISATEUR));
            if (!w_.isEmpty()) {
                getReceivedCards().supprimerCartes();
                getReceivedCards().ajouterCartes(partie_.getSwitchedCards().get(partie_.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
                updateCardsInPanelPresidentReceived();
                getGivenCards().supprimerCartes();
                getGivenCards().ajouterCartes(partie_.getSwitchedCards().get(DealPresident.NUMERO_UTILISATEUR));
                updateCardsInPanelPresidentGiven();
            }
        }
        getNoPlay().setVisible(true);
        if (partie_.getNextPlayer() == DealPresident.NUMERO_UTILISATEUR) {
            tapisPresident().setStatus(getWindow().getImageFactory(),lg_,partie_.getLastStatus(), partie_.getNextPlayer());
//            tapisPresident().repaintValidate();
            if (!partie_.getProgressingTrick().estVide()) {
                tapisPresident().setTalonPresident(getWindow().getImageFactory(),lg_,partie_.getProgressingTrick().getBestCards());
//                tapisPresident().repaintValidate();
            }
            placerBoutonsAvantJeuUtilisateurPresident();
            pack();
            return;
        }
        afficherMainUtilisateurPresident(false);
        pack();
        animationPlaying = new AnimationCardPresident(this);
        getOwner().getThreadFactory().newStartedThread(animationPlaying);
    }

    public void addButtonsForDiscard() {
        setCanDiscard(true);
//        clickedDiscard = false;
        setGivingCardsOk(getOwner().getCompoFactory().newPlainButton(WindowCards.OK));
        getGivingCardsOk().setEnabled(false);
        getGivingCardsOk().addActionListener(new GiveCardsEvent(this));
        //        getPanneauBoutonsJeu().add(getGivingCardsOk());
        getActionsHistory().add(getGivingCardsOk());
    }

    @Override
    public void discard(byte _index) {
        super.discard(_index);
        updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
        updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
        int max_ = partiePresident().numberGivenCards(DealPresident.NUMERO_UTILISATEUR);
        getGivingCardsOk().setEnabled(max_ == getGivenCards().total());
        pack();
    }

    @Override
    public void cancelDiscard(byte _index) {
        super.cancelDiscard(_index);
        updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
        updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
        getGivingCardsOk().setEnabled(false);
        pack();
    }

    @Override
    public void discard() {
        //The deal is now ready
        setCanDiscard(false);
        getGivingCardsOk().setVisible(false);
        GamePresident g_ = partiePresident();
        g_.giveWorstCards(getGivenCards());
        afficherMainUtilisateurPresident(false);
        getNoPlay().setVisible(true);
        pack();
        animationPlaying = new AnimationCardPresident(this);
        getOwner().getThreadFactory().newStartedThread(animationPlaying);
    }

    public void addButtonNextTrickPresident(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new NextTrickEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonEndDealPresident(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new EndDealEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonKeepPlayingDealPresident(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new KeepPlayingRandomEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonKeepPlayingEditedDealPresident(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new KeepPlayingEditedEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonStopPlayingPresident(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new StopPlayingEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonReplayDealPresident(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new ReplayEvent(this));
        _panneau.add(bouton_);
    }

    public void placerBoutonsAvantJeuUtilisateurPresident() {
        getHelpGame().setEnabledMenu(true);
        //Activer les conseils
        getConsulting().setEnabledMenu(true);
        setRaisonCourante(EMPTY);
        afficherMainUtilisateurPresident(true);
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().add(assemble());
        GamePresident g_ = partiePresident();
        if (g_.getStatus(DealPresident.NUMERO_UTILISATEUR) == Playing.HAS_TO_EQUAL) {
            getNoPlay().setText(getMessages().getVal(WindowCards.NO_PLAY_NOW));
        } else {
            getNoPlay().setText(getMessages().getVal(WindowCards.PASS_TRICK));
        }
        boolean _enabled = !g_.getProgressingTrick().estVide();
        getNoPlay().setEnabled(_enabled);
        //        getPanneauBoutonsJeu().add(getNoPlay());
        getPanneauBoutonsJeu().validate();
        getOwner().getTricksHands().setEnabledMenu(true);
        getOwner().getTeams().setEnabledMenu(true);
    }

    public void placerBoutonsFinPliUtilisateurPresident() {
        //Activer les conseils
        getConsulting().setEnabledMenu(false);
        getHelpGame().setEnabledMenu(true);
        GamePresident partie_=partiePresident();
        if(!partie_.keepPlayingCurrentGame()) {
            addButtonEndDealPresident(getMessages().getVal(WindowCards.END_DEAL), true);
        } else {
            addButtonNextTrickPresident(getMessages().getVal(WindowCards.NEXT_TRICK), true);
        }
    }

    public void editerPresident(GamePresident _partie) {
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabledMenu(false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        setaJoueCarte(false);
        setPartieSauvegardee(false);
        getPar().jouerPresident(_partie);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabledMenu(false);
        setChangerPileFin(false);
        mettreEnPlaceIhmPresident();
    }

    private void placerPresident() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabledMenu(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabledMenu(true);
        //Activer les conseils
        getConsulting().setEnabledMenu(true);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabledMenu(false);
        placerIhmPresident();
    }

    private void placerIhmPresident() {
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        String lg_ = getOwner().getLanguageKey();
        container_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowCards.HELP_GO_MENU)),GuiConstants.BORDER_LAYOUT_NORTH);
        GamePresident partie_=partiePresident();
        RulesPresident rules_ = partie_.getRegles();
        CarpetPresident tapis_=new CarpetPresident();
        StringList pseudos_ = pseudosPresident();
        int nbMax_ = rules_.getNbStacks() * Suit.couleursOrdinaires().size();
        tapis_.initTapisPresident(lg_,pseudos_, partie_.getLastStatus(), Math.min(nbMax_, rules_.getNbMaxCardsPerPlayer()), getOwner().getCompoFactory());
        getTapis().setTapisPresident(tapis_);
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
        setHandfuls(new ByteMap<AbsPlainLabel>());
        setDeclaredHandfuls(new ByteMap<AbsPanel>());
//        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0,1));
//        int nbPlayers_ = partie_.getNombreDeJoueurs();
        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
        AbsPanel panelCards_ = getOwner().getCompoFactory().newLineBox();
        AbsPanel panelDiscard_ = getOwner().getCompoFactory().newLineBox();
        panelDiscard_.setTitledBorder(getMessages().getVal(WindowCards.GIVEN_CARDS));
        panelCards_.add(panelDiscard_);
        setPanelGivenCards(panelDiscard_);
        AbsPanel panelRec_ = getOwner().getCompoFactory().newLineBox();
        panelRec_.setTitledBorder(getMessages().getVal(WindowCards.RECEIVED_CARDS));
        panelCards_.add(panelRec_);
        setPanelReceivedCards(panelRec_);
        sousPanneau_.add(panelCards_);
        setNoPlay(getOwner().getCompoFactory().newPlainButton(EMPTY));
        getNoPlay().addActionListener(new NoPlayPresidentEvent(this));
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
            Bytes w_ = game_.getWinners(new Bytes(DealPresident.NUMERO_UTILISATEUR));
            if (!w_.isEmpty()) {
                game_.giveWorstCards(w_);
                setCanDiscard(true);
                getReceivedCards().supprimerCartes();
                getReceivedCards().ajouterCartes(game_.getSwitchedCards().get(game_.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
                updateCardsInPanelPresidentReceived();
                getGivenCards().supprimerCartes();
                getVirtualHand().supprimerCartes();
                getVirtualHand().ajouterCartes(game_.mainUtilisateurTriee(getDisplayingPresident()));
                updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
                updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
                addButtonsForDiscard();
                pack();
                return;
            }
            game_.giveWorstCards();
            Bytes l_ = game_.getLoosers(new Bytes(DealPresident.NUMERO_UTILISATEUR));
            if (!l_.isEmpty()) {
                getReceivedCards().supprimerCartes();
                getReceivedCards().ajouterCartes(game_.getSwitchedCards().get(game_.getMatchingWinner(DealPresident.NUMERO_UTILISATEUR)));
                updateCardsInPanelPresidentReceived();
                getGivenCards().supprimerCartes();
                getGivenCards().ajouterCartes(game_.getSwitchedCards().get(DealPresident.NUMERO_UTILISATEUR));
                updateCardsInPanelPresidentGiven();
            }
            getNoPlay().setVisible(true);
            pack();
        } else {
            game_.giveWorstCards();
            getNoPlay().setVisible(true);
            pack();
        }
        getHelpGame().setEnabledMenu(true);
        animationPlaying = new AnimationCardPresident(this);
        getOwner().getThreadFactory().newStartedThread(animationPlaying);
    }

    @Override
    public void modify() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabledMenu(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabledMenu(true);
        //Activer les conseils
        getConsulting().setEnabledMenu(false);
        HandPresident pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        pile_ = chargerPilePresident(getNbStacks(),getOwner().getFrames());
        if (!pile_.validStack(getNbStacks())) {
            pile_ = HandPresident.stack(getNbStacks());
        }
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.PRESIDENT, getOwner().getFrames());
        DealPresident donne_;
        if(nb_==0||!getPar().enCoursDePartie()) {
            setNbStacks(getReglesPresident().getNbStacks());
            setChangerPileFin(true);
            donne_=new DealPresident(nb_,pile_);
            donne_.setRandomDealer(getReglesPresident(),getOwner().getGenerator());
            donne_.initDonne(getReglesPresident(),getOwner().getGenerator());
            getPar().jouerPresident(new GamePresident(GameType.RANDOM,donne_,getReglesPresident(), new Bytes()));
        } else {
            GamePresident partie_=partiePresident();
            Bytes newRanks_ = partie_.getNewRanks();
            donne_=new DealPresident(nb_,partie_.empiler());
            donne_.donneurSuivant(partie_.getDistribution().getDonneur(),partie_.getRegles());
            donne_.initDonne(partie_.getRegles(),getOwner().getGenerator());
            getPar().jouerPresident(new GamePresident(GameType.RANDOM,donne_,partie_.getRegles(), newRanks_));
        }
        mettreEnPlaceIhmPresident();
    }

    private void debutPliPresident() {
        //Activer le sous-menu conseil
        getConsulting().setEnabledMenu(false);
        //Activer le sous-menu aide au jeu
        getHelpGame().setEnabledMenu(true);
        /*Si on n'a pas encore fait de pli a la belote*/
        /*On affiche la main de l'utilisateur avec des ecouteurs sur les cartes et on supprime tous les boutons de l'ihm places a droite avant d'executer un eventuel Thread*/
        AbsPanel panneau_=getPanneauBoutonsJeu();
        panneau_.removeAll();
        panneau_.add(assemble());
//        panneau_.add(getNoPlay());
//        getNoPlay().setVisible(true);
//        getActionsHistory().repaint();
//        getActionsHistory().validate();
        panneau_.validate();
        setRaisonCourante(getMessages().getVal(WindowCards.WAIT_TURN));
        setThreadAnime(true);
        animationPlaying = new AnimationCardPresident(this);
        getOwner().getThreadFactory().newStartedThread(animationPlaying);
    }

    private void afficherMainUtilisateurPresident(boolean _ecouteur) {
        if (!_ecouteur) {
            setCarteEntree(false);
            setCarteSortie(false);
        }
        GamePresident partie_=partiePresident();
        //Les regles de la belote ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        HandPresident mainUtilisateur_=partie_.mainUtilisateurTriee(getDisplayingPresident());
        /*On place les cartes de l'utilisateur*/
        setCanPlay(_ecouteur);
        updateCardsInPanelPresident(getPanelHand(),mainUtilisateur_);
        getWindow().pack();
    }

    @Override
    public void noPlay() {
        if (!isCanPlay()) {
            return;
        }
        String lg_ = getOwner().getLanguageKey();
        GamePresident partie_=partiePresident();
        if (!partie_.canPass(DealPresident.NUMERO_UTILISATEUR)) {
            String title_ = getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE);
            ConfirmDialog.showMessage(getOwner(), Games.canPassMess(partie_, lg_), title_, lg_, GuiConstants.ERROR_MESSAGE);
            return;
        }
        /*L'utilisateur joue sa carte*/
        partie_.noPlay(DealPresident.NUMERO_UTILISATEUR);
        processUserActions();
    }

    public void finPliPresident(CardPresident _carteJouee, byte _index) {
        GamePresident partie_=partiePresident();
        /*L'utilisateur joue sa carte*/
        partie_.addCardsToCurrentTrick(DealPresident.NUMERO_UTILISATEUR, _carteJouee, _index);
        processUserActions();
    }

    private void processUserActions() {
        GamePresident partie_=partiePresident();
        //Activer le menu Partie/Pause
        getPause().setEnabledMenu(true);
        //Desactiver le sous-menu conseil
        getConsulting().setEnabledMenu(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        String lg_ = getOwner().getLanguageKey();
        ajouterTexteDansZone(StringUtil.concat(pseudo(),INTRODUCTION_PTS,Games.toString(partie_.getPlayedCards(),lg_),RETURN_LINE));
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
        setRaisonCourante(getMessages().getVal(WindowCards.END_TRICK));
        afficherMainUtilisateurPresident(false);
        tapisPresident().setTalonPresident(getWindow().getImageFactory(),lg_,partie_.getPlayedCards());
//        tapisPresident().repaintValidate();
        tapisPresident().setStatus(getWindow().getImageFactory(),lg_,partie_.getLastStatus(), partie_.getNextPlayer());
//        tapisPresident().repaintValidate();
        pause();
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().add(assemble());
//        getNoPlay().setVisible(true);
//        getActionsHistory().repaint();
//        getActionsHistory().validate();
        getPanneauBoutonsJeu().validate();
        setThreadAnime(true);
        animationPlaying = new AnimationCardPresident(this);
        getOwner().getThreadFactory().newStartedThread(animationPlaying);
    }

    public void finPartiePresident() {
        /*Descativer aide au jeu*/
        String lg_ = getOwner().getLanguageKey();
        getHelpGame().setEnabledMenu(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        AbsScrollPane ascenseur_;

        if(isChangerPileFin()) {
            GamePresident partie_=partiePresident();
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getOwner().getFrames()),FileConst.DECK_FOLDER,
                    StreamTextFile.SEPARATEUR,GameEnum.PRESIDENT.name(),
                    Long.toString(partie_.getRegles().getNbStacks()),FileConst.DECK_EXT),
                    DocumentWriterPresidentUtil.setHandPresident(partie_.empiler()), getWindow().getStreams());
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        StringList pseudos_=new StringList(pseudosPresident());
        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();

        GamePresident partie_=partiePresident();
        if(partie_.getType()==GameType.RANDOM) {
            setPartieAleatoireJouee(true);
            if(isChangerPileFin()) {
                changerNombreDeParties(GameEnum.PRESIDENT, partie_.getDistribution().getNombreDeParties(), getOwner().getFrames());
            }
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();

        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(partie_);
        res_.initialize(new StringList(pseudos_), getScores());
        res_.setUser(DealPresident.NUMERO_UTILISATEUR);
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        setScores(res_.getScores());

        AbsScrollPane scroll_=getOwner().getCompoFactory().newAbsScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_, getOwner().getFrames());
        PreparedAnalyzed stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT);
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBase(res_);
        editor_.initialize(stds_);
        scroll_.setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowCards.RESULTS_PAGE),scroll_);
        if(partie_.getType()==GameType.RANDOM) {
            Ints couleurs_=new Ints();
            couleurs_.add(GuiConstants.RED);
            couleurs_.add(GuiConstants.GREEN);
            couleurs_.add(GuiConstants.BLUE);
            if(nombreJoueurs_>3) {
                couleurs_.add(GuiConstants.YELLOW);
            }
            if(nombreJoueurs_>4) {
                couleurs_.add(GuiConstants.MAGENTA);
            }
            if(nombreJoueurs_>5) {
                couleurs_.add(GuiConstants.CYAN);
            }
            if(nombreJoueurs_>6) {
                couleurs_.add(GuiConstants.ORANGE);
            }
            if(nombreJoueurs_>7) {
                couleurs_.add(GuiConstants.newColor(128,64,0));
            }
            if(nombreJoueurs_>8) {
                couleurs_.add(GuiConstants.newColor(128,128,0));
            }
            if(nombreJoueurs_>9) {
                couleurs_.add(GuiConstants.newColor(128,0,255));
            }
            Graphic graphique_=new Graphic(getScores(),new Longs(res_.getSums()),new CustList<Rate>(res_.getSigmas()),couleurs_, getOwner().getCompoFactory());
            Rate derniereMoyenne_=new Rate(res_.getSums().last(),nombreJoueurs_);
            CustList<Rate> scoresCentresMoyenne_=new CustList<Rate>();
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                scoresCentresMoyenne_.add(Rate.minus(new Rate(getScores().last().get(joueur_)), derniereMoyenne_));
            }
            scoresCentresMoyenne_.add(Rate.multiply(new Rate(3), res_.getSigmas().last()));
            Rate max_ = Rate.zero();
            for(Rate maximum_:scoresCentresMoyenne_) {
                if (Rate.strGreater(maximum_.absNb(), max_)) {
                    max_ = maximum_.absNb();
                }
            }
            setMaxAbsoluScore(Math.max(max_.ll(),getMaxAbsoluScore()));
            int dimy_=(int)getMaxAbsoluScore();
            graphique_.setPreferredSize(new MetaDimension(2000,dimy_));
            ascenseur_=getOwner().getCompoFactory().newAbsScrollPane(graphique_);
            graphique_.setLocation(0,(600-dimy_)/2);
            ascenseur_.setPreferredSize(new MetaDimension(300,200));
            AbsPanel panneau_=getOwner().getCompoFactory().newBorder();
            panneau_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowCards.SCORES_EVOLUTION_DETAIL)),GuiConstants.BORDER_LAYOUT_NORTH);
            panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_CENTER);
            GraphicKey legende_=new GraphicKey(pseudos_,couleurs_, lg_, getOwner().getCompoFactory());
            legende_.setPreferredSize(new MetaDimension(300,15*(nombreJoueurs_+1)));
            ascenseur_=getOwner().getCompoFactory().newAbsScrollPane(legende_);
            ascenseur_.setPreferredSize(new MetaDimension(300,100));
            panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_SOUTH);
            onglets_.add(getMessages().getVal(WindowCards.SCORES_EVOLUTION),panneau_);
        }
        GamePresident game_=partiePresident();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        tricksHands_.sortHands(getDisplayingPresident(), game_.getNombreDeJoueurs());
        WindowCards ow_ = getOwner();
        AbsScrollPane panelCards_ = getOwner().getCompoFactory().newAbsScrollPane(new PanelTricksHandsPresident(ow_, tricksHands_,
                nombreJoueurs_,
                pseudosPresident(),
                getDisplayingPresident(),ow_).getContainer());
        panelCards_.setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowCards.HANDS_TRICKS),panelCards_);
        container_.add(onglets_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=getOwner().getCompoFactory().newPageBox();
        AbsPanel buttons_ = getOwner().getCompoFactory().newLineBox();
        GameType type_;
        long nombreParties_;
        type_=partie_.getType();
        nombreParties_=partie_.getNombre();
        int nombreTotalParties_=partie_.getRegles().getNbDeals();
        if(type_==GameType.EDIT&&nombreParties_<nombreTotalParties_) {
            addButtonKeepPlayingEditedDealPresident(buttons_, getMessages().getVal(WindowCards.KEEP_PLAYING_EDITED_DEAL));
        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&isPartieAleatoireJouee()||type_==GameType.RANDOM) {
            addButtonKeepPlayingDealPresident(buttons_, getMessages().getVal(WindowCards.KEEP_PLAYING_DEAL));
        }
        addButtonReplayDealPresident(buttons_, getMessages().getVal(WindowCards.REPLAY_DEAL));
        addButtonStopPlayingPresident(buttons_, getMessages().getVal(WindowCards.STOP));
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


    /**Pseudos utilis&eacute;s*/
    public StringList pseudosPresident() {
        return pseudosPresident(partiePresident().getNombreDeJoueurs());
    }

    private void updateCardsInPanelPresident(AbsPanel _panel, HandPresident _hand) {
        _panel.removeAll();
        int str_ = 0;
        int iter_ = IndexConstants.FIRST_INDEX;
        byte index_ = IndexConstants.SECOND_INDEX;
        String lg_ = getOwner().getLanguageKey();
        for (GraphicPresidentCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            int curStr_ = c.getCard().strength(partiePresident().isReversed());
            if (iter_ > IndexConstants.FIRST_INDEX) {
                if (curStr_ == str_) {
                    index_++;
                } else {
                    index_ = IndexConstants.SECOND_INDEX;
                }
            }
            c.addMouseListener(new ListenerCardPresidentSingleGame(this,c.getCard(), index_));
            str_ = curStr_;
            iter_++;
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaintChildren(getWindow().getImageFactory());
    }

    private void updateCardsInPanelPresidentDiscard(AbsPanel _panel, HandPresident _hand, boolean _inHand) {
        _panel.removeAll();
        byte index_ = IndexConstants.FIRST_INDEX;
        String lg_ = getOwner().getLanguageKey();
        for (GraphicPresidentCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            c.addMouseListener(new ListenerCardPresidentDiscard(this,c.getCard(),index_,_inHand,c));
            _panel.add(c);
            index_++;
        }
        if (!_inHand) {
            int rec_ = getReceivedCards().total();
            while (index_ < rec_) {
                AbsPlainLabel l_ = getOwner().getCompoFactory().newPlainLabel("");
                if (index_ > IndexConstants.FIRST_INDEX) {
                    l_.setPreferredSize(GraphicPresidentCard.getDimension(true));
                } else {
                    l_.setPreferredSize(GraphicPresidentCard.getDimension(false));
                }
                l_.setBackground(_panel.getBackgroundValue());
                l_.setForeground(_panel.getForegroundValue());
                _panel.add(l_);
                index_++;
            }
        }
        _panel.validate();
        _panel.repaintChildren(getWindow().getImageFactory());
    }

    @Override
    public void showTricksHands() {
        GamePresident game_ = partiePresident();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setReversed(game_.isReversed());
//        tricksHands_.setRules(getReglesPresident());
        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        WindowCards ow_ = getOwner();
        DialogTricksPresident.setDialogTricksPresident(getMessages().getVal(WindowCards.HANDS_TRICKS_PRESIDENT), ow_);
        DialogTricksPresident.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosPresident(), getDisplayingPresident(),ow_);

    }

    @Override
    public void conseil() {
        GamePresident game_ = partiePresident();
        String lg_ = getOwner().getLanguageKey();
        if (game_.availableSwitchingCards()) {
            HandPresident d_ = game_.strategieEchange(DealPresident.NUMERO_UTILISATEUR);
            String message_;
            message_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_PRESIDENT_GIVE), Games.toString(d_,lg_));
            ConfirmDialog.showMessage(getOwner(),message_, getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
            return;
        }
        HandPresident h_ = game_.playedCards();
        String message_;
        if (h_.estVide()) {
            message_ = getMessages().getVal(WindowCards.CONSULT_PRESIDENT_PASS);
        } else {
            message_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_PRESIDENT_PLAYER), Games.toString(game_.playedCards(),lg_));
        }
        ConfirmDialog.showMessage(getOwner(),message_, getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
    }

    @Override
    public void aideAuJeu() {
        String lg_ = getOwner().getLanguageKey();
        GamePresident g_ = partiePresident();
        DialogHelpPresident.setTitleDialog(getOwner(), StringUtil.concat(getMessages().getVal(WindowCards.HELP_GAME),SPACE,GameEnum.PRESIDENT.toString(lg_)));
        TreeMap<CardPresident, Byte> played_ = g_.getPlayedCardsByStrength();
        boolean reversed_ = g_.isReversed();
        int nbStacks_ = g_.getRegles().getNbStacks();
        getOwner().getDialogHelpPresident().setDialoguePresident(played_, reversed_, nbStacks_, lg_);
    }

    @Override
    public void nextTrick() {
        tapisPresident().setTalonPresident(getWindow().getImageFactory());
//        tapisPresident().repaintValidate();
        debutPliPresident();
    }

    @Override
    public void endDeal() {
        finPartiePresident();
        pack();
    }

    @Override
    public void keepPlayingRandom() {
        setChangerPileFin(true);
        modify();
    }

    @Override
    public void keepPlayingEdited() {
        GamePresident partie_=partiePresident();
        partie_.setNombre();
        HandPresident main_=partie_.empiler();
        Bytes newRanks_ = partie_.getNewRanks();
        DealPresident donne_=new DealPresident(0L,main_);
        donne_.donneurSuivant(partie_.getDistribution().getDonneur(),partie_.getRegles());
        donne_.initDonne(partie_.getRegles(),getOwner().getGenerator());
        getPar().jouerPresident(new GamePresident(GameType.EDIT,donne_,partie_.getRegles(),newRanks_));
        mettreEnPlaceIhmPresident();
    }

    @Override
    public void stopPlaying() {
        getOwner().menuSoloGames();
    }

    @Override
    public void replay() {
        GamePresident partie_=partiePresident();
        CustList<TrickPresident> plisFaits_=partie_.unionPlis();
        partie_.restituerMainsDepartRejouerDonne(plisFaits_, partie_.getNombreDeJoueurs());
        partie_.initPartie();
        mettreEnPlaceIhmPresident();
    }

}
