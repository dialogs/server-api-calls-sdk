const fs = require ('fs');
const rx = require ("rxjs/Rx");
const rxjsGrpc = require('rxjs-grpc/js/cli');

const gulp = require('gulp');
const gulpClean = require('gulp-clean');
const gulpSequence = require('gulp-sequence');
const gulpReplace = require('gulp-replace');

const packageJson = JSON.parse(fs.readFileSync("package.json"));

gulp.task('clean',['createDirs'], function () {
    return gulp.src(['npm/*', 'generated/*'], {read: false})
        .pipe(gulpClean());
});

gulp.task ('createDirs', function (cb) {
    function createDir (dir) {
        if (!fs.existsSync(dir)) {
            fs.mkdirSync(dir);
        }
    }
    
    createDir ('generated');
    createDir ('npm');
    
    return cb ();
});

// =============================================================================
// GRPC
// =============================================================================

gulp.task('grpc-copy-proto-to-generated', function () {
    return gulp.src('./*.proto')
            .pipe (gulp.dest('./generated/'));
});

gulp.task('grpc-remove-package-from-proto',['grpc-copy-proto-to-generated'], function () {
    return gulp.src('./generated/*.proto')
            .pipe (gulpReplace (/package.*/, 'package api;'))
            .pipe (gulp.dest('./generated/'));
});

gulp.task ('grpc-generate',['grpc-remove-package-from-proto'], function (cb) {
    fs.readdir ("generated", function (error, files ) {
        if (error) {
            cb (error);
        }
        else {
            rx.Observable.from (files)
                .filter (file => file.toLowerCase().endsWith (".proto"))
                .map (file => file.replace(".proto",""))
                .flatMap (file => rx.Observable.fromPromise (rxjsGrpc.main(['-o', `./generated/index.ts`, `./generated/${file}.proto`])))
                .toArray ()
                .subscribe (()=> cb (), cb);
        }
    });
});

gulp.task ('grpc-replace-enum', ['grpc-generate'], function () {
    return gulp
        .src(['./generated/*.ts'])
        
        .pipe(gulpReplace("import * as $protobuf from 'protobufjs';", ''))
        .pipe(gulpReplace("import { Observable } from 'rxjs/Observable';", "import { Observable } from 'rxjs/Rx';"))

        
        .pipe(gulpReplace('INBOUND = 1', 'INBOUND = "INBOUND"'))
        .pipe(gulpReplace('OUTBOUND = 2', 'OUTBOUND = "OUTBOUND"'))

        .pipe(gulpReplace('TRIYNG = 1', 'TRIYNG = "TRIYNG"'))
        .pipe(gulpReplace('PROGRESS = 2', 'PROGRESS = "PROGRESS"'))
        .pipe(gulpReplace('RINGING = 3', 'RINGING = "RINGING"'))
        .pipe(gulpReplace('TALKING = 4', 'TALKING = "TALKING"'))
        .pipe(gulpReplace('FINISHED = 5', 'FINISHED = "FINISHED"'))

        .pipe(gulpReplace('DISPOSE_REASON_UNKNOWN = 0', 'DISPOSE_REASON_UNKNOWN = "DISPOSE_REASON_UNKNOWN"'))
        .pipe(gulpReplace('SERVER_ERROR = 1', 'SERVER_ERROR = "SERVER_ERROR"'))
        .pipe(gulpReplace('NORMAL = 2', 'NORMAL = "NORMAL"'))
        .pipe(gulpReplace('CALLEE_NOT_FOUND = 3', 'CALLEE_NOT_FOUND = "CALLEE_NOT_FOUND"'))
        .pipe(gulpReplace('CALLER_DISABLED = 4', 'CALLER_DISABLED = "CALLER_DISABLED"'))
        .pipe(gulpReplace('CALLEE_DISABLED = 5', 'CALLEE_DISABLED = "CALLEE_DISABLED"'))
        .pipe(gulpReplace('NO_ANSWER = 6', 'NO_ANSWER = "NO_ANSWER"'))
        .pipe(gulpReplace('BUSY = 7', 'BUSY = "BUSY"'))
        .pipe(gulpReplace('PICKED_UP = 8', 'PICKED_UP = "PICKED_UP"'))
        .pipe(gulpReplace('CALL_DOES_NOT_EXISTS = 9', 'CALL_DOES_NOT_EXISTS = "CALL_DOES_NOT_EXISTS"'))
        .pipe(gulpReplace('REJECTED = 10', 'REJECTED = "REJECTED"'))
        .pipe(gulpReplace('INVALID_IP_RANGE = 11', 'INVALID_IP_RANGE = "INVALID_IP_RANGE"'))

        .pipe(gulpReplace('RELAY = 1', 'RELAY = "RELAY"'))
        .pipe(gulpReplace('ALL = 2', 'ALL = "ALL"'))
        .pipe(gulpReplace('NONE = 3', 'NONE = "NONE"'))

        .pipe(gulpReplace('USER = 1', 'USER = "USER"'))
        .pipe(gulpReplace('GROUP = 2', 'GROUP = "GROUP"'))
   
        .pipe(gulp.dest('./generated/'));    
});

gulp.task('grpc',['grpc-replace-enum'], function () {
    return gulp.src('./generated/*.ts')
        .pipe (gulpReplace ('(number|$protobuf.Long)', 'string'))
        .pipe (gulp.dest('./generated/'));
});

gulp.task('grpc-copy-proto-to-npm', function () {
    return gulp.src('./server.proto')
            .pipe(gulp.dest("./npm"));
});

gulp.task('generate-package.json', function (cb) {
    fs.writeFileSync("npm/package.json", JSON.stringify({
        name : packageJson.name,
        version : "VERSION",
        main: "index.js"
    }, null, 4));
    
    cb ();
});

// =============================================================================
// Build
// =============================================================================

gulp.task('build',['grpc-copy-proto-to-npm','grpc','generate-package.json']);

// =============================================================================
// Default
// =============================================================================

gulp.task('default', gulpSequence('clean','createDirs','build'));
