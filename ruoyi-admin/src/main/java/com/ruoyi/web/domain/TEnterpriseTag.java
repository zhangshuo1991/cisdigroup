package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @description t_enterprise_tag
 * @author zhangshuo
 * @date 2023-12-15
 */
@Data
@ApiModel("t_enterprise_tag")
public class TEnterpriseTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * entid
     */
    @ApiModelProperty("entid")
    private String entid;

    /**
     * entname
     */
    @ApiModelProperty("entname")
    private String entname;

    /**
     * engname
     */
    @ApiModelProperty("engname")
    private String engname;

    /**
     * uniscid
     */
    @ApiModelProperty("uniscid")
    private String uniscid;

    /**
     * regno
     */
    @ApiModelProperty("regno")
    private String regno;

    /**
     * enttype
     */
    @ApiModelProperty("enttype")
    private String enttype;

    /**
     * enttype_cn
     */
    @ApiModelProperty("enttype_cn")
    private String enttypeCn;

    /**
     * orgtype
     */
    @ApiModelProperty("orgtype")
    private String orgtype;

    /**
     * orgtype_cn
     */
    @ApiModelProperty("orgtype_cn")
    private String orgtypeCn;

    /**
     * isbranch
     */
    @ApiModelProperty("isbranch")
    private String isbranch;

    /**
     * ispartnership
     */
    @ApiModelProperty("ispartnership")
    private String ispartnership;

    /**
     * entscale
     */
    @ApiModelProperty("entscale")
    private String entscale;

    /**
     * entscale_cn
     */
    @ApiModelProperty("entscale_cn")
    private String entscaleCn;

    /**
     * esdate
     */
    @ApiModelProperty("esdate")
    private Date esdate;

    /**
     * enages
     */
    @ApiModelProperty("enages")
    private Double enages;

    /**
     * regcap
     */
    @ApiModelProperty("regcap")
    private Double regcap;

    /**
     * regcapcur
     */
    @ApiModelProperty("regcapcur")
    private String regcapcur;

    /**
     * regcapcur_cn
     */
    @ApiModelProperty("regcapcur_cn")
    private String regcapcurCn;

    /**
     * regcaprmb
     */
    @ApiModelProperty("regcaprmb")
    private Double regcaprmb;

    /**
     * enregcap
     */
    @ApiModelProperty("enregcap")
    private String enregcap;

    /**
     * enregcap_cn
     */
    @ApiModelProperty("enregcap_cn")
    private String enregcapCn;

    /**
     * paidcap
     */
    @ApiModelProperty("paidcap")
    private Double paidcap;

    /**
     * paidpct
     */
    @ApiModelProperty("paidpct")
    private Double paidpct;

    /**
     * invnums
     */
    @ApiModelProperty("invnums")
    private Integer invnums;

    /**
     * lerepname
     */
    @ApiModelProperty("lerepname")
    private String lerepname;

    /**
     * perid
     */
    @ApiModelProperty("perid")
    private String perid;

    /**
     * per_chgnums
     */
    @ApiModelProperty("per_chgnums")
    private Integer perChgnums;

    /**
     * per_disnums
     */
    @ApiModelProperty("per_disnums")
    private Integer perDisnums;

    /**
     * regorg
     */
    @ApiModelProperty("regorg")
    private String regorg;

    /**
     * regorg_cn
     */
    @ApiModelProperty("regorg_cn")
    private String regorgCn;

    /**
     * entstatus
     */
    @ApiModelProperty("entstatus")
    private String entstatus;

    /**
     * entstatus_cn
     */
    @ApiModelProperty("entstatus_cn")
    private String entstatusCn;

    /**
     * candate
     */
    @ApiModelProperty("candate")
    private Date candate;

    /**
     * revdate
     */
    @ApiModelProperty("revdate")
    private Date revdate;

    /**
     * opfrom
     */
    @ApiModelProperty("opfrom")
    private Date opfrom;

    /**
     * opto
     */
    @ApiModelProperty("opto")
    private Date opto;

    /**
     * domdistrict
     */
    @ApiModelProperty("domdistrict")
    private String domdistrict;

    /**
     * domdistrict_cn
     */
    @ApiModelProperty("domdistrict_cn")
    private String domdistrictCn;

    /**
     * dom
     */
    @ApiModelProperty("dom")
    private String dom;

    /**
     * cityno
     */
    @ApiModelProperty("cityno")
    private String cityno;

    /**
     * location
     */
    @ApiModelProperty("location")
    private String location;

    /**
     * enprov
     */
    @ApiModelProperty("enprov")
    private String enprov;

    /**
     * enprov_cn
     */
    @ApiModelProperty("enprov_cn")
    private String enprovCn;

    /**
     * encity
     */
    @ApiModelProperty("encity")
    private String encity;

    /**
     * encity_cn
     */
    @ApiModelProperty("encity_cn")
    private String encityCn;

    /**
     * opscope
     */
    @ApiModelProperty("opscope")
    private String opscope;

    /**
     * majorbiz
     */
    @ApiModelProperty("majorbiz")
    private String majorbiz;

    /**
     * industryphy
     */
    @ApiModelProperty("industryphy")
    private String industryphy;

    /**
     * industryphy_cn
     */
    @ApiModelProperty("industryphy_cn")
    private String industryphyCn;

    /**
     * industryco
     */
    @ApiModelProperty("industryco")
    private String industryco;

    /**
     * industryco_cn
     */
    @ApiModelProperty("industryco_cn")
    private String industrycoCn;

    /**
     * linkman
     */
    @ApiModelProperty("linkman")
    private String linkman;

    /**
     * telno
     */
    @ApiModelProperty("telno")
    private String telno;

    /**
     * postalcode
     */
    @ApiModelProperty("postalcode")
    private String postalcode;

    /**
     * email
     */
    @ApiModelProperty("email")
    private String email;

    /**
     * website
     */
    @ApiModelProperty("website")
    private String website;

    /**
     * empnum
     */
    @ApiModelProperty("empnum")
    private Integer empnum;

    /**
     * apprdate
     */
    @ApiModelProperty("apprdate")
    private Date apprdate;

    /**
     * isgacc
     */
    @ApiModelProperty("isgacc")
    private String isgacc;

    /**
     * islist
     */
    @ApiModelProperty("islist")
    private String islist;

    /**
     * listboard
     */
    @ApiModelProperty("listboard")
    private String listboard;

    /**
     * listexchange
     */
    @ApiModelProperty("listexchange")
    private String listexchange;

    /**
     * issuebds
     */
    @ApiModelProperty("issuebds")
    private Integer issuebds;

    /**
     * qualification
     */
    @ApiModelProperty("qualification")
    private String qualification;

    /**
     * punnums
     */
    @ApiModelProperty("punnums")
    private Integer punnums;

    /**
     * punnums3
     */
    @ApiModelProperty("punnums3")
    private Integer punnums3;

    /**
     * abnnums
     */
    @ApiModelProperty("abnnums")
    private Integer abnnums;

    /**
     * abnvalnums
     */
    @ApiModelProperty("abnvalnums")
    private Integer abnvalnums;

    /**
     * illnums
     */
    @ApiModelProperty("illnums")
    private Integer illnums;

    /**
     * illvalnums
     */
    @ApiModelProperty("illvalnums")
    private Integer illvalnums;

    /**
     * socsecnums
     */
    @ApiModelProperty("socsecnums")
    private Integer socsecnums;

    /**
     * soc110base
     */
    @ApiModelProperty("soc110base")
    private Double soc110base;

    /**
     * soc110pay
     */
    @ApiModelProperty("soc110pay")
    private Double soc110pay;

    /**
     * soc110unpay
     */
    @ApiModelProperty("soc110unpay")
    private Double soc110unpay;

    /**
     * ancheid
     */
    @ApiModelProperty("ancheid")
    private String ancheid;

    /**
     * ancheyear
     */
    @ApiModelProperty("ancheyear")
    private String ancheyear;

    /**
     * assgro
     */
    @ApiModelProperty("assgro")
    private Double assgro;

    /**
     * assgroly
     */
    @ApiModelProperty("assgroly")
    private Double assgroly;

    /**
     * assgrobly
     */
    @ApiModelProperty("assgrobly")
    private Double assgrobly;

    /**
     * liagro
     */
    @ApiModelProperty("liagro")
    private Double liagro;

    /**
     * liagroly
     */
    @ApiModelProperty("liagroly")
    private Double liagroly;

    /**
     * liagrobly
     */
    @ApiModelProperty("liagrobly")
    private Double liagrobly;

    /**
     * vendinc
     */
    @ApiModelProperty("vendinc")
    private Double vendinc;

    /**
     * vendincly
     */
    @ApiModelProperty("vendincly")
    private Double vendincly;

    /**
     * vendincbly
     */
    @ApiModelProperty("vendincbly")
    private Double vendincbly;

    /**
     * maibusinc
     */
    @ApiModelProperty("maibusinc")
    private Double maibusinc;

    /**
     * maibusincly
     */
    @ApiModelProperty("maibusincly")
    private Double maibusincly;

    /**
     * maibusincbly
     */
    @ApiModelProperty("maibusincbly")
    private Double maibusincbly;

    /**
     * progro
     */
    @ApiModelProperty("progro")
    private Double progro;

    /**
     * progroly
     */
    @ApiModelProperty("progroly")
    private Double progroly;

    /**
     * progrobly
     */
    @ApiModelProperty("progrobly")
    private Double progrobly;

    /**
     * netinc
     */
    @ApiModelProperty("netinc")
    private Double netinc;

    /**
     * netincyly
     */
    @ApiModelProperty("netincyly")
    private Double netincyly;

    /**
     * netincybly
     */
    @ApiModelProperty("netincybly")
    private Double netincybly;

    /**
     * ratgro
     */
    @ApiModelProperty("ratgro")
    private Double ratgro;

    /**
     * ratgroly
     */
    @ApiModelProperty("ratgroly")
    private Double ratgroly;

    /**
     * ratgrobly
     */
    @ApiModelProperty("ratgrobly")
    private Double ratgrobly;

    /**
     * totequ
     */
    @ApiModelProperty("totequ")
    private Double totequ;

    /**
     * totequly
     */
    @ApiModelProperty("totequly")
    private Double totequly;

    /**
     * totequbly
     */
    @ApiModelProperty("totequbly")
    private Double totequbly;

    /**
     * anrnums
     */
    @ApiModelProperty("anrnums")
    private Integer anrnums;

    /**
     * anrvalnums
     */
    @ApiModelProperty("anrvalnums")
    private Integer anrvalnums;

    /**
     * anyear
     */
    @ApiModelProperty("anyear")
    private String anyear;

    /**
     * antelno
     */
    @ApiModelProperty("antelno")
    private String antelno;

    /**
     * anaddr
     */
    @ApiModelProperty("anaddr")
    private String anaddr;

    /**
     * patnums
     */
    @ApiModelProperty("patnums")
    private Integer patnums;

    /**
     * ivenums
     */
    @ApiModelProperty("ivenums")
    private Integer ivenums;

    /**
     * grtnums
     */
    @ApiModelProperty("grtnums")
    private Integer grtnums;

    /**
     * umdnums
     */
    @ApiModelProperty("umdnums")
    private Integer umdnums;

    /**
     * dsnnums
     */
    @ApiModelProperty("dsnnums")
    private Integer dsnnums;

    /**
     * innovs
     */
    @ApiModelProperty("innovs")
    private Double innovs;

    /**
     * innovnets
     */
    @ApiModelProperty("innovnets")
    private Double innovnets;

    /**
     * patvalnums
     */
    @ApiModelProperty("patvalnums")
    private Integer patvalnums;

    /**
     * ivevalnums
     */
    @ApiModelProperty("ivevalnums")
    private Integer ivevalnums;

    /**
     * grtvalnums
     */
    @ApiModelProperty("grtvalnums")
    private Integer grtvalnums;

    /**
     * umdvalnums
     */
    @ApiModelProperty("umdvalnums")
    private Integer umdvalnums;

    /**
     * dsnvalnums
     */
    @ApiModelProperty("dsnvalnums")
    private Integer dsnvalnums;

    /**
     * induschar
     */
    @ApiModelProperty("induschar")
    private String induschar;

    /**
     * induschar_cn
     */
    @ApiModelProperty("induschar_cn")
    private String induscharCn;

    /**
     * cityvitality
     */
    @ApiModelProperty("cityvitality")
    private String cityvitality;

    /**
     * cityvitality_cn
     */
    @ApiModelProperty("cityvitality_cn")
    private String cityvitalityCn;

    /**
     * isctrlstate
     */
    @ApiModelProperty("isctrlstate")
    private String isctrlstate;

    /**
     * isctrlprivate
     */
    @ApiModelProperty("isctrlprivate")
    private String isctrlprivate;

    /**
     * istop500
     */
    @ApiModelProperty("istop500")
    private String istop500;

    /**
     * ismbr500
     */
    @ApiModelProperty("ismbr500")
    private String ismbr500;

    /**
     * groupid
     */
    @ApiModelProperty("groupid")
    private String groupid;

    /**
     * grpname
     */
    @ApiModelProperty("grpname")
    private String grpname;

    /**
     * isgrpmbr
     */
    @ApiModelProperty("isgrpmbr")
    private String isgrpmbr;

    /**
     * mbrroles
     */
    @ApiModelProperty("mbrroles")
    private String mbrroles;

    /**
     * isjrinst
     */
    @ApiModelProperty("isjrinst")
    private String isjrinst;

    /**
     * jrclafy
     */
    @ApiModelProperty("jrclafy")
    private String jrclafy;

    /**
     * jrclafy_cn
     */
    @ApiModelProperty("jrclafy_cn")
    private String jrclafyCn;

    /**
     * isinvst
     */
    @ApiModelProperty("isinvst")
    private String isinvst;

    /**
     * isamac
     */
    @ApiModelProperty("isamac")
    private String isamac;

    /**
     * endesize
     */
    @ApiModelProperty("endesize")
    private String endesize;

    /**
     * endesize_cn
     */
    @ApiModelProperty("endesize_cn")
    private String endesizeCn;

    /**
     * engrowth
     */
    @ApiModelProperty("engrowth")
    private String engrowth;

    /**
     * engrowth_cn
     */
    @ApiModelProperty("engrowth_cn")
    private String engrowthCn;

    /**
     * enlevrag
     */
    @ApiModelProperty("enlevrag")
    private String enlevrag;

    /**
     * enlevrag_cn
     */
    @ApiModelProperty("enlevrag_cn")
    private String enlevragCn;

    /**
     * enroe
     */
    @ApiModelProperty("enroe")
    private String enroe;

    /**
     * enroe_cn
     */
    @ApiModelProperty("enroe_cn")
    private String enroeCn;

    /**
     * enros
     */
    @ApiModelProperty("enros")
    private String enros;

    /**
     * enros_cn
     */
    @ApiModelProperty("enros_cn")
    private String enrosCn;

    /**
     * enratgro
     */
    @ApiModelProperty("enratgro")
    private String enratgro;

    /**
     * enratgro_cn
     */
    @ApiModelProperty("enratgro_cn")
    private String enratgroCn;

    /**
     * enassgro
     */
    @ApiModelProperty("enassgro")
    private String enassgro;

    /**
     * enassgro_cn
     */
    @ApiModelProperty("enassgro_cn")
    private String enassgroCn;

    /**
     * envendinc
     */
    @ApiModelProperty("envendinc")
    private String envendinc;

    /**
     * envendinc_cn
     */
    @ApiModelProperty("envendinc_cn")
    private String envendincCn;

    /**
     * enliagro
     */
    @ApiModelProperty("enliagro")
    private String enliagro;

    /**
     * enliagro_cn
     */
    @ApiModelProperty("enliagro_cn")
    private String enliagroCn;

    /**
     * enmaibusinc
     */
    @ApiModelProperty("enmaibusinc")
    private String enmaibusinc;

    /**
     * enmaibusinc_cn
     */
    @ApiModelProperty("enmaibusinc_cn")
    private String enmaibusincCn;

    /**
     * enprogro
     */
    @ApiModelProperty("enprogro")
    private String enprogro;

    /**
     * enprogro_cn
     */
    @ApiModelProperty("enprogro_cn")
    private String enprogroCn;

    /**
     * ennetinc
     */
    @ApiModelProperty("ennetinc")
    private String ennetinc;

    /**
     * ennetinc_cn
     */
    @ApiModelProperty("ennetinc_cn")
    private String ennetincCn;

    /**
     * entotequ
     */
    @ApiModelProperty("entotequ")
    private String entotequ;

    /**
     * entotequ_cn
     */
    @ApiModelProperty("entotequ_cn")
    private String entotequCn;

    /**
     * induslevrag
     */
    @ApiModelProperty("induslevrag")
    private String induslevrag;

    /**
     * induslevrag_cn
     */
    @ApiModelProperty("induslevrag_cn")
    private String induslevragCn;

    /**
     * indusroe
     */
    @ApiModelProperty("indusroe")
    private String indusroe;

    /**
     * indusroe_cn
     */
    @ApiModelProperty("indusroe_cn")
    private String indusroeCn;

    /**
     * indusros
     */
    @ApiModelProperty("indusros")
    private String indusros;

    /**
     * indusros_cn
     */
    @ApiModelProperty("indusros_cn")
    private String indusrosCn;

    /**
     * lvscntrbut
     */
    @ApiModelProperty("lvscntrbut")
    private String lvscntrbut;

    /**
     * lvscntrbut_cn
     */
    @ApiModelProperty("lvscntrbut_cn")
    private String lvscntrbutCn;

    /**
     * lvstrength
     */
    @ApiModelProperty("lvstrength")
    private String lvstrength;

    /**
     * lvstrength_cn
     */
    @ApiModelProperty("lvstrength_cn")
    private String lvstrengthCn;

    /**
     * liarate
     */
    @ApiModelProperty("liarate")
    private String liarate;

    /**
     * liarate_cn
     */
    @ApiModelProperty("liarate_cn")
    private String liarateCn;

    /**
     * grnetrate
     */
    @ApiModelProperty("grnetrate")
    private String grnetrate;

    /**
     * grnetrate_cn
     */
    @ApiModelProperty("grnetrate_cn")
    private String grnetrateCn;

    /**
     * istax2
     */
    @ApiModelProperty("istax2")
    private String istax2;

    /**
     * isprofit3
     */
    @ApiModelProperty("isprofit3")
    private String isprofit3;

    /**
     * isgrsalrate2
     */
    @ApiModelProperty("isgrsalrate2")
    private String isgrsalrate2;

    /**
     * evamkt
     */
    @ApiModelProperty("evamkt")
    private String evamkt;

    /**
     * evamkt_cn
     */
    @ApiModelProperty("evamkt_cn")
    private String evamktCn;

    /**
     * evavitality
     */
    @ApiModelProperty("evavitality")
    private String evavitality;

    /**
     * evavitality_cn
     */
    @ApiModelProperty("evavitality_cn")
    private String evavitalityCn;

    /**
     * indusscore
     */
    @ApiModelProperty("indusscore")
    private Double indusscore;

    /**
     * finscore
     */
    @ApiModelProperty("finscore")
    private Double finscore;

    /**
     * innovscore
     */
    @ApiModelProperty("innovscore")
    private Double innovscore;

    /**
     * comevascore
     */
    @ApiModelProperty("comevascore")
    private Double comevascore;

    /**
     * enlifcl
     */
    @ApiModelProperty("enlifcl")
    private String enlifcl;

    /**
     * enlifcl_cn
     */
    @ApiModelProperty("enlifcl_cn")
    private String enlifclCn;

    /**
     * disnums
     */
    @ApiModelProperty("disnums")
    private Integer disnums;

    /**
     * dis_provnums
     */
    @ApiModelProperty("dis_provnums")
    private Integer disProvnums;

    /**
     * dis_province
     */
    @ApiModelProperty("dis_province")
    private String disProvince;

    /**
     * dis_provtimes
     */
    @ApiModelProperty("dis_provtimes")
    private Integer disProvtimes;

    /**
     * dis_filtimes1
     */
    @ApiModelProperty("dis_filtimes1")
    private Integer disFiltimes1;

    /**
     * dis_filtimes2
     */
    @ApiModelProperty("dis_filtimes2")
    private Integer disFiltimes2;

    /**
     * dis_filtimes3
     */
    @ApiModelProperty("dis_filtimes3")
    private Integer disFiltimes3;

    /**
     * dis_pubtimes1
     */
    @ApiModelProperty("dis_pubtimes1")
    private Integer disPubtimes1;

    /**
     * dis_pubtimes2
     */
    @ApiModelProperty("dis_pubtimes2")
    private Integer disPubtimes2;

    /**
     * dis_pubtimes3
     */
    @ApiModelProperty("dis_pubtimes3")
    private Integer disPubtimes3;

    /**
     * ctrlid
     */
    @ApiModelProperty("ctrlid")
    private String ctrlid;

    /**
     * ctrlname
     */
    @ApiModelProperty("ctrlname")
    private String ctrlname;

    /**
     * ctrltype
     */
    @ApiModelProperty("ctrltype")
    private String ctrltype;

    /**
     * ctr_shratio
     */
    @ApiModelProperty("ctr_shratio")
    private Double ctrShratio;

    /**
     * ctr_equitytype
     */
    @ApiModelProperty("ctr_equitytype")
    private String ctrEquitytype;

    /**
     * ctr_wkexpers
     */
    @ApiModelProperty("ctr_wkexpers")
    private Double ctrWkexpers;

    /**
     * ctr_wkentid
     */
    @ApiModelProperty("ctr_wkentid")
    private String ctrWkentid;

    /**
     * ctr_wkentname
     */
    @ApiModelProperty("ctr_wkentname")
    private String ctrWkentname;

    /**
     * ctr_disnums
     */
    @ApiModelProperty("ctr_disnums")
    private Integer ctrDisnums;

    /**
     * holderid
     */
    @ApiModelProperty("holderid")
    private String holderid;

    /**
     * holder
     */
    @ApiModelProperty("holder")
    private String holder;

    /**
     * holdertype
     */
    @ApiModelProperty("holdertype")
    private String holdertype;

    /**
     * hdr_subconprop
     */
    @ApiModelProperty("hdr_subconprop")
    private Double hdrSubconprop;

    /**
     * hdr_wkexpers
     */
    @ApiModelProperty("hdr_wkexpers")
    private Double hdrWkexpers;

    /**
     * hdr_wkentid
     */
    @ApiModelProperty("hdr_wkentid")
    private String hdrWkentid;

    /**
     * hdr_wkentname
     */
    @ApiModelProperty("hdr_wkentname")
    private String hdrWkentname;

    /**
     * hdr_disnums
     */
    @ApiModelProperty("hdr_disnums")
    private Integer hdrDisnums;

    public TEnterpriseTag() {}
}
