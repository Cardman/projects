package code.bean;


public abstract class Bean {

    private String language = "";

    public abstract void beforeDisplaying();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String _language) {
        language = _language;
    }
}
