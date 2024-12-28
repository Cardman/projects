package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.Instances;
import aiki.map.places.*;
import code.gui.*;
import code.gui.events.*;
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

    public GeneComponentModelSubscribeLevelCave getGenePair() {
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
    public AbsActionListener buildLink(int _i) {
        return new DisplayLinksCaveEvent(this,_i);
    }

    @Override
    public void displayGrid(int _index) {
        getElement().removeAll();
        links.selectIndexes(getCave(),getSelectedPlace(),_index);
        getElement().add(links.form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame()));
        links.getClose().addActionListener(new CloseLinksFormEvent(getParent()));
        disable(getParent());
        getFrame().pack();
    }

    public static void disable(CrudGeneFormEntPlace _a) {
        _a.setEnabledButtons(false);
        for (CrudGeneFormLevel c: _a.getLevels()) {
            disableForm(c);
        }
        disableForm(_a);
        _a.enable(false);
    }

    public static void disableForm(AbsCrudGeneForm _a) {
        _a.getValidAddEdit().setEnabled(false);
        _a.getValidRemove().setEnabled(false);
        _a.getCancel().setEnabled(false);
        _a.getAdd().setEnabled(false);
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

    public ContentComponentModelLevelCaveLinks getLinks() {
        return links;
    }
}
