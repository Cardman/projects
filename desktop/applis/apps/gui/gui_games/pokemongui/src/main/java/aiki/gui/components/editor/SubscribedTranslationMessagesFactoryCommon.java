package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.effects.*;
import aiki.fight.items.*;
import aiki.fight.moves.effects.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class SubscribedTranslationMessagesFactoryCommon implements SubscribedTranslationMessagesFactory {

    private Effect effect;
    private EffectEndRound effectEndRoundAbility;
    private EffectEndRound effectEndRoundItem;
    private EffectEndRound effectEndRoundStatus;
    private EffectEndRound effectEndRoundCombo;
    private EffectWhileSendingWithStatistic effectSendingAbility;
    private EffectWhileSendingWithStatistic effectSendingItem;
    private Item itemForBattle;

    @Override
    public void renameExp(FacadeGame _facade, String _previous, String _next) {
        StringList mids_ = mids(_facade);
        if (effect != null) {
            _facade.getData().renameExpEffect(mids_, _previous, _next, effect);
        }
        if (effectEndRoundAbility != null) {
            _facade.getData().renameExpEndRound(mids_, _previous, _next, effectEndRoundAbility);
        }
        if (effectEndRoundCombo != null) {
            _facade.getData().renameExpEndRound(mids_, _previous, _next, effectEndRoundCombo);
        }
        if (effectEndRoundItem != null) {
            _facade.getData().renameExpEndRound(mids_, _previous, _next, effectEndRoundItem);
        }
        if (effectEndRoundStatus != null) {
            _facade.getData().renameExpEndRound(mids_, _previous, _next, effectEndRoundStatus);
        }
        if (effectSendingAbility != null) {
            _facade.getData().renameExpSend(mids_, _previous, _next, effectSendingAbility);
        }
        if (effectSendingItem != null) {
            _facade.getData().renameExpSend(mids_, _previous, _next, effectSendingItem);
        }
        if (itemForBattle instanceof ItemForBattle) {
            _facade.getData().renameExpItemForBattle(mids_, _previous, _next, (ItemForBattle) itemForBattle);
        }
    }
    @Override
    public void cancel() {
        effect = null;
        effectEndRoundAbility = null;
        effectEndRoundCombo = null;
        effectEndRoundItem = null;
        effectEndRoundStatus = null;
        effectSendingAbility = null;
        effectSendingItem = null;
        itemForBattle = null;
    }

    public void setEffect(Effect _e) {
        this.effect = _e;
    }

    public void setEffectEndRoundAbility(EffectEndRound _e) {
        this.effectEndRoundAbility = _e;
    }

    public void setEffectEndRoundCombo(EffectEndRound _e) {
        this.effectEndRoundCombo = _e;
    }

    public void setEffectEndRoundItem(EffectEndRound _e) {
        this.effectEndRoundItem = _e;
    }

    public void setEffectEndRoundStatus(EffectEndRound _e) {
        this.effectEndRoundStatus = _e;
    }

    public void setEffectSendingAbility(EffectWhileSendingWithStatistic _e) {
        this.effectSendingAbility = _e;
    }

    public void setEffectSendingItem(EffectWhileSendingWithStatistic _e) {
        this.effectSendingItem = _e;
    }

    public void setItemForBattle(Item _i) {
        this.itemForBattle = _i;
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
