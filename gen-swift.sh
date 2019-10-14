rm -f -rf ./Sources/DialogCalls-GRPC/

mkdir -p Sources/DialogCalls-GRPC 

./swiftDependency/protoCompiler/protoc *.proto \
--plugin=protoc-gen-swift=./swiftDependency/Proto/protoc-gen-swift \
--plugin=protoc-gen-swiftgrpc=./swiftDependency/GRPC/protoc-gen-swiftgrpc \
--swift_out=FileNaming=DropPath,Visibility=Public:Sources/DialogCalls-GRPC \
--swiftgrpc_out=Client=true,Server=false,TestStubs=true,FileNaming=DropPath,Visibility=Public:Sources/DialogCalls-GRPC \
-I ./swiftDependency/protoCompiler/ \
-I .
