package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormAbsAreaApparition extends CrudGeneFormListSub<AbsAreaApparition> {
    private GeneComponentModelSimpleElement<AbsAreaApparition> genePair;
    private Points<Block> blocks = new PointsBlock();
    private FormBlockTile formBlockTile;

    public CrudGeneFormAbsAreaApparition(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, null);

    }
    public void initForm(AbstractProgramInfos _core, AbsGeneComponentModelSubscribeFactory<AbsAreaApparition> _k) {
        getCrudGeneFormSubContent().clear();
        genePair = new GeneComponentModelSimpleElement<AbsAreaApparition>(_core,_k);
        initForm();
        initForm(new DisplayEntryCustEffect<AbsAreaApparition>(), genePair);
    }

    @Override
    protected void afterModif(int _index, AbsAreaApparition _value) {
        if (_index > -1) {
            if (LevelWithWildPokemon.existBlock(_index,blocks)) {
                return;
            }
            getCrudGeneFormSubContent().removeOpenSub();
            getList().remove(_index);
            renameAreas(_index);
            if (formBlockTile.getIndexApparition() != null && formBlockTile.getIndexApparition().getValue() > _index) {
                formBlockTile.getIndexApparition().setValue(formBlockTile.getIndexApparition().getValue() - 1L);
            }
            afterModif();
            return;
        }
        getCrudGeneFormSubContent().removeOpenSub();
        afterModif();
    }
    public void renameAreas(int _index) {
        for (Block b: blocks.values()) {
            if (b.getIndexApparition() > _index) {
                b.setIndexApparition((short) (b.getIndexApparition()-1));
            }
        }
    }
    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(genePair.all());
        return all_;
    }

    public GeneComponentModelSimpleElement<AbsAreaApparition> getGenePair() {
        return genePair;
    }

    public void setBlocks(Points<Block> _b) {
        this.blocks = _b;
    }

    public void setFormBlockTile(FormBlockTile _f) {
        this.formBlockTile = _f;
    }
}
