package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import cards.consts.GameType;
import cards.consts.Suit;
import cards.facade.enumerations.GameEnum;
import cards.facade.exceptions.GameLoadingException;
import cards.gui.MainWindow;
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
import code.gui.ConfirmDialog;
import code.gui.LabelButton;
import code.gui.SessionEditorPane;
import code.gui.ThreadInvoker;
import code.maths.Rate;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.TreeMap;
import code.util.consts.Constants;

public class ContainerSinglePresident extends ContainerPresident implements
        ContainerSingle {

    private AnimationCardPresident animationPlaying;
//    private boolean clickedDiscard;
//    private boolean clickedNoPlay;

    public ContainerSinglePresident(MainWindow _window) {
        super(_window);
    }

    public ContainerSinglePresident(MainWindow _window, String _nomFichier) {
        super(_window);
        chargerPartie(_nomFichier);
    }

    @Override
    protected void chargerPartie(String _fichier) {
        getPar().chargerPartie(_fichier);
        if (!getPar().enCoursDePartiePresident()) {
            throw new GameLoadingException();
        }
    }

    public GamePresident partiePresident() {
        return getPar().partiePresident();
    }

    public void jouerPresident(byte _joueur,String _pseudo) {
        GamePresident partie_=partiePresident();
        partie_.addCardsToCurrentTrick(_joueur);
        HandPresident h_ = partie_.getPlayedCards();
        ThreadInvoker.invokeNow(new AddTextEvents(this, StringList.concat(_pseudo,INTRODUCTION_PTS,h_.display(),RETURN_LINE)));
//        ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+h_+RETURN_LINE_CHAR);
        ThreadInvoker.invokeNow(new SettingPresidentStatus(this, partie_.getLastStatus(), partie_.getNextPlayer()));
//        tapisPresident().setStatus(partie_.getLastStatus(), partie_.getNextPlayer());
//        tapisPresident().repaintValidate();
        ThreadInvoker.invokeNow(new SettingPresidentHand(this, h_));
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
        getOwner().setTitle(GameEnum.PRESIDENT.display());
        placerPresident();
        getHelpGame().setEnabledMenu(false);
        if (partie_.availableSwitchingCards() && !partie_.readyToPlay()) {
            setCanDiscard(true);
            getConsulting().setEnabledMenu(true);
            getReceivedCards().supprimerCartes();
            getReceivedCards().ajouterCartes(partie_.getSwitchedCards().getVal(partie_.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
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
            Numbers<Byte> l_ = partie_.getLoosers(new Numbers<Byte>(DealPresident.NUMERO_UTILISATEUR));
            if (!l_.isEmpty()) {
                getReceivedCards().supprimerCartes();
                getReceivedCards().ajouterCartes(partie_.getSwitchedCards().getVal(partie_.getMatchingWinner(DealPresident.NUMERO_UTILISATEUR)));
                updateCardsInPanelPresidentReceived();
                getGivenCards().supprimerCartes();
                getGivenCards().ajouterCartes(partie_.getSwitchedCards().getVal(DealPresident.NUMERO_UTILISATEUR));
                updateCardsInPanelPresidentGiven();
            }
            Numbers<Byte> w_ = partie_.getWinners(new Numbers<Byte>(DealPresident.NUMERO_UTILISATEUR));
            if (!w_.isEmpty()) {
                getReceivedCards().supprimerCartes();
                getReceivedCards().ajouterCartes(partie_.getSwitchedCards().getVal(partie_.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
                updateCardsInPanelPresidentReceived();
                getGivenCards().supprimerCartes();
                getGivenCards().ajouterCartes(partie_.getSwitchedCards().getVal(DealPresident.NUMERO_UTILISATEUR));
                updateCardsInPanelPresidentGiven();
            }
        }
        getNoPlay().setVisibleButton(true);
        if (partie_.getNextPlayer() == DealPresident.NUMERO_UTILISATEUR) {
            tapisPresident().setStatus(partie_.getLastStatus(), partie_.getNextPlayer());
//            tapisPresident().repaintValidate();
            if (!partie_.getProgressingTrick().estVide()) {
                tapisPresident().setTalonPresident(partie_.getProgressingTrick().getBestCards());
//                tapisPresident().repaintValidate();
            }
            placerBoutonsAvantJeuUtilisateurPresident();
            pack();
            return;
        }
        afficherMainUtilisateurPresident(false);
        pack();
        animationPlaying = new AnimationCardPresident(this);
        animationPlaying.start();
    }

    public void addButtonsForDiscard() {
        setCanDiscard(true);
//        clickedDiscard = false;
        setGivingCardsOk(new LabelButton(MainWindow.OK));
        getGivingCardsOk().setEnabledLabel(false);
        getGivingCardsOk().addMouseListener(new GiveCardsEvent(this));
//        getPanneauBoutonsJeu().add(getGivingCardsOk());
        getActionsHistory().add(getGivingCardsOk());
    }

    @Override
    public void discard(byte _index) {
        super.discard(_index);
        updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
        updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
        int max_ = partiePresident().numberGivenCards(DealPresident.NUMERO_UTILISATEUR);
        getGivingCardsOk().setEnabledLabel(max_ == getGivenCards().total());
        pack();
    }

    @Override
    public void cancelDiscard(byte _index) {
        super.cancelDiscard(_index);
        updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
        updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
        getGivingCardsOk().setEnabledLabel(false);
        pack();
    }

    @Override
    public void discard() {
        //The deal is now ready
        setCanDiscard(false);
        getGivingCardsOk().setVisibleButton(false);
        GamePresident g_ = partiePresident();
        g_.giveWorstCards(getGivenCards());
        afficherMainUtilisateurPresident(false);
        getNoPlay().setVisibleButton(true);
        pack();
        animationPlaying = new AnimationCardPresident(this);
        animationPlaying.start();
    }

    public void addButtonNextTrickPresident(String _texte,boolean _apte) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new NextTrickEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonEndDealPresident(String _texte,boolean _apte) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new EndDealEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonKeepPlayingDealPresident(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingRandomEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonKeepPlayingEditedDealPresident(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingEditedEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonStopPlayingPresident(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new StopPlayingEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonReplayDealPresident(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new ReplayEvent(this));
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
            getNoPlay().setText(getMessages().getVal(MainWindow.NO_PLAY_NOW));
        } else {
            getNoPlay().setText(getMessages().getVal(MainWindow.PASS_TRICK));
        }
        getNoPlay().setEnabledLabel(!g_.getProgressingTrick().estVide());
//        getPanneauBoutonsJeu().add(getNoPlay());
        getPanneauBoutonsJeu().validate();
        getPanneauBoutonsJeu().repaint();
        getOwner().getTricksHands().setEnabledMenu(true);
        getOwner().getTeams().setEnabledMenu(true);
    }

    public void placerBoutonsFinPliUtilisateurPresident() {
        //Activer les conseils
        getConsulting().setEnabledMenu(false);
        getHelpGame().setEnabledMenu(true);
        GamePresident partie_=partiePresident();
        if(!partie_.keepPlayingCurrentGame()) {
            addButtonEndDealPresident(getMessages().getVal(MainWindow.END_DEAL), true);
        } else {
            addButtonNextTrickPresident(getMessages().getVal(MainWindow.NEXT_TRICK), true);
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
        JPanel container_=new JPanel();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        GamePresident partie_=partiePresident();
        RulesPresident rules_ = partie_.getRegles();
        CarpetPresident tapis_=new CarpetPresident();
        StringList pseudos_ = pseudosPresident();
        int nbMax_ = rules_.getNbStacks() * Suit.couleursOrdinaires().size();
        tapis_.initTapisPresident(pseudos_, partie_.getLastStatus(), Math.min(nbMax_, rules_.getNbMaxCardsPerPlayer()));
        getTapis().setTapisPresident(tapis_);
        container_.add(tapis_,BorderLayout.CENTER);
        JPanel panneau_=new JPanel();
        panneau_.setBackground(Color.BLUE);
        panneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        setPanelHand(panneau_);
        container_.add(panneau_,BorderLayout.SOUTH);
        JPanel panneau2_=new JPanel();
        panneau2_.setLayout(new BoxLayout(panneau2_, BoxLayout.PAGE_AXIS));
        setEvents(new JTextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        panneau2_.add(new JScrollPane(getEvents()));
        setHandfuls(new NumberMap<Byte,JLabel>());
        setDeclaredHandfuls(new NumberMap<Byte,JPanel>());
//        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0,1));
//        int nbPlayers_ = partie_.getNombreDeJoueurs();
        JPanel sousPanneau_=new JPanel();
        sousPanneau_.setLayout(new BoxLayout(sousPanneau_, BoxLayout.PAGE_AXIS));
        JPanel panelCards_ = new JPanel();
        JPanel panelDiscard_ = new JPanel();
        panelDiscard_.setBorder(BorderFactory.createTitledBorder(getMessages().getVal(MainWindow.GIVEN_CARDS)));
        panelDiscard_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panelCards_.add(panelDiscard_);
        setPanelGivenCards(panelDiscard_);
        JPanel panelRec_ = new JPanel();
        panelRec_.setBorder(BorderFactory.createTitledBorder(getMessages().getVal(MainWindow.RECEIVED_CARDS)));
        panelRec_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panelCards_.add(panelRec_);
        setPanelReceivedCards(panelRec_);
        sousPanneau_.add(panelCards_);
        setNoPlay(new LabelButton(EMPTY));
        getNoPlay().addMouseListener(new NoPlayPresidentEvent(this));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(new JScrollPane(sousPanneau_));
        getNoPlay().setVisibleButton(false);
        panneau2_.add(getNoPlay());
        setActionsHistory(panneau2_);
        container_.add(panneau2_,BorderLayout.EAST);
        tapisPresident().setTalonPresident();
//        tapisPresident().repaintValidate();
        JPanel panel_ = new JPanel();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
        panel_.add(new JScrollPane(container_));
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
            Numbers<Byte> w_ = game_.getWinners(new Numbers<Byte>(DealPresident.NUMERO_UTILISATEUR));
            if (!w_.isEmpty()) {
                game_.giveWorstCards(w_);
                setCanDiscard(true);
                getReceivedCards().supprimerCartes();
                getReceivedCards().ajouterCartes(game_.getSwitchedCards().getVal(game_.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
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
            Numbers<Byte> l_ = game_.getLoosers(new Numbers<Byte>(DealPresident.NUMERO_UTILISATEUR));
            if (!l_.isEmpty()) {
                getReceivedCards().supprimerCartes();
                getReceivedCards().ajouterCartes(game_.getSwitchedCards().getVal(game_.getMatchingWinner(DealPresident.NUMERO_UTILISATEUR)));
                updateCardsInPanelPresidentReceived();
                getGivenCards().supprimerCartes();
                getGivenCards().ajouterCartes(game_.getSwitchedCards().getVal(DealPresident.NUMERO_UTILISATEUR));
                updateCardsInPanelPresidentGiven();
            }
            getNoPlay().setVisibleButton(true);
            pack();
        } else {
            game_.giveWorstCards();
            getNoPlay().setVisibleButton(true);
            pack();
        }
        getHelpGame().setEnabledMenu(true);
        animationPlaying = new AnimationCardPresident(this);
        animationPlaying.start();
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
        try {
            pile_ = chargerPilePresident(getNbStacks());
            if (!pile_.validStack(getNbStacks())) {
                pile_ = HandPresident.stack(getNbStacks());
            }
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            pile_ = HandPresident.stack(getNbStacks());
        }
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.PRESIDENT);
//        try {
//            nb_=chargerNombreDeParties(GameEnum.PRESIDENT);
//        } catch (Exception exc_) {
//            exc_.printStackTrace();
//            nb_=0;
//        }
        DealPresident donne_;
        if(nb_==0||!getPar().enCoursDePartie()) {
            setNbStacks(getReglesPresident().getNbStacks());
            setChangerPileFin(true);
            donne_=new DealPresident(nb_,pile_);
            donne_.setRandomDealer(getReglesPresident());
            donne_.initDonne(getReglesPresident());
            getPar().jouerPresident(new GamePresident(GameType.RANDOM,donne_,getReglesPresident(), new Numbers<Byte>()));
        } else {
            GamePresident partie_=partiePresident();
            Numbers<Byte> newRanks_ = partie_.getNewRanks();
            donne_=new DealPresident(nb_,partie_.empiler());
            donne_.donneurSuivant(partie_.getDistribution().getDonneur(),partie_.getRegles());
            donne_.initDonne(partie_.getRegles());
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
        JPanel panneau_=getPanneauBoutonsJeu();
        panneau_.removeAll();
        panneau_.add(assemble());
//        panneau_.add(getNoPlay());
//        getNoPlay().setVisible(true);
//        getActionsHistory().repaint();
//        getActionsHistory().validate();
        panneau_.validate();
        panneau_.repaint();
        setRaisonCourante(getMessages().getVal(MainWindow.WAIT_TURN));
        setThreadAnime(true);
        animationPlaying = new AnimationCardPresident(this);
        animationPlaying.start();
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
        GamePresident partie_=partiePresident();
        if (!partie_.canPass(DealPresident.NUMERO_UTILISATEUR, Constants.getLanguage())) {
            String title_ = getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE);
            ConfirmDialog.showMessage(getOwner(), partie_.getErrorPlaying(), title_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
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
        ajouterTexteDansZone(StringList.concat(pseudo(),INTRODUCTION_PTS,partie_.getPlayedCards().display(),RETURN_LINE));
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
        setRaisonCourante(getMessages().getVal(MainWindow.END_TRICK));
        afficherMainUtilisateurPresident(false);
        tapisPresident().setTalonPresident(partie_.getPlayedCards());
//        tapisPresident().repaintValidate();
        tapisPresident().setStatus(partie_.getLastStatus(), partie_.getNextPlayer());
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
        getPanneauBoutonsJeu().repaint();
        setThreadAnime(true);
        animationPlaying = new AnimationCardPresident(this);
        animationPlaying.start();
    }

    public void finPartiePresident() {
        /*Descativer aide au jeu*/
        getHelpGame().setEnabledMenu(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        JPanel container_=new JPanel();
        JScrollPane ascenseur_;
        container_.setLayout(new BorderLayout());

        if(isChangerPileFin()) {
            GamePresident partie_=partiePresident();
            StreamTextFile.saveObject(StringList.concat(LaunchingCards.getTempFolderSl(),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.PRESIDENT.name(),Long.toString(partie_.getRegles().getNbStacks()),FileConst.DECK_EXT),partie_.empiler());
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        StringList pseudos_=new StringList(pseudosPresident());
        JTabbedPane onglets_=new JTabbedPane();

        GamePresident partie_=partiePresident();
        if(partie_.getType()==GameType.RANDOM) {
            setPartieAleatoireJouee(true);
            if(isChangerPileFin()) {
                changerNombreDeParties(GameEnum.PRESIDENT, partie_.getDistribution().getNombreDeParties());
            }
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();

        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(partie_);
        res_.initialize(new StringList(pseudos_), getScores());
        res_.setUser(DealPresident.NUMERO_UTILISATEUR);
        res_.setMessages(Constants.getLanguage());
        setScores(res_.getScores());

        SessionEditorPane editor_ = new SessionEditorPane();
        try {
//            editor_.setMainClass(SoftApplication.getMainClass());
//            editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor_.setLanguage(Constants.getLanguage());
            editor_.setDataBase(res_);
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT, new PresidentStandards());
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
        editor_.setEditable(false);
        JScrollPane scroll_=new JScrollPane(editor_);
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.RESULTS_PAGE),scroll_);
        if(partie_.getType()==GameType.RANDOM) {
            CustList<Color> couleurs_=new CustList<Color>();
            couleurs_.add(Color.RED);
            couleurs_.add(Color.GREEN);
            couleurs_.add(Color.BLUE);
            if(nombreJoueurs_>3) {
                couleurs_.add(Color.YELLOW);
            }
            if(nombreJoueurs_>4) {
                couleurs_.add(Color.MAGENTA);
            }
            if(nombreJoueurs_>5) {
                couleurs_.add(Color.CYAN);
            }
            if(nombreJoueurs_>6) {
                couleurs_.add(Color.ORANGE);
            }
            if(nombreJoueurs_>7) {
                couleurs_.add(new Color(128,64,0));
            }
            if(nombreJoueurs_>8) {
                couleurs_.add(new Color(128,128,0));
            }
            if(nombreJoueurs_>9) {
                couleurs_.add(new Color(128,0,255));
            }
            Graphic graphique_=new Graphic(getScores(),new Numbers<Long>(res_.getSums()),new EqList<Rate>(res_.getSigmas()),couleurs_);
            Rate derniereMoyenne_=new Rate(res_.getSums().last(),nombreJoueurs_);
            EqList<Rate> scoresCentresMoyenne_=new EqList<Rate>();
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
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
            graphique_.setPreferredSize(new Dimension(2000,dimy_));
            ascenseur_=new JScrollPane(graphique_);
            graphique_.setLocation(0,(600-dimy_)/2);
            ascenseur_.setPreferredSize(new Dimension(300,200));
            JPanel panneau_=new JPanel(new BorderLayout());
            panneau_.add(new JLabel(getMessages().getVal(MainWindow.SCORES_EVOLUTION_DETAIL),SwingConstants.CENTER),BorderLayout.NORTH);
            panneau_.add(ascenseur_,BorderLayout.CENTER);
            GraphicKey legende_=new GraphicKey(pseudos_,couleurs_);
            legende_.setPreferredSize(new Dimension(300,15*(nombreJoueurs_+1)));
            ascenseur_=new JScrollPane(legende_);
            ascenseur_.setPreferredSize(new Dimension(300,100));
            panneau_.add(ascenseur_,BorderLayout.SOUTH);
            onglets_.add(getMessages().getVal(MainWindow.SCORES_EVOLUTION),panneau_);
        }
        GamePresident game_=partiePresident();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistribution(game_.getDistribution(), true);
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        tricksHands_.sortHands(getDisplayingPresident(), game_.getNombreDeJoueurs());
        scroll_ = new JScrollPane(new PanelTricksHandsPresident(getOwner(), tricksHands_,
                nombreJoueurs_,
                pseudosPresident(),
                getDisplayingPresident()));
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.HANDS_TRICKS),scroll_);
        container_.add(onglets_,BorderLayout.CENTER);
        JPanel panneau_=new JPanel();
        panneau_.setLayout(new BoxLayout(panneau_, BoxLayout.PAGE_AXIS));
        JPanel buttons_ = new JPanel();
        GameType type_;
        long nombreParties_;
        type_=partie_.getType();
        nombreParties_=partie_.getNombre();
        int nombreTotalParties_=partie_.getRegles().getNbDeals();
        if(type_==GameType.EDIT&&nombreParties_<nombreTotalParties_) {
            addButtonKeepPlayingEditedDealPresident(buttons_, getMessages().getVal(MainWindow.KEEP_PLAYING_EDITED_DEAL));
        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&isPartieAleatoireJouee()||type_==GameType.RANDOM) {
            addButtonKeepPlayingDealPresident(buttons_, getMessages().getVal(MainWindow.KEEP_PLAYING_DEAL));
        }
        addButtonReplayDealPresident(buttons_, getMessages().getVal(MainWindow.REPLAY_DEAL));
        addButtonStopPlayingPresident(buttons_, getMessages().getVal(MainWindow.STOP));
        panneau_.add(buttons_);
        panneau_.add(getWindow().getClock());
        panneau_.add(getWindow().getLastSavedGameDate());
        container_.add(panneau_,BorderLayout.SOUTH);
//        if(type_!=GameType.EDIT) {
//            partie_.setNombre();
//        }

        partie_.setNombre();
        setContentPane(container_);
    }

//    private void changerNombreDePartiesPresident() {
//        CustList<Long> vl_=new CustList<Long>();
//        File fichier_=new File(LaunchingCards.getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+FileConst.DECK_FILE);
//        int total_=GameEnum.values().length;
//        boolean lu_=false;
//        try {
//            BufferedReader br_=new BufferedReader(new FileReader(fichier_));
//            for (int indice_ = CustList.FIRST_INDEX;indice_<total_;indice_++) {
//                vl_.add(Long.parseLong(br_.readLine()));
//            }
//            lu_=true;
//            br_.close();
//        } catch (Exception exc_) {
//            if(!lu_) {
//                vl_=new CustList<Long>();
//                for (int indice_ = CustList.FIRST_INDEX; indice_ < total_; indice_++) {
//                    vl_.add((long)0);
//                }
//            }
//        }
//        //Si l'action de battre les cartes est faite a chaque lancement
//        //de logiciel alors le nombre de parties est remis a zero lors
//        //d'une fermeture de logiciel
//        GamePresident partie_=partiePresident();
//        vl_.set(GameEnum.PRESIDENT.ordinal(), partie_.getDistribution().getNombreDeParties()+1);
//        try {
//            BufferedWriter bw_=new BufferedWriter(new FileWriter(fichier_));
//            for (int indice_ = CustList.FIRST_INDEX;indice_<total_;indice_++) {
//                bw_.write(vl_.get(indice_).toString());
//                bw_.newLine();
//            }
//            bw_.close();
//        } catch (Exception exc_) {}
//    }

    /**Pseudos utilis&eacute;s*/
    public StringList pseudosPresident() {
        return pseudosPresident(partiePresident().getNombreDeJoueurs());
    }

    private void updateCardsInPanelPresident(JPanel _panel, HandPresident _hand) {
        _panel.removeAll();
        int str_ = 0;
        int iter_ = CustList.FIRST_INDEX;
        byte index_ = CustList.SECOND_INDEX;
        for (GraphicPresidentCard c: getGraphicCards(_hand)) {
            int curStr_ = c.getCard().strength(partiePresident().isReversed());
            if (iter_ > CustList.FIRST_INDEX) {
                if (curStr_ == str_) {
                    index_ ++;
                } else {
                    index_ = CustList.SECOND_INDEX;
                }
            }
            c.addMouseListener(new ListenerCardPresidentSingleGame(this,c.getCard(), index_));
            str_ = curStr_;
            iter_ ++;
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaint();
    }

    private void updateCardsInPanelPresidentDiscard(JPanel _panel, HandPresident _hand, boolean _inHand) {
        _panel.removeAll();
        byte index_ = CustList.FIRST_INDEX;
        for (GraphicPresidentCard c: getGraphicCards(_hand)) {
            c.addMouseListener(new ListenerCardPresidentDiscard(this,c.getCard(),index_,_inHand));
            _panel.add(c);
            index_ ++;
        }
        if (!_inHand) {
            int rec_ = getReceivedCards().total();
            while (index_ < rec_) {
                JLabel l_ = new JLabel();
                if (index_ > CustList.FIRST_INDEX) {
                    l_.setPreferredSize(GraphicPresidentCard.getDimension(true));
                } else {
                    l_.setPreferredSize(GraphicPresidentCard.getDimension(false));
                }
                l_.setBackground(_panel.getBackground());
                l_.setForeground(_panel.getForeground());
                _panel.add(l_);
                index_ ++;
            }
        }
        _panel.validate();
        _panel.repaint();
    }

    @Override
    public void showTricksHands() {
        GamePresident game_ = partiePresident();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setReversed(game_.isReversed());
//        tricksHands_.setRules(getReglesPresident());
        tricksHands_.setDistribution(game_.getDistribution(), true);
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DialogTricksPresident.setDialogTricksPresident(getMessages().getVal(MainWindow.HANDS_TRICKS_PRESIDENT), getWindow());
        DialogTricksPresident.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosPresident(), getDisplayingPresident());

    }

    @Override
    public void conseil() {
        GamePresident game_ = partiePresident();
        if (game_.availableSwitchingCards()) {
            HandPresident d_ = game_.strategieEchange(DealPresident.NUMERO_UTILISATEUR);
            String message_;
            message_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_PRESIDENT_GIVE), d_.display());
            message_=StringList.concat(message_,game_.getReason());
            ConfirmDialog.showMessage(getOwner(),message_, getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        HandPresident h_ = game_.playedCards();
        String message_;
        if (h_.estVide()) {
            message_ = getMessages().getVal(MainWindow.CONSULT_PRESIDENT_PASS);
        } else {
            message_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_PRESIDENT_PLAYER), game_.playedCards().display());
        }
        message_=StringList.concat(message_,game_.getReason());
        ConfirmDialog.showMessage(getOwner(),message_, getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void aideAuJeu() {
        GamePresident g_ = partiePresident();
        DialogHelpPresident.setTitleDialog(getOwner(), StringList.concat(getMessages().getVal(MainWindow.HELP_GAME),SPACE,GameEnum.PRESIDENT.display()));
        TreeMap<CardPresident, Byte> played_ = g_.getPlayedCardsByStrength();
        boolean reversed_ = g_.isReversed();
        int nbStacks_ = g_.getRegles().getNbStacks();
        DialogHelpPresident.setDialoguePresident(played_, reversed_, nbStacks_);
    }

    @Override
    public void nextTrick() {
        tapisPresident().setTalonPresident();
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
        Numbers<Byte> newRanks_ = partie_.getNewRanks();
        DealPresident donne_=new DealPresident(0L,main_);
        donne_.donneurSuivant(partie_.getDistribution().getDonneur(),partie_.getRegles());
        donne_.initDonne(partie_.getRegles());
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
