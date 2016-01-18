/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsc.server.base;

/**
 * 各種サーバへのJava内からの接続するためのスーパークラス
 * 視聴サーバ、Widevineサーバなど
 * @author fujiyohi
 */
public class BaseServer {
    /**
     * 接続するサーバのURL(SOAP、JSON、APIなどの接続先URL)
     */
    private String serverUrl;
    /***************** Player⇒APIサーバ *****************/
    private String userId;
    private String keyId;
    private String onetimekey;
    private String usageType;
    private String deviceType;
    private String outputQuality;
    private String drmKeyId;
    /***************** Player⇒APIサーバ *****************/
    private String returnCd;
    private String tokenInfo;
    //private String usageType;//
    private String beginDate;
    private String expirationDate;
   private String  messageText;
   
   
   

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public void setOnetimekey(String onetimekey) {
        this.onetimekey = onetimekey;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setOutputQuality(String outputQuality) {
        this.outputQuality = outputQuality;
    }

    public void setDrmKeyId(String drmKeyId) {
        this.drmKeyId = drmKeyId;
    }

    public void setReturnCd(String returnCd) {
        this.returnCd = returnCd;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getOnetimekey() {
        return onetimekey;
    }

    public String getUsageType() {
        return usageType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getOutputQuality() {
        return outputQuality;
    }

    public String getDrmKeyId() {
        return drmKeyId;
    }

    public String getReturnCd() {
        return returnCd;
    }

    public String getTokenInfo() {
        return tokenInfo;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getMessageText() {
        return messageText;
    }

}
