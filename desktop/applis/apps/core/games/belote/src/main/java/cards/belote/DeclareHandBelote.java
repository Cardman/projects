package cards.belote;
import cards.belote.enumerations.DeclaresBelote;


public final class DeclareHandBelote {

    private DeclaresBelote declare = DeclaresBelote.UNDEFINED;

    private HandBelote hand = new HandBelote();

    private byte player;

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
        if (!hand.eq(_g.hand)) {
            return false;
        }
        return true;
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

    public byte getPlayer() {
        return player;
    }

    public void setPlayer(byte _player) {
        player = _player;
    }
}
