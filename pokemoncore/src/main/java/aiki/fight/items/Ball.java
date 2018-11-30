package aiki.fight.items;



public final class Ball extends Item {

    public static final String ITEM = "aiki.fight.items.Ball";

    private String catchingRate;

    @Override
    public String getItemType() {
        return ITEM;
    }

    public String getCatchingRate() {
        return catchingRate;
    }
    public void setCatchingRate(String _catchingRate) {
        catchingRate = _catchingRate;
    }

}
