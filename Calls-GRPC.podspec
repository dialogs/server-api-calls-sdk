Pod::Spec.new do |s|
  s.name     = "Calls-GRPC"
  s.version  = "0.0.2"
  s.license  = "Apache License, Version 2.0"
  s.authors  = { 'gRPC contributors' => 'grpc-io@googlegroups.com' }
  s.homepage = "https://grpc.io/"
  s.summary = "Calls-GRPC"
  s.source = { :git => 'https://gitlab.transmit.im/sdk/calls/server-api-calls-sdk' }

  s.ios.deployment_target = "10.0"

  # Base directory where the .proto files are.
  src = "."

  # Run protoc with the Objective-C and gRPC plugins to generate protocol messages and gRPC clients.
  s.dependency "!ProtoCompiler-gRPCPlugin", "~> 1.0"

  # Pods directory corresponding to this app's Podfile, relative to the location of this podspec.
  dependencies = 'swift_dependency'

  # Path where Cocoapods downloads protoc and the gRPC plugin.
  api_import = "include"
  protoc_dir = "#{dependencies}/!ProtoCompiler"
  protoc = "#{protoc_dir}/protoc"
  plugin = "#{dependencies}/!ProtoCompiler-gRPCPlugin/grpc_objective_c_plugin"

  # Directory where the generated files will be placed.
  dir = "#{s.name}"

  s.prepare_command = <<-CMD
    mkdir -p #{dir}

    #{protoc} \
        --plugin=protoc-gen-grpc=#{plugin} \
        --objc_out=#{dir}\
        --grpc_out=#{dir} \
        -I #{src} \
        -I #{protoc_dir} \
        #{src}/*.proto
    CMD

  ### TODO: default instruction above s.prepare_command  sometimes not executed causing absense of DialogSDK_GRPC pod module during Xcode compilation
  ### So put additional invocation to guarantee right installation
  test = "<<-CMD
    mkdir -p #{dir}
    #{protoc} \
        --plugin=protoc-gen-grpc=#{plugin} \
        --objc_out=#{dir} \
        --grpc_out=#{dir} \
        -I #{src} \
        -I #{protoc_dir} \
        #{src}/server.proto
  CMD"

    # Files generated by protoc
    s.subspec "Messages" do |ms|
    ms.source_files = "#{dir}/*.pbobjc.{h,m}", "#{dir}/**/*.pbobjc.{h,m}"
    ms.header_mappings_dir = dir
    ms.requires_arc = false
    # The generated files depend on the protobuf runtime.
    ms.dependency "Protobuf"
    end

    # Files generated by the gRPC plugin
    s.subspec "Services" do |ss|
    ss.source_files = "#{dir}/*.pbrpc.{h,m}", "#{dir}/**/*.pbrpc.{h,m}"
    ss.header_mappings_dir = dir
    ss.requires_arc = true
    # The generated files depend on the gRPC runtime, and on the files generated by protoc.
    ss.dependency "gRPC-ProtoRPC"
    ss.dependency "#{s.name}/Messages"
    end

  s.pod_target_xcconfig = {
    # This is needed by all pods that depend on Protobuf:
    'GCC_PREPROCESSOR_DEFINITIONS' => '$(inherited) GPB_USE_PROTOBUF_FRAMEWORK_IMPORTS=1',
    # This is needed by all pods that depend on gRPC-RxLibrary:
    'CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES' => 'YES',
  }
end
