/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danime.api.server.base;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 各種サーバへのJava内からの接続するためのスーパークラス 視聴サーバ、Widevineサーバなど
 *
 * @author fujiyohi
 */
public class ServerObject {

    
    /**
     * 接続するサーバのURL(SOAP、JSON、APIなどの接続先URL)
     */
    private String serverUrl;
    /**
     * *************** Player⇒APIサーバ ****************
     */
    private String userId;
    private String keyId;
    private String oneTimeKey;
    private String usageType;
    private String deviceType;
    private String outputQuality;
    private String drmKeyId;
    /**
     * *************** APIサーバ⇒視聴WEB ****************
     */
    private String customerId;
    private String authPass;
    /**
     * *************** 視聴WEB⇒APIサーバ ****************
     */
    private int queryResult;
    private String productType;//businessModel→productType
    private String beginDate;
    private String expirationDate;
    private String gracePeriod;
    /**
     * *************** APIサーバ⇒Tokenサーバ ****************
     */
    private String purchaseId;
    private String testFlag;
    /**
     * *************** Tokenサーバ⇒APIサーバ ****************
     */
    private String tokenInfo;
    private int errorCode;
    private String errorMessage;
    /**
     * *************** APIサーバ⇒Player ****************
     */
    private int returnCd;
}
