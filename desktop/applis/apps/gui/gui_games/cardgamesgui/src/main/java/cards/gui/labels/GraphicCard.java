package cards.gui.labels;

import cards.consts.CouleurValeur;
import cards.facade.enumerations.GameEnum;
import cards.gui.dialogs.FileConst;
import cards.gui.panels.Carpet;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.core.StringUtil;

public abstract class GraphicCard<T> extends AbsMetaLabelCard {
    static final String DEFAULT="Default";
    private T card;
    private CouleurValeur id;
    private final boolean fullCard;
    private boolean peindreCarte;
    private AbstractImage bufferedImage;
    private TranslationsLg lg;
    private final GameEnum gameEnum;

    protected GraphicCard(GameEnum _ge,boolean _f, AbsCompoFactory _fact, TranslationsLg _l) {
        super(_fact);
        setHorizontalAlignment(GuiConstants.RIGHT);
        setVerticalAlignment(GuiConstants.TOP);
        this.fullCard = _f;
        this.lg = _l;
        peindreCarte = false;
        gameEnum = _ge;
    }

    protected GraphicCard(GameEnum _ge,T _e, CouleurValeur _cv, boolean _f, AbstractProgramInfos _fact, TranslationsLg _l) {
        this(_ge,_f,_fact.getCompoFactory(),_l);
        peindreCarte = true;
        card = _e;
        id = _cv;
        int[][] file_ = _l.getMaxiCards().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,
                StringUtil.concatNb(id.getNo(), FileConst.TXT_EXT)));
        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(_fact.getImageFactory(),file_);
    }

    public void setPreferredSize(boolean _small) {
        setPreferredSize(Carpet.getDimension(_small));
    }

    public void setCarteEnJeu(AbstractImageFactory _fact, TranslationsLg _lg, T _pc, CouleurValeur _cv) {
        setCarte(_fact, _lg, _pc, _cv);
    }
    void setCarte(AbstractImageFactory _fact, TranslationsLg _lg, T _pc, CouleurValeur _cv) {
        lg = _lg;
        card=_pc;
        id = _cv;
        peindreCarte=true;
        int[][] file_ = _lg.getMaxiCards().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,
                StringUtil.concatNb(_cv.getNo(), FileConst.TXT_EXT)));
//        int[][] file_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
//                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(_fact,file_);
    }
    public void setJeu(TranslationsLg _lg) {
        lg = _lg;
        peindreCarte=false;
    }
    public T getCard() {
        return card;
    }

    /**Methode importante dessinant les cartes des jeux de cartes face non cachee sauf pour certaines cartes du solitaire*/
    @Override
    public void paintComponent(AbstractImage _g2) {
        if(!peindreCarte) {
            _g2.setColor(GuiConstants.RED);
            _g2.fillRect(0,0,getWidth()-1,getHeight()-1);
            _g2.setColor(GuiConstants.BLACK);
            _g2.drawRect(0,0,getWidth()-1,getHeight()-1);
            _g2.setColor(GuiConstants.BLUE);
            _g2.setFont(DEFAULT, GuiConstants.BOLD,20);
            _g2.drawString(gameEnum.toString(lg),20,80);
            return;
        }
        _g2.setColor(GuiConstants.WHITE);
        _g2.fillRect(0,0,getWidth(),getHeight());
        if(fullCard) {
            //whole card
            _g2.drawImage(bufferedImage, 0, 0);
        } else {
            _g2.drawImage(bufferedImage, -75, 0);
        }
        _g2.setColor(GuiConstants.BLACK);
        _g2.drawRect(0,0,getWidth()-1,getHeight()-1);
    }
}
