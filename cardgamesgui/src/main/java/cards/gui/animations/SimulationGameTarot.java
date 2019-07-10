package cards.gui.animations;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerSimuTarot;
import cards.tarot.*;
import code.gui.*;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SimulationGameTarot extends Thread implements SimulationGame {

    private Games partieSimulee = new Games();

    private ContainerSimuTarot container;

    private LabelButton stopButton;
    private SimulatingTarot simulatingTarot;

    /**This class thread is independant from EDT*/
    public SimulationGameTarot(ContainerSimuTarot _container) {
        container = _container;
        HandTarot pile_=HandTarot.pileBase();
        DealTarot donne_=new DealTarot(0L,pile_);
        RulesTarot regles_ = container.getWindow().getReglesTarot();
        donne_.setRandomDealer(regles_);
        regles_.setCartesBattues(MixCardsChoice.EACH_DEAL);
        donne_.initDonne(regles_);
        GameTarot gt_ = new GameTarot(GameType.EDIT,donne_,regles_);
        partieSimulee.jouerTarot(gt_);
        stopButton=new LabelButton(container.getMessages().getVal(MainWindow.STOP_DEMO));
        stopButton.addMouseListener(new StopEvent(this));
        DisplayingTarot dis_ = container.getDisplayingTarot();
        simulatingTarot = new SimulatingTarotImpl(container,partieSimulee,dis_,stopButton);
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
    private GameTarot partieTarotSimulee() {
        return partieSimulee.partieTarot();
    }

    @Override
    public void run() {
        new SettingSimulationComponent(this).start();
    }

    @Override
    public void setSimulationGui() {
        partieTarotSimulee().simuler(simulatingTarot);
    }

}
