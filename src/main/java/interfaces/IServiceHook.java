package interfaces;

import io.cucumber.java.Scenario;

public interface IServiceHook {
	
	public void setUp(Scenario scenario);
	
	public void tearDown(Scenario scenario);

}
