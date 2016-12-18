/**
 * Created by: leaf
 * Date: 11/26/15
 * Time: 12:33 PM
 */
'use strict';

App.service('userService', ['$resource', 'baseUrl', function($resource, baseUrl){

	this.users = function() {
		return $resource(baseUrl + 'user', null,
		  {
			  'update': {method: 'PUT'},
			  'getAll': {method: 'GET', isArray: true}
		  });
	};

}]);