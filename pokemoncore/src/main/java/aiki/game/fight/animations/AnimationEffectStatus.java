package aiki.game.fight.animations;
import aiki.DataBase;

public class AnimationEffectStatus extends AnimationEffect {

    private String status = DataBase.EMPTY_STRING;

    public String getStatus() {
        return status;
    }

    public void setStatus(String _status) {
        status = _status;
    }
}
