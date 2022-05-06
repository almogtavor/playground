// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pipeline/example/sample.proto

package pipeline.example;

/**
 * Protobuf type {@code Msg}
 */
public  final class Msg extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Msg)
    MsgOrBuilder {
  // Use Msg.newBuilder() to construct.
  private Msg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Msg() {
    foo_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Msg(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            foo_ = s;
            break;
          }
          case 18: {
            pipeline.example.SecondMsg.Builder subBuilder = null;
            if (blah_ != null) {
              subBuilder = blah_.toBuilder();
            }
            blah_ = input.readMessage(pipeline.example.SecondMsg.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(blah_);
              blah_ = subBuilder.buildPartial();
            }

            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pipeline.example.OuterSample.internal_static_Msg_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pipeline.example.OuterSample.internal_static_Msg_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pipeline.example.Msg.class, pipeline.example.Msg.Builder.class);
  }

  public static final int FOO_FIELD_NUMBER = 1;
  private volatile java.lang.Object foo_;
  /**
   * <code>optional string foo = 1;</code>
   */
  public java.lang.String getFoo() {
    java.lang.Object ref = foo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      foo_ = s;
      return s;
    }
  }
  /**
   * <code>optional string foo = 1;</code>
   */
  public com.google.protobuf.ByteString
      getFooBytes() {
    java.lang.Object ref = foo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      foo_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BLAH_FIELD_NUMBER = 2;
  private pipeline.example.SecondMsg blah_;
  /**
   * <code>optional .SecondMsg blah = 2;</code>
   */
  public boolean hasBlah() {
    return blah_ != null;
  }
  /**
   * <code>optional .SecondMsg blah = 2;</code>
   */
  public pipeline.example.SecondMsg getBlah() {
    return blah_ == null ? pipeline.example.SecondMsg.getDefaultInstance() : blah_;
  }
  /**
   * <code>optional .SecondMsg blah = 2;</code>
   */
  public pipeline.example.SecondMsgOrBuilder getBlahOrBuilder() {
    return getBlah();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getFooBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, foo_);
    }
    if (blah_ != null) {
      output.writeMessage(2, getBlah());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getFooBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, foo_);
    }
    if (blah_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getBlah());
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pipeline.example.Msg)) {
      return super.equals(obj);
    }
    pipeline.example.Msg other = (pipeline.example.Msg) obj;

    boolean result = true;
    result = result && getFoo()
        .equals(other.getFoo());
    result = result && (hasBlah() == other.hasBlah());
    if (hasBlah()) {
      result = result && getBlah()
          .equals(other.getBlah());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + FOO_FIELD_NUMBER;
    hash = (53 * hash) + getFoo().hashCode();
    if (hasBlah()) {
      hash = (37 * hash) + BLAH_FIELD_NUMBER;
      hash = (53 * hash) + getBlah().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pipeline.example.Msg parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pipeline.example.Msg parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pipeline.example.Msg parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pipeline.example.Msg parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pipeline.example.Msg parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pipeline.example.Msg parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pipeline.example.Msg parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pipeline.example.Msg parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pipeline.example.Msg parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pipeline.example.Msg parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(pipeline.example.Msg prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code Msg}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Msg)
      pipeline.example.MsgOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pipeline.example.OuterSample.internal_static_Msg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pipeline.example.OuterSample.internal_static_Msg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pipeline.example.Msg.class, pipeline.example.Msg.Builder.class);
    }

    // Construct using pipeline.example.Msg.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      foo_ = "";

      if (blahBuilder_ == null) {
        blah_ = null;
      } else {
        blah_ = null;
        blahBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pipeline.example.OuterSample.internal_static_Msg_descriptor;
    }

    public pipeline.example.Msg getDefaultInstanceForType() {
      return pipeline.example.Msg.getDefaultInstance();
    }

    public pipeline.example.Msg build() {
      pipeline.example.Msg result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pipeline.example.Msg buildPartial() {
      pipeline.example.Msg result = new pipeline.example.Msg(this);
      result.foo_ = foo_;
      if (blahBuilder_ == null) {
        result.blah_ = blah_;
      } else {
        result.blah_ = blahBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof pipeline.example.Msg) {
        return mergeFrom((pipeline.example.Msg)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pipeline.example.Msg other) {
      if (other == pipeline.example.Msg.getDefaultInstance()) return this;
      if (!other.getFoo().isEmpty()) {
        foo_ = other.foo_;
        onChanged();
      }
      if (other.hasBlah()) {
        mergeBlah(other.getBlah());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pipeline.example.Msg parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pipeline.example.Msg) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object foo_ = "";
    /**
     * <code>optional string foo = 1;</code>
     */
    public java.lang.String getFoo() {
      java.lang.Object ref = foo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        foo_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string foo = 1;</code>
     */
    public com.google.protobuf.ByteString
        getFooBytes() {
      java.lang.Object ref = foo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        foo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string foo = 1;</code>
     */
    public Builder setFoo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      foo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string foo = 1;</code>
     */
    public Builder clearFoo() {
      
      foo_ = getDefaultInstance().getFoo();
      onChanged();
      return this;
    }
    /**
     * <code>optional string foo = 1;</code>
     */
    public Builder setFooBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      foo_ = value;
      onChanged();
      return this;
    }

    private pipeline.example.SecondMsg blah_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pipeline.example.SecondMsg, pipeline.example.SecondMsg.Builder, pipeline.example.SecondMsgOrBuilder> blahBuilder_;
    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public boolean hasBlah() {
      return blahBuilder_ != null || blah_ != null;
    }
    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public pipeline.example.SecondMsg getBlah() {
      if (blahBuilder_ == null) {
        return blah_ == null ? pipeline.example.SecondMsg.getDefaultInstance() : blah_;
      } else {
        return blahBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public Builder setBlah(pipeline.example.SecondMsg value) {
      if (blahBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        blah_ = value;
        onChanged();
      } else {
        blahBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public Builder setBlah(
        pipeline.example.SecondMsg.Builder builderForValue) {
      if (blahBuilder_ == null) {
        blah_ = builderForValue.build();
        onChanged();
      } else {
        blahBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public Builder mergeBlah(pipeline.example.SecondMsg value) {
      if (blahBuilder_ == null) {
        if (blah_ != null) {
          blah_ =
            pipeline.example.SecondMsg.newBuilder(blah_).mergeFrom(value).buildPartial();
        } else {
          blah_ = value;
        }
        onChanged();
      } else {
        blahBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public Builder clearBlah() {
      if (blahBuilder_ == null) {
        blah_ = null;
        onChanged();
      } else {
        blah_ = null;
        blahBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public pipeline.example.SecondMsg.Builder getBlahBuilder() {
      
      onChanged();
      return getBlahFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public pipeline.example.SecondMsgOrBuilder getBlahOrBuilder() {
      if (blahBuilder_ != null) {
        return blahBuilder_.getMessageOrBuilder();
      } else {
        return blah_ == null ?
            pipeline.example.SecondMsg.getDefaultInstance() : blah_;
      }
    }
    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pipeline.example.SecondMsg, pipeline.example.SecondMsg.Builder, pipeline.example.SecondMsgOrBuilder> 
        getBlahFieldBuilder() {
      if (blahBuilder_ == null) {
        blahBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pipeline.example.SecondMsg, pipeline.example.SecondMsg.Builder, pipeline.example.SecondMsgOrBuilder>(
                getBlah(),
                getParentForChildren(),
                isClean());
        blah_ = null;
      }
      return blahBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:Msg)
  }

  // @@protoc_insertion_point(class_scope:Msg)
  private static final pipeline.example.Msg DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pipeline.example.Msg();
  }

  public static pipeline.example.Msg getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Msg>
      PARSER = new com.google.protobuf.AbstractParser<Msg>() {
    public Msg parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Msg(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Msg> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Msg> getParserForType() {
    return PARSER;
  }

  public pipeline.example.Msg getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

