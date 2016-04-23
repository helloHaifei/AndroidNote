package com.zhf.androidtest;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.example.zhf.common.Common;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }



    @SmallTest
    public void testMultiply() {
        assertEquals("10 x 5 must be 50", 50, 10 * 5);

        Common common = new Common();
        assertEquals("test",common.text);
    }
}