var Services = angular.module('Services', ['ngResource']);

Services.factory('ServiceMur', ['$resource',
  function($resource){
    return $resource('http://localhost:8080/StatusService/api/contenu/mur', {}, {
      query: {method:'GET', params:{}, isArray:true}
    });
  }]);
Services.factory('ServiceStatus', ['$resource',
  function($resource){
    return $resource('http://localhost:8080/StatusService/api/status/update', {}, {
      save: {method:'POST'}
    });
      
  }]);


      
