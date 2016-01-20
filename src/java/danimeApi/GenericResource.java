/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danimeApi;

import danime.api.server.TokenServer;
import danime.api.server.util.SvConst;
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
import danime.api.server.ViewingServer;
import java.util.Properties;
import java.io.IOException;

import net.arnx.jsonic.JSON;

import danime.api.JSon.*;
import danime.api.server.base.JSonObject;



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
    private SvConst cnst;
    
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource(){
        try{
        cnst = new SvConst();
        }catch(Exception ex){
            System.out.println(ex.toString());
            // 発生しない
        }
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
    // TODO: バージョンをURIに含める必要あり
    /**
     * @param String callback      : コールバックパラメータ
     * @param String userid        : コンテンツ購入者のユーザID
     * @param String keyid         : 購入チェック対象のコンテンツ識別子
     * @param String onetimekey    : ワンタイムキー
     * @param String usagetype     : 配信種別
     * @param String devicetype    : 再生端末タイプ
     * @param String outputquality : 映像品質
     * @param String drm_key_id    : UUIDの形態のDRM KEY ID
     */
    public String getJSON(@DefaultValue("") @QueryParam("callback") String callback,
            @DefaultValue("") @QueryParam("userid") String userid,
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
        
        // 返値用のJSONデータ格納用初期化
        JSon4Player json = new JSon4Player();
        
        /***********************************************************************
        * 視聴サーバへの問合せ(SOAPで送受信)
        *
        ***********************************************************************/
        ViewingServer viewSvr = new ViewingServer();
        
        
        /***********************************************************************
        * Tokenサーバへの問合せ(JSON POSTで投げて、TEXTで受信)
        *
        ***********************************************************************/
        // 送信用のJSON作成
        JSon4TokenServer jsTokenSrv = new JSon4TokenServer();
        // トークンサーバを生成
        TokenServer tknSrv = new TokenServer();
        tknSrv.setSendObj(jsTokenSrv);
        // トークンサーバと通信
        tknSrv.send();
        // TODO: エラー処理必須
        // 取得したトークン情報の格納
        json.tokenInfo = tknSrv.tokenKey;
        
        
        /***********************************************************************
        // 返りのJSONP作成
        // @see http://www.task-notes.com/entry/20150919/1442639772
        ***********************************************************************/
        // TODO: プレイヤーへの成否コードは生成しなおす
        json.returnCd = 0;
        // 以下は各サーバで取得した値を埋める
        json.productType = "RENTAL";
        json.beginDate = "2016/1/4 12:13:14";
        json.expirationDate = "2016/2/8 14:15:16";
        json.messageText = "";
        
        // JSONP形式に成型する
        return callback + '(' + JSON.encode(json) + ')';
    }
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String postHandler(String content) {
        return content;
    }
}
