define(['app'], function(app) {
    var injectParam = ['$scope'];
    var downloadIndexCtrl = function($scope) {
        $scope.initData = {
            "articles": [

                {
                    "id": 1,
                    "title": "Jenkins入门系列之—PDF文档下载",
                    "content": " Jenkins是一个开源软件项目，旨在提供一个开放易用的软件平台，使软件的持续集成变成可能。",
                    "createTime": "2017-08-09",
                    "looksCount": 201
                },
                {
                    "id": 1,
                    "title": "Jenkins入门系列之—PDF文档下载",
                    "content": " Jenkins是一个开源软件项目，旨在提供一个开放易用的软件平台，使软件的持续集成变成可能。",
                    "createTime": "2017-08-09",
                    "looksCount": 201
                },
                {
                    "id": 1,
                    "title": "Jenkins入门系列之—PDF文档下载",
                    "content": " Jenkins是一个开源软件项目，旨在提供一个开放易用的软件平台，使软件的持续集成变成可能。",
                    "createTime": "2017-08-09",
                    "looksCount": 201
                }
            ],
            "news": [{
                    "title": "最新咨询 ",
                    "items": [
                        { "title": "PHP 与 Kafka 连接与搭建", "url": "#" },
                        { "title": "SwooleDistributed框", "url": "#" }
                    ]

                },
                {
                    "title": "最新咨询 ",
                    "items": [
                        { "title": "PHP 与 Kafka 连接与搭建", "url": "#" },
                        { "title": "SwooleDistributed框", "url": "#" }
                    ]

                }
            ]
        };
        
    };

    downloadIndexCtrl.$inject = injectParam;
    app.register.controller("downloadIndexCtrl", downloadIndexCtrl);
});