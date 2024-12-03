package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.effects.*;
import aiki.fight.items.*;
import aiki.fight.moves.effects.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class SubscribedTranslationMessagesFactoryCommon implements SubscribedTranslationMessagesFactory {

    private final ModifiedEntitiesRename modifiedEntitiesRename = new ModifiedEntitiesRename();

    @Override
    public void renameExp(FacadeGame _facade, String _previous, String _next) {
        StringList mids_ = mids(_facade);
        modifiedEntitiesRename.renameExp(_facade,new IdRenamingDataBase(mids_, _previous, _next));
    }
    @Override
    public void cancel() {
        modifiedEntitiesRename.cancel();
    }

    public void setEffect(Effect _e) {
        this.modifiedEntitiesRename.setEffect(_e);
    }

    public void setEffectEndRoundAbility(EffectEndRound _e) {
        this.modifiedEntitiesRename.setEffectEndRoundAbility(_e);
    }

    public void setEffectEndRoundCombo(EffectEndRound _e) {
        this.modifiedEntitiesRename.setEffectEndRoundCombo(_e);
    }

    public void setEffectEndRoundItem(EffectEndRound _e) {
        this.modifiedEntitiesRename.setEffectEndRoundItem(_e);
    }

    public void setEffectEndRoundStatus(EffectEndRound _e) {
        this.modifiedEntitiesRename.setEffectEndRoundStatus(_e);
    }

    public void setEffectSendingAbility(EffectWhileSendingWithStatistic _e) {
        this.modifiedEntitiesRename.setEffectSendingAbility(_e);
    }

    public void setEffectSendingItem(EffectWhileSendingWithStatistic _e) {
        this.modifiedEntitiesRename.setEffectSendingItem(_e);
    }

    public void setItemForBattle(Item _i) {
        this.modifiedEntitiesRename.setItemForBattle(_i);
    }

    @Override
    public SubscribedTranslation buildSub(AbsMap<String, String> _map, AbsMap<String,String> _withEmpty) {
        return new SubscribedTranslationMessages<String>(_map,this, _withEmpty);
    }

    @Override
    public AbsMap<String,String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade) {
        return new StringMap<String>(buildMessages(_facade).getVal(_api.getLanguage()));
    }

    @Override
    public AbsMap<String,String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade, AbsMap<String,String> _withEmpty) {
        StringMap<String> out_ = new StringMap<String>(buildMessages(_facade).getVal(_api.getLanguage()));
        out_.addAllEntries(_withEmpty);
        return out_;
    }
}
