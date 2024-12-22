package aiki.gui.components.editor;


import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class ContentComponentModelRoad {
    private FormLevelGrid level;
    private final ContentComponentModelLevelWithWild levelWithWild = new ContentComponentModelLevelWithWild();
    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        AbsScrollPane map_ = _core.getCompoFactory().newAbsScrollPane();
        level = new FormLevelGrid(_core,_fac,map_,_f,_fact);
        AbsPanel form_ = getLevelWithWild().form(_core, _fac, _fact, _f);
        levelWithWild.getAreas().setFormBlockTile(level.getFormBlockTile());
        return _core.getCompoFactory().newHorizontalSplitPane(map_,_core.getCompoFactory().newAbsScrollPane(form_));
    }
    public void display(String _res) {
        levelWithWild.display(_res);
    }

    public ContentComponentModelLevelWithWild getLevelWithWild() {
        return levelWithWild;
    }

    public FormLevelGrid getLevel() {
        return level;
    }
}
