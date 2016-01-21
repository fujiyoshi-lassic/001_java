/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danime.api.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fujiyohi
 */
public class UtilServer {
    

    /**
     * キャメルケース表記をスネークケース表記（小文字）へ
     *
     * @param targetStr スネーク形式文字列
     * @return ローワーキャメルケース文字列
     * @see "http://blog.kengo-toda.jp/entry/20081130/1228026182"
     */
    public static String camelToSnake(String targetStr) {
        String convertedStr = targetStr
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2");
        return convertedStr.toLowerCase();
    }

    /**
     * スネークケース表記をローワーキャメルケース表記へ
     *
     * @param targetStr スネーク形式文字列
     * @return ローワーキャメルケース文字列
     * @see "http://blog.kengo-toda.jp/entry/20081130/1228026182"
     */
    public static String snakeToCamel(String targetStr) {
        Pattern p = Pattern.compile("_([a-z])");
        Matcher m = p.matcher(targetStr.toLowerCase());

        StringBuffer sb = new StringBuffer(targetStr.length());
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
