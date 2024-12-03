package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.effects.*;
import aiki.fight.items.*;
import aiki.fight.moves.effects.*;

public final class ModifiedEntitiesRename {

    private Effect effect;
    private EffectEndRound effectEndRoundAbility;
    private EffectEndRound effectEndRoundItem;
    private EffectEndRound effectEndRoundStatus;
    private EffectEndRound effectEndRoundCombo;
    private EffectWhileSendingWithStatistic effectSendingAbility;
    private EffectWhileSendingWithStatistic effectSendingItem;
    private Item itemForBattle;

    public void renameExp(FacadeGame _facade, AbsRenamingDataBase _abs) {
        if (effect != null) {
            _facade.getData().renameExpEffect(effect, _abs);
        }
        if (effectEndRoundAbility != null) {
            _facade.getData().renameExpEndRound(effectEndRoundAbility, _abs);
        }
        if (effectEndRoundCombo != null) {
            _facade.getData().renameExpEndRound(effectEndRoundCombo, _abs);
        }
        if (effectEndRoundItem != null) {
            _facade.getData().renameExpEndRound(effectEndRoundItem, _abs);
        }
        if (effectEndRoundStatus != null) {
            _facade.getData().renameExpEndRound(effectEndRoundStatus, _abs);
        }
        if (effectSendingAbility != null) {
            _facade.getData().renameExpSend(effectSendingAbility, _abs);
        }
        if (effectSendingItem != null) {
            _facade.getData().renameExpSend(effectSendingItem, _abs);
        }
        if (itemForBattle instanceof ItemForBattle) {
            _facade.getData().renameExpItemForBattle((ItemForBattle) itemForBattle, _abs);
        }
    }
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
}
