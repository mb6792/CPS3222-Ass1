package com.testing;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.liftmania.LiftControllerTest;
import com.liftmania.LiftTest;
import com.liftmania.gui.AnimationCommandTest;
import com.liftmania.gui.LiftsVisualiserTest;
import com.liftmania.gui.ShaftTest;

@RunWith(Suite.class)
@SuiteClasses({ LiftControllerTest.class, LiftTest.class, AnimationCommandTest.class, LiftsVisualiserTest.class, ShaftTest.class })
public class AllTests {

}
