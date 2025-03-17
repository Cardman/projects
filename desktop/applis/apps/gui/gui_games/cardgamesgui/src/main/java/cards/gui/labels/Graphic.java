package cards.gui.labels;


import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;
import code.maths.Rate;
import code.util.CustList;
import code.util.Ints;
import code.util.Longs;
import code.util.core.IndexConstants;

/**
    */

public final class Graphic extends AbsMetaLabelCard {
    private final CustList<Longs> scores;
    private final Longs sommes;
    private final CustList<Rate> sigmas;
    private final Ints couleurs;
    public Graphic(CustList<Longs> _pscores, Longs _psommes, CustList<Rate> _psigmas, Ints _pcouleurs, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        scores=_pscores;
        sommes=_psommes;
        sigmas=_psigmas;
        couleurs=_pcouleurs;
    }
    @Override
    public void paintComponent(AbstractImage _g) {
        int rapport_=getWidth()/scores.size();
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.translate(0,getHeight()/2);
        dessinerPointillesHorizontal(_g);
        dessinerPointillesVertical(_g);
        _g.setColor(GuiConstants.BLACK);
        _g.drawLine(0,0,getWidth(),0);
        int nombreJoueurs_=scores.last().size();
        double esperance_;
        int nbDeals_ = scores.size();
//        nbDealsMinusOne_--;
        for(int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
            _g.setColor(GuiConstants.newColor(couleurs.get(joueur_)));
//            esperance_=(double)sommes.first()/(double)nombreJoueurs_;
            int x_ = 0;
            int y_ = 0;
//            _g.drawLine(0,0,rapport_,(int)(esperance_-scores.first().get(joueur_)));
            for(int partie_ = IndexConstants.FIRST_INDEX; partie_<nbDeals_; partie_++) {
//            for(int partie_ = IndexConstants.FIRST_INDEX; partie_<nbDealsMinusOne_; partie_++) {
                esperance_=(double)sommes.get(partie_)/(double)nombreJoueurs_;
//                double esperance2_=(double)sommes.get(partie_+1)/(double)nombreJoueurs_;
                int nx_ = rapport_*(partie_+1);
                int ny_ = (int)(esperance_-scores.get(partie_).get(joueur_));
                _g.drawLine(x_,y_,nx_,ny_);
//                _g.drawLine(rapport_*(partie_+1),(int)(esperance_-scores.get(partie_).get(joueur_)),rapport_*(partie_+2),(int)(esperance2_-scores.get(partie_+1).get(joueur_)));
                x_ = nx_;
                y_ = ny_;
            }
        }
        _g.setColor(GuiConstants.GRAY);
//        Rate espPlusTroisSigmas_=sigmas.first();
//        Rate espMoinsTroisSigmas_=sigmas.first().opposNb();
        int x_ = 0;
        int y1_ = 0;
        int y2_ = 0;
//        _g.drawLine(0,0,rapport_,(int)-espPlusTroisSigmas_.ll());
//        _g.drawLine(0,0,rapport_,(int)-espMoinsTroisSigmas_.ll());
        for(int partie_ = IndexConstants.FIRST_INDEX; partie_<nbDeals_; partie_++) {
            Rate espPlusTroisSigmas_ = sigmas.get(partie_);
//            Rate espPlusTroisSigmas2_=sigmas.get(partie_+1);
            Rate espMoinsTroisSigmas_ = sigmas.get(partie_).opposNb();
//            Rate espMoinsTroisSigmas2_=sigmas.get(partie_+1).opposNb();
            int nx_ = rapport_*(partie_+1);
            int ny1_ = (int)-espPlusTroisSigmas_.ll();
            int ny2_ = (int)-espMoinsTroisSigmas_.ll();
            _g.drawLine(x_,y1_,nx_,ny1_);
            _g.drawLine(x_,y2_,nx_,ny2_);
//            _g.drawLine(rapport_*(partie_+1),(int)-espPlusTroisSigmas_.ll(),rapport_*(partie_+2),(int)-espPlusTroisSigmas2_.ll());
//            _g.drawLine(rapport_*(partie_+1),(int)-espMoinsTroisSigmas_.ll(),rapport_*(partie_+2),(int)-espMoinsTroisSigmas2_.ll());
            x_ = nx_;
            y1_ = ny1_;
            y2_ = ny2_;
        }
    }

    private void dessinerPointillesVertical(AbstractImage _g2) {
        _g2.setColor(GuiConstants.BLACK);
        int rapport2_=10;
        int rapport_=getWidth()/scores.size();
        int nombre_=getHeight()/2;
        int nombre2_=scores.size();
        for (int abscisse_ = IndexConstants.FIRST_INDEX; abscisse_<nombre2_; abscisse_++) {
            _g2.drawString(Long.toString(abscisse_+1L),(abscisse_+1)*rapport_,0);
            for(int ordonnee_=1;ordonnee_<nombre_;ordonnee_++) {
                _g2.drawLine((abscisse_+1)*rapport_,2*ordonnee_*rapport2_,(abscisse_+1)*rapport_,(2*ordonnee_+1)*rapport2_);
            }
            for(int ordonnee_=1;ordonnee_<nombre_;ordonnee_++) {
                _g2.drawLine((abscisse_+1)*rapport_,-2*ordonnee_*rapport2_,(abscisse_+1)*rapport_,-(2*ordonnee_+1)*rapport2_);
            }
        }
    }

    private void dessinerPointillesHorizontal(AbstractImage _g2) {
        _g2.setColor(GuiConstants.BLACK);
        int rapport2_=10;
        int nombre_=getHeight()/2;
        int nombre2_=getWidth()/2;
        _g2.drawString(Long.toString(0),0,0);
        for(int ordonnee_=1;ordonnee_<nombre_;ordonnee_++) {
            int res_ = ordonnee_ * rapport2_;
            _g2.drawString(Long.toString(res_),0,-res_);
            _g2.drawString(Long.toString(-res_),0, res_);
            for (int abscisse_ = IndexConstants.FIRST_INDEX; abscisse_<nombre2_; abscisse_++) {
                _g2.drawLine(2*abscisse_*rapport2_, res_,(2*abscisse_+1)*rapport2_, res_);
            }
            for (int abscisse_ = IndexConstants.FIRST_INDEX; abscisse_<nombre2_; abscisse_++) {
                _g2.drawLine(2*abscisse_*rapport2_,-res_,(2*abscisse_+1)*rapport2_,-res_);
            }
        }
    }
}
