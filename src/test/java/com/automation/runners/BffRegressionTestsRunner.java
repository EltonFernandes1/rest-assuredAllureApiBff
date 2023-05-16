package com.automation.runners;

import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SelectPackages("com.automation.test.bffService")
@SuiteDisplayName("Regression suite tests Bff")
@IncludeTags("RegressiveBff")
public class BffRegressionTestsRunner {
}


