package my.bookshop;

import java.util.ArrayList;
import java.util.List;

import com.sap.cloud.sdk.service.prov.api.EntityData;
import com.sap.cloud.sdk.service.prov.api.ExtensionHelper;
import com.sap.cloud.sdk.service.prov.api.annotations.AfterQuery;
import com.sap.cloud.sdk.service.prov.api.annotations.AfterRead;
import com.sap.cloud.sdk.service.prov.api.operations.Create;
import com.sap.cloud.sdk.service.prov.api.operations.Query;
import com.sap.cloud.sdk.service.prov.api.request.CreateRequest;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.request.ReadRequest;
import com.sap.cloud.sdk.service.prov.api.response.CreateResponse;
import com.sap.cloud.sdk.service.prov.api.response.ErrorResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponseAccessor;
import com.sap.cloud.sdk.service.prov.api.response.ReadResponse;
import com.sap.cloud.sdk.service.prov.api.response.ReadResponseAccessor;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogService {
	
	private static final Logger LOG = LoggerFactory.getLogger (CatalogService.class.getName());
	
	@AfterQuery (entity = "Authors", serviceName="CatalogService")
  public QueryResponse afterQueryAuthors (QueryRequest req, QueryResponseAccessor res, ExtensionHelper h) {
    List<EntityData> dataList = res.getEntityDataList(); //original list
    List<EntityData> modifiedList = new ArrayList<EntityData>(dataList.size()); //modified list
    for(EntityData ed : dataList){
		  EntityData ex = EntityData.getBuilder(ed).addElement("noOfBooks", 3).buildEntityData("Authors");
		  modifiedList.add(ex);
	  }
    return QueryResponse.setSuccess().setData(modifiedList).response();
  }
  
  @AfterRead (entity = "Authors", serviceName="CatalogService")
  public ReadResponse afterReadOrders (ReadRequest req, ReadResponseAccessor res, ExtensionHelper h) {
    EntityData ed = res.getEntityData();
    EntityData ex = EntityData.getBuilder(ed).addElement("noOfBooks", 3).buildEntityData("Authors");
    return ReadResponse.setSuccess().setData(ex).response();
  }
  
  	@Query(serviceName = "CatalogService", entity = "MyBooks")
	public QueryResponse queryMyBooks(QueryRequest queryRequest) {
		try {
			// Retrieve the name of the entity set from the QueryRequest object
			String entitySetName = queryRequest.getEntityName();

			// Add your implementation of the query operation here, using the
			// retrieved entity set name
			List<EntityData> sampleData = queryMyBooks(entitySetName);

			// Return an instance of QueryResponse in case of success
			return QueryResponse.setSuccess().setData(sampleData).response();
		} catch (Exception e) {
			// Return an instance of QueryResponse containing the error in case
			// of failure
			ErrorResponse errorResponse = ErrorResponse.getBuilder()
					.setMessage(e.getMessage())
					.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.setCause(e)
					.response();
			return QueryResponse.setError(errorResponse);
		}
	}
	
		@Create(serviceName = "CatalogService", entity = "MyDocuments")
	public CreateResponse createMyDocuments(CreateRequest createRequest) {
		try {
			// Retrieve the entity data from the CreateRequest object
			EntityData entityToCreate = createRequest.getData();

			// Add your implementation logic for the create operation here
			EntityData sampleData = storeDocument(entityToCreate);

			// Return an instance of CreateResponse in case of success
			return CreateResponse.setSuccess().setData(sampleData).response();
		} catch (Exception e) {
			// Return an instance of CreateResponse containing the error in case
			// of failure
			ErrorResponse errorResponse = ErrorResponse.getBuilder()
					.setMessage(e.getMessage())
					.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.setCause(e)
					.response();
			return CreateResponse.setError(errorResponse);
		}
	}

	private EntityData storeDocument(EntityData entityToCreate) {
		
			return entityToCreate;
		}

	private List<EntityData> queryMyBooks(String entitySetName) {
		return null;
	}

}
