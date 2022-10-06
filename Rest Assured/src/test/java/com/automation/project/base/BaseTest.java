package com.automation.project.base;

import com.framework.utils.JsonReader;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected static final String ENDPOINT_DATA = "http://ptsv2.com/t/fu807-1554722621/post";

    @BeforeTest
    @Parameters("testDataPath")
    protected void initData(@Optional String testDataPath) {

    }
}


