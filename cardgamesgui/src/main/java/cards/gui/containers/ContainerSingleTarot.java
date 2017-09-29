package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.consts.Constants;
import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Status;
import cards.consts.Suit;
import cards.facade.enumerations.GameEnum;
import cards.facade.exceptions.GameLoadingException;
import cards.gui.MainWindow;
import cards.gui.animations.AddTextEvents;
import cards.gui.animations.AnimationBidTarot;
import cards.gui.animations.AnimationCardTarot;
import cards.gui.animations.HandfulThread;
import cards.gui.animations.LoadingVideo;
import cards.gui.animations.SettingText;
import cards.gui.containers.events.EndDealEvent;
import cards.gui.containers.events.KeepPlayingEditedEvent;
import cards.gui.containers.events.KeepPlayingRandomEvent;
import cards.gui.containers.events.NextTrickEvent;
import cards.gui.containers.events.ReplayEvent;
import cards.gui.containers.events.SeeDogEvent;
import cards.gui.containers.events.SlamEvent;
import cards.gui.containers.events.StopPlayingEvent;
import cards.gui.containers.events.TakeDogEvent;
import cards.gui.containers.events.ValidateDogEvent;
import cards.gui.dialogs.DialogHelpTarot;
import cards.gui.dialogs.DialogTeamsPlayers;
import cards.gui.dialogs.DialogTricksTarot;
import cards.gui.dialogs.FileConst;
import cards.gui.events.ListenerBidTarotSingle;
import cards.gui.events.ListenerCardTarotSingleBeforeDog;
import cards.gui.events.ListenerCardTarotSingleDog;
import cards.gui.events.ListenerCardTarotSingleGame;
import cards.gui.events.ListenerCardTarotSingleHandful;
import cards.gui.events.ListenerHandfulTarot;
import cards.gui.events.ListenerMiseresTarot;
import cards.gui.events.ListenerNoHandfulTarot;
import cards.gui.labels.Graphic;
import cards.gui.labels.GraphicKey;
import cards.gui.labels.GraphicTarotCard;
import cards.gui.labels.MiniTarotCard;
import cards.gui.panels.CarpetTarot;
import cards.gui.panels.MiniCarpet;
import cards.gui.panels.PanelTricksHandsTarot;
import cards.main.LaunchingCards;
import cards.network.common.select.TeamsPlayers;
import cards.tarot.CallDiscard;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import cards.tarot.TrickTarot;
import cards.tarot.TricksHandsTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.ChoiceTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.PlayingDog;

public class ContainerSingleTarot extends ContainerTarot implements ContainerSingle {


    private ChoiceTarot choixTarot;
    private AnimationCardTarot animCarteTarot;
    private AnimationBidTarot animContratTarot;
    private BidTarot contratUtilisateur = BidTarot.FOLD;

    public ContainerSingleTarot(MainWindow _window) {
        super(_window);
        initButtonValidateDogTarot();
        initSlamButtonTarot();
    }

    public ContainerSingleTarot(MainWindow _window, String _file) {
        super(_window);
        initButtonValidateDogTarot();
        initSlamButtonTarot();
        chargerPartie(_file);
    }

    @Override
    protected void chargerPartie(String _fichier) {
        getPar().chargerPartie(_fichier);
        if (!getPar().enCoursDePartieTarot()) {
            throw new GameLoadingException();
        }
    }

    private void placerTarot() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabled(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabled(true);
        //Activer les conseils
        getConsulting().setEnabled(true);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabled(false);
        placerIhmTarot();
    }
    private void placerIhmTarot() {
        Container container_=new Container();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        GameTarot partie_=partieTarot();
        CarpetTarot tapis_=new CarpetTarot();
        StringList pseudos_ = pseudosTarot();
        tapis_.initTapisTarot(partie_.getNombreDeJoueurs(),getDisplayingTarot().getHoraire(),partie_.getDistribution().derniereMain().total());
        getTapis().setTapisTarot(tapis_);
        container_.add(tapis_,BorderLayout.CENTER);
        setPanelHand(new JPanel());
        getPanelHand().setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        JPanel panneau_=new JPanel();
        panneau_.add(getPanelHand());
        setPanelDiscardedTrumps(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
        getPanelDiscardedTrumps().setVisible(false);
        panneau_.add(getPanelDiscardedTrumps());
        panneau_.setBackground(Color.BLUE);
        container_.add(panneau_,BorderLayout.SOUTH);

        JPanel panneau2_=new JPanel();
        panneau2_.setLayout(new BoxLayout(panneau2_, BoxLayout.PAGE_AXIS));
        setEvents(new JTextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        panneau2_.add(new JScrollPane(getEvents()));
        setMini(new MiniCarpet(partie_.getNombreDeJoueurs(),getDisplayingTarot().getHoraire(),pseudos_));
        panneau2_.add(getMini());
        setDeclaringHandful(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT));
        getDeclaringHandful().setAlignmentY(Component.LEFT_ALIGNMENT);
        getDeclaringHandful().setContinuousLayout(true);
        getDeclaringHandful().setOneTouchExpandable(true);
        setIncludedTrumpsForHandful(new JPanel(new FlowLayout(FlowLayout.CENTER,0,0)));
        JScrollPane scroll_ = new JScrollPane(getIncludedTrumpsForHandful());
        scroll_.setPreferredSize(new Dimension(125,60));
        getDeclaringHandful().setLeftComponent(scroll_);
        setExcludedTrumpsForHandful(new JPanel(new FlowLayout(FlowLayout.CENTER,0,0)));
        scroll_ = new JScrollPane(getExcludedTrumpsForHandful());
        scroll_.setPreferredSize(new Dimension(125,60));
        getDeclaringHandful().setRightComponent(scroll_);
        setScrollDeclaringHandful(new JScrollPane(getDeclaringHandful()));
        getScrollDeclaringHandful().setPreferredSize(new Dimension(250,60));
        getScrollDeclaringHandful().setVisible(false);
        panneau2_.add(getScrollDeclaringHandful());
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
        scroll_ = new JScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);
        setPanelCallableCards(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
        setScrollCallableCards(new JScrollPane(getPanelCallableCards()));
        getScrollCallableCards().setVisible(false);
        panneau2_.add(getScrollCallableCards());
        JPanel sousPanneau_=new JPanel(new FlowLayout(FlowLayout.TRAILING,0,0));
        sousPanneau_.setLayout(new BoxLayout(sousPanneau_, BoxLayout.PAGE_AXIS));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,BorderLayout.EAST);
        tapisTarot().setTalonTarot(partie_.getDistribution().derniereMain());
        JPanel panel_ = new JPanel();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
        panel_.add(new JScrollPane(container_));
        panel_.add(getWindow().getClock());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }

    public GameTarot partieTarot() {
        return getPar().partieTarot();
    }
    public void jouerDonneEntrainement(ChoiceTarot _ct) {
        choixTarot = _ct;
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabled(false);
        setChangerPileFin(false);

        GameTarot.setChargementSimulation(0);
        setAnimChargement(new LoadingVideo(this));
        getAnimChargement().start();
        setPasse(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
        setaJoueCarte(false);
        byte nombreJoueurs_=(byte) getReglesTarot().getRepartition().getNombreJoueurs();
        byte nombreCartes_=(byte) getReglesTarot().getRepartition().getNombreCartesParJoueur();
        DealTarot donne_=new DealTarot(0L);
        donne_.initDonne(choixTarot,nombreCartes_,nombreJoueurs_,getReglesTarot());
        getPar().jouerTarot(new GameTarot(GameType.TRAINING,donne_,getReglesTarot()));
        GameTarot.setChargementSimulation(100);
        mettreEnPlaceIhmTarot();
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
        GameTarot partie_=partieTarot();
        nombreDeJoueurs_=partie_.getNombreDeJoueurs();
        getOwner().setTitle(EMPTY+GameEnum.TAROT);
        placerTarot();
        pack();
        StringList pseudos_=pseudosTarot();
        getHelpGame().setEnabled(false);
        if (!partie_.avecContrat()) {
            //Desactiver les conseils
            getConsulting().setEnabled(false);
            boolean firstRound_ = true;
            for (TrickTarot t: partie_.unionPlis(true)) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
                Numbers<Byte> players_ = partie_.orderedPlayers(t.getEntameur());
                for (byte p: players_) {
                    addTextInAreaByLoading(p,pseudos_.get(p),t.carteDuJoueur(p,nombreDeJoueurs_),firstRound_);
                }
                firstRound_ = false;
            }
            TrickTarot pliEnCours_=partie_.getPliEnCours();
            Numbers<Byte> joueurs_=pliEnCours_.joueursAyantJoue(nombreDeJoueurs_);
            for (byte p: joueurs_) {
                addTextInAreaByLoading(p,pseudos_.get(p),partie_.getPliEnCours().carteDuJoueur(p,partie_.getNombreDeJoueurs()),partie_.premierTour());
            }
            for(byte p:pliEnCours_.joueursAyantJoue(nombreDeJoueurs_)) {
                tapisTarot().setCarteTarot(p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
            }
            if (firstRound_ && pliEnCours_.estVide() && !pliEnCours_.getVuParToutJoueur()) {
                partie_.setPliEnCours(true);
            }
            getHelpGame().setEnabled(true);
            if (!partie_.keepPlayingCurrentGame()) {
                finPartieTarot();
                pack();
                return;
            }
            animCarteTarot=new AnimationCardTarot(this);
            animCarteTarot.start();
            return;
        }
        if(partie_.keepBidding()) {
            BidTarot contrat_=partie_.getContrat();
            //Desactiver les conseils
            getConsulting().setEnabled(false);
            afficherMainUtilisateurTarot(false);
            byte player_ = partie_.playerAfter(partie_.getDistribution().getDonneur());
            for(BidTarot b: partie_.tousContrats()) {
                String pseudo_ = pseudos_.get(player_);
                ajouterTexteDansZone(pseudo_+INTRODUCTION_PTS+b+RETURN_LINE_CHAR);
                player_ = partie_.playerAfter(player_);
            }
            if(partie_.playerHavingToBid() != DealTarot.NUMERO_UTILISATEUR) {
                setAnimContratTarot(new AnimationBidTarot(this));
                getAnimContratTarot().start();
            } else {
                //Activer les conseils
                getConsulting().setEnabled(true);
                setCanBid(true);
                for(BidTarot ench_:partie_.allowedBids()) {
                    ajouterBoutonContratTarot(ench_.toString(),ench_,ench_.estDemandable(contrat_));
                }
                pack();
            }
            return;
        }
        if (partie_.getContrat().isJouerDonne()) {
            getMini().setStatus(Status.TAKER, partie_.getPreneur());
        }
        if(partie_.isCallingState()) {
            if (partie_.getRegles().getDiscardAfterCall()) {
                byte player_ = partie_.playerAfter(partie_.getDistribution().getDonneur());
                for(BidTarot b: partie_.tousContrats()) {
                    String pseudo_ = pseudos_.get(player_);
                    ajouterTexteDansZone(pseudo_+INTRODUCTION_PTS+b+RETURN_LINE_CHAR);
                    player_ = partie_.playerAfter(player_);
                }
                afficherMainUtilisateurTarot(false);
                placerBoutonsAppel();
                pack();
            } else {
                if(partie_.unionPlis(true).isEmpty()) {
                    boolean existCard_ = false;
                    for (CardTarot c: partie_.getDistribution().derniereMain()) {
                        if (partie_.getDistribution().main().contient(c)) {
                            existCard_ = true;
                            break;
                        }
                    }
                    if (!existCard_) {
                        getConsulting().setEnabled(false);
                        addButtonTakeDogCardsTarot(getMessages().getVal(MainWindow.TAKE_CARDS), true);
                        afficherMainUtilisateurTarot(false);
                    } else {
                        TrickTarot ecart_=partie_.getPliEnCours();
                        getConsulting().setEnabled(ecart_.estVide());
                        setChien(ecart_.getCartes(),true);
                        afficherMainUtilisateurTarotChien();
                        placerBoutonsAppel();
                        pack();
                    }
                } else {
                    TrickTarot ecart_=partie_.getPliEnCours();
                    getConsulting().setEnabled(ecart_.estVide());
                    setChien(ecart_.getCartes(),true);
                    getValidateDog().setEnabledLabel(ecart_.total()==partie_.getDistribution().derniereMain().total());
                    getPanneauBoutonsJeu().add(getValidateDog());
                    //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), ecart_.total()==partie_.getDistribution().derniereMain().total());
                    afficherMainUtilisateurTarotChien();
                    placerBoutonsAppel();
                    pack();
                }
            }
            pack();
            return;
        }
        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            if (partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR) {
                if (partie_.unionPlis(true).isEmpty()) {
                    getConsulting().setEnabled(false);
                    boolean existCard_ = false;
                    for (CardTarot c: partie_.getDistribution().derniereMain()) {
                        if (partie_.getDistribution().main().contient(c)) {
                            existCard_ = true;
                            break;
                        }
                    }
                    if (!partie_.getPliEnCours().estVide()) {
                        getConsulting().setEnabled(false);
                        TrickTarot ecart_=partie_.getPliEnCours();
                        setChien(ecart_.getCartes(),true);
                        //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), ecart_.total()==partie_.getDistribution().derniereMain().total());
                        getValidateDog().setEnabledLabel(ecart_.total()==partie_.getDistribution().derniereMain().total());
                        getPanneauBoutonsJeu().add(getValidateDog());
                        if (ecart_.total()==partie_.getDistribution().derniereMain().total()) {
//                            ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                            getSlamButton().setEnabledLabel(true);
                            getSlamButton().setVisible(true);
                            getPanneauBoutonsJeu().add(getSlamButton());
                        }
                        afficherMainUtilisateurTarotChien();
                    } else if (existCard_) {
                        tapisTarot().retirerCartes();
                        getPanneauBoutonsJeu().removeAll();
                        if (partie_.getRegles().getDiscardAfterCall()) {
                            getValidateDog().setEnabledLabel(false);
                            getPanneauBoutonsJeu().add(getValidateDog());
                            //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
                        } else {
                            placerBoutonsAppel();
                            pack();
                        }
                        afficherMainUtilisateurTarotChien();
                    } else {
                        setChien(partie_.getDistribution().derniereMain(),false);
                        addButtonTakeDogCardsTarot(getMessages().getVal(MainWindow.TAKE_CARDS), true);
                        afficherMainUtilisateurTarot(false);
                    }
                    pack();
                    return;
                }
            } else {
                getConsulting().setEnabled(false);
                if (partie_.unionPlis(true).isEmpty()) {
                    byte player_ = partie_.playerAfter(partie_.getDistribution().getDonneur());
                    for(BidTarot b: partie_.tousContrats()) {
                        String pseudo_ = pseudos_.get(player_);
                        ajouterTexteDansZone(pseudo_+INTRODUCTION_PTS+b+RETURN_LINE_CHAR);
                        player_ = partie_.playerAfter(player_);
                    }
                    if(partie_.existeCarteAppelee()) {
                        String pseudo_ = pseudos_.get(partie_.getPreneur());
                        ajouterTexteDansZone(pseudo_+INTRODUCTION_PTS+partie_.getCarteAppelee()+RETURN_LINE_CHAR);
                    }
                    afficherMainUtilisateurTarot(false);
                    addButtonSeeDogTarot(getMessages().getVal(MainWindow.SEE_DOG), true);
                    pack();
                    return;
                }
                if(!partie_.getPliEnCours().getVuParToutJoueur()) {
                    byte player_ = partie_.playerAfter(partie_.getDistribution().getDonneur());
                    for(BidTarot b: partie_.tousContrats()) {
                        String pseudo_ = pseudos_.get(player_);
                        ajouterTexteDansZone(pseudo_+INTRODUCTION_PTS+b+RETURN_LINE_CHAR);
                        player_ = partie_.playerAfter(player_);
                    }
                    if(partie_.existeCarteAppelee()) {
                        String pseudo_ = pseudos_.get(partie_.getPreneur());
                        ajouterTexteDansZone(pseudo_+INTRODUCTION_PTS+partie_.getCarteAppelee()+RETURN_LINE_CHAR);
                    }
                    setChien(partie_.getDistribution().derniereMain(),false);
                    afficherMainUtilisateurTarot(false);
                    addButtonNextTrickTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                    pack();
                    return;
                }
            }
        } else {
            getConsulting().setEnabled(false);
            if (partie_.unionPlis(true).isEmpty()) {
                afficherMainUtilisateurTarot(false);
                if (partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR) {
                    if (partie_.getContrat()!=BidTarot.SLAM) {
//                        ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                        getConsulting().setEnabled(true);
                        getSlamButton().setEnabledLabel(true);
                        getSlamButton().setVisible(true);
                        getPanneauBoutonsJeu().add(getSlamButton());
                    }
                    addButtonNextTrickTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                } else {
                    addButtonNextTrickTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                }
                pack();
                return;
            }
            if (!partie_.getPliEnCours().getVuParToutJoueur()) {
                afficherMainUtilisateurTarot(false);
                if (partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR) {
                    if (partie_.getContrat()!=BidTarot.SLAM) {
//                        ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                        getConsulting().setEnabled(true);
                        getSlamButton().setEnabledLabel(true);
                        getSlamButton().setVisible(true);
                        getPanneauBoutonsJeu().add(getSlamButton());
                    }
                    addButtonNextTrickTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                } else {
                    addButtonNextTrickTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                }
                pack();
                return;
            }
        }
        getConsulting().setEnabled(false);
        getHelpGame().setEnabled(true);
        afficherMainUtilisateurTarot(false);
        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            HandTarot atouts_=partie_.getPlisAttaque().first().getCartes().couleur(Suit.TRUMP);
            if(!atouts_.estVide()) {
                for (GraphicTarotCard c: getGraphicCards(atouts_)) {
                    getPanelDiscardedTrumps().add(c);
                }
//                boolean entered_ = false;
//                for(CardTarot c: atouts_)
//                {
//                    GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//                    carte_.setPreferredSize(entered_);
//                    getPanelDiscardedTrumps().add(carte_);
//                    entered_ = true;
//                }
                getPanelDiscardedTrumps().setVisible(true);
                getPanelDiscardedTrumps().validate();
                getPanelDiscardedTrumps().repaint();
            }
        }
        boolean firstRound_ = true;
        for (TrickTarot t: partie_.unionPlis(true)) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            Numbers<Byte> players_ = partie_.orderedPlayers(t.getEntameur());
            for (byte p: players_) {
                addTextInAreaByLoading(p,pseudos_.get(p),t.carteDuJoueur(p,nombreDeJoueurs_),firstRound_);
            }
            firstRound_ = false;
        }
        TrickTarot pliEnCours_=partie_.getPliEnCours();
        Numbers<Byte> joueurs_=pliEnCours_.joueursAyantJoue(nombreDeJoueurs_);
        for (byte p: joueurs_) {
            addTextInAreaByLoading(p,pseudos_.get(p),partie_.getPliEnCours().carteDuJoueur(p,partie_.getNombreDeJoueurs()),partie_.premierTour());
        }
        for(byte p:pliEnCours_.joueursAyantJoue(nombreDeJoueurs_)) {
            tapisTarot().setCarteTarot(p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
        }
        if (!partie_.keepPlayingCurrentGame()) {
            finPartieTarot();
            pack();
            return;
        }
        animCarteTarot=new AnimationCardTarot(this);
        animCarteTarot.start();
    }
    public void ajouterBoutonContratTarot(String _texte,BidTarot _action,boolean _apte) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratTarot(_action));
        bouton_.addMouseListener(new ListenerBidTarotSingle(this,_action));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void initSlamButtonTarot() {
        LabelButton bouton_=new LabelButton(BidTarot.SLAM.toString());
        bouton_.addMouseListener(new SlamEvent(this));
        setSlamButton(bouton_);
    }
//    public void ajouterBoutonJeuChelemTarot(String _texte,boolean _apte) {
//        JPanel panneau_=getPanneauBoutonsJeu();
//        LabelButton bouton_=new LabelButton(_texte);
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
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new SeeDogEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonTakeDogCardsTarot(String _texte,boolean _apte) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new TakeDogEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void initButtonValidateDogTarot() {
        LabelButton bouton_=new LabelButton(getMessages().getVal(MainWindow.GO_CARD_GAME));
        bouton_.addMouseListener(new ValidateDogEvent(this));
        setValidateDog(bouton_);
    }
    @Override
    public void validateDog() {
        GameTarot partie_ = partieTarot();
        setCanDiscard(false);
        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            partie_.ajouterPliAttaque();
            HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
            if(!atouts_.estVide()) {
                getPanelDiscardedTrumps().removeAll();
                for (GraphicTarotCard c: getGraphicCards(atouts_)) {
                    getPanelDiscardedTrumps().add(c);
                }
//                boolean entered_ = false;
//                for(CardTarot c: atouts_)
//                {
//                    GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//                    carte_.setPreferredSize(entered_);
//                    getPanelDiscardedTrumps().add(carte_);
//                    entered_ = true;
//                }
                getPanelDiscardedTrumps().setVisible(true);
                getPanelDiscardedTrumps().validate();
                getPanelDiscardedTrumps().repaint();
                //pack();
            }
        }
        tapisTarot().setEcart(partie_.getDistribution().derniereMain());
        tapisTarot().setCartesTarotJeu(partie_.getNombreDeJoueurs());
        getScrollCallableCards().setVisible(false);
        debutPliTarot(false);
    }
//    public void addButtonValidateDogTarot(String _texte,boolean _apte) {
//        JPanel panneau_=getPanneauBoutonsJeu();
//        LabelButton bouton_=new LabelButton(_texte);
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
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new EndDealEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonNextTrickTarot(String _texte,boolean _apte) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new NextTrickEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonKeepPlayingDealTarot(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingRandomEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonKeepPlayingEditedDealTarot(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingEditedEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonStopPlayingTarot(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new StopPlayingEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonReplayDealTarot(JPanel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new ReplayEvent(this));
        _panneau.add(bouton_);
    }
    private void setChien(HandTarot _main,boolean _ecouteur) {
        setCanDiscard(_ecouteur);
        updateCardsInPanelTarotDog(tapisTarot().getCenterDeck(), _main, false);
    }
    public void placerBoutonsAvantJeuUtilisateurTarot(boolean _premierTour) {
        getHelpGame().setEnabled(true);
        //Activer les conseils
        getConsulting().setEnabled(true);
        try {
            partieTarot().changerConfiance();
        } catch (RuntimeException _0) {
        }
        getOwner().getTricksHands().setEnabled(true);
        getOwner().getTeams().setEnabled(true);
        afficherMainUtilisateurTarot(true);
        setRaisonCourante(EMPTY);
        setChoosenHandful(Handfuls.NO);
        setSelectedMiseres(new EnumMap<Miseres,Boolean>());
        if(_premierTour) {
            setCanExcludeTrumps(true);
            GameTarot partie_=partieTarot();
            EnumList<Handfuls> poignees_ = partie_.getAnnoncesPoigneesPossibles(DealTarot.NUMERO_UTILISATEUR);
            RulesTarot regles_=partie_.getRegles();
            HandTarot trumps_ = GameTarot.atoutsPoignee(partie_.getDistribution().main().couleurs());
            displayTrumpsForHandful(trumps_);
            JPanel panneau_=getPanneauBoutonsJeu();
            JPanel handFuls_ = new JPanel(new FlowLayout(FlowLayout.TRAILING,0,0));
            handFuls_.setLayout(new BoxLayout(handFuls_, BoxLayout.PAGE_AXIS));
            setInfoCurrentHandful(new JTextArea(EMPTY_STRING,1,15));
            JScrollPane scroll_ = new JScrollPane(getInfoCurrentHandful());
            scroll_.setPreferredSize(new Dimension(getEvents().getWidth(),70));
            handFuls_.add(scroll_);
            setListHandfuls(new ButtonGroup());
            for (Handfuls h: Handfuls.getNonDeclarableHandFuls()) {
                JRadioButton radio_ = new JRadioButton(h.toString());
                radio_.addMouseListener(new ListenerNoHandfulTarot(this, radio_, h));
                getListHandfuls().add(radio_);
                handFuls_.add(radio_);
            }
            for (Handfuls h: Handfuls.getDeclarableHandFuls()) {
                if (!regles_.poigneeAutorisee(h)) {
                    continue;
                }
                JRadioButton radio_ = new JRadioButton(h.toString());
                radio_.setEnabled(poignees_.containsObj(h));
                radio_.addMouseListener(new ListenerHandfulTarot(regles_.getPoigneesAutorisees().getVal(h), radio_, this, h));
                getListHandfuls().add(radio_);
                handFuls_.add(radio_);
            }
            panneau_.add(handFuls_);
            JPanel miseresPanel_ = new JPanel(new GridLayout(0,1));
            for(Miseres po_:regles_.getMiseres()) {
                JCheckBox check_ = new JCheckBox(po_.toString());
                //check_.addChangeListener(new ListenerMiseres(check_,po_));
                check_.addActionListener(new ListenerMiseresTarot(this,check_,po_));
                getSelectedMiseres().put(po_, false);
                miseresPanel_.add(check_);
            }
            panneau_.add(miseresPanel_);
        }
    }
    public void placerBoutonsFinPliUtilisateurTarot() {
        getHelpGame().setEnabled(true);
        //Activer les conseils
        getConsulting().setEnabled(false);
        GameTarot partie_=partieTarot();
        if(!partie_.keepPlayingCurrentGame()) {
            addButtonEndDealTarot(getMessages().getVal(MainWindow.END_DEAL), true);
        } else {
            partie_.setPliEnCours(true);
            addButtonNextTrickTarot(getMessages().getVal(MainWindow.NEXT_TRICK), true);
        }
    }

    public void placerBoutonsAppel() {
        //Activer les conseils
        getConsulting().setEnabled(true);
        GameTarot partie_=partieTarot();
        setCanCall(true);
        getPanneauBoutonsJeu().removeAll();
        updateCardsInPanelTarotCallBeforeDog(getPanelCallableCards(),partie_.callableCards());
        getScrollCallableCards().setVisible(true);
    }
    public void debutPliTarot(boolean _premierPliFait) {
        //Activer le sous-menu conseil
        getConsulting().setEnabled(false);
        //Activer le sous-menu aide au jeu
        getHelpGame().setEnabled(true);
        boolean premierTour_;
        GameTarot partie_=partieTarot();
        premierTour_=partie_.premierTour();
        if(premierTour_&&!_premierPliFait) {
            byte donneur_=partie_.getDistribution().getDonneur();
            if(!partie_.chelemAnnonce()) {
                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                partie_.setEntameur(partie_.playerAfter(donneur_));
            }
            partie_.setPliEnCours(true);
        }

        /*On affiche la main de l'utilisateur avec des ecouteurs sur les cartes et on supprime tous les boutons de l'ihm places a droite avant d'executer un eventuel Thread*/
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().setLayout(new BoxLayout(getPanneauBoutonsJeu(), BoxLayout.PAGE_AXIS));
        getPanneauBoutonsJeu().repaint();
        setRaisonCourante(getMessages().getVal(MainWindow.WAIT_TURN));
        setThreadAnime(true);
        animCarteTarot=new AnimationCardTarot(this);
        animCarteTarot.start();
    }
    @Override
    public void annonceTarotChelem() {
        GameTarot partie_=partieTarot();
        getScrollCallableCards().setVisible(false);
        if(!partie_.chelemAnnonce()) {
            int choix_=ConfirmDialog.getAnswer(getOwner(),getMessages().getVal(MainWindow.ASK_SLAM),getMessages().getVal(MainWindow.ASK_SLAM_TITLE), Constants.getLanguage(),JOptionPane.YES_NO_OPTION);
            if(choix_!=JOptionPane.YES_OPTION) {
                getScrollCallableCards().setVisible(true);
                return;
            }
            partie_.ajouterChelemUtilisateur();
            getPanneauBoutonsJeu().removeAll();
            getPanneauBoutonsJeu().setLayout(new BoxLayout(getPanneauBoutonsJeu(), BoxLayout.PAGE_AXIS));
            ajouterTexteDansZone(StringList.simpleFormat(getMessages().getVal(MainWindow.DECLARING_SLAM), pseudo())+RETURN_LINE_CHAR);
            setCanDiscard(false);
            if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                partie_.ajouterPliAttaque();
                HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
                if(!atouts_.estVide()) {
                    for (GraphicTarotCard c: getGraphicCards(atouts_)) {
                        getPanelDiscardedTrumps().add(c);
                    }
//                    boolean entered_ = false;
//                    for(CardTarot c: atouts_)
//                    {
//                        GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT, !entered_);
//                        carte_.setPreferredSize(entered_);
//                        getPanelDiscardedTrumps().add(carte_);
//                        entered_ = true;
//                    }
                    getPanelDiscardedTrumps().setVisible(true);
                    getPanelDiscardedTrumps().validate();
                    getPanelDiscardedTrumps().repaint();
                    //pack();
                }
            }
            getPanneauBoutonsJeu().validate();
            tapisTarot().setEcart(partie_.getDistribution().derniereMain());
            tapisTarot().setCartesTarotJeu(partie_.getNombreDeJoueurs());
            debutPliTarot(false);
        }
        //pack();
    }
    private void addTextInAreaByLoading(byte _joueur,String _pseudo,CardTarot _ct,boolean _premierTour) {
        GameTarot partie_=partieTarot();
        if(_premierTour) {
            if(partie_.pasJeuMisere()) {
                HandTarot poignee_=partie_.getPoignee(_joueur);
                for(Handfuls annonce_:partie_.getAnnoncesPoignees(_joueur)) {
                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
                }
                for(Miseres annonce_:partie_.getAnnoncesMiseres(_joueur)) {
                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
                }
                if(!poignee_.estVide()) {
                    getHandfuls().getVal(_joueur).setText(partie_.getAnnoncesPoignees(_joueur).first().toString());
                }
                poignee_.trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
                JPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
                panelToSet_.removeAll();
                for(CardTarot c: poignee_) {
                    MiniTarotCard carte_=new MiniTarotCard(c);
                    panelToSet_.add(carte_);
                }
                pack();
            }
        }
        if(partie_.getCarteAppelee().contient(_ct)) {
            getMini().setStatus(Status.CALLED_PLAYER, _joueur);
            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+Status.CALLED_PLAYER.toString());
        }

    }
    public void jouerTarot(byte _joueur,String _pseudo,boolean _premierTour) {
        GameTarot partie_=partieTarot();
        partie_.changerConfianceJeuCarteUnique();
        CardTarot ct_= partie_.getCarteJoueee();
        if(_premierTour) {
            if(partie_.pasJeuMisere()) {
                EnumList<Handfuls> annoncesPoignees_ = partie_.strategieAnnoncesPoignees(_joueur);
                partie_.setAnnoncesPoignees(_joueur, annoncesPoignees_);
                EnumList<Miseres> annoncesMiseres_ = partie_.strategieAnnoncesMiseres(_joueur);
                partie_.setAnnoncesMiseres(_joueur, annoncesMiseres_);
                HandTarot poignee_=partie_.strategiePoignee(_joueur);
                partie_.ajouterPoignee(poignee_,_joueur);
                for(Handfuls annonce_:annoncesPoignees_) {
                    ThreadInvoker.invokeNow(new AddTextEvents(this, _pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR));
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
                }
                for(Miseres annonce_:annoncesMiseres_) {
                    ThreadInvoker.invokeNow(new AddTextEvents(this, _pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR));
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
                }
                if(!poignee_.estVide()) {
                    JLabel label_ = getHandfuls().getVal(_joueur);
                    ThreadInvoker.invokeNow(new SettingText(label_, annoncesPoignees_.first().toString()));
//                    getHandfuls().getVal(_joueur).setText(annoncesPoignees_.first().toString());
                }
                poignee_.trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
                JPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
                ThreadInvoker.invokeNow(new HandfulThread(poignee_, panelToSet_));
//                panelToSet_.removeAll();
//                for(CardTarot c:poignee_)
//                {
//                    MiniTarotCard carte_=new MiniTarotCard(c);
//                    panelToSet_.add(carte_);
//                }
//                panelToSet_.validate();
                //pack();
            }
        }
        if(partie_.getCarteAppelee().contient(ct_)) {
            getMini().setStatus(Status.CALLED_PLAYER, _joueur);
            ThreadInvoker.invokeNow(new AddTextEvents(this, _pseudo+INTRODUCTION_PTS+Status.CALLED_PLAYER.toString()));
//            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+Status.CALLED_PLAYER.toString());
        }
        partie_.ajouterUneCarteDansPliEnCours(_joueur,ct_);
        tapisTarot().setCarteTarot(_joueur,ct_);
    }
    private void afficherMainUtilisateurTarotChien() {
        GameTarot partie_=partieTarot();
        //Les regles du tarot ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        HandTarot mainUtilisateur_=partie_.mainUtilisateurTriee(getDisplayingTarot());
        /*On place les cartes de l'utilisateur*/
        setCanDiscard(true);
        updateCardsInPanelTarotDog(getPanelHand(), mainUtilisateur_, true);

    }
    private void afficherMainUtilisateurTarot(boolean _ecouteur) {
        if (!_ecouteur) {
            setCarteEntree(false);
            setCarteSortie(false);
        }
        GameTarot partie_=partieTarot();
        //Les regles du tarot ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        HandTarot mainUtilisateur_=partie_.mainUtilisateurTriee(getDisplayingTarot());
        /*On place les cartes de l'utilisateur*/
        setCanPlay(_ecouteur);
        updateCardsInPanelTarotJeu(getPanelHand(), mainUtilisateur_);
        getWindow().pack();
    }
    public void finPliTarot(CardTarot _carteJouee) {
        setCanPlay(false);
        //Activer le menu Partie/Pause
        getPause().setEnabled(true);
        //Desactiver le sous-menu conseil
        getConsulting().setEnabled(false);
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
        GameTarot partie_=partieTarot();
        if(partie_.getCarteAppelee().contient(_carteJouee)) {
            getMini().setStatus(Status.CALLED_PLAYER, DealTarot.NUMERO_UTILISATEUR);
            ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+Status.CALLED_PLAYER);
        }
        /*L'utilisateur joue sa carte*/
        partie_.ajouterUneCarteDansPliEnCours(DealTarot.NUMERO_UTILISATEUR,_carteJouee);
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
        setRaisonCourante(getMessages().getVal(MainWindow.END_TRICK));
        afficherMainUtilisateurTarot(false);
        tapisTarot().setCarteTarot(DealTarot.NUMERO_UTILISATEUR,_carteJouee);
        pause();
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        getPanneauBoutonsJeu().repaint();
        animCarteTarot=new AnimationCardTarot(this);
        animCarteTarot.start();
        setThreadAnime(true);

    }
    public void finPartieTarot() {
        /*Descativer aide au jeu*/
        getHelpGame().setEnabled(false);
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
        Container container_=new Container();
        JScrollPane ascenseur_;
        container_.setLayout(new BorderLayout());

        if(isChangerPileFin()) {
            GameTarot partie_=partieTarot();
            StreamTextFile.saveObject(LaunchingCards.getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+GameEnum.TAROT.name()+FileConst.DECK_EXT,partie_.empiler());        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        StringList pseudos_=new StringList(pseudosTarot());
        JTabbedPane onglets_=new JTabbedPane();

        GameTarot partie_=partieTarot();
        if(partie_.getType()==GameType.RANDOM) {
            setPartieAleatoireJouee(true);
            if(isChangerPileFin()) {
                changerNombreDeParties(GameEnum.TAROT, partie_.getDistribution().getNombreDeParties());
            }
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();

        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(partie_);
        res_.initialize(new StringList(pseudos_), getScores());
        res_.setUser(DealTarot.NUMERO_UTILISATEUR);
        res_.setMessages(Constants.getLanguage());
        setScores(res_.getScores());

        SessionEditorPane editor_ = new SessionEditorPane();
        try {
//            editor_.setMainClass(SoftApplication.getMainClass());
//            editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor_.setLanguage(Constants.getLanguage());
            editor_.setDataBase(res_);
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
        editor_.setEditable(false);
        JScrollPane scroll_=new JScrollPane(editor_);
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.RESULTS_PAGE),scroll_);
        editor_ = new SessionEditorPane();
        try {
//            editor_.setMainClass(SoftApplication.getMainClass());
//            editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor_.setLanguage(Constants.getLanguage());
            editor_.setDataBase(res_);
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
        editor_.setEditable(false);
        ascenseur_=new JScrollPane(editor_);
        ascenseur_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.DETAIL_RESULTS_PAGE),ascenseur_);
        if(partie_.getType()==GameType.RANDOM) {
            CustList<Color> couleurs_=new CustList <Color>();
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
        GameTarot game_=partieTarot();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistribution(game_.getDistribution(), true);
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.unionPlis(true), game_.getNombreDeJoueurs());
        tricksHands_.sortHands(getDisplayingTarot(), game_.getNombreDeJoueurs());
        scroll_ = new JScrollPane(new PanelTricksHandsTarot(getOwner(),tricksHands_,
                nombreJoueurs_,
                pseudosTarot(),
                getDisplayingTarot()));
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
        int nombreTotalParties_=partie_.getRegles().getNombreParties();
        if(type_==GameType.EDIT&&nombreParties_<nombreTotalParties_) {
            addButtonKeepPlayingEditedDealTarot(buttons_, getMessages().getVal(MainWindow.KEEP_PLAYING_EDITED_DEAL));
        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&isPartieAleatoireJouee()||type_==GameType.RANDOM) {
            addButtonKeepPlayingDealTarot(buttons_, getMessages().getVal(MainWindow.KEEP_PLAYING_DEAL));
        }
        addButtonReplayDealTarot(buttons_, getMessages().getVal(MainWindow.REPLAY_DEAL));
        addButtonStopPlayingTarot(buttons_, getMessages().getVal(MainWindow.STOP));
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
//    private void changerNombreDePartiesTarot() {
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
//        GameTarot partie_=partieTarot();
//        vl_.set(GameEnum.TAROT.ordinal(),partie_.getDistribution().getNombreDeParties()+1);
//        try {
//            BufferedWriter bw_=new BufferedWriter(new FileWriter(fichier_));
//            for (int indice_ = CustList.FIRST_INDEX;indice_<total_;indice_++) {
//                bw_.write(vl_.get(indice_).toString());
//                bw_.newLine();
//            }
//            bw_.close();
//        } catch (Exception exc_) {}
//    }
    @Override
    public void modify() {
        getSave().setEnabled(true);
        getChange().setEnabled(true);
        getConsulting().setEnabled(false);
        HandTarot pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        try {
            pile_ = chargerPileTarot();
            if (!pile_.validStack()) {
                pile_ = HandTarot.pileBase();
            }
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            pile_ = HandTarot.pileBase();
        }
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.TAROT);
//        try {
//            nb_=chargerNombreDeParties(GameEnum.TAROT);
//        } catch (Exception exc_) {
//            nb_=0;
//            exc_.printStackTrace();
//        }
        DealTarot donne_;
        if(nb_==0||!getPar().enCoursDePartie()) {
            setChangerPileFin(true);
            donne_=new DealTarot(nb_,pile_);
            donne_.setRandomDealer(getReglesTarot());
            donne_.initDonne(getReglesTarot());
            getPar().jouerTarot(new GameTarot(GameType.RANDOM,donne_,getReglesTarot()));
        } else {
            GameTarot partie_=partieTarot();
            donne_=new DealTarot(nb_,partie_.empiler());
            donne_.donneurSuivant(partie_.getDistribution().getDonneur(),partie_.getRegles());
            donne_.initDonne(partie_.getRegles());
            getPar().jouerTarot(new GameTarot(GameType.RANDOM,donne_,partie_.getRegles()));
        }
        mettreEnPlaceIhmTarot();
    }
    public void editerTarot(GameTarot _partie) {
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabled(false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
        setaJoueCarte(false);
        setPartieSauvegardee(false);
        getPar().jouerTarot(_partie);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabled(false);
        setChangerPileFin(false);
        mettreEnPlaceIhmTarot();
    }
    private void mettreEnPlaceIhmTarot() {
        placerTarot();
        afficherMainUtilisateurTarot(false);
        pack();
        GameTarot partie_=partieTarot();
        byte sec_=partie_.joueurAyantPetitSec();
        if(sec_>-1) {
            String mes_ = getMessages().getVal(MainWindow.SMALL_ALONE_TEXT);
            ConfirmDialog.showMessage(getOwner(),StringList.simpleFormat(mes_, pseudosTarot().get(sec_)),getMessages().getVal(MainWindow.SMALL_ALONE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getOwner(),StringList.simpleFormat(mes_, pseudosTarot().get(sec_)),getMessages().getVal(MainWindow.SMALL_ALONE),JOptionPane.INFORMATION_MESSAGE);
            finPartieTarot();
            pack();
        } else {
            if(partie_.avecContrat()) {
                if (partie_.playerHavingToBid() != DealTarot.NUMERO_UTILISATEUR) {
                    setAnimContratTarot(new AnimationBidTarot(this));
                    getAnimContratTarot().start();
                } else {
                    setCanBid(true);
                    for(BidTarot b:partie_.allowedBids()) {
                        ajouterBoutonContratTarot(b.toString(),b,b.estDemandable(partie_.getContrat()));
                    }
                    pack();
                }
            } else {
                debutPliTarot(false);
            }
        }
    }
    public StringList pseudosTarot() {
        return pseudosTarot(partieTarot().getNombreDeJoueurs());
    }

    public void voirChien() {
        GameTarot partie_=partieTarot();
        setChien(partie_.getDistribution().derniereMain(),false);
        JPanel boutons_=getPanneauBoutonsJeu();
        boutons_.removeAll();
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            addButtonTakeDogCardsTarot(getMessages().getVal(MainWindow.TAKE_CARDS), true);
        } else {
            if (!partie_.getRegles().getDiscardAfterCall()) {
                partie_.appelApresEcart();
                if(partie_.existeCarteAppelee()) {
                    ajouterTexteDansZone(pseudosTarot().get(partie_.getPreneur())+INTRODUCTION_PTS+partie_.getCarteAppelee()+RETURN_LINE_CHAR);
                }
            } else {
                partie_.ecarter(true);
            }
            HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
            getPanelDiscardedTrumps().removeAll();
            if(!atouts_.estVide()) {
                for (GraphicTarotCard c: getGraphicCards(atouts_)) {
                    getPanelDiscardedTrumps().add(c);
                }
//                boolean entered_ = false;
//                for(CardTarot c: atouts_)
//                {
//                    GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//                    carte_.setPreferredSize(!entered_);
//                    getPanelDiscardedTrumps().add(carte_);
//                    entered_ = true;
//                }
                getPanelDiscardedTrumps().setVisible(true);
                getPanelDiscardedTrumps().validate();
                getPanelDiscardedTrumps().repaint();
            }
            addButtonNextTrickTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
        }
        //boutons_.validate();
        pack();
    }
    @Override
    public void prendreCartesChien() {
        GameTarot partie_=partieTarot();
        partie_.ajouterCartesUtilisateur();
        getConsulting().setEnabled(true);
        tapisTarot().retirerCartes();
        afficherMainUtilisateurTarotChien();
        getPanneauBoutonsJeu().removeAll();
        if (partie_.getRegles().getDiscardAfterCall()) {
            getValidateDog().setEnabledLabel(false);
            getPanneauBoutonsJeu().add(getValidateDog());
            getSlamButton().setVisible(false);
            getPanneauBoutonsJeu().add(getSlamButton());
            //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
        } else {
            placerBoutonsAppel();
        }
        pack();
    }
    public void ajouterUneCarteAuChien(CardTarot _ct) {
        getConsulting().setEnabled(false);
        GameTarot partie_=partieTarot();
        partie_.ajouterUneCarteDansPliEnCours(DealTarot.NUMERO_UTILISATEUR,_ct);
        afficherMainUtilisateurTarotChien();
        setChien(partie_.getPliEnCours().getCartes(),true);
        if (partie_.getRegles().getDiscardAfterCall()) {
//            JPanel boutons_=getPanneauBoutonsJeu();
//            LabelButton valide_=(LabelButton)boutons_.getComponent(0);
            boolean chienFait_ = partie_.getPliEnCours().total()==partie_.getDistribution().derniereMain().total();
            getValidateDog().setEnabledLabel(chienFait_);
            if(chienFait_) {
                //ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                getSlamButton().setEnabledLabel(true);
                getSlamButton().setVisible(true);
            }
            //boutons_.validate();
        }
        pack();
    }
    public void retirerUneCarteDuChien(CardTarot _ct) {
        GameTarot partie_=partieTarot();
        partie_.retirerUneCarteDuChien(_ct);
        getConsulting().setEnabled(partie_.getPliEnCours().estVide());
        partie_.ajouterUtilisateur(_ct);
        afficherMainUtilisateurTarotChien();
        setChien(partie_.getPliEnCours().getCartes(),true);
        if (partie_.getRegles().getDiscardAfterCall()) {
//            JPanel boutons_=getPanneauBoutonsJeu();
//            LabelButton valide_=(LabelButton)boutons_.getComponent(0);
            getValidateDog().setEnabledLabel(partie_.getPliEnCours().total()==partie_.getDistribution().derniereMain().total());
//            if(boutons_.getComponentCount()==2) {
//                boutons_.remove(1);
//            }
            getSlamButton().setVisible(false);
        }
        pack();
    }
    public void displayTrumpsForHandful(HandTarot _trumps) {
        getScrollDeclaringHandful().setVisible(!_trumps.estVide());
        if (getCurrentIncludedTrumps().estVide() && getCurrentExcludedTrumps().estVide()) {
            setCurrentIncludedTrumps(_trumps);
        }
        getCurrentIncludedTrumps().trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        getCurrentExcludedTrumps().trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        updateCardsInPanelTarotHandful(getIncludedTrumpsForHandful(), getCurrentIncludedTrumps(), true);
        updateCardsInPanelTarotHandful(getExcludedTrumpsForHandful(), getCurrentExcludedTrumps(), false);
        //pack();
        getDeclaringHandful().setDividerLocation(getDeclaringHandful().getWidth()*9/10);
    }
    private void updateCardsInPanelTarotDog(JPanel _panel, HandTarot _hand, boolean _inHand) {
        _panel.removeAll();
        for (GraphicTarotCard c: getGraphicCards(_hand)) {
            c.addMouseListener(new ListenerCardTarotSingleDog(this,c.getCard(),_inHand));
            _panel.add(c);
        }
//        boolean entered_ = false;
//        for(CardTarot c: _hand)
//        {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
////            carte_.addMouseListener(new EcouteurCarteTarotChien(_hand.carte(indice_),_inHand));
//            carte_.addMouseListener(new ListenerCardTarotSingleDog(this,c,_inHand));
//            _panel.add(carte_);
//            entered_ = true;
//        }
        _panel.validate();
        _panel.repaint();
    }
    private void updateCardsInPanelTarotCallBeforeDog(JPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        for (GraphicTarotCard c: getGraphicCards(_hand)) {
            c.addMouseListener(new ListenerCardTarotSingleBeforeDog(this,c.getCard()));
            _panel.add(c);
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
        _panel.validate();
        _panel.repaint();
    }
    private void updateCardsInPanelTarotHandful(JPanel _panel, HandTarot _hand, boolean _included) {
        _panel.removeAll();
        _panel.repaint();
        for(CardTarot c: _hand) {
            MiniTarotCard carte_=new MiniTarotCard(c);
//            carte_.addMouseListener(new EcouteurCarteTarotHandful(_hand.carte(indice_),_included));
            carte_.addMouseListener(new ListenerCardTarotSingleHandful(this, c,_included));
            _panel.add(carte_);
        }
    }
    private void updateCardsInPanelTarotJeu(JPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        for (GraphicTarotCard c: getGraphicCards(_hand)) {
            c.addMouseListener(new ListenerCardTarotSingleGame(this, c.getCard()));
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaint();
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

    @Override
    public void conseil() {
        GameTarot partie_=partieTarot();
        if(partie_.keepBidding()) {
            String message_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_BID), partie_.strategieContrat());
            message_ += partie_.getRaison();
            ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else if(partie_.isCallingState()) {
            if (!partie_.getRegles().getDiscardAfterCall()) {
                if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                    CallDiscard cartesAppeler_ = partie_.strategieAppelApresEcart(true);
                    String message_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_CALL), cartesAppeler_.getCarteAppelee().premiereCarte());
                    message_+=partie_.getRaison()+RETURN_LINE_CHAR;
                    message_ += StringList.simpleFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_DISCARD), cartesAppeler_.getEcartAFaire());
                    message_+=partie_.getRaison();
                    ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
                    //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
                } else {
                    HandTarot cartesAppeler_ = partie_.strategieAppel();
                    if(cartesAppeler_.estVide()) {
                        return;
                    }
                    String message_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_CALL), cartesAppeler_.premiereCarte());
                    message_+=partie_.getRaison();
                    ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
                    //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                HandTarot cartesAppeler_ = partie_.strategieAppel();
                if(cartesAppeler_.estVide()) {
                    return;
                }
                String message_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_CALL), cartesAppeler_.premiereCarte());
                message_+=partie_.getRaison();
                ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(partie_.getPliEnCours().estVide() && !partie_.getPliEnCours().getVuParToutJoueur()) {
            String message_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_DISCARD), partie_.strategieEcart());
            message_+=partie_.getRaison();
            ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else if(partie_.getContrat()!=BidTarot.SLAM && (partie_.unionPlis(true).isEmpty() || !partie_.getPliEnCours().getVuParToutJoueur())) {
            partie_.annoncerUnChelem(DealTarot.NUMERO_UTILISATEUR);
            ConfirmDialog.showMessage(getWindow(),partie_.getRaison(),getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),partie_.getRaison(),getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else {
            String message_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_PLAYER), partie_.strategieJeuCarteUnique());
            message_+=partie_.getRaison();
            ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), Constants.getLanguage(), JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        }

    }
    @Override
    public void showTeams() {
        GameTarot game_ = partieTarot();
        TeamsPlayers teams_ = new TeamsPlayers();
        teams_.setTeams(game_.playersBelongingToSameTeam());
        DialogTeamsPlayers.initDialogTeamsPlayers(getOwner());
        DialogTeamsPlayers.setDialogTeamsPlayers(pseudosTarot(), teams_);
    }
    @Override
    public void showTricksHands() {
        GameTarot game_=partieTarot();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistribution(game_.getDistribution(), true);
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.unionPlis(true), game_.getNombreDeJoueurs());
        DialogTricksTarot.setDialogTricksTarot(getMessages().getVal(MainWindow.HANDS_TRICKS_TAROT), getWindow());
        DialogTricksTarot.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosTarot(), getDisplayingTarot());
    }
    @Override
    public void aideAuJeu() {

        GameTarot partie_=partieTarot();
        HandTarot mainUtilisateur_=partie_.getDistribution().main();
        EnumMap<Suit,HandTarot> repartition_=mainUtilisateur_.couleurs();
        HandTarot cartesJouees_=partie_.cartesJoueesEnCours(DealTarot.NUMERO_UTILISATEUR);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_=cartesJouees_.couleurs();
        boolean carteAppeleeJouee_ = partie_.carteAppeleeJouee(cartesJouees_);
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_=partie_.cartesPossibles(
                !repartitionCartesJouees_.getVal(CardTarot.EXCUSE.couleur()).estVide(),
                repartitionCartesJouees_,partie_.unionPlis(false),
                !repartition_.getVal(CardTarot.EXCUSE.couleur()).estVide(),repartition_,
                DealTarot.NUMERO_UTILISATEUR,
                carteAppeleeJouee_);
        DialogHelpTarot.setTitleDialog(getWindow(),getMessages().getVal(MainWindow.HELP_GAME)+SPACE+GameEnum.TAROT);
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> hypotheses_ = partie_.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_= hypotheses_.getVal(Hypothesis.SURE);
        DialogHelpTarot.setDialogueTarot(cartesPossibles_,cartesCertaines_,repartitionCartesJouees_,pseudosTarot());
    }

    public BidTarot getContratUtilisateur() {
        return contratUtilisateur;
    }

    public void setContratUtilisateur(BidTarot _contratUtilisateur) {
        contratUtilisateur = _contratUtilisateur;
    }

    public AnimationBidTarot getAnimContratTarot() {
        return animContratTarot;
    }

    public void setAnimContratTarot(AnimationBidTarot _animContratTarot) {
        animContratTarot = _animContratTarot;
    }

    @Override
    public void nextTrick() {
        GameTarot partie_ = partieTarot();
        tapisTarot().setEcart(partie_.getDistribution().derniereMain());
        tapisTarot().setCartesTarotJeu(partie_.getNombreDeJoueurs());
        getScrollCallableCards().setVisible(false);
        debutPliTarot(!partie_.premierTour());
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
        GameTarot partie_=partieTarot();
        partie_.setNombre();
        HandTarot main_=partie_.empiler();
        DealTarot donne_=new DealTarot(0L,main_);
        donne_.donneurSuivant(partie_.getDistribution().getDonneur(),partie_.getRegles());
        donne_.initDonne(partie_.getRegles());
        getPar().jouerTarot(new GameTarot(GameType.EDIT,donne_,partie_.getRegles()));
        mettreEnPlaceIhmTarot();
    }

    @Override
    public void stopPlaying() {
        getOwner().menuSoloGames();
    }

    @Override
    public void replay() {
        GameTarot partie_=partieTarot();
        CustList<TrickTarot> plisFaits_=partie_.unionPlis(true);
        partie_.restituerMainsDepartRejouerDonne(plisFaits_, partie_.getNombreDeJoueurs());
        partie_.initPartie();
        mettreEnPlaceIhmTarot();
    }
}

