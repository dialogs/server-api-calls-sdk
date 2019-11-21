const gulp = require('gulp');
const gulpClean = require('gulp-clean');
const gulpReplace = require('gulp-replace');
const { series, parallel } = require('gulp');

const fs = require ('fs');
const rx = require ("rxjs/Rx");
const rxjsGrpc = require('rxjs-grpc/js/cli');
const exec = require('child_process').exec;

const packageJson = JSON.parse(fs.readFileSync("./package.json"));

const execute = (cb, command) => exec(command, function (err, stdout, stderr) {
    console.log(stdout);
    console.error(stderr);

    cb(err);
});

const createDirs = cb => {
    
    fs.mkdirSync('./generated', {recursive : true});
    fs.mkdirSync('./build/npm', {recursive: true});
    
    cb ();
};

const clean = () => gulp.src(['./build/*', './generated/*'], {read: false}).pipe(gulpClean());

const grpcCopyProtoToNpm = () => gulp.src('./*.proto')
    .pipe (gulpReplace ('package api;','package im.dlg.sdk.calls.client.history.api;'))
    .pipe (gulp.dest("./build/npm"));

const grpcCopyProtoToGenerated1 = () => gulp.src('./history.proto')
    .pipe (gulpReplace ('package im.dlg.sdk.calls.client.history.api;', 'package api;'))
    .pipe (gulp.dest('./generated'));

const grpcCopyProtoToGenerated2 = () => gulp.src('./server.proto')
    .pipe (gulpReplace ('package im.dlg.sdk.calls.client.p2p.api;', 'package api;'))
    .pipe (gulp.dest('./generated'));

const grpcP2P = () => rxjsGrpc.main(['-o', `./generated/p2p.ts`, `./generated/server.proto`]);
const grpcHistory = () => rxjsGrpc.main(['-o', `./generated/history.ts`, `./generated/history.proto`]);

const grpcReplace = () => gulp
        .src(['./generated/*.ts'])
        
        .pipe(gulpReplace (/.*grpc.*/g, ''))
        .pipe(gulpReplace (/.*Observable.*/g, ''))

        .pipe(gulpReplace ("number|Long|null", 'string|null'))
        .pipe(gulpReplace ("settings?: (api.CE_SETTINGS|null);", 'settings?: (api.ICE_SETTINGS|null);'))
        

        .pipe(gulpReplace('CALL_DIRECTION_UNKNOWN = 0', 'CALL_DIRECTION_UNKNOWN = "CALL_DIRECTION_UNKNOWN"'))
        .pipe(gulpReplace('CALL_DIRECTION_INBOUND = 1', 'CALL_DIRECTION_INBOUND = "CALL_DIRECTION_INBOUND"'))
        .pipe(gulpReplace('CALL_DIRECTION_OUTBOUND = 2', 'CALL_DIRECTION_OUTBOUND = "CALL_DIRECTION_OUTBOUND"'))

        .pipe(gulpReplace('CALL_STATE_UNKNOWN = 0', 'CALL_STATE_UNKNOWN = "CALL_STATE_UNKNOWN"'))
        .pipe(gulpReplace('CALL_STATE_TRYING = 1', 'CALL_STATE_TRYING = "CALL_STATE_TRYING"'))
        .pipe(gulpReplace('CALL_STATE_PROGRESS = 2', 'CALL_STATE_PROGRESS = "CALL_STATE_PROGRESS"'))
        .pipe(gulpReplace('CALL_STATE_RINGING = 3', 'CALL_STATE_RINGING = "CALL_STATE_RINGING"'))
        .pipe(gulpReplace('CALL_STATE_TALKING = 4', 'CALL_STATE_TALKING = "CALL_STATE_TALKING"'))
        .pipe(gulpReplace('CALL_STATE_FINISHED = 5', 'CALL_STATE_FINISHED = "CALL_STATE_FINISHED"'))

        .pipe(gulpReplace('DISPOSE_REASON_UNKNOWN = 0', 'DISPOSE_REASON_UNKNOWN = "DISPOSE_REASON_UNKNOWN"'))
        .pipe(gulpReplace('DISPOSE_REASON_ERROR = 1', 'DISPOSE_REASON_ERROR = "DISPOSE_REASON_ERROR"'))
        .pipe(gulpReplace('DISPOSE_REASON_CALL_DOES_NOT_EXISTS = 2', 'DISPOSE_REASON_CALL_DOES_NOT_EXISTS = "DISPOSE_REASON_CALL_DOES_NOT_EXISTS"'))
        .pipe(gulpReplace('DISPOSE_REASON_PICKED_UP = 3', 'DISPOSE_REASON_PICKED_UP = "DISPOSE_REASON_PICKED_UP"'))
        .pipe(gulpReplace('DISPOSE_REASON_NORMAL = 4', 'DISPOSE_REASON_NORMAL = "DISPOSE_REASON_NORMAL"'))
        .pipe(gulpReplace('DISPOSE_REASON_TRANSFER = 5', 'DISPOSE_REASON_TRANSFER = "DISPOSE_REASON_TRANSFER"'))
        .pipe(gulpReplace('DISPOSE_REASON_CALLEE_NOT_FOUND = 6', 'DISPOSE_REASON_CALLEE_NOT_FOUND = "DISPOSE_REASON_CALLEE_NOT_FOUND"'))
        .pipe(gulpReplace('DISPOSE_REASON_NO_ANSWER = 7', 'DISPOSE_REASON_NO_ANSWER = "DISPOSE_REASON_NO_ANSWER"'))
        .pipe(gulpReplace('DISPOSE_REASON_BUSY = 8', 'DISPOSE_REASON_BUSY = "DISPOSE_REASON_BUSY"'))
        .pipe(gulpReplace('DISPOSE_REASON_REJECTED = 9', 'DISPOSE_REASON_REJECTED = "DISPOSE_REASON_REJECTED"'))
        .pipe(gulpReplace('DISPOSE_REASON_CALLER_DISABLED = 10', 'DISPOSE_REASON_CALLER_DISABLED = "DISPOSE_REASON_CALLER_DISABLED"'))
        .pipe(gulpReplace('DISPOSE_REASON_CALLEE_DISABLED = 11', 'DISPOSE_REASON_CALLEE_DISABLED = "DISPOSE_REASON_CALLEE_DISABLED"'))

        .pipe(gulpReplace('HANGUP_REASON_UNKNOWN = 0', 'HANGUP_REASON_UNKNOWN = "HANGUP_REASON_UNKNOWN"'))
        .pipe(gulpReplace('HANGUP_REASON_ERROR = 1', 'HANGUP_REASON_ERROR = "HANGUP_REASON_ERROR"'))
        .pipe(gulpReplace('HANGUP_REASON_NORMAL = 2', 'HANGUP_REASON_NORMAL = "HANGUP_REASON_NORMAL"'))
        .pipe(gulpReplace('HANGUP_REASON_BUSY = 3', 'HANGUP_REASON_BUSY = "HANGUP_REASON_BUSY"'))
        .pipe(gulpReplace('HANGUP_REASON_REJECT = 4', 'HANGUP_REASON_REJECT = "HANGUP_REASON_REJECT"'))

        .pipe(gulpReplace('ICE_SETTINGS_UNKNOWN = 0', 'ICE_SETTINGS_UNKNOWN = "ICE_SETTINGS_UNKNOWN"'))
        .pipe(gulpReplace('ICE_SETTINGS_NONE = 1', 'ICE_SETTINGS_NONE = "ICE_SETTINGS_NONE"'))
        .pipe(gulpReplace('ICE_SETTINGS_RELAY = 2', 'ICE_SETTINGS_RELAY = "ICE_SETTINGS_RELAY"'))
        .pipe(gulpReplace('ICE_SETTINGS_ALL = 3', 'ICE_SETTINGS_ALL = "ICE_SETTINGS_ALL"'))
   
        .pipe(gulp.dest('./generated/'));

const generatePackageJson = cb => {
    fs.writeFileSync("./build/npm/package.json", JSON.stringify({
        name : packageJson.name,
        version : packageJson.version,
        author: packageJson.author
    }, null, 4));
    
    cb ();
};

const compileTs = cb => execute (cb, 'node node_modules/typescript/bin/tsc --extendedDiagnostics -p ./tsc.json');

exports.default = series (
    createDirs,
    clean,
    createDirs,
    parallel (
        generatePackageJson,
        grpcCopyProtoToNpm,
        series (
            grpcCopyProtoToGenerated1,
            grpcCopyProtoToGenerated2,
            parallel (
                grpcP2P,
                grpcHistory
            ),
            grpcReplace,
            compileTs
        )
    )
);