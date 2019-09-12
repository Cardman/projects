package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Status;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.MainWindow;
import cards.gui.animations.AddTextEvents;
import cards.gui.animations.AnimationBidTarot;
import cards.gui.animations.AnimationCardTarot;
import cards.gui.animations.HandfulThread;
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
import cards.tarot.*;
import cards.tarot.beans.TarotStandards;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.ChoiceTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.PlayingDog;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.maths.Rate;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;
import code.util.StringList;

public class ContainerSingleTarot extends ContainerTarot implements ContainerSingle {

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
    }

    private void placerTarot() {
        //Activer le menu Fichier/Sauvegarder
        getSave().setEnabledMenu(true);
        //Activer le menu Fichier/Changer de mode
        getChange().setEnabledMenu(true);
        //Activer les conseils
        getConsulting().setEnabledMenu(true);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabledMenu(false);
        placerIhmTarot();
    }
    private void placerIhmTarot() {
        Panel container_=Panel.newBorder();
        container_.add(new TextLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        GameTarot partie_=partieTarot();
        StringList pseudos_ = pseudosTarot();
        String lg_ = getOwner().getLanguageKey();
        CarpetTarot tapis_ = CarpetTarot.initTapisTarot(lg_, partie_.getNombreDeJoueurs(), getDisplayingTarot().isClockwise(), partie_.getDistribution().derniereMain().total());
        getTapis().setTapisTarot(tapis_);
        container_.add(tapis_.getContainer(),BorderLayout.CENTER);
        setPanelHand(Panel.newLineBox());
        Panel panneau_=Panel.newLineBox();
        panneau_.add(getPanelHand());
        setPanelDiscardedTrumps(Panel.newLineBox());
        getPanelDiscardedTrumps().setVisible(false);
        panneau_.add(getPanelDiscardedTrumps());
        panneau_.setBackground(Color.BLUE);
        container_.add(panneau_,BorderLayout.SOUTH);

        Panel panneau2_=Panel.newPageBox();
        setEvents(new TextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        panneau2_.add(new ScrollPane(getEvents()));
        setMini(MiniCarpet.newCarpet(partie_.getNombreDeJoueurs(),getDisplayingTarot().isClockwise(),pseudos_));
        panneau2_.add(getMiniPanel());
        setIncludedTrumpsForHandful(Panel.newLineBox());
        ScrollPane scrollIncl_ = new ScrollPane(getIncludedTrumpsForHandful());
        scrollIncl_.setPreferredSize(new Dimension(125,60));
        setExcludedTrumpsForHandful(Panel.newLineBox());
        ScrollPane scrollExc_ = new ScrollPane(getExcludedTrumpsForHandful());
        scrollExc_.setPreferredSize(new Dimension(125,60));
        setDeclaringHandful(new SplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollIncl_,scrollExc_));
        getDeclaringHandful().setAlignmentY(Component.LEFT_ALIGNMENT);
        getDeclaringHandful().setContinuousLayout(true);
        getDeclaringHandful().setOneTouchExpandable(true);
        setScrollDeclaringHandful(new ScrollPane(getDeclaringHandful()));
        getScrollDeclaringHandful().setPreferredSize(new Dimension(250,60));
        getScrollDeclaringHandful().setVisible(false);
        panneau2_.add(getScrollDeclaringHandful());
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
        setPanelCallableCards(Panel.newLineBox());
        setScrollCallableCards(new ScrollPane(getPanelCallableCards()));
        getScrollCallableCards().setVisible(false);
        panneau2_.add(getScrollCallableCards());
        Panel sousPanneau_=Panel.newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,BorderLayout.EAST);
        tapisTarot().setTalonTarot(lg_,partie_.getDistribution().derniereMain());
        Panel panel_ = Panel.newPageBox();
        panel_.add(new ScrollPane(container_));
        panel_.add(getWindow().getClock());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }

    public GameTarot partieTarot() {
        return getPar().partieTarot();
    }
    public void jouerDonneEntrainement(ChoiceTarot _ct) {
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabledMenu(false);
        setChangerPileFin(false);

        setPasse(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        setaJoueCarte(false);
        DealTarot donne_=new DealTarot(0L);
        donne_.initDonne(_ct, getReglesTarot());
        getPar().jouerTarot(new GameTarot(GameType.TRAINING,donne_,getReglesTarot()));
        mettreEnPlaceIhmTarot();
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
        GameTarot partie_=partieTarot();
        nombreDeJoueurs_=partie_.getNombreDeJoueurs();
        String lg_ = getOwner().getLanguageKey();
        getOwner().setTitle(GameEnum.TAROT.toString(lg_));
        placerTarot();
        pack();
        StringList pseudos_=pseudosTarot();
        getHelpGame().setEnabledMenu(false);
        if (!partie_.avecContrat()) {
            //Desactiver les conseils
            getConsulting().setEnabledMenu(false);
            for (TrickTarot t: partie_.unionPlis()) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
                Bytes players_ = partie_.orderedPlayers(t.getEntameur());
                for (byte p: players_) {
                    addTextInAreaByLoading(p,pseudos_.get(p),t.carteDuJoueur(p,nombreDeJoueurs_));
                }
            }
            TrickTarot pliEnCours_=partie_.getPliEnCours();
            Bytes joueurs_=pliEnCours_.joueursAyantJoue(nombreDeJoueurs_);
            for (byte p: joueurs_) {
                addTextInAreaByLoading(p,pseudos_.get(p),partie_.getPliEnCours().carteDuJoueur(p,partie_.getNombreDeJoueurs()));
            }
            for(byte p:pliEnCours_.joueursAyantJoue(nombreDeJoueurs_)) {
                tapisTarot().setCarteTarot(lg_, p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
            }
            if (partie_.premierTour() && pliEnCours_.estVide() && !pliEnCours_.getVuParToutJoueur()) {
                partie_.setPliEnCours(true);
            }
            getHelpGame().setEnabledMenu(true);
            if (!partie_.keepPlayingCurrentGame()) {
                finPartieTarot();
                pack();
                return;
            }
            animCarteTarot=new AnimationCardTarot(this);
            CustComponent.newThread(animCarteTarot).start();
            return;
        }
        if(partie_.keepBidding()) {
            BidTarot contrat_=partie_.getContrat();
            //Desactiver les conseils
            getConsulting().setEnabledMenu(false);
            afficherMainUtilisateurTarot(false);
            byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
            for(BidTarot b: partie_.getBids()) {
                String pseudo_ = pseudos_.get(player_);
                ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
                player_ = partie_.playerAfter(player_);
            }
            if(partie_.playerHavingToBid() != DealTarot.NUMERO_UTILISATEUR) {
                setAnimContratTarot(new AnimationBidTarot(this));
                CustComponent.newThread(getAnimContratTarot()).start();
            } else {
                //Activer les conseils
                getConsulting().setEnabledMenu(true);
                setCanBid(true);
                for(BidTarot ench_:partie_.allowedBids()) {
                    ajouterBoutonContratTarot(Games.toString(ench_,lg_),ench_,ench_.estDemandable(contrat_));
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
                byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
                for(BidTarot b: partie_.getBids()) {
                    String pseudo_ = pseudos_.get(player_);
                    ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
                    player_ = partie_.playerAfter(player_);
                }
                afficherMainUtilisateurTarot(false);
                placerBoutonsAppel();
                pack();
            } else {
                if(partie_.unionPlis().isEmpty()) {
                    boolean existCard_ = false;
                    for (CardTarot c: partie_.getDistribution().derniereMain()) {
                        if (partie_.getDistribution().hand().contient(c)) {
                            existCard_ = true;
                            break;
                        }
                    }
                    if (!existCard_) {
                        getConsulting().setEnabledMenu(false);
                        addButtonTakeDogCardsTarot(getMessages().getVal(MainWindow.TAKE_CARDS), true);
                        afficherMainUtilisateurTarot(false);
                    } else {
                        TrickTarot ecart_=partie_.getPliEnCours();
                        getConsulting().setEnabledMenu(ecart_.estVide());
                        setChien(ecart_.getCartes(),true);
                        afficherMainUtilisateurTarotChien();
                        placerBoutonsAppel();
                        pack();
                    }
                } else {
                    TrickTarot ecart_=partie_.getPliEnCours();
                    getConsulting().setEnabledMenu(ecart_.estVide());
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
                if (partie_.unionPlis().isEmpty()) {
                    getConsulting().setEnabledMenu(false);
                    boolean existCard_ = false;
                    for (CardTarot c: partie_.getDistribution().derniereMain()) {
                        if (partie_.getDistribution().hand().contient(c)) {
                            existCard_ = true;
                            break;
                        }
                    }
                    if (!partie_.getPliEnCours().estVide()) {
                        getConsulting().setEnabledMenu(false);
                        TrickTarot ecart_=partie_.getPliEnCours();
                        setChien(ecart_.getCartes(),true);
                        //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), ecart_.total()==partie_.getDistribution().derniereMain().total());
                        getValidateDog().setEnabledLabel(ecart_.total()==partie_.getDistribution().derniereMain().total());
                        getPanneauBoutonsJeu().add(getValidateDog());
                        if (ecart_.total()==partie_.getDistribution().derniereMain().total()) {
//                            ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                            getSlamButton().setEnabledLabel(true);
                            getSlamButton().setVisibleButton(true);
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
                getConsulting().setEnabledMenu(false);
                if (partie_.unionPlis().isEmpty()) {
                    byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
                    for(BidTarot b: partie_.getBids()) {
                        String pseudo_ = pseudos_.get(player_);
                        ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
                        player_ = partie_.playerAfter(player_);
                    }
                    if(!partie_.getCarteAppelee().estVide()) {
                        String pseudo_ = pseudos_.get(partie_.getPreneur());
                        ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),RETURN_LINE));
                    }
                    afficherMainUtilisateurTarot(false);
                    addButtonSeeDogTarot(getMessages().getVal(MainWindow.SEE_DOG), true);
                    pack();
                    return;
                }
                if(!partie_.getPliEnCours().getVuParToutJoueur()) {
                    byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
                    for(BidTarot b: partie_.getBids()) {
                        String pseudo_ = pseudos_.get(player_);
                        ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
                        player_ = partie_.playerAfter(player_);
                    }
                    if(!partie_.getCarteAppelee().estVide()) {
                        String pseudo_ = pseudos_.get(partie_.getPreneur());
                        ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),RETURN_LINE));
                    }
                    setChien(partie_.getDistribution().derniereMain(),false);
                    afficherMainUtilisateurTarot(false);
                    addButtonNextTrickTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                    pack();
                    return;
                }
            }
        } else {
            getConsulting().setEnabledMenu(false);
            if (partie_.unionPlis().isEmpty()) {
                afficherMainUtilisateurTarot(false);
                if (partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR) {
                    if (partie_.getContrat()!=BidTarot.SLAM) {
//                        ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                        getConsulting().setEnabledMenu(true);
                        getSlamButton().setEnabledLabel(true);
                        getSlamButton().setVisibleButton(true);
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
                        getConsulting().setEnabledMenu(true);
                        getSlamButton().setEnabledLabel(true);
                        getSlamButton().setVisibleButton(true);
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
        getConsulting().setEnabledMenu(false);
        getHelpGame().setEnabledMenu(true);
        afficherMainUtilisateurTarot(false);
        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            HandTarot atouts_=partie_.getTricks().first().getCartes().couleur(Suit.TRUMP);
            if(!atouts_.estVide()) {
                for (GraphicTarotCard c: getGraphicCards(lg_,atouts_)) {
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
                getPanelDiscardedTrumps().repaintChildren();
            }
        }
        for (TrickTarot t: partie_.unionPlis()) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            Bytes players_ = partie_.orderedPlayers(t.getEntameur());
            for (byte p: players_) {
                addTextInAreaByLoading(p,pseudos_.get(p),t.carteDuJoueur(p,nombreDeJoueurs_));
            }
        }
        TrickTarot pliEnCours_=partie_.getPliEnCours();
        Bytes joueurs_=pliEnCours_.joueursAyantJoue(nombreDeJoueurs_);
        for (byte p: joueurs_) {
            addTextInAreaByLoading(p,pseudos_.get(p),partie_.getPliEnCours().carteDuJoueur(p,partie_.getNombreDeJoueurs()));
        }
        for(byte p:pliEnCours_.joueursAyantJoue(nombreDeJoueurs_)) {
            tapisTarot().setCarteTarot(lg_,p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_));
        }
        if (!partie_.keepPlayingCurrentGame()) {
            finPartieTarot();
            pack();
            return;
        }
        animCarteTarot=new AnimationCardTarot(this);
        CustComponent.newThread(animCarteTarot).start();
    }
    public void ajouterBoutonContratTarot(String _texte,BidTarot _action,boolean _apte) {
        Panel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratTarot(_action));
        bouton_.addMouseListener(new ListenerBidTarotSingle(this,_action));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void initSlamButtonTarot() {
        String lg_ = getOwner().getLanguageKey();
        LabelButton bouton_=new LabelButton(Games.toString(BidTarot.SLAM,lg_));
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
        Panel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new SeeDogEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonTakeDogCardsTarot(String _texte,boolean _apte) {
        Panel panneau_=getPanneauBoutonsJeu();
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
        String lg_ = getOwner().getLanguageKey();
        setCanDiscard(false);
        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            partie_.addCurTrick();
            HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
            if(!atouts_.estVide()) {
                getPanelDiscardedTrumps().removeAll();
                for (GraphicTarotCard c: getGraphicCards(lg_,atouts_)) {
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
                getPanelDiscardedTrumps().repaintChildren();
                //pack();
            }
        }
        tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain());
        tapisTarot().setCartesTarotJeu(lg_,partie_.getNombreDeJoueurs());
        getScrollCallableCards().setVisible(false);
        debutPliTarot();
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
        Panel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new EndDealEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonNextTrickTarot(String _texte,boolean _apte) {
        Panel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new NextTrickEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonKeepPlayingDealTarot(Panel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingRandomEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonKeepPlayingEditedDealTarot(Panel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new KeepPlayingEditedEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonStopPlayingTarot(Panel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new StopPlayingEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonReplayDealTarot(Panel _panneau,String _texte) {
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new ReplayEvent(this));
        _panneau.add(bouton_);
    }
    private void setChien(HandTarot _main,boolean _ecouteur) {
        setCanDiscard(_ecouteur);
        updateCardsInPanelTarotDog(tapisTarot().getCenterDeck(), _main, false);
    }
    public void placerBoutonsAvantJeuUtilisateurTarot() {
        getHelpGame().setEnabledMenu(true);
        //Activer les conseils
        getConsulting().setEnabledMenu(true);
        partieTarot().changerConfiance();
        getOwner().getTricksHands().setEnabledMenu(true);
        getOwner().getTeams().setEnabledMenu(true);
        afficherMainUtilisateurTarot(true);
        setRaisonCourante(EMPTY);
        setChoosenHandful(Handfuls.NO);
        setSelectedMiseres(new EnumMap<Miseres,Boolean>());
        String lg_ = getOwner().getLanguageKey();
        GameTarot partie_=partieTarot();
        if(partie_.premierTourNoMisere()) {
            setCanExcludeTrumps(true);
            EnumList<Handfuls> poignees_ = partie_.getAnnoncesPoigneesPossibles(DealTarot.NUMERO_UTILISATEUR);
            RulesTarot regles_=partie_.getRegles();
            HandTarot trumps_ = GameTarotCommonPlaying.atoutsPoignee(partie_.getDistribution().hand().couleurs());
            displayTrumpsForHandful(trumps_);
            Panel panneau_=getPanneauBoutonsJeu();
            Panel handFuls_ = Panel.newPageBox();
            setInfoCurrentHandful(new TextArea(EMPTY_STRING,1,15));
            ScrollPane scroll_ = new ScrollPane(getInfoCurrentHandful());
            scroll_.setPreferredSize(new Dimension(getEvents().getWidth(),70));
            handFuls_.add(scroll_);
            CustList<RadioButton> list_ = new CustList<RadioButton>();
            for (Handfuls h: Handfuls.getNonDeclarableHandFuls()) {
                RadioButton radio_ = new RadioButton(Games.toString(h,lg_));
                list_.add(radio_);
                radio_.addMouseListener(new ListenerNoHandfulTarot(this, radio_, h,list_));
                handFuls_.add(radio_);
            }
            for (Handfuls h: Handfuls.getDeclarableHandFuls()) {
                if (!regles_.poigneeAutorisee(h)) {
                    continue;
                }
                RadioButton radio_ = new RadioButton(Games.toString(h,lg_));
                list_.add(radio_);
                radio_.setEnabled(poignees_.containsObj(h));
                radio_.addMouseListener(new ListenerHandfulTarot(regles_.getPoigneesAutorisees().getVal(h), radio_, this, h,list_));
                handFuls_.add(radio_);
            }
            panneau_.add(handFuls_);
            Panel miseresPanel_ = Panel.newGrid(0,1);
            for(Miseres po_:regles_.getMiseres()) {
                CustCheckBox check_ = new CustCheckBox(Games.toString(po_,lg_));
                //check_.addChangeListener(new ListenerMiseres(check_,po_));
                check_.addActionListener(new ListenerMiseresTarot(this,check_,po_));
                getSelectedMiseres().put(po_, false);
                miseresPanel_.add(check_);
            }
            panneau_.add(miseresPanel_);
        }
    }
    public void placerBoutonsFinPliUtilisateurTarot() {
        getHelpGame().setEnabledMenu(true);
        //Activer les conseils
        getConsulting().setEnabledMenu(false);
        GameTarot partie_=partieTarot();
        if(!partie_.keepPlayingCurrentGame()) {
            addButtonEndDealTarot(getMessages().getVal(MainWindow.END_DEAL), true);
        } else {
            addButtonNextTrickTarot(getMessages().getVal(MainWindow.NEXT_TRICK), true);
        }
    }

    public void placerBoutonsAppel() {
        //Activer les conseils
        getConsulting().setEnabledMenu(true);
        GameTarot partie_=partieTarot();
        setCanCall(true);
        getPanneauBoutonsJeu().removeAll();
        updateCardsInPanelTarotCallBeforeDog(getPanelCallableCards(),partie_.callableCards());
        getScrollCallableCards().setVisible(true);
    }
    public void debutPliTarot() {
        //Activer le sous-menu conseil
        getConsulting().setEnabledMenu(false);
        //Activer le sous-menu aide au jeu
        getHelpGame().setEnabledMenu(true);
        GameTarot partie_=partieTarot();
        if(partie_.premierTour()) {
            byte donneur_=partie_.getDistribution().getDealer();
            if(!partie_.chelemAnnonce()) {
                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                partie_.setEntameur(partie_.playerAfter(donneur_));
            }
            partie_.setPliEnCours(true);
        }

        /*On affiche la main de l'utilisateur avec des ecouteurs sur les cartes et on supprime tous les boutons de l'ihm places a droite avant d'executer un eventuel Thread*/
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        setRaisonCourante(getMessages().getVal(MainWindow.WAIT_TURN));
        setThreadAnime(true);
        animCarteTarot=new AnimationCardTarot(this);
        CustComponent.newThread(animCarteTarot).start();
    }
    @Override
    public void annonceTarotChelem() {
        GameTarot partie_=partieTarot();
        getScrollCallableCards().setVisible(false);
        String lg_ = getOwner().getLanguageKey();
        if(!partie_.chelemAnnonce()) {
            int choix_=ConfirmDialog.getAnswer(getOwner(),getMessages().getVal(MainWindow.ASK_SLAM),getMessages().getVal(MainWindow.ASK_SLAM_TITLE), lg_,JOptionPane.YES_NO_OPTION);
            if(choix_!=JOptionPane.YES_OPTION) {
                getScrollCallableCards().setVisible(true);
                return;
            }
            partie_.ajouterChelemUtilisateur();
            getPanneauBoutonsJeu().removeAll();
            ajouterTexteDansZone(StringList.concat(StringList.simpleStringsFormat(getMessages().getVal(MainWindow.DECLARING_SLAM), pseudo()),RETURN_LINE));
            setCanDiscard(false);
            if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                partie_.addCurTrick();
                HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
                if(!atouts_.estVide()) {
                    for (GraphicTarotCard c: getGraphicCards(lg_,atouts_)) {
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
                    getPanelDiscardedTrumps().repaintChildren();
                    //pack();
                }
            }
            getPanneauBoutonsJeu().validate();
            tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain());
            tapisTarot().setCartesTarotJeu(lg_,partie_.getNombreDeJoueurs());
            debutPliTarot();
        }
        //pack();
    }
    private void addTextInAreaByLoading(byte _joueur, String _pseudo, CardTarot _ct) {
        GameTarot partie_=partieTarot();
        String lg_ = getOwner().getLanguageKey();
        if(partie_.premierTourNoMisere()) {
            HandTarot poignee_=partie_.getPoignee(_joueur);
            for(Handfuls annonce_:partie_.getAnnoncesPoignees(_joueur)) {
                ajouterTexteDansZone(StringList.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE));
            }
            for(Miseres annonce_:partie_.getAnnoncesMiseres(_joueur)) {
                ajouterTexteDansZone(StringList.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE));
            }
            if(!poignee_.estVide()) {
                getHandfuls().getVal(_joueur).setText(Games.toString(partie_.getAnnoncesPoignees(_joueur).first(),lg_));
            }
            poignee_.trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
            Panel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
            panelToSet_.removeAll();
            for(CardTarot c: poignee_) {
                MiniTarotCard carte_=new MiniTarotCard(lg_, c);
                panelToSet_.add(carte_);
            }
            pack();
        }
        if(partie_.getCarteAppelee().contient(_ct)) {
            getMini().setStatus(Status.CALLED_PLAYER, _joueur);
            ajouterTexteDansZone(StringList.concat(_pseudo,INTRODUCTION_PTS,Games.toString(Status.CALLED_PLAYER,lg_)));
        }

    }
    public void jouerTarot(byte _joueur, String _pseudo) {
        GameTarot partie_=partieTarot();
        partie_.changerConfianceJeuCarteUnique();
        CardTarot ct_= partie_.getCarteJoueee();
        String lg_ = getOwner().getLanguageKey();
        if(partie_.premierTourNoMisere()) {
            EnumList<Handfuls> annoncesPoignees_ = partie_.getAnnoncesPoignees(_joueur);
            EnumList<Miseres> annoncesMiseres_ = partie_.getAnnoncesMiseres(_joueur);
            HandTarot poignee_=partie_.getPoignee(_joueur);
            for(Handfuls annonce_:annoncesPoignees_) {
                ThreadInvoker.invokeNow(new AddTextEvents(this, StringList.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE)));
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
            }
            for(Miseres annonce_:annoncesMiseres_) {
                ThreadInvoker.invokeNow(new AddTextEvents(this, StringList.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE)));
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
            }
            if(!poignee_.estVide()) {
                TextLabel label_ = getHandfuls().getVal(_joueur);
                ThreadInvoker.invokeNow(new SettingText(label_, Games.toString(annoncesPoignees_.first(),lg_)));
//                    getHandfuls().getVal(_joueur).setText(annoncesPoignees_.first().toString());
            }
            poignee_.trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
            Panel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
            ThreadInvoker.invokeNow(new HandfulThread(poignee_, panelToSet_, getWindow()));
//                panelToSet_.removeAll();
//                for(CardTarot c:poignee_)
//                {
//                    MiniTarotCard carte_=new MiniTarotCard(c);
//                    panelToSet_.add(carte_);
//                }
//                panelToSet_.validate();
            //pack();
        }
        if(partie_.getCarteAppelee().contient(ct_)) {
            getMini().setStatus(Status.CALLED_PLAYER, _joueur);
            ThreadInvoker.invokeNow(new AddTextEvents(this, StringList.concat(_pseudo,INTRODUCTION_PTS,Games.toString(Status.CALLED_PLAYER,lg_))));
//            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+Status.CALLED_PLAYER.toString());
        }
        partie_.ajouterUneCarteDansPliEnCours(_joueur,ct_);
        tapisTarot().setCarteTarot(lg_,_joueur,ct_);
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
        getPause().setEnabledMenu(true);
        //Desactiver le sous-menu conseil
        getConsulting().setEnabledMenu(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        GameTarot partie_=partieTarot();
        String lg_ = getOwner().getLanguageKey();
        if(partie_.getCarteAppelee().contient(_carteJouee)) {
            getMini().setStatus(Status.CALLED_PLAYER, DealTarot.NUMERO_UTILISATEUR);
            ajouterTexteDansZone(StringList.concat(pseudo(),INTRODUCTION_PTS,Games.toString(Status.CALLED_PLAYER,lg_)));
        }
        /*L'utilisateur joue sa carte*/
        partie_.ajouterUneCarteDansPliEnCours(DealTarot.NUMERO_UTILISATEUR,_carteJouee);
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
        setRaisonCourante(getMessages().getVal(MainWindow.END_TRICK));
        afficherMainUtilisateurTarot(false);
        tapisTarot().setCarteTarot(lg_,DealTarot.NUMERO_UTILISATEUR,_carteJouee);
        pause();
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        animCarteTarot=new AnimationCardTarot(this);
        CustComponent.newThread(animCarteTarot).start();
        setThreadAnime(true);

    }
    public void finPartieTarot() {
        /*Descativer aide au jeu*/
        String lg_ = getOwner().getLanguageKey();
        getHelpGame().setEnabledMenu(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        Panel container_=Panel.newBorder();
        ScrollPane ascenseur_;

        if(isChangerPileFin()) {
            GameTarot partie_=partieTarot();
            StreamTextFile.saveTextFile(StringList.concat(LaunchingCards.getTempFolderSl(),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.TAROT.name(),FileConst.DECK_EXT),DocumentWriterTarotUtil.setHandTarot(partie_.empiler()));
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        StringList pseudos_=new StringList(pseudosTarot());
        TabbedPane onglets_=new TabbedPane();

        GameTarot partie_=partieTarot();
        if(partie_.getType()==GameType.RANDOM) {
            setPartieAleatoireJouee(true);
            if(isChangerPileFin()) {
                changerNombreDeParties(GameEnum.TAROT, partie_.getDistribution().getNbDeals());
            }
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();

        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(partie_);
        res_.setUser(DealTarot.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(pseudos_), getScores());
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        setScores(res_.getScores());

        ScrollPane scroll_=new ScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_);
        editor_.setLanguage(lg_);
        editor_.setDataBase(res_);
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT, new TarotStandards());
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.RESULTS_PAGE),scroll_);
        ascenseur_=new ScrollPane();
        editor_ = new RenderedPage(ascenseur_);
        editor_.setLanguage(lg_);
        editor_.setDataBase(res_);
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT, new TarotStandards());
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
            Graphic graphique_=new Graphic(getScores(),new Longs(res_.getSums()),new EqList<Rate>(res_.getSigmas()),couleurs_);
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
            ScrollPane locScroll_=new ScrollPane(graphique_);
            graphique_.setLocation(0,(600-dimy_)/2);
            locScroll_.setPreferredSize(new Dimension(300,200));
            Panel panneau_=Panel.newBorder();
            panneau_.add(new TextLabel(getMessages().getVal(MainWindow.SCORES_EVOLUTION_DETAIL),SwingConstants.CENTER),BorderLayout.NORTH);
            panneau_.add(locScroll_,BorderLayout.CENTER);
            GraphicKey legende_=new GraphicKey(pseudos_,couleurs_, lg_);
            legende_.setPreferredSize(new Dimension(300,15*(nombreJoueurs_+1)));
            locScroll_=new ScrollPane(legende_);
            locScroll_.setPreferredSize(new Dimension(300,100));
            panneau_.add(locScroll_,BorderLayout.SOUTH);
            onglets_.add(getMessages().getVal(MainWindow.SCORES_EVOLUTION),panneau_);
        }
        GameTarot game_=partieTarot();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        tricksHands_.sortHands(getDisplayingTarot(), game_.getNombreDeJoueurs());
        MainWindow ow_ = getOwner();
        ScrollPane panelCards_ = new ScrollPane(new PanelTricksHandsTarot(ow_,tricksHands_,
                nombreJoueurs_,
                pseudosTarot(),
                getDisplayingTarot(),ow_).getContainer());
        panelCards_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.HANDS_TRICKS),panelCards_);
        container_.add(onglets_,BorderLayout.CENTER);
        Panel panneau_=Panel.newPageBox();
        Panel buttons_ = Panel.newLineBox();
        GameType type_;
        long nombreParties_;
        type_=partie_.getType();
        nombreParties_=partie_.getNumber();
        int nombreTotalParties_=partie_.getRegles().getNbDeals();
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
    @Override
    public void modify() {
        getSave().setEnabledMenu(true);
        getChange().setEnabledMenu(true);
        getConsulting().setEnabledMenu(false);
        HandTarot pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        pile_ = chargerPileTarot();
        if (!pile_.validStack()) {
            pile_ = HandTarot.pileBase();
        }
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.TAROT);
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
            donne_.donneurSuivant(partie_.getDistribution().getDealer(),partie_.getRegles());
            donne_.initDonne(partie_.getRegles());
            getPar().jouerTarot(new GameTarot(GameType.RANDOM,donne_,partie_.getRegles()));
        }
        mettreEnPlaceIhmTarot();
    }
    public void editerTarot(GameTarot _partie) {
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabledMenu(false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        setaJoueCarte(false);
        setPartieSauvegardee(false);
        getPar().jouerTarot(_partie);
        //Desactiver le menu Partie/Demo
        getDemo().setEnabledMenu(false);
        setChangerPileFin(false);
        mettreEnPlaceIhmTarot();
    }
    private void mettreEnPlaceIhmTarot() {
        placerTarot();
        afficherMainUtilisateurTarot(false);
        pack();
        GameTarot partie_=partieTarot();
        String lg_ = getOwner().getLanguageKey();
        if(partie_.avecContrat()) {
            if (partie_.playerHavingToBid() != DealTarot.NUMERO_UTILISATEUR) {
                setAnimContratTarot(new AnimationBidTarot(this));
                CustComponent.newThread(getAnimContratTarot()).start();
            } else {
                setCanBid(true);
                for(BidTarot b:partie_.allowedBids()) {
                    ajouterBoutonContratTarot(Games.toString(b,lg_),b,b.estDemandable(partie_.getContrat()));
                }
                pack();
            }
        } else {
            debutPliTarot();
        }
    }
    public StringList pseudosTarot() {
        return pseudosTarot(partieTarot().getNombreDeJoueurs());
    }

    public void voirChien() {
        String lg_ = getOwner().getLanguageKey();
        GameTarot partie_=partieTarot();
        setChien(partie_.getDistribution().derniereMain(),false);
        Panel boutons_=getPanneauBoutonsJeu();
        boutons_.removeAll();
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            addButtonTakeDogCardsTarot(getMessages().getVal(MainWindow.TAKE_CARDS), true);
        } else {
            if (!partie_.getRegles().getDiscardAfterCall()) {
                partie_.appelApresEcart();
                if(!partie_.getCarteAppelee().estVide()) {
                    ajouterTexteDansZone(StringList.concat(pseudosTarot().get(partie_.getPreneur()),INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),RETURN_LINE));
                }
            } else {
                partie_.ecarter(true);
            }
            HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
            getPanelDiscardedTrumps().removeAll();
            if(!atouts_.estVide()) {
                for (GraphicTarotCard c: getGraphicCards(lg_,atouts_)) {
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
                getPanelDiscardedTrumps().repaintChildren();
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
        getConsulting().setEnabledMenu(true);
        tapisTarot().retirerCartes();
        afficherMainUtilisateurTarotChien();
        getPanneauBoutonsJeu().removeAll();
        if (partie_.getRegles().getDiscardAfterCall()) {
            getValidateDog().setEnabledLabel(false);
            getPanneauBoutonsJeu().add(getValidateDog());
            getSlamButton().setVisibleButton(false);
            getPanneauBoutonsJeu().add(getSlamButton());
            //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
        } else {
            placerBoutonsAppel();
        }
        pack();
    }
    public void ajouterUneCarteAuChien(CardTarot _ct) {
        getConsulting().setEnabledMenu(false);
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
                getSlamButton().setVisibleButton(true);
            }
            //boutons_.validate();
        }
        pack();
    }
    public void retirerUneCarteDuChien(CardTarot _ct) {
        GameTarot partie_=partieTarot();
        partie_.retirerUneCarteDuChien(_ct);
        getConsulting().setEnabledMenu(partie_.getPliEnCours().estVide());
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
            getSlamButton().setVisibleButton(false);
        }
        pack();
    }
    public void displayTrumpsForHandful(HandTarot _trumps) {
        getScrollDeclaringHandful().setVisible(!_trumps.estVide());
        if (getCurrentIncludedTrumps().estVide() && getCurrentExcludedTrumps().estVide()) {
            setCurrentIncludedTrumps(_trumps);
        }
        getCurrentIncludedTrumps().trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
        getCurrentExcludedTrumps().trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
        updateCardsInPanelTarotHandful(getIncludedTrumpsForHandful(), getCurrentIncludedTrumps(), true);
        updateCardsInPanelTarotHandful(getExcludedTrumpsForHandful(), getCurrentExcludedTrumps(), false);
        //pack();
        getDeclaringHandful().setDividerLocation(getDeclaringHandful().getWidth()*9/10);
    }
    private void updateCardsInPanelTarotDog(Panel _panel, HandTarot _hand, boolean _inHand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(lg_,_hand)) {
            c.addMouseListener(new ListenerCardTarotSingleDog(this,c.getCard(),_inHand,c));
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
        _panel.repaintChildren();
    }
    private void updateCardsInPanelTarotCallBeforeDog(Panel _panel, HandTarot _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(lg_,_hand)) {
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
        _panel.repaintChildren();
    }
    private void updateCardsInPanelTarotHandful(Panel _panel, HandTarot _hand, boolean _included) {
        _panel.removeAll();
        _panel.validate();
        String lg_ = getOwner().getLanguageKey();
        for(CardTarot c: _hand) {
            MiniTarotCard carte_=new MiniTarotCard(lg_, c);
//            carte_.addMouseListener(new EcouteurCarteTarotHandful(_hand.carte(indice_),_included));
            carte_.addMouseListener(new ListenerCardTarotSingleHandful(this, c,_included));
            _panel.add(carte_);
        }
    }
    private void updateCardsInPanelTarotJeu(Panel _panel, HandTarot _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(lg_,_hand)) {
            c.addMouseListener(new ListenerCardTarotSingleGame(this, c.getCard()));
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaintChildren();
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
        String lg_ = getOwner().getLanguageKey();
        if(partie_.keepBidding()) {
            String message_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_BID), Games.toString(partie_.strategieContrat(),lg_));
            ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else if(partie_.isCallingState()) {
            if (!partie_.getRegles().getDiscardAfterCall()) {
                if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                    CallDiscard cartesAppeler_ = partie_.strategieAppelApresEcart(true);
                    String message_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_CALL), Games.toString(cartesAppeler_.getCarteAppelee().premiereCarte(),lg_),RETURN_LINE);
                    message_ = StringList.concat(message_,StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_DISCARD), Games.toString(cartesAppeler_.getEcartAFaire(),lg_)));
                    ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
                    //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
                } else {
                    HandTarot cartesAppeler_ = partie_.strategieAppel();
                    if(cartesAppeler_.estVide()) {
                        return;
                    }
                    String message_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_CALL), Games.toString(cartesAppeler_.premiereCarte(),lg_));
                    ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
                    //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                HandTarot cartesAppeler_ = partie_.strategieAppel();
                if(cartesAppeler_.estVide()) {
                    return;
                }
                String message_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_CALL), Games.toString(cartesAppeler_.premiereCarte(),lg_));
                ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(partie_.getPliEnCours().estVide() && !partie_.getPliEnCours().getVuParToutJoueur()) {
            String message_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_DISCARD), Games.toString(partie_.strategieEcart(),lg_));
            ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else if(partie_.getContrat()!=BidTarot.SLAM && (partie_.unionPlis().isEmpty() || !partie_.getPliEnCours().getVuParToutJoueur())) {
            partie_.annoncerUnChelem(DealTarot.NUMERO_UTILISATEUR);
            ConfirmDialog.showMessage(getWindow(),EMPTY_STRING,getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),partie_.getRaison(),getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else {
            String message_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CONSULT_TAROT_PLAYER), Games.toString(partie_.strategieJeuCarteUnique(),lg_));
            ConfirmDialog.showMessage(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE), lg_, JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        }

    }
    @Override
    public void showTeams() {
        GameTarot game_ = partieTarot();
        TeamsPlayers teams_ = new TeamsPlayers();
        teams_.setTeams(game_.getTeamsRelation().teams());
        DialogTeamsPlayers.initDialogTeamsPlayers(getOwner());
        DialogTeamsPlayers.setDialogTeamsPlayers(pseudosTarot(), teams_);
    }
    @Override
    public void showTricksHands() {
        GameTarot game_=partieTarot();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        MainWindow ow_ = getOwner();
        DialogTricksTarot.setDialogTricksTarot(getMessages().getVal(MainWindow.HANDS_TRICKS_TAROT), ow_);
        DialogTricksTarot.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosTarot(), getDisplayingTarot(),ow_);
    }
    @Override
    public void aideAuJeu() {
        String lg_ = getOwner().getLanguageKey();
        GameTarot partie_=partieTarot();
        HandTarot mainUtilisateur_=partie_.getDistribution().hand();
        GameTarotTrickInfo doneTrickInfo_ = partie_.getDoneTrickInfo();
        HandTarot cartesJouees_=doneTrickInfo_.cartesJoueesEnCours(DealTarot.NUMERO_UTILISATEUR);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_=cartesJouees_.couleurs();
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_= doneTrickInfo_.cartesPossibles(mainUtilisateur_);
        DialogHelpTarot.setTitleDialog(getWindow(),StringList.concat(getMessages().getVal(MainWindow.HELP_GAME),SPACE,GameEnum.TAROT.toString(lg_)));
        EnumMap<Hypothesis,EnumMap<Suit,CustList<HandTarot>>> hypotheses_ = doneTrickInfo_.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_= hypotheses_.getVal(Hypothesis.SURE);
        DialogHelpTarot.setDialogueTarot(cartesPossibles_,cartesCertaines_,repartitionCartesJouees_,pseudosTarot(), lg_);
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
        String lg_ = getOwner().getLanguageKey();
        tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain());
        tapisTarot().setCartesTarotJeu(lg_,partie_.getNombreDeJoueurs());
        getScrollCallableCards().setVisible(false);
        debutPliTarot();
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
        donne_.donneurSuivant(partie_.getDistribution().getDealer(),partie_.getRegles());
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
        CustList<TrickTarot> plisFaits_=partie_.getTricks();
        partie_.restituerMainsDepartRejouerDonne(plisFaits_, partie_.getNombreDeJoueurs());
        partie_.initPartie();
        mettreEnPlaceIhmTarot();
    }
}

