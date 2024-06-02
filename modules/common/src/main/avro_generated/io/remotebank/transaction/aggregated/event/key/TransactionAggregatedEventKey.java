/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.remotebank.transaction.aggregated.event.key;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class TransactionAggregatedEventKey extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8245605515708965334L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TransactionAggregatedEventKey\",\"namespace\":\"io.remotebank.transaction.aggregated.event.key\",\"fields\":[{\"name\":\"user_id\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TransactionAggregatedEventKey> ENCODER =
      new BinaryMessageEncoder<TransactionAggregatedEventKey>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TransactionAggregatedEventKey> DECODER =
      new BinaryMessageDecoder<TransactionAggregatedEventKey>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<TransactionAggregatedEventKey> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<TransactionAggregatedEventKey> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TransactionAggregatedEventKey>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this TransactionAggregatedEventKey to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a TransactionAggregatedEventKey from a ByteBuffer. */
  public static TransactionAggregatedEventKey fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public int user_id;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TransactionAggregatedEventKey() {}

  /**
   * All-args constructor.
   * @param user_id The new value for user_id
   */
  public TransactionAggregatedEventKey(java.lang.Integer user_id) {
    this.user_id = user_id;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return user_id;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: user_id = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'user_id' field.
   * @return The value of the 'user_id' field.
   */
  public java.lang.Integer getUserId() {
    return user_id;
  }

  /**
   * Sets the value of the 'user_id' field.
   * @param value the value to set.
   */
  public void setUserId(java.lang.Integer value) {
    this.user_id = value;
  }

  /**
   * Creates a new TransactionAggregatedEventKey RecordBuilder.
   * @return A new TransactionAggregatedEventKey RecordBuilder
   */
  public static io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder newBuilder() {
    return new io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder();
  }

  /**
   * Creates a new TransactionAggregatedEventKey RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TransactionAggregatedEventKey RecordBuilder
   */
  public static io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder newBuilder(io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder other) {
    return new io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder(other);
  }

  /**
   * Creates a new TransactionAggregatedEventKey RecordBuilder by copying an existing TransactionAggregatedEventKey instance.
   * @param other The existing instance to copy.
   * @return A new TransactionAggregatedEventKey RecordBuilder
   */
  public static io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder newBuilder(io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey other) {
    return new io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder(other);
  }

  /**
   * RecordBuilder for TransactionAggregatedEventKey instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TransactionAggregatedEventKey>
    implements org.apache.avro.data.RecordBuilder<TransactionAggregatedEventKey> {

    private int user_id;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.user_id)) {
        this.user_id = data().deepCopy(fields()[0].schema(), other.user_id);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing TransactionAggregatedEventKey instance
     * @param other The existing instance to copy.
     */
    private Builder(io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.user_id)) {
        this.user_id = data().deepCopy(fields()[0].schema(), other.user_id);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'user_id' field.
      * @return The value.
      */
    public java.lang.Integer getUserId() {
      return user_id;
    }

    /**
      * Sets the value of the 'user_id' field.
      * @param value The value of 'user_id'.
      * @return This builder.
      */
    public io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder setUserId(int value) {
      validate(fields()[0], value);
      this.user_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'user_id' field has been set.
      * @return True if the 'user_id' field has been set, false otherwise.
      */
    public boolean hasUserId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'user_id' field.
      * @return This builder.
      */
    public io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey.Builder clearUserId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TransactionAggregatedEventKey build() {
      try {
        TransactionAggregatedEventKey record = new TransactionAggregatedEventKey();
        record.user_id = fieldSetFlags()[0] ? this.user_id : (java.lang.Integer) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TransactionAggregatedEventKey>
    WRITER$ = (org.apache.avro.io.DatumWriter<TransactionAggregatedEventKey>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TransactionAggregatedEventKey>
    READER$ = (org.apache.avro.io.DatumReader<TransactionAggregatedEventKey>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}