package aiki.gui.components.editor;


import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class ContentComponentModelCave {
    private CrudGeneFormLevelCave crudGeneFormLevelCave;
    private AbsCustComponent form;
    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        crudGeneFormLevelCave = new CrudGeneFormLevelCave(_core,_fac,_fact,_f);
        crudGeneFormLevelCave.initForm(_core,new GeneComponentModelSubscribeFactoryDirect<LevelCave>(new GeneComponentModelSubscribeLevelCave(_f,_core,_fac,_fact,crudGeneFormLevelCave)));
        form = crudGeneFormLevelCave.getGroup();
        form.setVisible(false);
        return form;
    }
    public void setupRefs(Cave _cave, int _index) {
        crudGeneFormLevelCave.setIndexCave(_index);
        crudGeneFormLevelCave.setCave(_cave);
    }
    public void display(String _res) {
        form.setVisible(StringUtil.quickEq(_res,MessagesEditorSelect.PLACE_CAVE));
    }

    public CrudGeneFormLevelCave getCrudGeneFormLevelCave() {
        return crudGeneFormLevelCave;
    }
}
