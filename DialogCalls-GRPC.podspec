Pod::Spec.new do |spec|

  spec.name     = "DialogCalls-GRPC"
  spec.module_name = "DialogCalls-GRPC"
  spec.version  = "2.5.0"
  spec.license  = "Apache License, Version 2.0"
  spec.authors  = { "Dialog contributors" => "info@dlg.im" }
  spec.homepage = "https://dlg.im/"
  spec.summary = "This package contains Dialog's calls gRPC models and services"
  spec.source = { :git => "https://bitbucket.transmit.im/scm/calls/server-api-calls-sdk.git",
                  :tag => spec.version }

  spec.ios.deployment_target = "11.0"
  spec.requires_arc = true

  spec.dependency "SwiftGRPC"
  spec.dependency 'SwiftProtobuf'

  # Base directory where the .proto files are.
  src = "."

  # Pods directory corresponding to this app's Podfile, relative to the location of this podspec.
  dependencies = 'swiftDependency'

  # Path where Cocoapods downloads protoc and the gRPC plugin.
  protoc_dir = "#{dependencies}/protoCompiler"

  protoc = "#{protoc_dir}/protoc"
  swiftPlugin = "#{dependencies}/Proto/protoc-gen-swift"
  swiftGrpcPlugin = "#{dependencies}/GRPC/protoc-gen-swiftgrpc"

  # Directory where the generated files will be placed.
  dir = "Sources/#{spec.name}"

  spec.prepare_command = <<-CMD
    rm -f -rf #{dir}

    mkdir -p #{dir}

    #{protoc} #{src}/*.proto \
        --plugin=#{swiftPlugin} \
        --plugin=#{swiftGrpcPlugin} \
        --swift_out=FileNaming=DropPath,Visibility=Public:#{dir} \
        --swiftgrpc_out=Client=true,Server=false,TestStubs=true,FileNaming=DropPath,Visibility=Public:#{dir} \
        -I #{protoc_dir} \
        -I #{src} \

  CMD

  # Files generated by protoc
  spec.subspec "Messages" do |msgSpec|
  msgSpec.source_files = "#{dir}/**/*.pb.swift"
  msgSpec.header_mappings_dir = dir
  msgSpec.requires_arc = true
  end

  # Files generated by the gRPC plugin
  spec.subspec "Services" do |srvSpec|
  srvSpec.source_files = "#{dir}/**/*.grpc.swift"
  srvSpec.header_mappings_dir = dir
  srvSpec.requires_arc = true
  srvSpec.dependency "#{spec.name}/Messages"
  end

  spec.pod_target_xcconfig = {
    # This is needed by all pods that depend on Protobuf:
    "GCC_PREPROCESSOR_DEFINITIONS" => "$(inherited) GPB_USE_PROTOBUF_FRAMEWORK_IMPORTS=1",
    # This is needed by all pods that depend on gRPC-RxLibrary:
    "CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES" => "YES",
  }
end
