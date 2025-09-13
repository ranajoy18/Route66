package utils;

import org.aeonbits.owner.Config;

@Config.Sources({
	"classpath:config/env-${env}.properties",
	"classpath:config/env-local.properties"
})

public interface testConfig extends Config {
	@Key("baseUrl") @DefaultValue("https://www.redbus.in") String baseUrl();
	@Key("browser") @DefaultValue("chrome") String browser();
	  @Key("headless") @DefaultValue("true") boolean headless();
	  @Key("gridUrl") String gridUrl();
	  @Key("implicitWaitMs") @DefaultValue("0") int implicitWaitMs();
	  @Key("explicitWaitSec") @DefaultValue("15") int explicitWaitSec();
}
