package com.edureka.keyworddriven.tests;

import org.testng.annotations.Test;

import com.edureka.keyworddriven.engine.KeyWordEngine;


public class MobileTest {
 
	public KeyWordEngine keyWordEngine;
  @Test
  public void loginTest() {
	  
	  keyWordEngine= new KeyWordEngine();
	  
	  keyWordEngine.startExecution("login");
	  
  }
}