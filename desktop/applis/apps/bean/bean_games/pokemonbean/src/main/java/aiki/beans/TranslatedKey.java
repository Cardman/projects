package aiki.beans;


public final class TranslatedKey {
    private final String key;
    private final String translation;
    private final AbsRedirect redirect;
    private final String dest;
    private final String keyForm;

    public TranslatedKey(String _k, String _t) {
        this(_k,_t,null, "", "");
    }

    public TranslatedKey(String _k, String _t, AbsRedirect _r, String _d, String _kForm) {
        this.key = _k;
        translation = _t;
        redirect = _r;
        dest = _d;
        keyForm = _kForm;
    }

    public String getKey() {
        return key;
    }

    public String getTranslation() {
        return translation;
    }

    public AbsRedirect getRedirect() {
        return redirect;
    }

    public String getDest() {
        return dest;
    }

    public String getKeyForm() {
        return keyForm;
    }
}
