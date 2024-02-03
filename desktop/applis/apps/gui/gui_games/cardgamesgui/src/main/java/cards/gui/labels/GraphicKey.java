package cards.gui.labels;



import cards.gui.WindowCards;
import cards.gui.dialogs.FileConst;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;

public final class GraphicKey extends AbsMetaLabelCard {
    private static final String GRAPHIC_KEY = "cards.gui.labels.graphickey";
    private static final String DEFAULT="Default";
    private static final String DELTA = "delta";
    private final Ints couleurs;
    private final StringList pseudos;
    private final StringMap<String> messages;
    public GraphicKey(StringList _ppseudos, Ints _pcouleurs, String _lg, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        pseudos=_ppseudos;
        couleurs=_pcouleurs;
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _lg, GRAPHIC_KEY);
    }
    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.setColor(GuiConstants.BLACK);
        _g.translate(getWidth()/2,0);
        _g.setFont(DEFAULT, GuiConstants.BOLD,10);
        int nb_ = NumberUtil.min(pseudos.size(),couleurs.size());
        for (int i = 0; i < nb_; i++) {
            String n_ = pseudos.get(i);
            int c_ = couleurs.get(i);
            _g.translate(0,15);
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(n_,0,0);
            _g.setColor(c_);
            int x_ = -getWidth() / 2;
            _g.translate(x_,0);
            _g.drawLine(getWidth()/8,0,3*getWidth()/8,0);
            _g.translate(-x_,0);
        }
        _g.translate(0,15);
        _g.drawString(messages.getVal(DELTA),0,0);
        _g.translate(0,15);
        _g.setColor(GuiConstants.GRAY);
        _g.drawLine(getWidth()/8,0,3*getWidth()/8,0);
    }
}
