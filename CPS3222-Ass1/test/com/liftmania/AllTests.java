package com.liftmania;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.liftmania.LiftControllerTest;
import com.liftmania.LiftTest;
import com.liftmania.gui.AnimationCommandTest;

@RunWith(Suite.class)
@SuiteClasses({ LiftControllerTest.class, LiftTest.class, AnimationCommandTest.class })
public class AllTests {

}
