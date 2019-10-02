// swift-tools-version:5.0

import PackageDescription

let package = Package(
    name: "CallsGRPC",
    platforms: [
        .macOS(.v10_12),
        .iOS(.v11),
        .tvOS(.v11),
        .watchOS(.v4)
    ],
    products: [
        .library(
            name: "CallsGRPC",
            targets: ["CallsGRPC"]),
    ],
    dependencies: [
        .package(url: "https://github.com/grpc/grpc-swift.git", .exact("1.0.0-alpha.5"))
    ],
    targets: [
        .target(
            name: "CallsGRPC",
            dependencies: ["GRPC"]),
    ]
)
