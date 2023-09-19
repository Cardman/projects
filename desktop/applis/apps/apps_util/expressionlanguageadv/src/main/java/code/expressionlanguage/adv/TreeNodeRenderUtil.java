package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.syntax.FileBlockIndex;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class TreeNodeRenderUtil {
    public static final String BEGIN_ENCODE = "&#";
    public static final String END_ENCODE = ";";
    public static final String SPAN = "span";
    public static final String HTML = "html";
    public static final String STYLE = "style";
    public static final String COLOR = "color";
    public static final String BACKGROUND_COLOR = "background-" + COLOR;
    public static final String BLACK = "000000";
    public static final String WHITE = "ffffff";
    public static final String RED = "ff0000";
    public static final String CYAN = "00ffff";
    public static final String EXT_SPACE = "&#160;";

    private TreeNodeRenderUtil() {
    }

    static String format(DbgNodeStruct _node, ContextEl _ctx) {
        return "<" + HTML + "><" + SPAN + " " + STYLE + "='" + BACKGROUND_COLOR + ":#"+BLACK+";'>"
                + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+RED+";'>" + transform(_node.str()) + "</" + SPAN + ">"
                + EXT_SPACE
                + wrapTypeDecl(_node.value().getClassName(_ctx))
                + EXT_SPACE
                + wrapValue(_node.value(), _ctx)
                + "</" + SPAN + ">"
                + "</" + HTML + ">";
    }

    private static String wrapTypeDecl(String _type) {
        return "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+WHITE+";'>" +transform(_type) + "</" + SPAN + ">";
    }

    private static String wrapValue(Struct _str, ContextEl _ctx) {
        if (_str instanceof ArrayStruct) {
            return "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+CYAN+";'>" +((ArrayStruct)_str).getLength()+ "</" + SPAN +">";
        }
        if (_str instanceof DisplayableStruct) {
            return "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+CYAN+";'>" +transform(((DisplayableStruct)_str).getDisplayedString(_ctx).getInstance())+ "</" + SPAN +">";
        }
        return "";
    }
    static String locations(FileBlockIndex _elt) {
        String content_ = resource(_elt.getFile());
        int lenContent_ = content_.length();
        int locIndex_ = _elt.getIndex();
        if (locIndex_ >= lenContent_) {
            return "";
        }
        int min_ = NumberUtil.max(0, content_.lastIndexOf('\n', locIndex_));
        int endLine_ = content_.indexOf('\n', locIndex_);
        if (endLine_ < 0) {
            endLine_ = lenContent_ - 1;
        }
        int max_ = NumberUtil.min(lenContent_, endLine_ +1);
        int until_ = ElResolver.incrAfterWord(locIndex_,content_);
        String before_ = transform(content_.substring(min_, locIndex_));
        String occurrence_ = transform(content_.substring(locIndex_,until_));
        String after_ = transform(content_.substring(until_,max_));
        return "<" + HTML + "><" + SPAN + " " + STYLE + "='" + BACKGROUND_COLOR + ":#"+BLACK+";'>"
            + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+RED+";'>" + locIndex_ + "</" + SPAN + ">"
            + EXT_SPACE
            + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+WHITE+";'>" +before_+ "</" + SPAN + ">"
            + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+RED+";'>" +occurrence_+ "</" + SPAN + ">"
            + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+WHITE+";'>" +after_+ "</" + SPAN + ">"
            + "</" + SPAN + ">"
            + "</" + HTML + ">";
    }

    static String resource(FileBlock _res) {
        if (_res == null) {
            return "";
        }
        return StringUtil.nullToEmpty(_res.getContent());
    }
    static String transform(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            str_.append(escapeNum(c));
        }
        return str_.toString();
    }
    private static String escapeNum(char _ch) {
        return BEGIN_ENCODE +(int)_ch+ END_ENCODE;
    }
}
