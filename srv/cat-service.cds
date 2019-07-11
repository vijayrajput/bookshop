using my.bookshop from '../db/data-model';

@cds.query.limit: 2
service CatalogService {
  @Capabilities.SearchRestrictions.Searchable: true	
  entity Books  as projection on bookshop.Books ;  //excluding {stock};
  entity Authors  as projection on bookshop.Authors ;
  entity Orders  as projection on bookshop.Orders;
  entity Document as projection on bookshop.MyMimeEntity;
  @cds.persistence.skip
  entity MyDocuments {
  key id : UUID;
  @Core.MediaType: col2
  col1 : LargeBinary;
  @Core.IsMediaType : true
  col2 : String;
};
@cds.persistence.skip
entity MyBooks {
  key ID : Integer;
  title  : String;
  stock  : Integer;
}
  function getRatings() returns Integer;
  
  annotate Books with {
  	@Search.defaultSearchElement: true
  	title;
  	@Search.defaultSearchElement: true
  	author;
  	
  };
}
