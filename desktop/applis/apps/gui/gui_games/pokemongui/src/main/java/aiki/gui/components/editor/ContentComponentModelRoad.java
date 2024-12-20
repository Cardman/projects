package aiki.gui.components.editor;


import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class ContentComponentModelRoad {
    private final ContentComponentModelLevelWithWild levelWithWild = new ContentComponentModelLevelWithWild();
    public AbsPanel form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        return getLevelWithWild().form(_core, _fac, _fact, _f);
    }
    public void display(String _res) {
        levelWithWild.display(_res);
    }

    public ContentComponentModelLevelWithWild getLevelWithWild() {
        return levelWithWild;
    }
}
