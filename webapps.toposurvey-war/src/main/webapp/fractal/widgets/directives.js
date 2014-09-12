var module = angular.module('fractalWidgetsApp', []);

module.directive('contactarea', function() {
  return {
   restrict: 'E',
   transclude: false,
   templateUrl: 'contactArea/components.html'
 };
})


module.controller('ContactAreaCtrl', function ($scope, $http) {
  $scope.submitMessage = function() {
    var name = $scope.contactAreaName
    var subject = $scope.contactAreaSubject
    var message = $scope.contactAreaMessage
    var url = '/rest?name=' + name + '&subject=' + subject + '&message=' + message;
    alert(url);
    $http.get(url).success(function(data) {
      $scope.contactAreaName = '';
      $scope.contactAreaSubject = '';
      $scope.contactAreaMessage = '';
    });
  }
});
