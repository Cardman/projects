package cards.gui.labels;



import cards.gui.containers.ContainerSingleImpl;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesGuiCards;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;

public final class GraphicKey extends AbsMetaLabelCard {
    private static final String DEFAULT="";
    private final Ints couleurs;
    private final StringList pseudos;
    private final StringMap<String> messages;
    public GraphicKey(StringList _ppseudos, Ints _pcouleurs, AbstractProgramInfos _compoFactory) {
        super(_compoFactory.getCompoFactory());
        pseudos=_ppseudos;
        couleurs=_pcouleurs;
        messages = ContainerSingleImpl.file(_compoFactory.currentLg());
    }
    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.setColor(GuiConstants.BLACK);
        _g.translate(getWidth()/2,0);
        _g.setFont(new MetaFont(DEFAULT, GuiConstants.BOLD,10));
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
        _g.drawString(messages.getVal(MessagesGuiCards.MAIN_DELTA),0,0);
        _g.translate(0,15);
        _g.setColor(GuiConstants.GRAY);
        _g.drawLine(getWidth()/8,0,3*getWidth()/8,0);
    }
}
