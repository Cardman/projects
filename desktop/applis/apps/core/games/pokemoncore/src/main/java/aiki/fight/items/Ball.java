package aiki.fight.items;



public final class Ball extends Item {

    private String catchingRate;

    @Override
    public String getItemType() {
        return BALL;
    }

    public String getCatchingRate() {
        return catchingRate;
    }
    public void setCatchingRate(String _catchingRate) {
        catchingRate = _catchingRate;
    }

}
