package com.core.reporting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Usage: add @TestLinkTest annotation to out test method.
 * You have to set internal test plan ID and internal test case ID.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TestLinkTest {

    public int testPlanId();

    public int testCaseId();

}
