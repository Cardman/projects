package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementLevelMove implements DisplayEntryCustSubElement<LevelMove> {
    private final AbsMap<String, String> messages;
    private final SubscribedTranslationList subscribedTranslationList;

    public DisplayEntryCustSubElementLevelMove(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        messages = _sub.getFactoryMv().getContainer().buildMessages(_fact,_facade);
        subscribedTranslationList = _sub;
    }
    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<String>(messages,subscribedTranslationList.getFactoryMv(), new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, LevelMove> buildDisplay() {
        return new DisplayEntryCustLevelMove(messages);
    }

    @Override
    public Comparing<LevelMove> buildCmp() {
        return new ComparingLevelMove(messages);
    }
}
