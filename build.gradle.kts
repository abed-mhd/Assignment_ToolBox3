plugins {
	// Apply the Java plugin for Java support
	java
	// Apply the Spring Boot plugin for Spring Boot functionality
	id("org.springframework.boot") version "3.3.4"
	// Apply the Spring Dependency Management plugin for managing dependencies
	id("io.spring.dependency-management") version "1.1.6"
	// Add CheckStyle and SpotBugs plugins
	id("checkstyle")
}

// Define the group and version for the project
group = "com.emse.spring"
version = "0.0.1-SNAPSHOT"

// Specify the Java version to use for the project
java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21) // Use Java 21
	}
}

// Define the repositories to search for dependencies
repositories {
	mavenCentral() // Use Maven Central repository
}

// Project metadata
val author: String = "Abdelaziz Mohamad" // Author of the project
val projectDate: String = "2024-10-21" // Date of project creation
val license: String = "MIT" // Project license

dependencies {
	// Spring Boot dependencies
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA for database access
	implementation("org.springframework.boot:spring-boot-starter-web") // Spring Web starter
	runtimeOnly("com.h2database:h2") // H2 database for runtime
	testImplementation("org.springframework.boot:spring-boot-starter-test") // Testing support
	testRuntimeOnly("org.junit.platform:junit-platform-launcher") // JUnit platform launcher
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0") // SpringDoc for API documentation
}


// Configure Checkstyle
tasks.withType<Checkstyle> {
	configFile = file("config/checkstyle/checkstyle.xml")
}

// Task to generate a default Checkstyle configuration file if it doesn't exist
tasks.register("generateCheckstyleConfig") {
	doLast {
		val configDir = file("config/checkstyle")
		if (!configDir.exists()) {
			configDir.mkdirs()
		}
		val configFile = file("config/checkstyle/checkstyle.xml")
		if (!configFile.exists()) {
			configFile.writeText(
				"""
                <!DOCTYPE module PUBLIC
                    "-//Checkstyle//DTD Checkstyle Configuration 1.3//EN"
                    "https://checkstyle.org/dtds/configuration_1_3.dtd">
                <module name="Checker">
                    <module name="TreeWalker">
                        <module name="JavadocMethod"/>
                    </module>
                </module>
                """.trimIndent()
			)
		}
	}
}



// Configure the test tasks
tasks.withType<Test> {
	useJUnitPlatform() // Use JUnit platform for testing
}
