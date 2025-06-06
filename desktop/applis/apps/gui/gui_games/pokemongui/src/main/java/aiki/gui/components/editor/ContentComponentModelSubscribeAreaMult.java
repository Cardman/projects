package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelSubscribeAreaMult {
    private CrudGeneFormSimpleElementSub<CustList<WildPk>> walk;
    private CrudGeneFormSimpleElementSub<CustList<WildPk>> fish;
    private AbsPanel form;
    public AbsPanel form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        form = _core.getCompoFactory().newPageBox();
        walk = new CrudGeneFormSimpleElementSub<CustList<WildPk>>(_core, _fac, _fact, _f);
        walk.initForm(new DisplayEntryCustSubElementEffect<CustList<WildPk>>(),new GeneComponentModelSubscribeFactoryDirect<CustList<WildPk>>(new GeneComponentModelSubscribeWildPkList(_core, _fac, _fact, _f)));
        form.add(SubscribedTranslationList.line(_core,MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_core.currentLg())),MessagesEditorSelect.AREA_WALK,walk.getGroup()));
        fish = new CrudGeneFormSimpleElementSub<CustList<WildPk>>(_core, _fac, _fact, _f);
        fish.initForm(new DisplayEntryCustSubElementEffect<CustList<WildPk>>(),new GeneComponentModelSubscribeFactoryDirect<CustList<WildPk>>(new GeneComponentModelSubscribeWildPkList(_core, _fac, _fact, _f)));
        form.add(SubscribedTranslationList.line(_core,MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_core.currentLg())),MessagesEditorSelect.AREA_FISH,fish.getGroup()));
        form.setVisible(false);
        return form;
    }
    public void display(boolean _res) {
        form.setVisible(_res);
    }
    void buildEntity(MultAreaApparition _edited) {
        _edited.setWildPokemonList(walk.getList());
        _edited.setWildPokemonFishingList(fish.getList());
    }
    void feedForm(MultAreaApparition _edited) {
        walk.setupValues(ConverterCommonMapUtil.copyListListWildPk(_edited.getWildPokemonList()));
        fish.setupValues(ConverterCommonMapUtil.copyListListWildPk(_edited.getWildPokemonFishingList()));
    }

    public CrudGeneFormSimpleElementSub<CustList<WildPk>> getWalk() {
        return walk;
    }
}
