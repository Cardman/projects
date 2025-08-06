package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public interface AbsSubLevelLinks {
    AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f);
    AbsButton getClose();
    IdList<SubscribedTranslation> getTranslationsGrid();
}
