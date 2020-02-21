#!/bin/bash

export PACKAGE_VERSION=$(grep '"version"' package.json | cut -d '"' -f 4)
cd build/npm
export VERSION=$PACKAGE_VERSION-RELEASE
sed -Ei 's/.*version.*/    "version" :"'$VERSION'",/g' package.json
npm publish --tag=RELEASE-latest
