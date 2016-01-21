
package jp.dmktsp.anime.back.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="info" type="{http://ws.back.anime.dmktsp.jp/}PurchaseQueryInfo" minOccurs="0"/>
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
    "info"
})
@XmlRootElement(name = "GetPurchaseInfo")
public class GetPurchaseInfo {

    protected PurchaseQueryInfo info;

    /**
     * infoプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link PurchaseQueryInfo }
     *     
     */
    public PurchaseQueryInfo getInfo() {
        return info;
    }

    /**
     * infoプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseQueryInfo }
     *     
     */
    public void setInfo(PurchaseQueryInfo value) {
        this.info = value;
    }

}
