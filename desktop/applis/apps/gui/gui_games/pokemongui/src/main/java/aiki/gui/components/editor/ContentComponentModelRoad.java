package aiki.gui.components.editor;


import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.Coords;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelRoad {
    private final ContentComponentModelLevelWithWild levelWithWild = new ContentComponentModelLevelWithWild();

    public AbsCustComponent setupGridDims(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f,Coords _coords, Place _pl, LevelWithWildPokemon _wild) {
        AbsCustComponent form_ = getLevelWithWild().form(_core, _fac, _fact, _f);
        getLevelWithWild().setupGridDims(_coords, _pl, _wild);
        return form_;
    }

    public void removeSubs() {
        levelWithWild.removeSubs();
    }
    public GeneComponentModelEltEnumSub<String> getItems() {
        return getLevelWithWild().getItems();
    }

    public FormWildPk getLegendaryPks() {
        return getLevelWithWild().getLegendaryPks();
    }

    public GeneComponentModelEltEnumSub<Integer> getTm() {
        return getLevelWithWild().getTm();
    }

    public GeneComponentModelEltEnumSub<Integer> getHm() {
        return getLevelWithWild().getHm();
    }

    public ContentComponentModelDealerItem getDealerItem() {
        return getLevelWithWild().getDealerItem();
    }

    public ContentComponentModelTrainerMultiFights getTrainerMultiFights() {
        return getLevelWithWild().getTrainerMultiFights();
    }

    public ContentComponentModelDualFight getDualFight() {
        return getLevelWithWild().getDualFight();
    }

    public StringMap<AbsButton> getTiles() {
        return getLevelWithWild().getTiles();
    }
    public LevelWithWildPokemon getEdited() {
        return getLevelWithWild().getEdited();
    }
    public CrudGeneFormAbsAreaApparition getAreas() {
        return getLevelWithWild().getAreas();
    }
    public FormLevelGrid getLevel() {
        return getLevelWithWild().getLevel();
    }

    public AbsButton getRemoveTile() {
        return getLevelWithWild().getRemoveTile();
    }

    public AbsButton getMoveTile() {
        return getLevelWithWild().getMoveTile();
    }
    public ContentComponentModelLevelWithWild getLevelWithWild() {
        return levelWithWild;
    }

}
