# DefaultApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createRegisterRequest**](DefaultApi.md#createRegisterRequest) | **POST** /register | Create a RegisterRequest
[**getRegisterRequest**](DefaultApi.md#getRegisterRequest) | **GET** /register/{id} | Get a RegisterRequest
[**v1RegisterPost**](DefaultApi.md#v1RegisterPost) | **POST** /v1/register | 


<a name="createRegisterRequest"></a>
# **createRegisterRequest**
> RegisterResponse createRegisterRequest(registerRequest)

Create a RegisterRequest

Creates a new instance of a &#x60;RegisterRequest&#x60;.

### Example
```java
// Import classes:
import com.getronics.quarkus.api.ApiClient;
import com.getronics.quarkus.api.ApiException;
import com.getronics.quarkus.api.Configuration;
import com.getronics.quarkus.api.models.*;
import com.getronics.quarkus.api.register.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    RegisterRequest registerRequest = new RegisterRequest(); // RegisterRequest | A new `RegisterRequest` to be created.
    try {
      RegisterResponse result = apiInstance.createRegisterRequest(registerRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#createRegisterRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **registerRequest** | [**RegisterRequest**](RegisterRequest.md)| A new &#x60;RegisterRequest&#x60; to be created. |

### Return type

[**RegisterResponse**](RegisterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Successful response. |  -  |

<a name="getRegisterRequest"></a>
# **getRegisterRequest**
> RegisterResponse getRegisterRequest(id)

Get a RegisterRequest

Gets the details of a single instance of a &#x60;RegisterRequest&#x60;.

### Example
```java
// Import classes:
import com.getronics.quarkus.api.ApiClient;
import com.getronics.quarkus.api.ApiException;
import com.getronics.quarkus.api.Configuration;
import com.getronics.quarkus.api.models.*;
import com.getronics.quarkus.api.register.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "id_example"; // String | 
    try {
      RegisterResponse result = apiInstance.getRegisterRequest(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getRegisterRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

[**RegisterResponse**](RegisterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response - returns a single &#x60;RegisterResponse&#x60;. |  -  |

<a name="v1RegisterPost"></a>
# **v1RegisterPost**
> v1RegisterPost(registerRequest)



### Example
```java
// Import classes:
import com.getronics.quarkus.api.ApiClient;
import com.getronics.quarkus.api.ApiException;
import com.getronics.quarkus.api.Configuration;
import com.getronics.quarkus.api.models.*;
import com.getronics.quarkus.api.register.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    RegisterRequest registerRequest = new RegisterRequest(); // RegisterRequest | 
    try {
      apiInstance.v1RegisterPost(registerRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#v1RegisterPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **registerRequest** | [**RegisterRequest**](RegisterRequest.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

