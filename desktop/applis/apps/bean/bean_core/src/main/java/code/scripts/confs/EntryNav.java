package code.scripts.confs;

public final class EntryNav {
    private final String navCase;
    private final String redirect;

    public EntryNav(String _navCase, String _redirect) {
        this.navCase = _navCase;
        this.redirect = _redirect;
    }

    public String getNavCase() {
        return navCase;
    }

    public String getRedirect() {
        return redirect;
    }
}
