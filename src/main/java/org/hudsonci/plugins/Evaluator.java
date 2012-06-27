/*
 * The MIT License
 *
 * Copyright (c) 2012, Oracle Corporation, Christine Lu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.hudsonci.plugins;

import java.util.Map;

/**
 * Evaluate String based on Map of parameters
 *
 * @author Christine Lu
 */
public class Evaluator {

    public String evaluateField(Map<String, String> paramMap, String strInput) {
        String strResult = null;
        String[] strArray = strInput.split("\\s");
        for (int x = 0; x < strArray.length; x++) {
            int index = strArray[x].indexOf("$");
            if (index != -1) {
                String varName = null;
                int leftCurly = strArray[x].indexOf("{");
                int rightCurly = strArray[x].indexOf("}");
                if (leftCurly != -1 && rightCurly != -1) {
                    varName = strArray[x].substring(leftCurly + 1, rightCurly);
                } else {
                    varName = strArray[x].substring(index + 1);
                }
                String value = paramMap.get(varName);
                if (value != null && value.length() != 0) {
                    strResult = (strResult == null) ? value : strResult + " " + value;
                }
            } else {
                strResult = (strResult == null) ? strArray[x] : strResult + " " + strArray[x];
            }
        }
        return strResult;
    }
}
