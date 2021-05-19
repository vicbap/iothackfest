package com.getronics.quarkus.api.register.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegisterRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-19T12:09:00.549+02:00[Europe/Madrid]")
public class RegisterRequest   {
  @JsonProperty("serial")
  private String serial;

  @JsonProperty("name")
  private String name;

  @JsonProperty("longitude")
  private Double longitude;

  @JsonProperty("latitude")
  private Double latitude;

  @JsonProperty("keyStorePassword")
  private String keyStorePassword;

  public RegisterRequest serial(String serial) {
    this.serial = serial;
    return this;
  }

  /**
   * Get serial
   * @return serial
  */
  @ApiModelProperty(value = "")


  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public RegisterRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RegisterRequest longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
  */
  @ApiModelProperty(value = "")


  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public RegisterRequest latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
  */
  @ApiModelProperty(value = "")


  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public RegisterRequest keyStorePassword(String keyStorePassword) {
    this.keyStorePassword = keyStorePassword;
    return this;
  }

  /**
   * KeyStore Password
   * @return keyStorePassword
  */
  @ApiModelProperty(value = "KeyStore Password")


  public String getKeyStorePassword() {
    return keyStorePassword;
  }

  public void setKeyStorePassword(String keyStorePassword) {
    this.keyStorePassword = keyStorePassword;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterRequest registerRequest = (RegisterRequest) o;
    return Objects.equals(this.serial, registerRequest.serial) &&
        Objects.equals(this.name, registerRequest.name) &&
        Objects.equals(this.longitude, registerRequest.longitude) &&
        Objects.equals(this.latitude, registerRequest.latitude) &&
        Objects.equals(this.keyStorePassword, registerRequest.keyStorePassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serial, name, longitude, latitude, keyStorePassword);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterRequest {\n");
    
    sb.append("    serial: ").append(toIndentedString(serial)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    keyStorePassword: ").append(toIndentedString(keyStorePassword)).append("\n");
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

