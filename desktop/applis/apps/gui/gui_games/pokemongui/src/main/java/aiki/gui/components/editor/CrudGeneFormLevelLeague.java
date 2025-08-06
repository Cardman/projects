package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.Instances;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;

public final class CrudGeneFormLevelLeague extends CrudGeneFormLevel {
    private final ContentComponentModelLevelLeagueLinks links;
    private final GeneComponentModelSubscribeLevelLeague genePair;
    private League cave;

    public CrudGeneFormLevelLeague(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, CrudGeneFormEntPlace _crud) {
        super(_fact,_facade,_sub,_fr,_crud);
        genePair = new GeneComponentModelSubscribeLevelLeague(_fr,_fact,_facade,_sub,this);
        links = new ContentComponentModelLevelLeagueLinks();
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
        genePair.setupValue(cave.getRooms().get(getSelectedLevel()));
        CrudGeneFormLevel.disable(getParent());
        return compo_;
    }

    @Override
    public void setupPlace(int _index) {
        links.selectIndexes(getCave(),getSelectedPlace(),_index);
    }

    @Override
    public void setupLevel(int _s) {
        cave.getRooms().set(_s,genePair.tryRet());
    }

    @Override
    public void addLevel() {
        cave.getRooms().add(Instances.newLevelLeague());
    }

    public League getCave() {
        return cave;
    }

    public void setCave(League _c) {
        this.cave = _c;
    }

    public AbsSubLevelLinks getLinks() {
        return links;
    }
}
