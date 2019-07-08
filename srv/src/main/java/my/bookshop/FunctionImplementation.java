package my.bookshop;

import java.util.Arrays;

import com.sap.cloud.sdk.service.prov.api.annotations.Function;
import com.sap.cloud.sdk.service.prov.api.request.OperationRequest;
import com.sap.cloud.sdk.service.prov.api.response.OperationResponse;

public class FunctionImplementation {

@Function(Name="getRatings",serviceName="CatalogService")
public OperationResponse getApprovalStatus(OperationRequest functionRequest )
{
	
	OperationResponse response = OperationResponse.setSuccess().setPrimitiveData(Arrays.asList(2)).response();

	return response;
}
}
