
package com.cdyne.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DocumentSummary complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="DocumentSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MisspelledWord" type="{http://ws.cdyne.com/}Words" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="body" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MisspelledWordCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentSummary", propOrder = {
    "misspelledWord",
    "ver",
    "body",
    "misspelledWordCount"
})
public class DocumentSummary {

    @XmlElement(name = "MisspelledWord")
    protected List<Words> misspelledWord;
    protected String ver;
    protected String body;
    @XmlElement(name = "MisspelledWordCount")
    protected int misspelledWordCount;

    /**
     * Gets the value of the misspelledWord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misspelledWord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisspelledWord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Words }
     * 
     * 
     */
    public List<Words> getMisspelledWord() {
        if (misspelledWord == null) {
            misspelledWord = new ArrayList<Words>();
        }
        return this.misspelledWord;
    }

    /**
     * verプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVer() {
        return ver;
    }

    /**
     * verプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVer(String value) {
        this.ver = value;
    }

    /**
     * bodyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBody() {
        return body;
    }

    /**
     * bodyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBody(String value) {
        this.body = value;
    }

    /**
     * misspelledWordCountプロパティの値を取得します。
     * 
     */
    public int getMisspelledWordCount() {
        return misspelledWordCount;
    }

    /**
     * misspelledWordCountプロパティの値を設定します。
     * 
     */
    public void setMisspelledWordCount(int value) {
        this.misspelledWordCount = value;
    }

}
