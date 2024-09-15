package code.sml;

public final class MessagesRendKeyWordsDefs {
    private MessagesRendKeyWordsDefs() {
    }
    public static RendKeyWordsDefs init() {
        RendKeyWordsDefs v_ = new RendKeyWordsDefs();
        v_.setDefMinLetter("abcdefghijklmnopqrstuvwxyz");
        v_.setDefMajLetter("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        v_.setDefMinLatin("ivxlcdmq");
        v_.setDefMajLatin("IVXLCDMQ");
        return v_;
    }
}
