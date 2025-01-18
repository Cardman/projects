package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;

public final class ContentComponentModelSubscribeAreaSimple {
    private CrudGeneFormSimpleElementSub<WildPk> walk;
    private CrudGeneFormSimpleElementSub<WildPk> fish;
    private GeneComponentModelInt multFight;
    private AbsPanel form;
    public AbsPanel form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        form = _core.getCompoFactory().newPageBox();
        walk = new CrudGeneFormSimpleElementSub<WildPk>(_core, _fac, _fact, _f);
        walk.initForm(new DisplayEntryCustSubElementEffect<WildPk>(),new GeneComponentModelSubscribeFactoryDirect<WildPk>(new GeneComponentModelSubscribeWildPk(_core,_fac,_fact, _f)));
        form.add(walk.getGroup());
        fish = new CrudGeneFormSimpleElementSub<WildPk>(_core, _fac, _fact, _f);
        fish.initForm(new DisplayEntryCustSubElementEffect<WildPk>(),new GeneComponentModelSubscribeFactoryDirect<WildPk>(new GeneComponentModelSubscribeWildPk(_core,_fac,_fact, _f)));
        form.add(fish.getGroup());
        multFight = new GeneComponentModelInt(_core);
        form.add(multFight.geneInt());
        form.setVisible(false);
        return form;
    }
    public void display(boolean _res) {
        form.setVisible(_res);
    }
    void buildEntity(AreaApparition _edited) {
        _edited.setWildPokemon(walk.getList());
        _edited.setWildPokemonFishing(fish.getList());
        _edited.setMultFight(multFight.valueInt());
    }
    void feedForm(AreaApparition _edited) {
        walk.setupValues(ConverterCommonMapUtil.copyListWildPk(_edited.getWildPokemon()));
        fish.setupValues(ConverterCommonMapUtil.copyListWildPk(_edited.getWildPokemonFishing()));
        multFight.valueInt(_edited.getMultFight());
    }

    public CrudGeneFormSimpleElementSub<WildPk> getWalk() {
        return walk;
    }

}
