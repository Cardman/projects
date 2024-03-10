package cards.gui.animations;

import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerSimuTarot;
import cards.tarot.*;
import code.threads.AbstractAtomicInteger;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SimulationGameTarot implements Runnable,SimulationGame {

    private final Games partieSimulee = new Games();

    private final SimulatingTarotImpl simulatingTarot;
    private final WindowCards win;
    private final AbstractAtomicInteger state;
    /**This class thread is independant from EDT*/
    public SimulationGameTarot(ContainerSimuTarot _container, WindowCards _wc) {
        win = _wc;
//        HandTarot pile_=HandTarot.pileBase();
//        DealTarot donne_=new DealTarot(0L);
//        RulesTarot regles_ = container.getWindow().getReglesTarot();
//        donne_.setRandomDealer(regles_,container.getWindow().getGenerator());
//        regles_.getCommon().setMixedCards(MixCardsChoice.EACH_DEAL);
//        donne_.initDonne(regles_,container.getWindow().getGenerator(),pile_);
//        GameTarot gt_ = new GameTarot(GameType.EDIT,donne_,regles_);
        GameTarot gt_ = _container.getWindow().baseWindow().getFirstDealTarot().deal(_container);
        partieSimulee.jouerTarot(gt_);
        DisplayingTarot dis_ = _container.getDisplayingTarot();
        AbstractAtomicInteger arr_ = _container.getArretDemo();
        arr_.set(AbstractSimulatingTarot.STATE_ALIVE);
        state = arr_;
        simulatingTarot = new SimulatingTarotImpl(_container,partieSimulee,dis_, new StopEvent(this),_wc.baseWindow().getIa().getTarot(), arr_);
    }

    @Override
    public void stopSimulation() {
        state.set(AbstractSimulatingTarot.STATE_STOPPED);
        win.menuSoloGames();
    }
    private GameTarot partieTarotSimulee() {
        return partieSimulee.partieTarot();
    }

    @Override
    public void run() {
        setSimulationGui();
//        container.getOwner().getThreadFactory().newStartedThread(new SettingSimulationComponent(this));
    }

    @Override
    public void setSimulationGui() {
        if (partieTarotSimulee().simuler(simulatingTarot)){
//            simulatingTarot.endDeal();
            win.getFrames().getCompoFactory().invokeNow(new EndDealSimuTarot(simulatingTarot));
        }
    }

    public SimulatingTarotImpl getSimulatingTarot() {
        return simulatingTarot;
    }
}
