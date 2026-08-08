# Project2B - Jenkins CI/CD Pipeline

## Repository Status
This repository contains the final merged version of Project 2B after CI/CD pipeline validation.

## Technologies Used
- Java 17
- Maven 3.9.10
- Jenkins Pipeline
- JUnit 5
- JaCoCo
- SonarQube 10.7

## Pipeline Stages
1. Build & Test
2. SonarQube Analysis
3. Quality Gate
4. Publish JUnit Results
5. Publish JaCoCo Coverage Report

## Features Demonstrated
- Automated CI pipeline using Jenkins Pipeline
- Poll SCM automatic build trigger
- Automated JUnit test execution
- JaCoCo code coverage reporting
- SonarQube static code analysis
- SonarQube Quality Gate verification

This project demonstrates a Continuous Integration (CI) pipeline using Jenkins Pipeline, Maven, JUnit, JaCoCo, SonarQube, and Quality Gate verification. Every commit pushed to the repository automatically triggers the pipeline, which builds the project, executes unit tests, performs static code analysis, validates the Quality Gate, and publishes the test and code coverage reports.