package cards.gui.animations;

import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerSimuPresident;
import cards.president.*;
import code.threads.AbstractAtomicInteger;

/**Thread safe class*/
public final class SimulationGamePresident implements Runnable,SimulationGame {

    private final Games partieSimulee = new Games();
    private final ContainerSimuPresident container;
    private final SimulatingPresidentImpl simulatingPresident;
    private final WindowCards win;
    /**This class thread is independant from EDT*/
    public SimulationGamePresident(ContainerSimuPresident _container, WindowCards _wc) {
        win = _wc;
        container = _container;
//        RulesPresident regles_ = container.getWindow().getReglesPresident();
//        HandPresident pile_=HandPresident.stack(regles_.getNbStacks());
//        DealPresident donne_=new DealPresident(0L);
////        donne_.setRandomDealer(regles_,container.getWindow().getGenerator());
//        donne_.setDealer((byte) MonteCarloUtil.randomLong(regles_.getNbPlayers(),container.getWindow().getGenerator()));
//        regles_.getCommon().setMixedCards(MixCardsChoice.EACH_DEAL);
//        donne_.initDonne(regles_,container.getWindow().getGenerator(),pile_);
//        GamePresident gp_ = new GamePresident(GameType.EDIT,donne_,regles_, new Bytes());
        GamePresident gp_ = _container.getWindow().baseWindow().getFirstDealPresident().deal(_container);
        partieSimulee.jouerPresident(gp_);
        AbstractAtomicInteger arr_ = _container.getArretDemo();
        arr_.set(AbstractSimulatingPresident.STATE_ALIVE);
//        partieSimulee.sauvegarderPartieEnCours("demos/deal10.cdgame");
        DisplayingPresident dis_ = container.getDisplayingPresident();
        simulatingPresident = new SimulatingPresidentImpl(container,partieSimulee,dis_, new StopEvent(this),_wc.baseWindow().getIa().getPresident(), arr_);
    }
    @Override
    public Games getGames() {
        return partieSimulee;
    }
    @Override
    public void stopSimulation() {
        container.getArretDemo().set(AbstractSimulatingPresident.STATE_STOPPED);
//        container.setArretDemo(true);
        win.menuSoloGames();
    }

    private GamePresident partiePresidentSimulee() {
        return partieSimulee.partiePresident();
    }

    @Override
    public void run() {
        setSimulationGui();
//        container.getOwner().getThreadFactory().newStartedThread(new SettingSimulationComponent(this));
    }

    @Override
    public void setSimulationGui() {
        partiePresidentSimulee().simulate(simulatingPresident.getMaxDeals(),simulatingPresident,container.getWindow().getGenerator());
    }

    public SimulatingPresidentImpl getSimulatingPresident() {
        return simulatingPresident;
    }
}
