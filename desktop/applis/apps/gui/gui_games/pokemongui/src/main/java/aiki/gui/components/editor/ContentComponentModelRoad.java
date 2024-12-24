package aiki.gui.components.editor;


import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class ContentComponentModelRoad {
    private final ContentComponentModelLevelWithWild levelWithWild = new ContentComponentModelLevelWithWild();
    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        return getLevelWithWild().form(_core, _fac, _fact, _f);
    }

    public ContentComponentModelLevelWithWild getLevelWithWild() {
        return levelWithWild;
    }

    public FormLevelGrid getLevel() {
        return getLevelWithWild().getLevel();
    }
}
