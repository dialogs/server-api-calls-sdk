rm -f -rf ./Sources/DialogCalls-GRPC/

mkdir -p Sources/DialogCalls-GRPC 

./ios/protoCompiler/protoc *.proto \
--plugin=protoc-gen-swift=./ios/protoCompilerPlugins/protoc-gen-swift \
--plugin=protoc-gen-swiftgrpc=./ios/protoCompilerPlugins/protoc-gen-swiftgrpc \
--swift_opt=Visibility=Public,FileNaming=DropPath \
--swiftgrpc_opt=Visibility=Public,FileNaming=DropPath,Client=true,Server=false \
--swift_out=./Sources/DialogCalls-GRPC \
--swiftgrpc_out=./Sources/DialogCalls-GRPC \
-I . \
-I ./swiftDependency/protoCompiler/
