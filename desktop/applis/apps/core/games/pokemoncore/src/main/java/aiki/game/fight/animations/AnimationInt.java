package aiki.game.fight.animations;

public abstract class AnimationInt {
    private int index;
    protected AnimationInt() {
    }
    public int getIndex(){
        return index;
    }

    public void setIndex(int _i) {
        this.index = _i;
    }
}
