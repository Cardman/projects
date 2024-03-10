package cards.gui.animations;

import cards.belote.*;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerSimuBelote;
import code.threads.AbstractAtomicInteger;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SimulationGameBelote implements Runnable,SimulationGame {
    private final Games partieSimulee = new Games();
    private final SimulatingBeloteImpl simulatingBelote;
    private final WindowCards win;
    private final AbstractAtomicInteger state;

    /**This class thread is independant from EDT*/
    public SimulationGameBelote(ContainerSimuBelote _container, WindowCards _wc) {
        win = _wc;
//        HandBelote pile_=HandBelote.pileBase();
//        DealBelote donne_=new DealBelote(0L);
//        RulesBelote regles_ = container.getWindow().getReglesBelote();
//        donne_.setDealer((byte) MonteCarloUtil.randomLong(regles_.getDealing().getId().getNombreJoueurs(),container.getWindow().getGenerator()));
//        regles_.getCommon().setMixedCards(MixCardsChoice.EACH_DEAL);
//        donne_.initDonne(regles_, container.getWindow().getGenerator(),pile_);
//        GameBelote gb_ = new GameBelote(GameType.EDIT,donne_,regles_);
        GameBelote gb_ = _container.getWindow().baseWindow().getFirstDealBelote().deal(_container);
        partieSimulee.jouerBelote(gb_);
        DisplayingBelote dis_ = _container.getDisplayingBelote();
        AbstractAtomicInteger arr_ = _container.getArretDemo();
        arr_.set(AbstractSimulatingBelote.STATE_ALIVE);
        state = arr_;
        simulatingBelote = new SimulatingBeloteImpl(_container,partieSimulee,dis_, new StopEvent(this), _wc.baseWindow().getIa().getBelote(), arr_);
    }

    @Override
    public void stopSimulation() {
//        container.setArretDemo(true);
        state.set(AbstractSimulatingBelote.STATE_STOPPED);
        win.menuSoloGames();
    }
    private GameBelote partieBeloteSimulee() {
        return partieSimulee.partieBelote();
    }

    @Override
    public void run() {
        setSimulationGui();
//        container.thread(new SettingSimulationComponent(this));
//        container.getOwner().getThreadFactory().newStartedThread(new SettingSimulationComponent(this));
    }

    @Override
    public void setSimulationGui() {
        boolean ended_ = partieBeloteSimulee().simuler(simulatingBelote);
        if (ended_) {
            simulatingBelote.displayLastTrick(partieBeloteSimulee().getRamasseur());
//            simulatingBelote.endDeal();
            win.getFrames().getCompoFactory().invokeNow(new EndDealSimuBelote(simulatingBelote));
        }
    }

    public SimulatingBeloteImpl getSimulatingBelote() {
        return simulatingBelote;
    }
}
