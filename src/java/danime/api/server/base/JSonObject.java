package danime.api.server.base;

/**
 * APIサーバからクライアント側に返すJSONPのPOJO
 * @author fujiyohi
 */
public class JSonObject {
    /** 処理結果 */
    public String returnCd;
    /** ライセンストークン(From Tokenサーバ) */
    public String tokenInfo;
    /** 配信種別(businessModelから→productTypeに変わっています) */
    public String productType;
    /** 視聴可能開始日時 */
    public String beginDate;
    /** 視聴可能終了日時 */
    public String expirationDate;
    /** 視聴可能な期間 */
    public String messageText;

    /***************************************
     * 自動生成Getter、Setter
     ***************************************/
}
