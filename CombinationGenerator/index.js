/**
 * Created by KH1868 on 9/27/2015.
 */
var app = angular.module('Generator',  ["ngSanitize", "ngCsv"]);

app.controller('defaultCtrl', function ($scope) {
    $scope.entities = [];

    $scope.createEntities = function () {
        $scope.entities = [];
        for (i = 0; i < $scope.numberOfEntities; i++) {
            $scope.entities[i] = {};
            $scope.entities[i].name = "";
            $scope.entities[i].values = [];
        }
    };

    $scope.popValue = function(entityIndex) {
        console.log("index: " + $scope.entities[entityIndex].values.length);
        $scope.entities[entityIndex].values.splice($scope.entities[entityIndex].values.length - 1, 1);
    };

    $scope.addValue = function (entityIndex) {
        var new_value = "";
        if (typeof $scope.entities[entityIndex].values === undefined) {
            $scope.entities[entityIndex].values = [];
            console.log("empty values");
        }
        console.log($scope.entities[entityIndex].values.length);
        $scope.entities[entityIndex].values.push(new_value);
    };

    $scope.createCombinations = function () {
        $scope.headers = [];
        $scope.combinations = [];
        console.log(JSON.stringify($scope.entities));
        for (entityIndex = 0; entityIndex < $scope.entities.length; entityIndex++) {
            $scope.headers.push($scope.entities[entityIndex].name);
        }


        console.log(JSON.stringify($scope.headers));
        var entityOne = $scope.entities[0].values;
        for (entityIndex = 1; entityIndex < $scope.entities.length; entityIndex++) {
            var entityTwo = $scope.entities[entityIndex].values;

            entityOne = $scope.permute(entityOne, entityTwo);

            /*for (valueIndexOne = 0; valueIndexOne < entityOne.values.length; valueIndexOne++) {
             $scope.combinations.values = [];
             for (valueIndexTwo = 0; valueIndexTwo < entityTwo.values.length; valueIndexTwo++) {
             var combination = [];
             combination.push(entityOne.values[valueIndexOne]);
             combination.push(entityTwo.values[valueIndexTwo]);
             $scope.combinations.values.push(combination);
             console.log(entityOne.values[valueIndexOne]);
             console.log(entityTwo.values[valueIndexTwo]);
             }
             }
             entityOne = $scope.combinations;*/
        }
        $scope.combinations = entityOne;
        console.log(JSON.stringify($scope.combinations));
    };

    $scope.permute = function (arrayOne, arrayTwo) {
        var result = [];
        for (arrayOneIndex = 0; arrayOneIndex < arrayOne.length; arrayOneIndex++) {
            for (arrayTwoIndex = 0; arrayTwoIndex < arrayTwo.length; arrayTwoIndex++) {
                var combination = [];
                if (typeof arrayOne[arrayOneIndex] === 'string') {
                    combination.push(arrayOne[arrayOneIndex]);
                    combination.push(arrayTwo[arrayTwoIndex]);
                }
                else {
                    for (innerIndex = 0; innerIndex < arrayOne[arrayOneIndex].length; innerIndex++) {
                        combination.push(arrayOne[arrayOneIndex][innerIndex]);
                    }
                    combination.push(arrayTwo[arrayTwoIndex]);
                }
                result.push(combination);
            }
        }
        return result;
    };

    $scope.getNumber = function (num) {
        var returnValue = [];
        for (i = 1; i <= num; i++) {
            returnValue.push(i);
        }
        return returnValue;
    }
});