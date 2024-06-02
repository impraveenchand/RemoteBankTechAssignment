/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.remotebank.transaction.event.value;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class TransactionEventValue extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -2445371841887870637L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TransactionEventValue\",\"namespace\":\"io.remotebank.transaction.event.value\",\"fields\":[{\"name\":\"user_id\",\"type\":\"int\"},{\"name\":\"transaction_timestamp_millis\",\"type\":\"long\"},{\"name\":\"amount\",\"type\":\"float\"},{\"name\":\"currency\",\"type\":\"string\"},{\"name\":\"counterpart_id\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TransactionEventValue> ENCODER =
      new BinaryMessageEncoder<TransactionEventValue>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TransactionEventValue> DECODER =
      new BinaryMessageDecoder<TransactionEventValue>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<TransactionEventValue> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<TransactionEventValue> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TransactionEventValue>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this TransactionEventValue to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a TransactionEventValue from a ByteBuffer. */
  public static TransactionEventValue fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public int user_id;
  @Deprecated public long transaction_timestamp_millis;
  @Deprecated public float amount;
  @Deprecated public java.lang.CharSequence currency;
  @Deprecated public int counterpart_id;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TransactionEventValue() {}

  /**
   * All-args constructor.
   * @param user_id The new value for user_id
   * @param transaction_timestamp_millis The new value for transaction_timestamp_millis
   * @param amount The new value for amount
   * @param currency The new value for currency
   * @param counterpart_id The new value for counterpart_id
   */
  public TransactionEventValue(java.lang.Integer user_id, java.lang.Long transaction_timestamp_millis, java.lang.Float amount, java.lang.CharSequence currency, java.lang.Integer counterpart_id) {
    this.user_id = user_id;
    this.transaction_timestamp_millis = transaction_timestamp_millis;
    this.amount = amount;
    this.currency = currency;
    this.counterpart_id = counterpart_id;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return user_id;
    case 1: return transaction_timestamp_millis;
    case 2: return amount;
    case 3: return currency;
    case 4: return counterpart_id;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: user_id = (java.lang.Integer)value$; break;
    case 1: transaction_timestamp_millis = (java.lang.Long)value$; break;
    case 2: amount = (java.lang.Float)value$; break;
    case 3: currency = (java.lang.CharSequence)value$; break;
    case 4: counterpart_id = (java.lang.Integer)value$; break;
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
   * Gets the value of the 'transaction_timestamp_millis' field.
   * @return The value of the 'transaction_timestamp_millis' field.
   */
  public java.lang.Long getTransactionTimestampMillis() {
    return transaction_timestamp_millis;
  }

  /**
   * Sets the value of the 'transaction_timestamp_millis' field.
   * @param value the value to set.
   */
  public void setTransactionTimestampMillis(java.lang.Long value) {
    this.transaction_timestamp_millis = value;
  }

  /**
   * Gets the value of the 'amount' field.
   * @return The value of the 'amount' field.
   */
  public java.lang.Float getAmount() {
    return amount;
  }

  /**
   * Sets the value of the 'amount' field.
   * @param value the value to set.
   */
  public void setAmount(java.lang.Float value) {
    this.amount = value;
  }

  /**
   * Gets the value of the 'currency' field.
   * @return The value of the 'currency' field.
   */
  public java.lang.CharSequence getCurrency() {
    return currency;
  }

  /**
   * Sets the value of the 'currency' field.
   * @param value the value to set.
   */
  public void setCurrency(java.lang.CharSequence value) {
    this.currency = value;
  }

  /**
   * Gets the value of the 'counterpart_id' field.
   * @return The value of the 'counterpart_id' field.
   */
  public java.lang.Integer getCounterpartId() {
    return counterpart_id;
  }

  /**
   * Sets the value of the 'counterpart_id' field.
   * @param value the value to set.
   */
  public void setCounterpartId(java.lang.Integer value) {
    this.counterpart_id = value;
  }

  /**
   * Creates a new TransactionEventValue RecordBuilder.
   * @return A new TransactionEventValue RecordBuilder
   */
  public static io.remotebank.transaction.event.value.TransactionEventValue.Builder newBuilder() {
    return new io.remotebank.transaction.event.value.TransactionEventValue.Builder();
  }

  /**
   * Creates a new TransactionEventValue RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TransactionEventValue RecordBuilder
   */
  public static io.remotebank.transaction.event.value.TransactionEventValue.Builder newBuilder(io.remotebank.transaction.event.value.TransactionEventValue.Builder other) {
    return new io.remotebank.transaction.event.value.TransactionEventValue.Builder(other);
  }

  /**
   * Creates a new TransactionEventValue RecordBuilder by copying an existing TransactionEventValue instance.
   * @param other The existing instance to copy.
   * @return A new TransactionEventValue RecordBuilder
   */
  public static io.remotebank.transaction.event.value.TransactionEventValue.Builder newBuilder(io.remotebank.transaction.event.value.TransactionEventValue other) {
    return new io.remotebank.transaction.event.value.TransactionEventValue.Builder(other);
  }

  /**
   * RecordBuilder for TransactionEventValue instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TransactionEventValue>
    implements org.apache.avro.data.RecordBuilder<TransactionEventValue> {

    private int user_id;
    private long transaction_timestamp_millis;
    private float amount;
    private java.lang.CharSequence currency;
    private int counterpart_id;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.remotebank.transaction.event.value.TransactionEventValue.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.user_id)) {
        this.user_id = data().deepCopy(fields()[0].schema(), other.user_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.transaction_timestamp_millis)) {
        this.transaction_timestamp_millis = data().deepCopy(fields()[1].schema(), other.transaction_timestamp_millis);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.amount)) {
        this.amount = data().deepCopy(fields()[2].schema(), other.amount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.currency)) {
        this.currency = data().deepCopy(fields()[3].schema(), other.currency);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.counterpart_id)) {
        this.counterpart_id = data().deepCopy(fields()[4].schema(), other.counterpart_id);
        fieldSetFlags()[4] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing TransactionEventValue instance
     * @param other The existing instance to copy.
     */
    private Builder(io.remotebank.transaction.event.value.TransactionEventValue other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.user_id)) {
        this.user_id = data().deepCopy(fields()[0].schema(), other.user_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.transaction_timestamp_millis)) {
        this.transaction_timestamp_millis = data().deepCopy(fields()[1].schema(), other.transaction_timestamp_millis);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.amount)) {
        this.amount = data().deepCopy(fields()[2].schema(), other.amount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.currency)) {
        this.currency = data().deepCopy(fields()[3].schema(), other.currency);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.counterpart_id)) {
        this.counterpart_id = data().deepCopy(fields()[4].schema(), other.counterpart_id);
        fieldSetFlags()[4] = true;
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
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder setUserId(int value) {
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
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder clearUserId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'transaction_timestamp_millis' field.
      * @return The value.
      */
    public java.lang.Long getTransactionTimestampMillis() {
      return transaction_timestamp_millis;
    }

    /**
      * Sets the value of the 'transaction_timestamp_millis' field.
      * @param value The value of 'transaction_timestamp_millis'.
      * @return This builder.
      */
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder setTransactionTimestampMillis(long value) {
      validate(fields()[1], value);
      this.transaction_timestamp_millis = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'transaction_timestamp_millis' field has been set.
      * @return True if the 'transaction_timestamp_millis' field has been set, false otherwise.
      */
    public boolean hasTransactionTimestampMillis() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'transaction_timestamp_millis' field.
      * @return This builder.
      */
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder clearTransactionTimestampMillis() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'amount' field.
      * @return The value.
      */
    public java.lang.Float getAmount() {
      return amount;
    }

    /**
      * Sets the value of the 'amount' field.
      * @param value The value of 'amount'.
      * @return This builder.
      */
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder setAmount(float value) {
      validate(fields()[2], value);
      this.amount = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'amount' field has been set.
      * @return True if the 'amount' field has been set, false otherwise.
      */
    public boolean hasAmount() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'amount' field.
      * @return This builder.
      */
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder clearAmount() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'currency' field.
      * @return The value.
      */
    public java.lang.CharSequence getCurrency() {
      return currency;
    }

    /**
      * Sets the value of the 'currency' field.
      * @param value The value of 'currency'.
      * @return This builder.
      */
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder setCurrency(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.currency = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'currency' field has been set.
      * @return True if the 'currency' field has been set, false otherwise.
      */
    public boolean hasCurrency() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'currency' field.
      * @return This builder.
      */
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder clearCurrency() {
      currency = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'counterpart_id' field.
      * @return The value.
      */
    public java.lang.Integer getCounterpartId() {
      return counterpart_id;
    }

    /**
      * Sets the value of the 'counterpart_id' field.
      * @param value The value of 'counterpart_id'.
      * @return This builder.
      */
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder setCounterpartId(int value) {
      validate(fields()[4], value);
      this.counterpart_id = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'counterpart_id' field has been set.
      * @return True if the 'counterpart_id' field has been set, false otherwise.
      */
    public boolean hasCounterpartId() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'counterpart_id' field.
      * @return This builder.
      */
    public io.remotebank.transaction.event.value.TransactionEventValue.Builder clearCounterpartId() {
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TransactionEventValue build() {
      try {
        TransactionEventValue record = new TransactionEventValue();
        record.user_id = fieldSetFlags()[0] ? this.user_id : (java.lang.Integer) defaultValue(fields()[0]);
        record.transaction_timestamp_millis = fieldSetFlags()[1] ? this.transaction_timestamp_millis : (java.lang.Long) defaultValue(fields()[1]);
        record.amount = fieldSetFlags()[2] ? this.amount : (java.lang.Float) defaultValue(fields()[2]);
        record.currency = fieldSetFlags()[3] ? this.currency : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.counterpart_id = fieldSetFlags()[4] ? this.counterpart_id : (java.lang.Integer) defaultValue(fields()[4]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TransactionEventValue>
    WRITER$ = (org.apache.avro.io.DatumWriter<TransactionEventValue>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TransactionEventValue>
    READER$ = (org.apache.avro.io.DatumReader<TransactionEventValue>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}