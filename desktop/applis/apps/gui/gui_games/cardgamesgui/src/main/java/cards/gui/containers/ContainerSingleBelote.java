package cards.gui.containers;






import cards.belote.*;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Role;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.WindowCards;
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
import code.gui.document.PreparedAnalyzed;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.maths.Rate;
import code.scripts.imgs.cards.CardsInit;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class ContainerSingleBelote extends ContainerBelote implements ContainerSingle,ContainerPlayableBelote {

    private BidBeloteSuit contratUtilisateurBelote = new BidBeloteSuit();
    private boolean annonceBelote;
    private boolean annonceBeloteRebelote;

    private boolean clickedBid;
    private boolean clickedPass;

    public ContainerSingleBelote(WindowCards _window) {
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
            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(DeclaresBeloteRebelote.BELOTE_REBELOTE,lg_),RETURN_LINE)), getOwner().getFrames());
//            ajouterTexteDansZone(_pseudo+INTRODUCTION_PTS+DeclaresBeloteRebelote.BELOTE_REBELOTE+RETURN_LINE_CHAR);
        }
        if (partie_.premierTour()) {
            partie_.annoncer(_joueur);
            DeclareHandBelote usDecl_ = partie_.getAnnonce(_joueur);
            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new AddTextEvents(this, StringUtil.concat(_pseudo,INTRODUCTION_PTS,Games.toString(usDecl_.getDeclare(),lg_),RETURN_LINE)), getOwner().getFrames());
//            ajouterTexteDansZone(pseudo()+INTRODUCTION_PTS+usDecl_.getAnnonce()+RETURN_LINE_CHAR);
            if(!usDecl_.getHand().estVide()) {
                AbsPlainLabel label_ = getHandfuls().getVal(_joueur);
                ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new SettingText(label_, Games.toString(usDecl_.getDeclare(),lg_)), getOwner().getFrames());
//                getHandfuls().getVal(_joueur).setText(usDecl_.getAnnonce().toString());
            }
            if (partie_.getContrat().getCouleurDominante()) {
                usDecl_.getHand().trier(getDisplayingBelote().getSuits(), getDisplayingBelote().isDecreasing(), partie_.getContrat().getCouleur());
            } else {
                usDecl_.getHand().trier(getDisplayingBelote().getSuits(), getDisplayingBelote().isDecreasing(), partie_.getContrat().getOrdre());
            }

            AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(_joueur);
            ThreadInvoker.invokeNow(getOwner().getThreadFactory(),new DeclaringThread(panelToSet_, usDecl_, getOwner()), getOwner().getFrames());
//            panelToSet_.removeAll();
//            for(CardBelote c: usDecl_.getMain())
//            {
//                MiniBeloteCard carte_=new MiniBeloteCard(c);
//                panelToSet_.add(carte_);
//            }
        }
        partie_.jouer(_joueur,ct_);
        partie_.ajouterUneCarteDansPliEnCours(ct_);
        tapisBelote().setCarteBelote(getWindow().getImageFactory(),lg_,_joueur,ct_, getWindow().getImages());
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
        GameBelote partie_=partieBelote();
        nombreDeJoueurs_=partie_.getNombreDeJoueurs();
        String lg_ = getOwner().getLanguageKey();
        getOwner().setTitle(GameEnum.BELOTE.toString(lg_));
        placerBelote();
        pack();
        StringList pseudos_=pseudosBelote();
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        if(partie_.keepBidding()) {
            BidBeloteSuit contrat_=partie_.getContrat();
            //Desactiver les conseils
            MenuItemUtils.setEnabledMenu(getConsulting(),false);
            afficherMainUtilisateurBelote(false);
            byte player_ = partie_.playerAfter(partie_.getDistribution().getDealer());
            for(BidBeloteSuit b: partie_.tousContrats()) {
                String pseudo_ = pseudos_.get(player_);
                ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(b,lg_),RETURN_LINE));
                player_ = partie_.playerAfter(player_);
            }
            byte debut_= partie_.playerHavingToBid();
            if(debut_ != DealBelote.NUMERO_UTILISATEUR) {
                thread(new AnimationBidBelote(this));
            } else {
                if(partie_.keepBidding()) {
                    //Activer les conseils
                    MenuItemUtils.setEnabledMenu(getConsulting(),true);
                    setCanBid(true);
                    if (!partie_.getRegles().dealAll()) {
                        for(BidBeloteSuit e:partie_.getGameBeloteBid().allowedBids()) {
                            ajouterBoutonContratBelote(Games.toString(e,lg_),e,e.estDemandable(contrat_));
                        }
                    } else {
                        addButtonsForCoinche(partie_);
                    }
                } else if(partie_.getContrat().jouerDonne()) {
                    getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, partie_.getPreneur());
                    getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, partie_.getTeamsRelation().partenaires(partie_.getPreneur()).first());
                    addButtonNextTrickBelote(getMessages().getVal(WindowCards.GO_CARD_GAME), true);
                } else {
                    addButtonEndDealBelote(getMessages().getVal(WindowCards.END_DEAL), true);
                }
                pack();
            }
            return;
        }
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        if (partie_.getTricks().isEmpty() && partie_.getPliEnCours().estVide()) {
            afficherMainUtilisateurBelote(false);
            if (!partie_.getRegles().dealAll() && partie_.getDistribution().hand().total() == partie_.getRegles().getRepartition().getNombreCartesParJoueur()) {
                TrickBelote pliEnCours_=partie_.getPliEnCours();
                for(byte p:pliEnCours_.playersHavingPlayed(nombreDeJoueurs_)) {
                    tapisBelote().setCarteBelote(getWindow().getImageFactory(),lg_,p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_), getWindow().getImages());
                }
                if (!partie_.keepPlayingCurrentGame()) {
                    finPartieBelote();
                    pack();
                    return;
                }
                thread(new AnimationCardBelote(this));
            } else if(partie_.getContrat().jouerDonne()) {
                getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, partie_.getPreneur());
                getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, partie_.getTeamsRelation().partenaires(partie_.getPreneur()).first());
                addButtonNextTrickBelote(getMessages().getVal(WindowCards.GO_CARD_GAME), true);
                pack();
            } else {
                addButtonEndDealBelote(getMessages().getVal(WindowCards.END_DEAL), true);
                pack();
            }
            return;
        }
        TrickBelote pliEnCours_=partie_.getPliEnCours();
        for(byte p:pliEnCours_.playersHavingPlayed(nombreDeJoueurs_)) {
            tapisBelote().setCarteBelote(getWindow().getImageFactory(),lg_,p, pliEnCours_.carteDuJoueur(p, nombreDeJoueurs_), getWindow().getImages());
        }
        afficherMainUtilisateurBelote(false);
        if (!partie_.keepPlayingCurrentGame()) {
            finPartieBelote();
            pack();
            return;
        }
        thread(new AnimationCardBelote(this));
    }
    public void addButtonsForCoinche(GameBelote _partie) {
        int square_ = 1;
        Ints points_ = RulesBelote.getPoints();
        int size_ = points_.size();
        while (square_ * square_ < size_) {
            square_++;
        }
        setPanneauBoutonsJeuPoints(getOwner().getCompoFactory().newGrid(0, square_));
        getPointsButtons().clear();
        String lg_ = getOwner().getLanguageKey();
        for (int p_: points_) {
            LabelPoints label_ = new LabelPoints(p_, getOwner().getCompoFactory());
            label_.setEnabledLabel(_partie.getContrat().getPoints() < p_);
            label_.setToolTipText(Long.toString(p_));
            label_.addMouseList(new SelectPointsEvent(this, p_));
            getPointsButtons().add(label_);
            getPanneauBoutonsJeuPoints().add(label_.getButton());
        }
        getPanneauBoutonsJeu().add(getPanneauBoutonsJeuPoints());
        clickedBid = false;
        clickedPass = false;
        setBidOk(getOwner().getCompoFactory().newPlainButton(WindowCards.OK));
        getBidOk().setEnabled(false);
        getBidOk().addActionListener(new BidEvent(this));
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        AbsPanel panelSuits_ = getOwner().getCompoFactory().newLineBox();
        getBidsButtons().clear();
        for (Suit s: Suit.couleursOrdinaires()) {
            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setCouleur(s);
            bid_.setEnchere(BidBelote.SUIT);
            suitLabel_.setSuit(bid_, lg_);
            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
            panelSuits_.add(suitLabel_);
            getBidsButtons().add(suitLabel_);
        }
        panel_.add(panelSuits_);
        AbsPanel panelBids_ = getOwner().getCompoFactory().newLineBox();
        for (BidBelote b: BidBelote.getNonZeroBids()) {
            if (b.getCouleurDominante()) {
                continue;
            }
            if (!_partie.getRegles().getEncheresAutorisees().getVal(b)) {
                continue;
            }
            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setEnchere(b);
            suitLabel_.setSuit(bid_, lg_);
            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));

            panelBids_.add(suitLabel_);
            getBidsButtons().add(suitLabel_);
        }
        panel_.add(panelBids_);
        AbsPanel panelOk_ = getOwner().getCompoFactory().newLineBox();
        AbsPlainButton buttonSuit_ = getOwner().getCompoFactory().newPlainButton(Games.toString(BidBelote.FOLD,lg_));
        //clickedTwo = false;
        buttonSuit_.addActionListener(new FoldEvent(this));
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
        thread(new AnimationBidBelote(this));
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
        thread(new AnimationBidBelote(this));
    }
    public void ajouterBoutonContratBelote(String _texte,BidBeloteSuit _action,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratBelote(_action));
        bouton_.addActionListener(new ListenerBidBeloteSingle(this,_action));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonNextTrickBelote(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new NextTrickEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    public void addButtonEndDealBelote(String _texte,boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new EndDealEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }
    private void addButtonKeepPlayingDealBelote(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new KeepPlayingRandomEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonKeepPlayingEditedDealBelote(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new KeepPlayingEditedEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonStopPlayingBelote(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new StopPlayingEvent(this));
        _panneau.add(bouton_);
    }
    private void addButtonReplayDealBelote(AbsPanel _panneau,String _texte) {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new ReplayEvent(this));
        _panneau.add(bouton_);
    }
    public void placerBoutonsAvantJeuUtilisateurBelote(boolean _premierTour) {
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),true);
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        setRaisonCourante(EMPTY);
        GameBelote partie_=partieBelote();
        afficherMainUtilisateurBelote(true);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        String lg_ = getOwner().getLanguageKey();
        if(!partie_.cartesBeloteRebelote().estVide()) {
            annonceBeloteRebelote = false;
            AbsPanel panneau_ =getPanneauBoutonsJeu();
            AbsCustCheckBox caseCoche_ = getOwner().getCompoFactory().newCustCheckBox(Games.toString(DeclaresBeloteRebelote.BELOTE_REBELOTE,lg_));
            caseCoche_.setEnabled(partie_.autoriseBeloteRebelote());
            caseCoche_.addActionListener(new ChangeBeloteRebeloteEvent(this));
            panneau_.add(caseCoche_);
        }
        if(_premierTour) {
            DeclareHandBelote annonceMain_ = partie_.strategieAnnonces();
            if(annonceMain_.getDeclare() != DeclaresBelote.UNDEFINED) {
                annonceBelote = false;
                AbsPanel panneau_ =getPanneauBoutonsJeu();
                AbsCustCheckBox caseCoche_ = getOwner().getCompoFactory().newCustCheckBox(StringUtil.concat(Games.toString(annonceMain_.getDeclare(),lg_),INTRODUCTION_PTS,Games.toString(annonceMain_.getHand(),lg_)));
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
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
        GameBelote partie_=partieBelote();
        if(!partie_.keepPlayingCurrentGame()) {
            addButtonEndDealBelote(getMessages().getVal(WindowCards.END_DEAL), true);
        } else {
            addButtonNextTrickBelote(getMessages().getVal(WindowCards.NEXT_TRICK), true);
        }
    }

    public void editerBelote(GameBelote _partie) {
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        setPasse(false);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        setaJoueCarte(false);
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
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowCards.HELP_GO_MENU)),GuiConstants.BORDER_LAYOUT_NORTH);
        GameBelote partie_=partieBelote();
        StringList pseudos_ = pseudosBelote();
        String lg_ = getOwner().getLanguageKey();
        CarpetBelote tapis_ = CarpetBelote.initTapisBelote(lg_, partie_.getNombreDeJoueurs(), getDisplayingBelote().isClockwise(), pseudos_, 1, getOwner().getCompoFactory());
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
        setMini(MiniCarpet.newCarpet(getWindow().getImageFactory(),partie_.getNombreDeJoueurs(),getDisplayingBelote().isClockwise(),pseudos_, getOwner().getCompoFactory()));
        panneau2_.add(getMiniPanel());
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
        pack();
        GameBelote partie_=partieBelote();
        byte debut_= partie_.playerHavingToBid();
        String lg_ = getOwner().getLanguageKey();
        if(debut_ != DealBelote.NUMERO_UTILISATEUR) {
            thread(new AnimationBidBelote(this));
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
        MenuItemUtils.setEnabledMenu(getSave(),true);
        //Activer le menu Fichier/Changer de mode
        MenuItemUtils.setEnabledMenu(getChange(),true);
        //Activer les conseils
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        HandBelote pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        pile_ = chargerPileBelote(getOwner().getFrames());
        if (!pile_.validStack()) {
            pile_ = HandBelote.pileBase();
        }
        /*Chargement du nombre de parties jouees depuis le lancement du logiciel*/
        long nb_=chargerNombreDeParties(GameEnum.BELOTE, getOwner().getFrames());
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
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        //Activer le sous-menu aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),true);
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
                ajouterTexteDansZone(StringUtil.concat(pseudos_.get(partie_.getPreneur()),INTRODUCTION_PTS,Games.toString(couleurAtout_,lg_),RETURN_LINE));
            } else {
                ajouterTexteDansZone(StringUtil.concat(pseudos_.get(partie_.getPreneur()),INTRODUCTION_PTS,Games.toString(partie_.getContrat(),lg_),RETURN_LINE));
            }
            partie_.setPliEnCours();
        }
        /*On affiche la main de l'utilisateur avec des ecouteurs sur les cartes et on supprime tous les boutons de l'ihm places a droite avant d'executer un eventuel Thread*/
        AbsPanel panneau_=getPanneauBoutonsJeu();
        panneau_.removeAll();
        panneau_.validate();
        setRaisonCourante(getMessages().getVal(WindowCards.WAIT_TURN));
        setThreadAnime(true);
        thread(new AnimationCardBelote(this));


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
        MenuItemUtils.setEnabledMenu(getPause(),true);
        //Desactiver le sous-menu conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        GameBelote partie_=partieBelote();
        boolean premierTour_;
        premierTour_=partie_.premierTour();
        String lg_ = getOwner().getLanguageKey();
        if(premierTour_) {
            if(annonceBelote) {
                partie_.annoncer(DealBelote.NUMERO_UTILISATEUR);
                DeclareHandBelote usDecl_ = partie_.getAnnonce(DealBelote.NUMERO_UTILISATEUR);
                ajouterTexteDansZone(StringUtil.concat(pseudo(),INTRODUCTION_PTS,Games.toString(usDecl_.getDeclare(),lg_),RETURN_LINE));
                if(!usDecl_.getHand().estVide()) {
                    getHandfuls().getVal(DealBelote.NUMERO_UTILISATEUR).setText(Games.toString(usDecl_.getDeclare(),lg_));
                }
                AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(DealBelote.NUMERO_UTILISATEUR);
                panelToSet_.removeAll();
                for (GraphicBeloteCard c: getGraphicCards(getWindow(),lg_,usDecl_.getHand().getCards())) {
                    panelToSet_.add(c);
                }
                panelToSet_.validate();
                panelToSet_.repaintChildren(getWindow().getImageFactory());
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
            ajouterTexteDansZone(StringUtil.concat(pseudo(),INTRODUCTION_PTS,Games.toString(DeclaresBeloteRebelote.BELOTE_REBELOTE,lg_),RETURN_LINE));
        }
        //Pour ne pas a avoir a faire disparaitre un instant de temps la main de l'utilisateur
        //Il ne se rendra pas compte que la main est repeinte entierement
        setRaisonCourante(getMessages().getVal(WindowCards.END_TRICK));
        afficherMainUtilisateurBelote(false);
        tapisBelote().setCarteBelote(getWindow().getImageFactory(),lg_,DealBelote.NUMERO_UTILISATEUR,_carteJouee, getWindow().getImages());
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().repaintChildren(getWindow().getImageFactory());
        thread(new AnimationCardBelote(this));
        setThreadAnime(true);

    }

    public void finPartieBelote() {
        /*Descativer aide au jeu*/
        String lg_ = getOwner().getLanguageKey();
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        AbsScrollPane ascenseur_;
        AbsPanel panneau_;
        if(isChangerPileFin()) {
            GameBelote partie_=partieBelote();
            StreamTextFile.saveTextFile(
                    StringUtil.concat(LaunchingCards.getTempFolderSl(getOwner().getFrames()),FileConst.DECK_FOLDER,
                            StreamTextFile.SEPARATEUR,GameEnum.BELOTE.name(),FileConst.DECK_EXT),
                    DocumentWriterBeloteUtil.setHandBelote(partie_.empiler()), getWindow().getStreams());
        }
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        StringList pseudos_=new StringList(pseudosBelote());
        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
        GameBelote partie_=partieBelote();
        if(partie_.getType()==GameType.RANDOM) {
            setPartieAleatoireJouee(true);
            if(isChangerPileFin()) {
                changerNombreDeParties(GameEnum.BELOTE, partie_.getDistribution().getNbDeals(), getOwner().getFrames());
            }
        }
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(partie_);
        res_.setUser(DealBelote.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(pseudos_), getScores());
        res_.getRes().setGeneral(readCoreResource());
        res_.getRes().setSpecific(readResource());
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        setScores(res_.getScores());
        AbsScrollPane scroll_=getOwner().getCompoFactory().newAbsScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_, getOwner().getFrames());
        PreparedAnalyzed sOne_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE);
        ((BeloteStandards)sOne_.getBeanNatLgNames()).setDataBase(res_);
        editor_.initialize(sOne_);
        scroll_.setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowCards.RESULTS_PAGE),scroll_);
        if(partie_.getContrat().jouerDonne()) {
            scroll_=getOwner().getCompoFactory().newAbsScrollPane();
            editor_ = new RenderedPage(scroll_, getOwner().getFrames());
            PreparedAnalyzed sTwo_ = retrieve(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE);
            ((BeloteStandards)sTwo_.getBeanNatLgNames()).setDataBase(res_);
            editor_.initialize(sTwo_);
            scroll_.setPreferredSize(new MetaDimension(300,300));
            onglets_.add(getMessages().getVal(WindowCards.DETAIL_RESULTS_PAGE),scroll_);
        }
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
            Graphic graphique_=new Graphic(res_.getScores(),new Longs(res_.getSums()),new CustList<Rate>(),couleurs_, getOwner().getCompoFactory());
            Rate derniereMoyenne_=new Rate(res_.getSums().last(),nombreJoueurs_);
            CustList<Rate> scoresCentresMoyenne_=new CustList<Rate>();
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
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
            graphique_.setPreferredSize(new MetaDimension(2000,dimy_));
            ascenseur_=getOwner().getCompoFactory().newAbsScrollPane(graphique_);
            graphique_.setLocation(0,(600-dimy_)/2);
            ascenseur_.setPreferredSize(new MetaDimension(300,200));
            panneau_=getOwner().getCompoFactory().newBorder();
            panneau_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowCards.SCORES_EVOLUTION_DETAIL)),GuiConstants.BORDER_LAYOUT_NORTH);
            panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_CENTER);
            GraphicKey legende_=new GraphicKey(pseudos_,couleurs_, lg_, getOwner().getCompoFactory());
            legende_.setPreferredSize(new MetaDimension(300,15*(nombreJoueurs_+1)));
            ascenseur_=getOwner().getCompoFactory().newAbsScrollPane(legende_);
            ascenseur_.setPreferredSize(new MetaDimension(300,100));
            panneau_.add(ascenseur_,GuiConstants.BORDER_LAYOUT_SOUTH);
            onglets_.add(getMessages().getVal(WindowCards.SCORES_EVOLUTION),panneau_);
        }
        GameBelote game_ = partieBelote();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(getReglesBelote());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        tricksHands_.sortHands(getDisplayingBelote(), game_.getNombreDeJoueurs());
        WindowCards ow_ = getOwner();
        ascenseur_=getOwner().getCompoFactory().newAbsScrollPane(new PanelTricksHandsBelote(ow_.getCommonFrame(), tricksHands_, nombreJoueurs_, pseudosBelote(), getDisplayingBelote(),ow_).getContainer());
        ascenseur_.setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowCards.HANDS_TRICKS),ascenseur_);
        container_.add(onglets_,GuiConstants.BORDER_LAYOUT_CENTER);
        panneau_=getOwner().getCompoFactory().newPageBox();
        AbsPanel buttons_ = getOwner().getCompoFactory().newLineBox();
        GameType type_;
        long nombreParties_;
        type_=partie_.getType();
        nombreParties_=partie_.getNombre();
        int nombreTotalParties_=partie_.getRegles().getNombreParties();
        if(type_==GameType.EDIT&&nombreParties_<nombreTotalParties_) {
            addButtonKeepPlayingEditedDealBelote(buttons_, getMessages().getVal(WindowCards.KEEP_PLAYING_EDITED_DEAL));
        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&isPartieAleatoireJouee()||type_==GameType.RANDOM) {
            addButtonKeepPlayingDealBelote(buttons_, getMessages().getVal(WindowCards.KEEP_PLAYING_DEAL));
        }
        addButtonReplayDealBelote(buttons_, getMessages().getVal(WindowCards.REPLAY_DEAL));
        addButtonStopPlayingBelote(buttons_, getMessages().getVal(WindowCards.STOP));
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
    public StringList pseudosBelote() {
        return pseudosBelote(partieBelote().getNombreDeJoueurs());
    }

    private void updateCardsInPanelBelote(AbsPanel _panel, HandBelote _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicBeloteCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            c.addMouseListener(new ListenerCardBeloteSingleGame(this,c.getCard()));
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaintChildren(getWindow().getImageFactory());
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
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DialogTricksBelote.setDialogTricksBelote(getMessages().getVal(WindowCards.HANDS_TRICKS_BELOTE), getWindow());
        WindowCards ow_ = getOwner();
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
                    mesBid_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_BELOTE_BID), Games.toString(enchere_,lg_));
                } else {
                    mesBid_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CONSULT_BELOTE_BID_SUIT), Games.toString(enchereCouleur_.getCouleur(),lg_));
                }
                getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),mesBid_, getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(getOwner(),mesBid_,getMessages().getVal(MainWindow.CONSULT_TITLE),JOptionPane.INFORMATION_MESSAGE);
            } else {
                BidBeloteSuit enchereCouleur_=partie_.strategieContrat();
                String mesBid_;

                BidBelote enchere_ = enchereCouleur_.getEnchere();
                if(enchere_ == BidBelote.FOLD) {
                    mesBid_ = StringUtil.simpleStringsFormat(
                            getMessages().getVal(WindowCards.CONSULT_BELOTE_BID),
                            Games.toString(enchere_,lg_));
                } else if(!enchere_.getCouleurDominante()) {
                    mesBid_ = StringUtil.simpleStringsFormat(
                            getMessages().getVal(WindowCards.CONSULT_BELOTE_BID_POINTS),
                            Games.toString(enchere_,lg_), Long.toString(enchereCouleur_.getPoints()));
                } else {
                    mesBid_ = StringUtil.simpleStringsFormat(
                            getMessages().getVal(WindowCards.CONSULT_BELOTE_BID_SUIT_POINTS),
                            Games.toString(enchereCouleur_.getCouleur(),lg_), Long.toString(enchereCouleur_.getPoints()));
                }
                getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),mesBid_,
                        getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
            }
        } else {
            String message_ = StringUtil.simpleStringsFormat(
                    getMessages().getVal(WindowCards.CONSULT_BELOTE_PLAYER),
                    Games.toString(partie_.strategieJeuCarteUnique(),lg_));
            getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),message_,
                    getMessages().getVal(WindowCards.CONSULT_TITLE), lg_, GuiConstants.INFORMATION_MESSAGE);
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
        DialogHelpBelote.setTitleDialog(getOwner(), StringUtil.concat(getMessages().getVal(WindowCards.HELP_GAME),SPACE,GameEnum.BELOTE.toString(lg_)));
        EnumMap<Suit,CustList<HandBelote>> cartesPossibles_=info_.cartesPossibles(mainUtilisateur_);
        EnumMap<Hypothesis,EnumMap<Suit,CustList<HandBelote>>> hypotheses_ = info_.cartesCertaines(cartesPossibles_);
        EnumMap<Suit,CustList<HandBelote>> cartesCertaines_=hypotheses_.getVal(Hypothesis.SURE);
        Suit firstSuit_;
        if (partie_.getPliEnCours().estVide()) {
            firstSuit_ = couleurAtout_;
        } else {
            firstSuit_ = partie_.getPliEnCours().couleurDemandee();
        }
        getOwner().getDialogHelpBelote().setDialogueBelote(cartesPossibles_,cartesCertaines_,repartitionCartesJouees_,firstSuit_,contrat_,pseudosBelote(), lg_);
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
        String lg_ = getOwner().getLanguageKey();
        GameBelote partie_ = partieBelote();
        tapisBelote().setCartesBeloteJeu(getWindow().getImageFactory(),partie_.getNombreDeJoueurs(), lg_);
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

