// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/java/com/example/pubsubstudy/pubsub/schema/singlemessage.proto

package com.example.pubsubstudy.pubsub.schema;

public final class SingleMessageProtos {
  private SingleMessageProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pubsubstudy_SingleMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pubsubstudy_SingleMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\nGsrc/main/java/com/example/pubsubstudy/" +
      "pubsub/schema/singlemessage.proto\022\013pubsu" +
      "bstudy\"M\n\rSingleMessage\022\020\n\010pushType\030\001 \002(" +
      "\t\022\r\n\005token\030\002 \002(\t\022\r\n\005title\030\003 \002(\t\022\014\n\004body\030" +
      "\004 \002(\tB>\n%com.example.pubsubstudy.pubsub." +
      "schemaB\023SingleMessageProtosP\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_pubsubstudy_SingleMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_pubsubstudy_SingleMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pubsubstudy_SingleMessage_descriptor,
        new java.lang.String[] { "PushType", "Token", "Title", "Body", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
