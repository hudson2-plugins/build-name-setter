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

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test case for testing class Evaluator
 * @author Christine Lu
 */
public class EvaluatorTest {

    /**
     * Test of evaluateField method, of class Evaluator.
     */
    @Test
    public void testEvaluateField() {
        System.out.println("Testing method evaluateField");
        Map<String, String> paramMap = new HashMap();
        paramMap.put("DISPLAY_NAME", "Test Display Name");
        paramMap.put("DESCRIPTION", "Test Description");
        String strInput = "$DISPLAY_NAME xxx";
        Evaluator instance = new Evaluator();
        String expResult = "Test Display Name xxx";
        String result = instance.evaluateField(paramMap, strInput);
        strInput = "${DISPLAY_NAME} xxx";
        result = instance.evaluateField(paramMap, strInput);
        assertEquals(expResult, result);
    }
}
