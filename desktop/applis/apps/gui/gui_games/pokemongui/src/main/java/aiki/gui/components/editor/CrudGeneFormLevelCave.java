package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;

public final class CrudGeneFormLevelCave extends CrudGeneFormLevel {
    private final GeneComponentModelSubscribeLevelCave genePair;
    private Cave cave;

    public CrudGeneFormLevelCave(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, CrudGeneFormEntPlace _crud) {
        super(_fact,_facade,_sub,_fr,_crud);
        genePair = new GeneComponentModelSubscribeLevelCave(_fr,_fact,_facade,_sub,this);
    }

    public GeneComponentModelSubscribeLevelCave getGenePair() {
        return genePair;
    }

    @Override
    public Place getPlace() {
        return getCave();
    }

    @Override
    public AbsCustComponent gene(boolean _s) {
        AbsCustComponent compo_ = genePair.geneEnum();
        if (cave.getLevels().isValidIndex(getSelectedLevel())) {
            genePair.setupValue(cave.getLevels().get(getSelectedLevel()));
        }
        return compo_;
    }

    @Override
    public void setupLevel(int _s) {
        cave.getLevels().set(_s,genePair.tryRet());
    }

    @Override
    public void addLevel() {
        cave.getLevels().add(genePair.tryRet());
    }

    public Cave getCave() {
        return cave;
    }

    public void setCave(Cave _c) {
        this.cave = _c;
    }

}
