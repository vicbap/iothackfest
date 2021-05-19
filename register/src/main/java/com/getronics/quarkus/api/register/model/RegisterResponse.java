package com.getronics.quarkus.api.register.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegisterResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-19T12:09:00.549+02:00[Europe/Madrid]")
public class RegisterResponse   {
  @JsonProperty("id")
  private String id;

  @JsonProperty("truststore")
  private byte[] truststore;

  @JsonProperty("keystore")
  private byte[] keystore;

  public RegisterResponse id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RegisterResponse truststore(byte[] truststore) {
    this.truststore = truststore;
    return this;
  }

  /**
   * Get truststore
   * @return truststore
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public byte[] getTruststore() {
    return truststore;
  }

  public void setTruststore(byte[] truststore) {
    this.truststore = truststore;
  }

  public RegisterResponse keystore(byte[] keystore) {
    this.keystore = keystore;
    return this;
  }

  /**
   * Get keystore
   * @return keystore
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public byte[] getKeystore() {
    return keystore;
  }

  public void setKeystore(byte[] keystore) {
    this.keystore = keystore;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterResponse registerResponse = (RegisterResponse) o;
    return Objects.equals(this.id, registerResponse.id) &&
        Arrays.equals(this.truststore, registerResponse.truststore) &&
        Arrays.equals(this.keystore, registerResponse.keystore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, Arrays.hashCode(truststore), Arrays.hashCode(keystore));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    truststore: ").append(toIndentedString(truststore)).append("\n");
    sb.append("    keystore: ").append(toIndentedString(keystore)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

