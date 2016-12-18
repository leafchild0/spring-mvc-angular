/**
 * Created by: leaf
 * Date: 11/26/15
 * Time: 12:33 PM
 */
'use strict';

App.controller('UserController', ['$scope', 'userService', function($scope, userService) {
	var vm = this;
	vm.user = {id: null, userName: '', address: '', email: ''};
	vm.users = [];

	vm.fetchAllUsers = function() {
		userService.users().getAll()
		.then(
		  function(res) {
			  vm.users = res;
		  },
		  function(err) {
			  console.error('Error while fetching users' + err);
		  }
		);
	};

	vm.createUser = function(user) {
		userService.users().save(user)
		.$promise.then(
		  function(res) {
			  console.log("User was saved successfully");
		  },
		  function(err) {
			  console.error('Error while fetching users' + err);
		  }
		);
	};

	vm.updateUser = function(user, id) {
		userService.users().update({id: id}, user)
		.$promise.then(
		  function(res) {
			  console.log("User was updated successfully");
		  },
		  function(err) {
			  console.error('Error while fetching users' + err);
		  }
		);
	};

	vm.deleteUser = function(id) {
		userService.users().delete({id: id})
		.$promise.then(
		  function(res) {
			  console.log("User was saved successfully");
		  },
		  function(err) {
			  console.error('Error while fetching users' + err);
		  }
		);
	};

	vm.submit = function() {
		if (vm.user.id == null) {
			console.log('Saving New User', vm.user);
			vm.createUser(vm.user);
		}
		else {
			vm.updateUser(vm.user, vm.user.id);
			console.log('User updated with id ', vm.user.id);
		}
		vm.reset();
	};

	vm.edit = function(id) {
		console.log('id to be edited', id);
		for (var i = 0; i < vm.users.length; i++) {
			if (vm.users[i].id == id) {
				vm.user = angular.copy(vm.users[i]);
				break;
			}
		}
	};

	vm.remove = function(id) {
		for (var i = 0; i < vm.users.length; i++) {
			if (vm.users[i].id == id) {
				vm.reset();
				break;
			}
		}
		vm.deleteUser(id);
	};


	vm.reset = function() {
		vm.user = {id: null, username: '', address: '', email: ''};
		$scope.userForm.$setPristine();
	};

}]);