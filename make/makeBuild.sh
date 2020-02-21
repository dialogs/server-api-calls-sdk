#!/bin/bash

npm install --ignore-scripts;
npm rebuild;
npm run-script install;
grep "version" build/npm/package.json | cut -d '"' -f 4;