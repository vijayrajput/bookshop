using { CatalogService } from './cat-service';

annotate CatalogService with @(requires: 'authenticated-user');
annotate CatalogService.Books with @(restrict: [
  { grant: ['READ','WRITE'], to: 'admin' },
]);