namespace my.bookshop;
using { Country, managed } from '@sap/cds/common';

entity Books : managed {
  key ID : Integer;
  title  : localized String;
  author : Association to Authors;
  stock  : Integer;
}

entity Authors : managed {
  key ID : Integer;
  name   : String;
  virtual noOfBooks : Integer;
  books  : Association to many Books on books.author = $self;
}

entity Orders : managed {
  key ID  : UUID;
  book    : Association to Books;
  country : Country;
  amount  : Integer;
}
entity MyMimeEntity {
  key id : UUID;
  @Core.MediaType: col2
  col1 : LargeBinary;
  @Core.IsMediaType : true
  col2 : String;
}