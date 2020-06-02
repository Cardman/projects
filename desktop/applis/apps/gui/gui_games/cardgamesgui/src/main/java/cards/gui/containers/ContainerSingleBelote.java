package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import cards.belote.*;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Status;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.MainWindow;
import cards.gui.animations.AddTextEvents;
import cards.gui.animations.AnimationBidBelote;
import cards.gui.animations.AnimationCardBelote;
import cards.gui.animations.DeclaringThread;
import cards.gui.animations.SettingText;
import cards.gui.containers.events.BidEvent;
import cards.gui.containers.events.ChangeBeloteDeclareEvent;
import cards.gui.containers.events.ChangeBeloteRebeloteEvent;
import cards.gui.containers.events.EndDealEvent;
import cards.gui.containers.events.FoldEvent;
import cards.gui.containers.events.KeepPlayingEditedEvent;
import cards.gui.containers.events.KeepPlayingRandomEvent;
import cards.gui.containers.events.NextTrickEvent;
import cards.gui.containers.events.ReplayEvent;
import cards.gui.containers.events.StopPlayingEvent;
import cards.gui.dialogs.DialogHelpBelote;
import cards.gui.dialogs.DialogTeamsPlayers;
import cards.gui.dialogs.DialogTricksBelote;
import cards.gui.dialogs.FileConst;
import cards.gui.events.ListenerBidBeloteSingle;
import cards.gui.events.ListenerCardBeloteSingleGame;
import cards.gui.events.SelectPointsEvent;
import cards.gui.events.SelectSuitEvent;
import cards.gui.labels.Graphic;
import cards.gui.labels.GraphicBeloteCard;
import cards.gui.labels.GraphicKey;
import cards.gui.labels.LabelPoints;
import cards.gui.labels.SuitLabel;
import cards.gui.panels.CarpetBelote;
import cards.gui.panels.MiniCarpet;
import cards.gui.panels.PanelTricksHandsBelote;
import cards.main.LaunchingCards;
import cards.network.common.select.TeamsPlayers;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.maths.Rate;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;
import code.util.StringList;

public class ContainerSingleBelote extends ContainerBelote implements ContainerSingle {

    private AnimationCardBelote animCarteBelote;
    private AnimationBidBelote animContratBelote;
    private BidBeloteSuit contratUtilisateurBelote = new BidBeloteSuit();
    private boolean annonceBelote;
    private boolean annonceBeloteRebelote;

    private boolean clickedBid;
    private boolean clickedPass;

    public ContainerSingleBelote(MainWindow _window) {
        super(_window);
    }
    public GameBelote partieBelote() {
        return getPar().partieBelote();
    }
    public void jouerBelote(byte _joueur, String _pseudo) {
        GameBelote partie_=partieBelote();
        CardBelote ct_=partie_.strategieJeuCarteUnique();
        String lg_ = getOwner().getLanguageKey();
        if(partie_.annoncerBeloteRebelote(_joueur,ct_)) {
            partie_.setAnnoncesBeloteRebelote(_joueur,ct_);
            ThreadInvoker.invokeNow(new AddTextEvents(this, StringList.concat(_pseudo,INTRODUCTION_PTS,Games.toString(DeclaresBeloteRebelote.BELOTE_REBELOTE,lg_),RETURN_LINE)));
//            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+DeclaresBeloteRebelote.BELOTE_REBELOTE+RETURN_LINE_CHAR);
        }
        if (partie_.premierTour()) {
            partie_.annoncer(_joueur);
            DeclareHandBelote usDecl_ = partie_.getAnnonce(_joueur);
            ThreadInvoker.invokeNow(new AddTextEvents(this,StringList.concat(_pseudo,INTRODUCTION_PTS,Games.toString(usDecl_.getDeclare(),lg_),RETURN_LINE)));
//            ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+usDecl_.getAnnonce()+RETURN_LINE_CHAR);
            if(!usDecl_.getHand().estVide()) {
                TextLabel label_ = getHandfuls().getVal(_joueur);
                ThreadInvoker.invokeNow(new SettingText(label_, Games.toString(usDecl_.getDeclare(),lg_)));
//                getHandfuls().getVal(_joueur).setText(usDecl_.getAnnonce().toString());
            }
            if (partie_.getContrat().getCouleurDominante()) {
                usDecl_.getHand().trier(getDisplayingBelote().getSuits(), getDisplayingBelote().isDecreasing(), partie_.getContrat().getCouleur());
            } else {
                usDecl_.getHand().trier(getDisplayingBelote().getSuits(), getDisplayingBelote().isDecreasing(), partie_.getContrat().getOrdre());
            }

            Panel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
            ThreadInvoker.invokeNow(new DeclaringThread(panelToSet_, usDecl_, getOwner()));
//            panelToSet_.removeAll();
//            for(CardBelote c: usDecl_.getMain())
//            {
//                MiniBeloteCard carte_=new MiniBeloteCard(c);
//                panelToSet_.add(carte_);
//            }
        }
        partie_.jouer(_joueur,ct_);
        partie_.ajouterUneCarteDansPliEnCours(ct_);
        tapisBelote().setCarteBelote(lg_,_joueur,ct_);
    }
    /**Met en place l'ihm pour l'utilisateur lorsqu'une partie est editee ou chargee d'un fichier*/
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
        setChangerPileFin(false);
        setaJoueCarte(false);
        byte nombreDeJoueurs_;
        GameBelote partie_=partieBelote();
        nombreDeJoueurs_=partie_.getNombreDeJoueurs();
        String lg_ = getOwner().getLanguageKey();
        getOwner().setTitle(GameEnum.BELOTE.toString(lg_));
        placerBelote();
        pack();
        StringList pseudos_=pseudosBelote();
        getHelpGame().setEnabledMenu(false);
        if(partie_.keepBidding()) {
            BidBeloteSuit contrat_=partie_.getContrat();
            //Desactiver les conseils
            getConsulting().setEnabledMenu(false);
            afficherMainUtilisateurBelote(false);
            byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
            for(BidBeloteSuit b: partie_.tousContrats()) {
                String pseudo_ = pseudos_.get(player_);
                ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
                player_ = partie_.playerAfter(player_);
            }
            byte debut_= partie_.playerHavingToBid();
            if(debut_ != DealBelote.NUMERO_UTILISATEUR) {
                animContratBelote=new AnimationBidBelote(this);
                CustComponent.newThread(animContratBelote).start();
            } else {
                if(partie_.keepBidding()) {
                    //Activer les conseils
                    getConsulting().setEnabledMenu(true);
                    setCanBid(true);
                    if (!partie_.getRegles().dealAll()) {
                        for(BidBeloteSuit e:partie_.getGameBeloteBid().allowedBids()) {
                            ajouterBoutonContratBelote(Games.toString(e,lg_),e,e.estDemandable(contrat_));
                        }
                    } else {
                        addButtonsForCoinche(partie_);
                    }
                } else if(partie_.getContrat().jouerDonne()) {
                    getMini().setStatus(Status.TAKER, partie_.getPreneur());
                    getMini().setStatus(Status.CALLED_PLAYER, partie_.getTeamsRelation().partenaires(partie_.getPreneur()).first());
                    addButtonNextTrickBelote(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                } else {
                    addButtonEndDealBelote(getMessages().getVal(MainWindow.END_DEAL), true);
                }
                pack();
            }
            return;
        }
        getHelpGame().setEnabledMenu(true);
        if (partie_.getTricks().isEmpty() && partie_.getPliEnCours().estVide()) {
            afficherMainUtilisateurBelote(false);
            if (!partie_.getRegles().dealAll() && partie_.getDistribution().hand().total() == partie_.getRegles().getRepartition().getNombreCartesParJoueur()) {
                TrickBelote pliEnCours_=partie_.getPliEnCours();
                for(byte p:pliEnCours_.playersHavingPlayed(nombreDeJoueurs_)) {
                    tapisBelote().setCarteBelote(lg_,p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
                }
                if (!partie_.keepPlayingCurrentGame()) {
                    finPartieBelote();
                    pack();
                    return;
                }
                animCarteBelote=new AnimationCardBelote(this);
                CustComponent.newThread(animCarteBelote).start();
            } else if(partie_.getContrat().jouerDonne()) {
                getMini().setStatus(Status.TAKER, partie_.getPreneur());
                getMini().setStatus(Status.CALLED_PLAYER, partie_.getTeamsRelation().partenaires(partie_.getPreneur()).first());
                addButtonNextTrickBelote(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                pack();
            } else {
                addButtonEndDealBelote(getMessages().getVal(MainWindow.END_DEAL), true);
                pack();
            }
            return;
        }
        TrickBelote pliEnCours_=partie_.getPliEnCours();
        for(byte p:pliEnCours_.playersHavingPlayed(nombreDeJoueurs_)) {
            tapisBelote().setCarteBelote(lg_,p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
        }
        afficherMainUtilisateurBelote(false);
        if (!partie_.keepPlayingCurrentGame()) {
            finPartieBelote();
            pack();
            return;
        }
        animCarteBelote=new AnimationCardBelote(this);
        CustComponent.newThread(animCarteBelote).start();
    }
    public void addButtonsForCoinche(GameBelote _partie) {
        int square_ = 1;
        Ints points_ = RulesBelote.getPoints();
        int size_ = points_.size();
        while (square_ * square_ < size_) {
            square_++;
        }
        setPanneauBoutonsJeuPoints(Panel.newGrid(0, square_));
        getPointsButtons().clear();
        String lg_ = getOwner().getLanguageKey();
        for (int p_: points_) {
            LabelPoints label_ = new LabelPoints(p_);
            label_.setEnabledLabel(_partie.getContrat().getPoints() < p_);
            label_.setToolTipText(Integer.toString(p_));
            label_.addMouseListener(new SelectPointsEvent(this, p_));
            getPointsButtons().add(label_);
            getPanneauBoutonsJeuPoints().add(label_);
        }
        getPanneauBoutonsJeu().add(getPanneauBoutonsJeuPoints());
        clickedBid = false;
        clickedPass = false;
        setBidOk(new LabelButton(MainWindow.OK));
        getBidOk().setEnabledLabel(false);
        getBidOk().addMouseListener(new BidEvent(this));
        Panel panel_ = Panel.newPageBox();
        Panel panelSuits_ = Panel.newLineBox();
        getBidsButtons().clear();
        for (Suit s: Suit.couleursOrdinaires()) {
            SuitLabel suitLabel_ = new SuitLabel();
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setCouleur(s);
            bid_.setEnchere(BidBelote.SUIT);
            suitLabel_.setSuit(bid_, lg_);
            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
            panelSuits_.add(suitLabel_);
            getBidsButtons().add(suitLabel_);
        }
        panel_.add(panelSuits_);
        Panel panelBids_ = Panel.newLineBox();
        for (BidBelote b: BidBelote.getNonZeroBids()) {
            if (b.getCouleurDominante()) {
                continue;
            }
            if (!_partie.getRegles().getEncheresAutorisees().getVal(b)) {
                continue;
            }
            SuitLabel suitLabel_ = new SuitLabel();
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setEnchere(b);
            suitLabel_.setSuit(bid_, lg_);
            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));

            panelBids_.add(suitLabel_);
            getBidsButtons().add(suitLabel_);
        }
        panel_.add(panelBids_);
        Panel panelOk_ = Panel.newLineBox();
        LabelButton buttonSuit_ = new LabelButton(Games.toString(BidBelote.FOLD,lg_));
        //clickedTwo = false;
        buttonSuit_.addMouseListener(new FoldEvent(this));
        panelOk_.add(buttonSuit_);
        panelOk_.add(getBidOk());
        panel_.add(panelOk_);
        getPanneauBoutonsJeu().add(panel_);
    }
    @Override
    public void bid() {
        if (clickedBid) {
            return;
        }
        clickedBid = true;
        if (!isCanBid()) {
            return;
        }
        setCanBid(false);
        contratUtilisateurBelote=new BidBeloteSuit();
        contratUtilisateurBelote.setCouleur(getSuit());
        contratUtilisateurBelote.setEnchere(getBidType());
        contratUtilisateurBelote.setPoints(getPts());
        animContratBelote=new AnimationBidBelote(this);
        CustComponent.newThread(animContratBelote).start();
    }
    @Override
    public void fold() {
        if (clickedPass) {
            return;
        }
        clickedPass = true;
        if (!isCanBid()) {
            return;
        }
        setCanBid(false);
        contratUtilisateurBelote=new BidBeloteSuit();
        animContratBelote=new AnimationBidBelote(this);
        CustComponent.newThread(animContratBelote).start();
    }
    public void ajouterBoutonContratBelote(String _texte,BidBeloteSuit _action,boolean _apte) {
        Panel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratBelote(_action));
        bouton_.addMouseListener(new ListenerBidBeloteSingle(this,_action));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonNextTrickBelote(String _texte,boolean _apte) {
        Panel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new NextTrickEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonEndDealBelote(String _texte,boolean _apte) {
        Panel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new EndDealEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonKeepPlayingDealBelote(Panel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingRandomEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonKeepPlayingEditedDealBelote(Panel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingEditedEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonStopPlayingBelote(Panel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new StopPlayingEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonReplayDealBelote(Panel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new ReplayEvent(this));
        _panneau.add(bouton_);
    }
    public void placerBoutonsAvantJeuUtilisateurBelote(boolean _premierTour) {
        //Activer les conseils
        getConsulting().setEnabledMenu(true);
        getHelpGame().setEnabledMenu(true);
        setRaisonCourante(EMPTY);
        GameBelote partie_=partieBelote();
        afficherMainUtilisateurBelote(true);
        getOwner().getTricksHands().setEnabledMenu(true);
        getOwner().getTeams().setEnabledMenu(true);
        String lg_ = getOwner().getLanguageKey();
        if(!partie_.cartesBeloteRebelote().estVide()) {
            annonceBeloteRebelote = false;
            Panel panneau_ =getPanneauBoutonsJeu();
            CustCheckBox caseCoche_ = new CustCheckBox(Games.toString(DeclaresBeloteRebelote.BELOTE_REBELOTE,lg_));
            caseCoche_.setEnabled(partie_.autoriseBeloteRebelote());
            caseCoche_.addActionListener(new ChangeBeloteRebeloteEvent(this));
            panneau_.add(caseCoche_);
        }
        if(_premierTour) {
            DeclareHandBelote annonceMain_ = partie_.strategieAnnonces();
            if(annonceMain_.getDeclare() != DeclaresBelote.UNDEFINED) {
                annonceBelote = false;
                Panel panneau_ =getPanneauBoutonsJeu();
                CustCheckBox caseCoche_ = new CustCheckBox(StringList.concat(Games.toString(annonceMain_.getDeclare(),lg_),INTRODUCTION_PTS,Games.toString(annonceMain_.getHand(),lg_)));
                caseCoche_.addActionListener(new ChangeBeloteDeclareEvent(this));
                panneau_.add(caseCoche_);
            }
        }
    }
    @Override
    public void invertBeloteRebelote() {
        annonceBeloteRebelote = !annonceBeloteRebelote;
    }
    @Override
    public void invertBeloteDeclare() {
        annonceBelote = !annonceBelote;
    }
    public void placerBoutonsFinPliUtilisateurBelote() {
        //Activer les conseils
        getConsulting().setEnabledMenu(false);
        getHelpGame().setEnabledMenu(true);
        GameBelote partie_=partieBelote();
        if(!partie_.keepPlayingCurrentGame()) {
            addButtonEndDealBelote(getMessages().getVal(MainWindow.END_DEAL), true);
        } else {
            addButtonNextTrickBelote(getMessages().getVal(MainWindow.NEXT_TRICK), true);
        }
    }

    public void editerBelote(GameBelote _partie) {
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabledMenu(false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        setaJoueCarte(false);
        setPartieSauvegardee(false);
        getPar().jouerBelote(_partie);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabledMenu(false);
        setChangerPileFin(false);
        mettreEnPlaceIhmBelote();
    }
    private void placerBelote() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabledMenu(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabledMenu(true);
        //Activer les conseils
        getConsulting().setEnabledMenu(true);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabledMenu(false);
        placerIhmBelote();
    }
    private void placerIhmBelote() {
        Panel container_=Panel.newBorder();
        container_.add(new TextLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        GameBelote partie_=partieBelote();
        StringList pseudos_ = pseudosBelote();
        String lg_ = getOwner().getLanguageKey();
        CarpetBelote tapis_ = CarpetBelote.initTapisBelote(lg_, partie_.getNombreDeJoueurs(), getDisplayingBelote().isClockwise(), pseudos_, 1);
        getTapis().setTapisBelote(tapis_);
        container_.add(tapis_.getContainer(),BorderLayout.CENTER);
        Panel panneau_;
        panneau_= Panel.newLineBox();
        panneau_.setBackground(Color.BLUE);
        setPanelHand(panneau_);
        container_.add(panneau_,BorderLayout.SOUTH);
        Panel panneau2_=Panel.newPageBox();
        setEvents(new TextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        panneau2_.add(new ScrollPane(getEvents()));
        setMini(MiniCarpet.newCarpet(partie_.getNombreDeJoueurs(),getDisplayingBelote().isClockwise(),pseudos_));
        panneau2_.add(getMiniPanel());
        setHandfuls(new ByteMap<TextLabel>());
        setDeclaredHandfuls(new ByteMap<Panel>());
        Panel declaredHandfuls_ = Panel.newGrid(0,1);
        int nbPlayers_ = partie_.getNombreDeJoueurs();
        for (byte i=CustList.FIRST_INDEX;i<nbPlayers_;i++) {
            Panel declaredHandfulGroup_ = Panel.newLineBox();
            TextLabel lab_ = new TextLabel(pseudos_.get(i));
            declaredHandfulGroup_.add(lab_);
            TextLabel handful_ = new TextLabel(EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            getHandfuls().put(i, handful_);
            Panel declaredHandful_ = Panel.newLineBox();
            declaredHandfulGroup_.add(declaredHandful_);
            getDeclaredHandfuls().put(i, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        ScrollPane scroll_ = new ScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);
        Panel sousPanneau_=Panel.newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,BorderLayout.EAST);
        if (!partie_.getDistribution().derniereMain().estVide()) {
            tapisBelote().setTalonBelote(lg_,partie_.getDistribution().derniereMain());
        }
        Panel panel_ = Panel.newPageBox();
        panel_.add(new ScrollPane(container_));
        panel_.add(getWindow().getClock());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }
    private void mettreEnPlaceIhmBelote() {
        placerBelote();
        afficherMainUtilisateurBelote(false);
        pack();
        GameBelote partie_=partieBelote();
        byte debut_= partie_.playerHavingToBid();
        String lg_ = getOwner().getLanguageKey();
        if(debut_ != DealBelote.NUMERO_UTILISATEUR) {
            animContratBelote=new AnimationBidBelote(this);
            CustComponent.newThread(animContratBelote).start();
        } else {
            setCanBid(true);
            if (!partie_.getRegles().dealAll()) {
                for(BidBeloteSuit e:partie_.getGameBeloteBid().allowedBids()) {
                    ajouterBoutonContratBelote(Games.toString(e,lg_),e,true);
                }
            } else {
                addButtonsForCoinche(partie_);
            }
            pack();
        }
    }
    @Override
    public void modify() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabledMenu(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabledMenu(true);
        //Activer les conseils
        getConsulting().setEnabledMenu(false);
        HandBelote pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        pile_ = chargerPileBelote();
        if (!pile_.validStack()) {
            pile_ = HandBelote.pileBase();
        }
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.BELOTE);
        DealBelote donne_;
        if(nb_==0||!getPar().enCoursDePartie()) {
            setChangerPileFin(true);
            donne_=new DealBelote(nb_,pile_);
            donne_.setRandomDealer(getReglesBelote().getRepartition().getNombreJoueurs(),getOwner().getGenerator());
            donne_.initDonne(getReglesBelote(),getDisplayingBelote(),getOwner().getGenerator());
            getPar().jouerBelote(new GameBelote(GameType.RANDOM,donne_,getReglesBelote()));
        } else {
            GameBelote partie_=partieBelote();
            donne_=new DealBelote(nb_,partie_.empiler());
            donne_.donneurSuivant(partie_.getDistribution().getDealer(),partie_.getNombreDeJoueurs());
            donne_.initDonne(partie_.getRegles(),getDisplayingBelote(),getOwner().getGenerator());
            getPar().jouerBelote(new GameBelote(GameType.RANDOM,donne_,partie_.getRegles()));
        }
        mettreEnPlaceIhmBelote();
    }
    private void debutPliBelote(boolean _premierPliFait) {
        //Activer le sous-menu conseil
        getConsulting().setEnabledMenu(false);
        //Activer le sous-menu aide au jeu
        getHelpGame().setEnabledMenu(true);
        StringList pseudos_=pseudosBelote();
        boolean premierTour_;
        GameBelote partie_=partieBelote();
        premierTour_=partie_.premierTour();
        /*Si on n'a pas encore fait de pli a la belote*/
        String lg_ = getOwner().getLanguageKey();
        if(premierTour_&&!_premierPliFait) {
            partie_.completerDonne();
            if (!partie_.getDistribution().derniereMain().estVide()) {
                tapisBelote().retirerCartes();
            }
            if (partie_.getRegles().dealAll()) {
                int pts_ = partie_.getContrat().getPoints();
                if (pts_ >= HandBelote.pointsTotauxDixDeDer(partie_.getContrat())) {
                    partie_.setEntameur(partie_.getPreneur());
                }
            }
            if(partie_.getContrat().getCouleurDominante()) {
                Suit couleurAtout_=partie_.couleurAtout();
                ajouterTexteDansZone(StringList.concat(pseudos_.get(partie_.getPreneur()),INTRODUCTION_PTS,Games.toString(couleurAtout_,lg_),RETURN_LINE));
            } else {
                ajouterTexteDansZone(StringList.concat(pseudos_.get(partie_.getPreneur()),INTRODUCTION_PTS,Games.toString(partie_.getContrat(),lg_),RETURN_LINE));
            }
            partie_.setPliEnCours();
        }
        /*On affiche la main de l'utilisateur avec des ecouteurs sur les cartes et on supprime tous les boutons de l'ihm places a droite avant d'executer un eventuel Thread*/
        Panel panneau_=getPanneauBoutonsJeu();
        panneau_.removeAll();
        panneau_.validate();
        setRaisonCourante(getMessages().getVal(MainWindow.WAIT_TURN));
        setThreadAnime(true);
        animCarteBelote=new AnimationCardBelote(this);
        CustComponent.newThread(animCarteBelote).start();


    }
    private void afficherMainUtilisateurBelote(boolean _ecouteur) {
        if (!_ecouteur) {
            setCarteEntree(false);
            setCarteSortie(false);
        }
        GameBelote partie_=partieBelote();
        //Les regles de la belote ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        HandBelote mainUtilisateur_=partie_.mainUtilisateurTriee(getDisplayingBelote());
        /*On place les cartes de l'utilisateur*/
        setCanPlay(_ecouteur);
        updateCardsInPanelBelote(getPanelHand(),mainUtilisateur_);
        getWindow().pack();
    }
    public void finPliBelote(CardBelote _carteJouee) {
        setCanPlay(false);
        //Activer le menu Partie/Pause
        getPause().setEnabledMenu(true);
        //Desactiver le sous-menu conseil
        getConsulting().setEnabledMenu(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        GameBelote partie_=partieBelote();
        boolean premierTour_;
        premierTour_=partie_.premierTour();
        String lg_ = getOwner().getLanguageKey();
        if(premierTour_) {
            if(annonceBelote) {
                partie_.annoncer(DealBelote.NUMERO_UTILISATEUR);
                DeclareHandBelote usDecl_ = partie_.getAnnonce(DealBelote.NUMERO_UTILISATEUR);
                ajouterTexteDansZone(StringList.concat(pseudo(),INTRODUCTION_PTS,Games.toString(usDecl_.getDeclare(),lg_),RETURN_LINE));
                if(!usDecl_.getHand().estVide()) {
                    getHandfuls().getVal(DealBelote.NUMERO_UTILISATEUR).setText(Games.toString(usDecl_.getDeclare(),lg_));
                }
                Panel panelToSet_ = getDeclaredHandfuls().getVal(DealBelote.NUMERO_UTILISATEUR);
                panelToSet_.removeAll();
                for (GraphicBeloteCard c: getGraphicCards(lg_,usDecl_.getHand())) {
                    panelToSet_.add(c);
                }
                panelToSet_.validate();
                panelToSet_.repaintChildren();
//                boolean entered_ = false;
//                for(CardBelote c: usDecl_.getMain())
//                {
//                    GraphicBeloteCard carte_=new GraphicBeloteCard(c,SwingConstants.RIGHT,!entered_);
//                    carte_.setPreferredSize(entered_);
//                    panelToSet_.add(carte_);
//                    entered_ = true;
//                }
            }
        }
        /*L'utilisateur joue sa carte*/
        partie_.jouer(_carteJouee);
        partie_.ajouterUneCarteDansPliEnCours(_carteJouee);
        if (annonceBeloteRebelote) {
            partie_.setAnnoncesBeloteRebelote(DealBelote.NUMERO_UTILISATEUR,_carteJouee);
            ajouterTexteDansZone(StringList.concat(pseudo(),INTRODUCTION_PTS,Games.toString(DeclaresBeloteRebelote.BELOTE_REBELOTE,lg_),RETURN_LINE));
        }
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
        setRaisonCourante(getMessages().getVal(MainWindow.END_TRICK));
        afficherMainUtilisateurBelote(false);
        tapisBelote().setCarteBelote(lg_,DealBelote.NUMERO_UTILISATEUR,_carteJouee);
        pause();
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().repaintChildren();
        animCarteBelote = new AnimationCardBelote(this);
        CustComponent.newThread(animCarteBelote).start();
        setThreadAnime(true);

    }

    public void finPartieBelote() {
        /*Descativer aide au jeu*/
        String lg_ = getOwner().getLanguageKey();
        getHelpGame().setEnabledMenu(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        Panel container_=Panel.newBorder();
        ScrollPane ascenseur_;
        Panel panneau_;
        if(isChangerPileFin()) {
            GameBelote partie_=partieBelote();
            StreamTextFile.saveTextFile(
                    StringList.concat(LaunchingCards.getTempFolderSl(),FileConst.DECK_FOLDER,
                            StreamTextFile.SEPARATEUR,GameEnum.BELOTE.name(),FileConst.DECK_EXT),
                    DocumentWriterBeloteUtil.setHandBelote(partie_.empiler()));
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        StringList pseudos_=new StringList(pseudosBelote());
        TabbedPane onglets_=new TabbedPane();
        GameBelote partie_=partieBelote();
        if(partie_.getType()==GameType.RANDOM) {
            setPartieAleatoireJouee(true);
            if(isChangerPileFin()) {
                changerNombreDeParties(GameEnum.BELOTE, partie_.getDistribution().getNbDeals());
            }
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(partie_);
        res_.setUser(DealBelote.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(pseudos_), getScores());
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        setScores(res_.getScores());
        BeloteStandards stds_;
        ScrollPane scroll_=new ScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_);
        editor_.setLanguage(lg_);
        stds_ = new BeloteStandards();
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,res_,stds_);
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.RESULTS_PAGE),scroll_);
        if(partie_.getContrat().jouerDonne()) {
            scroll_=new ScrollPane();
            editor_ = new RenderedPage(scroll_);
            editor_.setLanguage(lg_);
            stds_ = new BeloteStandards();
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,res_, stds_);
            scroll_.setPreferredSize(new Dimension(300,300));
            onglets_.add(getMessages().getVal(MainWindow.DETAIL_RESULTS_PAGE),scroll_);
        }
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
            Graphic graphique_=new Graphic(res_.getScores(),new Longs(res_.getSums()),new EqList<Rate>(),couleurs_);
            Rate derniereMoyenne_=new Rate(res_.getSums().last(),nombreJoueurs_);
            EqList<Rate> scoresCentresMoyenne_=new EqList<Rate>();
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                scoresCentresMoyenne_.add(Rate.minus(new Rate(res_.getScores().last().get(joueur_)), derniereMoyenne_));
            }
            scoresCentresMoyenne_.add(Rate.multiply(new Rate(3), res_.getSigmas().last()));
            Rate max_ = Rate.zero();
            for(Rate maximum_:scoresCentresMoyenne_) {
                if (Rate.strGreater(maximum_.absNb(), max_)) {
                    max_ = maximum_.absNb();
                }
            }
            setMaxAbsoluScore(Math.max(max_.ll(),getMaxAbsoluScore()));
            int dimy_=(int) getMaxAbsoluScore();
            graphique_.setPreferredSize(new Dimension(2000,dimy_));
            ascenseur_=new ScrollPane(graphique_);
            graphique_.setLocation(0,(600-dimy_)/2);
            ascenseur_.setPreferredSize(new Dimension(300,200));
            panneau_=Panel.newBorder();
            panneau_.add(new TextLabel(getMessages().getVal(MainWindow.SCORES_EVOLUTION_DETAIL),SwingConstants.CENTER),BorderLayout.NORTH);
            panneau_.add(ascenseur_,BorderLayout.CENTER);
            GraphicKey legende_=new GraphicKey(pseudos_,couleurs_, lg_);
            legende_.setPreferredSize(new Dimension(300,15*(nombreJoueurs_+1)));
            ascenseur_=new ScrollPane(legende_);
            ascenseur_.setPreferredSize(new Dimension(300,100));
            panneau_.add(ascenseur_,BorderLayout.SOUTH);
            onglets_.add(getMessages().getVal(MainWindow.SCORES_EVOLUTION),panneau_);
        }
        GameBelote game_ = partieBelote();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(getReglesBelote());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        tricksHands_.sortHands(getDisplayingBelote(), game_.getNombreDeJoueurs());
        MainWindow ow_ = getOwner();
        ascenseur_=new ScrollPane(new PanelTricksHandsBelote(ow_, tricksHands_, nombreJoueurs_, pseudosBelote(), getDisplayingBelote(),ow_).getContainer());
        ascenseur_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.HANDS_TRICKS),ascenseur_);
        container_.add(onglets_,BorderLayout.CENTER);
        panneau_=Panel.newPageBox();
        Panel buttons_ = Panel.newLineBox();
        GameType type_;
        long nombreParties_;
        type_=partie_.getType();
        nombreParties_=partie_.getNombre();
        int nombreTotalParties_=partie_.getRegles().getNombreParties();
        if(type_==GameType.EDIT&&nombreParties_<nombreTotalParties_) {
            addButtonKeepPlayingEditedDealBelote(buttons_, getMessages().getVal(MainWindow.KEEP_PLAYING_EDITED_DEAL));
        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&isPartieAleatoireJouee()||type_==GameType.RANDOM) {
            addButtonKeepPlayingDealBelote(buttons_, getMessages().getVal(MainWindow.KEEP_PLAYING_DEAL));
        }
        addButtonReplayDealBelote(buttons_, getMessages().getVal(MainWindow.REPLAY_DEAL));
        addButtonStopPlayingBelote(buttons_, getMessages().getVal(MainWindow.STOP));
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
    /**Pseudos utilis&eacute;s*/
    public StringList pseudosBelote() {
        return pseudosBelote(partieBelote().getNombreDeJoueurs());
    }

    private void updateCardsInPanelBelote(Panel _panel, HandBelote _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicBeloteCard c: getGraphicCards(lg_,_hand)) {
            c.addMouseListener(new ListenerCardBeloteSingleGame(this,c.getCard()));
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaintChildren();
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
    public boolean isAnnonceBeloteRebelote() {
        return annonceBeloteRebelote;
    }
    @Override
    public void showTeams() {
        GameBelote game_ = partieBelote();
        TeamsPlayers teams_ = new TeamsPlayers();
        teams_.setTeams(game_.playersBelongingToSameTeam());
        DialogTeamsPlayers.initDialogTeamsPlayers(getOwner());
        DialogTeamsPlayers.setDialogTeamsPlayers(pseudosBelote(), teams_);
    }
    @Override
    public void showTricksHands() {
        GameBelote game_ = partieBelote();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(getReglesBelote());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DialogTricksBelote.setDialogTricksBelote(getMessages().getVal(MainWindow.HANDS_TRICKS_BELOTE), getWindow());
        MainWindow ow_ = getOwner();
        DialogTricksBelote.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosBelote(), getDisplayingBelote(),ow_);

    }
    @Override
    public void conseil() {

        GameBelote partie_=partieBelote();
        String lg_ = getOwner().getLanguageKey();
        if(partie_.keepBidding()) {
            if (!partie_.getRegles().dealAll()) {
                BidBeloteSuit enchereCouleur_=partie_.strategieContrat();
                String mesBid_;

                BidBelote enchere_ = enchereCouleur_.getEnchere();
                if(enchere_ == BidBelote.FOLD ||
                        !enchere_.getCouleurDominante()) {
                    mesBid_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_BELOTE_BID), Games.toString(enchere_,lg_));
                } else {
                    mesBid_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_BELOTE_BID_SUIT), Games.toString(enchereCouleur_.getCouleur(),lg_));
                }
                ConfirmDialog.showMessage(getOwner(),mesBid_, getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(getOwner(),mesBid_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
            } else {
                BidBeloteSuit enchereCouleur_=partie_.strategieContrat();
                String mesBid_;

                BidBelote enchere_ = enchereCouleur_.getEnchere();
                if(enchere_ == BidBelote.FOLD) {
                    mesBid_ = StringList.simpleStringsFormat(
                            getMessages().getVal(MainWindow.CONSULT_BELOTE_BID),
                            Games.toString(enchere_,lg_));
                } else if(!enchere_.getCouleurDominante()) {
                    mesBid_ = StringList.simpleStringsFormat(
                            getMessages().getVal(MainWindow.CONSULT_BELOTE_BID_POINTS),
                            Games.toString(enchere_,lg_), Long.toString(enchereCouleur_.getPoints()));
                } else {
                    mesBid_ = StringList.simpleStringsFormat(
                            getMessages().getVal(MainWindow.CONSULT_BELOTE_BID_SUIT_POINTS),
                            Games.toString(enchereCouleur_.getCouleur(),lg_), Long.toString(enchereCouleur_.getPoints()));
                }
                ConfirmDialog.showMessage(getOwner(),mesBid_,
                        getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            String message_ = StringList.simpleStringsFormat(
                    getMessages().getVal(MainWindow.CONSULT_BELOTE_PLAYER),
                    Games.toString(partie_.strategieJeuCarteUnique(),lg_));
            ConfirmDialog.showMessage(getOwner(),message_,
                    getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
        }

    }
    @Override
    public void aideAuJeu() {
        String lg_ = getOwner().getLanguageKey();
        GameBelote partie_=partieBelote();
        HandBelote mainUtilisateur_=partie_.getDistribution().hand();
        GameBeloteTrickInfo info_ = partie_.getDoneTrickInfo();
        BidBeloteSuit contrat_=partie_.getContrat();
        Suit couleurAtout_=partie_.couleurAtout();
        HandBelote cartesJouees_=info_.cartesJouees();
        cartesJouees_.ajouterCartes(partie_.getPliEnCours().getCartes());
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=cartesJouees_.couleurs(contrat_);
        DialogHelpBelote.setTitleDialog(getOwner(),StringList.concat(getMessages().getVal(MainWindow.HELP_GAME),SPACE,GameEnum.BELOTE.toString(lg_)));
        EnumMap<Suit,CustList<HandBelote>> cartesPossibles_=info_.cartesPossibles(mainUtilisateur_);
        EnumMap<Hypothesis,EnumMap<Suit,CustList<HandBelote>>> hypotheses_ = info_.cartesCertaines(cartesPossibles_);
        EnumMap<Suit,CustList<HandBelote>> cartesCertaines_=hypotheses_.getVal(Hypothesis.SURE);
        Suit firstSuit_;
        if (partie_.getPliEnCours().estVide()) {
            firstSuit_ = couleurAtout_;
        } else {
            firstSuit_ = partie_.getPliEnCours().couleurDemandee();
        }
        DialogHelpBelote.setDialogueBelote(cartesPossibles_,cartesCertaines_,repartitionCartesJouees_,firstSuit_,contrat_,pseudosBelote(), lg_);
    }

    public BidBeloteSuit getContratUtilisateurBelote() {
        return contratUtilisateurBelote;
    }

    public void setContratUtilisateurBelote(
            BidBeloteSuit _contratUtilisateurBelote) {
        contratUtilisateurBelote = _contratUtilisateurBelote;
    }

    public AnimationBidBelote getAnimContratBelote() {
        return animContratBelote;
    }

    public void setAnimContratBelote(AnimationBidBelote _animContratBelote) {
        animContratBelote = _animContratBelote;
    }
    @Override
    public void nextTrick() {
        String lg_ = getOwner().getLanguageKey();
        GameBelote partie_ = partieBelote();
        tapisBelote().setCartesBeloteJeu(partie_.getNombreDeJoueurs(), lg_);
        debutPliBelote(!partie_.premierTour());
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
        DealBelote donne_=new DealBelote(0L,main_);
        donne_.donneurSuivant(partie_.getDistribution().getDealer(),partie_.getNombreDeJoueurs());
        donne_.initDonne(partie_.getRegles(),getDisplayingBelote(),getOwner().getGenerator());
        getPar().jouerBelote(new GameBelote(GameType.EDIT,donne_,partie_.getRegles()));
        mettreEnPlaceIhmBelote();
    }
    @Override
    public void stopPlaying() {
        getOwner().menuSoloGames();
    }
    @Override
    public void replay() {
        GameBelote partie_=partieBelote();
        CustList<TrickBelote> plisFaits_=partie_.getTricks();
        partie_.restituerMainsDepartRejouerDonne(plisFaits_, partie_.getNombreDeJoueurs());
        partie_.initPartie();
        mettreEnPlaceIhmBelote();
    }
}

