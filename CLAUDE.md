# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Tech Stack

- **Language**: Kotlin 1.9.25
- **Framework**: Spring Boot 3.5.6 with Spring Data JDBC
- **Build Tool**: Gradle 8.14.3 with Kotlin DSL
- **UI**: Thymeleaf templates with HTMX for interactivity
- **JVM**: Java 17
- **Testing**: JUnit 5 with Spring Boot Test

## Development Commands

### Build and Run
```bash
# Run the application
./gradlew bootRun

# Build the project
./gradlew build

# Clean build
./gradlew clean build
```

### Code Quality
```bash
# Check code formatting (runs automatically on build)
./gradlew ktlintCheck

# Format code automatically
./gradlew ktlintFormat

# IMPORTANT: Always run before committing
./gradlew ktlintCheck
```

### Testing
```bash
# Run all tests
./gradlew test

# Run a single test class (use --tests flag)
./gradlew test --tests "com.github.dizk.almanakk.AlmanakkApplicationTests"

# Run tests with specific method
./gradlew test --tests "com.github.dizk.almanakk.AlmanakkApplicationTests.contextLoads"
```

### Development Workflow
```bash
# Check all verification tasks (includes tests)
./gradlew check

# Build JAR file
./gradlew bootJar

# Run with test classpath (useful for testing configurations)
./gradlew bootTestRun
```

## Project Structure

The application follows Spring Boot conventions with a Kotlin codebase:

- **Main application**: `src/main/kotlin/com/github/dizk/almanakk/` - Spring Boot application entry point
- **Resources**: `src/main/resources/` - Contains `application.properties`, static assets, and Thymeleaf templates
- **Tests**: `src/test/kotlin/com/github/dizk/almanakk/` - Unit and integration tests

## Architecture Overview

Almanakk is a read-only database browser application that allows users to navigate through database tables by following foreign key relationships, similar to browsing an almanac. The application:

1. Connects to PostgreSQL or MySQL databases (read-only mode)
2. Provides a web interface using Thymeleaf templates with HTMX for dynamic interactions
3. Allows navigation through foreign key relationships with breadcrumb tracking
4. Supports virtual foreign key definitions when database lacks proper constraints

## Development Guidelines

### Pure Functions and Side Effects
- Write pure functions wherever possible
- Push side effects to the boundary of the application (controllers, repositories)
- Separate business logic from I/O operations

### Testing Strategy
- Write unit tests for all business logic
- Don't write integration tests
- Focus on testing pure functions in isolation
- Never use mocks

### Spring Boot Patterns
- Use constructor-based dependency injection (Kotlin primary constructors)
- Keep controllers thin - delegate business logic to services
- Use data classes for DTOs and value objects

### Kotlin Best Practices
- Prefer `val` over `var` for immutability
- Use data classes for simple data holders
- Leverage Kotlin's null safety features
- Use extension functions to enhance readability
- Prefer expression bodies for simple functions