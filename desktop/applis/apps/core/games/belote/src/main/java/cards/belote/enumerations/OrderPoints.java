package cards.belote.enumerations;

public enum OrderPoints {
    TRUMP_JACK(8,20,14),
    TRUMP_9(7,14,9),
    TRUMP_1(6,11,6),
    TRUMP_10(5,10,4),
    TRUMP_KING(4,4,3),
    TRUMP_QUEEN(3,3,2),
    TRUMP_8(2,0,0),
    TRUMP_7(1,0,0),
    SUIT_1(8,11,19),
    SUIT_10(7,10,10),
    SUIT_KING(6,4,4),
    SUIT_QUEEN(5,3,3),
    SUIT_JACK(4,2,2),
    SUIT_9(3,0,0),
    SUIT_8(2,0,0),
    SUIT_7(1,0,0);

    private final int order;
    private final int pointsDomSuit;
    private final int pointsNoDomSuit;
    OrderPoints(int _order, int _pointsDomSuit, int _pointsNoDomSuit) {
        order = _order;
        pointsDomSuit = _pointsDomSuit;
        pointsNoDomSuit = _pointsNoDomSuit;
    }

    int getOrder() {
        return order;
    }

    int getPointsDomSuit() {
        return pointsDomSuit;
    }

    int getPointsNoDomSuit() {
        return pointsNoDomSuit;
    }
}
