package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.abilities.AbilityData;
import code.gui.AbsCommonFrame;
import code.gui.GeneComponentModel;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryAb extends SubscribedTranslationMessagesFactoryCommonParam<AbilityData> {

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedAbilities();
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renameAbility(_previous,_next);
    }

    @Override
    public StringMap<AbilityData> all(FacadeGame _facade) {
        return _facade.getData().getAbilities();
    }

    @Override
    public void delete(FacadeGame _facade, String _key) {
        _facade.getData().deleteAbility(_key);
    }

    @Override
    public void completeQuickMembers(FacadeGame _facade, String _key, AbilityData _value) {
        _facade.getData().completeQuickMembers(_key,_value);
    }

    @Override
    public GeneComponentModel<AbilityData> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent _facade) {
        return new GeneComponentModelAbilityData(_core);
    }

    @Override
    public void removeOpenSub(CrudGeneFormSubContent _base) {
        _base.removeOpenSub();
    }

    @Override
    public IdList<SubscribedTranslation> all() {
        return new IdList<SubscribedTranslation>();
    }

}
