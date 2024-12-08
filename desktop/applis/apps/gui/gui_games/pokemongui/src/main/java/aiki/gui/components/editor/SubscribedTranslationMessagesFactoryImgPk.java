package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.util.*;

public abstract class SubscribedTranslationMessagesFactoryImgPk extends SubscribedTranslationMessagesFactoryImg {

    @Override
    public void removeOpenSub(CrudGeneFormSubContent<EditedCrudPair<String, ImageArrayBaseSixtyFour>> _base) {
        _base.removeOpenSub();
    }

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedPokemon();
    }
}
