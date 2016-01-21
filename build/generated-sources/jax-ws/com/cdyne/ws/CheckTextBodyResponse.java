
package com.cdyne.ws;

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
 *         &lt;element name="DocumentSummary" type="{http://ws.cdyne.com/}DocumentSummary"/>
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
    "documentSummary"
})
@XmlRootElement(name = "CheckTextBodyResponse")
public class CheckTextBodyResponse {

    @XmlElement(name = "DocumentSummary", required = true, nillable = true)
    protected DocumentSummary documentSummary;

    /**
     * documentSummaryプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DocumentSummary }
     *     
     */
    public DocumentSummary getDocumentSummary() {
        return documentSummary;
    }

    /**
     * documentSummaryプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentSummary }
     *     
     */
    public void setDocumentSummary(DocumentSummary value) {
        this.documentSummary = value;
    }

}
