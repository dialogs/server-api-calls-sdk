DOCKER_RUNNER ?= node12-git-java
# DOCKER_RUNNER_IMAGE_NAME ?= calls-server-builder
DOCKER_BUILDER ?= minimal-node12
# DOCKER_FILE_BUILD ?= src/docker/deploy/Dockerfile.rhel7
DOCKER_BUILD_WORKSPACE_SUBDIR ?= dist
DOCKER_BUILD_WORKSPACE_DIR ?= .

NPM_TOKEN=1059b7d9-fe22-42d4-966e-eff346622cdb
NPM_REGISTRY=//registry.npmjs.org

NEXUS_SOURCE_REPOSITORY_USERNAME ?= dialogops
NEXUS_SOURCE_REPOSITORY_PASSWORD ?= 3Zw[w9s?}4F[92edBTLR

NODEJS_SOURCE ?= https://nodejs.org/dist/v12.13.0/node-v12.13.0-linux-x64.tar.gz
NODEJS_SOURCE_FILE ?= $(lastword $(subst /, ,$(NODEJS_SOURCE)))

makelib.inc:
	@curl https://bitbucket.transmit.im/projects/DVPS/repos/public/raw/makelib.inc?at=refs%2Fheads%2Fmaster -Lo makelib.inc
include makelib.inc

.PHONY: npm-publish npm-publish-release build

define ubi-node12-git-java
$(ubi-node12)\n\
RUN yum install -y git java\n
endef

# deps/$(NODEJS_SOURCE_FILE)
build:
	$(call run-in-docker, \
	npm config set //$(NPM_SOURCE_REGISTRY)/:_authToken $(NPM_SOURCE_REGISTRY_TOKEN); \
	npm install; \
	exec ./make/makeBuild.sh; \
	)

build-gradle:
	$(call run-in-docker, \
	echo "mavenUser=$(NEXUS_SOURCE_REPOSITORY_USERNAME)" > gradle.properties; \
	echo "mavenPassword=$(NEXUS_SOURCE_REPOSITORY_PASSWORD)" >> gradle.properties; \
	./gradlew build --stacktrace; \
	)

npm-publish:
	$(call run-in-docker, \
	npm config set //$(NPM_SOURCE_REGISTRY)/:_authToken $(NPM_SOURCE_REGISTRY_TOKEN); \
	exec ./make/makePublish.sh; \
	)

android-publish:
	$(call run-in-docker, \
	echo "mavenUser=$(NEXUS_SOURCE_REPOSITORY_USERNAME)" > gradle.properties; \
	echo "mavenPassword=$(NEXUS_SOURCE_REPOSITORY_PASSWORD)" >> gradle.properties; \
	./gradlew publish --stacktrace; \
	)

npm-publish-release:
	$(call run-in-docker, \
	npm config set //$(NPM_SOURCE_REGISTRY)/:_authToken $(NPM_SOURCE_REGISTRY_TOKEN); \
	exec ./make/makePublishRelease.sh; \
	)

android-publish-release:
	$(call run-in-docker, \
	echo "mavenUser=$(NEXUS_SOURCE_REPOSITORY_USERNAME)" > gradle.properties; \
	echo "mavenPassword=$(NEXUS_SOURCE_REPOSITORY_PASSWORD)" >> gradle.properties; \
	./gradlew publish --stacktrace; \
	)

deps/$(NODEJS_SOURCE_FILE):
	@mkdir -p deps
	cd deps && curl $(NODEJS_SOURCE) -OL && rm -rf node && mkdir node && cd node && tar xzf ../$(NODEJS_SOURCE_FILE) --strip 1 && rm ../$(NODEJS_SOURCE_FILE)

clean: docker-clean
	rm -rf dist build node_modules deps