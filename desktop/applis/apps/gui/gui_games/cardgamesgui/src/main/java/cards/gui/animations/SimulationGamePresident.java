package cards.gui.animations;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.containers.ContainerSimuPresident;
import cards.gui.dialogs.FileConst;
import cards.president.*;
import code.util.Bytes;

/**Thread safe class*/
public final class SimulationGamePresident implements Runnable,SimulationGame {

    private final Games partieSimulee = new Games();
    private final ContainerSimuPresident container;
    private final SimulatingPresident simulatingPresident;
    /**This class thread is independant from EDT*/
    public SimulationGamePresident(ContainerSimuPresident _container) {
        container = _container;
        RulesPresident regles_ = container.getWindow().getReglesPresident();
        HandPresident pile_=HandPresident.stack(regles_.getNbStacks());
        DealPresident donne_=new DealPresident(0L,pile_);
        donne_.setRandomDealer(regles_,container.getWindow().getGenerator());
        regles_.setMixedCards(MixCardsChoice.EACH_DEAL);
        donne_.initDonne(regles_,container.getWindow().getGenerator());
        GamePresident gp_ = new GamePresident(GameType.EDIT,donne_,regles_, new Bytes());
        partieSimulee.jouerPresident(gp_);
//        partieSimulee.sauvegarderPartieEnCours("demos/deal10.cdgame");
        DisplayingPresident dis_ = container.getDisplayingPresident();
        simulatingPresident = new SimulatingPresidentImpl(container,partieSimulee,dis_, new StopEvent(this));
    }
    @Override
    public Games getGames() {
        return partieSimulee;
    }
    @Override
    public void stopSimulation() {
        container.setArretDemo(true);
        container.getOwner().menuSoloGames();
    }

    private GamePresident partiePresidentSimulee() {
        return partieSimulee.partiePresident();
    }

    @Override
    public void run() {
        container.getOwner().getThreadFactory().newStartedThread(new SettingSimulationComponent(this));
    }

    @Override
    public void setSimulationGui() {
        int maxDeals_ = Math.min(FileConst.MAX_DEALS, container.getDisplayingPresident().getNbDeals());
        partiePresidentSimulee().simulate(maxDeals_,simulatingPresident,container.getWindow().getGenerator());
    }

}
