package com.training.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.training.sanity.tests.LoginTest;

@RunWith(Suite.class)
@SuiteClasses({LoginTest.class})
public class AllTests {

}
