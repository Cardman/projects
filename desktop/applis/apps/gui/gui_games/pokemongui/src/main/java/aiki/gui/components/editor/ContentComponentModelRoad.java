package aiki.gui.components.editor;


import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelRoad {
    private final ContentComponentModelLevelWithWild levelWithWild = new ContentComponentModelLevelWithWild();
    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        return getLevelWithWild().form(_core, _fac, _fact, _f);
    }
    public void setupGridDims(int _nbPlace, int _nbLevel, Place _pl, LevelWithWildPokemon _wild) {
        getLevelWithWild().setupGridDims(_nbPlace, _nbLevel, _pl, _wild);
    }

    public GeneComponentModelEltEnumSub<String> getItems() {
        return getLevelWithWild().getItems();
    }

    public FormWildPk getLegendaryPks() {
        return getLevelWithWild().getLegendaryPks();
    }

    public GeneComponentModelEltEnumSub<Short> getTm() {
        return getLevelWithWild().getTm();
    }

    public GeneComponentModelEltEnumSub<Short> getHm() {
        return getLevelWithWild().getHm();
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
    public ContentComponentModelLevelWithWild getLevelWithWild() {
        return levelWithWild;
    }

}
