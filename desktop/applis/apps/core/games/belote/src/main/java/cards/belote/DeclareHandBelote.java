package cards.belote;
import cards.belote.enumerations.DeclaresBelote;


public final class DeclareHandBelote {

    private DeclaresBelote declare = DeclaresBelote.UNDEFINED;

    private HandBelote hand = new HandBelote();

    private int player;

    public DeclareHandBelote(){
    }

    public DeclareHandBelote(DeclareHandBelote _annonceMainBelote) {
        declare = _annonceMainBelote.declare;
        hand = new HandBelote();
        hand.ajouterCartes(_annonceMainBelote.hand);
        player = _annonceMainBelote.getPlayer();
    }

    public boolean eq(DeclareHandBelote _g) {
        if (player != _g.player) {
            return false;
        }
        if (declare != _g.declare) {
            return false;
        }
        return hand.eq(_g.hand);
    }

    public DeclaresBelote getDeclare() {
        return declare;
    }

    public void setDeclare(DeclaresBelote _declare) {
        declare = _declare;
    }

    public HandBelote getHand() {
        return hand;
    }

    public void setHand(HandBelote _hand) {
        hand = _hand;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int _player) {
        player = _player;
    }
}
