package aiki.beans;

import aiki.beans.facade.fight.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayKeyHypothesis implements BeanDisplayEltGrid<KeyHypothesis> {
    @Override
    public int displayEltGrid(CommonBean _rend, KeyHypothesis _info) {
        _rend.formatMessageDirCts(_info.getPlayerPokemon());
        _rend.formatMessageDirCts(_info.getMove());
        String message_ = targetPk(_rend,_info);
        _rend.formatMessageDirCts(message_);
        _rend.formatMessageDirCts(_info.getDamage().toNumberString()+" - "+_info.getDamageSecond().toNumberString());
        return 4;
    }

    private String targetPk(CommonBean _rend, KeyHypothesis _k) {
        String message_;
        if (_k.isBelongsToUser()) {
            message_ = _rend.formatMessageRend(MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_PLAYER);
        } else {
            message_ = _rend.formatMessageRend(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_FOE);
        }
        message_+="\u00A0";
        message_+= _k.getTargetPokemon();
        return message_;
    }
}
