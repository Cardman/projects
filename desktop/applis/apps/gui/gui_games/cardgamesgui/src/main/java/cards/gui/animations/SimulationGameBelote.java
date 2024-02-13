package cards.gui.animations;

import cards.belote.*;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerSimuBelote;
import code.maths.montecarlo.MonteCarloUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SimulationGameBelote implements Runnable,SimulationGame {
    private final Games partieSimulee = new Games();
    private final ContainerSimuBelote container;
    private final SimulatingBelote simulatingBelote;
    private final WindowCards win;
    /**This class thread is independant from EDT*/
    public SimulationGameBelote(ContainerSimuBelote _container, WindowCards _wc) {
        container = _container;
        win = _wc;
        HandBelote pile_=HandBelote.pileBase();
        DealBelote donne_=new DealBelote(0L);
        RulesBelote regles_ = container.getWindow().getReglesBelote();
        donne_.setDealer((byte) MonteCarloUtil.randomLong(regles_.getDealing().getId().getNombreJoueurs(),container.getWindow().getGenerator()));
        regles_.getCommon().setMixedCards(MixCardsChoice.EACH_DEAL);
        donne_.initDonne(regles_, container.getWindow().getGenerator(),pile_);
        GameBelote gb_ = new GameBelote(GameType.EDIT,donne_,regles_);
        partieSimulee.jouerBelote(gb_);
        DisplayingBelote dis_ = container.getDisplayingBelote();
        simulatingBelote = new SimulatingBeloteImpl(container,partieSimulee,dis_, new StopEvent(this), _wc.baseWindow().getIa().getBelote());
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
    private GameBelote partieBeloteSimulee() {
        return partieSimulee.partieBelote();
    }

    @Override
    public void run() {
        container.getOwner().getThreadFactory().newStartedThread(new SettingSimulationComponent(this));
    }

    @Override
    public void setSimulationGui() {
        partieBeloteSimulee().simuler(simulatingBelote);
    }
}
