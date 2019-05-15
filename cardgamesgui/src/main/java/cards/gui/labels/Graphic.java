package cards.gui.labels;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import code.gui.PaintableLabel;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
/**
    */

public class Graphic extends PaintableLabel {
    private CustList<Numbers<Long>> scores;
    private Numbers<Long> sommes;
    private EqList<Rate> sigmas;
    private CustList<Color> couleurs;
    public Graphic(CustList<Numbers<Long>> _pscores,Numbers<Long> _psommes,EqList<Rate> _psigmas,CustList<Color> _pcouleurs) {
        scores=_pscores;
        sommes=_psommes;
        sigmas=_psigmas;
        couleurs=_pcouleurs;
    }
    @Override
    public void paintComponent(Graphics _g) {
        Graphics2D g2_=(Graphics2D)_g;
        int rapport_=getWidth()/scores.size();
        g2_.setColor(Color.WHITE);
        g2_.fillRect(0,0,getWidth(),getHeight());
        g2_.translate(0,getHeight()/2);
        dessinerPointilles(g2_,true);
        dessinerPointilles(g2_,false);
        g2_.setColor(Color.BLACK);
        g2_.drawLine(0,0,getWidth(),0);
        int nombreJoueurs_=scores.last().size();
        double esperance_;
        int nbDealsMinusOne_ = scores.size();
        nbDealsMinusOne_ --;
        for(byte joueur_=CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
            g2_.setColor(couleurs.get(joueur_));
            esperance_=sommes.first()/(double)nombreJoueurs_;
            g2_.drawLine(0,0,rapport_,(int)(esperance_-scores.first().get(joueur_)));
            for(int partie_=CustList.FIRST_INDEX;partie_<nbDealsMinusOne_;partie_++) {
                esperance_=sommes.get(partie_)/(double)nombreJoueurs_;
                double esperance2_=sommes.get(partie_+1)/(double)nombreJoueurs_;
                g2_.drawLine(rapport_*(partie_+1),(int)(esperance_-scores.get(partie_).get(joueur_)),rapport_*(partie_+2),(int)(esperance2_-scores.get(partie_+1).get(joueur_)));
            }
        }
        g2_.setColor(Color.GRAY);
        Rate espPlusTroisSigmas_=sigmas.first();
        Rate espMoinsTroisSigmas_=sigmas.first().opposNb();
        g2_.drawLine(0,0,rapport_,(int)-espPlusTroisSigmas_.ll());
        g2_.drawLine(0,0,rapport_,(int)-espMoinsTroisSigmas_.ll());
        for(int partie_=CustList.FIRST_INDEX;partie_<nbDealsMinusOne_;partie_++) {
            espPlusTroisSigmas_=sigmas.get(partie_);
            Rate espPlusTroisSigmas2_=sigmas.get(partie_+1);
            espMoinsTroisSigmas_=sigmas.get(partie_).opposNb();
            Rate espMoinsTroisSigmas2_=sigmas.get(partie_+1).opposNb();
            g2_.drawLine(rapport_*(partie_+1),(int)-espPlusTroisSigmas_.ll(),rapport_*(partie_+2),(int)-espPlusTroisSigmas2_.ll());
            g2_.drawLine(rapport_*(partie_+1),(int)-espMoinsTroisSigmas_.ll(),rapport_*(partie_+2),(int)-espMoinsTroisSigmas2_.ll());
        }
    }
    private void dessinerPointilles(Graphics2D _g2,boolean _horizontal) {
        _g2.setColor(Color.BLACK);
        int rapport2_=10;
        if(_horizontal) {
            int nombre_=getHeight()/2;
            int nombre2_=getWidth()/2;
            _g2.drawString(Integer.toString(0),0,0);
            for(int ordonnee_=1;ordonnee_<nombre_;ordonnee_++) {
                _g2.drawString(Integer.toString(ordonnee_*rapport2_),0,-ordonnee_*rapport2_);
                _g2.drawString(Integer.toString(-ordonnee_*rapport2_),0,ordonnee_*rapport2_);
                for (int abscisse_ = CustList.FIRST_INDEX;abscisse_<nombre2_;abscisse_++) {
                    _g2.drawLine(2*abscisse_*rapport2_,ordonnee_*rapport2_,(2*abscisse_+1)*rapport2_,ordonnee_*rapport2_);
                }
                for (int abscisse_ = CustList.FIRST_INDEX;abscisse_<nombre2_;abscisse_++) {
                    _g2.drawLine(2*abscisse_*rapport2_,-ordonnee_*rapport2_,(2*abscisse_+1)*rapport2_,-ordonnee_*rapport2_);
                }
            }
        } else {
            int rapport_=getWidth()/scores.size();
            int nombre_=getHeight()/2;
            int nombre2_=scores.size();
            for (int abscisse_ = CustList.FIRST_INDEX;abscisse_<nombre2_;abscisse_++) {
                _g2.drawString(Integer.toString(abscisse_+1),(abscisse_+1)*rapport_,0);
                for(int ordonnee_=1;ordonnee_<nombre_;ordonnee_++) {
                    _g2.drawLine((abscisse_+1)*rapport_,2*ordonnee_*rapport2_,(abscisse_+1)*rapport_,(2*ordonnee_+1)*rapport2_);
                }
                for(int ordonnee_=1;ordonnee_<nombre_;ordonnee_++) {
                    _g2.drawLine((abscisse_+1)*rapport_,-2*ordonnee_*rapport2_,(abscisse_+1)*rapport_,-(2*ordonnee_+1)*rapport2_);
                }
            }
        }
    }
}
