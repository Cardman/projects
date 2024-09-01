package aiki.sml.trs;

import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;

public final class Trs24{
private Trs24(){}
static String tr(){
String f= MessagesDataBaseConstants.DEF_CIBLE_EFFET+"\t"+DataBase.MOVE_FORMULA+"\tcib_eff__{0}\tLa cible est sous l''effet de l''attaque {0}\n";
f+= MessagesDataBaseConstants.DEF_GENRES_EGAUX+"\t"+DataBase.MOVE_FORMULA+"\tgr_eg\tLes genres sont &eacute;gaux.\n";
f+= MessagesDataBaseConstants.DEF_PK_SAUVAGE_GENRE+"\t"+DataBase.ITEM_FORMULA+"\tpk_sauv_gr\tGenre du pokemon sauvage\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_GENRE+"\t"+DataBase.MOVE_FORMULA+"\tcbt_nom\tGenre du lanceur\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_PV_MAX+"\t"+DataBase.MOVE_FORMULA+"\tlanc_pv_max\tPoints de vie totaux du lanceur\n";
f+= MessagesDataBaseConstants.DEF_EXISTE_GENRE_ASSEXUE+"\t"+DataBase.MOVE_FORMULA+"\texist_gr_assex\tIl existe un genre assexu&eacute;.\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_EFFET+"\t"+DataBase.MOVE_FORMULA+"\tlanc_eff__{0}\tLe lanceur est sous l''effet de l''attaque {0}\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_STATUTS+"\t"+DataBase.MOVE_FORMULA+"\tcib_statuts\tstatuts de la cible\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_TAILLE+"\t"+DataBase.MOVE_FORMULA+"\tcib_taille\tTaille de la cible\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_OBJET+"\t"+DataBase.MOVE_FORMULA+"\tcib_obj\tObjet de la cible\n";
f+= MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_COMBATTANT_ENTRANT+"\t"+DataBase.TYPE_FORMULA+"\timmu_type_att_cbt__{0}\tLe combattant envoy&eacute; est immunis&eacute; aux attaques de type {0}\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL_TOUR+"\t"+DataBase.MOVE_FORMULA+"\tlanc_deg_rec_tr\tD&eacute;g&acirc;ts re&ccedil;us par le lanceur pendant le tour\n";
f+= MessagesDataBaseConstants.DEF_DEJA_CAPTURE+"\t"+DataBase.ITEM_FORMULA+"\tdej_cap\tPokemon d&eacute;j&agrave; captur&eacute;\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_TYPES+"\t"+DataBase.MOVE_FORMULA+"\tcib_t\ttypes de la cibles\n";
f+= MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_TYPES+"\t"+DataBase.MOVE_FORMULA+"\tcbt_env_type\tTypes du combattant envoy&eacute;\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_STATUTS+"\t"+DataBase.MOVE_FORMULA+"\tlanc_statuts\tstatuts du lanceur\n";
f+= MessagesDataBaseConstants.DEF_PAS_PP_ATTAQUE_CIBLE+"\t"+DataBase.MOVE_FORMULA+"\tpas_pp_cible\tLa cible n''a pas de point de pouvoir pour son attaque\n";
f+= MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_CIBLE+"\t"+DataBase.MOVE_FORMULA+"\tsom_boost_pos_cib\tSomme des boosts positifs de la cible\n";
f+= MessagesDataBaseConstants.DEF_ATTAQUE_NOM+"\t"+DataBase.MOVE_FORMULA+"\tatt_nom\tNom de l''attaque\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_BOOST+"\t"+DataBase.STATIS_FORMULA+"\tlanc_boost__{0}\t{0} en terme de boost du lanceur\n";
f+= MessagesDataBaseConstants.DEF_ATTACK+"\t"+DataBase.MOVE_FORMULA+"\tatt\tValeur de l''attaque du lanceur\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_PV_RESTANTS+"\t"+DataBase.MOVE_FORMULA+"\tcib_pv_rest\tPoints de vie restants de la cible\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_POSSEDE_STATUT_RELATION+"\t"+DataBase.STATUS_FORMULA+"\tcible_pseudo_st__{0}\tLa cible est alt&eacute;r&eacute;e du pseudo statut {0}\n";
f+= MessagesDataBaseConstants.DEF_ATTAQUE_TYPES+"\t"+DataBase.MOVE_FORMULA+"\tatt_types\tTypes de l''attaque\n";
f+= MessagesDataBaseConstants.DEF_FIGHTER_OBJET+"\t"+DataBase.ITEM_FORMULA+"\tst_obj\tObjet du possesseur\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_CLONE+"\t"+DataBase.MOVE_FORMULA+"\tlanc_clone\tPV du clone du lanceur\n";
f+= MessagesDataBaseConstants.DEF_POWER+"\t"+DataBase.MOVE_FORMULA+"\tpow\tValeur de la puissance de l''attaque du lanceur\n";
f+= MessagesDataBaseConstants.DEF_PUISSANCE_BASE+"\t"+DataBase.ITEM_FORMULA+"\tpuis_base\tPuissance de base de l''attaque du possesseur\n";
f+= MessagesDataBaseConstants.DEF_MASSE_MOYENNE_PK+"\t"+DataBase.ITEM_FORMULA+"\tmasse_moy_pk\tMasse moyenne des pokemon\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES_TYPES+"\t"+DataBase.MOVE_FORMULA+"\tlanc_att_types\tTypes des attaques du lanceur\n";
f+= MessagesDataBaseConstants.DEF_PK_UT_NIVEAU+"\t"+DataBase.ITEM_FORMULA+"\tpk_ut_niv\tNiveau du pokemon du joueur\n";
f+= MessagesDataBaseConstants.DEF_DEFENSE+"\t"+DataBase.MOVE_FORMULA+"\tdef\tValeur de la d&eacute;fense du lanceur\n";
f+= MessagesDataBaseConstants.DEF_PK_SAUVAGE_TYPES_BASE+"\t"+DataBase.ITEM_FORMULA+"\tpk_sauv_types\tTypes de base du pokemon sauvage\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_CAPACITE+"\t"+DataBase.MOVE_FORMULA+"\tlanc_capac\tCapacit&eacute; courant du lanceur\n";
f+= MessagesDataBaseConstants.DEF_FIGHTER_NOM+"\t"+DataBase.ITEM_FORMULA+"\tcbt_nom\tNom du possesseur\n";
f+= MessagesDataBaseConstants.DEF_NB_UTILISATION_CONSECUTIF+"\t"+DataBase.ITEM_FORMULA+"\tnb_ut_cons\tNombre d''utilisations cons&eacute;cutives de l''attaque.\n";
f+= MessagesDataBaseConstants.DEF_FIGHTER_DER_JOUE+"\t"+DataBase.ITEM_FORMULA+"\tcbt_der_joue\tLe possesseur a jou&eacute; en dernier\n";
f+= MessagesDataBaseConstants.DEF_TEMPS_TOUR+"\t"+DataBase.ITEM_FORMULA+"\ttps_tr\tTemps de tour\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES+"\t"+DataBase.MOVE_FORMULA+"\tlanc_att\tAttaques du lanceur\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_BONHEUR+"\t"+DataBase.MOVE_FORMULA+"\tlanc_bonh\tBonheur du lanceur\n";
f+= MessagesDataBaseConstants.DEF_PK_SAUVAGE_PIERRES_EVOS+"\t"+DataBase.ITEM_FORMULA+"\tpk_sauv_ev\tPierres &eacute;volutives du pokemon sauvage\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_BOOST+"\t"+DataBase.STATIS_FORMULA+"\tcib_boost__{0}\t{0} en terme de boost de la cible\n";
f+= MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_ARRIERE+"\t"+DataBase.MOVE_FORMULA+"\tpas_part_arr\tPas de partenaire en arri&egrave;re\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_DISPARAIT+"\t"+DataBase.MOVE_FORMULA+"\tlanc_disp\tLe lanceur dispara&icirc;t pendant le tour\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_JOUE+"\t"+DataBase.MOVE_FORMULA+"\tlanc_joue\tLe lanceur a jou&eacute;\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL+"\t"+DataBase.MOVE_FORMULA+"\tlanc_deg_recu_tot\tD&eacute;g&acirc;ts totaux re&ccedil;s par le lanceur\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_NB_UTILISATION+"\t"+DataBase.MOVE_FORMULA+"\tlanc_nb_ut__{0}\tNombre d''utilisation de l''attaque {0} du lanceur\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_STATIS+"\t"+DataBase.STATIS_FORMULA+"\tcib_statis__{0}\t{0} en terme de base de statistique de la cible\n";
f+= MessagesDataBaseConstants.DEF_NB_UTILI_ATT_EQ_TOUR+"\t"+DataBase.MOVE_FORMULA+"\tnb_ut_att_eq_tr__{0}\tNombre d''utilisation de l''attaque {0} par l''&eacute;quipe\n";
f+= MessagesDataBaseConstants.DEF_NIVEAU+"\tlevel\tn\tNiveau du pokemon\n";
f+= MessagesDataBaseConstants.DEF_BOOST+"\tboost\tb\tBoost du pokemon\n";
//f+=DataBase.DEF_EV+"\tev\tev\tLa valeur ev d''une statistique du pokemon\n";
//f+=DataBase.DEF_IV+"\tiv\tiv\tLa valeur iv d''une statistique du pokemon\n";
//f+=DataBase.DEF_BASE+"\tbase\tbase\tLa valeur de base d''une statistique du pokemon\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_TAILLE+"\t"+DataBase.MOVE_FORMULA+"\tlanc_taille\ttaille du lanceur\n";
f+= MessagesDataBaseConstants.DEF_LIEU_COMBAT+"\t"+DataBase.ITEM_FORMULA+"\tl_cbt\tEnvironnement de combat\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_GENRE+"\t"+DataBase.MOVE_FORMULA+"\tcib_gr\tGenre de la cible\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_ATTAQUE_CHOISIE+"\t"+DataBase.MOVE_FORMULA+"\tcib_att_choisie\tAttaque choisie de la cible\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_NB_UTILISATION+"\t"+DataBase.MOVE_FORMULA+"\tcib_nb_ut__{0}\tNombre d''utilisation de l''attaque {0} de la cible\n";
f+= MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_CIBLE+"\t"+DataBase.TYPE_FORMULA+"\timmu_type_att_cib__{0}\tLa cible est immunis&eacute;e aux attaques de type {0}\n";
f+= MessagesDataBaseConstants.DEF_CLIMATS+"\t"+DataBase.MOVE_FORMULA+"\tclimats\tClimats actifs\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_CLONE+"\t"+DataBase.MOVE_FORMULA+"\tcib_clone\tPV du clone de la cible\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_ATTAQUES+"\t"+DataBase.MOVE_FORMULA+"\tcib_att\tAttaques de la cible\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_PV_MAX+"\t"+DataBase.MOVE_FORMULA+"\tcib_pv_max\tPoints de vie totaux de la cible\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_TYPES+"\t"+DataBase.MOVE_FORMULA+"\tlanc_t\ttypes du lanceurs\n";
f+= MessagesDataBaseConstants.DEF_FIGHTER_TYPES+"\t"+DataBase.STATUS_FORMULA+"\tcbt_types\tTypes du combattant\n";
f+= MessagesDataBaseConstants.DEF_PAS_UTILIS_ATTAQUE_CIBLE+"\t"+DataBase.MOVE_FORMULA+"\tpas_ut_cib\tAucune attaque utilis&eacute;e par la cible\n";
f+= MessagesDataBaseConstants.DEF_ATTAQUE_CATEGORIE+"\t"+DataBase.MOVE_FORMULA+"\tatt_cat\tCat&eacute;gorie de l''attaque du possesseur\n";
f+= MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_LANCEUR+"\t"+DataBase.MOVE_FORMULA+"\tnb_ko_lanceur\tNombre de pokemon ko dans l''&eacute;quipe du lanceur\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_CAPACITE+"\t"+DataBase.MOVE_FORMULA+"\tcib_capac\tCapacit&eacute; courant de la cible\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOTAL_TOUR+"\t"+DataBase.MOVE_FORMULA+"\tcib_deg_rec_tr\tD&eacute;g&acirc;ts re&ccedil;us par la cible pendant le tour\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_PP+"\t"+DataBase.MOVE_FORMULA+"\tlanc_pp__{0}\tPoints de pouvoir restants de l''attaque {0} du lanceur\n";
f+= MessagesDataBaseConstants.DEF_PAS_TOUR_TERRAIN+"\t"+DataBase.MOVE_FORMULA+"\tpas_tr\tVient d''entrer sur le terrain.\n";
f+= MessagesDataBaseConstants.DEF_FIGHTER_CLONE+"\t"+DataBase.STATUS_FORMULA+"\tcbt_clone\tPV du clone du combattant\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_NIVEAU+"\t"+DataBase.MOVE_FORMULA+"\tcib_niv\tNiveau de la cible\n";
f+= MessagesDataBaseConstants.DEF_FIGHTER_STATUTS+"\t"+DataBase.ITEM_FORMULA+"\tst_cbt\tStatuts du possesseur\n";
f+= MessagesDataBaseConstants.DEF_PK_SAUVAGE_MASSE+"\t"+DataBase.ITEM_FORMULA+"\tpk_sauv_mas\tMasse du pokemon sauvage\n";
f+= MessagesDataBaseConstants.DEF_TYPES_ATTAQUES_RES_VIDE+"\t"+DataBase.MOVE_FORMULA+"\ttypes_att_res_vide\tPas de type r&eacute;sistant &agrave; la derni&egrave;re attaque subie.\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_STATIS+"\t"+DataBase.STATIS_FORMULA+"\tlanc_statis__{0}\t{0} en terme de base de statistique du lanceur\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_DISPARAIT+"\t"+DataBase.MOVE_FORMULA+"\tcib_disp\tLa cible dispara&icirc;t pendant le tour\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOUR+"\t"+DataBase.CAT_FORMULA+"\tlanc_deg_rec_tr__{0}\tD&eacute;g&acirc;ts subis par le lanceur par des attaques de cat&eacute;gorie {0}.\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_MASSE+"\t"+DataBase.MOVE_FORMULA+"\tlanc_masse\tMasse du lanceur\n";
f+= MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT+"\t"+DataBase.TYPE_FORMULA+"\teff_base_cbt_envoye__{0}\tCoefficient d''efficacit&eacute; du type {0} sur les types du combattant entrant\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_NOM+"\t"+DataBase.MOVE_FORMULA+"\tcbt_nom\tNom du lanceur\n";
f+= MessagesDataBaseConstants.DEF_PK_UT_GENRE+"\t"+DataBase.ITEM_FORMULA+"\tpk_ut_gr\tGenre du pokemon du joueur\n";
f+= MessagesDataBaseConstants.DEF_FIGHTER_NB_UTILISATION+"\t"+DataBase.MOVE_FORMULA+"\tcbt_nb_ut__{0}\tNombre d''utilisation de l''attaque {0} du combattant\n";
f+= MessagesDataBaseConstants.DEF_NB_TOUR+"\t"+DataBase.MOVE_FORMULA+"\tnb_tr__{0}\tnombre de tour de l''attaque {0}\n";
f+= MessagesDataBaseConstants.DEF_PAS_ATTAQUE_INVOC+"\t"+DataBase.MOVE_FORMULA+"\tpass_att_invok\tAucune attaque invoquable\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_JOUE+"\t"+DataBase.MOVE_FORMULA+"\tcib_joue\tLa cible a jou&eacute;\n";
f+= MessagesDataBaseConstants.DEF_CIBLE_MASSE+"\t"+DataBase.MOVE_FORMULA+"\tcib_masse\tMasse de la cible\n";
f+= MessagesDataBaseConstants.DEF_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION+"\t"+DataBase.MOVE_FORMULA+"\teq_adv_cbt_entr_nb_ut__{0}\tNombre d''utilisation de l''attaque {0} par les membres de l''&eacute;quipe adverse au combattant entrant.\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_OBJET+"\t"+DataBase.MOVE_FORMULA+"\tlanc_obj\tObjet du lanceur\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUE_CHOISIE+"\t"+DataBase.MOVE_FORMULA+"\tlanc_att_choisie\tAttaque choisie du lanceur\n";
f+= MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE+"\t"+DataBase.ITEM_FORMULA+"\tpk_sauv_vit\tVitesse de base du pokemon sauvage\n";
f+= MessagesDataBaseConstants.DEF_PAS_ATTAQUES_COPIABLES+"\t"+DataBase.MOVE_FORMULA+"\tpas_att_cop\tAucune attaque copiable\n";
f+= MessagesDataBaseConstants.DEF_NB_COMBATTANTS_TERRAIN+"\t"+DataBase.MOVE_FORMULA+"\tnb_cbts\tNombre de commbattants sur le terrain\n";
f+= MessagesDataBaseConstants.DEF_PK_SAUVAGE_NIVEAU+"\t"+DataBase.ITEM_FORMULA+"\tpk_sauv_niv\tNiveau du pokemon sauvage\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_PV_RESTANTS+"\t"+DataBase.MOVE_FORMULA+"\tlanc_pv_rest\tPoints de vie restants du lanceur\n";
f+= MessagesDataBaseConstants.DEF_LANCEUR_NIVEAU+"\t"+DataBase.MOVE_FORMULA+"\tlanc_niv\tNiveau du lanceur\n";
f+= MessagesDataBaseConstants.DEF_COEFF_EFF+"\t"+DataBase.ITEM_FORMULA+"\tcoeff_eff\tCoefficent d''efficacit&eacute; de l''attaque du possesseur\n";
//f+=DataBase.DEF_LANCEUR_POSSEDE_STATUT_RELATION+"\t"+DataBase.STATUS_FORMULA+"\tcible_pseudo_st__{0}\tLe lanceur est alt&eacute;r&eacute;e du pseudo statut {0}\n";
f+= MessagesDataBaseConstants.DEF_NB_TOUR_GLOBAL+"\t"+DataBase.MOVE_FORMULA+"\tnb_tr_gl__{0}\tNombre de tour de l''attaque {0}\n";
f+= MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_LANCEUR+"\t"+DataBase.MOVE_FORMULA+"\tsom_boost_pos_lanc\tSomme des boosts positifs du lanceur\n";
f+= MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_TERRAIN+"\t"+DataBase.MOVE_FORMULA+"\tpas_part\tPas de partenaire sur le terrain\n";
f+= MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_CLONE+"\t"+DataBase.MOVE_FORMULA+"\tcbt_env_clone\tPoints de vie restants du clone du combattant envoy&eacute;\n";
f+= MessagesDataBaseConstants.DEF_AUCUN_BOOST_POSSIBLE+"\t"+DataBase.MOVE_FORMULA+"\tpas_boost\tAucun boost n''est possible pour la cible\n";
f+= MessagesDataBaseConstants.DEF_BASE_CAPT_PK+"\t"+DataBase.MOVE_FORMULA+"\tbase_capt\tTaux de capture de base du pokemon sauvage\n";
f+= MessagesDataBaseConstants.DEF_RATE_BALL_STATUS+"\t"+DataBase.MOVE_FORMULA+"\ttx_ball_statut\tProduit entre le taux de capture de la balle et les taux des statuts du pokemon sauvage.\n";
f+= MessagesDataBaseConstants.DEF_FOE_PK_MAX_HP+"\t"+DataBase.MOVE_FORMULA+"\tpv_max\tPoints de vie totaux du pokemon sauvage.\n";
f+= MessagesDataBaseConstants.DEF_FOE_PK_REMOTE_HP+"\t"+DataBase.MOVE_FORMULA+"\tpv_restants\tPoints de vie restants du pokemon sauvage.\n";
f+= MessagesDataBaseConstants.DEF_PK_UT_VITESSE+"\t"+DataBase.MOVE_FORMULA+"\tpk_joueur_vit\tVitesse du premier pokemon du joueur.\n";
f+= MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE+"\t"+DataBase.MOVE_FORMULA+"\tpk_sauvage_vit\tVitesse du pokemon sauvage.\n";
f+= MessagesDataBaseConstants.DEF_NB_FLEES+"\t"+DataBase.MOVE_FORMULA+"\tnb_fuites\tNombre de tentatives de fuite.\n";
f+= MessagesDataBaseConstants.DEF_LEVEL_WINNER+"\t"+DataBase.MOVE_FORMULA+"\tniv_p\tNiveau du combattant KO\n";
f+= MessagesDataBaseConstants.DEF_LEVEL_LOOSER+"\t"+DataBase.MOVE_FORMULA+"\tniv_g\tNiveau du pokemon du joueur qui gagne des points\n";
return f;
}
}
