package code.player;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.stream.AbstractFileCoreStream;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.StringList;

public final class SongList {
    private static final String MEDIA = "media";
    private static final String SRC = "src";
    private StringList songList = new StringList();

    public StringList getSongList() {
        return songList;
    }

    public void addSongsFromContent(String _doc, AbstractFileCoreStream _fact, TechStreams _str) {
        String content_ = StreamTextFile.contentsOfFile(_doc,_fact,_str);
        Document doc_ = DocumentBuilder.parseNoTextDocument(content_);
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
