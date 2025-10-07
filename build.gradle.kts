import com.google.protobuf.gradle.*

plugins {
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.graalvm.buildtools.native") version "0.10.2"
	id("com.google.protobuf") version "0.9.4"
}

group = "com.example.life"
version = "1.0.0"
description = "Life API - Personal data aggregation and metrics tracking"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework:spring-webflux")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.springframework.data:spring-data-commons")

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-registry-prometheus")

	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.postgresql:r2dbc-postgresql")

	implementation("io.lettuce:lettuce-core")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.expediagroup", "graphql-kotlin-spring-server", "7.1.1")

	// gRPC
	implementation("io.grpc:grpc-kotlin-stub:1.4.1")
	implementation("io.grpc:grpc-protobuf:1.66.0")
	implementation("io.grpc:grpc-okhttp:1.66.0")
	implementation("com.google.protobuf:protobuf-java:4.27.3")
	implementation("io.grpc:grpc-stub:1.66.0")

	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("io.r2dbc:r2dbc-h2")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:4.27.3" // Use the latest version of protoc
	}
	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.66.0" // Latest gRPC version
		}
		id("grpckt") {
			artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.1:jdk8@jar" // Latest gRPC Kotlin version
		}
	}
	generateProtoTasks {
		all().forEach { task ->
			task.plugins {
				id("grpc")
				id("grpckt")
			}
		}
	}
}

sourceSets {
	main {
		java {
			srcDirs("build/generated/source/proto/main/grpc", "build/generated/source/proto/main/grpckt")
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	systemProperty("spring.profiles.active", "test")
}
