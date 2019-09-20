package code.player;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.stream.StreamTextFile;
import code.util.StringList;

public final class SongList {
    private static final String MEDIA = "media";
    private static final String SRC = "src";
    private StringList songList = new StringList();

    public StringList getSongList() {
        return songList;
    }

    public void addSongsFromContent(String _doc) {
        String content_ = StreamTextFile.contentsOfFile(_doc);
        Document doc_ = DocumentBuilder.parseSax(content_);
        if (doc_ == null) {
            return;
        }
        addSongs(doc_);
    }

    public void addSongs(Document _doc) {
        for (Element e: _doc.getElementsByTagName(MEDIA)) {
            String v_ = e.getAttribute(SRC);
            songList.add(v_);
        }
    }
}
