package cards.tarot;

public final class CallDiscard {

    private HandTarot calledCard = new HandTarot();

    private HandTarot cardsToBeDiscarded = new HandTarot();

    private boolean slam;

    public HandTarot getCarteAppelee() {
        return calledCard;
    }

    public void setCarteAppelee(HandTarot _carteAppelee) {
        calledCard = _carteAppelee;
    }

    public HandTarot getEcartAFaire() {
        return cardsToBeDiscarded;
    }

    public void setEcartAFaire(HandTarot _ecartAFaire) {
        cardsToBeDiscarded = _ecartAFaire;
    }

    public boolean isChelem() {
        return slam;
    }

    public void setChelem(boolean _chelem) {
        slam = _chelem;
    }

}
