
package jp.dmktsp.anime.back.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetPurchaseInfoResult" type="{http://ws.back.anime.dmktsp.jp/}PurchaseQueryResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getPurchaseInfoResult"
})
@XmlRootElement(name = "GetPurchaseInfoResponse")
public class GetPurchaseInfoResponse {

    @XmlElement(name = "GetPurchaseInfoResult")
    protected PurchaseQueryResult getPurchaseInfoResult;

    /**
     * getPurchaseInfoResultプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link PurchaseQueryResult }
     *     
     */
    public PurchaseQueryResult getGetPurchaseInfoResult() {
        return getPurchaseInfoResult;
    }

    /**
     * getPurchaseInfoResultプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseQueryResult }
     *     
     */
    public void setGetPurchaseInfoResult(PurchaseQueryResult value) {
        this.getPurchaseInfoResult = value;
    }

}
