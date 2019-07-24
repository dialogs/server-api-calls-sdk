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

        
        .pipe(gulpReplace('CALL_DIRECTION_UNKNOWN = 0', 'CALL_DIRECTION_UNKNOWN = "CALL_DIRECTION_UNKNOWN"'))
        .pipe(gulpReplace('CALL_DIRECTION_INBOUND = 1', 'CALL_DIRECTION_INBOUND = "CALL_DIRECTION_INBOUND"'))
        .pipe(gulpReplace('CALL_DIRECTION_OUTBOUND = 2', 'CALL_DIRECTION_OUTBOUND = "CALL_DIRECTION_OUTBOUND"'))

        .pipe(gulpReplace('CALL_STATE_UNKNOWN = 0', 'CALL_STATE_UNKNOWN = "CALL_STATE_UNKNOWN"'))
        .pipe(gulpReplace('CALL_STATE_TRIYNG = 1', 'CALL_STATE_TRIYNG = "CALL_STATE_TRIYNG"'))
        .pipe(gulpReplace('CALL_STATE_PROGRESS = 2', 'CALL_STATE_PROGRESS = "CALL_STATE_PROGRESS"'))
        .pipe(gulpReplace('CALL_STATE_RINGING = 3', 'CALL_STATE_RINGING = "CALL_STATE_RINGING"'))
        .pipe(gulpReplace('CALL_STATE_TALKING = 4', 'CALL_STATE_TALKING = "CALL_STATE_TALKING"'))
        .pipe(gulpReplace('CALL_STATE_FINISHED = 5', 'CALL_STATE_FINISHED = "CALL_STATE_FINISHED"'))

        .pipe(gulpReplace('DISPOSE_REASON_UNKNOWN = 0', 'DISPOSE_REASON_UNKNOWN = "DISPOSE_REASON_UNKNOWN"'))
        .pipe(gulpReplace('DISPOSE_REASON_ERROR = 1', 'DISPOSE_REASON_ERROR = "DISPOSE_REASON_ERROR"'))
        .pipe(gulpReplace('DISPOSE_REASON_CALL_DOES_NOT_EXISTS = 2', 'DISPOSE_REASON_CALL_DOES_NOT_EXISTS = "DISPOSE_REASON_CALL_DOES_NOT_EXISTS"'))
        .pipe(gulpReplace('DISPOSE_REASON_PICKED_UP = 3', 'DISPOSE_REASON_PICKED_UP = "DISPOSE_REASON_PICKED_UP"'))
        .pipe(gulpReplace('DISPOSE_REASON_NORMAL = 4', 'DISPOSE_REASON_NORMAL = "DISPOSE_REASON_NORMAL"'))
        .pipe(gulpReplace('DISPOSE_REASON_NOT_FOUND = 5', 'DISPOSE_REASON_NOT_FOUND = "DISPOSE_REASON_NOT_FOUND"'))
        .pipe(gulpReplace('DISPOSE_REASON_NO_ANSWER = 6', 'DISPOSE_REASON_NO_ANSWER = "DISPOSE_REASON_NO_ANSWER"'))
        .pipe(gulpReplace('DISPOSE_REASON_BUSY = 7', 'DISPOSE_REASON_BUSY = "DISPOSE_REASON_BUSY"'))
        .pipe(gulpReplace('DISPOSE_REASON_REJECTED = 8', 'DISPOSE_REASON_REJECTED = "DISPOSE_REASON_REJECTED"'))

        .pipe(gulpReplace('HANGUP_REASON_UNKNOWN = 0', 'HANGUP_REASON_UNKNOWN = "HANGUP_REASON_UNKNOWN"'))
        .pipe(gulpReplace('HANGUP_REASON_ERROR = 1', 'HANGUP_REASON_ERROR = "HANGUP_REASON_ERROR"'))
        .pipe(gulpReplace('HANGUP_REASON_NORMAL = 2', 'HANGUP_REASON_NORMAL = "HANGUP_REASON_NORMAL"'))
        .pipe(gulpReplace('HANGUP_REASON_BUSY = 3', 'HANGUP_REASON_BUSY = "HANGUP_REASON_BUSY"'))
        .pipe(gulpReplace('HANGUP_REASON_REJECTED = 4', 'HANGUP_REASON_REJECTED = "HANGUP_REASON_REJECTED"'))

        .pipe(gulpReplace('ICE_SETTINGS_UNKNOWN = 0', 'ICE_SETTINGS_UNKNOWN = "ICE_SETTINGS_UNKNOWN"'))
        .pipe(gulpReplace('ICE_SETTINGS_NONE = 1', 'ICE_SETTINGS_NONE = "ICE_SETTINGS_NONE"'))
        .pipe(gulpReplace('ICE_SETTINGS_RELAY = 2', 'ICE_SETTINGS_RELAY = "ICE_SETTINGS_RELAY"'))
        .pipe(gulpReplace('ICE_SETTINGS_ALL = 3', 'ICE_SETTINGS_ALL = "ICE_SETTINGS_ALL"'))
   
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
