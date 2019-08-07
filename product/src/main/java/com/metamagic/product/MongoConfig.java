package com.metamagic.product;

import java.util.Properties;

public class MongoConfig {

	public Properties getDBConfig() {
		
		String dbhost = System.getenv("MONO_HOST");
		String port = System.getenv("MONO_PORT");

		Properties properties = new Properties();
		properties.put("spring.data.mongodb.database", "productdb");
		properties.put("spring.data.mongodb.host", dbhost);
		properties.put("spring.data.mongodb.port", port);
		return properties;
	}

}
