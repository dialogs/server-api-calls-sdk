#!/bin/bash

export PACKAGE_VERSION=$(grep '"version"' package.json | cut -d '"' -f 4)
cd build/npm
export VERSION=$PACKAGE_VERSION-$(git rev-parse --short HEAD)-$(date +%s)
sed -Ei 's/.*version.*/    "version" :"'$VERSION'",/g' package.json
npm publish --tag=$(git rev-parse --short HEAD)-latest
