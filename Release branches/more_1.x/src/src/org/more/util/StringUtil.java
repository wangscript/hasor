/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.more.util;
/**
 * �ַ������ߡ�
 * @version 2010-9-19
 * @author ������ (zyc@byshell.org)
 */
public class StringUtil {
    /**ת������ĸ��д*/
    public static String toUpperCase(String value) {
        StringBuffer sb = new StringBuffer(value);
        char firstChar = sb.charAt(0);
        sb.delete(0, 1);
        sb.insert(0, (char) ((firstChar >= 97) ? firstChar - 32 : firstChar));
        return sb.toString();
    }
    /**ת������ĸСд*/
    public static String toLowerCase(String value) {
        StringBuffer sb = new StringBuffer(value);
        char firstChar = sb.charAt(0);
        sb.delete(0, 1);
        sb.insert(0, (char) ((firstChar <= 90) ? firstChar + 32 : firstChar));
        return sb.toString();
    }
    /**ʹ��ͨ���ƥ���ַ�����*/
    public static boolean matchWild(String pattern, String str) {
        StringBuffer result = new StringBuffer("^");
        char metachar[] = { '$', '^', '[', ']', '(', ')', '{', '|', '+', '.', '\\' };
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            for (int j = 0; j < metachar.length; j++)
                if (ch == metachar[j]) {
                    result.append("\\");
                    result.append(ch);
                }
            if (ch == '*')
                result.append(".*");
            else if (ch == '?')
                result.append(".");
            else
                result.append(ch);
        }
        result.append("$");
        return str.matches(result.toString());
    }
}