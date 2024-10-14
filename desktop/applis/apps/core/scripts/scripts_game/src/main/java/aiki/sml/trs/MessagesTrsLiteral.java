package aiki.sml.trs;
import aiki.db.*;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsLiteral extends CstIgame{
private MessagesTrsLiteral(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(115);
e.add(MessagesDataBaseConstants.DEF_CIBLE_EFFET,DataBase.MOVE_FORMULA+"\ttarg_eff__{0}\tThe target is under the effect of the move {0}");
e.add(MessagesDataBaseConstants.DEF_GENRES_EGAUX,DataBase.MOVE_FORMULA+"\tgr_eg\tThe genders equal to themselves.");
e.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_GENRE,DataBase.ITEM_FORMULA+"\twild_pk_gr\tGender of the wild pokemon");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_GENRE,DataBase.MOVE_FORMULA+"\tuser_gr\tGenre of the user");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_PV_MAX,DataBase.MOVE_FORMULA+"\tusr_max_hp\tFull health points of the user");
e.add(MessagesDataBaseConstants.DEF_EXISTE_GENRE_ASSEXUE,DataBase.MOVE_FORMULA+"\texist_no_gr\tIt exists a no-sexual gender.");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_EFFET,DataBase.MOVE_FORMULA+"\tusr_eff__{0}\tThe user is under the effect of the move {0}");
e.add(MessagesDataBaseConstants.DEF_CIBLE_STATUTS,DataBase.MOVE_FORMULA+"\ttarg_status\tstatus of the target");
e.add(MessagesDataBaseConstants.DEF_CIBLE_TAILLE,DataBase.MOVE_FORMULA+"\ttarg_height\tHeight of the target");
e.add(MessagesDataBaseConstants.DEF_CIBLE_OBJET,DataBase.MOVE_FORMULA+"\ttarg_it\tItem of the target");
e.add(MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_COMBATTANT_ENTRANT,DataBase.TYPE_FORMULA+"\timmu_move_type_fighter__{0}\tThe sent fighter is protected against the moves of type {0}");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL_TOUR,DataBase.MOVE_FORMULA+"\tusr_suffered_damg_rd\tSuffered damage against the user while the round");
e.add(MessagesDataBaseConstants.DEF_DEJA_CAPTURE,DataBase.ITEM_FORMULA+"\talready_caught\tAlready caught pokemon");
e.add(MessagesDataBaseConstants.DEF_CIBLE_TYPES,DataBase.MOVE_FORMULA+"\ttarg_t\ttypes of the targets");
e.add(MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_TYPES,DataBase.MOVE_FORMULA+"\tsent_fighter_type\tTypes of the sent fighter");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_STATUTS,DataBase.MOVE_FORMULA+"\tusr_status\tstatus of the user");
e.add(MessagesDataBaseConstants.DEF_PAS_PP_ATTAQUE_CIBLE,DataBase.MOVE_FORMULA+"\tno_pp_target\tThe target has no power point for the move that the target owns");
e.add(MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_CIBLE,DataBase.MOVE_FORMULA+"\tsum_boost_pos_tar\tSum of the positive boosts of the target");
e.add(MessagesDataBaseConstants.DEF_ATTAQUE_NOM,DataBase.MOVE_FORMULA+"\tmove_name\tName of the move");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_BOOST,DataBase.STATIS_FORMULA+"\tusr_boost__{0}\t{0} of terms of boost of the user");
e.add(MessagesDataBaseConstants.DEF_ATTACK,DataBase.MOVE_FORMULA+"\tatt\tValue of the move of the user");
e.add(MessagesDataBaseConstants.DEF_CIBLE_PV_RESTANTS,DataBase.MOVE_FORMULA+"\ttarg_left_hp\tRemaining health points of the target");
e.add(MessagesDataBaseConstants.DEF_CIBLE_POSSEDE_STATUT_RELATION,DataBase.STATUS_FORMULA+"\ttarget_pseudo_st__{0}\tThe target is victim of the pseudo status {0}");
e.add(MessagesDataBaseConstants.DEF_ATTAQUE_TYPES,DataBase.MOVE_FORMULA+"\tmove_types\tTypes of the move");
e.add(MessagesDataBaseConstants.DEF_FIGHTER_OBJET,DataBase.ITEM_FORMULA+"\tfighter_it\tItem of the owner");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_CLONE,DataBase.MOVE_FORMULA+"\tusr_clone\tPV of the clone of the user");
e.add(MessagesDataBaseConstants.DEF_POWER,DataBase.MOVE_FORMULA+"\tpow\tValue of the power of the move of the user");
e.add(MessagesDataBaseConstants.DEF_PUISSANCE_BASE,DataBase.ITEM_FORMULA+"\tpower_base\tPower of base of the move of the owner");
e.add(MessagesDataBaseConstants.DEF_MASSE_MOYENNE_PK,DataBase.ITEM_FORMULA+"\tavg_pk_weight\tAverage weight of the pokemon");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES_TYPES,DataBase.MOVE_FORMULA+"\tusr_moves_types\tTypes of the moves of the user");
e.add(MessagesDataBaseConstants.DEF_PK_UT_NIVEAU,DataBase.ITEM_FORMULA+"\tpk_player_lev\tLevel of the pokemon of the player");
e.add(MessagesDataBaseConstants.DEF_DEFENSE,DataBase.MOVE_FORMULA+"\tdef\tValue of the defence of the user");
e.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_TYPES_BASE,DataBase.ITEM_FORMULA+"\twild_pk_types\tTypes de base of the wild pokemon");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_CAPACITE,DataBase.MOVE_FORMULA+"\tusr_ab\tCurrent ability of the user");
e.add(MessagesDataBaseConstants.DEF_FIGHTER_NOM,DataBase.ITEM_FORMULA+"\tfighter_nom\tName of the owner");
e.add(MessagesDataBaseConstants.DEF_NB_UTILISATION_CONSECUTIF,DataBase.ITEM_FORMULA+"\tnb_uses_cons\tNumber of consecutive uses of the move.");
e.add(MessagesDataBaseConstants.DEF_FIGHTER_DER_JOUE,DataBase.ITEM_FORMULA+"\tfighter_last_acted\tThe owner has played the last one");
e.add(MessagesDataBaseConstants.DEF_TEMPS_TOUR,DataBase.ITEM_FORMULA+"\ttps_rd\tTime of round");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES,DataBase.MOVE_FORMULA+"\tusr_att\tMoves of the user");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_BONHEUR,DataBase.MOVE_FORMULA+"\tusr_hap\tHappiness of the user");
e.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_PIERRES_EVOS,DataBase.ITEM_FORMULA+"\twild_pk_ev\tEvolution stones of the wild pokemon");
e.add(MessagesDataBaseConstants.DEF_CIBLE_BOOST,DataBase.STATIS_FORMULA+"\ttarg_boost__{0}\t{0} in terms of boost of the target");
e.add(MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_ARRIERE,DataBase.MOVE_FORMULA+"\tno_back_part\tNo back partner");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_DISPARAIT,DataBase.MOVE_FORMULA+"\tusr_disap\tThe user disappears while the round");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_JOUE,DataBase.MOVE_FORMULA+"\tusr_acted\tThe user has acted");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL,DataBase.MOVE_FORMULA+"\tusr_amt_suff_damg\tFull suffered damage against the user");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_NB_UTILISATION,DataBase.MOVE_FORMULA+"\tusr_nb_uses__{0}\tNumber of uses of the move {0} of the user");
e.add(MessagesDataBaseConstants.DEF_CIBLE_STATIS,DataBase.STATIS_FORMULA+"\ttarg_statis__{0}\t{0} in terms of base of statistic of the target");
e.add(MessagesDataBaseConstants.DEF_NB_UTILI_ATT_EQ_TOUR,DataBase.MOVE_FORMULA+"\tnb_uses_team_move_rd__{0}\tNumber of uses of the move {0} by the team");
e.add(MessagesDataBaseConstants.DEF_NIVEAU,"level\tl\tThe level of the Pokemon");
e.add(MessagesDataBaseConstants.DEF_BOOST,"boost\tb\tThe boost of the Pokemon");
//e.add(DataBase.DEF_EV,"ev\tev\tThe ev of a statistic of the pokemon");
//e.add(DataBase.DEF_IV,"iv\tiv\tThe iv of a statistic of the pokemon");
//e.add(DataBase.DEF_BASE,"base\tbase\tThe base value of a statistic of the pokemon");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_TAILLE,DataBase.MOVE_FORMULA+"\tusr_height\theight of the user");
e.add(MessagesDataBaseConstants.DEF_LIEU_COMBAT,DataBase.ITEM_FORMULA+"\tfight_env\tFight environment");
e.add(MessagesDataBaseConstants.DEF_CIBLE_GENRE,DataBase.MOVE_FORMULA+"\ttarg_gr\tGenre of the target");
e.add(MessagesDataBaseConstants.DEF_CIBLE_ATTAQUE_CHOISIE,DataBase.MOVE_FORMULA+"\ttarg_chosen_move\tChosen move of the target");
e.add(MessagesDataBaseConstants.DEF_CIBLE_NB_UTILISATION,DataBase.MOVE_FORMULA+"\ttarg_nb_uses__{0}\tNumber of uses of the move {0} of the target");
e.add(MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_CIBLE,DataBase.TYPE_FORMULA+"\timmu_move_type_targ__{0}\tThe target is protected against the moves of type {0}");
e.add(MessagesDataBaseConstants.DEF_CLIMATS,DataBase.MOVE_FORMULA+"\tweathers\tEnabled weathers");
e.add(MessagesDataBaseConstants.DEF_CIBLE_CLONE,DataBase.MOVE_FORMULA+"\ttarg_clone\tPV of the clone of the target");
e.add(MessagesDataBaseConstants.DEF_CIBLE_ATTAQUES,DataBase.MOVE_FORMULA+"\ttarg_moves\tMoves of the target");
e.add(MessagesDataBaseConstants.DEF_CIBLE_PV_MAX,DataBase.MOVE_FORMULA+"\ttarg_max_hp\tFull health points of the target");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_TYPES,DataBase.MOVE_FORMULA+"\tusr_t\ttypes of the user");
e.add(MessagesDataBaseConstants.DEF_FIGHTER_TYPES,DataBase.STATUS_FORMULA+"\tfighter_types\tTypes of the fighter");
e.add(MessagesDataBaseConstants.DEF_PAS_UTILIS_ATTAQUE_CIBLE,DataBase.MOVE_FORMULA+"\tno_use_tar\tNo used move by the target");
e.add(MessagesDataBaseConstants.DEF_ATTAQUE_CATEGORIE,DataBase.MOVE_FORMULA+"\tmove_cat\tCategory of the move of the owner");
e.add(MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_LANCEUR,DataBase.MOVE_FORMULA+"\tnb_ko_user\tNumber of ko pokemon of the team of the user");
e.add(MessagesDataBaseConstants.DEF_CIBLE_CAPACITE,DataBase.MOVE_FORMULA+"\ttarg_ab\tCurrent ability of the target");
e.add(MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOTAL_TOUR,DataBase.MOVE_FORMULA+"\ttarg_suffered_damg_rd\tSuffered damage against the target while the round");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_PP,DataBase.MOVE_FORMULA+"\tusr_pp__{0}\tRemaining power points of the move {0} of the user");
e.add(MessagesDataBaseConstants.DEF_PAS_TOUR_TERRAIN,DataBase.MOVE_FORMULA+"\tno_rd\tHas just been sent on the ground.");
e.add(MessagesDataBaseConstants.DEF_FIGHTER_CLONE,DataBase.STATUS_FORMULA+"\tfighter_clone\tPV of the clone of the fighter");
e.add(MessagesDataBaseConstants.DEF_CIBLE_NIVEAU,DataBase.MOVE_FORMULA+"\ttarg_lev\tLevel of the target");
e.add(MessagesDataBaseConstants.DEF_FIGHTER_STATUTS,DataBase.ITEM_FORMULA+"\tfighter_st\tStatus of the owner");
e.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_MASSE,DataBase.ITEM_FORMULA+"\twild_pk_wt\tWeight of wild pokemon");
e.add(MessagesDataBaseConstants.DEF_TYPES_ATTAQUES_RES_VIDE,DataBase.MOVE_FORMULA+"\tres_types_move_empty\tNo resisting type against the last suffered move.");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_STATIS,DataBase.STATIS_FORMULA+"\tusr_statis__{0}\t{0} in terms of base of statistic of the user");
e.add(MessagesDataBaseConstants.DEF_CIBLE_DISPARAIT,DataBase.MOVE_FORMULA+"\ttarg_disap\tThe target disappears while the round");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOUR,DataBase.CAT_FORMULA+"\tusr_suffered_damg_rd__{0}\tSuffered damage against the user by moves of category {0}.");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_MASSE,DataBase.MOVE_FORMULA+"\tusr_weight\tWeight of the user");
e.add(MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT,DataBase.TYPE_FORMULA+"\teff_base_sent_fighter__{0}\tRate of efficiency of the type {0} against the types of the sent fighter");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_NOM,DataBase.MOVE_FORMULA+"\tuser_name\tName of the user");
e.add(MessagesDataBaseConstants.DEF_PK_UT_GENRE,DataBase.ITEM_FORMULA+"\tpk_player_gr\tGender of the pokemon of the player");
e.add(MessagesDataBaseConstants.DEF_FIGHTER_NB_UTILISATION,DataBase.MOVE_FORMULA+"\tcbt_nb_uses__{0}\tNumber of uses of the move {0} of the fighter");
e.add(MessagesDataBaseConstants.DEF_NB_TOUR,DataBase.MOVE_FORMULA+"\tnb_rd__{0}\tnumber of round of the move {0}");
e.add(MessagesDataBaseConstants.DEF_PAS_ATTAQUE_INVOC,DataBase.MOVE_FORMULA+"\tno_invok_move\tNo invokable move");
e.add(MessagesDataBaseConstants.DEF_CIBLE_JOUE,DataBase.MOVE_FORMULA+"\ttarg_acted\tThe target has acted");
e.add(MessagesDataBaseConstants.DEF_CIBLE_MASSE,DataBase.MOVE_FORMULA+"\ttarg_weight\tWeight of the target");
e.add(MessagesDataBaseConstants.DEF_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION,DataBase.MOVE_FORMULA+"\tsent_foe_team_nb_uses__{0}\tNumber of uses of the move {0} by the members of the foe team of the sent fighter.");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_OBJET,DataBase.MOVE_FORMULA+"\tusr_obj\tItem of the user");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUE_CHOISIE,DataBase.MOVE_FORMULA+"\tusr_chosen_move\tChosen move of the user");
e.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE,DataBase.ITEM_FORMULA+"\twild_pk_speed\tBase speed of the wild pokemon");
e.add(MessagesDataBaseConstants.DEF_PAS_ATTAQUES_COPIABLES,DataBase.MOVE_FORMULA+"\tno_copy_moves\tNo move can be copied");
e.add(MessagesDataBaseConstants.DEF_NB_COMBATTANTS_TERRAIN,DataBase.MOVE_FORMULA+"\tnb_fighters\tNumber of fighters on the ground");
e.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_NIVEAU,DataBase.ITEM_FORMULA+"\twild_pk_lev\tLevel of the wild pokemon");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_PV_RESTANTS,DataBase.MOVE_FORMULA+"\tusr_left_hp\tRemaining points de vie of the user");
e.add(MessagesDataBaseConstants.DEF_LANCEUR_NIVEAU,DataBase.MOVE_FORMULA+"\tusr_lev\tLevel of the user");
e.add(MessagesDataBaseConstants.DEF_COEFF_EFF,DataBase.ITEM_FORMULA+"\trate_eff\tRate of efficiency of the move of the owner");
//e.add(DataBase.DEF_LANCEUR_POSSEDE_STATUT_RELATION,DataBase.STATUS_FORMULA+"\ttarget_pseudo_st__{0}\tThe user suffers of the pseudo status {0}");
e.add(MessagesDataBaseConstants.DEF_NB_TOUR_GLOBAL,DataBase.MOVE_FORMULA+"\tnb_gl_move__{0}\tNumber of tour of the move {0}");
e.add(MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_LANCEUR,DataBase.MOVE_FORMULA+"\tsum_boost_pos_user\tSum of the positive boosts of the user");
e.add(MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_TERRAIN,DataBase.MOVE_FORMULA+"\tno_part\tNo partner on the ground");
e.add(MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_CLONE,DataBase.MOVE_FORMULA+"\tsent_fighter_clone\tRemaining health points of the clone of the sent fighter");
e.add(MessagesDataBaseConstants.DEF_AUCUN_BOOST_POSSIBLE,DataBase.MOVE_FORMULA+"\tno_boost\tNo boost is possible pour the target");
e.add(MessagesDataBaseConstants.DEF_BASE_CAPT_PK,DataBase.MOVE_FORMULA+"\tcatch_base\tBase catching rate of the wild pokemon");
e.add(MessagesDataBaseConstants.DEF_RATE_BALL_STATUS,DataBase.MOVE_FORMULA+"\tball_status_rate\tProduct between catching rate of the ball and rates of the status of the wild pokemon.");
e.add(MessagesDataBaseConstants.DEF_FOE_PK_MAX_HP,DataBase.MOVE_FORMULA+"\tfull_hp\tFull health points of the wild pokemon.");
e.add(MessagesDataBaseConstants.DEF_FOE_PK_REMOTE_HP,DataBase.MOVE_FORMULA+"\trem_hp\tRemaining health points of the wild pokemon.");
e.add(MessagesDataBaseConstants.DEF_PK_UT_VITESSE,DataBase.MOVE_FORMULA+"\tpk_pl_speed\tSpeed of the first pokemon of the player.");
e.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE,DataBase.MOVE_FORMULA+"\tpk_foe_speed\tSpeed of the wild pokemon.");
e.add(MessagesDataBaseConstants.DEF_NB_FLEES,DataBase.MOVE_FORMULA+"\tnb_flees\tNumber of attempts of flee.");
e.add(MessagesDataBaseConstants.DEF_LEVEL_WINNER,DataBase.MOVE_FORMULA+"\tlvl_l\tLevel of the KO fighter");
e.add(MessagesDataBaseConstants.DEF_LEVEL_LOOSER,DataBase.MOVE_FORMULA+"\tlvl_w\tLevel of the player fighter which wins points");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(115);
f.add(MessagesDataBaseConstants.DEF_CIBLE_EFFET,DataBase.MOVE_FORMULA+"\tcib_eff__{0}\tLa cible est sous l''effet de l''attaque {0}");
f.add(MessagesDataBaseConstants.DEF_GENRES_EGAUX,DataBase.MOVE_FORMULA+"\tgr_eg\tLes genres sont égaux.");
f.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_GENRE,DataBase.ITEM_FORMULA+"\tpk_sauv_gr\tGenre du pokemon sauvage");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_GENRE,DataBase.MOVE_FORMULA+"\tcbt_nom\tGenre du lanceur");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_PV_MAX,DataBase.MOVE_FORMULA+"\tlanc_pv_max\tPoints de vie totaux du lanceur");
f.add(MessagesDataBaseConstants.DEF_EXISTE_GENRE_ASSEXUE,DataBase.MOVE_FORMULA+"\texist_gr_assex\tIl existe un genre assexué.");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_EFFET,DataBase.MOVE_FORMULA+"\tlanc_eff__{0}\tLe lanceur est sous l''effet de l''attaque {0}");
f.add(MessagesDataBaseConstants.DEF_CIBLE_STATUTS,DataBase.MOVE_FORMULA+"\tcib_statuts\tstatuts de la cible");
f.add(MessagesDataBaseConstants.DEF_CIBLE_TAILLE,DataBase.MOVE_FORMULA+"\tcib_taille\tTaille de la cible");
f.add(MessagesDataBaseConstants.DEF_CIBLE_OBJET,DataBase.MOVE_FORMULA+"\tcib_obj\tObjet de la cible");
f.add(MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_COMBATTANT_ENTRANT,DataBase.TYPE_FORMULA+"\timmu_type_att_cbt__{0}\tLe combattant envoyé est immunisé aux attaques de type {0}");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL_TOUR,DataBase.MOVE_FORMULA+"\tlanc_deg_rec_tr\tDégâts reçus par le lanceur pendant le tour");
f.add(MessagesDataBaseConstants.DEF_DEJA_CAPTURE,DataBase.ITEM_FORMULA+"\tdej_cap\tPokemon déjà capturé");
f.add(MessagesDataBaseConstants.DEF_CIBLE_TYPES,DataBase.MOVE_FORMULA+"\tcib_t\ttypes de la cibles");
f.add(MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_TYPES,DataBase.MOVE_FORMULA+"\tcbt_env_type\tTypes du combattant envoyé");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_STATUTS,DataBase.MOVE_FORMULA+"\tlanc_statuts\tstatuts du lanceur");
f.add(MessagesDataBaseConstants.DEF_PAS_PP_ATTAQUE_CIBLE,DataBase.MOVE_FORMULA+"\tpas_pp_cible\tLa cible n''a pas de point de pouvoir pour son attaque");
f.add(MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_CIBLE,DataBase.MOVE_FORMULA+"\tsom_boost_pos_cib\tSomme des boosts positifs de la cible");
f.add(MessagesDataBaseConstants.DEF_ATTAQUE_NOM,DataBase.MOVE_FORMULA+"\tatt_nom\tNom de l''attaque");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_BOOST,DataBase.STATIS_FORMULA+"\tlanc_boost__{0}\t{0} en terme de boost du lanceur");
f.add(MessagesDataBaseConstants.DEF_ATTACK,DataBase.MOVE_FORMULA+"\tatt\tValeur de l''attaque du lanceur");
f.add(MessagesDataBaseConstants.DEF_CIBLE_PV_RESTANTS,DataBase.MOVE_FORMULA+"\tcib_pv_rest\tPoints de vie restants de la cible");
f.add(MessagesDataBaseConstants.DEF_CIBLE_POSSEDE_STATUT_RELATION,DataBase.STATUS_FORMULA+"\tcible_pseudo_st__{0}\tLa cible est altérée du pseudo statut {0}");
f.add(MessagesDataBaseConstants.DEF_ATTAQUE_TYPES,DataBase.MOVE_FORMULA+"\tatt_types\tTypes de l''attaque");
f.add(MessagesDataBaseConstants.DEF_FIGHTER_OBJET,DataBase.ITEM_FORMULA+"\tst_obj\tObjet du possesseur");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_CLONE,DataBase.MOVE_FORMULA+"\tlanc_clone\tPV du clone du lanceur");
f.add(MessagesDataBaseConstants.DEF_POWER,DataBase.MOVE_FORMULA+"\tpow\tValeur de la puissance de l''attaque du lanceur");
f.add(MessagesDataBaseConstants.DEF_PUISSANCE_BASE,DataBase.ITEM_FORMULA+"\tpuis_base\tPuissance de base de l''attaque du possesseur");
f.add(MessagesDataBaseConstants.DEF_MASSE_MOYENNE_PK,DataBase.ITEM_FORMULA+"\tmasse_moy_pk\tMasse moyenne des pokemon");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES_TYPES,DataBase.MOVE_FORMULA+"\tlanc_att_types\tTypes des attaques du lanceur");
f.add(MessagesDataBaseConstants.DEF_PK_UT_NIVEAU,DataBase.ITEM_FORMULA+"\tpk_ut_niv\tNiveau du pokemon du joueur");
f.add(MessagesDataBaseConstants.DEF_DEFENSE,DataBase.MOVE_FORMULA+"\tdef\tValeur de la défense du lanceur");
f.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_TYPES_BASE,DataBase.ITEM_FORMULA+"\tpk_sauv_types\tTypes de base du pokemon sauvage");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_CAPACITE,DataBase.MOVE_FORMULA+"\tlanc_capac\tCapacité courant du lanceur");
f.add(MessagesDataBaseConstants.DEF_FIGHTER_NOM,DataBase.ITEM_FORMULA+"\tcbt_nom\tNom du possesseur");
f.add(MessagesDataBaseConstants.DEF_NB_UTILISATION_CONSECUTIF,DataBase.ITEM_FORMULA+"\tnb_ut_cons\tNombre d''utilisations consécutives de l''attaque.");
f.add(MessagesDataBaseConstants.DEF_FIGHTER_DER_JOUE,DataBase.ITEM_FORMULA+"\tcbt_der_joue\tLe possesseur a joué en dernier");
f.add(MessagesDataBaseConstants.DEF_TEMPS_TOUR,DataBase.ITEM_FORMULA+"\ttps_tr\tTemps de tour");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES,DataBase.MOVE_FORMULA+"\tlanc_att\tAttaques du lanceur");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_BONHEUR,DataBase.MOVE_FORMULA+"\tlanc_bonh\tBonheur du lanceur");
f.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_PIERRES_EVOS,DataBase.ITEM_FORMULA+"\tpk_sauv_ev\tPierres évolutives du pokemon sauvage");
f.add(MessagesDataBaseConstants.DEF_CIBLE_BOOST,DataBase.STATIS_FORMULA+"\tcib_boost__{0}\t{0} en terme de boost de la cible");
f.add(MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_ARRIERE,DataBase.MOVE_FORMULA+"\tpas_part_arr\tPas de partenaire en arrière");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_DISPARAIT,DataBase.MOVE_FORMULA+"\tlanc_disp\tLe lanceur disparaît pendant le tour");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_JOUE,DataBase.MOVE_FORMULA+"\tlanc_joue\tLe lanceur a joué");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL,DataBase.MOVE_FORMULA+"\tlanc_deg_recu_tot\tDégâts totaux reçs par le lanceur");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_NB_UTILISATION,DataBase.MOVE_FORMULA+"\tlanc_nb_ut__{0}\tNombre d''utilisation de l''attaque {0} du lanceur");
f.add(MessagesDataBaseConstants.DEF_CIBLE_STATIS,DataBase.STATIS_FORMULA+"\tcib_statis__{0}\t{0} en terme de base de statistique de la cible");
f.add(MessagesDataBaseConstants.DEF_NB_UTILI_ATT_EQ_TOUR,DataBase.MOVE_FORMULA+"\tnb_ut_att_eq_tr__{0}\tNombre d''utilisation de l''attaque {0} par l''équipe");
f.add(MessagesDataBaseConstants.DEF_NIVEAU,"level\tn\tNiveau du pokemon");
f.add(MessagesDataBaseConstants.DEF_BOOST,"boost\tb\tBoost du pokemon");
//f.add(DataBase.DEF_EV,"ev\tev\tLa valeur ev d''une statistique du pokemon");
//f.add(DataBase.DEF_IV,"iv\tiv\tLa valeur iv d''une statistique du pokemon");
//f.add(DataBase.DEF_BASE,"base\tbase\tLa valeur de base d''une statistique du pokemon");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_TAILLE,DataBase.MOVE_FORMULA+"\tlanc_taille\ttaille du lanceur");
f.add(MessagesDataBaseConstants.DEF_LIEU_COMBAT,DataBase.ITEM_FORMULA+"\tl_cbt\tEnvironnement de combat");
f.add(MessagesDataBaseConstants.DEF_CIBLE_GENRE,DataBase.MOVE_FORMULA+"\tcib_gr\tGenre de la cible");
f.add(MessagesDataBaseConstants.DEF_CIBLE_ATTAQUE_CHOISIE,DataBase.MOVE_FORMULA+"\tcib_att_choisie\tAttaque choisie de la cible");
f.add(MessagesDataBaseConstants.DEF_CIBLE_NB_UTILISATION,DataBase.MOVE_FORMULA+"\tcib_nb_ut__{0}\tNombre d''utilisation de l''attaque {0} de la cible");
f.add(MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_CIBLE,DataBase.TYPE_FORMULA+"\timmu_type_att_cib__{0}\tLa cible est immunisée aux attaques de type {0}");
f.add(MessagesDataBaseConstants.DEF_CLIMATS,DataBase.MOVE_FORMULA+"\tclimats\tClimats actifs");
f.add(MessagesDataBaseConstants.DEF_CIBLE_CLONE,DataBase.MOVE_FORMULA+"\tcib_clone\tPV du clone de la cible");
f.add(MessagesDataBaseConstants.DEF_CIBLE_ATTAQUES,DataBase.MOVE_FORMULA+"\tcib_att\tAttaques de la cible");
f.add(MessagesDataBaseConstants.DEF_CIBLE_PV_MAX,DataBase.MOVE_FORMULA+"\tcib_pv_max\tPoints de vie totaux de la cible");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_TYPES,DataBase.MOVE_FORMULA+"\tlanc_t\ttypes du lanceurs");
f.add(MessagesDataBaseConstants.DEF_FIGHTER_TYPES,DataBase.STATUS_FORMULA+"\tcbt_types\tTypes du combattant");
f.add(MessagesDataBaseConstants.DEF_PAS_UTILIS_ATTAQUE_CIBLE,DataBase.MOVE_FORMULA+"\tpas_ut_cib\tAucune attaque utilisée par la cible");
f.add(MessagesDataBaseConstants.DEF_ATTAQUE_CATEGORIE,DataBase.MOVE_FORMULA+"\tatt_cat\tCatégorie de l''attaque du possesseur");
f.add(MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_LANCEUR,DataBase.MOVE_FORMULA+"\tnb_ko_lanceur\tNombre de pokemon ko dans l''équipe du lanceur");
f.add(MessagesDataBaseConstants.DEF_CIBLE_CAPACITE,DataBase.MOVE_FORMULA+"\tcib_capac\tCapacité courant de la cible");
f.add(MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOTAL_TOUR,DataBase.MOVE_FORMULA+"\tcib_deg_rec_tr\tDégâts reçus par la cible pendant le tour");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_PP,DataBase.MOVE_FORMULA+"\tlanc_pp__{0}\tPoints de pouvoir restants de l''attaque {0} du lanceur");
f.add(MessagesDataBaseConstants.DEF_PAS_TOUR_TERRAIN,DataBase.MOVE_FORMULA+"\tpas_tr\tVient d''entrer sur le terrain.");
f.add(MessagesDataBaseConstants.DEF_FIGHTER_CLONE,DataBase.STATUS_FORMULA+"\tcbt_clone\tPV du clone du combattant");
f.add(MessagesDataBaseConstants.DEF_CIBLE_NIVEAU,DataBase.MOVE_FORMULA+"\tcib_niv\tNiveau de la cible");
f.add(MessagesDataBaseConstants.DEF_FIGHTER_STATUTS,DataBase.ITEM_FORMULA+"\tst_cbt\tStatuts du possesseur");
f.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_MASSE,DataBase.ITEM_FORMULA+"\tpk_sauv_mas\tMasse du pokemon sauvage");
f.add(MessagesDataBaseConstants.DEF_TYPES_ATTAQUES_RES_VIDE,DataBase.MOVE_FORMULA+"\ttypes_att_res_vide\tPas de type résistant à la dernière attaque subie.");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_STATIS,DataBase.STATIS_FORMULA+"\tlanc_statis__{0}\t{0} en terme de base de statistique du lanceur");
f.add(MessagesDataBaseConstants.DEF_CIBLE_DISPARAIT,DataBase.MOVE_FORMULA+"\tcib_disp\tLa cible disparaît pendant le tour");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOUR,DataBase.CAT_FORMULA+"\tlanc_deg_rec_tr__{0}\tDégâts subis par le lanceur par des attaques de catégorie {0}.");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_MASSE,DataBase.MOVE_FORMULA+"\tlanc_masse\tMasse du lanceur");
f.add(MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT,DataBase.TYPE_FORMULA+"\teff_base_cbt_envoye__{0}\tCoefficient d''efficacité du type {0} sur les types du combattant entrant");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_NOM,DataBase.MOVE_FORMULA+"\tcbt_nom\tNom du lanceur");
f.add(MessagesDataBaseConstants.DEF_PK_UT_GENRE,DataBase.ITEM_FORMULA+"\tpk_ut_gr\tGenre du pokemon du joueur");
f.add(MessagesDataBaseConstants.DEF_FIGHTER_NB_UTILISATION,DataBase.MOVE_FORMULA+"\tcbt_nb_ut__{0}\tNombre d''utilisation de l''attaque {0} du combattant");
f.add(MessagesDataBaseConstants.DEF_NB_TOUR,DataBase.MOVE_FORMULA+"\tnb_tr__{0}\tnombre de tour de l''attaque {0}");
f.add(MessagesDataBaseConstants.DEF_PAS_ATTAQUE_INVOC,DataBase.MOVE_FORMULA+"\tpass_att_invok\tAucune attaque invoquable");
f.add(MessagesDataBaseConstants.DEF_CIBLE_JOUE,DataBase.MOVE_FORMULA+"\tcib_joue\tLa cible a joué");
f.add(MessagesDataBaseConstants.DEF_CIBLE_MASSE,DataBase.MOVE_FORMULA+"\tcib_masse\tMasse de la cible");
f.add(MessagesDataBaseConstants.DEF_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION,DataBase.MOVE_FORMULA+"\teq_adv_cbt_entr_nb_ut__{0}\tNombre d''utilisation de l''attaque {0} par les membres de l''équipe adverse au combattant entrant.");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_OBJET,DataBase.MOVE_FORMULA+"\tlanc_obj\tObjet du lanceur");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUE_CHOISIE,DataBase.MOVE_FORMULA+"\tlanc_att_choisie\tAttaque choisie du lanceur");
f.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE,DataBase.ITEM_FORMULA+"\tpk_sauv_vit\tVitesse de base du pokemon sauvage");
f.add(MessagesDataBaseConstants.DEF_PAS_ATTAQUES_COPIABLES,DataBase.MOVE_FORMULA+"\tpas_att_cop\tAucune attaque copiable");
f.add(MessagesDataBaseConstants.DEF_NB_COMBATTANTS_TERRAIN,DataBase.MOVE_FORMULA+"\tnb_cbts\tNombre de commbattants sur le terrain");
f.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_NIVEAU,DataBase.ITEM_FORMULA+"\tpk_sauv_niv\tNiveau du pokemon sauvage");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_PV_RESTANTS,DataBase.MOVE_FORMULA+"\tlanc_pv_rest\tPoints de vie restants du lanceur");
f.add(MessagesDataBaseConstants.DEF_LANCEUR_NIVEAU,DataBase.MOVE_FORMULA+"\tlanc_niv\tNiveau du lanceur");
f.add(MessagesDataBaseConstants.DEF_COEFF_EFF,DataBase.ITEM_FORMULA+"\tcoeff_eff\tCoefficent d''efficacité de l''attaque du possesseur");
//f.add(DataBase.DEF_LANCEUR_POSSEDE_STATUT_RELATION,DataBase.STATUS_FORMULA+"\tcible_pseudo_st__{0}\tLe lanceur est alt&eacute;r&eacute;e du pseudo statut {0}");
f.add(MessagesDataBaseConstants.DEF_NB_TOUR_GLOBAL,DataBase.MOVE_FORMULA+"\tnb_tr_gl__{0}\tNombre de tour de l''attaque {0}");
f.add(MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_LANCEUR,DataBase.MOVE_FORMULA+"\tsom_boost_pos_lanc\tSomme des boosts positifs du lanceur");
f.add(MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_TERRAIN,DataBase.MOVE_FORMULA+"\tpas_part\tPas de partenaire sur le terrain");
f.add(MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_CLONE,DataBase.MOVE_FORMULA+"\tcbt_env_clone\tPoints de vie restants du clone du combattant envoyé");
f.add(MessagesDataBaseConstants.DEF_AUCUN_BOOST_POSSIBLE,DataBase.MOVE_FORMULA+"\tpas_boost\tAucun boost n''est possible pour la cible");
f.add(MessagesDataBaseConstants.DEF_BASE_CAPT_PK,DataBase.MOVE_FORMULA+"\tbase_capt\tTaux de capture de base du pokemon sauvage");
f.add(MessagesDataBaseConstants.DEF_RATE_BALL_STATUS,DataBase.MOVE_FORMULA+"\ttx_ball_statut\tProduit entre le taux de capture de la balle et les taux des statuts du pokemon sauvage.");
f.add(MessagesDataBaseConstants.DEF_FOE_PK_MAX_HP,DataBase.MOVE_FORMULA+"\tpv_max\tPoints de vie totaux du pokemon sauvage.");
f.add(MessagesDataBaseConstants.DEF_FOE_PK_REMOTE_HP,DataBase.MOVE_FORMULA+"\tpv_restants\tPoints de vie restants du pokemon sauvage.");
f.add(MessagesDataBaseConstants.DEF_PK_UT_VITESSE,DataBase.MOVE_FORMULA+"\tpk_joueur_vit\tVitesse du premier pokemon du joueur.");
f.add(MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE,DataBase.MOVE_FORMULA+"\tpk_sauvage_vit\tVitesse du pokemon sauvage.");
f.add(MessagesDataBaseConstants.DEF_NB_FLEES,DataBase.MOVE_FORMULA+"\tnb_fuites\tNombre de tentatives de fuite.");
f.add(MessagesDataBaseConstants.DEF_LEVEL_WINNER,DataBase.MOVE_FORMULA+"\tniv_p\tNiveau du combattant KO");
f.add(MessagesDataBaseConstants.DEF_LEVEL_LOOSER,DataBase.MOVE_FORMULA+"\tniv_g\tNiveau du pokemon du joueur qui gagne des points");
return f;
}
}
