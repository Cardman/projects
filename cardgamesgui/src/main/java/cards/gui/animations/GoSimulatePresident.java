package cards.gui.animations;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import code.gui.LabelButton;
import code.gui.SessionEditorPane;
import code.gui.ThreadInvoker;
import code.util.CustList;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.consts.Constants;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerPresident;
import cards.gui.containers.ContainerSimuPresident;
import cards.gui.dialogs.FileConst;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.ResultsPresident;
import cards.president.TrickPresident;
import cards.president.enumerations.Playing;

/**Thread safe class*/
public final class GoSimulatePresident extends Thread implements GoSimulate {

    private Games partieSimulee = new Games();
    private HandPresident userHand = new HandPresident();
    private ContainerSimuPresident container;
    private LabelButton stopButton;
    private int noDeal;

    /**This class thread is independant from EDT*/
    public GoSimulatePresident(Games _partieSimulee, ContainerSimuPresident _container, LabelButton _stopButton) {
        partieSimulee = _partieSimulee;
        container = _container;
        stopButton = _stopButton;
    }

    private GamePresident partiePresidentSimulee() {
        return partieSimulee.partiePresident();
    }
    /**Pseudos utilis_&eacute_;s*/
    private StringList pseudosSimuleePresident() {
//        CustList<String> pseudos_=new CustList<String>();
        GamePresident partie_=partiePresidentSimulee();
//        partie_.getNombreDeJoueurs();
//        pseudos_.add(container.pseudo());
//        pseudos_.addAll(container.getPseudosJoueurs().getPseudosPresident());
        return container.pseudosPresident(partie_.getNombreDeJoueurs());
    }

    @Override
    public void run() {
        GamePresident partie_=partiePresidentSimulee();
        GamePresident.setChargementSimulation(100);
        Constants.sleep(500);
        String event_;
        event_ = container.getMessages().getVal(MainWindow.BEGIN_DEMO)+ContainerPresident.RETURN_LINE_CHAR;
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//        container.ajouterTexteDansZone(event_);
//        container.ajouterTexteDansZone(container.getMessages().getVal(MainWindow.BEGIN_DEMO)+ContainerPresident.RETURN_LINE_CHAR);
        container.pause();
        int noDeal_ = CustList.FIRST_INDEX;
        int maxDeals_ = partie_.getDealTricks().size();
        StringList nicknames_=pseudosSimuleePresident();
        EqList<Numbers<Byte>> ranks_ = partie_.getRanksDeals();
        while (noDeal_ < maxDeals_) {
            noDeal = noDeal_;
            if(container.isArretDemo()) {
                arretDemo();
                return;
            }
            EqList<HandPresident> userHandsBef_ = partie_.getUserHands().get(noDeal_).first();
            if (partie_.allowSwitchCards() && noDeal_ > CustList.FIRST_INDEX) {
                NumberMap<Byte,HandPresident> switchedCards_ = partie_.getSwitchedCardsDeals().get(noDeal_);
                Numbers<Byte> ranksBef_ = ranks_.get(noDeal_ - 1);
                Numbers<Byte> losers_ = GamePresident.getLoosers(ranksBef_, partie_.nombresCartesEchangesMax());
                Numbers<Byte> winners_ = GamePresident.getWinners(ranksBef_, partie_.nombresCartesEchangesMax());
                HandPresident hUser_;
                userHand.supprimerCartes();
                hUser_ = partie_.mainUtilisateurTriee(userHandsBef_.first(), container.getDisplayingPresident());
                userHand.ajouterCartes(hUser_);
//                afficherMainUtilisateurSimuPresident(userHandsBef_.first());
                afficherMainUtilisateurSimuPresident();
                for (byte l: losers_) {
                    byte w_ = GamePresident.getMatchingWinner(winners_, losers_, l);
                    HandPresident h_ = switchedCards_.getVal(l);
                    event_ = nicknames_.get(l)+ContainerGame.INTRODUCTION_PTS+h_+ContainerPresident.RETURN_LINE_CHAR;
                    event_ += nicknames_.get(w_)+ContainerPresident.RETURN_LINE_CHAR;
                    ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                    container.ajouterTexteDansZone(event_);
//                    container.ajouterTexteDansZone(nicknames_.get(l)+ContainerGame.INTRODUCTION_PTS+h_+ContainerPresident.RETURN_LINE_CHAR);
//                    container.ajouterTexteDansZone(nicknames_.get(w_)+ContainerPresident.RETURN_LINE_CHAR);
                }
                event_ = ContainerPresident.EMPTY+ContainerPresident.RETURN_LINE_CHAR;
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
//                container.ajouterTexteDansZone(ContainerPresident.EMPTY+ContainerPresident.RETURN_LINE_CHAR);
                userHand.supprimerCartes();
                hUser_ = partie_.mainUtilisateurTriee(userHandsBef_.get(CustList.SECOND_INDEX), container.getDisplayingPresident());
                userHand.ajouterCartes(hUser_);
//                afficherMainUtilisateurSimuPresident(userHandsBef_.get(CustList.SECOND_INDEX));
                afficherMainUtilisateurSimuPresident();
                for (byte w: winners_) {
                    byte l_ = GamePresident.getMatchingLoser(losers_, winners_, w);
                    HandPresident h_ = switchedCards_.getVal(w);
                    event_ = nicknames_.get(w)+ContainerGame.INTRODUCTION_PTS+h_+ContainerPresident.RETURN_LINE_CHAR;
                    event_ += nicknames_.get(l_)+ContainerPresident.RETURN_LINE_CHAR;
                    ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                    container.ajouterTexteDansZone(event_);
//                    container.ajouterTexteDansZone(nicknames_.get(w)+ContainerGame.INTRODUCTION_PTS+h_+ContainerPresident.RETURN_LINE_CHAR);
//                    container.ajouterTexteDansZone(nicknames_.get(l_)+ContainerPresident.RETURN_LINE_CHAR);
                }
                event_ = ContainerPresident.EMPTY+ContainerPresident.RETURN_LINE_CHAR;
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
//                container.ajouterTexteDansZone(ContainerPresident.EMPTY+ContainerPresident.RETURN_LINE_CHAR);
                userHand.supprimerCartes();
                hUser_ = partie_.mainUtilisateurTriee(userHandsBef_.last(), container.getDisplayingPresident());
                userHand.ajouterCartes(hUser_);
//                afficherMainUtilisateurSimuPresident(userHandsBef_.last());
                afficherMainUtilisateurSimuPresident();
                if (losers_.containsObj(DealPresident.NUMERO_UTILISATEUR)) {
                    ThreadInvoker.invokeNow(new SimulationRefreshHandPresidentGiftLoser(this, noDeal_));
                }
                if (winners_.containsObj(DealPresident.NUMERO_UTILISATEUR)) {
                    ThreadInvoker.invokeNow(new SimulationRefreshHandPresidentGiftWinner(this, noDeal_));
                }
            } else {
                HandPresident notSorted_ = userHandsBef_.last();
                HandPresident h_ = partie_.mainUtilisateurTriee(notSorted_, container.getDisplayingPresident());
                userHand.supprimerCartes();
                userHand.ajouterCartes(h_);
//                afficherMainUtilisateurSimuPresident(userHandsBef_.last());
                afficherMainUtilisateurSimuPresident();
            }
//            int nbTricks_ = partie_.getFilledTricksCount();
            int nbTricks_ = partie_.getDealTricks().get(noDeal_).size();
            for (int i = CustList.FIRST_INDEX; i < nbTricks_; i++) {
//                int noTrick_ = partie_.getFilledTricksIndex(i);
                TrickPresident t_ = partie_.getDealTricks().get(noDeal_).get(i);
                if (t_.getNombreDeCartesParJoueur() == 0) {
                    continue;
                }
                NumberMap<Integer,NumberMap<Byte,Playing>> lastStatus_;
                try {
                    lastStatus_ = partie_.getLastStatusDeals().get(noDeal_).get(i);
                } catch (RuntimeException _0) {
                    continue;
                }
                NumberMap<Integer,Byte> nextPlayer_;
                try {
                    nextPlayer_ = partie_.getNextPlayerDeals().get(noDeal_).get(i);
                } catch (RuntimeException _0) {
                    continue;
                }
//                CustList<HandPresident> userHands_;
//                try {
//                    userHands_ = partie_.getUserHands().get(noDeal_).get(i+1);
//                } catch (Exception e_) {
//                    continue;
//                }
                byte player_ = t_.getEntameur();
//                int noUserHand_ = CustList.FIRST_INDEX;
                int noHand_ = CustList.FIRST_INDEX;
                ThreadInvoker.invokeNow(new SettingPresidentDeck(container));
//                container.tapisPresident().setTalonPresident();
//                container.tapisPresident().repaintValidate();
                ThreadInvoker.invokeNow(new SettingPresidentStatus(container, lastStatus_.getVal(-1), nextPlayer_.getVal(-1)));
//                container.tapisPresident().setStatus(lastStatus_.getVal(-1), nextPlayer_.getVal(-1));
//                container.tapisPresident().repaintValidate();
                for (HandPresident h: t_) {
                    Constants.sleep(1000);
                    container.pause();
                    ThreadInvoker.invokeNow(new SettingPresidentHand(container, h));
//                    container.tapisPresident().setTalonPresident(h);
//                    container.tapisPresident().repaintValidate();
                    if (lastStatus_.contains(noHand_)) {
                        ThreadInvoker.invokeNow(new SettingPresidentStatus(container, lastStatus_.getVal(noHand_), nextPlayer_.getVal(noHand_)));
//                        container.tapisPresident().setStatus(lastStatus_.getVal(noHand_), nextPlayer_.getVal(noHand_));
//                        container.tapisPresident().repaintValidate();
                    }
                    player_ = t_.getPlayer(noHand_, partie_.getNombreDeJoueurs());
                    event_ = nicknames_.get(player_)+ContainerGame.INTRODUCTION_PTS+h+ContainerPresident.RETURN_LINE_CHAR;
                    ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                    container.ajouterTexteDansZone(event_);
//                    container.ajouterTexteDansZone(nicknames_.get(player_)+ContainerGame.INTRODUCTION_PTS+h+ContainerPresident.RETURN_LINE_CHAR);
                    if (player_ == DealPresident.NUMERO_UTILISATEUR) {
                        userHand.supprimerCartes(h);
                        afficherMainUtilisateurSimuPresident();
//                        if (noUserHand_ < userHands_.size()) {
//                            HandPresident hUser_ = userHands_.get(noUserHand_);
//                            afficherMainUtilisateurSimuPresident(hUser_);
//                            noUserHand_ ++;
//                        }
                    }
                    noHand_ ++;
                    if(container.isArretDemo()) {
                        arretDemo();
                        return;
                    }
                }
                byte ramasseur_= t_.getRamasseur(partie_.getNombreDeJoueurs());
                String mess_ = container.getMessages().getVal(MainWindow.TRICK_WINNER);
                event_ = StringList.simpleFormat(mess_, nicknames_.get(ramasseur_))+ContainerPresident.RETURN_LINE_CHAR;
                event_ += ContainerPresident.EMPTY+ContainerPresident.RETURN_LINE_CHAR;
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
//                container.ajouterTexteDansZone(StringList.simpleFormat(mess_, nicknames_.get(ramasseur_))+ContainerPresident.RETURN_LINE_CHAR);
//                container.ajouterTexteDansZone(ContainerPresident.EMPTY+ContainerPresident.RETURN_LINE_CHAR);
                Constants.sleep(2000);
            }
//            int noTrick_ = CustList.FIRST_INDEX;
//            for (TrickPresident t: partie_.getDealTricks().get(noDeal_)) {
//                CustList<HandPresident> mainsUtilisateurs_= partie_.getUserHands().get(noDeal_).get(noTrick_);
//                //for (HandPresident h:)
//                if (t.getNombreDeCartesParJoueur() == 0) {
//
//                }
//                noTrick_ ++;
//            }
            ThreadInvoker.invokeNow(new EndSimulation(this));
            Constants.sleep(2000);
            noDeal_ ++;
        }
    }

    void displayGivenCardsWinner(int _noDeal) {
        GamePresident partie_=partiePresidentSimulee();
        EqList<Numbers<Byte>> ranks_ = partie_.getRanksDeals();
        NumberMap<Byte,HandPresident> switchedCards_ = partie_.getSwitchedCardsDeals().get(_noDeal);
        Numbers<Byte> ranksBef_ = ranks_.get(_noDeal - 1);
        Numbers<Byte> losers_ = GamePresident.getLoosers(ranksBef_, partie_.nombresCartesEchangesMax());
        Numbers<Byte> winners_ = GamePresident.getWinners(ranksBef_, partie_.nombresCartesEchangesMax());
        container.getReceivedCards().supprimerCartes();
        byte l_ = GamePresident.getMatchingLoser(losers_, winners_, DealPresident.NUMERO_UTILISATEUR);
        container.getReceivedCards().ajouterCartes(switchedCards_.getVal(l_));
        container.updateCardsInPanelPresidentReceived();
        container.getGivenCards().supprimerCartes();
        container.getGivenCards().ajouterCartes(switchedCards_.getVal(DealPresident.NUMERO_UTILISATEUR));
        container.updateCardsInPanelPresidentGiven();
    }

    void displayGivenCardsLoser(int _noDeal) {
        GamePresident partie_=partiePresidentSimulee();
        EqList<Numbers<Byte>> ranks_ = partie_.getRanksDeals();
        NumberMap<Byte,HandPresident> switchedCards_ = partie_.getSwitchedCardsDeals().get(_noDeal);
        Numbers<Byte> ranksBef_ = ranks_.get(_noDeal - 1);
        Numbers<Byte> losers_ = GamePresident.getLoosers(ranksBef_, partie_.nombresCartesEchangesMax());
        Numbers<Byte> winners_ = GamePresident.getWinners(ranksBef_, partie_.nombresCartesEchangesMax());
        container.getReceivedCards().supprimerCartes();
        byte w_ = GamePresident.getMatchingWinner(winners_, losers_, DealPresident.NUMERO_UTILISATEUR);
        container.getReceivedCards().ajouterCartes(switchedCards_.getVal(w_));
        container.updateCardsInPanelPresidentReceived();
        container.getGivenCards().supprimerCartes();
        container.getGivenCards().ajouterCartes(switchedCards_.getVal(DealPresident.NUMERO_UTILISATEUR));
        container.updateCardsInPanelPresidentGiven();
    }

//    private void afficherMainUtilisateurSimuPresident(HandPresident _mainUtilisateur) {
////        HandPresident h_ = new HandPresident(_mainUtilisateur);
////        h_.sortCards(_decroissant, partieSimulee.partiePresident().isReversed());
//        ThreadInvoker.invokeNow(new SimulationRefreshHandPresident(container, _mainUtilisateur));
//    }

    private void afficherMainUtilisateurSimuPresident() {
//        HandPresident h_ = new HandPresident(_mainUtilisateur);
//        h_.sortCards(_decroissant, partieSimulee.partiePresident().isReversed());
        ThreadInvoker.invokeNow(new SimulationRefreshHandPresident(container, new HandPresident(userHand)));
    }

    private void arretDemo() {
        SwingUtilities.invokeLater(new StopDemo(container));
    }

    @Override
    public void endSimulation() {
        JPanel panneau_=new JPanel();
        panneau_.setLayout(new BoxLayout(panneau_, BoxLayout.PAGE_AXIS));
        ResultsPresident res_ = new ResultsPresident();
        GamePresident currentGame_=partiePresidentSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleePresident();
        res_.initialize(new StringList(nicknames_), container.getScores(), currentGame_.getRanksDeals().get(noDeal));
//        res_.initialize(new CustList<>(nicknames_), container.getScores(), currentGame_.getRanksDeals().get(noDeal + 1));
        res_.setUser(DealPresident.NUMERO_UTILISATEUR);
        res_.setMessages(Constants.getLanguage());
        SessionEditorPane editor_ = new SessionEditorPane();
        try {
//            editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor_.setLanguage(Constants.getLanguage());
            editor_.setDataBase(res_);
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
        editor_.setEditable(false);
        JScrollPane scroll_=new JScrollPane(editor_);
        scroll_.setPreferredSize(new Dimension(300,300));
        panneau_.add(scroll_);
        panneau_.add(stopButton);
        panneau_.add(container.getOwner().getClock());
        panneau_.add(container.getOwner().getLastSavedGameDate());
        container.setContentPane(panneau_);
        container.pack();
    }

}
