package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.Instances;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;

public final class CrudGeneFormLevelCave extends CrudGeneFormLevel {
    private final ContentComponentModelLevelCaveLinks links;
    private final GeneComponentModelSubscribeLevelCave genePair;
    private Cave cave;

    public CrudGeneFormLevelCave(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, CrudGeneFormEntPlace _crud) {
        super(_fact,_facade,_sub,_fr,_crud);
        genePair = new GeneComponentModelSubscribeLevelCave(_fr,_fact,_facade,_sub,this);
        links = new ContentComponentModelLevelCaveLinks();
    }

    public AbsGeneComponentModelSubscribeLevel getGenePair() {
        return genePair;
    }

    @Override
    public Place getPlace() {
        return getCave();
    }

    @Override
    public AbsCustComponent gene() {
        AbsCustComponent compo_ = genePair.geneEnum();
        genePair.setupValue(cave.getLevels().get(getSelectedLevel()));
        disable(getParent());
        return compo_;
    }

    @Override
    public void setupPlace(int _index) {
        links.selectIndexes(getCave(),getSelectedPlace(),_index);
    }
    @Override
    public void setupLevel(int _s) {
        cave.getLevels().set(_s,genePair.tryRet());
    }

    @Override
    public void addLevel() {
        cave.getLevels().add(Instances.newLevelCave());
    }

    public Cave getCave() {
        return cave;
    }

    public void setCave(Cave _c) {
        this.cave = _c;
    }

    public AbsSubLevelLinks getLinks() {
        return links;
    }
}
