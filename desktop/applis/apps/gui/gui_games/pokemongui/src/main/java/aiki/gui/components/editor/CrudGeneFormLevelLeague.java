package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.Instances;
import aiki.map.places.*;
import code.gui.*;
import code.gui.events.*;
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

    public GeneComponentModelSubscribeLevelLeague getGenePair() {
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
        CrudGeneFormLevelCave.disable(getParent());
        return compo_;
    }

    @Override
    public AbsActionListener buildLink(int _i) {
        return new DisplayLinksCaveEvent(this,_i);
    }

    @Override
    public void displayGrid(int _index) {
        getElement().removeAll();
        links.selectIndexes(getCave(),getSelectedPlace(),_index);
        getElement().add(links.form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame()));
        links.getClose().addActionListener(new CloseLinksFormEvent(getParent()));
        CrudGeneFormLevelCave.disable(getParent());
        getFrame().pack();
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

    public ContentComponentModelLevelLeagueLinks getLinks() {
        return links;
    }
}
