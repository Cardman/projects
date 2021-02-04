package aiki.main;

import aiki.game.fight.BallNumberRate;
import aiki.game.fight.Fighter;
import aiki.map.pokemon.UsablePokemon;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class AikiFactory {
    private final AbstractGraphicListGenerator<BallNumberRate> geneBallNumberRate;
    private final AbstractGraphicListGenerator<Fighter> geneFighter;
    private final AbstractGraphicListGenerator<String> genePkPanel;
    private final AbstractGraphicListGenerator<String> geneItPanel;
    private final AbstractGraphicListGenerator<String> geneTmPanel;
    private final AbstractGraphicListGenerator<UsablePokemon> geneUsPkPanel;

    public AikiFactory(AbstractGraphicListGenerator<BallNumberRate> _geneBallNumberRate, AbstractGraphicListGenerator<Fighter> _geneFighter, AbstractGraphicListGenerator<String> _genePkPanel, AbstractGraphicListGenerator<String> _geneItPanel, AbstractGraphicListGenerator<String> _geneTmPanel, AbstractGraphicListGenerator<UsablePokemon> _geneUsPkPanel) {
        geneBallNumberRate = _geneBallNumberRate;
        geneFighter = _geneFighter;
        genePkPanel = _genePkPanel;
        geneItPanel = _geneItPanel;
        geneTmPanel = _geneTmPanel;
        geneUsPkPanel = _geneUsPkPanel;
    }

    public AbstractGraphicListGenerator<BallNumberRate> getGeneBallNumberRate() {
        return geneBallNumberRate;
    }

    public AbstractGraphicListGenerator<Fighter> getGeneFighter() {
        return geneFighter;
    }

    public AbstractGraphicListGenerator<String> getGenePkPanel() {
        return genePkPanel;
    }

    public AbstractGraphicListGenerator<String> getGeneItPanel() {
        return geneItPanel;
    }

    public AbstractGraphicListGenerator<String> getGeneTmPanel() {
        return geneTmPanel;
    }

    public AbstractGraphicListGenerator<UsablePokemon> getGeneUsPkPanel() {
        return geneUsPkPanel;
    }
}
