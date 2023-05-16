package com.automation.runners;

import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SelectPackages("com.automation.test.apiService")
@SuiteDisplayName("Regression suite tests Api")
@IncludeTags("RegressiveApi")
public class ApiRegressionTestsRunner {
}


