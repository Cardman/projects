package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.stream.AbsClipStream;
import code.stream.BytesInfo;
import code.util.core.StringUtil;

public final class InterpretedFile {
    private final BytesInfo input;
    private final String text;
    private final Document document;
    private final AbsClipStream clipSimple;
    private final AbsClipStream clipMp3;
    public InterpretedFile(AbstractProgramInfos _api, String[] _args) {
        if (_args.length == 0) {
            input = new BytesInfo(new byte[0],true);
            text = "";
            document = null;
            clipSimple = null;
            clipMp3 = null;
            return;
        }
        input = _api.getStreams().getBinFact().loadFile(_args[0]);
        text = StringUtil.nullToEmpty(StringUtil.decode(input.getBytes()));
        document = DocumentBuilder.parseNoTextDocument(text);
        clipSimple = _api.openClip(input.getBytes());
        clipMp3 = _api.openMp3(input.getBytes());
    }

    public BytesInfo getInput() {
        return input;
    }

    public AbsClipStream getClipMp3() {
        return clipMp3;
    }

    public AbsClipStream getClipSimple() {
        return clipSimple;
    }

    public String getText() {
        return text;
    }

    public Document getDocument() {
        return document;
    }
}
