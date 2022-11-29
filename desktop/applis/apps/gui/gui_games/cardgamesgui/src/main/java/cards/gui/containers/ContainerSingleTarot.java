package cards.gui.containers;






import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Role;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.WindowCards;
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
import cards.gui.labels.*;
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
import code.gui.document.PreparedAnalyzed;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.maths.Rate;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class ContainerSingleTarot extends ContainerTarot implements ContainerSingle,ContainerPlayableTarot {

    private BidTarot contratUtilisateur = BidTarot.FOLD;

    public ContainerSingleTarot(WindowCards _window) {
        super(_window);
        initButtonValidateDogTarot();
        initSlamButtonTarot();
    }

    public ContainerSingleTarot(WindowCards _window, String _file) {
        super(_window);
        initButtonValidateDogTarot();
        initSlamButtonTarot();
    }

    private void placerTarot() {
        //Activer le menu Fichier/Sauvegarder
        MenuItemUtils.setEnabledMenu(getSave(),true);
        //Activer le menu Fichier/Changer de mode
        MenuItemUtils.setEnabledMenu(getChange(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        //Desactiver le menu Partie/Demo
        MenuItemUtils.setEnabledMenu(getDemo(),false);
        placerIhmTarot();
    }
    private void placerIhmTarot() {
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowCards.HELP_GO_MENU)),GuiConstants.BORDER_LAYOUT_NORTH);
        GameTarot partie_=partieTarot();
        StringList pseudos_ = pseudosTarot();
        String lg_ = getOwner().getLanguageKey();
        CarpetTarot tapis_ = CarpetTarot.initTapisTarot(lg_, partie_.getNombreDeJoueurs(), getDisplayingTarot().getDisplaying().isClockwise(), partie_.getDistribution().derniereMain().total(), getOwner().getCompoFactory());
        getTapis().setTapisTarot(tapis_);
        container_.add(tapis_.getContainer(),GuiConstants.BORDER_LAYOUT_CENTER);
        setPanelHand(getOwner().getCompoFactory().newLineBox());
        AbsPanel panneau_=getOwner().getCompoFactory().newLineBox();
        panneau_.add(getPanelHand());
        setPanelDiscardedTrumps(getOwner().getCompoFactory().newLineBox());
        getPanelDiscardedTrumps().setVisible(false);
        panneau_.add(getPanelDiscardedTrumps());
        panneau_.setBackground(GuiConstants.BLUE);
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);

        AbsPanel panneau2_=getOwner().getCompoFactory().newPageBox();
        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
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
        setHandfuls(new ByteMap<AbsPlainLabel>());
        setDeclaredHandfuls(new ByteMap<AbsPanel>());
        AbsPanel declaredHandfuls_ = getOwner().getCompoFactory().newGrid(0,1);
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
        setPanelCallableCards(getOwner().getCompoFactory().newLineBox());
        setScrollCallableCards(getOwner().getCompoFactory().newAbsScrollPane(getPanelCallableCards()));
        getScrollCallableCards().setVisible(false);
        panneau2_.add(getScrollCallableCards());
        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
        tapisTarot().setTalonTarot(lg_,partie_.getDistribution().derniereMain(), getOwner().getCompoFactory());
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

        setPasse(false);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        setaJoueCarte(false);
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
        MenuItemUtils.setEnabledMenu(getDemo(),false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
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
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        if (!partie_.avecContrat()) {
            //Desactiver les conseils
            MenuItemUtils.setEnabledMenu(getConsulting(),false);
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
                tapisTarot().setCarteTarot(getWindow().getImageFactory(),lg_, p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_), getWindow().getImages());
            }
            if (partie_.premierTour() && pliEnCours_.estVide() && !pliEnCours_.getVuParToutJoueur()) {
                partie_.setPliEnCours(true);
            }
            MenuItemUtils.setEnabledMenu(getHelpGame(),true);
            if (!partie_.keepPlayingCurrentGame()) {
                finPartieTarot();
                pack();
                return;
            }
            thread(new AnimationCardTarot(this));
            return;
        }
        if(partie_.keepBidding()) {
            BidTarot contrat_=partie_.getContrat();
            //Desactiver les conseils
            MenuItemUtils.setEnabledMenu(getConsulting(),false);
            afficherMainUtilisateurTarot(false);
            byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
            for(BidTarot b: partie_.getBids()) {
                String pseudo_ = pseudos_.get(player_);
                ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
                player_ = partie_.playerAfter(player_);
            }
            if(partie_.playerHavingToBid() != DealTarot.NUMERO_UTILISATEUR) {
                thread(new AnimationBidTarot(this));
            } else {
                //Activer les conseils
                MenuItemUtils.setEnabledMenu(getConsulting(),true);
                setCanBid(true);
                for(BidTarot ench_:partie_.allowedBids()) {
                    ajouterBoutonContratTarot(Games.toString(ench_,lg_),ench_,ench_.estDemandable(contrat_));
                }
                pack();
            }
            return;
        }
        if (partie_.getContrat().isJouerDonne()) {
            getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, partie_.getPreneur());
        }
        if(partie_.isCallingState()) {
            if (partie_.getRegles().getDiscardAfterCall()) {
                byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
                for(BidTarot b: partie_.getBids()) {
                    String pseudo_ = pseudos_.get(player_);
                    ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
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
                        MenuItemUtils.setEnabledMenu(getConsulting(),false);
                        addButtonTakeDogCardsTarot(getMessages().getVal(WindowCards.TAKE_CARDS), true);
                        afficherMainUtilisateurTarot(false);
                    } else {
                        TrickTarot ecart_=partie_.getPliEnCours();
                        MenuItemUtils.setEnabledMenu(getConsulting(),ecart_.estVide());
                        setChien(ecart_.getCartes(),true);
                        afficherMainUtilisateurTarotChien();
                        placerBoutonsAppel();
                        pack();
                    }
                } else {
                    TrickTarot ecart_=partie_.getPliEnCours();
                    MenuItemUtils.setEnabledMenu(getConsulting(),ecart_.estVide());
                    setChien(ecart_.getCartes(),true);
                    getValidateDog().setEnabled(ecart_.total()==partie_.getDistribution().derniereMain().total());
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
                    MenuItemUtils.setEnabledMenu(getConsulting(),false);
                    boolean existCard_ = false;
                    for (CardTarot c: partie_.getDistribution().derniereMain()) {
                        if (partie_.getDistribution().hand().contient(c)) {
                            existCard_ = true;
                            break;
                        }
                    }
                    if (!partie_.getPliEnCours().estVide()) {
                        MenuItemUtils.setEnabledMenu(getConsulting(),false);
                        TrickTarot ecart_=partie_.getPliEnCours();
                        setChien(ecart_.getCartes(),true);
                        //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), ecart_.total()==partie_.getDistribution().derniereMain().total());
                        getValidateDog().setEnabled(ecart_.total()==partie_.getDistribution().derniereMain().total());
                        getPanneauBoutonsJeu().add(getValidateDog());
                        if (ecart_.total()==partie_.getDistribution().derniereMain().total()) {
//                            ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                            getSlamButton().setEnabled(true);
                            getSlamButton().setVisible(true);
                            getPanneauBoutonsJeu().add(getSlamButton());
                        }
                        afficherMainUtilisateurTarotChien();
                    } else if (existCard_) {
                        tapisTarot().retirerCartes();
                        getPanneauBoutonsJeu().removeAll();
                        if (partie_.getRegles().getDiscardAfterCall()) {
                            getValidateDog().setEnabled(false);
                            getPanneauBoutonsJeu().add(getValidateDog());
                            //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
                        } else {
                            placerBoutonsAppel();
                            pack();
                        }
                        afficherMainUtilisateurTarotChien();
                    } else {
                        setChien(partie_.getDistribution().derniereMain(),false);
                        addButtonTakeDogCardsTarot(getMessages().getVal(WindowCards.TAKE_CARDS), true);
                        afficherMainUtilisateurTarot(false);
                    }
                    pack();
                    return;
                }
            } else {
                MenuItemUtils.setEnabledMenu(getConsulting(),false);
                if (partie_.unionPlis().isEmpty()) {
                    byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
                    for(BidTarot b: partie_.getBids()) {
                        String pseudo_ = pseudos_.get(player_);
                        ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
                        player_ = partie_.playerAfter(player_);
                    }
                    if(!partie_.getCarteAppelee().estVide()) {
                        String pseudo_ = pseudos_.get(partie_.getPreneur());
                        ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),RETURN_LINE));
                    }
                    afficherMainUtilisateurTarot(false);
                    addButtonSeeDogTarot(getMessages().getVal(WindowCards.SEE_DOG), true);
                    pack();
                    return;
                }
                if(!partie_.getPliEnCours().getVuParToutJoueur()) {
                    byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
                    for(BidTarot b: partie_.getBids()) {
                        String pseudo_ = pseudos_.get(player_);
                        ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
                        player_ = partie_.playerAfter(player_);
                    }
                    if(!partie_.getCarteAppelee().estVide()) {
                        String pseudo_ = pseudos_.get(partie_.getPreneur());
                        ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),RETURN_LINE));
                    }
                    setChien(partie_.getDistribution().derniereMain(),false);
                    afficherMainUtilisateurTarot(false);
                    addButtonNextTrickTarot(getMessages().getVal(WindowCards.GO_CARD_GAME), true);
                    pack();
                    return;
                }
            }
        } else {
            MenuItemUtils.setEnabledMenu(getConsulting(),false);
            if (partie_.unionPlis().isEmpty()) {
                afficherMainUtilisateurTarot(false);
                if (partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR) {
                    if (partie_.getContrat()!=BidTarot.SLAM) {
//                        ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                        MenuItemUtils.setEnabledMenu(getConsulting(),true);
                        getSlamButton().setEnabled(true);
                        getSlamButton().setVisible(true);
                        getPanneauBoutonsJeu().add(getSlamButton());
                    }
                    addButtonNextTrickTarot(getMessages().getVal(WindowCards.GO_CARD_GAME), true);
                } else {
                    addButtonNextTrickTarot(getMessages().getVal(WindowCards.GO_CARD_GAME), true);
                }
                pack();
                return;
            }
            if (!partie_.getPliEnCours().getVuParToutJoueur()) {
                afficherMainUtilisateurTarot(false);
                if (partie_.getPreneur() == DealTarot.NUMERO_UTILISATEUR) {
                    if (partie_.getContrat()!=BidTarot.SLAM) {
//                        ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                        MenuItemUtils.setEnabledMenu(getConsulting(),true);
                        getSlamButton().setEnabled(true);
                        getSlamButton().setVisible(true);
                        getPanneauBoutonsJeu().add(getSlamButton());
                    }
                    addButtonNextTrickTarot(getMessages().getVal(WindowCards.GO_CARD_GAME), true);
                } else {
                    addButtonNextTrickTarot(getMessages().getVal(WindowCards.GO_CARD_GAME), true);
                }
                pack();
                return;
            }
        }
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        afficherMainUtilisateurTarot(false);
        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            HandTarot atouts_=partie_.getTricks().first().getCartes().couleur(Suit.TRUMP);
            if(!atouts_.estVide()) {
                for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,atouts_.getCards())) {
                    getPanelDiscardedTrumps().add(c.getPaintableLabel());
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
//                AbsMetaLabelCard.repaintChildren(getPanelDiscardedTrumps(),getWindow().getImageFactory());
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
            tapisTarot().setCarteTarot(getWindow().getImageFactory(),lg_,p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_), getWindow().getImages());
        }
        if (!partie_.keepPlayingCurrentGame()) {
            finPartieTarot();
            pack();
            return;
        }
        thread(new AnimationCardTarot(this));
    }

    public void ajouterBoutonContratTarot(String _texte,BidTarot _action,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratTarot(_action));
        bouton_.addActionListener(new ListenerBidTarotSingle(this,_action));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    private void initSlamButtonTarot() {
        String lg_ = getOwner().getLanguageKey();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(Games.toString(BidTarot.SLAM,lg_));
        bouton_.addActionListener(new SlamEvent(this));
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
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new SeeDogEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonTakeDogCardsTarot(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new TakeDogEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    private void initButtonValidateDogTarot() {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowCards.GO_CARD_GAME));
        bouton_.addActionListener(new ValidateDogEvent(this));
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
                for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,atouts_.getCards())) {
                    getPanelDiscardedTrumps().add(c.getPaintableLabel());
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
                //pack();
            }
        }
        tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain(), getOwner().getCompoFactory());
        tapisTarot().setCartesTarotJeu(getWindow().getImageFactory(),lg_,partie_.getNombreDeJoueurs());
        getScrollCallableCards().setVisible(false);
        debutPliTarot();
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
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new EndDealEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonNextTrickTarot(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new NextTrickEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonKeepPlayingDealTarot(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new KeepPlayingRandomEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonKeepPlayingEditedDealTarot(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new KeepPlayingEditedEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonStopPlayingTarot(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new StopPlayingEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonReplayDealTarot(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new ReplayEvent(this));
        _panneau.add(bouton_);
    }
    private void setChien(HandTarot _main,boolean _ecouteur) {
        setCanDiscard(_ecouteur);
        updateCardsInPanelTarotDog(tapisTarot().getCenterDeck(), _main, false);
    }
    public void placerBoutonsAvantJeuUtilisateurTarot() {
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        partieTarot().changerConfiance();
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        afficherMainUtilisateurTarot(true);
        setRaisonCourante(EMPTY);
        setChoosenHandful(Handfuls.NO);
        setSelectedMiseres(new IdMap<Miseres,BoolVal>());
        String lg_ = getOwner().getLanguageKey();
        GameTarot partie_=partieTarot();
        if(partie_.premierTourNoMisere()) {
            setCanExcludeTrumps(true);
            IdList<Handfuls> poignees_ = partie_.getAnnoncesPoigneesPossibles(DealTarot.NUMERO_UTILISATEUR);
            RulesTarot regles_=partie_.getRegles();
            HandTarot trumps_ = GameTarotCommonPlaying.atoutsPoignee(partie_.getDistribution().hand().couleurs());
            displayTrumpsForHandful(trumps_);
            AbsPanel panneau_=getPanneauBoutonsJeu();
            AbsPanel handFuls_ = getOwner().getCompoFactory().newPageBox();
            setInfoCurrentHandful(getOwner().getCompoFactory().newTextArea(EMPTY_STRING,1,15));
            AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(getInfoCurrentHandful());
            scroll_.setPreferredSize(new MetaDimension(getEvents().getWidth(),70));
            handFuls_.add(scroll_);
            CustList<AbsRadioButton> list_ = new CustList<AbsRadioButton>();
            for (Handfuls h: Handfuls.getNonDeclarableHandFuls()) {
                AbsRadioButton radio_ = getOwner().getCompoFactory().newRadioButton(Games.toString(h,lg_));
                list_.add(radio_);
                radio_.addMouseListener(new ListenerNoHandfulTarot(this, radio_, h,list_));
                handFuls_.add(radio_);
            }
            for (Handfuls h: Handfuls.getDeclarableHandFuls()) {
                if (!regles_.poigneeAutorisee(h)) {
                    continue;
                }
                AbsRadioButton radio_ = getOwner().getCompoFactory().newRadioButton(Games.toString(h,lg_));
                list_.add(radio_);
                radio_.setEnabled(poignees_.containsObj(h));
                radio_.addMouseListener(new ListenerHandfulTarot(regles_.getAllowedHandfuls().getVal(h), radio_, this, h,list_));
                handFuls_.add(radio_);
            }
            panneau_.add(handFuls_);
            AbsPanel miseresPanel_ = getOwner().getCompoFactory().newGrid(0,1);
            for(Miseres po_:regles_.getMiseres()) {
                AbsCustCheckBox check_ = getOwner().getCompoFactory().newCustCheckBox(Games.toString(po_,lg_));
                //check_.addChangeListener(new ListenerMiseres(check_,po_));
                check_.addActionListener(new ListenerMiseresTarot(this,check_,po_));
                getSelectedMiseres().put(po_, BoolVal.FALSE);
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
            addButtonEndDealTarot(getMessages().getVal(WindowCards.END_DEAL), true);
        } else {
            addButtonNextTrickTarot(getMessages().getVal(WindowCards.NEXT_TRICK), true);
        }
    }

    public void placerBoutonsAppel() {
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        GameTarot partie_=partieTarot();
        setCanCall(true);
        getPanneauBoutonsJeu().removeAll();
        updateCardsInPanelTarotCallBeforeDog(getPanelCallableCards(),partie_.callableCards());
        getScrollCallableCards().setVisible(true);
    }
    public void debutPliTarot() {
        //Activer le sous-menu conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        //Activer le sous-menu aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
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
        setRaisonCourante(getMessages().getVal(WindowCards.WAIT_TURN));
        setThreadAnime(true);
        thread(new AnimationCardTarot(this));
    }
    @Override
    public void annonceTarotChelem() {
        GameTarot partie_=partieTarot();
        getScrollCallableCards().setVisible(false);
        String lg_ = getOwner().getLanguageKey();
        if(!partie_.chelemAnnonce()) {
            int choix_=getOwner().getConfirmDialogAns().input(getOwner().getCommonFrame(),getMessages().getVal(WindowCards.ASK_SLAM),getMessages().getVal(WindowCards.ASK_SLAM_TITLE), lg_,GuiConstants.YES_NO_OPTION);
            if(choix_!=GuiConstants.YES_OPTION) {
                getScrollCallableCards().setVisible(true);
                return;
            }
            partie_.ajouterChelemUtilisateur();
            getPanneauBoutonsJeu().removeAll();
            ajouterTexteDansZone(StringUtil.concat(StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.DECLARING_SLAM), pseudo()),RETURN_LINE));
            setCanDiscard(false);
            if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                partie_.addCurTrick();
                HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
                if(!atouts_.estVide()) {
                    for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,atouts_.getCards())) {
                        getPanelDiscardedTrumps().add(c.getPaintableLabel());
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
//                    AbsMetaLabelCard.repaintChildren(getPanelDiscardedTrumps(),getWindow().getImageFactory());
                    //pack();
                }
            }
            getPanneauBoutonsJeu().validate();
            tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain(), getOwner().getCompoFactory());
            tapisTarot().setCartesTarotJeu(getWindow().getImageFactory(),lg_,partie_.getNombreDeJoueurs());
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
                ajouterTexteDansZone(StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE));
            }
            for(Miseres annonce_:partie_.getAnnoncesMiseres(_joueur)) {
                ajouterTexteDansZone(StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE));
            }
            if(!poignee_.estVide()) {
                getHandfuls().getVal(_joueur).setText(Games.toString(partie_.getAnnoncesPoignees(_joueur).first(),lg_));
            }
            poignee_.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
            AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
            panelToSet_.removeAll();
            for(CardTarot c: poignee_) {
                MiniTarotCard carte_=new MiniTarotCard(lg_, c, getOwner().getCompoFactory());
                panelToSet_.add(carte_.getPaintableLabel());
            }
            pack();
        }
        if(partie_.getCarteAppelee().contient(_ct)) {
            getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, _joueur);
            ajouterTexteDansZone(StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(Role.CALLED_PLAYER,lg_)));
        }

    }
    public void jouerTarot(byte _joueur, String _pseudo) {
        GameTarot partie_=partieTarot();
        partie_.changerConfianceJeuCarteUnique();
        CardTarot ct_= partie_.getCarteJoueee();
        String lg_ = getOwner().getLanguageKey();
        if(partie_.premierTourNoMisere()) {
            IdList<Handfuls> annoncesPoignees_ = partie_.getAnnoncesPoignees(_joueur);
            IdList<Miseres> annoncesMiseres_ = partie_.getAnnoncesMiseres(_joueur);
            HandTarot poignee_=partie_.getPoignee(_joueur);
            for(Handfuls annonce_:annoncesPoignees_) {
                ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE)), getOwner().getFrames());
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
            }
            for(Miseres annonce_:annoncesMiseres_) {
                ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE)), getOwner().getFrames());
//                    ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);
            }
            if(!poignee_.estVide()) {
                AbsPlainLabel label_ = getHandfuls().getVal(_joueur);
                ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new SettingText(label_, Games.toString(annoncesPoignees_.first(),lg_)), getOwner().getFrames());
//                    getHandfuls().getVal(_joueur).setText(annoncesPoignees_.first().toString());
            }
            poignee_.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
            AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new HandfulThread(poignee_, panelToSet_, getWindow()), getOwner().getFrames());
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
            getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, _joueur);
            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(Role.CALLED_PLAYER,lg_))), getOwner().getFrames());
//            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+Status.CALLED_PLAYER.toString());
        }
        partie_.ajouterUneCarteDansPliEnCours(_joueur,ct_);
        tapisTarot().setCarteTarot(getWindow().getImageFactory(),lg_,_joueur,ct_, getWindow().getImages());
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
        MenuItemUtils.setEnabledMenu(getPause(),true);
        //Desactiver le sous-menu conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        GameTarot partie_=partieTarot();
        String lg_ = getOwner().getLanguageKey();
        if(partie_.getCarteAppelee().contient(_carteJouee)) {
            getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, DealTarot.NUMERO_UTILISATEUR);
            ajouterTexteDansZone(StringUtil.concat(pseudo(),INTRODUCTION_PTS,Games.toString(Role.CALLED_PLAYER,lg_)));
        }
        /*L'utilisateur joue sa carte*/
        partie_.ajouterUneCarteDansPliEnCours(DealTarot.NUMERO_UTILISATEUR,_carteJouee);
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
        setRaisonCourante(getMessages().getVal(WindowCards.END_TRICK));
        afficherMainUtilisateurTarot(false);
        tapisTarot().setCarteTarot(getWindow().getImageFactory(),lg_,DealTarot.NUMERO_UTILISATEUR,_carteJouee, getWindow().getImages());
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        thread(new AnimationCardTarot(this));
        setThreadAnime(true);

    }
    public void finPartieTarot() {
        /*Descativer aide au jeu*/
        String lg_ = getOwner().getLanguageKey();
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        AbsScrollPane ascenseur_;

        if(isChangerPileFin()) {
            GameTarot partie_=partieTarot();
            StreamTextFile.saveTextFile(StringUtil.concat(
                    LaunchingCards.getTempFolderSl(getOwner().getFrames()),FileConst.DECK_FOLDER,
                    StreamTextFile.SEPARATEUR,GameEnum.TAROT.name(),FileConst.DECK_EXT),
                    DocumentWriterTarotUtil.setHandTarot(partie_.empiler()), getWindow().getStreams());
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        StringList pseudos_=new StringList(pseudosTarot());
        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();

        GameTarot partie_=partieTarot();
        if(partie_.getType()==GameType.RANDOM) {
            setPartieAleatoireJouee(true);
            if(isChangerPileFin()) {
                changerNombreDeParties(GameEnum.TAROT, partie_.getDistribution().getNbDeals(), getOwner().getFrames());
            }
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();

        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(partie_);
        res_.getRes().setUser(DealTarot.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(pseudos_), getScores());
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        setScores(res_.getRes().getScores());
        res_.getRes().setGeneral(readCoreResource());
        res_.getRes().setSpecific(readResource());

        AbsScrollPane scroll_=getOwner().getCompoFactory().newAbsScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_, getOwner().getFrames());
        PreparedAnalyzed sOne_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT);
        ((TarotStandards)sOne_.getBeanNatLgNames()).setDataBase(res_);
        editor_.initialize(sOne_);
        scroll_.setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowCards.RESULTS_PAGE),scroll_);
        ascenseur_=getOwner().getCompoFactory().newAbsScrollPane();
        editor_ = new RenderedPage(ascenseur_, getOwner().getFrames());
        PreparedAnalyzed sTwo_ = retrieve(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT);
        ((TarotStandards)sTwo_.getBeanNatLgNames()).setDataBase(res_);
        editor_.initialize(sTwo_);
        ascenseur_.setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowCards.DETAIL_RESULTS_PAGE),ascenseur_);
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
            Graphic graphique_=new Graphic(getScores(),new Longs(res_.getRes().getSums()),new CustList<Rate>(res_.getRes().getSigmas()),couleurs_, getOwner().getCompoFactory());
            Rate derniereMoyenne_=new Rate(res_.getRes().getSums().last(),nombreJoueurs_);
            CustList<Rate> scoresCentresMoyenne_=new CustList<Rate>();
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                scoresCentresMoyenne_.add(Rate.minus(new Rate(getScores().last().get(joueur_)), derniereMoyenne_));
            }
            scoresCentresMoyenne_.add(Rate.multiply(new Rate(3), res_.getRes().getSigmas().last()));
            Rate max_ = Rate.zero();
            for(Rate maximum_:scoresCentresMoyenne_) {
                if (Rate.strGreater(maximum_.absNb(), max_)) {
                    max_ = maximum_.absNb();
                }
            }
            setMaxAbsoluScore(NumberUtil.max(max_.ll(),getMaxAbsoluScore()));
            int dimy_=(int)getMaxAbsoluScore();
            graphique_.setPreferredSize(new MetaDimension(2000,dimy_));
            AbsScrollPane locScroll_=getOwner().getCompoFactory().newAbsScrollPane(graphique_.getPaintableLabel());
            graphique_.setLocation(0,(600-dimy_)/2);
            locScroll_.setPreferredSize(new MetaDimension(300,200));
            AbsPanel panneau_=getOwner().getCompoFactory().newBorder();
            panneau_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowCards.SCORES_EVOLUTION_DETAIL)),GuiConstants.BORDER_LAYOUT_NORTH);
            panneau_.add(locScroll_,GuiConstants.BORDER_LAYOUT_CENTER);
            GraphicKey legende_=new GraphicKey(pseudos_,couleurs_, lg_, getOwner().getCompoFactory());
            legende_.setPreferredSize(new MetaDimension(300,15*(nombreJoueurs_+1)));
            locScroll_=getOwner().getCompoFactory().newAbsScrollPane(legende_.getPaintableLabel());
            locScroll_.setPreferredSize(new MetaDimension(300,100));
            panneau_.add(locScroll_,GuiConstants.BORDER_LAYOUT_SOUTH);
            onglets_.add(getMessages().getVal(WindowCards.SCORES_EVOLUTION),panneau_);
        }
        GameTarot game_=partieTarot();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        tricksHands_.sortHands(getDisplayingTarot(), game_.getNombreDeJoueurs());
        WindowCards ow_ = getOwner();
        AbsScrollPane panelCards_ = getOwner().getCompoFactory().newAbsScrollPane(new PanelTricksHandsTarot(ow_.getCommonFrame(),tricksHands_,
                nombreJoueurs_,
                pseudosTarot(),
                getDisplayingTarot(),ow_).getContainer());
        panelCards_.setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowCards.HANDS_TRICKS),panelCards_);
        container_.add(onglets_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=getOwner().getCompoFactory().newPageBox();
        AbsPanel buttons_ = getOwner().getCompoFactory().newLineBox();
        GameType type_;
        long nombreParties_;
        type_=partie_.getType();
        nombreParties_=partie_.getNumber();
        int nombreTotalParties_= partie_.getRegles().getCommon().getNbDeals();
        if(type_==GameType.EDIT&&nombreParties_<nombreTotalParties_) {
            addButtonKeepPlayingEditedDealTarot(buttons_, getMessages().getVal(WindowCards.KEEP_PLAYING_EDITED_DEAL));
        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&isPartieAleatoireJouee()||type_==GameType.RANDOM) {
            addButtonKeepPlayingDealTarot(buttons_, getMessages().getVal(WindowCards.KEEP_PLAYING_DEAL));
        }
        addButtonReplayDealTarot(buttons_, getMessages().getVal(WindowCards.REPLAY_DEAL));
        addButtonStopPlayingTarot(buttons_, getMessages().getVal(WindowCards.STOP));
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
    public void modify() {
        MenuItemUtils.setEnabledMenu(getSave(),true);
        MenuItemUtils.setEnabledMenu(getChange(),true);
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        HandTarot pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        pile_ = chargerPileTarot(getOwner().getFrames());
        if (!pile_.validStack()) {
            pile_ = HandTarot.pileBase();
        }
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.TAROT, getOwner().getFrames());
        DealTarot donne_;
        if(nb_==0||!getPar().enCoursDePartie()) {
            setChangerPileFin(true);
            donne_=new DealTarot(nb_,pile_);
            donne_.setRandomDealer(getReglesTarot(),getOwner().getGenerator());
            donne_.initDonne(getReglesTarot(),getOwner().getGenerator());
            getPar().jouerTarot(new GameTarot(GameType.RANDOM,donne_,getReglesTarot()));
        } else {
            GameTarot partie_=partieTarot();
            donne_=new DealTarot(nb_,partie_.empiler());
            donne_.donneurSuivant(partie_.getDistribution().getDealer(),partie_.getRegles());
            donne_.initDonne(partie_.getRegles(),getOwner().getGenerator());
            getPar().jouerTarot(new GameTarot(GameType.RANDOM,donne_,partie_.getRegles()));
        }
        mettreEnPlaceIhmTarot();
    }
    public void editerTarot(GameTarot _partie) {
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        setaJoueCarte(false);
        setPartieSauvegardee(false);
        getPar().jouerTarot(_partie);
        //Desactiver le menu Partie/Demo
        MenuItemUtils.setEnabledMenu(getDemo(),false);
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
                thread(new AnimationBidTarot(this));
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
        AbsPanel boutons_=getPanneauBoutonsJeu();
        boutons_.removeAll();
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            addButtonTakeDogCardsTarot(getMessages().getVal(WindowCards.TAKE_CARDS), true);
        } else {
            if (!partie_.getRegles().getDiscardAfterCall()) {
                partie_.appelApresEcart();
                if(!partie_.getCarteAppelee().estVide()) {
                    ajouterTexteDansZone(StringUtil.concat(pseudosTarot().get(partie_.getPreneur()),INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),RETURN_LINE));
                }
            } else {
                partie_.ecarter(true);
            }
            HandTarot atouts_=partie_.getPliEnCours().getCartes().couleur(Suit.TRUMP);
            getPanelDiscardedTrumps().removeAll();
            if(!atouts_.estVide()) {
                for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,atouts_.getCards())) {
                    getPanelDiscardedTrumps().add(c.getPaintableLabel());
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
            }
            addButtonNextTrickTarot(getMessages().getVal(WindowCards.GO_CARD_GAME), true);
        }
        //boutons_.validate();
        pack();
    }
    @Override
    public void prendreCartesChien() {
        GameTarot partie_=partieTarot();
        partie_.ajouterCartesUtilisateur();
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        tapisTarot().retirerCartes();
        afficherMainUtilisateurTarotChien();
        getPanneauBoutonsJeu().removeAll();
        if (partie_.getRegles().getDiscardAfterCall()) {
            getValidateDog().setEnabled(false);
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
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        GameTarot partie_=partieTarot();
        partie_.ajouterUneCarteDansPliEnCours(DealTarot.NUMERO_UTILISATEUR,_ct);
        afficherMainUtilisateurTarotChien();
        setChien(partie_.getPliEnCours().getCartes(),true);
        if (partie_.getRegles().getDiscardAfterCall()) {
//            JPanel boutons_=getPanneauBoutonsJeu();
//            LabelButton valide_=(LabelButton)boutons_.getComponent(0);
            boolean chienFait_ = partie_.getPliEnCours().total()==partie_.getDistribution().derniereMain().total();
            getValidateDog().setEnabled(chienFait_);
            if(chienFait_) {
                //ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                getSlamButton().setEnabled(true);
                getSlamButton().setVisible(true);
            }
            //boutons_.validate();
        }
        pack();
    }
    public void retirerUneCarteDuChien(CardTarot _ct) {
        GameTarot partie_=partieTarot();
        partie_.retirerUneCarteDuChien(_ct);
        MenuItemUtils.setEnabledMenu(getConsulting(),partie_.getPliEnCours().estVide());
        partie_.ajouterUtilisateur(_ct);
        afficherMainUtilisateurTarotChien();
        setChien(partie_.getPliEnCours().getCartes(),true);
        if (partie_.getRegles().getDiscardAfterCall()) {
//            JPanel boutons_=getPanneauBoutonsJeu();
//            LabelButton valide_=(LabelButton)boutons_.getComponent(0);
            getValidateDog().setEnabled(partie_.getPliEnCours().total()==partie_.getDistribution().derniereMain().total());
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
        getCurrentIncludedTrumps().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        getCurrentExcludedTrumps().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        updateCardsInPanelTarotHandful(getIncludedTrumpsForHandful(), getCurrentIncludedTrumps(), true);
        updateCardsInPanelTarotHandful(getExcludedTrumpsForHandful(), getCurrentExcludedTrumps(), false);
        //pack();
        getDeclaringHandful().setDividerLocation(getDeclaringHandful().getWidth()*9/10);
    }
    private void updateCardsInPanelTarotDog(AbsPanel _panel, HandTarot _hand, boolean _inHand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            c.addMouseListener(new ListenerCardTarotSingleDog(this,c.getCard(),_inHand,c));
            _panel.add(c.getPaintableLabel());
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
    }
    private void updateCardsInPanelTarotCallBeforeDog(AbsPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            c.addMouseListener(new ListenerCardTarotSingleBeforeDog(this,c.getCard()));
            _panel.add(c.getPaintableLabel());
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
    }
    private void updateCardsInPanelTarotHandful(AbsPanel _panel, HandTarot _hand, boolean _included) {
        _panel.removeAll();
        _panel.validate();
        String lg_ = getOwner().getLanguageKey();
        for(CardTarot c: _hand) {
            MiniTarotCard carte_=new MiniTarotCard(lg_, c, getOwner().getCompoFactory());
//            carte_.addMouseListener(new EcouteurCarteTarotHandful(_hand.carte(indice_),_included));
            carte_.addMouseListener(new ListenerCardTarotSingleHandful(this, c,_included));
            _panel.add(carte_.getPaintableLabel());
        }
    }
    private void updateCardsInPanelTarotJeu(AbsPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            c.addMouseListener(new ListenerCardTarotSingleGame(this, c.getCard()));
            _panel.add(c.getPaintableLabel());
        }
        _panel.validate();
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
            String message_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_TAROT_BID), Games.toString(partie_.strategieContrat(),lg_));
            getOwner().getFrames().getMessageDialogAbs().input(getWindow().getCommonFrame(),message_,getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else if(partie_.isCallingState()) {
            if (!partie_.getRegles().getDiscardAfterCall()) {
                if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                    CallDiscard cartesAppeler_ = partie_.strategieAppelApresEcart(true);
                    String message_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_TAROT_CALL),
                            Games.toString(cartesAppeler_.getCarteAppelee().premiereCarte(),lg_),RETURN_LINE);
                    message_ = StringUtil.concat(message_, StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_TAROT_DISCARD), Games.toString(cartesAppeler_.getEcartAFaire(),lg_)));
                    getOwner().getFrames().getMessageDialogAbs().input(getWindow().getCommonFrame(),message_,getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
                    //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
                } else {
                    HandTarot cartesAppeler_ = partie_.strategieAppel();
                    if(cartesAppeler_.estVide()) {
                        return;
                    }
                    String message_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_TAROT_CALL), Games.toString(cartesAppeler_.premiereCarte(),lg_));
                    getOwner().getFrames().getMessageDialogAbs().input(getWindow().getCommonFrame(),message_,getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
                    //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                HandTarot cartesAppeler_ = partie_.strategieAppel();
                if(cartesAppeler_.estVide()) {
                    return;
                }
                String message_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_TAROT_CALL), Games.toString(cartesAppeler_.premiereCarte(),lg_));
                getOwner().getFrames().getMessageDialogAbs().input(getWindow().getCommonFrame(),message_,getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(partie_.getPliEnCours().estVide() && !partie_.getPliEnCours().getVuParToutJoueur()) {
            String message_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_TAROT_DISCARD), Games.toString(partie_.strategieEcart(),lg_));
            getOwner().getFrames().getMessageDialogAbs().input(getWindow().getCommonFrame(),message_,getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else if(partie_.getContrat()!=BidTarot.SLAM && (partie_.unionPlis().isEmpty() || !partie_.getPliEnCours().getVuParToutJoueur())) {
            partie_.annoncerUnChelem(DealTarot.NUMERO_UTILISATEUR);
            getOwner().getFrames().getMessageDialogAbs().input(getWindow().getCommonFrame(),EMPTY_STRING,getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),partie_.getRaison(),getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        } else {
            String message_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_TAROT_PLAYER), Games.toString(partie_.strategieJeuCarteUnique(),lg_));
            getOwner().getFrames().getMessageDialogAbs().input(getWindow().getCommonFrame(),message_,getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(getWindow(),message_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void showTeams() {
        GameTarot game_ = partieTarot();
        TeamsPlayers teams_ = new TeamsPlayers();
        teams_.setTeams(game_.getTeamsRelation().teams());
        DialogTeamsPlayers.initDialogTeamsPlayers(getOwner());
        DialogTeamsPlayers.setDialogTeamsPlayers(pseudosTarot(), teams_, getOwner().getDialogTeamsPlayers(), getOwner().getCompoFactory());
    }
    @Override
    public void showTricksHands() {
        GameTarot game_=partieTarot();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        WindowCards ow_ = getOwner();
        DialogTricksTarot.setDialogTricksTarot(getMessages().getVal(WindowCards.HANDS_TRICKS_TAROT), ow_);
        DialogTricksTarot.init(tricksHands_, game_.getNombreDeJoueurs(), pseudosTarot(), getDisplayingTarot(),ow_);
    }
    @Override
    public void aideAuJeu() {
        String lg_ = getOwner().getLanguageKey();
        GameTarot partie_=partieTarot();
        HandTarot mainUtilisateur_=partie_.getDistribution().hand();
        GameTarotTrickInfo doneTrickInfo_ = partie_.getDoneTrickInfo();
        HandTarot cartesJouees_=doneTrickInfo_.cartesJoueesEnCours(DealTarot.NUMERO_UTILISATEUR);
        IdMap<Suit,HandTarot> repartitionCartesJouees_=cartesJouees_.couleurs();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_= doneTrickInfo_.cartesPossibles(mainUtilisateur_);
        DialogHelpTarot.setTitleDialog(getWindow(), StringUtil.concat(getMessages().getVal(WindowCards.HELP_GAME),SPACE,GameEnum.TAROT.toString(lg_)));
        IdMap<Hypothesis,IdMap<Suit,CustList<HandTarot>>> hypotheses_ = doneTrickInfo_.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_= hypotheses_.getVal(Hypothesis.SURE);
        getOwner().getDialogHelpTarot().setDialogueTarot(cartesPossibles_,cartesCertaines_,repartitionCartesJouees_,pseudosTarot(), lg_);
    }

    public BidTarot getContratUtilisateur() {
        return contratUtilisateur;
    }

    public void setContratUtilisateur(BidTarot _contratUtilisateur) {
        contratUtilisateur = _contratUtilisateur;
    }

    @Override
    public void nextTrick() {
        GameTarot partie_ = partieTarot();
        String lg_ = getOwner().getLanguageKey();
        tapisTarot().setEcart(lg_,partie_.getDistribution().derniereMain(), getOwner().getCompoFactory());
        tapisTarot().setCartesTarotJeu(getWindow().getImageFactory(),lg_,partie_.getNombreDeJoueurs());
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
        donne_.initDonne(partie_.getRegles(),getOwner().getGenerator());
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

