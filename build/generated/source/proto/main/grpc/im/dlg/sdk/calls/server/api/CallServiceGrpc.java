package im.dlg.sdk.calls.server.api;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Call Service - main server interface for calls SDK 
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: server.proto")
public final class CallServiceGrpc {

  private CallServiceGrpc() {}

  public static final String SERVICE_NAME = "im.dlg.sdk.calls.CallService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.Empty,
      im.dlg.sdk.calls.server.api.Server.ServerStreamMessage> getConnectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "connect",
      requestType = im.dlg.sdk.calls.server.api.Server.Empty.class,
      responseType = im.dlg.sdk.calls.server.api.Server.ServerStreamMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.Empty,
      im.dlg.sdk.calls.server.api.Server.ServerStreamMessage> getConnectMethod() {
    io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.Empty, im.dlg.sdk.calls.server.api.Server.ServerStreamMessage> getConnectMethod;
    if ((getConnectMethod = CallServiceGrpc.getConnectMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getConnectMethod = CallServiceGrpc.getConnectMethod) == null) {
          CallServiceGrpc.getConnectMethod = getConnectMethod = 
              io.grpc.MethodDescriptor.<im.dlg.sdk.calls.server.api.Server.Empty, im.dlg.sdk.calls.server.api.Server.ServerStreamMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "im.dlg.sdk.calls.CallService", "connect"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.ServerStreamMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("connect"))
                  .build();
          }
        }
     }
     return getConnectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.CreateCallRequest,
      im.dlg.sdk.calls.server.api.Server.CreateCallResponse> getCreateCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createCall",
      requestType = im.dlg.sdk.calls.server.api.Server.CreateCallRequest.class,
      responseType = im.dlg.sdk.calls.server.api.Server.CreateCallResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.CreateCallRequest,
      im.dlg.sdk.calls.server.api.Server.CreateCallResponse> getCreateCallMethod() {
    io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.CreateCallRequest, im.dlg.sdk.calls.server.api.Server.CreateCallResponse> getCreateCallMethod;
    if ((getCreateCallMethod = CallServiceGrpc.getCreateCallMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getCreateCallMethod = CallServiceGrpc.getCreateCallMethod) == null) {
          CallServiceGrpc.getCreateCallMethod = getCreateCallMethod = 
              io.grpc.MethodDescriptor.<im.dlg.sdk.calls.server.api.Server.CreateCallRequest, im.dlg.sdk.calls.server.api.Server.CreateCallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "im.dlg.sdk.calls.CallService", "createCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.CreateCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.CreateCallResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("createCall"))
                  .build();
          }
        }
     }
     return getCreateCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.AnswerCallRequest,
      im.dlg.sdk.calls.server.api.Server.AnswerCallResponse> getAnswerCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "answerCall",
      requestType = im.dlg.sdk.calls.server.api.Server.AnswerCallRequest.class,
      responseType = im.dlg.sdk.calls.server.api.Server.AnswerCallResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.AnswerCallRequest,
      im.dlg.sdk.calls.server.api.Server.AnswerCallResponse> getAnswerCallMethod() {
    io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.AnswerCallRequest, im.dlg.sdk.calls.server.api.Server.AnswerCallResponse> getAnswerCallMethod;
    if ((getAnswerCallMethod = CallServiceGrpc.getAnswerCallMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getAnswerCallMethod = CallServiceGrpc.getAnswerCallMethod) == null) {
          CallServiceGrpc.getAnswerCallMethod = getAnswerCallMethod = 
              io.grpc.MethodDescriptor.<im.dlg.sdk.calls.server.api.Server.AnswerCallRequest, im.dlg.sdk.calls.server.api.Server.AnswerCallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "im.dlg.sdk.calls.CallService", "answerCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.AnswerCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.AnswerCallResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("answerCall"))
                  .build();
          }
        }
     }
     return getAnswerCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.RingingCallRequest,
      im.dlg.sdk.calls.server.api.Server.Empty> getRingingCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ringingCall",
      requestType = im.dlg.sdk.calls.server.api.Server.RingingCallRequest.class,
      responseType = im.dlg.sdk.calls.server.api.Server.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.RingingCallRequest,
      im.dlg.sdk.calls.server.api.Server.Empty> getRingingCallMethod() {
    io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.RingingCallRequest, im.dlg.sdk.calls.server.api.Server.Empty> getRingingCallMethod;
    if ((getRingingCallMethod = CallServiceGrpc.getRingingCallMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getRingingCallMethod = CallServiceGrpc.getRingingCallMethod) == null) {
          CallServiceGrpc.getRingingCallMethod = getRingingCallMethod = 
              io.grpc.MethodDescriptor.<im.dlg.sdk.calls.server.api.Server.RingingCallRequest, im.dlg.sdk.calls.server.api.Server.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "im.dlg.sdk.calls.CallService", "ringingCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.RingingCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("ringingCall"))
                  .build();
          }
        }
     }
     return getRingingCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.JoinCallRequest,
      im.dlg.sdk.calls.server.api.Server.JoinCallResponse> getJoinCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinCall",
      requestType = im.dlg.sdk.calls.server.api.Server.JoinCallRequest.class,
      responseType = im.dlg.sdk.calls.server.api.Server.JoinCallResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.JoinCallRequest,
      im.dlg.sdk.calls.server.api.Server.JoinCallResponse> getJoinCallMethod() {
    io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.JoinCallRequest, im.dlg.sdk.calls.server.api.Server.JoinCallResponse> getJoinCallMethod;
    if ((getJoinCallMethod = CallServiceGrpc.getJoinCallMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getJoinCallMethod = CallServiceGrpc.getJoinCallMethod) == null) {
          CallServiceGrpc.getJoinCallMethod = getJoinCallMethod = 
              io.grpc.MethodDescriptor.<im.dlg.sdk.calls.server.api.Server.JoinCallRequest, im.dlg.sdk.calls.server.api.Server.JoinCallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "im.dlg.sdk.calls.CallService", "joinCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.JoinCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.JoinCallResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("joinCall"))
                  .build();
          }
        }
     }
     return getJoinCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.DisposeCallRequest,
      im.dlg.sdk.calls.server.api.Server.Empty> getDisposeCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "disposeCall",
      requestType = im.dlg.sdk.calls.server.api.Server.DisposeCallRequest.class,
      responseType = im.dlg.sdk.calls.server.api.Server.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.DisposeCallRequest,
      im.dlg.sdk.calls.server.api.Server.Empty> getDisposeCallMethod() {
    io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.DisposeCallRequest, im.dlg.sdk.calls.server.api.Server.Empty> getDisposeCallMethod;
    if ((getDisposeCallMethod = CallServiceGrpc.getDisposeCallMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getDisposeCallMethod = CallServiceGrpc.getDisposeCallMethod) == null) {
          CallServiceGrpc.getDisposeCallMethod = getDisposeCallMethod = 
              io.grpc.MethodDescriptor.<im.dlg.sdk.calls.server.api.Server.DisposeCallRequest, im.dlg.sdk.calls.server.api.Server.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "im.dlg.sdk.calls.CallService", "disposeCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.DisposeCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("disposeCall"))
                  .build();
          }
        }
     }
     return getDisposeCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.AckRequest,
      im.dlg.sdk.calls.server.api.Server.Empty> getAckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ack",
      requestType = im.dlg.sdk.calls.server.api.Server.AckRequest.class,
      responseType = im.dlg.sdk.calls.server.api.Server.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.AckRequest,
      im.dlg.sdk.calls.server.api.Server.Empty> getAckMethod() {
    io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.AckRequest, im.dlg.sdk.calls.server.api.Server.Empty> getAckMethod;
    if ((getAckMethod = CallServiceGrpc.getAckMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getAckMethod = CallServiceGrpc.getAckMethod) == null) {
          CallServiceGrpc.getAckMethod = getAckMethod = 
              io.grpc.MethodDescriptor.<im.dlg.sdk.calls.server.api.Server.AckRequest, im.dlg.sdk.calls.server.api.Server.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "im.dlg.sdk.calls.CallService", "ack"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.AckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("ack"))
                  .build();
          }
        }
     }
     return getAckMethod;
  }

  private static volatile io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest,
      im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse> getJoinGroupCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinGroupCall",
      requestType = im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest.class,
      responseType = im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest,
      im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse> getJoinGroupCallMethod() {
    io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest, im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse> getJoinGroupCallMethod;
    if ((getJoinGroupCallMethod = CallServiceGrpc.getJoinGroupCallMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getJoinGroupCallMethod = CallServiceGrpc.getJoinGroupCallMethod) == null) {
          CallServiceGrpc.getJoinGroupCallMethod = getJoinGroupCallMethod = 
              io.grpc.MethodDescriptor.<im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest, im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "im.dlg.sdk.calls.CallService", "joinGroupCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("joinGroupCall"))
                  .build();
          }
        }
     }
     return getJoinGroupCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest,
      im.dlg.sdk.calls.server.api.Server.Empty> getHangupGroupCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hangupGroupCall",
      requestType = im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest.class,
      responseType = im.dlg.sdk.calls.server.api.Server.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest,
      im.dlg.sdk.calls.server.api.Server.Empty> getHangupGroupCallMethod() {
    io.grpc.MethodDescriptor<im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest, im.dlg.sdk.calls.server.api.Server.Empty> getHangupGroupCallMethod;
    if ((getHangupGroupCallMethod = CallServiceGrpc.getHangupGroupCallMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getHangupGroupCallMethod = CallServiceGrpc.getHangupGroupCallMethod) == null) {
          CallServiceGrpc.getHangupGroupCallMethod = getHangupGroupCallMethod = 
              io.grpc.MethodDescriptor.<im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest, im.dlg.sdk.calls.server.api.Server.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "im.dlg.sdk.calls.CallService", "hangupGroupCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  im.dlg.sdk.calls.server.api.Server.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("hangupGroupCall"))
                  .build();
          }
        }
     }
     return getHangupGroupCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CallServiceStub newStub(io.grpc.Channel channel) {
    return new CallServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CallServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CallServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CallServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CallServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Call Service - main server interface for calls SDK 
   * </pre>
   */
  public static abstract class CallServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Stream connection for server -&gt; client messages 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> connect(
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.ServerStreamMessage> responseObserver) {
      return asyncUnimplementedStreamingCall(getConnectMethod(), responseObserver);
    }

    /**
     * <pre>
     * Prepare call on server 
     * </pre>
     */
    public void createCall(im.dlg.sdk.calls.server.api.Server.CreateCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.CreateCallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateCallMethod(), responseObserver);
    }

    /**
     * <pre>
     * Prepare call on server 
     * </pre>
     */
    public void answerCall(im.dlg.sdk.calls.server.api.Server.AnswerCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.AnswerCallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAnswerCallMethod(), responseObserver);
    }

    /**
     * <pre>
     * Indicate that client see incommin call 
     * </pre>
     */
    public void ringingCall(im.dlg.sdk.calls.server.api.Server.RingingCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getRingingCallMethod(), responseObserver);
    }

    /**
     * <pre>
     * SDP exchange 
     * </pre>
     */
    public void joinCall(im.dlg.sdk.calls.server.api.Server.JoinCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.JoinCallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinCallMethod(), responseObserver);
    }

    /**
     * <pre>
     * Hangup call 
     * </pre>
     */
    public void disposeCall(im.dlg.sdk.calls.server.api.Server.DisposeCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getDisposeCallMethod(), responseObserver);
    }

    /**
     * <pre>
     * Indicate server message received 
     * </pre>
     */
    public void ack(im.dlg.sdk.calls.server.api.Server.AckRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getAckMethod(), responseObserver);
    }

    /**
     */
    public void joinGroupCall(im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinGroupCallMethod(), responseObserver);
    }

    /**
     */
    public void hangupGroupCall(im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getHangupGroupCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getConnectMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                im.dlg.sdk.calls.server.api.Server.Empty,
                im.dlg.sdk.calls.server.api.Server.ServerStreamMessage>(
                  this, METHODID_CONNECT)))
          .addMethod(
            getCreateCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                im.dlg.sdk.calls.server.api.Server.CreateCallRequest,
                im.dlg.sdk.calls.server.api.Server.CreateCallResponse>(
                  this, METHODID_CREATE_CALL)))
          .addMethod(
            getAnswerCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                im.dlg.sdk.calls.server.api.Server.AnswerCallRequest,
                im.dlg.sdk.calls.server.api.Server.AnswerCallResponse>(
                  this, METHODID_ANSWER_CALL)))
          .addMethod(
            getRingingCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                im.dlg.sdk.calls.server.api.Server.RingingCallRequest,
                im.dlg.sdk.calls.server.api.Server.Empty>(
                  this, METHODID_RINGING_CALL)))
          .addMethod(
            getJoinCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                im.dlg.sdk.calls.server.api.Server.JoinCallRequest,
                im.dlg.sdk.calls.server.api.Server.JoinCallResponse>(
                  this, METHODID_JOIN_CALL)))
          .addMethod(
            getDisposeCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                im.dlg.sdk.calls.server.api.Server.DisposeCallRequest,
                im.dlg.sdk.calls.server.api.Server.Empty>(
                  this, METHODID_DISPOSE_CALL)))
          .addMethod(
            getAckMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                im.dlg.sdk.calls.server.api.Server.AckRequest,
                im.dlg.sdk.calls.server.api.Server.Empty>(
                  this, METHODID_ACK)))
          .addMethod(
            getJoinGroupCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest,
                im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse>(
                  this, METHODID_JOIN_GROUP_CALL)))
          .addMethod(
            getHangupGroupCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest,
                im.dlg.sdk.calls.server.api.Server.Empty>(
                  this, METHODID_HANGUP_GROUP_CALL)))
          .build();
    }
  }

  /**
   * <pre>
   * Call Service - main server interface for calls SDK 
   * </pre>
   */
  public static final class CallServiceStub extends io.grpc.stub.AbstractStub<CallServiceStub> {
    private CallServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CallServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CallServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Stream connection for server -&gt; client messages 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> connect(
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.ServerStreamMessage> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getConnectMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Prepare call on server 
     * </pre>
     */
    public void createCall(im.dlg.sdk.calls.server.api.Server.CreateCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.CreateCallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Prepare call on server 
     * </pre>
     */
    public void answerCall(im.dlg.sdk.calls.server.api.Server.AnswerCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.AnswerCallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAnswerCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Indicate that client see incommin call 
     * </pre>
     */
    public void ringingCall(im.dlg.sdk.calls.server.api.Server.RingingCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRingingCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SDP exchange 
     * </pre>
     */
    public void joinCall(im.dlg.sdk.calls.server.api.Server.JoinCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.JoinCallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Hangup call 
     * </pre>
     */
    public void disposeCall(im.dlg.sdk.calls.server.api.Server.DisposeCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDisposeCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Indicate server message received 
     * </pre>
     */
    public void ack(im.dlg.sdk.calls.server.api.Server.AckRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAckMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinGroupCall(im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinGroupCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void hangupGroupCall(im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest request,
        io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHangupGroupCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Call Service - main server interface for calls SDK 
   * </pre>
   */
  public static final class CallServiceBlockingStub extends io.grpc.stub.AbstractStub<CallServiceBlockingStub> {
    private CallServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CallServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CallServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Prepare call on server 
     * </pre>
     */
    public im.dlg.sdk.calls.server.api.Server.CreateCallResponse createCall(im.dlg.sdk.calls.server.api.Server.CreateCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateCallMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Prepare call on server 
     * </pre>
     */
    public im.dlg.sdk.calls.server.api.Server.AnswerCallResponse answerCall(im.dlg.sdk.calls.server.api.Server.AnswerCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getAnswerCallMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Indicate that client see incommin call 
     * </pre>
     */
    public im.dlg.sdk.calls.server.api.Server.Empty ringingCall(im.dlg.sdk.calls.server.api.Server.RingingCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getRingingCallMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SDP exchange 
     * </pre>
     */
    public im.dlg.sdk.calls.server.api.Server.JoinCallResponse joinCall(im.dlg.sdk.calls.server.api.Server.JoinCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getJoinCallMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Hangup call 
     * </pre>
     */
    public im.dlg.sdk.calls.server.api.Server.Empty disposeCall(im.dlg.sdk.calls.server.api.Server.DisposeCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getDisposeCallMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Indicate server message received 
     * </pre>
     */
    public im.dlg.sdk.calls.server.api.Server.Empty ack(im.dlg.sdk.calls.server.api.Server.AckRequest request) {
      return blockingUnaryCall(
          getChannel(), getAckMethod(), getCallOptions(), request);
    }

    /**
     */
    public im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse joinGroupCall(im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getJoinGroupCallMethod(), getCallOptions(), request);
    }

    /**
     */
    public im.dlg.sdk.calls.server.api.Server.Empty hangupGroupCall(im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getHangupGroupCallMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Call Service - main server interface for calls SDK 
   * </pre>
   */
  public static final class CallServiceFutureStub extends io.grpc.stub.AbstractStub<CallServiceFutureStub> {
    private CallServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CallServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CallServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Prepare call on server 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<im.dlg.sdk.calls.server.api.Server.CreateCallResponse> createCall(
        im.dlg.sdk.calls.server.api.Server.CreateCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateCallMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Prepare call on server 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<im.dlg.sdk.calls.server.api.Server.AnswerCallResponse> answerCall(
        im.dlg.sdk.calls.server.api.Server.AnswerCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAnswerCallMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Indicate that client see incommin call 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<im.dlg.sdk.calls.server.api.Server.Empty> ringingCall(
        im.dlg.sdk.calls.server.api.Server.RingingCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRingingCallMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SDP exchange 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<im.dlg.sdk.calls.server.api.Server.JoinCallResponse> joinCall(
        im.dlg.sdk.calls.server.api.Server.JoinCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinCallMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Hangup call 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<im.dlg.sdk.calls.server.api.Server.Empty> disposeCall(
        im.dlg.sdk.calls.server.api.Server.DisposeCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDisposeCallMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Indicate server message received 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<im.dlg.sdk.calls.server.api.Server.Empty> ack(
        im.dlg.sdk.calls.server.api.Server.AckRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAckMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse> joinGroupCall(
        im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinGroupCallMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<im.dlg.sdk.calls.server.api.Server.Empty> hangupGroupCall(
        im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHangupGroupCallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_CALL = 0;
  private static final int METHODID_ANSWER_CALL = 1;
  private static final int METHODID_RINGING_CALL = 2;
  private static final int METHODID_JOIN_CALL = 3;
  private static final int METHODID_DISPOSE_CALL = 4;
  private static final int METHODID_ACK = 5;
  private static final int METHODID_JOIN_GROUP_CALL = 6;
  private static final int METHODID_HANGUP_GROUP_CALL = 7;
  private static final int METHODID_CONNECT = 8;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CallServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CallServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_CALL:
          serviceImpl.createCall((im.dlg.sdk.calls.server.api.Server.CreateCallRequest) request,
              (io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.CreateCallResponse>) responseObserver);
          break;
        case METHODID_ANSWER_CALL:
          serviceImpl.answerCall((im.dlg.sdk.calls.server.api.Server.AnswerCallRequest) request,
              (io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.AnswerCallResponse>) responseObserver);
          break;
        case METHODID_RINGING_CALL:
          serviceImpl.ringingCall((im.dlg.sdk.calls.server.api.Server.RingingCallRequest) request,
              (io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty>) responseObserver);
          break;
        case METHODID_JOIN_CALL:
          serviceImpl.joinCall((im.dlg.sdk.calls.server.api.Server.JoinCallRequest) request,
              (io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.JoinCallResponse>) responseObserver);
          break;
        case METHODID_DISPOSE_CALL:
          serviceImpl.disposeCall((im.dlg.sdk.calls.server.api.Server.DisposeCallRequest) request,
              (io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty>) responseObserver);
          break;
        case METHODID_ACK:
          serviceImpl.ack((im.dlg.sdk.calls.server.api.Server.AckRequest) request,
              (io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty>) responseObserver);
          break;
        case METHODID_JOIN_GROUP_CALL:
          serviceImpl.joinGroupCall((im.dlg.sdk.calls.server.api.Server.JoinGroupCallRequest) request,
              (io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.JoinGroupCallResponse>) responseObserver);
          break;
        case METHODID_HANGUP_GROUP_CALL:
          serviceImpl.hangupGroupCall((im.dlg.sdk.calls.server.api.Server.HangupGroupCallRequest) request,
              (io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONNECT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.connect(
              (io.grpc.stub.StreamObserver<im.dlg.sdk.calls.server.api.Server.ServerStreamMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CallServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CallServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return im.dlg.sdk.calls.server.api.Server.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CallService");
    }
  }

  private static final class CallServiceFileDescriptorSupplier
      extends CallServiceBaseDescriptorSupplier {
    CallServiceFileDescriptorSupplier() {}
  }

  private static final class CallServiceMethodDescriptorSupplier
      extends CallServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CallServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CallServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CallServiceFileDescriptorSupplier())
              .addMethod(getConnectMethod())
              .addMethod(getCreateCallMethod())
              .addMethod(getAnswerCallMethod())
              .addMethod(getRingingCallMethod())
              .addMethod(getJoinCallMethod())
              .addMethod(getDisposeCallMethod())
              .addMethod(getAckMethod())
              .addMethod(getJoinGroupCallMethod())
              .addMethod(getHangupGroupCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
