package cards.gui.animations;

import cards.belote.*;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerSimuBelote;
import code.gui.CustComponent;
import code.gui.LabelButton;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SimulationGameBelote implements Runnable,SimulationGame {
    private Games partieSimulee = new Games();
    private ContainerSimuBelote container;
    private LabelButton stopButton;
    private SimulatingBelote simulatingBelote;
    /**This class thread is independant from EDT*/
    public SimulationGameBelote(ContainerSimuBelote _container) {
        container = _container;
        HandBelote pile_=HandBelote.pileBase();
        DealBelote donne_=new DealBelote(0L,pile_);
        RulesBelote regles_ = container.getWindow().getReglesBelote();
        donne_.setRandomDealer(regles_.getRepartition().getNombreJoueurs(),container.getWindow().getGenerator());
        regles_.setCartesBattues(MixCardsChoice.EACH_DEAL);
        donne_.initDonne(regles_,container.getDisplayingBelote(),container.getWindow().getGenerator());
        GameBelote gb_ = new GameBelote(GameType.EDIT,donne_,regles_);
        partieSimulee.jouerBelote(gb_);
        stopButton=new LabelButton(container.getMessages().getVal(MainWindow.STOP_DEMO));
        stopButton.addMouseListener(new StopEvent(this));
        DisplayingBelote dis_ = container.getDisplayingBelote();
        simulatingBelote = new SimulatingBeloteImpl(container,partieSimulee,dis_,stopButton);
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
    private GameBelote partieBeloteSimulee() {
        return partieSimulee.partieBelote();
    }

    @Override
    public void run() {
        CustComponent.newThread(new SettingSimulationComponent(this)).start();
    }

    @Override
    public void setSimulationGui() {
        partieBeloteSimulee().simuler(simulatingBelote);
    }
}
