run-dist:
		./build/install/java-project-lvl3/bin/java-project-lvl3
build:
	./gradlew clean build
report:
	./gradlew jacocoTestReport
.PHONY: build