Pod::Spec.new do |spec|

  spec.name     = "DialogCalls-GRPC-objc"
  spec.module_name = "DialogCalls_GRPC_objc"
  spec.version  = "2.22.3"
  spec.license  = "Apache License, Version 2.0"
  spec.authors  = { "Dialog contributors" => "info@dlg.im" }
  spec.homepage = "https://dlg.im/"
  spec.summary = "This package contains Dialog's calls gRPC models and services"
  spec.source = { :git => "https://bitbucket.transmit.im/scm/calls/server-api-calls-sdk.git" }

  spec.ios.deployment_target = "11.0"
  spec.requires_arc = true
  spec.dependency "!ProtoCompiler-gRPCPlugin", "~> 1.0"
  
  # Base directory where the .proto files are.
  src = "."

  pods_root = 'Pods'

  dependencies = 'dependencies/objc'

  # Path where Cocoapods downloads protoc and the gRPC plugin.
  protoc_dir = "dependencies"
  protoc = "#{protoc_dir}/protoc"
  plugin = "#{dependencies}/grpc_objective_c_plugin"

  # Directory where the generated files will be placed.
  dir = "#{pods_root}/#{spec.name}"

  spec.prepare_command = <<-CMD
    mkdir -p #{dir}
    #{protoc} \
        --plugin=protoc-gen-grpc=#{plugin} \
        --objc_out=#{dir} \
        --grpc_out=#{dir} \
        -I #{src} \
        -I #{protoc_dir} \
        #{src}/*.proto
  CMD


  # Files generated by protoc
  spec.subspec "Messages" do |ms|
    ms.source_files = "#{dir}/*.pbobjc.{h,m}", "#{dir}/**/*.pbobjc.{h,m}"
    ms.header_mappings_dir = dir
    ms.requires_arc = false
    # The generated files depend on the protobuf runtime.
    ms.dependency "Protobuf"
  end

  # Files generated by the gRPC plugin
  spec.subspec "Services" do |ss|
    ss.source_files = "#{dir}/*.pbrpc.{h,m}", "#{dir}/**/*.pbrpc.{h,m}"
    ss.header_mappings_dir = dir
    ss.requires_arc = true
    # The generated files depend on the gRPC runtime, and on the files generated by protoc.
    ss.dependency "gRPC-ProtoRPC"
    ss.dependency "#{spec.name}/Messages"
  end

  spec.pod_target_xcconfig = {
    # This is needed by all pods that depend on Protobuf:
    'GCC_PREPROCESSOR_DEFINITIONS' => '$(inherited) GPB_USE_PROTOBUF_FRAMEWORK_IMPORTS=1',
    # This is needed by all pods that depend on gRPC-RxLibrary:
    'CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES' => 'YES',
  }
end