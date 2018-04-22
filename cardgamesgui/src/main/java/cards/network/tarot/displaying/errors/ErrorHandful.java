package cards.network.tarot.displaying.errors;
import cards.tarot.enumerations.Handfuls;
import code.util.annot.RwXml;

@RwXml
public final class ErrorHandful {

    private Handfuls handful;

    private String error;

    public Handfuls getHandful() {
        return handful;
    }

    public void setHandful(Handfuls _handful) {
        handful = _handful;
    }

    public String getError() {
        return error;
    }

    public void setError(String _error) {
        error = _error;
    }
}
