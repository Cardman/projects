package aiki.game.fight.animations;
import code.maths.Rate;
import code.util.StringList;

public class AnimationEffectDamage extends AnimationEffect {

    private Rate power = Rate.zero();

    private StringList types = new StringList();

    public Rate getDamage() {
        return power;
    }

    public void setDamage(Rate _power) {
        power = _power;
    }

    public StringList getTypes() {
        return types;
    }

    public void setTypes(StringList _types) {
        types = _types;
    }
}
