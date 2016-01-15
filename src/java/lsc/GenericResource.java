/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsc;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import lsc.server.viewingServer;
import java.util.Properties;
import java.io.IOException;
import lsc.server.*;


/**
 * REST Web Service
 *
 * @author fujiyohi
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * 定数クラス
     */
    private svConst cnst;
    
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource(){
        try{
        cnst = new svConst();
        }catch(Exception ex){
            System.out.println(ex.toString());
            // 発生しない
        }
    }
    private void getConstRead() throws IOException{
        Properties props = new Properties();
	props.loadFromXML(GenericResource.class.getResourceAsStream("Const.xml"));
        props.get("CNT_VIEWING_SV_URL");
	System.out.println(props);
    }

    /**
     * Retrieves representation of an instance of lsc.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.TEXT_PLAIN)
    //@Produces({"application/xml", "application/json"})
    //@Path("{from}/{to}/jsonp")
    @Produces({"application/javascript"})
    /**
     * @param String userid        : コンテンツ購入者のユーザID
     * @param String keyid         : 購入チェック対象のコンテンツ識別子
     * @param String onetimekey    : ワンタイムキー
     * @param String usagetype     : 配信種別
     * @param String devicetype    : 再生端末タイプ
     * @param String outputquality : 映像品質
     * @param String drm_key_id    : UUIDの形態のDRM KEY ID
     */
    public String getJSON(@DefaultValue("") @QueryParam("userid") String userid,
            @DefaultValue("") @QueryParam("keyid") String keyid,
            @DefaultValue("") @QueryParam("onetimekey") String onetimekey,
            @DefaultValue("") @QueryParam("usagetype") String usagetype,
            @DefaultValue("") @QueryParam("devicetype") String devicetype,
            @DefaultValue("") @QueryParam("outputquality") String outputquality,
            @DefaultValue("") @QueryParam("drm_key_id") String drm_key_id) {
        
        // 定数取得
        String v_url = cnst.const_viewing_sv_url;
        String t_url = cnst.const_token_sv_url;
        String v_id = cnst.const_contents_provider_id;
        String v_pw = cnst.const_contents_provider_pw;
        String test = v_url + "\n" + t_url + "\n" + v_id + "\n" + v_pw + "\n";
        
        // 視聴サーバへの問合せ
        
        // Tokenサーバへの問合せ
        return "ok=" + test + "\n\n" + userid + "\n" +  keyid + "\n" + onetimekey + "\n" + usagetype + "\n" + devicetype + "\n" + outputquality + "\n" + drm_key_id;
    }
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String postHandler(String content) {
        return content;
    }
}
