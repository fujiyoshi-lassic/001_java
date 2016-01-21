/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danime.api.controller;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import java.util.Properties;
import java.io.IOException;

import net.arnx.jsonic.JSON;

import danime.api.JSon.*;
import danime.api.server.ViewingServer;
import danime.api.server.TokenServer;
//import danime.api.server.base.JSonObject;
import danime.api.server.util.SvConst;

/**
 * REST Web Service
 *
 * @author fujiyohi
 */
@Path("Api")
public class GenericResource {

    private final String VERSION_V1 = "v1";
    @Context
    private UriInfo context;
    /**
     * 定数クラス
     */
    private SvConst cnst;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        try {
            cnst = new SvConst();
        } catch (Exception ex) {
            // TODO: エラーの握り潰し(発生しないはず)
            System.out.println(ex.toString());
        }
    }

    /**
     * Retrieves representation of an instance of lsc.GenericResource
     *
     * @return an instance of java.lang.String
     * @see http://d.hatena.ne.jp/hikaruright/20130222/1361506292
     */
    @GET
    //@Path("{from}/{to}/jsonp")
    //@Produces({"application/javascript"})
    //@JSONP
    @Path("/{version}")
    @Produces({"application/json", "application/javascript"})
    // TODO: バージョンをURIに含める必要あり
    /**
     * @param String callback : コールバックパラメータ
     * @param String userid : コンテンツ購入者のユーザID
     * @param String keyid : 購入チェック対象のコンテンツ識別子
     * @param String onetimekey : ワンタイムキー
     * @param String usagetype : 配信種別
     * @param String devicetype : 再生端末タイプ
     * @param String outputquality : 映像品質
     * @param String drm_key_id : UUIDの形態のDRM KEY ID
     */
    public String getJSON(@PathParam("version") String version,
            @DefaultValue("") @QueryParam("callback") String callBack,
            @DefaultValue("") @QueryParam("userId") String userId,
            @DefaultValue("") @QueryParam("keyId") String keyId,
            @DefaultValue("") @QueryParam("oneTimeKey") String oneTimeKey,
            @DefaultValue("") @QueryParam("usageType") String usageType,
            @DefaultValue("") @QueryParam("deviceType") String deviceType,
            @DefaultValue("") @QueryParam("outputQuality") String outputQuality,
            @DefaultValue("") @QueryParam("drmKeyId") String drmKeyId) {
        try {
//<editor-fold defaultstate="collapsed" desc="初期処理">
            // 返値用のJSONデータ格納用初期化
            JSon4Player json = new JSon4Player();
//</editor-fold>
            
//<editor-fold defaultstate="collapsed" desc="パラメータ走査">
            // 必須チェック
            if(callBack.isEmpty()
                    || userId.isEmpty()  
                    || keyId.isEmpty()
                    || drmKeyId.isEmpty() 
                    || oneTimeKey.isEmpty()){
                json.setReturnCd(1001, SvConst.SERVER_API);
                return JSONWithPaddinger(json, callBack);
                //return new JSONWithPadding(json, callBack);
            }
            if(!version.equals(VERSION_V1)){
                json.setReturnCd(1001, SvConst.SERVER_API);
            }
            
//</editor-fold>
            

////<editor-fold defaultstate="collapsed" desc="定数取得">
//            String viewUrl = cnst.const_viewing_sv_url;
//            String viewId = cnst.const_contents_provider_id;
//            String viewPw = cnst.const_contents_provider_pw;
//
//            String tokenUrl = cnst.const_token_sv_url;
//            boolean tokenFlag = cnst.const_token_debug_flag;
////</editor-fold>

//<editor-fold defaultstate="collapsed" desc="デバッグ処理(本番ではここは処理させない)">
            if (cnst.const_api_debug_mode == 9) {
                //return callback + '(' + JSON.encode(json) + ')';
            }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="視聴サーバへの問合せ(SOAPで送受信)">
            /**
             * *********************************************************************
             * 視聴サーバへの問合せ(SOAPで送受信)
             *
             **********************************************************************
             */
            ViewingServer viewSvr = new ViewingServer();

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Tokenサーバへの問合せ">
            /**
             * *********************************************************************
             * Tokenサーバへの問合せ(JSON POSTで投げて、TEXTで受信)
             *
             **********************************************************************
             */
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

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="返りのJSONP作成">
            /**
             * *********************************************************************
             * // 返りのJSONP作成 // @see
             * http://www.task-notes.com/entry/20150919/1442639772
             * *********************************************************************
             */
            // TODO: プレイヤーへの成否コードは生成しなおす
            json.returnCd = 0;
            // 以下は各サーバで取得した値を埋める
            json.productType = "RENTAL";
            json.beginDate = "2016/1/4 12:13:14";
            json.expirationDate = "2016/2/8 14:15:16";
            json.messageText = "";
//</editor-fold>

            // JSONP形式に成型する
            return callBack + '(' + JSON.encode(json) + ')';
        } catch (Exception ex) {
            JSon4Player jsonEx = new JSon4Player();
            if(cnst.const_api_debug_mode == SvConst.DEBUG_LEVEL_DEVELOPMENT){
                jsonEx.setReturnCd(1010, SvConst.SERVER_API, ex);
            }else{
                jsonEx.setReturnCd(1010, SvConst.SERVER_API);
            }
            return callBack + '(' + JSON.encode(jsonEx) + ')';
        }
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String postHandler(String content) {
        return content;
    }
    
    public String JSONWithPaddinger(Object obj, String callBack){
        return callBack + '(' + JSON.encode(obj) + ')';
    }
}
