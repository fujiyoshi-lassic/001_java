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
import danime.api.server.util.SvConst;
import danime.api.server.base.ServerObject;
import java.util.logging.Logger;

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
    private static final Logger LOG = Logger.getLogger(GenericResource.class.getName());

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        try {
            cnst = new SvConst();
        } catch (Exception ex) {
            // TODO: エラーの握り潰し(発生しないはず)
            System.out.println(ex.toString());
            LOG.log(java.util.logging.Level.WARNING, ex.toString());
        }
    }

    /**
     * Retrieves representation of an instance of lsc.GenericResource
     *
     * @return an instance of java.lang.String
     * @see "http://d.hatena.ne.jp/hikaruright/20130222/1361506292"
     */
    @GET
    //@Path("{from}/{to}/jsonp")
    //@Produces({"application/javascript"})
    //@JSONP
    @Path("/{version}")
    @Produces({"application/json", "application/javascript"})
    // TODO: バージョンをURIに含める必要あり
    /**
     * 処理本体
     * @param version バージョン情報。現在はv1のみ
     * @param callBack コールバックパラメータ
     * @param userId コンテンツ購入者のユーザID
     * @param keyId 購入チェック対象のコンテンツ識別子
     * @param oneTimeKey ワンタイムキー
     * @param usageType 配信種別
     * @param deviceType 再生端末タイプ
     * @param outputQuality 映像品質
     * @param drmKeyId UUIDの形態のDRM KEY ID
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
            if (callBack.isEmpty()
                    || userId.isEmpty()
                    || keyId.isEmpty()
                    || drmKeyId.isEmpty()
                    || oneTimeKey.isEmpty()) {
                json.setReturnCd(1001, SvConst.SERVER_API);
                return JSONWithPaddinger(json, callBack);
                //return new JSONWithPadding(json, callBack);
            }
            if (!version.equals(VERSION_V1)) {
                json.setReturnCd(1001, SvConst.SERVER_API);
            }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="パラメータオブジェクトに詰める(ServerObject svrObj)">
            ServerObject svrObj = new ServerObject();
            svrObj.userId = userId;
            svrObj.keyId = keyId;
            svrObj.oneTimeKey = oneTimeKey;
            svrObj.usageType = usageType;
            svrObj.deviceType = deviceType;
            svrObj.outputQuality = outputQuality;
            svrObj.drmKeyId = drmKeyId;
            // 以下は定数など
            svrObj.customerId = cnst.const_contents_provider_id;
            svrObj.authPass = cnst.const_contents_provider_pw;
            svrObj.testFlag = cnst.const_token_debug_flag.toString();
//</editor-fold>

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
            Boolean bl2 = viewSvr.connectSoapV2();
            Boolean bl = viewSvr.connectSoap();
            Boolean bl3 = viewSvr.connectSoapV3();

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Tokenサーバへの問合せ">
            /**
             * *********************************************************************
             * Tokenサーバへの問合せ(JSON POSTで投げて、TEXTで受信)
             *
             **********************************************************************
             */
// トークンサーバを生成
            TokenServer tknSrv = new TokenServer();
            tknSrv.setSendObj(svrObj);
// トークンサーバと通信
            tknSrv.send();
// TODO: エラー処理必須→エラーだろうと正常だろうとここまで来たら返すしかない。下位クラスで想定してセット済
            json.returnCd = tknSrv.returnCd;
            json.tokenInfo = tknSrv.tokenInfo;
            json.messageText = tknSrv.errorMessage;
//</editor-fold>

            // JSONP形式に成型して返却
            return JSONWithPaddinger(json, callBack);
        } catch (Exception ex) {
            // APIの内部エラーの際もJSONP形式に詰めて返す
            JSon4Player jsonEx = new JSon4Player();
            jsonEx.setReturnCd(1010, SvConst.SERVER_API, ex);
            return JSONWithPaddinger(jsonEx, callBack);
        }
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String postHandler(String content) {
        return content;
    }

    /**
     * オブジェクト型からJSONP形式にエンコードする。
     *
     * @param obj 構造体(クラス)
     * @param callBack(JSONPのコールバック関数名)
     * @return 成型されたJSONP文字列
     * @see "http://www.task-notes.com/entry/20150919/1442639772"
     */
    public String JSONWithPaddinger(Object obj, String callBack) {
        return callBack + '(' + JSON.encode(obj) + ')';
    }
}
