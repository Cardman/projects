package cards.gui.animations;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerSimuTarot;
import cards.tarot.*;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SimulationGameTarot implements Runnable,SimulationGame {

    private final Games partieSimulee = new Games();

    private final ContainerSimuTarot container;

    private final SimulatingTarot simulatingTarot;
    private final WindowCards win;
    /**This class thread is independant from EDT*/
    public SimulationGameTarot(ContainerSimuTarot _container, WindowCards _wc) {
        container = _container;
        win = _wc;
        HandTarot pile_=HandTarot.pileBase();
        DealTarot donne_=new DealTarot(0L);
        RulesTarot regles_ = container.getWindow().getReglesTarot();
        donne_.setRandomDealer(regles_,container.getWindow().getGenerator());
        regles_.getCommon().setMixedCards(MixCardsChoice.EACH_DEAL);
        donne_.initDonne(regles_,container.getWindow().getGenerator(),pile_);
        GameTarot gt_ = new GameTarot(GameType.EDIT,donne_,regles_);
        partieSimulee.jouerTarot(gt_);
        DisplayingTarot dis_ = container.getDisplayingTarot();
        simulatingTarot = new SimulatingTarotImpl(container,partieSimulee,dis_, new StopEvent(this),_wc.baseWindow().getIa().getTarot());
    }
    @Override
    public Games getGames() {
        return partieSimulee;
    }
    @Override
    public void stopSimulation() {
        container.setArretDemo(true);
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
        partieTarotSimulee().simuler(simulatingTarot);
    }

}
