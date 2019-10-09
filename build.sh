#!/bin/bash

rm -rf node_modules

npm install --ignore-scripts
npm rebuild
npm run-script install
