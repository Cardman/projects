package cards.belote;
import code.util.annot.RwXml;
import code.util.ints.Equallable;
import cards.belote.enumerations.DeclaresBelote;

@RwXml
public final class DeclareHandBelote implements Equallable<DeclareHandBelote> {

    private DeclaresBelote declare = DeclaresBelote.UNDEFINED;

    private HandBelote hand = new HandBelote();

    private byte player;

    public DeclareHandBelote(){
    }

    public DeclareHandBelote(DeclareHandBelote _annonceMainBelote) {
        declare = _annonceMainBelote.declare;
        hand = new HandBelote();
        hand.ajouterCartes(_annonceMainBelote.hand);
        player = _annonceMainBelote.getJoueur();
    }

    public DeclaresBelote getAnnonce() {
        return declare;
    }

    public void setAnnonce(DeclaresBelote _annonce) {
        declare = _annonce;
    }

    public HandBelote getMain() {
        return hand;
    }

    public void setMain(HandBelote _main) {
        hand = _main;
    }

    public byte getJoueur() {
        return player;
    }

    public void setJoueur(byte _joueur) {
        player = _joueur;
    }

    @Override
    public boolean eq(DeclareHandBelote _g) {
        if (declare != _g.declare) {
            return false;
        }
        if (!hand.eq(_g.hand)) {
            return false;
        }
        if (player != _g.player) {
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
