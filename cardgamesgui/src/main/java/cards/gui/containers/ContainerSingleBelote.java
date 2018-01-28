package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import code.gui.ConfirmDialog;
import code.gui.LabelButton;
import code.gui.SessionEditorPane;
import code.gui.ThreadInvoker;
import code.maths.Rate;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.consts.Constants;
import cards.belote.BidBeloteSuit;
import cards.belote.DealBelote;
import cards.belote.DeclareHandBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import cards.belote.TrickBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Status;
import cards.consts.Suit;
import cards.facade.enumerations.GameEnum;
import cards.facade.exceptions.GameLoadingException;
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
    public ContainerSingleBelote(MainWindow _window, String _nomFichier) {
        super(_window);
        chargerPartie(_nomFichier);
    }
    @Override
    protected void chargerPartie(String _fichier) {
        getPar().chargerPartie(_fichier);
        if (!getPar().enCoursDePartieBelote()) {
            throw new GameLoadingException();
        }
    }
    public GameBelote partieBelote() {
        return getPar().partieBelote();
    }
    public void jouerBelote(byte _joueur,String _pseudo,boolean _premierTour) {
        GameBelote partie_=partieBelote();
        CardBelote ct_=partie_.strategieJeuCarteUnique();
        if(partie_.annoncerBeloteRebelote(_joueur,ct_,Constants.getLanguage())) {
            partie_.setAnnoncesBeloteRebelote(_joueur,ct_);
            ThreadInvoker.invokeNow(new AddTextEvents(this, StringList.concat(_pseudo,INTRODUCTION_PTS,DeclaresBeloteRebelote.BELOTE_REBELOTE.display(),RETURN_LINE)));
//            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+DeclaresBeloteRebelote.BELOTE_REBELOTE+RETURN_LINE_CHAR);
        }
        if (_premierTour) {
            partie_.annoncer(_joueur);
            DeclareHandBelote usDecl_ = partie_.getAnnonce(_joueur);
            ThreadInvoker.invokeNow(new AddTextEvents(this,StringList.concat(_pseudo,INTRODUCTION_PTS,usDecl_.getAnnonce().display(),RETURN_LINE)));
//            ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+usDecl_.getAnnonce()+RETURN_LINE_CHAR);
            if(!usDecl_.getMain().estVide()) {
                JLabel label_ = getHandfuls().getVal(_joueur);
                ThreadInvoker.invokeNow(new SettingText(label_, usDecl_.getAnnonce().display()));
//                getHandfuls().getVal(_joueur).setText(usDecl_.getAnnonce().toString());
            }
            if (partie_.getContrat().getCouleurDominante()) {
                usDecl_.getMain().trier(getDisplayingBelote().getCouleurs(), getDisplayingBelote().getDecroissant(), partie_.getContrat().getCouleur());
            } else {
                usDecl_.getMain().trier(getDisplayingBelote().getCouleurs(), getDisplayingBelote().getDecroissant(), partie_.getContrat().getOrdre());
            }

            JPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
            ThreadInvoker.invokeNow(new DeclaringThread(panelToSet_, usDecl_));
//            panelToSet_.removeAll();
//            for(CardBelote c: usDecl_.getMain())
//            {
//                MiniBeloteCard carte_=new MiniBeloteCard(c);
//                panelToSet_.add(carte_);
//            }
        }
        partie_.jouer(_joueur,ct_);
        partie_.ajouterUneCarteDansPliEnCours(ct_);
        tapisBelote().setCarteBelote(_joueur,ct_);
    }
    /**Met en place l'ihm pour l'utilisateur lorsqu'une partie est editee ou chargee d'un fichier*/
    @Override
    public void load() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabled(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabled(true);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabled(false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
        setChangerPileFin(false);
        setaJoueCarte(false);
        byte nombreDeJoueurs_;
        GameBelote partie_=partieBelote();
        nombreDeJoueurs_=partie_.getNombreDeJoueurs();
        getOwner().setTitle(GameEnum.BELOTE.display());
        placerBelote();
        pack();
        StringList pseudos_=pseudosBelote();
        getHelpGame().setEnabled(false);
        if(partie_.keepBidding()) {
            BidBeloteSuit contrat_=partie_.getContrat();
            //Desactiver les conseils
            getConsulting().setEnabled(false);
            afficherMainUtilisateurBelote(false);
            byte player_ = partie_.playerAfter(partie_.getDistribution().getDonneur());
            for(BidBeloteSuit b: partie_.tousContrats()) {
                String pseudo_ = pseudos_.get(player_);
                ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,b.display(),RETURN_LINE));
                player_ = partie_.playerAfter(player_);
            }
            byte debut_= partie_.playerHavingToBid();
            if(debut_ != DealBelote.NUMERO_UTILISATEUR) {
                animContratBelote=new AnimationBidBelote(this);
                animContratBelote.start();
            } else {
                if(partie_.keepBidding()) {
                    //Activer les conseils
                    getConsulting().setEnabled(true);
                    setCanBid(true);
                    if (!partie_.getRegles().dealAll()) {
                        for(BidBeloteSuit e:partie_.allowedBids()) {
                            ajouterBoutonContratBelote(e.display(),e,e.estDemandable(contrat_));
                        }
                    } else {
                        addButtonsForCoinche(partie_);
                    }
                } else if(partie_.getContrat().jouerDonne()) {
                    getMini().setStatus(Status.TAKER, partie_.getPreneur());
                    getMini().setStatus(Status.CALLED_PLAYER, partie_.partenaires(partie_.getPreneur()).first());
                    addButtonNextTrickBelote(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                } else {
                    addButtonEndDealBelote(getMessages().getVal(MainWindow.END_DEAL), true);
                }
                pack();
            }
            return;
        }
        getHelpGame().setEnabled(true);
        if (partie_.unionPlis().isEmpty() && partie_.getPliEnCours().estVide()) {
            afficherMainUtilisateurBelote(false);
            if (!partie_.getRegles().dealAll() && partie_.getDistribution().main().total() == partie_.getRegles().getRepartition().getNombreCartesParJoueur()) {
                TrickBelote pliEnCours_=partie_.getPliEnCours();
                for(byte p:pliEnCours_.playersHavingPlayed(nombreDeJoueurs_)) {
                    tapisBelote().setCarteBelote(p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
                }
                if (!partie_.keepPlayingCurrentGame()) {
                    finPartieBelote();
                    pack();
                    return;
                }
                animCarteBelote=new AnimationCardBelote(this);
                animCarteBelote.start();
            } else if(partie_.getContrat().jouerDonne()) {
                getMini().setStatus(Status.TAKER, partie_.getPreneur());
                getMini().setStatus(Status.CALLED_PLAYER, partie_.partenaires(partie_.getPreneur()).first());
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
            tapisBelote().setCarteBelote(p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
        }
        afficherMainUtilisateurBelote(false);
        if (!partie_.keepPlayingCurrentGame()) {
            finPartieBelote();
            pack();
            return;
        }
        animCarteBelote=new AnimationCardBelote(this);
        animCarteBelote.start();
    }
    public void addButtonsForCoinche(GameBelote _partie) {
        int square_ = 1;
        Numbers<Integer> points_ = RulesBelote.getPoints();
        int size_ = points_.size();
        while (square_ * square_ < size_) {
            square_ ++;
        }
        setPanneauBoutonsJeuPoints(new JPanel(new GridLayout(0, square_)));
        getPointsButtons().clear();
        for (int p_: points_) {
            LabelPoints label_ = new LabelPoints(p_);
            label_.setEnabledLabel(_partie.getContrat().getPoints() < p_);
            label_.setToolTipText(Integer.toString(p_));
            label_.addMouseListener(new SelectPointsEvent(this, p_));
            getPointsButtons().add(label_);
            getPanneauBoutonsJeuPoints().add(label_);

            //label_.addMouseListener(arg0);
//            JToggleButton button_ = new JToggleButton(Integer.toString(p_));
//            button_.setEnabled(_partie.getContrat().getPoints() < p_);
//            button_.setToolTipText(Integer.toString(p_));
//            button_.addActionListener(new EcouteurPtsContratBelote(p_));
//            getPanneauBoutonsJeuPoints().add(button_);
        }
        getPanneauBoutonsJeu().add(getPanneauBoutonsJeuPoints());
        clickedBid = false;
        clickedPass = false;
        setBidOk(new LabelButton(MainWindow.OK));
        getBidOk().setEnabledLabel(false);
        getBidOk().addMouseListener(new BidEvent(this));
        JPanel panel_ = new JPanel();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
        JPanel panelSuits_ = new JPanel();
        getBidsButtons().clear();
        for (Suit s: Suit.couleursOrdinaires()) {
            SuitLabel suitLabel_ = new SuitLabel();
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setCouleur(s);
            bid_.setEnchere(BidBelote.SUIT);
            suitLabel_.setSuit(bid_);
//            suitLabel_.addMouseListener(new EcouteurSuitContratBelote(s));
            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
            //suitLabel_.setPreferredSize(new Dimension(20,20));
            panelSuits_.add(suitLabel_);
            getBidsButtons().add(suitLabel_);
        }
        panel_.add(panelSuits_);
        JPanel panelBids_ = new JPanel();
        for (BidBelote b: BidBelote.getNonZeroBids()) {
            if (b.getCouleurDominante()) {
                continue;
            }
            if (!_partie.getRegles().getEncheresAutorisees().getVal(b)) {
                continue;
            }
//            JToggleButton buttonSuit_ = new JToggleButton(b.toString());
//            buttonSuit_.addActionListener(new EcouteurGreaterBidBelote(b));

            SuitLabel suitLabel_ = new SuitLabel();
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setEnchere(b);
            suitLabel_.setSuit(bid_);
//            suitLabel_.addMouseListener(new EcouteurSuitContratBelote(s));
            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));

            panelBids_.add(suitLabel_);
            getBidsButtons().add(suitLabel_);
        }
        panel_.add(panelBids_);
        JPanel panelOk_ = new JPanel();
        LabelButton buttonSuit_ = new LabelButton(BidBelote.FOLD.display());
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
        animContratBelote.start();
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
        animContratBelote.start();
    }
    public void ajouterBoutonContratBelote(String _texte,BidBeloteSuit _action,boolean _apte) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratBelote(_action));
        bouton_.addMouseListener(new ListenerBidBeloteSingle(this,_action));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonNextTrickBelote(String _texte,boolean _apte) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new NextTrickEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonEndDealBelote(String _texte,boolean _apte) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new EndDealEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonKeepPlayingDealBelote(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingRandomEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonKeepPlayingEditedDealBelote(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingEditedEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonStopPlayingBelote(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new StopPlayingEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonReplayDealBelote(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new ReplayEvent(this));
        _panneau.add(bouton_);
    }
    public void placerBoutonsAvantJeuUtilisateurBelote(boolean _premierTour) {
        //Activer les conseils
        getConsulting().setEnabled(true);
        getHelpGame().setEnabled(true);
        setRaisonCourante(EMPTY);
        GameBelote partie_=partieBelote();
        afficherMainUtilisateurBelote(true);
        getOwner().getTricksHands().setEnabled(true);
        getOwner().getTeams().setEnabled(true);
        if(!partie_.cartesBeloteRebelote().estVide()) {
            annonceBeloteRebelote = false;
            JPanel panneau_ =getPanneauBoutonsJeu();
            JCheckBox caseCoche_ = new JCheckBox(DeclaresBeloteRebelote.BELOTE_REBELOTE.display());
            caseCoche_.setEnabled(partie_.autoriseBeloteRebelote(Constants.getLanguage()));
            caseCoche_.addActionListener(new ChangeBeloteRebeloteEvent(this));
            panneau_.add(caseCoche_);
        }
        if(_premierTour) {
            DeclareHandBelote annonceMain_ = partie_.strategieAnnonces(DealBelote.NUMERO_UTILISATEUR);
            if(annonceMain_.getAnnonce() != DeclaresBelote.UNDEFINED) {
                annonceBelote = false;
                JPanel panneau_ =getPanneauBoutonsJeu();
                JCheckBox caseCoche_ = new JCheckBox(StringList.concat(annonceMain_.getAnnonce().display(),INTRODUCTION_PTS,annonceMain_.getMain().display()));
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
        getConsulting().setEnabled(false);
        getHelpGame().setEnabled(true);
        GameBelote partie_=partieBelote();
        if(!partie_.keepPlayingCurrentGame()) {
            addButtonEndDealBelote(getMessages().getVal(MainWindow.END_DEAL), true);
        } else {
            partie_.setPliEnCours();
            addButtonNextTrickBelote(getMessages().getVal(MainWindow.NEXT_TRICK), true);
        }
    }

    public void editerBelote(GameBelote _partie) {
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabled(false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
        setaJoueCarte(false);
        setPartieSauvegardee(false);
        getPar().jouerBelote(_partie);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabled(false);
        setChangerPileFin(false);
        mettreEnPlaceIhmBelote();
    }
    private void placerBelote() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabled(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabled(true);
        //Activer les conseils
        getConsulting().setEnabled(true);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabled(false);
        placerIhmBelote();
    }
    private void placerIhmBelote() {
        Container container_=new Container();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        GameBelote partie_=partieBelote();
        CarpetBelote tapis_=new CarpetBelote();
        StringList pseudos_ = pseudosBelote();
        tapis_.initTapisBelote(partie_.getNombreDeJoueurs(),getDisplayingBelote().getHoraire(),pseudos_,1);
        getTapis().setTapisBelote(tapis_);
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
        setMini(new MiniCarpet(partie_.getNombreDeJoueurs(),getDisplayingBelote().getHoraire(),pseudos_));
        panneau2_.add(getMini());
        setHandfuls(new NumberMap<Byte,JLabel>());
        setDeclaredHandfuls(new NumberMap<Byte,JPanel>());
        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0,1));
        int nbPlayers_ = partie_.getNombreDeJoueurs();
        for (byte i=CustList.FIRST_INDEX;i<nbPlayers_;i++) {
            JPanel declaredHandfulGroup_ = new JPanel(new FlowLayout());
            JLabel lab_ = new JLabel(pseudos_.get(i));
            declaredHandfulGroup_.add(lab_);
            JLabel handful_ = new JLabel(EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            getHandfuls().put(i, handful_);
            JPanel declaredHandful_ = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            declaredHandfulGroup_.add(declaredHandful_);
            getDeclaredHandfuls().put(i, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        JScrollPane scroll_ = new JScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);
        JPanel sousPanneau_=new JPanel();
        sousPanneau_.setLayout(new BoxLayout(sousPanneau_, BoxLayout.PAGE_AXIS));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,BorderLayout.EAST);
        if (!partie_.getDistribution().derniereMain().estVide()) {
            tapisBelote().setTalonBelote(partie_.getDistribution().derniereMain());
        }
        JPanel panel_ = new JPanel();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
        panel_.add(new JScrollPane(container_));
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
        if(debut_ != DealBelote.NUMERO_UTILISATEUR) {
            animContratBelote=new AnimationBidBelote(this);
            animContratBelote.start();
        } else {
            setCanBid(true);
            if (!partie_.getRegles().dealAll()) {
                for(BidBeloteSuit e:partie_.allowedBids()) {
                    ajouterBoutonContratBelote(e.display(),e,true);
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
        getSave().setEnabled(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabled(true);
        //Activer les conseils
        getConsulting().setEnabled(false);
        HandBelote pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        try {
            pile_ = chargerPileBelote();
            if (!pile_.validStack()) {
                pile_ = HandBelote.pileBase();
            }
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            pile_ = HandBelote.pileBase();
        }
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.BELOTE);
//        try {
//            nb_=chargerNombreDeParties(GameEnum.BELOTE);
//        } catch (Exception exc_) {
//            exc_.printStackTrace();
//            nb_=0;
//        }
        DealBelote donne_;
        if(nb_==0||!getPar().enCoursDePartie()) {
            setChangerPileFin(true);
            donne_=new DealBelote(nb_,pile_);
            donne_.setRandomDealer(getReglesBelote().getRepartition().getNombreJoueurs());
            donne_.initDonne(getReglesBelote(),getDisplayingBelote());
            getPar().jouerBelote(new GameBelote(GameType.RANDOM,donne_,getReglesBelote()));
        } else {
            GameBelote partie_=partieBelote();
            donne_=new DealBelote(nb_,partie_.empiler());
            donne_.donneurSuivant(partie_.getDistribution().getDonneur(),partie_.getNombreDeJoueurs());
            donne_.initDonne(partie_.getRegles(),getDisplayingBelote());
            getPar().jouerBelote(new GameBelote(GameType.RANDOM,donne_,partie_.getRegles()));
        }
        mettreEnPlaceIhmBelote();
    }
    private void debutPliBelote(boolean _premierPliFait) {
        //Activer le sous-menu conseil
        getConsulting().setEnabled(false);
        //Activer le sous-menu aide au jeu
        getHelpGame().setEnabled(true);
        StringList pseudos_=pseudosBelote();
        boolean premierTour_;
        GameBelote partie_=partieBelote();
        premierTour_=partie_.premierTour();
        /*Si on n'a pas encore fait de pli a la belote*/
        if(premierTour_&&!_premierPliFait) {
            partie_.completerDonne();
            if (!partie_.getDistribution().derniereMain().estVide()) {
                tapisBelote().supprimerCarteTalon();
            }
            if (partie_.getRegles().dealAll()) {
                int pts_ = partie_.getContrat().getPoints();
                if (pts_ >= HandBelote.pointsTotauxDixDeDer(partie_.getContrat())) {
                    partie_.setEntameur(partie_.getPreneur());
                }
            }
            if(partie_.getContrat().getCouleurDominante()) {
                Suit couleurAtout_=partie_.couleurAtout();
                ajouterTexteDansZone(StringList.concat(pseudos_.get(partie_.getPreneur()),INTRODUCTION_PTS,couleurAtout_.display(),RETURN_LINE));
            } else {
                ajouterTexteDansZone(StringList.concat(pseudos_.get(partie_.getPreneur()),INTRODUCTION_PTS,partie_.getContrat().display(),RETURN_LINE));
            }
            partie_.setPliEnCours();
        }
        /*On affiche la main de l'utilisateur avec des ecouteurs sur les cartes et on supprime tous les boutons de l'ihm places a droite avant d'executer un eventuel Thread*/
        JPanel panneau_=getPanneauBoutonsJeu();
        panneau_.removeAll();
        panneau_.validate();
        panneau_.repaint();
        setRaisonCourante(getMessages().getVal(MainWindow.WAIT_TURN));
        setThreadAnime(true);
        animCarteBelote=new AnimationCardBelote(this);
        animCarteBelote.start();


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
        getPause().setEnabled(true);
        //Desactiver le sous-menu conseil
        getConsulting().setEnabled(false);
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
        GameBelote partie_=partieBelote();
        boolean premierTour_;
        premierTour_=partie_.premierTour();
        if(premierTour_) {
            if(annonceBelote) {
                partie_.annoncer(DealBelote.NUMERO_UTILISATEUR);
                DeclareHandBelote usDecl_ = partie_.getAnnonce(DealBelote.NUMERO_UTILISATEUR);
                ajouterTexteDansZone(StringList.concat(pseudo(),INTRODUCTION_PTS,usDecl_.getAnnonce().display(),RETURN_LINE));
                if(!usDecl_.getMain().estVide()) {
                    getHandfuls().getVal(DealBelote.NUMERO_UTILISATEUR).setText(usDecl_.getAnnonce().display());
                }
                JPanel panelToSet_ = getDeclaredHandfuls().getVal(DealBelote.NUMERO_UTILISATEUR);
                panelToSet_.removeAll();
                for (GraphicBeloteCard c: getGraphicCards(usDecl_.getMain())) {
                    panelToSet_.add(c);
                }
                panelToSet_.validate();
                panelToSet_.repaint();
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
            ajouterTexteDansZone(StringList.concat(pseudo(),INTRODUCTION_PTS,DeclaresBeloteRebelote.BELOTE_REBELOTE.display(),RETURN_LINE));
        }
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
        setRaisonCourante(getMessages().getVal(MainWindow.END_TRICK));
        afficherMainUtilisateurBelote(false);
        tapisBelote().setCarteBelote(DealBelote.NUMERO_UTILISATEUR,_carteJouee);
        pause();
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        getPanneauBoutonsJeu().repaint();
        animCarteBelote = new AnimationCardBelote(this);
        animCarteBelote.start();
        setThreadAnime(true);

    }

    public void finPartieBelote() {
        /*Descativer aide au jeu*/
        getHelpGame().setEnabled(false);
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
        Container container_=new Container();
        JScrollPane ascenseur_;
        container_.setLayout(new BorderLayout());
        JPanel panneau_=new JPanel();
        if(isChangerPileFin()) {
            GameBelote partie_=partieBelote();
            StreamTextFile.saveObject(StringList.concat(LaunchingCards.getTempFolderSl(),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.BELOTE.name(),FileConst.DECK_EXT),partie_.empiler());
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        StringList pseudos_=new StringList(pseudosBelote());
        JTabbedPane onglets_=new JTabbedPane();
        GameBelote partie_=partieBelote();
        if(partie_.getType()==GameType.RANDOM) {
            setPartieAleatoireJouee(true);
            if(isChangerPileFin()) {
                changerNombreDeParties(GameEnum.BELOTE, partie_.getDistribution().getNombreDeParties());
            }
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();
        panneau_=new JPanel();
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(partie_);
        res_.initialize(new StringList(pseudos_), getScores());
        res_.setUser(DealBelote.NUMERO_UTILISATEUR);
        res_.setMessages(Constants.getLanguage());
        setScores(res_.getScores());
        BeloteStandards stds_;
        SessionEditorPane editor_ = new SessionEditorPane();
        try {
//            editor_.setMainClass(SoftApplication.getMainClass());
//            editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor_.setLanguage(Constants.getLanguage());
            editor_.setDataBase(res_);
            stds_ = new BeloteStandards();
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE,stds_);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
        editor_.setEditable(false);
        JScrollPane scroll_=new JScrollPane(editor_);
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.RESULTS_PAGE),scroll_);
        if(partie_.getContrat().jouerDonne()) {
            editor_ = new SessionEditorPane();
            try {
//                editor_.setMainClass(SoftApplication.getMainClass());
//                editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
                editor_.setLanguage(Constants.getLanguage());
                editor_.setDataBase(res_);
                stds_ = new BeloteStandards();
                editor_.initialize(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE, stds_);
            } catch (RuntimeException _0) {
                _0.printStackTrace();
            }
            editor_.setEditable(false);
            scroll_=new JScrollPane(editor_);
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
            Graphic graphique_=new Graphic(res_.getScores(),new Numbers<Long>(res_.getSums()),new EqList<Rate>(),couleurs_);
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
            ascenseur_=new JScrollPane(graphique_);
            graphique_.setLocation(0,(600-dimy_)/2);
            ascenseur_.setPreferredSize(new Dimension(300,200));
            panneau_=new JPanel(new BorderLayout());
            panneau_.add(new JLabel(getMessages().getVal(MainWindow.SCORES_EVOLUTION_DETAIL),SwingConstants.CENTER),BorderLayout.NORTH);
            panneau_.add(ascenseur_,BorderLayout.CENTER);
            GraphicKey legende_=new GraphicKey(pseudos_,couleurs_);
            legende_.setPreferredSize(new Dimension(300,15*(nombreJoueurs_+1)));
            ascenseur_=new JScrollPane(legende_);
            ascenseur_.setPreferredSize(new Dimension(300,100));
            panneau_.add(ascenseur_,BorderLayout.SOUTH);
            onglets_.add(getMessages().getVal(MainWindow.SCORES_EVOLUTION),panneau_);
        }
        GameBelote game_ = partieBelote();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(getReglesBelote());
        tricksHands_.setDistribution(game_.getDistribution(), true);
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        tricksHands_.sortHands(getDisplayingBelote(), game_.getNombreDeJoueurs());
        ascenseur_=new JScrollPane(new PanelTricksHandsBelote(getOwner(), tricksHands_, nombreJoueurs_, pseudosBelote(), getDisplayingBelote()));
        ascenseur_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.HANDS_TRICKS),ascenseur_);
        container_.add(onglets_,BorderLayout.CENTER);
        panneau_=new JPanel();
        panneau_.setLayout(new BoxLayout(panneau_, BoxLayout.PAGE_AXIS));
        JPanel buttons_ = new JPanel();
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
//    private void changerNombreDePartiesBelote() {
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
//        GameBelote partie_=partieBelote();
//        vl_.set(GameEnum.BELOTE.ordinal(), partie_.getDistribution().getNombreDeParties()+1);
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
    public StringList pseudosBelote() {
        return pseudosBelote(partieBelote().getNombreDeJoueurs());
    }

    private void updateCardsInPanelBelote(JPanel _panel, HandBelote _hand) {
        _panel.removeAll();
        for (GraphicBeloteCard c: getGraphicCards(_hand)) {
            c.addMouseListener(new ListenerCardBeloteSingleGame(this,c.getCard()));
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaint();
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
        tricksHands_.setDistribution(game_.getDistribution(), true);
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DialogTricksBelote.setDialogTricksBelote(getMessages().getVal(MainWindow.HANDS_TRICKS_BELOTE), getWindow());
        DialogTricksBelote.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosBelote(), getDisplayingBelote());

    }
    @Override
    public void conseil() {

        GameBelote partie_=partieBelote();
        if(partie_.keepBidding()) {
            if (!partie_.getRegles().dealAll()) {
                BidBeloteSuit enchereCouleur_=partie_.strategieContrat();
                String mesBid_;

                BidBelote enchere_ = enchereCouleur_.getEnchere();
                if(enchere_ == BidBelote.FOLD ||
                        !enchere_.getCouleurDominante()) {
                    mesBid_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_BELOTE_BID), enchere_.display());
                } else {
                    mesBid_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_BELOTE_BID_SUIT), enchereCouleur_.getCouleur().display());
                }
                mesBid_=StringList.concat(mesBid_,partie_.getRaison());
                ConfirmDialog.showMessage(getOwner(),mesBid_, getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(getOwner(),mesBid_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
            } else {
                BidBeloteSuit enchereCouleur_=partie_.strategieContrat();
                String mesBid_;

                BidBelote enchere_ = enchereCouleur_.getEnchere();
                if(enchere_ == BidBelote.FOLD) {
                    mesBid_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_BELOTE_BID), enchere_.display());
                } else if(!enchere_.getCouleurDominante()) {
                    mesBid_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_BELOTE_BID_POINTS), enchere_.display(), Long.toString(enchereCouleur_.getPoints()));
                } else {
                    mesBid_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_BELOTE_BID_SUIT_POINTS), enchereCouleur_.getCouleur().display(), Long.toString(enchereCouleur_.getPoints()));
                }
                mesBid_=StringList.concat(mesBid_, partie_.getRaison());
                ConfirmDialog.showMessage(getOwner(),mesBid_, getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(getOwner(),mesBid_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            String message_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_BELOTE_PLAYER), partie_.strategieJeuCarteUnique().display());
            message_=StringList.concat(message_, partie_.getRaison());
            ConfirmDialog.showMessage(getOwner(),message_, getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getOwner(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        }

    }
    @Override
    public void aideAuJeu() {

        GameBelote partie_=partieBelote();
        HandBelote mainUtilisateur_=partie_.getDistribution().main();
        BidBeloteSuit contrat_=partie_.getContrat();
        Suit couleurAtout_=partie_.couleurAtout();
        EnumMap<Suit,HandBelote> repartition_=mainUtilisateur_.couleurs(contrat_);
        HandBelote cartesJouees_=partie_.cartesJouees();
        cartesJouees_.ajouterCartes(partie_.getPliEnCours().getCartes());
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=cartesJouees_.couleurs(contrat_);
        DialogHelpBelote.setTitleDialog(getOwner(),StringList.concat(getMessages().getVal(MainWindow.HELP_GAME),SPACE,GameEnum.BELOTE.display()));
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=partie_.cartesPossibles(repartitionCartesJouees_,partie_.unionPlis(),repartition_,DealBelote.NUMERO_UTILISATEUR,couleurAtout_);
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandBelote>>> hypotheses_ = partie_.cartesCertaines(cartesPossibles_);
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=hypotheses_.getVal(Hypothesis.SURE);
        Suit firstSuit_;
        if (partie_.getPliEnCours().estVide()) {
            firstSuit_ = couleurAtout_;
        } else {
            firstSuit_ = partie_.getPliEnCours().couleurDemandee();
        }
        DialogHelpBelote.setDialogueBelote(cartesPossibles_,cartesCertaines_,repartitionCartesJouees_,firstSuit_,contrat_,pseudosBelote());
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
        GameBelote partie_ = partieBelote();
        tapisBelote().setCartesBeloteJeu(partie_.getNombreDeJoueurs());
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
        donne_.donneurSuivant(partie_.getDistribution().getDonneur(),partie_.getNombreDeJoueurs());
        donne_.initDonne(partie_.getRegles(),getDisplayingBelote());
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
        CustList<TrickBelote> plisFaits_=partie_.unionPlis();
        partie_.restituerMainsDepartRejouerDonne(plisFaits_, partie_.getNombreDeJoueurs());
        partie_.initPartie();
        mettreEnPlaceIhmBelote();
    }
}

