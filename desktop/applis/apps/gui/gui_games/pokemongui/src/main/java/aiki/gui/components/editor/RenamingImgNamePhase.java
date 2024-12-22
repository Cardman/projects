package aiki.gui.components.editor;

public final class RenamingImgNamePhase {
    private String oldId = "";
    private String newId = "";
    private ImgRetriever retriever;


    public String getOldId() {
        return oldId;
    }

    public void setOldId(String _o) {
        this.oldId = _o;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String _n) {
        this.newId = _n;
    }

    public ImgRetriever getRetriever() {
        return retriever;
    }

    public void setRetriever(ImgRetriever _r) {
        this.retriever = _r;
    }
}
