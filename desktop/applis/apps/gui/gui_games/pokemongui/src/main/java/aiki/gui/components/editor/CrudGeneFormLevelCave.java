package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormLevelCave extends CrudGeneFormListSub<LevelCave> {
    private GeneComponentModelSimpleElement<LevelCave> genePair;
    private int indexCave;
    private Cave cave;

    public CrudGeneFormLevelCave(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, null);

    }
    public void initForm(AbstractProgramInfos _core, AbsGeneComponentModelSubscribeFactory<LevelCave> _k) {
        getCrudGeneFormSubContent().clear();
        genePair = new GeneComponentModelSimpleElement<LevelCave>(_core,_k);
        initForm();
        initForm(new DisplayEntryCustEffect<LevelCave>(), getGenePair());
    }

    @Override
    protected void afterModif(int _index, LevelCave _value) {
        if (_index > -1) {
            if (dataMap().deleteLevelPlace(indexCaveAdj(),_index) == null) {
                return;
            }
            getCrudGeneFormSubContent().removeOpenSub();
            getList().remove(_index);
            afterModif();
            return;
        }
        getCrudGeneFormSubContent().removeOpenSub();
        afterModif();
    }
    private DataMap dataMap() {
        if (indexCave >= 0) {
            DataMap dm_ = Instances.newDataMap();
            CustList<Place> pls_ = new CustList<Place>();
            pls_.addAllElts(getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getPlaces());
            Cave c_ = Instances.newCave();
            c_.setLinksWithOtherPlaces(cave.getLinksWithOtherPlaces());
            c_.setLevels(new CustList<LevelCave>(getList()));
            pls_.set(indexCave,c_);
            dm_.setPlaces(pls_);
            dm_.setMiniMap(getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getMiniMap());
            dm_.setBegin(getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getBegin());
            dm_.setAccessCondition(getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getAccessCondition());
            return dm_;
        }
        DataMap dm_ = Instances.newDataMap();
        CustList<Place> pls_ = new CustList<Place>();
        pls_.addAllElts(getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getPlaces());
        Cave c_ = Instances.newCave();
        c_.setLinksWithOtherPlaces(cave.getLinksWithOtherPlaces());
        c_.setLevels(new CustList<LevelCave>(getList()));
        pls_.add(c_);
        dm_.setPlaces(pls_);
        dm_.setMiniMap(getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getMiniMap());
        dm_.setBegin(getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getBegin());
        dm_.setAccessCondition(getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getAccessCondition());
        return dm_;
    }
    private int indexCaveAdj() {
        if (indexCave >= 0) {
            return indexCave;
        }
        return getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getPlaces().size();
    }
    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(genePair.all());
        return all_;
    }

    public GeneComponentModelSimpleElement<LevelCave> getGenePair() {
        return genePair;
    }

    public Cave getCave() {
        return cave;
    }

    public void setCave(Cave _c) {
        this.cave = _c;
    }

    public int getIndexCave() {
        return indexCave;
    }

    public void setIndexCave(int _i) {
        this.indexCave = _i;
    }
}
