package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class ContentComponentModelLevelWithWild {
    private CrudGeneFormAbsAreaApparition areas;
    private AbsPanel form;
    public AbsPanel form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        form = _core.getCompoFactory().newPageBox();
        areas = new CrudGeneFormAbsAreaApparition(_core, _fac, _fact, _f);
        areas.initForm(_core,new GeneComponentModelSubscribeFactoryDirect<AbsAreaApparition>(new GeneComponentModelSubscribeArea(_f, _core, _fac, _fact)));
        form.add(areas.getGroup());
        form.setVisible(false);
        return form;
    }
    public void display(String _res) {
        form.setVisible(StringUtil.quickEq(_res, MessagesEditorSelect.PLACE_ROAD));
    }

    public CrudGeneFormAbsAreaApparition getAreas() {
        return areas;
    }
}
