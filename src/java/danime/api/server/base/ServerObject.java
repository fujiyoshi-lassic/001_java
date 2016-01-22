package danime.api.server.base;

/**
 * 各種サーバへのJava内からの接続するためのスーパークラス 視聴サーバ、Widevineサーバなど
 * メンバ変数として引き渡すパラメータを保持する
 * 
 * @author fujiyohi
 */
public class ServerObject {

    
    /**
     * 接続するサーバのURL(SOAP、JSON、APIなどの接続先URL)
     */
    public String serverUrl;
    /****************************************************
     ***************** Player⇒APIサーバ *****************
     ***************************************************/
    /** コンテンツ購入者のユーザID */
    public String userId;
    /** 購入チェック対象のコンテンツ識別子 */
    public String keyId;
    /** ワンタイムキー */
    public String oneTimeKey;
    /** 配信種別 */
    public String usageType;
    /** 再生端末タイプ */
    public String deviceType;
    /** 映像品質 */
    public String outputQuality;
    /** UUIDの形態のDRM KEY ID */
    public String drmKeyId;
    /****************************************************
     ***************** APIサーバ⇒視聴WEB ****************
     ***************************************************/
    /** コンテンツプロバイダID */
    public String customerId;
    /** コンテンツプロバイダパスワード */
    public String authPass;
    /****************************************************
     ***************** 視聴WEB⇒APIサーバ ****************
     ***************************************************/
    /** 購入情報結果 */
    public int queryResult;
    /** 配信種別(businessModelから→productTypeに変わっています) */
    public String productType;
    /** 視聴可能開始日時 */
    public String beginDate;
    /** 視聴可能終了日時 */
    public String expirationDate;
    /** 視聴可能な期間 */
    public String gracePeriod;
    /** 視聴サーバのみの結果パラメータをインスタンス化したPOJO */
    private prmViewServer prmViewServer;
    /**
     * 視聴サーバのみの結果パラメータ取得
     * @return 視聴サーバのみの結果パラメータクラス
     */
    public prmViewServer getPrmViewServer() {
        return prmViewServer;
    }
    /**
     * 視聴サーバのみの結果パラメータ設定。これはViewingServerクラスから呼ばれることを想定
     * @param prmViewServer 
     */
    public void setPrmViewServer(prmViewServer prmViewServer) {
        this.prmViewServer = prmViewServer;
        this.queryResult = prmViewServer.queryResult;
        this.productType = prmViewServer.productType;
        this.beginDate = prmViewServer.beginDate;
        this.expirationDate = prmViewServer.expirationDate;
        this.gracePeriod = prmViewServer.gracePeriod;
    }
    /**
     * prmViewServerオブジェクトを生成。
     */
    public void createViewServer(){
        prmViewServer prmVS = new prmViewServer();
        prmVS.queryResult = this.queryResult;
        prmVS.productType = this.productType;
        prmVS.beginDate = this.beginDate;
        prmVS.expirationDate = this.expirationDate;
        prmVS.gracePeriod = this.gracePeriod;
        this.setPrmViewServer(prmVS);
    }
    
    /****************************************************
     ***************** APIサーバ⇒Tokenサーバ ************
     ***************************************************/
    /** 購入ID */
    public String purchaseId;
    /** テストフラグ */
    public String testFlag;
    /****************************************************
     ***************** Tokenサーバ⇒APIサーバ ************
     ***************************************************/
    /** ライセンストークン(From Tokenサーバ) */
    public String tokenInfo;
    /** エラーコード(From Tokenサーバ) */
    public int errorCode;
    /** エラーメッセージ(From Tokenサーバ) */
    public String errorMessage;
    /** トークンサーバのみの結果パラメータをインスタンス化したPOJO */
    private prmTokenServer prmTokenServer;
    /**
     * トークンサーバのみの結果パラメータ取得
     * @return トークンサーバのみの結果パラメータクラス
     */
    public prmTokenServer getPrmTokenServer() {
        return prmTokenServer;
    }
    /**
     * トークンサーバのみの結果パラメータ設定
     * @param prmTokenServer 
     */
    public void setPrmTokenServer(prmTokenServer prmTokenServer) {
        this.prmTokenServer = prmTokenServer;
    }
    /**
     * prmTokenServerオブジェクトを生成。
     */
    public void createTokenServer(){
        this.prmTokenServer.tokenInfo = this.tokenInfo;
        this.prmTokenServer.errorCode = this.errorCode;
        this.prmTokenServer.errorMessage = this.errorMessage;
    }
    
    /****************************************************
     ***************** APIサーバ⇒Player ****************
     ***************************************************/
    /** 処理結果 */
    public int returnCd;
    
    private final String EMPTY = "";
    private final String INI_BOOLEAN = "true";
    private final int ZERO = 0;

    /**
     * コンストラクタ
     * 全部の変数を初期化
     */
    public ServerObject() {
        this.serverUrl = EMPTY;
        this.userId = EMPTY;
        this.keyId = EMPTY;
        this.oneTimeKey = EMPTY;
        this.usageType = EMPTY;
        this.deviceType = EMPTY;
        this.outputQuality = EMPTY;
        this.drmKeyId = EMPTY;
        this.customerId = EMPTY;
        this.authPass = EMPTY;
        this.queryResult = ZERO;
        this.productType = EMPTY;
        this.beginDate = EMPTY;
        this.expirationDate = EMPTY;
        this.gracePeriod = EMPTY;
        this.purchaseId = EMPTY;
        this.testFlag = INI_BOOLEAN;
        this.tokenInfo = EMPTY;
        this.errorCode = ZERO;
        this.errorMessage = EMPTY;
        this.returnCd = ZERO;
    }
//<editor-fold defaultstate="collapsed" desc="視聴サーバ返値POJOクラス">
    /**
     * 視聴サーバ返値POJOクラス
     */
    class prmViewServer{
        /** 購入情報結果 */
        public int queryResult;
        /** 配信種別(businessModelから→productTypeに変わっています) */
        public String productType;
        /** 視聴可能開始日時 */
        public String beginDate;
        /** 視聴可能終了日時 */
        public String expirationDate;
        /** 視聴可能な期間 */
        public String gracePeriod;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="トークンサーバ返値POJOクラス">
    /**
     * トークンサーバ返値POJOクラス
     */
    class prmTokenServer{
        /** ライセンストークン(From Tokenサーバ) */
        public String tokenInfo;
        /** エラーコード(From Tokenサーバ) */
        public int errorCode;
        /** エラーメッセージ(From Tokenサーバ) */
        public String errorMessage;
    }
//</editor-fold>
}
